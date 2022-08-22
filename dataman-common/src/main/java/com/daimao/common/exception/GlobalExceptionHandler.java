package com.daimao.common.exception;

import com.daimao.common.response.CommonResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局异常处理
 *
 * @author daimao
 * @date 2022/8/5 23:12
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认异常处理
     *
     * @param e 异常
     * @return 异常处理
     */
    @ExceptionHandler(BaseException.class)
    public CommonResponse<String> defaultExceptionHandler(BaseException e) {
        return CommonResponse.failed(e.getMessage());
    }
    /**
     * 默认异常处理
     *
     * @param e 异常
     * @return 异常处理
     */
    @ExceptionHandler(DatabaseException.class)
    public CommonResponse<String> databaseExceptionHandler(DatabaseException e) {
        return CommonResponse.failed(e.getMessage());
    }

    /**
     * 默认异常处理
     *
     * @param e 异常
     * @return 异常处理
     */
    @ExceptionHandler(AuthException.class)
    public CommonResponse<String> authExceptionHandler(BaseException e) {
        return CommonResponse.unauthorized(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        StringBuilder message = new StringBuilder();
        allErrors.forEach(error -> message.append(error.getDefaultMessage()).append(";"));
        return CommonResponse.failed(message.toString());
    }
}
