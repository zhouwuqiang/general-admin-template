package com.java.general.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * description : 随机数工具类
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/6/22 10:54
 */
public class RandomHelper {
    /**
     * RANDOM 基数
     */
    private final static int RANDOM_BASE = 10;

    /**
     * 密码允许字符
     */
    private static String[] charArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "g", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "G", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};


    /**
     * 普通随机数
     */
    private static Random random = new Random();

    /**
     * 随机数种子
     */
    private static final String seed = "sdf";

    /**
     * 安全的随机数,添加系统运行时候的噪音.
     */
    private static SecureRandom secureRandom = new SecureRandom(seed.getBytes());

    /**
     * 产生指定长度的数字值随机数
     *
     * @param length 需要产生的长度
     * @return
     */
    public static String getRandomIntStr(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(RANDOM_BASE));
        }
        return stringBuilder.toString();
    }


    /**
     * 产生指定长度的字符随机数
     *
     * @param length 需要产生的长度
     * @return
     */
    public static String getRandomStr(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(charArray[secureRandom.nextInt(charArray.length)]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(secureRandom.nextInt());
        }

        System.out.println(RandomHelper.getRandomIntStr(5));
        System.out.println(RandomHelper.getRandomStr(10));

    }
}
