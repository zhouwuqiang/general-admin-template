package com.java.general.config.cache;

import com.java.general.exception.BusinessException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description :
 * 基于内存的缓存,线程安全的.
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/4/9 16:13
 */
public class RamCache {

    /**
     * 默认超时时间
     */
    private static final Long DEFAULT_TIME_OUT = 1000 * 160 * 10L;

    /**
     * 提前超时
     */
    private static final Long PER_TIME_OUT = 1000L;

    /**
     * 属性存放map
     */
    private static final Map<String, Object> ATTRIBUTE_MAP = new ConcurrentHashMap<>();

    /**
     * key 超时map
     */
    private static final Map<String, Long> KEY_TIME_OUT_MAP = new ConcurrentHashMap<>();


    /**
     * 判断是否存在
     *
     * @param key
     * @return
     */
    public static boolean exist(String key) {
        if (KEY_TIME_OUT_MAP.containsKey(key)) {
            if (KEY_TIME_OUT_MAP.get(key) > (System.currentTimeMillis() + PER_TIME_OUT)) {
                return true;
            } else {
                KEY_TIME_OUT_MAP.remove(key);
                ATTRIBUTE_MAP.remove(key);
            }
        }
        return false;
    }


    /**
     * 获取缓存, 缓存超时后返回空
     *
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T get(String key, Class<T> cls) {

        if (cls == null) {
            throw new BusinessException("类型不能为空!");
        }

        if (exist(key)) {
            if (cls.isInstance(ATTRIBUTE_MAP.get(key))) {
                return (T) ATTRIBUTE_MAP.get(key);
            } else {
                throw new BusinessException("缓存参数类型不匹配");
            }
        }

        return null;
    }


    /**
     * 添加缓存,默认超时时间 同名会被覆盖
     *
     * @param key
     * @param value
     * @return
     */
    public static void set(String key, Object value) {
        KEY_TIME_OUT_MAP.put(key, System.currentTimeMillis() + DEFAULT_TIME_OUT);
        ATTRIBUTE_MAP.put(key, value);
    }

    /**
     * 添加缓存,默认超时时间
     *
     * @param key
     * @param value
     * @param timeOut 超时时间
     */
    public static void set(String key, Object value, Long timeOut) {
        KEY_TIME_OUT_MAP.put(key, System.currentTimeMillis() + timeOut);
        ATTRIBUTE_MAP.put(key, value);
    }
}
