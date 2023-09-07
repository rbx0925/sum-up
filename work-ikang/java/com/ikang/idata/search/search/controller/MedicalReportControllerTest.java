package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.search.search.entity.vo.MedicalReportVo;
import com.ikang.idata.search.search.service.MedicalReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MedicalReportController.class)
class MedicalReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalReportService mockService;
    @MockBean
    private RestTemplate mockOkhttp3Resttemplate;

    @Test
    void testViewPdf() throws Exception {
        // Setup
        // Configure RestTemplate.exchange(...).
        final ResponseEntity<byte[]> responseEntity = new ResponseEntity<>("content".getBytes(), HttpStatus.OK);
        when(mockOkhttp3Resttemplate.exchange(eq("viewPdfUrl"), eq(HttpMethod.POST), eq(new HttpEntity<>(null)),
                eq(byte[].class), any(Object.class))).thenReturn(responseEntity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/medicalReport/viewPDF")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testViewPdf_RestTemplateThrowsRestClientException() throws Exception {
        // Setup
        when(mockOkhttp3Resttemplate.exchange(eq("viewPdfUrl"), eq(HttpMethod.POST), eq(new HttpEntity<>(null)),
                eq(byte[].class), any(Object.class))).thenThrow(RestClientException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/medicalReport/viewPDF")
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
        // Configure MedicalReportService.find(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        baseSearch.setColumnNames(Arrays.asList(new HashMap<>()));
        baseSearch.setSumOrAvgMap(new HashMap<>());
        baseSearch.setListMap(Arrays.asList(new HashMap<>()));
        baseSearch.setResourceId(0L);
        baseSearch.setDesensitization(false);
        baseSearch.setSort("sort");
        when(mockService.find(new MedicalReportVo())).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/medicalReport/find")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
