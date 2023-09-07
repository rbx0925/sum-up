package com.ikang.idata.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * MapUtil
 *
 * @author haigang.jia@ikang.com
 * @date 2019-06-14  下午 1:57
 */
@Slf4j
public class MapUtil {


    /**
     * JavaBean转HashMap
     * 用于灵活控制JavaBean的属性字段
     *
     * @param obj JavaBean
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author haigang.jia@ikang.com
     * @date 2019-06-14  下午 1:57
     */
    public static Map<String, Object> obj2Map(Object obj) {
        if (null == obj) {
            return null;
        }
        Class klass = obj.getClass();
        Field[] fields = klass.getDeclaredFields();
        Map<String, Object> map = new HashMap<>(fields.length,1);
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (IllegalAccessException e) {
           log.error("",e);
        }
        // 去除序列化字段
        map.remove("serialVersionUID");
        return map;
    }
}
