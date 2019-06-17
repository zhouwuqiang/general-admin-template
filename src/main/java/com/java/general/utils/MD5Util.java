package com.java.general.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * @Auther
 */
public class MD5Util {


    private static final Logger log = LoggerFactory.getLogger(MD5Util.class);


    /**
     * MD5 32位加密
     *
     * @param sourceStr
     * @return
     */
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            log.error("提交请求接口异常，异常信息：{},  sourceStr is :{}", e.getMessage(), sourceStr);
        } catch (UnsupportedEncodingException e) {
            log.error("提交请求接口异常，异常信息：{},  sourceStr is :{}", e.getMessage(), sourceStr);
        }
        return result;
    }
}
