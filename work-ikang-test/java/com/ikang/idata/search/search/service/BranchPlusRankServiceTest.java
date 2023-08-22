package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.search.search.entity.dto.PlusRankDTO;
import com.ikang.idata.search.search.entity.vo.PlusRankVo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class BranchPlusRankServiceTest {

    private BranchPlusRankService branchPlusRankServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        branchPlusRankServiceUnderTest = new BranchPlusRankService();
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final PlusRankVo vo = new PlusRankVo();
        vo.setHospid("hospid");
        vo.setAreaid("areaid");
        vo.setCheckStartTime("checkStartTime");
        vo.setCheckEndTime("checkEndTime");

        final PlusRankDTO expectedResult = new PlusRankDTO();
        final PlusRankDTO.RankTable rankTable = new PlusRankDTO.RankTable();
        rankTable.setTotalNumber(new BigDecimal("0.00"));
        rankTable.setTotalAmount(new BigDecimal("0.00"));
        rankTable.setCheckItemName("checkItemName");
        rankTable.setDepartmentName("departmentName");
        rankTable.setPerformanceAttribution("performanceAttribution");
        expectedResult.setNumber(Arrays.asList(rankTable));
        final PlusRankDTO.RankTable rankTable1 = new PlusRankDTO.RankTable();
        rankTable1.setTotalNumber(new BigDecimal("0.00"));
        rankTable1.setTotalAmount(new BigDecimal("0.00"));
        rankTable1.setCheckItemName("checkItemName");
        rankTable1.setDepartmentName("departmentName");
        rankTable1.setPerformanceAttribution("performanceAttribution");
        expectedResult.setAmount(Arrays.asList(rankTable1));
        final PlusRankDTO.RankTable rankTable2 = new PlusRankDTO.RankTable();
        rankTable2.setTotalNumber(new BigDecimal("0.00"));
        rankTable2.setTotalAmount(new BigDecimal("0.00"));
        rankTable2.setCheckItemName("checkItemName");
        rankTable2.setDepartmentName("departmentName");
        rankTable2.setPerformanceAttribution("performanceAttribution");
        expectedResult.setDepartment(Arrays.asList(rankTable2));
        final PlusRankDTO.RankTable rankTable3 = new PlusRankDTO.RankTable();
        rankTable3.setTotalNumber(new BigDecimal("0.00"));
        rankTable3.setTotalAmount(new BigDecimal("0.00"));
        rankTable3.setCheckItemName("checkItemName");
        rankTable3.setDepartmentName("departmentName");
        rankTable3.setPerformanceAttribution("performanceAttribution");
        expectedResult.setPerformanceAttribution(Arrays.asList(rankTable3));

        // Run the test
        final PlusRankDTO result = branchPlusRankServiceUnderTest.find(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testParse() {
        // Setup
        final JSONObject object = new JSONObject(0, false);
        final PlusRankDTO expectedResult = new PlusRankDTO();
        final PlusRankDTO.RankTable rankTable = new PlusRankDTO.RankTable();
        rankTable.setTotalNumber(new BigDecimal("0.00"));
        rankTable.setTotalAmount(new BigDecimal("0.00"));
        rankTable.setCheckItemName("checkItemName");
        rankTable.setDepartmentName("departmentName");
        rankTable.setPerformanceAttribution("performanceAttribution");
        expectedResult.setNumber(Arrays.asList(rankTable));
        final PlusRankDTO.RankTable rankTable1 = new PlusRankDTO.RankTable();
        rankTable1.setTotalNumber(new BigDecimal("0.00"));
        rankTable1.setTotalAmount(new BigDecimal("0.00"));
        rankTable1.setCheckItemName("checkItemName");
        rankTable1.setDepartmentName("departmentName");
        rankTable1.setPerformanceAttribution("performanceAttribution");
        expectedResult.setAmount(Arrays.asList(rankTable1));
        final PlusRankDTO.RankTable rankTable2 = new PlusRankDTO.RankTable();
        rankTable2.setTotalNumber(new BigDecimal("0.00"));
        rankTable2.setTotalAmount(new BigDecimal("0.00"));
        rankTable2.setCheckItemName("checkItemName");
        rankTable2.setDepartmentName("departmentName");
        rankTable2.setPerformanceAttribution("performanceAttribution");
        expectedResult.setDepartment(Arrays.asList(rankTable2));
        final PlusRankDTO.RankTable rankTable3 = new PlusRankDTO.RankTable();
        rankTable3.setTotalNumber(new BigDecimal("0.00"));
        rankTable3.setTotalAmount(new BigDecimal("0.00"));
        rankTable3.setCheckItemName("checkItemName");
        rankTable3.setDepartmentName("departmentName");
        rankTable3.setPerformanceAttribution("performanceAttribution");
        expectedResult.setPerformanceAttribution(Arrays.asList(rankTable3));

        // Run the test
        final PlusRankDTO result = branchPlusRankServiceUnderTest.parse(object);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildAggTop10() {
        // Setup
        // Run the test
        final AggregationBuilder result = branchPlusRankServiceUnderTest.buildAggTop10("fields", "order");

        // Verify the results
    }

    @Test
    void testBuildQuery() {
        // Setup
        final PlusRankVo vo = new PlusRankVo();
        vo.setHospid("hospid");
        vo.setAreaid("areaid");
        vo.setCheckStartTime("checkStartTime");
        vo.setCheckEndTime("checkEndTime");

        // Run the test
        final QueryBuilder result = branchPlusRankServiceUnderTest.buildQuery(vo);

        // Verify the results
    }
}
