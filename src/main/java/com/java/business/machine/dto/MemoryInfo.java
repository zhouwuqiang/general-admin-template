package com.java.business.machine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
public class MemoryInfo {

    /**
     * 堆内存
     */
   private JvmMemoryInfo jvmHeapMemory;

    /**
     * 非堆内存
     */
   private JvmMemoryInfo jvmNonHeapMemory;

}
