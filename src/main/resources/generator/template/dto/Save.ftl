package com.java.business.${module}.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


<#if columnSet??>
    <#list columnSet as field>
import ${field.columnTypePackage};
    </#list>
</#if>

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 20:10
 */
@Getter
@Setter
@ToString
public class ${classModelName}SaveRequestDto {

<#if columnSet??>
<#list columnSet as field>


    <#if field.remarks??>
        /**
        * ${field.remarks}
        */
    </#if>
        private ${field.columnType} ${field.columnName};
</#list>
</#if>

}
