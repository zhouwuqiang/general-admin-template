package com.java.general.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * description :
 *
 * @author alger
 * @version 1.0.0
 * @date 2018/12/28 14:51
 */
public class BigDecimalUtils {

    /**
     * 获取bigDecimal对象
     *
     * @param value
     * @param <T>
     * @return
     */
    public static <T> BigDecimal getBigDecimal(T value) {
        BigDecimal result = new BigDecimal("0");
        if (value != null) {
            if (value instanceof BigDecimal) {
                result = (BigDecimal) value;
            } else if (value instanceof String) {
                result = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                result = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                result = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("无法将 " + value.getClass() + "类型转换为 BigDecimal对象");
            }
        }
        return result;
    }

    /**
     * 四舍五入格式化位数
     *
     * @param value
     * @return
     */
    public static <T> BigDecimal format(T value) {
        return getBigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 四舍五入格式化位数
     *
     * @param value
     * @param scale
     * @return
     */
    public static <T> BigDecimal format(T value, int scale) {
        return getBigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 多个数值相加
     *
     * @param values
     * @return
     */
    public static BigDecimal multipleAdd(Object... values) {
        BigDecimal result = BigDecimal.ZERO;
        for (Object temp : values) {
            result = result.add(getBigDecimal(temp));
        }
        return result;
    }

    /**
     * 两个数值相加
     *
     * @param value
     * @param addend
     * @return
     */
    public static <T, E> BigDecimal add(T value, E addend) {
        return getBigDecimal(value).add(getBigDecimal(addend));
    }


    /**
     * 两个数相除 四舍五入
     * 精确到10位
     *
     * @param value
     * @param divisor
     * @return
     */
    public static <T, E> BigDecimal divide(T value, E divisor) {
        return getBigDecimal(value).divide(getBigDecimal(divisor), 10, BigDecimal.ROUND_HALF_UP);
    }
}

