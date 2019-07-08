package com.java.general.generator;

import com.java.general.generator.entity.TemplateColumn;
import com.java.general.generator.entity.TemplateDto;
import com.java.general.generator.entity.TemplateTable;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.CompilationUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/7/6 17:15
 */
public class TemplateFile extends GeneratedJavaFile {

    private static final String ENCODING = "UTF-8";


    private TemplateFormatter templateFormatter;

    /**
     * 配置变量信息
     */
    private Properties properties;

    /**
     * 替换参数信息
     */
    private Map<String, Object> paramMap;

    /**
     * 模板信息
     */
    private TemplateDto templateDto;


    public TemplateFile(TemplateDto templateDto, Set<TemplateTable> cacheTableSet,
                        Set<TemplateColumn> cacheColumnSet, Properties properties,
                        String targetProject, TemplateFormatter templateFormatter) {
        super(null, targetProject, ENCODING, null);

        this.templateFormatter = templateFormatter;
        this.templateDto = templateDto;
        this.properties = properties;

        this.paramMap = new HashMap<>();
        for (Object o : properties.keySet()) {
            paramMap.put(String.valueOf(o), properties.get(o));
        }
        paramMap.put("tableSet", cacheTableSet);
        paramMap.put("columnSet", cacheColumnSet);
    }

    @Override
    public CompilationUnit getCompilationUnit() {
        return null;
    }

    @Override
    public String getFileName() {
        return templateFormatter.getFileName(templateDto, properties);
    }

    @Override
    public String getFormattedContent() {
        return templateFormatter.getContext(templateDto, paramMap);
    }

    @Override
    public String getTargetPackage() {
        return templateFormatter.getTargetPackage(templateDto, properties);
    }

    @Override
    public boolean isMergeable() {
        return false;
    }

}
