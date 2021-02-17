package com.zih.booking.system.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//账号注册 忘记密码
@Configuration
public class RegisterMq {
    public static final String REGISTER_TOPIC_EXCHANGE = "customer.register.topic.exchange";
    public static final String REGISTER_QUEUE = "customer_system_register";
    public static final String REGISTER_ROUTINGKEY = "customs_register_password";
    @Bean
    public TopicExchange registerTopicExchange(){
        return new TopicExchange(REGISTER_TOPIC_EXCHANGE,true,false);
    }
    @Bean
    public Queue registerQueue(){
        return new Queue(REGISTER_QUEUE,true);
    }
    @Bean
    public Binding registerBinding(){
        return BindingBuilder.bind(registerQueue()).to(registerTopicExchange()).with(REGISTER_ROUTINGKEY);
    }

    public static final String CLIENT_PW_TOPIC_EXCHANGE = "client.pw.topic.exchange";

    public static final String CLIENT_PW_QUEUE = "client.pw.queue";

    public static final String CLIENT_PW_ROUTINGKEY = "client_pw_routingkey";
    @Bean
    public TopicExchange clientPwTopicExchange(){
        return new TopicExchange(CLIENT_PW_TOPIC_EXCHANGE,true,false);
    }
    @Bean
    public Queue clientPwQueue(){
        return new Queue(CLIENT_PW_QUEUE,true);
    }
    @Bean
    public Binding clientPwBinding(){
        return BindingBuilder.bind(clientPwQueue()).to(clientPwTopicExchange()).with(CLIENT_PW_ROUTINGKEY);
    }
}
