package com.ikang.idata.common.utils;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description 拼音工具类
 * @date 2022/2/21 16:54
 */
public class PinyinUtil {
    /**
     * @param chinese 中文
     * @return 首字符
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/21 16:59
     */
    public static String getFirstUpperCase(String chinese) {
        return cn.hutool.extra.pinyin.PinyinUtil.getFirstLetter(chinese, ",").split(",")[0].toUpperCase();
    }

}
