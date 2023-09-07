package com.ikang.idata.common.utils;


import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by sofical on 2020/7/16.
 */
public class ClassUtils {
    /**
     * to map.
     * @param clazz
     * @return
     */
    public static Map beanToMap(Object clazz) {
        Map<String, Object> map = new HashMap<>();
        try {
            Field[] fields = clazz.getClass().getDeclaredFields();
            for(Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(clazz));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 类对象复制.
     * @param clazz 源类对象.
     * @param targetClass 目标类对象.
     * @param <T> T.
     * @return T.
     */
    public static <T> T copy(Object clazz, Class<T> targetClass) {
        String json = JSON.toJSONString(clazz);
        return JSON.parseObject(json, targetClass);
    }

    /**
     * 列表类对象复制.
     * @param source 源.
     * @param targetClass  目标类对象.
     * @param <T> T.
     * @return List<T>.
     */
    public static <T> List<T> copy(List<?> source, Class<T> targetClass) {
        List<T> newList = new ArrayList<T>();
        for (Object obj : source) {
            T newObj = copy(obj, targetClass);
            newList.add(newObj);
        }
        return newList;
    }

    /**
     * class copy.
     * @param clazz
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T copyV2(Object clazz, Class<T> targetClass) {
        try {
            T toObj = targetClass.newInstance();
            Field[] toFields = toObj.getClass().getDeclaredFields();

            for (Field toField : toFields) {
                Field[] fields = clazz.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.getName().equals(toField.getName())) {
                        toField.setAccessible(true);
                        //如有异常，参考：https://blog.csdn.net/lx19860203/article/details/41308885/进行修改
                        toField.set(toObj, field.get(clazz));
                    }
                }
            }
            return toObj;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Map to class bean.
     * @param map map.
     * @param targetClass target class factory.
     * @param <T> T.
     * @return class bean.
     */
    public static <T> T mapToClass(Map map, Class<T> targetClass) {
        try {
            T toObj = targetClass.newInstance();
            Field[] toFields = toObj.getClass().getDeclaredFields();

            for (Field toField : toFields) {

                if (map.containsKey(toField.getName())) {
                    toField.setAccessible(true);
                    String type = map.get(toField.getName()).getClass().getName();
                    String toType = toField.getType().getName();
                    Object realValue = map.get(toField.getName());
                    if ("java.util.Date".equals(toType) && "java.lang.Long".equals(type)) {
                        realValue = new Date(Long.valueOf(realValue.toString()));
                    }
                    if ("java.lang.Double".equals(toType)) {
                        realValue = Double.valueOf(realValue.toString());
                    }
                    toField.set(toObj, realValue);
                }
            }
            return toObj;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断实例对象是否存在列.
     * @param clazz 实例对象
     * @param column 列名.
     * @return 是否存在结果.
     */
    public static Boolean containColumn(Object clazz, String column) {
        Field[] fields = clazz.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(column)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 按列名获取实例对象值.
     * @param clazz 实例对象.
     * @param column 列名.
     * @param <T> T.
     * @return 对象值.
     */
    public static  <T> T getColumn(Object clazz, String column, Class<T> tClass) {
        Field[] fields = clazz.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(column)) {
                try {
                    // 数字类型值的特殊处理
                    Object value = field.get(clazz);

                    String tClassName = tClass.getName();

                    String valueClassName = value.getClass().getName();
                    if (tClassName.equals(value.getClass().getName())) {
                        return (T) field.get(clazz);
                    }

                    // 短整型
                    if (valueClassName.toLowerCase().contains("short")) {
                        Short thisValue = (Short) value;
                        return formatValueFromInteger(thisValue.intValue(), tClassName);
                    }

                    // 整型
                    if (valueClassName.toLowerCase().contains("integer") || valueClassName.toLowerCase().contains("int")) {
                        Integer thisValue = (Integer) value;
                        return formatValueFromInteger(thisValue, tClassName);
                    }

                    // 浮点型.
                    if (valueClassName.toLowerCase().contains("float")) {
                        Float thisValue = (Float) value;
                        return formatValueFromFloat(thisValue, tClassName);
                    }

                    // 双精度
                    if (valueClassName.toLowerCase().contains("double")) {
                        Double thisValue = (Double) value;
                        return formatValueFromDouble(thisValue, tClassName);
                    }

                    return (T) value;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 从整型数据源中获取想要的数据类型.
     * @param value 数据源.
     * @param tClassName 目标类型.
     * @param <T> T.
     * @return 获取的值.
     */
    private static <T> T formatValueFromInteger(Integer value, String tClassName) {
        tClassName = tClassName.toLowerCase();
        if (tClassName.contains("string")) {
            return (T) value.toString();
        }
        if (tClassName.contains("double")) {
            return (T) Double.valueOf(value.toString());
        }
        if (tClassName.contains("float")) {
            return (T) Float.valueOf(value.toString());
        }
        if (tClassName.contains("bigdecimal")) {
            return (T) new BigDecimal(value.toString());
        }
        return (T) value;
    }

    /**
     * 从浮点类型数据源中获取想要的数据类型值.
     * @param value 数据源.
     * @param tClassName 目标类型.
     * @param <T> T.
     * @return 获取到的值.
     */
    private static <T> T formatValueFromFloat(Float value, String tClassName) {
        tClassName = tClassName.toLowerCase();
        if (tClassName.contains("string")) {
            return (T) value.toString();
        }
        if (tClassName.contains("double")) {
            return (T) Double.valueOf(value.toString());
        }
        if (tClassName.contains("integer")) {
            return (T) Integer.valueOf(value.toString());
        }
        if (tClassName.contains("bigdecimal")) {
            return (T) new BigDecimal(value.toString());
        }
        return (T) value;
    }

    /**
     * 从双精度类型数据源中获取想要的数据类型值.
     * @param value 数据源.
     * @param tClassName 目标类型.
     * @param <T> T.
     * @return 获取到的值.
     */
    private static <T> T formatValueFromDouble(Double value, String tClassName) {
        tClassName = tClassName.toLowerCase();
        if (tClassName.contains("string")) {
            return (T) value.toString();
        }
        if (tClassName.contains("float")) {
            return (T) Float.valueOf(value.toString());
        }
        if (tClassName.contains("integer")) {
            return (T) Integer.valueOf(value.toString());
        }
        if (tClassName.contains("bigdecimal")) {
            return (T) new BigDecimal(value.toString());
        }
        return (T) value;
    }
}
