package com.java.business.schedule.service;

import com.github.pagehelper.PageInfo;
import com.java.business.schedule.dto.ScheduleTableRequestDto;
import com.java.business.schedule.entity.ScheduledTask;

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
}
