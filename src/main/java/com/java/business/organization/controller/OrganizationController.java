package com.java.business.organization.controller;

import com.github.pagehelper.PageInfo;
import com.java.business.organization.dto.*;
import com.java.business.organization.facade.OrganizationFacade;
import com.java.business.role.dto.RoleRelationSaveRequestDto;
import com.java.business.utils.tree.dto.Tree;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/4 0:57
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {


    @Autowired
    private OrganizationFacade organizationFacade;


    /**
     * 获取组织结构数据
     *
     * @param request 请求对象
     * @return 跳转URL
     * @author temdy
     */
    @RequestMapping(value = "/table")
    @ControllerRecorder(path = "/organization/table")
    public ResponseDto table(@RequestBody OrganizationTableRequestDto requestDto, HttpServletRequest request) {

        PageInfo pageInfo = organizationFacade.queryTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo.getList());
    }


    /**
     * 保存组织结构
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/save")
    @ControllerRecorder(path = "/organization/save")
    public ResponseDto save(@RequestBody OrganizationSaveRequestDto requestDto) {

        organizationFacade.save(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

    /**
     * 删除组织结构
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/delete")
    @ControllerRecorder(path = "/organization/delete")
    public ResponseDto delete(@RequestBody OrganizationSaveRequestDto requestDto) {

        organizationFacade.delete(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }




    /**
     * 保存组织结构关联菜单
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/relation/save")
    @ControllerRecorder(path = "/organization/relation/save", validateClass = RelationSaveRequestDto.class)
    public ResponseDto relationSave(@RequestBody RelationSaveRequestDto requestDto) {

        organizationFacade.relationSave(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }


    /**
     * 获取组织结构树
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/list/tree")
    @ControllerRecorder(path = "/organization/list/tree")
    public ResponseDto listTree(@RequestBody OrganizationTreeRequestDto requestDto) {

        List<Organization> list = organizationFacade.queryListTree(requestDto);

        return ResponseUtil.bindSuccessResponse(list);
    }


}
