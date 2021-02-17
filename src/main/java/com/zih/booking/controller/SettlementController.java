package com.zih.booking.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.dao.BusiShippingorderMapper;
import com.zih.booking.model.*;
import com.zih.booking.request.ConfirmInvoiceRequest;
import com.zih.booking.request.SettlementRequest;
import com.zih.booking.response.SettlementResponse;
import com.zih.booking.service.BookingCityService;
import com.zih.booking.service.BusiShippingorderService;
import com.zih.booking.service.BusiTaizangInfoService;
import com.zih.booking.service.TrackGoodsStatusService;
import com.zih.booking.settlementDao.BusiTaizangInfoMapper;
import com.zih.booking.system.enums.LanguageEnum;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.system.vo.Result;
import com.zih.booking.utils.*;
import com.zih.booking.utils.email.IMailService;
import com.zih.booking.vo.TaiZangDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
@Slf4j
@RestController
@RequestMapping("/settlement")
@Api(tags = "结算中心")
public class SettlementController {
    @Resource
    private ResourceLoader resourceLoader;
    @Autowired
    private BusiShippingorderMapper busiShippingorderMapper;
    @Autowired
    private BusiTaizangInfoService taizangInfoService;
    @Autowired
    private BusiTaizangInfoMapper busiTaizangInfoMapper;
    @Autowired
    private BookingCityService bookingCityService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BusiShippingorderService busiShippingorderService;

    @Autowired
    private TrackGoodsStatusService goodsStatusService;
    @Autowired
    private IMailService mailService;

    @Value("${dcSystem.domainName}")
    private String dcDomainName;

    private String getEnAddress(String address) {
        String enAddress = address;
        if (bookingCityService.selectList(new EntityWrapper<BookingCity>().eq("name_cn", address)).size() != 0) {
            enAddress = bookingCityService.selectList(new EntityWrapper<BookingCity>().eq("name_cn", address)).get(0).getNameEn();
        }
        return enAddress;
    }

    @ApiOperation("费用确认列表 结算信息列表也是这个")
    @PostMapping("/list")
    public ApiResultI18n getList(@RequestBody SettlementRequest settlementRequest) {
        List<String> clients = tokenService.getClientIds(ServletUtils.getRequest());//客户id
        settlementRequest.setClientIds(clients);
        Page<SettlementResponse> page = new Page<>(settlementRequest.getPage(), settlementRequest.getLimit());
        log.info(dcDomainName);
        String jsonObject = HttpClientUtil.doPostJson(dcDomainName+ "/Ajax/CostList.ashx",JSONObject.toJSONString(settlementRequest));
        log.info(jsonObject);
        Integer  total = (Integer) JSONObject.parseObject(jsonObject).get("Total");
        List<SettlementResponse> li = JSONObject.parseArray(JSONObject.parseObject(jsonObject).get("Data").toString(), SettlementResponse.class);
        for (SettlementResponse res : li) {
            List<TrackGoodsStatus>goodsStatuses=goodsStatusService.selectList(new EntityWrapper<TrackGoodsStatus>().eq("order_id",res.getOrderId()));
            if (goodsStatuses.size()!=0){//箱号
                List<String> boxNums=new ArrayList<>();
                for (TrackGoodsStatus goodsStatus: goodsStatuses ) {
                    boxNums.add(goodsStatus.getBoxNum());
                }
                String string1 = String.join(",",boxNums);
                res.setXianghao(string1);
            }
        }
        page.setRecords(li);
        page.setTotal(total);
        if (settlementRequest.getLanguage().equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
            for (SettlementResponse response : page.getRecords()) {
                response.setOrderUploadsite(getEnAddress(response.getOrderUploadsite()));
                response.setOrderUnloadsite(getEnAddress(response.getOrderUnloadsite()));
            }
        }
        return ApiResultI18n.success(page, settlementRequest.getLanguage());
    }

    @GetMapping("/download")
    @ApiOperation(value = "下载    type  类型   0 委托付款说明书   1 调账说明 ")
    public void downModel(HttpServletRequest request, HttpServletResponse response, Integer type) {
        String filename = "委托付款说明书";
        String path = "static/委托付款说明书.doc";
        if (type == 1) {
            filename = "调账说明";
            path = "static/调账说明.docx";
        }
        ExcelUtil.downloadThymeleaf(resourceLoader, filename, path, request, response);
    }


    @PostMapping("/upload")
    @ApiOperation("上传文件 ")
    public Result uploadFile(HttpServletRequest request, @RequestParam MultipartFile file) {
        String fileUrl = null;
        try {
            fileUrl = Upload.uploadFile(request, file, "settlement");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return new Result(Result.FAIL, "上传失败");
        }
        return new Result(fileUrl);
    }

