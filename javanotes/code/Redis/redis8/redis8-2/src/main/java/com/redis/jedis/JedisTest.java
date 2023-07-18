package com.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;

/**
 * @Description: TODD
 * @AllClassName: com.redis.jedis.JedisTest
 */
public class JedisTest {
    public static void main(String[] args) {
        //根据redis信息创建Jedis对象
        Jedis jedis = new Jedis("39.106.35.112", 6379);

        //密码认证
        jedis.auth("abc123");

        //选择数据库
        jedis.select(7);

        //对redis进行操作
        String ping = jedis.ping();
        System.out.println(ping);

        //通过代码对redis进行操作
        jedis.hset("cat","name", "tom");
        String name = jedis.hget("cat", "name");
        System.out.println(name);

        //关闭连接
        jedis.close();

    }
}
