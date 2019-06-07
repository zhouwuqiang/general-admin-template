package com.java.business.schedule.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
public class ScheduleSaveRequestDto {

    private Integer id;

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

    /**
     * 远程任务地址
     */
    private String taskUrl;

    /**
     * 任务类名
     */
    private String taskServiceName;

    /**
     * 任务方法
     */
    private String taskServiceMethod;

    /**
     * 任务描述
     */
    private String taskDesc;

    /**
     * 任务表达式
     */
    private String taskCron;

    /**
     * 任务状态
     */
    private String taskStatus;

}
