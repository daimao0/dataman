package com.daimao.service.http;

import com.daimao.service.domain.vo.FieldVO;
import com.daimao.service.domain.vo.TableVO;

import java.util.List;

/**
 * @author daimao
 * @date 2022/8/21 0:38
 */
public interface TableService {
    /**
     * 获得所有表信息
     * @return 所有表
     */
    List<TableVO> listTableVO();

    /**
     * 根据表明获得字段信息
     *
     * @param tableName 表名
     * @return 字段信息
     */
    List<FieldVO> listFieldVO(String tableName);
}
