package com.zih.booking.system.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description : 自送货队列配置
 * @Author : wangzhichao
 * @Date: 2020-12-19 16:54
 */
@Configuration
public class SendGoodSelfMq {

    //交换机
    public static final String SENDGOODSSELF_TOPIC_EXCHANGE = "sendgoodsself_topic_exchange";

    //国内拼箱场站队列
    public static final String SENDGOODSSELF_CONINFO_QUEUE_PX = "sendgoodsself_coninfo_px";

    public static final String SENDGOODSSELF_CONINFO_QUEUE_XXYO = "sendgoodsself_coninfo_xxyo";

    public static final String SENDGOODSSELF_CONINFO_KEY_PX = "sendgoodsself_coninfo_key.#";
    //大口岸队列
    public static final String SENDGOODSSELF_CONINFO_QUEUE_DKA = "sendgoodsself_coninfo_dka";

    public static final String SENDGOODSSELF_CONINFO_QUEUE_GWCZ = "sendgoodsself_coninfo_gwcz";

    public static final String SENDGOODSSELF_CONINFO_QUEUE_GW = "sendgoodsself_coninfo_gw";

    // 国外场站发送箱封、装箱照信息
    public static final String SENDGOODSSELF_CONINFO_QUEUE_GWCZ_DKA = "sendgoodsself_coninfo_gwcz_dka";

    public static final String SENDGOODSSELF_CONINFO_QUEUE_GWCZ_BLPT = "sendgoodsself_coninfo_gwcz_blpt";

    public static final String SENDGOODSSELF_CONINFO_GWCZ_KEY_GWCZ = "sendgoodsself_coninfo_gwcz_key.#";

