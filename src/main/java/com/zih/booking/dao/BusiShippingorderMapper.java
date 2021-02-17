package com.zih.booking.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.model.BookingInquiryGoodsDetails;
import com.zih.booking.model.BusiShippingorder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zih.booking.model.TjrEmail;
import com.zih.booking.request.DocumentFileRequest;
import com.zih.booking.request.SettlementRequest;
import com.zih.booking.request.TrainListRequest;
import com.zih.booking.request.WarehouseRequest;
import com.zih.booking.response.*;
import com.zih.booking.vo.BusiShippingorderVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author wsl123
 * @since 2020-01-09
 */
@Repository
public interface BusiShippingorderMapper extends BaseMapper<BusiShippingorder> {
    List<TrainListResponse> selectTrainList(Page page, TrainListRequest trainListRequest);//运综

    List<DocumentFileResponse> selectDocList(Page page, DocumentFileRequest documentFileRequest);//dz

    List<SettlementResponse> selectSetList(Page page, SettlementRequest settlementRequest);//js

    TrainDetailResponse selectTrainDetail(String orderId);

    List<WarehouseResponse> selectWareList(Page page, WarehouseRequest documentFileRequest);//拼箱入仓

    List<BusiOrderListResponse> selectMyList(Page page, BusiShippingorderVo busiShippingorderVo);//我的订单

    AllocationNotice getAllocationNotice(String orderId);//配舱通知书

    Map<String, Object> getAllocationNoticePdfXi(String orderId);//pdf xi

    Map<String, Object> getAllocationNoticePdfDong(String orderId);//pdf dong

    HomePageResponse getTodayCount(@Param("today") String today, @Param("clients") List<String> clientId);

    List<BookingInquiryGoodsDetails> selectGoodsInfo(@Param("inquiryRecordId") String inquiryRecordId);

    public List<String> selectOrderMerEmail(String[] merchandiserId);  //查询客户跟单邮箱

    public String selectOrderTjrEmail(String tjrId);  //查询推荐人邮箱

    List<TjrEmail> selectTjrEmail(String orderId);

    List<BusiShippingorder> selectClientByOrderNumber(String orderNumber);

    public String siteListByClass(@Param("classId") String classId, @Param("isconsolidation") String isconsolidation); //查询托书关联班列的上下货站点

    @Select("select class_EastAndWest from busi_shippingorder where order_ID =  #{orderId}")
    String getClassEastAndWest(String orderId);

    BusiShippingorder selectClientByOrderId(String orderId);

}
