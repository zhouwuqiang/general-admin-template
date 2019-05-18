package com.java.general.executor.constant;

import java.util.concurrent.TimeUnit;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/1/23 13:48
 */
public interface ExecutorConstant {
    /**
     * 线程池配置
     */
    interface ThreadPoolConfig {
        /**
         * 核心线程数 没有任务最少保留线程数
         */
        Integer CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();

        /**
         * 最大线程数
         */
        Integer MAXIMUM_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

        /**
         * 空闲线程保持存活时间
         */
        Integer KEEP_ALIVE_TIME = 1000;

        /**
         * 时间单位
         */
        TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;
    }
}
