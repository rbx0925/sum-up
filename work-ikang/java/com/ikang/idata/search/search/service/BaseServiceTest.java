package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.*;
import com.ikang.idata.common.entity.es.ESBaseData;
import com.ikang.idata.common.entity.vo.*;
import com.ikang.idata.search.search.common.Com;
import com.ikang.idata.search.search.entity.vo.Statistics;
import com.ikang.idata.search.search.feign.impl.DictFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.ParamDataFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.ReturnDataFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BaseServiceTest {

    @Mock
    private Com mockCom;
    @Mock
    private DictFeignServiceImpl mockDictFeignService;
    @Mock
    private ParamDataFeignServiceImpl mockParamDataFeignService;
    @Mock
    private ReturnDataFeignServiceImpl mockReturnDataFeignService;

    @InjectMocks
    private BaseService baseServiceUnderTest;

    @Test
    void testGetQueryDsl1() throws Exception {
        // Setup
        final JSONObject vo = new JSONObject(0, false);
        final SortBuilder<?> sortBuilder = null;
        final SearchSourceBuilder expectedResult = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final SearchSourceBuilder result = baseServiceUnderTest.getQueryDsl(vo, sortBuilder);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetQueryDsl2() throws Exception {
        // Setup
        final JSONObject vo = new JSONObject(0, false);
        final SearchSourceBuilder expectedResult = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        // Run the test
        final SearchSourceBuilder result = baseServiceUnderTest.getQueryDsl(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetQueryDsl2_ReturnDataFeignServiceImplReturnsNoItems() throws Exception {
        // Setup
        final JSONObject vo = new JSONObject(0, false);
        final SearchSourceBuilder expectedResult = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(Collections.emptyList());

        // Run the test
        final SearchSourceBuilder result = baseServiceUnderTest.getQueryDsl(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPartInfos1() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setDataType(0);
        returnData.setDataValue("dataValue");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setReturnDataMemo("returnDataMemo");
        returnData.setSort(0);
        returnData.setDeleteStatus(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> expectedResult = Arrays.asList(returnData);

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData2 = new ReturnData();
        returnData2.setFieldDesc("fieldDesc");
        returnData2.setSumOrAvgFlag(0);
        returnData2.setId(0L);
        returnData2.setReturnDataIndex("returnDataIndex");
        returnData2.setReturnDataCode("field");
        returnData2.setReturnDataName("结果数量");
        returnData2.setDataType(0);
        returnData2.setDataValue("dataValue");
        returnData2.setReturnDataDeals(0);
        returnData2.setReturnDataValue("returnDataValue");
        returnData2.setShowFlag(0);
        returnData2.setReturnDataMemo("returnDataMemo");
        returnData2.setSort(0);
        returnData2.setDeleteStatus(0);
        returnData2.setGroupFlag(0);
        final List<ReturnData> returnData1 = Arrays.asList(returnData2);
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData1);

        // Run the test
        final List<ReturnData> result = baseServiceUnderTest.partInfos(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPartInfos1_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<ReturnData> result = baseServiceUnderTest.partInfos(0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testPartInfos2() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setDataType(0);
        returnData.setDataValue("dataValue");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setReturnDataMemo("returnDataMemo");
        returnData.setSort(0);
        returnData.setDeleteStatus(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> expectedResult = Arrays.asList(returnData);

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData2 = new ReturnData();
        returnData2.setFieldDesc("fieldDesc");
        returnData2.setSumOrAvgFlag(0);
        returnData2.setId(0L);
        returnData2.setReturnDataIndex("returnDataIndex");
        returnData2.setReturnDataCode("field");
        returnData2.setReturnDataName("结果数量");
        returnData2.setDataType(0);
        returnData2.setDataValue("dataValue");
        returnData2.setReturnDataDeals(0);
        returnData2.setReturnDataValue("returnDataValue");
        returnData2.setShowFlag(0);
        returnData2.setReturnDataMemo("returnDataMemo");
        returnData2.setSort(0);
        returnData2.setDeleteStatus(0);
        returnData2.setGroupFlag(0);
        final List<ReturnData> returnData1 = Arrays.asList(returnData2);
        when(mockReturnDataFeignService.listByReturnDataIndex("index")).thenReturn(returnData1);

        // Run the test
        final List<ReturnData> result = baseServiceUnderTest.partInfos("index");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testPartInfos2_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        when(mockReturnDataFeignService.listByReturnDataIndex("index")).thenReturn(Collections.emptyList());

        // Run the test
        final List<ReturnData> result = baseServiceUnderTest.partInfos("index");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testParseResult1() {
        // Setup
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
//        final BaseSearch result = baseServiceUnderTest.parseResult(esBaseData, 0L);

        // Verify the results
    }

    @Test
    void testParseResult1_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");

        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(Collections.emptyList());

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
//        final BaseSearch result = baseServiceUnderTest.parseResult(esBaseData, 0L);

        // Verify the results
    }

    @Test
    void testParseResult1_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());
        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
//        final BaseSearch result = baseServiceUnderTest.parseResult(esBaseData, 0L);

        // Verify the results
    }

    @Test
    void testParseResult2() {
        // Setup
        final JSONObject jsonObject = new JSONObject(0, false);

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
//        final BaseSearch result = baseServiceUnderTest.parseResult(jsonObject, 0L);

        // Verify the results
    }

    @Test
    void testParseResult2_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        final JSONObject jsonObject = new JSONObject(0, false);
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(Collections.emptyList());

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
//        final BaseSearch result = baseServiceUnderTest.parseResult(jsonObject, 0L);

        // Verify the results
    }

    @Test
    void testParseResult2_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final JSONObject jsonObject = new JSONObject(0, false);

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());
        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
//        final BaseSearch result = baseServiceUnderTest.parseResult(jsonObject, 0L);

        // Verify the results
    }

    @Test
    void testProcessTableList() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setDataType(0);
        returnData.setDataValue("dataValue");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setReturnDataMemo("returnDataMemo");
        returnData.setSort(0);
        returnData.setDeleteStatus(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> returnDataList = Arrays.asList(returnData);
        final List<Map<String, Object>> sourceList = Arrays.asList(new HashMap<>());

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
        final List<Map<String, Object>> result = baseServiceUnderTest.processTableList(returnDataList, sourceList);

        // Verify the results
    }

    @Test
    void testProcessTableList_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setDataType(0);
        returnData.setDataValue("dataValue");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setReturnDataMemo("returnDataMemo");
        returnData.setSort(0);
        returnData.setDeleteStatus(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> returnDataList = Arrays.asList(returnData);
        final List<Map<String, Object>> sourceList = Arrays.asList(new HashMap<>());
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());
        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
        final List<Map<String, Object>> result = baseServiceUnderTest.processTableList(returnDataList, sourceList);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetColumnNames() {
        // Setup
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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

        // Run the test
        final List<Map<String, Object>> result = baseServiceUnderTest.getColumnNames(returnData);

        // Verify the results
    }

    @Test
    void testDataList() {
        // Setup
        final List<Map<String, Object>> listMap = Arrays.asList(new HashMap<>());
        final List<Map<String, Object>> columnNames = Arrays.asList(new HashMap<>());

        // Run the test
        final List<List<Object>> result = baseServiceUnderTest.dataList(listMap, columnNames);

        // Verify the results
    }

    @Test
    void testDataListGroup() {
        // Setup
        final List<Map<String, Object>> listMap = Arrays.asList(new HashMap<>());
        final List<Map<String, Object>> columnNames = Arrays.asList(new HashMap<>());

        // Run the test
        final List<List<String>> result = baseServiceUnderTest.dataListGroup(listMap, columnNames);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList(Arrays.asList("value")));
    }

    @Test
    void testHead() {
        // Setup
        final List<Map<String, Object>> list = Arrays.asList(new HashMap<>());

        // Run the test
        final List<List<String>> result = baseServiceUnderTest.head(list);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList(Arrays.asList("value")));
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
        baseSearch.setResourceId(0L);
        baseSearch.setDesensitization(false);
        baseSearch.setSort("sort");

        final JSONObject aggregation = new JSONObject(0, false);

        // Run the test
        baseServiceUnderTest.getSumAvg(Arrays.asList("value"), baseSearch, aggregation);

        // Verify the results
    }

    @Test
    void testGetGroupOne() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setDataType(0);
        returnData.setDataValue("dataValue");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setReturnDataMemo("returnDataMemo");
        returnData.setSort(0);
        returnData.setDeleteStatus(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> collect = Arrays.asList(returnData);
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
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
        baseSearch.setResourceId(0L);
        baseSearch.setDesensitization(false);
        baseSearch.setSort("sort");

        final JSONObject aggregation = new JSONObject(0, false);

        // Run the test
        baseServiceUnderTest.getGroupOne(Arrays.asList("value"), collect, byTypes, baseSearch, aggregation,
                Arrays.asList("value"), Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testGetGroupTwo() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setDataType(0);
        returnData.setDataValue("dataValue");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setReturnDataMemo("returnDataMemo");
        returnData.setSort(0);
        returnData.setDeleteStatus(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> collect = Arrays.asList(returnData);
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
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
        baseSearch.setResourceId(0L);
        baseSearch.setDesensitization(false);
        baseSearch.setSort("sort");

        final JSONObject aggregation = new JSONObject(0, false);

        // Run the test
        baseServiceUnderTest.getGroupTwo(Arrays.asList("value"), collect, byTypes, baseSearch, aggregation,
                Arrays.asList("value"), Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testGetGroupOneAndTwo() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setDataType(0);
        returnData.setDataValue("dataValue");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setReturnDataMemo("returnDataMemo");
        returnData.setSort(0);
        returnData.setDeleteStatus(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> collect = Arrays.asList(returnData);
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
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
        baseSearch.setResourceId(0L);
        baseSearch.setDesensitization(false);
        baseSearch.setSort("sort");

        final BaseVO baseVO = new BaseVO();
        baseVO.setResourceId(0L);
        baseVO.setQueryMap(new HashMap<>());
        baseVO.setPageNum(0);
        baseVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        baseVO.setGroupBy(Arrays.asList(aggregationCondition));
        baseVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final JSONObject aggregation = new JSONObject(0, false);

        // Run the test
        baseServiceUnderTest.getGroupOneAndTwo(Arrays.asList("value"), collect, byTypes, baseSearch, baseVO,
                aggregation);

        // Verify the results
    }

    @Test
    void testGetDictInfos() {
        // Setup
        final BaseVO baseVO = new BaseVO();
        baseVO.setResourceId(0L);
        baseVO.setQueryMap(new HashMap<>());
        baseVO.setPageNum(0);
        baseVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        baseVO.setGroupBy(Arrays.asList(aggregationCondition));
        baseVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setDataType(0);
        returnData.setDataValue("dataValue");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setReturnDataMemo("returnDataMemo");
        returnData.setSort(0);
        returnData.setDeleteStatus(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> returnDataList = Arrays.asList(returnData);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final List<DictInfo> result = baseServiceUnderTest.getDictInfos(baseVO, returnDataList);

        // Verify the results
    }

    @Test
    void testGetDictInfos_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final BaseVO baseVO = new BaseVO();
        baseVO.setResourceId(0L);
        baseVO.setQueryMap(new HashMap<>());
        baseVO.setPageNum(0);
        baseVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        baseVO.setGroupBy(Arrays.asList(aggregationCondition));
        baseVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("field");
        returnData.setReturnDataName("结果数量");
        returnData.setDataType(0);
        returnData.setDataValue("dataValue");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setReturnDataMemo("returnDataMemo");
        returnData.setSort(0);
        returnData.setDeleteStatus(0);
        returnData.setGroupFlag(0);
        final List<ReturnData> returnDataList = Arrays.asList(returnData);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());

        // Run the test
        final List<DictInfo> result = baseServiceUnderTest.getDictInfos(baseVO, returnDataList);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetBuilderByGroup() throws Exception {
        // Setup
        final BaseVO baseVO = new BaseVO();
        baseVO.setResourceId(0L);
        baseVO.setQueryMap(new HashMap<>());
        baseVO.setPageNum(0);
        baseVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        baseVO.setGroupBy(Arrays.asList(aggregationCondition));
        baseVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        baseServiceUnderTest.getBuilderByGroup(baseVO, builder, Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testBuildHeaderStatistics() throws Exception {
        // Setup
        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        baseServiceUnderTest.buildHeaderStatistics(builder, Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testBuildComputation() {
        // Setup
        final AggregationBuilder lastAgg = null;

        // Run the test
        baseServiceUnderTest.buildComputation(Arrays.asList("value"), lastAgg);

        // Verify the results
    }

    @Test
    void testSetResponse() throws Exception {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        baseServiceUnderTest.setResponse(response, "title");

        // Verify the results
    }

    @Test
    void testSetResponse_ThrowsUnsupportedEncodingException() {
        // Setup
        final HttpServletResponse response = new MockHttpServletResponse();

        // Run the test
        assertThatThrownBy(() -> baseServiceUnderTest.setResponse(response, "title"))
                .isInstanceOf(UnsupportedEncodingException.class);
    }

    @Test
    void testSetParamMap() {
        // Setup
        final List<QueryBuilder> must = Arrays.asList();
        final Map paramMap = new HashMap<>();

        // Configure ParamDataFeignServiceImpl.selectParamDataByUserIdAndResourceId(...).
        final ParamData paramData1 = new ParamData();
        paramData1.setId(0L);
        paramData1.setParamDataIndex("paramDataIndex");
        paramData1.setParamDataCode("paramDataCode");
        paramData1.setParamDataName("paramDataName");
        paramData1.setDataType(0);
        paramData1.setDataValue("dataValue");
        paramData1.setParamDataDeals(0);
        paramData1.setParamDataValue("paramDataValue");
        paramData1.setParamDataAct(0);
        paramData1.setPlaceHolder("placeHolder");
        paramData1.setShowFlag(0);
        paramData1.setFixedPage(0);
        paramData1.setParamDataMemo("paramDataMemo");
        paramData1.setSort(0);
        paramData1.setOrderSort(0);
        final List<ParamData> paramData = Arrays.asList(paramData1);
        when(mockParamDataFeignService.selectParamDataByUserIdAndResourceId(0L, 0L)).thenReturn(paramData);

        // Run the test
        baseServiceUnderTest.setParamMap(must, paramMap, 0L);

        // Verify the results
    }

    @Test
    void testSetParamMap_ParamDataFeignServiceImplReturnsNoItems() {
        // Setup
        final List<QueryBuilder> must = Arrays.asList();
        final Map paramMap = new HashMap<>();
        when(mockParamDataFeignService.selectParamDataByUserIdAndResourceId(0L, 0L))
                .thenReturn(Collections.emptyList());

        // Run the test
        baseServiceUnderTest.setParamMap(must, paramMap, 0L);

        // Verify the results
    }

    @Test
    void testProcessParamMap() {
        // Setup
        final List<QueryBuilder> must = Arrays.asList();
        final Map<String, Object> paramMap = new HashMap<>();

        // Configure ParamDataFeignServiceImpl.selectParamDataByUserIdAndResourceId(...).
        final ParamData paramData1 = new ParamData();
        paramData1.setId(0L);
        paramData1.setParamDataIndex("paramDataIndex");
        paramData1.setParamDataCode("paramDataCode");
        paramData1.setParamDataName("paramDataName");
        paramData1.setDataType(0);
        paramData1.setDataValue("dataValue");
        paramData1.setParamDataDeals(0);
        paramData1.setParamDataValue("paramDataValue");
        paramData1.setParamDataAct(0);
        paramData1.setPlaceHolder("placeHolder");
        paramData1.setShowFlag(0);
        paramData1.setFixedPage(0);
        paramData1.setParamDataMemo("paramDataMemo");
        paramData1.setSort(0);
        paramData1.setOrderSort(0);
        final List<ParamData> paramData = Arrays.asList(paramData1);
        when(mockParamDataFeignService.selectParamDataByUserIdAndResourceId(0L, 0L)).thenReturn(paramData);

        // Run the test
        final List<QueryBuilder> result = baseServiceUnderTest.processParamMap(must, paramMap, 0L);

        // Verify the results
    }

    @Test
    void testProcessParamMap_ParamDataFeignServiceImplReturnsNoItems() {
        // Setup
        final List<QueryBuilder> must = Arrays.asList();
        final Map<String, Object> paramMap = new HashMap<>();
        when(mockParamDataFeignService.selectParamDataByUserIdAndResourceId(0L, 0L))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<QueryBuilder> result = baseServiceUnderTest.processParamMap(must, paramMap, 0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSetMusts() {
        // Setup
        final CommonSearchVO searchVO = new CommonSearchVO();
        searchVO.setResourceId(0L);
        searchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        searchVO.setGroupBy(Arrays.asList(aggregationCondition));
        searchVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        searchVO.setChannels("channels");
        searchVO.setProjectNo("projectNo");
        searchVO.setType(0);
        searchVO.setCardValidStatus(0);
        searchVO.setCardUseStatus(0);
        searchVO.setPurchaseStartTime("purchaseStartTime");
        searchVO.setRegChannel("regChannel");
        searchVO.setReportTimeDiff("reportTimeDiff");

        final List<QueryBuilder> must = Arrays.asList();

        // Run the test
//        baseServiceUnderTest.setMusts(searchVO, must);

        // Verify the results
    }

    @Test
    void testGetValuesByEsSearchSource1() {
        // Setup
        final Map<String, String> expectedResult = new HashMap<>();

        // Run the test
        final Map<String, String> result = baseServiceUnderTest.getValuesByEsSearchSource("queryDsl", "restUrl",
                "keyItem", "valueItem");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetValuesByEsSearchSource2() {
        assertThat(baseServiceUnderTest.getValuesByEsSearchSource("queryDsl", "restUrl", "item"))
                .isEqualTo(Arrays.asList("value"));
        assertThat(baseServiceUnderTest.getValuesByEsSearchSource("queryDsl", "restUrl", "item"))
                .isEqualTo(Collections.emptyList());
    }

    @Test
    void testCheckInstitute() {
        // Setup
        final UserChannelAndHospital channelOrHospitals = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmZone.setBrmZone("brmZone");
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        brmTypeToChiefZone.setProjectZoneType("projectZoneType");
        final BrmChiefZone brmChiefZone = new BrmChiefZone();
        brmChiefZone.setChiefZone("chiefZone");
        brmTypeToChiefZone.setChiefZone(Arrays.asList(brmChiefZone));
        channelOrHospitals.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        channelOrHospitals.setJobDistrictHeadArea(jobDistrictHeadArea);
        channelOrHospitals.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        channelOrHospitals.setChannels(Arrays.asList(userChannel));
        channelOrHospitals.setHospitals(new HashMap<>());

        // Run the test
        baseServiceUnderTest.checkInstitute(channelOrHospitals, new String[]{"voInstituteIdSplit"});

        // Verify the results
    }

    @Test
    void testProcessStatistics() throws Exception {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        // Run the test
        final BaseSearch result = baseServiceUnderTest.processStatistics(vo, builder, "url");

        // Verify the results
    }



    @Test
    void testProcessGroup() throws Exception {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
        final BaseSearch result = baseServiceUnderTest.processGroup(vo, builder, "url");

        // Verify the results
    }

    @Test
    void testProcessGroup_ReturnDataFeignServiceImplReturnsNoItems() throws Exception {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(Collections.emptyList());

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
        final BaseSearch result = baseServiceUnderTest.processGroup(vo, builder, "url");

        // Verify the results
    }

    @Test
    void testProcessGroup_DictFeignServiceImplReturnsNoItems() throws Exception {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());
        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
        final BaseSearch result = baseServiceUnderTest.processGroup(vo, builder, "url");

        // Verify the results
    }

    @Test
    void testCalculateTableHeader() {
        // Setup
        final ReturnData statisticsFieldInfo = new ReturnData();
        statisticsFieldInfo.setFieldDesc("fieldDesc");
        statisticsFieldInfo.setSumOrAvgFlag(0);
        statisticsFieldInfo.setId(0L);
        statisticsFieldInfo.setReturnDataIndex("returnDataIndex");
        statisticsFieldInfo.setReturnDataCode("field");
        statisticsFieldInfo.setReturnDataName("结果数量");
        statisticsFieldInfo.setDataType(0);
        statisticsFieldInfo.setDataValue("dataValue");
        statisticsFieldInfo.setReturnDataDeals(0);
        statisticsFieldInfo.setReturnDataValue("returnDataValue");
        statisticsFieldInfo.setShowFlag(0);
        statisticsFieldInfo.setReturnDataMemo("returnDataMemo");
        statisticsFieldInfo.setSort(0);
        statisticsFieldInfo.setDeleteStatus(0);
        statisticsFieldInfo.setGroupFlag(0);

        final Statistics sumOrAvg = new Statistics("fieldName", new cn.hutool.json.JSONObject(0, false, false));
        final Statistics bean = new Statistics("fieldName", new cn.hutool.json.JSONObject(0, false, false));

        // Run the test
        baseServiceUnderTest.calculateTableHeader(statisticsFieldInfo, sumOrAvg, bean);

        // Verify the results
    }

    @Test
    void testBuildColumnName() {
        // Setup
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        final List<AggregationCondition> groupBy = Arrays.asList(aggregationCondition);
        final Map<Integer, List<ReturnData>> groupFieldMap = new HashMap<>();

        // Run the test
        final List<Map<String, Object>> result = baseServiceUnderTest.buildColumnName(groupBy, groupFieldMap);

        // Verify the results
    }

    @Test
    void testGetBaseSearch() throws Exception {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
        final BaseSearch result = baseServiceUnderTest.getBaseSearch(vo, builder, "url");

        // Verify the results
    }

    @Test
    void testGetBaseSearch_ReturnDataFeignServiceImplReturnsNoItems() throws Exception {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(Collections.emptyList());

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
        final BaseSearch result = baseServiceUnderTest.getBaseSearch(vo, builder, "url");

        // Verify the results
    }

    @Test
    void testGetBaseSearch_DictFeignServiceImplReturnsNoItems() throws Exception {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());
        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
        final BaseSearch result = baseServiceUnderTest.getBaseSearch(vo, builder, "url");

        // Verify the results
    }


    @Test
    void testGeneralGroup() {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
        final BaseSearch result = baseServiceUnderTest.generalGroup(vo, "url");

        // Verify the results
    }

    @Test
    void testGeneralGroup_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(Collections.emptyList());

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
        final BaseSearch result = baseServiceUnderTest.generalGroup(vo, "url");

        // Verify the results
    }

    @Test
    void testGeneralGroup_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
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
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data_export",
                0L)).thenReturn(returnData);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());
        when(mockCom.valuePostProcessing(new HashMap<>(), new ReturnData())).thenReturn("result");

        // Run the test
        final BaseSearch result = baseServiceUnderTest.generalGroup(vo, "url");

        // Verify the results
    }
}
