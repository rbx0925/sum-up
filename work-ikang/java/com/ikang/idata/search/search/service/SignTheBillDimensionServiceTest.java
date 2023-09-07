package com.ikang.idata.search.search.service;

import com.ikang.idata.common.entity.SignTheBillDimensionCount;
import com.ikang.idata.common.entity.dto.SignTheBillDimensionDTO;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SignTheBillDimensionServiceTest {

    private SignTheBillDimensionService signTheBillDimensionServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        signTheBillDimensionServiceUnderTest = new SignTheBillDimensionService();
    }

    @Test
    void testMakeDate1() {
        // Setup
        final WorkTableSearchVo orderInfoSearchVo = new WorkTableSearchVo();
        orderInfoSearchVo.setBrmZone("brmZone");
        orderInfoSearchVo.setBrmDepartment("brmDepartment");
        orderInfoSearchVo.setBrmGroup("brmGroup");
        orderInfoSearchVo.setStartSignDate("startSignDate");
        orderInfoSearchVo.setEndSignDate("endSignDate");
        orderInfoSearchVo.setBrmUserId("brmUserId");
        orderInfoSearchVo.setChiefZone("chiefZone");

        final SignTheBillDimensionDTO expectedResult = new SignTheBillDimensionDTO(Arrays.asList(
                new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));

        // Run the test
        final SignTheBillDimensionDTO result = signTheBillDimensionServiceUnderTest.makeDate(orderInfoSearchVo, "type");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testMakeDate2() {
        // Setup
        final WorkTableSearchVo orderInfoSearchVo = new WorkTableSearchVo();
        orderInfoSearchVo.setBrmZone("brmZone");
        orderInfoSearchVo.setBrmDepartment("brmDepartment");
        orderInfoSearchVo.setBrmGroup("brmGroup");
        orderInfoSearchVo.setStartSignDate("startSignDate");
        orderInfoSearchVo.setEndSignDate("endSignDate");
        orderInfoSearchVo.setBrmUserId("brmUserId");
        orderInfoSearchVo.setChiefZone("chiefZone");

        final SignTheBillDimensionDTO expectedResult = new SignTheBillDimensionDTO(Arrays.asList(
                new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));

        // Run the test
        final SignTheBillDimensionDTO result = signTheBillDimensionServiceUnderTest.makeDate(orderInfoSearchVo, "type",
                false);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testDoMakeDate() throws Exception {
        // Setup
        final SearchSourceBuilder builder = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));
        final SignTheBillDimensionDTO expectedResult = new SignTheBillDimensionDTO(Arrays.asList(
                new SignTheBillDimensionCount("name", 0L, new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))));

        // Run the test
        final SignTheBillDimensionDTO result = signTheBillDimensionServiceUnderTest.doMakeDate("type", builder);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCountSignTheBillDimensionCountData() {
        // Setup
        final LinkedHashMap<String, LinkedHashMap<String, List>> dataMap = new LinkedHashMap<>();
        final HashMap<String, Map<String, SignTheBillDimensionCount>> expectedResult = new HashMap<>();

        // Run the test
        final HashMap<String, Map<String, SignTheBillDimensionCount>> result = signTheBillDimensionServiceUnderTest.countSignTheBillDimensionCountData(
                dataMap, "type");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateSignTheBillDimensionCountMap() {
        // Setup
        final HashMap<String, SignTheBillDimensionCount> signTheBillDimensionCountMap = new HashMap<>();
        final List<LinkedHashMap<String, Object>> dataList = Arrays.asList(new LinkedHashMap<>());

        // Run the test
        signTheBillDimensionServiceUnderTest.updateSignTheBillDimensionCountMap(signTheBillDimensionCountMap, dataList);

        // Verify the results
    }
}
