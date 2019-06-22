package com.java.general.utils;

import com.java.general.config.security.dto.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/22 10:40
 */
@Component
public class SpringSecurityUtil {

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public static User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

}
