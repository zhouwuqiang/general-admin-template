package com.java.business.user.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserSaveRequestDto;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.facade.UserFacade;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import com.java.general.validation.dto.ValidResult;
import com.java.general.validation.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/3/14 17:21
 */
@RestController
@RequestMapping("/user")
public class UserController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserFacade userFacade;

    /**
     * 获取主表数据
     *
     * @param request 请求对象
     * @return 跳转URL
     * @author temdy
     */
    @RequestMapping(value = "/table")
    @ControllerRecorder(path = "/user/table", validateClass = UserTableRequestDto.class, validateTypeClass = UserTableRequestDto.Table.class)
    public ResponseDto table(@RequestBody UserTableRequestDto requestDto, HttpServletRequest request) {

        PageInfo pageInfo = userFacade.queryTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo);
    }


    /**
     * 保存用户
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/save")
    @ControllerRecorder(path = "/user/table", validateClass = UserSaveRequestDto.class, validateTypeClass = UserSaveRequestDto.Insert.class)
    public ResponseDto save(@RequestBody UserSaveRequestDto requestDto) {

        userFacade.save(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

    /**
     * 保存用户
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/delete")
    @ControllerRecorder(path = "/user/delete", validateClass = UserSaveRequestDto.class, validateTypeClass = UserSaveRequestDto.Delete.class)
    public ResponseDto delete(@RequestBody UserSaveRequestDto requestDto) {

        userFacade.save(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }


}
