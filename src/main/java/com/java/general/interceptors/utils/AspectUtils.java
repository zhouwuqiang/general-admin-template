package com.java.general.interceptors.utils;

import com.java.general.exception.BusinessException;
import com.java.general.interceptors.log.LoggerRecorderAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/5/24 10:38
 */
public class AspectUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerRecorderAspect.class);

    /**
     * 切点中获取切点注解
     *
     * @param joinPoint
     */
    public static <T extends Annotation> T getDeclaredAnnotation(final JoinPoint joinPoint, Class<T> annotationType) {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        try {
            // 拿到方法对应的参数类型
            Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
            // 根据类、方法、参数类型（重载）获取到方法的具体信息
            Method objMethod = targetClass.getMethod(methodName, parameterTypes);
            // 拿到方法定义的注解信息
            return AnnotationUtils.findAnnotation(objMethod, annotationType);
        } catch (NoSuchMethodException e) {
            LOGGER.error("方法{}获取声明切点异常{}", methodName, e);
        }
        throw new BusinessException("类:" + targetClass.getName() + "方法:" + methodName + " 不包含注解" + annotationType.getName());
    }
}
