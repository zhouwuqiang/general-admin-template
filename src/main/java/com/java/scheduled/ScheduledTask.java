package com.java.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * description :
 * @Scheduled(fixedRate=2000)：上一次开始执行时间点后2秒再次执行；
 *
 * @Scheduled(fixedDelay=2000)：上一次执行完毕时间点后2秒再次执行；
 *
 * @Scheduled(initialDelay=1000, fixedDelay=2000)：第一次延迟1秒执行，然后在上一次执行完毕时间点后2秒再次执行；
 *
 * @Scheduled(cron="* * * * * ?")：按cron规则执行。
 * @author alger
 * @version 1.0.0
 * @date 2019/4/9 14:34
 */
@Component
@Slf4j
public class ScheduledTask {



    /**
     * 定时任务开始执行
     */
    @Scheduled(fixedDelay=1000*5)
    public void pullPlatformStatisticsInfo() {
        log.info("定时任务开始执行");
    }



}
