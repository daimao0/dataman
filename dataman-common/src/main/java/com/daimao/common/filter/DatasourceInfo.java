package com.daimao.common.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daimao
 * @date 2022/8/3 0:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatasourceInfo {

    /**
     * url
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
