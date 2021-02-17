package com.zih.booking.response;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "运综列表实体 响应类")
@Data
public class TrainListResponse {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
@ApiModelProperty("班列日期")
private Date classDate;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "委托书编号")
    private String orderNumber;

    @ApiModelProperty(value = "箱号")
    private String xianghao;

    @ApiModelProperty(value = "去回城")
    private String classEastAndWest;

    @ApiModelProperty(value = "整拼箱")
    private String IsConsolidation;

    @ApiModelProperty(value = "货品中文名称")
    private String goodsName;

    @ApiModelProperty(value = "货品英文名称")
    private String goodsEnName;

    @ApiModelProperty(value = "发货人")
    private String shipOrederContacts;

    @ApiModelProperty(value = "运综状态")
    private String trainState;
    private String trainStateEn;
    @ApiModelProperty(value = " 是否正常箱0是1否")
    private String isNormal;
    private String createTime;
}
