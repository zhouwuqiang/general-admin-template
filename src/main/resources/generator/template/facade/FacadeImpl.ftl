package com.java.business.${module}.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.general.constant.SystemCommonConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.business.${module}.facade.${classModelName}Facade;
import com.java.business.${module}.service.${classModelName}Service;
import com.java.business.${module}.dto.${classModelName}TableRequestDto;
import com.java.business.${module}.dto.${classModelName}SaveRequestDto;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 20:09
 */
@Service
public class ${classModelName}FacadeImpl implements ${classModelName}Facade {

    @Override
    public PageInfo queryTable(${classModelName}TableRequestDto requestDto) {
        return null;
    }

    @Override
    public void save(${classModelName}SaveRequestDto requestDto) {

    }

    @Override
    public void delete(${classModelName}SaveRequestDto requestDto) {

    }
}
