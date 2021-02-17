package com.zih.booking.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class InquiryVo {
    private  String containerNum;
    private  String uploadStationNum;
    private  String dropStationNum;
    private  String eastOrWest;
    private  String lineType;
    private  String totalWeight;
    private  String totalVolume;
    private  String billableVolume;
    private String goodsType;
    private String bookingTimeFlag; //0当月1次月
    private Date validDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date inquiryTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date nextMonth;
}
