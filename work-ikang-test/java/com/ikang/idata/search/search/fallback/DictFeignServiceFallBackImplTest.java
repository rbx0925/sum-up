package com.ikang.idata.search.search.fallback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DictFeignServiceFallBackImplTest {

    private DictFeignServiceFallBackImpl dictFeignServiceFallBackImplUnderTest;

    @BeforeEach
    void setUp() {
        dictFeignServiceFallBackImplUnderTest = new DictFeignServiceFallBackImpl();
    }

    @Test
    void testFindByTypeList() {
        assertThat(dictFeignServiceFallBackImplUnderTest.findByTypeList(Arrays.asList("value"))).isNull();
    }
}
