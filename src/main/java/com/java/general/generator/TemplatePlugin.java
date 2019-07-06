package com.java.general.generator;

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
     * 作者
     */
    private String author;

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
     * 模板地址
     */
    private List<String> templatePath = new ArrayList<>();


    @Override
    public void setProperties(Properties properties) {
        this.properties.putAll(properties);
        this.author = properties.getProperty("author");
        this.model = properties.getProperty("model");
        this.targetPackage = properties.getProperty("targetPackage");
        this.targetProject = properties.getProperty("targetProject");



        templatePath.add("controller/Controller.ftl");
        templatePath.add("dto/RequestDto.ftl");
    }


    @Override
    public boolean validate(List<String> warnings) {
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
        list.add(new TemplateFile(templatePath.get(1), "demo", properties, params, new TemplateFormatter()));
        return list;
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        return null;
    }
}
