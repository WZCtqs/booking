package com.zih.booking.vo;

import lombok.Data;

@Data
public class inquiryResultZx {

    private Long inquiryId; //询价id

    private Long inquiryResultId; //询价结果id

    private Integer containerNum; //箱量

    private String pickUpFees; //提货费

    private String railwayFees; //铁路运费

    private String deliveryFees; //派送费用

    private String pickUpBoxFee; //提箱费

    private String returnBoxFee; //还箱费
}
