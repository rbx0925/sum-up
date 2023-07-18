package com.redis.controller;

import com.redis.lua.SecKill_redisByScript;
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
    ) {
        //模拟用户id
        String userId = new Random().nextInt(5000) + "";
//        Boolean flag = seckillService.seckill(Integer.valueOf(productId),userId);

        Boolean flag = true;
        try {
            //使用lua脚本解决乐观锁有库存剩余的问题
            flag = SecKill_redisByScript.doSecKill(userId, productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag) {
            System.out.println("抢购成功");
        } else {
            System.out.println("抢购失败");
        }

        return "index";
    }

}
