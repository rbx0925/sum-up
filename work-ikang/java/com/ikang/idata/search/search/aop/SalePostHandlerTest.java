package com.ikang.idata.search.search.aop;

import com.ikang.idata.common.annotation.DataAuthType;
import com.ikang.idata.common.entity.UserChannel;
import com.ikang.idata.common.entity.UserPost;
import com.ikang.idata.common.entity.UserThird;
import com.ikang.idata.common.entity.vo.*;
import com.ikang.idata.common.utils.Result;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

class SalePostHandlerTest {

//    private SalePostHandler salePostHandlerUnderTest;

//    @BeforeEach
//    void setUp() throws Exception {
//        salePostHandlerUnderTest = new SalePostHandler();
//    }

    @Test
    void testHandleRequest() {
        // Setup
        final UserChannelAndHospital allAuthData = new UserChannelAndHospital();
        final BRMTypeToChiefZone brmTypeToChiefZone = new BRMTypeToChiefZone();
        final BRMZone brmZone = new BRMZone();
        brmZone.setBrmZone("brmZone");
        brmTypeToChiefZone.setBrmZone(Arrays.asList(brmZone));
        brmTypeToChiefZone.setProjectZoneType("projectZoneType");
        final BrmChiefZone brmChiefZone = new BrmChiefZone();
        brmChiefZone.setChiefZone("chiefZone");
        brmTypeToChiefZone.setChiefZone(Arrays.asList(brmChiefZone));
        allAuthData.setBrmTypeToChiefZones(Arrays.asList(brmTypeToChiefZone));
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        allAuthData.setJobDistrictHeadArea(jobDistrictHeadArea);
        allAuthData.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        allAuthData.setChannels(Arrays.asList(userChannel));
        final UserPost userPost = new UserPost();
        userPost.setPostCode("postCode");
        allAuthData.setUserPostList(Arrays.asList(userPost));

        final Field field = null;
        final DataAuthType annotation = null;
        final Result<UserThird> userThird = new Result<>();
        userThird.setCode(0);
        userThird.setMsg("msg");
        final UserThird userThird1 = new UserThird();
        userThird1.setId(0L);
        userThird1.setUserId(0L);
        userThird1.setThirdCode("thirdCode");
        userThird1.setThirdId("thirdId");
        userThird.setData(userThird1);

        // Run the test
//        salePostHandlerUnderTest.handleRequest(allAuthData, "arg", field, annotation, userThird);

        // Verify the results
    }
}
