package com.java.business.machine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.management.ObjectName;
import java.lang.management.ThreadInfo;
import java.util.List;

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
public class ThreadData {

    /**
     * 名称
     */
    private ObjectName objectName;

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
     * 线程结合
     */
    private ThreadInfo[] threadArray;

}
