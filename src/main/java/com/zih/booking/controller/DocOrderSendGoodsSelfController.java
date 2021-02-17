package com.zih.booking.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zih.booking.model.*;
import com.zih.booking.request.DocSendGoodsVO;
import com.zih.booking.service.*;
import com.zih.booking.system.config.rabbitmq.SendGoodSelfMq;
import com.zih.booking.system.vo.Result;
import com.zih.booking.utils.ContainerCheck;
import com.zih.booking.utils.UUIDUtils;
import com.zih.booking.vo.ProblemFileVo;
import com.zih.booking.vo.ProblemFileVo2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.*;

/**
 * @Description : 自送货接口
 * 客户上传自备箱装箱照，存放数据库格式为  箱号，铅封号，装箱照url拼接
 * @Author : wangzhichao
 * @Date: 2020-12-17 17:21
 */
@Slf4j
@RestController
@RequestMapping("/docSendGoodsSelf")
public class DocOrderSendGoodsSelfController {

    @Autowired
    private DocOrderDocumentService docOrderDocumentService;

    @Autowired
    private DocOrderService docOrderService;

    @Autowired
    private BusiShippingorderService busiShippingorderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TrackGoodsStatusService trackGoodsStatusService;
    @Autowired
    BusiZyInfoService busiZyInfoService;

    private static String server_ip;
    private static String uploadFolder;

    @Value("${file.server_ip}")
    public void setSystemUrl(String systemUrl) {
        server_ip = systemUrl;
    }

    @Value("${file.uploadFolder}")
    public void setFolder(String folder) {
        uploadFolder = folder;
    }

    /**
     * 根据箱号
     *
     * @param orderId
     * @param containerCount
     * @return
     */
    @GetMapping("/getConList")
    public List<DocSendGoodsVO> getList(String orderId, String orderNumber, Integer containerCount) {
        String classEastAndWest = busiShippingorderService.getClassEastAndWest(orderId);
        List<DocOrderDocument> documentList = docOrderDocumentService.selectList(new EntityWrapper<DocOrderDocument>()
                .eq("order_id", orderId)
                .eq("file_type_key", "boxing_photo_file")
                .eq("form_system", "1"));
        List<DocSendGoodsVO> list = new ArrayList<>();
        for (int i = 0; i < containerCount; i++) {
            DocSendGoodsVO goodsVO = new DocSendGoodsVO();
            goodsVO.setOrderId(orderId);
            goodsVO.setOrderNumber(orderNumber);
            goodsVO.setClassEastAndWest(classEastAndWest);
            if (documentList.size() > i) {
                goodsVO.setBoxingphotoFail(documentList.get(i).getBoxingphotoFail());
                goodsVO.setBoxingphotoStatus(documentList.get(i).getBoxingphotoStatus());
                goodsVO.setConSealFail(documentList.get(i).getContainerFail());
                goodsVO.setConSealStatus(documentList.get(i).getContainerStatus());
                goodsVO.setDocId(documentList.get(i).getId());
                goodsVO.setSealNumber(documentList.get(i).getSealnumber());
                goodsVO.setContainerNo(documentList.get(i).getContainerNo());
                if(StringUtils.isNotEmpty(documentList.get(i).getFileUrl()) && StringUtils.isNotEmpty(documentList.get(i).getFileName())){
                    String[] fileUrlArr = documentList.get(i).getFileUrl().split(";");
                    String[] fileNameArr = documentList.get(i).getFileName().split(";");
                    List<ProblemFileVo> urls = new ArrayList<>();
                    for (int j = 0; j < fileNameArr.length; j++) {
                        ProblemFileVo url = new ProblemFileVo();
                        url.setFileName(fileNameArr[j]);
                        url.setUrl(fileUrlArr[j]);
                        urls.add(url);
                    }
                    goodsVO.setUrlList(urls);
                }
            }
            list.add(goodsVO);
        }
        return list;
    }

