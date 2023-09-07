package com.ikang.idata.search.search.service;

import cn.hutool.core.lang.Pair;
import com.ikang.idata.search.search.common.PageResult;
import com.ikang.idata.search.search.entity.vo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IndustryCustomerAnalysisServiceTest {

    @Mock
    private BaseService mockBaseService;

    @InjectMocks
    private IndustryCustomerAnalysisService industryCustomerAnalysisServiceUnderTest;

    @Test
    void testFindIndustryDetail() {
        // Setup
        final IndustryCustomerAnalysisVo vo = new IndustryCustomerAnalysisVo();
        vo.setChiefZone("chiefZone");
        vo.setBrmZone("brmZone");
        vo.setProjectZoneType("projectZoneType");
        vo.setYear("year");
        vo.setMonth("month");
        vo.setProjectClassification("projectClassification");
        vo.setNewoldtype("newoldtype");
        vo.setProjectBusinessLine("projectBusinessLine");
        vo.setContain("contain");
        vo.setCustomerType("customerType");
        vo.setPaidType("paidType");
        vo.setPageNum(0);
        vo.setPageSize(0);

        final IndustryBreakdownVo industryBreakdownVo = new IndustryBreakdownVo();
        industryBreakdownVo.setIndustryType("industryType");
        final IndustryBreakdownVo.AnnualInfo annualInfo = new IndustryBreakdownVo.AnnualInfo();
        annualInfo.setSummaryOfReceivables(new BigDecimal("0.00"));
        annualInfo.setSignNumber(new BigDecimal("0.00"));
        annualInfo.setCheckNum(new BigDecimal("0.00"));
        annualInfo.setAmountPerOrder(new BigDecimal("0.00"));
        annualInfo.setAvgUnitPrice(new BigDecimal("0.00"));
        annualInfo.setReceivablesPercentage(new BigDecimal("0.00"));
        annualInfo.setReceivablesAmplitude(new BigDecimal("0.00"));
        annualInfo.setGrowthRate(new BigDecimal("0.00"));
        annualInfo.setIncreaseInAmountPerOrder(new BigDecimal("0.00"));
        annualInfo.setRanking(0);
        industryBreakdownVo.setPairs(Arrays.asList(new Pair<>("key", annualInfo)));
        final List<IndustryBreakdownVo> expectedResult = Arrays.asList(industryBreakdownVo);

        // Run the test
        final List<IndustryBreakdownVo> result = industryCustomerAnalysisServiceUnderTest.findIndustryDetail(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


}
