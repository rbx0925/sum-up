package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.BRMTypeToChiefZone;
import com.ikang.idata.common.entity.vo.BRMZone;
import com.ikang.idata.common.entity.vo.DentistryVo;
import com.ikang.idata.common.entity.vo.UserChannelAndHospital;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DentistryServiceTest {

    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;

    @InjectMocks
    private DentistryService dentistryServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final DentistryVo dentistryVo = new DentistryVo();
        dentistryVo.setResourceId(0L);
        dentistryVo.setQueryMap(new HashMap<>());
        dentistryVo.setPageNum(0);
        dentistryVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        dentistryVo.setGroupBy(Arrays.asList(aggregationCondition));
        dentistryVo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        dentistryVo.setDesensitization(false);
        dentistryVo.setLocId("locId");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmZone.setBrmZone("brmZone");
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setPermissionFlag(0);
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BaseSearch result = dentistryServiceUnderTest.find(dentistryVo);

        // Verify the results
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final DentistryVo dentistryVo = new DentistryVo();
        dentistryVo.setResourceId(0L);
        dentistryVo.setQueryMap(new HashMap<>());
        dentistryVo.setPageNum(0);
        dentistryVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        dentistryVo.setGroupBy(Arrays.asList(aggregationCondition));
        dentistryVo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        dentistryVo.setDesensitization(false);
        dentistryVo.setLocId("locId");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmZone.setBrmZone("brmZone");
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setPermissionFlag(0);
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BaseSearch result = dentistryServiceUnderTest.findGroupBy(dentistryVo);

        // Verify the results
    }

    @Test
    void testExportExcel() throws Exception {
        // Setup
        final DentistryVo dentistryVo = new DentistryVo();
        dentistryVo.setResourceId(0L);
        dentistryVo.setQueryMap(new HashMap<>());
        dentistryVo.setPageNum(0);
        dentistryVo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        dentistryVo.setGroupBy(Arrays.asList(aggregationCondition));
        dentistryVo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        dentistryVo.setDesensitization(false);
        dentistryVo.setLocId("locId");

        final MockHttpServletResponse response = new MockHttpServletResponse();

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmZone.setBrmZone("brmZone");
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setPermissionFlag(0);
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        dentistryServiceUnderTest.exportExcel(dentistryVo, response);

        // Verify the results
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
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        userChannelAndHospital.setHospitals(new HashMap<>());
        userChannelAndHospital.setPermissionFlag(0);
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BoolQueryBuilder result = dentistryServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
