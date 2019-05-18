package com.java.business.demo.controller;

import com.java.business.demo.service.DemoService;
import com.java.business.user.entity.UserBasicFace;
import com.java.general.config.datasource.MultipleDataSource;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/5 10:39
 */
@RestController
@RequestMapping("/api/demo")
public class DemoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    /**
     * 查询默认数据库
     *
     * @return
     */
    @RequestMapping(value = "/default")
    public ResponseDto defaultDataSource() {
        LOGGER.info("当前线程{},访问数据库", Thread.currentThread().getName(),MultipleDataSource.getDataSourceKey());
        List<UserBasicFace> result = demoService.defaultDataSource();
        return ResponseUtil.bindSuccessResponse(result);
    }


    /**
     * 刷新系统数据库连接
     *
     * @return
     */
    @RequestMapping(value = "/refresh")
    public ResponseDto refreshDataSource() {
       demoService.refreshDataSource();
        return ResponseUtil.bindSuccessResponse();
    }


    /**
     * 访问指定数据连接
     *
     * @return
     */
    @RequestMapping(value = "/designation")
    public ResponseDto designationDataSource() {
        List<UserBasicFace> result = demoService.designationDataSource();
        return ResponseUtil.bindSuccessResponse(result);
    }


    /**
     * 所有数据库连接
     *
     * @return
     */
    @RequestMapping(value = "/all")
    public ResponseDto allDataSource() {
        List<UserBasicFace> result = demoService.allDataSource();
        return ResponseUtil.bindSuccessResponse(result);
    }
}

