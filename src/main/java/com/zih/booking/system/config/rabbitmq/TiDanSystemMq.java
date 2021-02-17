package com.zih.booking.system.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// 提单系统 + 一点点的关务
@Configuration
public class TiDanSystemMq {
    //接收消息的     // 提单草单
    public static final String TD_DIRECT_EXCHANGE = "labInfo_exchange";
    public static final String TD_QUEUE = "labInfo_queue_client_test";
    public static final String TD_ROUTINGKEY = "labInfo_key";

    @Bean
    public DirectExchange tdDirectExchange(){
        return new DirectExchange(TD_DIRECT_EXCHANGE,true,false);
    }
    @Bean
    public Queue tdQueue(){
        return new Queue(TD_QUEUE,true);
    }
    @Bean
    public Binding tdBinding(){
        return BindingBuilder.bind(tdQueue()).to(tdDirectExchange()).with(TD_ROUTINGKEY);
    }
    //发送消息的 保存提单草单
    public static final String TD_DIRECT_RESPONSE_EXCHANGE = "labInfo_exchange";
    public static final String TD_RESPONSE_QUEUE = "labInfo_queue_client_response";
    public static final String TD_RESPONSE_QUEUE2 = "labInfo_queue_client_response2";
    public static final String TD_RESPONSE_ROUTINGKEY = "labInfo_response";
    @Bean
    public DirectExchange tdDirectResponseExchange(){
        return new DirectExchange(TD_DIRECT_RESPONSE_EXCHANGE,true,false);
    }
    @Bean
    public Queue tdResponseQueue(){
        return new Queue(TD_RESPONSE_QUEUE,true);
    }
    @Bean
    public Queue tdResponseQueue2(){
        return new Queue(TD_RESPONSE_QUEUE2,true);
    }
    @Bean
    public Binding tdResponseBinding(){
        return BindingBuilder.bind(tdResponseQueue()).to(tdDirectResponseExchange()).with(TD_RESPONSE_ROUTINGKEY);
    }
    @Bean
    public Binding tdResponseBinding2(){
        return BindingBuilder.bind(tdResponseQueue2()).to(tdDirectResponseExchange()).with(TD_RESPONSE_ROUTINGKEY);
    }
    //发送消息的  上传电放保函（type=3）  正本选择（type=4） 发给两个队列 一个培培（提单） 一个刘明磊（关务） 内容一样
    public static final String TD_DIRECT_DFBH_EXCHANGE = "labInfo_exchange";
    public static final String TD_DFBH_QUEUE = "labInfo_queue_client_dfbh";
    public static final String TD_DFBH_QUEUE2 = "labInfo_queue_client_dfbh2";
    public static final String TD_DFBH_ROUTINGKEY = "labInfo_dfbh";

    /*大口岸集输发送可请求分拨提单的状态*/
    public static final String TD_LABSATE_QUEUE_JS = "labSate_queue_jssystem";
    public static final String TD_LABSATE_ROUTINGKEY = "state_key";

    @Bean
    public DirectExchange tdDirectDFBHExchange(){
        return new DirectExchange(TD_DIRECT_DFBH_EXCHANGE,true,false);
    }
    @Bean
    public Queue tdDFBHQueue(){
        return new Queue(TD_DFBH_QUEUE,true);
    }
    @Bean
    public Binding tdDFBHBinding(){
        return BindingBuilder.bind(tdDFBHQueue()).to(tdDirectDFBHExchange()).with(TD_DFBH_ROUTINGKEY);
    }

    @Bean
    public Queue tdDFBHQueue2(){
        return new Queue(TD_DFBH_QUEUE2,true);
    }
    @Bean
    public Binding tdDFBHBinding2(){
        return BindingBuilder.bind(tdDFBHQueue2()).to(tdDirectDFBHExchange()).with(TD_DFBH_ROUTINGKEY);
    }

    @Bean
    public Queue tdLabsateQueue(){
        return new Queue(TD_LABSATE_QUEUE_JS,true);
    }
    @Bean
    public Binding tdLabsateBinding(){
        return BindingBuilder.bind(tdLabsateQueue()).to(tdDirectDFBHExchange()).with(TD_LABSATE_ROUTINGKEY);
    }

    /*---------------------------------客户端系统——>平台系统：随车文件-----------------------------*/
    public static final String CLIENT_TOPIC_EXCHANGE = "client.topic.exchange";

    public static final String FOLLOW_VEHICLE_ROUTINGKEY = "follow.vehicle.*";

    public static final String FOLLOW_VEHICLE_QUEUE = "follow_vehicle_queue";

    public static final String FOLLOW_VEHICLE_QUEUE_GW = "follow_vehicle_queue_gw";

