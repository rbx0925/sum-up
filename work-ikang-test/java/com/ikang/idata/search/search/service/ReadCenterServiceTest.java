package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.ReadCenterVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ReadCenterServiceTest {

    private ReadCenterService readCenterServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        readCenterServiceUnderTest = new ReadCenterService();
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final ReadCenterVo vo = new ReadCenterVo();
        vo.setResourceId(0L);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setExamineDateStart("examineDateStart");
        vo.setExamineDateEnd("examineDateEnd");
        vo.setReaderLocationname("readerLocationname");

        // Run the test
        final BaseSearch result = readCenterServiceUnderTest.find(vo);

        // Verify the results
    }

    @Test
    void testAssemblyConditions() throws Exception {
        assertThat(readCenterServiceUnderTest.assemblyConditions(new JSONObject(0, false))).isNull();
    }
}
