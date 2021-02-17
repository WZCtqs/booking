package com.zih.booking.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
//一级
@Data
public class OneNodeResponse {
    private String icon;
    private String nodeName;
    private Integer sort;
    private Integer state;
  //  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String time;
    private String id;
}
