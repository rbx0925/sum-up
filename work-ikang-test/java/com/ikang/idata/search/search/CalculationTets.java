package com.ikang.idata.search.search;

import cn.hutool.core.date.*;
import cn.hutool.core.date.format.DateParser;
import cn.hutool.core.date.format.DatePrinter;
import cn.hutool.core.date.format.GlobalCustomFormat;
import cn.hutool.core.util.StrUtil;
import com.ikang.idata.search.search.service.DepartmentAppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

import static cn.hutool.core.date.DateUtil.newSimpleFormat;

/**
 * @author <a href="yanan.mu-ext@ikang.com">jiangfeng</a>
 * @date 11/3/2022
 */
@ActiveProfiles(value = {"test"})
@SpringBootTest
@Slf4j
public class CalculationTets extends CalendarUtil {
    @Autowired
    DepartmentAppointmentService departmentAppointmentService;

    @Test
    public void test(){
        log.info("departmentAppointmentService:{}",departmentAppointmentService);
    }

    @Test
    public void test01(){
        BigDecimal calculation = departmentAppointmentService.Calculation("2", "1");
        log.info("calculation:{}",calculation);
    }
    @Test
    public void testDouble() {
        Double d = 04d;
        System.out.println(d.toString());
        System.out.println(new BigDecimal(d.toString()));
    }

