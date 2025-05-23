package nh.focus.common.util;

import lombok.Data;
import nh.focus.common.constant.ResultCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Description:结果集封装
 * @Author: ni_hao
 * @Date: 2025-04-23 15:49
 */
@Data
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    // 状态码
    private String code;
    // 信息描述
    private String msg;
    // 数据
    private T data;

    // 成功响应
    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.code());
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.code());
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(String msg, T data, String code) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


    // 失败响应
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SYSTEM_ERROR.code());
        result.setMsg(msg);
        return result;
    }

    public static <T> Result<T> error(String msg, String code) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
