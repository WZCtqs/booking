package com.zih.booking.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接收国外场站拼箱订舱数据
 */
@Data
public class GwczBookingVo implements Serializable {


    //数据
    public Integer Id;

    //班列日期
    public String ClassNumDate1;

    //班列编号
    public String ClassNumber;

    //往返
    public String Direction1;

    //上货站
    public String uploadSite1;

    //下货站
    public String unloadSite1;

    //zih推荐人
    public String Zihtjr;

    //邮箱
    public String Email;

    //订舱信息
    public String message;

    //提箱地
    public String tixiang;

    //还箱地
    public String huanxiang;


}
