package com.java.general.generator;

import com.alibaba.fastjson.JSON;
import com.java.general.generator.entity.GeneratorConfiguration;
import org.mybatis.generator.api.GeneratedJavaFile;
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

    private GeneratorConfiguration configuration;

    @Override
    public void setProperties(Properties properties) {
        this.properties.putAll(properties);
    }


    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        System.out.println(JSON.toJSONString(allColumns));
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
        return null;
    }
}
