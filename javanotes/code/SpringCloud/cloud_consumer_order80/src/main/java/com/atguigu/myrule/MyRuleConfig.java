package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @Description: TODD
 * @AllClassName: com.atguigu.myrule.MyRuleConfig
 */
@SpringBootConfiguration
public class MyRuleConfig {

    @Bean
    public IRule getRule(){
        return new RandomRule();
    }
}
