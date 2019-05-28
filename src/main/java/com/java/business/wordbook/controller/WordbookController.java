package com.java.business.wordbook.controller;

import com.github.pagehelper.PageInfo;
import com.java.business.user.dto.UserSaveRequestDto;
import com.java.business.wordbook.dto.WordbookAttributeSaveRequestDto;
import com.java.business.wordbook.dto.WordbookAttributeTableRequestDto;
import com.java.business.wordbook.dto.WordbookSaveRequestDto;
import com.java.business.wordbook.dto.WordbookTableRequestDto;
import com.java.business.wordbook.facade.WordbookFacade;
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
 * @date 2019/5/27 16:54
 */
@RestController
@RequestMapping("/wordbook")
public class WordbookController {


    @Autowired
    private WordbookFacade wordbookFacade;


    /**
     * 获取字典数据
     *
     * @param request 请求对象
     * @return 跳转URL
     * @author temdy
     */
    @RequestMapping(value = "/table")
    @ControllerRecorder(path = "/wordbook/table")
    public ResponseDto table(@RequestBody WordbookTableRequestDto requestDto, HttpServletRequest request) {

        PageInfo pageInfo = wordbookFacade.queryTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo);
    }


    /**
     * 保存字典
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/save")
    @ControllerRecorder(path = "/wordbook/save")
    public ResponseDto save(@RequestBody WordbookSaveRequestDto requestDto) {

        wordbookFacade.save(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

    /**
     * 保存删除字典参数
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/delete")
    @ControllerRecorder(path = "/wordbook/delete")
    public ResponseDto delete(@RequestBody UserSaveRequestDto requestDto) {

        wordbookFacade.delete(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

    /**
     * 获取字典数据
     *
     * @param request 请求对象
     * @return 跳转URL
     * @author temdy
     */
    @RequestMapping(value = "/attribute/table")
    @ControllerRecorder(path = "/wordbook/attribute/table")
    public ResponseDto attributeTable(@RequestBody WordbookAttributeTableRequestDto requestDto, HttpServletRequest request) {

        PageInfo pageInfo = wordbookFacade.queryAttributeTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo);
    }

    /**
     * 保存删除字典参数
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/delete/attribute")
    @ControllerRecorder(path = "/wordbook/delete/attribute")
    public ResponseDto deleteAttribute(@RequestBody WordbookAttributeSaveRequestDto requestDto) {

        wordbookFacade.deleteAttribute(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

}
