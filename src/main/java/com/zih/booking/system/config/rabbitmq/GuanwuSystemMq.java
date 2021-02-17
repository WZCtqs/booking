package com.zih.booking.system.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//提交报关材料时发送给关务系统
@Configuration
public class GuanwuSystemMq {

    public static final String GW_TOPIC_EXCHANGE = "gw.system.files.topic.exchange";
    /*报关材料*/
    /*关务系统*/
    public static final String GW_QUEUE = "gw_system_customs_apply_filec";
    /*国外场站*/
    public static final String GWCZ_QUEUE = "gwcz_system_customs_apply_filec";
    /*拼箱场站*/
//    public static final String PX_QUEUE = "px_system_customs_apply_filec";
    public static final String GW_ROUTINGKEY = "customs_apply_filec";

    @Bean
    public TopicExchange gwTopicExchange() {
        return new TopicExchange(GW_TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public Queue gwQueue() {
        return new Queue(GW_QUEUE, true);
    }

    @Bean
    public Binding gwBinding() {
        return BindingBuilder.bind(gwQueue()).to(gwTopicExchange()).with(GW_ROUTINGKEY);
    }

    @Bean
    public Queue gwczQueue() {
        return new Queue(GWCZ_QUEUE, true);
    }

    @Bean
    public Binding gwczBinding() {
        return BindingBuilder.bind(gwczQueue()).to(gwTopicExchange()).with(GW_ROUTINGKEY);
    }
//    @Bean
//    public Queue pxQueue() {
//        return new Queue(PX_QUEUE, true);
//    }

//    @Bean
//    public Binding pxBinding() {
//        return BindingBuilder.bind(pxQueue()).to(gwTopicExchange()).with(GW_ROUTINGKEY);
//    }

    /*报关材料正本*/
    public static final String GW_QUEUE_DECLARE_FORMAL = "gw_system_declare_formal";
    public static final String GW_ROUTINGKEY_FORMAL = "declare_customs_formal";

    @Bean
    public Queue gwQueueDeclare() {
        return new Queue(GW_QUEUE_DECLARE_FORMAL, true);
    }

    @Bean
    public Binding gwBindingDeclare() {
        return BindingBuilder.bind(gwQueueDeclare()).to(gwTopicExchange()).with(GW_ROUTINGKEY_FORMAL);
    }

    /*关务报关草单*/
    public static final String GW_QUEUE_DRAFT_CONFIRM = "gw_client_draft_confirm";
    public static final String GW_CONFIRM_KRY = "gw_system_draft.confirm";

    @Bean
    public Queue gwQueueConfirm() {
        return new Queue(GW_QUEUE_DRAFT_CONFIRM, true);
    }

    @Bean
    public Binding gwConfirmBindingDeclare() {
        return BindingBuilder.bind(gwQueueConfirm()).to(gwTopicExchange()).with(GW_CONFIRM_KRY);
    }
}
