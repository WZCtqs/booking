package com.zih.booking.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.model.ActualSender;
import com.zih.booking.service.ActualSenderService;
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
 * 实际发货方 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-02-26
 */
@RestController
@Api(tags = "实际发货方")
@RequestMapping("/actualSender")
public class ActualSenderController {
    @Autowired
    ActualSenderService actualSenderService;
    @Autowired
    TokenService tokenService;

    @ApiOperation(value = "列表",notes= "列表")
    @GetMapping(value = "/list")
    public Result list(@RequestParam Integer limit, @RequestParam Integer page,String actualMan,String goBack ) {
        List<String> clients=tokenService.getClientIds(ServletUtils.getRequest());
        //String client_id=tokenService.getClientId(ServletUtils.getRequest());//客户id
        Page<ActualSender> list =
                actualSenderService.selectPage(new Page<>(page,limit),
                        new EntityWrapper<ActualSender>()
                                .in("client_id",clients)
                                .eq("del_flag","0").like("actual_man",actualMan)
                                .like("go_back",goBack)
                                .orderBy("order_num")
                                );
        return new Result (list );
    }

    @ApiOperation(value = "增加",notes= "增加收货方 ")
    @PostMapping()
    public Result add(@RequestBody ActualSender actualSender) {
        String client_id=tokenService.getClientId(ServletUtils.getRequest());//客户id
        actualSender.setClientId(client_id);
        actualSender.setCreateTime(new Date());
        return  new Result(actualSenderService.insert(actualSender));
    }

    @ApiOperation(value = "修改",notes= "修改收货方 ")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody  ActualSender actualSender) {
        actualSender.setUpdateTime(new Date());
        return  new Result(actualSenderService.updateById(actualSender));
    }

    @ApiOperation(value = "删除",notes= "删除")
    @DeleteMapping
    public Result list(@RequestParam String id) {
        ActualSender actualSender=new ActualSender();
        actualSender.setActSenderId(id);
        actualSender.setDelFlag("1");
        return  new Result(actualSenderService.updateById(actualSender));
    }
    @ApiOperation(value = "批量删除", notes = "批量删除")
    @PostMapping("del")
    public Result list(@RequestBody DelArray ids) {
        List<ActualSender> list = new ArrayList<>();
        for (String id : ids.getIds()) {
            ActualSender sender = new ActualSender();
            sender.setActSenderId(id);
            sender.setDelFlag("1");
            list.add(sender);
        }
        return  new Result(actualSenderService.updateBatchById(list));
    }
}

