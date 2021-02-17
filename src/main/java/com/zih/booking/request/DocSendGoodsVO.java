package com.zih.booking.request;

import com.zih.booking.vo.ProblemFileVo;
import com.zih.booking.vo.ProblemFileVo2;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description : 自送货接收格式
 * @Author : wangzhichao
 * @Date: 2020-12-18 10:14
 */
@Data
public class DocSendGoodsVO implements Serializable {

    // id
    private String orderId;
    //docid
    private Long docId;
    //舱位号
    private String orderNumber;
    //去回程
    private String classEastAndWest;
    //箱号
    private String containerNo;
    //铅封号
    private String sealNumber;
    //装箱照审核失败原因
    private String conSealFail;
    //装箱照审核结果（0：待审核，1：审核失败，2：审核成功）
    private Integer conSealStatus;
    //装箱照审核失败原因
    private String boxingphotoFail;
    //装箱照审核结果（0：待审核，1：审核失败，2：审核成功）
    private Integer boxingphotoStatus;
    //装箱照集合
    private List<ProblemFileVo> urlList;
    //上传来源 client，blpt
    private String fromType;
    //发送子系统类型
    private String type;

    @Override
    public String toString() {
        return "DocSendGoodsVO{" +
                "orderId='" + orderId + '\'' +
                ", docId=" + docId +
                ", orderNumber='" + orderNumber + '\'' +
                ", classEastAndWest='" + classEastAndWest + '\'' +
                ", containerNo='" + containerNo + '\'' +
                ", sealNumber='" + sealNumber + '\'' +
                ", conSealFail='" + conSealFail + '\'' +
                ", conSealStatus=" + conSealStatus +
                ", boxingphotoFail='" + boxingphotoFail + '\'' +
                ", boxingphotoStatus=" + boxingphotoStatus +
                ", urlList=" + urlList +
                ", fromType='" + fromType + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
