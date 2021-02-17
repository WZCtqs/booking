package com.zih.booking.system.listener;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zih.booking.dao.BusiShippingorderMapper;
import com.zih.booking.dao.LadingBillMapper;
import com.zih.booking.dao.SysMessageMapper;
import com.zih.booking.model.BusiShippingorder;
import com.zih.booking.model.DocOrderDocument;
import com.zih.booking.model.LadingBill;
import com.zih.booking.model.SysMessage;
import com.zih.booking.service.DocOrderDocumentService;
import com.zih.booking.service.LadingBillService;
import com.zih.booking.system.config.rabbitmq.TiDanSystemMq;
import com.zih.booking.utils.email.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.core.Message;
import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

//监听大口岸提单草单
@Slf4j
@Component
public class TiDanListener {
    @Autowired
    private LadingBillService ladingBillService;
    @Autowired
    private LadingBillMapper ladingBillMapper;
    @Autowired
    private BusiShippingorderMapper busiShippingorderMapper;
    @Autowired
    private SysMessageMapper sysMessageMapper;
    @Autowired
    private DocOrderDocumentService docOrderDocumentService;

    @Autowired
    private IMailService mailService;

    //如果没有可以自动创建交换机、队列、绑定、路由
    @RabbitListener(queues = TiDanSystemMq.TD_QUEUE)
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String s = new String(message.getBody(), "UTF-8");
            log.info("提单消费端------>MQ接收到的数据："+s);
            LadingBill ladingBill=JSONObject.parseObject(s,LadingBill.class);
            ladingBill.setOrderId(StringUtils.isEmpty(ladingBill.getOrderId())?ladingBillMapper.getOrderIdByNumbers(ladingBill.getLbNumber()):ladingBill.getOrderId());
            ladingBill.setUpdateTime(new Date());
            ladingBill.setLbUrl1(null);
            if("6".equals(ladingBill.getLbState())){
                ladingBill.setLbReason1(null);
            }
            if("1".equals(ladingBill.getLbLetterState())){
                ladingBill.setLbReason2(null);
            }
            ladingBillService.insertOrUpdate(ladingBill);
            log.info("提单消费端------>添加/修改了一条提单数据" );
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            if("6".equals(ladingBill.getLbState())){
                List<BusiShippingorder> shippingorders = busiShippingorderMapper.selectClientByOrderNumber(ladingBill.getLbNumber());
                if(shippingorders.size() == 1) {
                    SysMessage sysMessage = new SysMessage();
                    sysMessage.setClientId(shippingorders.get(0).getClientId());
                    sysMessage.setOrderId(shippingorders.get(0).getOrderId());
                    sysMessage.setOrderNumber(shippingorders.get(0).getOrderNumber());
                    sysMessage.setMessageTitle("提单制作提醒");
                    sysMessage.setMessageContent("托书编号为: " + shippingorders.get(0).getOrderNumber() + "的提单草单已审核成功，请及时选择提单类型");
                    sysMessage.setMessageType("单证");
                    sysMessage.setCreateTime(new Date());
                    sysMessageMapper.insert(sysMessage);
                    try {
                        String emails = shippingorders.get(0).getClientEmail()
                                .replace(";", ",")
                                .replace("|", ",")
                                .replace("/", ",")
                                .replace("；", ",");
                        String[] emailArr = emails.split(",");
                        mailService.sendSimpleMail(emailArr, sysMessage.getMessageTitle(), sysMessage.getMessageContent());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            else if("1".equals(ladingBill.getLbState())){
                List<BusiShippingorder> shippingorders = busiShippingorderMapper.selectClientByOrderNumber(ladingBill.getLbNumber());
                SysMessage sysMessage = new SysMessage();
                sysMessage.setClientId(shippingorders.get(0).getClientId());
                sysMessage.setOrderId(shippingorders.get(0).getOrderId());
                sysMessage.setOrderNumber(shippingorders.get(0).getOrderNumber());
                sysMessage.setMessageTitle("提单制作提醒");
                sysMessage.setMessageContent("托书编号为: " + shippingorders.get(0).getOrderNumber() + "的提单草单已生成，请及时确认");
                sysMessage.setMessageType("单证");
                sysMessage.setCreateTime(new Date());
                sysMessageMapper.insert(sysMessage);
                DocOrderDocument docOrderDocument = new DocOrderDocument();
                docOrderDocument.setUploadTime(new Date());
                docOrderDocument.setIsSubmit(0);
                docOrderDocument.setIsChoose(0);
                docOrderDocumentService.update(docOrderDocument, new EntityWrapper<DocOrderDocument>().eq("order_id", ladingBill.getOrderId()));
            }
            else if("5".equals(ladingBill.getLbState())){
                DocOrderDocument docOrderDocument = new DocOrderDocument();
                docOrderDocument.setUploadTime(new Date());
                docOrderDocument.setIsSubmit(0);
                docOrderDocument.setIsChoose(0);
                docOrderDocumentService.update(docOrderDocument, new EntityWrapper<DocOrderDocument>().eq("order_id", ladingBill.getOrderId()));
                List<BusiShippingorder> shippingorders = busiShippingorderMapper.selectClientByOrderNumber(ladingBill.getLbNumber());
                if(shippingorders.size() == 1) {
                    SysMessage sysMessage = new SysMessage();
                    sysMessage.setClientId(shippingorders.get(0).getClientId());
                    sysMessage.setOrderId(shippingorders.get(0).getOrderId());
                    sysMessage.setOrderNumber(shippingorders.get(0).getOrderNumber());
                    sysMessage.setMessageTitle("草单审核通知");
                    sysMessage.setMessageContent("托书编号为: " + shippingorders.get(0).getOrderNumber() + "的草单审核失败！失败原因：" + ladingBill.getLbReason1());
                    sysMessage.setMessageType("单证");
                    sysMessage.setCreateTime(new Date());
                    sysMessageMapper.insert(sysMessage);
                }
            }
            if("0".equals(ladingBill.getLbLetterState())){
                List<BusiShippingorder> shippingorders = busiShippingorderMapper.selectClientByOrderNumber(ladingBill.getLbNumber());
                docOrderDocumentService.deleteByOrderIdAndFileTypeKey(ladingBill.getOrderId(), "letter_guarantee_filec");
                if(shippingorders.size() == 1) {
                    SysMessage sysMessage = new SysMessage();
                    sysMessage.setClientId(shippingorders.get(0).getClientId());
                    sysMessage.setOrderId(shippingorders.get(0).getOrderId());
                    sysMessage.setOrderNumber(shippingorders.get(0).getOrderNumber());
                    sysMessage.setMessageTitle("电放保函审核通知");
                    sysMessage.setMessageContent("托书编号为: " + shippingorders.get(0).getOrderNumber() + "的电放保函失败！失败原因：" + ladingBill.getLbReason2());
                    sysMessage.setMessageType("单证");
                    sysMessage.setCreateTime(new Date());
                    sysMessageMapper.insert(sysMessage);
                }
            }
            else if("1".equals(ladingBill.getLbLetterState())){
                List<BusiShippingorder> shippingorders = busiShippingorderMapper.selectClientByOrderNumber(ladingBill.getLbNumber());
                if(shippingorders.size() == 1) {
                    SysMessage sysMessage = new SysMessage();
                    sysMessage.setClientId(shippingorders.get(0).getClientId());
                    sysMessage.setOrderId(shippingorders.get(0).getOrderId());
                    sysMessage.setOrderNumber(shippingorders.get(0).getOrderNumber());
                    sysMessage.setMessageTitle("电放保函审核通知");//托书编号为XXX的提单草单已生成，请及时确认
                    sysMessage.setMessageContent("托书编号为: " + shippingorders.get(0).getOrderNumber() + "的电放保函已审核通过！");
                    sysMessage.setMessageType("单证");
                    sysMessage.setCreateTime(new Date());
                    sysMessageMapper.insert(sysMessage);
                }
            }
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            log.info("提单消费端------>MQ接收到的数据：啊！我失败了" );
            log.error(e.getMessage(), e);
        }
    }

    @RabbitListener(queues = TiDanSystemMq.CLIENT_PICKGOODS_DRIVER_QUEUE_BLPT)
    public void getDriverInfo(Message message, Channel channel) throws Exception {
        String s = new String(message.getBody(), "UTF-8");
        log.info("接收托书提货司机信息："+s);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
