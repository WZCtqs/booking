package com.zih.booking.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zih.booking.model.Goods;
import com.zih.booking.service.GoodsService;
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
 * 货物信息表 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-02-26
 */
@RestController
@Api(tags = "客户货物信息")
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired private TokenService tokenService;

    @ApiOperation(value = "列表",notes= " 货物信息列表")
    @GetMapping(value = "/list")
    public Result list(@RequestParam Integer limit, @RequestParam Integer page ,String goodsEnName,String goodsName,String goBack) {
        List<String> clients=tokenService.getClientIds(ServletUtils.getRequest());
        Page<Goods> list =
                goodsService.selectPage(new Page<>(page,limit),
                        new EntityWrapper<Goods>()
                                .in("client_id",clients)
                                .eq("del_flag","0")
                                .like("goods_name",goodsName)
                                .like("goods_en_name",goodsEnName)
                                .like("go_back",goBack)
                                .orderBy("order_num")
                                 );
        return new Result (list );
    }

    @ApiOperation(value = "增加",notes= "增加  ")
    @PostMapping()
    public Result add(@RequestBody Goods goods) {
        String client_id=tokenService.getClientId(ServletUtils.getRequest());//客户id
        goods.setClientId(client_id);
        goods.setCreateTime(new Date());
        return  new Result(goodsService.insert(goods));
    }

    @ApiOperation(value = "修改",notes= "修改  ")
    @PutMapping(value = "/edit")
    public Result edit(@RequestBody  Goods goods) {
        goods.setUpdateTime(new Date());
        return  new Result(goodsService.updateById(goods));
    }

    @ApiOperation(value = "删除",notes= "删除")
    @DeleteMapping
    public Result list(@RequestParam String id) {
        Goods goods=new Goods();
        goods.setGoodsId(id);
        goods.setDelFlag("1");
        return  new Result(goodsService.updateById(goods));
    }
    @ApiOperation(value = "批量删除", notes = "批量删除")
    @PostMapping("del")
    public Result list(@RequestBody DelArray ids) {
        List<Goods> list = new ArrayList<>();
        for (String id : ids.getIds()) {
            Goods sender = new Goods();
            sender.setGoodsId(id);
            sender.setDelFlag("1");
            list.add(sender);
        }
        return  new Result(goodsService.updateBatchById(list));
    }
}

