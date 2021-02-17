package com.zih.booking.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.zih.booking.dao.BusiShippingorderMapper;
import com.zih.booking.dao.TrackIconMapper;
import com.zih.booking.dao.TrackTwoLevelMapper;
import com.zih.booking.model.*;
import com.zih.booking.request.BaseRequest;
import com.zih.booking.response.OneNodeResponse;
import com.zih.booking.response.TrainDetailResponse;
import com.zih.booking.response.TwoNodeResponse;
import com.zih.booking.service.*;
import com.zih.booking.system.enums.LanguageEnum;
import com.zih.booking.system.enums.ResultStatusCode;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.utils.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
@Slf4j
@RestController
@RequestMapping("/gw")
@Api(tags = "官网运踪")
public class GuanWangController {
    @Autowired
    private BusiShippingorderMapper busiShippingorderMapper;
    @Autowired
    private BookingCityService bookingCityService;
    @Autowired
    private TrackIconMapper iconMapper;
    @Autowired
    BusiZyInfoService busiZyInfoService;
    @Autowired
    private DefaultKaptcha producer;
    @Autowired
    private TrackTwoLevelMapper trackTwoLevelMapper;
    @Autowired
    private TrackGoodsStatusService trackGoodsStatusService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TrackGoodsStatusService goodsStatusService;
    @Autowired
    private TrackAbnormalBoxService trackAbnormalBoxService;
    @Autowired
    private BusiShippingorderService busiShippingorderService;

    @GetMapping("/getTwoNode")
    @ApiOperation("2级时间节点")
    public ApiResultI18n getTwoNode(String id, String language, String number) {
        List<TwoNodeResponse> list = trackTwoLevelMapper.selectTwoNode(id);
        if(list!=null&&list.size()>0){
            String orderId=list.get(0).getOrderId();
            BusiShippingorder shippingOrder=busiShippingorderService.selectOne(new EntityWrapper<BusiShippingorder>().eq("order_id",orderId));
            if("0".equals(shippingOrder.getClassEastandwest())&&"2".equals(shippingOrder.getLineTypeid())){
                String zyRemrk="";
                //获取计划班列号
                //BusiZyInfo zyInfo=busiZyInfoService.selectZyInfoByOrder(orderId,boxNum);
                if (number.startsWith("ZIH")) {
                    List<BusiZyInfo> zyInfoList=busiZyInfoService.selectList(new EntityWrapper<BusiZyInfo>().eq("order_id",orderId).eq("del_flag","0"));
                    for(int i=0;i<zyInfoList.size();i++){
                        zyRemrk=zyRemrk+zyInfoList.get(i).getXianghao()+"-计划"+zyInfoList.get(i).getClasszyNo()+"\r\n";
                    }
                }else{
                    BusiZyInfo zyInfo=busiZyInfoService.selectOne(new EntityWrapper<BusiZyInfo>().eq("order_id",orderId).eq("del_flag","0").eq("xianghao",number));
                    zyRemrk="计划"+zyInfo.getClasszyNo();
                }
                for(int i=0;i<list.size();i++){
                    if("已进站".equals(list.get(i).getNameZh())){
                        list.get(i).setRemark(StringUtils.isNotEmpty(list.get(i).getRemark())?list.get(i).getRemark():""+zyRemrk);
                        list.get(i).setRemarkEn(StringUtils.isNotEmpty(list.get(i).getRemarkEn())?list.get(i).getRemarkEn():""+zyRemrk.replaceAll("计划","plan"));
                    }
                }
            }
        }
        if (language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
            for (TwoNodeResponse trackTwoLevel : list) {
                trackTwoLevel.setNameZh(trackTwoLevel.getNameEn());
                trackTwoLevel.setRemark(trackTwoLevel.getRemarkEn());
            }
        }
        return ApiResultI18n.success(list, language);
    }

    @ApiOperation("运综详情 + 一级时间节点")
    @PostMapping("/getTrainDetail")
    public ApiResultI18n getTrainDetail(@RequestParam String number, String language) {
        String tip="";
        /*String codeRedis = (String) redisTemplate.opsForValue().get(codeToken);

       if (!(StringUtils.isNotEmpty(codeRedis) && codeRedis.equals(code))) {

            return ApiResultI18n.failure(ResultStatusCode.VERIFY_CODE_ERROR, language);
        }*/
        if (number.startsWith("ZIH")) {//仓位号
            List<BusiShippingorder> list = busiShippingorderMapper.selectList(new EntityWrapper<BusiShippingorder>().eq("order_number", number));
            if (list.size() == 0) {
                //return ApiResultI18n.failure(1, "没有查询到仓位号是" + number + "的运踪数据", language);
                if("en".equals(language)){
                    tip="Please enter the correct Booking No.";
                }else{
                    tip="请输入符合要求的舱位号";
                }
                return ApiResultI18n.failure(1, tip, language);
            } else {
                String orderId = list.get(0).getOrderId();
                JSONObject json = getDetail(orderId, number,language);
                return ApiResultI18n.success(json, language);
            }
        } else {//箱号 拼箱箱号不能查询
            if("en".equals(language)){
                tip="Please enter the correct FCL Container No.";
            }else{
                tip="请输入符合要求的整柜箱号";
            }
            List<TrackGoodsStatus> tgsList=trackGoodsStatusService.selectList(new EntityWrapper<TrackGoodsStatus>().eq("box_num", number).eq("del_flag",0).orderBy("id",false));
            //没查到舱位号
            if(tgsList.size() == 0){
                return ApiResultI18n.failure(1, tip, language);
            }else{
                List<BusiShippingorder> bsList=busiShippingorderMapper.selectList(new EntityWrapper<BusiShippingorder>().eq("order_ID", tgsList.get(0).getOrderId()));
                if(bsList.size() == 0){
                    return ApiResultI18n.failure(1, tip, language);
                }else{
                    if("1".equals(bsList.get(0).getIsConsolidation())){
                        if("en".equals(language)){
                            tip="The LCL container number cannot be queried，Please enter the booking number";
                        }else{
                            tip="拼箱箱号无法查询，请输入仓位号";
                        }
                        return ApiResultI18n.failure(1, tip, language);
                    }else{
                        String orderId = bsList.get(0).getOrderId();
                        JSONObject json = getDetail(orderId, number,language);
                        return ApiResultI18n.success(json, language);
                    }
                }
            }
        }
    }

