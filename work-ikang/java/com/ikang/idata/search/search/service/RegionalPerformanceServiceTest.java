package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.RegionalPerformanceVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class RegionalPerformanceServiceTest {

    private RegionalPerformanceService regionalPerformanceServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        regionalPerformanceServiceUnderTest = new RegionalPerformanceService();
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final RegionalPerformanceVo vo = new RegionalPerformanceVo();
        vo.setResourceId(0L);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setDesensitization(false);
        vo.setAreanameId("areanameId");
        vo.setTheStatisticalPeriodBegins("theStatisticalPeriodBegins");
        vo.setEndOfTheStatisticalPeriod("endOfTheStatisticalPeriod");

        // Run the test
        final BaseSearch result = regionalPerformanceServiceUnderTest.find(vo);

        // Verify the results
    }

    @Test
    void testCheckNumberFind() {
        // Setup
        final RegionalPerformanceVo vo = new RegionalPerformanceVo();
        vo.setResourceId(0L);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setDesensitization(false);
        vo.setAreanameId("areanameId");
        vo.setTheStatisticalPeriodBegins("theStatisticalPeriodBegins");
        vo.setEndOfTheStatisticalPeriod("endOfTheStatisticalPeriod");

        final JSONObject expectedResult = new JSONObject(0, false);

        // Run the test
        final JSONObject result = regionalPerformanceServiceUnderTest.checkNumberFind(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testAssemblyConditions() throws Exception {
        assertThat(regionalPerformanceServiceUnderTest.assemblyConditions(new JSONObject(0, false))).isNull();
    }
}
