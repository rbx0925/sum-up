package com.ikang.idata.search.search.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @date: 2022/2/18 14:19
 */
public class ReflectUtils {

    /**
     * 根据 服务名称 ，方法名 反射调用  spring bean 中的 方法
     *
     * @param serviceName 服务名
     * @param methodName  方法名
     * @param params      参数
     * @return 执行结果
     * @throws Exception 异常
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/18 14:20
     */
    public static Object springInvokeMethod(String serviceName, String methodName, Object... params) throws Exception {
        Object service = ContextUtil.getBean(serviceName);
        return springInvokeMethod(service, methodName, params);
    }

    /**
     * @param serviceClz bean的class
     * @param methodName 方法名称
     * @param params     参数名称
     * @param <T>        返回类型
     * @return 返回结果
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/18 15:11
     */
    public static <T> T springInvokeMethod(Class<?> serviceClz, String methodName, Object... params) {
        Object bean = ContextUtil.getBean(serviceClz);
        return springInvokeMethod(bean, methodName, params);
    }


    /**
     * @param service    服务
     * @param methodName 方法名称
     * @param params     参数名称
     * @param <T>        返回类型
     * @return 返回结果
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/18 15:11
     */
    public static <T> T springInvokeMethod(Object service, String methodName, Object... params) {
        // 找到方法
        Method method;
        if (Objects.isNull(params)) {
            method = ReflectionUtils.findMethod(service.getClass(), methodName);
            // 执行方法
            assert method != null;
            return (T) ReflectionUtils.invokeMethod(method, service);
        } else {
            method = ReflectionUtils.findMethod(service.getClass(), methodName, Stream.of(params).map(Object::getClass).toArray(Class[]::new));
            assert method != null;
            return (T) ReflectionUtils.invokeMethod(method, service, params);
        }
    }

}