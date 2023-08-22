package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.search.search.entity.vo.ReportInterpretationVO;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class ReportInterpretationDetailsServiceTest {

    private ReportInterpretationDetailsService reportInterpretationDetailsServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        reportInterpretationDetailsServiceUnderTest = new ReportInterpretationDetailsService();
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = reportInterpretationDetailsServiceUnderTest.assemblyConditions(
                originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final ReportInterpretationVO searchVO = new ReportInterpretationVO();
        searchVO.setResourceId(0L);
        searchVO.setQueryMap(new HashMap<>());
        searchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        searchVO.setGroupBy(Arrays.asList(aggregationCondition));
        searchVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        searchVO.setDesensitization(false);
        searchVO.setAreaType("areaType");
        searchVO.setAreaName("areaName");
        searchVO.setHospId("hospId");

        // Run the test
        final BaseSearch result = reportInterpretationDetailsServiceUnderTest.find(searchVO);

        // Verify the results
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final ReportInterpretationVO searchVO = new ReportInterpretationVO();
        searchVO.setResourceId(0L);
        searchVO.setQueryMap(new HashMap<>());
        searchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        searchVO.setGroupBy(Arrays.asList(aggregationCondition));
        searchVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        searchVO.setDesensitization(false);
        searchVO.setAreaType("areaType");
        searchVO.setAreaName("areaName");
        searchVO.setHospId("hospId");

        // Run the test
        final BaseSearch result = reportInterpretationDetailsServiceUnderTest.findGroupBy(searchVO);

        // Verify the results
    }
}
