package com.zih.booking.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.dao.BookingInquiryMapper;
import com.zih.booking.dao.BookingInquiryResultMapper;
import com.zih.booking.dao.BusiClassesMapper;
import com.zih.booking.dao.BusiShippingorderMapper;
import com.zih.booking.model.*;
import com.zih.booking.request.BookingInquiryRequest;
import com.zih.booking.request.BookingSpaceRequest;
import com.zih.booking.request.InquiryListRequest;
import com.zih.booking.response.BookingInquiryResponse;
import com.zih.booking.response.InquiryDetailResponse;
import com.zih.booking.service.*;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.utils.DateUtils;
import com.zih.booking.utils.PdfUtils;
import com.zih.booking.utils.ServletUtils;
import com.zih.booking.vo.ContainerVo;
import com.zih.booking.vo.InquiryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.ParseException;
import java.util.*;

@Slf4j
@Api(tags = "订舱询价相关接口")
@RestController
@RequestMapping("/bi")
public class BookingInquiryController {

    @Value("${inquiry.del}")
    private String del;

    @Autowired
    private BookingInquiryService bookingInquiryService;
    @Autowired
    private BookingInquiryGoodsDetailsService bookingInquiryGoodsDetailsService;
    @Autowired
    private BookingInquiryResultService bookingInquiryResultService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BusiShippingorderService busiShippingorderService;
    @Autowired
    private BusiClassesMapper busiClassesMapper;
    @Autowired
    private BusiSiteService busiSiteService;
    @Autowired
    private BaseGoodsnoteService baseGoodsnoteService;
    @Autowired
    private BookingInquiryMapper bookingInquiryMapper;
    @Autowired
    private BookingInquiryResultMapper resultMapper;
    @Resource
    private ResourceLoader resourceLoader;
    @Autowired
    BusiShippingorderDangerousGoodsService dangerousGoodsService;
    @Autowired
    BusiClassesService busiClassesService;
    @Autowired
    private  BusiSiteService siteService;
    @Autowired
    private BusiShippingorderMapper busiShippingorderMapper;


    @ApiOperation(value = "询价接口")
    @PostMapping("inquiry")
    public ApiResultI18n inquiry(@RequestBody BookingInquiryRequest bookingInquiryRequest) {

        BookingInquiry bookingInquiry = new BookingInquiry();
        BeanUtils.copyProperties(bookingInquiryRequest, bookingInquiry);
        // 新增一条询价记录
        bookingInquiryService.insert(bookingInquiry);
        // 拼箱货物详情存储
        if (bookingInquiry.getGoodsType().equals("1")) {
            List<BookingInquiryGoodsDetails> goodsDetails = bookingInquiryRequest.getGoodsDetails();
            goodsDetails.forEach(goodsDetail -> {
                goodsDetail.setInquiryId(bookingInquiry.getId());
            });
            bookingInquiryGoodsDetailsService.insertBatch(goodsDetails);
        }
        return ApiResultI18n.success(bookingInquiryRequest.getLanguage());
    }

    @ApiOperation(value = "获取询价记录")
    @GetMapping("getList")
    public ApiResultI18n getList(InquiryListRequest inquiryListRequest) throws Exception {

        List<String> clients = tokenService.getClientIds(ServletUtils.getRequest());
        inquiryListRequest.setClientIds(clients);
        Page<BookingInquiryResponse> bookingInquiryPage =
                new Page<>(inquiryListRequest.getPage(), inquiryListRequest.getLimit());
        bookingInquiryPage.setRecords(bookingInquiryMapper.selectList(bookingInquiryPage, inquiryListRequest));
        // 添加货物品名
        bookingInquiryPage.getRecords().forEach(bookingInquiry -> {
            if ("1".equals(bookingInquiry.getGoodsType()) ||
                ("0".equals(bookingInquiry.getGoodsType()) &&
                    (
                        "1".equals(bookingInquiry.getDeliveryType()) ||
                        "0".equals(bookingInquiry.getDeliverySelfType()) ||
                        "1".equals(bookingInquiry.getDistributionType())
                    )
                )
            ) {
                bookingInquiry.setGoods(bookingInquiryGoodsDetailsService.selectList(
                        new EntityWrapper<BookingInquiryGoodsDetails>().eq("inquiry_id", bookingInquiry.getId())));
            }
        });
        return ApiResultI18n.success(bookingInquiryPage, inquiryListRequest.getLanguage());
    }

