package com.ikang.idata.search.search.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

class RedisConfigTest {

    private RedisConfig redisConfigUnderTest;

    @BeforeEach
    void setUp() {
        redisConfigUnderTest = new RedisConfig();
    }

    @Test
    void testKeyGenerator() {
        // Setup
        // Run the test
        final KeyGenerator result = redisConfigUnderTest.keyGenerator();

        // Verify the results
    }

    @Test
    void testCacheManager() {
        // Setup
        final RedisConnectionFactory factory = null;

        // Run the test
        final CacheManager result = redisConfigUnderTest.cacheManager(factory);

        // Verify the results
    }

    @Test
    void testRedisTemplate() {
        // Setup
        final RedisConnectionFactory factory = null;

        // Run the test
        final RedisTemplate<String, Object> result = redisConfigUnderTest.redisTemplate(factory);

        // Verify the results
    }
}
