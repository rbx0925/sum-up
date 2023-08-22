package com.ikang.idata.search.search.util;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HttpClientTest {

    @Test
    void testEsRequest() {
        // Setup
        final JSONObject expectedResult = new JSONObject(0, false);

        // Run the test
        final JSONObject result = HttpClient.esRequest("url", "context");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testEsRequestAgg() {
        // Setup
        final JSONObject expectedResult = new JSONObject(0, false);

        // Run the test
        final JSONObject result = HttpClient.esRequestAgg("url", "context");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
