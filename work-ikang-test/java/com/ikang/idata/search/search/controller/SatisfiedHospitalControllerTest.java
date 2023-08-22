package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.AreaAndHospital;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.Dname;
import com.ikang.idata.common.entity.HospitalAndName;
import com.ikang.idata.common.entity.vo.SatisFiedVO;
import com.ikang.idata.search.search.service.SatisfiedHospitalService;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SatisfiedHospitalController.class)
class SatisfiedHospitalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SatisfiedHospitalService mockSatisfiedHospitalService;

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        // Configure SatisfiedHospitalService.findGroupBy(...).
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
        when(mockSatisfiedHospitalService.findGroupBy(any(SatisFiedVO.class))).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/satisFied/findGroupBy")
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
        // Configure SatisfiedHospitalService.find(...).
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
        when(mockSatisfiedHospitalService.find(any(SatisFiedVO.class))).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/satisFied/find")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShow() throws Exception {
        // Setup
        // Configure SatisfiedHospitalService.show(...).
        final AreaAndHospital areaAndHospital = new AreaAndHospital();
        areaAndHospital.setFirstLetter("firstLetter");
        areaAndHospital.setAreaIdCode("areaIdCode");
        areaAndHospital.setAreaName("areaName");
        final HospitalAndName hospitalAndName = new HospitalAndName();
        hospitalAndName.setHospitalCode("hospitalCode");
        hospitalAndName.setHospitalName("hospitalName");
        areaAndHospital.setHospitals(Arrays.asList(hospitalAndName));
        areaAndHospital.setType(0);
        final List<AreaAndHospital> areaAndHospitals = Arrays.asList(areaAndHospital);
        when(mockSatisfiedHospitalService.show()).thenReturn(areaAndHospitals);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/satisFied/show")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShow_SatisfiedHospitalServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockSatisfiedHospitalService.show()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/satisFied/show")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowDname() throws Exception {
        // Setup
        // Configure SatisfiedHospitalService.showDname(...).
        final Dname dname = new Dname();
        dname.setDcode("dcode");
        dname.setDname("dname");
        dname.setDmapping("dmapping");
        dname.setDreasonmapping("dreasonmapping");
        dname.setFirstLetter("firstLetter");
        final List<Dname> dnames = Arrays.asList(dname);
        when(mockSatisfiedHospitalService.showDname()).thenReturn(dnames);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/satisFied/showDname")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowDname_SatisfiedHospitalServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockSatisfiedHospitalService.showDname()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/satisFied/showDname")
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
        final MockHttpServletResponse response = mockMvc.perform(post("/satisFied/path")
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
        final MockHttpServletResponse response = mockMvc.perform(post("/satisFied/path")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
