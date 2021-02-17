package com.zih.booking.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.zih.booking.model.BusiShippingorder;
import com.zih.booking.request.BookingSpaceRequest;
import com.zih.booking.request.TrainListRequest;
import com.zih.booking.response.TrainListResponse;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.vo.Cabin;
import com.zih.booking.vo.GwczBookingVo;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author wsl123
 * @since 2020-01-09
 */
public interface BusiShippingorderService extends IService<BusiShippingorder> {

    List<TrainListResponse> selectTrainList(Page page,TrainListRequest trainListRequest);

    ApiResultI18n add (BookingSpaceRequest bookingSpaceRequest);

    boolean xgAdd(Cabin cabin);

    boolean gwczAdd(GwczBookingVo gwczBookingVo);

    String getNameByCode(String name);

    String getClassEastAndWest(String orderId);
}
