package com.ikang.idata.search.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikang.idata.search.search.entity.User;
import com.ikang.idata.search.search.support.RedisCache;
import com.ikang.idata.search.search.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author rbx
 * @title
 * @Create 2023-07-13 14:05
 * @Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class redisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisCache redisCache;


    @Test
    public void one(){
        log.info("------开始RedisTemplate操作----");
        //定义字符串内容及存入缓存的key
        final String content="RedisTemplate实战字符串信息";
        final String key="redis:template:one:string";
        //Redis通用的操作组件
        ValueOperations valueOperations=redisTemplate.opsForValue();
        //将字符串信息写入缓存中
        log.info("写入缓存中的内容：{} ", content);
        valueOperations.set(key, content);
        //从缓存中读取内容
        Object result=valueOperations.get(key);
        log.info("读取出来的内容：{} ", result);
    }

    @Test
    public void two() throws Exception {
        log.info("------开始RedisTemplate操作----");
        //构造对象信息
        User user = new User(1, "debug", "阿修罗");
        //Redis通用的操作组件
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //将序列化后的信息写入缓存中
        final String key = "redis:template:two:object";
        final String content = objectMapper.writeValueAsString(user);
        valueOperations.set(key, content);
        log.info("写入缓存对象的信息：{} ", user);
        //从缓存中读取内容
        Object result = valueOperations.get(key);
        log.info("读取出来的内容：{} ", result);
        if (result != null){
            User resultUser = objectMapper.readValue(result.toString(), User.class);
            log.info("读取缓存内容并反序列化后的结果：{} ", resultUser);
        }
    }

    /**
     * 功能描述：添加字符串到redis
     */
    @Test
    public void add() {
        redisUtil.set("test", 1234);
    }

    /**
     * 功能描述：添加对象至redis
     */
    @Test
    public void addObject() {
        User user = User.builder()
                .id(1)
                .username("小萌")
                .info("test")
                .build();
        redisUtil.set(user.getId().toString(), user);
    }

    /**
     * 功能描述：添加对象集合至redis
     */
    @Test
    public void addObjects() {
        ArrayList<User> users = new ArrayList<>();
        User user = User.builder()
                .id(2)
                .username("小萌2")
                .info("test2")
                .build();
        users.add(user);
        redisUtil.set(user.getId().toString(),user,10);
        Object o = redisUtil.get("2");
        log.info("读取出来的内容：{} ", o);
    }

    /**
     * 功能描述：添加 hash-set
     */
    @Test
    public void addHashCache() {
        redisUtil.hset("hashKey", "key", "value");
    }

    /**
     * 功能描述：获取 hash-set
     */
    @Test
    public void getHashCache() {
        Object hget = redisUtil.hget("hashKey", "key");
        log.info("读取出来的内容：{} ", hget);
    }

    //以hash方式对String操作
    @Test
    public void test01(){
        HashOperations<String, Object, Object> forHash = redisTemplate.opsForHash();
        //以hash方式向Redis存储数据
        forHash.put("k2","name","王戎");
        forHash.put("k2","age","25");
        forHash.put("k2","sex","男");
        Map map = new HashMap();
        map.put("name","山涛");
        map.put("age","23");
        map.put("sex","男");
        forHash.putAll("k3",map);

        //通过hash获取指定的key下指定键的值
        Object o = forHash.get("k3", "name");
        log.info("读取出来的内容：{} ", o);

        //获取K3下所有的键
        Set<Object> k3 = forHash.keys("k3");
        log.info("读取出来的内容：{} ", k3);

        //获取k3下所有的值
        List<Object> k31 = forHash.values("k3");
        log.info("读取出来的内容：{} ", k31);

        //获取k3下所有键值对
        Map<Object, Object> k32 = forHash.entries("k3");
        log.info("读取出来的内容：{} ", k32);

        //删除k3下的指定键
        Long delete = forHash.delete("k3", "age");
        log.info("读取出来的内容：{} ", delete);

    }

    //对String的操作
    @Test
    public void contextLoads() {
        ValueOperations<String, String> forValue = redisTemplate.opsForValue();
        //Set<String> keys = redisTemplate.keys("k1");
        forValue.set("k1", "k2", Duration.ofSeconds(3000));
        String k1 = forValue.get("k1");
        System.out.println(k1);

        //相当于setnx ------如果存在该键则不存入，不存在则存入
        Boolean aBoolean = forValue.setIfAbsent("k1", "rbx", 30, TimeUnit.SECONDS);
        Boolean aBoolea = forValue.setIfAbsent("k11", "rbx", 30, TimeUnit.SECONDS);
        System.out.println(aBoolea);

        forValue.append("k11", "jjjj");
    }


}
