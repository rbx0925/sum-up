package com.ikang.idata.search.search.support;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ImpalaCheckCacheKeyTest {

    private ImpalaCheckCacheKey impalaCheckCacheKeyUnderTest;

    @BeforeEach
    void setUp() {
        impalaCheckCacheKeyUnderTest = new ImpalaCheckCacheKey();
    }

    @Test
    void testGetPrefix() {
        assertThat(impalaCheckCacheKeyUnderTest.getPrefix()).isEqualTo("idata:search:bigdata:queryInterval");
    }
}
