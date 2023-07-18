package com.ikang.idata.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * ThrowableUtil
 *
 * @author <a href="mailto:haigang.jia@ikang.com">Jia haiGang</a>
 * @date 2019年11月28日
 */
public class ThrowableUtil {

    public static String toString(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
