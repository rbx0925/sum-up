package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.ManagerWorkbenchOperates;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ManagerWorkbenchManagerServiceTest {

    private ManagerWorkbenchManagerService managerWorkbenchManagerServiceUnderTest;

    @Before
    public void setUp() {
        managerWorkbenchManagerServiceUnderTest = new ManagerWorkbenchManagerService();
    }

    @Test
    public void testFindMainSignInspection() {
        // Setup
        final WorkTableSearchVo searchVo = new WorkTableSearchVo();
        searchVo.setBrmZone("brmZone");
        searchVo.setBrmDepartment("brmDepartment");
        searchVo.setBrmGroup("brmGroup");
        searchVo.setStartSignDate("startSignDate");
        searchVo.setEndSignDate("endSignDate");
        searchVo.setBrmUserId("brmUserId");
        searchVo.setChiefZone("chiefZone");

        final ManagerWorkbenchOperates expectedResult = new ManagerWorkbenchOperates();
        expectedResult.setIncome(new BigDecimal("0.00"));
        expectedResult.setLocalCheckIncome(new BigDecimal("0.00"));
        expectedResult.setSelfIncomeRate(new BigDecimal("0.00"));
        expectedResult.setLastYearIncome(new BigDecimal("0.00"));
        expectedResult.setThridPriceAmount(new BigDecimal("0.00"));
        expectedResult.setAdditionalReceivablePersionalAmount(new BigDecimal("0.00"));
        expectedResult.setAdditionalReceivableEnterpriseAmount(new BigDecimal("0.00"));
        expectedResult.setMasterSignedGrowthRate(new BigDecimal("0.00"));
        expectedResult.setPackageReceivableAmount(new BigDecimal("0.00"));
        expectedResult.setLastYearPackageReceivableAmount(new BigDecimal("0.00"));

        // Run the test
        final ManagerWorkbenchOperates result = managerWorkbenchManagerServiceUnderTest.findMainSignInspection(searchVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = managerWorkbenchManagerServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
