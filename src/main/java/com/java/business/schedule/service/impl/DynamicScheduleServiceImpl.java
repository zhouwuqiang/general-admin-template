package com.java.business.schedule.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.business.schedule.dto.FormTableRequestDto;
import com.java.business.schedule.dto.ScheduleTableRequestDto;
import com.java.business.schedule.entity.ScheduledTask;
import com.java.business.schedule.entity.ScheduledTaskForm;
import com.java.business.schedule.mapper.ScheduledTaskFormMapper;
import com.java.business.schedule.mapper.ScheduledTaskMapper;
import com.java.business.schedule.service.DynamicScheduleService;
import com.java.general.constant.SystemCommonConstant;
import com.java.general.utils.UuidCodeWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/7 22:18
 */
@Service
public class DynamicScheduleServiceImpl implements DynamicScheduleService {


    @Autowired
    private ScheduledTaskMapper scheduledTaskMapper;

    @Autowired
    private ScheduledTaskFormMapper scheduledTaskFormMapper;

    @Override
    public PageInfo queryTable(ScheduleTableRequestDto requestDto) {
        Example example = new Example(ScheduledTask.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("deleteFlag", "00");
        criteria.andEqualTo("taskCode", requestDto.getTaskCode());
        criteria.andEqualTo("taskType", requestDto.getTaskType());

        if (StringUtils.isNotBlank(requestDto.getTaskName())) {
            criteria.andCondition("task_name like", "%" + requestDto.getTaskName() + "%");
        }

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());

        List<ScheduledTask> queryList = scheduledTaskMapper.selectByExample(example);

        return new PageInfo<>(queryList);

    }

    @Override
    public void save(ScheduledTask scheduledTask) {
        if (scheduledTask.getId() == null) {
            scheduledTask.setTaskCode(UuidCodeWorker.nextCode("TASK"));
            scheduledTaskMapper.insertSelective(scheduledTask);
        } else {
            scheduledTaskMapper.updateByPrimaryKeySelective(scheduledTask);
        }
    }

    @Override
    public PageInfo queryFormTable(FormTableRequestDto requestDto) {
        ScheduledTaskForm taskForm = new ScheduledTaskForm();
        taskForm.setDeleteFlag(SystemCommonConstant.DeleteFlag.NORMAL);
        taskForm.setTaskCode(requestDto.getTaskCode());

        PageHelper.startPage(requestDto.getPageNum(), requestDto.getPageSize());

        List<ScheduledTaskForm> queryList = scheduledTaskFormMapper.select(taskForm);

        return new PageInfo<>(queryList);
    }

    @Override
    public ScheduledTaskForm formSave(ScheduledTaskForm taskForm) {
        if (taskForm.getId() == null){
            taskForm.setInputName(UuidCodeWorker.nextCode("FORM"));
            scheduledTaskFormMapper.insertSelective(taskForm);
        }else{
            scheduledTaskFormMapper.updateByPrimaryKeySelective(taskForm);
        }
        return taskForm;
    }
}
