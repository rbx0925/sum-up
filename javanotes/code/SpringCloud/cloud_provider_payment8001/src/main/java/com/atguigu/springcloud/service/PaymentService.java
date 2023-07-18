package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springcloud.service.PaymentService
 */
public interface PaymentService {
    /**
     * 写入数据
     */
    int create(Payment payment);

    /**
     * 读取数据
     */
    public Payment getPaymentById(Long id);
}
