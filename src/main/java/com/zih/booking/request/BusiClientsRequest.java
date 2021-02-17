package com.zih.booking.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("公司信息保存")
@EqualsAndHashCode(callSuper = true)
public class BusiClientsRequest extends BaseRequest{

    @ApiModelProperty(value = "法人代表")
    private String clientLegalperson;

    @ApiModelProperty(value = "组织机构代码")
    private String clientCode;

    @ApiModelProperty(value = "单位名称")
    private String clientUnit;

    @ApiModelProperty(value = "单位地址")
    private String clientAddress;

    @ApiModelProperty(value = "座机号")
    private String clientTel;

    @ApiModelProperty(value = "用户名称")
    private String userinfoName;

    @ApiModelProperty(value = "手机号")
    private String clientLoginuser;

    @ApiModelProperty(value = "邮件")
    private String clientEmail;

    @ApiModelProperty(value = "职务")
    private String clientZw;

    @ApiModelProperty(value = "备注")
    private String remark;

}
