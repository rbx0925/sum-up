package com.ikang.idata.search.search.entity.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadTransmitDetailRecordVoTest {

    private ReadTransmitDetailRecordVo readTransmitDetailRecordVoUnderTest;

    @BeforeEach
    void setUp() {
        readTransmitDetailRecordVoUnderTest = new ReadTransmitDetailRecordVo();
    }

    @Test
    void testToString() {
        assertThat(readTransmitDetailRecordVoUnderTest.toString()).isEqualTo("result");
    }

    @Test
    void testEquals() {
        assertThat(readTransmitDetailRecordVoUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(readTransmitDetailRecordVoUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(readTransmitDetailRecordVoUnderTest.hashCode()).isEqualTo(0);
    }
}
