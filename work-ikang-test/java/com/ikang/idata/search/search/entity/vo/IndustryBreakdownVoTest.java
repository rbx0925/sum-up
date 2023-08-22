package com.ikang.idata.search.search.entity.vo;

import cn.hutool.core.lang.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class IndustryBreakdownVoTest {

    private IndustryBreakdownVo industryBreakdownVoUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        industryBreakdownVoUnderTest = new IndustryBreakdownVo();
        industryBreakdownVoUnderTest.pairs = Arrays.asList(new Pair<>("key", new IndustryBreakdownVo.AnnualInfo()));
    }

    @Test
    void testEquals() {
        assertThat(industryBreakdownVoUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(industryBreakdownVoUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(industryBreakdownVoUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(industryBreakdownVoUnderTest.toString()).isEqualTo("result");
    }
}
