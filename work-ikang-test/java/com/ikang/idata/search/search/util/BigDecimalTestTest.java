package com.ikang.idata.search.search.util;

import com.ikang.idata.search.search.BigDecimalTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


class BigDecimalTestTest {


    public BigDecimalTest bigDecimalTestUnderTest;

    @BeforeEach
    void setUp() {
        bigDecimalTestUnderTest = new BigDecimalTest();
    }

    @Test
    void testBigDecimalTest1() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.BigDecimalTest();

        // Verify the results
    }

    @Test
    void testStringTest() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.StringTest();

        // Verify the results
    }

    @Test
    void testA() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.a();

        // Verify the results
    }

    @Test
    void testATest() throws Exception {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.aTest();

        // Verify the results
    }

    @Test
    void testB() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.b();

        // Verify the results
    }

    @Test
    void testBTest() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.bTest();

        // Verify the results
    }

    @Test
    void testTestNegativeNumber() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testNegativeNumber();

        // Verify the results
    }

    @Test
    void testTestToString() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testToString();

        // Verify the results
    }

    @Test
    void testTestHashBasedTable() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testHashBasedTable();

        // Verify the results
    }

    @Test
    void testTestString() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testString();

        // Verify the results
    }

    @Test
    void testTestCompareTo() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testCompareTo();

        // Verify the results
    }

    @Test
    void testTestParseNumber() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testParseNumber();

        // Verify the results
    }

    @Test
    void testTestNegativeNumber1() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testNegativeNumber1();

        // Verify the results
    }

    @Test
    void testTestOn() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testOn();

        // Verify the results
    }

    @Test
    void testDiv1() {
        assertThat(CalculateUtil.div("1.999E+5", "1.999"))
                .isEqualTo(new BigDecimal("100000.0000000000"));
    }

    @Test
    void testDiv2() {
        assertThat(CalculateUtil.div(new BigDecimal("0.00"), new BigDecimal("0.00")))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testDiv3() {
        assertThat(CalculateUtil.div(new BigDecimal("0.00"), new BigDecimal("0.00"), 0))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testDiv4() {
        assertThat(CalculateUtil.div("v1", "v1", 0))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testCalculateGrowthRate4() {
        assertThat(CalculateUtil.calculateGrowthRate4(new BigDecimal("0.00"), new BigDecimal("0.00")))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testRound1() {
        assertThat(CalculateUtil.round("numStr", 0))
                .isEqualTo("result");
    }

    @Test
    void testRound2() {
        assertThat(CalculateUtil.round(new BigDecimal("0.00"), 0))
                .isEqualTo(new BigDecimal("0.00"));
    }


    @Test
    void testEquals() {
        assertThat(bigDecimalTestUnderTest.equals("o")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(bigDecimalTestUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(bigDecimalTestUnderTest.toString()).isEqualTo("BigDecimalTest()");
    }

    @Test
    void testCut() {
        assertThat(BigDecimalTest.cut(new int[]{0}, 0)).isEqualTo(0);
    }

    @Test
    void testMain() {
        // Setup
        // Run the test
        BigDecimalTest.main(new String[]{"args"});

        // Verify the results
    }
}

