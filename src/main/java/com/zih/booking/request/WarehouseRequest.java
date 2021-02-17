package com.zih.booking.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WarehouseRequest extends BaseRequest {
    private Integer limit;
    private Integer page;
    private String orderNumber;
    private String beginTime;
    private String endTime;
    private String classEastAndWest;
    private List<String> clientIds;
}