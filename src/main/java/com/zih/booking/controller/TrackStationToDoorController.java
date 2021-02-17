package com.zih.booking.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.dao.BusiShippingorderMapper;
import com.zih.booking.dao.TrackIconMapper;
import com.zih.booking.dao.TrackTwoLevelMapper;
import com.zih.booking.model.*;
import com.zih.booking.request.TrainListRequest;
import com.zih.booking.response.OneNodeResponse;
import com.zih.booking.response.TrainDetailResponse;
import com.zih.booking.response.TrainListResponse;
import com.zih.booking.response.TwoNodeResponse;
import com.zih.booking.service.*;
import com.zih.booking.system.enums.LanguageEnum;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.utils.DateUtils;
import com.zih.booking.utils.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 运踪_去回程站到门/门到站 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-01-15
 */
@RestController
@RequestMapping("/trackStationToDoor")
@Api(tags = "运踪节点信息")
public class TrackStationToDoorController {

    @Autowired
    private BusiShippingorderMapper busiShippingorderMapper;
    @Autowired
    private BusiShippingorderService busiShippingorderService;
    @Autowired
    private TrackStationToDoorService trackStationToDoorService;
    @Autowired
    private BusiSiteService busiSiteService;
    @Autowired
    private TrackIconMapper iconMapper;
    @Autowired
    private TrackTwoLevelMapper trackTwoLevelMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TrackOneLevelService oneLevelService;
    @Autowired
    private TrackGoodsStatusService goodsStatusService;
    @Autowired
    private TrackTrainService trackTrainService;
    @Autowired
    private TrackAbnormalBoxService trackAbnormalBoxService;
    @Autowired
    BusiZyInfoService busiZyInfoService;

    private String getEnAddress(String address) {
        String enAddress = address;
        if (busiSiteService.selectList(new EntityWrapper<BusiSite>().eq("name_cn", address)).size() != 0) {
            enAddress = busiSiteService.selectList(new EntityWrapper<BusiSite>().eq("name_cn", address)).get(0).getNameEn();
        }
        return enAddress;
    }

    @ApiOperation("运综分页列表信息")
    @GetMapping("/trainList")
    public ApiResultI18n getTrainList(TrainListRequest trainListRequest) {
        List<String> clients=tokenService.getClientIds(ServletUtils.getRequest());
        trainListRequest.setClientIds(clients);
        Page<TrainListResponse> page = new Page<>(trainListRequest.getPage(), trainListRequest.getLimit());
        page.setRecords(busiShippingorderService.selectTrainList(page, trainListRequest));
        String [] state={"1","2"};
        for (TrainListResponse response:page.getRecords()) {
            List<TrackOneLevel> list=oneLevelService.selectList(new EntityWrapper<TrackOneLevel>().eq("order_id",response.getOrderId()).in("state",state).eq("del_flag",0).orderBy("sort",false) );
            if(list.size()!=0){
              response.setTrainState(list.get(0).getNameZh());
              response.setTrainStateEn(list.get(0).getNameEn());
            }
            if("班列运踪".equals(response.getTrainState())&& StringUtils.isNotEmpty(response.getXianghao())){
                TrackGoodsStatus checkTgs=goodsStatusService.selectOne(new EntityWrapper<TrackGoodsStatus>().eq("del_flag",0).eq("order_id",response.getOrderId()).eq("box_num",response.getXianghao()));
                if(1==checkTgs.getIsNormal()){
                    response.setTrainState("异常箱运踪");
                    response.setTrainStateEn("AbnormalBox Tracking");
                }
            }
           /* List<TrackGoodsStatus>goodsStatuses=goodsStatusService.selectList(new EntityWrapper<TrackGoodsStatus>().eq("order_id",response.getOrderId()));
            if (goodsStatuses.size()!=0){//箱号
                List<String> boxNums=new ArrayList<>();
                for (TrackGoodsStatus goodsStatus: goodsStatuses ) {
                    boxNums.add(goodsStatus.getBoxNum());
                }
                String string1 = String.join(",",boxNums);
                response.setXianghao(string1);
            }*/
        }
        return ApiResultI18n.success(page, trainListRequest.getLanguage());
    }


    @ApiOperation("运综详情")
    @GetMapping("/getTrainDetail")
    public ApiResultI18n getTrainDetail(String orderId, String language) {
        TrainDetailResponse trainDetailResponse = busiShippingorderMapper.selectTrainDetail(orderId);
        if( trackTrainService.selectList(new EntityWrapper<TrackTrain>().eq("class_id",trainDetailResponse.getClassId())).size()!=0){
            TrackTrain t=trackTrainService.selectOne(new EntityWrapper<TrackTrain>().eq("class_id",trainDetailResponse.getClassId()));
            if(null!=t.getExceptEarlyTime()){
                trainDetailResponse.setExpectTime(t.getExceptEarlyTime()+"/"+t.getExceptLastTime());
            }
        }
        if (language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
            trainDetailResponse.setOrderUploadsite(getEnAddress(trainDetailResponse.getOrderUploadsite()));
            trainDetailResponse.setOrderUnloadsite(getEnAddress(trainDetailResponse.getOrderUnloadsite()));
        }
        return ApiResultI18n.success(trainDetailResponse, language);
    }

