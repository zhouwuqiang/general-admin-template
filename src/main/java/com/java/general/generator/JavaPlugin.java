package com.java.general.generator;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;
import java.util.Properties;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/25 11:57
 */
public class JavaPlugin extends PluginAdapter {

    private String targetPackage;

    private String targetProject;

    private String author = "";

    private String date = "";

    private String version = "";


    @Override
    public void setProperties(Properties properties) {
        targetPackage = properties.getProperty("targetPackage");
        targetProject = properties.getProperty("targetProject");
        author = properties.getProperty("author");
        date = properties.getProperty("date");
        version = properties.getProperty("version");
        this.properties.putAll(properties);
    }


    @Override
    public boolean validate(List<String> warnings) {
        return !(StringUtils.isBlank(targetPackage) || StringUtils.isBlank(targetProject));
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        System.out.println(JSON.toJSONString(allColumns));
    }


}
