package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    //配置远程调用的微服务的地址
    //public static final String PAYMENT_URL = "http://localhost:8001";
    //配置远程调用的微服务的地址，要写服务的名字CLOUD_PAYMENT_SERVICE
    //名字必须从Eureka注册中心进行http://localhost:7001/查看⚠️
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    //注入远程调用对象
    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create")
public CommonResult<Payment>   create(@RequestBody Payment payment){
        /**
         * 使用RestTemplate 远程调用支付模块接口
         * 第一个参数：请求地址
         * 第二个参数：响应类型
         */
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);  //写操作
    }

    @GetMapping("/consumer/payment/get/{id}")
public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        /**
         * 使用RestTemplate 远程调用支付模块接口
         * 第一个参数：请求地址
         * 第二个参数：响应类型
         */
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    //==> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin(){
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/", String.class);
        return result;
    }
}