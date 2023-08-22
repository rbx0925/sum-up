package com.ikang.idata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
//test
class SearchApplicationTest {

    private SearchApplication searchApplicationUnderTest;

    @BeforeEach
    void setUp() {
        searchApplicationUnderTest = new SearchApplication();
    }

    @Test
    void testRestTemplate() {
        // Setup
        // Run the test
        final RestTemplate result = searchApplicationUnderTest.restTemplate();

        // Verify the results
    }

    @Test
    void testMain() throws Exception {
        // Setup
        // Run the test
        SearchApplication.main(new String[]{"args"});

        // Verify the results
    }

    @Test
    void testMain_ThrowsUnknownHostException() {
        // Setup
        // Run the test
        assertThatThrownBy(() -> SearchApplication.main(new String[]{"args"})).isInstanceOf(UnknownHostException.class);
    }
}
