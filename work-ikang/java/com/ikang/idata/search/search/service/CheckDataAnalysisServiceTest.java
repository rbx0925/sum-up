package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.dto.CheckinAnalysisDTO;
import com.ikang.idata.common.entity.vo.CheckinVo;
import com.ikang.idata.search.search.common.PageResult;
import com.ikang.idata.search.search.entity.vo.CheckDataAnalysisVo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckDataAnalysisServiceTest {

    @Mock
    private ItemToCheckDetailsService mockCheckDetailsService;

    @InjectMocks
    private CheckDataAnalysisService checkDataAnalysisServiceUnderTest;

    @Test
    void testAnalysisOfIncomingData() {
        // Setup
        final CheckDataAnalysisVo vo = new CheckDataAnalysisVo();
        vo.setGroupType("groupType");
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
        vo.setPageNum(0);
        vo.setPageSize(0);

        final PageResult<CheckinAnalysisDTO> expectedResult = new PageResult<>();
        expectedResult.setResult(Arrays.asList());
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setTotal(0);
        expectedResult.setTotalStr("totalStr");

        when(mockCheckDetailsService.buildPayCardSignQuery(any(CheckinVo.class))).thenReturn(null);

        // Run the test
        final PageResult<CheckinAnalysisDTO> result = checkDataAnalysisServiceUnderTest.analysisOfIncomingData(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testParsePayCardSignObj() {
        // Setup
        final JSONObject payCardSignObj = new JSONObject(0, false);
        final CheckinAnalysisDTO checkinAnalysisDTO = new CheckinAnalysisDTO();
        checkinAnalysisDTO.setBrmGroup("brmGroup");
        checkinAnalysisDTO.setOwnerName("ownerName");
        checkinAnalysisDTO.setExaminationCount(new BigDecimal("0.00"));
        checkinAnalysisDTO.setActivatedCardsCount(new BigDecimal("0.00"));
        checkinAnalysisDTO.setActivatedCardsRate(new BigDecimal("0.00"));
        checkinAnalysisDTO.setNotBookedCount(new BigDecimal("0.00"));
        checkinAnalysisDTO.setNotArrivedCount(new BigDecimal("0.00"));
        checkinAnalysisDTO.setCheckInCount(new BigDecimal("0.00"));
        checkinAnalysisDTO.setOwnerid("ownerid");
        final List<CheckinAnalysisDTO> dtoList = Arrays.asList(checkinAnalysisDTO);
        final CheckDataAnalysisVo vo = new CheckDataAnalysisVo();
        vo.setGroupType("groupType");
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
        vo.setPageNum(0);
        vo.setPageSize(0);

        final CheckinAnalysisDTO checkinAnalysisDTO1 = new CheckinAnalysisDTO();
        checkinAnalysisDTO1.setBrmGroup("brmGroup");
        checkinAnalysisDTO1.setOwnerName("ownerName");
        checkinAnalysisDTO1.setExaminationCount(new BigDecimal("0.00"));
        checkinAnalysisDTO1.setActivatedCardsCount(new BigDecimal("0.00"));
        checkinAnalysisDTO1.setActivatedCardsRate(new BigDecimal("0.00"));
        checkinAnalysisDTO1.setNotBookedCount(new BigDecimal("0.00"));
        checkinAnalysisDTO1.setNotArrivedCount(new BigDecimal("0.00"));
        checkinAnalysisDTO1.setCheckInCount(new BigDecimal("0.00"));
        checkinAnalysisDTO1.setOwnerid("ownerid");
        final List<CheckinAnalysisDTO> expectedResult = Arrays.asList(checkinAnalysisDTO1);

        // Run the test
        final List<CheckinAnalysisDTO> result = checkDataAnalysisServiceUnderTest.parsePayCardSignObj(payCardSignObj,
                dtoList, vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildAggregation() {
        // Setup
        final CheckDataAnalysisVo vo = new CheckDataAnalysisVo();
        vo.setGroupType("groupType");
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
        vo.setPageNum(0);
        vo.setPageSize(0);

        // Run the test
        final AggregationBuilder result = checkDataAnalysisServiceUnderTest.buildAggregation(vo);

        // Verify the results
    }

    @Test
    void testBuildQuery() {
        // Setup
        final CheckDataAnalysisVo vo = new CheckDataAnalysisVo();
        vo.setGroupType("groupType");
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
        vo.setPageNum(0);
        vo.setPageSize(0);

        // Run the test
        final QueryBuilder result = checkDataAnalysisServiceUnderTest.buildQuery(vo);

        // Verify the results
    }
}
