package com.java.business.schedule.facade;

import com.github.pagehelper.PageInfo;
import com.java.business.schedule.dto.ScheduleSaveRequestDto;
import com.java.business.schedule.dto.ScheduleTableRequestDto;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/7 23:28
 */
public interface DynamicScheduleFacade {

    /**
     * 查询任务类表
     * @param requestDto
     * @return
     */
    PageInfo queryTable(ScheduleTableRequestDto requestDto);

    /**
     * 保存定时任务
     * @param requestDto
     */
    void save(ScheduleSaveRequestDto requestDto);
}
