package com.daimao.service.http.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daimao
 * @date 2022/8/5 0:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatabaseVO {
    /**
     * 数据库名称
     */
    private String databaseName;
}
