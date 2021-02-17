package com.zih.booking.controller;


import com.zih.booking.model.SysFailMq;
import com.zih.booking.service.SysFailMqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * <p>
 * 消息队列发送失败消息 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-12-09
 */
@Controller
@RequestMapping("/sysFailMq")
public class SysFailMqController {

    @Autowired
    RabbitTemplate template;

    @Autowired
    SysFailMqService sysFailMqService;

    @GetMapping("/aa")
    public void setTemplate(String exchange, String key) {
        SysFailMq mq = new SysFailMq();
        mq.setBody("");
        mq.setCode(2);
        mq.setText("44");
        mq.setExchange(exchange);
        mq.setRoutingkey(key);
        mq.setCreateTime(new Date());
        sysFailMqService.insert(mq);
    }
}

