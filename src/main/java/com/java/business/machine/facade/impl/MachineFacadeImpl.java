package com.java.business.machine.facade.impl;

import com.java.business.machine.dto.MachineInfo;
import com.java.business.machine.dto.MemoryInfo;
import com.java.business.machine.dto.RuntimeInfo;
import com.java.business.machine.dto.ThreadData;
import com.java.business.machine.facade.MachineFacade;
import com.java.business.machine.utils.MachineUtils;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/10 0:51
 */
@Service
public class MachineFacadeImpl implements MachineFacade {


    @Override
    public MachineInfo getMachineInfo() {
        return MachineUtils.getMachineInfo();
    }

    @Override
    public MemoryInfo getMemoryInfo() {
        MemoryInfo memoryInfo= new MemoryInfo();
        memoryInfo.setJvmHeapMemory(MachineUtils.getJvmHeapMemory());
        memoryInfo.setJvmNonHeapMemory(MachineUtils.getJvmNonHeapMemory());
        return memoryInfo;
    }

    @Override
    public RuntimeInfo getRuntimeInfo() {
        return MachineUtils.getRuntimeInfo();
    }

    @Override
    public ThreadData getThreadData() {
        ThreadData threadData= new ThreadData();
        threadData.setNewData(1);
        threadData.setBlockedData(2);
        threadData.setRunnableData(3);
        threadData.setTerminatedData(3);
        threadData.setWaitingData(3);
        threadData.setTimedWaitingData(2);
        return threadData;
    }
}
