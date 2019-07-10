package com.java.general.utils;

import com.java.general.exception.BusinessException;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * description : 通用时间处理类
 *
 * @author alger
 * @version 1.0.0
 * @date 2018/12/28 14:51
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    /**
     * 时间格式
     */
    public interface DateFormat {

        String YYYY = "yyyy";

        String YYYY_MM = "yyyy-MM";

        String YYYY_MM_DD = "yyyy-MM-dd";

        String YYYYMMDD = "yyyyMMdd";

        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

        String YYYY_MM_DD_00_00_00 = "yyyy-MM-dd 00:00:00";

        String YYYY_MM_DD_23_59_59 = "yyyy-MM-dd 23:59:59";
    }

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前日期 date类型
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期 指定格式
     *
     * @param format 日期格式
     * @return String
     */
    public static String getNowDateStr(final String format) {
        return parseDateStr(format, new Date());
    }

    /**
     * 将日期转换为指定格式
     *
     * @param format 日期格式
     * @param date   日期
     * @return String
     */
    public static String parseDateStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 将日期格式化成时间
     *
     * @param format  时间格式
     * @param dateStr 时间字符串
     * @return Date
     */
    public static Date getDate(final String dateStr, final String format) {
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式 不确定日期格式
     *
     * @param str
     * @return
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当天日历,当天开始时间
     *
     * @return
     */
    public static Calendar getCurrentCalendarBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * 获取当天日历,当天结束时间
     *
     * @return
     */
    public static Calendar getCurrentCalendarEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal;
    }

    /**
     * 获取服务器启动时间
     *
     * @return
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }


    private static final long  nd = 1000 * 24 * 60 * 60;
    private static final long  nh = 1000 * 60 * 60;
    private static final long  nm = 1000 * 60;
    private static final long  ns = 1000;

    /**
     * 计算两个时间差
     *
     * @param endDate
     * @param nowDate
     * @return
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        return getDatePoor(diff);
    }

    /**
     * 时间戳转换为string
     *
     * @param diff
     * @return
     */
    public static String getDatePoor( long diff) {
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }

    /**
     * 获取指定日期年份
     */
    public static int getYearByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.get(Calendar.YEAR);
        return c.get(Calendar.YEAR);

    }

    /**
     * 获取指定日期年份
     */
    public static int getMonthByDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.get(Calendar.MONTH);

        return c.get(Calendar.MONTH) + 1;
    }

    // 两个日期月份相减
    public static int dateDiff(Date beginDate, Date endDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(beginDate);
        int beginMonth = c.get(Calendar.MONTH);
        c.setTime(endDate);
        int endMonth = c.get(Calendar.MONTH);

        return beginMonth - endMonth;
    }

    public static Date dateDiff() throws ParseException {
        // 获取当前月最后一天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = sdf.format(ca.getTime());

        return sdf.parse(last);
    }



    /**
     * 线程休眠
     *
     * @param millis
     */
    public static void sleepByMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new BusinessException("线程等待休眠异常!{}", e.getMessage());
        }
    }
}
