package com.zih.booking.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel("登录model")
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends BaseRequest {

    @ApiModelProperty(value = "登录账号/注册账号",example = "13510536299")
    private String userName;
    @ApiModelProperty(value = "密码",example = "536299")
    private String password;
    @ApiModelProperty(value = "验证码的redis key",hidden = true)
    private String codeToken;

    @ApiModelProperty(value = "验证码（注册时使用）")
    private String verifyCode;
}
