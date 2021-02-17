package com.zih.booking.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 单个菜单属性
 */
@Data
public class MenuResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String path;

    private String component;

    private String name;

    private Meta meta;

    private Boolean hidden;
}
