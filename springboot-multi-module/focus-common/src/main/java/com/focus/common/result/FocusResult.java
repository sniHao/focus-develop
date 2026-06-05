package com.focus.common.result;

import com.focus.common.constant.FocusResultCode;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一响应结果封装
 */
@Data
public class FocusResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // 状态码
    private String code;
    // 信息描述
    private String msg;
    // 数据
    private T data;

    // 成功响应
    public static <T> FocusResult<T> success() {
        FocusResult<T> result = new FocusResult<>();
        result.setCode(FocusResultCode.SUCCESS.code());
        result.setMsg("ok");
        return result;
    }

    public static <T> FocusResult<T> success(T data) {
        FocusResult<T> result = new FocusResult<>();
        result.setCode(FocusResultCode.SUCCESS.code());
        result.setMsg("ok");
        result.setData(data);
        return result;
    }

    public static <T> FocusResult<T> success(String msg, T data) {
        FocusResult<T> result = new FocusResult<>();
        result.setCode(FocusResultCode.SUCCESS.code());
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> FocusResult<T> success(String msg, T data, String code) {
        FocusResult<T> result = new FocusResult<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    // 失败响应
    public static <T> FocusResult<T> error(String msg) {
        FocusResult<T> result = new FocusResult<>();
        result.setCode(FocusResultCode.SYSTEM_ERROR.code());
        result.setMsg(msg);
        return result;
    }

    public static <T> FocusResult<T> error(String msg, String code) {
        FocusResult<T> result = new FocusResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
