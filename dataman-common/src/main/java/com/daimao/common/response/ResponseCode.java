package com.daimao.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daimao
 * @date 2022/8/2 0:48
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(200, ""),
    /**
     * 操作失败
     */
    FAILED(500, "操作失败"),
    /**
     * 参数检验失败
     */
    VALIDATE_FAILED(400, "参数检验失败"),

    UNAUTHORIZED(401, "暂未登录或token已经过期"),

    FORBIDDEN(403, "没有相关权限");

    private final long code;

    private final String message;

}
