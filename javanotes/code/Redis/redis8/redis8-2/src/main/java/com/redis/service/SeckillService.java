package com.redis.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @Description: TODD
 * @AllClassName: com.redis.service.SeckillService
 */
@Service
public class SeckillService {

    public Boolean seckill(Integer productId, Integer userId) {
        Jedis jedis = null;

        try {
            //根据redis信息创建Jedis对象
            jedis = new Jedis("39.106.35.112", 6379);

            //密码认证
            jedis.auth("abc123");

            //选择数据库
            jedis.select(0);

            //对redis进行操作
            String ping = jedis.ping();
            System.out.println(ping);

            //通过代码对redis进行操作
            //准备产品和用户的key值
            String proKey = "sk:"+productId+":phone";
            String userKey = "sk:"+productId+":user";
            //获取到产品的库存
            String num = jedis.get(proKey);
            //判断库存是否充足
            if (Integer.parseInt(num)<=0){
                System.out.println("抢光了");
                return false;
            }
            //库存充足，库存减一
            jedis.decr(proKey);
            //将当前秒杀成功的用户添加到redis的list内
            jedis.rpush(userKey,userId.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            jedis.close();
        }

        return false;


    }

}
