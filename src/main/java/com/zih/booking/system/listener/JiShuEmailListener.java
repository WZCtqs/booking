package com.zih.booking.system.listener;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.zih.booking.request.BookingSpaceRequest;
import com.zih.booking.service.BusiClientsService;
import com.zih.booking.system.config.rabbitmq.JiShuSystemMq;
import com.zih.booking.utils.SendMailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

//集输 发送邮件
@Slf4j
@Component
public class JiShuEmailListener {
    @Autowired
    private BusiClientsService busiClientsService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value =JiShuSystemMq.JS_EMAIL_QUEUE, durable = "true"),
            exchange = @Exchange(value = JiShuSystemMq.JS_ORDER_TOPIC_EXCHANGE, durable = "true", type = "topic", ignoreDeclarationExceptions = "true"),
            key =JiShuSystemMq.JS_EMAIL_ROUTINGKEY
    ))
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String s = new String(message.getBody(), "UTF-8");
            log.info("在线订舱-->发送邮件通知集输------>MQ接收到的数据：" + s);
            BookingSpaceRequest bookingSpaceRequest = JSONObject.parseObject(s, BookingSpaceRequest.class);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            String[] email = {"lihj@zih718.com","sunyk@zih718.com"};//
            String contionerType = ("0".equals(bookingSpaceRequest.getIsConsolidation())) ? "整箱" : "拼箱";
            String text = "<p>订舱系统提醒：</p>\n" +
                    "<p>班列日期:" + format.format(bookingSpaceRequest.getClassDate()) + "</p>" +
                    "<p>货物品名：" + bookingSpaceRequest.getGoodsName() + "</p>" +
                    "<p>箱型：" + contionerType + "</p>" +
                    "<p>推荐人:" + bookingSpaceRequest.getClientTjr() + "</p>" +
                    "<p>订舱单位:" + bookingSpaceRequest.getClientUnit() + "</p>" +
                    "<p>提货费报价编号:" + bookingSpaceRequest.getInquiryNum() + "</p>" +
                    "<p>订舱人:" + bookingSpaceRequest.getClientContacts() + "</p>" +
                    "<p>请注意查收</p>";
            String subject = "订舱系统-客户委托书信息";//邮件主题   *
            SendMailUtil.sendHtmlMail(email, text, subject);
        } catch (Exception e) {//UnsupportedEncoding
            // TODO Auto-generated catch block
            log.info("集输发送邮件------>MQ接收到的数据：啊！我失败了");
            log.error(e.getMessage(), e);
        }
    }
}
