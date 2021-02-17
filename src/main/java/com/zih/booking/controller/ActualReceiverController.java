package com.zih.booking.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.model.ActualReceiver;
import com.zih.booking.service.ActualReceiverService;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.Result;
import com.zih.booking.utils.ServletUtils;
import com.zih.booking.vo.DelArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * <p>
 * 实际收货方 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-02-26
 */
@Slf4j
@RestController
@Api(tags = "实际收货方")
@RequestMapping("/actualReceiver")
public class ActualReceiverController {
    @Autowired
    ActualReceiverService actualReceiverService;
    @Autowired
    private TokenService tokenService;


    @ApiOperation(value = "列表",notes= " 列表")
    @GetMapping(value = "/list")
    public Result list(@RequestParam Integer limit, @RequestParam Integer page ,String actualMan,String goBack) {
     //   String client_id=tokenService.getClientId(ServletUtils.getRequest());//客户id
        List<String> clients=tokenService.getClientIds(ServletUtils.getRequest());
        for(String c:clients){
            log.info(String.valueOf(clients));
        }
        Page<ActualReceiver> list =
                actualReceiverService.selectPage(new Page<>(page,limit),
                        new EntityWrapper<ActualReceiver>()
                                .in("client_id",clients)
                                .eq("del_flag","0")
                                .like("actual_man",actualMan)
                                .like("go_back",goBack)
                                .orderBy("order_num")
                                );
        return new Result (list);
    }

    @ApiOperation(value = "增加",notes= "增加  ")
    @PostMapping()
    public Result add(@RequestBody ActualReceiver actualReceiver) {
        String client_id=tokenService.getClientId(ServletUtils.getRequest());//客户id
        actualReceiver.setClientId(client_id);
        actualReceiver.setCreateTime(new Date());
        return  new Result(actualReceiverService.insert(actualReceiver));
    }

    @ApiOperation(value = "修改",notes= "修改  ")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody  ActualReceiver actualReceiver) {
        actualReceiver.setUpdateTime(new Date());
        return  new Result(actualReceiverService.updateById(actualReceiver ));
    }

    @ApiOperation(value = "删除",notes= "删除")
    @DeleteMapping
    public Result list(@RequestParam String id) {
        ActualReceiver receiver=new ActualReceiver();
        receiver.setActReceiverId(id);
        receiver.setDelFlag("1");
        return  new Result(actualReceiverService.updateById(receiver));
    }
    @ApiOperation(value = "批量删除", notes = "批量删除")
    @PostMapping("del")
    public Result list(@RequestBody DelArray ids) {
        List<ActualReceiver> list = new ArrayList<>();
        for (String id : ids.getIds()) {
            ActualReceiver sender = new ActualReceiver();
            sender.setActReceiverId(id);
            sender.setDelFlag("1");
            list.add(sender);
        }
        return  new Result(actualReceiverService.updateBatchById(list));
    }
}

