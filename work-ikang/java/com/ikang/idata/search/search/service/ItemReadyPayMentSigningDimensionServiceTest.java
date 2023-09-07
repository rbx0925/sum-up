package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.vo.ItemReadyPayMentSigningDimensionVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemReadyPayMentSigningDimensionServiceTest {

    private ItemReadyPayMentSigningDimensionService itemReadyPayMentSigningDimensionServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        itemReadyPayMentSigningDimensionServiceUnderTest = new ItemReadyPayMentSigningDimensionService();
    }

    @Test
    void testAssemblyConditions() throws Exception {
        assertThat(
                itemReadyPayMentSigningDimensionServiceUnderTest.assemblyConditions(new JSONObject(0, false))).isNull();
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final ItemReadyPayMentSigningDimensionVO vo = new ItemReadyPayMentSigningDimensionVO();
        //vo.setResourceId(0L);
        //vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        //vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setBrmUserId("brmUserId");
        vo.setChiefZone("chiefZone");
        vo.setBrmZone("brmZone");
        vo.setBrmDepartment("brmDepartment");
        vo.setBrmGroup("brmGroup");
        //vo.setYewuxiantiao("yewuxiantiao");
        vo.setNewoldtype("newoldtype");
        vo.setXmksrqStartDate("xmksrqStartDate");
        vo.setXmksrqEndDate("xmksrqEndDate");

        // Run the test
        //final BaseSearch result = itemReadyPayMentSigningDimensionServiceUnderTest.find(vo);

        // Verify the results
    }
}
