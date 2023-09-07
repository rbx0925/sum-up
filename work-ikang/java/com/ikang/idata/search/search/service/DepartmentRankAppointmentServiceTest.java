package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.es.ESBaseData;
import com.ikang.idata.common.entity.vo.DepartmentRankDetailSearchVO;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentRankAppointmentServiceTest {

    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;
    @Mock
    private ESHttpClientService mockEsHttpClientService;
    @Mock
    private DepartmentRankService mockDepartmentRankService;

    private DepartmentRankAppointmentService departmentRankAppointmentServiceUnderTest;

    @Before
    public void setUp() {
        departmentRankAppointmentServiceUnderTest = new DepartmentRankAppointmentService(mockAuthorityFeignService, mockEsHttpClientService, mockDepartmentRankService);
    }

    @Test
    public void testFind() {
        // Setup
        final DepartmentRankDetailSearchVO departmentRankDetailSearchVO = new DepartmentRankDetailSearchVO();
        departmentRankDetailSearchVO.setHospId("hospId");
        departmentRankDetailSearchVO.setDepartmentName("departmentName");
        departmentRankDetailSearchVO.setDepartmentCode("departmentCode");
        departmentRankDetailSearchVO.setCheckItemCode("checkItemCode");
        departmentRankDetailSearchVO.setItemStatus("itemStatus");
        departmentRankDetailSearchVO.setCardId("cardId");
        departmentRankDetailSearchVO.setMobile("mobile");
        departmentRankDetailSearchVO.setSex("sex");
        departmentRankDetailSearchVO.setCompanyName("companyName");
        departmentRankDetailSearchVO.setWorkNo("workNo");

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, ESBaseData.class, "requestBody")).thenReturn(esBaseData);

        // Run the test
        final BaseSearch result = departmentRankAppointmentServiceUnderTest.find(departmentRankDetailSearchVO);

        // Verify the results
        verify(mockDepartmentRankService).setParams(new DepartmentRankDetailSearchVO(), Arrays.asList(), 0);
    }

    @Test
    public void testFindGroupBy() {
        // Setup
        final DepartmentRankDetailSearchVO searchVo = new DepartmentRankDetailSearchVO();
        searchVo.setHospId("hospId");
        searchVo.setDepartmentName("departmentName");
        searchVo.setDepartmentCode("departmentCode");
        searchVo.setCheckItemCode("checkItemCode");
        searchVo.setItemStatus("itemStatus");
        searchVo.setCardId("cardId");
        searchVo.setMobile("mobile");
        searchVo.setSex("sex");
        searchVo.setCompanyName("companyName");
        searchVo.setWorkNo("workNo");

        // Run the test
        final BaseSearch result = departmentRankAppointmentServiceUnderTest.findGroupBy(searchVo);

        // Verify the results
        verify(mockDepartmentRankService).setParams(new DepartmentRankDetailSearchVO(), Arrays.asList(), 0);
    }

    @Test
    public void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = departmentRankAppointmentServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockDepartmentRankService).setParams(new DepartmentRankDetailSearchVO(), Arrays.asList(), 0);
    }

    @Test
    public void testExportExcel() throws Exception {
        // Setup
        final DepartmentRankDetailSearchVO searchVo = new DepartmentRankDetailSearchVO();
        searchVo.setHospId("hospId");
        searchVo.setDepartmentName("departmentName");
        searchVo.setDepartmentCode("departmentCode");
        searchVo.setCheckItemCode("checkItemCode");
        searchVo.setItemStatus("itemStatus");
        searchVo.setCardId("cardId");
        searchVo.setMobile("mobile");
        searchVo.setSex("sex");
        searchVo.setCompanyName("companyName");
        searchVo.setWorkNo("workNo");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, ESBaseData.class, "requestBody")).thenReturn(esBaseData);

        // Run the test
        departmentRankAppointmentServiceUnderTest.exportExcel(searchVo, response);

        // Verify the results
        verify(mockDepartmentRankService).setParams(new DepartmentRankDetailSearchVO(), Arrays.asList(), 0);
    }

    @Test
    public void testExportExcel_ThrowsException() {
        // Setup
        final DepartmentRankDetailSearchVO searchVo = new DepartmentRankDetailSearchVO();
        searchVo.setHospId("hospId");
        searchVo.setDepartmentName("departmentName");
        searchVo.setDepartmentCode("departmentCode");
        searchVo.setCheckItemCode("checkItemCode");
        searchVo.setItemStatus("itemStatus");
        searchVo.setCardId("cardId");
        searchVo.setMobile("mobile");
        searchVo.setSex("sex");
        searchVo.setCompanyName("companyName");
        searchVo.setWorkNo("workNo");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, ESBaseData.class, "requestBody")).thenReturn(esBaseData);

        // Run the test
        assertThatThrownBy(() -> departmentRankAppointmentServiceUnderTest.exportExcel(searchVo, response)).isInstanceOf(Exception.class);
        verify(mockDepartmentRankService).setParams(new DepartmentRankDetailSearchVO(), Arrays.asList(), 0);
    }
}
