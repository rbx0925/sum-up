package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.DictInfo;
import com.ikang.idata.common.entity.vo.UserChannelAndHospital;
import com.ikang.idata.common.entity.vo.VaccinumSubjectVO;
import com.ikang.idata.search.search.feign.impl.DictFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VaccinumSubjectServiceTest {

    @Mock
    private UserSearchService mockUserSearchService;
    @Mock
    private DictFeignServiceImpl mockDictFeignService;

    private VaccinumSubjectService vaccinumSubjectServiceUnderTest;

    @Before
    public void setUp() {
        vaccinumSubjectServiceUnderTest = new VaccinumSubjectService(mockUserSearchService, mockDictFeignService);
    }

    @Test
    public void testShow() {
        // Setup
        // Run the test
        final UserChannelAndHospital result = vaccinumSubjectServiceUnderTest.show();

        // Verify the results
    }

    @Test
    public void testFind() {
        // Setup
        final VaccinumSubjectVO vaccinumSubjectVO = new VaccinumSubjectVO();
        vaccinumSubjectVO.setType("type");
        vaccinumSubjectVO.setContractid("contractid");
        vaccinumSubjectVO.setCCardNumber("cCardNumber");
        vaccinumSubjectVO.setCChannelName("cChannelName");
        vaccinumSubjectVO.setSaleOrderCode("saleOrderCode");
        vaccinumSubjectVO.setCGoodsName("cGoodsName");
        vaccinumSubjectVO.setServerAllamount("serverAllamount");
        vaccinumSubjectVO.setServerUseamount("serverUseamount");
        vaccinumSubjectVO.setSaleOrderTimeStart("saleOrderTimeStart");
        vaccinumSubjectVO.setSaleOrderTimeEnd("saleOrderTimeEnd");

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
        final BaseSearch result = vaccinumSubjectServiceUnderTest.find(vaccinumSubjectVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    public void testFind_DictFeignServiceImplReturnsNoItems() {
        // Setup
        final VaccinumSubjectVO vaccinumSubjectVO = new VaccinumSubjectVO();
        vaccinumSubjectVO.setType("type");
        vaccinumSubjectVO.setContractid("contractid");
        vaccinumSubjectVO.setCCardNumber("cCardNumber");
        vaccinumSubjectVO.setCChannelName("cChannelName");
        vaccinumSubjectVO.setSaleOrderCode("saleOrderCode");
        vaccinumSubjectVO.setCGoodsName("cGoodsName");
        vaccinumSubjectVO.setServerAllamount("serverAllamount");
        vaccinumSubjectVO.setServerUseamount("serverUseamount");
        vaccinumSubjectVO.setSaleOrderTimeStart("saleOrderTimeStart");
        vaccinumSubjectVO.setSaleOrderTimeEnd("saleOrderTimeEnd");

        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(Collections.emptyList());

        // Run the test
        final BaseSearch result = vaccinumSubjectServiceUnderTest.find(vaccinumSubjectVO);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    public void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = vaccinumSubjectServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }
}
