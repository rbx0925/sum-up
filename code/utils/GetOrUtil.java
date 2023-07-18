package com.ikang.idata.common.utils;

/**
 * GetOrUtil
 *
 * @author haigang.jia@ikang.com
 * @date 2019-06-14  下午 1:59
 */
public class GetOrUtil {

    /**
     * 如果返回null则返回or
     *
     * @param value 目标值
     * @param or    替换值
     * @return T
     * @author haigang.jia@ikang.com
     * @date 2019-07-08 下午 3:09
     */
    public static <T> T getOr(T value, T or) {
        return null == value ? or : value;
    }

    /**
     * Integer 类型的空比较替换
     * 当目标值为null时返回替换值，
     * 否则判断目标值是否超过限定值，超过时返回限定值，否则返回目标值
     *
     * @param value 目标值
     * @param or    替换值
     * @param limit 限定值
     * @return java.lang.Integer
     * @author haigang.jia@ikang.com
     * @date 2019-06-14  下午 1:59
     */
    public static Integer getOr(Integer value, Integer or, Integer limit) {
        Integer res = getOr(value, or);
        return res > limit ? limit : res;
    }
}
