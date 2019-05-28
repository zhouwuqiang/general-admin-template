package com.java.general.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.java.general.response.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
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
public class AjaxAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message = "账号或密码输入错误,请重新输入!";

        if(exception instanceof SessionAuthenticationException){
            message="账号已在其他设备登录,请先退出!";
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ResponseUtil.bindFailResponse(message)));
    }
}
