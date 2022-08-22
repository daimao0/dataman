package com.daimao.dao.datasource;

import com.daimao.common.exception.DatasourceException;
import com.daimao.common.filter.DatasourceInfo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author daimao
 * @date 2022/8/3 1:00
 */

public class MySQLDatasource implements Datasource {

    private static final MySQLDatasource MY_SQL_DATASOURCE = new MySQLDatasource();

    public static MySQLDatasource getInstance() {
        return MY_SQL_DATASOURCE;
    }

    private MySQLDatasource() {
    }

    /**
     * 获得连接
     *
     * @param datasourceInfo 数据源信息
     * @return 连接对象
     */
    @Override
    public Connection getConnection(DatasourceInfo datasourceInfo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = String.format("jdbc:mysql://%s", datasourceInfo.getUrl());
            String username = datasourceInfo.getUsername();
            String password = datasourceInfo.getPassword();
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new DatasourceException("数据源创建连接失败" + e.getMessage());
        }

    }
}
