package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.DailyDataAfterCheck;
import com.ikang.idata.common.entity.ECommerceBillboardDetails;
import com.ikang.idata.common.entity.vo.DailyDataAfterCheckDetailVO;
import com.ikang.idata.common.entity.vo.DailyDataAfterCheckVO;
import com.ikang.idata.search.search.service.DailyDataAfterCheckService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DailyDataAfterCheckController.class)
class DailyDataAfterCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DailyDataAfterCheckService mockDailyDataAfterCheckService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure DailyDataAfterCheckService.find(...).
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
        dailyDataAfterCheck.setCheckedNumRateByYear(new BigDecimal("0.00"));
        dailyDataAfterCheck.setReportNum(new BigDecimal("0.00"));
        dailyDataAfterCheck.setReportNumRingRate(new BigDecimal("0.00"));
        when(mockDailyDataAfterCheckService.find(new DailyDataAfterCheckVO())).thenReturn(dailyDataAfterCheck);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/dailyDataAfterCheck/find")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetDetails() throws Exception {
        // Setup
        // Configure DailyDataAfterCheckService.getDetails(...).
        final ECommerceBillboardDetails eCommerceBillboardDetails = new ECommerceBillboardDetails();
        eCommerceBillboardDetails.setIndexName("indexName");
        eCommerceBillboardDetails.setIndexUnit("indexUnit");
        final ECommerceBillboardDetails.Detail detail = new ECommerceBillboardDetails.Detail();
        detail.setTime("time");
        detail.setValue("value");
        detail.setValueStr("valueStr");
        detail.setUnit("unit");
        detail.setYearOnYear("yearOnYear");
        detail.setYearOnYearType("yearOnYearType");
        detail.setChainRatio("chainRatio");
        detail.setChainRatioType("chainRatioType");
        eCommerceBillboardDetails.setDetailList(Arrays.asList(detail));
        when(mockDailyDataAfterCheckService.getDetails(new DailyDataAfterCheckDetailVO()))
                .thenReturn(eCommerceBillboardDetails);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/dailyDataAfterCheck/getDetails")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetDetails_DailyDataAfterCheckServiceThrowsParseException() throws Exception {
        // Setup
        when(mockDailyDataAfterCheckService.getDetails(new DailyDataAfterCheckDetailVO()))
                .thenThrow(ParseException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/dailyDataAfterCheck/getDetails")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
