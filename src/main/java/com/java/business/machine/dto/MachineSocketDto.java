package com.java.business.machine.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/13 19:30
 */
@Getter
@Setter
public class MachineSocketDto<T> {

    /**
     * 机器信息
     */
    private MachineInfo machineInfo;

    /**
     * jvm内存信息
     */
    private MemoryInfo memoryInfo;

    /**
     * 线程信息
     */
    private ThreadDto threadDto;
}
