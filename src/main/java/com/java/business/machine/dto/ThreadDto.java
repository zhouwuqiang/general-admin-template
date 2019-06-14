package com.java.business.machine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/13 17:00
 */
@Getter
@Setter
@ToString
public class ThreadDto {

    /**
     * 活动的线程总数
     */
    private int threadCount;

    /**
     * 峰值
     */
    private int peakThreadCount;

    /**
     * 线程总数（被创建并执行过的线程总数）
     */
    private long totalStartedThreadCount;

    /**
     * 当初仍活动的守护线程（daemonThread）总数
     */
    private int daemonThreadCount;


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
