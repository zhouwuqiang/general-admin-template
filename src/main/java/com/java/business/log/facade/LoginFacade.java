package com.java.business.log.facade;

import com.github.pagehelper.PageInfo;
import com.java.business.log.dto.LoginLogTableRequestDto;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/21 18:18
 */
public interface LoginFacade {

    /**
     * 登录日志查询
     * @param requestDto
     * @return
     */
    PageInfo queryTable(LoginLogTableRequestDto requestDto);

    /**
     * 登录记录
     * @param request
     * @param authentication
     */
    void logging(HttpServletRequest request, Authentication authentication);
}
