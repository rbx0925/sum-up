package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.UserChannel;
import com.ikang.idata.common.entity.vo.*;
import com.ikang.idata.search.search.service.VaccinumSubjectService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VaccinumController.class)
class VaccinumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VaccinumSubjectService mockVaccinumSubjectService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure VaccinumSubjectService.find(...).
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
        when(mockVaccinumSubjectService.find(any(VaccinumSubjectVO.class))).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/vaccinumSubject/find")
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
        // Configure VaccinumSubjectService.show(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmZone.setBrmZone("brmZone");
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        brmTypeToChiefZone.setProjectZoneType("projectZoneType");
        final BrmChiefZone brmChiefZone = new BrmChiefZone();
        brmChiefZone.setChiefZone("chiefZone");
        brmTypeToChiefZone.setChiefZone(Arrays.asList(brmChiefZone));
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        when(mockVaccinumSubjectService.show()).thenReturn(userChannelAndHospital);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/vaccinumSubject/show")
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
        final MockHttpServletResponse response = mockMvc.perform(post("/vaccinumSubject/path")
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
        final MockHttpServletResponse response = mockMvc.perform(post("/vaccinumSubject/path")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
