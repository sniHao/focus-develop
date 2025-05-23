package nh.focus.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nh.focus.common.constant.ResultCode;

/**
 * @Description: 自定义异常类
 * @Author: ni_hao
 * @Date: 2025-04-23 15:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NhException extends Exception {
    private String msg;
    private String code;

    public NhException(String msg) {
        this.msg = msg;
        this.code = ResultCode.SYSTEM_ERROR.code();
    }

    public NhException(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
}
