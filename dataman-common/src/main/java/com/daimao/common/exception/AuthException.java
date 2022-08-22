package com.daimao.common.exception;

/**
 * @author daimao
 * @date 2022/8/20 0:18
 */

public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(message);
    }
}
