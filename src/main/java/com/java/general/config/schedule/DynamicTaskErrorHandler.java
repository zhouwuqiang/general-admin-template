package com.java.general.config.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ErrorHandler;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/7 21:57
 */
public class DynamicTaskErrorHandler implements ErrorHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(DynamicTaskErrorHandler.class);

    @Override
    public void handleError(Throwable throwable) {
        LOGGER.error("执行定时任务异常,",throwable);
    }
}
