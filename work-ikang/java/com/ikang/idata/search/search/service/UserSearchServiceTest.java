package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.*;
import com.ikang.idata.common.entity.es.CheckItemEntryData;
import com.ikang.idata.common.entity.es.CheckItemEntryHits;
import com.ikang.idata.common.entity.es.ESBaseData;
import com.ikang.idata.common.entity.vo.CommonSearchVO;
import com.ikang.idata.common.entity.vo.UserChannelAndHospital;
import com.ikang.idata.common.entity.vo.UserSearchTwoVO;
import com.ikang.idata.common.entity.vo.UserSearchVO;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.DictFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.ReturnDataFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserSearchServiceTest {

    @Mock
    private ESHttpClientService mockEsHttpClientService;
    @Mock
    private ReturnDataFeignServiceImpl mockReturnDataFeignService;
    @Mock
    private DictFeignServiceImpl mockDictFeignService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;

    @InjectMocks
    private UserSearchService userSearchServiceUnderTest;

    @Test
    void testFindByTimeAndOrigin() {
        // Setup
        final UserSearchTwoVO userSearchTwoVO = new UserSearchTwoVO();
        userSearchTwoVO.setRegisterStartTime("RegisterStartTime");
        userSearchTwoVO.setRegisterEndTime("RegisterEndTime");
        userSearchTwoVO.setUserOrigin("UserOrigin");
        userSearchTwoVO.setPageNum(0);
        userSearchTwoVO.setPageSize(0);

        final UserSearchTwo expectedResult = new UserSearchTwo();
        expectedResult.setTotalStr("totalStr");
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setMapList(Arrays.asList(new HashMap<>()));
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("searchUrlTwo", HttpMethod.POST, ESBaseData.class,
                "queryDsl")).thenReturn(esBaseData);

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
        returnData1.setDataType(0);
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("name")).thenReturn(returnData);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final UserSearchTwo result = userSearchServiceUnderTest.findByTimeAndOrigin(userSearchTwoVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindByTimeAndOrigin_ReturnDataFeignServiceImplReturnsNull() {
        // Setup
        final UserSearchTwoVO userSearchTwoVO = new UserSearchTwoVO();
        userSearchTwoVO.setRegisterStartTime("RegisterStartTime");
        userSearchTwoVO.setRegisterEndTime("RegisterEndTime");
        userSearchTwoVO.setUserOrigin("UserOrigin");
        userSearchTwoVO.setPageNum(0);
        userSearchTwoVO.setPageSize(0);

        final UserSearchTwo expectedResult = new UserSearchTwo();
        expectedResult.setTotalStr("totalStr");
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setMapList(Arrays.asList(new HashMap<>()));
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("searchUrlTwo", HttpMethod.POST, ESBaseData.class,
                "queryDsl")).thenReturn(esBaseData);

        when(mockReturnDataFeignService.listByReturnDataIndex("name")).thenReturn(null);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final UserSearchTwo result = userSearchServiceUnderTest.findByTimeAndOrigin(userSearchTwoVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindByTimeAndOrigin_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        final UserSearchTwoVO userSearchTwoVO = new UserSearchTwoVO();
        userSearchTwoVO.setRegisterStartTime("RegisterStartTime");
        userSearchTwoVO.setRegisterEndTime("RegisterEndTime");
        userSearchTwoVO.setUserOrigin("UserOrigin");
        userSearchTwoVO.setPageNum(0);
        userSearchTwoVO.setPageSize(0);

        final UserSearchTwo expectedResult = new UserSearchTwo();
        expectedResult.setTotalStr("totalStr");
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setMapList(Arrays.asList(new HashMap<>()));
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("searchUrlTwo", HttpMethod.POST, ESBaseData.class,
                "queryDsl")).thenReturn(esBaseData);

        when(mockReturnDataFeignService.listByReturnDataIndex("name")).thenReturn(Collections.emptyList());

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final UserSearchTwo result = userSearchServiceUnderTest.findByTimeAndOrigin(userSearchTwoVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindByTimeAndOrigin_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final UserSearchTwoVO userSearchTwoVO = new UserSearchTwoVO();
        userSearchTwoVO.setRegisterStartTime("RegisterStartTime");
        userSearchTwoVO.setRegisterEndTime("RegisterEndTime");
        userSearchTwoVO.setUserOrigin("UserOrigin");
        userSearchTwoVO.setPageNum(0);
        userSearchTwoVO.setPageSize(0);

        final UserSearchTwo expectedResult = new UserSearchTwo();
        expectedResult.setTotalStr("totalStr");
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setMapList(Arrays.asList(new HashMap<>()));
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("searchUrlTwo", HttpMethod.POST, ESBaseData.class,
                "queryDsl")).thenReturn(esBaseData);

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
        returnData1.setDataType(0);
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("name")).thenReturn(returnData);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());

        // Run the test
        final UserSearchTwo result = userSearchServiceUnderTest.findByTimeAndOrigin(userSearchTwoVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testInvokeAndGetResultTwo() {
        // Setup
        final UserSearchTwo expectedResult = new UserSearchTwo();
        expectedResult.setTotalStr("totalStr");
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setMapList(Arrays.asList(new HashMap<>()));
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("searchUrlTwo", HttpMethod.POST, ESBaseData.class,
                "queryDsl")).thenReturn(esBaseData);

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
        returnData1.setDataType(0);
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("name")).thenReturn(returnData);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final UserSearchTwo result = userSearchServiceUnderTest.invokeAndGetResultTwo("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testInvokeAndGetResultTwo_ReturnDataFeignServiceImplReturnsNull() {
        // Setup
        final UserSearchTwo expectedResult = new UserSearchTwo();
        expectedResult.setTotalStr("totalStr");
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setMapList(Arrays.asList(new HashMap<>()));
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("searchUrlTwo", HttpMethod.POST, ESBaseData.class,
                "queryDsl")).thenReturn(esBaseData);

        when(mockReturnDataFeignService.listByReturnDataIndex("name")).thenReturn(null);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final UserSearchTwo result = userSearchServiceUnderTest.invokeAndGetResultTwo("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testInvokeAndGetResultTwo_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        final UserSearchTwo expectedResult = new UserSearchTwo();
        expectedResult.setTotalStr("totalStr");
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setMapList(Arrays.asList(new HashMap<>()));
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("searchUrlTwo", HttpMethod.POST, ESBaseData.class,
                "queryDsl")).thenReturn(esBaseData);

        when(mockReturnDataFeignService.listByReturnDataIndex("name")).thenReturn(Collections.emptyList());

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final UserSearchTwo result = userSearchServiceUnderTest.invokeAndGetResultTwo("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testInvokeAndGetResultTwo_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final UserSearchTwo expectedResult = new UserSearchTwo();
        expectedResult.setTotalStr("totalStr");
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setMapList(Arrays.asList(new HashMap<>()));
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("searchUrlTwo", HttpMethod.POST, ESBaseData.class,
                "queryDsl")).thenReturn(esBaseData);

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
        returnData1.setDataType(0);
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("name")).thenReturn(returnData);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());

        // Run the test
        final UserSearchTwo result = userSearchServiceUnderTest.invokeAndGetResultTwo("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final UserSearchVO userSearchVO = new UserSearchVO();
        userSearchVO.setResourceId(0L);
        userSearchVO.setQueryMap(new HashMap<>());
        userSearchVO.setPageNum(0);
        userSearchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        userSearchVO.setGroupBy(Arrays.asList(aggregationCondition));
        userSearchVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        userSearchVO.setDesensitization(false);
        userSearchVO.setRegHospCodesList("regHospCodesList");
        userSearchVO.setRegHospCodesListCopy("regHospCodesListCopy");
        userSearchVO.setCheckHospCodesList("checkHospCodesList");
        userSearchVO.setCheckHospCodesListCopy("checkHospCodesListCopy");
        userSearchVO.setIndustryCodesList("industryCodesList");
        userSearchVO.setAreaCodesList("areaCodesList");
        userSearchVO.setPostCodesList("postCodesList");

        // Run the test
        final BaseSearch result = userSearchServiceUnderTest.find(userSearchVO);

        // Verify the results
    }

    @Test
    void testHandlingAuthority() {
        // Setup
        final List<QueryBuilder> filter = Arrays.asList();

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final UserChannel userChannel = new UserChannel();
        userChannel.setChannelCode("channelCode");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        final UserPost userPost = new UserPost();
        userPost.setPostCode("postCode");
        userPost.setPostName("postName");
        userChannelAndHospital.setUserPostList(Arrays.asList(userPost));
        final UserArea userArea = new UserArea();
        userArea.setAreaName("areaName");
        userChannelAndHospital.setUserAreaList(Arrays.asList(userArea));
        final UserTrade userTrade = new UserTrade();
        userTrade.setTradeName("tradeName");
        userChannelAndHospital.setUserTradeList(Arrays.asList(userTrade));
        userChannelAndHospital.setPermissionFlag(0);
        userChannelAndHospital.setDataFlags(Arrays.asList(0));
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        userSearchServiceUnderTest.handlingAuthority(filter);

        // Verify the results
    }

    @Test
    void testShowCheckItem() {
        // Setup
        // Configure ESHttpClientService.exchange(...).
        final CheckItemEntryData checkItemEntryData = new CheckItemEntryData();
        checkItemEntryData.setTotal(0);
        final CheckItemEntryHits checkItemEntryHits = new CheckItemEntryHits();
        final CheckItemEntry source = new CheckItemEntry();
        source.setReportmapping("reportmapping");
        source.setdCode("dCode");
        source.setdName("dName");
        source.setReportCode("reportCode");
        source.setReportName("reportName");
        checkItemEntryHits.setSource(source);
        checkItemEntryData.setCoverHits(Arrays.asList(checkItemEntryHits));
        when(mockEsHttpClientService.exchange("checkitemUrl", HttpMethod.POST, CheckItemEntryData.class,
                "builderr")).thenReturn(checkItemEntryData);

        // Run the test
        final List<CheckItem> result = userSearchServiceUnderTest.showCheckItem();

        // Verify the results
    }

    @Test
    void testSetMusts() {
        // Setup
        final CommonSearchVO searchVO = new CommonSearchVO();
        searchVO.setResourceId(0L);
        searchVO.setQueryMap(new HashMap<>());
        searchVO.setPageNum(0);
        searchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        searchVO.setGroupBy(Arrays.asList(aggregationCondition));
        searchVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        searchVO.setDesensitization(false);
        searchVO.setChannels("channels");
        searchVO.setCardUseStatus(0);
        searchVO.setRegChannel("regChannel");
        searchVO.setReportTimeDiff("reportTimeDiff");

        final List<QueryBuilder> must = Arrays.asList();

        // Run the test
        userSearchServiceUnderTest.setMusts(searchVO, must);

        // Verify the results
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final UserSearchVO userSearchVO = new UserSearchVO();
        userSearchVO.setResourceId(0L);
        userSearchVO.setQueryMap(new HashMap<>());
        userSearchVO.setPageNum(0);
        userSearchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        userSearchVO.setGroupBy(Arrays.asList(aggregationCondition));
        userSearchVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        userSearchVO.setDesensitization(false);
        userSearchVO.setRegHospCodesList("regHospCodesList");
        userSearchVO.setRegHospCodesListCopy("regHospCodesListCopy");
        userSearchVO.setCheckHospCodesList("checkHospCodesList");
        userSearchVO.setCheckHospCodesListCopy("checkHospCodesListCopy");
        userSearchVO.setIndustryCodesList("industryCodesList");
        userSearchVO.setAreaCodesList("areaCodesList");
        userSearchVO.setPostCodesList("postCodesList");

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
        returnData1.setDataType(0);
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data",
                0L)).thenReturn(returnData);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final BaseSearch result = userSearchServiceUnderTest.findGroupBy(userSearchVO);

        // Verify the results
    }

    @Test
    void testFindGroupBy_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        final UserSearchVO userSearchVO = new UserSearchVO();
        userSearchVO.setResourceId(0L);
        userSearchVO.setQueryMap(new HashMap<>());
        userSearchVO.setPageNum(0);
        userSearchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        userSearchVO.setGroupBy(Arrays.asList(aggregationCondition));
        userSearchVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        userSearchVO.setDesensitization(false);
        userSearchVO.setRegHospCodesList("regHospCodesList");
        userSearchVO.setRegHospCodesListCopy("regHospCodesListCopy");
        userSearchVO.setCheckHospCodesList("checkHospCodesList");
        userSearchVO.setCheckHospCodesListCopy("checkHospCodesListCopy");
        userSearchVO.setIndustryCodesList("industryCodesList");
        userSearchVO.setAreaCodesList("areaCodesList");
        userSearchVO.setPostCodesList("postCodesList");

        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data",
                0L)).thenReturn(Collections.emptyList());

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final BaseSearch result = userSearchServiceUnderTest.findGroupBy(userSearchVO);

        // Verify the results
    }

    @Test
    void testFindGroupBy_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final UserSearchVO userSearchVO = new UserSearchVO();
        userSearchVO.setResourceId(0L);
        userSearchVO.setQueryMap(new HashMap<>());
        userSearchVO.setPageNum(0);
        userSearchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        userSearchVO.setGroupBy(Arrays.asList(aggregationCondition));
        userSearchVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        userSearchVO.setDesensitization(false);
        userSearchVO.setRegHospCodesList("regHospCodesList");
        userSearchVO.setRegHospCodesListCopy("regHospCodesListCopy");
        userSearchVO.setCheckHospCodesList("checkHospCodesList");
        userSearchVO.setCheckHospCodesListCopy("checkHospCodesListCopy");
        userSearchVO.setIndustryCodesList("industryCodesList");
        userSearchVO.setAreaCodesList("areaCodesList");
        userSearchVO.setPostCodesList("postCodesList");

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
        returnData1.setDataType(0);
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data",
                0L)).thenReturn(returnData);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());

        // Run the test
        final BaseSearch result = userSearchServiceUnderTest.findGroupBy(userSearchVO);

        // Verify the results
    }

    @Test
    void testExportExcel() throws Exception {
        // Setup
        final UserSearchVO userSearchVO = new UserSearchVO();
        userSearchVO.setResourceId(0L);
        userSearchVO.setQueryMap(new HashMap<>());
        userSearchVO.setPageNum(0);
        userSearchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        userSearchVO.setGroupBy(Arrays.asList(aggregationCondition));
        userSearchVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        userSearchVO.setDesensitization(false);
        userSearchVO.setRegHospCodesList("regHospCodesList");
        userSearchVO.setRegHospCodesListCopy("regHospCodesListCopy");
        userSearchVO.setCheckHospCodesList("checkHospCodesList");
        userSearchVO.setCheckHospCodesListCopy("checkHospCodesListCopy");
        userSearchVO.setIndustryCodesList("industryCodesList");
        userSearchVO.setAreaCodesList("areaCodesList");
        userSearchVO.setPostCodesList("postCodesList");

        final MockHttpServletResponse response = new MockHttpServletResponse();

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
        returnData1.setDataType(0);
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data",
                0L)).thenReturn(returnData);

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        userSearchServiceUnderTest.exportExcel(userSearchVO, response);

        // Verify the results
    }

    @Test
    void testExportExcel_ReturnDataFeignServiceImplReturnsNoItems() throws Exception {
        // Setup
        final UserSearchVO userSearchVO = new UserSearchVO();
        userSearchVO.setResourceId(0L);
        userSearchVO.setQueryMap(new HashMap<>());
        userSearchVO.setPageNum(0);
        userSearchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        userSearchVO.setGroupBy(Arrays.asList(aggregationCondition));
        userSearchVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        userSearchVO.setDesensitization(false);
        userSearchVO.setRegHospCodesList("regHospCodesList");
        userSearchVO.setRegHospCodesListCopy("regHospCodesListCopy");
        userSearchVO.setCheckHospCodesList("checkHospCodesList");
        userSearchVO.setCheckHospCodesListCopy("checkHospCodesListCopy");
        userSearchVO.setIndustryCodesList("industryCodesList");
        userSearchVO.setAreaCodesList("areaCodesList");
        userSearchVO.setPostCodesList("postCodesList");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data",
                0L)).thenReturn(Collections.emptyList());

        // Configure DictFeignServiceImpl.findByTypeList(...).
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        userSearchServiceUnderTest.exportExcel(userSearchVO, response);

        // Verify the results
    }

    @Test
    void testExportExcel_DictFeignServiceImplReturnsNoItems() throws Exception {
        // Setup
        final UserSearchVO userSearchVO = new UserSearchVO();
        userSearchVO.setResourceId(0L);
        userSearchVO.setQueryMap(new HashMap<>());
        userSearchVO.setPageNum(0);
        userSearchVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        userSearchVO.setGroupBy(Arrays.asList(aggregationCondition));
        userSearchVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        userSearchVO.setDesensitization(false);
        userSearchVO.setRegHospCodesList("regHospCodesList");
        userSearchVO.setRegHospCodesListCopy("regHospCodesListCopy");
        userSearchVO.setCheckHospCodesList("checkHospCodesList");
        userSearchVO.setCheckHospCodesListCopy("checkHospCodesListCopy");
        userSearchVO.setIndustryCodesList("industryCodesList");
        userSearchVO.setAreaCodesList("areaCodesList");
        userSearchVO.setPostCodesList("postCodesList");

        final MockHttpServletResponse response = new MockHttpServletResponse();

        // Configure ReturnDataFeignServiceImpl.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setReturnDataCode("field");
        returnData1.setReturnDataName("结果数量");
        returnData1.setDataType(0);
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "role_return_data",
                0L)).thenReturn(returnData);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());

        // Run the test
        userSearchServiceUnderTest.exportExcel(userSearchVO, response);

        // Verify the results
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = userSearchServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
