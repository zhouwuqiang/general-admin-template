package com.java.business.machine.facade;

import com.java.business.machine.dto.MachineInfo;
import com.java.business.machine.dto.MemoryInfo;
import com.java.business.machine.dto.RuntimeInfo;
import com.java.business.machine.dto.ThreadDto;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/10 0:50
 */
public interface MachineFacade {

    /**
     * 获取机器信息
     * @return
     */
    MachineInfo getMachineInfo();

    /**
     * 获取jvm 内存信息
     * @return
     */
    MemoryInfo getMemoryInfo();

    /**
     * 获取运行信息
     * @return
     */
    RuntimeInfo getRuntimeInfo();

    /**
     * 获取线程信息
     * @return
     */
    ThreadDto getThreadDto();
}
