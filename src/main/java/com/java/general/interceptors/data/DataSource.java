package com.java.general.interceptors.data;

import java.lang.annotation.*;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/15 16:01
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
@Documented
public @interface DataSource {
    /**
     * 数据库名称
     * @return
     */
    String name() default "";
}
