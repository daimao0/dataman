package com.daimao.common.utils.secure;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;

import java.util.Map;

/**
 * jwtToken工具类
 *
 * @author daimao
 * @date 2022/8/20 0:24
 */

public class JwtUtils {
    /**
     * 获得jwtToken
     *
     * @param payload   载荷
     * @param offset    过期时间位移
     * @param dateField 时间单位
     * @return jwtToken
     */
    public static String getToken(Map<String, Object> payload, int offset, DateField dateField) {
        DateTime now = DateTime.now();
        DateTime expireTime = now.offsetNew(dateField, offset);
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, expireTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //密钥
        String key = "daimao";
        return JWTUtil.createToken(payload, key.getBytes());
    }

    /**
     * 通过token获得载荷
     *
     * @param token token
     * @return 载荷
     */
    public static Map<String, Object> getPayload(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        JWTPayload payload = jwt.getPayload();

        return null;
    }
}
