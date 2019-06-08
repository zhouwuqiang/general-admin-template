package com.java.business.schedule.controller;

import com.github.pagehelper.PageInfo;
import com.java.business.schedule.dto.FormTableRequestDto;
import com.java.business.schedule.dto.ScheduleSaveRequestDto;
import com.java.business.schedule.dto.ScheduleTableRequestDto;
import com.java.business.schedule.dto.TaskFormSaveRequestDto;
import com.java.business.schedule.entity.ScheduledTaskForm;
import com.java.business.schedule.facade.DynamicScheduleFacade;
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
 * @date 2019/6/7 23:29
 */
@RestController
@RequestMapping("/schedule")
public class DynamicScheduleController {


    @Autowired
    private DynamicScheduleFacade scheduleFacade;


    /**
     * 查询任务列表
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/table")
    @ControllerRecorder(path = "/schedule/table")
    public ResponseDto table(@RequestBody ScheduleTableRequestDto requestDto) {

        PageInfo pageInfo = scheduleFacade.queryTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo);
    }

    /**
     * 保存定时任务
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/save")
    @ControllerRecorder(path = "/schedule/save")
    public ResponseDto save(@RequestBody ScheduleSaveRequestDto requestDto) {

        scheduleFacade.save(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

    /**
     * 查询任务列表
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/form/table")
    @ControllerRecorder(path = "/schedule/form/table")
    public ResponseDto formTable(@RequestBody FormTableRequestDto requestDto) {

        PageInfo pageInfo = scheduleFacade.queryFormTable(requestDto);

        return ResponseUtil.bindSuccessResponse(pageInfo);
    }

    /**
     * 保存任务表单
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/form/save")
    @ControllerRecorder(path = "/schedule/form/save")
    public ResponseDto formSave(@RequestBody TaskFormSaveRequestDto requestDto) {

        ScheduledTaskForm taskForm = scheduleFacade.formSave(requestDto);

        return ResponseUtil.bindSuccessResponse(taskForm);
    }

    /**
     * 保存任务表单
     *
     * @param requestDto
     * @return
     */
    @RequestMapping(value = "/form/delete")
    @ControllerRecorder(path = "/schedule/form/delete")
    public ResponseDto formDelete(@RequestBody TaskFormSaveRequestDto requestDto) {

        scheduleFacade.formDelete(requestDto);

        return ResponseUtil.bindSuccessResponse();
    }

}
