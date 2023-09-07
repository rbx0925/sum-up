package com.ikang.idata.search.search.entity.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaperFreeVoTest {

    private PaperFreeVo paperFreeVoUnderTest;

    @BeforeEach
    void setUp() {
        paperFreeVoUnderTest = new PaperFreeVo();
    }

    @Test
    void testToString() {
        assertThat(paperFreeVoUnderTest.toString()).isEqualTo("result");
    }

    @Test
    void testEquals() {
        assertThat(paperFreeVoUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(paperFreeVoUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(paperFreeVoUnderTest.hashCode()).isEqualTo(0);
    }
}
