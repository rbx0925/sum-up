package com.ikang.idata.search.search.controller;

import cn.hutool.core.lang.Pair;
import com.ikang.idata.common.entity.dto.CheckinAnalysisDTO;
import com.ikang.idata.common.entity.vo.CheckinVo;
import com.ikang.idata.search.search.common.PageResult;
import com.ikang.idata.search.search.entity.dto.ItemToCheckDetailsDTO;
import com.ikang.idata.search.search.entity.dto.ProjectArriveKanbanDTO;
import com.ikang.idata.search.search.entity.vo.CheckDataAnalysisVo;
import com.ikang.idata.search.search.entity.vo.CheckinAddPageVo;
import com.ikang.idata.search.search.entity.vo.ProjectArrivefigVo;
import com.ikang.idata.search.search.service.CheckDataAnalysisService;
import com.ikang.idata.search.search.service.ItemToCheckDetailsService;
import com.ikang.idata.search.search.service.ProjectArriveKanbanBoardSerivce;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegionalGeneralManagerToCheckController.class)
class RegionalGeneralManagerToCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CheckDataAnalysisService mockCheckDataAnalysisService;
    @MockBean
    private ItemToCheckDetailsService mockCheckDetailsService;
    @MockBean
    private ProjectArriveKanbanBoardSerivce mockKanbanBoardSerivce;

    @Test
    void testFindAgg() throws Exception {
        // Setup
        // Configure ProjectArriveKanbanBoardSerivce.findKanban(...).
        final ProjectArriveKanbanDTO projectArriveKanbanDTO = new ProjectArriveKanbanDTO();
        projectArriveKanbanDTO.setNoAppointmentNoToCheckNum(new BigDecimal("0.00"));
        projectArriveKanbanDTO.setAppointmentNotCheckedNum(new BigDecimal("0.00"));
        projectArriveKanbanDTO.setNotCheckedNum(new BigDecimal("0.00"));
        projectArriveKanbanDTO.setNoAppointmentNoToCheckAmount(new BigDecimal("0.00"));
        projectArriveKanbanDTO.setAppointmentNotCheckedAmount(new BigDecimal("0.00"));
        when(mockKanbanBoardSerivce.findKanban(any(CheckinVo.class))).thenReturn(projectArriveKanbanDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/toCheck/findKanban")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindHistogram() throws Exception {
        // Setup
        // Configure ProjectArriveKanbanBoardSerivce.findChart(...).
        final ProjectArrivefigVo projectArrivefigVo = new ProjectArrivefigVo();
        projectArrivefigVo.setProjectType(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        projectArrivefigVo.setProjectStatus(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        when(mockKanbanBoardSerivce.findChart(any(CheckinVo.class))).thenReturn(projectArrivefigVo);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/toCheck/findChart")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAnalysisOfIncomingData() throws Exception {
        // Setup
        // Configure CheckDataAnalysisService.analysisOfIncomingData(...).
        final PageResult<CheckinAnalysisDTO> checkinAnalysisDTOPageResult = new PageResult<>();
        checkinAnalysisDTOPageResult.setResult(Arrays.asList());
        checkinAnalysisDTOPageResult.setPageSize(0);
        checkinAnalysisDTOPageResult.setPageNum(0);
        checkinAnalysisDTOPageResult.setTotal(0);
        checkinAnalysisDTOPageResult.setTotalStr("totalStr");
        final CheckDataAnalysisVo vo = new CheckDataAnalysisVo();
        vo.setGroupType("groupType");
        vo.setBrmUserId("brmUserId");
        vo.setChiefZone("chiefZone");
        vo.setProjectZoneType("projectZoneType");
        vo.setBrmZone("brmZone");
        when(mockCheckDataAnalysisService.analysisOfIncomingData(vo)).thenReturn(checkinAnalysisDTOPageResult);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                post("/areaWorkTableOperate/toCheck/analysisOfIncomingData")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure ItemToCheckDetailsService.findAggByItemNo(...).
        final PageResult<ItemToCheckDetailsDTO> itemToCheckDetailsDTOPageResult = new PageResult<>();
        itemToCheckDetailsDTOPageResult.setResult(Arrays.asList());
        itemToCheckDetailsDTOPageResult.setPageSize(0);
        itemToCheckDetailsDTOPageResult.setPageNum(0);
        itemToCheckDetailsDTOPageResult.setTotal(0);
        itemToCheckDetailsDTOPageResult.setTotalStr("totalStr");
        final CheckinAddPageVo vo = new CheckinAddPageVo();
        vo.setBrmUserId("brmUserId");
        vo.setChiefZone("chiefZone");
        vo.setProjectZoneType("projectZoneType");
        vo.setBrmZone("brmZone");
        vo.setBrmDepartment("brmDepartment");
        when(mockCheckDetailsService.findAggByItemNo(vo)).thenReturn(itemToCheckDetailsDTOPageResult);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/toCheck/findAggByItemNo")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
