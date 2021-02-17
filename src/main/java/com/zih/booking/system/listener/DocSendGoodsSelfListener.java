package com.zih.booking.system.listener;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rabbitmq.client.Channel;
import com.zih.booking.bo.BoxingPhotoExamineBO;
import com.zih.booking.bo.GwczBoxingMsgBO;
import com.zih.booking.dao.BusiShippingorderMapper;
import com.zih.booking.dao.SysMessageMapper;
import com.zih.booking.model.BusiShippingorder;
import com.zih.booking.model.DocOrderDocument;
import com.zih.booking.model.DocOrderInstation;
import com.zih.booking.model.SysMessage;
import com.zih.booking.service.DocOrderDocumentService;
import com.zih.booking.service.DocOrderInstationService;
import com.zih.booking.system.config.rabbitmq.SendGoodSelfMq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Description : 自送货功能监听
 * @Author : wangzhichao
 * @Date: 2020-12-18 17:28
 */
@Slf4j
@Component
public class DocSendGoodsSelfListener {

    @Autowired
    private DocOrderDocumentService docOrderDocumentService;

    @Autowired
    private BusiShippingorderMapper busiShippingorderMapper;

    @Autowired
    private DocOrderInstationService instationService;

    @Autowired
    private SysMessageMapper sysMessageMapper;

