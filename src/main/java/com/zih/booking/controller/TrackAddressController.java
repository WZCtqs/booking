package com.zih.booking.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zih.booking.model.TrackAddress;
import com.zih.booking.service.TrackAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 运踪_位置地名管理--沿途运踪使用 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/trackAddress")
@Api("/运踪_位置地名管理")
public class TrackAddressController {
    @Autowired
    private TrackAddressService trackAddressService;


}

