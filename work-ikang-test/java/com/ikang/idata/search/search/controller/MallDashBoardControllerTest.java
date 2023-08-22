package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.ECommerceBillboardDetails;
import com.ikang.idata.common.entity.ElectricityBoardFilm;
import com.ikang.idata.common.entity.vo.DataDetailsQo;
import com.ikang.idata.common.entity.vo.ElectricityBoardVo;
import com.ikang.idata.common.entity.vo.PieceVO;
import com.ikang.idata.search.search.service.MallDashBoardService;
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

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MallDashBoardController.class)
class MallDashBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MallDashBoardService mockElectricityBoardService;

    @Test
    void testFind() throws Exception {
        // Setup
        // Configure MallDashBoardService.find(...).
        final PieceVO pieceVO = new PieceVO();
        pieceVO.setHeader("header");
        pieceVO.setValue("value");
        pieceVO.setDate("date");
        pieceVO.setUnit("unit");
        pieceVO.setRingRatioType("ringRatioType");
        pieceVO.setRingRatio("ringRatio");
        pieceVO.setYearOnYearType("yearOnYearType");
        pieceVO.setYearOnYear("yearOnYear");
        pieceVO.setRemark("remark");
        final List<ElectricityBoardFilm> electricityBoardFilms = Arrays.asList(
                new ElectricityBoardFilm("typeName", Arrays.asList(pieceVO), "purchaseChannelCode", "purchaseDate",
                        "saleAmount", "incomeAmount", "cancelAmount", "saleNum", "saleCardPrice", "salePhone",
                        "personalNum", "salePhonePrice", "regNum", "checkNum", "reportNum"));
        when(mockElectricityBoardService.find(any(ElectricityBoardVo.class))).thenReturn(electricityBoardFilms);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/electricityBoard/find")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFind_MallDashBoardServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockElectricityBoardService.find(any(ElectricityBoardVo.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/electricityBoard/find")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFind1() throws Exception {
        // Setup
        // Configure MallDashBoardService.find1(...).
        final PieceVO pieceVO = new PieceVO();
        pieceVO.setHeader("header");
        pieceVO.setValue("value");
        pieceVO.setDate("date");
        pieceVO.setUnit("unit");
        pieceVO.setRingRatioType("ringRatioType");
        pieceVO.setRingRatio("ringRatio");
        pieceVO.setYearOnYearType("yearOnYearType");
        pieceVO.setYearOnYear("yearOnYear");
        pieceVO.setRemark("remark");
        final List<ElectricityBoardFilm> electricityBoardFilms = Arrays.asList(
                new ElectricityBoardFilm("typeName", Arrays.asList(pieceVO), "purchaseChannelCode", "purchaseDate",
                        "saleAmount", "incomeAmount", "cancelAmount", "saleNum", "saleCardPrice", "salePhone",
                        "personalNum", "salePhonePrice", "regNum", "checkNum", "reportNum"));
        when(mockElectricityBoardService.find1(any(ElectricityBoardVo.class))).thenReturn(electricityBoardFilms);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/electricityBoard/find1")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFind1_MallDashBoardServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockElectricityBoardService.find1(any(ElectricityBoardVo.class))).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/electricityBoard/find1")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetDetails() throws Exception {
        // Setup
        // Configure MallDashBoardService.getDetails(...).
        final ECommerceBillboardDetails eCommerceBillboardDetails = new ECommerceBillboardDetails();
        eCommerceBillboardDetails.setIndexName("indexName");
        eCommerceBillboardDetails.setIndexUnit("indexUnit");
        final ECommerceBillboardDetails.Detail detail = new ECommerceBillboardDetails.Detail();
        detail.setTime("time");
        detail.setValue("value");
        detail.setValueStr("valueStr");
        detail.setUnit("unit");
        detail.setYearOnYear("yearOnYear");
        detail.setYearOnYearType("yearOnYearType");
        detail.setChainRatio("chainRatio");
        detail.setChainRatioType("chainRatioType");
        eCommerceBillboardDetails.setDetailList(Arrays.asList(detail));
        when(mockElectricityBoardService.getDetails(new DataDetailsQo())).thenReturn(eCommerceBillboardDetails);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/electricityBoard/getDetails")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetDetails_MallDashBoardServiceThrowsParseException() throws Exception {
        // Setup
        when(mockElectricityBoardService.getDetails(new DataDetailsQo())).thenThrow(ParseException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/electricityBoard/getDetails")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
