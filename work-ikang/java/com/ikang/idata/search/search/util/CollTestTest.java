package com.ikang.idata.search.search.util;

import com.alibaba.csp.sentinel.node.metric.MetricNode;
import com.alibaba.csp.sentinel.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class CollTestTest {

    private CollTest collTestUnderTest;

    @BeforeEach
    void setUp() {
        collTestUnderTest = new CollTest();
    }

    @Test
    void testEmptyIfNull1() {
        assertThat(CollTest.emptyIfNull(new HashSet<>(Arrays.asList("value"))))
                .isEqualTo(new HashSet<>(Arrays.asList("value")));
        assertThat(CollTest.emptyIfNull(new HashSet<>(Arrays.asList("value")))).isEqualTo(Collections.emptySet());
    }

    @Test
    void testEmptyIfNull2() {
        assertThat(CollTest.emptyIfNull(Arrays.asList("value"))).isEqualTo(Arrays.asList("value"));
        assertThat(CollTest.emptyIfNull(Arrays.asList("value"))).isEqualTo(Collections.emptyList());
    }

    @Test
    void testUnion1() {
        assertThat(CollTest.union(Arrays.asList("value"), Arrays.asList("value"))).isEqualTo(Arrays.asList("value"));
        assertThat(CollTest.union(Arrays.asList("value"), Arrays.asList("value"))).isEqualTo(Collections.emptyList());
    }

    @Test
    void testUnion2() {
        assertThat(CollTest.union(Arrays.asList("value"), Arrays.asList("value"), Arrays.asList("value")))
                .isEqualTo(Arrays.asList("value"));
        assertThat(CollTest.union(Arrays.asList("value"), Arrays.asList("value"), Arrays.asList("value")))
                .isEqualTo(Collections.emptyList());
    }

    @Test
    void testUnionDistinct() {
        assertThat(CollTest.unionDistinct(Arrays.asList("value"), Arrays.asList("value"),
                Arrays.asList("value"))).isEqualTo(new HashSet<>(Arrays.asList("value")));
        assertThat(CollTest.unionDistinct(Arrays.asList("value"), Arrays.asList("value"),
                Arrays.asList("value"))).isEqualTo(Collections.emptySet());
    }

    @Test
    void testUnionAll() {
        assertThat(CollTest.unionAll(Arrays.asList("value"), Arrays.asList("value"), Arrays.asList("value")))
                .isEqualTo(Arrays.asList("value"));
        assertThat(CollTest.unionAll(Arrays.asList("value"), Arrays.asList("value"), Arrays.asList("value")))
                .isEqualTo(Collections.emptyList());
    }

    @Test
    void testIntersection1() {
        assertThat(CollTest.intersection(Arrays.asList("value"), Arrays.asList("value")))
                .isEqualTo(Arrays.asList("value"));
        assertThat(CollTest.intersection(Arrays.asList("value"), Arrays.asList("value")))
                .isEqualTo(Collections.emptyList());
    }

    @Test
    void testIntersection2() {
        assertThat(CollTest.intersection(Arrays.asList("value"), Arrays.asList("value"),
                Arrays.asList("value"))).isEqualTo(Arrays.asList("value"));
        assertThat(CollTest.intersection(Arrays.asList("value"), Arrays.asList("value"),
                Arrays.asList("value"))).isEqualTo(Collections.emptyList());
    }

    @Test
    void testIsEmpty() {
        assertThat(CollTest.isEmpty(Arrays.asList("value"))).isFalse();
    }

    @Test
    void testIsNotEmpty() {
        assertThat(CollTest.isNotEmpty(Arrays.asList("value"))).isFalse();
    }

    @Test
    void testCountMap() {
        // Setup
        final Map<String, Integer> expectedResult = new HashMap<>();

        // Run the test
        final Map<String, Integer> result = CollTest.countMap(Arrays.asList("value"));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testNewHashSet() {
        // Setup
        final HashSet<String> expectedResult = new HashSet<>(Arrays.asList("value"));

        // Run the test
        final HashSet<String> result = CollTest.newHashSet("ts");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testMetrics() {
        // Setup
        // Run the test
        final Map<Long, MetricNode> result = collTestUnderTest.metrics();

        // Verify the results
    }

    @Test
    void testRawMetricsInMin() {
        // Setup
        final Predicate<Long> timePredicate = null;

        // Run the test
        final List<MetricNode> result = collTestUnderTest.rawMetricsInMin(timePredicate);

        // Verify the results
    }
}
