package com.ikang.idata.search.search.common;

import com.ikang.idata.common.exceptions.BusinessException;
import com.ikang.idata.common.utils.Result;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.DecodeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;

class GlobalExceptionAdviceTest {

    private GlobalExceptionAdvice globalExceptionAdviceUnderTest;

    @BeforeEach
    void setUp() {
        globalExceptionAdviceUnderTest = new GlobalExceptionAdvice();
    }

    @Test
    void testValidationErrorHandler() {
        // Setup
//        final MethodArgumentNotValidException ex = new MethodArgumentNotValidException(new MethodParameter(null, 0),
//                null);

        // Run the test
//        final Result result = globalExceptionAdviceUnderTest.validationErrorHandler(ex);

        // Verify the results
    }

    @Test
    void testValidationBindExceptionErrorHandler() {
        // Setup
        final BindException ex = new BindException("target", "objectName");

        // Run the test
        final Result result = globalExceptionAdviceUnderTest.validationBindExceptionErrorHandler(ex);

        // Verify the results
    }

    @Test
    void testBusinessExceptionHandler1() {
        // Setup
        final DecodeException ex = new DecodeException(0, "message",
                Request.create(Request.HttpMethod.GET, "url", new HashMap<>(), Request.Body.create("data"),
                        new RequestTemplate()));

        // Run the test
        final Result result = globalExceptionAdviceUnderTest.businessExceptionHandler(ex);

        // Verify the results
    }

    @Test
    void testBusinessExceptionHandler2() {
        // Setup
        final BusinessException ex = new BusinessException(0, "message", new Exception("message"));

        // Run the test
        final Result result = globalExceptionAdviceUnderTest.businessExceptionHandler(ex);

        // Verify the results
    }

    @Test
    void testFeignExceptionHandler() {
        // Setup
        final FeignException ex = FeignException.errorStatus("methodKey", Response.builder().build());

        // Run the test
        final Result result = globalExceptionAdviceUnderTest.feignExceptionHandler(ex);

        // Verify the results
    }

    @Test
    void testExceptionHandler() {
        // Setup
        // Run the test
        final Result result = globalExceptionAdviceUnderTest.exceptionHandler(new Exception("message"));

        // Verify the results
    }

    @Test
    void testNoHandler() {
        // Setup
        final NoHandlerFoundException e = new NoHandlerFoundException("httpMethod", "requestURL",
                new HttpHeaders(null));

        // Run the test
        final Result result = globalExceptionAdviceUnderTest.noHandler(e);

        // Verify the results
    }
}
