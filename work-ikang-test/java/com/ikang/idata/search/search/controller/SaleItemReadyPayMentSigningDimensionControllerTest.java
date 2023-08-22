package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.dto.ItemReadyPayMentSigningDimensionDTO;
import com.ikang.idata.common.entity.vo.ItemReadyPayMentSigningDetailVO;
import com.ikang.idata.common.entity.vo.ItemReadyPayMentSigningDimensionVO;
import com.ikang.idata.search.search.service.ItemReadyPayMentSigningDimensionService;
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
import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SaleItemReadyPayMentSigningDimensionController.class)
class SaleItemReadyPayMentSigningDimensionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemReadyPayMentSigningDimensionService mockItemReadyPayMentSigningDimensionService;

    @Test
    void testFindItemReadyPaymentAgg() throws Exception {
        // Setup
        // Configure ItemReadyPayMentSigningDimensionService.findAgg(...).
        final ItemReadyPayMentSigningDimensionDTO itemReadyPayMentSigningDimensionDTO = new ItemReadyPayMentSigningDimensionDTO();
        itemReadyPayMentSigningDimensionDTO.setCommittedPrepaymentValue(new BigDecimal("0.00"));
        itemReadyPayMentSigningDimensionDTO.setCommittedPrepaymentCount(new BigDecimal("0.00"));
        itemReadyPayMentSigningDimensionDTO.setSignedCount(new BigDecimal("0.00"));
        itemReadyPayMentSigningDimensionDTO.setPromiseReadyValue(new BigDecimal("0.00"));
        itemReadyPayMentSigningDimensionDTO.setPromiseReadyMoney(new BigDecimal("0.00"));
        itemReadyPayMentSigningDimensionDTO.setPromiseItemMoney(new BigDecimal("0.00"));
        itemReadyPayMentSigningDimensionDTO.setRealValue(new BigDecimal("0.00"));
        itemReadyPayMentSigningDimensionDTO.setRealMoney(new BigDecimal("0.00"));
        itemReadyPayMentSigningDimensionDTO.setRealPromiseValue(new BigDecimal("0.00"));
        itemReadyPayMentSigningDimensionDTO.setSignItemTotalMoney(new BigDecimal("0.00"));
        when(mockItemReadyPayMentSigningDimensionService.findAgg(new ItemReadyPayMentSigningDimensionVO()))
                .thenReturn(itemReadyPayMentSigningDimensionDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/SaleitemReadyPayMentSigningDimension/findAgg")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindItemReadyPayMent() throws Exception {
        // Setup
        // Configure ItemReadyPayMentSigningDimensionService.find(...).
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
        when(mockItemReadyPayMentSigningDimensionService.find(new ItemReadyPayMentSigningDetailVO()))
                .thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/SaleitemReadyPayMentSigningDimension/find")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDepartmentExportExcel() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                post("/SaleitemReadyPayMentSigningDimension/itemReadyPayMentSign")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testDepartmentExportExcel_ThrowsException() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                post("/SaleitemReadyPayMentSigningDimension/itemReadyPayMentSign")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
