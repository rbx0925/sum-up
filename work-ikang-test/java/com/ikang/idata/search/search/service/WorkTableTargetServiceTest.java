package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.dto.ManagerWorkbenchOperatesDTO;
import com.ikang.idata.common.entity.dto.WorkTableTargetTaskDTO;
import com.ikang.idata.common.entity.vo.AreaWorkTableSignCheckVO;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkTableTargetServiceTest {

    @Mock
    private AreaWorkTableService mockAreaWorkTableService;

    private WorkTableTargetService workTableTargetServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        workTableTargetServiceUnderTest = new WorkTableTargetService(mockAreaWorkTableService);
    }

    @Test
    void testFindBudgetaryRevenues() throws Exception {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final ManagerWorkbenchOperatesDTO expectedResult = new ManagerWorkbenchOperatesDTO();
        expectedResult.setIncome(new BigDecimal("0.00"));
        expectedResult.setTask(new BigDecimal("0.00"));
        expectedResult.setMasterSignedCompletionRate(new BigDecimal("0.00"));

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
        signCheckVO.setUnitPriceRate(new BigDecimal("0.00"));
        signCheckVO.setPackageUnitPrice(new BigDecimal("0.00"));
        when(mockAreaWorkTableService.invokeAndGetResultByFindSignCheck("queryDsl")).thenReturn(signCheckVO);

        // Run the test
        final ManagerWorkbenchOperatesDTO result = workTableTargetServiceUnderTest.findBudgetaryRevenues(searchVO, 0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockAreaWorkTableService).setParamsBySignCheck(new WorkTableSearchVo(), new SearchSourceBuilder(
                StreamInput.wrap("content".getBytes())));
    }

    @Test
    void testSetParamsByTarget() throws Exception {
        // Setup
       /* final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");*/

        String json ="[ {\"brmDepartment\":\"青岛区保险部\",\"brmZone\":\"青岛区\",\"endSignDate\":\"2022-05-04\",\"startSignDate\":\"2022-05-01\"}]";
        List<WorkTableSearchVo> currentTerms = JSONObject.parseArray(json).toJavaList(WorkTableSearchVo.class);
        WorkTableSearchVo searchVO = currentTerms.get(0);
        final SearchSourceBuilder builder = new SearchSourceBuilder();

        // Run the test

        final boolean result = workTableTargetServiceUnderTest.setParamsByTarget(searchVO, builder, 2);

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    void testInvokeAndGetResultByTarget() {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final WorkTableTargetTaskDTO expectedResult = new WorkTableTargetTaskDTO();
        final WorkTableTargetTaskDTO.RenWu renwu = new WorkTableTargetTaskDTO.RenWu();
        renwu.setValue(new BigDecimal("0.00"));
        expectedResult.setRenwu(renwu);

        // Run the test
        final WorkTableTargetTaskDTO result = workTableTargetServiceUnderTest.invokeAndGetResultByTarget("queryDsl",
                searchVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTargetTaskDTO() {
        // Setup
       // final JSONObject jsonObject = new JSONObject(0, false);
        String json ="[{\"brmDepartment\":\"青岛区保险部\",\"brmZone\":\"青岛区\",\"endSignDate\":\"2022-05-04\",\"startSignDate\":\"2022-05-01\"}\n]";
        List<WorkTableSearchVo> currentTerms = JSONObject.parseArray(json).toJavaList(WorkTableSearchVo.class);
        WorkTableSearchVo searchVO = currentTerms.get(0);
       /* searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("startSignDate");
        searchVO.setEndSignDate("endSignDate");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");*/
        String temp="{\"hits\":[],\"total\":276,\"code\":\"200\",\"message\":\"成功\",\"aggregations\":{\"year_month\":{\"buckets\":[{\"from_as_string\":\"2022-05-01 00:00:00\",\"doc_count\":8,\"to_as_string\":\"2022-06-01 00:00:00\",\"from\":1.6513632E12,\"to\":1.6540416E12,\"key\":\"currentTerm\",\"renwu\":{\"value\":3387.2000045776367}}]}}}\n";
        JSONObject jsonObject =  JSONObject.parseObject(temp);
        final WorkTableTargetTaskDTO expectedResult = new WorkTableTargetTaskDTO();
        final WorkTableTargetTaskDTO.RenWu renwu = new WorkTableTargetTaskDTO.RenWu();
        renwu.setValue(new BigDecimal("0.00"));
        expectedResult.setRenwu(renwu);

        // Run the test
        final WorkTableTargetTaskDTO result = workTableTargetServiceUnderTest.getTargetTaskDTO(jsonObject, searchVO);

        // Verify the results
      //  assertThat(result).isEqualTo(expectedResult);
    }
}
