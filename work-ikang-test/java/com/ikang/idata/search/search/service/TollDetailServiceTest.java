package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.UserChannel;
import com.ikang.idata.common.entity.es.ESBaseData;
import com.ikang.idata.common.entity.vo.*;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TollDetailServiceTest {

    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;
    @Mock
    private ESHttpClientService mockEsHttpClientService;

    @InjectMocks
    private TollDetailService tollDetailServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final TollDetailVo tollDetailVo = new TollDetailVo();
        tollDetailVo.setResourceId(0L);
        tollDetailVo.setQueryMap(new HashMap<>());
        tollDetailVo.setPageNum(0);
        tollDetailVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        tollDetailVo.setGroupBy(Arrays.asList(aggregationCondition));
        tollDetailVo.setId("id");
        tollDetailVo.setExamUserId("examUserId");
        tollDetailVo.setRegMonth("regMonth");
        tollDetailVo.setRangePickerMonthStart("rangePickerMonthStart");
        tollDetailVo.setRangePickerMonthEnd("rangePickerMonthEnd");
        tollDetailVo.setRegDate("regDate");
        tollDetailVo.setLocId("locId");

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

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("tjychangetypedetailsUrl", HttpMethod.POST, ESBaseData.class,
                "requestBody")).thenReturn(esBaseData);

        // Run the test
        final BaseSearch result = tollDetailServiceUnderTest.find(tollDetailVo);

        // Verify the results
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final TollDetailVo tollDetailVo = new TollDetailVo();
        tollDetailVo.setResourceId(0L);
        tollDetailVo.setQueryMap(new HashMap<>());
        tollDetailVo.setPageNum(0);
        tollDetailVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        tollDetailVo.setGroupBy(Arrays.asList(aggregationCondition));
        tollDetailVo.setId("id");
        tollDetailVo.setExamUserId("examUserId");
        tollDetailVo.setRegMonth("regMonth");
        tollDetailVo.setRangePickerMonthStart("rangePickerMonthStart");
        tollDetailVo.setRangePickerMonthEnd("rangePickerMonthEnd");
        tollDetailVo.setRegDate("regDate");
        tollDetailVo.setLocId("locId");

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
        final BaseSearch result = tollDetailServiceUnderTest.findGroupBy(tollDetailVo);

        // Verify the results
    }

    @Test
    void testExportExcel() throws Exception {
        // Setup
        final TollDetailVo tollDetailVo = new TollDetailVo();
        tollDetailVo.setResourceId(0L);
        tollDetailVo.setQueryMap(new HashMap<>());
        tollDetailVo.setPageNum(0);
        tollDetailVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        tollDetailVo.setGroupBy(Arrays.asList(aggregationCondition));
        tollDetailVo.setId("id");
        tollDetailVo.setExamUserId("examUserId");
        tollDetailVo.setRegMonth("regMonth");
        tollDetailVo.setRangePickerMonthStart("rangePickerMonthStart");
        tollDetailVo.setRangePickerMonthEnd("rangePickerMonthEnd");
        tollDetailVo.setRegDate("regDate");
        tollDetailVo.setLocId("locId");

        final HttpServletResponse response = new MockHttpServletResponse();

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

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("tjychangetypedetailsUrl", HttpMethod.POST, ESBaseData.class,
                "requestBody")).thenReturn(esBaseData);

        // Run the test
        tollDetailServiceUnderTest.exportExcel(tollDetailVo, response);

        // Verify the results
    }

    @Test
    void testExportExcel_ThrowsException() {
        // Setup
        final TollDetailVo tollDetailVo = new TollDetailVo();
        tollDetailVo.setResourceId(0L);
        tollDetailVo.setQueryMap(new HashMap<>());
        tollDetailVo.setPageNum(0);
        tollDetailVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        tollDetailVo.setGroupBy(Arrays.asList(aggregationCondition));
        tollDetailVo.setId("id");
        tollDetailVo.setExamUserId("examUserId");
        tollDetailVo.setRegMonth("regMonth");
        tollDetailVo.setRangePickerMonthStart("rangePickerMonthStart");
        tollDetailVo.setRangePickerMonthEnd("rangePickerMonthEnd");
        tollDetailVo.setRegDate("regDate");
        tollDetailVo.setLocId("locId");

        final HttpServletResponse response = new MockHttpServletResponse();

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

        // Configure ESHttpClientService.exchange(...).
        final ESBaseData esBaseData = new ESBaseData();
        esBaseData.setCode("code");
        esBaseData.setMessage("message");
        esBaseData.setTotal(0);
        esBaseData.setOriginalHits(Arrays.asList(new LinkedHashMap<>()));
        esBaseData.setScroll("scroll");
        when(mockEsHttpClientService.exchange("tjychangetypedetailsUrl", HttpMethod.POST, ESBaseData.class,
                "requestBody")).thenReturn(esBaseData);

        // Run the test
        assertThatThrownBy(() -> tollDetailServiceUnderTest.exportExcel(tollDetailVo, response))
                .isInstanceOf(Exception.class);
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
        final BoolQueryBuilder result = tollDetailServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
