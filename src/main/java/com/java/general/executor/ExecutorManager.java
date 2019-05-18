package com.java.general.executor;


import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.java.general.exception.BusinessException;
import com.java.general.executor.constant.ExecutorConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * description : 线程队列管理类
 * 使用时需要创建 使用结束需要销毁
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/1/17 18:42
 */
public class ExecutorManager {


    private static Logger LOGGER = LoggerFactory.getLogger(ExecutorManager.class);

    /**
     * 线程池执行对象
     */
    private ExecutorService executorService;


    public ExecutorManager(String poolName) {
        /**
         * 线程工厂
         */
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        synchronized (this) {
                            LOGGER.error("Uncaught exception in thread >>{}:{}", t.getName(), e.getMessage(), e);
                        }
                    }
                }).setNameFormat("ExecutorManagerUtils-Thread-pool-%d").build();


        /**
         * 线程使用队列
         */
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

        executorService = new ThreadPoolExecutor(ExecutorConstant.ThreadPoolConfig.CORE_POOL_SIZE,
                ExecutorConstant.ThreadPoolConfig.MAXIMUM_POOL_SIZE,
                ExecutorConstant.ThreadPoolConfig.KEEP_ALIVE_TIME,
                ExecutorConstant.ThreadPoolConfig.TIME_UNIT,
                workQueue, namedThreadFactory);

    }

    /**
     * 批量返回线程任务
     * 主线程阻塞,等待批量返回结果
     *
     * @param taskList
     * @param <T>
     * @return
     */
    public <T> List<T> doSubmit(List<Callable<T>> taskList) {
        List<T> result = new ArrayList<>();

        List<Future<T>> futures = new ArrayList<>();
        for (Callable<T> task : taskList) {
            Future<T> future = executorService.submit(task);
            futures.add(future);
        }

        for (Future<T> future : futures) {
            try {
                result.add(future.get());
            } catch (Exception e) {
                LOGGER.error("批量返回线程任务执行异常!:{}", JSON.toJSONString(taskList), e);
                throw new BusinessException("批量返回线程任务执行异常!" + e.getMessage());
            }
        }
        return result;
    }

    /**
     * 提交 单个返回线程任务
     * 主线程阻塞,等待返回结果
     *
     * @param task
     * @param <T>
     * @return
     */
    public <T> T doSubmit(Callable<T> task) {

        Future<T> future = executorService.submit(task);
        try {
            return future.get();
        } catch (Exception e) {
            LOGGER.error("单个返回线程任务执行异常!:{}", JSON.toJSONString(task), e);
            throw new BusinessException("单个返回线程任务执行异常!" + e.getMessage());
        }
    }


    /**
     * 无返回的单个线程任务
     *
     * @param task
     */
    public void doExecutor(Runnable task) {
        executorService.execute(task);
    }

    /**
     * 无返回的批量线程任务
     *
     * @param tasks
     */
    public void doExecutor(List<Runnable> tasks) {
        for (Runnable task : tasks) {
            executorService.execute(task);
        }
    }

    /**
     * 关闭线程池
     * 所有线程执行完毕后续操作
     *
     * @return
     */
    public boolean shoutDown() {
        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                LOGGER.info("所有线程执行结束!成功关闭线程池");
                return true;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOGGER.info("线程休眠异常", e);
            }
        }
    }
}
