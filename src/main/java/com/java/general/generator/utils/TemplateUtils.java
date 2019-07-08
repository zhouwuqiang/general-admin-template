package com.java.general.generator.utils;

import com.java.general.generator.constant.TemplateConstant;
import com.java.general.generator.entity.TemplateDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/7/6 18:30
 */
public class TemplateUtils {

    /**
     * 模板缓存
     */
    private static final Map<String, TemplateDto> TEMPLATE_CACHE = new ConcurrentHashMap<>();


    static {
        TEMPLATE_CACHE.put(TemplateConstant.TemplateType.VIEW_MANAGER, new TemplateDto(TemplateConstant.TemplateType.VIEW_MANAGER, "/view/manager.ftl"));
        TEMPLATE_CACHE.put(TemplateConstant.TemplateType.VIEW_MANAGER_JS, new TemplateDto(TemplateConstant.TemplateType.VIEW_MANAGER_JS, "/js/manager.ftl"));
        TEMPLATE_CACHE.put(TemplateConstant.TemplateType.CONTROLLER, new TemplateDto(TemplateConstant.TemplateType.CONTROLLER, "/controller/Controller.ftl"));
        TEMPLATE_CACHE.put(TemplateConstant.TemplateType.FACADE, new TemplateDto(TemplateConstant.TemplateType.FACADE, "/facade/Facade.ftl"));
        TEMPLATE_CACHE.put(TemplateConstant.TemplateType.FACADE_IMPL, new TemplateDto(TemplateConstant.TemplateType.FACADE_IMPL, "/facade/FacadeImpl.ftl"));
        TEMPLATE_CACHE.put(TemplateConstant.TemplateType.SERVICE, new TemplateDto(TemplateConstant.TemplateType.SERVICE, "/service/Service.ftl"));
        TEMPLATE_CACHE.put(TemplateConstant.TemplateType.SERVICE_IMPL, new TemplateDto(TemplateConstant.TemplateType.SERVICE_IMPL, "/service/ServiceImpl.ftl"));
        TEMPLATE_CACHE.put(TemplateConstant.TemplateType.SAVE_REQUEST_DTO, new TemplateDto(TemplateConstant.TemplateType.SAVE_REQUEST_DTO, "/dto/Save.ftl"));
        TEMPLATE_CACHE.put(TemplateConstant.TemplateType.TABLE_REQUEST_DTO, new TemplateDto(TemplateConstant.TemplateType.TABLE_REQUEST_DTO, "/dto/TableRequestDto.ftl"));
    }


    public static TemplateDto getTemplate(String template) {
        return TEMPLATE_CACHE.get(template);
    }

    public static List<TemplateDto> getTemplateList() {
        return new ArrayList<>(TEMPLATE_CACHE.values());
    }
}
