package com.java.general.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.CompilationUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/7/6 17:15
 */
public class TemplateFile extends GeneratedJavaFile {

    private static final String ENCODING = "UTF-8";

    private String targetPackage;

    private TemplateFormatter templateFormatter;

    /**
     * 系统产生
     */
    private Properties properties;

    /**
     * 模板地址
     */
    private String tempSource;
    /**
     * 模板地址
     */
    private String targetFilename;
    /**
     * 模板参数
     */
    private Map<String, Object> params;

    public TemplateFile(String tempSource,String targetFilename,
                        Properties properties, Map<String, Object> params,
                        TemplateFormatter templateFormatter) {

        super(null, properties.getProperty("targetProject"), ENCODING, null);
        this.targetPackage = properties.getProperty("targetPackage");
        this.templateFormatter = templateFormatter;
        this.targetFilename = targetFilename;
        this.tempSource = tempSource;
        if (params == null) {
            params = new HashMap<>();
        }

        for (Object o : properties.keySet()) {
            params.put(String.valueOf(o), properties.get(o));
        }
        this.params = params;
    }

    @Override
    public CompilationUnit getCompilationUnit() {
        return null;
    }

    @Override
    public String getFileName() {
        return this.targetFilename;
    }

    @Override
    public String getFormattedContent() {
        return templateFormatter.getContext(tempSource, params);
    }

    @Override
    public String getTargetPackage() {
        return targetPackage;
    }

    @Override
    public boolean isMergeable() {
        return false;
    }

}
