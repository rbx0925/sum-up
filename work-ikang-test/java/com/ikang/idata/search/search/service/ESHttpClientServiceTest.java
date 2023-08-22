package com.ikang.idata.search.search.service;

import cn.hutool.json.JSONArray;
import com.ikang.idata.common.exceptions.BusinessException;
import com.ikang.idata.search.search.common.EsReturnResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ESHttpClientServiceTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private ESHttpClientService esHttpClientServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        esHttpClientServiceUnderTest = new ESHttpClientService(mockRestTemplate);
    }

    @Test
    void testExchange() {
        // Setup
        when(mockRestTemplate.exchange(eq("url"), eq(HttpMethod.GET), eq(new HttpEntity<>(null)), eq(String.class),
                any(Object.class))).thenReturn(new ResponseEntity<>("body", HttpStatus.OK));

        // Run the test
        final String result = esHttpClientServiceUnderTest.exchange("url", HttpMethod.GET, String.class, "requestBody");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    void testExchange_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate.exchange(eq("url"), eq(HttpMethod.GET), eq(new HttpEntity<>(null)), eq(String.class),
                any(Object.class))).thenThrow(RestClientException.class);

        // Run the test
        assertThatThrownBy(() -> esHttpClientServiceUnderTest.exchange("url", HttpMethod.GET, String.class,
                "requestBody")).isInstanceOf(BusinessException.class);
    }

    @Test
    void testEsQueryFetchHits() {
        // Setup
        // Configure RestTemplate.exchange(...).
        final EsReturnResult esReturnResult = new EsReturnResult();
        esReturnResult.setCode("code");
        esReturnResult.setMessage("message");
        esReturnResult.setTotal(0);
        esReturnResult.setHits(new JSONArray("object", false));
        esReturnResult.setScroll("scroll");
        final ResponseEntity<EsReturnResult> esReturnResultResponseEntity = new ResponseEntity<>(esReturnResult,
                HttpStatus.OK);
        when(mockRestTemplate.exchange(eq("url"), eq(HttpMethod.POST), eq(new HttpEntity<>(null)),
                eq(EsReturnResult.class), any(Object.class))).thenReturn(esReturnResultResponseEntity);

        // Run the test
        final List<String> result = esHttpClientServiceUnderTest.esQueryFetchHits("url", String.class, "dsl");

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testEsQueryFetchHits_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate.exchange(eq("url"), eq(HttpMethod.POST), eq(new HttpEntity<>(null)),
                eq(EsReturnResult.class), any(Object.class))).thenThrow(RestClientException.class);

        // Run the test
        assertThatThrownBy(
                () -> esHttpClientServiceUnderTest.esQueryFetchHits("url", String.class, "dsl"))
                .isInstanceOf(BusinessException.class);
    }
}
