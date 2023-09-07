package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.BRMTypeToChiefZone;
import com.ikang.idata.common.entity.vo.BRMZone;
import com.ikang.idata.common.entity.vo.DepartmentDoctorVO;
import com.ikang.idata.common.entity.vo.UserChannelAndHospital;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentDoctorServiceTest {

    @Mock
    private UserSearchService mockUserSearchService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;
    @Mock
    private GeneralService mockGeneralService;

    @InjectMocks
    private DepartmentDoctorService departmentDoctorServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final DepartmentDoctorVO vo = new DepartmentDoctorVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setDesensitization(false);
        vo.setExamDateStart("examDateStart");
        vo.setExamDateEnd("examDateEnd");
        vo.setExamineDateStart("examineDateStart");
        vo.setExamineDateEnd("examineDateEnd");
        vo.setDoctorNcc("doctorNcc");
        vo.setInstituteId("instituteId");
        vo.setDepartmentId("departmentId");
        vo.setDepartmentCode("departmentCode");
        vo.setDoctorName("doctorName");
        vo.setItemName("itemName");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmZone.setBrmZone("brmZone");
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        brmTypeToChiefZone.setProjectZoneType("projectZoneType");
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        userChannelAndHospital.setHospitals(new HashMap<>());
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BaseSearch result = departmentDoctorServiceUnderTest.find(vo);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final DepartmentDoctorVO vo = new DepartmentDoctorVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setDesensitization(false);
        vo.setExamDateStart("examDateStart");
        vo.setExamDateEnd("examDateEnd");
        vo.setExamineDateStart("examineDateStart");
        vo.setExamineDateEnd("examineDateEnd");
        vo.setDoctorNcc("doctorNcc");
        vo.setInstituteId("instituteId");
        vo.setDepartmentId("departmentId");
        vo.setDepartmentCode("departmentCode");
        vo.setDoctorName("doctorName");
        vo.setItemName("itemName");

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmZone.setBrmZone("brmZone");
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        brmTypeToChiefZone.setProjectZoneType("projectZoneType");
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        userChannelAndHospital.setHospitals(new HashMap<>());
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BaseSearch result = departmentDoctorServiceUnderTest.findGroupBy(vo);

        // Verify the results
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
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
        userChannelAndHospital.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        userChannelAndHospital.setHospitals(new HashMap<>());
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Run the test
        final BoolQueryBuilder result = departmentDoctorServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockUserSearchService).setParamMap(Arrays.asList(), new HashMap<>(), 0L);
    }
}
