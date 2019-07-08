package com.java.business.${module}.controller;

import com.github.pagehelper.PageInfo;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.business.${module}.dto.${classModelName}TableRequestDto;
import com.java.business.${module}.dto.${classModelName}SaveRequestDto;
import com.java.business.${module}.facade.${classModelName}Facade;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/7 12:41
 */
@RestController
@RequestMapping("/${module}")
public class ${classModelName}Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(${classModelName}Controller.class);

    @Autowired
    private ${classModelName}Facade ${module}Facade;

    /**
     * 获取主表数据
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/table")
    @ControllerRecorder(path = "/menu/table", validateClass = ${classModelName}TableRequestDto.class)
    public ResponseDto table(@RequestBody ${classModelName}TableRequestDto requestDto) {

        PageInfo pageInfo = ${module}Facade.queryTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo.getList());
    }


    /**
     * 保存
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/save")
    @ControllerRecorder(path = "/${module}/save")
    public ResponseDto save(@RequestBody ${classModelName}SaveRequestDto requestDto) {

        ${module}Facade.save(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

    /**
     * 删除
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/delete")
    @ControllerRecorder(path = "/${module}/delete")
    public ResponseDto delete(@RequestBody ${classModelName}SaveRequestDto requestDto) {

        ${module}Facade.delete(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

}
