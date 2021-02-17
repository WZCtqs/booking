package com.zih.booking.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zih.booking.model.BusiBoxfee;
import com.zih.booking.model.SystemDict;
import com.zih.booking.response.BoxResponse;
import com.zih.booking.response.DictResponse;
import com.zih.booking.service.BusiBoxfeeService;
import com.zih.booking.service.SystemDictService;
import com.zih.booking.system.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 提还箱地和费用规则 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-04-03
 */
@Api(tags = "提还箱地列表相关接口")
@RestController
@RequestMapping("/busiBoxfee")
public class BusiBoxfeeController {
    @Autowired
    BusiBoxfeeService boxfeeService;
    @Autowired
    SystemDictService dictService;

    @ApiOperation(value = "提还箱地列表",notes= "提还箱地列表 选择类型：0,提箱；1,还箱")
    @GetMapping(value = "/boxList")
    public Result boxList(@RequestParam String type,  String intra, @RequestParam String language , String containerType,String lineType) {

        Date date =new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String today=format.format(date);
        EntityWrapper<BusiBoxfee> ew = new EntityWrapper<>();
        ew.eq("address_type",type)
          .eq("state",1)
          .le("start_time",today).ge("end_time",today)
          .eq("container_type",containerType)
          .like("line_typeid",lineType);
        if ("1".equals(intra)) {
            ew.addFilter("(intra = '1' OR address = '釜山' OR address = '仁川' OR address = '大阪' OR address = '东京' OR address = '海防')");
        } else {
            ew.eq("intra", intra);
        }
        List<BusiBoxfee>list= boxfeeService.selectList(ew);
        List<BoxResponse> responses=new ArrayList<>();
        for (BusiBoxfee box:list) {
            BoxResponse response=new BoxResponse();
            response.setId(box.getBoxId());
            response.setName(box.getAddress());
            if("en".equalsIgnoreCase(language)){//英文
                response.setName(box.getAddressEn());
            }
            response.setCost(box.getCost());
            responses.add(response);
        }
        // 去重
        Set<BoxResponse> ts = new HashSet<>();
        ts.addAll(responses);
        return new Result (new ArrayList<>(ts));
    }

    @ApiOperation(value = "贸易方式列表",notes= " ")
    @GetMapping(value = "/busiDictList")
    public Result busiDictList(  ) {
        List<SystemDict>list= dictService.selectList(new EntityWrapper<SystemDict>().eq("fid",1));
        List<DictResponse> responses=new ArrayList<>();
        for (SystemDict dict:list) {
            DictResponse response=new DictResponse();
            response.setId(dict.getId());
            response.setName(dict.getmCheng());
            responses.add(response);
        }
        return new Result (responses);
    }

}

