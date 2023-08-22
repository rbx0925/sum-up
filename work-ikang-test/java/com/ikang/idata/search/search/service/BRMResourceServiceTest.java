package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.BRMResourceVO;
import com.ikang.idata.search.search.entity.vo.BRMZone;
import com.ikang.idata.search.search.entity.vo.BRMZoneDTO;
import com.ikang.idata.search.search.entity.vo.BRMZoneReturnVO;
import com.ikang.idata.search.search.feign.system.SystemUserFeignService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BRMResourceServiceTest {

    @Mock
    private ConstantService mockConstantService;
    @Mock
    private SystemUserFeignService mockSystemUserFeignService;

    @InjectMocks
    private BRMResourceService brmResourceServiceUnderTest;

    @Test
    void testFindBRMzone() {
        // Setup
        final BRMResourceVO brmResource = new BRMResourceVO();
        brmResource.setId("id");
        brmResource.setLeaderid("leaderid");
        brmResource.setChiefZone("chiefZone");
        brmResource.setBrmZone("brmZone");
        brmResource.setBrmDepartment("brmDepartment");
        brmResource.setBrmGroup("brmGroup");
        brmResource.setProfile("profile");
        brmResource.setIsusing("isusing");
        brmResource.setName("name");

        when(mockConstantService.getSourceByCondition("searchpowerUrl", "queryDsl"))
                .thenReturn(Arrays.asList(new JSONObject(0, false)));

        // Run the test
        final List<String> result = brmResourceServiceUnderTest.findBRMzone(brmResource);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testFindBRMzone_ConstantServiceReturnsNoItems() {
        // Setup
        final BRMResourceVO brmResource = new BRMResourceVO();
        brmResource.setId("id");
        brmResource.setLeaderid("leaderid");
        brmResource.setChiefZone("chiefZone");
        brmResource.setBrmZone("brmZone");
        brmResource.setBrmDepartment("brmDepartment");
        brmResource.setBrmGroup("brmGroup");
        brmResource.setProfile("profile");
        brmResource.setIsusing("isusing");
        brmResource.setName("name");

        when(mockConstantService.getSourceByCondition("searchpowerUrl", "queryDsl"))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = brmResourceServiceUnderTest.findBRMzone(brmResource);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindBRMDepartment() {
        // Setup
        final BRMResourceVO brmResource = new BRMResourceVO();
        brmResource.setId("id");
        brmResource.setLeaderid("leaderid");
        brmResource.setChiefZone("chiefZone");
        brmResource.setBrmZone("brmZone");
        brmResource.setBrmDepartment("brmDepartment");
        brmResource.setBrmGroup("brmGroup");
        brmResource.setProfile("profile");
        brmResource.setIsusing("isusing");
        brmResource.setName("name");

        when(mockConstantService.getSourceByCondition("searchpowerUrl", "queryDsl"))
                .thenReturn(Arrays.asList(new JSONObject(0, false)));

        // Run the test
        final List<String> result = brmResourceServiceUnderTest.findBRMDepartment(brmResource);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testFindBRMDepartment_ConstantServiceReturnsNoItems() {
        // Setup
        final BRMResourceVO brmResource = new BRMResourceVO();
        brmResource.setId("id");
        brmResource.setLeaderid("leaderid");
        brmResource.setChiefZone("chiefZone");
        brmResource.setBrmZone("brmZone");
        brmResource.setBrmDepartment("brmDepartment");
        brmResource.setBrmGroup("brmGroup");
        brmResource.setProfile("profile");
        brmResource.setIsusing("isusing");
        brmResource.setName("name");

        when(mockConstantService.getSourceByCondition("searchpowerUrl", "queryDsl"))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = brmResourceServiceUnderTest.findBRMDepartment(brmResource);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindBRMGroup() {
        // Setup
        final BRMResourceVO brmResource = new BRMResourceVO();
        brmResource.setId("id");
        brmResource.setLeaderid("leaderid");
        brmResource.setChiefZone("chiefZone");
        brmResource.setBrmZone("brmZone");
        brmResource.setBrmDepartment("brmDepartment");
        brmResource.setBrmGroup("brmGroup");
        brmResource.setProfile("profile");
        brmResource.setIsusing("isusing");
        brmResource.setName("name");

        when(mockConstantService.getSourceByCondition("searchpowerUrl", "queryDsl"))
                .thenReturn(Arrays.asList(new JSONObject(0, false)));

        // Run the test
        final List<String> result = brmResourceServiceUnderTest.findBRMGroup(brmResource);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testFindBRMGroup_ConstantServiceReturnsNoItems() {
        // Setup
        final BRMResourceVO brmResource = new BRMResourceVO();
        brmResource.setId("id");
        brmResource.setLeaderid("leaderid");
        brmResource.setChiefZone("chiefZone");
        brmResource.setBrmZone("brmZone");
        brmResource.setBrmDepartment("brmDepartment");
        brmResource.setBrmGroup("brmGroup");
        brmResource.setProfile("profile");
        brmResource.setIsusing("isusing");
        brmResource.setName("name");

        when(mockConstantService.getSourceByCondition("searchpowerUrl", "queryDsl"))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = brmResourceServiceUnderTest.findBRMGroup(brmResource);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindBRMzoneById() {
        // Setup
        final BRMResourceVO brmResource = new BRMResourceVO();
        brmResource.setId("id");
        brmResource.setLeaderid("leaderid");
        brmResource.setChiefZone("chiefZone");
        brmResource.setBrmZone("brmZone");
        brmResource.setBrmDepartment("brmDepartment");
        brmResource.setBrmGroup("brmGroup");
        brmResource.setProfile("profile");
        brmResource.setIsusing("isusing");
        brmResource.setName("name");

        when(mockConstantService.getSourceByCondition("searchpowerUrl", "queryDsl"))
                .thenReturn(Arrays.asList(new JSONObject(0, false)));

        // Run the test
        final List<String> result = brmResourceServiceUnderTest.findBRMzoneById(brmResource);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testFindBRMzoneById_ConstantServiceReturnsNoItems() {
        // Setup
        final BRMResourceVO brmResource = new BRMResourceVO();
        brmResource.setId("id");
        brmResource.setLeaderid("leaderid");
        brmResource.setChiefZone("chiefZone");
        brmResource.setBrmZone("brmZone");
        brmResource.setBrmDepartment("brmDepartment");
        brmResource.setBrmGroup("brmGroup");
        brmResource.setProfile("profile");
        brmResource.setIsusing("isusing");
        brmResource.setName("name");

        when(mockConstantService.getSourceByCondition("searchpowerUrl", "queryDsl"))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<String> result = brmResourceServiceUnderTest.findBRMzoneById(brmResource);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindBRMzoneByName() {
        // Setup
        final BRMResourceVO brmResource = new BRMResourceVO();
        brmResource.setId("id");
        brmResource.setLeaderid("leaderid");
        brmResource.setChiefZone("chiefZone");
        brmResource.setBrmZone("brmZone");
        brmResource.setBrmDepartment("brmDepartment");
        brmResource.setBrmGroup("brmGroup");
        brmResource.setProfile("profile");
        brmResource.setIsusing("isusing");
        brmResource.setName("name");

        final List<Map<String, String>> expectedResult = Arrays.asList(new HashMap<>());
        when(mockConstantService.getSourceByCondition("searchpowerUrl", "queryDsl"))
                .thenReturn(Arrays.asList(new JSONObject(0, false)));

        // Run the test
        final List<Map<String, String>> result = brmResourceServiceUnderTest.findBRMzoneByName(brmResource);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindBRMzoneByName_ConstantServiceReturnsNoItems() {
        // Setup
        final BRMResourceVO brmResource = new BRMResourceVO();
        brmResource.setId("id");
        brmResource.setLeaderid("leaderid");
        brmResource.setChiefZone("chiefZone");
        brmResource.setBrmZone("brmZone");
        brmResource.setBrmDepartment("brmDepartment");
        brmResource.setBrmGroup("brmGroup");
        brmResource.setProfile("profile");
        brmResource.setIsusing("isusing");
        brmResource.setName("name");

        final List<Map<String, String>> expectedResult = Arrays.asList(new HashMap<>());
        when(mockConstantService.getSourceByCondition("searchpowerUrl", "queryDsl"))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<Map<String, String>> result = brmResourceServiceUnderTest.findBRMzoneByName(brmResource);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testShowTypeToZone() {
        // Setup
        final BRMZoneReturnVO vo = new BRMZoneReturnVO();
        vo.setBrmZone("brmZone");
        vo.setLeaderid("leaderid");
        vo.setChiefZone("chiefZone");

        // Run the test
        final List<BRMZoneDTO> result = brmResourceServiceUnderTest.showTypeToZone(vo);

        // Verify the results
    }

    @Test
    void testCompareToSort() {
        // Setup
        final BRMZoneDTO brmZoneDTO = new BRMZoneDTO();
        final BRMZone brmZone1 = new BRMZone();
        brmZone1.setBrmZone("brmZone");
        brmZoneDTO.setBrmZone(Arrays.asList(brmZone1));
        brmZoneDTO.setProjectZoneType("projectZoneType");
        final List<BRMZoneDTO> brmZone = Arrays.asList(brmZoneDTO);

        // Run the test
        brmResourceServiceUnderTest.compareToSort(brmZone);

        // Verify the results
    }

    @Test
    void testIsChineseNumber() {
        assertThat(brmResourceServiceUnderTest.isChineseNumber("str")).isFalse();
    }
}
