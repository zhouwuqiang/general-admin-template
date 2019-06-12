package com.java.business.machine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.management.MemoryUsage;

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
public class JvmMemoryInfo {

    /**
     * 初始(M)
     */
    private long init;

    /**
     * 当前(已使用)(M)
     */
    private long used;

    /**
     * 提交的内存(已申请)(M)
     */
    private long committed;

    /**
     * 最大(上限)(M)
     */
    private long max;

}
