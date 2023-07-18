package com.atguigu.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//通过SpringCould原生注解@RefreshScope实现配置自动更新
//因为配置配到了云端，修改后需要实时更新
@RefreshScope
public class ConfigClientController{
    //对应nacos配置:nacos-config-client-dev.yaml
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}