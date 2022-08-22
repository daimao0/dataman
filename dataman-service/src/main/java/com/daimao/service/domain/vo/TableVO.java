package com.daimao.service.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daimao
 * @date 2022/8/21 0:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "表信息")
public class TableVO {
    @ApiModelProperty(value = "表名称")
    private String name;
    @ApiModelProperty(value = "表类型")
    private String type;
    @ApiModelProperty(value = "表注释")
    private String comment;
    @ApiModelProperty(value = "表注释")
    private String createDDL;
}
