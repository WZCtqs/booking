package com.zih.booking.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProvinceResponse implements Serializable {

    private String code;
    private String label;
    private String value;

    private List<CityVo> children;
}
