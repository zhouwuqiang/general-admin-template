package com.java.business.machine.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/10 1:44
 */
@Getter
@Setter
@ToString
public class ClassLoadingInfo {

    /**
     * 已加载类总数
     */
    private long totalLoadedClassCount;

    /**
     * 已加载当前类
     */
    private long loadedClassCount;

    /**
     * 已卸载类总数
     */
    private long unloadedClassCount;

}
