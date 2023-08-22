package com.ikang.idata.search.search.support;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class CacheKeyTest {

    private CacheKey cacheKeyUnderTest;

    @BeforeEach
    void setUp() {
        cacheKeyUnderTest = new CacheKey("key", Duration.ofDays(0L));
    }

    @Test
    void testToString() {
        assertThat(cacheKeyUnderTest.toString()).isEqualTo("result");
    }
}
