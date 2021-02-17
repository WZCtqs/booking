package com.zih.booking.system.enums;


import lombok.Getter;

import lombok.ToString;

@Getter
@ToString
public enum QuZhengEnum {

   // ORDER_SUBMIT("0","已提交订单"),
    ORDER_SUCESS("1","已订舱成功1"),
    YI_FANG_XIANG("2","已放箱2"),
    YI_TI_HUO("3","已提货2"),
    YI_RU_CANG("4","已入仓3"),
    YI_JIN_ZHAN("5","已进站3"),
    YI_BAO_GUAN("6","已报关通过3"),
    BAN_LIE("7","班列运综4"),
    YI_ZI_LIAO("8","已发提箱资料5"),
    YI_TI_XIANG("9","已提箱6"),
    YI_SONG_DA("10","已送达6"),
    YI_HUAN_XING("11","已还箱7");

    private String code;

    private String name;

    private QuZhengEnum(String code,String name){
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
//    public static String getBigNameByCode(String code,String num){
//        for (QuZhengEnum value : QuZhengEnum.values()) {
//            if(value.getCode().substring(value.getCode().length() - 1).equals(num) &&  ){
//                return value.getName();
//            }
//        }
//        return null;
//    }
}
