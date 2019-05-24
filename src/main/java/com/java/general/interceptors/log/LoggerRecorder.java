package com.java.general.interceptors.log;

import java.lang.annotation.*;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/15 16:01
 */

@Retention(RetentionPolicy.RUNTIME)//定义注解的保留策略
@Target({ElementType.METHOD})//运行标注的位置
@Inherited//说明子类可以继承父类中的该注解
@Documented//说明该注解将被包含在javadoc中
public @interface LoggerRecorder {

    /**
     * 日志类别
     * @return
     */
    String type() default "";
}
