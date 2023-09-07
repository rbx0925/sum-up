package com.ikang.idata.common.utils;

import cn.hutool.core.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ListUtil
 *
 * @author haigang.jia@ikang.com
 * @date 2019-06-25  上午 9:59
 */
public class ListUtil {

    /**
     * 将List按照指定长度len拆分为多个子List
     *
     * @param len  分组长度
     * @param list 目标List
     * @return java.util.List<java.util.List < T>>
     * @author haigang.jia@ikang.com
     * @date 2019-06-25 上午 10:00
     */
    public static <T> List<List<T>> groupList(int len, List<T> list) {
        List<List<T>> listGroup = new ArrayList<>();
        int listSize = list.size();
        for (int i = 0; i < list.size(); i += len) {
            if (i + len > listSize) {
                len = listSize - i;
            }
            List<T> newList = list.subList(i, i + len);
            listGroup.add(newList);
        }
        return listGroup;
    }

    /**
     * 从数组中随机获取target个不重复数据
     *
     * @param target 目标数
     * @param array  基数
     * @return java.util.List<T>
     * @author haigang.jia@ikang.com
     * @date 2019-06-28 上午 10:12
     */
    public static String[] randomArray(int target, String[] array) {
        assert null != array;
        if (target <= 0) {
            return new String[0];
        }
        if (target >= array.length) {
            return array;
        }
        int all = array.length;
        String[] targetArray = new String[target];
        for (int i = 0; i < targetArray.length; i++) {
            int index = RandomUtil.randomInt(all - i);
            targetArray[i] = array[index];
            String temp = array[index];
            array[index] = array[all - 1 - i];
            array[all - 1 - i] = temp;
        }
        return targetArray;
    }
}
