package com.ikang.idata.search.search.service;

import com.ikang.idata.common.entity.*;
import com.ikang.idata.common.entity.vo.JobDistrictHeadArea;
import com.ikang.idata.common.entity.vo.NationalRankFilmVo;
import com.ikang.idata.common.entity.vo.UserChannelAndHospital;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.DictFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.ReturnDataFeignServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NationalRankServiceTest {

    @Mock
    private GeneralService mockGeneralService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;
    @Mock
    private ReturnDataFeignServiceImpl mockReturnDataFeignService;
    @Mock
    private DictFeignServiceImpl mockDictFeignService;

    @InjectMocks
    private NationalRankService nationalRankServiceUnderTest;

    @Test
    public void testFind() {
        // Setup
        final NationalRankFilmVo nationalRankFilmVo = new NationalRankFilmVo();
        nationalRankFilmVo.setCheckMonth("checkMonth");
        nationalRankFilmVo.setFiscalYear("fiscalYear");
        nationalRankFilmVo.setCheckCode("checkCode");
        nationalRankFilmVo.setOrderByStr("orderByStr");
        nationalRankFilmVo.setSortOrder("sortOrder");

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

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
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
        when(mockReturnDataFeignService.listByReturnDataIndex("returnDataIndex")).thenReturn(returnData);

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
        final BaseSearch result = nationalRankServiceUnderTest.find(nationalRankFilmVo);

        // Verify the results
        verify(mockGeneralService).returnDateAfterHandle(new HashMap<>(), new HashMap<>(), new ReturnData(), Arrays.asList(new DictInfo()));
    }

    @Test
    public void testFind_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        final NationalRankFilmVo nationalRankFilmVo = new NationalRankFilmVo();
        nationalRankFilmVo.setCheckMonth("checkMonth");
        nationalRankFilmVo.setFiscalYear("fiscalYear");
        nationalRankFilmVo.setCheckCode("checkCode");
        nationalRankFilmVo.setOrderByStr("orderByStr");
        nationalRankFilmVo.setSortOrder("sortOrder");

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

        when(mockReturnDataFeignService.listByReturnDataIndex("returnDataIndex")).thenReturn(Collections.emptyList());

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
        final BaseSearch result = nationalRankServiceUnderTest.find(nationalRankFilmVo);

        // Verify the results
        verify(mockGeneralService).returnDateAfterHandle(new HashMap<>(), new HashMap<>(), new ReturnData(), Arrays.asList(new DictInfo()));
    }

    @Test
    public void testFind_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final NationalRankFilmVo nationalRankFilmVo = new NationalRankFilmVo();
        nationalRankFilmVo.setCheckMonth("checkMonth");
        nationalRankFilmVo.setFiscalYear("fiscalYear");
        nationalRankFilmVo.setCheckCode("checkCode");
        nationalRankFilmVo.setOrderByStr("orderByStr");
        nationalRankFilmVo.setSortOrder("sortOrder");

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

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
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
        when(mockReturnDataFeignService.listByReturnDataIndex("returnDataIndex")).thenReturn(returnData);

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());

        // Run the test
        final BaseSearch result = nationalRankServiceUnderTest.find(nationalRankFilmVo);

        // Verify the results
        verify(mockGeneralService).returnDateAfterHandle(new HashMap<>(), new HashMap<>(), new ReturnData(), Arrays.asList(new DictInfo()));
    }

    @Test
    public void testHead() {
        // Setup
        final List<Map<String, Object>> list = Arrays.asList(new HashMap<>());

        // Run the test
        final List result = nationalRankServiceUnderTest.head(list);

        // Verify the results
    }

    @Test
    public void testDataList() {
        // Setup
        final List<Map<String, Object>> listMap = Arrays.asList(new HashMap<>());
        final List<Map<String, Object>> columnNames = Arrays.asList(new HashMap<>());

        // Run the test
        final List<List<Object>> result = nationalRankServiceUnderTest.dataList(listMap, columnNames);

        // Verify the results
    }


}
