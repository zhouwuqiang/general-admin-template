package com.java.general.generator.utils;

import com.alibaba.fastjson.JSONObject;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;


/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/25 11:57
 */
public class FileUtil {

    private static final String CHARSET_NAME = "UTF-8";

    /**
     * @param name     模板名称
     * @param data     填充数据
     * @param filePath 输出文件
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateToJava(String name, Object data, String filePath) throws IOException, TemplateException {

        File file = new File(filePath);
        if (file.exists()) {
            System.err.println("ERROR: " + file.getPath().substring(file.getPath().lastIndexOf("\\") + 1, file.getPath().length()) + " 已存在，请手动修改");
            return;
        }

        //获取模板
        Template template = getTemplate(name);

        // 填充数据
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        writer.flush();

        // 写入文件
        FileOutputStream fos = new FileOutputStream(filePath);
        OutputStreamWriter osw = new OutputStreamWriter(fos, CHARSET_NAME);
        BufferedWriter bw = new BufferedWriter(osw, 1024);
        template.process(data, bw);
        fos.close();
    }

    /**
     * 获取模板
     *
     * @param name 模板名称
     * @return
     * @throws IOException
     */
    private static Template getTemplate(String name) throws IOException {
        return FreemarkerConfigUtils.getInstance().getTemplate(name);
    }


    /**
     * 获取项目目录
     * @return "E:\general-admin-template\src\main\"
     */
    private static String getBasicProjectPath() {
        String path = new File(FileUtil.class.getClassLoader().getResource("").getFile()).getPath() + File.separator;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(path.substring(0, path.indexOf("target"))).append("src").append(File.separator).append("main").append(File.separator);
        return stringBuilder.toString();
    }

    /**
     * 获取源码路径
     *
     * @return
     */
    public static String getSourcePath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getBasicProjectPath()).append("java").append(File.separator);
        return stringBuilder.toString();
    }

    /**
     * 获取资源文件路径
     *
     * @return
     */
    public static String getResourcePath() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getBasicProjectPath()).append("resources").append(File.separator);
        return stringBuilder.toString();
    }

}
