package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.*;
import com.ikang.idata.common.entity.dto.DetailDto;
import com.ikang.idata.common.entity.es.DetailDtoHits;
import com.ikang.idata.common.entity.es.ESBaseData;
import com.ikang.idata.common.entity.es.SearchScrollData;
import com.ikang.idata.common.entity.vo.GeneralSearchVO;
import com.ikang.idata.common.entity.vo.JobDistrictHeadArea;
import com.ikang.idata.common.entity.vo.UserChannelAndHospital;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.system.DictFeignService;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeneralServiceTest {

    @Mock
    private DictFeignService mockDictFeignService;
    @Mock
    private ESHttpClientService mockEsHttpClientService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;

    @InjectMocks
    private GeneralService generalServiceUnderTest;

    @Test
    public void testFind() {
        // Setup
        final GeneralSearchVO generalSearchVO = new GeneralSearchVO();
        generalSearchVO.setRegMonth("regMonth");
        generalSearchVO.setAreaId("areaId");
        generalSearchVO.setLocId("locId");
        generalSearchVO.setHospId("hospId");
        generalSearchVO.setDesensitization(false);
        generalSearchVO.setSort("sort");

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

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, ESBaseData.class, "requestBody")).thenReturn(esBaseData);

        // Run the test
        final BaseSearch result = generalServiceUnderTest.find(generalSearchVO, "url");

        // Verify the results
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

        // Run the test
        final BoolQueryBuilder result = generalServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testExportExcel() throws Exception {
        // Setup
        final GeneralSearchVO generalSearchVO = new GeneralSearchVO();
        generalSearchVO.setRegMonth("regMonth");
        generalSearchVO.setAreaId("areaId");
        generalSearchVO.setLocId("locId");
        generalSearchVO.setHospId("hospId");
        generalSearchVO.setDesensitization(false);
        generalSearchVO.setSort("sort");

        final HttpServletResponse response = new MockHttpServletResponse();

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

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, ESBaseData.class, "requestBody")).thenReturn(esBaseData);

        // Run the test
        generalServiceUnderTest.exportExcel(generalSearchVO, response, "url", "reportName");

        // Verify the results
    }

    @Test
    public void testExportExcel_ThrowsException() {
        // Setup
        final GeneralSearchVO generalSearchVO = new GeneralSearchVO();
        generalSearchVO.setRegMonth("regMonth");
        generalSearchVO.setAreaId("areaId");
        generalSearchVO.setLocId("locId");
        generalSearchVO.setHospId("hospId");
        generalSearchVO.setDesensitization(false);
        generalSearchVO.setSort("sort");

        final HttpServletResponse response = new MockHttpServletResponse();

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

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, ESBaseData.class, "requestBody")).thenReturn(esBaseData);

        // Run the test
        assertThatThrownBy(() -> generalServiceUnderTest.exportExcel(generalSearchVO, response, "url", "reportName")).isInstanceOf(Exception.class);
    }

    @Test
    public void testExportReportExcelDetail() throws Exception {
        // Setup
        final GeneralSearchVO generalSearchVO = new GeneralSearchVO();
        generalSearchVO.setRegMonth("regMonth");
        generalSearchVO.setAreaId("areaId");
        generalSearchVO.setLocId("locId");
        generalSearchVO.setHospId("hospId");
        generalSearchVO.setDesensitization(false);
        generalSearchVO.setSort("sort");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure ESHttpClientService.exchange(...).
        final SearchScrollData searchScrollData = new SearchScrollData();
        final DetailDtoHits detailDtoHits = new DetailDtoHits();
        detailDtoHits.setOriginalSource(new LinkedHashMap<>());
        detailDtoHits.setIgnored(Arrays.asList("value"));
        final DetailDto source = new DetailDto();
        source.setWorkNo("workNo");
        source.setCardId("cardId");
        source.setAreaName("areaName");
        source.setLocName("locName");
        source.setCostDays(0);
        source.setCostType("costType");
        source.setRegDate("regDate");
        source.setReportDate("reportDate");
        source.setIsDelay("isDelay");
        detailDtoHits.setSource(source);
        searchScrollData.setCoverHits(Arrays.asList(detailDtoHits));
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, SearchScrollData.class, "requestBody")).thenReturn(searchScrollData);

        // Run the test
        generalServiceUnderTest.exportReportExcelDetail(generalSearchVO, response, "reportName");

        // Verify the results
    }

    @Test
    public void testExportReportExcelDetail_ThrowsException() {
        // Setup
        final GeneralSearchVO generalSearchVO = new GeneralSearchVO();
        generalSearchVO.setRegMonth("regMonth");
        generalSearchVO.setAreaId("areaId");
        generalSearchVO.setLocId("locId");
        generalSearchVO.setHospId("hospId");
        generalSearchVO.setDesensitization(false);
        generalSearchVO.setSort("sort");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure ESHttpClientService.exchange(...).
        final SearchScrollData searchScrollData = new SearchScrollData();
        final DetailDtoHits detailDtoHits = new DetailDtoHits();
        detailDtoHits.setOriginalSource(new LinkedHashMap<>());
        detailDtoHits.setIgnored(Arrays.asList("value"));
        final DetailDto source = new DetailDto();
        source.setWorkNo("workNo");
        source.setCardId("cardId");
        source.setAreaName("areaName");
        source.setLocName("locName");
        source.setCostDays(0);
        source.setCostType("costType");
        source.setRegDate("regDate");
        source.setReportDate("reportDate");
        source.setIsDelay("isDelay");
        detailDtoHits.setSource(source);
        searchScrollData.setCoverHits(Arrays.asList(detailDtoHits));
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, SearchScrollData.class, "requestBody")).thenReturn(searchScrollData);

        // Run the test
        assertThatThrownBy(() -> generalServiceUnderTest.exportReportExcelDetail(generalSearchVO, response, "reportName")).isInstanceOf(Exception.class);
    }

    @Test
    public void testGetDetail() {
        // Setup
        final GeneralSearchVO generalSearchVO = new GeneralSearchVO();
        generalSearchVO.setRegMonth("regMonth");
        generalSearchVO.setAreaId("areaId");
        generalSearchVO.setLocId("locId");
        generalSearchVO.setHospId("hospId");
        generalSearchVO.setDesensitization(false);
        generalSearchVO.setSort("sort");

        // Configure ESHttpClientService.exchange(...).
        final SearchScrollData searchScrollData = new SearchScrollData();
        final DetailDtoHits detailDtoHits = new DetailDtoHits();
        detailDtoHits.setOriginalSource(new LinkedHashMap<>());
        detailDtoHits.setIgnored(Arrays.asList("value"));
        final DetailDto source = new DetailDto();
        source.setWorkNo("workNo");
        source.setCardId("cardId");
        source.setAreaName("areaName");
        source.setLocName("locName");
        source.setCostDays(0);
        source.setCostType("costType");
        source.setRegDate("regDate");
        source.setReportDate("reportDate");
        source.setIsDelay("isDelay");
        detailDtoHits.setSource(source);
        searchScrollData.setCoverHits(Arrays.asList(detailDtoHits));
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, SearchScrollData.class, "requestBody")).thenReturn(searchScrollData);

        // Run the test
        final List<DetailDto> result = generalServiceUnderTest.getDetail(generalSearchVO);

        // Verify the results
    }

    @Test
    public void testInvokeAndScrollGetResult() {
        // Setup
        // Configure ESHttpClientService.exchange(...).
        final SearchScrollData searchScrollData = new SearchScrollData();
        final DetailDtoHits detailDtoHits = new DetailDtoHits();
        detailDtoHits.setOriginalSource(new LinkedHashMap<>());
        detailDtoHits.setIgnored(Arrays.asList("value"));
        final DetailDto source = new DetailDto();
        source.setWorkNo("workNo");
        source.setCardId("cardId");
        source.setAreaName("areaName");
        source.setLocName("locName");
        source.setCostDays(0);
        source.setCostType("costType");
        source.setRegDate("regDate");
        source.setReportDate("reportDate");
        source.setIsDelay("isDelay");
        detailDtoHits.setSource(source);
        searchScrollData.setCoverHits(Arrays.asList(detailDtoHits));
        when(mockEsHttpClientService.exchange("url", HttpMethod.GET, SearchScrollData.class, "requestBody")).thenReturn(searchScrollData);

        // Run the test
        final List<DetailDto> result = generalServiceUnderTest.invokeAndScrollGetResult("queryDsl");

        // Verify the results
    }

    @Test
    public void testReturnDateAfterHandle() {
        // Setup
        final Map<String, Object> source = new HashMap<>();
        final Map ma = new HashMap<>();
        final ReturnData el = new ReturnData();
        el.setFieldDesc("fieldDesc");
        el.setSumOrAvgFlag(0);
        el.setId(0L);
        el.setReturnDataIndex("returnDataIndex");
        el.setReturnDataCode("returnDataCode");
        el.setReturnDataName("returnDataName");
        el.setDataType(0);
        el.setDataValue("dataValue");
        el.setReturnDataDeals(0);
        el.setReturnDataValue("returnDataValue");

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
        final List<DictInfo> byType = Arrays.asList(dictInfo);

        // Run the test
        generalServiceUnderTest.returnDateAfterHandle(source, ma, el, byType);

        // Verify the results
    }
}
