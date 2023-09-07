package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.search.search.entity.vo.SatisfactionWithSMSVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class SatisfactionWithSMSServiceTest {

    private SatisfactionWithSMSService satisfactionWithSMSServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        satisfactionWithSMSServiceUnderTest = new SatisfactionWithSMSService();
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = satisfactionWithSMSServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final SatisfactionWithSMSVo vo = new SatisfactionWithSMSVo();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setInstituteId("instituteId");
        vo.setAreaidCode("areaidCode");

        // Run the test
        final BaseSearch result = satisfactionWithSMSServiceUnderTest.find(vo);

        // Verify the results
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final SatisfactionWithSMSVo vo = new SatisfactionWithSMSVo();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setInstituteId("instituteId");
        vo.setAreaidCode("areaidCode");

        // Run the test
        final BaseSearch result = satisfactionWithSMSServiceUnderTest.findGroupBy(vo);

        // Verify the results
    }
}
