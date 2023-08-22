package com.ikang.idata.search.search.feign.impl;

import com.ikang.idata.common.entity.UserConfig;
import com.ikang.idata.common.utils.Result;
import com.ikang.idata.search.search.feign.system.SystemUserConfigFeignService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SystemUserConfigFeignServiceImplTest {

    @Mock
    private SystemUserConfigFeignService mockSystemUserConfigFeignService;

    @InjectMocks
    private SystemUserConfigFeignServiceImpl systemUserConfigFeignServiceImplUnderTest;

    @Test
    void testQueryUserConfig() {
        // Setup
        final UserConfig expectedResult = new UserConfig();
        expectedResult.setId(0L);
        expectedResult.setUserId(0L);
        expectedResult.setDepartmentId("departmentId");
        expectedResult.setMinMoney("minMoney");
        expectedResult.setMaxMoney("maxMoney");

        // Configure SystemUserConfigFeignService.queryUserConfig(...).
        final Result<UserConfig> userConfigResult = new Result<>();
        userConfigResult.setCode(0);
        userConfigResult.setMsg("msg");
        final UserConfig userConfig = new UserConfig();
        userConfig.setId(0L);
        userConfig.setUserId(0L);
        userConfig.setDepartmentId("departmentId");
        userConfig.setMinMoney("minMoney");
        userConfig.setMaxMoney("maxMoney");
        userConfigResult.setData(userConfig);
        when(mockSystemUserConfigFeignService.queryUserConfig(0L)).thenReturn(userConfigResult);

        // Run the test
        final UserConfig result = systemUserConfigFeignServiceImplUnderTest.queryUserConfig(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
