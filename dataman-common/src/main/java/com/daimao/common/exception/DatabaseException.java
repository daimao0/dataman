package com.daimao.common.exception;

/**
 * @author daimao
 * @date 2022/8/21 1:05
 */

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message) {
        super(message);
    }
}
