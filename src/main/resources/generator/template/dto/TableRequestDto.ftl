package com.java.business.${module}.dto;

import com.java.general.request.dto.PageRequestDto;
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
* @date 2019/6/4 20:08
*/
@Getter
@Setter
@ToString
public class ${classModelName}TableRequestDto extends PageRequestDto {

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
