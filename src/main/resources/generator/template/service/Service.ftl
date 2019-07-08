package com.java.business.${module}.service;

import com.github.pagehelper.PageInfo;

import com.java.business.${module}.dto.${classModelName}TableRequestDto;
<#if columnSet??>
    <#list tableSet as table>
import com.java.business.${module}.entity.${table.className};
    </#list>
</#if>


import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 20:11
 */
public interface ${classModelName}Service {
    /**
     * 列表查询
     *
     * @param requestDto
     * @return
     */
    PageInfo queryTable(${classModelName}TableRequestDto requestDto);

<#if columnSet??>
    <#list tableSet as table>
    /**
    * 保存
    *
    * @param requestDto
    */
    ${table.className} save(${table.className} requestDto);
    </#list>
</#if>


}
