package com.java.general.generator.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/7/6 18:16
 */
@Getter
@Setter
public class TemplateDto {

    /**
     * 模板文件地址
     */
    private String templatePath;

    /**
     * 模板类型
     */
    private String templateType;


    public TemplateDto() {
    }

    public TemplateDto(String templatePath, String templateType) {
        this.templatePath = templatePath;
        this.templateType = templateType;
    }
}
