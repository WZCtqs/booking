package com.zih.booking.response;

import lombok.Data;

//二级
@Data
public class TwoNodeResponse {
    private Long id;
    /**
     * 订单id
     */

    private String orderId;
    /**
     * 一级节点id
     */

    private Long oneId;
    /**
     * 是否客户端二级节点0是1否
     */

    private String isCustom;

    private String nameZh;

    private String nameEn;
    /**
     * 1正常  2异常
     */
    private Integer state;
    /**
     * 时间
     */
    private String time;
    /**
     * 顺序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备注英文
     */
    private String remarkEn;
    /**
     * 创建时间
     */

    private String driverMessage;//司机信息
}
