package com.java.business.menu.controller;

import com.alibaba.fastjson.JSON;
import com.java.general.config.security.dto.User;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/7 12:41
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    /**
     * 获取菜单列表
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public ResponseDto table() {
        LOGGER.info("/menu/list >>");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        LOGGER.info("/menu/list >> response:{}", JSON.toJSONString(userDetails.getMenus()));
        return ResponseUtil.bindSuccessResponse(userDetails.getMenus());
    }


}