    @Bean
    public TopicExchange sendGoodsSelfTopicExchange() {
        return new TopicExchange(SENDGOODSSELF_TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public Queue sendGoodsSelfQueuePX() {
        return new Queue(SENDGOODSSELF_CONINFO_QUEUE_PX, true);
    }

    @Bean
    public Binding sendGoodsSelfBindingPX() {
        return BindingBuilder.bind(sendGoodsSelfQueuePX()).to(sendGoodsSelfTopicExchange()).with(SENDGOODSSELF_CONINFO_KEY_PX);
    }
    @Bean
    public Queue sendGoodsSelfQueueXXYO() {
        return new Queue(SENDGOODSSELF_CONINFO_QUEUE_XXYO, true);
    }

    @Bean
    public Binding sendGoodsSelfBindingXXYO() {
        return BindingBuilder.bind(sendGoodsSelfQueueXXYO()).to(sendGoodsSelfTopicExchange()).with(SENDGOODSSELF_CONINFO_KEY_PX);
    }

    @Bean
    public Queue sendGoodsSelfQueueDKA() {
        return new Queue(SENDGOODSSELF_CONINFO_QUEUE_DKA, true);
    }

    @Bean
    public Binding sendGoodsSelfBindingDKA() {
        return BindingBuilder.bind(sendGoodsSelfQueueDKA()).to(sendGoodsSelfTopicExchange()).with(SENDGOODSSELF_CONINFO_KEY_PX);
    }

    @Bean
    public Queue sendGoodsSelfQueueGWCZ() {
        return new Queue(SENDGOODSSELF_CONINFO_QUEUE_GWCZ, true);
    }

    @Bean
    public Binding sendGoodsSelfBindingGWCZ() {
        return BindingBuilder.bind(sendGoodsSelfQueueGWCZ()).to(sendGoodsSelfTopicExchange()).with(SENDGOODSSELF_CONINFO_KEY_PX);
    }
    @Bean
    public Queue sendGoodsSelfQueueGW() {
        return new Queue(SENDGOODSSELF_CONINFO_QUEUE_GW, true);
    }

    @Bean
    public Binding sendGoodsSelfBindingGW() {
        return BindingBuilder.bind(sendGoodsSelfQueueGW()).to(sendGoodsSelfTopicExchange()).with(SENDGOODSSELF_CONINFO_KEY_PX);
    }

    @Bean
    public Queue sendGoodsSelfQueueGWCZ_DAK() {
        return new Queue(SENDGOODSSELF_CONINFO_QUEUE_GWCZ_DKA, true);
    }

    @Bean
    public Binding sendGoodsSelfBindingGWCZ_DAK() {
        return BindingBuilder.bind(sendGoodsSelfQueueGWCZ_DAK()).to(sendGoodsSelfTopicExchange()).with(SENDGOODSSELF_CONINFO_GWCZ_KEY_GWCZ);
    }

    @Bean
    public Queue sendGoodsSelfQueueGWCZ_BLPT() {
        return new Queue(SENDGOODSSELF_CONINFO_QUEUE_GWCZ_BLPT, true);
    }

    @Bean
    public Binding sendGoodsSelfBindingGWCZ_BLPT() {
        return BindingBuilder.bind(sendGoodsSelfQueueGWCZ_BLPT()).to(sendGoodsSelfTopicExchange()).with(SENDGOODSSELF_CONINFO_GWCZ_KEY_GWCZ);
    }

    /*****************************************子系统发送审核结果队列*************************************/

    //国内拼箱场站队列
    public static final String BOXINGPHOTO_PX_EXAMINE_QUEUE_BLPT = "boxingphoto_px_examine_blpt";

    public static final String BOXINGPHOTO_EXAMINE_KEY_PX = "boxingphoto_px_examine_key";
    //大口岸队列
    public static final String BOXINGPHOTO_DKA_EXAMINE_QUEUE_BLPT = "boxingphoto_dka_examine_blpt";
    public static final String BOXINGPHOTO_DKA_EXAMINE_QUEUE_GWCZ = "boxingphoto_dka_examine_gwcz";

    public static final String BOXINGPHOTO_DKA_EXAMINE_KEY = "boxingphoto_dka_examine_key";
    public static final String BOXINGPHOTO_DKA_EXAMINE_GWCZ_KEY = "boxingphoto_dka_examine_gwcz_key";
    //国外场站
    public static final String BOXINGPHOTO_GWCZ_EXAMINE_QUEUE_BLPT = "boxingphoto_gwcz_examine_blpt";
    public static final String BOXINGPHOTO_GWCZ_EXAMINE_KEY = "boxingphoto_gwcz_examine_key";

    @Bean
    public Queue boxingPhotoExamineQueuePX() {
        return new Queue(BOXINGPHOTO_PX_EXAMINE_QUEUE_BLPT, true);
    }

    @Bean
    public Binding boxingPhotoExamineBindingPX() {
        return BindingBuilder.bind(boxingPhotoExamineQueuePX()).to(sendGoodsSelfTopicExchange()).with(BOXINGPHOTO_EXAMINE_KEY_PX);
    }

    @Bean
    public Queue boxingPhotoDKAExamineQueueBLPT() {
        return new Queue(BOXINGPHOTO_DKA_EXAMINE_QUEUE_BLPT, true);
    }

    @Bean
    public Binding boxingPhotoDKAExamineBindingBLPT() {
        return BindingBuilder.bind(boxingPhotoDKAExamineQueueBLPT()).to(sendGoodsSelfTopicExchange()).with(BOXINGPHOTO_DKA_EXAMINE_KEY);
    }

    @Bean
    public Queue boxingPhotoDKAExamineQueueGWCZ() {
        return new Queue(BOXINGPHOTO_DKA_EXAMINE_QUEUE_GWCZ, true);
    }

    @Bean
    public Binding boxingPhotoDKAExamineBindingGWCZ() {
        return BindingBuilder.bind(boxingPhotoDKAExamineQueueGWCZ()).to(sendGoodsSelfTopicExchange()).with(BOXINGPHOTO_DKA_EXAMINE_GWCZ_KEY);
    }
    @Bean
    public Queue boxingPhotoGWCZExamineQueueBLPT() {
        return new Queue(BOXINGPHOTO_GWCZ_EXAMINE_QUEUE_BLPT, true);
    }

    @Bean
    public Binding boxingPhotoExamineBindingGWCZ() {
        return BindingBuilder.bind(boxingPhotoGWCZExamineQueueBLPT()).to(sendGoodsSelfTopicExchange()).with(BOXINGPHOTO_GWCZ_EXAMINE_KEY);
    }
}
