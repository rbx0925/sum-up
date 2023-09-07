package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.*;
import com.ikang.idata.common.entity.vo.AIcomputeVo;
import com.ikang.idata.common.entity.vo.BRMTypeToChiefZone;
import com.ikang.idata.common.entity.vo.BRMZone;
import com.ikang.idata.common.entity.vo.UserChannelAndHospital;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.ReturnDataFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AIcomputeServiceTest {

    @Mock
    private UserSearchService mockUserSearchService;
    @Mock
    private GeneralService mockGeneralService;
    @Mock
    private ReturnDataFeignServiceImpl mockReturnDataFeignService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;

    @InjectMocks
    private AIcomputeService aIcomputeServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final AIcomputeVo aIcomputeVo = new AIcomputeVo();
        aIcomputeVo.setResourceId(0L);
        aIcomputeVo.setQueryMap(new HashMap<>());
        aIcomputeVo.setPageNum(0);
        aIcomputeVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aIcomputeVo.setGroupBy(Arrays.asList(aggregationCondition));
        aIcomputeVo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        aIcomputeVo.setDesensitization(false);
        aIcomputeVo.setInstituteId("instituteId");
        aIcomputeVo.setHospnameName("hospnameName");
        aIcomputeVo.setItemName("itemName");
        aIcomputeVo.setDepartmentCode("departmentCode");
        aIcomputeVo.setAreanameName("areanameName");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setPermissionFlag(0);
        userChannelAndHospital.setDataFlags(Arrays.asList(0));
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BaseSearch result = aIcomputeServiceUnderTest.find(aIcomputeVo);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);

        // Confirm GeneralService.returnDateAfterHandle(...).
        final ReturnData el = new ReturnData();
        el.setFieldDesc("fieldDesc");
        el.setSumOrAvgFlag(0);
        el.setReturnDataCode("field");
        el.setReturnDataName("结果数量");
        el.setReturnDataDeals(0);
        el.setReturnDataValue("returnDataValue");
        el.setShowFlag(0);
        el.setGroupFlag(0);
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> byType = Arrays.asList(dictInfo);
        verify(mockGeneralService).returnDateAfterHandle(new HashMap<>(), new HashMap<>(), el, byType);
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final AIcomputeVo aIcomputeVo = new AIcomputeVo();
        aIcomputeVo.setResourceId(0L);
        aIcomputeVo.setQueryMap(new HashMap<>());
        aIcomputeVo.setPageNum(0);
        aIcomputeVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aIcomputeVo.setGroupBy(Arrays.asList(aggregationCondition));
        aIcomputeVo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        aIcomputeVo.setDesensitization(false);
        aIcomputeVo.setInstituteId("instituteId");
        aIcomputeVo.setHospnameName("hospnameName");
        aIcomputeVo.setItemName("itemName");
        aIcomputeVo.setDepartmentCode("departmentCode");
        aIcomputeVo.setAreanameName("areanameName");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setPermissionFlag(0);
        userChannelAndHospital.setDataFlags(Arrays.asList(0));
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BaseSearch result = aIcomputeServiceUnderTest.findGroupBy(aIcomputeVo);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
        verify(mockUserSearchService).getBuilderByGroup(any(BaseVO.class),
                eq(new SearchSourceBuilder(StreamInput.wrap("content".getBytes()))), eq(Arrays.asList("value")));
        verify(mockUserSearchService).getSumAvg(eq(Arrays.asList("value")), any(BaseSearch.class),
                eq(new JSONObject(0, false)));

        // Confirm UserSearchService.getGroupOneAndTwo(...).
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> collect = Arrays.asList(returnData);
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> byTypes = Arrays.asList(dictInfo);
        verify(mockUserSearchService).getGroupOneAndTwo(eq(Arrays.asList("value")), eq(collect), eq(byTypes),
                any(BaseSearch.class), any(BaseVO.class), eq(new JSONObject(0, false)));
    }

    @Test
    void testGetGroupTwo() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> collect = Arrays.asList(returnData);
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> byTypes = Arrays.asList(dictInfo);
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        baseSearch.setColumnNames(Arrays.asList(new HashMap<>()));
        baseSearch.setSumOrAvgMap(new HashMap<>());
        baseSearch.setListMap(Arrays.asList(new HashMap<>()));

        final JSONObject aggregation = new JSONObject(0, false);

        // Run the test
        aIcomputeServiceUnderTest.getGroupTwo(Arrays.asList("value"), collect, byTypes, baseSearch, aggregation,
                Arrays.asList("value"), Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testGetGroupOne() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> collect = Arrays.asList(returnData);
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> byTypes = Arrays.asList(dictInfo);
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        baseSearch.setColumnNames(Arrays.asList(new HashMap<>()));
        baseSearch.setSumOrAvgMap(new HashMap<>());
        baseSearch.setListMap(Arrays.asList(new HashMap<>()));

        final JSONObject aggregation = new JSONObject(0, false);

        // Run the test
        aIcomputeServiceUnderTest.getGroupOne(Arrays.asList("value"), collect, byTypes, baseSearch, aggregation,
                Arrays.asList("value"), Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testGetSumAvg() {
        // Setup
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        baseSearch.setColumnNames(Arrays.asList(new HashMap<>()));
        baseSearch.setSumOrAvgMap(new HashMap<>());
        baseSearch.setListMap(Arrays.asList(new HashMap<>()));

        final JSONObject aggregation = new JSONObject(0, false);

        // Run the test
        aIcomputeServiceUnderTest.getSumAvg(Arrays.asList("value"), baseSearch, aggregation);

        // Verify the results
    }

    @Test
    void testHead() {
        // Setup
        final List<Map<String, Object>> list = Arrays.asList(new HashMap<>());

        // Run the test
        final List result = aIcomputeServiceUnderTest.head(list);

        // Verify the results
    }

    @Test
    void testDataListGroup() {
        // Setup
        final List<Map<String, Object>> listMap = Arrays.asList(new HashMap<>());
        final List<Map<String, Object>> columnNames = Arrays.asList(new HashMap<>());

        // Run the test
        final List<List<String>> result = aIcomputeServiceUnderTest.dataListGroup(listMap, columnNames);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList(Arrays.asList("value")));
    }

    @Test
    void testDataList() {
        // Setup
        final List<Map<String, Object>> listMap = Arrays.asList(new HashMap<>());
        final List<Map<String, Object>> columnNames = Arrays.asList(new HashMap<>());

        // Run the test
        final List<List<Object>> result = aIcomputeServiceUnderTest.dataList(listMap, columnNames);

        // Verify the results
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setPermissionFlag(0);
        userChannelAndHospital.setDataFlags(Arrays.asList(0));
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BoolQueryBuilder result = aIcomputeServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }
}
