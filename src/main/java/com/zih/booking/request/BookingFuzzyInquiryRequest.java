package com.zih.booking.request;

import com.zih.booking.model.BookingFuzzyInquiryGoodsDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookingFuzzyInquiryRequest extends BaseRequest {

    /**
     * 发货地城市code
     */
    private String shipmentCityCode;
    /**
     * 发货地
     */
    private String shipmentPlace;
    /**
     * 收货地城市code
     */
    private String receiptCityCode;
    /**
     * 收货地
     */
    private String receiptPlace;
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
    private List<BookingFuzzyInquiryGoodsDetails> goodsDetails;

}
