package nh.focus.common.exception;

import nh.focus.common.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

/**
 * @Description:异常统一处理
 * @Author: ni_hao
 * @Date: 2025-04-23 15:44
 */
@ControllerAdvice
public class ControllerException {
    private static final Logger logger = LoggerFactory.getLogger(ControllerException.class);

    /**
     * 系统异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<?> exceptionHandler(Exception e) {
        logger.error("系统异常:{}", e.getMessage(), e);
        return Result.error("系统异常");
    }

    /**
     * 参数校验异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<?> methodExceptionHandler(MethodArgumentNotValidException e) {
        return Result.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * 限流异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ResponseStatusException.class)
    public Result<?> reExceptionHandler(ResponseStatusException e) {
        return Result.error(e.getReason(), e.getStatusCode().toString());
    }

    /**
     * 业务异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NhException.class)
    public Result<?> nhExceptionHandler(NhException e) {
        if (Objects.isNull(e.getCode())) return Result.error(e.getMsg());
        logger.error("业务异常:{} , {}", e.getCode(), e.getMsg());
        return Result.error(e.getMsg(), e.getCode());
    }

}
