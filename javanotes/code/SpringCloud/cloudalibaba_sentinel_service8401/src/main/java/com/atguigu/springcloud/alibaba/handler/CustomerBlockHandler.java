package com.atguigu.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {

    //要注意降级方法的返回值和形参列表要一致
    //方法必须是public static的
    
    public static String handleException(String p1, String p2,BlockException exception){
        return "包含降级方法的类中的第一个方法";
    }

    public static String handleException2(String p1, String p2,BlockException exception){
        return "包含降级方法的类中的第二个方法";
}
}
