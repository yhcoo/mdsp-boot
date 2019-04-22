package com.yhcoo.upms.controller;

import com.yhcoo.upms.model.dto.SysUserInfoDTO;
import com.yhcoo.upms.model.entity.SysUser;
import com.yhcoo.upms.model.query.SysUserVoQuery;
import com.yhcoo.upms.service.SysUserService;
import com.yhcoo.common.annotation.SysLog;
import com.yhcoo.common.constants.MdspServiceNameConstants;
import com.yhcoo.common.constants.SecurityConstants;
import com.yhcoo.common.enums.ResponseCodeEnum;
import com.yhcoo.common.enums.SmsMessageChannnelEnum;
import com.yhcoo.common.enums.SmsTemplateEnum;
import com.yhcoo.common.template.sms.SmsMessageTemplate;
import com.yhcoo.common.util.ApiResult;
import com.yhcoo.common.util.UserUtil;
import com.yhcoo.common.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用户controller", tags = {"用户操作接口"})
public class SysUserController {

    private static final String MODULE_NAME = "系统用户模块";

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @SysLog(serviceId = MdspServiceNameConstants.MDSP_USER_SERVICE, moduleName = MODULE_NAME, actionName = "根据token获取用户信息")
    @ApiOperation(value = "获取用户信息", notes = "用户详细信息，附带角色信息，权限信息", httpMethod = "GET")
    @GetMapping("/info")
    public ApiResult<SysUserInfoDTO> getInfo(){
        Long userId = UserUtil.getUserId(request);
        List<String> roles =UserUtil.getRoles(request);
        return new ApiResult<>(sysUserService.getUserInfo(userId, roles));
    }

    @SysLog(serviceId = MdspServiceNameConstants.MDSP_USER_SERVICE, moduleName = MODULE_NAME, actionName = "根据用户名获取用户信息")
    @ApiOperation(value = "根据用户名获取用户信息", notes = "用户详细信息，附带角色信息，权限信息", httpMethod = "GET")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string")
    @GetMapping("/loadUserByUsername/{username}")
    public SysUserVo loadUserByUsername(@PathVariable(value = "username") String username){
        return sysUserService.loadUserByUsername(username);
    }

    @ApiOperation(value = "根据mobile获取用户信息", notes = "用户详细信息，附带角色信息，权限信息", httpMethod = "GET")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "string")
    @GetMapping("/loadUserByMobile/{mobile}")
    public SysUserVo loadUserByMobile(@PathVariable(value = "mobile") String mobile){
        return sysUserService.loadUserByMobile(mobile);
    }

    @ApiOperation(value = "获取用户角色信息", notes = "根据token获取用户角色信息", httpMethod = "GET")
    @GetMapping("/roles")
    public ApiResult<List<String>> getRoles(){
        return new ApiResult<>(UserUtil.getRoles(request));
    }

    @SysLog(serviceId = MdspServiceNameConstants.MDSP_USER_SERVICE, moduleName = MODULE_NAME, actionName = "用户信息分页查询")
    @ApiOperation(value = "获取用户信息 分页查询", notes = "用户信息分页查询", httpMethod = "GET")
    @ApiImplicitParam(name = "query", value = "用户信息查询条件", required = false, dataType = "SysUserVoQuery")
    @GetMapping("/page")
    public ApiResult<SysUserVoQuery> pageByQuery(SysUserVoQuery query){
        return new ApiResult<>(sysUserService.pageUserVoByQuery(query));
    }

    @SysLog(serviceId = MdspServiceNameConstants.MDSP_USER_SERVICE, moduleName = MODULE_NAME, actionName = "用户信息分页查询")
    @ApiOperation(value = "添加用户", notes = "添加用户信息  带角色信息", httpMethod = "POST")
    @ApiImplicitParam(name = "sysUserVo", value = "用户信息", required = true, dataType = "SysUserVo")
    @PostMapping
    public ApiResult<Boolean> save(@RequestBody SysUserVo sysUserVo){
        return new ApiResult<>(sysUserService.save(sysUserVo));
    }

    @SysLog(serviceId = MdspServiceNameConstants.MDSP_USER_SERVICE, moduleName = MODULE_NAME, actionName = "修改用户信息")
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息 带角色信息", httpMethod = "PUT")
    @ApiImplicitParam(name = "sysUserVo", value = "用户信息", required = true, dataType = "SysUserVo")
    @PutMapping
    public ApiResult<Boolean> update(@RequestBody SysUserVo sysUserVo){
        return new ApiResult<>(sysUserService.update(sysUserVo));
    }

    @SysLog(serviceId = MdspServiceNameConstants.MDSP_USER_SERVICE, moduleName = MODULE_NAME, actionName = "删除用户信息")
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息", httpMethod = "DELETE")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    @DeleteMapping("/id/{id}")
    public ApiResult<Boolean> delete(@PathVariable("id") Long id){
        return new ApiResult<>(sysUserService.delete(id));
    }

    @SysLog(serviceId = MdspServiceNameConstants.MDSP_USER_SERVICE, moduleName = MODULE_NAME, actionName = "主键查询用户信息")
    @ApiOperation(value = "主键查询用户信息", notes = "查询用户信息", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    @GetMapping("/id/{id}")
    public ApiResult<SysUser> get(@PathVariable("id") Long id){
        SysUser u = sysUserService.getById(id);
        return new ApiResult<>(sysUserService.getById(id));
    }

    @ApiOperation(value = "发送登录验证码", notes = "发送登录验证码", httpMethod = "GET")
    @ApiImplicitParam(name = "mobile", value = "电话号码", required = true, dataType = "string")
    @GetMapping("/mobile/{mobile}")
    public ApiResult<String> sendMobileCode(@PathVariable("mobile") String mobile){
        Object originCode  = redisTemplate.opsForValue().get(SecurityConstants.REDIS_CODE_PREFIX + mobile);
        if(originCode != null) {
            log.info("手机号{}验证码{}尚未失效，请失效后再申请。", mobile, originCode);
            return new ApiResult<>("验证码尚未失效", ResponseCodeEnum.FAIL);
        }
        SysUserVo sysUserVo = sysUserService.loadUserByMobile(mobile);
        if(sysUserVo == null) {
            log.error("手机号为{} 用户不存在", mobile);
            return new ApiResult<String>("手机号不存在", ResponseCodeEnum.FAIL);
        }
        String code = RandomStringUtils.random(4, false, true);
        String[] params = {code};
        SmsMessageTemplate smsMessageTemplate = new SmsMessageTemplate();
        smsMessageTemplate.setParams(params);
        smsMessageTemplate.setMobile(mobile);
        smsMessageTemplate.setSignName(SmsTemplateEnum.LOGIN_CODE.getSignName());
        smsMessageTemplate.setTemplate(SmsTemplateEnum.LOGIN_CODE.getTempalte());
        smsMessageTemplate.setChannel(SmsMessageChannnelEnum.TENCENT_CLOUD.getCode());

        // 发送消息处理中心
//        rabbitTemplate.convertAndSend(MqQueueNameConstant.MOBILE_CODE_QUEUE,smsMessageTemplate);
        // 存redis
        redisTemplate.opsForValue().set(SecurityConstants.REDIS_CODE_PREFIX+mobile, Integer.valueOf(code), SecurityConstants.REDIS_CODE_EXPIRE, TimeUnit.SECONDS);
        return new ApiResult<>(code);
    }




}
