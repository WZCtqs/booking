package com.zih.booking.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

//拼箱入仓
@Data
public class WarehouseResponse {
    @ApiModelProperty(value = "委托书编号")
    private String orderNumber;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("班列日期")
    private Date classDate;

    @ApiModelProperty(value = "去回城")
    private String classEastAndWest;
    //推荐人
    private String clientTjr;
    //货物品名
    private String goodsName;
    private String goodsEnName;

    //托书数量
    private String totalNumber;
    //托书总体积
    private String totalCBM;
    //托书总重量
    private String totalKGS;



    //特殊要求备注
    private String clientOrderRemarks;
    //是否可堆叠（1是0否2仅可自叠）
    private String goodsCannotPileUp;
    //是否易碎（1是0否）
    private String goodsFragile;
    //包装方式
    private String goodsPacking;
    //数量
    private String goodsNumber;
    //规格(长*宽*高*件数)
    private String goodsStandard;
    //毛重
    private String lb14;
    //重量
    private  String goodsKGS;
    //体积
    private String goodsCBM;
    //结算体积
    private String lb152;

    private Integer inquiryId;
}
