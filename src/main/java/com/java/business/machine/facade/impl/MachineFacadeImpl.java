package com.java.business.machine.facade.impl;

import com.java.business.machine.dto.*;
import com.java.business.machine.facade.MachineFacade;
import com.java.business.machine.utils.MachineUtils;
import org.springframework.stereotype.Service;

import java.lang.management.ThreadInfo;

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
        MemoryInfo memoryInfo = new MemoryInfo();
        memoryInfo.setJvmHeapMemory(MachineUtils.getJvmHeapMemory());
        memoryInfo.setJvmNonHeapMemory(MachineUtils.getJvmNonHeapMemory());
        return memoryInfo;
    }

    @Override
    public RuntimeInfo getRuntimeInfo() {
        return MachineUtils.getRuntimeInfo();
    }

    @Override
    public ThreadDto getThreadDto() {
        ThreadDto threadDto = new ThreadDto();

        ThreadData threadData = MachineUtils.getThreadInfo();

        threadDto.setThreadCount(threadData.getThreadCount());
        threadDto.setDaemonThreadCount(threadData.getDaemonThreadCount());
        threadDto.setPeakThreadCount(threadData.getPeakThreadCount());
        threadDto.setTotalStartedThreadCount(threadData.getTotalStartedThreadCount());

        ThreadInfo[] threadArray = threadData.getThreadArray();
        int newData = 0;
        int blockedData = 0;
        int runnableData = 0;
        int terminatedData = 0;
        int waitingData = 0;
        int timedWaitingData = 0;


        for (ThreadInfo item : threadArray) {
            switch (item.getThreadState()) {
                case NEW:
                    newData++;
                    break;
                case BLOCKED:
                    blockedData++;
                    break;
                case RUNNABLE:
                    runnableData++;
                    break;
                case TERMINATED:
                    terminatedData++;
                    break;
                case WAITING:
                    waitingData++;
                    break;
                case TIMED_WAITING:
                    timedWaitingData++;
                    break;
                default:

            }
        }

        threadDto.setNewData(newData);
        threadDto.setBlockedData(blockedData);
        threadDto.setRunnableData(runnableData);
        threadDto.setTerminatedData(terminatedData);
        threadDto.setWaitingData(waitingData);
        threadDto.setTimedWaitingData(timedWaitingData);

        return threadDto;
    }
}
