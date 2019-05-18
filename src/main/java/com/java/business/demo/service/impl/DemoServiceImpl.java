package com.java.business.demo.service.impl;


import com.java.business.demo.controller.DemoController;
import com.java.business.demo.service.DemoService;
import com.java.business.user.entity.UserBasicFace;
import com.java.business.user.mapper.UserBasicFaceMapper;
import com.java.general.config.datasource.DataSourceConfig;
import com.java.general.config.datasource.MultipleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/4 11:01
 */
@Service
public class DemoServiceImpl implements DemoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Autowired
    private MultipleDataSource multipleDataSource;


    @Autowired
    private UserBasicFaceMapper basicFaceMapper;

    private static List<DataSourceConfig> dataSourceList = new ArrayList<>();

    static {
        DataSourceConfig DB1 = new DataSourceConfig();
        DB1.setSourceName("DB1");
        DB1.setUrl("jdbc:mysql://127.0.0.1:3306/db1?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        DB1.setUserName("root");
        DB1.setUserPassword("root");
        dataSourceList.add(DB1);
        DataSourceConfig DB2 = new DataSourceConfig();
        DB2.setSourceName("DB2");
        DB2.setUrl("jdbc:mysql://127.0.0.1:3306/db2?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        DB2.setUserName("root");
        DB2.setUserPassword("root");
        dataSourceList.add(DB2);
        DataSourceConfig DB3 = new DataSourceConfig();
        DB3.setSourceName("DB3");
        DB3.setUrl("jdbc:mysql://127.0.0.1:3306/db3?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        DB3.setUserName("root");
        DB3.setUserPassword("root");
        dataSourceList.add(DB3);
    }


    @Override
    public List<UserBasicFace> defaultDataSource() {
        List<UserBasicFace> result = basicFaceMapper.selectAll();
        return result;
    }

    @Override
    public void refreshDataSource() {
        multipleDataSource.refreshDataSource(dataSourceList);
    }

    @Override
    public List<UserBasicFace> designationDataSource() {
        MultipleDataSource.setDataSourceKey("DB1");
        List<UserBasicFace> result = basicFaceMapper.selectAll();
        return result;
    }

    @Override
    public List<UserBasicFace> allDataSource() {
        List<UserBasicFace> result = new ArrayList<>();
        LOGGER.info("当前线程{},访问数据库{}", Thread.currentThread().getName(),MultipleDataSource.getDataSourceKey());
        result.addAll(basicFaceMapper.selectAll());
        MultipleDataSource.setDataSourceKey("DB1");
        LOGGER.info("当前线程{},访问数据库{}", Thread.currentThread().getName(),MultipleDataSource.getDataSourceKey());
        result.addAll(basicFaceMapper.selectAll());
        MultipleDataSource.setDataSourceKey("DB2");
        LOGGER.info("当前线程{},访问数据库{}", Thread.currentThread().getName(),MultipleDataSource.getDataSourceKey());
        result.addAll(basicFaceMapper.selectAll());
        MultipleDataSource.setDataSourceKey("DB3");
        LOGGER.info("当前线程{},访问数据库{}", Thread.currentThread().getName(),MultipleDataSource.getDataSourceKey());
        result.addAll(basicFaceMapper.selectAll());
        return result;
    }


}
