package com.java.business.machine.utils;


import com.java.business.machine.dto.ClassLoadingInfo;
import com.java.business.machine.dto.JvmMemoryInfo;
import com.java.business.machine.dto.MachineInfo;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;


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

        while (true){
            Thread.sleep(1000);
            System.out.println(MachineUtils.getMachineInfo());
//            Thread.sleep(1000);
//            System.out.println(MachineUtils.getJvmHeapMemory());
//            System.out.println(MachineUtils.getJvmNoHeapMemory());
//            Thread.sleep(1000);
//            System.out.println(MachineUtils.getClassLoadingInfo());
        }

    }



    /**
     * 获取系统信息
     *
     * @return
     */
    public static MachineInfo getMachineInfo() {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return new MachineInfo(operatingSystemMXBean);
    }

    /**
     * 获取jvm 堆内存情况使用
     *
     * @return
     */
    public static JvmMemoryInfo getJvmHeapMemory() {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        return new JvmMemoryInfo(memory.getHeapMemoryUsage());
    }

    /**
     *获取jvm 非堆内存情况使用
     *
     * @return
     */
    public static JvmMemoryInfo getJvmNoHeapMemory() {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        return new JvmMemoryInfo(memory.getNonHeapMemoryUsage());
    }

    /**
     * 获得类加载情况
     * @return
     */
    public static ClassLoadingInfo getClassLoadingInfo() {
        ClassLoadingMXBean classLoad = ManagementFactory.getClassLoadingMXBean();;
        return new ClassLoadingInfo(classLoad);
    }
}
