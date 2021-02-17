package com.zih.booking.vo;


import com.zih.booking.request.BaseRequest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class BusiShippingorderVo extends BaseRequest {

    private List<String> clientIds;
    private Long khId; //客户子账号id
    private Integer limit;//一页几条
    private Integer page;//第几页
    @ApiModelProperty(value = "委托书编号")
    private String orderNumber;//委托书编号

    @ApiModelProperty(value = "班列日期 ")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date classDate;
    @ApiModelProperty(value = "往返 0为去程 1为回程")
    private String  classEastAndWest; //往返 0为去程 1为回程
    @ApiModelProperty(value = "//整拼箱 0整柜 1拼箱")
    private  String IsConsolidation; //整拼箱 0整柜 1拼箱
    @ApiModelProperty(value = "路线 ：0中欧 2中亚 3中越 4中俄")
    private String lineTypeid;//路线 ：0中欧 2中亚 3中越 4中俄
    @ApiModelProperty(value = "状态 0未审核 1已审核通过 2已审核未通过 3已取消的委托 4转待审核  5草稿  ")
    private String isExamline;//状态 0未审核 1已审核通过 2已审核未通过 3已取消的委托 4转待审核  5草稿
    @ApiModelProperty(value = "业务员编码")
    private String ywNumber;//业务员编码
    @ApiModelProperty(value = "业务编码")
    private String clientYwNumber; //业务编码
    @ApiModelProperty(value = "联系人 ")
    private String clientContacts;//联系人（发货方）
    @ApiModelProperty(value = "货物品名")
    private String goodsName;//货物品名
    @ApiModelProperty(value = "无视  不用管")
    private String goodsEnName;
}
