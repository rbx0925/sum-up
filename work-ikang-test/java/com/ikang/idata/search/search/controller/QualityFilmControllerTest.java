package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.QualityFilm;
import com.ikang.idata.common.entity.vo.LocAndInstituteVO;
import com.ikang.idata.common.entity.vo.QualityFilmVo;
import com.ikang.idata.search.search.service.QualityFilmService;
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
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(QualityFilmController.class)
class QualityFilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QualityFilmService mockQualityFilmService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure QualityFilmService.find(...).
        final QualityFilm qualityFilm = new QualityFilm();
        qualityFilm.setPageSize(0);
        qualityFilm.setPageNum(0);
        qualityFilm.setTotalStr("totalStr");
        qualityFilm.setTotal(0);
        qualityFilm.setColumnNames(Arrays.asList());
        when(mockQualityFilmService.find(any(QualityFilmVo.class))).thenReturn(qualityFilm);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/quality/find")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindDoctorGroupBy() throws Exception {
        // Setup
        when(mockQualityFilmService.findDoctorGroupBy(any(LocAndInstituteVO.class))).thenReturn(Arrays.asList("value"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/quality/findDoctorGroupBy")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindDoctorGroupBy_QualityFilmServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockQualityFilmService.findDoctorGroupBy(any(LocAndInstituteVO.class)))
                .thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/quality/findDoctorGroupBy")
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
        final MockHttpServletResponse response = mockMvc.perform(post("/quality/exportExcel")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockQualityFilmService).exportExcel(any(QualityFilmVo.class), any(HttpServletResponse.class));
    }

    @Test
    void testExportExcel_QualityFilmServiceThrowsException() throws Exception {
        // Setup
        doThrow(Exception.class).when(mockQualityFilmService).exportExcel(any(QualityFilmVo.class),
                any(HttpServletResponse.class));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/quality/exportExcel")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
