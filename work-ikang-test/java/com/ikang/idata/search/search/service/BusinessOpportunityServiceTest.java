package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.CiTy;
import com.ikang.idata.common.entity.vo.OpportunityVO;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusinessOpportunityServiceTest {

    @Mock
    private UserSearchService mockUserSearchService;
    @Mock
    private BusinessService mockBusinessService;

    private BusinessOpportunityService businessOpportunityServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        businessOpportunityServiceUnderTest = new BusinessOpportunityService(mockUserSearchService,
                mockBusinessService);
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));
        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        final BoolQueryBuilder result = businessOpportunityServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testAssemblyConditions_BusinessServiceReturnsNoItems() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));
        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Collections.emptyList());

        // Run the test
        final BoolQueryBuilder result = businessOpportunityServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testFindOpportunity() {
        // Setup
        final OpportunityVO opportunityVO = new OpportunityVO();
        opportunityVO.setResourceId(0L);
        opportunityVO.setQueryMap(new HashMap<>());
        opportunityVO.setPageNum(0);
        opportunityVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        opportunityVO.setGroupBy(Arrays.asList(aggregationCondition));
        opportunityVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        opportunityVO.setDesensitization(false);
        opportunityVO.setChooseType("chooseType");
        opportunityVO.setXmbh("xmbh");
        opportunityVO.setOwnerId("ownerId");
        opportunityVO.setKhmc("khmc");
        opportunityVO.setName("name");
        opportunityVO.setType("type");
        opportunityVO.setSsdq("ssdq");
        opportunityVO.setDaqu("daqu");
        opportunityVO.setJsrqStart("jsrqStart");
        opportunityVO.setJsrqEnd("jsrqEnd");
        opportunityVO.setJieDuan("jieDuan");
        opportunityVO.setRecordType("recordType");

        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        final BaseSearch result = businessOpportunityServiceUnderTest.findOpportunity(opportunityVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testFindOpportunity_BusinessServiceReturnsNoItems() {
        // Setup
        final OpportunityVO opportunityVO = new OpportunityVO();
        opportunityVO.setResourceId(0L);
        opportunityVO.setQueryMap(new HashMap<>());
        opportunityVO.setPageNum(0);
        opportunityVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        opportunityVO.setGroupBy(Arrays.asList(aggregationCondition));
        opportunityVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        opportunityVO.setDesensitization(false);
        opportunityVO.setChooseType("chooseType");
        opportunityVO.setXmbh("xmbh");
        opportunityVO.setOwnerId("ownerId");
        opportunityVO.setKhmc("khmc");
        opportunityVO.setName("name");
        opportunityVO.setType("type");
        opportunityVO.setSsdq("ssdq");
        opportunityVO.setDaqu("daqu");
        opportunityVO.setJsrqStart("jsrqStart");
        opportunityVO.setJsrqEnd("jsrqEnd");
        opportunityVO.setJieDuan("jieDuan");
        opportunityVO.setRecordType("recordType");

        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Collections.emptyList());

        // Run the test
        final BaseSearch result = businessOpportunityServiceUnderTest.findOpportunity(opportunityVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testExportExcelOpportunity() throws Exception {
        // Setup
        final OpportunityVO opportunityVO = new OpportunityVO();
        opportunityVO.setResourceId(0L);
        opportunityVO.setQueryMap(new HashMap<>());
        opportunityVO.setPageNum(0);
        opportunityVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        opportunityVO.setGroupBy(Arrays.asList(aggregationCondition));
        opportunityVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        opportunityVO.setDesensitization(false);
        opportunityVO.setChooseType("chooseType");
        opportunityVO.setXmbh("xmbh");
        opportunityVO.setOwnerId("ownerId");
        opportunityVO.setKhmc("khmc");
        opportunityVO.setName("name");
        opportunityVO.setType("type");
        opportunityVO.setSsdq("ssdq");
        opportunityVO.setDaqu("daqu");
        opportunityVO.setJsrqStart("jsrqStart");
        opportunityVO.setJsrqEnd("jsrqEnd");
        opportunityVO.setJieDuan("jieDuan");
        opportunityVO.setRecordType("recordType");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        businessOpportunityServiceUnderTest.exportExcelOpportunity(opportunityVO, response);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testExportExcelOpportunity_BusinessServiceReturnsNoItems() throws Exception {
        // Setup
        final OpportunityVO opportunityVO = new OpportunityVO();
        opportunityVO.setResourceId(0L);
        opportunityVO.setQueryMap(new HashMap<>());
        opportunityVO.setPageNum(0);
        opportunityVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        opportunityVO.setGroupBy(Arrays.asList(aggregationCondition));
        opportunityVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        opportunityVO.setDesensitization(false);
        opportunityVO.setChooseType("chooseType");
        opportunityVO.setXmbh("xmbh");
        opportunityVO.setOwnerId("ownerId");
        opportunityVO.setKhmc("khmc");
        opportunityVO.setName("name");
        opportunityVO.setType("type");
        opportunityVO.setSsdq("ssdq");
        opportunityVO.setDaqu("daqu");
        opportunityVO.setJsrqStart("jsrqStart");
        opportunityVO.setJsrqEnd("jsrqEnd");
        opportunityVO.setJieDuan("jieDuan");
        opportunityVO.setRecordType("recordType");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Collections.emptyList());

        // Run the test
        businessOpportunityServiceUnderTest.exportExcelOpportunity(opportunityVO, response);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }
}
