package com.java.business.schedule.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.schedule.dto.FormTableRequestDto;
import com.java.business.schedule.dto.ScheduleSaveRequestDto;
import com.java.business.schedule.dto.ScheduleTableRequestDto;
import com.java.business.schedule.dto.TaskFormSaveRequestDto;
import com.java.business.schedule.entity.ScheduledTask;
import com.java.business.schedule.entity.ScheduledTaskForm;
import com.java.business.schedule.facade.DynamicScheduleFacade;
import com.java.business.schedule.service.DynamicScheduleService;
import com.java.general.constant.SystemCommonConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/7 23:28
 */
@Service
public class DynamicScheduleFacadeImpl implements DynamicScheduleFacade {

    @Autowired
    private DynamicScheduleService dynamicScheduleService;

    @Override
    public PageInfo queryTable(ScheduleTableRequestDto requestDto) {
        return dynamicScheduleService.queryTable(requestDto);
    }

    @Override
    public void save(ScheduleSaveRequestDto requestDto) {
        ScheduledTask scheduledTask = new ScheduledTask();
        BeanUtils.copyProperties(requestDto, scheduledTask);
        dynamicScheduleService.save(scheduledTask);
    }

    @Override
    public PageInfo queryFormTable(FormTableRequestDto requestDto) {
        return dynamicScheduleService.queryFormTable(requestDto);
    }

    @Override
    public ScheduledTaskForm formSave(TaskFormSaveRequestDto requestDto) {

        ScheduledTaskForm taskForm = new ScheduledTaskForm();

        BeanUtils.copyProperties(requestDto,taskForm);

        taskForm = dynamicScheduleService.formSave(taskForm);

        return taskForm;
    }

    @Override
    public void formDelete(TaskFormSaveRequestDto requestDto) {
        ScheduledTaskForm taskForm = new ScheduledTaskForm();
        taskForm.setId(requestDto.getId());
        taskForm.setDeleteFlag(SystemCommonConstant.DeleteFlag.DELETE);
        dynamicScheduleService.formSave(taskForm);
    }
}
