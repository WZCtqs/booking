package com.zih.booking.vo;

import lombok.Data;

import java.util.List;

@Data
public class MailVo {
    private static final long serialVersionUID = 4280650483975256784L;
    // 邮件发送者的标示
    private String key;
    // 登陆邮件发送服务器的用户名和密码
    private String mailAccount;
    private String mailPassword;
    //收件人名字
    private String senderAlias;
    // 邮件接收者的地址数组
    private List<String> receiveAddressArray;
    // 邮件主题
    private String subject;
    // 邮件的文本内容
    private String content;
    // 邮件附件的文件名
    private String[] attachFileNames;

}
