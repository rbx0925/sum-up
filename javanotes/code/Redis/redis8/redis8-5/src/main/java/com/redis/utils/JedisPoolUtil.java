package com.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static volatile JedisPool jedisPool = null;

	private JedisPoolUtil() {
	}

	public static JedisPool getJedisPoolInstance() {
		if (null == jedisPool) {
			synchronized (JedisPoolUtil.class) {
				if (null == jedisPool) {
					JedisPoolConfig poolConfig = new JedisPoolConfig();
					//最大连接数, 默认8个
					poolConfig.setMaxTotal(200);
					//最大空闲连接数,默认8个
					poolConfig.setMaxIdle(32);
					//获取连接时的最大等待毫秒数,如果超时就抛异常
					poolConfig.setMaxWaitMillis(100*1000);
					//连接超时时是否阻塞，false时报异常,ture阻塞直到超时, 默认true
					poolConfig.setBlockWhenExhausted(true);

					//测试ping pong
					poolConfig.setTestOnBorrow(true);

					//传入Redis数据库信息，包括ip、端口、超时时间、密码
					jedisPool = new JedisPool(poolConfig, "39.106.35.112", 6379, 60000, "abc123");
				}
			}
		}
		return jedisPool;
	}

	public static void release(JedisPool jedisPool, Jedis jedis) {
		if (null != jedis) {
			jedisPool.returnResource(jedis);
		}
	}

}
