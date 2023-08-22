package com.ikang.idata.search.search.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.Arrays;
import java.util.List;

class WebConfigTest {

    private WebConfig webConfigUnderTest;

    @BeforeEach
    void setUp() {
        webConfigUnderTest = new WebConfig();
    }

    @Test
    void testConfigureMessageConverters() {
        // Setup
        final List<HttpMessageConverter<?>> converters = Arrays.asList();

        // Run the test
        webConfigUnderTest.configureMessageConverters(converters);

        // Verify the results
    }

    @Test
    void testAddResourceHandlers() {
        // Setup
        final ResourceHandlerRegistry registry = new ResourceHandlerRegistry(null, null);

        // Run the test
        webConfigUnderTest.addResourceHandlers(registry);

        // Verify the results
    }

    @Test
    void testWebServerFactoryCustomizer() {
        // Setup
        // Run the test
        final WebServerFactoryCustomizer<ConfigurableWebServerFactory> result = webConfigUnderTest.webServerFactoryCustomizer();

        // Verify the results
    }

    @Test
    void testFastJsonHttpMessageConverter() {
        // Setup
        // Run the test
        final FastJsonHttpMessageConverter result = webConfigUnderTest.fastJsonHttpMessageConverter();

        // Verify the results
    }
}
