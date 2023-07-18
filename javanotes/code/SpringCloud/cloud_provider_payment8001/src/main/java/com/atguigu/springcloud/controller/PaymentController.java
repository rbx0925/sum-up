package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

//@RequestBody和@RestController注解
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;


    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    //接收的Json，要使用@RequestBody注解⚠️
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);
        if (result > 0) {
            //说明有数据，能查询成功
            return new CommonResult(200, "插入数据库成功" + serverPort, result);
        } else {
            //查询失败
            return new CommonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果：" + payment);
        if (payment != null) {
            //说明有数据，能查询成功
            return new CommonResult(200, "查询成功" + serverPort, payment);
        } else {
            //查询失败
            return new CommonResult(444, "没有对应记录，查询ID：" + id, null);
        }
    }

    //测试超时
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        //单位秒
        try { TimeUnit.SECONDS.sleep(3); }catch (Exception e) {e.printStackTrace();}
        System.out.println("serverPort = " + serverPort);
        return serverPort;
    }

    //测试断言
    @GetMapping(value = "/payment/lb/{id}")
    public String paymentLb(@PathVariable Long id) {
        return "断言" + serverPort;
    }

    //测试zipkin
    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "hi ,i'am paymentzipkin server，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}