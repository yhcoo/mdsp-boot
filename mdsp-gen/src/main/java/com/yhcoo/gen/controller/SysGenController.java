package com.yhcoo.gen.controller;


import com.yhcoo.common.annotation.SysLog;
import com.yhcoo.common.constants.MdspServiceNameConstants;
import com.yhcoo.common.util.ApiResult;
import com.yhcoo.gen.model.dto.BuildConfigDTO;
import com.yhcoo.gen.model.query.TableInfoQuery;
import com.yhcoo.gen.service.SysGenService;
import com.yhcoo.gen.service.TableInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
//@RequestMapping("/gen")
@Api(value = "代码生成controller", tags = {"代码生成接口管理"})
public class SysGenController {

    private static final String MODULE_NAME = "代码生成模块";


    @Autowired
    private TableInfoService tableInfoService;

    @Autowired
    private SysGenService sysGenService;

    @SysLog(serviceId = MdspServiceNameConstants.MDSP_GEN_SERVICE, moduleName = MODULE_NAME, actionName = "分页查询数据库中所有的表信息")
    @ApiOperation(value = "分页查询数据库中所有的表信息", notes = "分页查询数据库中所有的表信息", httpMethod = "GET")
    @ApiImplicitParam(name = "query", value = "表信息查询条件", required = false, dataType = "TableInfoQuery")
    @ResponseBody
    @GetMapping("/code/page")
    public ApiResult<TableInfoQuery> page(TableInfoQuery query){
        return new ApiResult<>(tableInfoService.pageByQuery(query));
    }


    @SysLog(serviceId = MdspServiceNameConstants.MDSP_GEN_SERVICE, moduleName = MODULE_NAME, actionName = "根据表名称生成代码  返回zip包")
    @ApiOperation(value = "根据表名称生成代码", notes = "根据表名称生成代码  返回zip包", httpMethod = "POST")
    @ApiImplicitParam(name = "buildConfigDTO", value = "表配置", required = true, dataType = "BuildConfigDTO")
    @PostMapping("/code/build")
    public void code(@RequestBody BuildConfigDTO buildConfigDTO, HttpServletResponse response) throws IOException {
        buildConfigDTO.setBuildCodeType(1);
        byte[] data = sysGenService.genCodeByTableName(buildConfigDTO);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

    @SysLog(serviceId = MdspServiceNameConstants.MDSP_GEN_SERVICE, moduleName = MODULE_NAME, actionName = "根据表名称生成代码  返回zip包")
    @ApiOperation(value = "根据表名称生成代码", notes = "根据表名称生成代码  返回zip包", httpMethod = "POST")
    @ApiImplicitParam(name = "buildConfigDTO", value = "表配置", required = true, dataType = "BuildConfigDTO")
    @PostMapping("/code/buildall")
    public ApiResult codeBuildAll(@RequestBody BuildConfigDTO buildConfigDTO) {
        buildConfigDTO.setBuildCodeType(2);
        sysGenService.genCodeByTableName(buildConfigDTO);
        return new ApiResult<>(null,"代码已经写入项目");
    }

}
