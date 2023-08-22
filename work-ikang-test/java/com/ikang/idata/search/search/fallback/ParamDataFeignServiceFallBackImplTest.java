package com.ikang.idata.search.search.fallback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParamDataFeignServiceFallBackImplTest {

    private ParamDataFeignServiceFallBackImpl paramDataFeignServiceFallBackImplUnderTest;

    @BeforeEach
    void setUp() {
        paramDataFeignServiceFallBackImplUnderTest = new ParamDataFeignServiceFallBackImpl();
    }

    @Test
    void testSelectParamDataByUserIdAndResourceId() {
        assertThat(paramDataFeignServiceFallBackImplUnderTest.selectParamDataByUserIdAndResourceId(0L, 0L)).isNull();
    }

    @Test
    void testSelectParamDataListByIndex() {
        assertThat(paramDataFeignServiceFallBackImplUnderTest.selectParamDataListByIndex("index")).isNull();
    }

    @Test
    void testQueryPageButton() {
        assertThat(paramDataFeignServiceFallBackImplUnderTest.queryPageButton(0L, 0L)).isNull();
    }
}