    public JSONObject getDetail(String orderId, String number,String language) {

        JSONObject json = new JSONObject();
        json.put("abnormal", 0);
        TrainDetailResponse trainDetailResponse = busiShippingorderMapper.selectTrainDetail(orderId);
        if (language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
            trainDetailResponse.setOrderUploadsite(getEnAddress(trainDetailResponse.getOrderUploadsite()));
            trainDetailResponse.setOrderUnloadsite(getEnAddress(trainDetailResponse.getOrderUnloadsite()));
        }
        List<OneNodeResponse> node = iconMapper.selectZhOneNode(orderId);
        if (language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
            node = iconMapper.selectEnOneNode(orderId);
        }
        //查询货物状态表数据
        List<TrackGoodsStatus> abList=new ArrayList<>();
        if(number.startsWith("ZIH")){
            abList=goodsStatusService.selectList(new EntityWrapper<TrackGoodsStatus>().eq("order_id",orderId).eq("del_flag","0").eq("is_normal",1));
        }else{
            abList=goodsStatusService.selectList(new EntityWrapper<TrackGoodsStatus>().eq("order_id",orderId).eq("box_num",number).eq("del_flag","0").eq("is_normal",1));
        }
        if(abList!=null&&abList.size()!=0){
           node.get(3).setState(2);//班列运踪异常
           node.get(3).setNodeName("异常箱运踪");
           if(language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())){
               node.get(3).setNodeName("AbnormalBox Tracking");
           }
           List<List<TrackAbnormalBox>> abnormalList=new ArrayList<>();
           for(int i=0;i<abList.size();i++){
               List<TrackAbnormalBox> abnormalBoxList = trackAbnormalBoxService.selectList(new EntityWrapper<TrackAbnormalBox>().eq("order_id",orderId).
                       eq("del_flag","0").eq("box_num",abList.get(i).getBoxNum()).orderBy("update_time", false));
               if(abnormalBoxList!=null&&abnormalBoxList.size()>0){
                   abnormalList.add(abnormalBoxList);
               }
           }
            json.put("abnormal", 1);
            json.put("abnormalBox", abnormalList);
       }
        json.put("detail", trainDetailResponse);

        BusiShippingorder shippingOrder=busiShippingorderService.selectOne(new EntityWrapper<BusiShippingorder>().eq("order_id",orderId));
        //中亚去程只显示到班列运踪
        if("0".equals(shippingOrder.getClassEastandwest())&&"2".equals(shippingOrder.getLineTypeid())){
            node=node.subList(0,4);
   /*         BusiZyInfo zyInfo=busiZyInfoService.selectOne(new EntityWrapper<BusiZyInfo>().eq("order_id",orderId).eq("del_flag","0").
                    eq("box_num",abList.get(i).getBoxNum()));
            node.add(node.get(3));
            node.get(3).setNodeName("发运计划");
            if (language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
                node.get(3).setNodeName("Shipping plan");
            }
            node.get(3).setSort(4);
            node.get(3).setState(0);
            node.get(3).setTime(zyInfo.getClasszyNo());
            node.get(3).setIcon(node.get(2).getIcon())*/;
        }else if("1".equals(shippingOrder.getClassEastandwest())&&"2".equals(shippingOrder.getLineTypeid())){
            node.remove(1);
            node.remove(1);
        }
        json.put("node", node);
        return json;
    }


    @ApiOperation(value = "获取验证码", notes = "获取验证码的接口")
    @GetMapping(value = "/getCode")
    public ApiResultI18n generateVerificationCode(BaseRequest baseRequest) {
        Map<String, Object> map = new HashMap<>();
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        ByteArrayOutputStream outputStream = null;
        BufferedImage image = producer.createImage(text);
        outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return ApiResultI18n.failure(ResultStatusCode.VERIFY_CODE_GENERATE_ERROR, baseRequest.getLanguage());
        }
        //base64图片
        BASE64Encoder encoder = new BASE64Encoder();
        String imageStr = encoder.encode(outputStream.toByteArray());
        //删除 \r\n
        imageStr = imageStr.replaceAll("\n", "").replaceAll("\r", "");
        map.put("img", imageStr);
        //生成验证码对应的token  以token为key  验证码为value存在redis中
        String codeToken = UUIDUtils.getStringRandom(16);
        redisTemplate.opsForValue().set(codeToken, text, 10, TimeUnit.MINUTES);
        map.put("codeToken", codeToken);
        log.debug("-------------------------------start-----------------------------------");
        log.debug("当前时间:"+new Date().toLocaleString());
        log.debug("生成的验证码"+text);
        log.debug("生成的的codeToken："+codeToken);
        log.debug("-------------------------------end-----------------------------------");
        return ApiResultI18n.success(map, baseRequest.getLanguage());
    }


    private String getEnAddress(String address) {
        String enAddress = address;
        if (bookingCityService.selectList(new EntityWrapper<BookingCity>().eq("name_cn", address)).size() != 0) {
            enAddress = bookingCityService.selectList(new EntityWrapper<BookingCity>().eq("name_cn", address)).get(0).getNameEn();
        }
        return enAddress;
    }

}
