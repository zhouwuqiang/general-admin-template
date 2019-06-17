package com.java.general.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * description :
 * 32位  XXXXXX - 053051 - 20181110160128586 - 001
 * 6位:  业务识别
 * 6位: ip地址127.001.123.123 >> 123123
 * 17位: 时间       yyyyMMddHHmmssSSS
 * 3位:  自增序号
 *
 * @author alger
 * @version 1.0.0
 * @date 2019/1/4 14:44
 */
public class UuidCodeWorker {


    private static final Logger LOGGER = LoggerFactory.getLogger(UuidCodeWorker.class);

    /**************************************** final ******************************************************/


    /**
     * ip地址 取后6位
     */
    private static String IP_ADDRESS = formatIpAddress();

    /**
     * 默认业务标识 6 位
     */
    private final static String BUSINESS_CODE = "XXXXXX" ;


    /**
     * 最大序号 这里为1000 (1111101000=1000)
     */
    private final static long MAX_SEQUENCE = 999L;

    /**
     * 毫秒内序列
     */
    private static long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private static long lastTimestamp = -1L;


    /**************************************** Constructors ******************************************************/


    public synchronized static String nextCode(String prefix) {
        StringBuilder code = new StringBuilder();

        if (StringUtils.isNotBlank(prefix)){
            code.append(prefix);
        }

        code.append(IP_ADDRESS);

        long timestamp = timeGen();

        if (lastTimestamp == timestamp) {
            sequence++;
            if (sequence == MAX_SEQUENCE) {
                //毫秒内序列溢出
                timestamp = waitNextMillis(lastTimestamp);
                sequence = 0L;
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        code.append(getTime(timestamp));
        code.append(formatSequence(sequence));

        return code.toString();
    }


    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private static long waitNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }


    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    private static long timeGen() {
        return System.currentTimeMillis();
    }


    /**
     * 获取时间
     *
     * @return
     */
    private static String getTime(long timestamp) {
        return DateFormatUtils.format(new Date(timestamp), "yyyyMMddHHmmssSSS");
    }

    /**
     * 格式化序号
     *
     * @param sequence
     * @return
     */
    private static String formatSequence(long sequence) {
        return formatString(String.valueOf(sequence), 3);
    }

    /**
     * 格式ip地址
     *
     * @return
     */
    private static String formatIpAddress() {
        StringBuilder codeIp = new StringBuilder();

        String ipAddress = LocalIpUtil.getIpAddress();
        String[] units = ipAddress.split("\\.");
        codeIp.append(formatString(units[2],3));
        codeIp.append(formatString(units[3],3));

        return codeIp.toString();
    }

    /**
     * 格式化三位string 不足0 填充
     * @param time
     * @param length
     * @return
     */
    private static String formatString(String time, int length) {
        StringBuilder stringBuilder = new StringBuilder(time);
        while (stringBuilder.length() < length) {
            stringBuilder.insert(0, "0");
        }
        return stringBuilder.toString();

    }
}
