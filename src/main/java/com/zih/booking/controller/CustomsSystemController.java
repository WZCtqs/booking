package com.zih.booking.controller;

import com.zih.booking.request.CustomsRequest;
import com.zih.booking.response.CustomsResponse;
import com.zih.booking.service.DocOrderDocumentService;
import com.zih.booking.system.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customs")
@Api(tags = "关务系统N-2 N-3 查询")
public class CustomsSystemController {

    @Autowired
    private DocOrderDocumentService docOrderDocumentService;

    @ApiOperation("关务系统进口随车文件数据完成度查询 （班列N-2 N-3）")
    @GetMapping("/list")
    public Result getCustomsDataList(CustomsRequest customsRequest) {

         List<CustomsResponse> list = docOrderDocumentService.getCustomsDataList(customsRequest);

         return new Result<>(list);
    }

}
