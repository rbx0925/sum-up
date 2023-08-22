package com.ikang.idata.search.search.service;

import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.CheckTotalResultMore;
import com.ikang.idata.common.entity.DailyDataAfterCheck;
import com.ikang.idata.common.entity.ECommerceBillboardDetails;
import com.ikang.idata.common.entity.vo.DailyDataAfterCheckDetailVO;
import com.ikang.idata.common.entity.vo.DailyDataAfterCheckVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class DailyDataAfterCheckServiceTest {

    @Mock
    private ESHttpClientService mockEsHttpClientService;

    @InjectMocks
    private DailyDataAfterCheckService dailyDataAfterCheckServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final DailyDataAfterCheckVO dailyDataAfterCheckVO = new DailyDataAfterCheckVO();
        dailyDataAfterCheckVO.setLocId("locId");
        dailyDataAfterCheckVO.setActualDate("actualDate");

        final DailyDataAfterCheck expectedResult = new DailyDataAfterCheck();
        expectedResult.setAreaId("areaId");
        expectedResult.setLocIds("locIds");
        expectedResult.setFilterType(0);
        expectedResult.setRegNum(new BigDecimal("0.00"));
        expectedResult.setCheckRate(new BigDecimal("0.00"));
        expectedResult.setRegNumRingRate(new BigDecimal("0.00"));
        expectedResult.setRegNumRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckNum(new BigDecimal("0.00"));
        expectedResult.setCheckNumRingRate(new BigDecimal("0.00"));
        expectedResult.setCheckNumRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckedNum(new BigDecimal("0.00"));
        expectedResult.setCheckedNumRingRate(new BigDecimal("0.00"));
        expectedResult.setAmountTop10(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        expectedResult.setNumberTop10(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        final DailyDataAfterCheck.Channel channel = new DailyDataAfterCheck.Channel();
        expectedResult.setChannel(channel);

        // Run the test
        final DailyDataAfterCheck result = dailyDataAfterCheckServiceUnderTest.find(dailyDataAfterCheckVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildBranchDiseaseDetectionProgramTOP10() {
        // Setup
        final DailyDataAfterCheckVO dailyDataAfterCheckVO = new DailyDataAfterCheckVO();
        dailyDataAfterCheckVO.setLocId("locId");
        dailyDataAfterCheckVO.setActualDate("actualDate");

        final DailyDataAfterCheck dailyDataAfterCheck = new DailyDataAfterCheck();
        dailyDataAfterCheck.setAreaId("areaId");
        dailyDataAfterCheck.setLocIds("locIds");
        dailyDataAfterCheck.setFilterType(0);
        dailyDataAfterCheck.setRegNum(new BigDecimal("0.00"));
        dailyDataAfterCheck.setCheckRate(new BigDecimal("0.00"));
        dailyDataAfterCheck.setRegNumRingRate(new BigDecimal("0.00"));
        dailyDataAfterCheck.setRegNumRateByYear(new BigDecimal("0.00"));
        dailyDataAfterCheck.setCheckNum(new BigDecimal("0.00"));
        dailyDataAfterCheck.setCheckNumRingRate(new BigDecimal("0.00"));
        dailyDataAfterCheck.setCheckNumRateByYear(new BigDecimal("0.00"));
        dailyDataAfterCheck.setCheckedNum(new BigDecimal("0.00"));
        dailyDataAfterCheck.setCheckedNumRingRate(new BigDecimal("0.00"));
        dailyDataAfterCheck.setAmountTop10(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        dailyDataAfterCheck.setNumberTop10(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        final DailyDataAfterCheck.Channel channel = new DailyDataAfterCheck.Channel();
        dailyDataAfterCheck.setChannel(channel);

        // Run the test
        dailyDataAfterCheckServiceUnderTest.buildBranchDiseaseDetectionProgramTOP10(dailyDataAfterCheckVO,
                dailyDataAfterCheck);

        // Verify the results
    }

    @Test
    void testGetResult() {
        // Setup
        final JSONObject jsonObject = new JSONObject(0, false);
        final CheckTotalResultMore expectedResult = new CheckTotalResultMore();
        expectedResult.setRegNum(new BigDecimal("0.00"));
        expectedResult.setCheckRate(new BigDecimal("0.00"));
        expectedResult.setRegNumRingRate(new BigDecimal("0.00"));
        expectedResult.setRegNumRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckNum(new BigDecimal("0.00"));
        expectedResult.setCheckNumRingRate(new BigDecimal("0.00"));
        expectedResult.setCheckNumRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckedNum(new BigDecimal("0.00"));
        expectedResult.setCheckedNumRingRate(new BigDecimal("0.00"));
        expectedResult.setCheckedNumRateByYear(new BigDecimal("0.00"));
        expectedResult.setReportNum(new BigDecimal("0.00"));
        expectedResult.setReportNumRingRate(new BigDecimal("0.00"));
        expectedResult.setReportNumRateByYear(new BigDecimal("0.00"));
        expectedResult.setLocnameAdditionNum(new BigDecimal("0.00"));
        expectedResult.setLocnameAchv(new BigDecimal("0.00"));
        expectedResult.setLocnameAchvRingRate(new BigDecimal("0.00"));
        expectedResult.setLocnameAchvRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckSalesVolume(new BigDecimal("0.00"));
        expectedResult.setCheckSalesVolumeRingRate(new BigDecimal("0.00"));
        expectedResult.setCheckSalesVolumeRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckPackageReceivableRingRate(new BigDecimal("0.00"));
        expectedResult.setCheckPackageReceivableRateByYear(new BigDecimal("0.00"));
        expectedResult.setLocnameAdditionTotal(new BigDecimal("0.00"));
        expectedResult.setLocnameAdditionTotalRingRate(new BigDecimal("0.00"));
        expectedResult.setLocnameAdditionTotalRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckSalePrice(new BigDecimal("0.00"));
        expectedResult.setLocAdditionPrice(new BigDecimal("0.00"));
        expectedResult.setMaleNum(new BigDecimal("0.00"));
        expectedResult.setMaleNumRate(new BigDecimal("0.00"));
        expectedResult.setFemaleNum(new BigDecimal("0.00"));
        expectedResult.setFemaleNumRate(new BigDecimal("0.00"));
        expectedResult.setVipNum(new BigDecimal("0.00"));
        expectedResult.setVipNumRate(new BigDecimal("0.00"));
        expectedResult.setSmNum(new BigDecimal("0.00"));
        expectedResult.setSmNumRate(new BigDecimal("0.00"));
        expectedResult.setFmNum(new BigDecimal("0.00"));
        expectedResult.setFmNumRate(new BigDecimal("0.00"));
        expectedResult.setStaffNum(new BigDecimal("0.00"));
        expectedResult.setStaffNumRate(new BigDecimal("0.00"));
        expectedResult.setFitNum(new BigDecimal("0.00"));
        expectedResult.setFitNumRate(new BigDecimal("0.00"));
        expectedResult.setEntryNum(new BigDecimal("0.00"));
        expectedResult.setEntryNumRate(new BigDecimal("0.00"));
        expectedResult.setRetireNum(new BigDecimal("0.00"));
        expectedResult.setRetireNumRate(new BigDecimal("0.00"));
        expectedResult.setTrade1(new BigDecimal("0.00"));
        expectedResult.setTrade1Rate(new BigDecimal("0.00"));
        expectedResult.setTrade2(new BigDecimal("0.00"));
        expectedResult.setTrade2Rate(new BigDecimal("0.00"));
        expectedResult.setTrade3(new BigDecimal("0.00"));
        expectedResult.setTrade3Rate(new BigDecimal("0.00"));
        expectedResult.setTrade4(new BigDecimal("0.00"));
        expectedResult.setTrade4Rate(new BigDecimal("0.00"));
        expectedResult.setTrade5(new BigDecimal("0.00"));
        expectedResult.setTrade5Rate(new BigDecimal("0.00"));
        expectedResult.setTrade6(new BigDecimal("0.00"));
        expectedResult.setTrade6Rate(new BigDecimal("0.00"));
        expectedResult.setTrade7(new BigDecimal("0.00"));
        expectedResult.setTrade7Rate(new BigDecimal("0.00"));
        expectedResult.setTrade8(new BigDecimal("0.00"));
        expectedResult.setTrade8Rate(new BigDecimal("0.00"));
        expectedResult.setTrade9(new BigDecimal("0.00"));
        expectedResult.setTrade9Rate(new BigDecimal("0.00"));
        expectedResult.setTrade10(new BigDecimal("0.00"));
        expectedResult.setTrade10Rate(new BigDecimal("0.00"));
        expectedResult.setTrade11(new BigDecimal("0.00"));
        expectedResult.setTrade11Rate(new BigDecimal("0.00"));
        expectedResult.setTrade12(new BigDecimal("0.00"));
        expectedResult.setTrade12Rate(new BigDecimal("0.00"));
        expectedResult.setTrade13(new BigDecimal("0.00"));

        // Run the test
        final CheckTotalResultMore result = dailyDataAfterCheckServiceUnderTest.getResult(jsonObject,
                Arrays.asList("value"));

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testInvokeAndGetResult() {
        // Setup
        final JSONObject expectedResult = new JSONObject(0, false);

        // Run the test
        final JSONObject result = dailyDataAfterCheckServiceUnderTest.invokeAndGetResult("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetDetails() throws Exception {
        // Setup
        final DailyDataAfterCheckDetailVO dailyDataAfterCheckDetailVO = new DailyDataAfterCheckDetailVO();
        dailyDataAfterCheckDetailVO.setLocId("locId");
        dailyDataAfterCheckDetailVO.setStartTime("startTime");
        dailyDataAfterCheckDetailVO.setEndTime("endTime");
        dailyDataAfterCheckDetailVO.setType("type");

        final ECommerceBillboardDetails expectedResult = new ECommerceBillboardDetails();
        expectedResult.setIndexName("分院加项客单价");
        expectedResult.setIndexUnit("人");
        final ECommerceBillboardDetails.Detail detail = new ECommerceBillboardDetails.Detail();
        detail.setTime("time");
        detail.setValue("value");
        detail.setValueStr("valueStr");
        detail.setUnit("人");
        detail.setYearOnYear("yearOnYear");
        detail.setYearOnYearType("yearOnYearType");
        detail.setChainRatio("chainRatio");
        detail.setChainRatioType("chainRatioType");
        expectedResult.setDetailList(Arrays.asList(detail));

        // Run the test
        final ECommerceBillboardDetails result = dailyDataAfterCheckServiceUnderTest.getDetails(
                dailyDataAfterCheckDetailVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetDetails_ThrowsParseException() {
        // Setup
        final DailyDataAfterCheckDetailVO dailyDataAfterCheckDetailVO = new DailyDataAfterCheckDetailVO();
        dailyDataAfterCheckDetailVO.setLocId("locId");
        dailyDataAfterCheckDetailVO.setStartTime("startTime");
        dailyDataAfterCheckDetailVO.setEndTime("endTime");
        dailyDataAfterCheckDetailVO.setType("type");

        // Run the test
        assertThatThrownBy(
                () -> dailyDataAfterCheckServiceUnderTest.getDetails(dailyDataAfterCheckDetailVO))
                .isInstanceOf(ParseException.class);
    }

    @Test
    void testMain() {
        // Setup
        // Run the test
        DailyDataAfterCheckService.main(new String[]{"args"});

        // Verify the results
    }
}
