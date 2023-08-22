package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.*;
import com.ikang.idata.common.entity.vo.*;
import com.ikang.idata.search.search.entity.vo.Statistics;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.SystemUserFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorReadFilmServiceTest {

    @Mock
    private UserSearchService mockUserSearchService;
    @Mock
    private GeneralService mockGeneralService;
    @Mock
    private SystemUserFeignServiceImpl mockSystemUserFeignService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;

    @InjectMocks
    private DoctorReadFilmService doctorReadFilmServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final DoctorReadFilmVO doctorReadFilmVO = new DoctorReadFilmVO();
        doctorReadFilmVO.setResourceId(0L);
        doctorReadFilmVO.setQueryMap(new HashMap<>());
        doctorReadFilmVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        doctorReadFilmVO.setGroupBy(Arrays.asList(aggregationCondition));
        doctorReadFilmVO.setExamDateStart("examDateStart");
        doctorReadFilmVO.setExamDateEnd("examDateEnd");
        doctorReadFilmVO.setExamineDateStart("examineDateStart");
        doctorReadFilmVO.setExamineDateEnd("examineDateEnd");
        doctorReadFilmVO.setLocId("locId");
        doctorReadFilmVO.setDepartmentName("departmentName");
        doctorReadFilmVO.setDoctorName("doctorName");
        doctorReadFilmVO.setItemName("itemName");
        doctorReadFilmVO.setItemCode("itemCode");
        doctorReadFilmVO.setReaderlocationname("readerlocationname");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmZone.setBrmZone("brmZone");
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        brmTypeToChiefZone.setProjectZoneType("projectZoneType");
        final BrmChiefZone brmChiefZone = new BrmChiefZone();
        brmChiefZone.setChiefZone("chiefZone");
        brmTypeToChiefZone.setChiefZone(Arrays.asList(brmChiefZone));
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setPermissionFlag(0);
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BaseSearch result = doctorReadFilmServiceUnderTest.find(doctorReadFilmVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final DoctorReadFilmVO doctorReadFilmVO = new DoctorReadFilmVO();
        doctorReadFilmVO.setResourceId(0L);
        doctorReadFilmVO.setQueryMap(new HashMap<>());
        doctorReadFilmVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        doctorReadFilmVO.setGroupBy(Arrays.asList(aggregationCondition));
        doctorReadFilmVO.setExamDateStart("examDateStart");
        doctorReadFilmVO.setExamDateEnd("examDateEnd");
        doctorReadFilmVO.setExamineDateStart("examineDateStart");
        doctorReadFilmVO.setExamineDateEnd("examineDateEnd");
        doctorReadFilmVO.setLocId("locId");
        doctorReadFilmVO.setDepartmentName("departmentName");
        doctorReadFilmVO.setDoctorName("doctorName");
        doctorReadFilmVO.setItemName("itemName");
        doctorReadFilmVO.setItemCode("itemCode");
        doctorReadFilmVO.setReaderlocationname("readerlocationname");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmZone.setBrmZone("brmZone");
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        brmTypeToChiefZone.setProjectZoneType("projectZoneType");
        final BrmChiefZone brmChiefZone = new BrmChiefZone();
        brmChiefZone.setChiefZone("chiefZone");
        brmTypeToChiefZone.setChiefZone(Arrays.asList(brmChiefZone));
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setPermissionFlag(0);
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BaseSearch result = doctorReadFilmServiceUnderTest.findGroupBy(doctorReadFilmVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
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

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BaseSearch result = doctorReadFilmServiceUnderTest.getBaseSearch(vo, builder, "url");

        // Verify the results
    }

    @Test
    void testProcessGroupTableList() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("count");
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
        final List<ReturnData> groupFieldInfos = Arrays.asList(returnData);
        final List<Map<String, Object>> sourceList = Arrays.asList(new HashMap<>());

        // Run the test
//        doctorReadFilmServiceUnderTest.processGroupTableList(groupFieldInfos, sourceList);

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
        final List<Map<String, Object>> result = doctorReadFilmServiceUnderTest.buildColumnName(groupBy, groupFieldMap);

        // Verify the results
    }

    @Test
    void testProcessStatisticsAndConstructSumOrAvgMap() {
        // Setup
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("count");
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
        final List<Map<String, Object>> result = Arrays.asList(new HashMap<>());

        // Run the test
//        final Map<String, Map<String, Object>> result = doctorReadFilmServiceUnderTest.processStatisticsAndConstructSumOrAvgMap(
//                returnData, result);

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
        statisticsFieldInfo.setReturnDataCode("count");
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
        doctorReadFilmServiceUnderTest.calculateTableHeader(statisticsFieldInfo, sumOrAvg, bean);

        // Verify the results
    }

    @Test
    void testConstructAggStatement() throws Exception {
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

        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        doctorReadFilmServiceUnderTest.constructAggStatement(vo, builder, Arrays.asList("value"));

        // Verify the results
    }

    @Test
    void testFindDoctorGroupBy() {
        // Setup
        final LocAndInstituteVO locAndInstituteVO = new LocAndInstituteVO();
        locAndInstituteVO.setType(0);
        locAndInstituteVO.setInstituteId("instituteId");
        locAndInstituteVO.setLocId("locId");

        // Run the test
        final List<String> result = doctorReadFilmServiceUnderTest.findDoctorGroupBy(locAndInstituteVO);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testFindItemGroupBy() {
        // Setup
        final ReaderVO readerVO = new ReaderVO();
        readerVO.setType(0);
        readerVO.setDepartmentId("departmentId");
        readerVO.setDepartmentName("departmentName");

        // Run the test
//        final List<String> result = doctorReadFilmServiceUnderTest.findItemGroupBy(readerVO);
//
//        // Verify the results
//        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testInvokeAndGetResultForItem() {
        assertThat(doctorReadFilmServiceUnderTest.invokeAndGetResultForItem("queryDsl", "itemName"))
                .isEqualTo(Arrays.asList("value"));
        assertThat(doctorReadFilmServiceUnderTest.invokeAndGetResultForItem("queryDsl", "itemName"))
                .isEqualTo(Collections.emptyList());
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
        brmZone.setBrmZone("brmZone");
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        brmTypeToChiefZone.setProjectZoneType("projectZoneType");
        final BrmChiefZone brmChiefZone = new BrmChiefZone();
        brmChiefZone.setChiefZone("chiefZone");
        brmTypeToChiefZone.setChiefZone(Arrays.asList(brmChiefZone));
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setPermissionFlag(0);
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BoolQueryBuilder result = doctorReadFilmServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }
}
