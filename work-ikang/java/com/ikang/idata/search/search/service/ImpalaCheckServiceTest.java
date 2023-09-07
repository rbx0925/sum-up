package com.ikang.idata.search.search.service;

import com.ikang.idata.search.search.support.CacheKey;
import com.ikang.idata.search.search.support.RedisCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ImpalaCheckServiceTest {

    @Mock
    private RedisCache mockRedisCache;

    private ImpalaCheckService impalaCheckServiceUnderTest;

    @Before
    public void setUp() {
        impalaCheckServiceUnderTest = new ImpalaCheckService(mockRedisCache);
    }

    @Test
    public void testCheckInterval() throws Exception {
        // Setup
        when(mockRedisCache.exists(any(CacheKey.class))).thenReturn(false);

        // Run the test
        impalaCheckServiceUnderTest.checkInterval();

        // Verify the results
        verify(mockRedisCache).set(any(CacheKey.class), eq("value"));
    }

    @Test
    public void testCheckInterval_RedisCacheExistsReturnsNull() throws Exception {
        // Setup
        when(mockRedisCache.exists(any(CacheKey.class))).thenReturn(null);

        // Run the test
        impalaCheckServiceUnderTest.checkInterval();

        // Verify the results
        verify(mockRedisCache).set(any(CacheKey.class), eq("value"));
    }

    @Test
    public void testCheckInterval_ThrowsException() {
        // Setup
        when(mockRedisCache.exists(any(CacheKey.class))).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> impalaCheckServiceUnderTest.checkInterval()).isInstanceOf(Exception.class);
        verify(mockRedisCache).set(any(CacheKey.class), eq("value"));
    }
}
