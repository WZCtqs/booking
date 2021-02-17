package com.zih.booking.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "发货地/收货地响应类")
@Data
public class CityResponse implements Serializable {

    @ApiModelProperty(value = "城市code")
    private String cityCode;

    @ApiModelProperty(value = "城市名称")
    private String cityName;

}
