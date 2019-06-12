package com.java.business.machine.facade;

import com.java.business.machine.dto.MachineInfo;
import com.java.business.machine.dto.MemoryInfo;

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
}
