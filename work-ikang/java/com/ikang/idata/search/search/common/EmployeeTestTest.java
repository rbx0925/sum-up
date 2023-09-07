package com.ikang.idata.search.search.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeTestTest {

    private EmployeeTest employeeTestUnderTest;

    @BeforeEach
    void setUp() {
        employeeTestUnderTest = new EmployeeTest("name", 0, 0, "address", new BigDecimal("0.00"));
    }

    @Test
    void testToString() {
        assertThat(employeeTestUnderTest.toString()).isEqualTo("result");
    }

    @Test
    void testEquals() {
        assertThat(employeeTestUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(employeeTestUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(employeeTestUnderTest.hashCode()).isEqualTo(0);
    }
}
