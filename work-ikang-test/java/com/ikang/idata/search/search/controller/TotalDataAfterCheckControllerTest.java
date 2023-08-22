package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.CheckTotalDataAnalysis;
import com.ikang.idata.common.entity.vo.CheckTotalResult;
import com.ikang.idata.common.entity.vo.CheckTotalVo;
import com.ikang.idata.search.search.service.TotalDataAfterCheckService;
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
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TotalDataAfterCheckController.class)
class TotalDataAfterCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TotalDataAfterCheckService mockCheckTotalService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure TotalDataAfterCheckService.find(...).
        final CheckTotalResult checkTotalResult = new CheckTotalResult();
        checkTotalResult.setActualDateStart("actualDateStart");
        checkTotalResult.setActualDateEnd("actualDateEnd");
        checkTotalResult.setAreaId("areaId");
        checkTotalResult.setLocIds("locIds");
        checkTotalResult.setFilterType(0);
        checkTotalResult.setRegNum(new BigDecimal("0.00"));
        checkTotalResult.setCheckRate(new BigDecimal("0.00"));
        checkTotalResult.setRegNumRingRate(new BigDecimal("0.00"));
        checkTotalResult.setRegNumRateByYear(new BigDecimal("0.00"));
        checkTotalResult.setCheckNum(new BigDecimal("0.00"));
        checkTotalResult.setCheckNumRingRate(new BigDecimal("0.00"));
        checkTotalResult.setCheckNumRateByYear(new BigDecimal("0.00"));
        checkTotalResult.setCheckedNum(new BigDecimal("0.00"));
        checkTotalResult.setCheckedNumRingRate(new BigDecimal("0.00"));
        checkTotalResult.setCheckedNumRateByYear(new BigDecimal("0.00"));
        when(mockCheckTotalService.find(new CheckTotalVo())).thenReturn(checkTotalResult);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/checktotal/find")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAnalysis() throws Exception {
        // Setup
        // Configure TotalDataAfterCheckService.analysis(...).
        final CheckTotalDataAnalysis checkTotalDataAnalysis = new CheckTotalDataAnalysis();
        checkTotalDataAnalysis.setActualDateStart("actualDateStart");
        checkTotalDataAnalysis.setActualDateEnd("actualDateEnd");
        checkTotalDataAnalysis.setAreaId("areaId");
        checkTotalDataAnalysis.setLocIds("locIds");
        checkTotalDataAnalysis.setFilterType(0);
//        checkTotalDataAnalysis.setLocnameAchv(
//                Arrays.asList(new CheckTotalDataAnalysis.LocnameAchv(new BigDecimal("0.00"), "actualDate")));
//        checkTotalDataAnalysis.setCheckedSales(
//                Arrays.asList(new CheckTotalDataAnalysis.CheckedSales(new BigDecimal("0.00"), "actualDate")));
        checkTotalDataAnalysis.setMaleNum(new BigDecimal("0.00"));
        checkTotalDataAnalysis.setMaleNumRate(new BigDecimal("0.00"));
        checkTotalDataAnalysis.setFemaleNum(new BigDecimal("0.00"));
        checkTotalDataAnalysis.setFemaleNumRate(new BigDecimal("0.00"));
        checkTotalDataAnalysis.setVipNum(new BigDecimal("0.00"));
        checkTotalDataAnalysis.setVipNumRate(new BigDecimal("0.00"));
        checkTotalDataAnalysis.setSmNum(new BigDecimal("0.00"));
        checkTotalDataAnalysis.setSmNumRate(new BigDecimal("0.00"));
        when(mockCheckTotalService.analysis(new CheckTotalVo())).thenReturn(checkTotalDataAnalysis);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/checktotal/analysis")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
