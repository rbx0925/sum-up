package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.*;
import com.ikang.idata.common.entity.vo.*;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.DictFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.ReturnDataFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.SystemUserFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SatisfiedHospitalServiceTest {

    @Mock
    private GeneralService mockGeneralService;
    @Mock
    private UserSearchService mockUserSearchService;
    @Mock
    private ReturnDataFeignServiceImpl mockReturnDataFeignService;
    @Mock
    private DictFeignServiceImpl mockDictFeignService;
    @Mock
    private SystemUserFeignServiceImpl mockSystemUserFeignService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;

    @InjectMocks
    private SatisfiedHospitalService satisfiedHospitalServiceUnderTest;

    @Test
    public void testShow() {
        // Setup
        // Configure SystemUserFeignServiceImpl.selectUserCheckLineList(...).
        final UserCheckLine userCheckLine = new UserCheckLine();
        userCheckLine.setAreaCode("areaCode");
        userCheckLine.setAreaName("areaName");
        userCheckLine.setId(0L);
        userCheckLine.setUserId(0L);
        userCheckLine.setCreateBy(0L);
        userCheckLine.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userCheckLine.setUpdateBy(0L);
        userCheckLine.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userCheckLine.setDelFlag(0);
        userCheckLine.setHospitalCode("hospitalCode");
        final List<UserCheckLine> userCheckLines = Arrays.asList(userCheckLine);
        when(mockSystemUserFeignService.selectUserCheckLineList(0L)).thenReturn(userCheckLines);

        // Configure SystemUserFeignServiceImpl.getById(...).
        final User user = new User();
        user.setId(0L);
        user.setRealName("realName");
        user.setUserNum("userNum");
        user.setUserName("userName");
        user.setPhoneNum("phoneNum");
        user.setRoles(Arrays.asList(0L));
        user.setDeleteStatus(0);
        user.setUserPassword("userPassword");
        user.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setSalt("salt");
        when(mockSystemUserFeignService.getById(0L)).thenReturn(user);

        // Run the test
//        final List<AreaAndHospital> result = satisfiedHospitalServiceUnderTest.show();

        // Verify the results
    }

    @Test
    public void testShow_SystemUserFeignServiceImplSelectUserCheckLineListReturnsNoItems() {
        // Setup
        when(mockSystemUserFeignService.selectUserCheckLineList(0L)).thenReturn(Collections.emptyList());

        // Configure SystemUserFeignServiceImpl.getById(...).
        final User user = new User();
        user.setId(0L);
        user.setRealName("realName");
        user.setUserNum("userNum");
        user.setUserName("userName");
        user.setPhoneNum("phoneNum");
        user.setRoles(Arrays.asList(0L));
        user.setDeleteStatus(0);
        user.setUserPassword("userPassword");
        user.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setSalt("salt");
        when(mockSystemUserFeignService.getById(0L)).thenReturn(user);

        // Run the test
//        final List<AreaAndHospital> result = satisfiedHospitalServiceUnderTest.show();

        // Verify the results
//        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testFind() {
        // Setup
        final SatisFiedVO satisFiedVO = new SatisFiedVO();
        satisFiedVO.setType("type");
        satisFiedVO.setCardNumber("cardNumber");
        satisFiedVO.setProjectId("projectId");
        satisFiedVO.setWorkNo("workNo");
        satisFiedVO.setDoctorinspectionLines("doctorinspectionLines");
        satisFiedVO.setLocId("locId");
        satisFiedVO.setRegDateStart("regDateStart");
        satisFiedVO.setRegDateEnd("regDateEnd");
        satisFiedVO.setEvaluateFrom("evaluateFrom");
        satisFiedVO.setTotalSatisfaction("totalSatisfaction");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        userChannel.setChannelCode("channelCode");
        userChannel.setChannelName("channelName");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setAreaAndNames(new HashMap<>());
        userChannelAndHospital.setAreaAndNamesAndHospitals(new LinkedHashMap<>());
        final UserPost userPost = new UserPost();
        userPost.setId(0L);
        userPost.setUserId(0L);
        userPost.setCreateBy(0L);
        userPost.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setUpdateBy(0L);
        userPost.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setPostCode("postCode");
        userPost.setPostName("postName");
        userChannelAndHospital.setUserPostList(Arrays.asList(userPost));
        final UserArea userArea = new UserArea();
        userArea.setId(0L);
        userArea.setUserId(0L);
        userArea.setCreateBy(0L);
        userArea.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setUpdateBy(0L);
        userArea.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setAreaCode("areaCode");
        userArea.setAreaName("areaName");
        userChannelAndHospital.setUserAreaList(Arrays.asList(userArea));
        final UserTrade userTrade = new UserTrade();
        userTrade.setId(0L);
        userTrade.setUserId(0L);
        userTrade.setCreateBy(0L);
        userTrade.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setUpdateBy(0L);
        userTrade.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setTradeCode("tradeCode");
        userTrade.setTradeName("tradeName");
        userChannelAndHospital.setUserTradeList(Arrays.asList(userTrade));
        final ReceiveUnit receiveUnit = new ReceiveUnit();
        receiveUnit.setGroupOrganCode("groupOrganCode");
        receiveUnit.setGroupOrganName("groupOrganName");
        userChannelAndHospital.setUserFinanceList(Arrays.asList(new UserFinance(receiveUnit)));
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Configure SystemUserFeignServiceImpl.getById(...).
        final User user = new User();
        user.setId(0L);
        user.setRealName("realName");
        user.setUserNum("userNum");
        user.setUserName("userName");
        user.setPhoneNum("phoneNum");
        user.setRoles(Arrays.asList(0L));
        user.setDeleteStatus(0);
        user.setUserPassword("userPassword");
        user.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setSalt("salt");
        when(mockSystemUserFeignService.getById(0L)).thenReturn(user);

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
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final BaseSearch result = satisfiedHospitalServiceUnderTest.find(satisFiedVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
        verify(mockGeneralService).returnDateAfterHandle(new HashMap<>(), new HashMap<>(), new ReturnData(), Arrays.asList(new DictInfo()));
    }

    @Test
    public void testFind_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final SatisFiedVO satisFiedVO = new SatisFiedVO();
        satisFiedVO.setType("type");
        satisFiedVO.setCardNumber("cardNumber");
        satisFiedVO.setProjectId("projectId");
        satisFiedVO.setWorkNo("workNo");
        satisFiedVO.setDoctorinspectionLines("doctorinspectionLines");
        satisFiedVO.setLocId("locId");
        satisFiedVO.setRegDateStart("regDateStart");
        satisFiedVO.setRegDateEnd("regDateEnd");
        satisFiedVO.setEvaluateFrom("evaluateFrom");
        satisFiedVO.setTotalSatisfaction("totalSatisfaction");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        userChannel.setChannelCode("channelCode");
        userChannel.setChannelName("channelName");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setAreaAndNames(new HashMap<>());
        userChannelAndHospital.setAreaAndNamesAndHospitals(new LinkedHashMap<>());
        final UserPost userPost = new UserPost();
        userPost.setId(0L);
        userPost.setUserId(0L);
        userPost.setCreateBy(0L);
        userPost.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setUpdateBy(0L);
        userPost.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setPostCode("postCode");
        userPost.setPostName("postName");
        userChannelAndHospital.setUserPostList(Arrays.asList(userPost));
        final UserArea userArea = new UserArea();
        userArea.setId(0L);
        userArea.setUserId(0L);
        userArea.setCreateBy(0L);
        userArea.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setUpdateBy(0L);
        userArea.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setAreaCode("areaCode");
        userArea.setAreaName("areaName");
        userChannelAndHospital.setUserAreaList(Arrays.asList(userArea));
        final UserTrade userTrade = new UserTrade();
        userTrade.setId(0L);
        userTrade.setUserId(0L);
        userTrade.setCreateBy(0L);
        userTrade.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setUpdateBy(0L);
        userTrade.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setTradeCode("tradeCode");
        userTrade.setTradeName("tradeName");
        userChannelAndHospital.setUserTradeList(Arrays.asList(userTrade));
        final ReceiveUnit receiveUnit = new ReceiveUnit();
        receiveUnit.setGroupOrganCode("groupOrganCode");
        receiveUnit.setGroupOrganName("groupOrganName");
        userChannelAndHospital.setUserFinanceList(Arrays.asList(new UserFinance(receiveUnit)));
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Configure SystemUserFeignServiceImpl.getById(...).
        final User user = new User();
        user.setId(0L);
        user.setRealName("realName");
        user.setUserNum("userNum");
        user.setUserName("userName");
        user.setPhoneNum("phoneNum");
        user.setRoles(Arrays.asList(0L));
        user.setDeleteStatus(0);
        user.setUserPassword("userPassword");
        user.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setSalt("salt");
        when(mockSystemUserFeignService.getById(0L)).thenReturn(user);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());

        // Run the test
        final BaseSearch result = satisfiedHospitalServiceUnderTest.find(satisFiedVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
        verify(mockGeneralService).returnDateAfterHandle(new HashMap<>(), new HashMap<>(), new ReturnData(), Arrays.asList(new DictInfo()));
    }

    @Test
    public void testShowDname() {
        // Setup
        final Dname dname = new Dname();
        dname.setDcode("dcode");
        dname.setDname("dname");
        dname.setDmapping("dmapping");
        dname.setDreasonmapping("dreasonmapping");
        dname.setFirstLetter("firstLetter");
        final List<Dname> expectedResult = Arrays.asList(dname);

        // Run the test
        final List<Dname> result = satisfiedHospitalServiceUnderTest.showDname();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testFindGroupBy() {
        // Setup
        final SatisFiedVO satisFiedVO = new SatisFiedVO();
        satisFiedVO.setType("type");
        satisFiedVO.setCardNumber("cardNumber");
        satisFiedVO.setProjectId("projectId");
        satisFiedVO.setWorkNo("workNo");
        satisFiedVO.setDoctorinspectionLines("doctorinspectionLines");
        satisFiedVO.setLocId("locId");
        satisFiedVO.setRegDateStart("regDateStart");
        satisFiedVO.setRegDateEnd("regDateEnd");
        satisFiedVO.setEvaluateFrom("evaluateFrom");
        satisFiedVO.setTotalSatisfaction("totalSatisfaction");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        userChannel.setChannelCode("channelCode");
        userChannel.setChannelName("channelName");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setAreaAndNames(new HashMap<>());
        userChannelAndHospital.setAreaAndNamesAndHospitals(new LinkedHashMap<>());
        final UserPost userPost = new UserPost();
        userPost.setId(0L);
        userPost.setUserId(0L);
        userPost.setCreateBy(0L);
        userPost.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setUpdateBy(0L);
        userPost.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setPostCode("postCode");
        userPost.setPostName("postName");
        userChannelAndHospital.setUserPostList(Arrays.asList(userPost));
        final UserArea userArea = new UserArea();
        userArea.setId(0L);
        userArea.setUserId(0L);
        userArea.setCreateBy(0L);
        userArea.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setUpdateBy(0L);
        userArea.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setAreaCode("areaCode");
        userArea.setAreaName("areaName");
        userChannelAndHospital.setUserAreaList(Arrays.asList(userArea));
        final UserTrade userTrade = new UserTrade();
        userTrade.setId(0L);
        userTrade.setUserId(0L);
        userTrade.setCreateBy(0L);
        userTrade.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setUpdateBy(0L);
        userTrade.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setTradeCode("tradeCode");
        userTrade.setTradeName("tradeName");
        userChannelAndHospital.setUserTradeList(Arrays.asList(userTrade));
        final ReceiveUnit receiveUnit = new ReceiveUnit();
        receiveUnit.setGroupOrganCode("groupOrganCode");
        receiveUnit.setGroupOrganName("groupOrganName");
        userChannelAndHospital.setUserFinanceList(Arrays.asList(new UserFinance(receiveUnit)));
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Configure SystemUserFeignServiceImpl.getById(...).
        final User user = new User();
        user.setId(0L);
        user.setRealName("realName");
        user.setUserNum("userNum");
        user.setUserName("userName");
        user.setPhoneNum("phoneNum");
        user.setRoles(Arrays.asList(0L));
        user.setDeleteStatus(0);
        user.setUserPassword("userPassword");
        user.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setSalt("salt");
        when(mockSystemUserFeignService.getById(0L)).thenReturn(user);

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
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "tableName", 0L)).thenReturn(returnData);

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
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final BaseSearch result = satisfiedHospitalServiceUnderTest.findGroupBy(satisFiedVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
        verify(mockUserSearchService).getSumAvg(eq(Arrays.asList("value")), any(BaseSearch.class), eq(new JSONObject(0, false)));
        verify(mockUserSearchService).getGroupOneAndTwo(eq(Arrays.asList("value")), eq(Arrays.asList(new ReturnData())), eq(Arrays.asList(new DictInfo())), any(BaseSearch.class), any(BaseVO.class), eq(new JSONObject(0, false)));
    }

    @Test
    public void testFindGroupBy_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        final SatisFiedVO satisFiedVO = new SatisFiedVO();
        satisFiedVO.setType("type");
        satisFiedVO.setCardNumber("cardNumber");
        satisFiedVO.setProjectId("projectId");
        satisFiedVO.setWorkNo("workNo");
        satisFiedVO.setDoctorinspectionLines("doctorinspectionLines");
        satisFiedVO.setLocId("locId");
        satisFiedVO.setRegDateStart("regDateStart");
        satisFiedVO.setRegDateEnd("regDateEnd");
        satisFiedVO.setEvaluateFrom("evaluateFrom");
        satisFiedVO.setTotalSatisfaction("totalSatisfaction");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        userChannel.setChannelCode("channelCode");
        userChannel.setChannelName("channelName");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setAreaAndNames(new HashMap<>());
        userChannelAndHospital.setAreaAndNamesAndHospitals(new LinkedHashMap<>());
        final UserPost userPost = new UserPost();
        userPost.setId(0L);
        userPost.setUserId(0L);
        userPost.setCreateBy(0L);
        userPost.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setUpdateBy(0L);
        userPost.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setPostCode("postCode");
        userPost.setPostName("postName");
        userChannelAndHospital.setUserPostList(Arrays.asList(userPost));
        final UserArea userArea = new UserArea();
        userArea.setId(0L);
        userArea.setUserId(0L);
        userArea.setCreateBy(0L);
        userArea.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setUpdateBy(0L);
        userArea.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setAreaCode("areaCode");
        userArea.setAreaName("areaName");
        userChannelAndHospital.setUserAreaList(Arrays.asList(userArea));
        final UserTrade userTrade = new UserTrade();
        userTrade.setId(0L);
        userTrade.setUserId(0L);
        userTrade.setCreateBy(0L);
        userTrade.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setUpdateBy(0L);
        userTrade.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setTradeCode("tradeCode");
        userTrade.setTradeName("tradeName");
        userChannelAndHospital.setUserTradeList(Arrays.asList(userTrade));
        final ReceiveUnit receiveUnit = new ReceiveUnit();
        receiveUnit.setGroupOrganCode("groupOrganCode");
        receiveUnit.setGroupOrganName("groupOrganName");
        userChannelAndHospital.setUserFinanceList(Arrays.asList(new UserFinance(receiveUnit)));
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Configure SystemUserFeignServiceImpl.getById(...).
        final User user = new User();
        user.setId(0L);
        user.setRealName("realName");
        user.setUserNum("userNum");
        user.setUserName("userName");
        user.setPhoneNum("phoneNum");
        user.setRoles(Arrays.asList(0L));
        user.setDeleteStatus(0);
        user.setUserPassword("userPassword");
        user.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setSalt("salt");
        when(mockSystemUserFeignService.getById(0L)).thenReturn(user);

        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "tableName", 0L)).thenReturn(Collections.emptyList());

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
        final List<DictInfo> dictInfos = Arrays.asList(dictInfo);
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(dictInfos);

        // Run the test
        final BaseSearch result = satisfiedHospitalServiceUnderTest.findGroupBy(satisFiedVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
        verify(mockUserSearchService).getSumAvg(eq(Arrays.asList("value")), any(BaseSearch.class), eq(new JSONObject(0, false)));
        verify(mockUserSearchService).getGroupOneAndTwo(eq(Arrays.asList("value")), eq(Arrays.asList(new ReturnData())), eq(Arrays.asList(new DictInfo())), any(BaseSearch.class), any(BaseVO.class), eq(new JSONObject(0, false)));
    }

    @Test
    public void testFindGroupBy_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final SatisFiedVO satisFiedVO = new SatisFiedVO();
        satisFiedVO.setType("type");
        satisFiedVO.setCardNumber("cardNumber");
        satisFiedVO.setProjectId("projectId");
        satisFiedVO.setWorkNo("workNo");
        satisFiedVO.setDoctorinspectionLines("doctorinspectionLines");
        satisFiedVO.setLocId("locId");
        satisFiedVO.setRegDateStart("regDateStart");
        satisFiedVO.setRegDateEnd("regDateEnd");
        satisFiedVO.setEvaluateFrom("evaluateFrom");
        satisFiedVO.setTotalSatisfaction("totalSatisfaction");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        userChannel.setChannelCode("channelCode");
        userChannel.setChannelName("channelName");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setAreaAndNames(new HashMap<>());
        userChannelAndHospital.setAreaAndNamesAndHospitals(new LinkedHashMap<>());
        final UserPost userPost = new UserPost();
        userPost.setId(0L);
        userPost.setUserId(0L);
        userPost.setCreateBy(0L);
        userPost.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setUpdateBy(0L);
        userPost.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setPostCode("postCode");
        userPost.setPostName("postName");
        userChannelAndHospital.setUserPostList(Arrays.asList(userPost));
        final UserArea userArea = new UserArea();
        userArea.setId(0L);
        userArea.setUserId(0L);
        userArea.setCreateBy(0L);
        userArea.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setUpdateBy(0L);
        userArea.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setAreaCode("areaCode");
        userArea.setAreaName("areaName");
        userChannelAndHospital.setUserAreaList(Arrays.asList(userArea));
        final UserTrade userTrade = new UserTrade();
        userTrade.setId(0L);
        userTrade.setUserId(0L);
        userTrade.setCreateBy(0L);
        userTrade.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setUpdateBy(0L);
        userTrade.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setTradeCode("tradeCode");
        userTrade.setTradeName("tradeName");
        userChannelAndHospital.setUserTradeList(Arrays.asList(userTrade));
        final ReceiveUnit receiveUnit = new ReceiveUnit();
        receiveUnit.setGroupOrganCode("groupOrganCode");
        receiveUnit.setGroupOrganName("groupOrganName");
        userChannelAndHospital.setUserFinanceList(Arrays.asList(new UserFinance(receiveUnit)));
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Configure SystemUserFeignServiceImpl.getById(...).
        final User user = new User();
        user.setId(0L);
        user.setRealName("realName");
        user.setUserNum("userNum");
        user.setUserName("userName");
        user.setPhoneNum("phoneNum");
        user.setRoles(Arrays.asList(0L));
        user.setDeleteStatus(0);
        user.setUserPassword("userPassword");
        user.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setSalt("salt");
        when(mockSystemUserFeignService.getById(0L)).thenReturn(user);

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
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "tableName", 0L)).thenReturn(returnData);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());

        // Run the test
        final BaseSearch result = satisfiedHospitalServiceUnderTest.findGroupBy(satisFiedVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
        verify(mockUserSearchService).getSumAvg(eq(Arrays.asList("value")), any(BaseSearch.class), eq(new JSONObject(0, false)));
        verify(mockUserSearchService).getGroupOneAndTwo(eq(Arrays.asList("value")), eq(Arrays.asList(new ReturnData())), eq(Arrays.asList(new DictInfo())), any(BaseSearch.class), any(BaseVO.class), eq(new JSONObject(0, false)));
    }

    @Test
    public void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        userChannel.setChannelCode("channelCode");
        userChannel.setChannelName("channelName");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setAreaAndNames(new HashMap<>());
        userChannelAndHospital.setAreaAndNamesAndHospitals(new LinkedHashMap<>());
        final UserPost userPost = new UserPost();
        userPost.setId(0L);
        userPost.setUserId(0L);
        userPost.setCreateBy(0L);
        userPost.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setUpdateBy(0L);
        userPost.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userPost.setPostCode("postCode");
        userPost.setPostName("postName");
        userChannelAndHospital.setUserPostList(Arrays.asList(userPost));
        final UserArea userArea = new UserArea();
        userArea.setId(0L);
        userArea.setUserId(0L);
        userArea.setCreateBy(0L);
        userArea.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setUpdateBy(0L);
        userArea.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userArea.setAreaCode("areaCode");
        userArea.setAreaName("areaName");
        userChannelAndHospital.setUserAreaList(Arrays.asList(userArea));
        final UserTrade userTrade = new UserTrade();
        userTrade.setId(0L);
        userTrade.setUserId(0L);
        userTrade.setCreateBy(0L);
        userTrade.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setUpdateBy(0L);
        userTrade.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        userTrade.setTradeCode("tradeCode");
        userTrade.setTradeName("tradeName");
        userChannelAndHospital.setUserTradeList(Arrays.asList(userTrade));
        final ReceiveUnit receiveUnit = new ReceiveUnit();
        receiveUnit.setGroupOrganCode("groupOrganCode");
        receiveUnit.setGroupOrganName("groupOrganName");
        userChannelAndHospital.setUserFinanceList(Arrays.asList(new UserFinance(receiveUnit)));
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Configure SystemUserFeignServiceImpl.getById(...).
        final User user = new User();
        user.setId(0L);
        user.setRealName("realName");
        user.setUserNum("userNum");
        user.setUserName("userName");
        user.setPhoneNum("phoneNum");
        user.setRoles(Arrays.asList(0L));
        user.setDeleteStatus(0);
        user.setUserPassword("userPassword");
        user.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setSalt("salt");
        when(mockSystemUserFeignService.getById(0L)).thenReturn(user);

        // Run the test
        final BoolQueryBuilder result = satisfiedHospitalServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    public void testQuerySMSSatisfactionList() {
        // Setup
        final BranchRankVo rankVo = new BranchRankVo();
        rankVo.setAreaId("areaId");
        rankVo.setBranchId("branchId");
        rankVo.setYears("years");

        final SMSSatisfactionVo smsSatisfactionVo = new SMSSatisfactionVo();
        smsSatisfactionVo.setRegmonth("regmonth");
        smsSatisfactionVo.setRegcnyear("regcnyear");
        smsSatisfactionVo.setLocid("locid");
        smsSatisfactionVo.setLocname("locname");
        smsSatisfactionVo.setAreaid("areaid");
        smsSatisfactionVo.setAreaname("areaname");
        smsSatisfactionVo.setTcnt("tcnt");
        smsSatisfactionVo.setVscnt("vscnt");
        smsSatisfactionVo.setScnt("scnt");
        smsSatisfactionVo.setNscnt("nscnt");
        final List<SMSSatisfactionVo> expectedResult = Arrays.asList(smsSatisfactionVo);

        // Run the test
        final List<SMSSatisfactionVo> result = satisfiedHospitalServiceUnderTest.querySMSSatisfactionList(rankVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
