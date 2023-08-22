package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.DepartmentPerformanceVo;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class DepartmentPerformanceServiceTest {

    private DepartmentPerformanceService departmentPerformanceServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        departmentPerformanceServiceUnderTest = new DepartmentPerformanceService();
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final DepartmentPerformanceVo vo = new DepartmentPerformanceVo();
        vo.setResourceId(0L);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setAreanameId("areanameId");
        vo.setLocidId("locidId");
        vo.setDepartmentName("departmentName");
//        vo.setExamineDateStart("examineDateStart");
//        vo.setExamineDateEnd("examineDateEnd");
//        vo.setCheckDateStart("checkDateStart");
//        vo.setCheckDateEnd("checkDateEnd");
//
        // Run the test
        final BaseSearch result = departmentPerformanceServiceUnderTest.find(vo);

        // Verify the results
    }

    @Test
    void testFindCheckNumByUserCheckItem() {
        // Setup
        final DepartmentPerformanceVo vo = new DepartmentPerformanceVo();
        vo.setResourceId(0L);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setAreanameId("areanameId");
        vo.setLocidId("locidId");
        vo.setDepartmentName("departmentName");
//        vo.setExamineDateStart("examineDateStart");
//        vo.setExamineDateEnd("examineDateEnd");
//        vo.setCheckDateStart("checkDateStart");
//        vo.setCheckDateEnd("checkDateEnd");

        final JSONObject expectedResult = new JSONObject(0, false);

        // Run the test
        final JSONObject result = departmentPerformanceServiceUnderTest.findCheckNumByUserCheckItem(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testBuildAggregationConditions() {
        // Setup
        // Run the test
        final AggregationBuilder result = departmentPerformanceServiceUnderTest.buildAggregationConditions();

        // Verify the results
    }

    @Test
    void testBuildQueryCriteria() {
        // Setup
        final DepartmentPerformanceVo vo = new DepartmentPerformanceVo();
        vo.setResourceId(0L);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setAreanameId("areanameId");
        vo.setLocidId("locidId");
        vo.setDepartmentName("departmentName");
//        vo.setExamineDateStart("examineDateStart");
//        vo.setExamineDateEnd("examineDateEnd");
//        vo.setCheckDateStart("checkDateStart");
//        vo.setCheckDateEnd("checkDateEnd");

        // Run the test
        final QueryBuilder result = departmentPerformanceServiceUnderTest.buildQueryCriteria(vo);

        // Verify the results
    }

    @Test
    void testBuildQuery() {
        // Setup
        final DepartmentPerformanceVo vo = new DepartmentPerformanceVo();
        vo.setResourceId(0L);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setAreanameId("areanameId");
        vo.setLocidId("locidId");
        vo.setDepartmentName("departmentName");
//        vo.setExamineDateStart("examineDateStart");
//        vo.setExamineDateEnd("examineDateEnd");
//        vo.setCheckDateStart("checkDateStart");
//        vo.setCheckDateEnd("checkDateEnd");

        // Run the test
        final QueryBuilder result = departmentPerformanceServiceUnderTest.buildQuery(vo);

        // Verify the results
    }

    @Test
    void testParseResultData() {
        // Setup
        final JSONObject jsonObject = new JSONObject(0, false);
        final JSONObject object = new JSONObject(0, false);

        // Run the test
        final BaseSearch result = departmentPerformanceServiceUnderTest.parseResultData(jsonObject, 0L, object);

        // Verify the results
    }

    @Test
    void testAssemblyConditions() throws Exception {
        assertThat(departmentPerformanceServiceUnderTest.assemblyConditions(new JSONObject(0, false))).isNull();
    }
}
