package com.java.business.log.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.log.dto.LoginLogTableRequestDto;
import com.java.business.log.facade.LoginFacade;
import com.java.business.log.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/21 18:18
 */
@Service
public class LoginFacadeImpl implements LoginFacade {

    @Autowired
    private LoginLogService loginLogService;

    @Override
    public PageInfo queryTable(LoginLogTableRequestDto requestDto) {
        return loginLogService.queryTable(requestDto);
    }
}
