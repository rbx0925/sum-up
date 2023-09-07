package com.ikang.idata.search.search.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import org.elasticsearch.common.io.stream.InputStreamStreamInput;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.*;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.search.SearchException;
import org.elasticsearch.search.SearchModule;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.elasticsearch.common.xcontent.DeprecationHandler.THROW_UNSUPPORTED_OPERATION;

/**
 * @author <a href="mailto:minxin.fan-ext@ikang.com">minxin.fan</a>
 * @version 1.0
 * @description: TODO
 * @date 2023/3/30 9:26
 */
public class FileUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final ThreadLocal<Map<String, DateFormat>> dateFormatThreadLocal = new ThreadLocal<Map<String, DateFormat>>();
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    // ---------------------- format parse ----------------------
    private static Logger logger1 = LoggerFactory.getLogger(DateUtil.class);


    /*public static void appendFileLine(String fileName, String content) {

        // file
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                return;
            }
        }

        // content
        if (content == null) {
            content = "";
        }
        content += "\r\n";

        // append file content
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, true);
            fos.write(content.getBytes("utf-8"));
            fos.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

    }

    public static List<String> loadFileLines(String fileName){

        List<String> result = new ArrayList<>();

        // valid log file
        File file = new File(fileName);
        if (!file.exists()) {
            return result;
        }

        // read file
        StringBuffer logContentBuffer = new StringBuffer();
        int toLineNum = 0;
        LineNumberReader reader = null;
        try {
            //reader = new LineNumberReader(new FileReader(logFile));
            reader = new LineNumberReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String line = null;
            while ((line = reader.readLine())!=null) {
                if (line!=null && line.trim().length()>0) {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return result;
    }*/

    /**
     * delete recursively
     *
     * @param root
     * @return
     */
    public static boolean deleteRecursively(File root) {
        if (root != null && root.exists()) {
            if (root.isDirectory()) {
                File[] children = root.listFiles();
                if (children != null) {
                    for (File child : children) {
                        deleteRecursively(child);
                    }
                }
            }
            return root.delete();
        }
        return false;
    }

    public static void deleteFile(String fileName) {
        // file
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void writeFileContent(File file, byte[] data) {

        // file
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }

        // append file content
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data);
            fos.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

    }

    public static byte[] readFileContent(File file) {
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(filecontent);
            in.close();

            return filecontent;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    private static DateFormat getDateFormat(String pattern) {
        if (pattern == null || pattern.trim().length() == 0) {
            throw new IllegalArgumentException("pattern cannot be empty.");
        }

        Map<String, DateFormat> dateFormatMap = dateFormatThreadLocal.get();
        if (dateFormatMap != null && dateFormatMap.containsKey(pattern)) {
            return dateFormatMap.get(pattern);
        }

        synchronized (dateFormatThreadLocal) {
            if (dateFormatMap == null) {
                dateFormatMap = new HashMap<String, DateFormat>();
            }
            dateFormatMap.put(pattern, new SimpleDateFormat(pattern));
            dateFormatThreadLocal.set(dateFormatMap);
        }

        return dateFormatMap.get(pattern);
    }

    /**
     * format datetime. like "yyyy-MM-dd"
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDate(Date date) {
        return format(date, DATE_FORMAT);
    }

    /**
     * format date. like "yyyy-MM-dd HH:mm:ss"
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDateTime(Date date) {
        return format(date, DATETIME_FORMAT);
    }

    /**
     * format date
     *
     * @param date
     * @param patten
     * @return
     * @throws ParseException
     */
    public static String format(Date date, String patten) {
        return getDateFormat(patten).format(date);
    }

    /**
     * parse date string, like "yyyy-MM-dd HH:mm:s"
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String dateString) {
        return parse(dateString, DATE_FORMAT);
    }

    /**
     * parse datetime string, like "yyyy-MM-dd HH:mm:ss"
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date parseDateTime(String dateString) {
        return parse(dateString, DATETIME_FORMAT);
    }

    /**
     * parse date
     *
     * @param dateString
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateString, String pattern) {
        try {
            Date date = getDateFormat(pattern).parse(dateString);
            return date;
        } catch (Exception e) {
            logger1.warn("parse date error, dateString = {}, pattern={}; errorMsg = {}", dateString, pattern, e.getMessage());
            return null;
        }
    }


    // ---------------------- add date ----------------------

    public static Date addYears(final Date date, final int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date addMonths(final Date date, final int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addHours(final Date date, final int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    private static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            return null;
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

}
