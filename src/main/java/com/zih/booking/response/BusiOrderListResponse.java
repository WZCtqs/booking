package com.zih.booking.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class BusiOrderListResponse {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty("班列日期")
    private Date classDate;

    private String classState;

    private String classId;
    @ApiModelProperty(value = "整拼箱")
    private String isConsolidation;
    private Integer TurnCount;
    @ApiModelProperty(value = "订单id")
    private String orderId;
    private Integer inquiryId;
    private Integer inquiryRecordId;
    private String bookingTimeFlag; //预期订舱时间（0当月1次月)
    @ApiModelProperty(value = "委托书编号")
    private String orderNumber;

    @ApiModelProperty(value = "上货站")
    private String orderUploadsite;

    @ApiModelProperty(value = "下货站")
    private String orderUnloadsite;
    private String orderUploadcode;
    private String orderUnloadcode;
    @ApiModelProperty(value = "订舱时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date tjTime;

    //审核时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date exameTime;

    @ApiModelProperty(value = "状态 0未审核 1已审核通过  2已审核未通过 3已取消的委托  4转待审核  5草稿 6转待失败")
    private String isExamline;

    @ApiModelProperty(value = "箱号")
    private String xianghao;

    @ApiModelProperty(value = "往返")
    private String classEastAndWest;

    @ApiModelProperty(value = "重量")
    private String goodsKGS;

    @ApiModelProperty(value = "体积")
    private String goodsCBM;

    @ApiModelProperty(value = "货品名称")
    private String goodsName;
    private String goodsEnName;
    @ApiModelProperty(value = "箱型")
    private String  containerType;

    @ApiModelProperty(value = "发货方联系人")
    private String shipOrederContacts;

    @ApiModelProperty(value = "业务员编码")
    private String ywNumber;

    @ApiModelProperty(value = "业务编码")
    private String clientYwNumber; //业务编码

    @ApiModelProperty(value = "箱量")
    private String containerBoxamount;

    private String clientContacts;//联系人
    @ApiModelProperty(value = "路线 ：0中欧 2中亚 3中越 4中俄")
    private String lineTypeid;//路线 ：0中欧 2中亚 3中越 4中俄
    private String site;
    private String examInfo;
    private String xgRefuseInfo;
    private String refuseInfo;
    private String failReason;
    private String isReason; //是否有驳回原因 0否 1是
    private String shipOrederName;//发货方名称

    @ApiModelProperty(value = "服务：0门到门 1门到站 2站到站 3站到门")
    private String bookingService;

    private Long khId; //客户子账号id

    /** 1有修改记录0没有修改记录 */
    private String isupdate;

    //报价有效期
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validDate;

    /** 特殊要求备注 :0再次订舱*/
    private String clientOrderRemarks;

    /** 取消托书是否可编辑 0是 1否*/
    private String  isEdit;


}
