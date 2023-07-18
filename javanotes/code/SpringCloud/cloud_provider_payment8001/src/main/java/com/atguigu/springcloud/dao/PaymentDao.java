package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

//@Component和@Repository注解加不加都可以，主要作用是开发时其他地方使用不报红线
//@Component or @Repository

//为了创建代理对象，可以在当前接口上添加Mapper注解或在启动类上添加扫描包注解
//mybatis提供的，等价：@MapperScan("com.atguigu.springcloud.dao")
@Mapper
public interface PaymentDao {
    /**
     * 添加
     */
    int create(Payment payment);

    /**
     * 根据id查询
     */
    Payment getPaymentById(@Param("id") Long id);
}