    @PostMapping("/preservation")
    @ApiOperation("上传完的保存按钮 发票号taizangFpNo 舱位号orderNumber")
    public Result preservation(@RequestParam String taizangFpNo, @RequestParam String orderNumber, Integer type, @RequestParam String fileUrl) throws Exception {
        //  String orderId=busiShippingorderMapper.selectOne(new EntityWrapper<BusiShippingorder>().eq("order_number", orderNumber));
        BusiShippingorder order = busiShippingorderService.selectOne(new EntityWrapper<BusiShippingorder>().eq("order_number", orderNumber));
        if (null == order) {
            return new Result(1, "该仓位号" + orderNumber + "不存在");
        }
        Map map = new HashMap();
        map.put("orderId", order.getOrderId());
        JSONObject object = HttpClientUtil.getRequest(dcDomainName+ "/Ajax/costTZ.ashx", map, null);
        log.info(object.toJSONString());
        if(200 == Integer.parseInt(object.get("Status").toString())){
            String beanString = object.get("Data").toString().replace("[","").replace("]","");
            BusiTaizangInfo taizangInfo = JSONObject.toJavaObject(JSON.parseObject(beanString), BusiTaizangInfo.class);
            if (null == taizangInfo) {
                return new Result(1, "该仓位号" + orderNumber + "不存在");
            }
            taizangInfo.setTaizangFpNo(taizangFpNo);
            if (type == 1) {
                taizangInfo.setPaychangeLink(fileUrl);
                taizangInfo.setPaychangeState("1");
            } else {
                taizangInfo.setPayLink(fileUrl);
                taizangInfo.setPayState("1");
            }
            return new Result(taizangInfoService.updateById(taizangInfo));
        }else {
            return new Result(1, "该仓位号" + orderNumber + "不存在");
        }
    }

    @ApiOperation("结算信息 导出pdf  ")
    @GetMapping("/pdfOut")
    public void pdfOut(String orderId, HttpServletResponse response,String language)throws Exception {
        Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(getDetail(orderId, language).getData()),Map.class);
        String templatePath = "";
       log.info(String.valueOf(map));
        if (map.get("isConsolidation").equals("1")) {
            templatePath = "static/结算拼箱模板.pdf";
        } else {
            templatePath = "static/结算整箱模板.pdf";
        }if (map.get("class_EastAndWest").equals("0")) {
           map.put("bgType","报关费");
        } else {
            map.put("bgType","清关费");
        }
        List<TrackGoodsStatus>goodsStatuses=goodsStatusService.selectList(new EntityWrapper<TrackGoodsStatus>().eq("order_id",orderId));
        if (goodsStatuses.size()!=0){//箱号
            List<String> boxNums=new ArrayList<>();
            for (TrackGoodsStatus goodsStatus: goodsStatuses ) {
                boxNums.add(goodsStatus.getBoxNum());
            }
            String string1 = String.join(",",boxNums);
            map.put("container_no",string1);
        }
        PdfUtils.pdfout2(resourceLoader, map,  templatePath);
        // 下载本地文件
        String fileName = "配舱通知书.pdf".toString(); // 文件的默认保存名
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

