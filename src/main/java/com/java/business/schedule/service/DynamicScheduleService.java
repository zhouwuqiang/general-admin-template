package com.java.business.schedule.service;

import com.github.pagehelper.PageInfo;
import com.java.business.schedule.dto.FormTableRequestDto;
import com.java.business.schedule.dto.ScheduleTableRequestDto;
import com.java.business.schedule.entity.ScheduledTask;
import com.java.business.schedule.entity.ScheduledTaskForm;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/7 22:17
 */
public interface DynamicScheduleService {

    /**
     * 查询配置任务表格
     * @param requestDto
     * @return
     */
    PageInfo queryTable(ScheduleTableRequestDto requestDto);

    /**
     * 保存定时任务
     * @param scheduledTask
     */
    void save(ScheduledTask scheduledTask);

    /**
     * 查询form 表格
     * @param requestDto
     * @return
     */
    PageInfo queryFormTable(FormTableRequestDto requestDto);

    /**
     * 保存表单
     * @param taskForm
     * @return
     */
    ScheduledTaskForm formSave(ScheduledTaskForm taskForm);
}
