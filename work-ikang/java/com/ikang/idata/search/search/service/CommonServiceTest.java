package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.vo.TelemarketingVo;
import com.ikang.idata.search.search.config.TelemarketingProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommonServiceTest {

    @Mock
    private TelemarketingProperties mockTelemarketingProperties;
    @Mock
    private ApplicationContext mockContext;

    @InjectMocks
    private CommonService commonServiceUnderTest;

    @Test
    void testQueryPhoneNumber() throws Exception {
        // Setup
        final TelemarketingVo vo = new TelemarketingVo();
        vo.setServiceName("serviceName");
        vo.setPhoneNumberTypeField("phoneNumberTypeField");
        vo.setConditionRecord("conditionRecord");
        vo.setProjectName("projectName");
        vo.setProjectDescribe("projectDescribe");
        vo.setOriginalQueryConditions(new JSONObject(0, false));
        vo.setMenuName("menuName");

        when(mockContext.getBean("serviceName")).thenReturn("result");

        // Configure TelemarketingProperties.getServiceConfig(...).
        final TelemarketingProperties.UrlConfig urlConfig = new TelemarketingProperties.UrlConfig();
        urlConfig.setSearchScrollUrl("searchScrollUrl");
        urlConfig.setScrollidKeeptime("scrollidKeeptime");
        urlConfig.setScrollUrl("scrollUrl");
        when(mockTelemarketingProperties.getServiceConfig("serviceName")).thenReturn(urlConfig);

        // Run the test
        final Map<String, Object> result = commonServiceUnderTest.queryPhoneNumber(vo, 0L, "serviceName");

        // Verify the results
    }

    @Test
    void testQueryPhoneNumber_ApplicationContextThrowsBeansException() {
        // Setup
        final TelemarketingVo vo = new TelemarketingVo();
        vo.setServiceName("serviceName");
        vo.setPhoneNumberTypeField("phoneNumberTypeField");
        vo.setConditionRecord("conditionRecord");
        vo.setProjectName("projectName");
        vo.setProjectDescribe("projectDescribe");
        vo.setOriginalQueryConditions(new JSONObject(0, false));
        vo.setMenuName("menuName");

        when(mockContext.getBean("serviceName")).thenThrow(BeansException.class);

        // Run the test
        assertThatThrownBy(() -> commonServiceUnderTest.queryPhoneNumber(vo, 0L, "serviceName"))
                .isInstanceOf(BeansException.class);
    }
}
