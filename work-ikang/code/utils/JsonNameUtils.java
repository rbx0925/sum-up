package com.ikang.idata.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * JsonNameutils.
 *
 * @author zj.
 *         Created on 2019/9/27 0027.
 */
@Slf4j
public class JsonNameUtils {

    /**
     * 转换为下划线.
     * @param camelCaseName to change string.
     * @return result.
     */
    public static String underscoreName(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        if (camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());
            for (int i = 1; i < camelCaseName.length(); i++) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    /**
     * 转换为驼峰.
     * @param underscoreName to change string.
     * @return result.
     */
    public static String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * object json encode.
     * @param object object.
     * @return result.
     */
    public static String JsonEncode(Object object) {
        return JsonEncode(object, true);
    }

    /**
     * object json encode.
     * @param object object.
     * @param toUnderscore is underscore.
     * @return result.
     */
    public static String JsonEncode(Object object, Boolean toUnderscore) {
        Map<String, Object> map = new HashMap<>();
        try {
            Boolean isMap = false;
            Method[] methods = object.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if ("values".equals(method.getName())) {
                    isMap = true;
                    break;
                }
            }

            if (isMap) {
                Map<String, Object> source = (Map) object;
                for (Map.Entry<String, Object> item : source.entrySet()) {
                    map.put(toUnderscore ? underscoreName(item.getKey()) : item.getKey(), item.getValue());
                }
            } else {
                Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    map.put(toUnderscore ? underscoreName(field.getName()) : field.getName(), field.get(object));
                }
            }
        } catch (IllegalAccessException e) {
            log.info(e.getMessage(), e);
        }
        return JSONObject.toJSONString(map);
    }
}
