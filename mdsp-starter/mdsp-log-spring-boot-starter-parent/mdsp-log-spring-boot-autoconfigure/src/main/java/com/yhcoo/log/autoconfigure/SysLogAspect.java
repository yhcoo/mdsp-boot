package com.yhcoo.log.autoconfigure;

import cn.hutool.core.bean.BeanUtil;
import com.yhcoo.common.annotation.SysLog;
import com.yhcoo.common.constants.MqQueueNameConstant;
import com.yhcoo.common.dto.SysLogDTO;
import com.yhcoo.common.enums.OperationStatusEnum;
import com.yhcoo.common.util.UrlUtil;
import com.yhcoo.common.util.UserUtil;
import com.google.gson.Gson;
import com.yhcoo.log.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Slf4j
public class SysLogAspect {

//    @Autowired
//    private SysLogService sysLogService;


    @Around("execution(public com.yhcoo.common.util.ApiResult *(..))")
//    @Around(value = "@annotation(SysLog)")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        Object result = null;
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();

        long startTime = System.currentTimeMillis();
        SysLogDTO sysLogDTO = new SysLogDTO();
        // 需要记录日志存库
        if(targetMethod.isAnnotationPresent(SysLog.class)) {
            Gson gson = new Gson();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            // 获取注解
            SysLog sysLog = targetMethod.getAnnotation(SysLog.class);
            sysLogDTO
                    .setServiceId(sysLog.serviceId())
                    .setModuleName(sysLog.moduleName())
                    .setActionName(sysLog.actionName())
                    .setParams(gson.toJson(request.getParameterMap()))
                    .setRemoteAddr(UrlUtil.getRemoteHost(request))
                    .setMethod(request.getMethod())
                    .setRequestUri(request.getRequestURI())
                    .setUserAgent(request.getHeader("user-agent"));

            // 获取当前用户名
            String username = UserUtil.getUserName(request);
            sysLogDTO.setCreateBy(username);
        }
        try {
            result = pjp.proceed();
            sysLogDTO.setStatus(OperationStatusEnum.SUCCESS.getCode());
        } catch (Throwable e) {
            sysLogDTO.setException(UrlUtil.getTrace(e));
            sysLogDTO.setStatus(OperationStatusEnum.FAIL.getCode());
        }
        // 本次操作用时（毫秒）
        long elapsedTime = System.currentTimeMillis() - startTime;
        log.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);
        sysLogDTO.setTime(String.valueOf(elapsedTime));

        com.yhcoo.log.model.SysLog sysLog = new com.yhcoo.log.model.SysLog();
        BeanUtil.copyProperties(sysLogDTO, sysLog);
        sysLog.setCreateTime(new Date());
//        sysLogService.save(sysLog);
        return result;
    }

}
