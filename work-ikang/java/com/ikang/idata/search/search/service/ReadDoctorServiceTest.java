package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.ReadCenterDoctorVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ReadDoctorServiceTest {

    private ReadDoctorService readDoctorServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        readDoctorServiceUnderTest = new ReadDoctorService();
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final ReadCenterDoctorVo vo = new ReadCenterDoctorVo();
        vo.setResourceId(0L);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setReaderLocationname("readerLocationname");
        vo.setDepartmentName("departmentName");
        vo.setDoctorName("doctorName");
        vo.setExamineDateStart("examineDateStart");
        vo.setExamineDateEnd("examineDateEnd");
        vo.setDoctorAccountType("doctorAccountType");
        vo.setDoctorNcc("doctorNcc");

        // Run the test
        final BaseSearch result = readDoctorServiceUnderTest.find(vo);

        // Verify the results
    }

    @Test
    void testAssemblyConditions() throws Exception {
        assertThat(readDoctorServiceUnderTest.assemblyConditions(new JSONObject(0, false))).isNull();
    }
}
