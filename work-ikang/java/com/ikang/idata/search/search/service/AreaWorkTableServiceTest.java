package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.vo.AreaWorkTableCheckVO;
import com.ikang.idata.common.entity.vo.AreaWorkTableFinanceVO;
import com.ikang.idata.common.entity.vo.AreaWorkTableRankingVO;
import com.ikang.idata.common.entity.vo.AreaWorkTableSignCheckVO;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class AreaWorkTableServiceTest {

    private AreaWorkTableService areaWorkTableServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        areaWorkTableServiceUnderTest = new AreaWorkTableService();
    }

    @Test
    void testInvokeAndGetResultByFindSignCheck() {
        // Setup
        final AreaWorkTableSignCheckVO expectedResult = new AreaWorkTableSignCheckVO();
        expectedResult.setIncome(new BigDecimal("0.00"));
        expectedResult.setSameTermIncome(new BigDecimal("0.00"));
        expectedResult.setIncomeRate(new BigDecimal("0.00"));
        expectedResult.setLocalCheckIncome(new BigDecimal("0.00"));
        expectedResult.setSelfIncomeRate(new BigDecimal("0.00"));
        expectedResult.setPackageReceivableAmount(new BigDecimal("0.00"));
        expectedResult.setSameTermPackageReceivableAmount(new BigDecimal("0.00"));
        expectedResult.setPackageReceivableAmountRate(new BigDecimal("0.00"));
        expectedResult.setAdditionalReceivableAmount(new BigDecimal("0.00"));
        expectedResult.setSameTermAdditionalReceivableAmount(new BigDecimal("0.00"));
        expectedResult.setAdditionalReceivableAmountRate(new BigDecimal("0.00"));
        expectedResult.setUnitPrice(new BigDecimal("0.00"));
        expectedResult.setSameTermUnitPrice(new BigDecimal("0.00"));
        expectedResult.setUnitPriceRate(new BigDecimal("0.00"));
        expectedResult.setPackageUnitPrice(new BigDecimal("0.00"));
        expectedResult.setSameTermPackageUnitPrice(new BigDecimal("0.00"));
        expectedResult.setPackageUnitPriceRate(new BigDecimal("0.00"));
        expectedResult.setTotalPackageDiscount(new BigDecimal("0.00"));
        expectedResult.setSameTermTotalPackageDiscount(new BigDecimal("0.00"));
        expectedResult.setTotalPackageDiscountRate(new BigDecimal("0.00"));
        expectedResult.setCheckPackageDiscount(new BigDecimal("0.00"));
        expectedResult.setSameTermCheckPackageDiscount(new BigDecimal("0.00"));
        expectedResult.setCheckPackageDiscountRate(new BigDecimal("0.00"));
        expectedResult.setWorkNos(new BigDecimal("0.00"));
        expectedResult.setSameTermWorkNos(new BigDecimal("0.00"));
        expectedResult.setWorkNosRate(new BigDecimal("0.00"));

        // Run the test
        final AreaWorkTableSignCheckVO result = areaWorkTableServiceUnderTest.invokeAndGetResultByFindSignCheck(
                "queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testInvokeAndGetResultByFindFinance() {
        // Setup
        final AreaWorkTableFinanceVO expectedResult = new AreaWorkTableFinanceVO();
        expectedResult.setTotalIncome(new BigDecimal("0.00"));
        expectedResult.setTotalTask(new BigDecimal("0.00"));
        expectedResult.setTotalTaskRate(new BigDecimal("0.00"));
        expectedResult.setSameTermTotalIncome(new BigDecimal("0.00"));
        expectedResult.setTotalIncomeRate(new BigDecimal("0.00"));
        expectedResult.setSignIncome(new BigDecimal("0.00"));
        expectedResult.setSignTask(new BigDecimal("0.00"));
        expectedResult.setSignTaskRate(new BigDecimal("0.00"));
        expectedResult.setSameTermSignIncome(new BigDecimal("0.00"));
        expectedResult.setSignIncomeRate(new BigDecimal("0.00"));

        // Run the test
        final AreaWorkTableFinanceVO result = areaWorkTableServiceUnderTest.invokeAndGetResultByFindFinance("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSetParamsByFinance() throws Exception {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        areaWorkTableServiceUnderTest.setParamsByFinance(searchVO, builder);

        // Verify the results
    }

    @Test
    void testSetParamsBySignCheck() throws Exception {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        areaWorkTableServiceUnderTest.setParamsBySignCheck(searchVO, builder);

        // Verify the results
    }

    @Test
    void testSetParamsByFindCheck() throws Exception {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        areaWorkTableServiceUnderTest.setParamsByFindCheck(searchVO, builder);

        // Verify the results
    }

    @Test
    void testInvokeAndGetResultByFindCheck() {
        // Setup
        final AreaWorkTableCheckVO expectedResult = new AreaWorkTableCheckVO();
        expectedResult.setCheckIncome(new BigDecimal("0.00"));
        expectedResult.setSameTermCheckIncome(new BigDecimal("0.00"));
        expectedResult.setCheckIncomeRate(new BigDecimal("0.00"));
        expectedResult.setSelfIncome(new BigDecimal("0.00"));
        expectedResult.setSelfIncomeRate(new BigDecimal("0.00"));
        expectedResult.setPackageIncome(new BigDecimal("0.00"));
        expectedResult.setSameTermPackageIncome(new BigDecimal("0.00"));
        expectedResult.setPackageIncomeRate(new BigDecimal("0.00"));
        expectedResult.setAdditionalIncome(new BigDecimal("0.00"));
        expectedResult.setSameTermAdditionalIncome(new BigDecimal("0.00"));
        expectedResult.setAdditionalIncomeRate(new BigDecimal("0.00"));
        expectedResult.setUnitPrice(new BigDecimal("0.00"));
        expectedResult.setSameTermUnitPrice(new BigDecimal("0.00"));
        expectedResult.setUnitPriceRate(new BigDecimal("0.00"));
        expectedResult.setPackageUnitPrice(new BigDecimal("0.00"));
        expectedResult.setSameTermPackageUnitPrice(new BigDecimal("0.00"));
        expectedResult.setPackageUnitPriceRate(new BigDecimal("0.00"));
        expectedResult.setWorkNos(new BigDecimal("0.00"));
        expectedResult.setSameTermWorkNos(new BigDecimal("0.00"));
        expectedResult.setWorkNosRate(new BigDecimal("0.00"));

        // Run the test
        final AreaWorkTableCheckVO result = areaWorkTableServiceUnderTest.invokeAndGetResultByFindCheck("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testAreaWorkTableRanking() {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

       // final AreaWorkTableRankingVO expectedResult = new AreaWorkTableRankingVO(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
                //0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L);

        // Run the test
        final AreaWorkTableRankingVO result = areaWorkTableServiceUnderTest.areaWorkTableRanking(searchVO);

        // Verify the results
       // assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testAreaWorkTableRankingBuildDSLAndQuery() {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final JSONObject expectedResult = new JSONObject(0, false);

        // Run the test
        final JSONObject result = areaWorkTableServiceUnderTest.areaWorkTableRankingBuildDSLAndQuery(searchVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
