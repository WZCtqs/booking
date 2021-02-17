package com.zih.booking.vo;

import lombok.Data;

import java.util.Date;


//回程整柜二级
@Data
public class ReturnCabinet {

    private Date check_result_time;
    private Date release_box_time;
    private Date carry_cargo_time;
    private Date in_store_time;
    private Date in_station_time;
    private Date release_time;
    private Date carry_box_information_time;
    private Date carry_container_time;
    private Date reach_time;
    private Date return_box_time;
    private Date except_come_time;
    private String ship_oreder_address;
    private String receive_order_address;
    private String file_url;
}
