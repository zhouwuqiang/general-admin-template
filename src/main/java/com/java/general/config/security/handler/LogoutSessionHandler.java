package com.java.general.config.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/28 18:09
 */
@Component
public class LogoutSessionHandler implements LogoutHandler {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        String sessionId = httpServletRequest.getSession().getId();
        sessionRegistry.removeSessionInformation(sessionId);
    }
}
