package com.java.general.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * description :  请求执行时间拦截器
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/15 14:22
 */
@WebFilter(urlPatterns = "/*", filterName = "ExecutorTimeFilter")
public class ExecutorTimeFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorTimeFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("initialization  ExecutorTimeFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        filterChain.doFilter(servletRequest,servletResponse);
        LOGGER.info("{} >> 执行时间 :{}" ,request.getRequestURI(),(System.currentTimeMillis()-start));
    }

    @Override
    public void destroy() {
        LOGGER.info("destroy  ExecutorTimeFilter");
    }
}
