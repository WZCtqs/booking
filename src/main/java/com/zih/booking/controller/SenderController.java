package com.zih.booking.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.model.Sender;
import com.zih.booking.service.SenderService;
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
@RequestMapping("/sender")
@Api(tags = "发货方相关接口")
public class SenderController {
    @Autowired
    SenderService senderService;
    @Autowired
    private TokenService tokenService;

    @ApiOperation(value = "列表", notes = "发货方列表")
    @GetMapping(value = "/list")
    public Result list(@RequestParam Integer limit, @RequestParam Integer page, @RequestParam(required = false) String senderName) {
        List<String> clients=tokenService.getClientIds(ServletUtils.getRequest());
        Page<Sender> list =
                senderService.selectPage(new Page<>(page, limit),
                        new EntityWrapper<Sender>()
                                .in("client_id", clients)
                                .eq("del_flag", "0")
                                .like("sender_name", senderName)
                                .orderBy("order_num")
                );
        return new Result(list);
    }

    @ApiOperation(value = "增加", notes = "增加发货方 ")
    @PostMapping()
    public Result add(@RequestBody Sender sender) {
        String client_id = tokenService.getClientId(ServletUtils.getRequest());//客户id
        sender.setClientId(client_id);
        sender.setCreateTime(new Date());
        return new Result(senderService.insert(sender));
    }

    @ApiOperation(value = "修改", notes = "修改发货方 ")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody Sender sender) {
        sender.setUpdateTime(new Date());
        return new Result(senderService.updateById(sender));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping
    public Result list(@RequestParam String id) {
        Sender sender = new Sender();
        sender.setSenderId(id);
        sender.setDelFlag("1");
        return new Result(senderService.updateById(sender));
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @PostMapping("del")
    public Result list(@RequestBody DelArray ids) {
        List<Sender> list = new ArrayList<>();
        for (String id : ids.getIds()) {
            Sender sender = new Sender();
            sender.setSenderId(id);
            sender.setDelFlag("1");
            list.add(sender);
        }
        return new Result(senderService.updateBatchById(list));
    }
}

