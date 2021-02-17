package com.zih.booking.system.config;

import com.zih.booking.model.SysFailMq;
import com.zih.booking.service.SysFailMqService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @Description :
 * @Author : wangzhichao
 * @Date: 2020-12-11 09:55
 */
@Component
public class RabbitmqConfirmAndReturn implements  RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    SysFailMqService sysFailMqService;

    @PostConstruct
    public void init() {
        //指定 ConfirmCallback
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("消息发送成功：" + correlationData);
        } else {
            System.out.println("消息发送失败：" + s);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        SysFailMq mq = new SysFailMq();
        mq.setBody(new String(message.getBody()));
        mq.setCode(replyCode);
        mq.setText(replyText);
        mq.setExchange(exchange);
        mq.setRoutingkey(routingKey);
        mq.setCreateTime(new Date());
        sysFailMqService.insert(mq);
    }
}
