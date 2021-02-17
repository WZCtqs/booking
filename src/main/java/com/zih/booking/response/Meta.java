package com.zih.booking.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单内部数据
 */
@Data
public class Meta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String icon;
}
