package com.java.general.generator;


import com.java.general.exception.BusinessException;
import com.java.general.generator.constant.TemplateConstant;
import com.java.general.generator.entity.TemplateDto;
import com.java.general.generator.utils.FileUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/7/6 17:36
 */
public class TemplateFormatter {


    public String getContext(TemplateDto templateDto, Map<String, Object> params) {
        try {
            return FileUtil.generateContext(templateDto.getTemplatePath(), params);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFileName(TemplateDto templateDto, Properties properties) {

        String module = properties.getProperty("module");
        module = module.substring(0, 1).toUpperCase() + module.substring(1);

        switch (templateDto.getTemplateType()) {
            case TemplateConstant.TemplateType.VIEW_MANAGER:
                return "manager.html";
            case TemplateConstant.TemplateType.VIEW_MANAGER_JS:
                return "manager.js";
            case TemplateConstant.TemplateType.CONTROLLER:
                return module + "Controller.java";
            case TemplateConstant.TemplateType.FACADE:
                return module + "Facade.java";
            case TemplateConstant.TemplateType.FACADE_IMPL:
                return module + "FacadeImpl.java";
            case TemplateConstant.TemplateType.SERVICE:
                return module + "Service.java";
            case TemplateConstant.TemplateType.SERVICE_IMPL:
                return module + "ServiceImpl.java";
            case TemplateConstant.TemplateType.SAVE_REQUEST_DTO:
                return module + "SaveRequestDto.java";
            case TemplateConstant.TemplateType.TABLE_REQUEST_DTO:
                return module + "TableRequestDto.java";
            default:
                throw new BusinessException("模板类型不正确!");
        }
    }

    public String getTargetPackage(TemplateDto templateDto, Properties properties) {

        String module = properties.getProperty("module");

        switch (templateDto.getTemplateType()) {
            case TemplateConstant.TemplateType.VIEW_MANAGER:
                return "templates." + module;
            case TemplateConstant.TemplateType.VIEW_MANAGER_JS:
                return "static.js." + module;
            case TemplateConstant.TemplateType.CONTROLLER:
                return "com.java.business." + module + ".controller";
            case TemplateConstant.TemplateType.FACADE:
                return "com.java.business." + module + ".facade";
            case TemplateConstant.TemplateType.FACADE_IMPL:
                return "com.java.business." + module + ".facade.impl";
            case TemplateConstant.TemplateType.SERVICE:
                return "com.java.business." + module + ".service";
            case TemplateConstant.TemplateType.SERVICE_IMPL:
                return "com.java.business." + module + ".service.impl";
            case TemplateConstant.TemplateType.SAVE_REQUEST_DTO:
                return  "com.java.business." + module + ".dto";
            case TemplateConstant.TemplateType.TABLE_REQUEST_DTO:
                return  "com.java.business." + module + ".dto";
            default:
                throw new BusinessException("模板类型不正确!");
        }
    }
}