   /* @GetMapping("/getOneNode")
    @ApiOperation("该订单全部一级时间节点")
    public ApiResultI18n getOneNode(String orderId, String language) {
        List<OneNodeResponse> list = iconMapper.selectZhOneNode(orderId);
        if (language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
            list = iconMapper.selectEnOneNode(orderId);
        }
        for(int i =0;i<list.size();i++){
            if(!"".equals(list.get(i).getTime())&&null!=list.get(i).getTime()){
                list.get(i).setTime(list.get(i).getTime().substring(0,10));
            }
        }
        return ApiResultI18n.success(list, language);
    }*/

    @GetMapping("/getOneNode")
    @ApiOperation("该订单全部一级时间节点")
    public ApiResultI18n getOneNode(String orderId, String boxNum, String language) {
        Map<String, Object> map = new HashMap<>();
        List<OneNodeResponse> list = iconMapper.selectZhOneNode(orderId);
        if (language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
            list = iconMapper.selectEnOneNode(orderId);
        }
        map.put("abnormal",0);
        if(StringUtils.isNotEmpty(boxNum)&&!"null".equals(boxNum)){
            TrackGoodsStatus checkTgs=goodsStatusService.selectOne(new EntityWrapper<TrackGoodsStatus>().eq("order_id",orderId).eq("box_num",boxNum).eq("del_flag",0));
            if(1==checkTgs.getIsNormal()&&null!=list.get(3).getState()&&1==list.get(3).getState()){//是否正常箱0是1否
                list.get(3).setState(2);//班列运踪异常
                list.get(3).setNodeName("异常箱运踪");
                if(language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())){
                    list.get(3).setNodeName("AbnormalBox Tracking");
                }
                //添加异常箱运踪信息
                List<TrackAbnormalBox> abnormalList = trackAbnormalBoxService.selectList(new EntityWrapper<TrackAbnormalBox>().eq("order_id",orderId).eq("box_num",boxNum).eq("del_flag",0).orderBy("update_time", false));
                map.put("abnormalBox",abnormalList);
                map.put("abnormal",1);
            }
        }

        for(int i =0;i<list.size();i++){
            if(!"".equals(list.get(i).getTime())&&null!=list.get(i).getTime()){
            list.get(i).setTime(DateUtils.parseStr(DateUtils.parseDate("yyyy-MM-dd",list.get(i).getTime()),"yyyy-MM-dd"));
            }
        }

        BusiShippingorder shippingOrder=busiShippingorderService.selectOne(new EntityWrapper<BusiShippingorder>().eq("order_id",orderId));
        //中亚去程只显示到班列运踪
        if("0".equals(shippingOrder.getClassEastandwest())&&"2".equals(shippingOrder.getLineTypeid())){
            list=list.subList(0,4);
      /*      BusiZyInfo zyInfo=busiZyInfoService.selectOne(new EntityWrapper<BusiZyInfo>().eq("order_id",orderId).eq("del_flag","0").eq("box_num",boxNum));
            list.add(list.get(3));
            list.get(3).setNodeName("发运计划");
            if (language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
                list.get(3).setNodeName("Shipping plan");
            }
            list.get(3).setSort(4);
            list.get(3).setState(0);
            list.get(3).setTime(zyInfo.getClasszyNo());
            list.get(3).setIcon(list.get(2).getIcon());*/
        }else if("1".equals(shippingOrder.getClassEastandwest())&&"2".equals(shippingOrder.getLineTypeid())){
            list.remove(1);
            list.remove(1);
        }

        map.put("oneNode",list);
        return ApiResultI18n.success(map, language);
    }


    @GetMapping("/getTwoNode")
    @ApiOperation("2级时间节点")
    public ApiResultI18n getTwoNode(String id, String language, String number) {
        List<TwoNodeResponse> list = trackTwoLevelMapper.selectTwoNode(id);
        if(list!=null&&list.size()>0){
            String orderId=list.get(0).getOrderId();
            BusiShippingorder shippingOrder=busiShippingorderService.selectOne(new EntityWrapper<BusiShippingorder>().eq("order_id",orderId));
            if("0".equals(shippingOrder.getClassEastandwest())&&"2".equals(shippingOrder.getLineTypeid())){
                BusiZyInfo zyInfo=busiZyInfoService.selectOne(new EntityWrapper<BusiZyInfo>().eq("order_id",orderId).eq("del_flag","0").eq("xianghao",number));
                for(int i=0;i<list.size();i++){
                    if("已进站".equals(list.get(i).getNameZh())){
                        list.get(i).setRemark(StringUtils.isNotEmpty(list.get(i).getRemark())?list.get(i).getRemark():""+"计划"+zyInfo.getClasszyNo());
                        list.get(i).setRemarkEn(StringUtils.isNotEmpty(list.get(i).getRemarkEn())?list.get(i).getRemarkEn():""+"plan"+zyInfo.getClasszyNo());
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

    @GetMapping("/getTimeNode")
    @ApiIgnore("该订单全部二级时间节点")
    public ApiResultI18n getTimeNode(String orderId, String language) {
        TrackStationToDoor trackStationToDoor = trackStationToDoorService.selectOne(new EntityWrapper<TrackStationToDoor>()
                .eq("order_id", orderId));
        return ApiResultI18n.success(trackStationToDoor, language);
    }

}

