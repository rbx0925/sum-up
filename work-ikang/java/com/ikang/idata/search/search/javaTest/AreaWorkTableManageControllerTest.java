package com.ikang.idata.search.search.javaTest;

import com.ikang.idata.common.entity.vo.AreaWorkTableCheckVO;
import com.ikang.idata.common.entity.vo.AreaWorkTableFinanceVO;
import com.ikang.idata.common.entity.vo.AreaWorkTableSignCheckVO;
import com.ikang.idata.search.search.controller.AreaWorkTableManageController;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import com.ikang.idata.search.search.service.AreaWorkTableManageService;
import com.ikang.idata.search.search.service.AreaWorkTableService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AreaWorkTableManageController.class)
class AreaWorkTableManageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AreaWorkTableManageService mockAreaWorkTableManageService;
    @MockBean
    private AreaWorkTableService mockAreaWorkTableService;

    @Test
    void testFindFinance() throws Exception {
        // Setup
        // Configure AreaWorkTableManageService.findFinance(...).
        final AreaWorkTableFinanceVO financeVO = new AreaWorkTableFinanceVO();
        financeVO.setTotalIncome(new BigDecimal("0.00"));
        financeVO.setTotalTask(new BigDecimal("0.00"));
        financeVO.setTotalTaskRate(new BigDecimal("0.00"));
        financeVO.setSameTermTotalIncome(new BigDecimal("0.00"));
        financeVO.setTotalIncomeRate(new BigDecimal("0.00"));
        financeVO.setSignIncome(new BigDecimal("0.00"));
        financeVO.setSignTask(new BigDecimal("0.00"));
        financeVO.setSignTaskRate(new BigDecimal("0.00"));
        financeVO.setSameTermSignIncome(new BigDecimal("0.00"));
        financeVO.setSignIncomeRate(new BigDecimal("0.00"));
        when(mockAreaWorkTableManageService.findFinance(new WorkTableSearchVo())).thenReturn(financeVO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableManage/findFinance")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindCheck() throws Exception {
        // Setup
        // Configure AreaWorkTableManageService.findCheck(...).
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
        when(mockAreaWorkTableManageService.findCheck(new WorkTableSearchVo())).thenReturn(areaWorkTableCheckVO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableManage/findCheck")
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
        // Configure AreaWorkTableManageService.findSignCheck(...).
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
        when(mockAreaWorkTableManageService.findSignCheck(new WorkTableSearchVo(), 0)).thenReturn(signCheckVO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableManage/findSignCheck")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAreaWorkTableRanking() throws Exception {
        // Setup
        // Configure AreaWorkTableService.areaWorkTableRanking(...).
//        final AreaWorkTableRankingVO areaWorkTableRankingVO = new AreaWorkTableRankingVO(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
//                0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L);
//        when(mockAreaWorkTableService.areaWorkTableRanking(new WorkTableSearchVo())).thenReturn(areaWorkTableRankingVO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/areaWorkTableManage/areaWorkTableRanking")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
