package com.daimao.common.response;

import lombok.Data;

/**
 * @author daimao
 * @date 2022/8/2 0:42
 */
@Data
public class CommonResponse<T> {
    /**
     * 响应码
     */
    private long code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回值
     */
    private T data;


    CommonResponse(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }


    public static <T> CommonResponse<T> success(T data, String message) {
        return new CommonResponse<>(ResponseCode.SUCCESS.getCode(), message, data);
    }


    public static <T> CommonResponse<T> failed(String message) {
        return new CommonResponse<>(ResponseCode.FAILED.getCode(), message, null);
    }


    public static <T> CommonResponse<T> unauthorized(String message) {
        return new CommonResponse<>(ResponseCode.UNAUTHORIZED.getCode(), message, null);
    }


    public static <T> CommonResponse<T> forbidden(String message) {
        return new CommonResponse<>(ResponseCode.FORBIDDEN.getCode(), message, null);
    }
}
