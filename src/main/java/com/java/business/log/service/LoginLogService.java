package com.java.business.log.service;

import com.github.pagehelper.PageInfo;
import com.java.business.log.dto.LoginLogTableRequestDto;
import com.java.business.log.entity.UserLoginLog;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/21 18:23
 */
public interface LoginLogService {

    /**
     * 登录日志列表查询
     * @param requestDto
     * @return
     */
    PageInfo queryTable(LoginLogTableRequestDto requestDto);

    /**
     * 保存登录日志
     * @param loginLog
     */
    void save(UserLoginLog loginLog);
}
