package com.zih.booking.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.dao.BusiShippingorderMapper;

import com.zih.booking.model.*;
import com.zih.booking.request.WarehouseRequest;
import com.zih.booking.response.AllocationNotice;
import com.zih.booking.response.BusiOrderListResponse;
import com.zih.booking.response.WarehouseResponse;
import com.zih.booking.service.*;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.utils.*;
import com.zih.booking.vo.BusiShippingorderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-01-09
 */
@Slf4j
@RestController
@RequestMapping("/busiShippingorder")
@Api(tags = "订单中心")
public class BusiShippingorderController {
    @Resource
    private ResourceLoader resourceLoader;
    @Autowired
    private BusiShippingorderMapper busiShippingorderMapper;
    @Autowired
    private BusiSiteService busiSiteService;
    @Autowired
    private TrackGoStationAddressService addressService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DocOrderDocumentService docOrderDocumentService;
    @Autowired
    private BookingInquiryGoodsDetailsService goodsDetailsService;
    @Autowired
    private BusiStationService busiStationService;
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/getWare")
    @ApiOperation("拼箱入仓信息")
    public ApiResultI18n getWare(WarehouseRequest documentFileRequest) {
        List<String> clients=tokenService.getClientIds(ServletUtils.getRequest());
        documentFileRequest.setClientIds(clients);
        Page<WarehouseResponse> page = new Page<>(documentFileRequest.getPage(), documentFileRequest.getLimit());
        List<WarehouseResponse> list = busiShippingorderMapper.selectWareList(page, documentFileRequest);
        page.setRecords(list);
        if (documentFileRequest.getLanguage().equalsIgnoreCase("en")) {
            for (WarehouseResponse response : page.getRecords()) {
                response.setGoodsName(response.getGoodsEnName());
            }
        }
        return ApiResultI18n.success(page, documentFileRequest.getLanguage());
    }

    @GetMapping("/myOrders")
    @ApiOperation("我的订单")
    public ApiResultI18n getMyOrders(BusiShippingorderVo busiShippingorderVo) {
        List<String> clients=tokenService.getClientIds(ServletUtils.getRequest());
        busiShippingorderVo.setClientIds(clients);
        String khId = tokenService.getKhId(ServletUtils.getRequest());
        if(null != khId && khId!=""){
            busiShippingorderVo.setKhId(Long.valueOf(khId));
            clients.add(tokenService.getKhClientId(ServletUtils.getRequest()));
            busiShippingorderVo.setClientIds(clients);
        }
        if (busiShippingorderVo.getLanguage().equalsIgnoreCase("en")) {
            busiShippingorderVo.setGoodsEnName(busiShippingorderVo.getGoodsName());
            busiShippingorderVo.setGoodsName("");
        }
        Page<BusiOrderListResponse> page = new Page<>(busiShippingorderVo.getPage(), busiShippingorderVo.getLimit());
        page.setRecords(busiShippingorderMapper.selectMyList(page, busiShippingorderVo));
        for (BusiOrderListResponse response : page.getRecords()) {
            TrackGoStationAddress address = addressService.selectOne(new EntityWrapper<TrackGoStationAddress>().eq("class_id", response.getClassId()).eq("code", response.getOrderUnloadcode()));
            if (null != address && null != address.getStationAddress()) {
                response.setSite(address.getStationAddress());
            }
            //驳回原因
            String failReason = "";
            if(response.getExamInfo()!=null && response.getExamInfo()!=""){
                failReason += response.getExamInfo()+";";
            }
            if(response.getXgRefuseInfo()!=null && response.getXgRefuseInfo()!=""){
                failReason += response.getXgRefuseInfo()+";";
            }
            if(response.getRefuseInfo()!=null && response.getRefuseInfo()!=""){
                failReason += response.getRefuseInfo();
            }
            response.setFailReason(failReason);
            if(null!=failReason && failReason!=""){
                response.setIsReason("1"); //是否有驳回原因 0否 1是
            }else{
                response.setIsReason("0"); //是否有驳回原因 0否 1是
            }
            //取消托书是否可编辑 0是 1否
            String isEdit = "0";
            if("0".equals(response.getClientOrderRemarks()) && response.getExameTime()==null){
                isEdit = "1";
            }
            response.setIsEdit(isEdit);
        }
        if (busiShippingorderVo.getLanguage().equalsIgnoreCase("en")) {
            for (BusiOrderListResponse response : page.getRecords()) {
                response.setGoodsName(response.getGoodsEnName());
                response.setOrderUnloadsite(getEnNameByCh(response.getOrderUnloadsite()));
                response.setOrderUploadsite(getEnNameByCh(response.getOrderUploadsite()));
            }
        }
        return ApiResultI18n.success(page, busiShippingorderVo.getLanguage());
    }


