package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.dto.BillDetailDTO;
import com.ikang.idata.common.entity.es.BillDetailData;
import com.ikang.idata.common.entity.es.BillDetailHits;
import com.ikang.idata.common.entity.vo.BillDetailDTOData;
import com.ikang.idata.common.entity.vo.BillDetailVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BillDetailServiceTest {

    @Mock
    private ESHttpClientService mockEsHttpClientService;

    private BillDetailService billDetailServiceUnderTest;

    @Before
    public void setUp() {
        billDetailServiceUnderTest = new BillDetailService(mockEsHttpClientService);
    }

    @Test
    public void testGetBillDetail() {
        // Setup
        final BillDetailVo billDetailVo = new BillDetailVo();
        billDetailVo.setBrmZone("brmZone");
        billDetailVo.setBrmDepartment("brmDepartment");
        billDetailVo.setBrmGroup("brmGroup");
        billDetailVo.setStartSignDate("startSignDate");
        billDetailVo.setEndSignDate("endSignDate");
        billDetailVo.setBrmUserId("brmUserId");
        billDetailVo.setChiefZone("chiefZone");
        billDetailVo.setCategory("category");
        billDetailVo.setOrderByStr("orderByStr");
        billDetailVo.setSortOrder("sortOrder");

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
        final List<BillDetailDTO> expectedResult = Arrays.asList(billDetailDTO);

        // Configure ESHttpClientService.exchange(...).
        final BillDetailData billDetailData = new BillDetailData();
        final BillDetailHits billDetailHits = new BillDetailHits();
        billDetailHits.setOriginalSource(new LinkedHashMap<>());
        final BillDetailDTO source = new BillDetailDTO();
        source.setSignProjectId("signProjectId");
        source.setProjectName("projectName");
        source.setBrmZone("brmZone");
        source.setBrmDepartment("brmDepartment");
        source.setBrmGroup("brmGroup");
        source.setName("name");
        source.setSignDate("signDate");
        source.setSignMoney(new BigDecimal("0.00"));
        source.setSignDiscount(new BigDecimal("0.00"));
        source.setSetMealPrice(new BigDecimal("0.00"));
        billDetailHits.setSource(source);
        billDetailData.setCoverHits(Arrays.asList(billDetailHits));
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, BillDetailData.class, "requestBody")).thenReturn(billDetailData);

        // Run the test
        final List<BillDetailDTO> result = billDetailServiceUnderTest.getBillDetail(billDetailVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testInvokeBillDetail() {
        // Setup
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
        final List<BillDetailDTO> expectedResult = Arrays.asList(billDetailDTO);

        // Configure ESHttpClientService.exchange(...).
        final BillDetailData billDetailData = new BillDetailData();
        final BillDetailHits billDetailHits = new BillDetailHits();
        billDetailHits.setOriginalSource(new LinkedHashMap<>());
        final BillDetailDTO source = new BillDetailDTO();
        source.setSignProjectId("signProjectId");
        source.setProjectName("projectName");
        source.setBrmZone("brmZone");
        source.setBrmDepartment("brmDepartment");
        source.setBrmGroup("brmGroup");
        source.setName("name");
        source.setSignDate("signDate");
        source.setSignMoney(new BigDecimal("0.00"));
        source.setSignDiscount(new BigDecimal("0.00"));
        source.setSetMealPrice(new BigDecimal("0.00"));
        billDetailHits.setSource(source);
        billDetailData.setCoverHits(Arrays.asList(billDetailHits));
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, BillDetailData.class, "requestBody")).thenReturn(billDetailData);

        // Run the test
        final List<BillDetailDTO> result = billDetailServiceUnderTest.invokeBillDetail("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetBillDetailData() {
        // Setup
        final BillDetailVo billDetailVo = new BillDetailVo();
        billDetailVo.setBrmZone("brmZone");
        billDetailVo.setBrmDepartment("brmDepartment");
        billDetailVo.setBrmGroup("brmGroup");
        billDetailVo.setStartSignDate("startSignDate");
        billDetailVo.setEndSignDate("endSignDate");
        billDetailVo.setBrmUserId("brmUserId");
        billDetailVo.setChiefZone("chiefZone");
        billDetailVo.setCategory("category");
        billDetailVo.setOrderByStr("orderByStr");
        billDetailVo.setSortOrder("sortOrder");

        // Run the test
        final BillDetailDTOData result = billDetailServiceUnderTest.getBillDetailData(billDetailVo);

        // Verify the results
    }

    @Test
    public void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = billDetailServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
