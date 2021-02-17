package com.zih.booking.request;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("注册model")
@EqualsAndHashCode(callSuper = true)
public class RegisterRequest extends BaseRequest{

//    @ApiModelProperty(value = "登录账号/注册账号",example = "13510536299")
//    private String userName;
//    @ApiModelProperty(value = "密码",example = "536299")
//    private String password;
//    @ApiModelProperty(value = "验证码的redis key",hidden = true)
//    private String codeToken;
//    @ApiModelProperty(value = "验证码（注册时使用）")
 //   private String verifyCode;
    @ApiModelProperty(value = "法人代表" )
    private String clientLegalperson;
    @ApiModelProperty(value = "组织机构代码" )
    private String clientCode;
    @ApiModelProperty(value = "单位名称" )
    private String clientUnit;
    @ApiModelProperty(value = "单位地址" )
    private String clientAddress;
    @ApiModelProperty(value = "订舱人" )
    private String clientContacts;
    @ApiModelProperty(value = "订舱人邮件" )
    private String clientEmail;
    @ApiModelProperty(value = "部门" )
    private String clientDept;
    @ApiModelProperty(value = "职务" )
    private String clientZw;
    @ApiModelProperty(value = "手机号" )
    private String clientPhone;
    @ApiModelProperty(value = "座机" )
    private String clientTel;
    @ApiModelProperty(value = "备注" )
    private String remark;
    /**
     * 推荐人
     */

    private String clientTjr;
    /**
     * varchar	推荐人ID
     */

    private String clientTjrId;

    //是否签署合同
    private String isSign;
    //有效期
    private String validDate;

}
