package com.java.general.interceptors.data;

import com.java.general.config.datasource.MultipleDataSource;
import com.java.general.interceptors.utils.AspectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * description : 通过注解切换数据库数据源
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/15 16:04
 */

@Aspect
@Component
public class DataSourceAspect {

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.java.general.interceptors.data.DataSource)")
    public void dataSourceAspect() {
    }

    /**
     * 环绕通知：灵活自由的在目标方法中切入代码
     *
     * @param joinPoint
     */
    @Around("dataSourceAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        DataSource dataSource = AspectUtils.getDeclaredAnnotation(joinPoint, DataSource.class);
        MultipleDataSource.setDataSourceKey(dataSource.name());

        Object object = joinPoint.proceed();

        MultipleDataSource.removeDataSourceKey();
        return object;
    }

}
