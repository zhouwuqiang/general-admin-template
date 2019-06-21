package com.java.general.listener;

import com.java.general.config.security.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * description : 监听session对象的创建以及销毁
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/25 13:11
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        LOGGER.info("监听器：Session >>>> 创建{}",httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        LOGGER.info("监听器：Session >>>> 销毁{}",httpSessionEvent.getSession().getId());
    }
}
