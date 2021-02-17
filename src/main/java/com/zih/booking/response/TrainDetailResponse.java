package com.zih.booking.response;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "运综详情实体 响应类")
@Data
public class TrainDetailResponse {

    @ApiModelProperty(value = "委托书编号")
    @TableField("order_number")
    private String orderNumber;
    @ApiModelProperty(value = "发货地")
    @TableField("order_uploadSite")
    private String orderUploadsite;
    @ApiModelProperty(value = "收货地")
    @TableField("order_unloadSite")
    private String orderUnloadsite;
    @ApiModelProperty(value = "预计到达时间")
    private String expectTime;
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private String createTime;
    private String receiveEmail;
    private String classId;
    private String orderId;

}
