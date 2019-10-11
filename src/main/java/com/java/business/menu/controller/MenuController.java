package com.java.business.menu.controller;

import com.github.pagehelper.PageInfo;
import com.java.business.menu.dto.MenuListRequestDto;
import com.java.business.menu.dto.MenuSaveRequestDto;
import com.java.business.menu.dto.MenuTableRequestDto;
import com.java.business.menu.facade.MenuFacade;
import com.java.general.config.security.dto.Menu;
import com.java.general.config.security.dto.User;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(value = "菜单管理value",tags="菜单管理")
public class MenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);


    @Autowired
    private MenuFacade menuFacade;

    /**
     * 获取菜单列表
     *
     * @return
     */
    @ApiOperation("获取当前登录用户菜单列表")
    @GetMapping(value = "/list")
    @ControllerRecorder(path = "/menu/list")
    public ResponseDto list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        return ResponseUtil.bindSuccessResponse(userDetails.getMenus());
    }

    /**
     * 获取主表数据
     *
     * @param requestDto
     * @return
     */
    @ApiOperation("获取菜单管理列表")
    @PostMapping(value = "/table")
    @ControllerRecorder(path = "/menu/table", validateClass = MenuTableRequestDto.class)
    public ResponseDto table(@RequestBody MenuTableRequestDto requestDto) {

        PageInfo pageInfo = menuFacade.queryTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo.getList());
    }


    /**
     * 保存组织结构
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "保存组织结构",notes = "保存组织结构")
    @PostMapping(value = "/save")
    @ControllerRecorder(path = "/menu/save")
    public ResponseDto save(@RequestBody MenuSaveRequestDto requestDto) {

        menuFacade.save(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

    /**
     * 删除组织结构
     *
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "删除组织结构",notes = "删除组织结构")
    @PostMapping(value = "/delete")
    @ControllerRecorder(path = "/menu/delete")
    public ResponseDto delete(@RequestBody MenuSaveRequestDto requestDto) {

        menuFacade.delete(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }



    /*************************************************** 角色授权 **********************************************/

    /**
     * 获取菜单结构树
     *
     * @return
     */
    @ApiOperation(value = "获取菜单结构树",notes = "获取菜单结构树")
    @PostMapping(value = "/list/tree")
    @ControllerRecorder(path = "/menu/list/tree")
    public ResponseDto listTree(@RequestBody MenuListRequestDto menuListRequestDto) {

        List<Menu> list = menuFacade.queryListTree(menuListRequestDto);

        return ResponseUtil.bindSuccessResponse(list);
    }


}
