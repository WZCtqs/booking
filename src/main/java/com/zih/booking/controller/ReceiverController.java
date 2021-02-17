package com.zih.booking.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.model.Receiver;
import com.zih.booking.service.ReceiverService;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.Result;
import com.zih.booking.utils.ServletUtils;
import com.zih.booking.vo.DelArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-02-25
 */
@RestController
@RequestMapping("/receiver")
@Api(tags = "收货方相关接口")
public class ReceiverController {
    @Autowired
    ReceiverService receiverService;
    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "列表", notes = "收货方列表")
    @GetMapping(value = "/list")
    public Result list(@RequestParam Integer limit, @RequestParam Integer page, String receiverName) {
        List<String> clients=tokenService.getClientIds(ServletUtils.getRequest());
        Page<Receiver> list =
                receiverService.selectPage(new Page<>(page, limit),
                        new EntityWrapper<Receiver>()
                                .in("client_id", clients)
                                .eq("del_flag", "0")
                                .like("receiver_name", receiverName)
                                .orderBy("order_num")
                );
        return new Result(list);
    }

    @GetMapping(value = "/emailList")
    public Result emailList(String receiverName) {
        List<String> clients=tokenService.getClientIds(ServletUtils.getRequest());
        List<Receiver> list = receiverService.selectList(new EntityWrapper<Receiver>()
                                .in("client_id", clients)
                                .eq("del_flag", "0")
                                .like("receiver_name", receiverName)
                                .orderBy("order_num")
                );
        return new Result(list);
    }

    @ApiOperation(value = "增加", notes = "增加收货方 ")
    @PostMapping()
    public Result add(@RequestBody Receiver receiver) {
        String client_id = tokenService.getClientId(ServletUtils.getRequest());//客户id
        receiver.setClientId(client_id);
        receiver.setCreateTime(new Date());
        return new Result(receiverService.insert(receiver));
    }

    @ApiOperation(value = "修改", notes = "修改收货方 ")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody Receiver receiver) {
        receiver.setUpdateTime(new Date());
        return new Result(receiverService.updateById(receiver));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping
    public Result list(@RequestParam String id) {
        Receiver receiver = new Receiver();
        receiver.setReceiverId(id);
        receiver.setDelFlag("1");
        return new Result(receiverService.updateById(receiver));
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @PostMapping("del")
    public Result list(@RequestBody DelArray ids) {
        List<Receiver> list = new ArrayList<>();
        for (String id : ids.getIds()) {
            Receiver sender = new Receiver();
            sender.setReceiverId(id);
            sender.setDelFlag("1");
            list.add(sender);
        }
        return new Result(receiverService.updateBatchById(list));
    }

}