    @ApiOperation(value = "获取询价拼箱货物详情")
    @GetMapping("goodsDetails")
    public ApiResultI18n getGoodsDetails(Integer id, String language) {

        List<BookingInquiryGoodsDetails> list =
                bookingInquiryGoodsDetailsService.selectList(
                        new EntityWrapper<BookingInquiryGoodsDetails>().eq("inquiry_id", id));
        return ApiResultI18n.success(list, language);
    }

    @ApiOperation(value = "获取询价结果列表")
    @GetMapping("inquiryResult")
    public ApiResultI18n getInquiryResult(Integer id, String language) {

        List<BookingInquiryResult> list =
                bookingInquiryResultService.selectList(
                        new EntityWrapper<BookingInquiryResult>().eq("inquiry_id", id));
        if(language.equalsIgnoreCase("en")){
            for (BookingInquiryResult r : list) {
                if(null!=r.getUploadStation()) {
                    r.setUploadStation(siteService.selectOne(new EntityWrapper<BusiSite>().eq("name_cn", r.getUploadStation())).getNameEn());
                }if(null!=r.getDropStation()){
                    r.setDropStation(siteService.selectOne(new EntityWrapper<BusiSite>().eq("name_cn",r.getDropStation())).getNameEn());
                }
            }
        }
        return ApiResultI18n.success(list, language);
    }

