package com.daimao.dao.datasource;

import com.daimao.common.filter.DatasourceInfo;

import java.sql.Connection;

/**
 * @author daimao
 * @date 2022/8/3 0:51
 */

public interface Datasource {

    /**
     * 获得连接
     *
     * @return 连接对象
     */
    Connection getConnection(DatasourceInfo datasourceInfo);


}
