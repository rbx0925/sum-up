package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.BranchAppointmentBoard;
import com.ikang.idata.common.entity.UserConfig;
import com.ikang.idata.common.entity.vo.BranchAppointmentBoardQo;
import com.ikang.idata.search.search.service.DepartmentAppointmentService;
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

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BranchAppointmentBoardController.class)
class BranchAppointmentBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentAppointmentService mockDepartmentAppointmentService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure DepartmentAppointmentService.queryKanban(...).
        final BranchAppointmentBoard branchAppointmentBoard = new BranchAppointmentBoard();
        branchAppointmentBoard.setLocidId("locidId");
        branchAppointmentBoard.setLocationnameName("locationnameName");
        branchAppointmentBoard.setHospId("hospId");
        branchAppointmentBoard.setHospName("hospName");
        branchAppointmentBoard.setRegdateDate("regdateDate");
        branchAppointmentBoard.setYuYueTotal("yuYueTotal");
        branchAppointmentBoard.setPiYueTotal("piYueTotal");
        branchAppointmentBoard.setPiYueTotalPercentage(new BigDecimal("0.00"));
        branchAppointmentBoard.setPiYueTotalElse("piYueTotalElse");
        branchAppointmentBoard.setPiYueTotalElsePercentage(new BigDecimal("0.00"));
        branchAppointmentBoard.setYuYueTotalPercentage(new BigDecimal("0.00"));
        branchAppointmentBoard.setTuanJianTotal("tuanJianTotal");
        branchAppointmentBoard.setTuanJianTotalPercentage(new BigDecimal("0.00"));
        branchAppointmentBoard.setSanKeTotal("sanKeTotal");
        branchAppointmentBoard.setSanKeTotalPercentage(new BigDecimal("0.00"));
        when(mockDepartmentAppointmentService.queryKanban(new BranchAppointmentBoardQo()))
                .thenReturn(branchAppointmentBoard);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/branchAppointmentBoard/v2/find")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetMaxAndMinMoney() throws Exception {
        // Setup
        // Configure DepartmentAppointmentService.getMaxAndMinMoney(...).
        final UserConfig userConfig = new UserConfig();
        userConfig.setId(0L);
        userConfig.setUserId(0L);
        userConfig.setDepartmentId("departmentId");
        userConfig.setMinMoney("minMoney");
        userConfig.setMaxMoney("maxMoney");
        when(mockDepartmentAppointmentService.getMaxAndMinMoney()).thenReturn(userConfig);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/branchAppointmentBoard/v2/getMaxAndMinMoney")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
