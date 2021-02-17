package com.zih.booking.system.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//运踪发邮件
@Configuration
public class TrainEmailMq {
    public static final String TRAIN_EMAIL_TOPIC_EXCHANGE = "customer.train.email.topic.exchange";
    public static final String TRAIN_EMAIL_QUEUE = "customer.train.email.topic.queue";
    public static final String TRAIN_EMAIL_ROUTINGKEY = "customer.train.send.email";

    @Bean
    public TopicExchange trainEmailTopicExchange() {
        return new TopicExchange(TRAIN_EMAIL_TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public Queue trainEmailQueue() {
        return new Queue(TRAIN_EMAIL_QUEUE, true);
    }

    @Bean
    public Binding trainEmailBinding() {
        return BindingBuilder.bind(trainEmailQueue()).to(trainEmailTopicExchange()).with(TRAIN_EMAIL_ROUTINGKEY);
    }
}
