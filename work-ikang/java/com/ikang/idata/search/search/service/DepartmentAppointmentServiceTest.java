package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.BranchAppointmentBoard;
import com.ikang.idata.common.entity.UserConfig;
import com.ikang.idata.common.entity.es.ESBaseData;
import com.ikang.idata.common.entity.vo.BranchAppointmentBoardQo;
import com.ikang.idata.common.entity.vo.DepartmentAppointmentQo;
import com.ikang.idata.search.search.feign.impl.SystemUserConfigFeignServiceImpl;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentAppointmentServiceTest {

    @Mock
    private ESHttpClientService mockEsHttpClientService;
    @Mock
    private SystemUserConfigFeignServiceImpl mockSystemUserConfigFeignService;

    private DepartmentAppointmentService departmentAppointmentServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        departmentAppointmentServiceUnderTest = new DepartmentAppointmentService(mockEsHttpClientService,
                mockSystemUserConfigFeignService);
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final DepartmentAppointmentQo qo = new DepartmentAppointmentQo();
        qo.setResourceId(0L);
        qo.setQueryMap(new HashMap<>());
        qo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        qo.setGroupBy(Arrays.asList(aggregationCondition));
        qo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        qo.setInstituteId("instituteId");
        qo.setAreanameId("areanameId");
        qo.setRegdateDate("regdateDate");

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
        final BaseSearch result = departmentAppointmentServiceUnderTest.find(qo);

        // Verify the results
    }

    @Test
    void testQueryKanban() {
        // Setup
        final BranchAppointmentBoardQo qo = new BranchAppointmentBoardQo();
        qo.setInstituteId("instituteId");
        qo.setAreaId("areaId");
        qo.setRegdateDate("regdateDate");
        qo.setRegdateDateStart("regdateDateStart");
        qo.setOccupiedOrNot("occupiedOrNot");

        // Configure SystemUserConfigFeignServiceImpl.queryUserConfig(...).
        final UserConfig userConfig = new UserConfig();
        userConfig.setId(0L);
        userConfig.setUserId(0L);
        userConfig.setDepartmentId("departmentId");
        userConfig.setMinMoney("minMoney");
        userConfig.setMaxMoney("maxMoney");
        when(mockSystemUserConfigFeignService.queryUserConfig(0L)).thenReturn(userConfig);

        // Run the test
        final BranchAppointmentBoard result = departmentAppointmentServiceUnderTest.queryKanban(qo);

        // Verify the results
    }

    @Test
    void testIsEmpty() {
        assertThat(departmentAppointmentServiceUnderTest.isEmpty("s")).isFalse();
    }

    @Test
    void testCalculation() {
        assertThat(departmentAppointmentServiceUnderTest.Calculation("v1", "v2")).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final DepartmentAppointmentQo qo = new DepartmentAppointmentQo();
        qo.setResourceId(0L);
        qo.setQueryMap(new HashMap<>());
        qo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        qo.setGroupBy(Arrays.asList(aggregationCondition));
        qo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        qo.setInstituteId("instituteId");
        qo.setAreanameId("areanameId");
        qo.setRegdateDate("regdateDate");

        // Run the test
        final BaseSearch result = departmentAppointmentServiceUnderTest.findGroupBy(qo);

        // Verify the results
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = departmentAppointmentServiceUnderTest.assemblyConditions(
                originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetMaxAndMinMoney() {
        // Setup
        final UserConfig expectedResult = new UserConfig();
        expectedResult.setId(0L);
        expectedResult.setUserId(0L);
        expectedResult.setDepartmentId("departmentId");
        expectedResult.setMinMoney("minMoney");
        expectedResult.setMaxMoney("maxMoney");

        // Configure SystemUserConfigFeignServiceImpl.queryUserConfig(...).
        final UserConfig userConfig = new UserConfig();
        userConfig.setId(0L);
        userConfig.setUserId(0L);
        userConfig.setDepartmentId("departmentId");
        userConfig.setMinMoney("minMoney");
        userConfig.setMaxMoney("maxMoney");
        when(mockSystemUserConfigFeignService.queryUserConfig(0L)).thenReturn(userConfig);

        // Run the test
        final UserConfig result = departmentAppointmentServiceUnderTest.getMaxAndMinMoney();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
