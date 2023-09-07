package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.ResponseImpalaDTO;
import com.ikang.idata.common.entity.UserConfig;
import com.ikang.idata.common.entity.es.ESBaseData;
import com.ikang.idata.search.search.entity.vo.*;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationKanbanServiceTest {

    @Mock
    private ESHttpClientService mockEsHttpClientService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;
    @Mock
    private ImpalaCheckService mockImpalaCheckService;
    @Mock
    private DepartmentAppointmentService mockDepartmentAppointmentService;

    private RegistrationKanbanService registrationKanbanServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        registrationKanbanServiceUnderTest = new RegistrationKanbanService(mockEsHttpClientService,
                mockAuthorityFeignService, mockImpalaCheckService, mockDepartmentAppointmentService);
    }

    @Test
    void testQueryKanbanByImpala() {
        // Setup
        final RegistrationKanbanSearchVo vo = new RegistrationKanbanSearchVo();
        vo.setInstituteId("instituteId");
        vo.setRegistrationDate("registrationDate");

        final RegistrationKanbanSummary expectedResult = new RegistrationKanbanSummary();

        // Configure ESHttpClientService.exchange(...).
        final ResponseImpalaDTO responseImpalaDTO = new ResponseImpalaDTO();
        responseImpalaDTO.setCode(0);
        responseImpalaDTO.setTotal(0);
        responseImpalaDTO.setData("data");
        when(mockEsHttpClientService.exchange("registrationSummaryImpalaUrl", HttpMethod.POST, ResponseImpalaDTO.class,
                new HashMap<>())).thenReturn(responseImpalaDTO);

        // Configure DepartmentAppointmentService.getMaxAndMinMoney(...).
        final UserConfig userConfig = new UserConfig();
        userConfig.setId(0L);
        userConfig.setUserId(0L);
        userConfig.setDepartmentId("departmentId");
        userConfig.setMinMoney("minMoney");
        userConfig.setMaxMoney("maxMoney");
        when(mockDepartmentAppointmentService.getMaxAndMinMoney()).thenReturn(userConfig);

        // Run the test
        final RegistrationKanbanSummary result = registrationKanbanServiceUnderTest.queryKanbanByImpala(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testQueryKanbanByEs() {
        // Setup
        final RegistrationKanbanSearchVo vo = new RegistrationKanbanSearchVo();
        vo.setInstituteId("instituteId");
        vo.setRegistrationDate("registrationDate");

        final RegistrationKanbanSummary expectedResult = new RegistrationKanbanSummary();

        // Configure ESHttpClientService.exchange(...).
        final RegistrationKanbanSummaryData registrationKanbanSummaryData = new RegistrationKanbanSummaryData();
        registrationKanbanSummaryData.setTotal(0);
        final RegistrationKanbanSummaryHits registrationKanbanSummaryHits = new RegistrationKanbanSummaryHits();
        registrationKanbanSummaryHits.setOriginalSource(new LinkedHashMap<>());
        final RegistrationKanbanSummary source = new RegistrationKanbanSummary();
        source.setId("id");
        source.setAreaid("areaid");
        source.setAreaname("areaname");
        source.setLocid("locid");
        source.setLocname("locname");
        source.setHospid("hospid");
        source.setHospname("hospname");
        source.setExamday("examday");
        source.setTotalcnt(new BigDecimal("0.00"));
        source.setRegcheckcnt(new BigDecimal("0.00"));
        source.setHighvaluecnt(new BigDecimal("0.00"));
        registrationKanbanSummaryHits.setSource(source);
        registrationKanbanSummaryData.setCoverHits(Arrays.asList(registrationKanbanSummaryHits));
        when(mockEsHttpClientService.exchange("registrationSummaryESUrl", HttpMethod.POST,
                RegistrationKanbanSummaryData.class, "requestBody")).thenReturn(registrationKanbanSummaryData);

        // Configure DepartmentAppointmentService.getMaxAndMinMoney(...).
        final UserConfig userConfig = new UserConfig();
        userConfig.setId(0L);
        userConfig.setUserId(0L);
        userConfig.setDepartmentId("departmentId");
        userConfig.setMinMoney("minMoney");
        userConfig.setMaxMoney("maxMoney");
        when(mockDepartmentAppointmentService.getMaxAndMinMoney()).thenReturn(userConfig);

        // Run the test
        final RegistrationKanbanSummary result = registrationKanbanServiceUnderTest.queryKanbanByEs(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = registrationKanbanServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindByImpala() {
        // Setup
        final RegisterDetailSearchVo searchVo = new RegisterDetailSearchVo();
        searchVo.setResourceId(0L);
        searchVo.setQueryMap(new HashMap<>());
        searchVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        searchVo.setGroupBy(Arrays.asList(aggregationCondition));
        searchVo.setInstituteId("instituteId");
        searchVo.setRegistrationDate("registrationDate");
        searchVo.setDesensitization(false);

        // Configure ESHttpClientService.exchange(...).
        final ResponseImpalaDTO responseImpalaDTO = new ResponseImpalaDTO();
        responseImpalaDTO.setCode(0);
        responseImpalaDTO.setTotal(0);
        responseImpalaDTO.setData("data");
        when(mockEsHttpClientService.exchange("registrationDetailImpalaUrl", HttpMethod.POST, ResponseImpalaDTO.class,
                new HashMap<>())).thenReturn(responseImpalaDTO);

        // Run the test
        final BaseSearch result = registrationKanbanServiceUnderTest.findByImpala(searchVo);

        // Verify the results
    }

    @Test
    void testFindByEs() {
        // Setup
        final RegisterDetailSearchVo searchVo = new RegisterDetailSearchVo();
        searchVo.setResourceId(0L);
        searchVo.setQueryMap(new HashMap<>());
        searchVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        searchVo.setGroupBy(Arrays.asList(aggregationCondition));
        searchVo.setInstituteId("instituteId");
        searchVo.setRegistrationDate("registrationDate");
        searchVo.setDesensitization(false);

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("registrationDetailESUrl", HttpMethod.POST, ESBaseData.class,
                "requestBody")).thenReturn(esBaseData);

        // Run the test
        final BaseSearch result = registrationKanbanServiceUnderTest.findByEs(searchVo);

        // Verify the results
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final RegisterDetailSearchVo searchVo = new RegisterDetailSearchVo();
        searchVo.setResourceId(0L);
        searchVo.setQueryMap(new HashMap<>());
        searchVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        searchVo.setGroupBy(Arrays.asList(aggregationCondition));
        searchVo.setInstituteId("instituteId");
        searchVo.setRegistrationDate("registrationDate");
        searchVo.setDesensitization(false);

        // Run the test
        final BaseSearch result = registrationKanbanServiceUnderTest.findGroupBy(searchVo);

        // Verify the results
    }

    @Test
    void testQueryKanban() throws Exception {
        // Setup
        final RegistrationKanbanSearchVo vo = new RegistrationKanbanSearchVo();
        vo.setInstituteId("instituteId");
        vo.setRegistrationDate("registrationDate");

        final RegistrationKanbanSummary expectedResult = new RegistrationKanbanSummary();

        // Configure ESHttpClientService.exchange(...).
        final ResponseImpalaDTO responseImpalaDTO = new ResponseImpalaDTO();
        responseImpalaDTO.setCode(0);
        responseImpalaDTO.setTotal(0);
        responseImpalaDTO.setData("data");
        when(mockEsHttpClientService.exchange("registrationSummaryImpalaUrl", HttpMethod.POST, ResponseImpalaDTO.class,
                new HashMap<>())).thenReturn(responseImpalaDTO);

        // Configure DepartmentAppointmentService.getMaxAndMinMoney(...).
        final UserConfig userConfig = new UserConfig();
        userConfig.setId(0L);
        userConfig.setUserId(0L);
        userConfig.setDepartmentId("departmentId");
        userConfig.setMinMoney("minMoney");
        userConfig.setMaxMoney("maxMoney");
        when(mockDepartmentAppointmentService.getMaxAndMinMoney()).thenReturn(userConfig);

        // Run the test
        final RegistrationKanbanSummary result = registrationKanbanServiceUnderTest.queryKanban(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testQueryKanban_ThrowsException() {
        // Setup
        final RegistrationKanbanSearchVo vo = new RegistrationKanbanSearchVo();
        vo.setInstituteId("instituteId");
        vo.setRegistrationDate("registrationDate");

        // Configure ESHttpClientService.exchange(...).
        final ResponseImpalaDTO responseImpalaDTO = new ResponseImpalaDTO();
        responseImpalaDTO.setCode(0);
        responseImpalaDTO.setTotal(0);
        responseImpalaDTO.setData("data");
        when(mockEsHttpClientService.exchange("registrationSummaryImpalaUrl", HttpMethod.POST, ResponseImpalaDTO.class,
                new HashMap<>())).thenReturn(responseImpalaDTO);

        // Configure DepartmentAppointmentService.getMaxAndMinMoney(...).
        final UserConfig userConfig = new UserConfig();
        userConfig.setId(0L);
        userConfig.setUserId(0L);
        userConfig.setDepartmentId("departmentId");
        userConfig.setMinMoney("minMoney");
        userConfig.setMaxMoney("maxMoney");
        when(mockDepartmentAppointmentService.getMaxAndMinMoney()).thenReturn(userConfig);

        // Run the test
        assertThatThrownBy(() -> registrationKanbanServiceUnderTest.queryKanban(vo)).isInstanceOf(Exception.class);
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final RegisterDetailSearchVo searchVo = new RegisterDetailSearchVo();
        searchVo.setResourceId(0L);
        searchVo.setQueryMap(new HashMap<>());
        searchVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        searchVo.setGroupBy(Arrays.asList(aggregationCondition));
        searchVo.setInstituteId("instituteId");
        searchVo.setRegistrationDate("registrationDate");
        searchVo.setDesensitization(false);

        // Configure ESHttpClientService.exchange(...).
        final ResponseImpalaDTO responseImpalaDTO = new ResponseImpalaDTO();
        responseImpalaDTO.setCode(0);
        responseImpalaDTO.setTotal(0);
        responseImpalaDTO.setData("data");
        when(mockEsHttpClientService.exchange("registrationDetailImpalaUrl", HttpMethod.POST, ResponseImpalaDTO.class,
                new HashMap<>())).thenReturn(responseImpalaDTO);

        // Run the test
        final BaseSearch result = registrationKanbanServiceUnderTest.find(searchVo);

        // Verify the results
    }

    @Test
    void testFind_ThrowsException() {
        // Setup
        final RegisterDetailSearchVo searchVo = new RegisterDetailSearchVo();
        searchVo.setResourceId(0L);
        searchVo.setQueryMap(new HashMap<>());
        searchVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        searchVo.setGroupBy(Arrays.asList(aggregationCondition));
        searchVo.setInstituteId("instituteId");
        searchVo.setRegistrationDate("registrationDate");
        searchVo.setDesensitization(false);

        // Configure ESHttpClientService.exchange(...).
        final ResponseImpalaDTO responseImpalaDTO = new ResponseImpalaDTO();
        responseImpalaDTO.setCode(0);
        responseImpalaDTO.setTotal(0);
        responseImpalaDTO.setData("data");
        when(mockEsHttpClientService.exchange("registrationDetailImpalaUrl", HttpMethod.POST, ResponseImpalaDTO.class,
                new HashMap<>())).thenReturn(responseImpalaDTO);

        // Run the test
        assertThatThrownBy(() -> registrationKanbanServiceUnderTest.find(searchVo)).isInstanceOf(Exception.class);
    }
}
