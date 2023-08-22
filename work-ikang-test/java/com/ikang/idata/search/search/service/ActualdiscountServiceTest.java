package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.search.search.entity.vo.ActualdiscountVO;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class ActualdiscountServiceTest {

    private ActualdiscountService actualdiscountServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        actualdiscountServiceUnderTest = new ActualdiscountService();
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = actualdiscountServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final ActualdiscountVO vo = new ActualdiscountVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setProjectid("projectid");
        vo.setQysjStart("qysjStart");
        vo.setQysjEnd("qysjEnd");
        vo.setRegdateStart("regdateStart");
        vo.setRegdateEnd("regdateEnd");
        vo.setChiefZone("chiefZone");
        vo.setJazkStart("jazkStart");
        vo.setJazkEnd("jazkEnd");
        vo.setXmpjzkStart("xmpjzkStart");
        vo.setXmpjzkEnd("xmpjzkEnd");
        vo.setVipzkStart("vipzkStart");
        vo.setVipzkEnd("vipzkEnd");
        vo.setZyzkStart("zyzkStart");
        vo.setZyzkEnd("zyzkEnd");
        vo.setZdzkStart("zdzkStart");
        vo.setZdzkEnd("zdzkEnd");

        // Run the test
        final BaseSearch result = actualdiscountServiceUnderTest.find(vo);

        // Verify the results
    }
}
