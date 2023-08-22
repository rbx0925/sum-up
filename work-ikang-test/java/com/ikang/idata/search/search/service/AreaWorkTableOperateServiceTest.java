package com.ikang.idata.search.search.service;

import com.ikang.idata.common.entity.dto.BillDailyDataAreaDTO;
import com.ikang.idata.common.entity.dto.DevelopmentDailyDTO;
import com.ikang.idata.common.entity.vo.AreaWorkTableCheckVO;
import com.ikang.idata.common.entity.vo.AreaWorkTableSignCheckVO;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AreaWorkTableOperateServiceTest {

    @Mock
    private AreaWorkTableManageService mockAreaWorkTableManageService;
    @Mock
    private SignService mockSignService;
    @Mock
    private ESHttpClientService mockEsHttpClientService;

    private AreaWorkTableOperateService areaWorkTableOperateServiceUnderTest;

    @Before
    public void setUp() {
        areaWorkTableOperateServiceUnderTest = new AreaWorkTableOperateService(mockAreaWorkTableManageService, mockSignService, mockEsHttpClientService);
    }

    @Test
    public void testFindCheck() {
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
        when(mockAreaWorkTableManageService.findCheck(new WorkTableSearchVo())).thenReturn(areaWorkTableCheckVO);

        // Run the test
        final AreaWorkTableCheckVO result = areaWorkTableOperateServiceUnderTest.findCheck(searchVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testFindSignCheck() {
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
        //when(mockAreaWorkTableManageService.findSignCheck(new WorkTableSearchVo())).thenReturn(areaWorkTableSignCheckVO);

        // Run the test
       // final AreaWorkTableSignCheckVO result = areaWorkTableOperateServiceUnderTest.findSignCheck(searchVO);

        // Verify the results
       // assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetDevelopmentDailyDataArea() {
        // Setup
        final WorkTableSearchVo workTableSearchVo = new WorkTableSearchVo();
        workTableSearchVo.setBrmZone("brmZone");
        workTableSearchVo.setBrmDepartment("brmDepartment");
        workTableSearchVo.setBrmGroup("brmGroup");
        workTableSearchVo.setStartSignDate("startSignDate");
        workTableSearchVo.setEndSignDate("endSignDate");
        workTableSearchVo.setBrmUserId("brmUserId");
        workTableSearchVo.setChiefZone("chiefZone");

        final DevelopmentDailyDTO expectedResult = new DevelopmentDailyDTO(0L, 0L, new BigDecimal("0.00"), 0L, 0L, new BigDecimal("0.00"));

        // Run the test
        final DevelopmentDailyDTO result = areaWorkTableOperateServiceUnderTest.getDevelopmentDailyDataArea(workTableSearchVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetSearchSourceBuilder() throws Exception {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final Consumer<List<QueryBuilder>> mockConsumer = mock(Consumer.class);
        final SearchSourceBuilder expectedResult = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final SearchSourceBuilder result = areaWorkTableOperateServiceUnderTest.getSearchSourceBuilder(searchVO, mockConsumer, "parameter");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetBillDailyDataArea() {
        // Setup
        final WorkTableSearchVo workTableSearchVo = new WorkTableSearchVo();
        workTableSearchVo.setBrmZone("brmZone");
        workTableSearchVo.setBrmDepartment("brmDepartment");
        workTableSearchVo.setBrmGroup("brmGroup");
        workTableSearchVo.setStartSignDate("startSignDate");
        workTableSearchVo.setEndSignDate("endSignDate");
        workTableSearchVo.setBrmUserId("brmUserId");
        workTableSearchVo.setChiefZone("chiefZone");

        final BillDailyDataAreaDTO expectedResult = new BillDailyDataAreaDTO(new BigDecimal("0.00"), 0L, new BigDecimal("0.00"), 0L, new BigDecimal("0.00"), 0L, new BigDecimal("0.00"), 0L);

        // Run the test
        final BillDailyDataAreaDTO result = areaWorkTableOperateServiceUnderTest.getBillDailyDataArea(workTableSearchVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