    @ApiOperation("费用确认单 详情  orderId=380e9b63-5c28-4754-94fb-c307e90c0560 可用于测试")
    @GetMapping("/getDetail")
    public ApiResultI18n getDetail(String orderId, String language) throws Exception {
        Map map = new HashMap();
        map.put("orderId", orderId);
        JSONObject result = HttpClientUtil.getRequest(dcDomainName+ "/Ajax/CostDetail.ashx", map, null);
        log.info(result.toJSONString());
        if(200 == Integer.parseInt(result.get("Status").toString())){
//            TaiZangDetail taizangInfo = busiTaizangInfoMapper.getByOrderId(orderId);
            String beanString = result.get("Data").toString().replace("[","").replace("]","");
            TaiZangDetail taizangInfo = JSONObject.toJavaObject(JSON.parseObject(beanString), TaiZangDetail.class);
            log.info(taizangInfo.toString());
            List<TrackGoodsStatus>goodsStatuses=goodsStatusService.selectList(new EntityWrapper<TrackGoodsStatus>().eq("order_id",orderId));
            if (goodsStatuses.size()!=0){//箱号
                List<String> boxNums=new ArrayList<>();
                for (TrackGoodsStatus goodsStatus: goodsStatuses ) {
                    boxNums.add(goodsStatus.getBoxNum());
                }
                String string1 = String.join(",",boxNums);
                taizangInfo.setContainer_no(string1);
            }
            if (language.equalsIgnoreCase(LanguageEnum.LANGUAGE_EN_US.getLanguage())) {
                taizangInfo.setOrder_uploadSite(getEnAddress(taizangInfo.getOrder_uploadSite()));
                taizangInfo.setOrder_unloadSite(getEnAddress(taizangInfo.getOrder_unloadSite()));
            }
            String line_typeid = taizangInfo.getTypeid(); //线路id（0中欧2中亚，3中越，4中俄）
            if (line_typeid.equals("0") || line_typeid.equals("4")) {//铁路运费币种
                taizangInfo.setSiteIncomeCurrency("USD");
            } else {
                taizangInfo.setSiteIncomeCurrency("RMB");
            }
            if (line_typeid.equals("2")) {  //返利币种
                taizangInfo.setDiscountsCurrency("RMB");
            } else {
                taizangInfo.setDiscountsCurrency("USD");
            }
            if (taizangInfo.getIsConsolidation().equals("1") && taizangInfo.getClass_EastAndWest().equals("0")) {//拼箱 西向
                taizangInfo.setTH_INCOMESCurrency("RMB");//提货费币种
                taizangInfo.setContainer_boxAmount("LCL");
                taizangInfo.setContainer_type("LCL");
                if (line_typeid.equals("0") || line_typeid.equals("4")) {//分拨费币种
                    taizangInfo.setSH_INCOMESCurrency("EUR");
                } else {
                    taizangInfo.setSH_INCOMESCurrency("USD");
                }
            }
            if (taizangInfo.getIsConsolidation().equals("1") && taizangInfo.getClass_EastAndWest().equals("1")) {//拼箱 东向
                taizangInfo.setSH_INCOMESCurrency("RMB");//分拨费币种
                taizangInfo.setContainer_boxAmount("LCL");
                taizangInfo.setContainer_type("LCL");
                if (line_typeid.equals("0") || line_typeid.equals("4")) {
                    taizangInfo.setTH_INCOMESCurrency("EUR");//提货费币种
                } else {
                    taizangInfo.setTH_INCOMESCurrency("USD");
                }
            }
            if (taizangInfo.getIsConsolidation().equals("0") && taizangInfo.getClass_EastAndWest().equals("0")) {//整柜 西向
                taizangInfo.setTH_INCOMESCurrency("USD");//提货费币种
                if (line_typeid.equals("0") || line_typeid.equals("4")) {//分拨费币种
                    taizangInfo.setSH_INCOMESCurrency("EUR");
                } else {
                    taizangInfo.setSH_INCOMESCurrency("USD");
                }
            }
            if (taizangInfo.getIsConsolidation().equals("0") && taizangInfo.getClass_EastAndWest().equals("1")) {//整柜 东向
                taizangInfo.setSH_INCOMESCurrency("USD");//分拨费币种
                if (line_typeid.equals("0") || line_typeid.equals("4")) {
                    taizangInfo.setTH_INCOMESCurrency("EUR");//提货费币种
                } else {
                    taizangInfo.setTH_INCOMESCurrency("USD");
                }
            }
            return ApiResultI18n.success(taizangInfo, language);
        }
        return ApiResultI18n.failure("获取详情失败！Failed to get details！");
    }

