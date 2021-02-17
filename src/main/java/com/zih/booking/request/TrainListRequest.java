package com.zih.booking.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrainListRequest extends BaseRequest {

    /**
     * 班列日期
     */
    private String classDate;
    /**
     * 整箱、拼箱
     */
    private String isConsolidation;
    /**
     * 往返
     */
    private String classEastAndWest;
    /**
     * 委托书编号
     */
    private String orderNumber;
    private String xianghao;
    private String shipOrederContacts;
    /**
     * 每页多少条数据
     */
    private Integer limit;

    /**
     * 当前页码
     */
    private Integer page;
    private List<String> clientIds;

}

