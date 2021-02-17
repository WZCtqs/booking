
package com.zih.booking.system.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.zih.booking.service.BusiShippingorderService;
import com.zih.booking.vo.Cabin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//箱管空箱订舱
@Slf4j
@Component
public class JiShuBookingListener {
    @Autowired
    private BusiShippingorderService busiShippingorderService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "xg_order_kx_queue_blpt", durable = "true"),
            exchange = @Exchange(value = "xg.order.topic.exchange", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"),
            key = "xg.system.order.*"
    ))
    public void onMessage(Message message, Channel channel) throws Exception {
        try {

            String s = new String(message.getBody(), "UTF-8");
            log.debug("箱管空箱订舱------>MQ接收到的数据："+s);
            Cabin cabin =JSONObject.parseObject(s,Cabin.class);
            log.debug("箱管空箱订舱------>Cabin实体：\n{}",cabin);

            // 订舱成功，确认消息
            if(busiShippingorderService.xgAdd(cabin)){

                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            }
        } catch (Exception e) {//UnsupportedEncoding
            // TODO Auto-generated catch block
            log.debug("箱管空箱订舱------>MQ接收到的数据：啊！我失败了" );
            log.error(e.getMessage(), e);
        }
    }

}