    @ApiOperation(" 询价结果详情 导出pdf  ")
    @GetMapping("/pdfOut")
    public void pdfOut(String id, HttpServletResponse response, String language) throws Exception {
        Map<String, Object> map = resultMapper.getPdf(id);
        String templatePath = "static/询价结果模板.pdf";
        if (!map.containsKey("shipment_place")) {
            map.put("shipment_place", map.get("upload_station"));
        }
        if (!map.containsKey("receipt_place")) {
            map.put("receipt_place", map.get("drop_station"));
        }
        if (map.get("east_or_west").equals("0") && map.get("goods_type").equals("0")) {//铁路运费备注
            map.put("rail_remark", " 报价含一票报关费用（限8个货物品名以内），一票货物品名超过8项，每超出一页加收50元。随车文件每票品名20个以内免费，超过20 个，超过的部分每个加收10元。实际经营单位超过 3 个，或 HS 编码数量大于 13 个的集装箱，收费标准：实际经营单位每超出一个加收 200USD，HS 编码每超出一个加收 20USD");
        }
        if (map.get("east_or_west").equals("0") && map.get("goods_type").equals("1")) {
            map.put("rail_remark", "报价含一票报关费用（限8个货物品名以内），一票货物品名超过8项，每超出一页加收50元。随车文件每票品名20个以内免费，超过20 个，超过的部分每个加收10元。");
        }
        if (map.get("east_or_west").equals("1") && map.get("goods_type").equals("0")) {
            map.put("rail_remark", "郑州清关：基础报关费200元/票（包含8项品名），一票货物品名超过8项，每超出一页加收50元。随车文件每票品名20个以内免费，超过20 个，超过的部分每个加收10元。\n" +
                    "            实际经营单位超过 3 个，或 HS 编码数量大于 13 个的集装箱，收费标准：实际经营单位每超出一个加收 200USD，HS 编码每超出一个加收 20USD。报检查验实报实销。");
        }
        if (map.get("east_or_west").equals("1") && map.get("goods_type").equals("1")) {
            map.put("rail_remark", " 郑州清关：基础报关费200元/票（包含8项品名），一票货物品名超过8项，每超出一页加收50元。随车文件每票品名20个以内免费，超过20 个，超过的部分每个加收10元。");
        }
        if (map.get("delivery_type").equals("1") || map.get("goods_type").equals("1")) {//国外场站备注
            map.put("other1", "备注：国内外场站费用收费标准见附件，最终费用以实际发生的费用为准；");
            map.put("other2", "场站收费标准2020年第二季度价格方案");
        }
        if (map.containsKey("hx_address") && map.containsKey("tx_address")) {//提还箱地
            map.put("hx_address", map.get("tx_address") + "/" + map.get("hx_address"));
        } else if (map.containsKey("tx_address")) {
            map.put("hx_address", map.get("tx_address"));
        }
        if (map.containsKey("is_stack")) {//是否堆叠
            if (map.get("is_stack").toString().equals("0")) {
                map.put("is_stack", "否");
            }
            if (map.get("is_stack").toString().equals("1")) {
                map.put("is_stack", "是");
            }
        }
        map.put("upload_station", map.get("upload_station") + "/" + map.get("drop_station"));
        PdfUtils.pdfout2(resourceLoader, map, templatePath);
        // 下载本地文件
        String fileName = "询价结果.pdf"; // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("C:/pdf/testout1.pdf");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @ApiOperation(value = "获取询价结果详情")
    @GetMapping("detail")
    public ApiResultI18n getInquiryResultDetail(Integer id, String language) {
        BookingInquiryResult result = bookingInquiryResultService.selectById(id);
        BookingInquiry inquiry = bookingInquiryService.selectById(result.getInquiryId());
        InquiryDetailResponse response = new InquiryDetailResponse();
        BeanUtils.copyProperties(result, response);
        BeanUtils.copyProperties(inquiry, response);
        return ApiResultI18n.success(response, language);
    }

    @ApiOperation(value = "班列列表")
    @GetMapping("classes")
    public ApiResultI18n getClasses(String orderId,String inquiryRecordId, String language) {
        InquiryVo inquiry = bookingInquiryMapper.selectInquiryVo(inquiryRecordId);//获取询价信息
        String bookingTimeFlag = inquiry.getBookingTimeFlag(); //0当月1次月
        Date inquiryTime = inquiry.getInquiryTime(); //询价时间
        if(bookingTimeFlag!=null && bookingTimeFlag!="" && inquiryTime!=null){
            inquiry.setNextMonth(DateUtils.getNextMonthByDate(inquiryTime));
        }
        List<BusiClasses> list = new ArrayList<>();
        if (inquiry.getGoodsType().equals("0")) {
            list = busiClassesMapper.selectZXList(inquiry);
        } else {
            list = busiClassesMapper.selectPXList(inquiry);
        }
        if(null!=orderId){ //编辑传orderId 其他情况直接忽略
          String classId=  busiShippingorderService.selectById(orderId).getClassId();
          BusiClasses classes=busiClassesService.selectById(classId);
          if(classes!=null){
              if(!list.contains(classes)){
                  //判断再次订舱时原班列是否在有效期
                  String isAgain = busiShippingorderService.selectById(orderId).getClientOrderRemarks(); //0 再次订舱
                  if("0".equals(isAgain)){
                      Long validdate = null!=inquiry.getValidDate()?(inquiry.getValidDate()).getTime():0; //询价有效期
                      Long classdate = null!=classes.getClassStime()?(classes.getClassStime()).getTime():0; //班列日期
                      if(classdate<=validdate){
                          list.add(classes);
                      }
                  }else{
                      list.add(classes);
                  }
              }
          }
        }
        for (BusiClasses c : list) {
            c.setClassStationofdepartureName(getNameByCode(c.getClassStationofdeparture(), language));
            c.setClassStationofdestinationName(getNameByCode(c.getClassStationofdestination(), language));
        }
        return ApiResultI18n.success(list, language);
    }


    @ApiOperation(value = "在线订舱", notes = "在线订舱  ")
    @PostMapping("/add")
    public ApiResultI18n add(@RequestBody BookingSpaceRequest bookingSpaceRequest) {
        String language = bookingSpaceRequest.getLanguage();
        //订舱时间小于提货时间
        String binningway = bookingSpaceRequest.getShipOrderBinningway();//委托ZIH提货 0是 1否
        if(!StringUtils.isEmpty(binningway)){
            Date orderTime = new Date();
            Date arriveTime = null;
            if("0".equals(binningway)){
                arriveTime = bookingSpaceRequest.getShipOrderUnloadtime();
            }
            if("1".equals(binningway)){
                arriveTime = bookingSpaceRequest.getShipOrderSendtime();
            }
            if(orderTime!=null && arriveTime!=null){
                int comto = orderTime.compareTo(arriveTime);//o小于a返回-1，o大于a返回1，相等返回0
                if(comto>0){
                    return ApiResultI18n.failure(500, "提货时间/自送货时间不能早于订舱时间", bookingSpaceRequest.getLanguage());
                }
            }
        }
        //所选班列是否包含上下货站
        String eastAndWest = bookingSpaceRequest.getClassEastandwest();//0西向 1东向
        if(!StringUtils.isEmpty(bookingSpaceRequest.getClassId()) && (!StringUtils.isEmpty(bookingSpaceRequest.getIsConsolidation())) && (!StringUtils.isEmpty(eastAndWest))){
            String siteStr = busiShippingorderMapper.siteListByClass(bookingSpaceRequest.getClassId(),bookingSpaceRequest.getIsConsolidation()); //班列站点
            if(!StringUtils.isEmpty(siteStr)){
                String siteName = "0".equals(eastAndWest)?bookingSpaceRequest.getOrderUnloadsite():bookingSpaceRequest.getOrderUploadsite(); //所选上下货站
                String siteCode = busiShippingorderService.getNameByCode(siteName);
                if(!StringUtils.isEmpty(siteCode)){
                    String[] siteArr = siteStr.split(",");
                    List<String> siteList = Arrays.asList(siteArr);
                    if(!siteList.contains(siteCode)){
                        if("en".equals(language)){
                            return ApiResultI18n.failure(500, "Insufficient space", bookingSpaceRequest.getLanguage());
                        }else{
                            return ApiResultI18n.failure(500, "所选班列与上下货站点不匹配", bookingSpaceRequest.getLanguage());
                        }
                    }
                }
            }
        }
        String goodsReport = "";
        String goodsClearance = "";
        if("0".equals(bookingSpaceRequest.getClassEastandwest())){
            goodsReport = bookingSpaceRequest.getGoodsInReport();
            goodsClearance = bookingSpaceRequest.getGoodsOutClearance();
        }
        if("1".equals(bookingSpaceRequest.getClassEastandwest())){
            goodsReport = bookingSpaceRequest.getGoodsReport();
            goodsClearance = bookingSpaceRequest.getGoodsClearance();
        }
        if("en".equals(language)){
            //品名
            if(!StringUtils.isEmpty(bookingSpaceRequest.getGoodsName())) {
                if (dangerousGoodsService.selectCount(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_name",bookingSpaceRequest.getGoodsName()).eq("note_level", 0)) != 0) {
                    String msg = dangerousGoodsService.selectList(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_name",bookingSpaceRequest.getGoodsName()).eq("note_level", 0)).get(0).getSpecialEnRemark();
                    return ApiResultI18n.failure(500, msg, bookingSpaceRequest.getLanguage());
                }
            }
            if(!StringUtils.isEmpty(bookingSpaceRequest.getGoodsEnName())) {
                if (dangerousGoodsService.selectCount(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_en_name",bookingSpaceRequest.getGoodsEnName()).eq("note_level", 0)) != 0) {
                    String msg = dangerousGoodsService.selectList(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_en_name",bookingSpaceRequest.getGoodsEnName()).eq("note_level", 0)).get(0).getSpecialEnRemark();
                    return ApiResultI18n.failure(500, msg, bookingSpaceRequest.getLanguage());
                }
            }
            //报关hs编码
            if(!StringUtils.isEmpty(goodsReport)){
                if (dangerousGoodsService.selectCount(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_report", goodsReport).eq("note_level", 0)) != 0) {
                    String msg = dangerousGoodsService.selectList(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_report", goodsReport).eq("note_level", 0)).get(0).getSpecialEnRemark();
                    return ApiResultI18n.failure(500, msg, bookingSpaceRequest.getLanguage());
                }
            }
            //清关hs编码
            if(!StringUtils.isEmpty(goodsClearance)){
                if (dangerousGoodsService.selectCount(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_clearance", goodsClearance).eq("note_level", 0)) != 0) {
                    String msg = dangerousGoodsService.selectList(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_clearance", goodsClearance).eq("note_level", 0)).get(0).getSpecialEnRemark();
                    return ApiResultI18n.failure(500, msg, bookingSpaceRequest.getLanguage());
                }
            }
        }else{
            //品名
            if(!StringUtils.isEmpty(bookingSpaceRequest.getGoodsName())) {
                if (dangerousGoodsService.selectCount(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_name",bookingSpaceRequest.getGoodsName()).eq("note_level", 0)) != 0) {
                    String msg = dangerousGoodsService.selectList(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_name",bookingSpaceRequest.getGoodsName()).eq("note_level", 0)).get(0).getSpecialremark();
                    return ApiResultI18n.failure(500, msg, bookingSpaceRequest.getLanguage());
                }
            }
            if(!StringUtils.isEmpty(bookingSpaceRequest.getGoodsEnName())) {
                if (dangerousGoodsService.selectCount(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_en_name",bookingSpaceRequest.getGoodsEnName()).eq("note_level", 0)) != 0) {
                    String msg = dangerousGoodsService.selectList(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_en_name",bookingSpaceRequest.getGoodsEnName()).eq("note_level", 0)).get(0).getSpecialremark();
                    return ApiResultI18n.failure(500, msg, bookingSpaceRequest.getLanguage());
                }
            }
            //报关hs编码
            if(!StringUtils.isEmpty(goodsReport)){
                if (dangerousGoodsService.selectCount(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_report", goodsReport).eq("note_level", 0)) != 0) {
                    String msg = dangerousGoodsService.selectList(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_report", goodsReport).eq("note_level", 0)).get(0).getSpecialremark();
                    return ApiResultI18n.failure(500, msg, bookingSpaceRequest.getLanguage());
                }
            }
            //清关hs编码
            if(!StringUtils.isEmpty(goodsClearance)){
                if (dangerousGoodsService.selectCount(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_clearance", goodsClearance).eq("note_level", 0)) != 0) {
                    String msg = dangerousGoodsService.selectList(new EntityWrapper<BusiShippingorderDangerousGoods>().eq("goods_clearance", goodsClearance).eq("note_level", 0)).get(0).getSpecialremark();
                    return ApiResultI18n.failure(500, msg, bookingSpaceRequest.getLanguage());
                }
            }
        }
        return busiShippingorderService.add(bookingSpaceRequest);
    }

    @ApiIgnore(value = "特殊单证列表")
    @GetMapping("baseGoodsnote")
    public ApiResultI18n baseGoodsnote(@RequestParam Integer limit, @RequestParam Integer page, @RequestParam String goBack, String language) {
        Object[] objects = new Object[2];
        objects[0] = goBack;
        objects[1] = "0|1";
        Page<BaseGoodsnote> list = baseGoodsnoteService.selectPage(new Page<>(page, limit),
                new EntityWrapper<BaseGoodsnote>()
                        .eq("goods_class", "0")
                        .in("EastAndWest", objects)
        );
        return ApiResultI18n.success(list, language);
    }

    @RequestMapping("/containerType")//线路类型：0是中欧2是中亚3是中越4是中俄  goodsType  0整柜 1拼箱
    public List<ContainerVo> containerType(@RequestParam String lineType, @RequestParam String language, @RequestParam String belong,@RequestParam String goodsType) throws ParseException {
        List<ContainerVo> sr = this.doPost();  List<ContainerVo> st=new ArrayList<>();
        String zhonge="40尺普箱，40尺高箱，40尺冷藏箱";
        if(lineType.equals("4") && goodsType.equals("0")) {// 中俄整柜 只有zhonge这三种
            for (ContainerVo r : sr) {
                if (zhonge.indexOf(r.getName()) != -1) {
                    st.add(r);
                }
            }
        }else {// 其他  删去del中包含的
            //  String del = "20尺超高开顶箱，40尺超高开顶箱，20尺挂衣箱，40尺挂衣箱，20尺普高开顶箱，40尺普高开顶箱，40尺分层箱";//正式服务器
//              String del = "40尺分层箱"; //测试服务器
            for (ContainerVo r : sr) {
                if (del.indexOf(r.getName()) == -1) {
                    st.add(r);
                }
            }
        }
        if(language.equalsIgnoreCase("en")){
            for (ContainerVo s : st) {
                s.setName(s.getNameEn());
            }
        }
        if (lineType.equals("0") && belong.equals("0")) {//中欧 zih 其他再删去40GP
            for (ContainerVo m : st) {
                if (m.getCode().equals("40GP")) {
                    st.remove(m);
                    break;
                }
            }
        }
        return st;
    }

    public static List<ContainerVo> doPost() {
        HttpClient client = HttpClients.createDefault();
        // 要调用的接口方法 箱管系统的箱型列表
        String url = "http://xg.zih718.com:8080/conteasy/container/all_container_type";
        HttpGet post = new HttpGet(url);
        try {
            post.setURI(URI.create(url));
            HttpResponse res = client.execute(post);
            String response1 = EntityUtils.toString(res.getEntity());
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                List<ContainerVo> containerVos = JSONObject.parseArray(response1, ContainerVo.class);
                return containerVos;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    private String getNameByCode(String code, String language) {
        String name = code;
        if (language.equalsIgnoreCase("en")) {
            name = busiSiteService.selectOne(new EntityWrapper<BusiSite>().eq("code", code)).getNameEn();
        } else {
            name = busiSiteService.selectOne(new EntityWrapper<BusiSite>().eq("code", code)).getNameCn();
        }
        return name;
    }


}
