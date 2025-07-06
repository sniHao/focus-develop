package com.focus.api.common.util;

import com.focus.domain.common.constant.FocusResultCode;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:结果集封装
 * @Author: ni_hao
 * @Date: 2025-04-23 15:49
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
    public static <T> FocusResult<T> success(String msg) {
        FocusResult<T> focusResult = new FocusResult<>();
        focusResult.setCode(FocusResultCode.SUCCESS.code());
        focusResult.setMsg(msg);
        return focusResult;
    }

    public static <T> FocusResult<T> success(T data) {
        FocusResult<T> focusResult = new FocusResult<>();
        focusResult.setCode(FocusResultCode.SUCCESS.code());
        focusResult.setMsg("ok");
        focusResult.setData(data);
        return focusResult;
    }
    public static <T> FocusResult<T> success(String msg, T data) {
        FocusResult<T> focusResult = new FocusResult<>();
        focusResult.setCode(FocusResultCode.SUCCESS.code());
        focusResult.setMsg(msg);
        focusResult.setData(data);
        return focusResult;
    }

    public static <T> FocusResult<T> success(String msg, T data, String code) {
        FocusResult<T> focusResult = new FocusResult<>();
        focusResult.setCode(code);
        focusResult.setMsg(msg);
        focusResult.setData(data);
        return focusResult;
    }


    // 失败响应
    public static <T> FocusResult<T> error(String msg) {
        FocusResult<T> focusResult = new FocusResult<>();
        focusResult.setCode(FocusResultCode.SYSTEM_ERROR.code());
        focusResult.setMsg(msg);
        return focusResult;
    }

    public static <T> FocusResult<T> error(String msg, String code) {
        FocusResult<T> focusResult = new FocusResult<>();
        focusResult.setCode(code);
        focusResult.setMsg(msg);
        return focusResult;
    }
}
