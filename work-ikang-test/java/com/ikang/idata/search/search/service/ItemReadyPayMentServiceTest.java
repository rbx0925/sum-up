package com.ikang.idata.search.search.service;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.dto.ItemReadyPayMentDTO;
import com.ikang.idata.common.entity.vo.ItemReadyPayMentVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemReadyPayMentServiceTest {

    private ItemReadyPayMentService itemReadyPayMentServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        itemReadyPayMentServiceUnderTest = new ItemReadyPayMentService();
    }

    @Test
    void testAssemblyConditions() throws Exception {
        assertThat(itemReadyPayMentServiceUnderTest.assemblyConditions(new JSONObject(0, false))).isNull();
    }

    @Test
    void testFind111() {
        // Setup
        final ItemReadyPayMentVO vo = new ItemReadyPayMentVO();
        vo.setResourceId(0L);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setChiefZone("chiefZone");
        vo.setXmksrqStartDate("xmksrqStartDate");
        vo.setXmksrqEndDate("xmksrqEndDate");

        final ItemReadyPayMentDTO itemReadyPayMentDTO = new ItemReadyPayMentDTO();
        itemReadyPayMentDTO.setSsdq("ssdq");
        itemReadyPayMentDTO.setTotalItemMoney(new BigDecimal("0.00"));
        itemReadyPayMentDTO.setReadyItemMoney(new BigDecimal("0.00"));
        itemReadyPayMentDTO.setPactValue(new BigDecimal("0.00"));
        itemReadyPayMentDTO.setPromiseReadyMoney(new BigDecimal("0.00"));
        itemReadyPayMentDTO.setPromiseReadyValue(new BigDecimal("0.00"));
        itemReadyPayMentDTO.setPromiseRealMoney(new BigDecimal("0.00"));
        itemReadyPayMentDTO.setRealValue(new BigDecimal("0.00"));
        itemReadyPayMentDTO.setPlanReach(new BigDecimal("0.00"));
        itemReadyPayMentDTO.setRealPromiseValue(new BigDecimal("0.00"));
        final List<ItemReadyPayMentDTO> expectedResult = Arrays.asList(itemReadyPayMentDTO);

        // Run the test
//        final List<ItemReadyPayMentDTO> result = itemReadyPayMentServiceUnderTest.find111(vo);

        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final ItemReadyPayMentVO vo = new ItemReadyPayMentVO();
        vo.setResourceId(0L);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setChiefZone("chiefZone");
        vo.setXmksrqStartDate("xmksrqStartDate");
        vo.setXmksrqEndDate("xmksrqEndDate");

        // Run the test
        final BaseSearch result = itemReadyPayMentServiceUnderTest.find(vo);

        // Verify the results
    }
}
