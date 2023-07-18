package com.redis.controller;

import com.redis.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

/**
 * @Description: TODD
 * @AllClassName: com.redis.controller.Seckill
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    SeckillService seckillService;

    @RequestMapping()
    public String seckill(
            @RequestParam String productId
    ){
        //模拟用户id
        Integer userId = new Random().nextInt(5000);
        Boolean flag = seckillService.seckill(Integer.valueOf(productId),userId);
        if (flag){
            System.out.println("抢购成功");
        }else {
            System.out.println("抢购失败");
        }

        return "index";
    }

}
