package com.daimao.dao.datasource;

/**
 * 数据源工厂类
 *
 * @author daimao
 * @date 2022/8/21 1:57
 */

public class DatasourceFactory {

    /**
     * 根据不同的datasource生成不同的数据源
     *
     * @param datasourceClass clazz
     * @return 不同的数据源实例
     */
    public static Datasource getDatasource(Class<? extends Datasource> datasourceClass) {
        String name = datasourceClass.getName();
        switch (DatasourceEnum.getEnum(name)) {
            case MYSQL -> {
                return MySQLDatasource.getInstance();
            }
            default -> {
            }
        }
        return MySQLDatasource.getInstance();
    }
}
