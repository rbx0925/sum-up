package com.atguigu.springcloud.service;

public interface PaymentService {
    String paymentInfo_OK(Integer id);
    String payment_Timeout(Integer id);
    String paymentCircuitBreaker(Integer id);
}