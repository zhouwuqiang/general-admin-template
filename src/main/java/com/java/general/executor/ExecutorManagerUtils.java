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
 * description : 线程队列管理工具类
 *               静态方法直接使用,使用后无需销毁系统保留
 * @author alger
 * @version 1.0.0
 * @date 2019/1/17 18:42
 */
public class ExecutorManagerUtils {


    private static Logger LOGGER = LoggerFactory.getLogger(ExecutorManagerUtils.class);

    /**
     * 线程池执行对象
     */
    private static ExecutorService executorService = new ThreadPoolExecutor(ExecutorConstant.ThreadPoolConfig.CORE_POOL_SIZE,
            ExecutorConstant.ThreadPoolConfig.MAXIMUM_POOL_SIZE,
            ExecutorConstant.ThreadPoolConfig.KEEP_ALIVE_TIME,
            ExecutorConstant.ThreadPoolConfig.TIME_UNIT,
            new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setUncaughtExceptionHandler(
            new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    synchronized (this) {
                        LOGGER.error("Uncaught exception in thread >>{}:{}" , t.getName() , e.getMessage(),e);
                    }
                }
            }).setNameFormat("ExecutorManagerUtils-Thread-pool-%d").build());

    private ExecutorManagerUtils() {

    }

    /**
     * 批量返回线程任务
     * 主线程阻塞,等待批量返回结果
     *
     * @param taskList
     * @param <T>
     * @return
     */
    public static <T> List<T> doSubmit(List<Callable<T>> taskList) {
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
     * 返回future对象
     *
     * @param task
     * @param <T>
     * @return
     */
    public static <T> Future<T> doSubmit(Callable<T> task) {
        Future<T> future = executorService.submit(task);
        return future;
    }

    /**
     * 提交 单个返回线程任务
     * 返回返回值
     *
     * @param task
     * @param <T>
     * @return
     */
    public static <T> T submitGetResult(Callable<T> task) {
        Future<T> future = executorService.submit(task);
        try {
            return future.get();
        } catch (InterruptedException e) {
            LOGGER.error("终端线程异常:{}", JSON.toJSONString(task), e);
        } catch (ExecutionException e) {
            LOGGER.error("执行线程任务异常:{}", JSON.toJSONString(task), e);
        }
        throw new BusinessException("线程任务执行异常!");
    }

    /**
     * 无返回的单个线程任务
     *
     * @param task
     */
    public static void doExecutor(Runnable task) {
        executorService.execute(task);
    }

    /**
     * 无返回的批量线程任务
     *
     * @param tasks
     */
    public static void doExecutor(List<Runnable> tasks) {
        for (Runnable task : tasks) {
            executorService.execute(task);
        }
    }
}
