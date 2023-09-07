package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.search.search.entity.vo.RegisterDetailSearchVo;
import com.ikang.idata.search.search.entity.vo.RegistrationKanbanSearchVo;
import com.ikang.idata.search.search.entity.vo.RegistrationKanbanSummary;
import com.ikang.idata.search.search.service.RegistrationKanbanService;
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

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegistrationKanbanController.class)
class RegistrationKanbanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistrationKanbanService mockRegistrationKanbanService;

    @Test
    void testQueryKanban() throws Exception {
        // Setup
        // Configure RegistrationKanbanService.queryKanban(...).
        final RegistrationKanbanSearchVo vo = new RegistrationKanbanSearchVo();
        vo.setInstituteId("instituteId");
        vo.setRegistrationDate("registrationDate");
        when(mockRegistrationKanbanService.queryKanban(vo)).thenReturn(new RegistrationKanbanSummary());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/registrationKanban/kanban")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testQueryKanban_RegistrationKanbanServiceThrowsException() throws Exception {
        // Setup
        // Configure RegistrationKanbanService.queryKanban(...).
        final RegistrationKanbanSearchVo vo = new RegistrationKanbanSearchVo();
        vo.setInstituteId("instituteId");
        vo.setRegistrationDate("registrationDate");
        when(mockRegistrationKanbanService.queryKanban(vo)).thenThrow(Exception.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/registrationKanban/kanban")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure RegistrationKanbanService.find(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        final RegisterDetailSearchVo searchVo = new RegisterDetailSearchVo();
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        searchVo.setGroupBy(Arrays.asList(aggregationCondition));
        searchVo.setInstituteId("instituteId");
        searchVo.setRegistrationDate("registrationDate");
        searchVo.setDesensitization(false);
        when(mockRegistrationKanbanService.find(searchVo)).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/registrationKanban/find")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFind_RegistrationKanbanServiceThrowsException() throws Exception {
        // Setup
        // Configure RegistrationKanbanService.find(...).
        final RegisterDetailSearchVo searchVo = new RegisterDetailSearchVo();
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        searchVo.setGroupBy(Arrays.asList(aggregationCondition));
        searchVo.setInstituteId("instituteId");
        searchVo.setRegistrationDate("registrationDate");
        searchVo.setDesensitization(false);
        when(mockRegistrationKanbanService.find(searchVo)).thenThrow(Exception.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/registrationKanban/find")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        // Configure RegistrationKanbanService.findGroupBy(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        final RegisterDetailSearchVo searchVo = new RegisterDetailSearchVo();
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        searchVo.setGroupBy(Arrays.asList(aggregationCondition));
        searchVo.setInstituteId("instituteId");
        searchVo.setRegistrationDate("registrationDate");
        searchVo.setDesensitization(false);
        when(mockRegistrationKanbanService.findGroupBy(searchVo)).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/registrationKanban/findGroupBy")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportExcel() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/registrationKanban/exportExcel")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportExcel_ThrowsException() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/registrationKanban/exportExcel")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
