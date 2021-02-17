package com.zih.booking.vo;

import lombok.Data;

@Data
public class SendEmailVo {
    private  String  to;
    private  String[] id;
    private String orderNumber;
}
