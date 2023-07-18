package com.ikang.idata.common.utils;


import com.ikang.idata.common.enums.CodeEnum;

/**
 * Created by zhiyuan.xu@ikang.com
 * 2017-07-16 18:36
 */
public class EnumUtil {

    public static <T extends CodeEnum,R> T getByCode(R code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
