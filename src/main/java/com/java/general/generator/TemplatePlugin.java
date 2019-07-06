package com.java.general.generator;

import com.java.general.generator.entity.TemplateTable;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.*;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/25 11:57
 */
public class TemplatePlugin extends PluginAdapter {

    /**
     * 模块名称
     */
    private String model;

    /**
     * 目标目录根目录
     */
    private String targetPackage;

    /**
     * 项目根目录
     */
    private String targetProject;

    /**
     * 表格
     */
    private Set<TemplateTable> cacheTableSet;



    @Override
    public void setProperties(Properties properties) {
        this.properties.putAll(properties);
        this.model = properties.getProperty("model");
        this.targetPackage = properties.getProperty("targetPackage");
        this.targetProject = properties.getProperty("targetProject");
    }


    @Override
    public boolean validate(List<String> warnings) {

        if (StringUtils.isBlank(model)) {
            warnings.add("model 未配置,不会生成任何文件!");
            return false;
        }

        if (StringUtils.isBlank(targetPackage)) {
            warnings.add("targetPackage 未配置,不会生成任何文件!");
            return false;
        }

        if (StringUtils.isBlank(targetProject)) {
            warnings.add("targetProject 未配置,不会生成任何文件!");
            return false;
        }
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> list = new ArrayList<>();
        Map params = new HashMap();

        //service
        list.add(new TemplateFile("/dto/service.ftl", properties, params, new TemplateFormatter()));


        //serviceImpl
        list.add(new TemplateFile("/dto/service.ftl", properties, params, new TemplateFormatter()));
        return list;
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        return null;
    }
}
