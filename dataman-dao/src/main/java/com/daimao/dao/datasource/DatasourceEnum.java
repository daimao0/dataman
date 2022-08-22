package com.daimao.dao.datasource;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author daimao
 * @date 2022/8/21 2:12
 */
@Getter
@AllArgsConstructor
public enum DatasourceEnum {

    MYSQL("com.daimao.dao.datasource.MySQLDatasource");

    private final String name;

    private static final Map<String, DatasourceEnum> ALL_ENUM = new HashMap<>(2);

    static {
        Arrays.stream(DatasourceEnum.values())
                .forEach(datasourceEnum -> ALL_ENUM.put(datasourceEnum.getName(), datasourceEnum));
    }

    public static DatasourceEnum getEnum(String name) {
        return ALL_ENUM.get(name);
    }
}