    /**
     * 监听拼箱系统 返回去程托书审核结果
     *
     * @param message
     * @param channel
     */
    @RabbitListener(queues = SendGoodSelfMq.BOXINGPHOTO_PX_EXAMINE_QUEUE_BLPT)
    public void getPxExamine(Message message, Channel channel) {
        try {
            String msg = new String(message.getBody());
            log.info("拼箱返回去程托书审核结果" + msg);
            BoxingPhotoExamineBO result = JSONObject.parseObject(msg, BoxingPhotoExamineBO.class);
            DocOrderDocument document = new DocOrderDocument();
            document.setUploadTime(new Date());
            if (result.getPhotoType() != null && result.getPhotoType() == 1) {
                //审核失败，通知客户
                document.setBoxingphotoFail(result.getPhotoFail());
                document.setBoxingphotoStatus(1);
                // 通知客户适合失败结果
                BusiShippingorder shippingorders = busiShippingorderMapper.selectClientByOrderId(result.getOrderId());
                SysMessage sysMessage = new SysMessage();
                sysMessage.setClientId(shippingorders.getClientId());
                sysMessage.setOrderId(shippingorders.getOrderId());
                sysMessage.setOrderNumber(shippingorders.getOrderNumber());
                sysMessage.setMessageTitle("装箱号审核结果");
                sysMessage.setMessageContent("托书编号为: " + shippingorders.getOrderNumber() + "的" + result.getContainerNo() + "装箱照审核失败，请重新上传");
                sysMessage.setMessageType("单证");
                sysMessage.setCreateTime(new Date());
                sysMessageMapper.insert(sysMessage);
            } else if (result.getConType() != null && result.getConType() == 1) {
                //审核失败，通知客户
                document.setContainerFail(result.getConFail());
                document.setContainerStatus(1);
                // 通知客户适合失败结果
                BusiShippingorder shippingorders = busiShippingorderMapper.selectClientByOrderId(result.getOrderId());
                SysMessage sysMessage = new SysMessage();
                sysMessage.setClientId(shippingorders.getClientId());
                sysMessage.setOrderId(shippingorders.getOrderId());
                sysMessage.setOrderNumber(shippingorders.getOrderNumber());
                sysMessage.setMessageTitle("箱号铅封号审核结果");
                sysMessage.setMessageContent("托书编号为: " + shippingorders.getOrderNumber() + "的" + result.getContainerNo() + "箱号铅封号审核失败，请重新确认");
                sysMessage.setMessageType("单证");
                sysMessage.setCreateTime(new Date());
                sysMessageMapper.insert(sysMessage);
            }
            docOrderDocumentService.update(document, new EntityWrapper<DocOrderDocument>()
                    .eq("order_id", result.getOrderId())
                    .eq("file_type_key", "boxing_photo_file")
                    .eq("container_no", result.getContainerNo()));

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听大口岸发送回程托书装箱照审核结果
     *
     * @param message
     * @param channel
     */
    @RabbitListener(queues = SendGoodSelfMq.BOXINGPHOTO_DKA_EXAMINE_QUEUE_BLPT)
    public void getDkaExamine(Message message, Channel channel) {
        try {
            log.info("大口岸返回回程托书审核结果" + message);
            String msg = new String(message.getBody());
            BoxingPhotoExamineBO result = JSONObject.parseObject(msg, BoxingPhotoExamineBO.class);
            DocOrderDocument document = new DocOrderDocument();
            document.setUploadTime(new Date());
            if (result.getAuditState() != null && result.getAuditState().equals("3")) {
                //审核失败，通知客户
                document.setBoxingphotoFail(result.getPhotoFail());
                document.setBoxingphotoStatus(1);
            }
            if (result.getAuditState() != null && result.getAuditState().equals("2")) {
                //审核成功
                document.setBoxingphotoStatus(2);
            }
            docOrderDocumentService.update(document, new EntityWrapper<DocOrderDocument>()
                    .eq("order_id", result.getOrderId())
                    .eq("container_no", result.getContainerNo())
                    .eq("file_type_key", "boxing_photo_file"));

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听国外场站发送回程托书箱号 铅封号审核结果
     *
     * @param message
     * @param channel
     */
    @RabbitListener(queues = SendGoodSelfMq.BOXINGPHOTO_GWCZ_EXAMINE_QUEUE_BLPT)
    public void getGwczExamine(Message message, Channel channel) {
        try {
            log.info("国外场站发送回程托书箱号 铅封号审核结果" + message);
            String msg = new String(message.getBody());
            BoxingPhotoExamineBO result = JSONObject.parseObject(msg, BoxingPhotoExamineBO.class);
            DocOrderDocument document = new DocOrderDocument();
            document.setUploadTime(new Date());
            if (result.getPhotoType() == 1) {
                //审核失败，通知客户
                document.setContainerFail(result.getConFail());
                document.setContainerStatus(1);
            } else {
                //审核成功
                document.setContainerStatus(2);
            }
            docOrderDocumentService.update(document, new EntityWrapper<DocOrderDocument>()
                    .eq("order_id", result.getOrderId())
                    .eq("container_no", result.getContainerNo()));

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = SendGoodSelfMq.SENDGOODSSELF_CONINFO_QUEUE_GWCZ_BLPT)
    public void getGwczBoxingPhoto(Message message, Channel channel) {
        String msg = new String(message.getBody());
        log.info("国外场站发送拼箱装箱照数据" + msg);
        try {
            GwczBoxingMsgBO boxingMsgBO = JSONObject.parseObject(msg, GwczBoxingMsgBO.class);
            // 根据箱号和班列编号查询到站信息表获取拼箱托书信息
            List<DocOrderInstation> instationList = instationService.getDocOrderInstByConNo(boxingMsgBO.getContainerNo(), boxingMsgBO.getClassnumber());
            for (DocOrderInstation inst : instationList) {
                docOrderDocumentService.delete(new EntityWrapper<DocOrderDocument>()
                        .eq("order_id",inst.getOrderId())
                        .eq("container_no",boxingMsgBO.getContainerNo())
                        .eq("form_system","gwcz")
                        .eq("file_type_key","boxing_photo_file"));
                DocOrderDocument document = new DocOrderDocument();
                document.setFileTypeKey("boxing_photo_file");
                document.setCreateTime(new Date());
                document.setUploadTime(new Date());
                document.setContainerStatus(2);
                document.setBoxingphotoStatus(2);
                document.setContainerNo(boxingMsgBO.getContainerNo());
                document.setOrderId(inst.getOrderId());
                document.setOrderNumber(inst.getOrderNumber());
                document.setFormSystem("gwcz");
                StringBuilder fileName = new StringBuilder();
                StringBuilder fileUrl = new StringBuilder();
                for (int i = 0; i < boxingMsgBO.getContainerImgUrl().size(); i++) {
                    if (i == 0) {
                        fileName.append(boxingMsgBO.getContainerImgUrl().get(i).getFileName());
                        fileUrl.append(boxingMsgBO.getContainerImgUrl().get(i).getFileUrl());
                    } else {
                        fileName.append(";").append(boxingMsgBO.getContainerImgUrl().get(i).getFileName());
                        fileUrl.append(";").append(boxingMsgBO.getContainerImgUrl().get(i).getFileUrl());
                    }
                }
                document.setFileUrl(fileUrl.toString());
                document.setFileName(fileName.toString());
                docOrderDocumentService.insert(document);
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
