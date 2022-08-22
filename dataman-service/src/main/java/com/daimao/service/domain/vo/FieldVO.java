package com.daimao.service.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daimao
 * @date 2022/8/21 1:22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "字段信息")
public class FieldVO {
    @ApiModelProperty(value = "字段名称")
    private String name;
    @ApiModelProperty(value = "字段类型")
    private String type;
    @ApiModelProperty(value = "字段注释")
    private String remark;
}
