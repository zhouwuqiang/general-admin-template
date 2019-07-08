package com.java.business.${module}.facade;

import com.github.pagehelper.PageInfo;
import com.java.business.${module}.dto.${classModelName}TableRequestDto;
import com.java.business.${module}.dto.${classModelName}SaveRequestDto;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 20:09
 */
public interface ${classModelName}Facade {

    /**
     * 表格查询
     *
     * @param requestDto
     * @return
     */
    PageInfo queryTable(${classModelName}TableRequestDto requestDto);

    /**
     * 保存
     *
     * @param requestDto
     */
    void save(${classModelName}SaveRequestDto requestDto);

    /**
     * 删除
     *
     * @param requestDto
     */
    void delete(${classModelName}SaveRequestDto requestDto);

}
