package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.*;
import com.ikang.idata.common.entity.vo.EleThemeVo;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.SystemUserFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EleThemeServiceTest {

    @Mock
    private BusinessService mockBusinessService;
    @Mock
    private UserSearchService mockUserSearchService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;
    @Mock
    private SystemUserFeignServiceImpl mockSystemUserFeignService;

    @InjectMocks
    private EleThemeService eleThemeServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final EleThemeVo eleThemeVo = new EleThemeVo();
        eleThemeVo.setResourceId(0L);
        eleThemeVo.setQueryMap(new HashMap<>());
        eleThemeVo.setPageNum(0);
        eleThemeVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        eleThemeVo.setGroupBy(Arrays.asList(aggregationCondition));
        eleThemeVo.setChannels("channels");
        eleThemeVo.setCardUseStatus(0);
        eleThemeVo.setRegHospCodesList("regHospCodesList");
        eleThemeVo.setRegHospCodesListCopy("regHospCodesListCopy");
        eleThemeVo.setCheckHospCodesList("checkHospCodesList");
        eleThemeVo.setCheckHospCodesListCopy("checkHospCodesListCopy");
        eleThemeVo.setRegChannel("regChannel");
        eleThemeVo.setReportTimeDiff("reportTimeDiff");
        eleThemeVo.setFinance("finance");
        eleThemeVo.setDaqu("daqu");
        eleThemeVo.setSsdq("ssdq");
        eleThemeVo.setFinanceCodesList("financeCodesList");
        eleThemeVo.setIndustryCodesList("industryCodesList");
        eleThemeVo.setAreaCodesList("areaCodesList");
        eleThemeVo.setPostCodesList("postCodesList");

        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        final BaseSearch result = eleThemeServiceUnderTest.find(eleThemeVo);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testFind_BusinessServiceReturnsNoItems() {
        // Setup
        final EleThemeVo eleThemeVo = new EleThemeVo();
        eleThemeVo.setResourceId(0L);
        eleThemeVo.setQueryMap(new HashMap<>());
        eleThemeVo.setPageNum(0);
        eleThemeVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        eleThemeVo.setGroupBy(Arrays.asList(aggregationCondition));
        eleThemeVo.setChannels("channels");
        eleThemeVo.setCardUseStatus(0);
        eleThemeVo.setRegHospCodesList("regHospCodesList");
        eleThemeVo.setRegHospCodesListCopy("regHospCodesListCopy");
        eleThemeVo.setCheckHospCodesList("checkHospCodesList");
        eleThemeVo.setCheckHospCodesListCopy("checkHospCodesListCopy");
        eleThemeVo.setRegChannel("regChannel");
        eleThemeVo.setReportTimeDiff("reportTimeDiff");
        eleThemeVo.setFinance("finance");
        eleThemeVo.setDaqu("daqu");
        eleThemeVo.setSsdq("ssdq");
        eleThemeVo.setFinanceCodesList("financeCodesList");
        eleThemeVo.setIndustryCodesList("industryCodesList");
        eleThemeVo.setAreaCodesList("areaCodesList");
        eleThemeVo.setPostCodesList("postCodesList");

        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Collections.emptyList());

        // Run the test
        final BaseSearch result = eleThemeServiceUnderTest.find(eleThemeVo);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final EleThemeVo eleThemeVo = new EleThemeVo();
        eleThemeVo.setResourceId(0L);
        eleThemeVo.setQueryMap(new HashMap<>());
        eleThemeVo.setPageNum(0);
        eleThemeVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        eleThemeVo.setGroupBy(Arrays.asList(aggregationCondition));
        eleThemeVo.setChannels("channels");
        eleThemeVo.setCardUseStatus(0);
        eleThemeVo.setRegHospCodesList("regHospCodesList");
        eleThemeVo.setRegHospCodesListCopy("regHospCodesListCopy");
        eleThemeVo.setCheckHospCodesList("checkHospCodesList");
        eleThemeVo.setCheckHospCodesListCopy("checkHospCodesListCopy");
        eleThemeVo.setRegChannel("regChannel");
        eleThemeVo.setReportTimeDiff("reportTimeDiff");
        eleThemeVo.setFinance("finance");
        eleThemeVo.setDaqu("daqu");
        eleThemeVo.setSsdq("ssdq");
        eleThemeVo.setFinanceCodesList("financeCodesList");
        eleThemeVo.setIndustryCodesList("industryCodesList");
        eleThemeVo.setAreaCodesList("areaCodesList");
        eleThemeVo.setPostCodesList("postCodesList");

        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        final BaseSearch result = eleThemeServiceUnderTest.findGroupBy(eleThemeVo);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
        verify(mockUserSearchService).getSumAvg(eq(Arrays.asList("value")), any(BaseSearch.class),
                eq(new JSONObject(0, false)));
        verify(mockUserSearchService).getGroupOneAndTwo(eq(Arrays.asList("value")), eq(Arrays.asList(new ReturnData())),
                eq(Arrays.asList(new DictInfo())), any(BaseSearch.class), any(BaseVO.class),
                eq(new JSONObject(0, false)));
    }

    @Test
    void testFindGroupBy_BusinessServiceReturnsNoItems() {
        // Setup
        final EleThemeVo eleThemeVo = new EleThemeVo();
        eleThemeVo.setResourceId(0L);
        eleThemeVo.setQueryMap(new HashMap<>());
        eleThemeVo.setPageNum(0);
        eleThemeVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        eleThemeVo.setGroupBy(Arrays.asList(aggregationCondition));
        eleThemeVo.setChannels("channels");
        eleThemeVo.setCardUseStatus(0);
        eleThemeVo.setRegHospCodesList("regHospCodesList");
        eleThemeVo.setRegHospCodesListCopy("regHospCodesListCopy");
        eleThemeVo.setCheckHospCodesList("checkHospCodesList");
        eleThemeVo.setCheckHospCodesListCopy("checkHospCodesListCopy");
        eleThemeVo.setRegChannel("regChannel");
        eleThemeVo.setReportTimeDiff("reportTimeDiff");
        eleThemeVo.setFinance("finance");
        eleThemeVo.setDaqu("daqu");
        eleThemeVo.setSsdq("ssdq");
        eleThemeVo.setFinanceCodesList("financeCodesList");
        eleThemeVo.setIndustryCodesList("industryCodesList");
        eleThemeVo.setAreaCodesList("areaCodesList");
        eleThemeVo.setPostCodesList("postCodesList");

        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Collections.emptyList());

        // Run the test
        final BaseSearch result = eleThemeServiceUnderTest.findGroupBy(eleThemeVo);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
        verify(mockUserSearchService).getSumAvg(eq(Arrays.asList("value")), any(BaseSearch.class),
                eq(new JSONObject(0, false)));
        verify(mockUserSearchService).getGroupOneAndTwo(eq(Arrays.asList("value")), eq(Arrays.asList(new ReturnData())),
                eq(Arrays.asList(new DictInfo())), any(BaseSearch.class), any(BaseVO.class),
                eq(new JSONObject(0, false)));
    }

    @Test
    void testExportExcel() throws Exception {
        // Setup
        final EleThemeVo eleThemeVo = new EleThemeVo();
        eleThemeVo.setResourceId(0L);
        eleThemeVo.setQueryMap(new HashMap<>());
        eleThemeVo.setPageNum(0);
        eleThemeVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        eleThemeVo.setGroupBy(Arrays.asList(aggregationCondition));
        eleThemeVo.setChannels("channels");
        eleThemeVo.setCardUseStatus(0);
        eleThemeVo.setRegHospCodesList("regHospCodesList");
        eleThemeVo.setRegHospCodesListCopy("regHospCodesListCopy");
        eleThemeVo.setCheckHospCodesList("checkHospCodesList");
        eleThemeVo.setCheckHospCodesListCopy("checkHospCodesListCopy");
        eleThemeVo.setRegChannel("regChannel");
        eleThemeVo.setReportTimeDiff("reportTimeDiff");
        eleThemeVo.setFinance("finance");
        eleThemeVo.setDaqu("daqu");
        eleThemeVo.setSsdq("ssdq");
        eleThemeVo.setFinanceCodesList("financeCodesList");
        eleThemeVo.setIndustryCodesList("industryCodesList");
        eleThemeVo.setAreaCodesList("areaCodesList");
        eleThemeVo.setPostCodesList("postCodesList");

        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        eleThemeServiceUnderTest.exportExcel(eleThemeVo, response);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
        verify(mockUserSearchService).getSumAvg(eq(Arrays.asList("value")), any(BaseSearch.class),
                eq(new JSONObject(0, false)));
        verify(mockUserSearchService).getGroupOneAndTwo(eq(Arrays.asList("value")), eq(Arrays.asList(new ReturnData())),
                eq(Arrays.asList(new DictInfo())), any(BaseSearch.class), any(BaseVO.class),
                eq(new JSONObject(0, false)));
    }

    @Test
    void testExportExcel_BusinessServiceReturnsNoItems() throws Exception {
        // Setup
        final EleThemeVo eleThemeVo = new EleThemeVo();
        eleThemeVo.setResourceId(0L);
        eleThemeVo.setQueryMap(new HashMap<>());
        eleThemeVo.setPageNum(0);
        eleThemeVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        eleThemeVo.setGroupBy(Arrays.asList(aggregationCondition));
        eleThemeVo.setChannels("channels");
        eleThemeVo.setCardUseStatus(0);
        eleThemeVo.setRegHospCodesList("regHospCodesList");
        eleThemeVo.setRegHospCodesListCopy("regHospCodesListCopy");
        eleThemeVo.setCheckHospCodesList("checkHospCodesList");
        eleThemeVo.setCheckHospCodesListCopy("checkHospCodesListCopy");
        eleThemeVo.setRegChannel("regChannel");
        eleThemeVo.setReportTimeDiff("reportTimeDiff");
        eleThemeVo.setFinance("finance");
        eleThemeVo.setDaqu("daqu");
        eleThemeVo.setSsdq("ssdq");
        eleThemeVo.setFinanceCodesList("financeCodesList");
        eleThemeVo.setIndustryCodesList("industryCodesList");
        eleThemeVo.setAreaCodesList("areaCodesList");
        eleThemeVo.setPostCodesList("postCodesList");

        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Collections.emptyList());

        // Run the test
        eleThemeServiceUnderTest.exportExcel(eleThemeVo, response);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
        verify(mockUserSearchService).getSumAvg(eq(Arrays.asList("value")), any(BaseSearch.class),
                eq(new JSONObject(0, false)));
        verify(mockUserSearchService).getGroupOneAndTwo(eq(Arrays.asList("value")), eq(Arrays.asList(new ReturnData())),
                eq(Arrays.asList(new DictInfo())), any(BaseSearch.class), any(BaseVO.class),
                eq(new JSONObject(0, false)));
    }

    @Test
    void testExportExcel_ThrowsException() throws Exception {
        // Setup
        final EleThemeVo eleThemeVo = new EleThemeVo();
        eleThemeVo.setResourceId(0L);
        eleThemeVo.setQueryMap(new HashMap<>());
        eleThemeVo.setPageNum(0);
        eleThemeVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        eleThemeVo.setGroupBy(Arrays.asList(aggregationCondition));
        eleThemeVo.setChannels("channels");
        eleThemeVo.setCardUseStatus(0);
        eleThemeVo.setRegHospCodesList("regHospCodesList");
        eleThemeVo.setRegHospCodesListCopy("regHospCodesListCopy");
        eleThemeVo.setCheckHospCodesList("checkHospCodesList");
        eleThemeVo.setCheckHospCodesListCopy("checkHospCodesListCopy");
        eleThemeVo.setRegChannel("regChannel");
        eleThemeVo.setReportTimeDiff("reportTimeDiff");
        eleThemeVo.setFinance("finance");
        eleThemeVo.setDaqu("daqu");
        eleThemeVo.setSsdq("ssdq");
        eleThemeVo.setFinanceCodesList("financeCodesList");
        eleThemeVo.setIndustryCodesList("industryCodesList");
        eleThemeVo.setAreaCodesList("areaCodesList");
        eleThemeVo.setPostCodesList("postCodesList");

        final HttpServletResponse response = new MockHttpServletResponse();
        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        assertThatThrownBy(() -> eleThemeServiceUnderTest.exportExcel(eleThemeVo, response))
                .isInstanceOf(Exception.class);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
        verify(mockUserSearchService).getSumAvg(eq(Arrays.asList("value")), any(BaseSearch.class),
                eq(new JSONObject(0, false)));
        verify(mockUserSearchService).getGroupOneAndTwo(eq(Arrays.asList("value")), eq(Arrays.asList(new ReturnData())),
                eq(Arrays.asList(new DictInfo())), any(BaseSearch.class), any(BaseVO.class),
                eq(new JSONObject(0, false)));
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));
        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        final BoolQueryBuilder result = eleThemeServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testAssemblyConditions_BusinessServiceReturnsNoItems() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));
        when(mockBusinessService.showCiTy("daqu", 0)).thenReturn(Collections.emptyList());

        // Run the test
        final BoolQueryBuilder result = eleThemeServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }
}
