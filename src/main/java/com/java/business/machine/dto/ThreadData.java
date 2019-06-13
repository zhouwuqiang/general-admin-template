package com.java.business.machine.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/13 17:00
 */
@Getter
@Setter
public class ThreadData {

    /**
     * 新增状态
     */
    private Integer newData;

    /**
     * 运行状态
     */
    private Integer runnableData;

    /**
     * 阻塞状态
     */
    private Integer blockedData;

    /**
     * 等待状态
     */
    private Integer waitingData;

    /**
     * 定时等待状态
     */
    private Integer timedWaitingData;

    /**
     * 终止状态
     */
    private Integer terminatedData;

}
