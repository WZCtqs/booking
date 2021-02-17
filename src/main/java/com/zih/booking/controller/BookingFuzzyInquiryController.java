package com.zih.booking.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.dao.BusiSiteMapper;
import com.zih.booking.model.*;
import com.zih.booking.request.BookingFuzzyInquiryRequest;
import com.zih.booking.request.InquiryListRequest;
import com.zih.booking.response.CityResponse;
import com.zih.booking.service.*;
import com.zih.booking.system.config.Consts;
import com.zih.booking.system.enums.LanguageEnum;
import com.zih.booking.system.enums.TransportModeEnum;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.utils.ServletUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
@Slf4j
@Api(tags = "订舱询盘相关接口")
@RestController
@RequestMapping("/bfi")
public class BookingFuzzyInquiryController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    TokenService tokenService;
    @Autowired
    private BookingCityService bookingCityService;
    @Autowired
    private BookingFuzzyInquiryService bookingFuzzyInquiryService;
    @Autowired
    private BookingFuzzyInquiryGoodsDetailsService bookingFuzzyInquiryGoodsDetailsService;
    @Autowired
    private BookingFuzzyInquiryResultService bookingFuzzyInquiryResultService;
    @Autowired
    private ZgRailDivisionService zgRailDivisionService;
    @Autowired
    private ShRailDivisionService shRailDivisionService;
    @Autowired
    private BusiSiteService busiSiteService;
    @Autowired
    private BusiSiteMapper busiSiteMapper;
    @ApiOperation(value = "type 1上货站  2下货站  goodsType  0整柜 1拼箱  eastWest 去回程,0为去程 1为回程")
    @GetMapping("site")
    public ApiResultI18n getSite(@RequestParam String lineTypeid, @RequestParam String language, @RequestParam Integer type, @RequestParam Integer goodsType,
                                 @RequestParam Integer eastWest) {
        List<CityResponse> list = new ArrayList<>();
        if (1 == type && 0 == goodsType && 1 == eastWest &&
                ("2".equals(lineTypeid) || "4".equals(lineTypeid))) {
            if ("2".equals(lineTypeid) && "zh".equals(language)) {
                CityResponse cityResponse=new CityResponse();
                cityResponse.setCityCode("998");
                cityResponse.setCityName("塔什干");
                list.add(cityResponse);
                CityResponse cityResponse2=new CityResponse();
                cityResponse2.setCityCode("998_02");
                cityResponse2.setCityName("谢尔盖利");
                list.add(cityResponse2);
            } else if("2".equals(lineTypeid) && "en".equals(language)) {
                CityResponse cityResponse=new CityResponse();
                cityResponse.setCityCode("998");
                cityResponse.setCityName("Tashkent");
                list.add(cityResponse);
                CityResponse cityResponse2=new CityResponse();
                cityResponse2.setCityCode("998_02");
                cityResponse2.setCityName("Sergeli");
                list.add(cityResponse2);
            }
            if ("4".equals(lineTypeid) && "zh".equals(language)) {
                CityResponse cityResponse1=new CityResponse();
                cityResponse1.setCityCode("375_220071");
                cityResponse1.setCityName("明斯克");
                CityResponse cityResponse2=new CityResponse();
                cityResponse2.setCityCode("007-2");
                cityResponse2.setCityName("丘诺亚尔");
                CityResponse cityResponse3=new CityResponse();
                cityResponse3.setCityCode("007-3");
                cityResponse3.setCityName("克拉斯诺亚尔斯克");
                CityResponse cityResponse4=new CityResponse();
                cityResponse4.setCityCode("007-6");
                cityResponse4.setCityName("伊尔库茨克");
                CityResponse cityResponse5=new CityResponse();
                cityResponse5.setCityCode("007-23");
                cityResponse5.setCityName("后贝加尔斯克");
                CityResponse cityResponse6=new CityResponse();
                cityResponse6.setCityCode("230506");
                cityResponse6.setCityName("库帕夫纳");
                CityResponse cityResponse7=new CityResponse();
                cityResponse7.setCityCode("86_012600");
                cityResponse7.setCityName("二连浩特");
                CityResponse cityResponse8=new CityResponse();
                cityResponse8.setCityCode("86_021400");
                cityResponse8.setCityName("满洲里");
                CityResponse cityResponse9=new CityResponse();
                cityResponse9.setCityCode("249");
                cityResponse9.setCityName("绥芬河");
                list.add(cityResponse1);
                list.add(cityResponse2);
                list.add(cityResponse3);
                list.add(cityResponse4);
                list.add(cityResponse5);
                list.add(cityResponse6);
                list.add(cityResponse7);
                list.add(cityResponse8);
                list.add(cityResponse9);
            } else if("4".equals(lineTypeid) && "en".equals(language)) {
                CityResponse cityResponse1=new CityResponse();
                cityResponse1.setCityCode("375_220071");
                cityResponse1.setCityName("Minsk");
                CityResponse cityResponse2=new CityResponse();
                cityResponse2.setCityCode("007-2");
                cityResponse2.setCityName("Chunoyal");
                CityResponse cityResponse3=new CityResponse();
                cityResponse3.setCityCode("007-3");
                cityResponse3.setCityName("Krasnoyarsk");
                CityResponse cityResponse4=new CityResponse();
                cityResponse4.setCityCode("007-6");
                cityResponse4.setCityName("Irkutsk");
                CityResponse cityResponse5=new CityResponse();
                cityResponse5.setCityCode("007-23");
                cityResponse5.setCityName("Zabaikalsk");
                CityResponse cityResponse6=new CityResponse();
                cityResponse6.setCityCode("230506");
                cityResponse6.setCityName("Kupavna");
                CityResponse cityResponse7=new CityResponse();
                cityResponse7.setCityCode("86_012600");
                cityResponse7.setCityName("Erenhot");
                CityResponse cityResponse8=new CityResponse();
                cityResponse8.setCityCode("86_021400");
                cityResponse8.setCityName("Manzhouli");
                CityResponse cityResponse9=new CityResponse();
                cityResponse9.setCityCode("249");
                cityResponse9.setCityName("Suifenhe");
                list.add(cityResponse1);
                list.add(cityResponse2);
                list.add(cityResponse3);
                list.add(cityResponse4);
                list.add(cityResponse5);
                list.add(cityResponse6);
                list.add(cityResponse7);
                list.add(cityResponse8);
                list.add(cityResponse9);
            }
        } else {
            if (type == 1 && goodsType == 0) {
                list =busiSiteMapper.selectZXSList(lineTypeid,eastWest,language);
            }if (type == 1 && goodsType == 1) {
                list =busiSiteMapper.selectPXSList(lineTypeid,eastWest,language);
            }if (type == 2 && goodsType == 0) {
                list =busiSiteMapper.selectZXXList(lineTypeid,eastWest,language);
            }if (type == 2 && goodsType == 1) {
                list =busiSiteMapper.selectPXXList(lineTypeid,eastWest,language);
            }
            if ((eastWest == 0 && type == 1) || (eastWest == 1 && type == 2)) {//去程上货站 回城下货站 只有郑州
                CityResponse cityResponse=new CityResponse();
                cityResponse.setCityCode("86_450000");
                cityResponse.setCityName("郑州");
                if ("en".equalsIgnoreCase(language)){
                    cityResponse.setCityName("Zhengzhou");
                }
                list.clear();
                list.add(cityResponse);
            }
        }

        //按照经常使用的顺序排列
        List<String> regulationOrder = Arrays.asList("汉堡","慕尼黑","列日","华沙","马拉","米兰","布列斯特","布拉格","布达佩斯","巴黎","里昂","莫斯科","明斯克","圣彼得堡","杜伊斯堡","赫尔辛堡");
        list.sort(new Comparator<CityResponse>() {
            @Override
            public int compare(CityResponse o1, CityResponse o2) {
                String o1_province = o1.getCityName();
                String o2_province = o2.getCityName();
                int o1_index = regulationOrder.indexOf(o1_province);
                int o2_index = regulationOrder.indexOf(o2_province);
                if (o1_index == -1) {
                    return 1;
                }
                if (o2_index == -1) {
                    return -1;
                }
                return o1_index - o2_index;
            }
        });
        return ApiResultI18n.success(list, language);
    }

    @ApiOperation(value = "type 1发货地 2收货地 ;eastWest 去回程 0为去程 1为回程 ;intra 1-国内 0-国外")
    @GetMapping("city")
    public ApiResultI18n getCity(@RequestParam String lineTypeid, String language, @RequestParam Integer type, @RequestParam Integer eastWest) {
        List<BusiSite> bookingCities = new ArrayList<>();
        String intra = "";
        if ((type == 1 && eastWest == 0) || (type == 2 && eastWest == 1)) {
            intra = "1";
        }
        if ((type == 1 && eastWest == 1) || (type == 2 && eastWest == 0)) {
            intra = "0";
        }
        bookingCities = busiSiteService.selectList(new EntityWrapper<BusiSite>().like("line_typeid", lineTypeid).eq("intra", intra));
        List<CityResponse> list = new ArrayList<>();
        bookingCities.forEach(bookingCity -> {
            CityResponse cityResponse = new CityResponse();
            cityResponse.setCityCode(bookingCity.getCode());
            if (language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
                cityResponse.setCityName(bookingCity.getNameEn());
            } else {
                cityResponse.setCityName(bookingCity.getNameCn());
            }
            list.add(cityResponse);
        });
        return ApiResultI18n.success(list, language);
    }


    @ApiOperation(value = "询盘接口")
    @PostMapping("inquiry")
    public ApiResultI18n inquiry(@RequestBody BookingFuzzyInquiryRequest bookingFuzzyInquiryRequest) throws Exception {

        BookingFuzzyInquiry bookingFuzzyInquiry = new BookingFuzzyInquiry();
        BeanUtils.copyProperties(bookingFuzzyInquiryRequest, bookingFuzzyInquiry);
        // 新增一条询盘记录
        bookingFuzzyInquiryService.insert(bookingFuzzyInquiry);
        // 拼箱货物详情存储
        if (bookingFuzzyInquiry.getGoodsType().equals("1")) {
            List<BookingFuzzyInquiryGoodsDetails> goodsDetails = bookingFuzzyInquiryRequest.getGoodsDetails();
            goodsDetails.forEach(goodsDetail -> {
                goodsDetail.setInquiryId(bookingFuzzyInquiry.getId());
            });
            bookingFuzzyInquiryGoodsDetailsService.insertBatch(goodsDetails);
        }
        List<BookingFuzzyInquiryResult> resultList = new ArrayList<>();
        Map map = org.apache.commons.beanutils.BeanUtils.describe(bookingFuzzyInquiryRequest);
        // 请求箱行亚欧获取国内公路报价（暂未开发）
        ResponseEntity<BookingFuzzyInquiryResult> bookingFuzzyInquiryResult1 =
                getForEntity(Consts.BFIR_GET_XXYO, map);
        if (bookingFuzzyInquiryResult1.getStatusCodeValue() == 200) {
            resultList.add(bookingFuzzyInquiryResult1.getBody());
        }
        // 请求集疏获取国外公路报价（暂不需要）
//        ResponseEntity<BookingFuzzyInquiryResult> bookingFuzzyInquiryResult2 =
//                getForEntity(Consts.BFIR_GET_JS,map);
//        if (bookingFuzzyInquiryResult2.getStatusCodeValue() != 200) {
//            return ApiResultI18n.failure(ResultStatusCode.QUOTE_ERROR,bookingFuzzyInquiryRequest.getLanguage());
//        }
//        resultList.add(bookingFuzzyInquiryResult2.getBody());
        // 铁路段报价
        List<BookingFuzzyInquiryResult> list = getRailResult(bookingFuzzyInquiryRequest);
        if (CollectionUtils.isNotEmpty(list)) {
            resultList.addAll(list);
        }
        // 批量插入询盘结果
        resultList.forEach(result -> {
            result.setInquiryId(bookingFuzzyInquiry.getId());
        });
        bookingFuzzyInquiryResultService.insertBatch(resultList);
        return ApiResultI18n.success(resultList, bookingFuzzyInquiryRequest.getLanguage());
    }

    @ApiOperation(value = "获取询盘记录")
    @GetMapping("getList")
    public ApiResultI18n getList(InquiryListRequest inquiryListRequest) {
        List<String> clients = tokenService.getClientIds(ServletUtils.getRequest());
        Page<BookingFuzzyInquiry> bookingFuzzyInquiryPage =
                bookingFuzzyInquiryService.selectPage(
                        new Page<>(inquiryListRequest.getPage(), inquiryListRequest.getLimit()),
                        new EntityWrapper<BookingFuzzyInquiry>()
                                .addFilterIfNeed(StringUtils.isNotEmpty(inquiryListRequest.getInquiryDate()),
                                        "DATE_FORMAT(inquiry_time,'%Y-%m-%d') = " + inquiryListRequest.getInquiryDate())
                                .in("client_id", clients)
                                .orderBy("inquiry_time", false)
                );
        return ApiResultI18n.success(bookingFuzzyInquiryPage, inquiryListRequest.getLanguage());
    }

    @ApiOperation(value = "获取询盘结果")
    @GetMapping("getResult")
    public ApiResultI18n getResult(Integer inquiryId, String language) {
        List<BookingFuzzyInquiryResult> list =
                bookingFuzzyInquiryResultService.selectList(
                        new EntityWrapper<BookingFuzzyInquiryResult>()
                                .eq("inquiry_id", inquiryId));
        return ApiResultI18n.success(list, language);
    }

    @ApiOperation(value = "获取询盘拼箱货物详情")
    @GetMapping("goodsDetails")
    public ApiResultI18n getGoodsDetails(Integer id, String language) {

        List<BookingFuzzyInquiryGoodsDetails> list =
                bookingFuzzyInquiryGoodsDetailsService.selectList(
                        new EntityWrapper<BookingFuzzyInquiryGoodsDetails>().eq("inquiry_id", id));
        return ApiResultI18n.success(list, language);
    }


    /**
     * 获取铁路报价信息
     *
     * @param bookingFuzzyInquiryRequest
     * @return
     */
    private List<BookingFuzzyInquiryResult> getRailResult(BookingFuzzyInquiryRequest bookingFuzzyInquiryRequest) {

        List<BookingFuzzyInquiryResult> results = new ArrayList<>();

        // 查看上货站信息
        BookingCity bookingCity1 = bookingCityService.selectOne(
                new EntityWrapper<BookingCity>()
                        .eq("code", bookingFuzzyInquiryRequest.getShipmentCityCode()));
        // 查看下货站信息
        BookingCity bookingCity2 = bookingCityService.selectOne(
                new EntityWrapper<BookingCity>()
                        .eq("code", bookingFuzzyInquiryRequest.getReceiptCityCode()));
        // 去程
        if ("86".equals(bookingCity1.getCountryCode())) {
            // 整柜
            if (bookingFuzzyInquiryRequest.getGoodsType().equals("0")) {
                // 调用接口获取整柜费用
                List<ZgRailDivision> zgRailDivisions =
                        zgRailDivisionService.selectList(
                                new EntityWrapper<ZgRailDivision>()
                                        .eq("line_type", bookingCity2.getLineType())
                                        .eq("order_upload_site", "郑州")
                                        .eq("container_type_value", bookingFuzzyInquiryRequest.getContainerType())
                        );
                BigDecimal count = new BigDecimal(bookingFuzzyInquiryRequest.getContainerNum());
                if (CollectionUtils.isNotEmpty(zgRailDivisions)) {
                    zgRailDivisions.forEach(zgRailDivision -> {
                        BookingFuzzyInquiryResult bookingFuzzyInquiryResult = new BookingFuzzyInquiryResult();
                        bookingFuzzyInquiryResult.setInterval("郑州-" + zgRailDivision.getOrderUnloadSite());
                        bookingFuzzyInquiryResult.setUploadSite(zgRailDivision.getOrderUnloadSite());
                        bookingFuzzyInquiryResult.setTransportMode(TransportModeEnum.RAILWAY.getTransportMode());
                        bookingFuzzyInquiryResult.setAging(zgRailDivision.getTimeLimit());
                        bookingFuzzyInquiryResult.setFees(
                                String.valueOf(new BigDecimal(zgRailDivision.getRailCost()).multiply(count))
                        );
                        bookingFuzzyInquiryResult.setCurrencyType(zgRailDivision.getUnit());
                        results.add(bookingFuzzyInquiryResult);
                    });
                }

            } else {
                // 调用接口获取拼箱费用
                List<ShRailDivision> shRailDivisions =
                        shRailDivisionService.selectList(
                                new EntityWrapper<ShRailDivision>()
                                        .eq("line_type", bookingCity2.getLineType())
                                        .eq("order_upload_site", "郑州")
                                        .eq("container_type_value", bookingFuzzyInquiryRequest.getContainerType())
                        );
                BigDecimal volume = new BigDecimal(bookingFuzzyInquiryRequest.getTotalVolume());
                if (CollectionUtils.isNotEmpty(shRailDivisions)) {
                    shRailDivisions.forEach(shRailDivision -> {
                        BookingFuzzyInquiryResult bookingFuzzyInquiryResult = new BookingFuzzyInquiryResult();
                        bookingFuzzyInquiryResult.setInterval("郑州-" + shRailDivision.getOrderUnloadSite());
                        bookingFuzzyInquiryResult.setUploadSite(shRailDivision.getOrderUnloadSite());
                        bookingFuzzyInquiryResult.setTransportMode(TransportModeEnum.RAILWAY.getTransportMode());
                        bookingFuzzyInquiryResult.setAging(shRailDivision.getTimeLimit());
                        // 计算散货价格(体积)
                        // 起运站收费
                        BigDecimal a = shRailDivision.getOrderUploadSiteCost().multiply(volume);
                        // 运费单价
                        BigDecimal b = new BigDecimal(shRailDivision.getLclFreight()).multiply(volume);
                        // 拆箱费、基本仓储费
                        BigDecimal c = shRailDivision.getOrderUnloadSiteBacost().multiply(volume);
                        // 每票固定收费
                        BigDecimal min = new BigDecimal(shRailDivision.getMinVolume());
                        BigDecimal max = new BigDecimal(shRailDivision.getMaxVolume());
                        BigDecimal d = new BigDecimal(0);
                        if (volume.compareTo(min) == -1 || volume.compareTo(min) == 0) {
                            d = volume.multiply(shRailDivision.getMinVolumeCost());
                        } else if (volume.compareTo(max) == 1 || volume.compareTo(max) == 0) {
                            d = volume.multiply(shRailDivision.getMaxVolumeCost());
                        } else {
                            d = volume.multiply(shRailDivision.getMiddleVolumeCost());
                        }
                        bookingFuzzyInquiryResult.setFees(String.valueOf(a.add(b).add(c).add(d)));
                        bookingFuzzyInquiryResult.setCurrencyType(shRailDivision.getUnit());
                        results.add(bookingFuzzyInquiryResult);
                    });
                }

            }

        } else {
            // 整柜
            if (bookingFuzzyInquiryRequest.getGoodsType().equals("0")) {
                // 调用接口获取整柜费用
                List<ZgRailDivision> zgRailDivisions =
                        zgRailDivisionService.selectList(
                                new EntityWrapper<ZgRailDivision>()
                                        .eq("line_type", bookingCity2.getLineType())
                                        .eq("order_unload_site", "郑州")
                                        .eq("container_type_value", bookingFuzzyInquiryRequest.getContainerType())
                        );
                BigDecimal count = new BigDecimal(bookingFuzzyInquiryRequest.getContainerNum());
                if (CollectionUtils.isNotEmpty(zgRailDivisions)) {
                    zgRailDivisions.forEach(zgRailDivision -> {
                        BookingFuzzyInquiryResult bookingFuzzyInquiryResult = new BookingFuzzyInquiryResult();
                        bookingFuzzyInquiryResult.setInterval(zgRailDivision.getOrderUnloadSite() + "-郑州");
                        bookingFuzzyInquiryResult.setUploadSite(zgRailDivision.getOrderUploadSite());
                        bookingFuzzyInquiryResult.setTransportMode(TransportModeEnum.RAILWAY.getTransportMode());
                        bookingFuzzyInquiryResult.setAging(zgRailDivision.getTimeLimit());
                        bookingFuzzyInquiryResult.setFees(
                                String.valueOf(new BigDecimal(zgRailDivision.getRailCost()).multiply(count))
                        );
                        bookingFuzzyInquiryResult.setCurrencyType(zgRailDivision.getUnit());
                        results.add(bookingFuzzyInquiryResult);
                    });
                }
            } else {
                // 调用接口获取拼箱费用
                List<ShRailDivision> shRailDivisions =
                        shRailDivisionService.selectList(
                                new EntityWrapper<ShRailDivision>()
                                        .eq("line_type", bookingCity2.getLineType())
                                        .eq("order_unload_site", "郑州")
                                        .eq("container_type_value", bookingFuzzyInquiryRequest.getContainerType())
                        );
                BigDecimal volume = new BigDecimal(bookingFuzzyInquiryRequest.getTotalVolume());
                if (CollectionUtils.isNotEmpty(shRailDivisions)) {
                    shRailDivisions.forEach(shRailDivision -> {
                        BookingFuzzyInquiryResult bookingFuzzyInquiryResult = new BookingFuzzyInquiryResult();
                        bookingFuzzyInquiryResult.setInterval(shRailDivision.getOrderUnloadSite() + "-郑州");
                        bookingFuzzyInquiryResult.setUploadSite(shRailDivision.getOrderUploadSite());
                        bookingFuzzyInquiryResult.setTransportMode(TransportModeEnum.RAILWAY.getTransportMode());
                        bookingFuzzyInquiryResult.setAging(shRailDivision.getTimeLimit());
                        // 计算散货价格(体积)
                        // 起运站收费
                        BigDecimal a = shRailDivision.getOrderUploadSiteCost().multiply(volume);
                        // 运费单价
                        BigDecimal b = new BigDecimal(shRailDivision.getLclFreight()).multiply(volume);
                        // 拆箱费、基本仓储费
                        BigDecimal c = shRailDivision.getOrderUnloadSiteBacost().multiply(volume);
                        // 每票固定收费
                        BigDecimal min = new BigDecimal(shRailDivision.getMinVolume());
                        BigDecimal max = new BigDecimal(shRailDivision.getMaxVolume());
                        BigDecimal d = new BigDecimal(0);
                        if (volume.compareTo(min) == -1 || volume.compareTo(min) == 0) {
                            d = volume.multiply(shRailDivision.getMinVolumeCost());
                        } else if (volume.compareTo(max) == 1 || volume.compareTo(max) == 0) {
                            d = volume.multiply(shRailDivision.getMaxVolumeCost());
                        } else {
                            d = volume.multiply(shRailDivision.getMiddleVolumeCost());
                        }
                        bookingFuzzyInquiryResult.setFees(String.valueOf(a.add(b).add(c).add(d)));
                        bookingFuzzyInquiryResult.setCurrencyType(shRailDivision.getUnit());
                        results.add(bookingFuzzyInquiryResult);
                    });
                }
            }
        }
        return results;
    }

    /**
     * 封装的get请求，暂时只支持map传参，并且value只支持基本类型和String
     *
     * @param url
     * @param object
     * @return
     */
    private ResponseEntity<BookingFuzzyInquiryResult> getForEntity(String url, Object object) {
        StringBuffer stringBuffer = new StringBuffer(url);
        if (object instanceof Map) {
            Iterator iterator = ((Map) object).entrySet().iterator();
            if (iterator.hasNext()) {
                stringBuffer.append("?");
                Object element;
                while (iterator.hasNext()) {
                    element = iterator.next();
                    Map.Entry<String, Object> entry = (Map.Entry) element;
                    //过滤value为null，value为null时进行拼接字符串会变成 "null"字符串
                    if (entry.getValue() != null) {
                        stringBuffer.append(element).append("&");
                    }
                    url = stringBuffer.substring(0, stringBuffer.length() - 1);
                }
            }
        } else {
            throw new RuntimeException("url请求:" + url + "请求参数有误不是map类型");
        }
        log.info("url请求:" + url);
        return restTemplate.getForEntity(url, BookingFuzzyInquiryResult.class);
    }


}
