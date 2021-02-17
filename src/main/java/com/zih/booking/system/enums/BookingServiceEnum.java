package com.zih.booking.system.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @Description: 服务（0：门到门；1：门到站；2：站到站；3：站到门）
 * @Author: shahy
 * @Date: 2020/1/6
 */
@Getter
@ToString
public enum BookingServiceEnum {


    /**
     * 0：门到门
     */
    DOOR_TO_DOOR("0","门到门"),
    /**
     * 1：门到站
     */
    DOOR_TO_STATION("1","门到站"),
    /**
     * 2：站到站
     */
    STATION_TO_STATION("2","站到站"),
    /**
     * 3：站到门
     */
    STATION_TO_DOOR("3","站到门");

    private String code;

    private String name;

    private BookingServiceEnum(String code,String name){
        this.code = code;
        this.name = name;
    }

}
