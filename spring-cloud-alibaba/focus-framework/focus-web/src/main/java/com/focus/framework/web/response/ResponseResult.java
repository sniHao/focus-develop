package com.focus.framework.web.response;

import cn.hutool.core.util.ObjUtil;
import com.focus.framework.core.excetion.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 返回结果
 *
 * @author carldong
 * @date 2024/9/10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {

    /**
     * 响应返回编码 不为空
     */
    private int code;

    /**
     * 响应返回信息 不为空
     */
    private String msg;

    /**
     * 返回结果
     */
    private T data;

    /**
     * 创建时间,处理json的时间参数解析
     */
    // @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date operationTime;

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        if (ObjUtil.isNotNull(data)) {
            this.data = data;
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResponseResult success() {
        return ResponseResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResponseResult success(Object data) {
        return ResponseResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseResult success(String msg, Object data) {
        return new ResponseResult(200, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ResponseResult error() {
        return ResponseResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseResult error(String msg) {
        return ResponseResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseResult error(String msg, Object data) {
        return new ResponseResult(500, msg, data);
    }


    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param code 返回的错误码
     * @return 警告消息
     */
    public static ResponseResult error(Integer code, String msg) {
        return new ResponseResult(code, msg, null);
    }


    /**
     * 返回错误消息
     *
     * @param basicEnum 返回内容
     * @return 警告消息
     */
    public static ResponseResult<Object> error(IBaseEnum basicEnum) {
        return new ResponseResult(basicEnum.getCode(), basicEnum.getMsg(), null);
    }

}
