package com.zih.booking.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class orderRequest {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty("班列日期")
    private Date beginTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty("班列日期")
    private Date endTime;

    @ApiModelProperty("订单号（委托书编号）（舱位号）")
    private  String orderNumber;

    @ApiModelProperty("0为去程 1为回程")
    private String classEastAndWest;

    @ApiModelProperty(" 整拼箱 0整柜 1拼箱")
    private String IsConsolidation;

    @ApiModelProperty("箱号")
    private String xianghao;
}
