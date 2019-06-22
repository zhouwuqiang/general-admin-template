package com.java.general.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.security.MessageDigest;

/**
 * Md5加密方法
 *
 * @author alger
 */
public class Md5Utils {

    private static final String CHARSET_NAME = "UTF-8";

    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);

    private static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(s.getBytes(CHARSET_NAME));
            return algorithm.digest();
        } catch (Exception e) {
            log.error("MD5 Error param:{}", s, e);
        }
        return null;
    }

    private static String toHex(byte[] hash) {

        Assert.notNull(hash, "object is required");

        StringBuilder stringBuilder = new StringBuilder(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                stringBuilder.append("0");
            }
            stringBuilder.append(Long.toString(hash[i] & 0xff, 16));
        }
        return stringBuilder.toString();
    }


    public static String hash(String s) {
        try {
            return new String(toHex(md5(s)).getBytes(CHARSET_NAME), CHARSET_NAME);
        } catch (Exception e) {
            log.error("not supported charset...{}", s, e);
            return s;
        }
    }
}