    @GetMapping("/stationAddress")
    @ApiIgnore("西向整柜车站地址")
    public ApiResultI18n stationAddress(@RequestParam String classId, String language) {
        TrackGoStationAddress address = addressService.selectOne(new EntityWrapper<TrackGoStationAddress>().eq("class_id", classId));
        JSONObject json = new JSONObject();
        json.put("address", address);
        return ApiResultI18n.success(json, language);
    }

    @GetMapping("/cancel")
    @ApiOperation("取消托书")
    public ApiResultI18n cancel(@RequestParam String orderId, String language) {
        BusiShippingorder busiShippingorder = new BusiShippingorder();
        busiShippingorder.setOrderId(orderId);
        busiShippingorder.setIsExamline("3");
        return ApiResultI18n.success(busiShippingorderMapper.updateById(busiShippingorder), language);
    }

    private String getEnNameByCh(String nameCn) {
        String enName = nameCn;
        enName = busiSiteService.selectOne(new EntityWrapper<BusiSite>().eq("name_cn", nameCn).or().eq("name_en", nameCn)).getNameEn();
        return enName;
    }

    @GetMapping("/allocationNotice")
    @ApiOperation("配舱通知书")
    public ApiResultI18n getAllocationNotice(@RequestParam String orderId, String language) {
        AllocationNotice allocationNotice = busiShippingorderMapper.getAllocationNotice(orderId);
        if (language.equalsIgnoreCase("en")) {
            allocationNotice.setGoodsName(allocationNotice.getGoodsEnName());
            allocationNotice.setOrderUnloadsite(getEnNameByCh(allocationNotice.getOrderUnloadsite()));
            allocationNotice.setOrderUploadsite(getEnNameByCh(allocationNotice.getOrderUploadsite()));
            allocationNotice.setReceiveOrderName(allocationNotice.getReceiveOrderEnName());
        }
        if (allocationNotice.getIsConsolidation().equals(1)) {
            allocationNotice.setZxAddress(allocationNotice.getPxAddress());
            allocationNotice.setContainerBoxamount("LCL");
            allocationNotice.setContainerType("LCL");
        }
        if (StringUtils.isNotEmpty(allocationNotice.getOrderMerchandiserId())) {
            boolean hasMoreIds = allocationNotice.getOrderMerchandiserId().contains("|");
            String[] ids = hasMoreIds ? allocationNotice.getOrderMerchandiserId().split("|") : new String[]{allocationNotice.getOrderMerchandiserId()};
            StringBuilder sb = new StringBuilder();
            for (int i=0;  i< ids.length; i++) {
                SysUser user = sysUserService.selectOne(new EntityWrapper<SysUser>().eq("dc_user_id", ids[i]));
                if (null != user) {
                    sb.append(user.getNickName())
                            .append(":")
                            .append(user.getPhonenumber()).append("; ");
                }
            }
            allocationNotice.setOrderMerchandiser(sb.toString());
        }
        if(allocationNotice.getClassEastAndWest().equals("1")){
            allocationNotice.setPxYstype(allocationNotice.getIsConsolidation().equals("0") ? allocationNotice.getZxAddress() : allocationNotice.getPxYstype() + " - " + allocationNotice.getPxAddress());
            BusiStation station = busiStationService.selectById(allocationNotice.getStationID());
            if (null != station && StringUtils.isEmpty(station.getCuntofftime())) {
                allocationNotice.setPxYstype(station.getPxYstype());
                String zp = allocationNotice.getIsConsolidation();//0:整，1：拼
                String bg = allocationNotice.getClientOrderBindingWay();// 0:是，1：否
                String flag = zp + bg;
                switch (flag) {
                    case "00":
                        allocationNotice.setCuntofftime(station.getFclCustomsTime());
                        break;
                    case "01":
                        allocationNotice.setCuntofftime(station.getFclCustomsNotTime());
                        break;
                    case "10":
                        allocationNotice.setCuntofftime(station.getLclCustomsTime());
                        break;
                    case "11":
                        allocationNotice.setCuntofftime(station.getLclCustomsNotTime());
                        break;
                }
            }
        }
        allocationNotice.setContacts(HtmlParser.Html2Text(allocationNotice.getContacts()));
        return ApiResultI18n.success(allocationNotice, language);
    }

    @GetMapping("/qrCode")
    public ResponseEntity<byte[]> getQrCode(@RequestParam String orderId) throws Exception {
        byte[] pngData = QRCodeGenerator.getQRCodeImage(orderId, 350, 350);
        // Set headers
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(pngData, headers, HttpStatus.CREATED);
    }

