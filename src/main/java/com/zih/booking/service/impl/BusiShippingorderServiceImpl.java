package com.zih.booking.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zih.booking.dao.*;
import com.zih.booking.model.*;
import com.zih.booking.request.BookingSpaceRequest;
import com.zih.booking.request.TrainListRequest;
import com.zih.booking.response.TrainListResponse;
import com.zih.booking.service.*;
import com.zih.booking.system.config.rabbitmq.JiShuSystemMq;
import com.zih.booking.system.token.LoginUser;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.utils.BeanChangeUtil;
import com.zih.booking.utils.BeanUtil;
import com.zih.booking.utils.DateUtils;
import com.zih.booking.utils.ServletUtils;
import com.zih.booking.vo.Cabin;
import com.zih.booking.vo.GwczBookingVo;
import com.zih.booking.vo.ShippingOrder;
import com.zih.booking.vo.inquiryResultZx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.*;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author wsl123
 * @since 2020-01-09
 */
@Slf4j
@Service
public class BusiShippingorderServiceImpl extends ServiceImpl<BusiShippingorderMapper, BusiShippingorder> implements BusiShippingorderService {
    @Autowired
    BusiShippingorderMapper busiShippingorderMapper;
    @Autowired
    BusiShippingorderGoodsService busiShippingorderGoodsService;
    @Autowired
    ReceiverService receiverService;
    @Autowired
    SenderService senderService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    BusiClientsService clientsService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ActualReceiverService actualReceiverService;
    @Autowired
    private ActualSenderService actualSenderService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private BookingCityService bookingCityService;
    @Autowired
    private TrackTwoLevelService trackTwoLevelService;
    @Autowired
    private BusiSiteService siteService;
    @Autowired
    private BusiClassesMapper busiClassesMapper;
    @Autowired
    private BusiSiteMapper busiSiteMapper;
    @Autowired
    private BusiSiteService busiSiteService;
    @Autowired
    private BusiLinesiteService busiLinesiteService;
    @Autowired
    private BaseGoodsnoteMapper baseGoodsnoteMapper;
    @Autowired
    private BusiShippingorderDangerousGoodsService dangerousGoodsService;
    @Autowired
    private AsyncService asyncService;
    @Autowired
    private BookingInquiryMapper bookingInquiryMapper;

    @Override
    public String getNameByCode(String name) {
        String code = name;
        if (null != siteService.selectOne(new EntityWrapper<BusiSite>().eq("name_cn", code))) {
            code = siteService.selectOne(new EntityWrapper<BusiSite>().eq("name_cn", code)).getCode();
        } else if (null != siteService.selectOne(new EntityWrapper<BusiSite>().eq("name_en", code))) {
            code = siteService.selectOne(new EntityWrapper<BusiSite>().eq("name_en", code)).getCode();
        }
        return code;
    }

    @Override
    public List<TrainListResponse> selectTrainList(Page page, TrainListRequest trainListRequest) {
        List<TrainListResponse> list = busiShippingorderMapper.selectTrainList(page, trainListRequest);
        if (trainListRequest.getLanguage().equalsIgnoreCase("EN"))
            for (TrainListResponse t : list) {
                t.setTrainState(t.getTrainStateEn());
            }
        return list;
    }


