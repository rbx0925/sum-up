package com.ikang.idata.search.search.javaTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ActivityPageTest {

    private ActivityPage activityPageUnderTest;

    @BeforeEach
    void setUp() {
        activityPageUnderTest = new ActivityPage();
    }

    @Test
    void testEquals() {
        assertThat(activityPageUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(activityPageUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(activityPageUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(activityPageUnderTest.toString()).isEqualTo("result");
    }
}
