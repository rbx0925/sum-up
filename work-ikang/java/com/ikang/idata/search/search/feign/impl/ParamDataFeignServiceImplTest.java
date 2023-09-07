package com.ikang.idata.search.search.feign.impl;

import com.ikang.idata.common.entity.ParamData;
import com.ikang.idata.common.entity.RoleResource;
import com.ikang.idata.common.utils.Result;
import com.ikang.idata.search.search.feign.system.ParamDataFeignService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParamDataFeignServiceImplTest {

    @Mock
    private ParamDataFeignService mockParamDataFeignService;

    @InjectMocks
    private ParamDataFeignServiceImpl paramDataFeignServiceImplUnderTest;

    @Test
    void testSelectParamDataByUserIdAndResourceId() {
        // Setup
        final ParamData paramData = new ParamData();
        paramData.setId(0L);
        paramData.setParamDataIndex("paramDataIndex");
        paramData.setParamDataCode("paramDataCode");
        paramData.setParamDataName("paramDataName");
        paramData.setDataType(0);
        paramData.setDataValue("dataValue");
        paramData.setParamDataDeals(0);
        paramData.setParamDataValue("paramDataValue");
        paramData.setParamDataAct(0);
        paramData.setPlaceHolder("placeHolder");
        paramData.setShowFlag(0);
        paramData.setFixedPage(0);
        paramData.setParamDataMemo("paramDataMemo");
        paramData.setSort(0);
        paramData.setOrderSort(0);
        final List<ParamData> expectedResult = Arrays.asList(paramData);

        // Configure ParamDataFeignService.selectParamDataByUserIdAndResourceId(...).
        final Result<List<ParamData>> listResult = new Result<>();
        listResult.setCode(0);
        listResult.setMsg("msg");
        final ParamData paramData1 = new ParamData();
        paramData1.setId(0L);
        paramData1.setParamDataIndex("paramDataIndex");
        paramData1.setParamDataCode("paramDataCode");
        paramData1.setParamDataName("paramDataName");
        paramData1.setDataType(0);
        paramData1.setDataValue("dataValue");
        paramData1.setParamDataDeals(0);
        paramData1.setParamDataValue("paramDataValue");
        paramData1.setParamDataAct(0);
        paramData1.setPlaceHolder("placeHolder");
        paramData1.setShowFlag(0);
        paramData1.setFixedPage(0);
        listResult.setData(Arrays.asList(paramData1));
        when(mockParamDataFeignService.selectParamDataByUserIdAndResourceId(0L, 0L)).thenReturn(listResult);

        // Run the test
        final List<ParamData> result = paramDataFeignServiceImplUnderTest.selectParamDataByUserIdAndResourceId(0L, 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSelectParamDataListByIndex() {
        // Setup
        final ParamData paramData = new ParamData();
        paramData.setId(0L);
        paramData.setParamDataIndex("paramDataIndex");
        paramData.setParamDataCode("paramDataCode");
        paramData.setParamDataName("paramDataName");
        paramData.setDataType(0);
        paramData.setDataValue("dataValue");
        paramData.setParamDataDeals(0);
        paramData.setParamDataValue("paramDataValue");
        paramData.setParamDataAct(0);
        paramData.setPlaceHolder("placeHolder");
        paramData.setShowFlag(0);
        paramData.setFixedPage(0);
        paramData.setParamDataMemo("paramDataMemo");
        paramData.setSort(0);
        paramData.setOrderSort(0);
        final List<ParamData> expectedResult = Arrays.asList(paramData);

        // Configure ParamDataFeignService.selectParamDataListByIndex(...).
        final Result<List<ParamData>> listResult = new Result<>();
        listResult.setCode(0);
        listResult.setMsg("msg");
        final ParamData paramData1 = new ParamData();
        paramData1.setId(0L);
        paramData1.setParamDataIndex("paramDataIndex");
        paramData1.setParamDataCode("paramDataCode");
        paramData1.setParamDataName("paramDataName");
        paramData1.setDataType(0);
        paramData1.setDataValue("dataValue");
        paramData1.setParamDataDeals(0);
        paramData1.setParamDataValue("paramDataValue");
        paramData1.setParamDataAct(0);
        paramData1.setPlaceHolder("placeHolder");
        paramData1.setShowFlag(0);
        paramData1.setFixedPage(0);
        listResult.setData(Arrays.asList(paramData1));
        when(mockParamDataFeignService.selectParamDataListByIndex("index")).thenReturn(listResult);

        // Run the test
        final List<ParamData> result = paramDataFeignServiceImplUnderTest.selectParamDataListByIndex("index");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testQueryPageButton() {
        // Setup
        final RoleResource expectedResult = new RoleResource();
        expectedResult.setId(0L);
        expectedResult.setResourceId(0L);
        expectedResult.setRoleId(0L);
        expectedResult.setDeleteStatus(0);
        expectedResult.setCreateBy(0L);
        expectedResult.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        expectedResult.setUpdateBy(0L);
        expectedResult.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        expectedResult.setExportExcel(0);
        expectedResult.setTelemarketing(0);
        expectedResult.setDesensitize(0);

        // Configure ParamDataFeignService.queryPageButton(...).
        final Result<RoleResource> roleResourceResult = new Result<>();
        roleResourceResult.setCode(0);
        roleResourceResult.setMsg("msg");
        final RoleResource roleResource = new RoleResource();
        roleResource.setId(0L);
        roleResource.setResourceId(0L);
        roleResource.setRoleId(0L);
        roleResource.setDeleteStatus(0);
        roleResource.setCreateBy(0L);
        roleResource.setCreateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        roleResource.setUpdateBy(0L);
        roleResource.setUpdateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        roleResource.setExportExcel(0);
        roleResource.setTelemarketing(0);
        roleResource.setDesensitize(0);
        roleResourceResult.setData(roleResource);
        when(mockParamDataFeignService.queryPageButton(0L, 0L)).thenReturn(roleResourceResult);

        // Run the test
        final RoleResource result = paramDataFeignServiceImplUnderTest.queryPageButton(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
