package com.java.general.executor;

import com.java.general.utils.DateUtils;
import org.junit.Test;


/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/7/12 17:33
 */
public class ExecutorManagerUtilsTest {


    @Test
    public void executorTest() {

        for (int i = 0; i < 1040; i++) {
            ExecutorManagerUtils.doExecutor(getRunnable());
        }

        DateUtils.sleepByMillis(60 * 20 * 1000L);
    }


    private Runnable getRunnable() {
        Runnable runnable = () -> {
            DateUtils.sleepByMillis(2000L);
        };
        return runnable;
    }
}
