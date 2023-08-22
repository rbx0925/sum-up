package com.ikang.idata.search.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BigDecimalTestTest {

    private BigDecimalTest bigDecimalTestUnderTest;

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
    void testTestDiv1() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testDiv1();

        // Verify the results
    }

    @Test
    void testTestDiv2() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testDiv2();

        // Verify the results
    }

    @Test
    void testTestDiv3() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testDiv3();

        // Verify the results
    }

    @Test
    void testTestDiv4() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testDiv4();

        // Verify the results
    }

    @Test
    void testTestCalculateGrowthRate4() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testCalculateGrowthRate4();

        // Verify the results
    }

    @Test
    void testTestRound1() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testRound1();

        // Verify the results
    }

    @Test
    void testTestRound2() {
        // Setup
        // Run the test
        bigDecimalTestUnderTest.testRound2();

        // Verify the results
    }

    @Test
    void testEquals() {
        assertThat(bigDecimalTestUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(bigDecimalTestUnderTest.canEqual("other")).isFalse();
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
