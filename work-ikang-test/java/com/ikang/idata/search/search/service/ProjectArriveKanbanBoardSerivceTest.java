package com.ikang.idata.search.search.service;

import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.vo.CheckinVo;
import com.ikang.idata.search.search.entity.dto.ProjectArriveKanbanDTO;
import com.ikang.idata.search.search.entity.vo.ProjectArrivefigVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
class ProjectArriveKanbanBoardSerivceTest {

    @Mock
    private ItemToCheckDetailsService mockCheckDetailsService;

    @InjectMocks
    private ProjectArriveKanbanBoardSerivce projectArriveKanbanBoardSerivceUnderTest;

    @Test
    void testFindKanban() {
        // Setup
        final CheckinVo vo = new CheckinVo();
        vo.setBrmUserId("brmUserId");
        vo.setChiefZone("chiefZone");
        vo.setProjectZoneType("projectZoneType");
        vo.setBrmZone("brmZone");
        vo.setBrmDepartment("brmDepartment");

        final ProjectArriveKanbanDTO expectedResult = new ProjectArriveKanbanDTO();
        expectedResult.setNoAppointmentNoToCheckNum(new BigDecimal("0.00"));
        expectedResult.setAppointmentNotCheckedNum(new BigDecimal("0.00"));
        expectedResult.setNotCheckedNum(new BigDecimal("0.00"));
        expectedResult.setNoAppointmentNoToCheckAmount(new BigDecimal("0.00"));
        expectedResult.setAppointmentNotCheckedAmount(new BigDecimal("0.00"));
        expectedResult.setNotCheckedAmount(new BigDecimal("0.00"));
        expectedResult.setNoAppointmentNoToCheckPhoneNum(new BigDecimal("0.00"));
        expectedResult.setAppointmentNotCheckedPhoneNum(new BigDecimal("0.00"));
        expectedResult.setNotCheckedPhoneNum(new BigDecimal("0.00"));
        expectedResult.setNoAppointmentNoToCheckPhoneAmount(new BigDecimal("0.00"));
        expectedResult.setAppointmentNotCheckedPhoneAmount(new BigDecimal("0.00"));
        expectedResult.setNotCheckedPhoneAmount(new BigDecimal("0.00"));
        expectedResult.setActivateCardNum(new BigDecimal("0.00"));
        expectedResult.setProjectTotalNum(new BigDecimal("0.00"));
        expectedResult.setActivationRate(new BigDecimal("0.00"));
        expectedResult.setArrivedNum(new BigDecimal("0.00"));
        expectedResult.setActivateCardTotalNum(new BigDecimal("0.00"));
        expectedResult.setActivateCardArrivalRate(new BigDecimal("0.00"));
        expectedResult.setArrivedCloseItemNum(new BigDecimal("0.00"));
        expectedResult.setActivateCardCloseItemTotalNum(new BigDecimal("0.00"));
        expectedResult.setActivateCardCloseItemArrivalRate(new BigDecimal("0.00"));

        when(mockCheckDetailsService.buildQuery(any(CheckinVo.class))).thenReturn(null);
        when(mockCheckDetailsService.buildPayCardSignQuery(any(CheckinVo.class))).thenReturn(null);

        // Run the test
        final ProjectArriveKanbanDTO result = projectArriveKanbanBoardSerivceUnderTest.findKanban(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildAgg() throws Exception {
        // Setup
        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        projectArriveKanbanBoardSerivceUnderTest.buildAgg(builder);

        // Verify the results
    }

    @Test
    void testParse() {
        // Setup
        final JSONObject object = new JSONObject(0, false);
        final ProjectArriveKanbanDTO expectedResult = new ProjectArriveKanbanDTO();
        expectedResult.setNoAppointmentNoToCheckNum(new BigDecimal("0.00"));
        expectedResult.setAppointmentNotCheckedNum(new BigDecimal("0.00"));
        expectedResult.setNotCheckedNum(new BigDecimal("0.00"));
        expectedResult.setNoAppointmentNoToCheckAmount(new BigDecimal("0.00"));
        expectedResult.setAppointmentNotCheckedAmount(new BigDecimal("0.00"));
        expectedResult.setNotCheckedAmount(new BigDecimal("0.00"));
        expectedResult.setNoAppointmentNoToCheckPhoneNum(new BigDecimal("0.00"));
        expectedResult.setAppointmentNotCheckedPhoneNum(new BigDecimal("0.00"));
        expectedResult.setNotCheckedPhoneNum(new BigDecimal("0.00"));
        expectedResult.setNoAppointmentNoToCheckPhoneAmount(new BigDecimal("0.00"));
        expectedResult.setAppointmentNotCheckedPhoneAmount(new BigDecimal("0.00"));
        expectedResult.setNotCheckedPhoneAmount(new BigDecimal("0.00"));
        expectedResult.setActivateCardNum(new BigDecimal("0.00"));
        expectedResult.setProjectTotalNum(new BigDecimal("0.00"));
        expectedResult.setActivationRate(new BigDecimal("0.00"));
        expectedResult.setArrivedNum(new BigDecimal("0.00"));
        expectedResult.setActivateCardTotalNum(new BigDecimal("0.00"));
        expectedResult.setActivateCardArrivalRate(new BigDecimal("0.00"));
        expectedResult.setArrivedCloseItemNum(new BigDecimal("0.00"));
        expectedResult.setActivateCardCloseItemTotalNum(new BigDecimal("0.00"));
        expectedResult.setActivateCardCloseItemArrivalRate(new BigDecimal("0.00"));

        // Run the test
        final ProjectArriveKanbanDTO result = projectArriveKanbanBoardSerivceUnderTest.parse(object);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetBigDecimal() {
        assertThat(projectArriveKanbanBoardSerivceUnderTest.getBigDecimal("jsonObject", "path"))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testFindChart() {
        // Setup
        final CheckinVo vo = new CheckinVo();
        vo.setBrmUserId("brmUserId");
        vo.setChiefZone("chiefZone");
        vo.setProjectZoneType("projectZoneType");
        vo.setBrmZone("brmZone");
        vo.setBrmDepartment("brmDepartment");

        final ProjectArrivefigVo expectedResult = new ProjectArrivefigVo();
        expectedResult.setProjectType(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        expectedResult.setProjectStatus(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));

        when(mockCheckDetailsService.buildQuery(any(CheckinVo.class))).thenReturn(null);

        // Run the test
        final ProjectArrivefigVo result = projectArriveKanbanBoardSerivceUnderTest.findChart(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testParseChartData() {
        // Setup
        final JSONObject object = new JSONObject(0, false);
        final ProjectArrivefigVo expectedResult = new ProjectArrivefigVo();
        expectedResult.setProjectType(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        expectedResult.setProjectStatus(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));

        // Run the test
        final ProjectArrivefigVo result = projectArriveKanbanBoardSerivceUnderTest.parseChartData(object);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetChart() {
        // Setup
        final JSONArray jsonArray = new JSONArray(Arrays.asList("value"));
        final List<Pair<String, List<Pair<String, BigDecimal>>>> expectedResult = Arrays.asList(
                new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00")))));

        // Run the test
        final List<Pair<String, List<Pair<String, BigDecimal>>>> result = projectArriveKanbanBoardSerivceUnderTest.getChart(
                jsonArray);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildAggChart() throws Exception {
        // Setup
        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        projectArriveKanbanBoardSerivceUnderTest.buildAggChart(builder);

        // Verify the results
    }
}
