package com.zih.booking.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@ApiModel(value = "结算")
@Data
public class SettlementResponse {


    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "委托书编号")
    private String orderNumber;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("班列日期")
    private String classDate;

    @ApiModelProperty(value = "班列编号")
    private String orderClassBh;

    @ApiModelProperty(value = "箱号")
    private String xianghao;

    @ApiModelProperty(value = "整箱箱型")
    private String  containerType;
    @ApiModelProperty(value = "拼箱箱型")
    private String  xiangxing;
    @ApiModelProperty(value = "箱量")
    private String containerBoxAmount;

    @ApiModelProperty(value = "体积")
    private String goodsCBM;

    @ApiModelProperty(value = "货品名称")
    private String goodsName;

    @ApiModelProperty(value = "上货站")
    private String orderUploadsite;

    @ApiModelProperty(value = "下货站")
    private String orderUnloadsite;

    @ApiModelProperty(value = "去回城")
    private String classEastAndWest;

    @ApiModelProperty(value = "整拼箱")
    private String IsConsolidation;

    @ApiModelProperty(value = "重量")
    private String goodsKGS;
    @ApiModelProperty(value = "提货地")
    private String shipOrderUnloadAddress;


    @ApiModelProperty(value = "提货时间")
    private String shipOrderUnloadTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "班列到站日期")
    private Date classEndTime;

    @ApiModelProperty(value = "费用确认单确认状态0未确认，1已确认")
    private String costVerify;

    @ApiModelProperty(value = "费用美金")
    private String Totalusd;
    @ApiModelProperty(value = "费用人民币")
    private String TotalmoneyS;
    @ApiModelProperty(value = "发票号")
    private String taizangFpNo;
    @ApiModelProperty(value = "k开票状态")
    private String billState;
    @ApiModelProperty(value = "付款信息 /就是个日期")
    private String  taizangPayDate;
    //f发票url
    private String bill_link1;
}