    @ApiOperation("费用单 确认 ")
    @GetMapping("/confirmInvoice")
    public ApiResultI18n confirmInvoice(ConfirmInvoiceRequest invoiceRequest) {
        BusiTaizangInfo taizangInfo = new BusiTaizangInfo();
       // BeanUtils.copyProperties(invoiceRequest, taizangInfo);
        taizangInfo.setConfirmationDate(new Date().toString());
        BusiShippingorder shippingorder = new BusiShippingorder();
        shippingorder.setOrderId(invoiceRequest.getOrderId());
        shippingorder.setCostVerify("1");//更新托书表
        busiShippingorderMapper.updateById(shippingorder);

//        taizangInfo.setTaizangId(invoiceRequest.getTaizang_id());
//        taizangInfo.setKpEur(invoiceRequest.getKp_eur());
//        taizangInfo.setKpRmb(invoiceRequest.getKp_rmb());
//        taizangInfo.setKpUsd(invoiceRequest.getKp_usd());
//        taizangInfo.setRemarkKp(invoiceRequest.getRemark_kp());
//        taizangInfo.setCostVerify("1");//更新台账表

        Map map = new HashMap();
        map.put("orderId", invoiceRequest.getOrderId());
        map.put("kp_eur",invoiceRequest.getKp_eur());
        map.put("kp_rmb",invoiceRequest.getKp_rmb());
        map.put("kp_usd",invoiceRequest.getKp_usd());
        map.put("Remark_kp",invoiceRequest.getRemark_kp());
//        new Thread(()->{
//            /*发送邮件*/
//            List<TjrEmail> list = busiShippingorderMapper.selectTjrEmail(invoiceRequest.getOrderId());
//            if(list.size() > 0 && StringUtils.isNotEmpty(list.get(0).getMCheng())){
//                int index = list.get(0).getMCheng().indexOf("/");
//                if(index > 0) {
//                    String emails = list.get(0).getMCheng().substring(index+1);
//                    if (StringUtils.isNotEmpty(emails.trim())){
//                        String[] emailArriy = emails.trim().split(";");
//                        String body = "客户已费用确认---舱位号："
//                                + list.get(0).getOrderNumber()+", 人民币开票金额: "+
//                                invoiceRequest.getKp_rmb() + ", 美金开票金额: " +
//                                invoiceRequest.getKp_usd() + ", 欧元开票金额: " +
//                                invoiceRequest.getKp_eur();
//                        if (list.get(0).getDeptCode().contains("YWB1000100")
//                                || list.get(0).getDeptCode().contains("YWB1000101")
//                                || list.get(0).getDeptCode().contains("YWB1000200")
//                                || list.get(0).getDeptCode().contains("YWB1000202")){
//                            log.info(emailArriy[0]);
//                            mailService.sendSimpleMail(emailArriy[0], "客户已费用确认---舱位号："
//                                    + list.get(0).getOrderNumber(), body);
//                        }else {
//                            log.info(emails);
//                            mailService.sendSimpleMail(emailArriy, "客户已费用确认---舱位号："
//                                    + list.get(0).getOrderNumber(), body);
//                        }
//                    }
//                }
//            }
//        }).start();
        String result = HttpClientUtil.doPostJson(dcDomainName+ "/Ajax/Updatecost.ashx", JSONObject.toJSONString(map));
        log.info(result);
        if(Integer.parseInt(JSONObject.parseObject(result).get("Status").toString())==200){
            return ApiResultI18n.success(1, invoiceRequest.getLanguage());
        }else {
            return ApiResultI18n.failure(1,"提交失败！", invoiceRequest.getLanguage());
        }
//        return ApiResultI18n.success(busiTaizangInfoMapper.updateById(taizangInfo), invoiceRequest.getLanguage());
    }

    @ApiOperation("费用确认列表导出 ")
    @PostMapping("/exportExcel")//
    public void exportExcel(HttpServletResponse response, @RequestBody SettlementRequest settlementRequest) throws Exception {
        String client_id = tokenService.getClientId(ServletUtils.getRequest());//客户id
        List<String> clients = tokenService.getClientIds(ServletUtils.getRequest());//客户id
        settlementRequest.setClientIds(clients);
        TemplateExportParams params = new TemplateExportParams(
                "static/翼源国际货运代理（上海）有限公司青岛分公司客户费用表01.xlsx");//
        String jsonObject = HttpClientUtil.doPostJson(dcDomainName+ "/Ajax/CostListExport.ashx",JSONObject.toJSONString(settlementRequest));
        log.info(jsonObject);
        Map mapp = JSONObject.parseObject(jsonObject);
        Map<String, Object> map = new HashMap<String, Object>();
//        List<Map<String, String>> listMap2 = busiTaizangInfoMapper.getAllExports(settlementRequest);
        map.put("maplist", mapp.get("Data"));
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        ExcelExport.export(response, workbook, "翼源国际货运代理（上海）有限公司青岛分公司客户费用表" + new Date().toString());
    }

    @ApiOperation("结算信息列表导出 ")
    @PostMapping("/exportExcel2")//
    public void exportExcel2(HttpServletResponse response, @RequestBody SettlementRequest settlementRequest) throws Exception {
        String client_id = tokenService.getClientId(ServletUtils.getRequest());//客户id
        List<String> clients = tokenService.getClientIds(ServletUtils.getRequest());//客户id
        settlementRequest.setClientIds(clients);
        TemplateExportParams params = new TemplateExportParams(
                "static/翼源国际货运代理（上海）有限公司青岛分公司客户费用表02.xlsx");
        String jsonObject = HttpClientUtil.doPostJson(dcDomainName+ "/Ajax/CostListExport.ashx",JSONObject.toJSONString(settlementRequest));
        log.info(jsonObject);
        Map mapp = JSONObject.parseObject(jsonObject);
        Map<String, Object> map = new HashMap<String, Object>();
//        List<Map<String, String>> listMap = busiTaizangInfoMapper.getAllExports(settlementRequest);
        map.put("maplist", mapp.get("Data"));
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        ExcelExport.export(response, workbook, "翼源国际货运代理（上海）有限公司青岛分公司客户费用表" + new Date().toString());
    }
}
