package com.java.business.role.controller;

import com.github.pagehelper.PageInfo;
import com.java.business.role.dto.RoleSaveRequestDto;
import com.java.business.role.dto.RoleTableRequestDto;
import com.java.business.role.facade.RoleFacade;
import com.java.business.user.dto.UserSaveRequestDto;
import com.java.business.user.dto.UserTableRequestDto;
import com.java.business.user.facade.UserFacade;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
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
@RequestMapping("/role")
public class RoleController {


    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);


    @Autowired
    private RoleFacade roleFacade;

    /**
     * 获取主表数据
     *
     * @param request 请求对象
     * @return 跳转URL
     * @author temdy
     */
    @RequestMapping(value = "/table")
    @ControllerRecorder(path = "/role/table", validateClass = RoleTableRequestDto.class)
    public ResponseDto table(@RequestBody RoleTableRequestDto requestDto, HttpServletRequest request) {

        PageInfo pageInfo = roleFacade.queryTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo);
    }


    /**
     * 保存角色
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/save")
    @ControllerRecorder(path = "/role/table", validateClass = RoleSaveRequestDto.class)
    public ResponseDto save(@RequestBody RoleSaveRequestDto requestDto) {

        roleFacade.save(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

    /**
     * 删除角色
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/delete")
    @ControllerRecorder(path = "/role/delete", validateClass = RoleSaveRequestDto.class)
    public ResponseDto delete(@RequestBody RoleSaveRequestDto requestDto) {

        roleFacade.delete(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }


}
