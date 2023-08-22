package com.ikang.idata.search.search.controller;

import cn.hutool.core.lang.Pair;
import com.ikang.idata.search.search.entity.vo.TenderSearchVo;
import com.ikang.idata.search.search.entity.vo.WinBidAnalysisResultVo;
import com.ikang.idata.search.search.service.ProjectSigningService;
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
@WebMvcTest(WinBidAnalysisController.class)
class WinBidAnalysisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectSigningService mockService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure ProjectSigningService.winBidAnalysis(...).
        final WinBidAnalysisResultVo winBidAnalysisResultVo = new WinBidAnalysisResultVo();
        winBidAnalysisResultVo.setWinBidNum(new BigDecimal("0.00"));
        winBidAnalysisResultVo.setTenderNum(new BigDecimal("0.00"));
        winBidAnalysisResultVo.setBidRate(new BigDecimal("0.00"));
        winBidAnalysisResultVo.setWinBidMoney(new BigDecimal("0.00"));
        winBidAnalysisResultVo.setTenderMoney(new BigDecimal("0.00"));
        winBidAnalysisResultVo.setBidMoneyRate(new BigDecimal("0.00"));
        winBidAnalysisResultVo.setEnterpriseNatureBidNum(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        winBidAnalysisResultVo.setEnterpriseNatureBidMoney(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        winBidAnalysisResultVo.setScaleBidNum(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        winBidAnalysisResultVo.setScaleBidMoney(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        winBidAnalysisResultVo.setNatureOfTheBidNum(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        winBidAnalysisResultVo.setNatureOfTheBidMoney(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        winBidAnalysisResultVo.setCustomerNatureMoney(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        winBidAnalysisResultVo.setTenderSituationMoney(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        winBidAnalysisResultVo.setSamePeriod(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        when(mockService.winBidAnalysis(new TenderSearchVo())).thenReturn(winBidAnalysisResultVo);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkPlatform/winBid")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
