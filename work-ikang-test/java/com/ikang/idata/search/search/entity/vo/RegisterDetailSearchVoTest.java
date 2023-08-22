package com.ikang.idata.search.search.entity.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RegisterDetailSearchVoTest {

    private RegisterDetailSearchVo registerDetailSearchVoUnderTest;

    @BeforeEach
    void setUp() {
        registerDetailSearchVoUnderTest = new RegisterDetailSearchVo();
    }

    @Test
    void testConvertImpalaParam() {
        // Setup
        // Run the test
        final Map<String, Object> result = registerDetailSearchVoUnderTest.convertImpalaParam();

        // Verify the results
    }

    @Test
    void testToString() {
        assertThat(registerDetailSearchVoUnderTest.toString()).isEqualTo("result");
    }

    @Test
    void testEquals() {
        assertThat(registerDetailSearchVoUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(registerDetailSearchVoUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(registerDetailSearchVoUnderTest.hashCode()).isEqualTo(0);
    }
}
