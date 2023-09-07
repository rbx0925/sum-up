package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.BRMResourceVO;
import com.ikang.idata.search.search.entity.vo.BRMZone;
import com.ikang.idata.search.search.entity.vo.BRMZoneDTO;
import com.ikang.idata.search.search.entity.vo.BRMZoneReturnVO;
import com.ikang.idata.search.search.service.BRMResourceService;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(BRMResourceController.class)
class BRMResourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BRMResourceService mockBrmResourceService;

    @Test
    void testShowBRMzone() throws Exception {
        // Setup
        when(mockBrmResourceService.findBRMzone(new BRMResourceVO())).thenReturn(Arrays.asList("value"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showBRMzone")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowBRMzone_BRMResourceServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBrmResourceService.findBRMzone(new BRMResourceVO())).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showBRMzone")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindBRMDepartment() throws Exception {
        // Setup
        when(mockBrmResourceService.findBRMDepartment(new BRMResourceVO())).thenReturn(Arrays.asList("value"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showBRMDepartment")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindBRMDepartment_BRMResourceServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBrmResourceService.findBRMDepartment(new BRMResourceVO())).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showBRMDepartment")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindBRMGroup() throws Exception {
        // Setup
        when(mockBrmResourceService.findBRMGroup(new BRMResourceVO())).thenReturn(Arrays.asList("value"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showBRMGroup")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindBRMGroup_BRMResourceServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBrmResourceService.findBRMGroup(new BRMResourceVO())).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showBRMGroup")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowBRMzoneById() throws Exception {
        // Setup
        when(mockBrmResourceService.findBRMzoneById(new BRMResourceVO())).thenReturn(Arrays.asList("value"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showBRMzoneById")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowBRMzoneById_BRMResourceServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBrmResourceService.findBRMzoneById(new BRMResourceVO())).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showBRMzoneById")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowBRMzoneByName() throws Exception {
        // Setup
        when(mockBrmResourceService.findBRMzoneByName(new BRMResourceVO())).thenReturn(Arrays.asList(new HashMap<>()));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showBRMzoneByName")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowBRMzoneByName_BRMResourceServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBrmResourceService.findBRMzoneByName(new BRMResourceVO())).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showBRMzoneByName")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowTypeToZone() throws Exception {
        // Setup
        // Configure BRMResourceService.showTypeToZone(...).
        final BRMZoneDTO brmZoneDTO = new BRMZoneDTO();
        final BRMZone brmZone = new BRMZone();
        brmZone.setBrmZone("brmZone");
        brmZoneDTO.setBrmZone(Arrays.asList(brmZone));
        brmZoneDTO.setProjectZoneType("projectZoneType");
        final List<BRMZoneDTO> brmZoneDTOS = Arrays.asList(brmZoneDTO);
        when(mockBrmResourceService.showTypeToZone(any(BRMZoneReturnVO.class))).thenReturn(brmZoneDTOS);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showTypeToZone")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testShowTypeToZone_BRMResourceServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockBrmResourceService.showTypeToZone(any(BRMZoneReturnVO.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/brmResource/showTypeToZone")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
