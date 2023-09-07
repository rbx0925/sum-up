package com.ikang.idata.search.search.feign.impl;

import com.ikang.idata.common.entity.User;
import com.ikang.idata.common.entity.UserCheckLine;
import com.ikang.idata.common.utils.Result;
import com.ikang.idata.search.search.feign.system.SystemUserFeignService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SystemUserFeignServiceImplTest {

    @Mock
    private SystemUserFeignService mockSystemUserFeignService;

    @InjectMocks
    private SystemUserFeignServiceImpl systemUserFeignServiceImplUnderTest;

    @Test
    void testSelectUserCheckLineList() {
        // Setup
        // Configure SystemUserFeignService.selectUserCheckLineList(...).
        final Result<List<UserCheckLine>> listResult = new Result<>();
        listResult.setCode(0);
        listResult.setMsg("msg");
        final UserCheckLine userCheckLine = new UserCheckLine();
        userCheckLine.setAreaCode("areaCode");
        userCheckLine.setAreaName("areaName");
        userCheckLine.setId(0L);
        userCheckLine.setUserId(0L);
        userCheckLine.setCreateBy(0L);
        userCheckLine.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userCheckLine.setUpdateBy(0L);
        userCheckLine.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userCheckLine.setDelFlag(0);
        userCheckLine.setHospitalCode("hospitalCode");
        userCheckLine.setHospitalName("hospitalName");
        userCheckLine.setCheckLineCode("checkLineCode");
        listResult.setData(Arrays.asList(userCheckLine));
        when(mockSystemUserFeignService.selectUserCheckLineList(0L)).thenReturn(listResult);

        // Run the test
        final List<UserCheckLine> result = systemUserFeignServiceImplUnderTest.selectUserCheckLineList(0L);

        // Verify the results
    }

    @Test
    void testGetById() {
        // Setup
        // Configure SystemUserFeignService.getById(...).
        final Result<User> userResult = new Result<>();
        userResult.setCode(0);
        userResult.setMsg("msg");
        final User user = new User();
        user.setId(0L);
        user.setRealName("realName");
        user.setUserNum("userNum");
        user.setUserName("userName");
        user.setPhoneNum("phoneNum");
        user.setRoles(Arrays.asList(0L));
        user.setDeleteStatus(0);
        user.setUserPassword("userPassword");
        user.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setSalt("salt");
        user.setPwdLastUpdatetime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        user.setNeedNewPwd(0);
        userResult.setData(user);
        when(mockSystemUserFeignService.getById(0L)).thenReturn(userResult);

        // Run the test
        final User result = systemUserFeignServiceImplUnderTest.getById(0L);

        // Verify the results
    }
}
