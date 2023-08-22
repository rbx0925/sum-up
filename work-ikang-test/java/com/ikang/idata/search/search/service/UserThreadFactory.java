package com.ikang.idata.search.search.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/7/18
 */

@Slf4j
public class UserThreadFactory implements ThreadFactory {


    private final String namePrefix;

    private  final AtomicInteger nextId = new AtomicInteger();

    UserThreadFactory(String whatFeaturOfGroup){
        namePrefix ="From UserThreadFactory's" + whatFeaturOfGroup + "-worker-" ;

    }


    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement() ;
//        Thread  thread = new Thread(null, task, name , 0, false);
//        System.out.println(thread.getName());
//        return thread;
        return  null;
    }

    private static final ThreadLocal<DataFormat> df = new ThreadLocal<DataFormat>(){
        @Override
        protected  DataFormat initialValue(){
            // return new SimpleDataFormat("yyyy-MM-dd");
            return null;
        }
    };



    /**
     * 两个对象值的对比（objA为对比）
     * @param objA
     * @param objB
     * @return
     */
    public static List<String> compareValue(Object objA, Object objB) {
        // 只要比较有一个为空，就返回Null 不做比较
        if(objA==null || objB==null) {
            return null;
        }

        //当前类差异集合
        List<String> differenceAttrs = new ArrayList<String>();
        try {
            Class<?> clazzA = objA.getClass();
            Class<?> clazzB = objB.getClass();
            Method[] methods = clazzA.getDeclaredMethods();
            Object resultA = null;
            Object resultB = null;
            Method methodB = null;
            for(Method method:methods) {
                if(method.getName().startsWith("get")) {
                    methodB = clazzB.getMethod(method.getName(), null);
                    resultB = methodB.invoke(objB, null);
                    resultA = method.invoke(objA, null);
                    if(resultA==null&&resultB==null) {
                        continue;
                    }

                    if(resultA==null&&resultB!=null) {
                        differenceAttrs.add(getSubString(method));
                    }
                    else if(resultA!=null&&resultB==null) {
                        differenceAttrs.add(getSubString(method));
                    }else {
                        if(!(resultA instanceof List)) {
                            if(!resultA.equals(resultB)) {
                                differenceAttrs.add(getSubString(method));
                            }
                        }
                    }

                }
            }
        }catch(Exception e) {
            log.error("",e);
        }
        return differenceAttrs;
    }

    private static String getSubString(Method method) {
        String nameTrim = method.getName().substring(3);
        nameTrim = nameTrim.substring(0,1).toLowerCase()+nameTrim.substring(1);
        return nameTrim;
    }

    /**
     * 根据对象和属性，来得到属性的值
     * @param obj  对象
     * @param fieldName 属性名称
     * @return
     */
    public static String getFieldValue(Object obj,String fieldName){
        if(StringUtils.isEmpty(fieldName)) {
            return "";
        }
        fieldName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
        Class<?> clazzA = obj.getClass();
        String result = null;
        try {
            Method method = clazzA.getMethod(fieldName, null);
            result = (String)method.invoke(obj, null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getMessage());
        }
        if(StringUtils.isEmpty(result)) {
            return "";
        }
        return result;
    }
}
