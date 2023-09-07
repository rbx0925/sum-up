package com.ikang.idata.common.utils;

import cn.hutool.core.date.DateField;
import com.alibaba.fastjson.JSON;
import com.ikang.idata.common.entity.DateBean;
import com.ikang.idata.common.entity.DateList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.ikang.idata.common.consts.MagicConst.INTEGER_1;
import static com.ikang.idata.common.consts.MagicConst.INT_2;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @version 1.0
 * @date 2021/12/9 18:19
 */
@Slf4j
public class DateUtil {
    public final static String FULL_ROW_BARS_DATETIME_PATTERN = "yyyy/MM/dd HH:mm";

    /**
     * 日期格式 年 如2009
     */
    public static final String DATEFORMATYEAR = "yyyy";

    /**
     * 日期格式 年 月  如 2009-02
     */
    public static final String DATEFORMATMONTH = "yyyy-MM";

    /**
     * 日期格式 年 月 日 如2009-02-26
     */
    public static final String DATEFORMATDAY = "yyyy-MM-dd";

    /**
     * 日期格式 年 月 日 时 如2009-02-26 15
     */
    public static final String DATEFORMATHOUR = "yyyy-MM-dd HH";

    /**
     * 日期格式 年 月 日 时 分 如2009-02-26 15:40
     */
    public static final String DATEFORMATMINUTE = "yyyy-MM-dd HH:mm";

    /**
     * 日期格式年 月 日 时 分 秒 如 2009-02-26 15:40:00
     */
    public static final String DATEFORMATSECOND = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期格式年 月 日 时 分 秒 毫秒 如2009-02-26 15:40:00 110
     */
    public static final String DATEFORMATMILLISECOND = "yyyy-MM-dd HH:mm:ss SSS";

    /**
     * 日期格式 年 月 日 如2009-02-26
     */
    public static final DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern(DATEFORMATDAY);

    /**
     * 日期格式 年 月 日 如2009-02-26  15:40:00
     */
    public static final DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern(DATEFORMATSECOND);


    public final static DateTimeFormatter DTF_DATE_FORMAT_DATETIME_YEAR_MONTH_DAY = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public final static DateTimeFormatter DTF_DATE_FORMAT_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取两个日期之间的所有日期
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List<String> getBetweenDates(String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMATDAY);

        Date start = new SimpleDateFormat(DATEFORMATDAY).parse(startDate);//定义起始日期
        Date end = new SimpleDateFormat(DATEFORMATDAY).parse(endDate);//定义结束日期

