package com.java.business.schedule.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "scheduled_task")
public class ScheduledTask {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 任务编号
     */
    @Column(name = "task_code")
    private String taskCode;

    /**
     * 任务名称
     */
    @Column(name = "task_name")
    private String taskName;

    /**
     * 任务类型(字典表: task_code)
     */
    @Column(name = "task_type")
    private String taskType;

    /**
     * 远程任务地址
     */
    @Column(name = "task_url")
    private String taskUrl;

    /**
     * 任务类名
     */
    @Column(name = "task_service_name")
    private String taskServiceName;

    /**
     * 任务方法
     */
    @Column(name = "task_service_method")
    private String taskServiceMethod;

    /**
     * 任务描述
     */
    @Column(name = "task_desc")
    private String taskDesc;

    /**
     * 任务表达式
     */
    @Column(name = "task_cron")
    private String taskCron;

    /**
     * 任务状态
     */
    @Column(name = "task_status")
    private String taskStatus;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 更新用户编号
     */
    @Column(name = "UPDATE_USER")
    private String updateUser;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 创建用户编号
     */
    @Column(name = "CREATE_USER")
    private String createUser;

    /**
     * 删除标识(00-正常 01-删除)
     */
    @Column(name = "DELETE_FLAG")
    private String deleteFlag;
}