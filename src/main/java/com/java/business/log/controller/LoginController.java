package com.java.business.log.controller;

import com.github.pagehelper.PageInfo;
import com.java.business.log.dto.LoginLogTableRequestDto;
import com.java.business.log.facade.LoginFacade;
import com.java.business.menu.dto.MenuTableRequestDto;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/21 18:16
 */
@RestController
@RequestMapping("/log/login")
public class LoginController {

    @Autowired
    private LoginFacade loginFacade;
    /**
     * 获取主表数据
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/table")
    @ControllerRecorder(path = "/log/login/table")
    public ResponseDto table(@RequestBody LoginLogTableRequestDto requestDto) {

        PageInfo pageInfo = loginFacade.queryTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo);
    }

}
