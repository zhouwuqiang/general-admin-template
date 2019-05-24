package com.java.general.interceptors.data;

import com.alibaba.fastjson.JSON;
import com.java.general.config.datasource.MultipleDataSource;
import com.java.general.interceptors.utils.AspectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/15 16:04
 */

@Aspect
@Component
public class DataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.java.general.interceptors.data.DataSource)")
    public void dataSourceAspect() {
    }

    /**
     * 前置通知：在目标方法执行前调用
     *
     * @param joinPoint
     */
    @Before("dataSourceAspect()")
    public void before(JoinPoint joinPoint) {
    }

    /**
     * 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
     */
    @AfterReturning("dataSourceAspect()")
    public void afterReturning(JoinPoint joinPoint) {
    }

    /**
     * 后置/最终通知：无论目标方法在执行过程中出现一场都会在它之后调用
     */
    @After("dataSourceAspect()")
    public void after(JoinPoint joinPoint) {
    }

    /**
     * 异常通知：目标方法抛出异常时执行
     */
    @AfterThrowing("dataSourceAspect()")
    public void afterThrowing(JoinPoint joinPoint) {
        LOGGER.info("异常通知：目标方法抛出异常时执行");
    }

    /**
     * 环绕通知：灵活自由的在目标方法中切入代码
     *
     * @param joinPoint
     */
    @Around("dataSourceAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        LOGGER.info("目标方法名为:{}", joinPoint.getSignature().getName());
        LOGGER.info("目标方法所属类的类名:{}", joinPoint.getSignature().getDeclaringTypeName());
        LOGGER.info("目标方法传入参数:{}", joinPoint.getArgs());

        DataSource dataSource = AspectUtils.getDeclaredAnnotation(joinPoint, DataSource.class);
        MultipleDataSource.setDataSourceKey(dataSource.name());

        LOGGER.info("环绕通知:目标执行前");
        Object object = joinPoint.proceed();
        LOGGER.info("环绕通知:目标执行后{}", JSON.toJSONString(object));

        MultipleDataSource.setDataSourceKey(null);
        return object;
    }

}
