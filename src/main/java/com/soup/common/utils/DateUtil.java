package com.soup.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具类
 *
 * @author zhaoyi
 */
public final class DateUtil {

    public static String dateFormate = "yyyy-MM-dd HH:mm:ss";
    public static String dateFormateFull = "yyyy-MM-dd HH:mm:ss:SSS";
    public static String dateFormateShort = "yyyy-MM-dd";
    public static String monthFormate = "yyyy-MM";

    public static String dateFormateLong = "yyyyMMddHHmmssSSS";
    public static String dateFormateNormal = "yyyyMMddHHmmss";
    public static String dateTimeFormate = "yyyyMMdd";

    /**
     * 获取时间的
     */
    public static String getTime(String formatter) {
        DateTime dateTime = new DateTime();

        String currentTime;
        if (StringUtils.isEmpty(formatter)) {
            currentTime = dateTime.toString(dateFormateLong);
        } else {
            currentTime = dateTime.toString(formatter);
        }
        return currentTime;
    }

    /**
     * 获取当前时间
     */
    public static String getTime() {
        return getTime(dateFormateLong);
    }

    /**
     * 时间格式化
     *
     * @param date 需要格式化的日期
     * @return 格式化时间
     */
    public static String date2Str(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(dateFormate);
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
            pattern = dateFormateShort;
        }

        return date2Str(calendar.getTime(), pattern);
    }

    /**
     * 获取月第一天
     * @param month 日期，格式：yyyy-MM
     * @return 日期月第一天
     */
    public static String getFirstDayOfMonth(String month) {
        LocalDate firstday = LocalDate.now().dayOfMonth().withMinimumValue();
        if (StringUtils.isNotEmpty(month)) {
            Date date = str2Date(month, monthFormate);
            if (null != date) {
                firstday = LocalDate.fromDateFields(date).dayOfMonth().withMinimumValue();
            }
        }

        return date2Str(firstday.toDate(), dateFormateShort);
    }

    /**
     * 获取月最后一天
     * @param month 日期，格式：yyyy-MM
     * @return 日期月第一天
     */
    public static String getEndDayOfMonth(String month) {
        LocalDate lastDay = LocalDate.now().dayOfMonth().withMaximumValue();
        if (StringUtils.isNotEmpty(month)) {
            Date date = str2Date(month, monthFormate);
            if (null != date) {
                lastDay = LocalDate.fromDateFields(date).dayOfMonth().withMaximumValue();
            }
        }

        return date2Str(lastDay.toDate(), dateFormateShort);
    }

    /**
     * Date 转 LocalDate
     */
    public static java.time.LocalDate date2LocalDate(Date date) {
        if (null != date) {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();

            // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
            return instant.atZone(zoneId).toLocalDate();
        }
        return null;
    }

    /**
     * java.time.LocalDate 转 Date
     */
    public static Date localDate2date(java.time.LocalDate localDate) {
        if (null != localDate) {
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDate.atStartOfDay(zoneId);

            return Date.from(zdt.toInstant());
        }

        return null;
    }
}
