package com.ikang.idata.search.search.aop;

import com.ikang.idata.common.annotation.DataAuthType;
import com.ikang.idata.common.entity.UserArea;
import com.ikang.idata.common.entity.UserChannel;
import com.ikang.idata.common.entity.UserThird;
import com.ikang.idata.common.entity.vo.JobDistrictHeadArea;
import com.ikang.idata.common.entity.vo.UserChannelAndHospital;
import com.ikang.idata.common.utils.Result;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

class SaleAreaHandlerTest {

//    private SaleAreaHandler saleAreaHandlerUnderTest;

//    @BeforeEach
//    void setUp() throws Exception {
//        saleAreaHandlerUnderTest = new SaleAreaHandler();
//    }

    @Test
    void testHandleRequest() {
        // Setup
        final UserChannelAndHospital allAuthData = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        allAuthData.setJobDistrictHeadArea(jobDistrictHeadArea);
        allAuthData.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        allAuthData.setChannels(Arrays.asList(userChannel));
        final UserArea userArea = new UserArea();
        userArea.setAreaCode("areaCode");
        allAuthData.setUserAreaList(Arrays.asList(userArea));

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
//        saleAreaHandlerUnderTest.handleRequest(allAuthData, "arg", field, annotation, userThird);

        // Verify the results
    }
}
