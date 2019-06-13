package com.java.business.machine.utils;


import com.java.business.machine.dto.ClassLoadingInfo;
import com.java.business.machine.dto.JvmMemoryInfo;
import com.java.business.machine.dto.MachineInfo;
import com.java.business.machine.dto.RuntimeInfo;
import com.java.general.utils.DateUtils;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.*;
import java.util.Date;


/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/10 1:16
 */
public class MachineUtils {

    private static final long MB = 1024 * 1024;


    public static void main(String[] args) throws InterruptedException {

        while (true) {
            Thread.sleep(1000);
//            System.out.println(MachineUtils.getMachineInfo());
//            Thread.sleep(1000);
//            System.out.println(MachineUtils.getJvmHeapMemory());
//            System.out.println(MachineUtils.getJvmNoHeapMemory());
//            Thread.sleep(1000);
//            System.out.println(MachineUtils.getClassLoadingInfo());
            System.out.println(MachineUtils.getRuntimeInfo());
        }

    }


    /**
     * 获取系统信息
     *
     * @return
     */
    public static MachineInfo getMachineInfo() {
        MachineInfo machineInfo = new MachineInfo();

        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        machineInfo.setSystemName(operatingSystemMXBean.getName());

        machineInfo.setSystemVersion(operatingSystemMXBean.getVersion());

        machineInfo.setSystemArch(operatingSystemMXBean.getArch());

        machineInfo.setAvailableProcessors(operatingSystemMXBean.getAvailableProcessors());

        machineInfo.setTotalPhysicalMemory(operatingSystemMXBean.getTotalPhysicalMemorySize());

        machineInfo.setFreePhysicalMemorySize(operatingSystemMXBean.getFreePhysicalMemorySize());

        machineInfo.setProcessCpuLoad(operatingSystemMXBean.getProcessCpuLoad());

        machineInfo.setSystemCpuLoad(operatingSystemMXBean.getSystemCpuLoad());

        return machineInfo;
    }

    /**
     * 获取jvm 堆内存情况使用
     *
     * @return
     */
    public static JvmMemoryInfo getJvmHeapMemory() {

        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memory.getHeapMemoryUsage();

        return conventMemoryInfo(memoryUsage);
    }

    /**
     * 获取jvm 非堆内存情况使用
     *
     * @return
     */
    public static JvmMemoryInfo getJvmNonHeapMemory() {

        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memory.getNonHeapMemoryUsage();

        return conventMemoryInfo(memoryUsage);
    }


    private static JvmMemoryInfo conventMemoryInfo(MemoryUsage memoryUsage) {
        JvmMemoryInfo memoryInfo = new JvmMemoryInfo();
        memoryInfo.setInit(memoryUsage.getInit() / MB);
        memoryInfo.setUsed(memoryUsage.getUsed() / MB);
        memoryInfo.setCommitted(memoryUsage.getCommitted() / MB);
        memoryInfo.setMax(memoryUsage.getMax() / MB);
        return memoryInfo;
    }

    /**
     * 获得类加载情况
     *
     * @return
     */
    public static ClassLoadingInfo getClassLoadingInfo() {
        ClassLoadingInfo classLoadingInfo = new ClassLoadingInfo();
        ClassLoadingMXBean classLoad = ManagementFactory.getClassLoadingMXBean();

        classLoadingInfo.setTotalLoadedClassCount(classLoad.getTotalLoadedClassCount());
        classLoadingInfo.setLoadedClassCount(classLoad.getLoadedClassCount());
        classLoadingInfo.setUnloadedClassCount(classLoad.getUnloadedClassCount());

        return classLoadingInfo;
    }


    /**
     * 获取运行信息
     *
     * @return
     */
    public static RuntimeInfo getRuntimeInfo() {

        RuntimeInfo runtimeInfo = new RuntimeInfo();
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();

        runtimeInfo.setName(runtime.getName().split("@")[0]);
        runtimeInfo.setSpecName(runtime.getSpecName());
        runtimeInfo.setSpecVendor(runtime.getSpecVendor());
        runtimeInfo.setSpecVersion(runtime.getSpecVersion());
        runtimeInfo.setStartTime(DateUtils.parseDateStr(DateUtils.DateFormat.YYYY_MM_DD_HH_MM_SS,new Date(runtime.getStartTime())));
        runtimeInfo.setSystemProperties(runtime.getSystemProperties());
        runtimeInfo.setUptime(DateUtils.getDatePoor(runtime.getUptime()));
        runtimeInfo.setVmName(runtime.getVmName());
        runtimeInfo.setVmVendor(runtime.getVmVendor());
        runtimeInfo.setVmVersion(runtime.getVmVersion());
        runtimeInfo.setArgs(runtime.getInputArguments());
        runtimeInfo.setClassPath(runtime.getClassPath());
        runtimeInfo.setBootClassPath(runtime.getBootClassPath());
        runtimeInfo.setLibraryPath(runtime.getLibraryPath());

        return runtimeInfo;
    }


}
