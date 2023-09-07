package com.ikang.idata.search.search.javaTest;



import com.ikang.idata.common.entity.dto.ManagerWorkbenchOperatesDTO;
import com.ikang.idata.common.entity.vo.AreaWorkTableCheckVO;
import com.ikang.idata.common.entity.vo.AreaWorkTableFinanceVO;
import com.ikang.idata.common.entity.vo.AreaWorkTableSignCheckVO;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import com.ikang.idata.search.search.service.AreaWorkTableManageService;
import com.ikang.idata.search.search.service.AreaWorkTableService;
import com.ikang.idata.search.search.service.WorkTableTargetService;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
class AreaWorkTableManageServiceTest {

    @Mock
    private AreaWorkTableService mockAreaWorkTableService;
    @Mock
    private WorkTableTargetService mockWorkTableTargetService;

    private AreaWorkTableManageService areaWorkTableManageServiceUnderTest;

    @BeforeEach
    void setUp() {
        areaWorkTableManageServiceUnderTest = new AreaWorkTableManageService(mockAreaWorkTableService,
                mockWorkTableTargetService);
    }

    @Test
    void testFindFinance() throws Exception {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final AreaWorkTableFinanceVO expectedResult = new AreaWorkTableFinanceVO();
        expectedResult.setTotalIncome(new BigDecimal("0.00"));
        expectedResult.setTotalTask(new BigDecimal("0.00"));
        expectedResult.setTotalTaskRate(new BigDecimal("0.00"));
        expectedResult.setSameTermTotalIncome(new BigDecimal("0.00"));
        expectedResult.setTotalIncomeRate(new BigDecimal("0.00"));
        expectedResult.setSignIncome(new BigDecimal("0.00"));
        expectedResult.setSignTask(new BigDecimal("0.00"));
        expectedResult.setSignTaskRate(new BigDecimal("0.00"));
        expectedResult.setSameTermSignIncome(new BigDecimal("0.00"));
        expectedResult.setSignIncomeRate(new BigDecimal("0.00"));

        // Configure AreaWorkTableService.invokeAndGetResultByFindFinance(...).
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
        when(mockAreaWorkTableService.invokeAndGetResultByFindFinance("queryDsl")).thenReturn(financeVO);

        // Run the test
        final AreaWorkTableFinanceVO result = areaWorkTableManageServiceUnderTest.findFinance(searchVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockAreaWorkTableService).setParamsByFinance(new WorkTableSearchVo(), new SearchSourceBuilder(
                StreamInput.wrap("content".getBytes())));
    }

    @Test
    void testFindCheck() throws Exception {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final AreaWorkTableCheckVO expectedResult = new AreaWorkTableCheckVO();
        expectedResult.setCheckIncome(new BigDecimal("0.00"));
        expectedResult.setSameTermCheckIncome(new BigDecimal("0.00"));
        expectedResult.setCheckIncomeRate(new BigDecimal("0.00"));
        expectedResult.setSelfIncome(new BigDecimal("0.00"));
        expectedResult.setSelfIncomeRate(new BigDecimal("0.00"));
        expectedResult.setPackageIncome(new BigDecimal("0.00"));
        expectedResult.setSameTermPackageIncome(new BigDecimal("0.00"));
        expectedResult.setPackageIncomeRate(new BigDecimal("0.00"));
        expectedResult.setAdditionalIncome(new BigDecimal("0.00"));
        expectedResult.setSameTermAdditionalIncome(new BigDecimal("0.00"));
        expectedResult.setAdditionalIncomeRate(new BigDecimal("0.00"));
        expectedResult.setUnitPrice(new BigDecimal("0.00"));
        expectedResult.setSameTermUnitPrice(new BigDecimal("0.00"));
        expectedResult.setUnitPriceRate(new BigDecimal("0.00"));
        expectedResult.setPackageUnitPrice(new BigDecimal("0.00"));

        // Configure AreaWorkTableService.invokeAndGetResultByFindCheck(...).
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
        when(mockAreaWorkTableService.invokeAndGetResultByFindCheck("queryDsl")).thenReturn(areaWorkTableCheckVO);

        // Run the test
        final AreaWorkTableCheckVO result = areaWorkTableManageServiceUnderTest.findCheck(searchVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockAreaWorkTableService).setParamsByFindCheck(new WorkTableSearchVo(), new SearchSourceBuilder(
                StreamInput.wrap("content".getBytes())));
    }

    @Test
    void testFindSignCheck() throws Exception {
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
        expectedResult.setBudgetTarget(new BigDecimal("0.00"));
        expectedResult.setIncomeCompletionRate(new BigDecimal("0.00"));

        // Configure AreaWorkTableService.invokeAndGetResultByFindSignCheck(...).
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
        signCheckVO.setBudgetTarget(new BigDecimal("0.00"));
        signCheckVO.setIncomeCompletionRate(new BigDecimal("0.00"));
        when(mockAreaWorkTableService.invokeAndGetResultByFindSignCheck("queryDsl")).thenReturn(signCheckVO);

        // Configure WorkTableTargetService.findBudgetaryRevenues(...).
        final ManagerWorkbenchOperatesDTO managerWorkbenchOperatesDTO = new ManagerWorkbenchOperatesDTO();
        managerWorkbenchOperatesDTO.setIncome(new BigDecimal("0.00"));
        managerWorkbenchOperatesDTO.setTask(new BigDecimal("0.00"));
        managerWorkbenchOperatesDTO.setMasterSignedCompletionRate(new BigDecimal("0.00"));
        // when(mockWorkTableTargetService.findBudgetaryRevenues(new WorkTableSearchVo()))
        // .thenReturn(managerWorkbenchOperatesDTO);

        // Run the test
        //final AreaWorkTableSignCheckVO result = areaWorkTableManageServiceUnderTest.findSignCheck(searchVO);

        // Verify the results
        //  assertThat(result).isEqualTo(expectedResult);
        verify(mockAreaWorkTableService).setParamsBySignCheck(new WorkTableSearchVo(), new SearchSourceBuilder(
                StreamInput.wrap("content".getBytes())));
    }
}

