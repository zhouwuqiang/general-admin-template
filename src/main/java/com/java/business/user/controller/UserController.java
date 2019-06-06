package com.java.business.user.controller;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserDetailRequestDto;
import com.java.business.user.dto.UserDetailResponseDto;
import com.java.business.user.dto.UserSaveRequestDto;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.facade.UserFacade;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import com.java.general.utils.SpringContextUtil;
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
     * 查询用户详情
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/detail")
    @ControllerRecorder(path = "/user/detail")
    public ResponseDto detail(@RequestBody UserDetailRequestDto requestDto) {

        UserDetailResponseDto responseDto = userFacade.detail(requestDto);

        return ResponseUtil.bindSuccessResponse(responseDto);
    }

    /**
     * 个人用户中心
     *
     * @return
     */
    @RequestMapping(value = "/center")
    @ControllerRecorder(path = "/user/center")
    public ResponseDto center() {
        UserDetailRequestDto requestDto = new UserDetailRequestDto();
        requestDto.setUserCode(SpringContextUtil.getLoginUser().getUserCode());
        UserDetailResponseDto responseDto = userFacade.detail(requestDto);

        return ResponseUtil.bindSuccessResponse(responseDto);
    }

    /**
     * 保存用户
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/save")
    @ControllerRecorder(path = "/user/save")
    public ResponseDto save(@RequestBody UserSaveRequestDto requestDto) {

        userFacade.save(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

    /**
     * 删除用户
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/delete")
    @ControllerRecorder(path = "/user/delete")
    public ResponseDto delete(@RequestBody UserSaveRequestDto requestDto) {

        userFacade.save(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }


}
