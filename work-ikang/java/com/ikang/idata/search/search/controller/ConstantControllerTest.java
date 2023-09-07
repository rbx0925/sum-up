package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.ReceiveUnit;
import com.ikang.idata.search.search.service.ConstantService;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConstantController.class)
class ConstantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConstantService mockConstantService;

    @Test
    void testReceiveUnit() throws Exception {
        // Setup
        // Configure ConstantService.queryAll(...).
        final ReceiveUnit receiveUnit = new ReceiveUnit();
        receiveUnit.setGroupOrganCode("groupOrganCode");
        receiveUnit.setGroupOrganName("groupOrganName");
        final List<ReceiveUnit> receiveUnits = Arrays.asList(receiveUnit);
        when(mockConstantService.queryAll()).thenReturn(receiveUnits);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/constant/receiveUnit")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testReceiveUnit_ConstantServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockConstantService.queryAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/constant/receiveUnit")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDistrictTotalArea() throws Exception {
        // Setup
        when(mockConstantService.districtTotalAreaQuery()).thenReturn(Arrays.asList("value"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/constant/districtTotalArea")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDistrictTotalArea_ConstantServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockConstantService.districtTotalAreaQuery()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/constant/districtTotalArea")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
