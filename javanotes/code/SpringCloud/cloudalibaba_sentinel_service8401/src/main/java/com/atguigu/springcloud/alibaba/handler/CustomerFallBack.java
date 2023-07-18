package com.atguigu.springcloud.alibaba.handler;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springcloud.alibaba.handler.CustomerFallBack
 */
public class CustomerFallBack {
    //必须加static修饰，和原方法型参列表一致⚠️

    public static String fallBack1(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        return "fall back 1";
    }

    public static String fallBack2(@RequestParam(value = "p1",required = false) String p1,
                            @RequestParam(value = "p2",required = false) String p2) {
        return "fall back 2";
    }
}
