package com.ikang.idata.search.search.util;

import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * @author rbx
 * @title
 * @Create 2023-07-28 11:17
 * @Description
 */
public class DateFormatConfig {
    private String pattern = "yyyy-MM-dd HH:mm:ss";
    public LocalDateTimeSerializer LocalDateTimeSerializer(){
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void Test(){
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance();
    }
}
