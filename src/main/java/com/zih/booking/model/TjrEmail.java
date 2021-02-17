package com.zih.booking.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description :
 * @Author : wangzhichao
 * @Date: 2020-09-15 15:42
 */
@Data
public class TjrEmail implements Serializable {
    private String orderNumber;
    private String mCheng;
    private String deptCode;
}
