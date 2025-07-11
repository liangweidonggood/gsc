package com.lwd.gsc.common.exception;

import com.lwd.gsc.common.result.ResResult;
import com.lwd.gsc.common.result.ResultCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;


/**
 * 全局异常处理器（基于 ResultCode 和 ResResult 统一返回）
 *
 * @author lwd
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResResult<?> handleBusinessException(BusinessException e) {
        return ResResult.fail(e.getResultCode(), e.getData());
    }

    /**
     * 处理参数校验异常（@Valid）
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResResult<?> handleValidationExceptions(Exception ex) {
        return ResResult.fail(ResultCode.PARAM_ERROR).setMsg((ex instanceof MethodArgumentNotValidException methodEx
                ? methodEx.getBindingResult()
                : ((BindException) ex).getBindingResult())
                .getAllErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("参数错误"));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResResult<?> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResResult.fail(ResultCode.PARAM_ERROR).setMsg(
                ex.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .findFirst()
                        .orElse("参数校验失败"));
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public ResResult<?> handleNullPointerException(NullPointerException ex) {
        log.error("NullPointerException", ex);
        return ResResult.fail(ResultCode.NULL_POINTER).setMsg(ex.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResResult<?> handleResourceNotFound() {
        return ResResult.fail(ResultCode.NOT_FOUND);
    }

    /**
     * 处理所有未捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public ResResult<?> handleOtherExceptions(Exception ex) {
        log.error("Exception", ex);
        return ResResult.fail(ResultCode.FAIL).setMsg(ex.getMessage());
    }
}
