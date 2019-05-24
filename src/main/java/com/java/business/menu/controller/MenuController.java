package com.java.business.menu.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.java.business.menu.dto.MenuTableRequestDto;
import com.java.business.menu.facade.MenuFacade;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.general.config.security.dto.User;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.interceptors.log.LoggerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import com.java.general.validation.dto.ValidResult;
import com.java.general.validation.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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


    @Autowired
    private MenuFacade menuFacade;

    /**
     * 获取菜单列表
     *
     * @return
     */
    @LoggerRecorder
    @RequestMapping(value = "/list")
    public ResponseDto table() {
        LOGGER.info("/menu/list >>");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        LOGGER.info("/menu/list >> response:{}", JSON.toJSONString(userDetails.getMenus()));
        return ResponseUtil.bindSuccessResponse(userDetails.getMenus());
    }


    /**
     * 获取主表数据
     *
     * @param requestDto
     * @return
     */
    @ControllerRecorder(path = "/menu/table", validateClass = MenuTableRequestDto.class)
    @RequestMapping(value = "/table")
    public ResponseDto table(@RequestBody MenuTableRequestDto requestDto) {

        PageInfo pageInfo = menuFacade.queryTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo);
    }


}
