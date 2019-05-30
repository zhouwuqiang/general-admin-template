package com.java.general.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * description : 监听servletContext对象的创建以及销毁
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/30 11:09
 */

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info(sce.getServletContext().getServletContextName()+" init");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info(sce.getServletContext().getServletContextName()+" destroy");

    }
}
