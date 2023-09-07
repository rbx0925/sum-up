package com.ikang.idata.search.search.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RunAbleDemoTest {

    private RunAbleDemo runAbleDemoUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        runAbleDemoUnderTest = new RunAbleDemo("name");
    }

    @Test
    void testRun() {
        // Setup
        // Run the test
        runAbleDemoUnderTest.run();

        // Verify the results
    }

    @Test
    void testStart() {
        // Setup
        // Run the test
        runAbleDemoUnderTest.start();

        // Verify the results
    }
}
