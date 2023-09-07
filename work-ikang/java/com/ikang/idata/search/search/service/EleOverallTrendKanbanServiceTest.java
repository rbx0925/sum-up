package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONArray;
import com.ikang.idata.common.entity.EleOverallTrendFilm;
import com.ikang.idata.common.entity.vo.EleOverallTrendResult;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class EleOverallTrendKanbanServiceTest {

    private EleOverallTrendKanbanService eleOverallTrendKanbanServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        eleOverallTrendKanbanServiceUnderTest = new EleOverallTrendKanbanService();
    }

    @Test
    void testGetTime() {
        // Setup
        final EleOverallTrendFilm.Piece piece = new EleOverallTrendFilm.Piece();
        piece.setHeader("header");
        piece.setValue("value");
        piece.setDate("date");
        piece.setUnit("unit");
        piece.setRingRatioType("ringRatioType");
        piece.setRingRatio("ringRatio");
        piece.setYearOnYearType("yearOnYearType");
        piece.setYearOnYear("yearOnYear");
        piece.setRemark("remark");
        final EleOverallTrendResult expectedResult = new EleOverallTrendResult(Arrays.asList(
                new EleOverallTrendFilm("typeName", Arrays.asList(piece), "purchaseChannelCode", "purchaseDate",
                        "month", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))), Arrays.asList(new HashMap<>()));

        // Run the test
        final EleOverallTrendResult result = eleOverallTrendKanbanServiceUnderTest.getTime(Arrays.asList("value"),
                "purchaseDateStart", "purchaseDateEnd");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetQueryBuilder() throws Exception {
        // Setup
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = eleOverallTrendKanbanServiceUnderTest.getQueryBuilder(Arrays.asList("value"),
                "start", "end");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testInvokeAndGetResult() {
        // Setup
        final EleOverallTrendFilm.Piece piece = new EleOverallTrendFilm.Piece();
        piece.setHeader("header");
        piece.setValue("value");
        piece.setDate("date");
        piece.setUnit("unit");
        piece.setRingRatioType("ringRatioType");
        piece.setRingRatio("ringRatio");
        piece.setYearOnYearType("yearOnYearType");
        piece.setYearOnYear("yearOnYear");
        piece.setRemark("remark");
        final EleOverallTrendResult expectedResult = new EleOverallTrendResult(Arrays.asList(
                new EleOverallTrendFilm("typeName", Arrays.asList(piece), "purchaseChannelCode", "purchaseDate",
                        "month", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))), Arrays.asList(new HashMap<>()));

        // Run the test
        final EleOverallTrendResult result = eleOverallTrendKanbanServiceUnderTest.invokeAndGetResult("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testParseResult() {
        // Setup
        final JSONArray hits = new JSONArray(Arrays.asList("value"));
        final EleOverallTrendFilm.Piece piece = new EleOverallTrendFilm.Piece();
        piece.setHeader("header");
        piece.setValue("value");
        piece.setDate("date");
        piece.setUnit("unit");
        piece.setRingRatioType("ringRatioType");
        piece.setRingRatio("ringRatio");
        piece.setYearOnYearType("yearOnYearType");
        piece.setYearOnYear("yearOnYear");
        piece.setRemark("remark");
        final EleOverallTrendResult expectedResult = new EleOverallTrendResult(Arrays.asList(
                new EleOverallTrendFilm("typeName", Arrays.asList(piece), "purchaseChannelCode", "purchaseDate",
                        "month", new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"),
                        new BigDecimal("0.00"))), Arrays.asList(new HashMap<>()));

        // Run the test
        final EleOverallTrendResult result = eleOverallTrendKanbanServiceUnderTest.parseResult(hits);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSetData() {
        // Setup
        final EleOverallTrendFilm.Piece piece = new EleOverallTrendFilm.Piece();
        piece.setHeader("header");
        piece.setValue("value");
        piece.setDate("date");
        piece.setUnit("unit");
        piece.setRingRatioType("ringRatioType");
        piece.setRingRatio("ringRatio");
        piece.setYearOnYearType("yearOnYearType");
        piece.setYearOnYear("yearOnYear");
        piece.setRemark("remark");
        final EleOverallTrendFilm expectedResult = new EleOverallTrendFilm("typeName", Arrays.asList(piece),
                "purchaseChannelCode", "purchaseDate", "month", new BigDecimal("0.00"), new BigDecimal("0.00"),
                new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"),
                new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"),
                new BigDecimal("0.00"));

        // Run the test
        final EleOverallTrendFilm result = eleOverallTrendKanbanServiceUnderTest.setData("hit");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetMonth() {
        assertThat(eleOverallTrendKanbanServiceUnderTest.getMonth("date")).isEqualTo("result");
    }
}
