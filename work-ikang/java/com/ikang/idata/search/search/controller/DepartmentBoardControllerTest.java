package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.DepartmentRankDetailSearchVO;
import com.ikang.idata.common.entity.vo.DepartmentRankResultDTO;
import com.ikang.idata.common.entity.vo.DepartmentRankSearchVO;
import com.ikang.idata.search.search.service.DepartmentRankAppointmentService;
import com.ikang.idata.search.search.service.DepartmentRankRegistrationService;
import com.ikang.idata.search.search.service.DepartmentRankService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DepartmentBoardController.class)
class DepartmentBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentRankService mockDepartmentRankService;
    @MockBean
    private DepartmentRankRegistrationService mockDepartmentRankRegistrationService;
    @MockBean
    private DepartmentRankAppointmentService mockDepartmentRankAppointmentService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure DepartmentRankService.find(...).
        final List<DepartmentRankResultDTO> departmentRankResultDTOS = Arrays.asList(
                new DepartmentRankResultDTO("departmentCode", "departmentName"));
        final DepartmentRankSearchVO vo = new DepartmentRankSearchVO();
        vo.setLocIdExport("locIdExport");
        vo.setAreanameId("areanameId");
        vo.setHospId("hospId");
        vo.setDate("date");
        vo.setResourceId(0L);
        when(mockDepartmentRankService.find(vo)).thenReturn(departmentRankResultDTOS);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard/find")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFind_DepartmentRankServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure DepartmentRankService.find(...).
        final DepartmentRankSearchVO vo = new DepartmentRankSearchVO();
        vo.setLocIdExport("locIdExport");
        vo.setAreanameId("areanameId");
        vo.setHospId("hospId");
        vo.setDate("date");
        vo.setResourceId(0L);
        when(mockDepartmentRankService.find(vo)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard/find")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFind_DepartmentRankServiceThrowsException() throws Exception {
        // Setup
        // Configure DepartmentRankService.find(...).
        final DepartmentRankSearchVO vo = new DepartmentRankSearchVO();
        vo.setLocIdExport("locIdExport");
        vo.setAreanameId("areanameId");
        vo.setHospId("hospId");
        vo.setDate("date");
        vo.setResourceId(0L);
        when(mockDepartmentRankService.find(vo)).thenThrow(Exception.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard/find")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDepartmentExportExcel1() throws Exception {
        // Setup
        // Configure DepartmentRankService.getAllHospital(...).
        final List<DepartmentRankResultDTO> departmentRankResultDTOS = Arrays.asList(
                new DepartmentRankResultDTO("departmentCode", "departmentName"));
        final DepartmentRankSearchVO vo = new DepartmentRankSearchVO();
        vo.setLocIdExport("locIdExport");
        vo.setAreanameId("areanameId");
        vo.setHospId("hospId");
        vo.setDate("date");
        vo.setResourceId(0L);
        when(mockDepartmentRankService.getAllHospital(vo)).thenReturn(departmentRankResultDTOS);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDepartmentExportExcel1_DepartmentRankServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure DepartmentRankService.getAllHospital(...).
        final DepartmentRankSearchVO vo = new DepartmentRankSearchVO();
        vo.setLocIdExport("locIdExport");
        vo.setAreanameId("areanameId");
        vo.setHospId("hospId");
        vo.setDate("date");
        vo.setResourceId(0L);
        when(mockDepartmentRankService.getAllHospital(vo)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDepartmentExportExcel1_DepartmentRankServiceThrowsException() throws Exception {
        // Setup
        // Configure DepartmentRankService.getAllHospital(...).
        final DepartmentRankSearchVO vo = new DepartmentRankSearchVO();
        vo.setLocIdExport("locIdExport");
        vo.setAreanameId("areanameId");
        vo.setHospId("hospId");
        vo.setDate("date");
        vo.setResourceId(0L);
        when(mockDepartmentRankService.getAllHospital(vo)).thenThrow(Exception.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDepartmentKanbanWholeExport() throws Exception {
        // Setup
        // Configure DepartmentRankService.find(...).
        final List<DepartmentRankResultDTO> departmentRankResultDTOS = Arrays.asList(
                new DepartmentRankResultDTO("departmentCode", "departmentName"));
        final DepartmentRankSearchVO vo = new DepartmentRankSearchVO();
        vo.setLocIdExport("locIdExport");
        vo.setAreanameId("areanameId");
        vo.setHospId("hospId");
        vo.setDate("date");
        vo.setResourceId(0L);
        when(mockDepartmentRankService.find(vo)).thenReturn(departmentRankResultDTOS);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDepartmentKanbanWholeExport_DepartmentRankServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure DepartmentRankService.find(...).
        final DepartmentRankSearchVO vo = new DepartmentRankSearchVO();
        vo.setLocIdExport("locIdExport");
        vo.setAreanameId("areanameId");
        vo.setHospId("hospId");
        vo.setDate("date");
        vo.setResourceId(0L);
        when(mockDepartmentRankService.find(vo)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDepartmentKanbanWholeExport_DepartmentRankServiceThrowsException() throws Exception {
        // Setup
        // Configure DepartmentRankService.find(...).
        final DepartmentRankSearchVO vo = new DepartmentRankSearchVO();
        vo.setLocIdExport("locIdExport");
        vo.setAreanameId("areanameId");
        vo.setHospId("hospId");
        vo.setDate("date");
        vo.setResourceId(0L);
        when(mockDepartmentRankService.find(vo)).thenThrow(Exception.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindAppointmentDetail() throws Exception {
        // Setup
        // Configure DepartmentRankAppointmentService.find(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        final DepartmentRankDetailSearchVO departmentRankDetailSearchVO = new DepartmentRankDetailSearchVO();
        departmentRankDetailSearchVO.setHospId("hospId");
        departmentRankDetailSearchVO.setDepartmentName("departmentName");
        departmentRankDetailSearchVO.setDepartmentCode("departmentCode");
        departmentRankDetailSearchVO.setCheckItemCode("checkItemCode");
        departmentRankDetailSearchVO.setItemStatus("itemStatus");
        when(mockDepartmentRankAppointmentService.find(departmentRankDetailSearchVO)).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard/appointmentDetail")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindAppointmentDetailGroupBy() throws Exception {
        // Setup
        // Configure DepartmentRankAppointmentService.findGroupBy(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        final DepartmentRankDetailSearchVO searchVo = new DepartmentRankDetailSearchVO();
        searchVo.setHospId("hospId");
        searchVo.setDepartmentName("departmentName");
        searchVo.setDepartmentCode("departmentCode");
        searchVo.setCheckItemCode("checkItemCode");
        searchVo.setItemStatus("itemStatus");
        when(mockDepartmentRankAppointmentService.findGroupBy(searchVo)).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard/appointmentDetailGroupBy")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindRegistrationDetail() throws Exception {
        // Setup
        // Configure DepartmentRankRegistrationService.find(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        final DepartmentRankDetailSearchVO departmentRankDetailSearchVO = new DepartmentRankDetailSearchVO();
        departmentRankDetailSearchVO.setHospId("hospId");
        departmentRankDetailSearchVO.setDepartmentName("departmentName");
        departmentRankDetailSearchVO.setDepartmentCode("departmentCode");
        departmentRankDetailSearchVO.setCheckItemCode("checkItemCode");
        departmentRankDetailSearchVO.setItemStatus("itemStatus");
        when(mockDepartmentRankRegistrationService.find(departmentRankDetailSearchVO)).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard/registrationDetail")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindRegistrationDetailGroupBy() throws Exception {
        // Setup
        // Configure DepartmentRankRegistrationService.findGroupBy(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        final DepartmentRankDetailSearchVO searchVo = new DepartmentRankDetailSearchVO();
        searchVo.setHospId("hospId");
        searchVo.setDepartmentName("departmentName");
        searchVo.setDepartmentCode("departmentCode");
        searchVo.setCheckItemCode("checkItemCode");
        searchVo.setItemStatus("itemStatus");
        when(mockDepartmentRankRegistrationService.findGroupBy(searchVo)).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard/registrationDetailGroupBy")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAppointmenExportExcel() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard/exportExcel")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAppointmenExportExcel_ThrowsException() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard/exportExcel")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testRegistrationExportExcel() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testRegistrationExportExcel_ThrowsException() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/departmentBoard")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
