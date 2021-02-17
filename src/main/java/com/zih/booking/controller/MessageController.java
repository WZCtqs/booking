package com.zih.booking.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.model.Message;
import com.zih.booking.model.SysMessage;
import com.zih.booking.service.MessageService;

import com.zih.booking.service.SysMessageService;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.Result;
import com.zih.booking.utils.ServletUtils;
import com.zih.booking.vo.DelArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-03-05
 */
@RestController
@Api(tags = "消息提醒")
@RequestMapping("/message")
public class MessageController {

    @Autowired
    SysMessageService messageService;
    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "列表", notes = " 消息提醒")
    @GetMapping(value = "/list")
    public Result list(@RequestParam Integer limit, @RequestParam Integer page, String messageTitle) {
        List<String> clients = tokenService.getClientIds(ServletUtils.getRequest());
        Page<SysMessage> list = new Page<>();
        List<SysMessage> list1 = messageService.getMsgPageByClientId(clients.get(0), messageTitle, page, limit);
        int count = messageService.getMsgPageByClientIdCount(clients.get(0), messageTitle);
        list.setRecords(list1);
        list.setTotal(count);
        return new Result(list);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping
    public Result list(@RequestParam String id) {
        SysMessage message = new SysMessage();
        message.setDelFlag(1);
        return new Result(messageService.updateById(message));
    }

    @ApiOperation(value = "批量已读", notes = "批量已读")
    @PostMapping("deal")
    public Result list(@RequestBody DelArray ids) {
        List<SysMessage> list = new ArrayList<>();
        for (String id : ids.getIds()) {
            SysMessage msg = new SysMessage();
            msg.setId(Long.parseLong(id));
            msg.setMsgStatus(1);
            list.add(msg);
        }
        return new Result(messageService.updateBatchById(list));
    }

    @ApiOperation(value = "全部已读", notes = "全部已读")
    @PostMapping("dealAll")
    public Result list() {
        String clientId = tokenService.getClientId(ServletUtils.getRequest());
         SysMessage message=new SysMessage();
         message.setMsgStatus(1);
        messageService.update(message,new EntityWrapper<SysMessage>().eq("client_id",clientId));
        return new Result();
    }

    @ApiOperation(value = "未读", notes = "全部未读")
    @PostMapping("undealAll")
    public Result undeallist() {
        String clientId = tokenService.getClientId(ServletUtils.getRequest());
        return new Result(messageService.getNotReadCount(clientId));
    }

}

