package com.java.general.config.security.handler;

import com.alibaba.fastjson.JSON;
import com.java.general.response.code.impl.ResponseEnum;
import com.java.general.response.utils.ResponseUtil;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/21 16:25
 */
@Component
public class AjaxSessionInformationExpiredStrategy  implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ResponseUtil.bindResponseEnum(ResponseEnum.USER_SESSION_TIME_OUT)));
        response.flushBuffer();
    }
}
