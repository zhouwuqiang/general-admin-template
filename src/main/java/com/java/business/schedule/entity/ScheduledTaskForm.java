package com.java.business.schedule.entity;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "scheduled_task_form")
public class ScheduledTaskForm {
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
     * 任务表单名称
     */
    @Column(name = "input_name")
    private String inputName;

    /**
     * 任务表单label
     */
    @Column(name = "input_label")
    private String inputLabel;

    /**
     * 任务表单备注
     */
    @Column(name = "input_memo")
    private String inputMemo;

    /**
     * 任务表单是否必填
     */
    @Column(name = "input_not_null")
    private String inputNotNull;

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