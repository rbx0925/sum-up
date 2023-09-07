package com.ikang.idata.search.search.entity.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RegionalPerformanceDTOTest {

    private RegionalPerformanceDTO regionalPerformanceDTOUnderTest;

    @BeforeEach
    void setUp() {
        regionalPerformanceDTOUnderTest = new RegionalPerformanceDTO();
    }

    @Test
    void testConvertToMap() {
        // Setup
        // Run the test
        final Map<String, Object> result = regionalPerformanceDTOUnderTest.convertToMap();

        // Verify the results
    }

    @Test
    void testEquals() {
        assertThat(regionalPerformanceDTOUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(regionalPerformanceDTOUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(regionalPerformanceDTOUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(regionalPerformanceDTOUnderTest.toString()).isEqualTo("result");
    }
}
