package com.ikang.idata.search.search.service;

import cn.hutool.core.lang.Pair;
import com.ikang.idata.search.search.entity.vo.CustomerSatisfactionSearchVo;
import com.ikang.idata.search.search.entity.vo.MainCustomerSatisfactionVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class MainCustomerSatisfactionServiceTest {

    private MainCustomerSatisfactionService mainCustomerSatisfactionServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        mainCustomerSatisfactionServiceUnderTest = new MainCustomerSatisfactionService();
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final CustomerSatisfactionSearchVo vo = new CustomerSatisfactionSearchVo();
        vo.setBrmZone("brmZone");
        vo.setBrmDepartment("brmDepartment");
        vo.setBrmGroup("brmGroup");
        vo.setStartRegDate("startRegDate");
        vo.setEndRegDate("endRegDate");
        vo.setProjectid("projectid");
        vo.setBrmUserId("brmUserId");
        vo.setChiefZone("chiefZone");

        final MainCustomerSatisfactionVo expectedResult = new MainCustomerSatisfactionVo();
        expectedResult.setSatisfiedSmsReply(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        expectedResult.setCheckingSatisfaction(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));

        // Run the test
        final MainCustomerSatisfactionVo result = mainCustomerSatisfactionServiceUnderTest.find(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
