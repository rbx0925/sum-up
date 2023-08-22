package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.TollCollectVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TollCollectServiceTest {

    @Mock
    private UserSearchService mockUserSearchService;

    private TollCollectService tollCollectServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        tollCollectServiceUnderTest = new TollCollectService(mockUserSearchService);
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final TollCollectVo tollCollectVo = new TollCollectVo();
        tollCollectVo.setResourceId(0L);
        tollCollectVo.setQueryMap(new HashMap<>());
        tollCollectVo.setPageNum(0);
        tollCollectVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        tollCollectVo.setGroupBy(Arrays.asList(aggregationCondition));
        tollCollectVo.setId("id");
        tollCollectVo.setRegMonth("regMonth");
        tollCollectVo.setRangePickerMonthStart("rangePickerMonthStart");
        tollCollectVo.setRangePickerMonthEnd("rangePickerMonthEnd");
        tollCollectVo.setAreaId("areaId");
        tollCollectVo.setAreaName("areaName");
        tollCollectVo.setLocId("locId");

        // Run the test
        final BaseSearch result = tollCollectServiceUnderTest.find(tollCollectVo);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final TollCollectVo tollCollectVo = new TollCollectVo();
        tollCollectVo.setResourceId(0L);
        tollCollectVo.setQueryMap(new HashMap<>());
        tollCollectVo.setPageNum(0);
        tollCollectVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        tollCollectVo.setGroupBy(Arrays.asList(aggregationCondition));
        tollCollectVo.setId("id");
        tollCollectVo.setRegMonth("regMonth");
        tollCollectVo.setRangePickerMonthStart("rangePickerMonthStart");
        tollCollectVo.setRangePickerMonthEnd("rangePickerMonthEnd");
        tollCollectVo.setAreaId("areaId");
        tollCollectVo.setAreaName("areaName");
        tollCollectVo.setLocId("locId");

        // Run the test
        final BaseSearch result = tollCollectServiceUnderTest.findGroupBy(tollCollectVo);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testExportExcel() throws Exception {
        // Setup
        final TollCollectVo tollCollectVo = new TollCollectVo();
        tollCollectVo.setResourceId(0L);
        tollCollectVo.setQueryMap(new HashMap<>());
        tollCollectVo.setPageNum(0);
        tollCollectVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        tollCollectVo.setGroupBy(Arrays.asList(aggregationCondition));
        tollCollectVo.setId("id");
        tollCollectVo.setRegMonth("regMonth");
        tollCollectVo.setRangePickerMonthStart("rangePickerMonthStart");
        tollCollectVo.setRangePickerMonthEnd("rangePickerMonthEnd");
        tollCollectVo.setAreaId("areaId");
        tollCollectVo.setAreaName("areaName");
        tollCollectVo.setLocId("locId");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        tollCollectServiceUnderTest.exportExcel(tollCollectVo, response);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testExportExcel_ThrowsException() throws Exception {
        // Setup
        final TollCollectVo tollCollectVo = new TollCollectVo();
        tollCollectVo.setResourceId(0L);
        tollCollectVo.setQueryMap(new HashMap<>());
        tollCollectVo.setPageNum(0);
        tollCollectVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        tollCollectVo.setGroupBy(Arrays.asList(aggregationCondition));
        tollCollectVo.setId("id");
        tollCollectVo.setRegMonth("regMonth");
        tollCollectVo.setRangePickerMonthStart("rangePickerMonthStart");
        tollCollectVo.setRangePickerMonthEnd("rangePickerMonthEnd");
        tollCollectVo.setAreaId("areaId");
        tollCollectVo.setAreaName("areaName");
        tollCollectVo.setLocId("locId");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> tollCollectServiceUnderTest.exportExcel(tollCollectVo, response))
                .isInstanceOf(Exception.class);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = tollCollectServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }
}
