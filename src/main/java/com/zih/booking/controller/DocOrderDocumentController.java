package com.zih.booking.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.dao.BusiShippingorderMapper;
import com.zih.booking.dao.DocLattersMapper;
import com.zih.booking.dao.DocOrderDocumentMapper;
import com.zih.booking.dao.DocOrderMapper;
import com.zih.booking.model.*;
import com.zih.booking.request.DocConfirmVO;
import com.zih.booking.request.DocumentFileRequest;
import com.zih.booking.response.*;
import com.zih.booking.service.DocOrderDocumentService;
import com.zih.booking.service.LadingBillService;
import com.zih.booking.service.TrackTwoLevelService;
import com.zih.booking.system.config.rabbitmq.GuanwuSystemMq;
import com.zih.booking.system.config.rabbitmq.TiDanSystemMq;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.system.vo.Result;
import com.zih.booking.utils.*;
import com.zih.booking.vo.ProblemFileVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 单证文件表 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-01-07
 */
@Slf4j
@RestController
@RequestMapping("/docOrderDocument")
@Api(tags = " 单证中心", description = "单证中心 --  相关接口")
public class DocOrderDocumentController {

    @Resource
    private ResourceLoader resourceLoader;
    @Autowired
    private DocOrderDocumentService docOrderDocumentService;
    @Autowired
    private BusiShippingorderMapper busiShippingorderMapper;
    @Autowired
    private DocOrderDocumentMapper docOrderDocumentMapper;
    @Autowired
    private DocOrderMapper docOrderMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private LadingBillService ladingBillService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private TrackTwoLevelService trackTwoLevelService;

    @Autowired
    private DocLattersMapper docLattersMapper;

    @ApiOperation("列表展示已订舱货物信息")
    @GetMapping("/list")
    public ApiResultI18n getList(DocumentFileRequest documentFileRequest) {
        List<String> clients = tokenService.getClientIds(ServletUtils.getRequest());
        documentFileRequest.setClientIds(clients);
        String khId = tokenService.getKhId(ServletUtils.getRequest());
        if(null != khId && khId!=""){
            documentFileRequest.setKhId(Long.valueOf(khId));
            clients.add(tokenService.getKhClientId(ServletUtils.getRequest()));
            documentFileRequest.setClientIds(clients);
        }
        Page<DocumentFileResponse> page = new Page<>(documentFileRequest.getPage(), documentFileRequest.getLimit());
        page.setRecords(busiShippingorderMapper.selectDocList(page, documentFileRequest));
        return ApiResultI18n.success(page, documentFileRequest.getLanguage());
    }

