package com.zih.booking.system.config;


/**
 * 常用定数类
 */
public class Consts {

    /**
     * 订舱询盘请求箱行亚欧
     */
    public final static String BFIR_GET_XXYO = "http://";
    /**
     * 订舱询盘请求集疏
     */
    public final static String BFIR_GET_JS = "http://";

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_client_key";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";
}
