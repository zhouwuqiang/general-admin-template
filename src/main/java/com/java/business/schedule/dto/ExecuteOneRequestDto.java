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
public class ExecuteOneRequestDto extends PageRequestDto {

    /**
     * 任务编号
     */
    private String taskCode;

    /**
     * 参数
     */
    private String params;
}
