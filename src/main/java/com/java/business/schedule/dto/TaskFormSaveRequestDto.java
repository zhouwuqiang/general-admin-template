package com.java.business.schedule.dto;

import com.java.general.request.dto.PageRequestDto;
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
public class TaskFormSaveRequestDto {

    private Integer id;

    /**
     * 任务编号
     */
    private String taskCode;

    /**
     * 任务表单名称
     */
    private String inputName;

    /**
     * 任务表单label
     */
    private String inputLabel;

    /**
     * 任务表单备注
     */
    private String inputMemo;

    /**
     * 任务表单是否必填
     */
    private String inputNotNull;


}
