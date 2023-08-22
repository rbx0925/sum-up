package com.ikang.idata.search.search.aop;

import com.ikang.idata.common.entity.SignTheBillDimensionCount;
import com.ikang.idata.common.entity.dto.*;
import com.ikang.idata.common.entity.vo.AreaWorkTableCheckVO;
import com.ikang.idata.common.entity.vo.AreaWorkTableSignCheckVO;
import com.ikang.idata.common.entity.vo.BillDetailDTOData;
import com.ikang.idata.common.entity.vo.BillDetailVo;
import com.ikang.idata.search.search.controller.AreaWorkTableOperateController;
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
@WebMvcTest(AreaWorkTableOperateController.class)
class AreaWorkTableOperateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AreaWorkTableOperateService mockAreaWorkTableOperateService;
    @MockBean
    private SignService mockSignService;
    @MockBean
    private SignTheBillDimensionService mockSignTheBillDimensionService;
    @MockBean
    private ChanceAnalyseService mockChanceAnalyseService;
    @MockBean
    private BillDetailService mockBillDetailService;

    @Test
    void testFindCheck() throws Exception {
        // Setup
        // Configure AreaWorkTableOperateService.findCheck(...).
        final AreaWorkTableCheckVO areaWorkTableCheckVO = new AreaWorkTableCheckVO();
        areaWorkTableCheckVO.setCheckIncome(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setSameTermCheckIncome(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setCheckIncomeRate(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setSelfIncome(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setSelfIncomeRate(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setPackageIncome(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setSameTermPackageIncome(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setPackageIncomeRate(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setAdditionalIncome(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setSameTermAdditionalIncome(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setAdditionalIncomeRate(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setUnitPrice(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setSameTermUnitPrice(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setUnitPriceRate(new BigDecimal("0.00"));
        areaWorkTableCheckVO.setPackageUnitPrice(new BigDecimal("0.00"));
        when(mockAreaWorkTableOperateService.findCheck(new WorkTableSearchVo())).thenReturn(areaWorkTableCheckVO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/findCheck")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindSignCheck() throws Exception {
        // Setup
        // Configure AreaWorkTableOperateService.findSignCheck(...).
        final AreaWorkTableSignCheckVO signCheckVO = new AreaWorkTableSignCheckVO();
        signCheckVO.setIncome(new BigDecimal("0.00"));
        signCheckVO.setSameTermIncome(new BigDecimal("0.00"));
        signCheckVO.setIncomeRate(new BigDecimal("0.00"));
        signCheckVO.setLocalCheckIncome(new BigDecimal("0.00"));
        signCheckVO.setSelfIncomeRate(new BigDecimal("0.00"));
        signCheckVO.setPackageReceivableAmount(new BigDecimal("0.00"));
        signCheckVO.setSameTermPackageReceivableAmount(new BigDecimal("0.00"));
        signCheckVO.setPackageReceivableAmountRate(new BigDecimal("0.00"));
        signCheckVO.setAdditionalReceivableAmount(new BigDecimal("0.00"));
        signCheckVO.setSameTermAdditionalReceivableAmount(new BigDecimal("0.00"));
        signCheckVO.setAdditionalReceivableAmountRate(new BigDecimal("0.00"));
        signCheckVO.setUnitPrice(new BigDecimal("0.00"));
        signCheckVO.setSameTermUnitPrice(new BigDecimal("0.00"));
        signCheckVO.setUnitPriceRate(new BigDecimal("0.00"));
        signCheckVO.setPackageUnitPrice(new BigDecimal("0.00"));
        when(mockAreaWorkTableOperateService.findSignCheck(new WorkTableSearchVo(), 0)).thenReturn(signCheckVO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/findSignCheck")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindSignKanbanDistrictHeadSignDaily() throws Exception {
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
                post("/areaWorkTableOperate/findSignKanban/districtHead/signDaily")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindSignKanbanDistrictHeadAnalysis() throws Exception {
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
                post("/areaWorkTableOperate/findSignKanban/districtHead/analysisOfNewAndOldOrders")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetCustomerTypeDataArea() throws Exception {
        // Setup
        // Configure SignTheBillDimensionService.makeDate(...).
        final SignTheBillDimensionDTO signTheBillDimensionDTO = new SignTheBillDimensionDTO(
                Arrays.asList(new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));
        when(mockSignTheBillDimensionService.makeDate(new WorkTableSearchVo(), "fields"))
                .thenReturn(signTheBillDimensionDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/getCustomerTypeDataArea")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetIndustryTypeDataArea() throws Exception {
        // Setup
        // Configure SignTheBillDimensionService.makeDate(...).
        final SignTheBillDimensionDTO signTheBillDimensionDTO = new SignTheBillDimensionDTO(
                Arrays.asList(new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));
        when(mockSignTheBillDimensionService.makeDate(new WorkTableSearchVo(), "fields"))
                .thenReturn(signTheBillDimensionDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/getIndustryTypeDataArea")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetProjectScaleDataArea() throws Exception {
        // Setup
        // Configure SignTheBillDimensionService.makeDate(...).
        final SignTheBillDimensionDTO signTheBillDimensionDTO = new SignTheBillDimensionDTO(
                Arrays.asList(new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));
        when(mockSignTheBillDimensionService.makeDate(new WorkTableSearchVo(), "fields"))
                .thenReturn(signTheBillDimensionDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/getProjectScaleDataArea")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetNewOldTypeDataArea() throws Exception {
        // Setup
        // Configure SignTheBillDimensionService.makeDate(...).
        final SignTheBillDimensionDTO signTheBillDimensionDTO = new SignTheBillDimensionDTO(
                Arrays.asList(new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));
        when(mockSignTheBillDimensionService.makeDate(new WorkTableSearchVo(), "fields"))
                .thenReturn(signTheBillDimensionDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/getNewOldTypeDataArea")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetNewSourceDataArea() throws Exception {
        // Setup
        // Configure SignTheBillDimensionService.makeDate(...).
        final SignTheBillDimensionDTO signTheBillDimensionDTO = new SignTheBillDimensionDTO(
                Arrays.asList(new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));
        when(mockSignTheBillDimensionService.makeDate(new WorkTableSearchVo(), "fields", false))
                .thenReturn(signTheBillDimensionDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/getNewSourceDataArea")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetChanceAnalyseDataArea() throws Exception {
        // Setup
        // Configure ChanceAnalyseService.makeDate(...).
        final ChanceAnalyseDTO chanceAnalyseDTO = new ChanceAnalyseDTO(0L, new BigDecimal("0.00"),
                new BigDecimal("0.00"), new BigDecimal("0.00"), 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                new BigDecimal("0.00"), 0L, new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"));
        when(mockChanceAnalyseService.makeDate(new WorkTableSearchVo())).thenReturn(chanceAnalyseDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/getChanceAnalyseDataArea")
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
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/getBillDailyDataArea")
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
                post("/areaWorkTableOperate/getDevelopmentDailyDataArea")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetBillDetails() throws Exception {
        // Setup
        // Configure BillDetailService.getBillDetailData(...).
        final BillDetailDTOData billDetailDTOData = new BillDetailDTOData();
        billDetailDTOData.setCalculateRegisterData("calculateRegisterData");
        billDetailDTOData.setPageSize(0);
        billDetailDTOData.setPageNum(0);
        billDetailDTOData.setTotal(0);
        final BillDetailDTO billDetailDTO = new BillDetailDTO();
        billDetailDTO.setSignProjectId("signProjectId");
        billDetailDTO.setProjectName("projectName");
        billDetailDTO.setBrmZone("brmZone");
        billDetailDTO.setBrmDepartment("brmDepartment");
        billDetailDTO.setBrmGroup("brmGroup");
        billDetailDTO.setName("name");
        billDetailDTO.setSignDate("signDate");
        billDetailDTO.setSignMoney(new BigDecimal("0.00"));
        billDetailDTO.setSignDiscount(new BigDecimal("0.00"));
        billDetailDTO.setSetMealPrice(new BigDecimal("0.00"));
        billDetailDTOData.setBillDetailDTOList(Arrays.asList(billDetailDTO));
        when(mockBillDetailService.getBillDetailData(new BillDetailVo())).thenReturn(billDetailDTOData);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableOperate/getBillDetails")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
