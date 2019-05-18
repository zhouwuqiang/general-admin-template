package com.java.general.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/1/5 15:14
 */
@Controller
public class SysIndexController {


    private static final Logger LOGGER = LoggerFactory.getLogger(SysIndexController.class);

    @RequestMapping(value = {"/", "/index"})
    public String login() {
        LOGGER.debug("请求首页!");
        return "/index";
    }

}
