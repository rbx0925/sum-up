package com.ikang.idata.search.search.javaTest;

import com.ikang.idata.common.entity.SignTheBillDimensionCount;
import com.ikang.idata.common.entity.dto.BillDailyDataAreaDTO;
import com.ikang.idata.common.entity.dto.ChanceAnalyseDTO;
import com.ikang.idata.common.entity.dto.DevelopmentDailyDTO;
import com.ikang.idata.common.entity.dto.SignTheBillDimensionDTO;
import com.ikang.idata.common.entity.vo.AreaWorkTableSignCheckVO;
import com.ikang.idata.search.search.controller.SaleWorkTableOperateController;
import com.ikang.idata.search.search.entity.vo.SignResultVo;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import com.ikang.idata.search.search.service.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SaleWorkTableOperateController.class)
class SaleWorkTableOperateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManagerWorkTableOperateService mockManagerWorkTableOperateService;
    @MockBean
    private SignService mockSignService;
    @MockBean
    private SignTheBillDimensionService mockSignTheBillDimensionService;
    @MockBean
    private ChanceAnalyseService mockChanceAnalyseService;
    @MockBean
    private AreaWorkTableOperateService mockAreaWorkTableOperateService;
    @MockBean
    private BillDetailService mockBillDetailService;

    @Test
    void testFindSignCheck() throws Exception {
        // Setup
        // Configure ManagerWorkTableOperateService.findSignCheck(...).
        final AreaWorkTableSignCheckVO areaWorkTableSignCheckVO = new AreaWorkTableSignCheckVO();
        areaWorkTableSignCheckVO.setIncome(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setSameTermIncome(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setIncomeRate(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setLocalCheckIncome(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setSelfIncomeRate(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setPackageReceivableAmount(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setSameTermPackageReceivableAmount(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setPackageReceivableAmountRate(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setAdditionalReceivableAmount(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setSameTermAdditionalReceivableAmount(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setAdditionalReceivableAmountRate(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setUnitPrice(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setSameTermUnitPrice(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setUnitPriceRate(new BigDecimal("0.00"));
        areaWorkTableSignCheckVO.setPackageUnitPrice(new BigDecimal("0.00"));
        when(mockManagerWorkTableOperateService.findSignCheck(new WorkTableSearchVo()))
                .thenReturn(areaWorkTableSignCheckVO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/saleWorkTableOperate/sale/findSignCheck")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindSignKanbanMarketSignDaily() throws Exception {
        // Setup
        // Configure SignService.findSignKanbanSignDaily(...).
        final SignResultVo signResultVo = new SignResultVo();
        signResultVo.setSignMoneySum(new BigDecimal("0.00"));
        signResultVo.setSignProjectIdCount(new BigDecimal("0.00"));
        signResultVo.setWorknosSum(new BigDecimal("0.00"));
        signResultVo.setAvgOrderMoney(new BigDecimal("0.00"));
        signResultVo.setAvgCustomerUnitPrice(new BigDecimal("0.00"));
        signResultVo.setAvgSignDiscount(new BigDecimal("0.00"));
        signResultVo.setSignMoneySumLastYear(new BigDecimal("0.00"));
        signResultVo.setSignProjectIdCountLastYear(new BigDecimal("0.00"));
        signResultVo.setWorknosSumLastYear(new BigDecimal("0.00"));
        signResultVo.setAvgOrderMoneyLastYear(new BigDecimal("0.00"));
        signResultVo.setAvgCustomerUnitPriceLastYear(new BigDecimal("0.00"));
        signResultVo.setAvgSignDiscountLastYear(new BigDecimal("0.00"));
        signResultVo.setSignMoneySumGrowthRate(new BigDecimal("0.00"));
        signResultVo.setSignProjectIdCountGrowthRate(new BigDecimal("0.00"));
        signResultVo.setWorknosSumGrowthRate(new BigDecimal("0.00"));
        when(mockSignService.findSignKanbanSignDaily(new WorkTableSearchVo())).thenReturn(signResultVo);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        post("/saleWorkTableOperate/sale/findSignKanban/market/signDaily")
                                .content("content").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindSignKanbanMarketAnalysis() throws Exception {
        // Setup
        // Configure SignService.findSignKanban(...).
        final SignResultVo signResultVo = new SignResultVo();
        signResultVo.setSignMoneySum(new BigDecimal("0.00"));
        signResultVo.setSignProjectIdCount(new BigDecimal("0.00"));
        signResultVo.setWorknosSum(new BigDecimal("0.00"));
        signResultVo.setAvgOrderMoney(new BigDecimal("0.00"));
        signResultVo.setAvgCustomerUnitPrice(new BigDecimal("0.00"));
        signResultVo.setAvgSignDiscount(new BigDecimal("0.00"));
        signResultVo.setSignMoneySumLastYear(new BigDecimal("0.00"));
        signResultVo.setSignProjectIdCountLastYear(new BigDecimal("0.00"));
        signResultVo.setWorknosSumLastYear(new BigDecimal("0.00"));
        signResultVo.setAvgOrderMoneyLastYear(new BigDecimal("0.00"));
        signResultVo.setAvgCustomerUnitPriceLastYear(new BigDecimal("0.00"));
        signResultVo.setAvgSignDiscountLastYear(new BigDecimal("0.00"));
        signResultVo.setSignMoneySumGrowthRate(new BigDecimal("0.00"));
        signResultVo.setSignProjectIdCountGrowthRate(new BigDecimal("0.00"));
        signResultVo.setWorknosSumGrowthRate(new BigDecimal("0.00"));
        when(mockSignService.findSignKanban(new WorkTableSearchVo())).thenReturn(signResultVo);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        post("/saleWorkTableOperate/sale/findSignKanban/market/analysisOfNewAndOldOrders")
                                .content("content").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetCustomerTypeDataDirector() throws Exception {
        // Setup
        // Configure SignTheBillDimensionService.makeDate(...).
        final SignTheBillDimensionDTO signTheBillDimensionDTO = new SignTheBillDimensionDTO(
                Arrays.asList(new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));
        when(mockSignTheBillDimensionService.makeDate(new WorkTableSearchVo(), "fields"))
                .thenReturn(signTheBillDimensionDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        post("/saleWorkTableOperate/sale/getCustomerTypeDataDirector")
                                .content("content").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetIndustryTypeDataDirector() throws Exception {
        // Setup
        // Configure SignTheBillDimensionService.makeDate(...).
        final SignTheBillDimensionDTO signTheBillDimensionDTO = new SignTheBillDimensionDTO(
                Arrays.asList(new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));
        when(mockSignTheBillDimensionService.makeDate(new WorkTableSearchVo(), "fields"))
                .thenReturn(signTheBillDimensionDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        post("/saleWorkTableOperate/sale/getIndustryTypeDataDirector")
                                .content("content").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetProjectScaleDataDirector() throws Exception {
        // Setup
        // Configure SignTheBillDimensionService.makeDate(...).
        final SignTheBillDimensionDTO signTheBillDimensionDTO = new SignTheBillDimensionDTO(
                Arrays.asList(new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));
        when(mockSignTheBillDimensionService.makeDate(new WorkTableSearchVo(), "fields"))
                .thenReturn(signTheBillDimensionDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        post("/saleWorkTableOperate/sale/getProjectScaleDataDirector")
                                .content("content").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetNewOldTypeDataDirector() throws Exception {
        // Setup
        // Configure SignTheBillDimensionService.makeDate(...).
        final SignTheBillDimensionDTO signTheBillDimensionDTO = new SignTheBillDimensionDTO(
                Arrays.asList(new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));
        when(mockSignTheBillDimensionService.makeDate(new WorkTableSearchVo(), "fields"))
                .thenReturn(signTheBillDimensionDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        post("/saleWorkTableOperate/sale/getNewOldTypeDataDirector")
                                .content("content").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetNewSourceDataDirector() throws Exception {
        // Setup
        // Configure SignTheBillDimensionService.makeDate(...).
        final SignTheBillDimensionDTO signTheBillDimensionDTO = new SignTheBillDimensionDTO(
                Arrays.asList(new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));
        when(mockSignTheBillDimensionService.makeDate(new WorkTableSearchVo(), "fields", false))
                .thenReturn(signTheBillDimensionDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        post("/saleWorkTableOperate/sale/getNewSourceDataDirector")
                                .content("content").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetChanceAnalyseDataDirector() throws Exception {
        // Setup
        // Configure ChanceAnalyseService.makeDate(...).
        final ChanceAnalyseDTO chanceAnalyseDTO = new ChanceAnalyseDTO(0L, new BigDecimal("0.00"),
                new BigDecimal("0.00"), new BigDecimal("0.00"), 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                new BigDecimal("0.00"), 0L, new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"));
        when(mockChanceAnalyseService.makeDate(new WorkTableSearchVo())).thenReturn(chanceAnalyseDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        post("/saleWorkTableOperate/sale/getChanceAnalyseDataDirector")
                                .content("content").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetBillDailyDataArea() throws Exception {
        // Setup
        // Configure AreaWorkTableOperateService.getBillDailyDataArea(...).
        final BillDailyDataAreaDTO billDailyDataAreaDTO = new BillDailyDataAreaDTO(new BigDecimal("0.00"), 0L,
                new BigDecimal("0.00"), 0L, new BigDecimal("0.00"), 0L, new BigDecimal("0.00"), 0L);
        when(mockAreaWorkTableOperateService.getBillDailyDataArea(new WorkTableSearchVo()))
                .thenReturn(billDailyDataAreaDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/saleWorkTableOperate/getBillDailyDataArea")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetDevelopmentDailyDataArea() throws Exception {
        // Setup
        // Configure AreaWorkTableOperateService.getDevelopmentDailyDataArea(...).
        final DevelopmentDailyDTO developmentDailyDTO = new DevelopmentDailyDTO(0L, 0L, new BigDecimal("0.00"), 0L, 0L,
                new BigDecimal("0.00"));
        when(mockAreaWorkTableOperateService.getDevelopmentDailyDataArea(new WorkTableSearchVo()))
                .thenReturn(developmentDailyDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        post("/saleWorkTableOperate/getDevelopmentDailyDataArea")
                                .content("content").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

}
