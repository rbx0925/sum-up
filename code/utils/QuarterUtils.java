package com.ikang.idata.common.utils;

import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * 季度工具栏
 *
 * @author <a href="jiangfeng.yan@ikang.com">jiangfeng</a>
 * @version 2021年06月15日 下午 2:53
 */
public class QuarterUtils {

    public static Date localDate2Date(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        Instant instant1 = zonedDateTime.toInstant();
        Date from = Date.from(instant1);
        return from;
    }
    /**
     *  当季              对应q为0  year传值失效
     *  Q1：当年4月~6月   对应q为1
     * 	Q2：当年7月~9月   对应q为2
     * 	Q3：当年10月~12月 对应q为3
     * 	Q4：次年1月~3月   对应q为4
     * 	跟据年year和季度q返回季度第一天和最后一天
     * 	year为0代表今年
     * 	eg: getQuarterByYear(2021, 1)
     * 	    返回
     * 	    ["2021-04-01","2021-06-30"]
     */
    public static String[] getQuarterByYear(int year, int q) {
        if (q < 0 || q > 4) {
            return null;
        }
        if (year <= 0) {
            year = LocalDate.now().getYear();
        }
        String str;
        String[] quarter = new String[4];
        Calendar quarterCalendar = Calendar.getInstance();
        switch (q) {
            case 0:
                LocalDate now = LocalDate.now();
//                now = LocalDate.of(year,1,1);
                now = now.plusDays(-1);
                Date date = localDate2Date(now);
                int season = getSeason(date);
                if (season - 1 == 0) {
                    quarter = getQuarterByYear(now.getYear() - 1, 4);
                } else {
                    quarter = getQuarterByYear(now.getYear(), season - 1);
                }
                break;
            case 1:
                quarterCalendar.set(Calendar.YEAR, year);
                quarterCalendar.set(Calendar.MONTH, 6);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = QuarterUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
                quarter[0] = str.substring(0, str.length() - 5) + "04-01 00:00:00";
                quarter[1] = str + " 23:59:59";
                quarter[2] = year + "财年Q1";
                quarter[3] = q + "";
                break;
            case 2:
                quarterCalendar.set(Calendar.YEAR, year);
                quarterCalendar.set(Calendar.MONTH, 9);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = QuarterUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
                quarter[0] = str.substring(0, str.length() - 5) + "07-01 00:00:00";
                quarter[1] = str + " 23:59:59";
                quarter[2] = year + "财年Q2";
                quarter[3] = q + "";
                break;
            case 3:
                quarterCalendar.set(Calendar.YEAR, year);
                str = QuarterUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
                quarter[0] = str.substring(0, str.length() - 5) + "10-01 00:00:00";
                quarter[1] = str.substring(0, str.length() - 5) + "12-31 23:59:59";
                quarter[2] = year + "财年Q3";
                quarter[3] = q + "";
                break;
            case 4:
                quarterCalendar.set(Calendar.YEAR, year + 1);
                quarterCalendar.set(Calendar.MONTH, 3);
                quarterCalendar.set(Calendar.DATE, 1);
                quarterCalendar.add(Calendar.DATE, -1);
                str = QuarterUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
                quarter[0] = str.substring(0, str.length() - 5) + "01-01 00:00:00";
                quarter[1] = str + " 23:59:59";
                quarter[2] = year + "财年Q4";
                quarter[3] = q + "";
                break;
        }
        return quarter;
    }

//    public static String[] getCurrQuarter(int num) {
//        String[] s = new String[2];
//        String str;
//        // 设置本年的季
//        Calendar quarterCalendar = null;
//        switch (num) {
//            case 1: // 本年到现在经过了一个季度，在加上前4个季度
//                quarterCalendar = Calendar.getInstance();
//                quarterCalendar.set(Calendar.MONTH, 3);
//                quarterCalendar.set(Calendar.DATE, 1);
//                quarterCalendar.add(Calendar.DATE, -1);
//                str = QuarterUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
//                s[0] = str.substring(0, str.length() - 5) + "01-01";
//                s[1] = str;
//                break;
//            case 2: // 本年到现在经过了二个季度，在加上前三个季度
//                quarterCalendar = Calendar.getInstance();
//                quarterCalendar.set(Calendar.MONTH, 6);
//                quarterCalendar.set(Calendar.DATE, 1);
//                quarterCalendar.add(Calendar.DATE, -1);
//                str = QuarterUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
//                s[0] = str.substring(0, str.length() - 5) + "04-01";
//                s[1] = str;
//                break;
//            case 3:// 本年到现在经过了三个季度，在加上前二个季度
//                quarterCalendar = Calendar.getInstance();
//                quarterCalendar.set(Calendar.MONTH, 9);
//                quarterCalendar.set(Calendar.DATE, 1);
//                quarterCalendar.add(Calendar.DATE, -1);
//                str = QuarterUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
//                s[0] = str.substring(0, str.length() - 5) + "07-01";
//                s[1] = str;
//                break;
//            case 4:// 本年到现在经过了四个季度，在加上前一个季度
//                quarterCalendar = Calendar.getInstance();
//                str = QuarterUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
//                s[0] = str.substring(0, str.length() - 5) + "10-01";
//                s[1] = str.substring(0, str.length() - 5) + "12-31";
//                break;
//        }
//        return s;
//    }
    public static String formatDate(Date currentDate, String pattern){
        if(currentDate == null || "".equals(pattern) || pattern == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(currentDate);
    }

    public static void main(String[] args) {
//        DateTime dateTime = new DateTime("2017-9-14 21:55:18", "yyyy-MM-dd HH:mm:ss");
//        // 获取日期成员（年、季度、月、日）
//        int year = dateTime.year();// 年
//        System.out.println("year = " + year);
//        int season = dateTime.season();// 季度
//        System.out.println("season = " + season);
//        int month = dateTime.month();// 月份
//        System.out.println("month = " + month);
//        int dayOfWeek = dateTime.dayOfWeek();// 周几
//        System.out.println("dayOfWeek = " + dayOfWeek);
//        int dayOfMonth = dateTime.dayOfMonth();// 此月第N天
//        System.out.println("dayOfMonth = " + dayOfMonth);
//        // 调整日期时间
//        DateTime dateTime1 = dateTime.offset(DateField.DAY_OF_MONTH, -1);
//        System.out.println(dateTime1.toString());

        String[]  quarterByYear = getQuarterByYear(2021, 1);
//        System.out.println("quarterByYear1 = "  + quarterByYear[0]+ "--"+ quarterByYear[1]+ "--"+ quarterByYear[2]+ "--"+ quarterByYear[3]);
//        quarterByYear = getQuarterByYear(2021, 2);
//        System.out.println("quarterByYear2 = " + quarterByYear[0]+ "--"+ quarterByYear[1]+ "--"+ quarterByYear[2]+ "--"+ quarterByYear[3]);
//        quarterByYear = getQuarterByYear(2021, 3);
//        System.out.println("quarterByYear3 = "  + quarterByYear[0]+ "--"+ quarterByYear[1]+ "--"+ quarterByYear[2]+ "--"+ quarterByYear[3]);
//        quarterByYear = getQuarterByYear(2020, 4);
//        System.out.println("quarterByYear4 = " + quarterByYear[0]+ "--"+ quarterByYear[1]+ "--"+ quarterByYear[2]+ "--"+ quarterByYear[3]);
//        quarterByYear = getQuarterByYear(2020, 0);
//        System.out.println("quarterByYear5 = " + quarterByYear[0]+ "--"+ quarterByYear[1]+ "--"+ quarterByYear[2]+ "--"+ quarterByYear[3]);
        quarterByYear = getQuarterByYear(2021, 0);
        System.out.println("quarterByYear6 = "  + quarterByYear[0]+ "--"+ quarterByYear[1]+ "--"+ quarterByYear[2]+ "--"+ quarterByYear[3]);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse("2021-07-02");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int season = DateUtil.quarter(date);
        int season2 = getSeason(date);
        System.out.println("season = " + season);
        System.out.println("season2 = " + season2);
    }

    public static int getSeason(Date date) {
        int season = 0;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }
}
