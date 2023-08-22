package com.ikang.idata.search.search.javaTest;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AggregationConditionTest {

    private AggregationCondition aggregationConditionUnderTest;

    @BeforeEach
    void setUp() {
        aggregationConditionUnderTest = new AggregationCondition();
    }

    @Test
    void testEquals() {
        assertThat(aggregationConditionUnderTest.equals("o")).isFalse();
    }

    @Test
    void testCanEqual() {
        assertThat(aggregationConditionUnderTest.canEqual("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(aggregationConditionUnderTest.hashCode()).isEqualTo(0);
    }

    @Test
    void testToString() {
        assertThat(aggregationConditionUnderTest.toString()).isEqualTo("result");
    }

    @Test
    void testParse() {
        // Setup
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        final List<AggregationCondition> expectedResult = Arrays.asList(aggregationCondition);

        // Run the test
        final List<AggregationCondition> result = AggregationCondition.parse("condition");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
