package com.zih.booking.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import com.zih.booking.dao.BusiShippingorderMapper;
import com.zih.booking.model.BusiShippingorder;
import com.zih.booking.model.ProblemFeedback;
import com.zih.booking.model.ProblemFile;

import com.zih.booking.request.ProblemFeedbackRequest;

import com.zih.booking.service.ProblemFeedbackService;
import com.zih.booking.service.ProblemFileService;
import com.zih.booking.system.token.TokenService;
import com.zih.booking.system.vo.ApiResultI18n;
import com.zih.booking.system.vo.Result;
import com.zih.booking.utils.ServletUtils;
import com.zih.booking.utils.Upload;
import com.zih.booking.vo.ProblemFileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 问题反馈 前端控制器
 * </p>
 *
 * @author wsl123
 * @since 2020-02-27
 */
@RestController
@Api(tags = "问题反馈")
@RequestMapping("/problemFeedback")
public class ProblemFeedbackController {
    @Autowired
    ProblemFeedbackService problemService;
    @Autowired
    ProblemFileService fileService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private BusiShippingorderMapper busiShippingorderMapper;


    @ApiOperation(value = "列表", notes = " 问题反馈")
    @GetMapping(value = "/list")
    public Result list(@RequestParam Integer limit, @RequestParam Integer page, String orderNumber, String status) {
        List<String> clients=tokenService.getClientIds(ServletUtils.getRequest());
        Page<ProblemFeedback> list =
                problemService.selectPage(new Page<>(page, limit),
                        new EntityWrapper<ProblemFeedback>()
                                          .in("client_id",clients)
                                .eq("del_flag", "0")
                                .like("order_number", orderNumber)
                                .like("status", status)
                );
        return new Result(list);
    }

    @ApiOperation(value = "详情", notes = " 问题反馈详情")
    @GetMapping(value = "/detail")
    public Result list(@RequestParam String problemId) {
        return new Result(problemService.selectById(problemId));
    }

    @ApiOperation(value = "获取全部文件", notes = " 文件url")
    @GetMapping(value = "/getFileUrls")
    public Result getFileUrls(@RequestParam String problemId) {
        return new Result(fileService.selectList(new EntityWrapper<ProblemFile>().eq("problem_id", problemId)));
    }

    @ApiOperation(value = "增加", notes = "增加")
    @PostMapping
    public Result add(@RequestBody ProblemFeedbackRequest problemFeedbackRequest) {
        String client_id = tokenService.getClientId(ServletUtils.getRequest());//客户id
        List<BusiShippingorder> list = busiShippingorderMapper.selectList(new EntityWrapper<BusiShippingorder>().eq("order_number", problemFeedbackRequest.getProblemFeedback().getOrderNumber()));
        if (list.size() == 0) {
            return new Result(1, "仓位号不存在");
        } else {
            ProblemFeedback problemFeedback = problemFeedbackRequest.getProblemFeedback();
            problemFeedback.setCreateTime(new Date());
            problemFeedback.setStatus("0");
            problemFeedback.setClientId(client_id);
            problemService.insert(problemFeedback);
            String id = problemFeedback.getProblemId();
            for (ProblemFileVo url : problemFeedbackRequest.getUrls()) {
                ProblemFile file = new ProblemFile();
                file.setFileUrl(url.getUrl());
                file.setFileName(url.getFileName());
                file.setCreateTime(new Date());
                file.setProblemId(id);
                file.setType("0");
                fileService.insert(file);
            }
            return new Result(true);
        }
    }


    @ApiOperation(value = "多文件上传", notes = "多文件上传 ")
    @PostMapping("upload")
    public Result upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) {
        String path ="problemFeedback";
        return new Result(Upload.filesUpload(file,path, request));
    }

    @ApiOperation(value = "上传补充资料", notes = "上传补充资料  ")
    @PostMapping("uploadSupplement")
    public Result uploadSupplement(@RequestParam String problemId, @RequestParam("file") MultipartFile[] file, HttpServletRequest request) {
        String path ="problemFeedback";
        ProblemFileVo[] urls = Upload.filesUpload(file,path, request);
        List<ProblemFile> list = new ArrayList<>();
        for (ProblemFileVo url : urls) {
            ProblemFile problemFile = new ProblemFile();
            problemFile.setFileUrl(url.getUrl());
            problemFile.setFileName(url.getFileName());
            problemFile.setCreateTime(new Date());
            problemFile.setProblemId(problemId);
            problemFile.setType("2");
            list.add(problemFile);
            // fileService.insert(file);
        }
        return new Result(fileService.insertBatch(list));
    }

}

