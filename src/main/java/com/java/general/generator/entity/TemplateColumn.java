package com.java.general.generator.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/7/6 18:16
 */
@Getter
@Setter
public class TemplateColumn {

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * 字段类型包
     */
    private String columnTypePackage;

    /**
     * 字段注释
     */
    private String remarks;
}
