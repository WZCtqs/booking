package com.zih.booking.system.enums;


import lombok.Getter;

import lombok.ToString;

@Getter
@ToString
public enum QuPinEnum {

   // ORDER_SUBMIT("0","已提交订单"),
    ORDER_SUCESS("1","已订舱成功"),
    YI_TI_HUO("3","已提货"),
    YI_RU_CANG("4","已入仓"),
    YI_BAO_GUAN("6","已报关通过"),
    BAN_LIE("7","班列运综"),
    YI_ZI_LIAO("8","已拆箱完成"),
    YI_TI_XIANG("9","已提货"),
    YI_SONG_DA("10","已送达");


    private String code;

    private String name;

    private QuPinEnum(String code,String name){
        this.code = code;
        this.name = name;
    }
    public static String getNameByCode(String code){
        for (QuZhengEnum value : QuZhengEnum.values()) {
            if(value.getCode().equals(code)){
                return value.getName();
            }
        }
        return null;
    }
}

