package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.springcloud.service.impl.PaymentServiceImpl
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    //使用@Resource注解代替@Autowired注解，可防止报红
    @Resource
    //@Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById( Long id){
        return paymentDao.getPaymentById(id);
    }
}
