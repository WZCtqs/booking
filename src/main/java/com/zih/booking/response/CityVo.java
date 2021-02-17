package com.zih.booking.response;

import lombok.Data;

import java.util.List;

@Data
public class CityVo {

    private String code;
    private String label;
    private String value;

    private List<AreaVo> children;
}
