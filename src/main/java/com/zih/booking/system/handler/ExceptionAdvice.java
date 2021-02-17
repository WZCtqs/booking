package com.zih.booking.system.handler;

import com.zih.booking.system.enums.ResultStatusCode;
import com.zih.booking.system.vo.ApiResultI18n;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author shahy
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, BindException.class,
            ServletRequestBindingException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ApiResultI18n handleHttpMessageNotReadableException(Exception e) {
        log.error("参数解析失败", e);
        if (e instanceof BindException){
            return ApiResultI18n.failure(ResultStatusCode.BAD_REQUEST.getCode(), ((BindException)e).getAllErrors().get(0).getDefaultMessage(),null);
        }
        return ApiResultI18n.failure(ResultStatusCode.BAD_REQUEST.getCode(), e.getMessage(),null);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResultI18n handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e);
        return ApiResultI18n.failure(ResultStatusCode.METHOD_NOT_ALLOWED,null);
    }

    /**
     * 500
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ApiResultI18n handleException(Exception e) {
        log.error("服务运行异常", e);
        return ApiResultI18n.failure(ResultStatusCode.SYSTEM_ERR,null);
    }
    /**
     * 接口未授权
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value= UnauthorizedException.class)
    public ApiResultI18n handleUnauthorizedException(Exception e) {
        log.error("未经授权", e);
        return ApiResultI18n.failure(ResultStatusCode.UNAUTHO_ERROR,null);
    }
}
