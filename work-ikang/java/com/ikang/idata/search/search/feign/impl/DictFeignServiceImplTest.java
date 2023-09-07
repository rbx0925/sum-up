package com.ikang.idata.search.search.feign.impl;

import com.ikang.idata.common.entity.DictInfo;
import com.ikang.idata.common.utils.Result;
import com.ikang.idata.search.search.feign.system.DictFeignService;
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
class DictFeignServiceImplTest {

    @Mock
    private DictFeignService mockDictFeignService;

    @InjectMocks
    private DictFeignServiceImpl dictFeignServiceImplUnderTest;

    @Test
    void testFindByTypeList() {
        // Setup
        // Configure DictFeignService.findByTypeList(...).
        final Result<List<DictInfo>> listResult = new Result<>();
        listResult.setCode(0);
        listResult.setMsg("msg");
        final DictInfo dictInfo = new DictInfo();
        dictInfo.setId(0L);
        dictInfo.setCode("code");
        dictInfo.setValue("value");
        dictInfo.setType("type");
        dictInfo.setDescription("description");
        dictInfo.setPid(0L);
        dictInfo.setSort(0);
        dictInfo.setDeleteStatus(0);
        dictInfo.setCreateBy(0L);
        dictInfo.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        dictInfo.setUpdateBy(0L);
        dictInfo.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        listResult.setData(Arrays.asList(dictInfo));
        when(mockDictFeignService.findByTypeList(Arrays.asList("value"))).thenReturn(listResult);

        // Run the test
        final List<DictInfo> result = dictFeignServiceImplUnderTest.findByTypeList(Arrays.asList("value"));

        // Verify the results
    }
}
