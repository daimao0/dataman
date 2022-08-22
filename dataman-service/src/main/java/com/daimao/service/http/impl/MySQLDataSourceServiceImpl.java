package com.daimao.service.http.impl;

import com.alibaba.fastjson.JSON;
import com.daimao.common.exception.BaseException;
import com.daimao.common.filter.DatasourceInfo;
import com.daimao.common.filter.UserContext;
import com.daimao.common.utils.secure.ASEUtils;
import com.daimao.dao.datasource.Datasource;
import com.daimao.dao.datasource.DatasourceFactory;
import com.daimao.dao.datasource.MySQLDatasource;
import com.daimao.service.domain.request.DatabaseAuthRequest;
import com.daimao.service.http.DataSourceService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daimao
 * @date 2022/8/6 0:38
 */
@Service
public class MySQLDataSourceServiceImpl implements DataSourceService {

    private final Datasource mySQLDatasource = DatasourceFactory.getDatasource(MySQLDatasource.class);

    /**
     * 展示数据库列表
     *
     * @return 数据库列表
     */
    @Override
    public List<DatabaseVO> showDatabases() {
        //定义返回值
        ArrayList<DatabaseVO> databaseVOS = new ArrayList<>();
        //获得链接
        DatasourceInfo datasourceInfo = UserContext.getDatasourceInfo();
        Connection connection = mySQLDatasource.getConnection(datasourceInfo);
        //获得statement
        try {
            String sql = "show Databases;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String dbName = resultSet.getString(1);
                databaseVOS.add(DatabaseVO.builder().databaseName(dbName).build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return databaseVOS;
    }

    /**
     * 获得数据源token
     *
     * @param databaseAuthRequest 数据源登录校验参数
     * @return token
     */
    @Override
    public String getConnectionToken(DatabaseAuthRequest databaseAuthRequest) {
        //获得链接
        DatasourceInfo datasourceInfo = DatasourceInfo.builder()
                .url(databaseAuthRequest.getUrl())
                .username(databaseAuthRequest.getUsername())
                .password(databaseAuthRequest.getPassword())
                .build();
        Connection connection = mySQLDatasource.getConnection(datasourceInfo);
        if (connection != null) {
            //将数据源登录信息生成对称密文
            return ASEUtils.generateToken(JSON.toJSONString(datasourceInfo));
        }
        throw new BaseException("连接数据源失败，请检查用户名密码是否正确");
    }
}
