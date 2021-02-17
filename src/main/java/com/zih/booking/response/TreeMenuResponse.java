package com.zih.booking.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author shahy
 */
@Data
public class TreeMenuResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String path;

    private String component;

    private String name;

    private Meta meta;

    private Boolean hidden;

    private List<MenuResponse> children;

}
