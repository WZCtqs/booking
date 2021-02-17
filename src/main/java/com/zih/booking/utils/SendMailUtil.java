package com.zih.booking.utils;

import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.sun.mail.util.MailSSLSocketFactory;
@Slf4j
public class SendMailUtil{

    public static void sendTrackMail(String sendMail, String password,String[] receiveMail,String subject, String content) {
        try{
            String from = "zih-tracing@zih718.com";//发件人昵称展示   *
            String host = "smtp.qiye.163.com";//企业邮箱smtp   *
            content=content+"<p></p>\n" +
                    "<br /><br />\n" +
                    "<p style=\"color:blue\">&nbsp;&nbsp;Best Regards</p>\n" +
                    "<p></p><br /> \n" +
                    "<p>&nbsp;&nbsp;郑州国际陆港开发建设有限公司   管理部 路务信息组</p>\n" +
                    "<p>&nbsp;&nbsp;Zhengzhou International Hub Development and Construction Co, Ltd.</p>\n" +
                    "<p></p>\n" +
                    "<p style=\"color:blue\">&nbsp;&nbsp;Web: zxdc.zih718.com</p>\n" +
                    "<p>&nbsp;&nbsp;Add:郑州市经济技术开发区经北四路与航海路第十八大街交叉口郑州国际陆港园区</p>\n" +
                    "<p>&nbsp;&nbsp;Crossing of Jingbei 4th Road and 18th Ave.of Hanghai Road,Zhengzhou Economic and Technological Development Zone,Henan,China</p>\n" +
                    "<p style=\"color:blue\">&nbsp;&nbsp;Tel:    +86（0）371-55171376 （ tracing info ） </p>\n" +
                    "\n" +
                    "<p style=\"color:blue\">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;     +86（0）371-55175609（ pick up issues）</p>";
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
            messageHelper.setFrom(new InternetAddress(nick + " <"+sendMail+">"));// 邮件发送者
            messageHelper.setTo(receiveMail);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);

            //设置邮件服务器登录信息
            javaMailSend.setHost(host);
            javaMailSend.setUsername(sendMail);
            javaMailSend.setPassword(password);
            javaMailSend.setJavaMailProperties(prop);
            javaMailSend.send(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

// to:收件人列表
// text：邮件内容
    public static void sendHtmlMail(String[] to, String text,String subject) {
        try{
            String from = "zih-tracing@zih718.com";//发件人昵称展示   *
           // String[] to =s;//接收邮箱
      //      String subject = "Tracing info of West-Bound ZZ-train2020-01-08";//邮件主题   *
           // String text = "邮件内容：哈哈哈";
            String host = "smtp.qiye.163.com";//企业邮箱smtp   *
            String username = "wangshl@zih718.com";//发件的企业邮箱   *
            String password= "SCbczJzrZngBCdUM";//发件的企业邮箱的授权码   *

            text=text+"<p></p>\n" +
                    "<br /><br />\n" +
                    "<p style=\"color:blue\">&nbsp;&nbsp;Best Regards</p>\n" +
                    "<p></p><br /> \n" +
                    "<p>&nbsp;&nbsp;郑州国际陆港开发建设有限公司   管理部 路务信息组</p>\n" +
                    "<p>&nbsp;&nbsp;Zhengzhou International Hub Development and Construction Co, Ltd.</p>\n" +
                    "<p></p>\n" +
                    "<p style=\"color:blue\">&nbsp;&nbsp;Web: www.zih718.com</p>\n" +
                    "<p>&nbsp;&nbsp;Add:郑州市经济技术开发区经北四路与航海路第十八大街交叉口郑州国际陆港园区</p>\n" +
                    "<p>&nbsp;&nbsp;Crossing of Jingbei 4th Road and 18th Ave.of Hanghai Road,Zhengzhou Economic and Technological Development Zone,Henan,China</p>\n" +
                    "<p style=\"color:blue\">&nbsp;&nbsp;Tel:    +86（0）371-55171376 （ tracing info ） </p>\n" +
                    "\n" +
                    "<p style=\"color:blue\">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;     +86（0）371-55175609（ pick up issues）</p>";
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
            // TODO Auto-generated catch block

            log.error(e.getMessage(), e);
        }
    }
/*    public static  void main(String[] args){
        log.info("begin");
        String subject = "Tracing info of West-Bound ZZ-train2020-01-08";//邮件主题   *
        String []to={"1043274501@qq.com","wsl19961106@163.com","m17637503103@163.com"};//
        String text="<p>尊敬的客户，您好/Dear customer：</p>\n" +
                "<p>班列日期/Train date：2020-01-08；</p>\n" +
                "<p>舱位编号/Booking number：ZIHWB200108PFHX07；</p>\n" +
                "<p>运踪时间/Tracing time：2020-01-13 13:03；</p>\n" +
                "<p>状态/Status:在/in 阿拜；</p>\n" +
                "<p>距离布列斯特/distance until BREST:4100km；</p>\n" +
                "<p>距离马拉/distance until Malaszewicze:4130km；</p>\n" +
                "<p>距离汉堡/distance until Hamburg:5090km；</p>\n" +
                "<p>预计到港时间/ETA time:1-23/1-24；</p>\n" +
                "<p>备注/Remark:根据GPS信息显示/According to the information of GPS。</p>\n" +
                "<p>我司会根据班列的实时运行情况对ETA进行调整，请知悉。/we will adjust the ETA according to the real-time operation of the train, please be informed.</p>\n" ;

        SendMailUtil.sendHtmlMail(to,text,subject);
        log.info("end");
    }*/


}
