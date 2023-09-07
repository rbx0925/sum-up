/*
 * Copyright (c) 2016 iKang Guobin Healthcare Group. All rights reserved.
 */
package com.ikang.idata.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 属性拷贝工具类
 *
 * @author <a href="mailto:binbin.guo@ikang.com">Guo Bin</a>
 * @version 2019年03月19日 20:38
 */
@Slf4j
public class BeanCopierUtil {
    private static Map<String, BeanCopier> BEAN_COPIERS = new HashMap<>();

    /**
     *
     * @description: 属性拷贝--属性名称、类型都相同，成功复制
     *
     * @param: [source, target]
     * @return: void
     * @auther: ikang
     * @date: 2019/3/19 20:53
     */
    public static void copy(Object source, Object target) {
        String key = genKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(source, target, null);
    }

    private static String genKey(Class<?> sourceClazz, Class<?> targetClazz) {
        return sourceClazz.getName() + ":" + targetClazz.getName();
    }


    /**
     * 将entityList转换成modelList
     * @param fromList
     * @param tClass
     * @param <F>
     * @param <T>
     * @return
     */
    public static<F,T> List<T> entityListToModelList(List<F> fromList, Class<T> tClass){
        if(fromList.isEmpty() || fromList == null){
            return null;
        }
        List<T> tList = new ArrayList<>();
        for(F f : fromList){
            T t = entityToModel(f, tClass);
            tList.add(t);
        }
        return tList;
    }

    public static<F,T> T entityToModel(F entity, Class<T> modelClass) {
        Object model = null;
        if (entity == null || modelClass ==null) {
            return null;
        }

        try {
            model = modelClass.newInstance();
        } catch (InstantiationException e) {
            log.error("entityToModel : 实例化异常", e);
        } catch (IllegalAccessException e) {
            log.error("entityToModel : 安全权限异常", e);
        }
        BeanUtils.copyProperties(entity, model);
        return (T)model;
    }
}
