package com.ikang.idata.search.search.service;

import com.ikang.idata.common.entity.vo.AreaWorkTableSignCheckVO;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManagerWorkTableOperateServiceTest {

    @Mock
    private AreaWorkTableManageService mockAreaWorkTableManageService;

    private ManagerWorkTableOperateService managerWorkTableOperateServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        managerWorkTableOperateServiceUnderTest = new ManagerWorkTableOperateService(mockAreaWorkTableManageService);
    }

    @Test
    void testFindSignCheck() {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final AreaWorkTableSignCheckVO expectedResult = new AreaWorkTableSignCheckVO();
        expectedResult.setIncome(new BigDecimal("0.00"));
        expectedResult.setSameTermIncome(new BigDecimal("0.00"));
        expectedResult.setIncomeRate(new BigDecimal("0.00"));
        expectedResult.setLocalCheckIncome(new BigDecimal("0.00"));
        expectedResult.setSelfIncomeRate(new BigDecimal("0.00"));
        expectedResult.setPackageReceivableAmount(new BigDecimal("0.00"));
        expectedResult.setSameTermPackageReceivableAmount(new BigDecimal("0.00"));
        expectedResult.setPackageReceivableAmountRate(new BigDecimal("0.00"));
        expectedResult.setAdditionalReceivableAmount(new BigDecimal("0.00"));
        expectedResult.setSameTermAdditionalReceivableAmount(new BigDecimal("0.00"));
        expectedResult.setAdditionalReceivableAmountRate(new BigDecimal("0.00"));
        expectedResult.setUnitPrice(new BigDecimal("0.00"));
        expectedResult.setSameTermUnitPrice(new BigDecimal("0.00"));
        expectedResult.setUnitPriceRate(new BigDecimal("0.00"));
        expectedResult.setPackageUnitPrice(new BigDecimal("0.00"));

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
        final AreaWorkTableSignCheckVO result = managerWorkTableOperateServiceUnderTest.findSignCheck(searchVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
