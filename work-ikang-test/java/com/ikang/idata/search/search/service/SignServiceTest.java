package com.ikang.idata.search.search.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.enums.DeferredEnum;
import com.ikang.idata.common.utils.CheckUtil;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.entity.OrderInfoEnum;
import com.ikang.idata.search.search.entity.RenewalOrderInfoEnum;
import com.ikang.idata.search.search.entity.vo.*;
import com.ikang.idata.search.search.util.HttpClient;
import com.ikang.idata.search.search.util.Stringutil;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregator;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ikang.idata.common.consts.MagicConst.*;
import static com.ikang.idata.search.search.service.SignService.OWNERID;
import static com.ikang.idata.search.search.service.SignService.SORT_FLAG;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SignServiceTest {

    private SignService signServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        signServiceUnderTest = new SignService();
    }

    @Test
    void testFindSignKanban() {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("yearOnYearTimeStart");
        searchVO.setEndSignDate("2023-04-03");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final SignResultVo expectedResult = new SignResultVo();
        expectedResult.setSignMoneySum(new BigDecimal("0.00"));
        expectedResult.setSignProjectIdCount(new BigDecimal("0.00"));
        expectedResult.setWorknosSum(new BigDecimal("0.00"));
        expectedResult.setAvgOrderMoney(new BigDecimal("0.00"));
        expectedResult.setAvgCustomerUnitPrice(new BigDecimal("0.00"));
        expectedResult.setAvgSignDiscount(new BigDecimal("0.00"));
        expectedResult.setSignMoneySumLastYear(new BigDecimal("0.00"));
        expectedResult.setSignProjectIdCountLastYear(new BigDecimal("0.00"));
        expectedResult.setWorknosSumLastYear(new BigDecimal("0.00"));
        expectedResult.setAvgOrderMoneyLastYear(new BigDecimal("0.00"));
        expectedResult.setAvgCustomerUnitPriceLastYear(new BigDecimal("0.00"));
        expectedResult.setAvgSignDiscountLastYear(new BigDecimal("0.00"));
        expectedResult.setNewSignMoneySum(new BigDecimal("0.00"));
        expectedResult.setNewSignProjectIdCount(new BigDecimal("0.00"));
        expectedResult.setNewAvgOrderMoney(new BigDecimal("0.00"));
        expectedResult.setNewAvgSignDiscount(new BigDecimal("0.00"));
        expectedResult.setNewSignMoneySumLastYear(new BigDecimal("0.00"));
        expectedResult.setNewSignProjectIdCountLastYear(new BigDecimal("0.00"));
        expectedResult.setNewAvgOrderMoneyLastYear(new BigDecimal("0.00"));
        expectedResult.setNewAvgSignDiscountLastYear(new BigDecimal("0.00"));
        expectedResult.setOldSignMoneySum(new BigDecimal("0.00"));
        expectedResult.setOldSignProjectIdCount(new BigDecimal("0.00"));
        expectedResult.setOldAvgOrderMoney(new BigDecimal("0.00"));
        expectedResult.setOldAvgSignDiscount(new BigDecimal("0.00"));
        expectedResult.setOldSignMoneySumLastYear(new BigDecimal("0.00"));
        expectedResult.setOldSignProjectIdCountLastYear(new BigDecimal("0.00"));
        expectedResult.setOldAvgOrderMoneyLastYear(new BigDecimal("0.00"));
        expectedResult.setOldAvgSignDiscountLastYear(new BigDecimal("0.00"));
        expectedResult.setRenews(new BigDecimal("0.00"));
        expectedResult.setRenewsLastYear(new BigDecimal("0.00"));
        expectedResult.setRenewMoney(new BigDecimal("0.00"));
        expectedResult.setRenewMoneyLastYear(new BigDecimal("0.00"));
        expectedResult.setSumUpRenews(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewsLastYear(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewMoney(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewMoneyLastYear(new BigDecimal("0.00"));

        // Run the test
        final SignResultVo result = signServiceUnderTest.findSignKanban(searchVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testClear() {
        assertThat(signServiceUnderTest.clear("s")).isEqualTo("result");
    }

    @Test
    void testFindSignKanbanSignDaily() {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("yearOnYearTimeStart");
        searchVO.setEndSignDate("2023-04-03");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final SignResultVo expectedResult = new SignResultVo();
        expectedResult.setSignMoneySum(new BigDecimal("0.00"));
        expectedResult.setSignProjectIdCount(new BigDecimal("0.00"));
        expectedResult.setWorknosSum(new BigDecimal("0.00"));
        expectedResult.setAvgOrderMoney(new BigDecimal("0.00"));
        expectedResult.setAvgCustomerUnitPrice(new BigDecimal("0.00"));
        expectedResult.setAvgSignDiscount(new BigDecimal("0.00"));
        expectedResult.setSignMoneySumLastYear(new BigDecimal("0.00"));
        expectedResult.setSignProjectIdCountLastYear(new BigDecimal("0.00"));
        expectedResult.setWorknosSumLastYear(new BigDecimal("0.00"));
        expectedResult.setAvgOrderMoneyLastYear(new BigDecimal("0.00"));
        expectedResult.setAvgCustomerUnitPriceLastYear(new BigDecimal("0.00"));
        expectedResult.setAvgSignDiscountLastYear(new BigDecimal("0.00"));
        expectedResult.setNewSignMoneySum(new BigDecimal("0.00"));
        expectedResult.setNewSignProjectIdCount(new BigDecimal("0.00"));
        expectedResult.setNewAvgOrderMoney(new BigDecimal("0.00"));
        expectedResult.setNewAvgSignDiscount(new BigDecimal("0.00"));
        expectedResult.setNewSignMoneySumLastYear(new BigDecimal("0.00"));
        expectedResult.setNewSignProjectIdCountLastYear(new BigDecimal("0.00"));
        expectedResult.setNewAvgOrderMoneyLastYear(new BigDecimal("0.00"));
        expectedResult.setNewAvgSignDiscountLastYear(new BigDecimal("0.00"));
        expectedResult.setOldSignMoneySum(new BigDecimal("0.00"));
        expectedResult.setOldSignProjectIdCount(new BigDecimal("0.00"));
        expectedResult.setOldAvgOrderMoney(new BigDecimal("0.00"));
        expectedResult.setOldAvgSignDiscount(new BigDecimal("0.00"));
        expectedResult.setOldSignMoneySumLastYear(new BigDecimal("0.00"));
        expectedResult.setOldSignProjectIdCountLastYear(new BigDecimal("0.00"));
        expectedResult.setOldAvgOrderMoneyLastYear(new BigDecimal("0.00"));
        expectedResult.setOldAvgSignDiscountLastYear(new BigDecimal("0.00"));
        expectedResult.setRenews(new BigDecimal("0.00"));
        expectedResult.setRenewsLastYear(new BigDecimal("0.00"));
        expectedResult.setRenewMoney(new BigDecimal("0.00"));
        expectedResult.setRenewMoneyLastYear(new BigDecimal("0.00"));
        expectedResult.setSumUpRenews(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewsLastYear(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewMoney(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewMoneyLastYear(new BigDecimal("0.00"));

        // Run the test
        final SignResultVo result = signServiceUnderTest.findSignKanbanSignDaily(searchVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindOldOrderRenewAnalyze() {
        // Setup
        final WorkTableSearchVo searchVO = new WorkTableSearchVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("yearOnYearTimeStart");
        searchVO.setEndSignDate("2023-04-03");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");

        final OldSingleContractRenewalAnalysisResultVo expectedResult = new OldSingleContractRenewalAnalysisResultVo();
        expectedResult.setLostOrderNum(new BigDecimal("0.00"));
        expectedResult.setLostOrderAmount(new BigDecimal("0.00"));
        expectedResult.setRenews(new BigDecimal("0.00"));
        expectedResult.setRenewsLastYear(new BigDecimal("0.00"));
        expectedResult.setRenewMoney(new BigDecimal("0.00"));
        expectedResult.setRenewMoneyLastYear(new BigDecimal("0.00"));
        expectedResult.setSumUpRenews(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewsLastYear(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewMoney(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewMoneyLastYear(new BigDecimal("0.00"));
        expectedResult.setContractsRenewedNum(new BigDecimal("0.00"));
        expectedResult.setToBeRenewedNum(new BigDecimal("0.00"));
        expectedResult.setContractsRenewedAmount(new BigDecimal("0.00"));
        expectedResult.setToBeRenewedAmount(new BigDecimal("0.00"));
        final OldSingleContractRenewalAnalysisResultVo.Pillar pillar = new OldSingleContractRenewalAnalysisResultVo.Pillar();
        pillar.setKey("key");
        pillar.setSign(new BigDecimal("0.00"));
        pillar.setTobeSign(new BigDecimal("0.00"));
        pillar.setLostOrder(new BigDecimal("0.00"));
        pillar.setProjectProportion(new BigDecimal("0.00"));
        expectedResult.setNumberScale(Arrays.asList(pillar));
        final OldSingleContractRenewalAnalysisResultVo.Pillar pillar1 = new OldSingleContractRenewalAnalysisResultVo.Pillar();
        pillar1.setKey("key");
        pillar1.setSign(new BigDecimal("0.00"));
        pillar1.setTobeSign(new BigDecimal("0.00"));
        pillar1.setLostOrder(new BigDecimal("0.00"));
        pillar1.setProjectProportion(new BigDecimal("0.00"));
        expectedResult.setAmountScale(Arrays.asList(pillar1));
        expectedResult.setAdvanceNum(new BigDecimal("0.00"));
        expectedResult.setPunctualNum(new BigDecimal("0.00"));
        expectedResult.setDelayNum(new BigDecimal("0.00"));
        expectedResult.setAdvanceMoney(new BigDecimal("0.00"));
        expectedResult.setPunctualMoney(new BigDecimal("0.00"));
        expectedResult.setDelayMoney(new BigDecimal("0.00"));

        // Run the test
        final OldSingleContractRenewalAnalysisResultVo result = signServiceUnderTest.findOldOrderRenewAnalyze(searchVO);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindOldOrderAggregateAnalysis() {
        // Setup
        final RenewalSummaryAnalysisVo searchVO = new RenewalSummaryAnalysisVo();
        searchVO.setBrmZone("brmZone");
        searchVO.setBrmDepartment("brmDepartment");
        searchVO.setBrmGroup("brmGroup");
        searchVO.setStartSignDate("yearOnYearTimeStart");
        searchVO.setEndSignDate("2023-04-03");
        searchVO.setBrmUserId("brmUserId");
        searchVO.setChiefZone("chiefZone");
        searchVO.setAggType("brm_group");

        // Run the test
        final BaseSearch result = signServiceUnderTest.findOldOrderAggregateAnalysis(searchVO);

        // Verify the results
    }

    @Test
    void testGetResultVo() {
        // Setup
        final RenewalAnalysisResultVo expectedResult = new RenewalAnalysisResultVo();
        expectedResult.setSumUpRenewsGrowthRate(new BigDecimal("0.00"));
        expectedResult.setSumUpRenews(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewMoney(new BigDecimal("0.00"));
        expectedResult.setContractsRenewedNum(new BigDecimal("0.00"));
        expectedResult.setContractsRenewedAmount(new BigDecimal("0.00"));
        expectedResult.setToBeRenewedNum(new BigDecimal("0.00"));
        expectedResult.setToBeRenewedAmount(new BigDecimal("0.00"));
        expectedResult.setLostOrderNum(new BigDecimal("0.00"));
        expectedResult.setLostOrderAmount(new BigDecimal("0.00"));
        expectedResult.setRenews(new BigDecimal("0.00"));
        expectedResult.setRenewsLastYear(new BigDecimal("0.00"));
        expectedResult.setRenewMoney(new BigDecimal("0.00"));
        expectedResult.setRenewMoneyLastYear(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewsLastYear(new BigDecimal("0.00"));
        expectedResult.setSumUpRenewMoneyLastYear(new BigDecimal("0.00"));

        // Run the test
        final RenewalAnalysisResultVo result = signServiceUnderTest.getResultVo("objectNextyearStage", "jsonObject");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

//    @Test
//    void testMain() {
        // Setup
        // Run the test
//        SignService.main(new String[]{"args"});

        // Verify the results
//    }

    /**
     * @param searchVO 查询条件
     * @param consumer 处理
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/3/29 11:08
     */
    private SearchSourceBuilder getSearchSourceBuilder(WorkTableSearchVo searchVO, Consumer<List<QueryBuilder>> consumer) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(MagicConst.INT_0);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        List<QueryBuilder> must = boolQuery.must();
        must.add(QueryBuilders.termsQuery(DeferredEnum.DELETED.getFields(), STR_0));
        //拼接条件
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmZone()),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.brm_zone.getFields(), searchVO.getBrmZone().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmDepartment()),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.brm_department.getFields(), searchVO.getBrmDepartment().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmGroup()),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.brm_group.getFields(), searchVO.getBrmGroup().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmUserId()),
                () -> must.add(QueryBuilders.wildcardQuery(OrderInfoEnum.leaderid.getFields(), ("*<" + searchVO.getBrmUserId() + ">*"))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getChiefZone()),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.chief_zone.getFields(), searchVO.getChiefZone().split(COMMA_SPLIT))));
        consumer.accept(must);
        builder.query(boolQuery);
        return builder;
    }
    /**
     * 构建查询条件
     *
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/4/2 11:35
     */
    private void constructAggByBrmType(RenewalSummaryAnalysisVo searchVO, String yearOnYearTimeStart, String yearOnYearTimeEnd, String endSignDate, SearchSourceBuilder sourceBuilder) {
        //聚合组装
        RangeAggregationBuilder rangeAggX = AggregationBuilders.range(RenewalOrderInfoEnum.sign_date.getFields()).field(RenewalOrderInfoEnum.sign_date.getFields())
                .addRange(new RangeAggregator.Range("签约时间范围-1", yearOnYearTimeStart, yearOnYearTimeEnd))
                .addRange(new RangeAggregator.Range("签约时间范围", searchVO.getStartSignDate(), endSignDate));
        CardinalityAggregationBuilder signProjectIdAgg = AggregationBuilders.cardinality(RenewalOrderInfoEnum.sign_project_id.getFields()).field(RenewalOrderInfoEnum.sign_project_id.getFields());
        TermsAggregationBuilder nextyearStageAgg = AggregationBuilders.terms(RenewalOrderInfoEnum.nextyear_stage.getFields()).field(RenewalOrderInfoEnum.nextyear_stage.getFields());

        SumAggregationBuilder signMoneySum = AggregationBuilders.sum(RenewalOrderInfoEnum.sign_money.getFields()).field(RenewalOrderInfoEnum.sign_money.getFields());
        TermsAggregationBuilder newOldTypeAgg = AggregationBuilders.terms(RenewalOrderInfoEnum.newoldtype.getFields()).field(RenewalOrderInfoEnum.newoldtype.getFields());
        ValueCountAggregationBuilder newyearSigninfoCount = AggregationBuilders.count(RenewalOrderInfoEnum.newyear_signinfo.getFields()).field(RenewalOrderInfoEnum.newyear_signinfo.getFields());
        SumAggregationBuilder newyearSignmoneySum = AggregationBuilders.sum(RenewalOrderInfoEnum.newyear_signmoney.getFields()).field(RenewalOrderInfoEnum.newyear_signmoney.getFields());

        rangeAggX.subAggregation(signMoneySum);
        rangeAggX.subAggregation(signProjectIdAgg);
        nextyearStageAgg.subAggregation(signProjectIdAgg);
        nextyearStageAgg.subAggregation(newyearSignmoneySum);
        rangeAggX.subAggregation(nextyearStageAgg);
        newOldTypeAgg.subAggregation(newyearSigninfoCount);
        newOldTypeAgg.subAggregation(signMoneySum);
        rangeAggX.subAggregation(newOldTypeAgg);

        TermsAggregationBuilder aggType = AggregationBuilders.terms(searchVO.getAggType()).field(searchVO.getAggType()).size(PAGE_SIZE_9999);
        //按传入参数进行聚合
        if (OWNERID.equals(searchVO.getAggType())) {
            //销售名称
            TopHitsAggregationBuilder result = AggregationBuilders.topHits("result").size(INT_1).fetchSource(new String[]{RenewalOrderInfoEnum.brm_group.getFields(), RenewalOrderInfoEnum.ownername.getFields()}, null);
            aggType.subAggregation(result);
        }
        aggType.subAggregation(rangeAggX);
        sourceBuilder.aggregation(aggType);
    }
    public BaseSearch findOldOrderAggregateAnalysis(RenewalSummaryAnalysisVo searchVO) {

        //同期范围
        String yearOnYearTimeStart = DateUtil.offset(DateUtil.parse(searchVO.getStartSignDate()), DateField.YEAR, -1).toDateStr();
        String yearOnYearTimeEnd = DateUtil.offsetDay(DateUtil.offset(DateUtil.parse(searchVO.getEndSignDate()), DateField.YEAR, -1), 1).toDateStr();
        String endSignDate = DateUtil.offsetDay(DateUtil.parse(searchVO.getEndSignDate()), 1).toDateStr();
        //
        SearchSourceBuilder sourceBuilder = getSearchSourceBuilder(BeanUtil.copyProperties(searchVO,WorkTableSearchVo.class), queryBuilders -> queryBuilders.add(QueryBuilders.existsQuery(RenewalOrderInfoEnum.nextyear_stage.getFields())));


        //老单续约分析agg
        oldOrderRenewalAnalysisByBrmType(searchVO, yearOnYearTimeStart, yearOnYearTimeEnd, endSignDate, sourceBuilder);

        //查询 大数据
        JSONObject objectNextyearStage = HttpClient.esRequest("http://10.18.18.72:8085/renewalanalyse", sourceBuilder.toString());
        //构建  老单续签 综合续约金额    老单续签 综合续约个数 查询条件
        SearchSourceBuilder builder1 = getSearchSourceBuilder(BeanUtil.copyProperties(searchVO,WorkTableSearchVo.class), System.out::println);
        //聚合组装
        constructAggByBrmType(searchVO, yearOnYearTimeStart, yearOnYearTimeEnd, endSignDate, builder1);

        JSONObject jsonObject = HttpClient.esRequest("http://10.18.18.72:8085/renewalanalyse", builder1.toString());

        cn.hutool.json.JSONArray objects = JSONUtil.parseArray(JSONPath.eval(objectNextyearStage, "$.aggregations." + searchVO.getAggType() + ".buckets"));
        cn.hutool.json.JSONArray array = JSONUtil.parseArray(JSONPath.eval(jsonObject, "$.aggregations." + searchVO.getAggType() + ".buckets"));
        Map<String, cn.hutool.json.JSONObject> map = objects.stream().map(JSONUtil::parseObj).collect(Collectors.toMap(jsonObject1 -> jsonObject1.getStr(KEY), Function.identity()));
        Map<String, cn.hutool.json.JSONObject> stringJSONObjectMap = array.stream().map(JSONUtil::parseObj).collect(Collectors.toMap(jsonObject1 -> jsonObject1.getStr(KEY), Function.identity()));
        List<Map<String, Object>> vos = map.entrySet().stream().map(entry -> {
            cn.hutool.json.JSONObject value = entry.getValue();
            RenewalAnalysisResultVo vo = getResultVo(value, stringJSONObjectMap.get(entry.getKey()));
            if (OWNERID.equals(searchVO.getAggType())) {
                JSONObject eval = (JSONObject) JSONPath.eval(value, "result.hits.hits[0]._source");
                eval.putAll(BeanUtil.beanToMap(vo));
                return eval;
            } else {
                JSONObject object = new JSONObject().fluentPut(searchVO.getAggType(), entry.getKey());
                object.putAll(BeanUtil.beanToMap(vo));
                return object;
            }
        }).collect(Collectors.toList());


        //获取字段信息

        LinkedList<Map<String, Object>> fields = FieldUtils.getFieldsListWithAnnotation(RenewalAnalysisResultVo.class, ApiModelProperty.class).stream()
                .map(field -> new JSONObject()
                        .fluentPut(RETURN_DATA_NAME, field.getAnnotation(ApiModelProperty.class).value())
                        .fluentPut(RETURN_DATA_CODE, field.getName())
                        .fluentPut(SORT_FLAG, INTEGER_1))
                .collect(Collectors.toCollection(CollUtil::newLinkedList));
        if (OWNERID.equals(searchVO.getAggType())) {
            fields.addFirst(new JSONObject()
                    .fluentPut(RETURN_DATA_CODE, RenewalOrderInfoEnum.brm_group.getFields())
                    .fluentPut(RETURN_DATA_NAME, "小组"));
            //字段修改
            fields.addFirst(new JSONObject()
                    .fluentPut(RETURN_DATA_CODE, RenewalOrderInfoEnum.ownername.getFields())
                    .fluentPut(RETURN_DATA_NAME, "销售姓名"));
        } else {
            fields.addFirst(new JSONObject()
                    .fluentPut(RETURN_DATA_CODE, searchVO.getAggType())
                    .fluentPut(RETURN_DATA_NAME, RenewalOrderInfoEnum.valueOf(searchVO.getAggType()).getDesc()));
        }

        BaseSearch search = new BaseSearch();
        search.setColumnNames(fields);
        search.setReturnData(vos);
        return search;

    }



    @Test
    public void testFindOldOrderAggregateAnalysisOne() {
        // Positive Test Case
        RenewalSummaryAnalysisVo searchVO = new RenewalSummaryAnalysisVo();
        searchVO.setStartSignDate("2020-01-01");
        searchVO.setEndSignDate("2020-12-31");
        searchVO.setAggType("OWNERID");
        BaseSearch result = findOldOrderAggregateAnalysis(searchVO);
        assertNotNull(result);
        assertEquals(3, result.getColumnNames().size());
        assertTrue(result.getReturnData().size() > 0);
        // Negative Test Case
        searchVO.setStartSignDate("");
        searchVO.setEndSignDate("");
        searchVO.setAggType("");
        result = findOldOrderAggregateAnalysis(searchVO);
        assertNull(result);
    }

    private void oldOrderRenewalAnalysisByBrmType(RenewalSummaryAnalysisVo searchVO, String yearOnYearTimeStart, String yearOnYearTimeEnd, String endSignDate, SearchSourceBuilder sourceBuilder) {
        RangeAggregationBuilder rangeAggX = AggregationBuilders.range(RenewalOrderInfoEnum.sign_date.getFields()).field(RenewalOrderInfoEnum.sign_date.getFields())
                .addRange(new RangeAggregator.Range("签约时间范围-1", yearOnYearTimeStart, yearOnYearTimeEnd))
                .addRange(new RangeAggregator.Range("签约时间范围", searchVO.getStartSignDate(), endSignDate));
        CardinalityAggregationBuilder signProjectIdAgg = AggregationBuilders.cardinality(RenewalOrderInfoEnum.sign_project_id.getFields()).field(RenewalOrderInfoEnum.sign_project_id.getFields());
        //已续约待续约丢单状态 聚合条件
        TermsAggregationBuilder renewalLoseStateAgg = AggregationBuilders.terms(RenewalOrderInfoEnum.renewal_lose_state.getFields()).field(RenewalOrderInfoEnum.renewal_lose_state.getFields());
        //第二年的当前阶段
        TermsAggregationBuilder nextyearStageAgg = AggregationBuilders.terms(RenewalOrderInfoEnum.nextyear_stage.getFields()).field(RenewalOrderInfoEnum.nextyear_stage.getFields());

        SumAggregationBuilder signMoneySum = AggregationBuilders.sum(RenewalOrderInfoEnum.sign_money.getFields()).field(RenewalOrderInfoEnum.sign_money.getFields());
        TermsAggregationBuilder newOldTypeAgg = AggregationBuilders.terms(RenewalOrderInfoEnum.newoldtype.getFields()).field(RenewalOrderInfoEnum.newoldtype.getFields());
        ValueCountAggregationBuilder newyearSigninfoCount = AggregationBuilders.count(RenewalOrderInfoEnum.newyear_signinfo.getFields()).field(RenewalOrderInfoEnum.newyear_signinfo.getFields());
        SumAggregationBuilder newyearSignmoneySum = AggregationBuilders.sum(RenewalOrderInfoEnum.newyear_signmoney.getFields()).field(RenewalOrderInfoEnum.newyear_signmoney.getFields());
        //已续约待续约丢单状态 金额 聚合条件
        SumAggregationBuilder renewalLoseMoneyAgg = AggregationBuilders.sum(RenewalOrderInfoEnum.renewal_lose_money.getFields()).field(RenewalOrderInfoEnum.renewal_lose_money.getFields());

        rangeAggX.subAggregation(signMoneySum);
        //已续约待续约丢单状态 ->个数和金额
        renewalLoseStateAgg.subAggregation(renewalLoseMoneyAgg);
        renewalLoseStateAgg.subAggregation(signProjectIdAgg);

        rangeAggX.subAggregation(renewalLoseStateAgg);
        rangeAggX.subAggregation(signProjectIdAgg);
        nextyearStageAgg.subAggregation(signProjectIdAgg);
        nextyearStageAgg.subAggregation(newyearSignmoneySum);
        rangeAggX.subAggregation(nextyearStageAgg);
        newOldTypeAgg.subAggregation(newyearSigninfoCount);
        newOldTypeAgg.subAggregation(signMoneySum);
        rangeAggX.subAggregation(newOldTypeAgg);

        TermsAggregationBuilder aggType = AggregationBuilders.terms(searchVO.getAggType()).field(searchVO.getAggType()).size(PAGE_SIZE_9999);
        //按传入参数进行聚合
        if (OWNERID.equals(searchVO.getAggType())) {
            //销售名称
            TopHitsAggregationBuilder result = AggregationBuilders.topHits("result").size(INT_1).fetchSource(new String[]{RenewalOrderInfoEnum.brm_group.getFields(), RenewalOrderInfoEnum.ownername.getFields()}, null);
            aggType.subAggregation(result);
        }
        aggType.subAggregation(rangeAggX);
        sourceBuilder.aggregation(aggType);
    }

    /**
     * 构建公共返回数据
     * @param objectNextyearStage
     * @param jsonObject
     * @return
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date 2023/4/6 10:30
     */
    public RenewalAnalysisResultVo getResultVo(Object objectNextyearStage, Object jsonObject) {
        RenewalAnalysisResultVo resultVo = new RenewalAnalysisResultVo();

        cn.hutool.json.JSONArray renewalLoseState = JSONUtil.parseArray(JSONPath.eval(objectNextyearStage, "$.sign_date.buckets[?(@.key=='签约时间范围-1')].renewal_lose_state.buckets"));
        Map<String, cn.hutool.json.JSONObject> keyData = renewalLoseState.stream().map(JSONUtil::parseObj).collect(Collectors.toMap(jsonObject1 -> jsonObject1.getStr(KEY), Function.identity()));
        //解析 已续约待续约丢单状态
        resultVo.setContractsRenewedNum(Optional.ofNullable(keyData.get("已续约"))
                .map(jsonObject1 -> jsonObject1.getJSONObject(RenewalOrderInfoEnum.sign_project_id.getFields()).getBigDecimal(VALUE)).orElse(ZERO));
        resultVo.setContractsRenewedAmount(Optional.ofNullable(keyData.get("已续约"))
                .map(jsonObject1 -> jsonObject1.getJSONObject(RenewalOrderInfoEnum.renewal_lose_money.getFields()).getBigDecimal(VALUE)).orElse(ZERO));

        resultVo.setToBeRenewedNum(Optional.ofNullable(keyData.get("待续约"))
                .map(jsonObject1 -> jsonObject1.getJSONObject(RenewalOrderInfoEnum.sign_project_id.getFields()).getBigDecimal(VALUE)).orElse(ZERO));
        resultVo.setToBeRenewedAmount(Optional.ofNullable(keyData.get("待续约"))
                .map(jsonObject1 -> jsonObject1.getJSONObject(RenewalOrderInfoEnum.renewal_lose_money.getFields()).getBigDecimal(VALUE)).orElse(ZERO));

        resultVo.setLostOrderNum(Optional.ofNullable(keyData.get("丢单"))
                .map(jsonObject1 -> jsonObject1.getJSONObject(RenewalOrderInfoEnum.sign_project_id.getFields()).getBigDecimal(VALUE)).orElse(ZERO));
        resultVo.setLostOrderAmount(Optional.ofNullable(keyData.get("丢单"))
                .map(jsonObject1 -> jsonObject1.getJSONObject(RenewalOrderInfoEnum.renewal_lose_money.getFields()).getBigDecimal(VALUE)).orElse(ZERO));


        //同期老单个数
        Object renewsLastYear = JSONPath.eval(objectNextyearStage, "$.sign_date.buckets[?(@.key=='签约时间范围-1')].sign_project_id.value");
        resultVo.setRenewsLastYear(Objects.isNull(renewsLastYear) || Stringutil.isNullEmpty(clear(renewsLastYear + "")) ? ZERO : new BigDecimal(clear(renewsLastYear + "")));
        //当期 已签约      以nextyear_stage  第二年机会的当前阶段 为5-已签单||6-已结项
        Object renews = JSONPath.eval(objectNextyearStage, "$.sign_date.buckets[?(@.key=='签约时间范围-1')].nextyear_stage.buckets[?(@.key=='5-已签单' || @.key=='6-已结项')].sign_project_id.value");
        String renewsValue = Objects.isNull(renews) ? "0" : ((int) ((JSONArray) JSON.toJSON(renews)).stream().mapToDouble(value -> Double.parseDouble(value + "")).sum()) + "";
        resultVo.setRenews(new BigDecimal(renewsValue));
        resultVo.setSumUpRenewsLastYear(resultVo.getRenewsLastYear());
        //老单续签 综合续约个数
        Object sumUpRenews = JSONPath.eval(jsonObject, "$.sign_date.buckets[?(@.key=='签约时间范围')].newoldtype.buckets[?(@.key=='老机会')].newyear_signinfo.value");
        resultVo.setSumUpRenews(Objects.isNull(sumUpRenews) || StringUtil.isEmpty(clear(sumUpRenews + "")) ? ZERO : new BigDecimal(clear(sumUpRenews + "")));
        //同期老单金额
        Object renewMoneyLastYear = JSONPath.eval(objectNextyearStage, "$.sign_date.buckets[?(@.key=='签约时间范围-1')].sign_money.value");
        resultVo.setRenewMoneyLastYear(Objects.isNull(renewMoneyLastYear) || StringUtil.isEmpty(clear(renewMoneyLastYear + "")) ? ZERO : new BigDecimal(clear(renewMoneyLastYear + "")));
        //老单续签 续约金额
        Object renewMoney = JSONPath.eval(objectNextyearStage, "$.sign_date.buckets[?(@.key=='签约时间范围-1')].nextyear_stage.buckets[?(@.key=='5-已签单' || @.key=='6-已结项')].newyear_signmoney.value");
        String renewMoneyValue = Objects.isNull(renewMoney) ? "0" : ((int) ((JSONArray) JSON.toJSON(renewMoney)).stream().mapToDouble(value -> Double.parseDouble(value + "")).sum()) + "";
        resultVo.setRenewMoney(new BigDecimal(renewMoneyValue));
        //老单续签 综合续约金额
        resultVo.setSumUpRenewMoneyLastYear(resultVo.getRenewMoneyLastYear());
        Object sumUpRenewMoney = JSONPath.eval(jsonObject, "$.sign_date.buckets[?(@.key=='签约时间范围')].newoldtype.buckets[?(@.key=='老机会')].sign_money.value");
        resultVo.setSumUpRenewMoney(Objects.isNull(sumUpRenewMoney) || StringUtil.isEmpty(clear(sumUpRenewMoney + "")) ? ZERO : new BigDecimal(clear(sumUpRenewMoney + "")));
        return resultVo;
    }
    /**
     * @param s 参数
     * @return java.lang.String
     * @description: 替换字符串
     * @auther yanan.mu-ext
     * @date 2022-02-24 下午2:12
     */
    public String clear(String s) {
        return s.replaceAll("\\[", "").replaceAll("\\]", "");
    }


    @Test
    public void testGetResultVo_Positive() {
        Object objectNextyearStage = new Object();
        Object jsonObject = new Object();
        RenewalAnalysisResultVo resultVo = getResultVo(objectNextyearStage, jsonObject);
        assertNotNull(resultVo);
        assertNotNull(resultVo.getContractsRenewedNum());
        assertNotNull(resultVo.getContractsRenewedAmount());
        assertNotNull(resultVo.getToBeRenewedNum());
        assertNotNull(resultVo.getToBeRenewedAmount());
        assertNotNull(resultVo.getLostOrderNum());
        assertNotNull(resultVo.getLostOrderAmount());
        assertNotNull(resultVo.getRenewsLastYear());
        assertNotNull(resultVo.getRenews());
        assertNotNull(resultVo.getSumUpRenewsLastYear());
        assertNotNull(resultVo.getSumUpRenews());
        assertNotNull(resultVo.getRenewMoneyLastYear());
        assertNotNull(resultVo.getRenewMoney());
        assertNotNull(resultVo.getSumUpRenewMoneyLastYear());
        assertNotNull(resultVo.getSumUpRenewMoney());
    }
    @Test
    public void testGetResultVo_Negative() {
        Object objectNextyearStage = null;
        Object jsonObject = null;
        RenewalAnalysisResultVo resultVo = getResultVo(objectNextyearStage, jsonObject);
        assertNull(resultVo);
    }
}
