package com.java.business.schedule.dto;

import com.java.general.request.dto.PageRequestDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/27 19:40
 */
@Setter
@Getter
@ToString
public class ScheduleTableRequestDto extends PageRequestDto {

    /**
     * 任务编号
     */
    private String taskCode;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务类型(字典表: task_code)
     */
    private String taskType;
}
