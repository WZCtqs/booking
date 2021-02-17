package com.zih.booking.system.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.zih.booking.system.config.rabbitmq.TrainEmailMq;
import com.zih.booking.utils.SendMailUtil;
import com.zih.booking.vo.EmaiMqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class TrainEmailListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = TrainEmailMq.TRAIN_EMAIL_QUEUE, durable = "true"),
            exchange = @Exchange(value = TrainEmailMq.TRAIN_EMAIL_TOPIC_EXCHANGE, durable = "true", type = "topic", ignoreDeclarationExceptions = "true"),
            key = TrainEmailMq.TRAIN_EMAIL_ROUTINGKEY
    ))
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String s = new String(message.getBody(), "UTF-8");
            EmaiMqVo emaiMqVo = JSONObject.parseObject(s, EmaiMqVo.class);
            String[] email = {emaiMqVo.getReceiveEmail()};
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            SendMailUtil.sendTrackMail(emaiMqVo.getAccount(), emaiMqVo.getPassword(), email,emaiMqVo.getSubject(),emaiMqVo.getContent());
        } catch (Exception e) {
            log.info("用户注册------>MQ接收到的数据：啊！我失败了");
            log.error(e.getMessage(), e);
        }
    }
}
