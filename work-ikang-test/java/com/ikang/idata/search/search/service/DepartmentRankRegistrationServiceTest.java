package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.ResponseImpalaDTO;
import com.ikang.idata.common.entity.es.ESBaseData;
import com.ikang.idata.common.entity.vo.DepartmentRankDetailSearchVO;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentRankRegistrationServiceTest {

    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;
    @Mock
    private ESHttpClientService mockEsHttpClientService;
    @Mock
    private DepartmentRankService mockDepartmentRankService;

    private DepartmentRankRegistrationService departmentRankRegistrationServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        departmentRankRegistrationServiceUnderTest = new DepartmentRankRegistrationService(mockAuthorityFeignService,
                mockEsHttpClientService, mockDepartmentRankService);
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final DepartmentRankDetailSearchVO departmentRankDetailSearchVO = new DepartmentRankDetailSearchVO();
        departmentRankDetailSearchVO.setResourceId(0L);
        departmentRankDetailSearchVO.setQueryMap(new HashMap<>());
        departmentRankDetailSearchVO.setPageNum(0);
        departmentRankDetailSearchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        departmentRankDetailSearchVO.setGroupBy(Arrays.asList(aggregationCondition));
        departmentRankDetailSearchVO.setHospId("hospId");
        departmentRankDetailSearchVO.setDepartmentName("departmentName");
        departmentRankDetailSearchVO.setDepartmentCode("departmentCode");
        departmentRankDetailSearchVO.setCheckItemCode("checkItemCode");
        departmentRankDetailSearchVO.setItemStatus("itemStatus");
        departmentRankDetailSearchVO.setRegistrationDate("registrationDate");
        departmentRankDetailSearchVO.setAreaIdCode("areaIdCode");

        // Configure ESHttpClientService.exchange(...).
        final ResponseImpalaDTO responseImpalaDTO = new ResponseImpalaDTO();
        responseImpalaDTO.setCode(0);
        responseImpalaDTO.setTotal(0);
        responseImpalaDTO.setData("data");
        when(mockEsHttpClientService.exchange("technicalofficedetailsImpalaUrl", HttpMethod.POST,
                ResponseImpalaDTO.class, new HashMap<>())).thenReturn(responseImpalaDTO);

        // Run the test
        final BaseSearch result = departmentRankRegistrationServiceUnderTest.find(departmentRankDetailSearchVO);

        // Verify the results
    }

    @Test
    void testFindDepartmentRankDetail() {
        // Setup
        final DepartmentRankDetailSearchVO departmentRankDetailSearchVO = new DepartmentRankDetailSearchVO();
        departmentRankDetailSearchVO.setResourceId(0L);
        departmentRankDetailSearchVO.setQueryMap(new HashMap<>());
        departmentRankDetailSearchVO.setPageNum(0);
        departmentRankDetailSearchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        departmentRankDetailSearchVO.setGroupBy(Arrays.asList(aggregationCondition));
        departmentRankDetailSearchVO.setHospId("hospId");
        departmentRankDetailSearchVO.setDepartmentName("departmentName");
        departmentRankDetailSearchVO.setDepartmentCode("departmentCode");
        departmentRankDetailSearchVO.setCheckItemCode("checkItemCode");
        departmentRankDetailSearchVO.setItemStatus("itemStatus");
        departmentRankDetailSearchVO.setRegistrationDate("registrationDate");
        departmentRankDetailSearchVO.setAreaIdCode("areaIdCode");

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("appointmentdetailsUrl", HttpMethod.POST, ESBaseData.class,
                "requestBody")).thenReturn(esBaseData);

        // Run the test
        final BaseSearch result = departmentRankRegistrationServiceUnderTest.findDepartmentRankDetail(
                departmentRankDetailSearchVO);

        // Verify the results
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final DepartmentRankDetailSearchVO searchVo = new DepartmentRankDetailSearchVO();
        searchVo.setResourceId(0L);
        searchVo.setQueryMap(new HashMap<>());
        searchVo.setPageNum(0);
        searchVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        searchVo.setGroupBy(Arrays.asList(aggregationCondition));
        searchVo.setHospId("hospId");
        searchVo.setDepartmentName("departmentName");
        searchVo.setDepartmentCode("departmentCode");
        searchVo.setCheckItemCode("checkItemCode");
        searchVo.setItemStatus("itemStatus");
        searchVo.setRegistrationDate("registrationDate");
        searchVo.setAreaIdCode("areaIdCode");

        // Run the test
        final BaseSearch result = departmentRankRegistrationServiceUnderTest.findGroupBy(searchVo);

        // Verify the results
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = departmentRankRegistrationServiceUnderTest.assemblyConditions(
                originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSetParams() {
        // Setup
        final DepartmentRankDetailSearchVO vo = new DepartmentRankDetailSearchVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setHospId("hospId");
        vo.setDepartmentName("departmentName");
        vo.setDepartmentCode("departmentCode");
        vo.setCheckItemCode("checkItemCode");
        vo.setItemStatus("itemStatus");
        vo.setRegistrationDate("registrationDate");
        vo.setAreaIdCode("areaIdCode");

        final List<QueryBuilder> must = Arrays.asList();

        // Run the test
        departmentRankRegistrationServiceUnderTest.setParams(vo, must, 0);

        // Verify the results
    }

    @Test
    void testExportExcel() throws Exception {
        // Setup
        final DepartmentRankDetailSearchVO searchVo = new DepartmentRankDetailSearchVO();
        searchVo.setResourceId(0L);
        searchVo.setQueryMap(new HashMap<>());
        searchVo.setPageNum(0);
        searchVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        searchVo.setGroupBy(Arrays.asList(aggregationCondition));
        searchVo.setHospId("hospId");
        searchVo.setDepartmentName("departmentName");
        searchVo.setDepartmentCode("departmentCode");
        searchVo.setCheckItemCode("checkItemCode");
        searchVo.setItemStatus("itemStatus");
        searchVo.setRegistrationDate("registrationDate");
        searchVo.setAreaIdCode("areaIdCode");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure ESHttpClientService.exchange(...).
        final ResponseImpalaDTO responseImpalaDTO = new ResponseImpalaDTO();
        responseImpalaDTO.setCode(0);
        responseImpalaDTO.setTotal(0);
        responseImpalaDTO.setData("data");
        when(mockEsHttpClientService.exchange("technicalofficedetailsImpalaUrl", HttpMethod.POST,
                ResponseImpalaDTO.class, new HashMap<>())).thenReturn(responseImpalaDTO);

        // Run the test
        departmentRankRegistrationServiceUnderTest.exportExcel(searchVo, response);

        // Verify the results
    }

    @Test
    void testExportExcel_ThrowsException() {
        // Setup
        final DepartmentRankDetailSearchVO searchVo = new DepartmentRankDetailSearchVO();
        searchVo.setResourceId(0L);
        searchVo.setQueryMap(new HashMap<>());
        searchVo.setPageNum(0);
        searchVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        searchVo.setGroupBy(Arrays.asList(aggregationCondition));
        searchVo.setHospId("hospId");
        searchVo.setDepartmentName("departmentName");
        searchVo.setDepartmentCode("departmentCode");
        searchVo.setCheckItemCode("checkItemCode");
        searchVo.setItemStatus("itemStatus");
        searchVo.setRegistrationDate("registrationDate");
        searchVo.setAreaIdCode("areaIdCode");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure ESHttpClientService.exchange(...).
        final ResponseImpalaDTO responseImpalaDTO = new ResponseImpalaDTO();
        responseImpalaDTO.setCode(0);
        responseImpalaDTO.setTotal(0);
        responseImpalaDTO.setData("data");
        when(mockEsHttpClientService.exchange("technicalofficedetailsImpalaUrl", HttpMethod.POST,
                ResponseImpalaDTO.class, new HashMap<>())).thenReturn(responseImpalaDTO);

        // Run the test
        assertThatThrownBy(
                () -> departmentRankRegistrationServiceUnderTest.exportExcel(searchVo, response))
                .isInstanceOf(Exception.class);
    }

    @Test
    void testFindByImpala() {
        // Setup
        final DepartmentRankDetailSearchVO departmentRankDetailSearchVO = new DepartmentRankDetailSearchVO();
        departmentRankDetailSearchVO.setResourceId(0L);
        departmentRankDetailSearchVO.setQueryMap(new HashMap<>());
        departmentRankDetailSearchVO.setPageNum(0);
        departmentRankDetailSearchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        departmentRankDetailSearchVO.setGroupBy(Arrays.asList(aggregationCondition));
        departmentRankDetailSearchVO.setHospId("hospId");
        departmentRankDetailSearchVO.setDepartmentName("departmentName");
        departmentRankDetailSearchVO.setDepartmentCode("departmentCode");
        departmentRankDetailSearchVO.setCheckItemCode("checkItemCode");
        departmentRankDetailSearchVO.setItemStatus("itemStatus");
        departmentRankDetailSearchVO.setRegistrationDate("registrationDate");
        departmentRankDetailSearchVO.setAreaIdCode("areaIdCode");

        // Configure ESHttpClientService.exchange(...).
        final ResponseImpalaDTO responseImpalaDTO = new ResponseImpalaDTO();
        responseImpalaDTO.setCode(0);
        responseImpalaDTO.setTotal(0);
        responseImpalaDTO.setData("data");
        when(mockEsHttpClientService.exchange("technicalofficedetailsImpalaUrl", HttpMethod.POST,
                ResponseImpalaDTO.class, new HashMap<>())).thenReturn(responseImpalaDTO);

        // Run the test
        final BaseSearch result = departmentRankRegistrationServiceUnderTest.findByImpala(departmentRankDetailSearchVO);

        // Verify the results
    }
}
