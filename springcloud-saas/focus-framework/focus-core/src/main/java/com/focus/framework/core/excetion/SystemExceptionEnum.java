package com.focus.framework.core.excetion;


import lombok.Getter;


@Getter
public enum SystemExceptionEnum implements IBaseEnum {
    SYSTEM_ERROR_200001(200001, "系统错误"),
    SYSTEM_ERROR_200002(200002, "操作失败"),
    SYSTEM_ERROR_200003(200003, "服务异常，请稍候重试或联系客服"),
    SYSTEM_ERROR_200004(200004, "网络请求错误"),
    SYSTEM_ERROR_200005(200005, "无效请求"),
    SYSTEM_ERROR_200006(200006, "非法请求"),
    SYSTEM_ERROR_200007(200007, "重复请求"),
    SYSTEM_ERROR_200008(200008, "系统维护中"),

    SYSTEM_ERROR_200009(200009, "该账号无此操作权限"),
    ;

    SystemExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 编码
     */
    public final int code;
    /**
     * 信息
     */
    public final String msg;
}
