package com.java.general.generator.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/25 15:31
 */
@Getter
@Setter
public class GeneratorConfiguration {

    /**
     * 作者
     */
    private String author;

    /**
     * 日期
     */
    private String date;

    /**
     * 版本
     */
    private String version;

    /**
     * 目标目录根目录
     */
    private String targetPackage;

    /**
     * 项目根目录
     */
    private String targetProject;

}
