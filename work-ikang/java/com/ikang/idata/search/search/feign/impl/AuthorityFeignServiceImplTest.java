package com.ikang.idata.search.search.feign.impl;

import com.ikang.idata.common.entity.UserChannel;
import com.ikang.idata.common.entity.vo.*;
import com.ikang.idata.common.utils.Result;
import com.ikang.idata.search.search.feign.system.AuthorityFeignService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorityFeignServiceImplTest {

    @Mock
    private AuthorityFeignService mockAuthorityFeignService;

    @InjectMocks
    private AuthorityFeignServiceImpl authorityFeignServiceImplUnderTest;

    @Test
    void testGetChannelOrHospitals() {
        // Setup
        // Configure AuthorityFeignService.getChannelOrHospitals(...).
        final Result<UserChannelAndHospital> userChannelAndHospitalResult = new Result<>();
        userChannelAndHospitalResult.setCode(0);
        userChannelAndHospitalResult.setMsg("msg");
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
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospitalResult.setData(userChannelAndHospital);
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospitalResult);

        // Run the test
        final UserChannelAndHospital result = authorityFeignServiceImplUnderTest.getChannelOrHospitals(0L);

        // Verify the results
    }
}
