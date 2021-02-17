package com.zih.booking.request;

import lombok.Data;

import java.util.Date;
//sh
@Data
public class TiTimeRequest {
   private Date  shipOrderUnloadTime;
    private String orderNumber;
    private String shipOrderUnloadWay;
    private String shipOrderUnloadAddress;
}
