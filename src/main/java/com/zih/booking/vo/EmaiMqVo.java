package com.zih.booking.vo;

import lombok.Data;

@Data
public class EmaiMqVo {
    /** $column.columnComment */
    private Long id;

    /** SMTP服务器 */
    private String smtpSever;

    /** SMTP端口号 */
    private String smtpPort;

    /** 邮件发送认证 0需要 1不需要 */
    private Integer emailAuthentication;

    /** 名称 */
    private String name;

    /** 账号 */
    private String account;

    /** 密码 */
    private String password;

    /** 删除标志0正常1删除 */
    private Integer delFlag;

    /** 去回程 0去 1回  */
    private Integer goCome;

    /** 拼整箱  0整柜 1拼箱 */
    private Integer consolidationType;

    /** 奇偶数 0偶数 1奇数 */
    private Integer oddEven;

    /** 客户和线上使用 0客户 1线上 */
    private Integer isCustom;

    /** 线路类型：0中欧中越 1中亚中俄  2异常箱 */
    private Integer lineType;

    private String content;
    private String subject;
    private String receiveEmail;
}
