
package com.zih.booking.system.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.zih.booking.service.BusiShippingorderService;
import com.zih.booking.vo.GwczBookingVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 拼箱大柜订舱
@Slf4j
@Component
public class GwczBookingListener {

    @Autowired
    private BusiShippingorderService busiShippingorderService;

//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "px_dingcang_queue_gwcz", durable = "true"),
//            exchange = @Exchange(value = "gwcz.order.topic.exchange", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"),
//            key = "gwcz.system.order.*"
//    ))
    public void onMessage(Message message, Channel channel) throws Exception {
        try {

            String s = new String(message.getBody(), "UTF-8");
            log.debug("国外场站拼箱订舱------>MQ接收到的数据："+s);
            GwczBookingVo gwczBookingVo =JSONObject.parseObject(s,GwczBookingVo.class);
            log.debug("国外场站拼箱订舱------>GwczBookingVo实体：\n{}",gwczBookingVo);

            // 订舱成功，确认消息
            if(busiShippingorderService.gwczAdd(gwczBookingVo)){

                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            }
        } catch (Exception e) {//UnsupportedEncoding
            // TODO Auto-generated catch block
            log.debug("国外场站拼箱订舱------>MQ接收到的数据：啊！我失败了" );
            log.error(e.getMessage(), e);
        }
    }

}

