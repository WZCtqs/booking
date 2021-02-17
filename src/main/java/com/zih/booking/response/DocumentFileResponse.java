package com.zih.booking.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DocumentFileResponse {

    private String bookingService;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("班列日期")
    private Date classDate;

    @ApiModelProperty(value = "整拼箱")
    private String IsConsolidation;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "委托书编号")
    private String orderNumber;

    @ApiModelProperty(value = "上货站")
    private String orderUploadsite;

    @ApiModelProperty(value = "下货站")
    private String orderUnloadsite;

    @ApiModelProperty(value = "订舱时间")
    private String createTime;

    @ApiModelProperty(value = "箱号")
    private String xianghao;

    @ApiModelProperty(value = "去回城")
    private String classEastAndWest;

    @ApiModelProperty(value = "重量")
    private String goodsKGS;

    @ApiModelProperty(value = "体积")
    private String goodsCBM;

    @ApiModelProperty(value = "货品名称")
    private String goodsName;

    @ApiModelProperty(value = "箱型")
    private String containerType;

    @ApiModelProperty(value = "箱量")
    private String containerCount;

    @ApiModelProperty(value = "发货方")
    private String shipOrederContacts;

    private String YMNumber;

    @ApiModelProperty(value = "委托ZIH提货 0是 1否  2铁路到货")
    private String shipOrderBinningWay;
}
