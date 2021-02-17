package com.zih.booking.system.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * @Description: 铁路线路
 * @Author: shahy
 * @Date: 2020/1/6
 */
@Getter
@ToString
public enum RailwayLineEnum {

    /**
     * 郑欧
     */
    ZHENG_OU("郑欧"),
    /**
     * 郑俄
     */
    ZHENG_E("郑俄"),
    /**
     * 郑中亚
     */
    ZHENG_ZHONG_YA("郑中亚"),
    /**
     * 郑中越
     */
    ZHENG_ZHONG_YUE("郑中越");

    private String lineName;

    private RailwayLineEnum(String lineName){
        this.lineName = lineName;
    }


}
