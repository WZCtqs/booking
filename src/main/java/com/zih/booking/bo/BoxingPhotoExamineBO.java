package com.zih.booking.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description : 拼箱发送装箱照审核结果
 * @Author : wangzhichao
 * @Date: 2020-12-19 15:51
 */
@Data
public class BoxingPhotoExamineBO implements Serializable {

    //orderid
    private String orderId;
    //箱号
    private String containerNo;
    //箱号审核结果 0：成功，1：失败
    private Integer conType;
    //箱号审核失败原因
    private String conFail;
    //装箱照审核结果 0：成功，1：失败
    private Integer photoType;
    //审核失败原因
    private String photoFail;
    // 到货时间
    private Date getGoodsTime;
    // 锁定状态 1已上传2已锁定3取消锁定
    private String AuditState;
}
