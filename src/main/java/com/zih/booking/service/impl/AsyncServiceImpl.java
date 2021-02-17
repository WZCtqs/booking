package com.zih.booking.service.impl;

import com.zih.booking.dao.BusiShippingorderMapper;
import com.zih.booking.model.BusiShippingorder;
import com.zih.booking.model.BusiShippingorderGoods;
import com.zih.booking.service.AsyncService;
import com.zih.booking.utils.SendOrderMailUtil;
import com.zih.booking.utils.SendOrderMailUtils;
import com.zih.booking.utils.SendPassMailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    @Autowired
    BusiShippingorderMapper busiShippingorderMapper;

    //给客户、推荐人发送邮件
    @Override
    @Async("threadPoolTaskExecutor")
    public void orderSendEmail(BusiShippingorder busiShippingorder, BusiShippingorderGoods busiShippingorderGoods){
        String subject = "订舱系统提醒-客户订舱";
        String content = SendOrderMailUtil.chOrderEmailContent(busiShippingorder,busiShippingorderGoods);
        if(null!=content && content!=""){
            //订舱方邮箱发送邮件
//            String clientMail = busiShippingorder.getClientEmail();
//            if(null != clientMail){
//                String[] clientMailArr = clientMail.split(";");
//                for(int i=0;i<clientMailArr.length;i++){
//                    SendPassMailUtil.sendPassMail(clientMailArr[i],subject,content);
//                }
//            }
            //推荐人发送邮件
            String tjrId = busiShippingorder.getClientTjrId();
            if(null != tjrId){
                String tjrEmail = busiShippingorderMapper.selectOrderTjrEmail(tjrId);
                if(null!=tjrEmail && tjrEmail!=""){
                    String[] tjrEmailArr = tjrEmail.split(";");
                    for(int i=0;i<tjrEmailArr.length;i++){
                        SendOrderMailUtils.mailByAll("smtp.qiye.163.com","0","booking1@zih718.com","Lgzih718",tjrEmailArr[i],subject,content);
                    }
                }
            }
            //跟单发送邮件
            String merchandiserIds = busiShippingorder.getOrderMerchandiserId();
            if(null != merchandiserIds){
                String[] merchandiserId = merchandiserIds.trim().split("\\|");
                List<String> merchandiserEmails = busiShippingorderMapper.selectOrderMerEmail(merchandiserId);
                if(merchandiserEmails.size()>0){
                    String[] sendMerchandiserEmail = merchandiserEmails.toArray(new String[merchandiserEmails.size()]); //邮箱集合转化为数组
                    for(int i=0;i<sendMerchandiserEmail.length;i++){
                        SendOrderMailUtils.mailByAll("smtp.qiye.163.com","0","booking1@zih718.com","Lgzih718",sendMerchandiserEmail[i],subject,content);
                    }
                }
            }
        }
    }
}
