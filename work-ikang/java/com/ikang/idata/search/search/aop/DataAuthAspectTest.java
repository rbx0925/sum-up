package com.ikang.idata.search.search.aop;

import com.ikang.idata.common.annotation.DataAuth;
import com.ikang.idata.common.entity.UserThird;
import com.ikang.idata.common.entity.vo.JobDistrictHeadArea;
import com.ikang.idata.common.entity.vo.UserChannelAndHospital;
import com.ikang.idata.common.utils.Result;
import com.ikang.idata.search.search.feign.system.AuthorityFeignService;
import com.ikang.idata.search.search.feign.system.SystemUserFeignService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataAuthAspectTest {

    @Mock
    private AuthorityFeignService mockAuthorityFeignService;
    @Mock
    private SystemUserFeignService mockSystemUserFeignService;

//    @InjectMocks
//    private DataAuthAspect dataAuthAspectUnderTest;

    @Test
    void testDoAround() throws Throwable {
        // Setup
        final ProceedingJoinPoint point = null;
        final DataAuth dataAuth = null;

        // Configure AuthorityFeignService.getChannelOrHospitals(...).
        final Result<UserChannelAndHospital> userChannelAndHospitalResult = new Result<>();
        userChannelAndHospitalResult.setCode(0);
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setPermissionFlag(0);
        userChannelAndHospital.setDataFlags(Arrays.asList(0));
        userChannelAndHospitalResult.setData(userChannelAndHospital);
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospitalResult);

        // Configure SystemUserFeignService.getUserThirdByUserIdAndCode(...).
        final Result<UserThird> userThirdResult = new Result<>();
        userThirdResult.setCode(0);
        userThirdResult.setMsg("msg");
        final UserThird userThird = new UserThird();
        userThird.setId(0L);
        userThird.setUserId(0L);
        userThirdResult.setData(userThird);
        when(mockSystemUserFeignService.getUserThirdByUserIdAndCode("brm", 0L)).thenReturn(userThirdResult);

        // Run the test
//        final Object result = dataAuthAspectUnderTest.doAround(point, dataAuth);

        // Verify the results
    }
}