    private static final String[] wtb = new String[]{"sun", "mon", "tue", "wed", "thu", "fri", "sat", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec", "gmt", "ut", "utc", "est", "edt", "cst", "cdt", "mst", "mdt", "pst", "pdt"};


    public static DateTime date() {
        return new DateTime();
    }


    public static DateTime date(Date date) {
        return date instanceof DateTime ? (DateTime)date : dateNew(date);
    }

    public static DateTime dateNew(Date date) {
        return new DateTime(date);
    }

    public static DateTime date(long date) {
        return new DateTime(date);
    }

    public static DateTime date(Calendar calendar) {
        return new DateTime(calendar);
    }

    public static DateTime date(TemporalAccessor temporalAccessor) {
        return new DateTime(temporalAccessor);
    }

    public static long current() {
        return System.currentTimeMillis();
    }

    public static long currentSeconds() {
        return System.currentTimeMillis() / 1000L;
    }

    public static String now() {
        return formatDateTime(new DateTime());
    }

    public static String today() {
        return formatDate(new DateTime());
    }

    public static int year(Date date) {
        return DateTime.of(date).year();
    }

    public static int quarter(Date date) {
        return DateTime.of(date).quarter();
    }

    public static Quarter quarterEnum(Date date) {
        return DateTime.of(date).quarterEnum();
    }

    public static int month(Date date) {
        return DateTime.of(date).month();
    }

    public static Month monthEnum(Date date) {
        return DateTime.of(date).monthEnum();
    }

    public static int weekOfYear(Date date) {
        return DateTime.of(date).weekOfYear();
    }

    public static int weekOfMonth(Date date) {
        return DateTime.of(date).weekOfMonth();
    }

    public static int dayOfMonth(Date date) {
        return DateTime.of(date).dayOfMonth();
    }

    public static int dayOfYear(Date date) {
        return DateTime.of(date).dayOfYear();
    }

    public static int dayOfWeek(Date date) {
        return DateTime.of(date).dayOfWeek();
    }

    public static Week dayOfWeekEnum(Date date) {
        return DateTime.of(date).dayOfWeekEnum();
    }

    public static boolean isWeekend(Date date) {
        Week week = dayOfWeekEnum(date);
        return Week.SATURDAY == week || Week.SUNDAY == week;
    }

    public static int hour(Date date, boolean is24HourClock) {
        return DateTime.of(date).hour(is24HourClock);
    }

    public static int minute(Date date) {
        return DateTime.of(date).minute();
    }

    public static int second(Date date) {
        return DateTime.of(date).second();
    }

    public static int millisecond(Date date) {
        return DateTime.of(date).millisecond();
    }

    public static boolean isAM(Date date) {
        return DateTime.of(date).isAM();
    }

    public static boolean isPM(Date date) {
        return DateTime.of(date).isPM();
    }

    public static int thisYear() {
        return year(date());
    }

    public static int thisMonth() {
        return month(date());
    }

    public static Month thisMonthEnum() {
        return monthEnum(date());
    }

    public static int thisWeekOfYear() {
        return weekOfYear(date());
    }

    public static int thisWeekOfMonth() {
        return weekOfMonth(date());
    }

    public static int thisDayOfMonth() {
        return dayOfMonth(date());
    }

    public static int thisDayOfWeek() {
        return dayOfWeek(date());
    }

    public static Week thisDayOfWeekEnum() {
        return dayOfWeekEnum(date());
    }

    public static int thisHour(boolean is24HourClock) {
        return hour(date(), is24HourClock);
    }

    public static int thisMinute() {
        return minute(date());
    }

    public static int thisSecond() {
        return second(date());
    }

    public static int thisMillisecond() {
        return millisecond(date());
    }

    public static String yearAndQuarter(Date date) {
        return yearAndQuarter(calendar(date));
    }

    public static LinkedHashSet<String> yearAndQuarter(Date startDate, Date endDate) {
        return startDate != null && endDate != null ? yearAndQuarter(startDate.getTime(), endDate.getTime()) : new LinkedHashSet(0);
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return LocalDateTimeUtil.formatNormal(localDateTime);
    }

    public static String format(LocalDateTime localDateTime, String format) {
        return LocalDateTimeUtil.format(localDateTime, format);
    }

    public static String format(Date date, String format) {
        if (null != date && !StrUtil.isBlank(format)) {
            if (GlobalCustomFormat.isCustomFormat(format)) {
                return GlobalCustomFormat.format(date, format);
            } else {
                TimeZone timeZone = null;
                if (date instanceof DateTime) {
                    timeZone = ((DateTime)date).getTimeZone();
                }

                return format((Date)date, (DateFormat)newSimpleFormat(format, (Locale)null, timeZone));
            }
        } else {
            return null;
        }
    }

    public static String format(Date date, DatePrinter format) {
        return null != format && null != date ? format.format(date) : null;
    }

    public static String format(Date date, DateFormat format) {
        return null != format && null != date ? format.format(date) : null;
    }

    public static String format(Date date, DateTimeFormatter format) {
        return null != format && null != date ? TemporalAccessorUtil.format(date.toInstant(), format) : null;
    }

    public static String formatDateTime(Date date) {
        return null == date ? null : DatePattern.NORM_DATETIME_FORMAT.format(date);
    }

    public static String formatDate(Date date) {
        return null == date ? null : DatePattern.NORM_DATE_FORMAT.format(date);
    }

    public static String formatTime(Date date) {
        return null == date ? null : DatePattern.NORM_TIME_FORMAT.format(date);
    }

    public static String formatHttpDate(Date date) {
        return null == date ? null : DatePattern.HTTP_DATETIME_FORMAT.format(date);
    }

    public static String formatChineseDate(Date date, boolean isUppercase, boolean withTime) {
        if (null == date) {
            return null;
        } else {
            return !isUppercase ? (withTime ? DatePattern.CHINESE_DATE_TIME_FORMAT : DatePattern.CHINESE_DATE_FORMAT).format(date) : CalendarUtil.formatChineseDate(CalendarUtil.calendar(date), withTime);
        }
    }

    public static LocalDateTime parseLocalDateTime(CharSequence dateStr) {
        return parseLocalDateTime(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static LocalDateTime parseLocalDateTime(CharSequence dateStr, String format) {
        return LocalDateTimeUtil.parse(dateStr, format);
    }

    public static DateTime parse(CharSequence dateStr, DateFormat dateFormat) {
        return new DateTime(dateStr, dateFormat);
    }

    public static DateTime parse(CharSequence dateStr, DateParser parser) {
        return new DateTime(dateStr, parser);
    }

    public static DateTime parse(CharSequence dateStr, DateParser parser, boolean lenient) {
        return new DateTime(dateStr, parser);
    }

    public static DateTime parse(CharSequence dateStr, DateTimeFormatter formatter) {
        return new DateTime(dateStr, formatter);
    }

    public static DateTime parse(CharSequence dateStr, String format) {
        return new DateTime(dateStr, format);
    }

    public static DateTime parse(CharSequence dateStr, String format, Locale locale) {
        return GlobalCustomFormat.isCustomFormat(format) ? new DateTime(GlobalCustomFormat.parse(dateStr, format)) : new DateTime(dateStr, newSimpleFormat(format, locale, (TimeZone)null));
    }

    public static DateTime parse(String str, String... parsePatterns) throws DateException {
        return new DateTime(CalendarUtil.parseByPatterns(str, parsePatterns));
    }

    public static DateTime parseDateTime(CharSequence dateString) {
        CharSequence dateString1 = normalize(dateString);
        return parse((CharSequence)dateString, (DateParser)DatePattern.NORM_DATETIME_FORMAT);
    }

    public static DateTime parseDate(CharSequence dateString) {
        CharSequence dateString1 = normalize(dateString);
        return parse((CharSequence)dateString, (DateParser)DatePattern.NORM_DATE_FORMAT);
    }

    private static String normalize(CharSequence dateStr) {
        if (StrUtil.isBlank(dateStr)) {
            return StrUtil.str(dateStr);
        } else {
            List<String> dateAndTime = StrUtil.splitTrim(dateStr, ' ');
            int size = dateAndTime.size();
            if (size >= 1 && size <= 2) {
                StringBuilder builder = StrUtil.builder();
                String datePart = ((String)dateAndTime.get(0)).replaceAll("[/.年月]", "-");
                datePart = StrUtil.removeSuffix(datePart, "日");
                builder.append(datePart);
                if (size == 2) {
                    builder.append(' ');
                    String timePart = ((String)dateAndTime.get(1)).replaceAll("[时分秒]", ":");
                    timePart = StrUtil.removeSuffix(timePart, ":");
                    timePart = timePart.replace(',', '.');
                    builder.append(timePart);
                }

                return builder.toString();
            } else {
                return StrUtil.str(dateStr);
            }
        }
    }
}
