package com.soup.commons.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 *
 * @author zhaoyi
 */
public final class DateUtil {

    public static final String DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATE_SHORT = "yyyy-MM-dd";
    public static final String DATE_FORMATE_LONG = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMATE_NORMAL = "yyyyMMddHHmmss";

    private DateUtil() {
    }

    /**
     * 获取当前时间格式化的字符串
     *
     * @param pattern 格式化，默认采用的格式化方式：yyyyMMddHHmmssSSS
     * @return 当前时间的格式化
     */
    public static String getTime(String pattern) {
        DateTime dateTime = new DateTime();
        String formateTime;
        if (StringUtils.isNotEmpty(pattern)) {
            formateTime = dateTime.toString(pattern);
        } else {
            formateTime = dateTime.toString(DATE_FORMATE_LONG);
        }
        return formateTime;
    }

    /**
     * 时间格式化
     *
     * @param date 需要格式化的日期
     * @param pattern 需要
     * @return 格式化时间
     */
    public static String date2Str(Date date, String pattern) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(pattern);
    }

    /**
     * 字符串转时间
     *
     * @param dateStr 字符串时间
     * @param parttern 时间格式
     * @return 时间
     */
    public static Date str2Date(String dateStr, String parttern) {
        if (StringUtils.isNotEmpty(dateStr)) {
            DateTimeFormatter formatter = DateTimeFormat.forPattern(parttern);
            DateTime dateTime = DateTime.parse(dateStr, formatter);
            return dateTime.toDate();
        }

        return null;
    }

    /**
     * 将excel中的日期值转换成日期格式
     *
     * @param use1904windowing 是否使用904windowing
     * @param value 待转换的值
     * @return 日期字符串
     */
    public static String getPOIDate(boolean use1904windowing, double value, String dateFormate) {
        int wholeDays = (int) Math.floor(value);
        int millisecondsInDay = (int) ((value - (double) wholeDays) * 8.64E7D + 0.5D);
        Calendar calendar = new GregorianCalendar();
        short startYear = 1900;
        byte dayAdjust = -1;
        if (use1904windowing) {
            startYear = 1904;
            dayAdjust = 1;
        } else if (wholeDays < 61) {
            dayAdjust = 0;
        }
        calendar.set(startYear, Calendar.JANUARY, wholeDays + dayAdjust, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, millisecondsInDay);
        if (calendar.get(Calendar.MILLISECOND) == 0) {
            calendar.clear(Calendar.MILLISECOND);
        }

        String pattern = dateFormate;
        if (StringUtils.isEmpty(pattern)) {
            pattern = DATE_FORMATE_SHORT;
        }

        return date2Str(calendar.getTime(), pattern);
    }
}
