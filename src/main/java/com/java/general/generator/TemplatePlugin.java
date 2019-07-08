package com.java.general.generator;

import com.java.general.exception.BusinessException;
import com.java.general.generator.constant.TemplateConstant;
import com.java.general.generator.entity.TemplateColumn;
import com.java.general.generator.entity.TemplateDto;
import com.java.general.generator.entity.TemplateTable;
import com.java.general.generator.utils.TableBuilder;
import com.java.general.generator.utils.TemplateUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
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

    /**
     * 表格
     */
    private Set<TemplateColumn> cacheColumnSet;

    @Override
    public void setProperties(Properties properties) {
        this.properties.putAll(properties);
        this.model = properties.getProperty("model");
        this.targetPackage = properties.getProperty("targetPackage");
        this.targetProject = properties.getProperty("targetProject");
        cacheTableSet = new HashSet<>();
        cacheColumnSet = new HashSet<>();
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
        cacheTableSet.add(TableBuilder.buildTable(introspectedTable));
        cacheColumnSet.addAll(TableBuilder.buildColumn(introspectedTable));
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> list = new ArrayList<>();

        return list;
    }


    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        List<GeneratedJavaFile> list = new ArrayList<>();
        List<TemplateDto> templateDtoList = TemplateUtils.getTemplateList();
        for (TemplateDto templateDto : templateDtoList) {
            list.add(new TemplateFile(templateDto, cacheTableSet, cacheColumnSet, properties, judgeTargetProject(templateDto), new TemplateFormatter()));
        }
        return list;
    }


    private String judgeTargetProject(TemplateDto templateDto) {
        switch (templateDto.getTemplateType()) {
            case TemplateConstant.TemplateType.VIEW_MANAGER:
                return "src/main/webapp";
            case TemplateConstant.TemplateType.VIEW_MANAGER_JS:
                return "src/main/webapp";
            case TemplateConstant.TemplateType.CONTROLLER:
                return "src/main/java";
            case TemplateConstant.TemplateType.FACADE:
                return "src/main/java";
            case TemplateConstant.TemplateType.FACADE_IMPL:
                return "src/main/java";
            case TemplateConstant.TemplateType.SERVICE:
                return "src/main/java";
            case TemplateConstant.TemplateType.SERVICE_IMPL:
                return "src/main/java";
            case TemplateConstant.TemplateType.SAVE_REQUEST_DTO:
                return "src/main/java";
            case TemplateConstant.TemplateType.TABLE_REQUEST_DTO:
                return "src/main/java";
            default:
                throw new BusinessException("模板类型不正确!");
        }
    }
}
