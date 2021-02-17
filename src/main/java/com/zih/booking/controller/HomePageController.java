package com.zih.booking.controller;


import com.zih.booking.dao.BusiShippingorderMapper;

import com.zih.booking.response.HomePageResponse;
import com.zih.booking.service.BusiShippingorderService;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.Result;
import com.zih.booking.utils.DateUtils;
import com.zih.booking.utils.ServletUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "AAA首页")
@RequestMapping("/homePage")
public class HomePageController {
    @Autowired
    private BusiShippingorderService busiShippingorderService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BusiShippingorderMapper busiShippingorderMapper;
    @GetMapping(value = "/remove")
    public Result remove(String today) {
        String clientId = tokenService.getClientId(ServletUtils.getRequest());
        return new Result(redisTemplate.delete(clientId + "statistics") + today);
    }

    @ApiOperation(value = "首页30天数据", notes = "首页数据统计")
    @GetMapping(value = "/statistics")
    public Result statistics() {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String clientId = tokenService.getClientId(ServletUtils.getRequest());
        List<HomePageResponse> list = new ArrayList<>();
        if (!redisTemplate.hasKey(clientId + "statistics" + today)) {
            List<String> dates = DateUtils.getDaysBetwwen(29);
            for (String date : dates) {
                HomePageResponse homePageResponse = getHomePageByDate(date);
                list.add(homePageResponse);
            }
            redisTemplate.opsForValue().set(clientId + "statistics" + today, list, 1, TimeUnit.DAYS);
        }
        list = (List<HomePageResponse>) redisTemplate.opsForValue().get(clientId + "statistics" + today);//前面29天从redis取
        HomePageResponse homePageResponse = getHomePageByDate(today);//今天的每次刷新查询
        list.add(homePageResponse);
        return new Result(list);
    }

    private HomePageResponse getHomePageByDate(String date) {
        List<String> clients = tokenService.getClientIds(ServletUtils.getRequest());
        HomePageResponse homePageResponse = busiShippingorderMapper.getTodayCount(date,clients);
        homePageResponse.setDate(date);
        return homePageResponse;
    }
}
//        Integer count = busiShippingorderService.selectCount(new EntityWrapper<BusiShippingorder>()
//                .in("client_ID", clients)
//                .addFilter("DATE_FORMAT(order_audit_createdate,'%Y-%m-%d') = " +'"'+ date+ '"'));//订舱时间
//        Integer count2 = busiShippingorderService.selectCount(new EntityWrapper<BusiShippingorder>()
//                .in("client_ID", clients)
//                .eq("IsExamline", "1")//审核通过
//                .addFilter("DATE_FORMAT(Exame_time,'%Y-%m-%d') = " + '"'+date+ '"'));//审核时间
//        Integer count3 = busiShippingorderService.selectCount(new EntityWrapper<BusiShippingorder>()
//                .in("client_ID", clients)
//                .eq("IsExamline", "2")//审核未通过
//                .addFilter("DATE_FORMAT(Exame_time,'%Y-%m-%d') = " +'"'+ date+ '"'));//审核时间
//        homePageResponse.setCount(count);
//        homePageResponse.setCount2(count2);
//        homePageResponse.setCount3(count3);