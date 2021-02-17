package com.zih.booking.system.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.zih.booking.dao.DocOrderMapper;
import com.zih.booking.model.DocOrder;
import com.zih.booking.model.DocOrderDocument;
import com.zih.booking.service.DocOrderDocumentService;
import com.zih.booking.vo.BgFileVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Slf4j
@Component
public class BgcdListener{

    @Autowired
    private DocOrderDocumentService docOrderDocumentService;
    @Autowired
    private DocOrderMapper docOrderMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "gw_client_draft_queue", durable = "true"),
            exchange = @Exchange(value = "gw_system_draft_topic_exchange", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"),
            key = "gw_system_draft.cp"
    ))
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            Date nowDate = new Date();
            String s = new String(message.getBody(), "UTF-8");
            log.debug("关务系统 报关草单------>MQ接收到的数据："+s);
            BgFileVo bgFileVo =JSONObject.parseObject(s,BgFileVo.class);
            log.debug("关务系统 报关草单------>BgFileVo实体：\n{}",bgFileVo);
            DocOrderDocument  orderDocument=new DocOrderDocument();
            BeanUtils.copyProperties(bgFileVo,orderDocument);
            orderDocument.setFormSystem("关务系统");
            orderDocument.setFileTypeKey("declare_customs_draft_file");
            orderDocument.setUploadTime(nowDate);
            orderDocument.setCreateTime(nowDate);
            // 订舱成功，确认消息
            if(docOrderDocumentService.insert(orderDocument)){
                /*更新单证状态*/
                DocOrder docOrder = new DocOrder();
                docOrder.setOrderId(orderDocument.getOrderId());
                docOrder.setFileTypeKey(orderDocument.getFileTypeKey());
                docOrder.setActualSupply(1);
                docOrder.setActualSupplyTime(nowDate);
                docOrderMapper.updateActualSupply(docOrder);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {//UnsupportedEncoding
            // TODO Auto-generated catch block
            log.debug("关务系统 报关草单------>MQ接收到的数据：啊！我失败了" );
            log.error(e.getMessage(), e);
        }
    }
}
