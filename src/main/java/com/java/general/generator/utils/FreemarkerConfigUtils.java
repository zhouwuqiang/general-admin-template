package com.java.general.generator.utils;

import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/25 11:57
 */
public class FreemarkerConfigUtils {

    private static String BASE_PACKAGE_PATH = "generator/formwork";

    private static String path = new File(FreemarkerConfigUtils.class.getClassLoader().getResource(BASE_PACKAGE_PATH).getFile()).getPath();

    private static Configuration configuration;

    public static synchronized Configuration getInstance() {
        if (null == configuration) {
            configuration = new Configuration(Configuration.VERSION_2_3_23);
            try {
                if (path.contains("jar")) {
                    configuration.setClassForTemplateLoading(FreemarkerConfigUtils.class, BASE_PACKAGE_PATH);
                } else {
                    configuration.setDirectoryForTemplateLoading(new File(path));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            configuration.setEncoding(Locale.CHINA, "utf-8");
        }
        return configuration;
    }

}
