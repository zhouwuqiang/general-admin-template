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
public class ChangeStatusRequestDto {


    /**
     * 任务编号
     */
    private String taskCode;

    /**
     * 任务状态
     */
    private String taskStatus;

}
