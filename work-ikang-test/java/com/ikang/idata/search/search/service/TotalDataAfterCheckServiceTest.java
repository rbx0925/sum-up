package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.CheckTotalDataAnalysis;
import com.ikang.idata.common.entity.vo.CheckTotalResult;
import com.ikang.idata.common.entity.vo.CheckTotalVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TotalDataAfterCheckServiceTest {

    @Mock
    private DailyDataAfterCheckService mockDailyDataAfterCheckService;

    @InjectMocks
    private TotalDataAfterCheckService totalDataAfterCheckServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final CheckTotalVo vo = new CheckTotalVo();
        vo.setLocIds("locIds");
        vo.setFilterType(0);
        vo.setActualDateStart("actualDateStart");
        vo.setActualDateEnd("actualDateEnd");

        final CheckTotalResult expectedResult = new CheckTotalResult();
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
        expectedResult.setLocnameAchv(new BigDecimal("0.00"));
        expectedResult.setLocnameAchvRingRate(new BigDecimal("0.00"));
        expectedResult.setLocnameAchvRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckSalesVolume(new BigDecimal("0.00"));
        expectedResult.setCheckSalesVolumeRingRate(new BigDecimal("0.00"));
        expectedResult.setCheckSalesVolumeRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckPackageReceivableRingRate(new BigDecimal("0.00"));
        expectedResult.setCheckPackageReceivableRateByYear(new BigDecimal("0.00"));
        expectedResult.setLocnameAdditionTotalRingRate(new BigDecimal("0.00"));
        expectedResult.setLocnameAdditionTotalRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckSalePrice(new BigDecimal("0.00"));
        expectedResult.setLocAdditionPrice(new BigDecimal("0.00"));

        // Run the test
        final CheckTotalResult result = totalDataAfterCheckServiceUnderTest.find(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetResult() {
        // Setup
        final JSONObject jsonObject = new JSONObject(0, false);
        final CheckTotalResult expectedResult = new CheckTotalResult();
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
        expectedResult.setLocnameAchv(new BigDecimal("0.00"));
        expectedResult.setLocnameAchvRingRate(new BigDecimal("0.00"));
        expectedResult.setLocnameAchvRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckSalesVolume(new BigDecimal("0.00"));
        expectedResult.setCheckSalesVolumeRingRate(new BigDecimal("0.00"));
        expectedResult.setCheckSalesVolumeRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckPackageReceivableRingRate(new BigDecimal("0.00"));
        expectedResult.setCheckPackageReceivableRateByYear(new BigDecimal("0.00"));
        expectedResult.setLocnameAdditionTotalRingRate(new BigDecimal("0.00"));
        expectedResult.setLocnameAdditionTotalRateByYear(new BigDecimal("0.00"));
        expectedResult.setCheckSalePrice(new BigDecimal("0.00"));
        expectedResult.setLocAdditionPrice(new BigDecimal("0.00"));

        // Run the test
        final CheckTotalResult result = totalDataAfterCheckServiceUnderTest.getResult(jsonObject);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testAnalysis() {
        // Setup
        final CheckTotalVo vo = new CheckTotalVo();
        vo.setLocIds("locIds");
        vo.setFilterType(0);
        vo.setActualDateStart("actualDateStart");
        vo.setActualDateEnd("actualDateEnd");

        final CheckTotalDataAnalysis expectedResult = new CheckTotalDataAnalysis();
        final CheckTotalDataAnalysis.LocnameAchv locnameAchv = new CheckTotalDataAnalysis.LocnameAchv();
        locnameAchv.setLocnameAchv(new BigDecimal("0.00"));
        locnameAchv.setActualDate("actualDate");
        expectedResult.setLocnameAchvList(Arrays.asList(locnameAchv));
        final CheckTotalDataAnalysis.CheckedSales checkedSales = new CheckTotalDataAnalysis.CheckedSales();
        checkedSales.setCheckSalesVolume(new BigDecimal("0.00"));
        checkedSales.setActualDate("actualDate");
        expectedResult.setCheckedSalesList(Arrays.asList(checkedSales));
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
        expectedResult.setTrade13Rate(new BigDecimal("0.00"));
        expectedResult.setTrade14(new BigDecimal("0.00"));
        expectedResult.setTrade14Rate(new BigDecimal("0.00"));
        expectedResult.setTrade15(new BigDecimal("0.00"));
        expectedResult.setTrade15Rate(new BigDecimal("0.00"));
        expectedResult.setTrade16(new BigDecimal("0.00"));
        expectedResult.setTrade16Rate(new BigDecimal("0.00"));
        expectedResult.setTrade17(new BigDecimal("0.00"));
        expectedResult.setTrade17Rate(new BigDecimal("0.00"));
        expectedResult.setTrade18(new BigDecimal("0.00"));
        expectedResult.setTrade18Rate(new BigDecimal("0.00"));
        expectedResult.setTrade19(new BigDecimal("0.00"));
        expectedResult.setTrade19Rate(new BigDecimal("0.00"));
        expectedResult.setTrade20(new BigDecimal("0.00"));
        expectedResult.setTrade20Rate(new BigDecimal("0.00"));
        expectedResult.setTrade21(new BigDecimal("0.00"));
        expectedResult.setTrade21Rate(new BigDecimal("0.00"));
        expectedResult.setAge1(new BigDecimal("0.00"));
        expectedResult.setAge1Rate(new BigDecimal("0.00"));
        expectedResult.setAge2(new BigDecimal("0.00"));
        expectedResult.setAge2Rate(new BigDecimal("0.00"));

        when(mockDailyDataAfterCheckService.buildBranchProjectAggAmountConditions()).thenReturn(null);
        when(mockDailyDataAfterCheckService.buildBranchProjectAggCountConditions()).thenReturn(null);

        // Run the test
        final CheckTotalDataAnalysis result = totalDataAfterCheckServiceUnderTest.analysis(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDataAnalysis() {
        // Setup
        final JSONObject jsonObject = new JSONObject(0, false);
        final CheckTotalDataAnalysis expectedResult = new CheckTotalDataAnalysis();
        final CheckTotalDataAnalysis.LocnameAchv locnameAchv = new CheckTotalDataAnalysis.LocnameAchv();
        locnameAchv.setLocnameAchv(new BigDecimal("0.00"));
        locnameAchv.setActualDate("actualDate");
        expectedResult.setLocnameAchvList(Arrays.asList(locnameAchv));
        final CheckTotalDataAnalysis.CheckedSales checkedSales = new CheckTotalDataAnalysis.CheckedSales();
        checkedSales.setCheckSalesVolume(new BigDecimal("0.00"));
        checkedSales.setActualDate("actualDate");
        expectedResult.setCheckedSalesList(Arrays.asList(checkedSales));
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
        expectedResult.setTrade13Rate(new BigDecimal("0.00"));
        expectedResult.setTrade14(new BigDecimal("0.00"));
        expectedResult.setTrade14Rate(new BigDecimal("0.00"));
        expectedResult.setTrade15(new BigDecimal("0.00"));
        expectedResult.setTrade15Rate(new BigDecimal("0.00"));
        expectedResult.setTrade16(new BigDecimal("0.00"));
        expectedResult.setTrade16Rate(new BigDecimal("0.00"));
        expectedResult.setTrade17(new BigDecimal("0.00"));
        expectedResult.setTrade17Rate(new BigDecimal("0.00"));
        expectedResult.setTrade18(new BigDecimal("0.00"));
        expectedResult.setTrade18Rate(new BigDecimal("0.00"));
        expectedResult.setTrade19(new BigDecimal("0.00"));
        expectedResult.setTrade19Rate(new BigDecimal("0.00"));
        expectedResult.setTrade20(new BigDecimal("0.00"));
        expectedResult.setTrade20Rate(new BigDecimal("0.00"));
        expectedResult.setTrade21(new BigDecimal("0.00"));
        expectedResult.setTrade21Rate(new BigDecimal("0.00"));
        expectedResult.setAge1(new BigDecimal("0.00"));
        expectedResult.setAge1Rate(new BigDecimal("0.00"));
        expectedResult.setAge2(new BigDecimal("0.00"));
        expectedResult.setAge2Rate(new BigDecimal("0.00"));

        // Run the test
        final CheckTotalDataAnalysis result = totalDataAfterCheckServiceUnderTest.dataAnalysis(jsonObject);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testInvokeAndGetResult() {
        // Setup
        final JSONObject expectedResult = new JSONObject(0, false);

        // Run the test
        final JSONObject result = totalDataAfterCheckServiceUnderTest.invokeAndGetResult("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
