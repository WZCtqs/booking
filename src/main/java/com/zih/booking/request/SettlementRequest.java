package com.zih.booking.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class SettlementRequest extends BaseRequest{
    private Integer limit;
    private Integer page;
    private String orderNumber;
    private String beginTime;
    private String endTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date classDate;
    //往返
    private String   classEastAndWest;
    //整拼箱
    private  String IsConsolidation;
    private String containerType;
    //始发站
    private String orderUploadSite;
    //终到站
    private String orderUnloadSite;
    @ApiModelProperty(value = "费用确认单确认状态 0未确认，1已确认")
    private String costVerify;
    private  String billState;
    private List<String> clientIds;
}
