package com.atguigu.springcloud.service;

import com.atguigu.springcloud.fallback.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//value属性指定调用的微服务名⚠️
//fallback属性指定包含当前接口方法的降级类对象⚠️
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVICE",fallback = PaymentFallbackService.class)
//@FeignClient("CLOUD-HYSTRIX-PAYMENT-SERVICE")
public interface PaymentHystrixService {
    //与8001服务提供者一致
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    //与8001服务提供者一致
    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfo_Timeout(@PathVariable("id") Integer id);
}