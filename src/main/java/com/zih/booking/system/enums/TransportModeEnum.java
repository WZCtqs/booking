package com.zih.booking.system.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @Description: 运输方式
 * @Author: shahy
 * @Date: 2020/1/6
 */
@Getter
@ToString
public enum TransportModeEnum {

    /**
     * 公路运输
     */
    HIGHWAY("公路运输"),
    /**
     * 铁路运输
     */
    RAILWAY("铁路运输");

    private String transportMode;

    private TransportModeEnum(String transportMode){
        this.transportMode = transportMode;
    }


}