        List<String> result = new ArrayList<String>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
            result.add(sdf.format(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    /**
     * 根据开始结束时间获取中间年月 ["2021-02","2021-03"]
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    public static List<String> getBetweenMonths(String startDate, String endDate)  {
        if (StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)) {
            return null;
        }
        return findDates(startDate,endDate,"month");
    }

    /**
     * 根据开始结束时间获取中间年月
     * @param dBegin 开始时间
     * @param dEnd 结束时间
     * @param flag 标识 year计算年之间的  month计算月份
     * @return
     */
    public static List<String> findDates(String dBegin, String dEnd, String flag) {
        List<String> lDate = null;
        try {
            SimpleDateFormat simpleDateFormat = null;
            if ("month".equals(flag)) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            } else if ("year".equals(flag)) {
                simpleDateFormat = new SimpleDateFormat("yyyy");
            }
            Date de = simpleDateFormat.parse(dEnd);
            Date db = simpleDateFormat.parse(dBegin);
            lDate = new ArrayList<String>();
            lDate.add(simpleDateFormat.format(db.getTime()));
            Calendar calBegin = Calendar.getInstance();
            calBegin.setTime(db);
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(de);
            while (de.after(calBegin.getTime())) {
                if ("month".equals(flag)) {
                    calBegin.add(Calendar.MONTH,1);
                } else if ("year".equals(flag)) {
                    calBegin.add(Calendar.YEAR,1);
                }

                lDate.add(simpleDateFormat.format(calBegin.getTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return lDate;
    }




    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * @description: 通过传入的时间值，返回当前值、环比值、同比值的list集合
     * eg:
     *  2020-02-29
     *  2019-02-28
     *  2020-02-28
     * @param purchaseDate
     * @return java.util.List<java.lang.String>
     * @auther yanan.mu-ext
     * @date 2022-02-21 下午3:06
     */
    public static List<String> getDatesList(String purchaseDate) {
        //同比
        String YOYTime = "";
        //环比
        String QOQTime = "";
        SimpleDateFormat df = new SimpleDateFormat(DATEFORMATDAY);//设置日期格式  HH:mm:ss
        //获取当前时间的同比和环比
        try {
            Date date = df.parse(purchaseDate);
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar1.setTime(date);
            calendar2.setTime(date);
            calendar1.add(Calendar.YEAR, -1);  // 在当前年基础上-1
            calendar2.add(Calendar.DAY_OF_MONTH, -1);  // 在当前日基础上-1
            SimpleDateFormat format = new SimpleDateFormat(DATEFORMATDAY);
            YOYTime = format.format(calendar1.getTime());
            QOQTime = format.format(calendar2.getTime());
        } catch (ParseException e) {
            log.error("时间日期转换异常： {}",e);
        }

        List<String> dates = new ArrayList<>();
        dates.add(purchaseDate);
        dates.add(YOYTime);
        dates.add(QOQTime);
        return dates;
    }

    /**
     * @description: 获取当前月份的第一天
     *
     * @param date 日期
     * @return java.util.Date
     * @auther yanan.mu-ext
     * @date 2022-03-22 下午1:40
     */
    public static String getFirstDay(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] split = date.split("-");
        // 获取Calendar类的实例
        Calendar c = Calendar.getInstance();
        // 设置年份
        c.set(Calendar.YEAR, Integer.valueOf(split[0]));
        // 设置月份，因为月份从0开始，所以用month - 1
        c.set(Calendar.MONTH, Integer.valueOf(split[1]) - 1);
        // 设置日期
        c.set(Calendar.DAY_OF_MONTH, 1);

        return format.format(c.getTime());
    }

    /**
     * @description: 获取当前月份的最后一天
     *
     * @param date 日期
     * @return java.util.Date
     * @auther yanan.mu-ext
     * @date 2022-03-22 下午1:42
     */
    public static String getLastDay(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] split = date.split("-");
        // 获取Calendar类的实例
        Calendar c = Calendar.getInstance();
        // 设置年份
        c.set(Calendar.YEAR, Integer.valueOf(split[0]));
        // 设置月份，因为月份从0开始，所以用month - 1
        c.set(Calendar.MONTH, Integer.valueOf(split[1]) - 1);
        // 获取当前时间下，该月的最大日期的数字
        int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 将获取的最大日期数设置为Calendar实例的日期数
        c.set(Calendar.DAY_OF_MONTH, lastDay);
        return format.format(c.getTime());
    }


    public static Date getFirstDayDateOfMonth(String startDate) {

        final Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(startDate);
            cal.setTime(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }

    public static Date getLastDayDateOfMonth(String startDate) {

        final Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(startDate);
            cal.setTime(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }
    /**
     * LocalDate转Date
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * Date转LocalDate
     * @param date
     */
    public static LocalDate date2LocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 时间转字符串
     * @param date 时间
     * @param pattern 格式化类型，默认为yyyy-MM-dd HH:mm:ss，其它使用DateUtils.xxx
     * @return
     */
    public static String format(Date date, String pattern){
        if(date == null){
            return "";
        }else{
            if(StringUtils.isBlank(pattern)){
                pattern = FULL_ROW_BARS_DATETIME_PATTERN;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(date);
        }
    }

    /**
     * 方法描述:
     *
     * @param filterType 筛选类别 默认月份：0； 季度：1 ；年份 2
     * @param start  选择的开始时间
     * @param end 选择的结束时间
     * @return
     * @author  wenyue.gao@ikang.com
     * @date    2022/3/21 9:24
     */
    public static DateList getDateList (int filterType, String start, String end ) {
        DateList dateList = new DateList();
        // 当前选择的日期范围
        dateList.setSelectDateList(DateUtil.getBetweenMonths(start,end));
        // 获取同比、环比时间
        DateListBean dateBean = getDateList(filterType, start);
        // 环比时间列表
        dateList.setMoMDateList(DateUtil.getBetweenMonths(dateBean.getStartDateMoM(), dateBean.getEndDateMoM()));
        // 同比时间列表
        dateList.setYoYDateList(DateUtil.getBetweenMonths(dateBean.getStartDateYoY(), dateBean.getEndDateYoY()));
        return dateList;
    }


    /**
     * 方法描述: 根据年月获取同期开始结束
     *
     * @param startDate 选择开始时间
     * @param endDate 选择结束时间
     * @param isRange 1 用es  range [from ,to) 左闭右开
     * @return  com.ikang.idata.common.entity.DateBean
     * @author  wenyue.gao@ikang.com
     * @date    2022/3/23 9:59
     */
    public static DateBean getDateBeanMonth (String startDate, String endDate, Integer isRange)  {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

        DateBean dateBean = new DateBean();
        dateBean.setSelectStartDate(startDate);
        if (INTEGER_1.equals(isRange)) {
            try {
                Date date = format.parse(endDate);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.MONTH, +1);
                dateBean.setSelectEndDate(format.format(c.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
       Date parse = null;
        Date parse1 = null;
        try {
            parse = format.parse(startDate);
            parse1 = format.parse(endDate);
        } catch (ParseException e) {
            log.info(e.toString(), e);
        }
         Calendar year = Calendar.getInstance();
        year.setTime(parse);
        year.add(Calendar.YEAR, -1);
        // 同期月开始
        dateBean.setSameTermStartDate(format.format(year.getTime()));
        Calendar year1 = Calendar.getInstance();
        year1.setTime(parse1);
        year1.add(Calendar.YEAR, -1);
        if(INTEGER_1.equals(isRange)) {
            year1.add(Calendar.MONTH, +1);
        }
        // 同期月结束
        dateBean.setSameTermEndDate( format.format(year1.getTime()));
        // 环比月开始
        Calendar month = Calendar.getInstance();
        month.setTime(parse);
        month.add(Calendar.MONTH, -1);
        Date y = month.getTime();

        dateBean.setMonthStartDate(format.format(month.getTime()));
        Calendar month1 = Calendar.getInstance();
        month1.setTime(parse1);
        month1.add(Calendar.MONTH, -1);
        if(INTEGER_1.equals(isRange)) {
            month1.add(Calendar.MONTH, +1);
        }
        // 环比月结束
        dateBean.setMonthEndDate(format.format(month1.getTime()));
        return dateBean;
    }



    public static String addDay(SimpleDateFormat format, String endDate) {
        try {
            Date date = format.parse(endDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, +1);
            return format.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 方法描述: 根据年月获取开始日，结束日（含同期）
     *
     * @param startDate 选择开始时间
     * @param endDate 选择结束时间
     * @param isRange 1 用es  range [from ,to) 左闭右开
     * @return  com.ikang.idata.common.entity.DateBean
     * @author  wenyue.gao@ikang.com
     * @date    2022/3/23 9:59
     */
    public static DateBean getDateBean (String startDate, String endDate, Integer isRange) {
        DateBean dateBean = new DateBean();
        String[] startSplit = startDate.split("-");
        // 格式 "2020-01" 转成 2020-01-01
        if (INT_2 == startSplit.length) {
            // 月开始第一天
            startDate = DateUtil.getFirstDayOfMonth(startDate);
        }
        String[] endSplit = endDate.split("-");
        // 格式 "2020-03" 转成 2020-03-31
        if (INT_2 == endSplit.length) {
            // 月结束最后天
            endDate =  DateUtil.getLastDayOfMonth(endDate);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (INTEGER_1.equals(isRange)) {
            endDate = addDay(sdf, endDate);
        }
        dateBean.setSelectStartDate(startDate);

        dateBean.setSelectEndDate(endDate);
        // 同期月开始第一天


        String lastYearDateStart =sdf.format(cn.hutool.core.date.DateUtil.offset(cn.hutool.core.date.DateUtil.parse(startDate), DateField.YEAR, -1));
        dateBean.setSameTermStartDate(lastYearDateStart);
        // 同期月结束最后天
        String lastYearDateEnd =sdf.format(cn.hutool.core.date.DateUtil.offset(cn.hutool.core.date.DateUtil.parse(endDate), DateField.YEAR, -1));
        dateBean.setSameTermEndDate(lastYearDateEnd);
        return dateBean;
    }




    /**
     * 获取某个月第一天
     * @param startDate
     * @return
     */
    public static String getFirstDayOfMonth(String startDate) {
        final Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            Date parse = simpleDateFormat.parse(startDate);
            cal.setTime(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获得某月的最后一天
     * @param  startDate 要获取的月份
     * @return
     */
    public static String getLastDayOfMonth(String startDate) {

        final Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            Date parse = simpleDateFormat.parse(startDate);
            cal.setTime(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final int last = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }


    /**
     * 获取某个月第一天的开始时刻
     * @param month
     * @return
     */
    public static String getFirstDayTimeOfMonth(int month) {
        Calendar cal = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        // 设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        // 格式化日期，获取开始时刻
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime()) + " 00:00:00";
        return firstDayOfMonth;
    }

    /**
     * 获得某月的最后一天的最后时刻
     * @param month  要获取的月份
     * @return
     */
    public static String getLastDayTimeOfMonth(int month) {
        Calendar cal = Calendar.getInstance();
        // 设置月份
        cal.set(Calendar.MONTH, month - 1);
        // 获取月份的最大天数
        int lastDay = 0;
        //2月份每年的天数不固定
        if (month == 2) {
            lastDay = cal.getLeastMaximum(Calendar.DAY_OF_MONTH);
        } else {
            lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        // 设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期，获取最后时刻
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime()) + " 23:59:59";
        return lastDayOfMonth;
    }


    /**
     * 获取同比、环比时间
     * @param filterType 筛选类别 默认月份：0； 季度：1 ；年份 2
     *  @param start 选择的开始时间
     * @return DateListBean
     */
    public static DateListBean getDateList(int filterType, String start) {

        DateListBean dateListBean = new DateListBean();
        //当前时间,2020-09-10
        LocalDate nowDate = getFirstDayDateOfMonth(start)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //上月1日,2020-08-01
        LocalDate lastMonthStart = nowDate.plusMonths(-1);
        //上月最后一日,2020-08-31
        LocalDate lastMonthEnd = nowDate.plusDays(-1);

        if(0 == filterType){
            //月度，上月
            //上月第一天,2020-08-01
            Date startDate = localDate2Date(lastMonthStart);
            dateListBean.setStartDate(format(startDate,DATEFORMATDAY));
            //上月最后一天,2020-08-31
            Date endDate = localDate2Date(lastMonthEnd);
            dateListBean.setEndDate(format(endDate,DATEFORMATDAY));

            //同比，去年相同月份
            //相同月份第一天，2019-08-01
            Date startDateYoY = localDate2Date(lastMonthStart.plusYears(-1));
            dateListBean.setStartDateYoY(format(startDateYoY,DATEFORMATDAY));
            //相同月份最后一天，2019-08-31
            LocalDate date = nowDate.plusYears(-1);
            Date endDateYoY = localDate2Date(date.plusDays(-1));
            dateListBean.setEndDateYoY(format(endDateYoY,DATEFORMATDAY));

            //环比，上个月
            //上个月第一天，2020-07-01
            Date startDateMoM = localDate2Date(lastMonthStart.plusMonths(-1));
            dateListBean.setStartDateMoM(format(startDateMoM,DATEFORMATDAY));
            //上个月最后一天，2020-07-30
            Date endDateMoM = localDate2Date(lastMonthStart.plusDays(-1));
            dateListBean.setEndDateMoM(format(endDateMoM,DATEFORMATDAY));

        }
        if(1 == filterType){
            //季度
            //当前季度
            //当前季度开始第一天，2020-07-01
            Date startDate = checkStartDate(nowDate, filterType);
            dateListBean.setStartDate(format(startDate,DATEFORMATDAY));
            //上月最后一天,2020-08-31
            Date endDate = localDate2Date(lastMonthEnd);
            dateListBean.setEndDate(format(endDate,DATEFORMATDAY));

            //同比，去年相同季度
            //相同季度第一天，2019-07-01
            Date startDateYoY = checkStartDate(nowDate.plusYears(-1),filterType);
            dateListBean.setStartDateYoY(format(startDateYoY,DATEFORMATDAY));
            //相同季度最后一天，2019-09-30
            Date endDateYoY = checkEndDate(nowDate.plusYears(-1),filterType);
            dateListBean.setEndDateYoY(format(endDateYoY,DATEFORMATDAY));

            //环比，上个月
            //上个季度第一天，2020-04-01
            Date startDateMoM = checkStartDate(nowDate.plusMonths(-3),filterType);
            dateListBean.setStartDateMoM(format(startDateMoM,DATEFORMATDAY));
            //上个季度最后一天，2020-06-30
            Date endDateMoM = checkEndDate(nowDate.plusMonths(-3),filterType);
            dateListBean.setEndDateMoM(format(endDateMoM,DATEFORMATDAY));

        }
        if(2 == filterType){
            //年度
            //当前年度
            //当前年度开始第一天，2020-01-01
            Date startDate = checkStartDate(nowDate,filterType);
            dateListBean.setStartDate(format(startDate,DATEFORMATDAY));
            //上月最后一天,2020-08-31
            Date endDate = localDate2Date(lastMonthEnd);
            dateListBean.setEndDate(format(endDate,DATEFORMATDAY));

            //同比，去年
            //去年第一天，2019-01-01
            Date startDateYoY = checkStartDate(nowDate.plusYears(-1),filterType);
            dateListBean.setStartDateYoY(format(startDateYoY,DATEFORMATDAY));
            //去年最后一天，2019-12-31
            Date endDateYoY = checkEndDate(nowDate,filterType);
            dateListBean.setEndDateYoY(format(endDateYoY,DATEFORMATDAY));

            //环比，上个月
            //上个季度第一天，2019-01-01
            Date startDateMoM = checkStartDate(nowDate.plusYears(-1),filterType);
            dateListBean.setStartDateMoM(format(startDateMoM,DATEFORMATDAY));
            //上个季度最后一天，2019-12-31
            Date endDateMoM = checkEndDate(nowDate,filterType);
            dateListBean.setEndDateMoM(format(endDateMoM,DATEFORMATDAY));

        }
        return dateListBean;

    }
    /**
     * 获取时间区间开始时间
     * @param localDate 当前日期
     * @param timeType 时间区间类型（月、季度、年度）
     * @return Date
     */
    private static Date checkStartDate(LocalDate localDate,int timeType) {
        Date date = null;
        //开始为每月的1日
        localDate = localDate.withDayOfMonth(1);
        //判断区间
        switch (timeType){
            case 0:
                // 月
                break;
            case 1:
                // 季
                localDate = checkMouth(localDate);
                break;
            case 2:
                // 年
                localDate = localDate.withMonth(1);
                break;
            default:
        }
        //转为Date
        date = localDate2Date(localDate);
        return date;
    }

    /**
     * 获取时间区间结束时间
     * @param localDate 当前日期
     * @param timeType 时间区间类型（月、季度、年度）
     * @return Date
     */
    private static Date checkEndDate(LocalDate localDate,int timeType) {
        Date date = null;
        //开始为每月的1日
        localDate = localDate.withDayOfMonth(1);

        //判断区间
        switch (timeType){
            case 0:
                //往前一天，为上月月底
                localDate = localDate.plusDays(-1);
                break;
            case 1:
                //季
                //季度第一个月的第一天
                localDate = checkMouth(localDate);
                //下个季度第一个月的第一天
                localDate = localDate.plusMonths(+3);
                //本季度最后一个月的最后一天
                localDate = localDate.plusDays(-1);
                break;
            case 2:
                //年
                //年1月1日
                localDate = localDate.withMonth(1);
                //去年最后一天
                localDate = localDate.plusDays(-1);
                break;
            default:
        }
        //转为Date
        date = localDate2Date(localDate);
        return date;
    }

    /**
     * 判断季度
     * @param date 当前日期
     * @return 季度开始日期
     */
    private static LocalDate checkMouth(LocalDate date){
        LocalDate startDate = date;
        switch (date.getMonthValue()){
            case 1:
            case 2:
            case 3:
                startDate = date.withMonth(1);
                break;
            case 4:
            case 5:
            case 6:
                startDate = date.withMonth(4);
                break;
            case 7:
            case 8:
            case 9:
                startDate = date.withMonth(7);
                break;
            case 10:
            case 11:
            case 12:
                startDate = date.withMonth(10);
                break;
            default:
        }

        return startDate;
    }


    /**
     * @description: 通过传入的时间值，返回当前值、环比值、同比值的list集合
     * eg:
     *  2020-02-29
     *  2019-02-28
     *  2020-02-28
     * @param purchaseDate
     * @return java.util.List<java.lang.String>
     * @auther yanan.mu-ext
     * @date 2022-02-21 下午3:06
     */
    public static List<String> getDateList(String purchaseDate) {
        //同比
        String YOYTime = "";
        //环比
        String QOQTime = "";
        SimpleDateFormat df = new SimpleDateFormat(DATEFORMATDAY);//设置日期格式  HH:mm:ss
        //获取当前时间的同比和环比
        try {
            Date date = df.parse(purchaseDate);
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar1.setTime(date);
            calendar2.setTime(date);
            calendar1.add(Calendar.YEAR, -1);  // 在当前年基础上-1
            calendar2.add(Calendar.DAY_OF_MONTH, -1);  // 在当前日基础上-1
            SimpleDateFormat format = new SimpleDateFormat(DATEFORMATDAY);
            YOYTime = format.format(calendar1.getTime());
            QOQTime = format.format(calendar2.getTime());
        } catch (ParseException e) {
            log.error("时间日期转换异常： {}",e);
        }

        List<String> dates = new ArrayList<>();
        dates.add(purchaseDate);
        dates.add(YOYTime);
        dates.add(QOQTime);
        return dates;
    }

    /**
     * 按指定的格式，把Date转换成String 如date为null,返回null
     *
     * @param dateString   日期字符串
     * @return String
     */
    public static LocalDateTime dateStringToLocalDateTime(String dateString) {
        return LocalDateTime.parse(dateString, DateUtil.DTF_DATE_FORMAT_DATETIME);
    }

    /**
     * 按指定的格式，把Date转换成String 如date为null,返回null
     *
     * @param dateString   日期字符串
     * @return String
     */
    public static LocalDateTime dateStringToLocalDateTimeNotHaveHour(String dateString) {
        return LocalDateTime.parse(dateString, DateUtil.DTF_DATE_FORMAT_DATETIME_YEAR_MONTH_DAY);
    }

    /**
     * 按指定的格式，把Date转换成String 如date为null,返回null
     *
     * @param dateString   日期字符串
     * @return LocalDate
     */
    public static LocalDate dateStringToLocalDate(String dateString) {
        return LocalDate.parse(dateString, DateUtil.DTF_DATE_FORMAT_DATETIME_YEAR_MONTH_DAY);
    }


    public static void main(String[] args) {
        List<String> dateList = getDateList("2020-02-29");
        //  dateList.stream().forEach(System.out::println);

        List<String> betweenMonths = getBetweenMonths("2022-02-01 00:00:00", "2022-02-28 59:59:59");
        log.info(JSON.toJSONString(betweenMonths));

        DateListBean dateList1 = getDateList(2, "2022-02-01");
        log.info(JSON.toJSONString(dateList1));

        System.out.println(getFirstDayOfMonth("2020-02"));
        System.out.println(getLastDayOfMonth("2020-02"));

        DateBean dateBeanMonth = getDateBeanMonth("2020-02", "2020-03", 1);
        log.info(JSON.toJSONString(dateBeanMonth));

        DateBean dateBean = getDateBean("2020-02-01", "2020-03-01", 1);
        log.info(JSON.toJSONString(dateBean));

        System.out.println(getFirstDayOfMonth("2020-02"));
        System.out.println(getLastDayOfMonth("2020-02"));

    }
}
