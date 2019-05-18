package com.java.general.exception;

import com.alibaba.fastjson.JSON;
import com.java.general.response.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author alger
 * @version 1.0.0
 * 自定义统一异常处理
 * <p>
 * 通过url查询出方法,如果方法上包含ResponseBody注解,封装json数据返回
 * <p>
 * 其他情况返回500页面
 * @date 2018/12/25 18:16
 */
@Component
public class BusinessExceptionResolver implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessExceptionResolver.class);

    /**
     * 视图前缀
     */
    private static final String VIEW = "/view/";

    private static final String STATIC_PATH = "/static/";

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String url = request.getServletPath();
        Map paramMap = request.getParameterMap();
        LOGGER.info("处理请求异常:{} 参数:{}", url, JSON.toJSONString(paramMap), ex);

        if (!url.contains(VIEW) || !url.contains(STATIC_PATH)) {
            responseJson(response, ex);
            return new ModelAndView();
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/view/error/500");
        return modelAndView;
    }

    private void responseJson(HttpServletResponse response, Exception ex) {
        //设置状态码
        response.setStatus(HttpStatus.OK.value());
        //设置ContentType
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //避免乱码
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
            response.getWriter().write(JSON.toJSONString(ResponseUtil.bindFailResponse(ex.getMessage())));
        } catch (IOException e) {
            LOGGER.error("与客户端通讯异常:{}", e.getMessage(), e);
        }
    }
}
