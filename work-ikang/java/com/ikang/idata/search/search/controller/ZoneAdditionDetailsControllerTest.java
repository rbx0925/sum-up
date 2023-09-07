package com.ikang.idata.search.search.controller;

import cn.hutool.core.lang.Pair;
import com.ikang.idata.search.search.entity.vo.*;
import com.ikang.idata.search.search.service.ZoneAdditionDetailsService;
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
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ZoneAdditionDetailsController.class)
class ZoneAdditionDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZoneAdditionDetailsService mockZoneAdditionDetailsService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure ZoneAdditionDetailsService.find(...).
        final RankingByAdditionList rankingByAdditionList = new RankingByAdditionList();
        final RankingByAddition rankingByAddition = new RankingByAddition();
        rankingByAddition.setItemName("itemName");
        rankingByAddition.setAdditionCount("additionCount");
        rankingByAddition.setAdditionMoney("additionMoney");
        rankingByAdditionList.setCountList(Arrays.asList(rankingByAddition));
        final RankingByAddition rankingByAddition1 = new RankingByAddition();
        rankingByAddition1.setItemName("itemName");
        rankingByAddition1.setAdditionCount("additionCount");
        rankingByAddition1.setAdditionMoney("additionMoney");
        rankingByAdditionList.setMoneyList(Arrays.asList(rankingByAddition1));
        when(mockZoneAdditionDetailsService.find(any(ManagerAdditionDetailsVo.class)))
                .thenReturn(rankingByAdditionList);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/zoneAdditionDetails/find")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindPortrait() throws Exception {
        // Setup
        // Configure ZoneAdditionDetailsService.findPortrait(...).
        final PortraitVo portraitVo = new PortraitVo();
        portraitVo.setAddItemNum(new BigDecimal("0.00"));
        portraitVo.setAddItemMoney(new BigDecimal("0.00"));
        portraitVo.setAddItemMarketMoney(new BigDecimal("0.00"));
        portraitVo.setSameAddItemNum(new BigDecimal("0.00"));
        portraitVo.setSameAddItemMoney(new BigDecimal("0.00"));
        portraitVo.setSameAddItemMarketMoney(new BigDecimal("0.00"));
        portraitVo.setCheckNum(new BigDecimal("0.00"));
        portraitVo.setCheckMoney(new BigDecimal("0.00"));
        portraitVo.setAddItemNumRate(new BigDecimal("0.00"));
        portraitVo.setAddItemRate(new BigDecimal("0.00"));
        portraitVo.setAddItemMoneyRate(new BigDecimal("0.00"));
        portraitVo.setSameAddItemMoneyRate(new BigDecimal("0.00"));
        portraitVo.setAvgAddItemMoney(new BigDecimal("0.00"));
        portraitVo.setSameAvgAddItemMoney(new BigDecimal("0.00"));
        portraitVo.setAvgAddItemMoneyRate(new BigDecimal("0.00"));
        when(mockZoneAdditionDetailsService.findPortrait(any(ManagerAdditionDetailsVo.class))).thenReturn(portraitVo);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/zoneAdditionDetails/findPortrait")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindTrend() throws Exception {
        // Setup
        // Configure ZoneAdditionDetailsService.findTrend(...).
        final Trend trend = new Trend();
        trend.setCurrentYear(2020);
        trend.setSameYear(2020);
        trend.setTwoYear(2020);
        trend.setThreeYear(2020);
        trend.setFourYear(2020);
        trend.setCurrentRate(new BigDecimal("0.00"));
        trend.setSameYearRate(new BigDecimal("0.00"));
        trend.setTwoYearRate(new BigDecimal("0.00"));
        trend.setThreeYearRate(new BigDecimal("0.00"));
        trend.setFourYearRate(new BigDecimal("0.00"));
        when(mockZoneAdditionDetailsService.findTrend(any(ManagerAdditionDetailsVo.class))).thenReturn(trend);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/zoneAdditionDetails/findTrend")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testPortraitAnalysisBySex() throws Exception {
        // Setup
        // Configure ZoneAdditionDetailsService.portraitAnalysisBySex(...).
        final PortraitAnalysisVo portraitAnalysisVo = new PortraitAnalysisVo();
        portraitAnalysisVo.setWomanNum(new BigDecimal("0.00"));
        portraitAnalysisVo.setManNum(new BigDecimal("0.00"));
        portraitAnalysisVo.setWomanNumRate(new BigDecimal("0.00"));
        portraitAnalysisVo.setManNumRate(new BigDecimal("0.00"));
        portraitAnalysisVo.setWomanMoney(new BigDecimal("0.00"));
        portraitAnalysisVo.setManMoney(new BigDecimal("0.00"));
        portraitAnalysisVo.setWomanMoneyRate(new BigDecimal("0.00"));
        portraitAnalysisVo.setManMoneyRate(new BigDecimal("0.00"));
        portraitAnalysisVo.setWomanAvgMoney(new BigDecimal("0.00"));
        portraitAnalysisVo.setManAvgMoney(new BigDecimal("0.00"));
        portraitAnalysisVo.setWomanAvgMarketMoney(new BigDecimal("0.00"));
        portraitAnalysisVo.setManAvgMarketMoney(new BigDecimal("0.00"));
        when(mockZoneAdditionDetailsService.portraitAnalysisBySex(any(ManagerAdditionDetailsVo.class)))
                .thenReturn(portraitAnalysisVo);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/zoneAdditionDetails/portraitAnalysisBySex")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testPortraitAnalysisByClient() throws Exception {
        // Setup
        // Configure ZoneAdditionDetailsService.portraitAnalysisByClient(...).
        final AnalysisByClient analysisByClient = new AnalysisByClient();
        analysisByClient.setName("name");
        analysisByClient.setNum(new BigDecimal("0.00"));
        analysisByClient.setNumRate(new BigDecimal("0.00"));
        analysisByClient.setMoney(new BigDecimal("0.00"));
        analysisByClient.setMoneyRate(new BigDecimal("0.00"));
        analysisByClient.setAvgMoney(new BigDecimal("0.00"));
        final List<AnalysisByClient> analysisByClients = Arrays.asList(analysisByClient);
        when(mockZoneAdditionDetailsService.portraitAnalysisByClient(any(ManagerAdditionDetailsByVo.class)))
                .thenReturn(analysisByClients);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/zoneAdditionDetails/portraitAnalysisByClient")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testPortraitAnalysisByClient_ZoneAdditionDetailsServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockZoneAdditionDetailsService.portraitAnalysisByClient(any(ManagerAdditionDetailsByVo.class)))
                .thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/zoneAdditionDetails/portraitAnalysisByClient")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        // Configure ZoneAdditionDetailsService.findGroupBy(...).
        final List<Pair<String, List<String>>> pairs = Arrays.asList(new Pair<>("key", Arrays.asList("value")));
        when(mockZoneAdditionDetailsService.findGroupBy()).thenReturn(pairs);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/zoneAdditionDetails/findGroupBy")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindGroupBy_ZoneAdditionDetailsServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockZoneAdditionDetailsService.findGroupBy()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/zoneAdditionDetails/findGroupBy")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
