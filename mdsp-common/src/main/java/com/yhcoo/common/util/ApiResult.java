package com.yhcoo.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhcoo.common.enums.ResponseCodeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 统一响应信息主体
 */
@Data
public class ApiResult<T> implements Serializable {

    private T data;

    private Integer code = ResponseCodeEnum.SUCCESS.getCode();

    private String message = ResponseCodeEnum.SUCCESS.getMessage();

    public ApiResult() {
    }

    public ApiResult(T data) {
        this.data = data;
    }

    public ApiResult(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public ApiResult(T data, ResponseCodeEnum responseCode) {
        this.data = data;
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public ApiResult(Throwable throwable) {
        this.message = throwable.getMessage();
        this.code = ResponseCodeEnum.FAIL.getCode();
    }

    public ApiResult(Throwable throwable, ResponseCodeEnum  code) {
        this.message = throwable.getMessage();
        this.code = code.getCode();
    }

    public static ApiResult replaceRecords(Page qresult, List records) {
        Page page = new Page<>();
        page.setRecords(records);
        page.setCurrent(qresult.getCurrent());
        page.setSize(qresult.getSize());
        page.setPages(qresult.getPages());
        page.setTotal(qresult.getTotal());
        return new ApiResult(page);
    }

    public static ApiResult ok() {
        return new ApiResult<>();
    }

    public static ApiResult ok(Object data) {
        return new ApiResult<>(data);
    }

    public static ApiResult setMsg(String message) {
        ApiResult r = ApiResult.ok(null);
        r.setMessage(message);
        return r;
    }

    public static ApiResult ok(Object data, String message) {
        ApiResult r = ApiResult.ok(data);
        r.setMessage(message);
        return r;
    }

    public static ApiResult fail(int code, String message) {
        ApiResult obj = new ApiResult();
        obj.setCode(code);
        obj.setMessage(message);
        return obj;
    }

    public static Object badArgument() {
        return fail(401, "参数不对");
    }

    public static ApiResult badArgumentValue() {
        return fail(402, "参数值不对");
    }

    public static ApiResult unlogin() {
        return fail(501, "请登录");
    }

    public static ApiResult serious() {
        return fail(502, "系统内部错误");
    }

    public static ApiResult unsupport() {
        return fail(503, "业务不支持");
    }

    public static ApiResult updatedDateExpired() {
        return fail(504, "更新数据已经失效");
    }

    public static ApiResult updatedDataFailed() {
        return fail(505, "更新数据失败");
    }

    public static Object unauthz() {
        return fail(506, "无操作权限");
    }


}
