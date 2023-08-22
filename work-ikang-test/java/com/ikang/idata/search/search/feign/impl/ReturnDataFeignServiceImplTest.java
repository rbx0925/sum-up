package com.ikang.idata.search.search.feign.impl;

import com.ikang.idata.common.entity.ReturnData;
import com.ikang.idata.common.utils.Result;
import com.ikang.idata.search.search.feign.system.ReturnDataFeignService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReturnDataFeignServiceImplTest {

    @Mock
    private ReturnDataFeignService mockReturnDataFeignService;

    @InjectMocks
    private ReturnDataFeignServiceImpl returnDataFeignServiceImplUnderTest;

    @Test
    void testSelectAllByUserIDAndIndexAndTableAndResourceId() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("returnDataCode");
        returnData.setReturnDataName("returnDataName");
        returnData.setDataType(0);
        returnData.setDataValue("dataValue");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setReturnDataMemo("returnDataMemo");
        returnData.setSort(0);
        returnData.setDeleteStatus(0);
        returnData.setCreateBy(0L);
        final List<ReturnData> expectedResult = Arrays.asList(returnData);

        // Configure ReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(...).
        final Result<List<ReturnData>> listResult = new Result<>();
        listResult.setCode(0);
        listResult.setMsg("msg");
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("returnDataCode");
        returnData1.setReturnDataName("returnDataName");
        returnData1.setDataType(0);
        returnData1.setDataValue("dataValue");
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setReturnDataMemo("returnDataMemo");
        listResult.setData(Arrays.asList(returnData1));
        when(mockReturnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(0L, "tableName", 0L))
                .thenReturn(listResult);

        // Run the test
        final List<ReturnData> result = returnDataFeignServiceImplUnderTest.selectAllByUserIDAndIndexAndTableAndResourceId(
                0L, "tableName", 0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testListByReturnDataIndex() {
        // Setup
        final ReturnData returnData = new ReturnData();
        returnData.setFieldDesc("fieldDesc");
        returnData.setSumOrAvgFlag(0);
        returnData.setId(0L);
        returnData.setReturnDataIndex("returnDataIndex");
        returnData.setReturnDataCode("returnDataCode");
        returnData.setReturnDataName("returnDataName");
        returnData.setDataType(0);
        returnData.setDataValue("dataValue");
        returnData.setReturnDataDeals(0);
        returnData.setReturnDataValue("returnDataValue");
        returnData.setShowFlag(0);
        returnData.setReturnDataMemo("returnDataMemo");
        returnData.setSort(0);
        returnData.setDeleteStatus(0);
        returnData.setCreateBy(0L);
        final List<ReturnData> expectedResult = Arrays.asList(returnData);

        // Configure ReturnDataFeignService.listByReturnDataIndex(...).
        final Result<List<ReturnData>> listResult = new Result<>();
        listResult.setCode(0);
        listResult.setMsg("msg");
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("returnDataCode");
        returnData1.setReturnDataName("returnDataName");
        returnData1.setDataType(0);
        returnData1.setDataValue("dataValue");
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setReturnDataMemo("returnDataMemo");
        listResult.setData(Arrays.asList(returnData1));
        when(mockReturnDataFeignService.listByReturnDataIndex("returnDataIndex")).thenReturn(listResult);

        // Run the test
        final List<ReturnData> result = returnDataFeignServiceImplUnderTest.listByReturnDataIndex("returnDataIndex");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
