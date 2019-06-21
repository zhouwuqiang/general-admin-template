package com.java.business.log.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.log.dto.LoginLogTableRequestDto;
import com.java.business.log.entity.UserLoginLog;
import com.java.business.log.facade.LoginFacade;
import com.java.business.log.service.LoginLogService;
import com.java.general.config.security.dto.User;
import com.java.general.utils.RequestSystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
    @Async
    public void logging(HttpServletRequest request, Authentication authentication) {
        User userDetails = (User) authentication.getPrincipal();

        UserLoginLog loginLog = new UserLoginLog();
        loginLog.setUserName(userDetails.getUsername());
        loginLog.setIpAddress(request.getRemoteAddr());
        loginLog.setSystemType(RequestSystemUtils.getRequestSystemInfo(request));
        loginLog.setBrowserType(RequestSystemUtils.getRequestBrowserInfo(request));

        loginLogService.save(loginLog);
    }

    @Override
    public PageInfo queryTable(LoginLogTableRequestDto requestDto) {
        return loginLogService.queryTable(requestDto);
    }
}
