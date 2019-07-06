package com.java.general.generator;



import com.java.general.generator.utils.FileUtil;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/7/6 17:36
 */
public class TemplateFormatter {



    public String getContext(String tempSource, Map<String, Object> params) {
        try {
            return FileUtil.generateContext(tempSource,params);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
