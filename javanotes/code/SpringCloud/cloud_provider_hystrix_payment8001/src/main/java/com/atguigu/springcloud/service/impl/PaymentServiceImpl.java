package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    //成功
    public String paymentInfo_OK(Integer id){
        //随便return的数据，用于测试
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id：  "+id+"\t"+"哈哈哈"  ;
    }

    //失败
    /**
     * 超时降级演示
     * 注解@HystrixCommand：⚠️
     * fallbackMethod：设置降级方法
     * commandProperties：设置降级的其他参数
     */
    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler",commandProperties = {
            /**
             * 注解@HystrixProperty：
             * value：配置多少毫秒内不会降级，正常处理业务
             */
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String payment_Timeout(Integer id){
        //设置数学异常，进行模拟异常测试，不会等待，立刻执行兜底方法⚠️
        //int a = 2/0;

        int timeNumber = 3;
        //延时操作
        try { TimeUnit.SECONDS.sleep(timeNumber); }catch (Exception e) {e.printStackTrace();}
        //随便return的数据，用于测试
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+id+"\t"+"呜呜呜"+" 耗时(秒)"+timeNumber;
    }

    /**
     * 降级兜底方法，上面方法出问题,我来处理，返回一个出错信息，降级方法与业务方法的参数类型、个数、返回值要保持一致⚠️
     * 触发条件：当违反配置的降级规则（比如注解设置了5秒却睡了6秒）和出现异常时都会触发降级
     */
    public String payment_TimeoutHandler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+" payment_TimeoutHandler,系统繁忙,请稍后再试\t o(╥﹏╥)o ";
    }

    //服务熔断
    //10秒内10个请求，错误率大于等于60%就会触发熔断器，然后5秒后会放一个请求尝试恢复
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            //当在配置时间窗口内达到此数量，打开断路，默认20个
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            //断路多久以后开始尝试是否恢复，默认5s，如果恢复，就关了断路器
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            //出错百分比阈值，当达到此阈值后，开始短路。默认50%
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        //hutool.cn工具包
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    //降级兜底方法
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }
}