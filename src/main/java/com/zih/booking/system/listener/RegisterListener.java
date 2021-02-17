package com.zih.booking.system.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.zih.booking.model.BusiClients;
import com.zih.booking.service.BusiClientsService;
import com.zih.booking.system.config.rabbitmq.RegisterMq;
import com.zih.booking.utils.SendMailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;


//忘记密码 发送邮件
@Slf4j
@Component
public class RegisterListener {
    @Autowired
    private BusiClientsService busiClientsService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value =RegisterMq.REGISTER_QUEUE, durable = "true"),
            exchange = @Exchange(value = RegisterMq.REGISTER_TOPIC_EXCHANGE, durable = "true", type = "topic", ignoreDeclarationExceptions = "true"),
            key =RegisterMq.REGISTER_ROUTINGKEY
    ))
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String s = new String(message.getBody(), "UTF-8");
            log.info("用户注册-->发送邮件------>MQ接收到的数据："+s);
            BusiClients busiClients   =JSONObject.parseObject(s,BusiClients.class);
            log.info("用户注册-->发送邮件------>发送一封信的邮件 收件人："+busiClients.getClientEmail() );
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            String[]email={busiClients.getClientEmail()};
            String text="<p>尊敬的客户，您好/Dear customer：</p>\n" +
                    "<p>您的账号是:/Your username is:</p>"+busiClients.getClientLoginuser()+"\n\n\n"+
                    "<p>您的新密码是:/Your password is:</p>"+busiClients.getClientLoginpwd()+"\n\n\n";
            String subject = "Your registration password";//邮件主题   *
            SendMailUtil.sendHtmlMail(email,text,subject);
        } catch (Exception e) {
            log.info("用户注册------>MQ接收到的数据：啊！我失败了" );
            log.error(e.getMessage(), e);
        }
    }
}
