package com.ikang.idata.search.search.fallback;

import com.ikang.idata.common.entity.OperateLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class LogServiceFallbackImplTest {

    private LogServiceFallbackImpl logServiceFallbackImplUnderTest;

    @BeforeEach
    void setUp() {
        logServiceFallbackImplUnderTest = new LogServiceFallbackImpl();
    }

    @Test
    void testSave() {
        assertThat(logServiceFallbackImplUnderTest.save(new OperateLog())).isNull();
    }

    @Test
    void testFindByTypeList() {
        assertThat(logServiceFallbackImplUnderTest.findByTypeList(Arrays.asList("value"))).isNull();
    }
}
