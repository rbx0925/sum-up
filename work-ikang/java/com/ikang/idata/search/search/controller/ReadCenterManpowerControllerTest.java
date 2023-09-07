package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.FilmSourceVo;
import com.ikang.idata.common.entity.vo.ReadCenterDoctorVo;
import com.ikang.idata.common.entity.vo.ReadCenterVo;
import com.ikang.idata.search.search.service.FilmSourceService;
import com.ikang.idata.search.search.service.ReadCenterService;
import com.ikang.idata.search.search.service.ReadDoctorService;
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
@WebMvcTest(ReadCenterManpowerController.class)
class ReadCenterManpowerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReadCenterService mockReadCenterService;
    @MockBean
    private FilmSourceService mockFilmSourceService;
    @MockBean
    private ReadDoctorService mockReadDoctorService;

    @Test
    void testFind1() throws Exception {
        // Setup
        // Configure ReadCenterService.find(...).
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
        when(mockReadCenterService.find(new ReadCenterVo())).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/readCenterManpower/readCenter")
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
        final MockHttpServletResponse response = mockMvc.perform(post("/readCenterManpower/ByReadCenter")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportExcel1_ThrowsException() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/readCenterManpower/ByReadCenter")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFind2() throws Exception {
        // Setup
        // Configure FilmSourceService.find(...).
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
        when(mockFilmSourceService.find(new FilmSourceVo())).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/readCenterManpower/filmSource")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportExcel2() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/readCenterManpower/ByFilmSource")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportExcel2_ThrowsException() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/readCenterManpower/ByFilmSource")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindByReadDoctor() throws Exception {
        // Setup
        // Configure ReadDoctorService.find(...).
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
        when(mockReadDoctorService.find(new ReadCenterDoctorVo())).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/readCenterManpower/readDoctor")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportExcelByReadDoctor() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/readCenterManpower/ByReadDoctor")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportExcelByReadDoctor_ThrowsException() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/readCenterManpower/ByReadDoctor")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
