package com.java.general.config.schedule;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/9 14:20
 */
@EnableScheduling
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(4,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build());
        scheduledTaskRegistrar.setScheduler(executorService);
    }
}
