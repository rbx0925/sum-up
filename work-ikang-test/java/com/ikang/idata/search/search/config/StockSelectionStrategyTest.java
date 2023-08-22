package com.ikang.idata.search.search.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockSelectionStrategyTest {

    private StockSelectionStrategy stockSelectionStrategyUnderTest;

    @BeforeEach
    void setUp() {
        stockSelectionStrategyUnderTest = new StockSelectionStrategy();
    }

    @Test
    void testUpdate() {
        // Setup
        // Run the test
        stockSelectionStrategyUnderTest.update(0.0, 0);

        // Verify the results
    }
}
