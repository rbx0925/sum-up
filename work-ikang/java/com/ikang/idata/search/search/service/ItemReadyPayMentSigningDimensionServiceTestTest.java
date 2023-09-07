package com.ikang.idata.search.search.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ItemReadyPayMentSigningDimensionServiceTestTest {

    private ItemReadyPayMentSigningDimensionServiceTest itemReadyPayMentSigningDimensionServiceTestUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        itemReadyPayMentSigningDimensionServiceTestUnderTest = new ItemReadyPayMentSigningDimensionServiceTest();
    }

    @Test
    void testSetUp() throws Exception {
        // Setup
        // Run the test
        itemReadyPayMentSigningDimensionServiceTestUnderTest.setUp();

        // Verify the results
    }

    @Test
    void testSetUp_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> itemReadyPayMentSigningDimensionServiceTestUnderTest.setUp())
                .isInstanceOf(Exception.class);
    }

    @Test
    void testTestAssemblyConditions() throws Exception {
        // Setup
        // Run the test
        itemReadyPayMentSigningDimensionServiceTestUnderTest.testAssemblyConditions();

        // Verify the results
    }

    @Test
    void testTestAssemblyConditions_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(
                () -> itemReadyPayMentSigningDimensionServiceTestUnderTest.testAssemblyConditions())
                .isInstanceOf(Exception.class);
    }

    @Test
    void testTestFind() throws Exception {
        // Setup
        // Run the test
        itemReadyPayMentSigningDimensionServiceTestUnderTest.testFind();

        // Verify the results
    }

    @Test
    void testTestFind_ThrowsException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> itemReadyPayMentSigningDimensionServiceTestUnderTest.testFind())
                .isInstanceOf(Exception.class);
    }
}
