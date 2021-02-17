package com.zih.booking.system.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义登录身份
 */
public class UserToken extends UsernamePasswordToken {
    //登录方式
    private LoginType loginType;


    public UserToken(LoginType loginType, final String username, final String password) {
        super(username, password);
        this.loginType = loginType;
    }


    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

}