    /**
     * 保存箱号 铅封号
     *
     * @param request
     * @return
     */
    @PostMapping("saveConAndSeal")
    @Transactional
    public Result uploadBoxingPhoto(@RequestBody DocSendGoodsVO request) {
        log.info(request.toString());
        if (!ContainerCheck.checkDigit(request.getContainerNo())) {
            return new Result(0, "con error");
        } else if (StringUtils.isEmpty(request.getContainerNo())) {
            return new Result(0, "con error");
        } else  {
            Date nowDate = new Date();
            DocOrderDocument document = new DocOrderDocument();
            document.setId(request.getDocId());
            document.setOrderId(request.getOrderId());
            document.setOrderNumber(request.getOrderNumber());
            document.setFormSystem("1");
            document.setUploadTime(nowDate);
            document.setFileTypeKey("boxing_photo_file");
            document.setContainerNo(request.getContainerNo().trim());
            document.setSealnumber(request.getSealNumber().trim());
            if(request.getUrlList().size()>0) {
                String[] fileNames = request.getUrlList().stream()
                        .map(item -> item.getFileName()).toArray(String[]::new);
                for (int i = 0; i < fileNames.length; i++) {
                    String fileType = fileNames[i].substring(fileNames[i].lastIndexOf("."));
                    fileNames[i] = request.getOrderNumber() + "_" + request.getContainerNo() + "_" + i + fileType;
                    request.getUrlList().get(i).setFileName(fileNames[i]);
                }
                String[] fileUrls = request.getUrlList().stream().map(item -> item.getUrl()).toArray(String[]::new);
                document.setFileName(StringUtils.join(fileNames, ";"));
                document.setFileUrl(StringUtils.join(fileUrls, ";"));
                // 修改托书单证表状态
                DocOrder docOrder = new DocOrder();
                docOrder.setActualSupply(1);
                docOrder.setActualSupplyTime(nowDate);
                docOrder.setOrderId(request.getOrderId());
                docOrder.setFileTypeKey("boxing_photo_file");
                docOrderService.update(docOrder, new EntityWrapper<DocOrder>().eq("order_id", request.getOrderId()));
            }
            if (null != request.getDocId()) {
                request.setType("update");
                docOrderDocumentService.updateById(document);
            }else {
                document.setCreateTime(nowDate);
                request.setType("insert");
                if(request.getUrlList().size()>0) {
                    document.setBoxingphotoStatus(0);
                }
                document.setContainerStatus(0);
                docOrderDocumentService.insert(document);
                request.setDocId(document.getId());
            }
            request.setFromType("client");
//            if(request.getUrlList().size()>0) {
                // 发送子系统（去程：拼箱，回程：大口岸、国外场站）信息
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            rabbitTemplate.convertAndSend(SendGoodSelfMq.SENDGOODSSELF_TOPIC_EXCHANGE, "sendgoodsself_coninfo_key.#", request, correlationData);
//            }
            //货物状态表数据修改
            TrackGoodsStatus trackGoodsStatus=trackGoodsStatusService.selectOne(new EntityWrapper<TrackGoodsStatus>().eq("order_id", request.getOrderId())
                    .eq("box_num",request.getContainerNo().trim()).eq("del_flag",0));//根据订单id和箱号查看数据库是否有该条数据
            if(trackGoodsStatus!=null){//有数据，修改
                trackGoodsStatus.setSealNum(request.getSealNumber().trim());// 封号
                trackGoodsStatus.setFromSystem("客户端修改");//来源
                //获取实际班列id
                BusiShippingorder shippingOrder=busiShippingorderService.selectOne(new EntityWrapper<BusiShippingorder>().eq("order_ID",request.getOrderId()));
                if(shippingOrder!=null&&!"2".equals(shippingOrder.getLineTypeid())){
                    trackGoodsStatus.setActualClassId(shippingOrder.getClassId());
                }
                trackGoodsStatusService.update(trackGoodsStatus,new EntityWrapper<TrackGoodsStatus>().eq("id", trackGoodsStatus.getId()));
            }else{//新增
                trackGoodsStatus=new TrackGoodsStatus();
                trackGoodsStatus.setOrderId(request.getOrderId());
                trackGoodsStatus.setBoxNum(request.getContainerNo().trim());
                trackGoodsStatus.setSealNum(request.getSealNumber().trim());// 封号
                trackGoodsStatus.setFromSystem("客户端添加");//来源
                //获取实际班列id
                BusiShippingorder shippingOrder=busiShippingorderService.selectOne(new EntityWrapper<BusiShippingorder>().eq("order_ID",request.getOrderId()));
                trackGoodsStatus.setActualClassId(shippingOrder.getClassId());
                trackGoodsStatusService.insert(trackGoodsStatus);
                trackGoodsStatus=trackGoodsStatusService.selectOne(new EntityWrapper<TrackGoodsStatus>().eq("order_id", request.getOrderId())
                        .eq("box_num",request.getContainerNo().trim()).eq("del_flag",0));
                BusiZyInfo zyinfo = new BusiZyInfo();
                zyinfo.setCostId(UUIDUtils.getId());
                zyinfo.setTrackId(trackGoodsStatus.getId()); //货物状态表id
                zyinfo.setOrderId(request.getOrderId()); //订单id
                zyinfo.setOrderNumber(request.getOrderNumber()); //订单编号
                zyinfo.setXianghao(request.getContainerNo().trim());//箱号
                busiZyInfoService.insert(zyinfo);
            }

            return new Result(true);
        }
    }

