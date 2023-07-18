package com.redis.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

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

            //监控proKey，为其上锁
            jedis.watch(proKey);

            //获取到产品的库存
            String num = jedis.get(proKey);
            //判断库存是否充足
            if (Integer.parseInt(num)<=0){
                System.out.println("抢光了");
                return false;
            }

            //开启队列，将操作放到队列里，返回事务对象
            //接下来加入队列内的方法不再通过jedis调，通过multi去调
            //开启队队列必须在获取库存下方，队列里不应该包含不加到队列中的命令
            Transaction multi = jedis.multi();

            //库存充足，库存减一
            //jedis.decr(proKey);
            multi.decr(proKey);

            //将当前秒杀成功的用户添加到redis的list内
            //jedis.rpush(userKey,userId.toString());
            multi.rpush(userKey,userId.toString());

            //执行队列，可以解决超卖的问题
            multi.exec();

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
