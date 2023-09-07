package com.ikang.idata.search.search.common;

import com.ikang.idata.common.entity.ReturnData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ComTest {

    private Com comUnderTest;

    @BeforeEach
    void setUp() {
        comUnderTest = new Com();
    }

    @Test
    void testValuePostProcessing() {
        // Setup
        final Map<String, Object> source = new HashMap<>();
        final ReturnData el = new ReturnData();
        el.setFieldDesc("fieldDesc");
        el.setSumOrAvgFlag(0);
        el.setId(0L);
        el.setReturnDataIndex("returnDataIndex");
        el.setReturnDataCode("returnDataCode");
        el.setReturnDataName("returnDataName");
        el.setDataType(0);
        el.setDataValue("dataValue");
        el.setReturnDataDeals(0);
        el.setReturnDataValue("returnDataValue");
        el.setShowFlag(0);
        el.setReturnDataMemo("returnDataMemo");
        el.setSort(0);
        el.setDeleteStatus(0);
        el.setCreateBy(0L);

        // Run the test
        final String result = comUnderTest.valuePostProcessing(source, el);

        // Verify the results
        assertThat(result).isEqualTo("");
    }
}
