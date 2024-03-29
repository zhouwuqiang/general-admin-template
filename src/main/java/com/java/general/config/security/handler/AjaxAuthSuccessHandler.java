package com.java.general.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.java.business.log.facade.LoginFacade;
import com.java.general.response.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/13 10:57
 */
@Component
public class AjaxAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private LoginFacade loginFacade;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        loginFacade.logging(request,authentication);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ResponseUtil.bindSuccessResponse()));
        response.flushBuffer();
    }
}
