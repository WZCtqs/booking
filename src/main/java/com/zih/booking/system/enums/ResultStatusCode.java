package com.zih.booking.system.enums;


import lombok.Getter;
import lombok.ToString;

/**
 * @author shahy
 * @description 错误代码
 */
@Getter
@ToString
public enum ResultStatusCode {

    /**
     * 默认
     */
    SUCCESS(0, "result.status.success"),
    FAIL(1, "result.status.fail"),

    // *********************系统错误*********************
    // 签名错误
    SIGN_ERROR(120, "result.status.sign.error"),
    // 访问超时
    TIME_OUT(130, "result.status.timeout"),
    // 参数解析失败
    BAD_REQUEST(400, "result.status.bad.request"),
    // 未授权
    UNAUTHO_ERROR(403, "result.status.unauth.error"),
    // 登录失效
    INVALID_TOKEN(401, "result.status.invalid.token"),
    // 不支持当前请求方法
    METHOD_NOT_ALLOWED(405, "result.status.method.not.allowed"),
    // 服务器运行异常
    SYSTEM_ERR(500, "result.status.system.err"),
    // 上传文件异常
    UPLOAD_ERROR(501, "result.status.upload.error"),


    // **********************登录接口**********************
    // 该用户不存在或密码错误
    NOT_EXIST_USER_OR_ERROR_PWD(10000, "result.status.not.exist.user.or.error.pwd"),
    // 该用户已被冻结
    USER_FROZEN(10001, "result.status.user.frozen"),
    // 验证码图片生成失败
    VERIFY_CODE_GENERATE_ERROR(10002, "result.status.verify.code.generate.error"),
    // 验证码错误
    VERIFY_CODE_ERROR(10003, "result.status.verify.code.error"),
    // 验证码过期
    VERIFY_CODE_EXPIRED(10004, "result.status.verify.code.expired"),
    // 该手机号未注册
    NOT_EXIST_PHONE(10005, "result.status.not.exist.phone"),

    // **********************订舱询盘**********************
    // 报价异常
    QUOTE_ERROR(20000, "result.status.quote.error");

    private int code;
    private String msg;

    private ResultStatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
