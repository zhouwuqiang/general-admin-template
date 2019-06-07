package com.java.business.schedule.facade.impl;

import com.github.pagehelper.PageInfo;
import com.java.business.schedule.dto.ScheduleSaveRequestDto;
import com.java.business.schedule.dto.ScheduleTableRequestDto;
import com.java.business.schedule.entity.ScheduledTask;
import com.java.business.schedule.facade.DynamicScheduleFacade;
import com.java.business.schedule.service.DynamicScheduleService;
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
}
