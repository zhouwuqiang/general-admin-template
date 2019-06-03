package com.java.business.organization.controller;

import com.github.pagehelper.PageInfo;
import com.java.business.organization.dto.OrganizationSaveRequestDto;
import com.java.business.organization.dto.OrganizationTableRequestDto;
import com.java.business.organization.facade.OrganizationFacade;
import com.java.business.user.dto.UserSaveRequestDto;
import com.java.business.wordbook.dto.WordbookSaveRequestDto;
import com.java.business.wordbook.dto.WordbookTableRequestDto;
import com.java.general.interceptors.controller.ControllerRecorder;
import com.java.general.response.dto.ResponseDto;
import com.java.general.response.utils.ResponseUtil;
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
 * @date 2019/6/4 0:57
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {


    @Autowired
    private OrganizationFacade organizationFacade;
    /**
     * 获取字典数据
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
     * 保存字典
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
     * 保存删除字典参数
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


}
