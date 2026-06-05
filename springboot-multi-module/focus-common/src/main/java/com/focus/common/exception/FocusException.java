package com.focus.common.exception;

import com.focus.common.constant.FocusResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义业务异常类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FocusException extends RuntimeException {
    private String msg;
    private String code;

    public FocusException(String msg) {
        this.msg = msg;
        this.code = FocusResultCode.SYSTEM_ERROR.code();
    }

    public FocusException(FocusResultCode resultCode) {
        this.msg = resultCode.tips();
        this.code = resultCode.code();
    }

    public FocusException(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
}
