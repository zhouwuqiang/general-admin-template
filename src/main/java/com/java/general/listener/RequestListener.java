package com.java.general.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * description : 监听request对象的创建以及销毁
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/30 11:11
 */
@WebListener
public class RequestListener implements ServletRequestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        LOGGER.info("销毁时执行");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        LOGGER.info("创建时执行");
    }
}
