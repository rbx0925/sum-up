package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.HospitalPerformanceVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class HospitalPerformanceServiceTest {

    private HospitalPerformanceService hospitalPerformanceServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        hospitalPerformanceServiceUnderTest = new HospitalPerformanceService();
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final HospitalPerformanceVo vo = new HospitalPerformanceVo();
        vo.setResourceId(0L);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setAreanameId("areanameId");
        vo.setLocidId("locidId");
//        vo.setExamineDateStart("examineDateStart");
//        vo.setExamineDateEnd("examineDateEnd");

        // Run the test
        final BaseSearch result = hospitalPerformanceServiceUnderTest.find(vo);

        // Verify the results
    }

//    @Test
//    void testParseResultData() {
//        // Setup
//        final JSONObject jsonObject = new JSONObject(0, false);
//
//        // Run the test
//        final BaseSearch result = hospitalPerformanceServiceUnderTest.parseResultData(jsonObject, 0L, checkNumberJson);
//
//        // Verify the results
//    }

    @Test
    void testAssemblyConditions() throws Exception {
        assertThat(hospitalPerformanceServiceUnderTest.assemblyConditions(new JSONObject(0, false))).isNull();
    }
}
