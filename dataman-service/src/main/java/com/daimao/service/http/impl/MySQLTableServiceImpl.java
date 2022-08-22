package com.daimao.service.http.impl;

import com.daimao.common.exception.DatabaseException;
import com.daimao.common.filter.UserContext;
import com.daimao.dao.datasource.Datasource;
import com.daimao.dao.datasource.DatasourceFactory;
import com.daimao.dao.datasource.MySQLDatasource;
import com.daimao.service.domain.vo.FieldVO;
import com.daimao.service.domain.vo.TableVO;
import com.daimao.service.http.TableService;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daimao
 * @date 2022/8/21 0:38
 */
@Service
public class MySQLTableServiceImpl implements TableService {

    private final Datasource mySQLDatasource = DatasourceFactory.getDatasource(MySQLDatasource.class);

    /**
     * 获得所有表信息
     *
     * @return 所有表
     */
    @Override
    public List<TableVO> listTableVO() {
        Connection connection = mySQLDatasource.getConnection(UserContext.getDatasourceInfo());
        //定义返回结果
        ArrayList<TableVO> tableVOS = new ArrayList<>();
        try {
            //获得Connect元数据信息
            DatabaseMetaData metaData = connection.getMetaData();
            //获得表信息
            ResultSet rs = metaData.getTables(connection.getCatalog(), "%", "%", new String[]{"TABLE"});
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                String ddl = getCreateTableDDL(tableName);
                assert ddl != null;
                TableVO tableVO = TableVO.builder()
                        .name(tableName)
                        .comment(getTableComment(ddl))
                        .createDDL(ddl)
                        .build();
                tableVOS.add(tableVO);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return tableVOS;
    }

    /**
     * 根据表明获得字段信息
     *
     * @param tableName 表名
     * @return 字段信息
     */
    @Override
    public List<FieldVO> listFieldVO(String tableName) {
        Connection connection = mySQLDatasource.getConnection(UserContext.getDatasourceInfo());
        //定义返回结果
        ArrayList<FieldVO> fieldVOS = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, "%", tableName, "%");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String typeName = columns.getString("TYPE_NAME");
                String remarks = columns.getString("REMARKS");
                FieldVO fieldVO = FieldVO.builder()
                        .name(columnName)
                        .type(typeName)
                        .remark(remarks)
                        .build();
                fieldVOS.add(fieldVO);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return fieldVOS;
    }


    private String getCreateTableDDL(String tableName) {
        Connection connection = mySQLDatasource.getConnection(UserContext.getDatasourceInfo());
        String sql = "SHOW CREATE TABLE " + tableName;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                return resultSet.getString(2);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        return null;
    }


    /**
     * 传入建表语句，返回表注释信息
     *
     * @param ddl 建表语句
     * @return 表注释
     */
    public static String getTableComment(String ddl) {
        String comment;
        int index = ddl.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = ddl.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }
}
