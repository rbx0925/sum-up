package com.ikang.idata.search.search.entity.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TenderSearchVoTest {

    private TenderSearchVo tenderSearchVoUnderTest;

    @BeforeEach
    void setUp() {
        tenderSearchVoUnderTest = new TenderSearchVo();
    }

    @Test
    void testEquals() {
        assertThat(tenderSearchVoUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(tenderSearchVoUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(tenderSearchVoUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(tenderSearchVoUnderTest.toString()).isEqualTo("result");
    }
}
