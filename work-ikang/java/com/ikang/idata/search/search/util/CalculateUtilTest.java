package com.ikang.idata.search.search.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CalculateUtilTest {

    @Test
    void testDiv1() {
        assertThat(CalculateUtil.div("v1", "v1")).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testDiv2() {
        assertThat(CalculateUtil.div(new BigDecimal("0.00"), new BigDecimal("0.00"))).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testDiv3() {
        assertThat(CalculateUtil.div(new BigDecimal("0.00"), new BigDecimal("0.00"), 0))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testDiv4() {
        assertThat(CalculateUtil.div("v1", "v1", 0)).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testCalculateGrowthRate4() {
        assertThat(CalculateUtil.calculateGrowthRate4(new BigDecimal("0.00"), new BigDecimal("0.00")))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testRound1() {
        assertThat(CalculateUtil.round("numStr", 0)).isEqualTo("result");
    }

    @Test
    void testRound2() {
        assertThat(CalculateUtil.round(new BigDecimal("0.00"), 0)).isEqualTo(new BigDecimal("0.00"));
    }
}
