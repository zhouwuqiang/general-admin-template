package com.java.general.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * HTTP请求工具
 * @author Edison
 * @date 2019年3月15日 上午10:46:13
 */
public class HttpRequestUtil {

    private HttpRequestUtil() {
        super();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestUtil.class);

    /**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String params) {
        return sendGet(url, null, params);
    }

    /**
     * 向指定URL发送GET方法的请求,允许设置请求参数及参数
     */
    public static String sendGet(String url, Map<String, String> requestProperties, Map<String, String> params) {
        return sendGet(url, requestProperties, genParamstrByMap(params));
    }

    /**
     * 向指定URL发送GET方法的请求,允许设置请求参数及参数
     */
    public static String sendGet(String url, Map<String, String> requestProperties, String params) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            if (StringUtils.isNotBlank(params)) {
                urlNameString = urlNameString + "?" + params;
            }
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("userpumpwater-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 设置requestProperties
            if (MapUtils.isNotEmpty(requestProperties)) {
                Set<String> keys = requestProperties.keySet();
                for (Iterator<String> it = keys.iterator(); it.hasNext();) {
                    String key = it.next();
                    String property = MapUtils.getString(requestProperties, key);
                    connection.setRequestProperty(key, property);
                }
            }

            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            LOGGER.error("发送GET请求出现异常！原因:" + e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                LOGGER.error("关闭输入流异常:" + e.getMessage());
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param params 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String params) {
        return sendPost(url, null, params);
    }

    /**
     * 向指定 URL 发送POST方法的请求,允许设置请求参数及参数
     */
    public static String sendPost(String url, Map<String, String> requestProperties, Map<String, String> params) {
        return sendPost(url, requestProperties, genParamstrByMap(params));
    }

    /**
     * 向指定 URL 发送POST方法的请求
     */
    public static String sendPost(String url, Map<String, String> requestProperties, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("userpumpwater-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded;charset=UTF-8");
            if (MapUtils.isNotEmpty(requestProperties)) {
                Set<String> keys = requestProperties.keySet();
                for (Iterator<String> it = keys.iterator(); it.hasNext();) {
                    String key = it.next();
                    String property = MapUtils.getString(requestProperties, key);
                    conn.setRequestProperty(key, property);
                }
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new PrintWriter(conn.getOutputStream());
            out.print(params);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            LOGGER.error("发送 POST 请求出现异常！原因:" + e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                LOGGER.error("关闭流异常,原因:", e.getMessage());
            }
        }
        return result;
    }

    /**
     * 将map格式化成参数字符串
     */
    public static String genParamstrByMap(Map<String, String> params) {
        StringBuilder paramstr = new StringBuilder();
        Set<String> keys = params.keySet();
        Iterator<String> itKeys = keys.iterator();
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            if (paramstr.length() > 0) {
                paramstr.append("&");
            }
            paramstr.append(key + "=" + params.get(key));
        }
        return paramstr.toString();
    }

    /**
     * 将json格式化成参数字符串
     */
    public static String genParamstrByJSON(JSONObject params) {
        StringBuilder paramstr = new StringBuilder();
        Set<String> keys = params.keySet();
        Iterator<String> itKeys = keys.iterator();
        while (itKeys.hasNext()) {
            String key = itKeys.next();
            if (paramstr.length() > 0) {
                paramstr.append("&");
            }
            paramstr.append(key + "=" + params.get(key));
        }
        return paramstr.toString();
    }

}