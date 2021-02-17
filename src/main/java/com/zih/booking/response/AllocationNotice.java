package com.zih.booking.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

//配舱通知书
@Data
public class AllocationNotice {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("班列日期")
    private Date classDate;

    private String classNumber;//班列号

    @ApiModelProperty(value = "整拼箱")
    private String isConsolidation;


    private String clientUnit;//To 订舱方

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "委托书编号/仓位号")
    private String orderNumber;

    private String stationID;

    @ApiModelProperty(value = "发货方")
    private String shipOrederContacts;
    private String shipOrederName;

    private String goodsMark;//唛头

    private String receiveOrderName;//收货方
    private String receiveOrderEnName;

    @ApiModelProperty(value = "上货站")
    private String orderUploadsite;

    @ApiModelProperty(value = "下货站")
    private String orderUnloadsite;

    private String goodsPacking;//包装形式
    private String goodsCannotpileup;//是否可堆叠（1是0否2仅可自叠）
    private String goodsNumber;//数量
    private String goodsStandard;//规格尺寸

    @ApiModelProperty(value = "往返")
    private String classEastAndWest;

    @ApiModelProperty(value = "重量")
    private String goodsKgs;

    @ApiModelProperty(value = "体积")
    private String goodsCbm;

    @ApiModelProperty(value = "货品名称")
    private String goodsName;

    private String goodsEnName;

    @ApiModelProperty(value = "箱型")
    private String  containerType;

    @ApiModelProperty(value = "业务编码")
    private String clientYwNumber;

    @ApiModelProperty(value = "箱量")
    private String containerBoxamount;

    private String station;//车站名称

    private String pxYstype;

    private String cuntofftime; //截港时间
    private String zxAddress;//车站地址
    private String pxAddress;
    private String contacts;//联系方式

    /**
     * 是否报关
     */
    private String clientOrderBindingWay;

    private String orderMerchandiserId;

    private String orderMerchandiser;
}
