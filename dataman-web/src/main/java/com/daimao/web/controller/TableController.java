package com.daimao.web.controller;

import com.daimao.common.response.CommonResponse;
import com.daimao.service.domain.vo.FieldVO;
import com.daimao.service.domain.vo.TableVO;
import com.daimao.service.http.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author daimao
 * @date 2022/8/21 0:20
 */
@RequestMapping("/api/v1/{database}/table")
@RestController
@Api(tags = "数据库表接口")
public class TableController {
    private final TableService tableService;

    @Autowired
    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping()
    @ApiOperation(value = "获得所有的表")
    public CommonResponse<List<TableVO>> listTableVO(@PathVariable String database) {
        List<TableVO> tableVOS = tableService.listTableVO();
        return CommonResponse.success(tableVOS);
    }

    @GetMapping("/{tableName}/field")
    @ApiOperation(value = "获得当前表的字段")
    public CommonResponse<List<FieldVO>> listFieldVO(@PathVariable String database, @PathVariable String tableName) {
        List<FieldVO> fieldVOS = tableService.listFieldVO(tableName);
        return CommonResponse.success(fieldVOS);
    }
}