    /**
     * 删除上传箱号铅封号装箱照信息
     *
     * @param docId
     * @return
     */
    @DeleteMapping("/{docId}/{orderId}")
    public Result deleteConInfo(@PathVariable("docId") Long docId, @PathVariable("orderId") String orderId) {
        //更新托书单证表状态
        DocOrder docOrder = new DocOrder();
        docOrder.setActualSupply(0);
        docOrder.setActualSupplyTime(null);
        docOrder.setOrderId(orderId);
        docOrder.setFileTypeKey("boxing_photo_file");
        docOrderService.update(docOrder, new EntityWrapper<DocOrder>().eq("order_id", orderId));
        DocOrderDocument document = docOrderDocumentService.getDocOrderDocumentById(docId);
        if(StringUtils.isNotEmpty(document.getFileUrl())) {
            String[] urls = document.getFileUrl().split(";");
            for (int i = 0; i < urls.length; i++) {
                String path = urls[i].replace(server_ip, uploadFolder.replace("booking/", ""));
                System.out.println(path);
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        // todo 发送子系统信息（去程：拼箱，回程：大口岸、国外场站）
//        String classEastAndWest = busiShippingorderService.getClassEastAndWest(orderId);
        DocSendGoodsVO sendGoodsVO = new DocSendGoodsVO();
        sendGoodsVO.setType("delete");
        sendGoodsVO.setDocId(docId);
        sendGoodsVO.setOrderId(document.getOrderId());
        sendGoodsVO.setOrderNumber(document.getOrderNumber());
        sendGoodsVO.setContainerNo(document.getContainerNo());
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(SendGoodSelfMq.SENDGOODSSELF_TOPIC_EXCHANGE, "sendgoodsself_coninfo_key.#", sendGoodsVO, correlationData);
        //货物状态表数据删除
        TrackGoodsStatus trackGoodsStatus=new TrackGoodsStatus();
        trackGoodsStatus.setFromSystem("客户端删除");
        trackGoodsStatus.setDelFlag(1);
        trackGoodsStatusService.update(trackGoodsStatus,new EntityWrapper<TrackGoodsStatus>().eq("order_id", document.getOrderId())
                .eq("box_num",document.getContainerNo()).eq("del_flag",0));
        BusiZyInfo zyinfo = new BusiZyInfo();
        zyinfo.setDelFlag("1");
        busiZyInfoService.update(zyinfo,new EntityWrapper<BusiZyInfo>().eq("order_id", document.getOrderId())
                .eq("xianghao",document.getContainerNo()).eq("del_flag",0));
        return new Result(docOrderDocumentService.deleteById(docId));
    }
}
