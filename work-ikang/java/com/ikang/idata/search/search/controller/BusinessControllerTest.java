package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.CiTy;
import com.ikang.idata.common.entity.TradeName;
import com.ikang.idata.common.entity.vo.OpportunityVO;
import com.ikang.idata.common.entity.vo.SiginingVO;
import com.ikang.idata.search.search.service.BusinessOpportunityService;
import com.ikang.idata.search.search.service.BusinessService;
import com.ikang.idata.search.search.service.BusinessSigningService;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BusinessController.class)
class BusinessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BusinessSigningService mockBusinessSigningService;
    @MockBean
    private BusinessService mockBusinessService;
    @MockBean
    private BusinessOpportunityService mockBusinessOpportunityService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure BusinessService.findTrade(...).
        final List<TradeName> tradeNames = Arrays.asList(new TradeName("tradeName"));
        when(mockBusinessService.findTrade()).thenReturn(tradeNames);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/business/show")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFind_BusinessServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBusinessService.findTrade()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/business/show")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowCiTy() throws Exception {
        // Setup
        when(mockBusinessService.showCiTy("daQu", 0)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/business/showCiTy/{daQu}/{daquCode}", "daQu", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowCiTy_BusinessServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBusinessService.showCiTy("daQu", 0)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/business/showCiTy/{daQu}/{daquCode}", "daQu", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowEnum() throws Exception {
        // Setup
        when(mockBusinessService.showEnum()).thenReturn(new HashMap<>());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/business/showEnum")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindOpportunity() throws Exception {
        // Setup
        // Configure BusinessOpportunityService.findOpportunity(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        when(mockBusinessOpportunityService.findOpportunity(any(OpportunityVO.class))).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/business/findOpportunity")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindSigning() throws Exception {
        // Setup
        // Configure BusinessSigningService.findSigining(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        when(mockBusinessSigningService.findSigining(any(SiginingVO.class))).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/business/findSigining")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportExcel1() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/business/exportExcel/opportunity")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockBusinessOpportunityService).exportExcelOpportunity(any(OpportunityVO.class),
                any(HttpServletResponse.class));
    }

    @Test
    void testExportExcel1_BusinessOpportunityServiceThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockBusinessOpportunityService).exportExcelOpportunity(any(OpportunityVO.class),
                any(HttpServletResponse.class));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/business/exportExcel/opportunity")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportExcel2() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/business/exportExcel/sigining")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockBusinessSigningService).exportExcelSiginng(any(SiginingVO.class), any(HttpServletResponse.class));
    }

    @Test
    void testExportExcel2_BusinessSigningServiceThrowsIOException() throws Exception {
        // Setup
        doThrow(IOException.class).when(mockBusinessSigningService).exportExcelSiginng(any(SiginingVO.class),
                any(HttpServletResponse.class));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/business/exportExcel/sigining")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
