package com.java.general.filters;

import com.java.general.config.datasource.MultipleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * description :     访问请求过滤器
 *          同平台,多数据源配置
 *         1.nginx中需要配置可以获取到请求的域名
 *         2.这个切点在登录之后
 *         3.需要校验登录用户是否有权限访问地址
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/15 14:16
 */
@WebFilter(urlPatterns = "/*", filterName = "AccessFilter")
public class AccessFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("initialization  AccessFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

//        LOGGER.info("协议:{}",request.getProtocol());
//        LOGGER.info("客户端IP:{}",request.getRemoteAddr());
//        LOGGER.info("客户端主机名:{}",request.getRemoteHost());
//        LOGGER.info("编码:{}",request.getCharacterEncoding());
//        LOGGER.info("所发送的字节数:{}",request.getContentLength());
//        LOGGER.info("数据类型:{}",request.getContentType());
//        LOGGER.info("Authorization头:{}",request.getAuthType());
//        LOGGER.info("请求类型:{}",request.getMethod());
//        LOGGER.info("URL中的附加路径信息:{}",request.getPathInfo());
//        LOGGER.info("映射到服务器实际路径之后的路径信息:{}",request.getPathTranslated());
//        LOGGER.info("请求URL:{}",request.getRequestURI());
//        LOGGER.info("URL中调用Servlet的那一部分，不包含附加路径信息和查询字符串:{}",request.getServletPath());
//        LOGGER.info("Host:{}",request.getHeader("Host"));
//        LOGGER.info("Referer:{}",request.getHeader("Referer"));
//        LOGGER.info("Host:{}",request.getHeader("Host"));
//        LOGGER.info("协议名称:{}",request.getScheme());
        LOGGER.info("Web服务器名字:{}",request.getServerName());
//        LOGGER.info("服务器监听的端口:{}",request.getServerPort());
        LOGGER.info("请求URL:{},当前线程数据库:{}",request.getRequestURI(),MultipleDataSource.getDataSourceKey());
        MultipleDataSource.removeDataSourceKey();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.info("destroy  AccessFilter");
    }
}
