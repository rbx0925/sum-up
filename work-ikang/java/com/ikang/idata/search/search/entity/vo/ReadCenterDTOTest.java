package com.ikang.idata.search.search.entity.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ReadCenterDTOTest {

    private ReadCenterDTO readCenterDTOUnderTest;

    @BeforeEach
    void setUp() {
        readCenterDTOUnderTest = new ReadCenterDTO();
    }

    @Test
    void testConvertToMap() {
        // Setup
        // Run the test
        final Map<String, Object> result = readCenterDTOUnderTest.convertToMap();

        // Verify the results
    }

    @Test
    void testEquals() {
        assertThat(readCenterDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(readCenterDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(readCenterDTOUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(readCenterDTOUnderTest.toString()).isEqualTo("result");
    }
}
