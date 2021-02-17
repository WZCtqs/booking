package com.zih.booking.system.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//集输
@Configuration
public class JiShuSystemMq {
    //在线订舱发送托书给集疏
    public static final String JS_ORDER_TOPIC_EXCHANGE = "js.system.booking.exchange";
    public static final String JS_ORDER_QUEUE = "js_system_customs_booking";
    public static final String JS_ORDER_ROUTINGKEY = "customs.booking.space.all";
    //在线订舱发给老订舱
    public static final String ODC_ORDER_TOPIC_EXCHANGE = "odc.system.booking.exchange";
    public static final String ODC_ORDER_QUEUE = "odc_system_customs_booking";
    public static final String ODC_ORDER_ROUTINGKEY = "customs.booking.space.odc";

    public static final String ODC_ORDER_QUEUE2 = "odc_system_customs_booking2";
    //public static final String ODC_ORDER_ROUTINGKEY2 = "2customs.booking.space.odc2";
    //在线订舱发送邮件
    public static final String JS_EMAIL_QUEUE = "js_system_customs_email";
    public static final String JS_EMAIL_ROUTINGKEY = "customs.booking.email.send";

    //托书发送箱管审核
    public static final String XG_ORDER_TOPIC_EXCHANGE = "xg.system.check.exchange";
    public static final String XG_ORDER_ROUTINGKEY = "xg.system.topic.check.*";
    //public static final String XG_ORDER_QUEUE = "xg_system_check_queue_blpt";

    @Bean
    public TopicExchange jsTopicExchange() {
        return new TopicExchange(JS_ORDER_TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public Queue jsQueue() {
        return new Queue(JS_ORDER_QUEUE, true);
    }

    @Bean
    public Binding jsBinding() {
        return BindingBuilder.bind(jsQueue()).to(jsTopicExchange()).with(JS_ORDER_ROUTINGKEY);
    }

    @Bean
    public TopicExchange odcTopicExchange() {
        return new TopicExchange(ODC_ORDER_TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public Queue odcQueue() {
        return new Queue(ODC_ORDER_QUEUE, true);
    }

    @Bean
    public Binding odcBinding() {
        return BindingBuilder.bind(odcQueue()).to(odcTopicExchange()).with(ODC_ORDER_ROUTINGKEY);
    }
    @Bean
    public Queue odcQueue2() {
        return new Queue(ODC_ORDER_QUEUE2, true);
    }

//    @Bean
//    public Binding odcBinding2() {
//        return BindingBuilder.bind(odcQueue2()).to(odcTopicExchange()).with(ODC_ORDER_ROUTINGKEY2);
//    }
    @Bean
    public Queue jsEmailQueue() {
        return new Queue(JS_EMAIL_QUEUE, true);
    }
    @Bean
    public Binding jsBinding2() {
        return BindingBuilder.bind(jsEmailQueue()).to(jsTopicExchange()).with(JS_EMAIL_ROUTINGKEY);
    }

}
