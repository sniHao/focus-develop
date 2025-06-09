package com.focus.framework.web.exception;

import com.focus.framework.core.excetion.BusinessException;
import com.focus.framework.core.excetion.SystemException;
import com.focus.framework.core.excetion.SystemExceptionEnum;
import com.focus.framework.web.response.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理业务错误异常
     *
     * @param exception 业务异常
     * @return 响应数据，包含错误代码和异常堆栈信息
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseResult<Object> handleBusinessException(BusinessException exception, HttpServletRequest request) {
        log.error("请求地址 [{}]，发生业务异常:{}", request.getRequestURI(), exception.getCustomMessage());
        if (ObjectUtils.isEmpty(exception.getBasicEnum())) {
            return ResponseResult.error(exception.getCustomCode() == null ? 199001 : exception.getCustomCode(),
                    exception.getCustomMessage());
        }
        return ResponseResult.error(exception.getBasicEnum());
    }

//    @ExceptionHandler(NotPermissionException.class)
//    public ResponseResult<Object> handleNotPermissionException(NotPermissionException e) {
//        log.error("权限验证失败：{}", e.getMessage());
//        return ResponseResult.error(SystemExceptionEnum.SYSTEM_ERROR_200009.getCode(),
//                SystemExceptionEnum.SYSTEM_ERROR_200009.getMsg());
//    }

    /**
     * 处理系统异常。
     * 返回系统异常中的错误代码和错误消息。
     *
     * @param exception 系统异常
     * @return 响应数据，包含错误代码和错误消息
     */
    @ExceptionHandler(SystemException.class)
    public ResponseResult<Object> handleSystemException(SystemException exception, HttpServletRequest request) {
        log.error("请求地址 [{}]，发生系统错误。", request.getRequestURI(), exception);
        if (ObjectUtils.isEmpty(exception.getBasicEnum())) {
            return ResponseResult.error(exception.getCustomCode() == null ? SystemExceptionEnum.SYSTEM_ERROR_200001.getCode() :
                    exception.getCustomCode(), exception.getCustomMessage());
        }
        return ResponseResult.error(exception.getBasicEnum());
    }

    /**
     * Valid参数校验 异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                      HttpServletRequest request) {
        log.error("请求地址 [{}]，发生参数校验异常。", request.getRequestURI(), exception);
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseResult.error(199999, message);
    }

    /**
     * Valid参数校验 异常（列表类型）
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseResult<Void> handleConstraintViolationException(ConstraintViolationException exception,
                                                                   HttpServletRequest request) {
        log.error("请求地址 [{}]，发生参数校验异常。", request.getRequestURI(), exception);
        String message = exception.getConstraintViolations().iterator().next().getMessage();
        return ResponseResult.error(199999, message);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult<Void> handleRuntimeException(RuntimeException exception, HttpServletRequest request) {
        log.error("请求地址 [{}]，发生运行异常。", request.getRequestURI(), exception);
        return ResponseResult.error(SystemExceptionEnum.SYSTEM_ERROR_200001.getCode(),
                SystemExceptionEnum.SYSTEM_ERROR_200001.getMsg());
    }

    /**
     * 处理其他未知异常。
     * 返回HTTP响应状态码500，包含错误代码和异常堆栈信息。
     */
    @ExceptionHandler(Throwable.class)
    public ResponseResult<Object> handleException(Throwable exception, HttpServletRequest request) {
        log.error("请求地址 [{}]，发生未知异常。", request.getRequestURI(), exception);
        return ResponseResult.error(SystemExceptionEnum.SYSTEM_ERROR_200001.getCode(),
                SystemExceptionEnum.SYSTEM_ERROR_200001.getMsg());
    }

}
