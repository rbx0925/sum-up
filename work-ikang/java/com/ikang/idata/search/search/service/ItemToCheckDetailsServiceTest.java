package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.vo.CheckinVo;
import com.ikang.idata.search.search.common.PageResult;
import com.ikang.idata.search.search.entity.dto.ItemToCheckDetailsDTO;
import com.ikang.idata.search.search.entity.vo.CheckinAddPageVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.support.ValueType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemToCheckDetailsServiceTest {

    private ItemToCheckDetailsService itemToCheckDetailsServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        itemToCheckDetailsServiceUnderTest = new ItemToCheckDetailsService();
    }

    @Test
    void testFindAggByItemNo() {
        // Setup
        final CheckinAddPageVo vo = new CheckinAddPageVo();
        vo.setBrmUserId("brmUserId");
        vo.setChiefZone("chiefZone");
        vo.setProjectZoneType("projectZoneType");
        vo.setPageNum(0);
        vo.setPageSize(0);

        final PageResult<ItemToCheckDetailsDTO> expectedResult = new PageResult<>();
        expectedResult.setResult(Arrays.asList());
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setTotal(0);
        expectedResult.setTotalStr("totalStr");

        // Run the test
        final PageResult<ItemToCheckDetailsDTO> result = itemToCheckDetailsServiceUnderTest.findAggByItemNo(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testParsePayCardSignObj() {
        // Setup
        final JSONObject payCardSignObj = new JSONObject(0, false);
        final ItemToCheckDetailsDTO itemToCheckDetailsDTO = new ItemToCheckDetailsDTO();
        itemToCheckDetailsDTO.setProjectNumber("projectNumber");
        itemToCheckDetailsDTO.setProjectName("projectName");
        itemToCheckDetailsDTO.setProjectStatus("projectStatus");
        itemToCheckDetailsDTO.setExaminationCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setProjectAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setActivatedCardsCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setActivateCardAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setActivatedCardsRate(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setNotBookedCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setNotBookedAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setNotArrivedCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setNotArrivedAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setCheckInCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setCheckInAmount(new BigDecimal("0.00"));
        final List<ItemToCheckDetailsDTO> dtos = Arrays.asList(itemToCheckDetailsDTO);
        final ItemToCheckDetailsDTO itemToCheckDetailsDTO1 = new ItemToCheckDetailsDTO();
        itemToCheckDetailsDTO1.setProjectNumber("projectNumber");
        itemToCheckDetailsDTO1.setProjectName("projectName");
        itemToCheckDetailsDTO1.setProjectStatus("projectStatus");
        itemToCheckDetailsDTO1.setExaminationCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO1.setProjectAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO1.setActivatedCardsCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO1.setActivateCardAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO1.setActivatedCardsRate(new BigDecimal("0.00"));
        itemToCheckDetailsDTO1.setNotBookedCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO1.setNotBookedAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO1.setNotArrivedCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO1.setNotArrivedAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO1.setCheckInCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO1.setCheckInAmount(new BigDecimal("0.00"));
        final List<ItemToCheckDetailsDTO> expectedResult = Arrays.asList(itemToCheckDetailsDTO1);

        // Run the test
        final List<ItemToCheckDetailsDTO> result = itemToCheckDetailsServiceUnderTest.parsePayCardSignObj(
                payCardSignObj, dtos);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildAggregation() {
        // Setup
        final TermsAggregationBuilder expectedResult = new TermsAggregationBuilder("name", ValueType.STRING);

        // Run the test
        final TermsAggregationBuilder result = itemToCheckDetailsServiceUnderTest.buildAggregation();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildPayCardSignQuery() {
        // Setup
        final CheckinVo vo = new CheckinVo();
        vo.setBrmUserId("brmUserId");
        vo.setChiefZone("chiefZone");
        vo.setProjectZoneType("projectZoneType");
        vo.setBrmZone("brmZone");
        vo.setBrmDepartment("brmDepartment");
        vo.setBrmGroup("brmGroup");
        vo.setStartSignDate("startSignDate");
        vo.setEndSignDate("endSignDate");
        vo.setAreaZone("areaZone");
        vo.setName("name");
        vo.setProjectType("projectType");
        vo.setStartDate("startDate");
        vo.setEndDate("endDate");
        vo.setProjectExecuteStartDate("projectExecuteStartDate");
        vo.setProjectExecuteEndDate("projectExecuteEndDate");
        vo.setProjectFinishStartDate("projectFinishStartDate");
        vo.setProjectFinishEndDate("projectFinishEndDate");

        // Run the test
        final QueryBuilder result = itemToCheckDetailsServiceUnderTest.buildPayCardSignQuery(vo);

        // Verify the results
    }

    @Test
    void testParse() {
        // Setup
        final JSONObject object = new JSONObject(0, false);
        final ItemToCheckDetailsDTO itemToCheckDetailsDTO = new ItemToCheckDetailsDTO();
        itemToCheckDetailsDTO.setProjectNumber("projectNumber");
        itemToCheckDetailsDTO.setProjectName("projectName");
        itemToCheckDetailsDTO.setProjectStatus("projectStatus");
        itemToCheckDetailsDTO.setExaminationCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setProjectAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setActivatedCardsCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setActivateCardAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setActivatedCardsRate(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setNotBookedCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setNotBookedAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setNotArrivedCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setNotArrivedAmount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setCheckInCount(new BigDecimal("0.00"));
        itemToCheckDetailsDTO.setCheckInAmount(new BigDecimal("0.00"));
        final List<ItemToCheckDetailsDTO> expectedResult = Arrays.asList(itemToCheckDetailsDTO);

        // Run the test
        final List<ItemToCheckDetailsDTO> result = itemToCheckDetailsServiceUnderTest.parse(object);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildAgg() {
        // Setup
        // Run the test
        final AggregationBuilder result = itemToCheckDetailsServiceUnderTest.buildAgg();

        // Verify the results
    }

    @Test
    void testConstructStopflagAggQuery() throws Exception {
        // Setup
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = itemToCheckDetailsServiceUnderTest.ConstructStopflagAggQuery();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildQuery() {
        // Setup
        final CheckinVo vo = new CheckinVo();
        vo.setBrmUserId("brmUserId");
        vo.setChiefZone("chiefZone");
        vo.setProjectZoneType("projectZoneType");
        vo.setBrmZone("brmZone");
        vo.setBrmDepartment("brmDepartment");
        vo.setBrmGroup("brmGroup");
        vo.setStartSignDate("startSignDate");
        vo.setEndSignDate("endSignDate");
        vo.setAreaZone("areaZone");
        vo.setName("name");
        vo.setProjectType("projectType");
        vo.setStartDate("startDate");
        vo.setEndDate("endDate");
        vo.setProjectExecuteStartDate("projectExecuteStartDate");
        vo.setProjectExecuteEndDate("projectExecuteEndDate");
        vo.setProjectFinishStartDate("projectFinishStartDate");
        vo.setProjectFinishEndDate("projectFinishEndDate");

        // Run the test
        final QueryBuilder result = itemToCheckDetailsServiceUnderTest.buildQuery(vo);

        // Verify the results
    }
}
