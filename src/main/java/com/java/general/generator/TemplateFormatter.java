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

        String model = properties.getProperty("model");
        model = model.substring(0, 1).toUpperCase() + model.substring(1);

        switch (templateDto.getTemplateType()) {
            case TemplateConstant.TemplateType.VIEW_MANAGER:
                return "manager.html";
            case TemplateConstant.TemplateType.VIEW_MANAGER_JS:
                return "manager.js";
            case TemplateConstant.TemplateType.CONTROLLER:
                return model + "Controller.java";
            case TemplateConstant.TemplateType.FACADE:
                return model + "Facade.java";
            case TemplateConstant.TemplateType.FACADE_IMPL:
                return model + "FacadeImpl.java";
            case TemplateConstant.TemplateType.SERVICE:
                return model + "Service.java";
            case TemplateConstant.TemplateType.SERVICE_IMPL:
                return model + "ServiceImpl.java";
            case TemplateConstant.TemplateType.SAVE_REQUEST_DTO:
                return model + "SaveRequestDto.java";
            case TemplateConstant.TemplateType.TABLE_REQUEST_DTO:
                return model + "TableRequestDto.java";
            default:
                throw new BusinessException("模板类型不正确!");
        }
    }

    public String getTargetPackage(TemplateDto templateDto, Properties properties) {

        String model = properties.getProperty("model");

        switch (templateDto.getTemplateType()) {
            case TemplateConstant.TemplateType.VIEW_MANAGER:
                return "templates." + model;
            case TemplateConstant.TemplateType.VIEW_MANAGER_JS:
                return "static.js." + model;
            case TemplateConstant.TemplateType.CONTROLLER:
                return "com.java.business." + model + ".controller";
            case TemplateConstant.TemplateType.FACADE:
                return "com.java.business." + model + ".facade";
            case TemplateConstant.TemplateType.FACADE_IMPL:
                return "com.java.business." + model + ".facade.impl";
            case TemplateConstant.TemplateType.SERVICE:
                return "com.java.business." + model + ".service";
            case TemplateConstant.TemplateType.SERVICE_IMPL:
                return "com.java.business." + model + ".service.impl";
            case TemplateConstant.TemplateType.SAVE_REQUEST_DTO:
                return  "com.java.business." + model + ".dto";
            case TemplateConstant.TemplateType.TABLE_REQUEST_DTO:
                return  "com.java.business." + model + ".dto";
            default:
                throw new BusinessException("模板类型不正确!");
        }
    }
}
