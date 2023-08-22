package com.ikang.idata.search.search.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ActivityTest {

    private Activity activityUnderTest;

    @BeforeEach
    void setUp() {
        activityUnderTest = new Activity();
    }

    @Test
    void testOfTest() {
        // Setup
        // Run the test
        activityUnderTest.ofTest();

        // Verify the results
    }

    @Test
    void testOfNullAbleTest() {
        // Setup
        // Run the test
        activityUnderTest.ofNullAbleTest();

        // Verify the results
    }

    @Test
    void testIsPresentTest() {
        // Setup
        // Run the test
        activityUnderTest.isPresentTest();

        // Verify the results
    }

    @Test
    void testGetTest() {
        // Setup
        // Run the test
        activityUnderTest.getTest();

        // Verify the results
    }

    @Test
    void testIfPresentTest() {
        // Setup
        // Run the test
        activityUnderTest.ifPresentTest();

        // Verify the results
    }

    @Test
    void testOrElseTest() {
        // Setup
        // Run the test
        activityUnderTest.orElseTest();

        // Verify the results
    }

    @Test
    void testOrElseGetTest() {
        // Setup
        // Run the test
        activityUnderTest.orElseGetTest();

        // Verify the results
    }

    @Test
    void testOrElseThrowTest() {
        // Setup
        // Run the test
        activityUnderTest.orElseThrowTest();

        // Verify the results
    }

    @Test
    void testToString() {
        assertThat(activityUnderTest.toString()).isEqualTo("result");
    }

    @Test
    void testEquals() {
        assertThat(activityUnderTest.equals("o")).isTrue();
    }

    @Test
    void testCanEqual() {
        assertThat(activityUnderTest.canEqual("other")).isTrue();
    }

    @Test
    void testHashCode() {
        assertThat(activityUnderTest.hashCode()).isEqualTo(0);
    }
}
