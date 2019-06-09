package com.java.business.machine;

import com.sun.management.OperatingSystemMXBean;

import java.io.File;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.*;
import java.util.ArrayList;
import java.util.List;


/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/9 23:54
 */
public class Machine {

    private static final int CPUTIME = 500;

    private static final int PERCENT = 100;

    private static final int FAULTLENGTH = 10;

    public static void main(String[] args)  {

        System.out.println(getMemory());
    }

    public static void getJVMInfo(){

        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memorymbean.getHeapMemoryUsage();
        System.out.println("INIT HEAP: " + usage.getInit());
        System.out.println("MAX HEAP: " + usage.getMax());
        System.out.println("USE HEAP: " + usage.getUsed());
        System.out.println("\nFull Information:");
        System.out.println("Heap Memory Usage: "
                + memorymbean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: "
                + memorymbean.getNonHeapMemoryUsage());

        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("===================java options=============== ");
        System.out.println(inputArguments);



        System.out.println("=======================通过java来获取相关系统状态============================ ");
        int i = (int)Runtime.getRuntime().totalMemory()/1024;//Java 虚拟机中的内存总量,以字节为单位
        System.out.println("总的内存量 i is "+i);
        int j = (int)Runtime.getRuntime().freeMemory()/1024;//Java 虚拟机中的空闲内存量
        System.out.println("空闲内存量 j is "+j);
        System.out.println("最大内存量 is "+Runtime.getRuntime().maxMemory()/1024);

        System.out.println("=======================OperatingSystemMXBean============================ ");
        OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//    System.out.println(osm.getFreeSwapSpaceSize()/1024);
//    System.out.println(osm.getFreePhysicalMemorySize()/1024);
//    System.out.println(osm.getTotalPhysicalMemorySize()/1024);

        //获取操作系统相关信息
        System.out.println("osm.getArch() "+osm.getArch());
        System.out.println("osm.getAvailableProcessors() "+osm.getAvailableProcessors());
        //System.out.println("osm.getCommittedVirtualMemorySize() "+osm.getCommittedVirtualMemorySize());
        System.out.println("osm.getName() "+osm.getName());
        //System.out.println("osm.getProcessCpuTime() "+osm.getProcessCpuTime());
        System.out.println("osm.getVersion() "+osm.getVersion());
        //获取整个虚拟机内存使用情况
        System.out.println("=======================MemoryMXBean============================ ");
        MemoryMXBean mm=(MemoryMXBean)ManagementFactory.getMemoryMXBean();
        System.out.println("getHeapMemoryUsage "+mm.getHeapMemoryUsage());
        System.out.println("getNonHeapMemoryUsage "+mm.getNonHeapMemoryUsage());
        //获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
        System.out.println("=======================ThreadMXBean============================ ");
        ThreadMXBean tm=(ThreadMXBean)ManagementFactory.getThreadMXBean();
        System.out.println("getThreadCount "+tm.getThreadCount());
        System.out.println("getPeakThreadCount "+tm.getPeakThreadCount());
        System.out.println("getCurrentThreadCpuTime "+tm.getCurrentThreadCpuTime());
        System.out.println("getDaemonThreadCount "+tm.getDaemonThreadCount());
        System.out.println("getCurrentThreadUserTime "+tm.getCurrentThreadUserTime());

        //当前编译器情况
        System.out.println("=======================CompilationMXBean============================ ");
        CompilationMXBean gm=(CompilationMXBean)ManagementFactory.getCompilationMXBean();
        System.out.println("getName "+gm.getName());
        System.out.println("getTotalCompilationTime "+gm.getTotalCompilationTime());

        //获取多个内存池的使用情况
        System.out.println("=======================MemoryPoolMXBean============================ ");
        List<MemoryPoolMXBean> mpmList=ManagementFactory.getMemoryPoolMXBeans();
        for(MemoryPoolMXBean mpm:mpmList){
            System.out.println("getUsage "+mpm.getUsage());
            System.out.println("getMemoryManagerNames "+mpm.getMemoryManagerNames().toString());
        }
        //获取GC的次数以及花费时间之类的信息
        System.out.println("=======================MemoryPoolMXBean============================ ");
        List<GarbageCollectorMXBean> gcmList=ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean gcm:gcmList){
            System.out.println("getName "+gcm.getName());
            System.out.println("getMemoryPoolNames "+gcm.getMemoryPoolNames());
        }
        //获取运行时信息
        System.out.println("=======================RuntimeMXBean============================ ");
        RuntimeMXBean rmb=(RuntimeMXBean)ManagementFactory.getRuntimeMXBean();
        System.out.println("getClassPath "+rmb.getClassPath());
        System.out.println("getLibraryPath "+rmb.getLibraryPath());
        System.out.println("getVmVersion "+rmb.getVmVersion());

    }

    /**
     * 获取内存使用率
     * @return
     */
    public static String getMemory() {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 总的物理内存+虚拟内存
        long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();
        // 剩余的物理内存
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        // 总物理内存
        long totalPhysicalMemory = osmxb.getTotalPhysicalMemorySize();
        Double compare = (Double) (1 - freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;
        String str = "内存已使用:" + compare.intValue() + "%";
        return str;
    }


    /**
     * 获取文件系统使用率
     * @return
     */
    public static List<String> getDisk() {
        // 操作系统
        List<String> list = new ArrayList<String>();
        for (char c = 'A'; c <= 'Z'; c++) {
            String dirName = c + ":/";
            File win = new File(dirName);
            if (win.exists()) {
                long total = (long) win.getTotalSpace();
                long free = (long) win.getFreeSpace();
                Double compare = (Double) (1 - free * 1.0 / total) * 100;
                String str = c + ":盘  已使用 " + compare.intValue() + "%";
                list.add(str);
            }
        }
        return list;
    }


    /**
     * 获得cpu使用率
     * @return
     */
    public static String getCpuRatioForWindows() {
        try {
            String procCmd =
                    System.getenv("windir")
                            + "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
            // 取进程信息
            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
            Thread.sleep(CPUTIME);
            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
            if (c0 != null && c1 != null) {
                long idletime = c1[0] - c0[0];
                long busytime = c1[1] - c0[1];
                return "CPU使用率:" + Double.valueOf(PERCENT * (busytime) * 1.0 / (busytime + idletime)).intValue() + "%";
            } else {
                return "CPU使用率:" + 0 + "%";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "CPU使用率:" + 0 + "%";
        }
    }

    /**
     * 读取cpu相关信息
     * @param proc
     * @return
     */
    private static long[] readCpu(final Process proc) {
        long[] retn = new long[2];
        try {
            proc.getOutputStream().close();
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = input.readLine();
            if (line == null || line.length() < FAULTLENGTH) {
                return null;
            }
            int capidx = line.indexOf("Caption");
            int cmdidx = line.indexOf("CommandLine");
            int rocidx = line.indexOf("ReadOperationCount");
            int umtidx = line.indexOf("UserModeTime");
            int kmtidx = line.indexOf("KernelModeTime");
            int wocidx = line.indexOf("WriteOperationCount");
            long idletime = 0;
            long kneltime = 0;
            long usertime = 0;
            while ((line = input.readLine()) != null) {
                if (line.length() < wocidx) {
                    continue;
                }
                // 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
                // ThreadCount,UserModeTime,WriteOperation
                String caption = substring(line, capidx, cmdidx - 1).trim();
                String cmd = substring(line, cmdidx, kmtidx - 1).trim();
                if (cmd.indexOf("wmic.exe") >= 0) {
                    continue;
                }
                String s1 = substring(line, kmtidx, rocidx - 1).trim();
                String s2 = substring(line, umtidx, wocidx - 1).trim();
                if (caption.equals("System Idle Process") || caption.equals("System")) {
                    if (s1.length() > 0) {
                        idletime += Long.valueOf(s1).longValue();
                    }

                    if (s2.length() > 0) {
                        idletime += Long.valueOf(s2).longValue();
                    }

                    continue;
                }
                if (s1.length() > 0){
                    kneltime += Long.valueOf(s1).longValue();
                }

                if (s2.length() > 0){
                    usertime += Long.valueOf(s2).longValue();
                }
            }
            retn[0] = idletime;
            retn[1] = kneltime + usertime;
            return retn;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                proc.getInputStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 由于String.subString对汉字处理存在问题（把一个汉字视为一个字节)，因此在 包含汉字的字符串时存在隐患，现调整如下：
     *
     * @param src       要截取的字符串
     * @param start_idx 开始坐标（包括该坐标)
     * @param end_idx   截止坐标（包括该坐标）
     * @return
     */
    private static String substring(String src, int start_idx, int end_idx) {
        byte[] b = src.getBytes();
        String tgt = "";
        for (int i = start_idx; i <= end_idx; i++) {
            tgt += (char) b[i];
        }
        return tgt;
    }


}
