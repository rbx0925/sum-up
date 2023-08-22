package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.CiTy;
import com.ikang.idata.common.entity.vo.SiginingVO;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusinessSigningServiceTest {

    @Mock
    private BusinessService mockBusinessService;
    @Mock
    private UserSearchService mockUserSearchService;

    private BusinessSigningService businessSigningServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        businessSigningServiceUnderTest = new BusinessSigningService(mockBusinessService, mockUserSearchService);
    }

    @Test
    void testFindSigining() {
        // Setup
        final SiginingVO siginingVO = new SiginingVO();
        siginingVO.setResourceId(0L);
        siginingVO.setQueryMap(new HashMap<>());
        siginingVO.setPageNum(0);
        siginingVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        siginingVO.setGroupBy(Arrays.asList(aggregationCondition));
        siginingVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        siginingVO.setDesensitization(false);
        siginingVO.setSiginingType("siginingType");
        siginingVO.setName("name");
        siginingVO.setOwnerId("ownerId");
        siginingVO.setKhmc("khmc");
        siginingVO.setXmjc("xmjc");
        siginingVO.setXszj("xszj");
        siginingVO.setYwjhmc("ywjhmc");
        siginingVO.setType("type");
        siginingVO.setSsdq("ssdq");
        siginingVO.setDaqu("daqu");
        siginingVO.setQysjStart("qysjStart");
        siginingVO.setQysjEnd("qysjEnd");
        siginingVO.setXmjeLow("xmjeLow");
        siginingVO.setXmjeHigh("xmjeHigh");
        siginingVO.setTjrsLow("tjrsLow");
        siginingVO.setTjrsHigh("tjrsHigh");
        siginingVO.setZhuangtai("zhuangtai");
        siginingVO.setXmksrqBegin("xmksrqBegin");
        siginingVO.setXmksrqEnd("xmksrqEnd");
        siginingVO.setXmjsrqBegin("xmjsrqBegin");
        siginingVO.setXmjsrqEnd("xmjsrqEnd");
        siginingVO.setBxkhhzlx("bxkhhzlx");
        siginingVO.setFuwurenqunleixing("fuwurenqunleixing");
        siginingVO.setKhlx("khlx");

        when(mockBusinessService.showCiTy("daqu", 1)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        final BaseSearch result = businessSigningServiceUnderTest.findSigining(siginingVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testFindSigining_BusinessServiceReturnsNoItems() {
        // Setup
        final SiginingVO siginingVO = new SiginingVO();
        siginingVO.setResourceId(0L);
        siginingVO.setQueryMap(new HashMap<>());
        siginingVO.setPageNum(0);
        siginingVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        siginingVO.setGroupBy(Arrays.asList(aggregationCondition));
        siginingVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        siginingVO.setDesensitization(false);
        siginingVO.setSiginingType("siginingType");
        siginingVO.setName("name");
        siginingVO.setOwnerId("ownerId");
        siginingVO.setKhmc("khmc");
        siginingVO.setXmjc("xmjc");
        siginingVO.setXszj("xszj");
        siginingVO.setYwjhmc("ywjhmc");
        siginingVO.setType("type");
        siginingVO.setSsdq("ssdq");
        siginingVO.setDaqu("daqu");
        siginingVO.setQysjStart("qysjStart");
        siginingVO.setQysjEnd("qysjEnd");
        siginingVO.setXmjeLow("xmjeLow");
        siginingVO.setXmjeHigh("xmjeHigh");
        siginingVO.setTjrsLow("tjrsLow");
        siginingVO.setTjrsHigh("tjrsHigh");
        siginingVO.setZhuangtai("zhuangtai");
        siginingVO.setXmksrqBegin("xmksrqBegin");
        siginingVO.setXmksrqEnd("xmksrqEnd");
        siginingVO.setXmjsrqBegin("xmjsrqBegin");
        siginingVO.setXmjsrqEnd("xmjsrqEnd");
        siginingVO.setBxkhhzlx("bxkhhzlx");
        siginingVO.setFuwurenqunleixing("fuwurenqunleixing");
        siginingVO.setKhlx("khlx");

        when(mockBusinessService.showCiTy("daqu", 1)).thenReturn(Collections.emptyList());

        // Run the test
        final BaseSearch result = businessSigningServiceUnderTest.findSigining(siginingVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));
        when(mockBusinessService.showCiTy("daqu", 1)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        final BoolQueryBuilder result = businessSigningServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testAssemblyConditions_BusinessServiceReturnsNoItems() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));
        when(mockBusinessService.showCiTy("daqu", 1)).thenReturn(Collections.emptyList());

        // Run the test
        final BoolQueryBuilder result = businessSigningServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testExportExcelSiginng() throws Exception {
        // Setup
        final SiginingVO siginingVO = new SiginingVO();
        siginingVO.setResourceId(0L);
        siginingVO.setQueryMap(new HashMap<>());
        siginingVO.setPageNum(0);
        siginingVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        siginingVO.setGroupBy(Arrays.asList(aggregationCondition));
        siginingVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        siginingVO.setDesensitization(false);
        siginingVO.setSiginingType("siginingType");
        siginingVO.setName("name");
        siginingVO.setOwnerId("ownerId");
        siginingVO.setKhmc("khmc");
        siginingVO.setXmjc("xmjc");
        siginingVO.setXszj("xszj");
        siginingVO.setYwjhmc("ywjhmc");
        siginingVO.setType("type");
        siginingVO.setSsdq("ssdq");
        siginingVO.setDaqu("daqu");
        siginingVO.setQysjStart("qysjStart");
        siginingVO.setQysjEnd("qysjEnd");
        siginingVO.setXmjeLow("xmjeLow");
        siginingVO.setXmjeHigh("xmjeHigh");
        siginingVO.setTjrsLow("tjrsLow");
        siginingVO.setTjrsHigh("tjrsHigh");
        siginingVO.setZhuangtai("zhuangtai");
        siginingVO.setXmksrqBegin("xmksrqBegin");
        siginingVO.setXmksrqEnd("xmksrqEnd");
        siginingVO.setXmjsrqBegin("xmjsrqBegin");
        siginingVO.setXmjsrqEnd("xmjsrqEnd");
        siginingVO.setBxkhhzlx("bxkhhzlx");
        siginingVO.setFuwurenqunleixing("fuwurenqunleixing");
        siginingVO.setKhlx("khlx");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        when(mockBusinessService.showCiTy("daqu", 1)).thenReturn(Arrays.asList(new CiTy("city")));

        // Run the test
        businessSigningServiceUnderTest.exportExcelSiginng(siginingVO, response);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testExportExcelSiginng_BusinessServiceReturnsNoItems() throws Exception {
        // Setup
        final SiginingVO siginingVO = new SiginingVO();
        siginingVO.setResourceId(0L);
        siginingVO.setQueryMap(new HashMap<>());
        siginingVO.setPageNum(0);
        siginingVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        siginingVO.setGroupBy(Arrays.asList(aggregationCondition));
        siginingVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        siginingVO.setDesensitization(false);
        siginingVO.setSiginingType("siginingType");
        siginingVO.setName("name");
        siginingVO.setOwnerId("ownerId");
        siginingVO.setKhmc("khmc");
        siginingVO.setXmjc("xmjc");
        siginingVO.setXszj("xszj");
        siginingVO.setYwjhmc("ywjhmc");
        siginingVO.setType("type");
        siginingVO.setSsdq("ssdq");
        siginingVO.setDaqu("daqu");
        siginingVO.setQysjStart("qysjStart");
        siginingVO.setQysjEnd("qysjEnd");
        siginingVO.setXmjeLow("xmjeLow");
        siginingVO.setXmjeHigh("xmjeHigh");
        siginingVO.setTjrsLow("tjrsLow");
        siginingVO.setTjrsHigh("tjrsHigh");
        siginingVO.setZhuangtai("zhuangtai");
        siginingVO.setXmksrqBegin("xmksrqBegin");
        siginingVO.setXmksrqEnd("xmksrqEnd");
        siginingVO.setXmjsrqBegin("xmjsrqBegin");
        siginingVO.setXmjsrqEnd("xmjsrqEnd");
        siginingVO.setBxkhhzlx("bxkhhzlx");
        siginingVO.setFuwurenqunleixing("fuwurenqunleixing");
        siginingVO.setKhlx("khlx");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        when(mockBusinessService.showCiTy("daqu", 1)).thenReturn(Collections.emptyList());

        // Run the test
        businessSigningServiceUnderTest.exportExcelSiginng(siginingVO, response);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }
}
