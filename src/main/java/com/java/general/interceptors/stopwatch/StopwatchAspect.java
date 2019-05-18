package com.java.general.interceptors.stopwatch;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/15 16:04
 */

@Aspect
@Component
public class StopwatchAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(StopwatchAspect.class);

    ThreadLocal<Long> beginTime = new ThreadLocal<>();


    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.java.general.interceptors.stopwatch.Stopwatch)")
    public void stopwatchAspect() {
    }

    /**
     * 前置通知：在目标方法执行前调用
     *
     * @param joinPoint
     */
    @Before("stopwatchAspect()")
    public void before(JoinPoint joinPoint) {
        beginTime.set(System.currentTimeMillis());
        LOGGER.info("前置通知：在目标方法执行前调用!");
    }

    /**
     * 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
     */
    @AfterReturning("stopwatchAspect()")
    public void afterReturning(JoinPoint joinPoint) {
        LOGGER.info("后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行!");
    }

    /**
     * 后置/最终通知：无论目标方法在执行过程中出现一场都会在它之后调用
     */
    @After("stopwatchAspect()")
    public void after(JoinPoint joinPoint) {
        LOGGER.info("后置/最终通知：无论目标方法在执行过程中出现一场都会在它之后调用!{}",  System.currentTimeMillis()-beginTime.get());
        beginTime.remove();
    }

    /**
     * 异常通知：目标方法抛出异常时执行
     */
    @AfterThrowing("stopwatchAspect()")
    public void afterThrowing(JoinPoint joinPoint) {
        LOGGER.info("异常通知：目标方法抛出异常时执行");
    }

    /**
     * 环绕通知：灵活自由的在目标方法中切入代码
     *
     * @param joinPoint
     */
    @Around("stopwatchAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        LOGGER.info("目标方法名为:{}", joinPoint.getSignature().getName());
        LOGGER.info("目标方法所属类的类名:{}", joinPoint.getSignature().getDeclaringTypeName());
        LOGGER.info("目标方法传入参数:{}", joinPoint.getArgs());
        try {
            Stopwatch stopwatch = getDeclaredAnnotation(joinPoint);
            LOGGER.info("注解参数:{}", stopwatch.mark());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        LOGGER.info("环绕通知:目标执行前");
        Object object = joinPoint.proceed();
        LOGGER.info("环绕通知:目标执行后",JSON.toJSONString(object));
        return object;
    }


    /**
     * 获取方法中声明的注解
     *
     * @param joinPoint
     */
    private Stopwatch getDeclaredAnnotation(JoinPoint joinPoint) throws NoSuchMethodException {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        Stopwatch annotation = AnnotationUtils.findAnnotation(objMethod, Stopwatch.class);
        // 返回
        return annotation;
    }

}
