package com.seezoon.dao.modules.sys.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.seezoon.dao.framework.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 组织机构
 *
 * @author seezoon-generator 2021年1月12日 下午10:54:44
 */
@ApiModel
@Getter
@Setter
@ToString
public class SysDept extends BaseEntity<Integer> {

    @ApiModelProperty(value = "父部门根节点默认为0", required = true)
    @NotNull
    private Integer parentId = 0;
    /**
     * eg: /0/1/
     */
    @ApiModelProperty(value = "所有父节点，自动计算")
    @Size(max = 255)
    private String parentIds;
    @ApiModelProperty(value = "部门名称", required = true)
    @NotBlank
    @Size(max = 100)
    private String name;
    @ApiModelProperty(value = "排序", required = true)
    @NotNull
    private Integer sort;
    @ApiModelProperty(value = "联系人")
    @Size(max = 50)
    private String contactUser;
    @ApiModelProperty(value = "联系电话")
    @Size(max = 50)
    private String telephone;

    /**
     * 以下为join 字段
     */
    @ApiModelProperty(value = "父部门名称(只显示)")
    private String parentName;
}