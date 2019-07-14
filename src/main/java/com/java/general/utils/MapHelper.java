package com.java.general.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * description : Map工具类
 *
 * @author alger
 * @version 1.0.0
 * @date 2018/12/28 14:51
 */
public class MapHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapHelper.class);

    /**
     * 获得字串值
     *
     * @param name 键值名称
     * @return 若不存在，则返回空字串
     */
    public static String getString(Map<?, ?> map, String name) {
        if (checkParam(map, name)) {
            return "";
        }
        return map.get(name).toString();
    }

    /**
     * 返回整型值
     *
     * @param name 键值名称
     * @return 若不存在，则返回0
     */
    public static int getInt(Map<?, ?> map, String name) {
        if (checkParam(map, name)) {
            return 0;
        }
        return Integer.parseInt(map.get(name).toString());
    }

    /**
     * 获取长整型值
     *
     * @param name 键值名称
     * @return 若不存在, 则返回0
     */
    public static long getLong(Map<?, ?> map, String name) {
        if (checkParam(map, name)) {
            return 0;
        }
        return Long.parseLong(map.get(name).toString());
    }

    /**
     * 获取Float型值
     *
     * @param name 键值名称
     * @return 若不存在，或转换失败，则返回0
     */
    public static float getFloat(Map<?, ?> map, String name) {
        if (checkParam(map, name)) {
            return 0;
        }
        return Float.parseFloat(map.get(name).toString());
    }

    /**
     * 获取Double型值
     *
     * @param name 键值名称
     * @return 若不存在，或转换失败，则返回0
     */
    public static double getDouble(Map<?, ?> map, String name) {
        if (checkParam(map, name)) {
            return 0;
        }
        return Double.parseDouble(map.get(name).toString());
    }

    /**
     * 获取Bool值
     *
     * @param name 键值名称
     * @return 若不存在，或转换失败，则返回false
     */
    public static boolean getBoolean(Map<?, ?> map, String name) {
        if (checkParam(map, name)) {
            return false;
        }

        return Boolean.valueOf(map.get(name).toString());
    }


    private static boolean checkParam(Map<?, ?> map, String name) {
        if (name == null || StringUtils.isBlank(name)) {
            return true;
        }

        if (!map.containsKey(name)) {
            return true;
        }

        if (map.get(name) == null) {
            return true;
        }

        return false;
    }
}
