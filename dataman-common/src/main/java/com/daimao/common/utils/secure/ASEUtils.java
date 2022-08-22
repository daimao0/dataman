package com.daimao.common.utils.secure;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * @author daimao
 * @date 2022/8/20 0:52
 */

public class ASEUtils {

    /**
     * 密钥：16字节
     */
    private static final byte[] KEY = "0@oKU%6Qy^&W8jxd".getBytes();

    /**
     * 加密内容
     *
     * @param content 内容
     * @return 密文
     */
    public static String generateToken(String content) {
        AES aes = SecureUtil.aes(KEY);
        return aes.encryptHex(content);
    }

    /**
     * 解密密文
     *
     * @param token 密文
     * @return 密文
     */
    public static String decodeToken(String token) {
        AES aes = SecureUtil.aes(KEY);
        return aes.decryptStr(token);
    }


}
