package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.search.search.entity.vo.DentalCardDataVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class DentalCardDataServiceTest {

    private DentalCardDataService dentalCardDataServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        dentalCardDataServiceUnderTest = new DentalCardDataService();
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = dentalCardDataServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final DentalCardDataVo vo = new DentalCardDataVo();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setDesensitization(false);
        vo.setInstituteId("instituteId");
        vo.setAreaId("areaId");

        // Run the test
        final BaseSearch result = dentalCardDataServiceUnderTest.find(vo);

        // Verify the results
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final DentalCardDataVo vo = new DentalCardDataVo();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setDesensitization(false);
        vo.setInstituteId("instituteId");
        vo.setAreaId("areaId");

        // Run the test
        final BaseSearch result = dentalCardDataServiceUnderTest.findGroupBy(vo);

        // Verify the results
    }
}