    @ApiOperation(value = "多文件上传", notes = "多文件上传 ")
    @PostMapping("/uploadDoc")
    public Result upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) {
        String path = "docOrderDocument";
        return new Result(Upload.filesUpload(file, path, request));
    }

    @PostMapping("/deleteDoc")
    public Result upload(@RequestParam("url") String url) {
        return new Result(Upload.deleteFile(url));
    }

    @PostMapping("/add")
    @ApiOperation("上传单证")
    public Result uploadFile(@RequestBody DocConfirmVO docConfirmVO) {
        String lbId = docConfirmVO.getLbId();
        String fileTypeKey = docConfirmVO.getFileTypeKey();
        String orderNumber = docConfirmVO.getOrderNumber();
        String orderId = docConfirmVO.getOrderId();
        String xianghao = docConfirmVO.getXianghao();
        String confirmRemark = docConfirmVO.getConfirmRemark();
        Set<ProblemFileVo> set = new HashSet<>(Arrays.asList(docConfirmVO.getUrls()));
        ProblemFileVo[] urls = set.toArray(new ProblemFileVo[set.size()]);
        String classEastandwest = busiShippingorderMapper.selectById(orderId).getClassEastandwest();
        List<DocOrderDocument> docs = new ArrayList<>();
        Date nowDate = new Date();
        if (fileTypeKey.equals("letter_guarantee_filec")) {
            LadingBill ladingBill = ladingBillService.selectOne(new EntityWrapper<LadingBill>().eq("order_id", orderId));
            //电放保函 -- 通知大口岸 关务
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("lb_number", orderNumber);
            jsonObject.put("confirmRemark", confirmRemark);
            jsonObject.put("lb_state", 3);
            if (null != xianghao) {
                jsonObject.put("lb_container", xianghao);
            }
            jsonObject.put("lb_id", ladingBill.getLbId());
            String urlString = "";
            String urlNameString = "";
            for (int i = 0; i < urls.length; i++) {
                if (i == 0) {
                    urlString = urls[0].getUrl();
                    urlNameString = urls[0].getFileName();
                } else {
                    urlString = urlString + ";" + urls[i].getUrl();
                    urlNameString = urlNameString + ";" + urls[i].getFileName();
                }
                /*保存单证信息*/
                DocOrderDocument doc = new DocOrderDocument();
                doc.setFileUrl(urls[i].getUrl());
                doc.setFileName(urls[i].getFileName());
                doc.setOrderNumber(orderNumber);
                doc.setFileTypeKey(fileTypeKey);
                doc.setOrderId(orderId);
                doc.setFormSystem("1");
                doc.setUploadTime(nowDate);
                doc.setCreateTime(nowDate);
                doc.setConfirmRemark(confirmRemark);
                docs.add(doc);
            }
            jsonObject.put("lb_url1", urlString);
            jsonObject.put("lb_url1name", urlNameString);
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend(TiDanSystemMq.TD_DIRECT_DFBH_EXCHANGE, TiDanSystemMq.TD_DFBH_ROUTINGKEY, jsonObject, correlationData);
        } else {
            for (ProblemFileVo url : urls) {
                DocOrderDocument doc = new DocOrderDocument();
                doc.setFileUrl(url.getUrl());
                doc.setFileName(url.getFileName());
                doc.setOrderNumber(orderNumber);
                doc.setFileTypeKey(fileTypeKey);
                doc.setOrderId(orderId);
                doc.setFormSystem("1");
                doc.setUploadTime(nowDate);
                doc.setCreateTime(nowDate);
                doc.setConfirmRemark(confirmRemark);
                if (fileTypeKey.equals("customs_apply_filec")) {//报关材料 通知关务系统  新增二级运踪节点 回程是清关 去程16，回程19
                    TrackTwoLevel trackTwoLevel = new TrackTwoLevel();
                    trackTwoLevel.setOrderId(orderId);
                    trackTwoLevel.setNameZh("单据已提供");
                    trackTwoLevel.setNameEn("The documents have been provided");
                    trackTwoLevel.setIsCustom("1");
                    trackTwoLevel.setState(1);
                    trackTwoLevel.setTime(nowDate.toLocaleString());
                    trackTwoLevel.setCreateTime(nowDate);
                    if (classEastandwest.equals("0")) {//去程
                        trackTwoLevel.setSort(16);
                        trackTwoLevelService.delete(new EntityWrapper<TrackTwoLevel>().eq("sort",16).eq("order_id",orderId));
                        trackTwoLevelService.insert(trackTwoLevel);
                    }/* else {
                        trackTwoLevel.setSort(19);
                    }
                    trackTwoLevelService.insert(trackTwoLevel);*/
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("file_type_key", "customs_apply_filec");
                    jsonObject.put("order_id", orderId);
                    jsonObject.put("order_number", orderNumber);
                    jsonObject.put("filename", url.getFileName());
                    jsonObject.put("url", url.getUrl());
                    jsonObject.put("type", "insert");
                    jsonObject.put("confirmRemark", confirmRemark);
                    CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
                    rabbitTemplate.convertAndSend(GuanwuSystemMq.GW_TOPIC_EXCHANGE, GuanwuSystemMq.GW_ROUTINGKEY, jsonObject, correlationData);
                }
                //随车文件 -- 发送大口岸系统/关务系统、国外场站系统
                if (doc.getFileTypeKey().equals("follow_vehicle_filec")) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("type", "insert");
                    jsonObject.put("filename", url.getFileName());
                    jsonObject.put("order_id", orderId);
                    jsonObject.put("order_number", orderNumber);
                    jsonObject.put("url", url.getUrl());
                    jsonObject.put("confirmRemark", confirmRemark);
                    CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
                    rabbitTemplate.convertAndSend(TiDanSystemMq.CLIENT_TOPIC_EXCHANGE, TiDanSystemMq.FOLLOW_VEHICLE_ROUTINGKEY, jsonObject, correlationData);
                }
                //客户自装箱装箱明细 -- 发送 拼箱系统、关务系统
                if (doc.getFileTypeKey().equals("boxing_list_filec")) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("type", "insert");
                    jsonObject.put("filename", url.getFileName());
                    jsonObject.put("order_id", orderId);
                    jsonObject.put("url", url.getUrl());
                    jsonObject.put("confirmRemark", confirmRemark);
                    CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
                    rabbitTemplate.convertAndSend(TiDanSystemMq.ORDER_BOXING_LISTC_TOPIC_EXCHANGE, TiDanSystemMq.ORDER_BOXING_LISTC_ROUTINGKEY, jsonObject, correlationData);
                }
                //报关材料正本 --发送关务系统
                if (doc.getFileTypeKey().equals("declare_customs_formal_file")) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("type", "insert");
                    jsonObject.put("filename", url.getFileName());
                    jsonObject.put("order_id", orderId);
                    jsonObject.put("url", url.getUrl());
                    jsonObject.put("confirmRemark", confirmRemark);
                    CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
                    rabbitTemplate.convertAndSend(GuanwuSystemMq.GW_TOPIC_EXCHANGE, GuanwuSystemMq.GW_ROUTINGKEY_FORMAL, jsonObject, correlationData);
                }
                // 船级证书--发送箱行亚欧、箱管系统、关务
                if (doc.getFileTypeKey().equals("certificate_of_classification")) {
                    doc.setContainerNo(xianghao);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("type", "insert");
                    jsonObject.put("filename", url.getFileName());
                    jsonObject.put("order_id", orderId);
                    jsonObject.put("container_no", xianghao);
                    jsonObject.put("url", url.getUrl());
                    jsonObject.put("confirmRemark", confirmRemark);
                    CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
                    rabbitTemplate.convertAndSend(TiDanSystemMq.CLIENT_TOPIC_EXCHANGE, TiDanSystemMq.CERTIFICATE_BINDING, jsonObject, correlationData);
                }
                docs.add(doc);
            }
        }
        /*更新单证状态*/
        DocOrder docOrder = new DocOrder();
        docOrder.setOrderId(orderId);
        docOrder.setFileTypeKey(fileTypeKey);
        docOrder.setActualSupply(1);
        docOrder.setActualSupplyTime(nowDate);
        docOrderMapper.updateActualSupply(docOrder);
        return new Result(docOrderDocumentService.insertBatch(docs));
    }

    @GetMapping("/getNode")
    @ApiOperation("节点列表")
    public ApiResultI18n getNode(@RequestParam String orderId, String language) {
        String[] fileTypeKey = getFileTypeKey(orderId);
        List<String> wareIdList = Arrays.asList(fileTypeKey);
//        List<DocNode> nodes = docOrderDocumentMapper.selectNodeList(orderId, wareIdList);
        List<DocNode> nodes = docOrderDocumentMapper.selectNewNodeList(orderId, wareIdList);
        List<DocNodeResponse> responses = new ArrayList<>();
        Integer isChoose = 0;
        Integer isSubmit = 0;
        List<DocOrderDocument> list = docOrderDocumentService.selectList(new EntityWrapper<DocOrderDocument>().eq("order_id", orderId).eq("file_type_key", "order_notice_file"));
        if (list.size() != 0) {
            isChoose = list.get(0).getIsChoose();
            isSubmit = list.get(0).getIsSubmit();
        }
        for (DocNode docNode : nodes) {
            if (docNode.getFileTypeKey().equals("lading_bill_draft_file") && null == docNode.getLbState()) { //提单草单 没有发送时不展示
                continue;
            }
//            if (docNode.getFileTypeKey().equals("letter_guarantee_filec")  && (null == docNode.getLbState()|| docNode.getLbState()!=6)) {// 电放保函 提单草单未审核通过时不展示
//                continue;
//            }
            //来源不是客户端的 并且没有上传的节点 暂不展示
            if (!docNode.getFileTypeKey().equals("lading_bill_draft_file") && !docNode.getFileFrom().equals("1") && docOrderDocumentService.selectList(new EntityWrapper<DocOrderDocument>().eq("order_id", orderId).eq("file_type_key", docNode.getFileTypeKey())).size() == 0) {
                continue;
            }
            if (docNode.getFileTypeKey().equals("letter_guarantee_filec")) {
                if (isChoose == 4) {
                    continue;
                }
            }
            DocNodeResponse response = new DocNodeResponse();
            List<DocFile> docFiles = new ArrayList<>();
            BeanUtils.copyProperties(docNode, response);
            response.setIsChoose(isChoose);
            response.setIsSubmit(isSubmit);
            if (language.equalsIgnoreCase("en")) {
                docNode.setFileTypeText(docNode.getFileTypeTextEn());
            }
            //查询已上传的文件
            boolean hasLatters = false;
            List<DocOrderDocument> files = docOrderDocumentService.selectList(new EntityWrapper<DocOrderDocument>().eq("order_id", orderId).eq("file_type_key", response.getFileTypeKey()));
            if (files.size() != 0) {
                for (DocOrderDocument docOrderDocument : files) {
                    DocFile file = new DocFile();
                    BeanUtils.copyProperties(docOrderDocument, file);
                    if (docOrderDocument.getFileTypeKey().equals("letter_guarantee_filec")) {
                        // 获取
                        file.setEnabChange(docNode.getLbLetterState());
                        hasLatters = true;
                    }
                    docFiles.add(file);
                }
            }
            if (!hasLatters) {
                // 电放保函的情况下，查询已维护的长期电放保函
                List<DocClientLatters> clientLatters = new ArrayList<>();
                if (docNode.getFileTypeKey().equals("letter_guarantee_filec")) {
                    clientLatters = docLattersMapper.selectLattersByOrderId(orderId);
                    response.setHasLatters(0);
                    for (DocClientLatters latter : clientLatters) {
                        DocFile file = new DocFile();
                        file.setFileUrl(latter.getLatterUrl());
                        file.setFileName(latter.getLatterName());
                        docFiles.add(file);
                    }
                }
            }
            /*处理文件url连接*/
            for (DocFile f : docFiles) {
                f.setFileUrl(DocUrlHandle.urlHandle(f.getFileUrl()));
            }
            if(!"lading_bill_draft_file".equals(docNode.getFileTypeKey()) && !"letter_guarantee_filec".equals(docNode.getFileTypeKey())){
                response.setLbFaildReason(null);
                response.setLbLetterFaildReason(null);
            }
            response.setFiles(docFiles);
            responses.add(response);
        }
        return ApiResultI18n.success(responses, language);
    }


    @ApiOperation(value = "提单草单详情", notes = "星期六  ")
    @GetMapping("/tdDetail")
    public Result tdDetail(@RequestParam String orderId) {
        return new Result(ladingBillService.selectOne(new EntityWrapper<LadingBill>().eq("order_id", orderId)));
    }


    @ApiOperation(value = "提单草单保存(发送子系统)", notes = "星期六  ")
    @PostMapping("/tdSave") //通知大口岸  +   关务
    public Result tdSave(@RequestBody LadingBill ladingBill) {
        ladingBill.setUpdateTime(new Date());
        DocOrderDocument docOrderDocument = new DocOrderDocument();
        docOrderDocument.setUploadTime(new Date());
        docOrderDocument.setIsSubmit(1);
        docOrderDocumentService.update(docOrderDocument, new EntityWrapper<DocOrderDocument>().eq("order_id", ladingBill.getOrderId()));
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(TiDanSystemMq.TD_DIRECT_RESPONSE_EXCHANGE, TiDanSystemMq.TD_RESPONSE_ROUTINGKEY, ladingBill, correlationData);
        return new Result(ladingBillService.updateById(ladingBill));
    }

    @ApiOperation(value = "提单草单保存", notes = "星期六  ")
    @PostMapping("/ladingSave")
    public Result ladingSave(@RequestBody LadingBill ladingBill) {
        ladingBill.setUpdateTime(new Date());
        ladingBill.setLbState(null);
        ladingBill.setLbLetterState(null);
        return new Result(ladingBillService.updateById(ladingBill));
    }


    @ApiOperation(value = "关务报关草单确认", notes = "报关草单确认  ")
    @GetMapping("/gwConfirm") //通知  关务
    public Result gwConfirm(DocFile file, String orderNumber, String orderId) {
        DocOrderDocument docOrderDocument = new DocOrderDocument();
        docOrderDocument.setConfirmRemark(file.getConfirmRemark());
        docOrderDocument.setIsConfirm(file.getIsConfirm());
        JSONObject object = new JSONObject();
        object.put("isConfirm", file.getIsConfirm());
        object.put("confirmRemark", file.getConfirmRemark());
        object.put("fileUrl", file.getFileUrl());
        object.put("orderId", orderId);
        object.put("orderNumber", orderNumber);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(GuanwuSystemMq.GW_TOPIC_EXCHANGE, GuanwuSystemMq.GW_CONFIRM_KRY, object, correlationData);
        return new Result(docOrderDocumentService.update(docOrderDocument, new EntityWrapper<DocOrderDocument>().eq("file_url", file.getFileUrl())));
    }

    @GetMapping("zhengbenGive")
    @ApiOperation("选择正本")//大口岸
    public Result zhengbenGive(String lbId, String orderId, @RequestParam String orderNumber, String xianghao, @RequestParam Integer isChoose) {
        DocOrderDocument docOrderDocument = new DocOrderDocument();
        docOrderDocument.setUploadTime(new Date());
        docOrderDocument.setIsChoose(isChoose);
        docOrderDocumentService.update(docOrderDocument, new EntityWrapper<DocOrderDocument>().eq("order_id", orderId));
        if (isChoose == 4) {
            //消息队列
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("order_id", orderId);
            jsonObject.put("lb_id", lbId);
            jsonObject.put("lb_number", orderNumber);
            jsonObject.put("lb_state", isChoose);
            if (null != xianghao) {
                jsonObject.put("lb_container", xianghao);
            }
            jsonObject.put("lb_url1", "");
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend(TiDanSystemMq.TD_DIRECT_DFBH_EXCHANGE, TiDanSystemMq.TD_DFBH_ROUTINGKEY, jsonObject, correlationData);
            log.info("选择正本发送消息" + jsonObject.toJSONString());
        }
        return new Result(true);
    }


    @ApiOperation(value = "提货时间", notes = "提货时间  ")
    @GetMapping("/thTime")
    public Result thTime(@RequestParam String orderId) {
        BusiShippingorder busiShippingorder = busiShippingorderMapper.selectById(orderId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shipOrderUnloadtime", busiShippingorder.getShipOrderUnloadtime());//提货时间
        jsonObject.put("shipOrderUnloadcontacts", busiShippingorder.getShipOrderUnloadcontacts());//提货联系人
        jsonObject.put("shipOrderUnloadway", busiShippingorder.getShipOrderUnloadway());//提货联系方式
        return new Result(jsonObject);
    }

    @ApiOperation(value = "提货时间修改", notes = "提货时间修改  ")
    @PostMapping("/updateThTime")
    public Result updateThTime(@RequestParam String orderId, @RequestParam Date shipOrderUnloadtime, @RequestParam String shipOrderUnloadcontacts, @RequestParam String shipOrderUnloadway) {
        BusiShippingorder busiShippingorder = new BusiShippingorder();
        busiShippingorder.setOrderId(orderId);
        busiShippingorder.setShipOrderUnloadtime(shipOrderUnloadtime);//提货时间
        busiShippingorder.setShipOrderUnloadcontacts(shipOrderUnloadcontacts);//提货联系人
        busiShippingorder.setShipOrderUnloadway(shipOrderUnloadway);//提货联系方式
        return new Result(busiShippingorderMapper.updateById(busiShippingorder));
    }

    private String[] getFileTypeKey(String orderId) {//classEastandwest 0为去程 1为回程 IsConsolidation 0整柜 1拼箱  委托ZIH提货 0是 1否
        BusiShippingorder busiShippingorder = busiShippingorderMapper.selectById(orderId);
        String shipOrderBinningway = busiShippingorder.getShipOrderBinningway();//委托ZIH提货 0是 1否
        String receiveOrderIspart = busiShippingorder.getReceiveOrderIspart();//是否由ZIH代理送货  0否 1是
        String lineType = busiShippingorder.getLineTypeid();//线路
        //shipOrderBinningway=0  receiveOrderIspart=1 都委托
        if (busiShippingorder.getClassEastandwest().equals("0") && busiShippingorder.getIsConsolidation().equals("0") && shipOrderBinningway.equals("0") && receiveOrderIspart.equals("1")) {//去程整柜 委托ZIH提货  由ZIH代理送货
            String[] fileTypeKey = {"order_notice_file", "declare_customs_draft_file", "pcik_container_file", "pick_goods_file", "boxing_list_filec", "boxing_scheme_file",
                    "customs_apply_filec", "declare_customs_formal_file", "follow_vehicle_filec", "declare_customs_file", "lading_bill_draft_file",
                    "smgs_waybill_file", "letter_guarantee_filec", "lading_bill_formal_file", "receipt_goods_file"};
            return handlerFileTypes(fileTypeKey, lineType);
        }
        if (busiShippingorder.getClassEastandwest().equals("0") && busiShippingorder.getIsConsolidation().equals("0") && shipOrderBinningway.equals("0") && receiveOrderIspart.equals("0")) {//去程整柜 委托ZIH提货  不由ZIH代理送货
            String[] fileTypeKey = {"order_notice_file", "declare_customs_draft_file", "pcik_container_file", "return_con_file", "pick_goods_file", "boxing_list_filec", "boxing_scheme_file",
                    "customs_apply_filec", "declare_customs_formal_file", "follow_vehicle_filec", "declare_customs_file", "lading_bill_draft_file",
                    "smgs_waybill_file", "letter_guarantee_filec", "lading_bill_formal_file", "receipt_goods_file"};
            return handlerFileTypes(fileTypeKey, lineType);
        }
        if (busiShippingorder.getClassEastandwest().equals("0") && busiShippingorder.getIsConsolidation().equals("0") && !shipOrderBinningway.equals("0")) {//去程整柜 不 委托ZIH提货
            String[] fileTypeKey = {"order_notice_file", "declare_customs_draft_file", "pcik_container_file", "return_con_file", "boxing_list_filec", "boxing_scheme_file",
                    "customs_apply_filec", "declare_customs_formal_file", "follow_vehicle_filec", "declare_customs_file", "lading_bill_draft_file",
                    "smgs_waybill_file", "letter_guarantee_filec", "lading_bill_formal_file", "receipt_goods_file"};
            return handlerFileTypes(fileTypeKey, lineType);

        }
        if (busiShippingorder.getClassEastandwest().equals("0") && busiShippingorder.getIsConsolidation().equals("1") && shipOrderBinningway.equals("0")) {//去程拼箱 委托ZIH提货
            String[] fileTypeKey = {"order_notice_file", "declare_customs_draft_file", "pick_goods_file",
                    "customs_apply_filec", "declare_customs_formal_file", "follow_vehicle_filec", "declare_customs_file", "lading_bill_draft_file",
                    "smgs_waybill_file", "letter_guarantee_filec", "lading_bill_formal_file", "receipt_goods_file"};
            return handlerFileTypes(fileTypeKey, lineType);
        }
        if (busiShippingorder.getClassEastandwest().equals("0") && busiShippingorder.getIsConsolidation().equals("1") && !shipOrderBinningway.equals("0")) {//去程拼箱 不 委托ZIH提货
            String[] fileTypeKey = {"order_notice_file", "declare_customs_draft_file",
                    "customs_apply_filec", "declare_customs_formal_file", "follow_vehicle_filec", "declare_customs_file", "lading_bill_draft_file",
                    "smgs_waybill_file", "letter_guarantee_filec", "lading_bill_formal_file", "receipt_goods_file"};
            return handlerFileTypes(fileTypeKey, lineType);
        }
        // "pull_goods_file",  回程不显示提货时间
        if (busiShippingorder.getClassEastandwest().equals("1") && busiShippingorder.getIsConsolidation().equals("0") && shipOrderBinningway.equals("0") && receiveOrderIspart.equals("1")) {//回城整柜  委托ZIH提货  由ZIH代理送货
            String[] fileTypeKey = {"order_notice_file", "declare_customs_draft_file", "boxing_list_filec", "boxing_scheme_file",
                    "follow_vehicle_filec", "customs_apply_filec", "declare_customs_formal_file", "declare_customs_file", "lading_bill_draft_file",
                    "smgs_waybill_file", "clearance_paper_file", "letter_guarantee_filec", "lading_bill_formal_file", "receipt_goods_file"};
            return handlerFileTypes(fileTypeKey, lineType);
        }
        // "pull_goods_file",  回程不显示提货时间
        if (busiShippingorder.getClassEastandwest().equals("1") && busiShippingorder.getIsConsolidation().equals("0") && receiveOrderIspart.equals("1")) {//回城整柜      不委托ZIH提货   由ZIH代理送货
            String[] fileTypeKey = {"order_notice_file", "declare_customs_draft_file", "pcik_container_file", "return_con_file", "boxing_list_filec", "boxing_scheme_file",
                    "follow_vehicle_filec", "customs_apply_filec", "declare_customs_formal_file", "declare_customs_file", "lading_bill_draft_file",
                    "smgs_waybill_file", "clearance_paper_file", "letter_guarantee_filec", "lading_bill_formal_file", "receipt_goods_file"};
            return handlerFileTypes(fileTypeKey, lineType);
        }
        if (busiShippingorder.getClassEastandwest().equals("1") && busiShippingorder.getIsConsolidation().equals("0") && receiveOrderIspart.equals("0")) {//回城整柜   不由ZIH代理送货
            String[] fileTypeKey = {"order_notice_file", "declare_customs_draft_file", "pcik_container_file", "return_con_file", "boxing_list_filec", "boxing_scheme_file",
                    "follow_vehicle_filec", "customs_apply_filec", "declare_customs_formal_file", "declare_customs_file", "lading_bill_draft_file",
                    "smgs_waybill_file", "clearance_paper_file", "letter_guarantee_filec", "lading_bill_formal_file", "receipt_goods_file"};
            return handlerFileTypes(fileTypeKey, lineType);
        }
        // "pull_goods_file",  回程不显示提货时间
        if (busiShippingorder.getClassEastandwest().equals("1") && busiShippingorder.getIsConsolidation().equals("1") && receiveOrderIspart.equals("1")) {//回城拼箱  由ZIH代理送货
            String[] fileTypeKey = {"order_notice_file", "declare_customs_draft_file", "follow_vehicle_filec", "customs_apply_filec", "declare_customs_formal_file",
                    "declare_customs_file", "lading_bill_draft_file", "letter_guarantee_filec", "smgs_waybill_file", "lading_bill_formal_file", "receipt_goods_file"};
            return handlerFileTypes(fileTypeKey, lineType);
        }
        if (busiShippingorder.getClassEastandwest().equals("1") && busiShippingorder.getIsConsolidation().equals("1") && receiveOrderIspart.equals("0")) {//回城拼箱 不由ZIH代理送货
            String[] fileTypeKey = {"order_notice_file", "declare_customs_draft_file", "follow_vehicle_filec", "customs_apply_filec", "declare_customs_formal_file",
                    "declare_customs_file", "lading_bill_draft_file", "letter_guarantee_filec", "smgs_waybill_file", "lading_bill_formal_file", "receipt_goods_file"};
            return handlerFileTypes(fileTypeKey, lineType);
        } else {
            return null;
        }
    }

    /*中亚线路增加船级证书上传*/
    public static String[] handlerFileTypes(String[] array, String lineType) {
//        if(lineType.equals("2")){
//            String[] newArray = Arrays.copyOf(array, array.length + 1);
//            newArray[newArray.length-1] = "certificate_of_classification";
//            return newArray;
//        }
        return array;
    }

    @GetMapping("/latters/download")
    @ApiOperation(value = "下载    type  类型   0 长期   1 单次 ")
    public void downModel(HttpServletRequest request, HttpServletResponse response, String language, Integer type) {
        String filename = "电放保函";
        String path = "static/latters/";
        if (type == 0) {
            filename = "长期" + filename;
            if ("zh".equals(language)) {
                path = path + "中文版长期电放保函模板.doc";
            } else {
                path = path + "英文版长期电放保函模板.doc";
            }
        } else {
            filename = "单次" + filename;
            if ("zh".equals(language)) {
                path = path + "中文版单次电放保函模板.doc";
            } else {
                path = path + "英文版单次电放保函模板.doc";
            }
        }
        ExcelUtil.downloadThymeleaf(resourceLoader, filename, path, request, response);
    }

    @GetMapping("/ladingBillExportPdf")
    @ApiOperation("提单草单word导出 ")
    public void qrCode2(@RequestParam String orderId, HttpServletResponse response) throws Exception {

        LadingBill ladingBill = ladingBillService.selectOne(new EntityWrapper<LadingBill>().eq("order_id", orderId));
        Map<String,Object> dataMap = new HashMap<String, Object>();
        dataMap.put("lb1", StringUtils.isNotEmpty(ladingBill.getLb1())?charHandle(ladingBill.getLb1()):"");
        dataMap.put("lb2", StringUtils.isNotEmpty(ladingBill.getLb2())?charHandle(ladingBill.getLb2()):"");
        dataMap.put("lb3", StringUtils.isNotEmpty(ladingBill.getLb3())?charHandle(ladingBill.getLb3()):"");
        dataMap.put("lb4", StringUtils.isNotEmpty(ladingBill.getLb4())?charHandle(ladingBill.getLb4()):"");
        dataMap.put("lb5", StringUtils.isNotEmpty(ladingBill.getLb5())?charHandle(ladingBill.getLb5()):"");
        dataMap.put("lb6", StringUtils.isNotEmpty(ladingBill.getLb6())?charHandle(ladingBill.getLb6()):"");
        dataMap.put("lb7", StringUtils.isNotEmpty(ladingBill.getLb7())?charHandle(ladingBill.getLb7()):"");
        dataMap.put("lb8", StringUtils.isNotEmpty(ladingBill.getLb8())?charHandle(ladingBill.getLb8()):"");
        dataMap.put("lb10", StringUtils.isNotEmpty(ladingBill.getLb10())?charHandle(ladingBill.getLb10()):"");
        dataMap.put("lb11", StringUtils.isNotEmpty(ladingBill.getLb11())?charHandle(ladingBill.getLb11()):"");
        dataMap.put("lb9", StringUtils.isNotEmpty(ladingBill.getLb9())?charHandle(ladingBill.getLb9()):"");
        dataMap.put("lb12", StringUtils.isNotEmpty(ladingBill.getLb12())?charHandle(ladingBill.getLb12()):"");
        dataMap.put("lb131", StringUtils.isNotEmpty(ladingBill.getLb131())?charHandle(ladingBill.getLb131()):"");
        dataMap.put("lb132", StringUtils.isNotEmpty(ladingBill.getLb132())?charHandle(ladingBill.getLb132()):"");
        dataMap.put("lb14", StringUtils.isNotEmpty(ladingBill.getLb14())?charHandle(ladingBill.getLb14()):"");
        dataMap.put("lb151", StringUtils.isNotEmpty(ladingBill.getLb151())?charHandle(ladingBill.getLb151()):"");
        dataMap.put("lb152", StringUtils.isNotEmpty(ladingBill.getLb152())?charHandle(ladingBill.getLb152()):"");
        dataMap.put("lb153", StringUtils.isNotEmpty(ladingBill.getLb153())?charHandle(ladingBill.getLb153()):"");
        dataMap.put("lb16", StringUtils.isNotEmpty(ladingBill.getLb16())?charHandle(ladingBill.getLb16()):"");
        dataMap.put("lb19", StringUtils.isNotEmpty(ladingBill.getLb19())?charHandle(ladingBill.getLb19()):"");
        dataMap.put("lb20", StringUtils.isNotEmpty(ladingBill.getLb20())?charHandle(ladingBill.getLb20()):"");
        dataMap.put("lbmark", StringUtils.isNotEmpty(ladingBill.getLbMark())?ladingBill.getLbMark() :"");
        dataMap.put("lbNumber", StringUtils.isNotEmpty(ladingBill.getLbNumber())?ladingBill.getLbNumber():"");
        //Configuration 用于读取ftl文件
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        //指定路径的第一种方式（根据某个类的相对路径指定）
        configuration.setClassForTemplateLoading(this.getClass(), "/template");
        //输出文档路径及名称
        File outFile = new File("C:/pdf/draft.doc");

        //以utf-8的编码读取ftl文件
        Template template = configuration.getTemplate("draft (2).ftl", "utf-8");
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
        template.process(dataMap, out);
        out.close();

        // 下载本地文件
        String fileName = "draft-" + ladingBill.getLbNumber() + ".doc"; // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("C:/pdf/draft.doc");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public String charHandle(String str){
        str = str.replace("<", "&lt;");
        str = str.replace(">", "&gt;");
        str = str.replace("&", "&amp;");
        str = str.replace("'", "&apos;");
        str = str.replaceAll("\n", "<w:br/>");
        return str;
    }

    @GetMapping("/ladingBillWordExport")
    @ApiOperation("提单草单word导出 ")
    public void ladingBillWordExport(@RequestParam String orderId, HttpServletResponse response) throws Exception {
        LadingBill ladingBill = ladingBillService.selectOne(new EntityWrapper<LadingBill>().eq("order_id", orderId));
        String path = WordExportUtil.TidanDraftWordExport(ladingBill);
        // 读到流中
        InputStream inStream = new FileInputStream(path);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + "draftWord" + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }finally {
            new Thread(()->{
                try {
                    Thread.sleep(15000);
                    File paths = new File(path);
                    if (paths.exists()) {
                        paths.delete();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

