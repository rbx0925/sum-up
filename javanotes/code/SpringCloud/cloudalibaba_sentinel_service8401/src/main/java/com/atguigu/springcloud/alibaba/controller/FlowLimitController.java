package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.alibaba.handler.CustomerBlockHandler;
import com.atguigu.springcloud.alibaba.handler.CustomerFallBack;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController{
    @GetMapping("/testA")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }


    /**
     * 关于@SentinelResource注解
     * value属性：指定流量限制的资源名
     * blockHandler属性：指定违反规则时调用的兜底方法名
     * blockHandlerClass属性：指定包含违法规则时调用的降级方法的类的类型
     * fallback属性：指定出异常时调用的兜底方法名
     * fallbackClass属性：指定出异常时调用的兜底方法的类的类型
     * 要注意降级方法的返回值和形参列表要一致⚠️
     */
    @GetMapping("/testHotKey")
    @SentinelResource(
            value = "testHotKey",
            blockHandler = "handleException2",
            blockHandlerClass = CustomerBlockHandler.class,
            fallback = "fallBack1",
            fallbackClass = CustomerFallBack.class
    )
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        //int age = 10/0;
        return "------testHotKey";
    }


}