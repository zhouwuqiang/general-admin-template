package com.java.general.config.schedule;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.java.general.executor.constant.ExecutorConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * description : 初始化动态task执行器
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/9 14:20
 */
@Configuration
public class DynamicScheduleConfig{

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(ExecutorConstant.ThreadPoolConfig.CORE_POOL_SIZE);
        threadPoolTaskScheduler.setErrorHandler(new DynamicTaskErrorHandler());
        threadPoolTaskScheduler.setThreadFactory(new ThreadFactoryBuilder().setNameFormat("DynamicTask-Thread-pool-%d").build());
        return threadPoolTaskScheduler;
    }
}
