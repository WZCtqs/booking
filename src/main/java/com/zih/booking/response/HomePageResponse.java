package com.zih.booking.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class HomePageResponse implements Serializable {
    //定义一个序列号
    private static final long serialVersionUID = 1L;

    private String Date;
    private Integer count;
    private Integer count2;
    private Integer count3;
}
