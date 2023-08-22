package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.ReturnData;
import com.ikang.idata.common.entity.vo.UpSpaceRecordVO;
import com.ikang.idata.search.search.feign.impl.ReturnDataFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpSpaceRecordServiceTest {

    @Mock
    private UserSearchService mockUserSearchService;
    @Mock
    private ReturnDataFeignServiceImpl mockReturnDataFeignService;

    @InjectMocks
    private UpSpaceRecordService upSpaceRecordServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final UpSpaceRecordVO vo = new UpSpaceRecordVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setExamDate("examDate");
        vo.setWorkNo("workNo");
        vo.setCustomerName("customerName");
        vo.setOriginalHospLine("originalHospLine");
        vo.setOriginalHospLineCopy("originalHospLineCopy");
        vo.setHospLineId("hospLineId");
        vo.setHospLineIdCopy("hospLineIdCopy");
        vo.setAreanameCode("areanameCode");

        // Run the test
        final BaseSearch result = upSpaceRecordServiceUnderTest.find(vo);

        // Verify the results
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final UpSpaceRecordVO vo = new UpSpaceRecordVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setExamDate("examDate");
        vo.setWorkNo("workNo");
        vo.setCustomerName("customerName");
        vo.setOriginalHospLine("originalHospLine");
        vo.setOriginalHospLineCopy("originalHospLineCopy");
        vo.setHospLineId("hospLineId");
        vo.setHospLineIdCopy("hospLineIdCopy");
        vo.setAreanameCode("areanameCode");

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("returnDataCode");
        returnData1.setReturnDataName("returnDataName");
        returnData1.setDataType(0);
        returnData1.setDataValue("dataValue");
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setReturnDataMemo("returnDataMemo");
        returnData1.setSort(0);
        returnData1.setDeleteStatus(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data",
                0L)).thenReturn(returnData);

        // Run the test
        final BaseSearch result = upSpaceRecordServiceUnderTest.findGroupBy(vo);

        // Verify the results
    }

    @Test
    void testFindGroupBy_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        final UpSpaceRecordVO vo = new UpSpaceRecordVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setExamDate("examDate");
        vo.setWorkNo("workNo");
        vo.setCustomerName("customerName");
        vo.setOriginalHospLine("originalHospLine");
        vo.setOriginalHospLineCopy("originalHospLineCopy");
        vo.setHospLineId("hospLineId");
        vo.setHospLineIdCopy("hospLineIdCopy");
        vo.setAreanameCode("areanameCode");

        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data",
                0L)).thenReturn(Collections.emptyList());

        // Run the test
        final BaseSearch result = upSpaceRecordServiceUnderTest.findGroupBy(vo);

        // Verify the results
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = upSpaceRecordServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
