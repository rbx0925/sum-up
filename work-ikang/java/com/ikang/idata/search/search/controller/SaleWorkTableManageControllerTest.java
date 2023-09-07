package com.ikang.idata.search.search.controller;

import com.ikang.idata.common.entity.dto.ManagerWorkbenchOperatesDTO;
import com.ikang.idata.common.entity.vo.AreaWorkTableSignCheckVO;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import com.ikang.idata.search.search.service.AreaWorkTableManageService;
import com.ikang.idata.search.search.service.WorkTableTargetService;
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
@WebMvcTest(SaleWorkTableManageController.class)
class SaleWorkTableManageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AreaWorkTableManageService mockAreaWorkTableManageService;
    @MockBean
    private WorkTableTargetService mockWorkTableTargetService;

    @Test
    void testFindBudgetaryRevenues() throws Exception {
        // Setup
        // Configure WorkTableTargetService.findBudgetaryRevenues(...).
        final ManagerWorkbenchOperatesDTO managerWorkbenchOperatesDTO = new ManagerWorkbenchOperatesDTO();
        managerWorkbenchOperatesDTO.setIncome(new BigDecimal("0.00"));
        managerWorkbenchOperatesDTO.setTask(new BigDecimal("0.00"));
        managerWorkbenchOperatesDTO.setMasterSignedCompletionRate(new BigDecimal("0.00"));
        when(mockWorkTableTargetService.findBudgetaryRevenues(new WorkTableSearchVo(), 0))
                .thenReturn(managerWorkbenchOperatesDTO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/saleWorkTableManage/findBudgetaryRevenues")
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
        when(mockAreaWorkTableManageService.findSignCheck(new WorkTableSearchVo(), 0))
                .thenReturn(areaWorkTableSignCheckVO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/saleWorkTableManage/findSignCheck")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
