package com.ikang.idata.search.search.fallback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReturnDataFeignServiceFallBackImplTest {

    private ReturnDataFeignServiceFallBackImpl returnDataFeignServiceFallBackImplUnderTest;

    @BeforeEach
    void setUp() {
        returnDataFeignServiceFallBackImplUnderTest = new ReturnDataFeignServiceFallBackImpl();
    }

    @Test
    void testSelectAllByUserIDAndIndexAndTableAndResourceId() {
        assertThat(returnDataFeignServiceFallBackImplUnderTest.selectAllByUserIDAndIndexAndTableAndResourceId(0L,
                "tableName", 0L)).isNull();
    }

    @Test
    void testListByReturnDataIndex() {
        assertThat(returnDataFeignServiceFallBackImplUnderTest.listByReturnDataIndex("returnDataIndex")).isNull();
    }
}