    public static final String FOLLOW_VEHICLE_QUEUE_GWCZ = "follow_vehicle_queue_gwcz";
    /*---------------------------------客户端系统——>平台系统：随车文件-----------------------------*/
    /*---------------------------------客户端系统——>平台系统：随车文件材料------------------------------*/
    @Bean
    public TopicExchange clientTopicExchange(){
        return (TopicExchange) ExchangeBuilder
                .topicExchange(CLIENT_TOPIC_EXCHANGE).durable(true).build();
    }
    @Bean
    public Queue followVehicleQueueBLPT(){
        return new Queue(FOLLOW_VEHICLE_QUEUE);
    }
    @Bean
    public Binding followVehicleBindingPX(TopicExchange clientTopicExchange, Queue followVehicleQueueBLPT){
        return BindingBuilder.bind(followVehicleQueueBLPT).to(clientTopicExchange)
                .with(FOLLOW_VEHICLE_ROUTINGKEY);
    }
    @Bean
    public Queue followVehicleQueueGW(){
        return new Queue(FOLLOW_VEHICLE_QUEUE_GW);
    }
    @Bean
    public Binding followVehicleBindingGW(TopicExchange clientTopicExchange, Queue followVehicleQueueGW){
        return BindingBuilder.bind(followVehicleQueueGW).to(clientTopicExchange)
                .with(FOLLOW_VEHICLE_ROUTINGKEY);
    }
    @Bean
    public Queue followVehicleQueueGWCZ(){
        return new Queue(FOLLOW_VEHICLE_QUEUE_GWCZ);
    }
    @Bean
    public Binding followVehicleBindingGWCZ(TopicExchange clientTopicExchange, Queue followVehicleQueueGWCZ){
        return BindingBuilder.bind(followVehicleQueueGWCZ).to(clientTopicExchange)
                .with(FOLLOW_VEHICLE_ROUTINGKEY);
    }
    /*---------------------------------客户端系统——>平台系统：随车文件材料------------------------------*/
    /*---------------------------------平台——>拼箱系统：客户自装箱装箱明细-----------------------------*/
    public static final String ORDER_BOXING_LISTC_TOPIC_EXCHANGE = "order.boxing.listc.topic.exchange";

    public static final String ORDER_BOXING_LISTC_ROUTINGKEY = "order.boxing.listc.*";
//    public static final String ORDER_BOXING_LISTC_QUEUE_BLPT = "order_boxing_listc_queue_blpt";
    // 拼箱系统消息队列
//    public static final String ORDER_BOXING_LISTC_QUEUE_PX = "order_boxing_listc_queue_px";
    @Bean
    public TopicExchange orderBoxingListcTopicExchange(){
        return (TopicExchange) ExchangeBuilder
                .topicExchange(ORDER_BOXING_LISTC_TOPIC_EXCHANGE).durable(true).build();
    }
//    @Bean
//    public Queue orderBoxingListcQueuePX(){
//        return new Queue(ORDER_BOXING_LISTC_QUEUE_PX);
//    }
//    @Bean
//    public Binding orderBoxingListcBindingBLPT(TopicExchange orderBoxingListcTopicExchange, Queue orderBoxingListcQueuePX){
//        return BindingBuilder.bind(orderBoxingListcQueuePX).to(orderBoxingListcTopicExchange)
//                .with(ORDER_BOXING_LISTC_ROUTINGKEY);
//    }
    /*---------------------------------客户端——>拼箱场站、平台：客户自装箱装箱明细------------------------------*/
    /*船级社证书*/
    public static final String CLIENT_CERTIFICATE_QUEUE_XG = "client_certificate_queue_xg";
    public static final String CLIENT_CERTIFICATE_QUEUE_XXYO = "client_certificate_queue_xxyo";
    public static final String CLIENT_CERTIFICATE_QUEUE_GW = "client_certificate_queue_gw";
    public static final String CERTIFICATE_BINDING = "client.certificate";
    @Bean
    public Queue certificateQueueXG(){
        return new Queue(CLIENT_CERTIFICATE_QUEUE_XG);
    }
    @Bean
    public Binding certificateBindingXG(TopicExchange clientTopicExchange, Queue certificateQueueXG){
        return BindingBuilder.bind(certificateQueueXG).to(clientTopicExchange)
                .with(CERTIFICATE_BINDING);
    }
    @Bean
    public Queue certificateQueueXXYO(){
        return new Queue(CLIENT_CERTIFICATE_QUEUE_XXYO);
    }
    @Bean
    public Binding certificateBindingXXYO(TopicExchange clientTopicExchange, Queue certificateQueueXXYO){
        return BindingBuilder.bind(certificateQueueXXYO).to(clientTopicExchange)
                .with(CERTIFICATE_BINDING);
    }
    @Bean
    public Queue certificateQueueGW(){
        return new Queue(CLIENT_CERTIFICATE_QUEUE_GW);
    }
    @Bean
    public Binding certificateBindingGW(TopicExchange clientTopicExchange, Queue certificateQueueGW){
        return BindingBuilder.bind(certificateQueueGW).to(clientTopicExchange)
                .with(CERTIFICATE_BINDING);
    }
    /*---------------------------------客户端——>箱行亚欧、平台：客户提货文件信息------------------------------*/
    public static final String CLIENT_PICKGOODSTIMEFILE_QUEUE_XXYO = "client_pickgoodstimefile_queue_xxyo";
    public static final String PICKGOODSTIMEFILE_BINDING = "client.pickgoodstimefile";
    @Bean
    public Queue pickgoodstimefileQueueXXYO(){
        return new Queue(CLIENT_PICKGOODSTIMEFILE_QUEUE_XXYO);
    }
    @Bean
    public Binding pickgoodstimefileBindingXG(TopicExchange clientTopicExchange, Queue pickgoodstimefileQueueXXYO){
        return BindingBuilder.bind(pickgoodstimefileQueueXXYO).to(clientTopicExchange)
                .with(PICKGOODSTIMEFILE_BINDING);
    }

    /*---------------------------------箱行亚欧——>客户端：提货司机信息------------------------------*/
    public static final String CLIENT_PICKGOODS_DRIVER_QUEUE_BLPT = "xxyo_pickgoods_driver_queue_blpt";
    public static final String PICKGOODS_DRIVER_BINDING = "xxyo.pickgoods_driver";
    @Bean
    public Queue pickgoodsDriverQueueXXYO(){
        return new Queue(CLIENT_PICKGOODS_DRIVER_QUEUE_BLPT);
    }
    @Bean
    public Binding pickgoodsDriverBindingXG(TopicExchange clientTopicExchange, Queue pickgoodsDriverQueueXXYO){
        return BindingBuilder.bind(pickgoodsDriverQueueXXYO).to(clientTopicExchange)
                .with(PICKGOODS_DRIVER_BINDING);
    }
}
