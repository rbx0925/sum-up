package com.atguigu.springcloud.config;

import org.springframework.boot.SpringBootConfiguration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
public class ApplicationContextConfig {

    //使用Ribbon在客户端实现负载均衡时必须添加该注解
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}