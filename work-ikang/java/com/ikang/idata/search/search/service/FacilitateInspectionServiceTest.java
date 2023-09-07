package com.ikang.idata.search.search.service;

import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.dto.BSidePushRecordsEsResults;
import com.ikang.idata.common.entity.dto.MobilePhoneNumberInfo;
import com.ikang.idata.common.entity.vo.FacilitateInspectionVo;
import com.ikang.idata.common.entity.vo.PerformanceStatisticsVo;
import com.ikang.idata.search.search.common.PageResult;
import com.ikang.idata.search.search.entity.vo.FacilitateInspectionTableSearchVo;
import com.ikang.idata.search.search.entity.vo.FacilitateInspectionTableVo;
import com.ikang.idata.search.search.feign.impl.CheckPushRecordsFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FacilitateInspectionServiceTest {

    @Mock
    private CheckPushRecordsFeignServiceImpl mockCheckPushRecordsFeignService;

    @InjectMocks
    private FacilitateInspectionService facilitateInspectionServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final FacilitateInspectionTableSearchVo vo = new FacilitateInspectionTableSearchVo();
        vo.setName("name");
        vo.setQysjStart("qysjStart");
        vo.setQysjEnd("qysjEnd");
        vo.setXmjsrqStart("xmjsrqStart");
        vo.setXmjsrqEnd("xmjsrqEnd");
        vo.setQianyueleixing("qianyueleixing");
        vo.setProjectBusinessLine("projectBusinessLine");
        vo.setKhmc("khmc");
        vo.setProjectType("projectType");
        vo.setCustomerType("customerType");
        vo.setIndustryType("industryType");
        vo.setApplypublic("applypublic");
        vo.setProjectScale("projectScale");
        vo.setXmksrqStart("xmksrqStart");
        vo.setXmksrqEnd("xmksrqEnd");
        vo.setCheckRateSmall("checkRateSmall");
        vo.setCheckRateLarge("checkRateLarge");
        vo.setChiefZone("chiefZone");
        vo.setPageNum(0);
        vo.setPageSize(0);

        final PageResult<FacilitateInspectionTableVo> expectedResult = new PageResult<>();
        expectedResult.setResult(Arrays.asList());
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setTotal(0);
        expectedResult.setAdditionalData("additionalData");

        when(mockCheckPushRecordsFeignService.forNearlySevenDays()).thenReturn(Arrays.asList("value"));

        // Run the test
        final PageResult<FacilitateInspectionTableVo> result = facilitateInspectionServiceUnderTest.find(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_CheckPushRecordsFeignServiceImplReturnsNoItems() {
        // Setup
        final FacilitateInspectionTableSearchVo vo = new FacilitateInspectionTableSearchVo();
        vo.setName("name");
        vo.setQysjStart("qysjStart");
        vo.setQysjEnd("qysjEnd");
        vo.setXmjsrqStart("xmjsrqStart");
        vo.setXmjsrqEnd("xmjsrqEnd");
        vo.setQianyueleixing("qianyueleixing");
        vo.setProjectBusinessLine("projectBusinessLine");
        vo.setKhmc("khmc");
        vo.setProjectType("projectType");
        vo.setCustomerType("customerType");
        vo.setIndustryType("industryType");
        vo.setApplypublic("applypublic");
        vo.setProjectScale("projectScale");
        vo.setXmksrqStart("xmksrqStart");
        vo.setXmksrqEnd("xmksrqEnd");
        vo.setCheckRateSmall("checkRateSmall");
        vo.setCheckRateLarge("checkRateLarge");
        vo.setChiefZone("chiefZone");
        vo.setPageNum(0);
        vo.setPageSize(0);

        final PageResult<FacilitateInspectionTableVo> expectedResult = new PageResult<>();
        expectedResult.setResult(Arrays.asList());
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setTotal(0);
        expectedResult.setAdditionalData("additionalData");

        when(mockCheckPushRecordsFeignService.forNearlySevenDays()).thenReturn(Collections.emptyList());

        // Run the test
        final PageResult<FacilitateInspectionTableVo> result = facilitateInspectionServiceUnderTest.find(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testParse() {
        // Setup
        final JSONObject object = new JSONObject(0, false);
        final FacilitateInspectionTableSearchVo vo = new FacilitateInspectionTableSearchVo();
        vo.setName("name");
        vo.setQysjStart("qysjStart");
        vo.setQysjEnd("qysjEnd");
        vo.setXmjsrqStart("xmjsrqStart");
        vo.setXmjsrqEnd("xmjsrqEnd");
        vo.setQianyueleixing("qianyueleixing");
        vo.setProjectBusinessLine("projectBusinessLine");
        vo.setKhmc("khmc");
        vo.setProjectType("projectType");
        vo.setCustomerType("customerType");
        vo.setIndustryType("industryType");
        vo.setApplypublic("applypublic");
        vo.setProjectScale("projectScale");
        vo.setXmksrqStart("xmksrqStart");
        vo.setXmksrqEnd("xmksrqEnd");
        vo.setCheckRateSmall("checkRateSmall");
        vo.setCheckRateLarge("checkRateLarge");
        vo.setChiefZone("chiefZone");
        vo.setPageNum(0);
        vo.setPageSize(0);

        final PageResult<FacilitateInspectionTableVo> expectedResult = new PageResult<>();
        expectedResult.setResult(Arrays.asList());
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setTotal(0);
        expectedResult.setAdditionalData("additionalData");

        when(mockCheckPushRecordsFeignService.forNearlySevenDays()).thenReturn(Arrays.asList("value"));

        // Run the test
        final PageResult<FacilitateInspectionTableVo> result = facilitateInspectionServiceUnderTest.parse(object, vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testParse_CheckPushRecordsFeignServiceImplReturnsNoItems() {
        // Setup
        final JSONObject object = new JSONObject(0, false);
        final FacilitateInspectionTableSearchVo vo = new FacilitateInspectionTableSearchVo();
        vo.setName("name");
        vo.setQysjStart("qysjStart");
        vo.setQysjEnd("qysjEnd");
        vo.setXmjsrqStart("xmjsrqStart");
        vo.setXmjsrqEnd("xmjsrqEnd");
        vo.setQianyueleixing("qianyueleixing");
        vo.setProjectBusinessLine("projectBusinessLine");
        vo.setKhmc("khmc");
        vo.setProjectType("projectType");
        vo.setCustomerType("customerType");
        vo.setIndustryType("industryType");
        vo.setApplypublic("applypublic");
        vo.setProjectScale("projectScale");
        vo.setXmksrqStart("xmksrqStart");
        vo.setXmksrqEnd("xmksrqEnd");
        vo.setCheckRateSmall("checkRateSmall");
        vo.setCheckRateLarge("checkRateLarge");
        vo.setChiefZone("chiefZone");
        vo.setPageNum(0);
        vo.setPageSize(0);

        final PageResult<FacilitateInspectionTableVo> expectedResult = new PageResult<>();
        expectedResult.setResult(Arrays.asList());
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setTotal(0);
        expectedResult.setAdditionalData("additionalData");

        when(mockCheckPushRecordsFeignService.forNearlySevenDays()).thenReturn(Collections.emptyList());

        // Run the test
        final PageResult<FacilitateInspectionTableVo> result = facilitateInspectionServiceUnderTest.parse(object, vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildAgg() throws Exception {
        // Setup
        final SearchSourceBuilder expectedResult = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final SearchSourceBuilder result = facilitateInspectionServiceUnderTest.buildAgg();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCheckedInFiler() {
        // Setup
        // Run the test
        final QueryBuilder result = facilitateInspectionServiceUnderTest.checkedInFiler();

        // Verify the results
    }

    @Test
    void testPhoneUnreservedFilter() throws Exception {
        // Setup
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = facilitateInspectionServiceUnderTest.phoneUnreservedFilter();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildQuery() throws Exception {
        // Setup
        final FacilitateInspectionTableSearchVo vo = new FacilitateInspectionTableSearchVo();
        vo.setName("name");
        vo.setQysjStart("qysjStart");
        vo.setQysjEnd("qysjEnd");
        vo.setXmjsrqStart("xmjsrqStart");
        vo.setXmjsrqEnd("xmjsrqEnd");
        vo.setQianyueleixing("qianyueleixing");
        vo.setProjectBusinessLine("projectBusinessLine");
        vo.setKhmc("khmc");
        vo.setProjectType("projectType");
        vo.setCustomerType("customerType");
        vo.setIndustryType("industryType");
        vo.setApplypublic("applypublic");
        vo.setProjectScale("projectScale");
        vo.setXmksrqStart("xmksrqStart");
        vo.setXmksrqEnd("xmksrqEnd");
        vo.setCheckRateSmall("checkRateSmall");
        vo.setCheckRateLarge("checkRateLarge");
        vo.setChiefZone("chiefZone");
        vo.setPageNum(0);
        vo.setPageSize(0);

        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = facilitateInspectionServiceUnderTest.buildQuery(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testQueryPhoneQuantity() throws Exception {
        // Setup
        final FacilitateInspectionVo vo = new FacilitateInspectionVo();
        vo.setName("name");
        vo.setQysjStart("qysjStart");
        vo.setQysjEnd("qysjEnd");
        vo.setXmjsrqStart("xmjsrqStart");
        vo.setXmjsrqEnd("xmjsrqEnd");

        final MobilePhoneNumberInfo expectedResult = new MobilePhoneNumberInfo();
        expectedResult.setMobilePhoneNumber(new HashSet<>(Arrays.asList("value")));
        final MobilePhoneNumberInfo.Record record = new MobilePhoneNumberInfo.Record();
        record.setProjectNumber("projectNumber");
        record.setMobilePhoneNumber("mobilePhoneNumber");
        record.setCardNumber("cardNumber");
        expectedResult.setRecords(Arrays.asList(record));

        // Run the test
        final MobilePhoneNumberInfo result = facilitateInspectionServiceUnderTest.queryPhoneQuantity(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testQueryPhoneQuantity_ThrowsException() {
        // Setup
        final FacilitateInspectionVo vo = new FacilitateInspectionVo();
        vo.setName("name");
        vo.setQysjStart("qysjStart");
        vo.setQysjEnd("qysjEnd");
        vo.setXmjsrqStart("xmjsrqStart");
        vo.setXmjsrqEnd("xmjsrqEnd");

        // Run the test
        assertThatThrownBy(() -> facilitateInspectionServiceUnderTest.queryPhoneQuantity(vo))
                .isInstanceOf(Exception.class);
    }

    @Test
    void testBSidePushRecords() {
        // Setup
        final BSidePushRecordsEsResults bSidePushRecordsEsResults = new BSidePushRecordsEsResults();
        bSidePushRecordsEsResults.setId(0L);
        bSidePushRecordsEsResults.setSuccessfulReach(new BigDecimal("0.00"));
        bSidePushRecordsEsResults.setReservedCards(new BigDecimal("0.00"));
        bSidePushRecordsEsResults.setAppointmentRate(new BigDecimal("0.00"));
        bSidePushRecordsEsResults.setCardsCheckeds(new BigDecimal("0.00"));
        bSidePushRecordsEsResults.setArrivalRate(new BigDecimal("0.00"));
        final List<BSidePushRecordsEsResults> expectedResult = Arrays.asList(bSidePushRecordsEsResults);

        // Run the test
        final List<BSidePushRecordsEsResults> result = facilitateInspectionServiceUnderTest.bSidePushRecords(
                Arrays.asList(0L));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBSidePushRecordsParse() {
        // Setup
        final JSONObject object = new JSONObject(0, false);
        final BSidePushRecordsEsResults bSidePushRecordsEsResults = new BSidePushRecordsEsResults();
        bSidePushRecordsEsResults.setId(0L);
        bSidePushRecordsEsResults.setSuccessfulReach(new BigDecimal("0.00"));
        bSidePushRecordsEsResults.setReservedCards(new BigDecimal("0.00"));
        bSidePushRecordsEsResults.setAppointmentRate(new BigDecimal("0.00"));
        bSidePushRecordsEsResults.setCardsCheckeds(new BigDecimal("0.00"));
        bSidePushRecordsEsResults.setArrivalRate(new BigDecimal("0.00"));
        final List<BSidePushRecordsEsResults> expectedResult = Arrays.asList(bSidePushRecordsEsResults);

        // Run the test
        final List<BSidePushRecordsEsResults> result = facilitateInspectionServiceUnderTest.bSidePushRecordsParse(
                object);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildBSidePushRecordAgg() throws Exception {
        // Setup
        final SearchSourceBuilder expectedResult = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final SearchSourceBuilder result = facilitateInspectionServiceUnderTest.buildBSidePushRecordAgg();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildBSidePushRecordQuery() throws Exception {
        // Setup
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = facilitateInspectionServiceUnderTest.buildBSidePushRecordQuery(
                Arrays.asList(0L));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testEffectStatistics() {
        // Setup
        final PerformanceStatisticsVo expectedResult = new PerformanceStatisticsVo();
        expectedResult.setSuccessfulReach(new BigDecimal("0.00"));
        expectedResult.setReachCards(new BigDecimal("0.00"));
        expectedResult.setReservedCards(new BigDecimal("0.00"));
        expectedResult.setAppointmentRate(new BigDecimal("0.00"));
        expectedResult.setCardsCheckeds(new BigDecimal("0.00"));
        expectedResult.setArrivalRate(new BigDecimal("0.00"));
        expectedResult.setTimeDistributionOfAppointmentCheckIn(
                Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        expectedResult.setArrivalTimeDistribution(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        expectedResult.setAppointmentSource(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));

        // Run the test
        final PerformanceStatisticsVo result = facilitateInspectionServiceUnderTest.effectStatistics(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testParsePerformanceStatistics() {
        // Setup
        final JSONObject object = new JSONObject(0, false);
        final PerformanceStatisticsVo expectedResult = new PerformanceStatisticsVo();
        expectedResult.setSuccessfulReach(new BigDecimal("0.00"));
        expectedResult.setReachCards(new BigDecimal("0.00"));
        expectedResult.setReservedCards(new BigDecimal("0.00"));
        expectedResult.setAppointmentRate(new BigDecimal("0.00"));
        expectedResult.setCardsCheckeds(new BigDecimal("0.00"));
        expectedResult.setArrivalRate(new BigDecimal("0.00"));
        expectedResult.setTimeDistributionOfAppointmentCheckIn(
                Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        expectedResult.setArrivalTimeDistribution(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        expectedResult.setAppointmentSource(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));

        // Run the test
        final PerformanceStatisticsVo result = facilitateInspectionServiceUnderTest.parsePerformanceStatistics(object);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildEffectStatisticsAgg() throws Exception {
        // Setup
        final SearchSourceBuilder expectedResult = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final SearchSourceBuilder result = facilitateInspectionServiceUnderTest.buildEffectStatisticsAgg(
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildEffectStatisticsQuery() throws Exception {
        // Setup
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = facilitateInspectionServiceUnderTest.buildEffectStatisticsQuery(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
