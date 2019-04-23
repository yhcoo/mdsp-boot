package com.yhcoo.log.controller;

import com.yhcoo.common.util.ApiResult;
import com.yhcoo.log.model.entity.SysLog;
import com.yhcoo.log.model.query.SysLogQuery;
import com.yhcoo.log.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Api(value = "日志controller", tags = {"系统日志操作接口"})
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @ApiOperation(value = "日志信息分页查询", notes = "日志信息分页查询", httpMethod = "GET")
    @ApiImplicitParam(name = "sysLogQuery", value = "日志信息查询类", required = false, dataType = "SysLogQuery")
    @GetMapping("/page")
    public ApiResult<SysLogQuery> pageByQuery(SysLogQuery sysLogQuery){
        return new ApiResult<>(sysLogService.pageByQuery(sysLogQuery));
    }

    @ApiOperation(value = "插入操作日志", notes = "插入操作日志", httpMethod = "POST")
    @ApiImplicitParam(name = "sysLog", value = "插入操作日志", required = true, dataType = "ApiResult")
    @PostMapping("/save")
    public ApiResult save(SysLog sysLog){
        return new ApiResult<>(sysLogService.save(sysLog));
    }

}