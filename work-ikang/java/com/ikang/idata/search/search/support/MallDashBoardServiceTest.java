package com.ikang.idata.search.search.support;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.ElectricityBoardFilm;
import com.ikang.idata.common.entity.es.ElectricityBoardFilmData;
import com.ikang.idata.common.entity.es.ElectricityBoardFilmHits;
import com.ikang.idata.common.entity.vo.ElectricityBoardVo;
import com.ikang.idata.common.entity.vo.PieceVO;
import com.ikang.idata.search.search.service.ESHttpClientService;
import com.ikang.idata.search.search.service.MallDashBoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MallDashBoardServiceTest {

    @Mock
    private RestTemplate mockRestTemplate;
    @Mock
    private ESHttpClientService mockEsHttpClientService;

    @InjectMocks
    private MallDashBoardService mallDashBoardServiceUnderTest;

    @Test
    void testFind() throws Exception {
        // Setup
        final ElectricityBoardVo electricityBoardVo = new ElectricityBoardVo();
        electricityBoardVo.setPurchaseChannelCode("purchaseChannelCode");
        electricityBoardVo.setPurchaseDate("date");
        electricityBoardVo.setSaleAmount("saleAmount");
        electricityBoardVo.setIncomeAmount("incomeAmount");
        electricityBoardVo.setCancelAmount("cancelAmount");
        electricityBoardVo.setSaleNum("saleNum");
        electricityBoardVo.setSaleCardPrice("saleCardPrice");
        electricityBoardVo.setSalePhone("salePhone");
        electricityBoardVo.setPersonalNum("personalNum");
        electricityBoardVo.setSalePhonePrice("salePhonePrice");
        electricityBoardVo.setRegNum("regNum");
        electricityBoardVo.setCheckNum("checkNum");
        electricityBoardVo.setReportNum("reportNum");

        final PieceVO pieceVO = new PieceVO();
        pieceVO.setHeader("件单价");
        pieceVO.setValue("value");
        pieceVO.setDate("date");
        pieceVO.setUnit("件");
        pieceVO.setRingRatioType("ringRatioType");
        pieceVO.setRingRatio("ringRatio");
        pieceVO.setYearOnYearType("yearOnYearType");
        pieceVO.setYearOnYear("yearOnYear");
        pieceVO.setRemark("注：商品数量根据卡的数量统计");
        final List<ElectricityBoardFilm> expectedResult = Arrays.asList(
                new ElectricityBoardFilm("typeName", Arrays.asList(pieceVO), "purchaseChannelCode", "purchaseDate",
                        "saleAmount", "incomeAmount", "cancelAmount", "saleNum", "saleCardPrice", "salePhone",
                        "personalNum", "salePhonePrice", "regNum", "checkNum", "reportNum"));

        // Configure ESHttpClientService.exchange(...).
        final ElectricityBoardFilmData electricityBoardFilmData = new ElectricityBoardFilmData();
        final ElectricityBoardFilmHits electricityBoardFilmHits = new ElectricityBoardFilmHits();
        final ElectricityBoardFilm source = new ElectricityBoardFilm();
        source.setTypeName("交易数据");
        final PieceVO pieceVO1 = new PieceVO();
        pieceVO1.setHeader("件单价");
        pieceVO1.setValue("value");
        pieceVO1.setDate("date");
        pieceVO1.setUnit("件");
        pieceVO1.setRingRatioType("ringRatioType");
        pieceVO1.setRingRatio("ringRatio");
        pieceVO1.setYearOnYearType("yearOnYearType");
        pieceVO1.setYearOnYear("yearOnYear");
        pieceVO1.setRemark("注：商品数量根据卡的数量统计");
        source.setPieces(Arrays.asList(pieceVO1));
        source.setPurchaseDate("purchaseDate");
        source.setSaleAmount("saleAmount");
        source.setIncomeAmount("incomeAmount");
        source.setCancelAmount("cancelAmount");
        source.setSaleNum("saleNum");
        source.setSaleCardPrice("saleCardPrice");
        source.setSalePhone("salePhone");
        source.setPersonalNum("personalNum");
        source.setSalePhonePrice("salePhonePrice");
        source.setRegNum("regNum");
        source.setCheckNum("checkNum");
        source.setReportNum("reportNum");
        electricityBoardFilmHits.setSource(source);
        electricityBoardFilmData.setCoverHits(Arrays.asList(electricityBoardFilmHits));
        when(mockEsHttpClientService.exchange("electricityboardUrl", HttpMethod.POST, ElectricityBoardFilmData.class,
                "queryDsl")).thenReturn(electricityBoardFilmData);

        // Run the test
        final List<ElectricityBoardFilm> result = mallDashBoardServiceUnderTest.find(electricityBoardVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind1() {
        // Setup
        final ElectricityBoardVo electricityBoardVo = new ElectricityBoardVo();
        electricityBoardVo.setPurchaseChannelCode("purchaseChannelCode");
        electricityBoardVo.setPurchaseDate("date");
        electricityBoardVo.setSaleAmount("saleAmount");
        electricityBoardVo.setIncomeAmount("incomeAmount");
        electricityBoardVo.setCancelAmount("cancelAmount");
        electricityBoardVo.setSaleNum("saleNum");
        electricityBoardVo.setSaleCardPrice("saleCardPrice");
        electricityBoardVo.setSalePhone("salePhone");
        electricityBoardVo.setPersonalNum("personalNum");
        electricityBoardVo.setSalePhonePrice("salePhonePrice");
        electricityBoardVo.setRegNum("regNum");
        electricityBoardVo.setCheckNum("checkNum");
        electricityBoardVo.setReportNum("reportNum");

        final PieceVO pieceVO = new PieceVO();
        pieceVO.setHeader("件单价");
        pieceVO.setValue("value");
        pieceVO.setDate("date");
        pieceVO.setUnit("件");
        pieceVO.setRingRatioType("ringRatioType");
        pieceVO.setRingRatio("ringRatio");
        pieceVO.setYearOnYearType("yearOnYearType");
        pieceVO.setYearOnYear("yearOnYear");
        pieceVO.setRemark("注：商品数量根据卡的数量统计");
        final List<ElectricityBoardFilm> expectedResult = Arrays.asList(
                new ElectricityBoardFilm("typeName", Arrays.asList(pieceVO), "purchaseChannelCode", "purchaseDate",
                        "saleAmount", "incomeAmount", "cancelAmount", "saleNum", "saleCardPrice", "salePhone",
                        "personalNum", "salePhonePrice", "regNum", "checkNum", "reportNum"));
        when(mockRestTemplate.postForObject(eq("electricityboardUrl"), eq("queryDsl"), eq(JSONObject.class),
                any(Object.class))).thenReturn(new JSONObject(0, false));

        // Run the test
        final List<ElectricityBoardFilm> result = mallDashBoardServiceUnderTest.find1(electricityBoardVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind1_RestTemplateReturnsNull() {
        // Setup
        final ElectricityBoardVo electricityBoardVo = new ElectricityBoardVo();
        electricityBoardVo.setPurchaseChannelCode("purchaseChannelCode");
        electricityBoardVo.setPurchaseDate("date");
        electricityBoardVo.setSaleAmount("saleAmount");
        electricityBoardVo.setIncomeAmount("incomeAmount");
        electricityBoardVo.setCancelAmount("cancelAmount");
        electricityBoardVo.setSaleNum("saleNum");
        electricityBoardVo.setSaleCardPrice("saleCardPrice");
        electricityBoardVo.setSalePhone("salePhone");
        electricityBoardVo.setPersonalNum("personalNum");
        electricityBoardVo.setSalePhonePrice("salePhonePrice");
        electricityBoardVo.setRegNum("regNum");
        electricityBoardVo.setCheckNum("checkNum");
        electricityBoardVo.setReportNum("reportNum");

        final PieceVO pieceVO = new PieceVO();
        pieceVO.setHeader("件单价");
        pieceVO.setValue("value");
        pieceVO.setDate("date");
        pieceVO.setUnit("件");
        pieceVO.setRingRatioType("ringRatioType");
        pieceVO.setRingRatio("ringRatio");
        pieceVO.setYearOnYearType("yearOnYearType");
        pieceVO.setYearOnYear("yearOnYear");
        pieceVO.setRemark("注：商品数量根据卡的数量统计");
        final List<ElectricityBoardFilm> expectedResult = Arrays.asList(
                new ElectricityBoardFilm("typeName", Arrays.asList(pieceVO), "purchaseChannelCode", "purchaseDate",
                        "saleAmount", "incomeAmount", "cancelAmount", "saleNum", "saleCardPrice", "salePhone",
                        "personalNum", "salePhonePrice", "regNum", "checkNum", "reportNum"));
        when(mockRestTemplate.postForObject(eq("electricityboardUrl"), eq("queryDsl"), eq(JSONObject.class),
                any(Object.class))).thenReturn(null);

        // Run the test
        final List<ElectricityBoardFilm> result = mallDashBoardServiceUnderTest.find1(electricityBoardVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind1_RestTemplateThrowsRestClientException() {
        // Setup
        final ElectricityBoardVo electricityBoardVo = new ElectricityBoardVo();
        electricityBoardVo.setPurchaseChannelCode("purchaseChannelCode");
        electricityBoardVo.setPurchaseDate("date");
        electricityBoardVo.setSaleAmount("saleAmount");
        electricityBoardVo.setIncomeAmount("incomeAmount");
        electricityBoardVo.setCancelAmount("cancelAmount");
        electricityBoardVo.setSaleNum("saleNum");
        electricityBoardVo.setSaleCardPrice("saleCardPrice");
        electricityBoardVo.setSalePhone("salePhone");
        electricityBoardVo.setPersonalNum("personalNum");
        electricityBoardVo.setSalePhonePrice("salePhonePrice");
        electricityBoardVo.setRegNum("regNum");
        electricityBoardVo.setCheckNum("checkNum");
        electricityBoardVo.setReportNum("reportNum");

        when(mockRestTemplate.postForObject(eq("electricityboardUrl"), eq("queryDsl"), eq(JSONObject.class),
                any(Object.class))).thenThrow(RestClientException.class);

        // Run the test
        assertThatThrownBy(() -> mallDashBoardServiceUnderTest.find1(electricityBoardVo))
                .isInstanceOf(RestClientException.class);
    }

    @Test
    void testInvokeAndGetResult1() {
        // Setup
        final PieceVO pieceVO = new PieceVO();
        pieceVO.setHeader("件单价");
        pieceVO.setValue("value");
        pieceVO.setDate("date");
        pieceVO.setUnit("件");
        pieceVO.setRingRatioType("ringRatioType");
        pieceVO.setRingRatio("ringRatio");
        pieceVO.setYearOnYearType("yearOnYearType");
        pieceVO.setYearOnYear("yearOnYear");
        pieceVO.setRemark("注：商品数量根据卡的数量统计");
        final List<ElectricityBoardFilm> expectedResult = Arrays.asList(
                new ElectricityBoardFilm("typeName", Arrays.asList(pieceVO), "purchaseChannelCode", "purchaseDate",
                        "saleAmount", "incomeAmount", "cancelAmount", "saleNum", "saleCardPrice", "salePhone",
                        "personalNum", "salePhonePrice", "regNum", "checkNum", "reportNum"));
        when(mockRestTemplate.postForObject(eq("electricityboardUrl"), eq("queryDsl"), eq(JSONObject.class),
                any(Object.class))).thenReturn(new JSONObject(0, false));

        // Run the test
        final List<ElectricityBoardFilm> result = mallDashBoardServiceUnderTest.invokeAndGetResult1("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testInvokeAndGetResult1_RestTemplateReturnsNull() {
        // Setup
        final PieceVO pieceVO = new PieceVO();
        pieceVO.setHeader("件单价");
        pieceVO.setValue("value");
        pieceVO.setDate("date");
        pieceVO.setUnit("件");
        pieceVO.setRingRatioType("ringRatioType");
        pieceVO.setRingRatio("ringRatio");
        pieceVO.setYearOnYearType("yearOnYearType");
        pieceVO.setYearOnYear("yearOnYear");
        pieceVO.setRemark("注：商品数量根据卡的数量统计");
        final List<ElectricityBoardFilm> expectedResult = Arrays.asList(
                new ElectricityBoardFilm("typeName", Arrays.asList(pieceVO), "purchaseChannelCode", "purchaseDate",
                        "saleAmount", "incomeAmount", "cancelAmount", "saleNum", "saleCardPrice", "salePhone",
                        "personalNum", "salePhonePrice", "regNum", "checkNum", "reportNum"));
        when(mockRestTemplate.postForObject(eq("electricityboardUrl"), eq("queryDsl"), eq(JSONObject.class),
                any(Object.class))).thenReturn(null);

        // Run the test
        final List<ElectricityBoardFilm> result = mallDashBoardServiceUnderTest.invokeAndGetResult1("queryDsl");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testInvokeAndGetResult1_RestTemplateThrowsRestClientException() {
        // Setup
        when(mockRestTemplate.postForObject(eq("electricityboardUrl"), eq("queryDsl"), eq(JSONObject.class),
                any(Object.class))).thenThrow(RestClientException.class);

        // Run the test
        assertThatThrownBy(() -> mallDashBoardServiceUnderTest.invokeAndGetResult1("queryDsl"))
                .isInstanceOf(RestClientException.class);
    }

    @Test
    void testInvokeAndGetResult() {
        // Setup
        final ElectricityBoardVo electricityBoardVo = new ElectricityBoardVo();
        electricityBoardVo.setPurchaseChannelCode("purchaseChannelCode");
        electricityBoardVo.setPurchaseDate("date");
        electricityBoardVo.setSaleAmount("saleAmount");
        electricityBoardVo.setIncomeAmount("incomeAmount");
        electricityBoardVo.setCancelAmount("cancelAmount");
        electricityBoardVo.setSaleNum("saleNum");
        electricityBoardVo.setSaleCardPrice("saleCardPrice");
        electricityBoardVo.setSalePhone("salePhone");
        electricityBoardVo.setPersonalNum("personalNum");
        electricityBoardVo.setSalePhonePrice("salePhonePrice");
        electricityBoardVo.setRegNum("regNum");
        electricityBoardVo.setCheckNum("checkNum");
        electricityBoardVo.setReportNum("reportNum");

        final PieceVO pieceVO = new PieceVO();
        pieceVO.setHeader("件单价");
        pieceVO.setValue("value");
        pieceVO.setDate("date");
        pieceVO.setUnit("件");
        pieceVO.setRingRatioType("ringRatioType");
        pieceVO.setRingRatio("ringRatio");
        pieceVO.setYearOnYearType("yearOnYearType");
        pieceVO.setYearOnYear("yearOnYear");
        pieceVO.setRemark("注：商品数量根据卡的数量统计");
        final List<ElectricityBoardFilm> expectedResult = Arrays.asList(
                new ElectricityBoardFilm("typeName", Arrays.asList(pieceVO), "purchaseChannelCode", "purchaseDate",
                        "saleAmount", "incomeAmount", "cancelAmount", "saleNum", "saleCardPrice", "salePhone",
                        "personalNum", "salePhonePrice", "regNum", "checkNum", "reportNum"));

        // Configure ESHttpClientService.exchange(...).
        final ElectricityBoardFilmData electricityBoardFilmData = new ElectricityBoardFilmData();
        final ElectricityBoardFilmHits electricityBoardFilmHits = new ElectricityBoardFilmHits();
        final ElectricityBoardFilm source = new ElectricityBoardFilm();
        source.setTypeName("交易数据");
        final PieceVO pieceVO1 = new PieceVO();
        pieceVO1.setHeader("件单价");
        pieceVO1.setValue("value");
        pieceVO1.setDate("date");
        pieceVO1.setUnit("件");
        pieceVO1.setRingRatioType("ringRatioType");
        pieceVO1.setRingRatio("ringRatio");
        pieceVO1.setYearOnYearType("yearOnYearType");
        pieceVO1.setYearOnYear("yearOnYear");
        pieceVO1.setRemark("注：商品数量根据卡的数量统计");
        source.setPieces(Arrays.asList(pieceVO1));
        source.setPurchaseDate("purchaseDate");
        source.setSaleAmount("saleAmount");
        source.setIncomeAmount("incomeAmount");
        source.setCancelAmount("cancelAmount");
        source.setSaleNum("saleNum");
        source.setSaleCardPrice("saleCardPrice");
        source.setSalePhone("salePhone");
        source.setPersonalNum("personalNum");
        source.setSalePhonePrice("salePhonePrice");
        source.setRegNum("regNum");
        source.setCheckNum("checkNum");
        source.setReportNum("reportNum");
        electricityBoardFilmHits.setSource(source);
        electricityBoardFilmData.setCoverHits(Arrays.asList(electricityBoardFilmHits));
        when(mockEsHttpClientService.exchange("electricityboardUrl", HttpMethod.POST, ElectricityBoardFilmData.class,
                "queryDsl")).thenReturn(electricityBoardFilmData);

        // Run the test
        final List<ElectricityBoardFilm> result = mallDashBoardServiceUnderTest.invokeAndGetResult("queryDsl",
                electricityBoardVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

}
