package com.zih.booking.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Properties;

@Slf4j
public class SendPassMailUtil {
    public static void sendPassMail(String to, String subject,String text){
        try{
            String from = "zih-client@zih718.com";//发件人昵称展示   *
            String host = "smtp.qiye.163.com";//企业邮箱smtp   *
            String username = "booking@zih718.com";//发件的企业邮箱   *
            String password= "UD6VqLfJRq2yhDwJ";//发件的企业邮箱的授权码   *
            //设置服务器验证信息
            Properties prop = new Properties();
            prop.setProperty("mail.smtp.auth", "true");
            prop.setProperty("mail.smtp.timeout", "994"); // 加密端口(ssl)  可通过 https://qiye.163.com/help/client-profile.html 进行查询

            MailSSLSocketFactory sf = new MailSSLSocketFactory();// SSL加密
            sf.setTrustAllHosts(true); // 设置信任所有的主机
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);

            //设置邮件内容
            JavaMailSenderImpl javaMailSend = new JavaMailSenderImpl();
            MimeMessage message = javaMailSend.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
            String nick = MimeUtility.encodeText(from);//设置昵称
            messageHelper.setFrom(new InternetAddress(nick + " <"+username+">"));// 邮件发送者
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);

            //设置邮件服务器登录信息
            javaMailSend.setHost(host);
            javaMailSend.setUsername(username);
            javaMailSend.setPassword(password);
            javaMailSend.setJavaMailProperties(prop);

            javaMailSend.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
