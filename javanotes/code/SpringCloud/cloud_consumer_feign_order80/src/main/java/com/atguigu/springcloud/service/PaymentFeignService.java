package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springcloud.service.PaymentFeignService
 */
//通过value属性指定要调用对微服务应用程序的名字，直接在接口里边开始远程调用
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    //该地址必须与Controller中的保持一致
    @GetMapping(value = "/payment/get/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/create")
    CommonResult create(@RequestBody Payment payment);

    //模拟超时
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
