package com.atguigu.springcloud;

import com.atguigu.myrule.MyRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

//name属性指定调用微服务的名字，从注册中心获取
//configuration属性指定了包含负载均衡配置类的类型
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyRuleConfig.class)
//注册中心客户端注解
@EnableEurekaClient
@SpringBootApplication
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}