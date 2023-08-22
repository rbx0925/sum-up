package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.CiTy;
import com.ikang.idata.common.entity.TradeName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusinessServiceTest {

    @Mock
    private ConstantService mockConstantService;

    @InjectMocks
    private BusinessService businessServiceUnderTest;

    @Test
    void testFindTrade() {
        // Setup
        final List<TradeName> expectedResult = Arrays.asList(new TradeName("tradeName"));

        // Run the test
        final List<TradeName> result = businessServiceUnderTest.findTrade();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testShowEnum() {
        // Setup
        final Map<String, List<String>> expectedResult = new HashMap<>();
        when(mockConstantService.getSourceByCondition("enumUrl", "dsl"))
                .thenReturn(Arrays.asList(new JSONObject(0, false)));

        // Run the test
        final Map<String, List<String>> result = businessServiceUnderTest.showEnum();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testShowEnum_ConstantServiceReturnsNoItems() {
        // Setup
        final Map<String, List<String>> expectedResult = new HashMap<>();
        when(mockConstantService.getSourceByCondition("enumUrl", "dsl")).thenReturn(Collections.emptyList());

        // Run the test
        final Map<String, List<String>> result = businessServiceUnderTest.showEnum();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testShowCiTy() {
        // Setup
        final List<CiTy> expectedResult = Arrays.asList(new CiTy("city"));

        // Run the test
        final List<CiTy> result = businessServiceUnderTest.showCiTy("daQu", 0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testAssemblyConditions() throws Exception {
        assertThat(businessServiceUnderTest.assemblyConditions(new JSONObject(0, false))).isNull();
    }
}
