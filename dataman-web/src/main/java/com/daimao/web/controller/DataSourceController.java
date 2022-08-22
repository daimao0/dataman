package com.daimao.web.controller;

import com.daimao.common.response.CommonResponse;
import com.daimao.service.http.DataSourceService;
import com.daimao.service.http.impl.DatabaseVO;
import com.daimao.service.domain.request.DatabaseAuthRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author daimao
 * @date 2022/8/2 0:28
 */
@RestController
@RequestMapping("/api/v1/$datasource")
@Api(tags = "数据源接口")
public class DataSourceController {
    private final DataSourceService dataSourceService;

    @Autowired
    public DataSourceController(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

    @PostMapping(value = "/getConnectionToken")
    @ApiOperation(value = "获得数据源token")
    public CommonResponse<String> getConnectionToken(@RequestBody @Validated DatabaseAuthRequest databaseAuthRequest) {
        String token = dataSourceService.getConnectionToken(databaseAuthRequest);
        return CommonResponse.success(token);
    }


    @GetMapping(value = "/databases")
    @ApiOperation(value = "展示所有的数据库")
    public CommonResponse<List<DatabaseVO>> showDatabases() {
        List<DatabaseVO> databaseVOS = dataSourceService.showDatabases();
        return CommonResponse.success(databaseVOS);
    }
}