    @GetMapping("/pdfOut")
    @ApiOperation("配舱通知书 pdf导出 ")
    public void pdfOut(@RequestParam String orderId,@RequestParam String classEastandwest, HttpServletResponse response, String language) throws Exception {
        Map<String, Object> map = getMap(orderId,language,classEastandwest);
        Map<String, byte[]> map2 = new HashMap();
        map2.put("img", QRCodeGenerator.getQRCodeImage(orderId, 350, 350));
        String templatePath="";
        if(classEastandwest.equals("0")){
            templatePath= "static/ZIHWB200422LH12入货通知书模板.pdf";
        }else{
            templatePath= "static/ZIHEB200422LH12入货通知书模板2.pdf";
        }
        PdfUtils.pdfout(resourceLoader, map, map2, templatePath,3);
        // 下载本地文件
        String fileName = "配舱通知叔叔.pdf".toString(); // 文件的默认保存名
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
    @GetMapping("/pdfOut2")
    @ApiOperation("配舱通知书  ")
    public String pdfOut2(@RequestParam String orderId,  String classEastandwest, HttpServletResponse response, String language) throws Exception {
        String fileUrl = docOrderDocumentService.selectOne(new EntityWrapper<DocOrderDocument>().eq("order_id",orderId).eq("file_type_key","order_notice_file")).getFileUrl();
        return DocUrlHandle.urlHandle(fileUrl);
    }

    @GetMapping("/qrCode2")
    @ApiOperation("二维码导出 ")
    public void qrCode2(@RequestParam String orderId, HttpServletResponse response, String language) throws Exception {
        AllocationNotice allocationNotice = busiShippingorderMapper.getAllocationNotice(orderId);
        if(allocationNotice.getClassEastAndWest().equals("1")){
            BusiStation station = busiStationService.selectById(allocationNotice.getStationID());
            if (null != station && StringUtils.isEmpty(station.getCuntofftime())) {
                allocationNotice.setPxYstype(station.getPxYstype());
                String zp = allocationNotice.getIsConsolidation();//0:整，1：拼
                String bg = allocationNotice.getClientOrderBindingWay();// 0:是，1：否
                String flag = zp + bg;
                switch (flag) {
                    case "00":
                        allocationNotice.setCuntofftime(station.getFclCustomsTime());
                        break;
                    case "01":
                        allocationNotice.setCuntofftime(station.getFclCustomsNotTime());
                        break;
                    case "10":
                        allocationNotice.setCuntofftime(station.getLclCustomsTime());
                        break;
                    case "11":
                        allocationNotice.setCuntofftime(station.getLclCustomsNotTime());
                        break;
                }
            }
        }
        allocationNotice.setContacts(HtmlParser.Html2Text(allocationNotice.getContacts()));
        Map<String, Object> map =new HashMap<>();
        map.put("spaceNo",allocationNotice.getOrderNumber());
        map.put("quantity",allocationNotice.getGoodsNumber());
        map.put("out",allocationNotice.getGoodsPacking());
        map.put("destination",allocationNotice.getClassEastAndWest().equals("0")?allocationNotice.getOrderUnloadsite():allocationNotice.getOrderUploadsite());
        map.put("address",allocationNotice.getClassEastAndWest().equals("0")?"ZIH Warehouse":allocationNotice.getStation());
        map.put("sendGoodsAddress",allocationNotice.getClassEastAndWest().equals("0")?"郑欧班列多式联运场站":allocationNotice.getPxYstype());
        if(allocationNotice.getClassEastAndWest().equals("1")) {
            map.put("contact", "联系方式：");
            map.put("contactInfo", allocationNotice.getContacts());
        }

        Map<String, byte[]> map2 = new HashMap();
        map2.put("img", QRCodeGenerator.getQRCodeImage(orderId, 350, 350));
        String templatePath="static/二维码模板.pdf";
        PdfUtils.pdfout(resourceLoader, map, map2, templatePath,1);
        // 下载本地文件
        String fileName = "二维码.pdf".toString(); // 文件的默认保存名
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
    private Map getMap(String orderId,String language,String classEastandwest){
        Map<String, Object> map =new HashMap<>();
        if(classEastandwest.equals(0)){
         map = busiShippingorderMapper.getAllocationNoticePdfXi(orderId);
        }else{
         map=busiShippingorderMapper.getAllocationNoticePdfDong(orderId);
            if ("1".equals(map.get("isConsolidation"))) {
               map.put("zxAddress",map.get("pxAddress"));
            }map.remove("pxAddress");
        }
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        map.put("classDate", format.format(map.get("classDate")));
        map.put("date", format.format(date));
        if (language.equalsIgnoreCase("en")) {
            map.put("goodsNmae", map.get("goodsEnName"));
            map.put("orderUnloadsite", getEnNameByCh(map.get("orderUnloadsite").toString()));
            map.put("orderUploadsite", getEnNameByCh(map.get("orderUploadsite").toString()));
            map.put("receiveOrderName", map.get("receiveOrderEnName"));
        }
        if ("1".equals(map.get("isConsolidation"))) {
            map.put("containerType", "LCL*LCL");
        } else {
            map.put("containerType", map.get("containerType") + "*" + map.get("containerBoxAmount"));
        }
        map.remove("goodsEnName");
        map.remove("receiveOrderEnName");
        map.remove("isConsolidation");
        map.remove("containerBoxAmount");
        return map;
    }
}
