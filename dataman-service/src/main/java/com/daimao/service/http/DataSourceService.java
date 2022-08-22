package com.daimao.service.http;

import com.daimao.service.http.impl.DatabaseVO;
import com.daimao.service.domain.request.DatabaseAuthRequest;

import java.util.List;

/**
 * @author daimao
 * @date 2022/8/5 0:21
 */
public interface DataSourceService {

    /**
     * 获得数据库
     * @return 数据库DTO
     */
   List<DatabaseVO> showDatabases();

    /**
     * 获得数据源token
     * @param databaseAuthRequest 数据源登录校验参数
     * @return token
     */
    String getConnectionToken(DatabaseAuthRequest databaseAuthRequest);
}
