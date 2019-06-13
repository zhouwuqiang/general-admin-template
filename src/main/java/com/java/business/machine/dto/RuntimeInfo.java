package com.java.business.machine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/10 1:21
 */
@Getter
@Setter
@ToString
public class RuntimeInfo {

    /**
     * 进程PID
     */
    private String name;

    /**
     * jvm规范名称
     */
    private String specName;

    /**
     * jvm规范运营商
     */
    private String  specVendor;

    /**
     * jvm规范版本
     */
    private String specVersion;

    /**
     * 启动毫秒 返回虚拟机在毫秒内的开始时间。该方法返回了虚拟机启动时的近似时间
     */
    private String startTime;

    /**
     * 系统属性
     */
    private Map<String, String> systemProperties;

    /**
     * jvm正常运行时间（毫秒）
     */
    private String uptime;

    /**
     * jvm名称
     */
    private String vmName;

    /**
     * jvm运营商
     */
    private String  vmVendor;

    /**
     * jvm实现版本
     */
    private String vmVersion;

    /**
     * vm参数
     */
    private List<String> args;

    /**
     * 类路径
     */
    private String classPath;

    /**
     * 引导类路径
     */
    private String bootClassPath;

    /**
     * 库路径
     */
    private String libraryPath;

}
