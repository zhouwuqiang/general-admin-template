package com.java.business.machine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/10 0:52
 */
@Getter
@Setter
@ToString
public class MachineInfo {


    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 系统版本
     */
    private String systemVersion;

    /**
     * 系统架构
     */
    private String systemArch;

    /**
     * 可用的内核数
     */
    private int availableProcessors;

    /**
     * 总物理内存
     */
    private long totalPhysicalMemory;

    /**
     * 剩余物理内存
     */
    private long freePhysicalMemorySize;


    /**
     * 进程cpu负载
     */
    private double processCpuLoad;

    /**
     * 系统cpu负载
     */
    private double systemCpuLoad;

}
