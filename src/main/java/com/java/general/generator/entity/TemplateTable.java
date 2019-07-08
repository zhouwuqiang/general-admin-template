package com.java.general.generator.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/7/6 18:16
 */
@Getter
@Setter
public class TemplateTable {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 类名
     */
    private String className;

    /**
     * 变量名称
     */
    private String variableName;

}