    //@Transactional(propagation = Propagation.SUPPORTS)
    @Transactional
    @Override
    public ApiResultI18n add(BookingSpaceRequest bookingSpaceRequest) {
        String language = bookingSpaceRequest.getLanguage();
        String client_id = tokenService.getClientId(ServletUtils.getRequest());//客户id
        Sender sender = new Sender();//发货方
        BeanUtils.copyProperties(bookingSpaceRequest, sender);
        sender.setSenderName(bookingSpaceRequest.getShipOrederName());
        sender.setSenderMan(bookingSpaceRequest.getShipOrederContacts());
        sender.setSenderPhone(bookingSpaceRequest.getShipOrederPhone());
        sender.setSenderAddress(bookingSpaceRequest.getShipOrederAddress());
        sender.setSenderEmail(bookingSpaceRequest.getShipOrederEmail());
        sender.setClientId(client_id);
        log.debug("新增订舱发货方数据 ：\n {}", sender);
        if (null != bookingSpaceRequest.getShipOrederName() && !"".equals(bookingSpaceRequest.getShipOrederName())) {
            senderService.insertOrUpdate(sender);
        }
        Receiver receiver = new Receiver();//收货方
        BeanUtils.copyProperties(bookingSpaceRequest, receiver);
        receiver.setReceiverId(bookingSpaceRequest.getReceiveId());
        receiver.setReceiverName(bookingSpaceRequest.getReceiveOrderName());
        receiver.setReceiverMan(bookingSpaceRequest.getReceiveOrderContacts());
        receiver.setReceiverEmail(bookingSpaceRequest.getReceiveOrderEmail());
        receiver.setReceiverPhone(bookingSpaceRequest.getReceiveOrderPhone());
        receiver.setReceiverAddress(bookingSpaceRequest.getReceiveOrderAddress());
        receiver.setTransitEmail(bookingSpaceRequest.getReceiveOrderReceiveemail());
        receiver.setArriveEmail(bookingSpaceRequest.getReceiveOrderMail());
        receiver.setClientId(client_id);
        log.debug("新增订舱收货方数据 ：\n {}", receiver);
        if (null != bookingSpaceRequest.getReceiveOrderName() && !"".equals(bookingSpaceRequest.getReceiveOrderName())) {
            receiverService.insertOrUpdate(receiver);
        }
        //  订单
        BusiShippingorder busiShippingorder = BeanUtil.copyPropertiesASM(bookingSpaceRequest, BusiShippingorder.class);
        busiShippingorder.setLineTypeid(bookingSpaceRequest.getLineType());

        if (null != bookingSpaceRequest.getSenderProvince()) {
            busiShippingorder.setShipOrderUnloadaddress(bookingSpaceRequest.getSenderProvince() + "/" + bookingSpaceRequest.getSenderCity() + "/" + bookingSpaceRequest.getSenderArea());
        }
        if (null != bookingSpaceRequest.getReceiveProvince()) {
            busiShippingorder.setReceiveOrderPartaddress(bookingSpaceRequest.getReceiveProvince() + "/" + bookingSpaceRequest.getReceiveCity() + "/" + bookingSpaceRequest.getReceiveArea());
        }
        busiShippingorder.setIsConsolidation(bookingSpaceRequest.getIsConsolidation());
        busiShippingorder.setTjTime(new Date());
        //busiShippingorder.setClientId(client_id);
        busiShippingorder.setOrderUploadcode(getNameByCode(bookingSpaceRequest.getOrderUploadsite()));
        busiShippingorder.setOrderUnloadcode(getNameByCode(bookingSpaceRequest.getOrderUnloadsite()));
        BusiClients client = clientsService.selectById(bookingSpaceRequest.getClientId());
//        if (null != bookingSpaceRequest.getClassEastandwest() && bookingSpaceRequest.getClassEastandwest().equals("0")) {//xi
//            busiShippingorder.setOrderMerchandiser(client.getwMerchandiser());//西向跟单员
//            busiShippingorder.setOrderMerchandiserId(client.getwMerchandiserId());//跟单员id
//            // busiShippingorder.setYwNumber(client.getwMerchandiserNumber());//跟单员工号
//        }
//        if (bookingSpaceRequest.getClassEastandwest().equals("1")) {//dong
//            busiShippingorder.setOrderMerchandiser(client.geteMerchandiser());//东向同上
//            busiShippingorder.setOrderMerchandiserId(client.geteMerchandiserId());
//            //busiShippingorder.setYwNumber(client.geteMerchandiserNumber());
//        }
        if (bookingSpaceRequest.getIsExamline().equals("0")) {
            busiShippingorder.setIsExamline("0");
        } else {
            busiShippingorder.setIsExamline("5");
        }
        busiShippingorder.setCreateuserid(bookingSpaceRequest.getClientId());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        busiShippingorder.setCreateusername(loginUser.getUserType().equals("0") ?
                loginUser.getBusiClients().getUserinfoName() : loginUser.getKhUser().getUserName());
        busiShippingorder.setYwNumber(loginUser.getUserType().equals("0") ?
                "" : loginUser.getKhUser().getJobNumber());
        busiShippingorder.setShipOrderId(sender.getSenderId());
        busiShippingorder.setReceiveOrderId(receiver.getReceiverId());
        busiShippingorder.setOrderAuditCreatedate(new Date());
        if("0".equals(bookingSpaceRequest.getIsExamline())){
            busiShippingorder.setTjFTime(new Date()); //首次提交托书时间
        }
        busiShippingorder.setCreatedate(new Date());
       //特种箱新增箱管审核
        String isconsolidation = busiShippingorder.getIsConsolidation(); //0整柜 1拼箱
        String containerType = bookingSpaceRequest.getContainerType();
        String[] specialBoxType = {"20HOT","20HT","20OT","40HOT","40HT","40MT","40OT","40RF","45RF"};
        if("0".equals(bookingSpaceRequest.getIsExamline())){  //提交待审核
           if("0".equals(isconsolidation)){  //整柜
               if(containerType!=null && containerType!=""){
                   List<String> boxListS = Arrays.asList(specialBoxType);
                   if(boxListS.contains(containerType)){
                       busiShippingorder.setIsExamline("7");
                   }
               }
           }
       }
        if("en".equals(bookingSpaceRequest.getLanguage())){
            busiShippingorder.setYuyan("1");
        }
        StringBuffer buffer = new StringBuffer();
        //整柜箱量是否修改
        if("0".equals(busiShippingorder.getIsConsolidation())){
            String boxAmount = busiShippingorder.getContainerBoxamount(); //新提交箱量
            if(boxAmount!=null && boxAmount!=""){
                String inquiryRecordId = busiShippingorder.getInquiryRecordId(); //询价结果id
                inquiryResultZx inquiryInfo = bookingInquiryMapper.inquiryPirce(inquiryRecordId);
                if(inquiryInfo!=null){
                    Integer containerNum = inquiryInfo.getContainerNum(); //询价箱量
                    Integer boxAmountInt = StringUtils.isEmpty(boxAmount)?0:Integer.valueOf(boxAmount);
                    //判断箱量是否超过舱位数
                    String classId = busiShippingorder.getClassId();
                    Integer classZgCount = bookingInquiryMapper.selectClassZgCount(classId);//查询班列整柜总数
                    if(boxAmountInt>classZgCount){
                        if("en".equals(language)){
                            buffer.append("Insufficient space;");
                        }else{
                            buffer.append("箱量与班列信息不匹配，请重新选择班列;");
                        }
                        return ApiResultI18n.failure(500, buffer.toString(), bookingSpaceRequest.getLanguage());
                    }
                    busiShippingorder.setContainerBoxamount(boxAmount);
                    if(boxAmountInt!=containerNum){ //箱量改变
                        //箱量增加，判断是否满足舱位
                        if(boxAmountInt>containerNum){
                            String boxType = busiShippingorder.getContainerType();
                            if(boxType!=null && boxType!=""){
                                Double boxAmountReal = Double.valueOf(boxAmountInt);
                                if(boxType.contains("20")){
                                    boxAmountReal = Double.valueOf(boxAmountInt)/2;
                                }
                                Double orderZgCount = bookingInquiryMapper.selectZgCount(classId);//查询班列已定的整柜数量
                                Double maxCount = classZgCount - orderZgCount;
                                if(maxCount<0 || maxCount<boxAmountReal){
                                    if("en".equals(language)){
                                        buffer.append("Insufficient space;");
                                    }else{
                                        buffer.append("箱量与班列信息不匹配，请重新选择班列;");
                                    }
                                    return ApiResultI18n.failure(500, buffer.toString(), bookingSpaceRequest.getLanguage());
                                }
                            }
                        }
                        //价格
                        busiShippingorder.setShipThCost(zgPrice(inquiryInfo.getPickUpFees(),containerNum,boxAmount)); //提货费
                        busiShippingorder.setSiteCost(zgPrice(inquiryInfo.getRailwayFees(),containerNum,boxAmount));//铁路运费
                        busiShippingorder.setReceiveShCost(zgPrice(inquiryInfo.getDeliveryFees(),containerNum,boxAmount)); //送货费
                        busiShippingorder.setPickUpBoxFee(zgBoxFee(inquiryInfo.getPickUpBoxFee(),containerNum,boxAmount));//提箱费
                        busiShippingorder.setReturnBoxFee(zgBoxFee(busiShippingorder.getReturnBoxFee(),containerNum,boxAmount));//还箱费
                    }
                }
            }
        }

        log.debug("新增订舱订单数据 ：\n {}", busiShippingorder);
        Integer a = busiShippingorderMapper.insert(busiShippingorder);
       //特种箱新增箱管审核
       if("0".equals(bookingSpaceRequest.getIsExamline())){  //提交待审核
           if("0".equals(isconsolidation)){  //整柜
               if(containerType!=null && containerType!=""){
                   List<String> boxList = Arrays.asList(specialBoxType);
                   if(boxList.contains(containerType)){  //特种箱
                       ShippingOrder orderCheckInfo = new ShippingOrder();
                       BeanUtils.copyProperties(busiShippingorder,orderCheckInfo);
                       orderCheckInfo.setIsconsolidation(bookingSpaceRequest.getIsConsolidation());
                       orderCheckInfo.setLineTypeid(bookingSpaceRequest.getLineType());
                       orderCheckInfo.setOrderId(busiShippingorder.getOrderId());
                       orderCheckInfo.setType("1"); //新增订舱
                       orderCheckInfo.setOrderId(busiShippingorder.getOrderId());
                       orderCheckInfo.setIsexamline(busiShippingorder.getIsExamline());
                       orderCheckInfo.setGoodsName(bookingSpaceRequest.getGoodsName());
                       orderCheckInfo.setGoodsPacking(bookingSpaceRequest.getGoodsPacking());
                       orderCheckInfo.setGoodsCbm(bookingSpaceRequest.getGoodsCbm());
                       orderCheckInfo.setGoodsKgs(bookingSpaceRequest.getGoodsKgs());
                       orderCheckInfo.setGoodsNumber(bookingSpaceRequest.getGoodsNumber());
                       CorrelationData correlationDataCheck = new CorrelationData(UUID.randomUUID().toString());
                       rabbitTemplate.convertAndSend(JiShuSystemMq.XG_ORDER_TOPIC_EXCHANGE, JiShuSystemMq.XG_ORDER_ROUTINGKEY, orderCheckInfo, correlationDataCheck);
                   }
               }
           }
       }

        //草稿  新增二级节点
        if (!bookingSpaceRequest.getIsExamline().equals("0")&&("0".equals(busiShippingorder.getClassEastandwest())||
                ("1".equals(busiShippingorder.getClassEastandwest())&&"0".equals(busiShippingorder.getIsConsolidation())))) {
            TrackTwoLevel trackTwoLevel = new TrackTwoLevel();
            trackTwoLevel.setOrderId(busiShippingorder.getOrderId());
            trackTwoLevel.setNameZh("草稿");
            trackTwoLevel.setNameEn("draft");
            trackTwoLevel.setIsCustom("1");
            trackTwoLevel.setState(1);
            trackTwoLevel.setTime(new Date().toLocaleString());
            trackTwoLevel.setSort(1);
            trackTwoLevel.setCreateBy(client_id);
            trackTwoLevel.setCreateTime(new Date());
            trackTwoLevelService.insert(trackTwoLevel);
        }

        //商品 （关联订单表的）
        BusiShippingorderGoods busiShippingorderGoods = new BusiShippingorderGoods();
        BeanUtils.copyProperties(bookingSpaceRequest, busiShippingorderGoods);
        busiShippingorderGoods.setGoodsId(null);
        busiShippingorderGoods.setOrderId(busiShippingorder.getOrderId());
        if (bookingSpaceRequest.getIsExamline().equals("0")) {
            if("en".equals(language)){
                buffer.append("success;");
            }else{
                buffer.append("订舱成功;");
            }
        } else {
            if("en".equals(language)){
                buffer.append("success;");
            }else{
                buffer.append("保存草稿成功;");
            }
        }
        log.debug("新增订舱关联订单表的商品信息数据 ：\n {}", busiShippingorderGoods);
        busiShippingorderGoodsService.insert(busiShippingorderGoods);

        //商品 （单纯的商品）
        Goods goods = new Goods();
        BeanUtils.copyProperties(bookingSpaceRequest, goods);
        goods.setGoBack(bookingSpaceRequest.getClassEastandwest());
        goods.setGoodsOutReport(bookingSpaceRequest.getGoodsReport());
        goods.setGoodsInClearance(bookingSpaceRequest.getGoodsClearance());
        goods.setClientId(client_id);
        log.debug("新增订舱单纯的商品信息数据 ：\n {}", goods);
        if (null != bookingSpaceRequest.getGoodsName() && !"".equals(bookingSpaceRequest.getGoodsName())) {
            goodsService.insertOrUpdate(goods);
        }
        //实际收货方
        ActualReceiver actualReceiver = new ActualReceiver();
        BeanUtils.copyProperties(bookingSpaceRequest, actualReceiver);
        actualReceiver.setClientId(client_id);
        actualReceiver.setActualMan(bookingSpaceRequest.getReceiveOrderZihcontacts());
        actualReceiver.setActualEmail(bookingSpaceRequest.getReceiveOrderZihemail());
        actualReceiver.setActualPhone(bookingSpaceRequest.getReceiveOrderZihtel());
        actualReceiver.setGoBack(bookingSpaceRequest.getClassEastandwest());
        actualReceiver.setTransitEmail(bookingSpaceRequest.getReceiveOrderReceiveemail());
        actualReceiver.setArriveEmail(bookingSpaceRequest.getReceiveOrderMail());
        if (null != bookingSpaceRequest.getReceiveProvince()) {
            actualReceiver.setProvince(bookingSpaceRequest.getReceiveProvince());
            actualReceiver.setCity(bookingSpaceRequest.getReceiveCity());
            actualReceiver.setArea(bookingSpaceRequest.getReceiveArea());
            actualReceiver.setActualDetailAdd(bookingSpaceRequest.getDetailedAddress());
        } else {
            actualReceiver.setActualAddress(bookingSpaceRequest.getReceiveOrderPartaddress());
        }
        if (null != bookingSpaceRequest.getReceiveOrderZihcontacts() && !"".equals(bookingSpaceRequest.getReceiveOrderZihcontacts())) {
            actualReceiverService.insertOrUpdate(actualReceiver);
        }
        //实际发货方啊啊啊啊啊啊
        ActualSender actualSender = new ActualSender();
        BeanUtils.copyProperties(bookingSpaceRequest, actualSender);
        actualSender.setClientId(client_id);
        actualSender.setGoBack(bookingSpaceRequest.getClassEastandwest());
        actualSender.setActualMan(bookingSpaceRequest.getShipOrderUnloadcontacts());
        actualSender.setActualEmail(bookingSpaceRequest.getShipOrderUnloadwayEmail());
        actualSender.setActualPhone(bookingSpaceRequest.getShipOrderUnloadway());
        if (null != bookingSpaceRequest.getSenderProvince()) {
            actualSender.setProvince(bookingSpaceRequest.getSenderProvince());
            actualSender.setCity(bookingSpaceRequest.getSenderCity());
            actualSender.setArea(bookingSpaceRequest.getSenderArea());
            actualSender.setActualDetailAdd(bookingSpaceRequest.getDetailedAddress());
        } else {
            actualSender.setActualAddress(bookingSpaceRequest.getShipOrderUnloadaddress());
        }
        if (null != bookingSpaceRequest.getShipOrderUnloadcontacts() && !"".equals(bookingSpaceRequest.getShipOrderUnloadcontacts())) {
            actualSenderService.insertOrUpdate(actualSender);
        }

        //发给老订舱
        // 因为审核后的订舱信息 她已经用了邓培松的实体类 所以也拷贝到她的类 然后发送
        //领导说没必要 --- 就注释了
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        ShippingOrder order = new ShippingOrder();
        BeanUtils.copyProperties(bookingSpaceRequest, order);
        order.setClientTjrEmail(client.getClientEmail());
//        order.setOrderMerchandiser(client.geteMerchandiser());
//        order.setOrderMerchandiserId(client.geteMerchandiserId());
//        order.setMerchandiserIdW(client.getwMerchandiserId());
//        order.setMerchandiserW(client.getwMerchandiser());
        order.setOrderMerchandiser(bookingSpaceRequest.getOrderMerchandiser());
        order.setOrderMerchandiserId(bookingSpaceRequest.getOrderMerchandiserId());
        order.setMerchandiserIdW(bookingSpaceRequest.getOrderMerchandiserId());
        order.setMerchandiserW(bookingSpaceRequest.getOrderMerchandiser());
        order.setClassBh(busiShippingorder.getOrderClassBh());
        order.setIsexamline(null == bookingSpaceRequest.getIsExamline() ? "5" : bookingSpaceRequest.getIsExamline());
        order.setIsconsolidation(bookingSpaceRequest.getIsConsolidation());
        order.setLineTypeid(bookingSpaceRequest.getLineType());
        if(null!=bookingSpaceRequest.getLanguage()){
        order.setYuyan(bookingSpaceRequest.getLanguage().equalsIgnoreCase("en") ? "1" : "0");}
        else{
            order.setYuyan("0");
        }
        order.setTjTime(new Date());
        order.setOrderAuditCreatedate(new Date());
        order.setOrderAuditUsername(client.getClientUnit());
        if (null != bookingSpaceRequest.getDictId()) {
            order.setDictId(Long.parseLong(bookingSpaceRequest.getDictId() + ""));
        }
        order.setCreatedate(new Date());
        order.setCreateuserid(client_id);
        order.setCreateusername(client.getClientUnit());
        order.setSitecost(null == bookingSpaceRequest.getSiteCost() ? "" : bookingSpaceRequest.getSiteCost());
        order.setOrderId(busiShippingorder.getOrderId());
        //集疏使用
        bookingSpaceRequest.setOrderId(busiShippingorder.getOrderId());
        bookingSpaceRequest.setTjTime(new Date());
        bookingSpaceRequest.setOrderAuditCreatedate(new Date());
        if("7".equals(busiShippingorder.getIsExamline())){
            order.setIsexamline("7");
            bookingSpaceRequest.setIsExamline("7");
        }
        //货物规格
        if(null!=order.getInquiryRecordId()){
            List<BookingInquiryGoodsDetails> goodsList = busiShippingorderMapper.selectGoodsInfo(order.getInquiryRecordId());
            if(goodsList.size()>0){
                //货物规格
                String goodsStandard = "";
                for(BookingInquiryGoodsDetails goodsItem:goodsList){
                    String standard = goodsItem.getGoodsLength()+"*"+goodsItem.getGoodsWidth()+"*"+goodsItem.getGoodsHeight()+"*"+goodsItem.getGoodsAmount()+";";
                    goodsStandard += standard;
                }
                goodsStandard = goodsStandard.substring(0,goodsStandard.length()-1);  //截掉末尾分号
                order.setGoodsStandard(goodsStandard);
                bookingSpaceRequest.setGoodsStandard(goodsStandard);
                //货物详细信息
                StringBuilder goodsDetail = new StringBuilder();
                goodsList.forEach(goodsInfo->{
                    goodsDetail.append("品名:")
                            .append(goodsInfo.getGoodsName()).append("/")
                            .append(goodsInfo.getGoodsAmount()).append("件/")
                            .append(goodsInfo.getGoodsWeight()).append("kg/")
                            .append(goodsInfo.getGoodsLength()).append("cm*")
                            .append(goodsInfo.getGoodsWidth()).append("cm*")
                            .append(goodsInfo.getGoodsHeight()).append("cm;\r\n");
                });
                order.setGoodsInfoDetail(goodsDetail.toString());
                bookingSpaceRequest.setGoodsInfoDetail(goodsDetail.toString());
            }
        }
        rabbitTemplate.convertAndSend(JiShuSystemMq.ODC_ORDER_TOPIC_EXCHANGE, JiShuSystemMq.ODC_ORDER_ROUTINGKEY, order, correlationData);
        //rabbitTemplate.convertAndSend(JiShuSystemMq.ODC_ORDER_TOPIC_EXCHANGE, JiShuSystemMq.ODC_ORDER_ROUTINGKEY2, order, correlationData);

        //if (bookingSpaceRequest.getIsExamline().equals("0")) {

            //给集输发送托书内容
            CorrelationData correlationData2 = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend(JiShuSystemMq.JS_ORDER_TOPIC_EXCHANGE, JiShuSystemMq.JS_ORDER_ROUTINGKEY, bookingSpaceRequest, correlationData2);

            //给集输发送邮件
            if ("1".equals(bookingSpaceRequest.getClassEastandwest()) && "0".equals(bookingSpaceRequest.getShipOrderBinningway())) {
                CorrelationData ss = new CorrelationData(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend(JiShuSystemMq.JS_ORDER_TOPIC_EXCHANGE, JiShuSystemMq.JS_EMAIL_ROUTINGKEY, bookingSpaceRequest, ss);
            }
        //}
        //给客户、推荐人发送邮件
        asyncService.orderSendEmail(busiShippingorder,busiShippingorderGoods);
        if (a == 1) {
            return ApiResultI18n.success(ApiResultI18n.success(buffer.toString(), bookingSpaceRequest.getLanguage()), bookingSpaceRequest.getLanguage());
        } else {
            return ApiResultI18n.failure(500, "error", bookingSpaceRequest.getLanguage());
        }

    }

    //计算整柜价格
    public String zgPrice(String price,Integer containerNum,String boxAmount){
        String newPrice = "";
        if(boxAmount!="" && boxAmount!=null){
            int boxAmountI = Integer.valueOf(boxAmount);
            if(price!="" && price!=null && containerNum!=null){
                DecimalFormat df=new DecimalFormat(".##");
                newPrice = df.format(Double.valueOf(price)/containerNum*boxAmountI);
            }
        }
        return newPrice;
    }

    //计算提还箱费价格
    public String zgBoxFee(String price,Integer containerNum,String boxAmount){
        String newPrice = "";
        if(boxAmount!="" && boxAmount!=null){
            int boxAmountI = Integer.valueOf(boxAmount);
            if(price!="" && price!=null && containerNum!=null){
                StringBuilder sb = new StringBuilder(price.substring(0,1) + " ");
                int PriceC = DateUtils.reInt(price);
                if(containerNum!=0){
                    newPrice = String.valueOf(PriceC/containerNum*boxAmountI);
                }
                newPrice = sb.append(newPrice).toString();
            }
        }
        return newPrice;
    }

    /**
     * 获取订单特殊备注
     */

    private Map selectSpecialRemark(String goodsName, String goodsReport, String classId, String orderUnloadsite) {
        Map specialRemark = new HashMap();
        String nameRemark = "";
        String hsRemark = "";
        BusiClasses classInfo = busiClassesMapper.selectById(classId);
        if (!StringUtils.isEmpty(classInfo)) {
            String unloadsite = orderUnloadsite;//下货站
            String station = busiLinesiteService.selectOne(new EntityWrapper<BusiLinesite>()
                    .eq("class_t_StationOfDeparture_code", classInfo.getClassStationofdeparture())
                    .eq("class_t_StationOfDestination_code", classInfo.getClassStationofdestination())).getNameCn();// classInfo.getNameCn();
            String eastandwest = classInfo.getClassEastandwest(); //东西向 0西向 1东向
            String inhs = goodsReport;
            //货物名称特殊备注
            List<BaseGoodsnote> goodsNameRemarkList = baseGoodsnoteMapper.selectBaseGoodsnoteListByName(eastandwest, goodsName);
            for (BaseGoodsnote noteItem : goodsNameRemarkList) {
                if (("1".equals(noteItem.getUnloadSite())) && ("1".equals(noteItem.getStation()))) {
                    nameRemark = nameRemark + noteItem.getSpecialnotes() + ";";
                } else {
                    if (!"1".equals(noteItem.getUnloadSite())) {
                        if ((noteItem.getUnloadSite()).contains(unloadsite)) {
                            nameRemark = nameRemark + noteItem.getGoodsname() + ":" + unloadsite + "站点" + noteItem.getSpecialnotes() + ";";
                        }
                    }
                    if (!"1".equals(noteItem.getStation())) {
                        if (station.contains(noteItem.getStation())) {
                            nameRemark = nameRemark + noteItem.getGoodsname() + ":此班列的" + noteItem.getStation() + "口岸" + noteItem.getSpecialnotes() + ";";
                        }
                    }
                }
            }
            //hs编码特殊备注
            if (null != inhs && inhs != "") {
                List<BaseGoodsnote> hsNameRemarkList = baseGoodsnoteMapper.selectBaseGoodsnoteListByOrderHs(eastandwest, inhs);
                String ishs = "0";
                for (BaseGoodsnote hsItem : hsNameRemarkList) {
                    if ("0".equals(hsItem.getInHS())) {
                        if ("0".equals(ishs)) {
                            hsRemark = hsRemark + hsItem.getInHS() + ":" + hsItem.getSpecialnotes() + ";";
                            ishs = "1";
                        }
                    } else {
                        if (("1".equals(hsItem.getUnloadSite())) && ("1".equals(hsItem.getStation()))) {
                            hsRemark = hsRemark + hsItem.getInHS() + ":" + hsItem.getSpecialnotes() + ";";
                        } else {
                            if (!"1".equals(hsItem.getUnloadSite())) {
                                if ((hsItem.getUnloadSite()).contains(unloadsite)) {
                                    hsRemark = hsRemark + hsItem.getGoodsname() + ":" + unloadsite + "站点" + hsItem.getSpecialnotes() + ";";
                                }
                            }
                            if (!"1".equals(hsItem.getStation())) {
                                if (station.contains(hsItem.getStation())) {
                                    hsRemark = hsRemark + hsItem.getGoodsname() + ":此班列的" + hsItem.getStation() + hsItem.getSpecialnotes() + ";";
                                }
                            }
                        }
                    }
                }
            }
        }
            specialRemark.put("nameRemark", nameRemark);
            specialRemark.put("hsRemark", hsRemark);
            return specialRemark;
        }

    /**
     * 箱管空箱订舱
     *
     * @param cabin
     * @return
     */
    @Transactional
    @Override
    public boolean xgAdd (Cabin cabin){

            log.debug("箱管空箱订舱数据 ：\n {}", cabin);
            // 查询是否有原订舱数据
            boolean notOld = false;
            BusiShippingorder oldBusiShippingorder = busiShippingorderMapper.selectById(cabin.getOrderId());
            if (null == oldBusiShippingorder) {
                notOld = true;
            }
            if ("0".equals(cabin.getType()) || ("1".equals(cabin.getType()) && notOld)) { // 新增
                //  订单
                BusiShippingorder busiShippingorder = getShippingOrder(cabin);
                busiShippingorder.setOrderAuditCreatedate(new Date());
                busiShippingorder.setTjFTime(new Date());
                busiShippingorderMapper.insert(busiShippingorder);
                //商品 （关联订单表的）
                BusiShippingorderGoods busiShippingorderGoods = getBusiShippingorderGoods(cabin);
                // 创建时间
                busiShippingorderGoods.setCreatedate(new Date());
                busiShippingorderGoodsService.insert(busiShippingorderGoods);
                ShippingOrder orderInfoRabbmq = new ShippingOrder();
                BeanUtils.copyProperties(busiShippingorder,orderInfoRabbmq);
                BeanUtils.copyProperties(busiShippingorderGoods,orderInfoRabbmq);
                orderInfoRabbmq.setIsexamline(busiShippingorder.getIsExamline());
                orderInfoRabbmq.setIsconsolidation(busiShippingorder.getIsConsolidation());
                pushMsg(orderInfoRabbmq,"0");
            } else if ("1".equals(cabin.getType())) { // 编辑
                //  订单
                BusiShippingorder busiShippingorder = getShippingOrder(cabin);
                busiShippingorder.setIsupdate("1");
                busiShippingorderMapper.updateById(busiShippingorder);
                BusiShippingorder newBusiShippingorder = busiShippingorderMapper.selectById(cabin.getOrderId());
                // 订单表信息变动
                BeanChangeUtil<BusiShippingorder> b1 = new BeanChangeUtil<>();
                String c1 = b1.contrastObj(oldBusiShippingorder,newBusiShippingorder);
                //商品 （关联订单表的）
                BusiShippingorderGoods busiShippingorderGoods = busiShippingorderGoodsService.selectOne(
                        new EntityWrapper<BusiShippingorderGoods>().eq("order_ID",cabin.getOrderId())
                );
                StringBuilder sb = new StringBuilder();
                if (!StringUtils.isEmpty(busiShippingorderGoods.getGoodsHistoryEditrecord())) {
                    sb.append(busiShippingorderGoods.getGoodsHistoryEditrecord());
                } else {
                    sb.append("null");
                }
                sb.append("<th>修改人：").append(cabin.getCustomer())
                  .append(",修改时间：").append(DateUtils.parseStr(new Date())).append("</th>")
                  .append(c1).append("<###>");

                busiShippingorderGoods.setGoodsHistoryEditrecord(sb.toString());
                busiShippingorderGoodsService.updateById(busiShippingorderGoods);
                ShippingOrder orderInfoRabbmq = new ShippingOrder();
                BeanUtils.copyProperties(busiShippingorder,orderInfoRabbmq);
                BeanUtils.copyProperties(busiShippingorderGoods,orderInfoRabbmq);
                orderInfoRabbmq.setEditRecord(sb.toString());
                orderInfoRabbmq.setIsexamline(busiShippingorder.getIsExamline());
                orderInfoRabbmq.setIsconsolidation(busiShippingorder.getIsConsolidation());
                pushMsg(orderInfoRabbmq,"7");
            } else if ("2".equals(cabin.getType())) { // 删除
                BusiShippingorder busiShippingorder = busiShippingorderMapper.selectById(cabin.getOrderId());
                if (null == busiShippingorder) {
                    log.debug("箱管空箱需要取消的托书不存在，{}",cabin.getOrderId());
                    return true;
                }
                //更新订单表箱量
                busiShippingorder.setContainerBoxamount("0");
                //托书操作人信息
                busiShippingorder.setIsExamline("3");
                busiShippingorder.setTjTime(new Date());
                busiShippingorderMapper.updateById(busiShippingorder);
                //商品 （关联订单表的）
                BusiShippingorderGoods busiShippingorderGoods = busiShippingorderGoodsService.selectOne(
                        new EntityWrapper<BusiShippingorderGoods>().eq("order_ID",cabin.getOrderId())
                );
                ShippingOrder orderInfoRabbmq = new ShippingOrder();
                BeanUtils.copyProperties(busiShippingorder,orderInfoRabbmq);
                BeanUtils.copyProperties(busiShippingorderGoods,orderInfoRabbmq);
                orderInfoRabbmq.setIsexamline(busiShippingorder.getIsExamline());
                orderInfoRabbmq.setIsconsolidation(busiShippingorder.getIsConsolidation());
                pushMsg(orderInfoRabbmq,"6");
            }
            return true;
        }


    /**
     * 根据箱管传过来的数据，复制到订单实体
     *
     * @param cabin
     * @return
     */
    public BusiShippingorder getShippingOrder(Cabin cabin) {
        //  订单
        BusiShippingorder busiShippingorder = new BusiShippingorder();
        // 班列id
        if (!StringUtils.isEmpty(cabin.getClassId())) {
            busiShippingorder.setClassId(cabin.getClassId());
        }

        // 班列号
        if (!StringUtils.isEmpty(cabin.getClassNo())) {
            busiShippingorder.setClassNumber(cabin.getClassNo());
        }
        // 班列编号
        if (!StringUtils.isEmpty(cabin.getClassBh())) {
            busiShippingorder.setOrderClassBh(cabin.getClassBh());
        }
        // 去回程
        if (!StringUtils.isEmpty(cabin.getClassEastWest())) {
            busiShippingorder.setClassEastandwest(String.valueOf(cabin.getClassEastWest()));
        }
        // 班列发车日
        if (!StringUtils.isEmpty(cabin.getClassDepatureTime())) {
            busiShippingorder.setClassDate(cabin.getClassDepatureTime());
        }
        // 发车城市

        // 班列目的城市

        // 贸易方式
        busiShippingorder.setDictId(2097);
        busiShippingorder.setDictName("其他贸易");

        //  0拼箱 1独立箱
        if (!StringUtils.isEmpty(cabin.getIsIndependent())) {
            if (String.valueOf(cabin.getIsIndependent()).equals("0")) {
                busiShippingorder.setIsConsolidation("1");
            } else {
                busiShippingorder.setIsConsolidation("0");
            }
        }
        //

        // 订单id
        busiShippingorder.setOrderId(cabin.getOrderId());
        // 订单号/托书
        if (!StringUtils.isEmpty(cabin.getOrderNumber())) {
            busiShippingorder.setOrderNumber(cabin.getOrderNumber());
        }
        // 重箱托书编号
        if (!StringUtils.isEmpty(cabin.getPreOrderNumber())) {
            busiShippingorder.setZxNumber(cabin.getPreOrderNumber());
        }
        // 箱量
        if (!StringUtils.isEmpty(cabin.getContainerQuantity())) {
            busiShippingorder.setContainerBoxamount(String.valueOf(cabin.getContainerQuantity()));
        }
        // 箱型
        if (!StringUtils.isEmpty(cabin.getContainerType())) {
            busiShippingorder.setContainerType(cabin.getContainerType());
        }
        // 上货站编码
        if (!StringUtils.isEmpty(cabin.getLoadStation())) {
            busiShippingorder.setOrderUploadcode(cabin.getLoadStation());
            // 上货站名称
            BusiSite busiSite1 = busiSiteService.selectOne(
                    new EntityWrapper<BusiSite>().eq("code", cabin.getLoadStation()));
            if (null != busiSite1) {
                busiShippingorder.setOrderUploadsite(busiSite1.getNameCn());
            }
        }
        // 下货站编码
        if (!StringUtils.isEmpty(cabin.getUnloadStation())) {
            busiShippingorder.setOrderUnloadcode(cabin.getUnloadStation());
            // 下货站名称
            BusiSite busiSite2 = busiSiteService.selectOne(
                    new EntityWrapper<BusiSite>().eq("code", cabin.getUnloadStation()));
            if (null != busiSite2) {
                busiShippingorder.setOrderUnloadsite(busiSite2.getNameCn());
            }
        }
        // 客户推荐人
        if (!StringUtils.isEmpty(cabin.getClientTjr())) {
            busiShippingorder.setClientTjr(cabin.getClientTjr());
        }
        // 跟单姓名
        if (!StringUtils.isEmpty(cabin.getDeclarant())) {
            busiShippingorder.setOrderMerchandiser(cabin.getDeclarant());
        }
        // 跟单邮箱declarantEmail


        // 订舱方名称
        if (!StringUtils.isEmpty(cabin.getCustomer())) {
            busiShippingorder.setClientUnit(cabin.getCustomer());
        }
        // 发货方
        if (!StringUtils.isEmpty(cabin.getShipCustomer())) {
            busiShippingorder.setShipOrederName(cabin.getShipCustomer());
        }
        // 提箱地
        if (!StringUtils.isEmpty(cabin.getFhCity())) {
            if (!StringUtils.isEmpty(cabin.getClassEastWest())) {
                if (cabin.getClassEastWest() == 0) {
                    busiShippingorder.setShipHyd(cabin.getFhCity());
                } else {
                    busiShippingorder.setShipFhSite(cabin.getFhCity());
                }
            }
        }
        // 委托ZIH提货 0是 1否
        if (!StringUtils.isEmpty(cabin.getIsNeedPickGoods())) {
            busiShippingorder.setShipOrderBinningway(String.valueOf(cabin.getIsNeedPickGoods()));
        }
        // 提货方式（整箱到车站，散货到堆场）
        if (!StringUtils.isEmpty(cabin.getThType())) {
            busiShippingorder.setShipThType(cabin.getThType());
        }
        //

        // 自送货时间
        if (!StringUtils.isEmpty(cabin.getSelfSendTime())) {
            busiShippingorder.setShipOrderSendtime(cabin.getSelfSendTime());
        }
        // 提货时间
        if (!StringUtils.isEmpty(cabin.getGoodsPickDeadline())) {
            busiShippingorder.setShipOrderUnloadtime(cabin.getGoodsPickDeadline());
        }
        // 提货地址
        if (!StringUtils.isEmpty(cabin.getGoodsPickAddr())) {
            busiShippingorder.setShipOrderUnloadaddress(cabin.getGoodsPickAddr());
        }
        // 到货城市receiveHyd
        if (!StringUtils.isEmpty(cabin.getDhCity())) {
            busiShippingorder.setReceiveHyd(cabin.getDhCity());
        }
        // 分拨方式isNeedSendGoods
        if (!StringUtils.isEmpty(cabin.getIsNeedSendGoods())) {
            busiShippingorder.setShipOrderBinningway(String.valueOf(cabin.getIsNeedPickGoods()));
        }

        // 还箱地
        if (!StringUtils.isEmpty(cabin.getHxd())) {
            busiShippingorder.setReceiveHxAddress(cabin.getHxd());
        }
        // 箱管费
        if (!StringUtils.isEmpty(cabin.getXgFee())) {
            busiShippingorder.setReceiveXgCost(cabin.getXgFee());
        }

        // 审核时间
        if (!StringUtils.isEmpty(cabin.getExamineTime())) {
            busiShippingorder.setExameTime(cabin.getExamineTime());
        }
        // 线路类型
        if (!StringUtils.isEmpty(cabin.getLineTypeid())) {
            busiShippingorder.setLineTypeid(String.valueOf(cabin.getLineTypeid()));
        }
        // 箱属
        if (!StringUtils.isEmpty(cabin.getOrderAuditBelongTo())) {
            busiShippingorder.setOrderAuditBelongto(cabin.getOrderAuditBelongTo());
        }
        // 去程提货地，回程送货地详细地址（去回程）
        if (!StringUtils.isEmpty(cabin.getDetailedAddress())) {
            busiShippingorder.setDetailedAddress(cabin.getDetailedAddress());
        }
        // 通讯地址
        if (!StringUtils.isEmpty(cabin.getReceiveOrderAddr())) {
            busiShippingorder.setReceiveOrderAddress(cabin.getReceiveOrderAddr());
        }
        //

//      busiShippingorder.setReceiveOrderPartaddress();

        busiShippingorder.setTjTime(new Date());
//      busiShippingorder.setClientId();

        // ZIH报关
        busiShippingorder.setClientOrderBindingway("0");

        // 审核通过
        busiShippingorder.setIsExamline("1");
//      busiShippingorder.setShipOrderId();
//      busiShippingorder.setReceiveOrderId();
        // 是否需要发票 0否 1是
        busiShippingorder.setOrderIsticket("1");

        // 订舱客户id
        if (!StringUtils.isEmpty(cabin.getClientId())) {
            busiShippingorder.setClientId(cabin.getClientId());
        } else { // 84886ee5-6f04-4227-a358-011423e34e6d
            busiShippingorder.setClientId("84886ee5-6f04-4227-a358-011423e34e6d");
        }
        BusiClients busiClients = clientsService.selectById(busiShippingorder.getClientId());
        // 创建时间
        busiShippingorder.setOrderAuditCreatedate(new Date());
        // 邮箱
        String emails = busiClients.getClientEmail();
        // 客户推荐人
        busiShippingorder.setClientTjrId(busiClients.getClientTjrId());
        busiShippingorder.setClientTjr(busiClients.getClientTjr());
        if (!StringUtils.isEmpty(cabin.getClassEastWest())) {
            if (busiShippingorder.getClassEastandwest().equals("0")) {//xi
                busiShippingorder.setOrderMerchandiser(busiClients.getwMerchandiser());//西向跟单员
                busiShippingorder.setOrderMerchandiserId(busiClients.getwMerchandiserId());//跟单员id
            }
            if (busiShippingorder.getClassEastandwest().equals("1")) {//dong
                busiShippingorder.setOrderMerchandiser(busiClients.geteMerchandiser());
                busiShippingorder.setOrderMerchandiserId(busiClients.geteMerchandiserId());
            }
        }
        // 订舱方联系人
        busiShippingorder.setClientContacts(busiClients.getClientContacts());
        // 订舱方联系方式
        busiShippingorder.setClientTel(busiClients.getClientTel());
        // 订舱方邮箱
        busiShippingorder.setClientEmail(busiClients.getClientEmail());
        // 订舱方地址
        busiShippingorder.setClientAddress(busiClients.getClientAddress());
        // 委托zih报关：0是 1否
        busiShippingorder.setClientOrderBindingway("0");
        // 报关地点
        if (!StringUtils.isEmpty(cabin.getClassEastWest())) {
            if ("0".equals(busiShippingorder.getClassEastandwest())) {
                busiShippingorder.setClientOrderBindingaddress("郑州");
            } else if ("1".equals(busiShippingorder.getClassEastandwest()) &&
                    "0".equals(busiShippingorder.getLineTypeid())) {
                if ("汉堡".equals(busiShippingorder.getOrderUploadsite())) {
                    busiShippingorder.setClientOrderBindingaddress("汉堡");
                } else if ("慕尼黑".equals(busiShippingorder.getOrderUploadsite())) {
                    busiShippingorder.setClientOrderBindingaddress("慕尼黑");
                }
            } else {
                busiShippingorder.setClientOrderBindingaddress("属地");
            }
        }

        // 发货方
        busiShippingorder.setShipOrederName("0");
        // 发货方联系人
        busiShippingorder.setShipOrederContacts("0");
        // 发货方邮箱
        busiShippingorder.setShipOrederEmail(emails);
        // 发货方联系电话
        busiShippingorder.setShipOrederPhone("0");
        // 发货方地址
        busiShippingorder.setShipOrederAddress("0");

        // 提货方式（整箱到车站，散货到堆场
        // 委托ZIH提货（0是整箱到车站，1是散货到堆场）针对整柜
        busiShippingorder.setShipThTypeId("0");
        busiShippingorder.setShipThType("整箱到车站");
        /**
         * 发货方自送货方式 0散货到堆场 1整箱到车站
         */
        busiShippingorder.setShipZsTypeId("1");
        busiShippingorder.setShipZsType("整箱到车站");

        // 分拨方式

        // 收货方名称
        busiShippingorder.setReceiveOrderName("0");
        // 收货方联系人
        busiShippingorder.setReceiveOrderContacts("0");
        // 到站通知提货邮箱
        busiShippingorder.setReceiveOrderMail(emails);
        // 收货方联系电话
        busiShippingorder.setReceiveOrderPhone("0");
        // 收货方邮箱
        busiShippingorder.setReceiveOrderEmail(emails);
        // 通讯地址
        busiShippingorder.setReceiveOrderAddress("0");
        // 是否由ZIH代理清关  0否 1是
        busiShippingorder.setReceiveOrderIsclearance("1");
        // 是否由ZIH代理送货  0否 1是
        busiShippingorder.setReceiveOrderIspart("1");
        // 送货地址
        busiShippingorder.setReceiveOrderPartaddress("0");
        // 送货分拨联系人
        busiShippingorder.setReceiveOrderZihcontacts("0");
        // 送货分拨邮箱
        busiShippingorder.setReceiveOrderZihemail(emails);
        // 送货分拨联系电话
        busiShippingorder.setReceiveOrderZihtel("0");
        // 在途信息接收邮箱
        busiShippingorder.setReceiveOrderReceiveemail(emails);

        return busiShippingorder;
    }


    /**
     * 根据箱管传过来的数据，复制到订单货物实体
     * @param cabin
     * @return
     */
    public BusiShippingorderGoods getBusiShippingorderGoods(Cabin cabin){
        //商品 （关联订单表的）
        BusiShippingorderGoods busiShippingorderGoods = new BusiShippingorderGoods();
        // 订单ID
        busiShippingorderGoods.setOrderId(cabin.getOrderId());
        // 唛头
        busiShippingorderGoods.setGoodsMark("0");
        // 货品中文名称
        if (!StringUtils.isEmpty(cabin.getGoodsTypes())) {
            busiShippingorderGoods.setGoodsName(cabin.getGoodsTypes());
        }
        // 货品英文名称
        if (!StringUtils.isEmpty(cabin.getGoodsTypesEn())) {
            busiShippingorderGoods.setGoodsEnName(cabin.getGoodsTypesEn());
        }
        // 最外层包装形式
        if (!StringUtils.isEmpty(cabin.getGoodsPacking())) {
            busiShippingorderGoods.setGoodsPacking(cabin.getGoodsPacking());
        }
        // 最外层包装数量
        if (!StringUtils.isEmpty(cabin.getGoodsNumber())) {
            busiShippingorderGoods.setGoodsNumber(cabin.getGoodsNumber());
        }
        // 体积
        if (!StringUtils.isEmpty(cabin.getGoodsVolume())) {
            busiShippingorderGoods.setGoodsCbm(cabin.getGoodsVolume());
        }
        // 重量
        if (!StringUtils.isEmpty(cabin.getGoodsWeight())) {
            busiShippingorderGoods.setGoodsKgs(cabin.getGoodsWeight());
        }
        // 货物备注
        if (!StringUtils.isEmpty(cabin.getRemark())) {
            busiShippingorderGoods.setRemark(cabin.getRemark());
        }
        // 国内清关HS
        busiShippingorderGoods.setGoodsClearance("0");
        // 国外清关HS
        busiShippingorderGoods.setGoodsOutClearance("0");
        // 国内报关HS
        busiShippingorderGoods.setGoodsInReport("0");
        // 国外报关HS
        busiShippingorderGoods.setGoodsReport("0");

        return busiShippingorderGoods;
    }

    /**
     * 国外场站拼箱订舱
     *
     * @param gwczBookingVo
     * @return
     */
    @Transactional
    @Override
    public boolean gwczAdd (GwczBookingVo gwczBookingVo){
            log.debug("新增国外场站拼箱订舱数据 ：\n {}", gwczBookingVo);
            // 查询新增或编辑
            List<BusiShippingorder> busiShippingorders =
                    busiShippingorderMapper.selectList(
                            new EntityWrapper<BusiShippingorder>().eq("zx_number", gwczBookingVo.getId()));
            List<BusiClasses> busiClasses = busiClassesMapper.selectList(
                    new EntityWrapper<BusiClasses>().eq("class_bh", gwczBookingVo.getClassNumber()));
            if (ObjectUtils.isEmpty(busiClasses)) {
                log.debug("没有这个班列信息,班列号：{}", gwczBookingVo.getClassNumber());
                return true;
            }
            if (ObjectUtils.isEmpty(busiShippingorders)) {
                //  订单
                BusiShippingorder busiShippingorder = new BusiShippingorder();

                // 暂存拼箱数据id
                busiShippingorder.setZxNumber(String.valueOf(gwczBookingVo.getId()));

                // 审核状态
                busiShippingorder.setIsExamline("0");
                // 站到站
                busiShippingorder.setBookingService("2");
                //  0拼箱 1独立箱
                busiShippingorder.setIsConsolidation("0");
                // 箱属
                busiShippingorder.setOrderAuditBelongto("0");
                // 提交时间
                busiShippingorder.setTjTime(new Date());
                // 去回程
                busiShippingorder.setClassEastandwest(busiClasses.get(0).getClassEastandwest());
                // 线路id
                busiShippingorder.setLineTypeid(String.valueOf(busiClasses.get(0).getLineTypeid()));
                // 是否可提前班列（0是1否）
                busiShippingorder.setPutoffClass(1);
                // 班列id
                busiShippingorder.setClassId(String.valueOf(busiClasses.get(0).getClassId()));
                // 班列号
                busiShippingorder.setClassNumber(busiClasses.get(0).getClassNumber());
                // 班列编号
                busiShippingorder.setOrderClassBh(busiClasses.get(0).getClassBh());

                // 班列发车日
                busiShippingorder.setClassDate(busiClasses.get(0).getClassStime());

                List<BusiSite> up = busiSiteMapper.selectList(
                        new EntityWrapper<BusiSite>().eq("name_cn", gwczBookingVo.getUploadSite1()));
                List<BusiSite> down = busiSiteMapper.selectList(
                        new EntityWrapper<BusiSite>().eq("name_cn", gwczBookingVo.getUnloadSite1()));
                // 上货站
                busiShippingorder.setOrderUploadsite(gwczBookingVo.getUploadSite1());
                if (!ObjectUtils.isEmpty(up)) {
                    // 上货站编码
                    busiShippingorder.setOrderUploadcode(up.get(0).getCode());
                }
                // 下货站
                busiShippingorder.setOrderUnloadsite(gwczBookingVo.getUnloadSite1());
                if (!ObjectUtils.isEmpty(down)) {
                    // 下货站编码
                    busiShippingorder.setOrderUnloadcode(down.get(0).getCode());
                }

                // 贸易方式
                busiShippingorder.setDictId(2097);
                busiShippingorder.setDictName("其他贸易");

                // 是否需要发票 0否 1是
                busiShippingorder.setOrderIsticket("1");

                // 客户id
                busiShippingorder.setClientId("660ebb56-1be9-47cf-a45b-f1de355291c8");
                BusiClients busiClients = clientsService.selectById(busiShippingorder.getClientId());
                // 创建时间
                busiShippingorder.setOrderAuditCreatedate(new Date());
                // 创建人姓名
                if(StringUtils.isEmpty(gwczBookingVo.getZihtjr())){
                    busiShippingorder.setOrderAuditUsername(busiClients.getClientContacts());
                } else {
                    busiShippingorder.setOrderAuditUsername(gwczBookingVo.getZihtjr());
                }
                // 邮箱
                String emails = busiClients.getClientEmail();
                if (!StringUtils.isEmpty(gwczBookingVo.getEmail())) {
                    emails = gwczBookingVo.getEmail();
                }

                // 客户推荐人
                busiShippingorder.setClientTjrId(busiClients.getClientTjrId());
                busiShippingorder.setClientTjr(busiClients.getClientTjr());
                if (busiClasses.get(0).getClassEastandwest().equals("0")) {//xi
                    busiShippingorder.setOrderMerchandiser(busiClients.getwMerchandiser());//西向跟单员
                    busiShippingorder.setOrderMerchandiserId(busiClients.getwMerchandiserId());//跟单员id
                }
                if (busiClasses.get(0).getClassEastandwest().equals("1")) {//dong
                    busiShippingorder.setOrderMerchandiser(busiClients.geteMerchandiser());
                    busiShippingorder.setOrderMerchandiserId(busiClients.geteMerchandiserId());
                }
                // 订舱方名称
                busiShippingorder.setClientUnit(busiClients.getClientUnit());
                // 订舱方联系人
                busiShippingorder.setClientContacts(busiClients.getClientContacts());
                // 订舱方联系方式
                busiShippingorder.setClientTel(busiClients.getClientTel());
                // 订舱方邮箱
                busiShippingorder.setClientEmail(busiClients.getClientEmail());
                // 订舱方地址
                busiShippingorder.setClientAddress(busiClients.getClientAddress());
                // 委托zih报关：0是 1否
                busiShippingorder.setClientOrderBindingway("0");
                // 报关地点
                if ("0".equals(busiShippingorder.getClassEastandwest())) {
                    busiShippingorder.setClientOrderBindingaddress("郑州");
                } else if ("1".equals(busiShippingorder.getClassEastandwest()) &&
                        "0".equals(busiShippingorder.getLineTypeid())) {
                    if ("汉堡".equals(busiShippingorder.getOrderUploadsite())) {
                        busiShippingorder.setClientOrderBindingaddress("汉堡");
                    } else if ("慕尼黑".equals(busiShippingorder.getOrderUploadsite())) {
                        busiShippingorder.setClientOrderBindingaddress("慕尼黑");
                    }
                } else {
                    busiShippingorder.setClientOrderBindingaddress("属地");
                }

                // 发货方
                busiShippingorder.setShipOrederName("陆港拼箱");
                // 发货方联系人
                busiShippingorder.setShipOrederContacts("陆港拼箱");
                // 发货方邮箱
                busiShippingorder.setShipOrederEmail(emails);
                // 发货方联系电话
                busiShippingorder.setShipOrederPhone("陆港拼箱");
                // 发货方地址
                busiShippingorder.setShipOrederAddress("陆港拼箱");
                // 委托ZIH提货 0是 1否  2铁路到货
                busiShippingorder.setShipOrderBinningway("1");
                // 提箱地
                if (busiClasses.get(0).getClassEastandwest().equals("0")) {
                    busiShippingorder.setShipHyd(gwczBookingVo.getTixiang());
                } else {
                    busiShippingorder.setShipFhSite(gwczBookingVo.getTixiang());
                }
                // 提货方式（整箱到车站，散货到堆场
                // 委托ZIH提货（0是整箱到车站，1是散货到堆场）针对整柜
                busiShippingorder.setShipThTypeId("0");
                busiShippingorder.setShipThType("整箱到车站");
                /**
                 * 发货方自送货方式 0散货到堆场 1整箱到车站
                 */
                busiShippingorder.setShipZsTypeId("1");
                busiShippingorder.setShipZsType("整箱到车站");

                // 自送货时间
                busiShippingorder.setShipOrderSendtime(busiShippingorder.getClassDate());
                // 提货时间
                busiShippingorder.setShipOrderUnloadtime(busiShippingorder.getClassDate());
                // 提货地址
                // busiShippingorder.setShipOrderUnloadaddress();
                // 到货城市receiveHyd
                // busiShippingorder.setReceiveHyd();

                // 分拨方式

                // 收货方名称
                busiShippingorder.setReceiveOrderName("陆港拼箱");
                // 收货方联系人
                busiShippingorder.setReceiveOrderContacts("陆港拼箱");
                // 到站通知提货邮箱
                busiShippingorder.setReceiveOrderMail(emails);
                // 收货方联系电话
                busiShippingorder.setReceiveOrderPhone("陆港拼箱");
                // 收货方邮箱
                busiShippingorder.setReceiveOrderEmail(emails);
                // 通讯地址
                busiShippingorder.setReceiveOrderAddress("陆港拼箱");
                // 是否由ZIH代理清关  0否 1是
                busiShippingorder.setReceiveOrderIsclearance("1");
                // 是否由ZIH代理送货  0否 1是
                busiShippingorder.setReceiveOrderIspart("1");
                // 送货地址
                busiShippingorder.setReceiveOrderPartaddress("陆港拼箱");
                // 送货分拨联系人
                busiShippingorder.setReceiveOrderZihcontacts("陆港拼箱");
                // 送货分拨邮箱
                busiShippingorder.setReceiveOrderZihemail(emails);
                // 送货分拨联系电话
                busiShippingorder.setReceiveOrderZihtel("陆港拼箱");
                // 在途信息接收邮箱
                busiShippingorder.setReceiveOrderReceiveemail(emails);
                // 还箱地
                busiShippingorder.setReceiveHxAddress(gwczBookingVo.getHuanxiang());

                // 箱型箱量数据2*20GP
                String message = gwczBookingVo.getMessage();
                String[] arr = message.split("\\*");
                busiShippingorder.setContainerType(arr[1]);
                busiShippingorder.setContainerTypeval(arr[1]);
                busiShippingorder.setContainerBoxamount(arr[0]);
                busiShippingorderMapper.insert(busiShippingorder);
                //商品 （关联订单表的）
                BusiShippingorderGoods busiShippingorderGoods = new BusiShippingorderGoods();
                // 订单ID
                busiShippingorderGoods.setOrderId(busiShippingorder.getOrderId());
                // 唛头
                busiShippingorderGoods.setGoodsMark("陆港拼箱");
                // 货品中文名称
                busiShippingorderGoods.setGoodsName("陆港拼箱");
                // 货品英文名称
                busiShippingorderGoods.setGoodsEnName("陆港拼箱");
                // 国内清关HS
                busiShippingorderGoods.setGoodsClearance("陆港拼箱");
                // 国外清关HS
                busiShippingorderGoods.setGoodsOutClearance("陆港拼箱");
                // 国内报关HS
                busiShippingorderGoods.setGoodsInReport("陆港拼箱");
                // 国外报关HS
                busiShippingorderGoods.setGoodsReport("陆港拼箱");
                // 最外层包装形式
                busiShippingorderGoods.setGoodsPacking("陆港拼箱");
                // 最外层包装数量
                busiShippingorderGoods.setGoodsNumber("0");
                // 体积
                busiShippingorderGoods.setGoodsCbm("0");
                // 重量
                busiShippingorderGoods.setGoodsKgs("0");
                // 规格
                busiShippingorderGoods.setGoodsStandard("0");
                // 是否需要装箱方案：0需要 1不需要
                busiShippingorderGoods.setGoodsIsscheme("1");
                // 创建时间
                busiShippingorderGoods.setCreatedate(new Date());
                busiShippingorderGoodsService.insert(busiShippingorderGoods);
            } else {
                BusiShippingorder busiShippingorder = busiShippingorders.get(0);
                // 客户id
                busiShippingorder.setClientId("660ebb56-1be9-47cf-a45b-f1de355291c8");
                BusiClients busiClients = clientsService.selectById(busiShippingorder.getClientId());
                // 邮箱
                String emails = busiClients.getClientEmail();
                if (!StringUtils.isEmpty(gwczBookingVo.getEmail())) {
                    emails = gwczBookingVo.getEmail();
                }
                // 审核状态
                busiShippingorder.setIsExamline("0");
                // 提交时间
                busiShippingorder.setTjTime(new Date());
                // 去回程
                busiShippingorder.setClassEastandwest(busiClasses.get(0).getClassEastandwest());
                // 线路id
                busiShippingorder.setLineTypeid(String.valueOf(busiClasses.get(0).getLineTypeid()));
                // 是否可提前班列（0是1否）
                busiShippingorder.setPutoffClass(1);
                // 班列id
                busiShippingorder.setClassId(String.valueOf(busiClasses.get(0).getClassId()));
                // 班列号
                busiShippingorder.setClassNumber(busiClasses.get(0).getClassNumber());
                // 班列编号
                busiShippingorder.setOrderClassBh(busiClasses.get(0).getClassBh());

                // 班列发车日
                busiShippingorder.setClassDate(busiClasses.get(0).getClassStime());

                List<BusiSite> up = busiSiteMapper.selectList(
                        new EntityWrapper<BusiSite>().eq("name_cn", gwczBookingVo.getUploadSite1()));
                List<BusiSite> down = busiSiteMapper.selectList(
                        new EntityWrapper<BusiSite>().eq("name_cn", gwczBookingVo.getUnloadSite1()));
                // 上货站
                busiShippingorder.setOrderUploadsite(gwczBookingVo.getUploadSite1());
                if (!ObjectUtils.isEmpty(up)) {
                    // 上货站编码
                    busiShippingorder.setOrderUploadcode(up.get(0).getCode());
                }
                // 下货站
                busiShippingorder.setOrderUnloadsite(gwczBookingVo.getUnloadSite1());
                if (!ObjectUtils.isEmpty(down)) {
                    // 下货站编码
                    busiShippingorder.setOrderUnloadcode(down.get(0).getCode());
                }
                // 发货方邮箱
                busiShippingorder.setShipOrederEmail(emails);
                // 提箱地
                if (busiClasses.get(0).getClassEastandwest().equals("0")) {
                    busiShippingorder.setShipHyd(gwczBookingVo.getTixiang());
                } else {
                    busiShippingorder.setShipFhSite(gwczBookingVo.getTixiang());
                }
                // 报关地点
                if ("0".equals(busiShippingorder.getClassEastandwest())) {
                    busiShippingorder.setClientOrderBindingaddress("郑州");
                } else if ("1".equals(busiShippingorder.getClassEastandwest()) &&
                        "0".equals(busiShippingorder.getLineTypeid())) {
                    if ("汉堡".equals(busiShippingorder.getOrderUploadsite())) {
                        busiShippingorder.setClientOrderBindingaddress("汉堡");
                    } else if ("慕尼黑".equals(busiShippingorder.getOrderUploadsite())) {
                        busiShippingorder.setClientOrderBindingaddress("慕尼黑");
                    }
                } else {
                    busiShippingorder.setClientOrderBindingaddress("属地");
                }

                // 到站通知提货邮箱
                busiShippingorder.setReceiveOrderMail(emails);
                // 收货方邮箱
                busiShippingorder.setReceiveOrderEmail(emails);
                // 送货分拨邮箱
                busiShippingorder.setReceiveOrderZihemail(emails);
                // 在途信息接收邮箱
                busiShippingorder.setReceiveOrderReceiveemail(emails);
                // 还箱地
                busiShippingorder.setReceiveHxAddress(gwczBookingVo.getHuanxiang());
                // 自送货时间
                busiShippingorder.setShipOrderSendtime(busiShippingorder.getClassDate());
                // 提货时间
                busiShippingorder.setShipOrderUnloadtime(busiShippingorder.getClassDate());
                // 箱型箱量数据2*20GP
                String message = gwczBookingVo.getMessage();
                String[] arr = message.split("\\*");
                busiShippingorder.setContainerType(arr[1]);
                busiShippingorder.setContainerTypeval(arr[1]);
                busiShippingorder.setContainerBoxamount(arr[0]);
                busiShippingorderMapper.updateById(busiShippingorder);
            }
            return true;
        }

    /**
     * 推送子系统托书
     * @param orderInfoRabbmq
     * @param messageType
     */
    public void pushMsg(ShippingOrder orderInfoRabbmq,String messageType){

            // messageType 0新的，6取消托书，7修改托书
            orderInfoRabbmq.setIsDelete("0");//删除标识 0否 1是
            orderInfoRabbmq.setMessageType(messageType); //操作类型

            //货物规格
            List<BookingInquiryGoodsDetails> goodsList = busiShippingorderMapper.selectGoodsInfo(orderInfoRabbmq.getInquiryRecordId());
            if(goodsList.size()>0){
                String goodsStandard = "";
                for(BookingInquiryGoodsDetails goodsItem:goodsList){
                    String standard = goodsItem.getGoodsLength()+"*"+goodsItem.getGoodsWidth()+"*"+goodsItem.getGoodsHeight()+"*"+goodsItem.getGoodsAmount()+";";
                    goodsStandard += standard;
                }
                goodsStandard = goodsStandard.substring(0,goodsStandard.length()-1); //截掉末尾分号
                orderInfoRabbmq.setGoodsStandard(goodsStandard);
            }
            //修改记录
            String editRecord = orderInfoRabbmq.getEditRecord();
            if(!StringUtils.isEmpty(editRecord)){
                editRecord = editRecord.substring(4);
                if(!StringUtils.isEmpty(editRecord)){
                    editRecord = StringUtils.replace(editRecord,"<###>","<br/>");
                    editRecord = StringUtils.replace(editRecord,"<td>",";");
                }
                orderInfoRabbmq.setEditRecord(editRecord);
            }
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            MessageProperties header = new MessageProperties();
            header.getHeaders().put("__TypeId__","Object");
            ObjectMapper objectMapper = new ObjectMapper();
        Message message = null;
        try {
            message = new Message(objectMapper.writeValueAsBytes(orderInfoRabbmq), header);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        rabbitTemplate.convertAndSend("order.dynamic.topic.exchange", "order.dynamic.check", message,correlationData);
    }

    @Override
    public String getClassEastAndWest(String orderId) {
        return busiShippingorderMapper.getClassEastAndWest(orderId);
    }


}
