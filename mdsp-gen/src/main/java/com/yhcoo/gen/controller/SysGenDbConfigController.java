package com.yhcoo.gen.controller;


import lombok.extern.slf4j.Slf4j;
import com.yhcoo.common.annotation.SysLog;
import com.yhcoo.common.constants.MdspServiceNameConstants;
import com.yhcoo.common.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.yhcoo.common.util.Assert;

import com.yhcoo.gen.model.entity.SysGenDbConfig;
import com.yhcoo.gen.model.query.SysGenDbConfigQuery;
import com.yhcoo.gen.service.SysGenDbConfigService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Collection;

/**
 * @author kingkey
 */
@Slf4j
@RestController
@RequestMapping("/sysGenDbConfig")
@Api(value = "代码生成表 Controller", tags = {"代码生成表操作接口"})
public class SysGenDbConfigController {

  private static final String MODULE_NAME = "代码生成表模块";
  private static final String SERVICE_ID = MdspServiceNameConstants.MDSP_GEN_SERVICE;

  @Autowired
  private SysGenDbConfigService sysGenDbConfigService;


  @SysLog(serviceId = SERVICE_ID, moduleName = MODULE_NAME, actionName = "代码生成表添加")
  @ApiOperation(value = "添加", notes = "添加代码生成表信息", httpMethod = "POST")
  @ApiImplicitParam(name = "sysGenDbConfig", value = "查询条件sysGenDbConfig", required = true, dataType = "SysGenDbConfig")
  @PostMapping("add")
  public ApiResult<Boolean> add(@RequestBody SysGenDbConfig sysGenDbConfig){
    return new ApiResult<>(sysGenDbConfigService.save(sysGenDbConfig));
  }

  @SysLog(serviceId = SERVICE_ID, moduleName = MODULE_NAME, actionName = "代码生成表修改")
  @ApiOperation(value = "修改", notes = "修改代码生成表信息", httpMethod = "POST")
  @ApiImplicitParam(name = "id", value = "查询条件ID", required = true, dataType = "SysGenDbConfig")
  @PostMapping("update")
  public ApiResult<Boolean> update(@RequestBody SysGenDbConfig sysGenDbConfig){
    Assert.isBlank(sysGenDbConfig.getId(),"ID不能为空");
    return new ApiResult<>(sysGenDbConfigService.updateById(sysGenDbConfig));
  }


  @SysLog(serviceId = SERVICE_ID, moduleName = MODULE_NAME, actionName = "代码生成表主键删除")
  @ApiOperation(value = "主键删除", notes = "主键删除代码生成表信息", httpMethod = "POST")
  @ApiImplicitParam(name = "id", value = "查询条件ID", required = true, dataType = "Long")
  @PostMapping("delete")
  public ApiResult<Boolean> deleteById(Long id){
    Assert.isBlank( id,"ID不能为空");
    return new ApiResult<>(sysGenDbConfigService.removeById(id));
  }


  @SysLog(serviceId = SERVICE_ID, moduleName = MODULE_NAME, actionName = "代码生成表信息分页查询")
  @ApiOperation(value = "主键查询", notes = "主键查询代码生成表信息", httpMethod = "GET")
  @ApiImplicitParam(name = "id", value = "查询条件ID", required = true, dataType = "Long")
  @GetMapping("get")
  public ApiResult<SysGenDbConfig> getById(Long id){
    Assert.isBlank( id,"ID不能为空");
    return new ApiResult(sysGenDbConfigService.getById(id));
  }


  @SysLog(serviceId = SERVICE_ID, moduleName = MODULE_NAME, actionName = "代码生成表信息分页查询")
  @ApiOperation(value = "获取代码生成表分页查询", notes = "代码生成表信息分页查询", httpMethod = "GET")
  @ApiImplicitParam(name = "query", value = "用户信息查询条件", required = false, dataType = "SysGenDbConfigQuery")
  @GetMapping("/page")
  public ApiResult<SysGenDbConfigQuery> pageByQuery(SysGenDbConfigQuery query){
    return new ApiResult(sysGenDbConfigService.pageByQuery(query));
  }

  @SysLog(serviceId = SERVICE_ID, moduleName = MODULE_NAME, actionName = "代码生成表查询所有")
  @ApiOperation(value = "查询所有信息", notes = "查询所有信息", httpMethod = "GET")
  @GetMapping("all")
  public ApiResult<Collection> selectAll(){
    return new ApiResult<>(sysGenDbConfigService.listByMap(new HashMap<>()));
  }


}
