package com.zih.booking.system.token;

import com.zih.booking.model.BusiClients;
import com.zih.booking.model.KhUser;

import java.util.Set;

/**
 * 登录用户身份
 */
public class LoginUser
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String token;
    /**
     * 用户类型
     */
    private String userType;

    /**
     * 登陆时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;
    /**
     * 菜单
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private BusiClients busiClients;

    private KhUser khUser;

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public Long getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Long loginTime)
    {
        this.loginTime = loginTime;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation()
    {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation)
    {
        this.loginLocation = loginLocation;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public Long getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Long expireTime)
    {
        this.expireTime = expireTime;
    }

    public BusiClients getBusiClients()
    {
        return busiClients;
    }

    public void setBusiClients(BusiClients busiClients)
    {
        this.busiClients = busiClients;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public KhUser getKhUser() {
        return khUser;
    }

    public void setKhUser(KhUser khUser) {
        this.khUser = khUser;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
