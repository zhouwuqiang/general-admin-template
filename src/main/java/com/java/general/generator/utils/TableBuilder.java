package com.java.general.generator.utils;

import com.java.general.generator.entity.TemplateColumn;
import com.java.general.generator.entity.TemplateTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/7/8 9:35
 */
public class TableBuilder {


    /**
     * 构建基础表格
     *
     * @param introspectedTable
     * @return
     */
    public static TemplateTable buildTable(IntrospectedTable introspectedTable) {
        TemplateTable templateTable = new TemplateTable();
        templateTable.setTableName(introspectedTable.getFullyQualifiedTable().getIntrospectedTableName());

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        templateTable.setVariableName(Introspector.decapitalize(type.getShortName()));
        templateTable.setClassName(type.getShortName());

        return templateTable;
    }

    public static List<TemplateColumn> buildColumn(IntrospectedTable introspectedTable) {
        List<TemplateColumn> result = new ArrayList<>();

        List<IntrospectedColumn> allColumn = introspectedTable.getAllColumns();

        for (IntrospectedColumn item : allColumn) {
            TemplateColumn column = new TemplateColumn();
            column.setColumnName(item.getJavaProperty());
            column.setRemarks(item.getRemarks());
            FullyQualifiedJavaType type = item.getFullyQualifiedJavaType();
            column.setColumnType(type.getShortName());
            column.setColumnTypePackage(type.getFullyQualifiedName());
            result.add(column);
        }

        return result;
    }

}
