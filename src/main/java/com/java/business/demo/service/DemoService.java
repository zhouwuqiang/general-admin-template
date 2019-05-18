package com.java.business.demo.service;


import com.java.business.user.entity.UserBasicFace;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/4 11:01
 */
public interface DemoService {


    List<UserBasicFace>  defaultDataSource();

    /**
     * 刷新所有数据源
     */
    void refreshDataSource();

    List<UserBasicFace> designationDataSource();

    List<UserBasicFace> allDataSource();
}
