package com.zih.booking.request;

import com.zih.booking.model.BookingInquiryGoodsDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookingInquiryRequest extends BaseRequest {

    /**
     * 发货地
     */
    private String shipmentPlace;
    /**
     * 上货站
     */
    private String uploadStation;
    /**
     * 收货地
     */
    private String receiptPlace;
    /**
     * 下货站
     */
    private String dropStation;
    /**
     * 服务（0：门到门；1：门到站；2：站到站；3：站到门）
     */
    private String bookingService;
    /**
     * 是否委托zih提货（0：是；1：否）
     */
    private String isPickUp;
    /**
     * 是否委托zih送货（0：是；1：否）
     */
    private String isDelivery;
    /**
     * 是否委托zih代理报关（0：是；1：否）
     */
    private String isOrderCustoms;
    /**
     * 是否委托zih代理清关（0：是；1：否）
     */
    private String isClearCustoms;
    /**
     * 箱属（0：自备；1：zih）
     */
    private String containerBelong;
    /**
     * 货物类型（0：整柜；1：拼箱）
     */
    private String goodsType;
    /**
     * 箱型
     */
    private String containerType;
    /**
     * 箱量
     */
    private Integer containerNum;
    /**
     * 包装方式
     */
    private String packageType;
    /**
     * 是否可堆叠（0否：1是）
     */
    private String isStack;
    /**
     * 总数量
     */
    private String totalAmount;
    /**
     * 总重量(kg)
     */
    private String totalWeight;
    /**
     * 总体积(m³)
     */
    private String totalVolume;
    /**
     * 拼箱货物详情
     */
    private List<BookingInquiryGoodsDetails> goodsDetails;

}
