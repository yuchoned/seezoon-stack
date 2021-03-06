package com.seezoon.dao.modules.${moduleName}.entity;

<#if importBigDecimal>
import java.math.BigDecimal;
</#if>
<#if importDate>
import java.util.Date;
</#if>

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
 * ${menuName!}
 *
 * @author seezoon-generator ${.now}
 */
@ApiModel
@Getter
@Setter
@ToString
public class ${className} extends BaseEntity<${pkPlan.dataType.javaType()}> {

<#list columnPlans as columnPlan>
   <#if !columnPlan.defaultField>
    @ApiModelProperty(value = "${columnPlan.fieldName!}"<#if !columnPlan.nullable>, required = true</#if>)
      <#if !columnPlan.nullable>
        <#if columnPlan.stringType>
    @NotBlank
    @Size(max = ${columnPlan.maxLength})
        <#else>
    @NotNull
        </#if>
      </#if>
    private ${columnPlan.dataType.javaType()} ${columnPlan.javaFieldName};

   </#if>
</#list>

<#if !pkPlan.defaultJavaPkName>
    @Override
    public ${pkPlan.dataType.javaType()} getId() {
        return ${pkPlan.javaFieldName};
    }

    @Override
    public void setId(${pkPlan.dataType.javaType()} ${pkPlan.javaFieldName}) {
        this.id = ${pkPlan.javaFieldName};
        this.${pkPlan.javaFieldName} = ${pkPlan.javaFieldName};
    }
</#if>
}