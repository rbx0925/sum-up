package com.ikang.idata.search.search.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.utils.CheckUtil;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.entity.OrderInfoEnum;
import com.ikang.idata.search.search.entity.RenewalOrderInfoEnum;
import com.ikang.idata.search.search.entity.vo.SignResultVo;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import com.ikang.idata.search.search.util.CalculateUtil;
import com.ikang.idata.search.search.util.HttpClient;
import com.ikang.idata.search.search.util.Stringutil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregator;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.ikang.idata.common.consts.MagicConst.*;
import static java.math.BigDecimal.ZERO;


@ActiveProfiles(value = {"test"})
@SpringBootTest
@Slf4j
public class SignResultTest {

    public static final String NEWPROJECT = "新项目";
    public static final String OLDPROJECT = "老项目";
    @Value("${kanban.signinformation.searchUrl}")
    private String signinformationUrl;

    @Value("${kanban.renewalanalyse.searchUrl}")
    private String renewalanalyseUrl;

    /**
     * 新老单分析
     *
     * @param searchVO 查询条件
     */
    public SignResultVo findSignKanban(WorkTableSearchVo searchVO) {
        SearchSourceBuilder builder = getSearchSourceBuilder(searchVO, System.out::println);
        //聚合组装
        //同期范围
        String yearOnYearTimeStart = DateUtil.offset(DateUtil.parse(searchVO.getStartSignDate()), DateField.YEAR, -1).toDateStr();
        String yearOnYearTimeEnd = DateUtil.offsetDay(DateUtil.offset(DateUtil.parse(searchVO.getEndSignDate()), DateField.YEAR, -1), 1).toDateStr();
        String endSignDate = DateUtil.offsetDay(DateUtil.parse(searchVO.getEndSignDate()), 1).toDateStr();

        RangeAggregationBuilder rangeAgg = AggregationBuilders.range(OrderInfoEnum.sign_date.getFields()).field(OrderInfoEnum.sign_date.getFields())
                .addRange(new RangeAggregator.Range("同期", yearOnYearTimeStart, yearOnYearTimeEnd))
                .addRange(new RangeAggregator.Range("当期", searchVO.getStartSignDate(), endSignDate));
        SumAggregationBuilder sumSignMoney = AggregationBuilders.sum(OrderInfoEnum.sign_money.getFields()).field(OrderInfoEnum.sign_money.getFields());
        ValueCountAggregationBuilder signProjectIdCount = AggregationBuilders.count(OrderInfoEnum.sign_project_id.getFields()).field(OrderInfoEnum.sign_project_id.getFields());
        SumAggregationBuilder worknosSum = AggregationBuilders.sum(OrderInfoEnum.sign_nos.getFields()).field(OrderInfoEnum.sign_nos.getFields());
        SumAggregationBuilder avgDiscountSum = AggregationBuilders.sum(OrderInfoEnum.avg_discount.getFields()).field(OrderInfoEnum.avg_discount.getFields());

        //添加条件
        FilterAggregationBuilder filter = AggregationBuilders.filter(OrderInfoEnum.avg_discount.getFields() + "_filter", QueryBuilders.existsQuery(OrderInfoEnum.avg_discount.getFields()));
        filter.subAggregation(avgDiscountSum);
        filter.subAggregation(signProjectIdCount);

        rangeAgg.subAggregation(filter);
        rangeAgg.subAggregation(sumSignMoney);
        rangeAgg.subAggregation(signProjectIdCount);
        rangeAgg.subAggregation(worknosSum);
        builder.aggregation(rangeAgg);

        // 新/老
        TermsAggregationBuilder newOldAgg = AggregationBuilders.terms(OrderInfoEnum.newoldtype.getFields()).field(OrderInfoEnum.newoldtype.getFields());
        newOldAgg.subAggregation(filter);
        newOldAgg.subAggregation(sumSignMoney);
        newOldAgg.subAggregation(signProjectIdCount);
        rangeAgg.subAggregation(newOldAgg);
        //请求es
        JSONObject object = HttpClient.esRequest(signinformationUrl, builder.toString());
        //解析
        JSONObject currentPeriod = ((JSONArray) JSONObject.toJSON(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='当期')]"))).getJSONObject(0);
        JSONObject correspondingTimePeriod = ((JSONArray) JSONObject.toJSON(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='同期')]"))).getJSONObject(0);

        SignResultVo resultVo = new SignResultVo();
        resultVo.setSignMoneySum(currentPeriod.getJSONObject(OrderInfoEnum.sign_money.getFields()).getBigDecimal(VALUE));
        resultVo.setSignProjectIdCount(currentPeriod.getJSONObject(OrderInfoEnum.sign_project_id.getFields()).getBigDecimal(VALUE));
        resultVo.setWorknosSum(currentPeriod.getJSONObject(OrderInfoEnum.sign_nos.getFields()).getBigDecimal(VALUE));
        resultVo.setAvgOrderMoney(CalculateUtil.div(resultVo.getSignMoneySum(), resultVo.getSignProjectIdCount()));
        resultVo.setAvgCustomerUnitPrice(CalculateUtil.div(resultVo.getSignMoneySum(), resultVo.getWorknosSum()));
        resultVo.setAvgSignDiscount(CalculateUtil.div(currentPeriod.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.avg_discount.getFields()).get(VALUE) + "",
                currentPeriod.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.sign_project_id.getFields()).get(VALUE) + ""));

        resultVo.setSignMoneySumLastYear(correspondingTimePeriod.getJSONObject(OrderInfoEnum.sign_money.getFields()).getBigDecimal(VALUE));
        resultVo.setSignProjectIdCountLastYear(correspondingTimePeriod.getJSONObject(OrderInfoEnum.sign_project_id.getFields()).getBigDecimal(VALUE));
        resultVo.setWorknosSumLastYear(correspondingTimePeriod.getJSONObject(OrderInfoEnum.sign_nos.getFields()).getBigDecimal(VALUE));
        resultVo.setAvgOrderMoneyLastYear(CalculateUtil.div(resultVo.getSignMoneySumLastYear(), resultVo.getSignProjectIdCountLastYear()));
        resultVo.setAvgCustomerUnitPriceLastYear(CalculateUtil.div(resultVo.getSignMoneySumLastYear(), resultVo.getWorknosSumLastYear()));
        resultVo.setAvgSignDiscountLastYear(CalculateUtil.div(correspondingTimePeriod.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.avg_discount.getFields()).get(VALUE) + "",
                correspondingTimePeriod.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.sign_project_id.getFields()).get(VALUE) + ""));

        Map<String, JSONObject> currentNewAndOldOrders = currentPeriod.getJSONObject(OrderInfoEnum.newoldtype.getFields()).getJSONArray(BUCKETS)
                .stream().collect(Collectors.toMap(o -> ((JSONObject) JSON.toJSON(o)).getString(KEY), o -> (JSONObject) JSON.toJSON(o)));
        JSONObject currentNewOrders = currentNewAndOldOrders.get(NEWPROJECT);
        resultVo.setNewSignMoneySum(Objects.isNull(currentNewOrders) ? ZERO : currentNewOrders.getJSONObject(OrderInfoEnum.sign_money.getFields()).getBigDecimal(VALUE));
        resultVo.setNewSignProjectIdCount(Objects.isNull(currentNewOrders) ? ZERO : currentNewOrders.getJSONObject(OrderInfoEnum.sign_project_id.getFields()).getBigDecimal(VALUE));
        resultVo.setNewAvgOrderMoney(Objects.isNull(currentNewOrders) ? ZERO : CalculateUtil.div(resultVo.getNewSignMoneySum(), resultVo.getNewSignProjectIdCount()));
        resultVo.setNewAvgSignDiscount(Objects.isNull(currentNewOrders) ? ZERO : CalculateUtil.div(currentNewOrders.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.avg_discount.getFields()).get(VALUE) + "",
                currentNewOrders.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.sign_project_id.getFields()).get(VALUE) + ""));
        //同期新单
        Map<String, JSONObject> correspondingTimePeriodNewAndOldOrders = correspondingTimePeriod.getJSONObject(OrderInfoEnum.newoldtype.getFields()).getJSONArray(BUCKETS)
                .stream().collect(Collectors.toMap(o -> ((JSONObject) JSON.toJSON(o)).getString(KEY), o -> (JSONObject) JSON.toJSON(o)));
        JSONObject newOrderInTheSamePeriod = correspondingTimePeriodNewAndOldOrders.get(NEWPROJECT);
        resultVo.setNewSignMoneySumLastYear(Objects.isNull(newOrderInTheSamePeriod) ? ZERO : newOrderInTheSamePeriod.getJSONObject(OrderInfoEnum.sign_money.getFields()).getBigDecimal(VALUE));
        resultVo.setNewSignProjectIdCountLastYear(Objects.isNull(newOrderInTheSamePeriod) ? ZERO : newOrderInTheSamePeriod.getJSONObject(OrderInfoEnum.sign_project_id.getFields()).getBigDecimal(VALUE));
        resultVo.setNewAvgOrderMoneyLastYear(Objects.isNull(newOrderInTheSamePeriod) ? ZERO : CalculateUtil.div(resultVo.getNewSignMoneySumLastYear(), resultVo.getNewSignProjectIdCountLastYear()));
        resultVo.setNewAvgSignDiscountLastYear(Objects.isNull(newOrderInTheSamePeriod) ? ZERO : CalculateUtil.div(newOrderInTheSamePeriod.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.avg_discount.getFields()).get(VALUE) + "",
                newOrderInTheSamePeriod.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.sign_project_id.getFields()).get(VALUE) + ""));


        JSONObject currentOld = currentNewAndOldOrders.get(OLDPROJECT);
        resultVo.setOldSignMoneySum(Objects.isNull(currentOld) ? ZERO : currentOld.getJSONObject(OrderInfoEnum.sign_money.getFields()).getBigDecimal(VALUE));
        resultVo.setOldSignProjectIdCount(Objects.isNull(currentOld) ? ZERO : currentOld.getJSONObject(OrderInfoEnum.sign_project_id.getFields()).getBigDecimal(VALUE));
        resultVo.setOldAvgOrderMoney(Objects.isNull(currentOld) ? ZERO : CalculateUtil.div(resultVo.getOldSignMoneySum(), resultVo.getOldSignProjectIdCount()));
        resultVo.setOldAvgSignDiscount(Objects.isNull(currentOld) ? ZERO : CalculateUtil.div(currentOld.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.avg_discount.getFields()).get(VALUE) + "",
                currentOld.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.sign_project_id.getFields()).get(VALUE) + ""));

        JSONObject samePeriodOldOrder = correspondingTimePeriodNewAndOldOrders.get(OLDPROJECT);
        resultVo.setOldSignMoneySumLastYear(Objects.isNull(samePeriodOldOrder) ? ZERO : samePeriodOldOrder.getJSONObject(OrderInfoEnum.sign_money.getFields()).getBigDecimal(VALUE));
        resultVo.setOldSignProjectIdCountLastYear(Objects.isNull(samePeriodOldOrder) ? ZERO : samePeriodOldOrder.getJSONObject(OrderInfoEnum.sign_project_id.getFields()).getBigDecimal(VALUE));
        resultVo.setOldAvgOrderMoneyLastYear(Objects.isNull(samePeriodOldOrder) ? ZERO : CalculateUtil.div(resultVo.getOldSignMoneySumLastYear(), resultVo.getOldSignProjectIdCountLastYear()));
        resultVo.setOldAvgSignDiscountLastYear(Objects.isNull(samePeriodOldOrder) ? ZERO : CalculateUtil.div(samePeriodOldOrder.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.avg_discount.getFields()).get(VALUE) + "",
                samePeriodOldOrder.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.sign_project_id.getFields()).get(VALUE) + ""));

        //老单续约

       /*
          查询条件 nextyear_stage  is not null
          聚合 时间
          sum（项目金额)
          聚合 第二年机会的当前阶段
          count  项目号
          sum  第二年机会的已签单项目金额
          sum（项目金额)
          聚合 当前项目新老机会
          count 第二年机会的续签签单信息表字段

        */


        SearchSourceBuilder sourceBuilder = getSearchSourceBuilder(searchVO, queryBuilders -> queryBuilders.add(QueryBuilders.existsQuery(RenewalOrderInfoEnum.nextyear_stage.getFields())));
        constructAgg(searchVO, yearOnYearTimeStart, yearOnYearTimeEnd, endSignDate, sourceBuilder);
        //查询 大数据
        JSONObject object1 = HttpClient.esRequest(renewalanalyseUrl, sourceBuilder.toString());
        //构建  老单续签 综合续约金额    老单续签 综合续约个数 查询条件
        SearchSourceBuilder builder1 = getSearchSourceBuilder(searchVO, System.out::println);
        constructAgg(searchVO, yearOnYearTimeStart, yearOnYearTimeEnd, endSignDate, builder1);
        JSONObject jsonObject = HttpClient.esRequest(renewalanalyseUrl, builder1.toString());

        //解析
        Object renewsLastYear = JSONPath.eval(object1, "$.aggregations.sign_date.buckets[?(@.key=='签约时间范围-1')].sign_project_id.value");
        resultVo.setRenewsLastYear(Objects.isNull(renewsLastYear) || Stringutil.isNullEmpty(clear(renewsLastYear + "")) ? ZERO : new BigDecimal(clear(renewsLastYear + "")));

        Object renews = JSONPath.eval(object1, "$.aggregations.sign_date.buckets[?(@.key=='签约时间范围-1')].nextyear_stage.buckets[?(@.key=='5-已签单' || @.key=='6-已结项')].sign_project_id.value");
        String renewsValue = Objects.isNull(renews) ? "0" : ((int) ((JSONArray) JSON.toJSON(renews)).stream().mapToDouble(value -> Double.parseDouble(value + "")).sum()) + "";
        resultVo.setRenews(new BigDecimal(renewsValue));
        resultVo.setSumUpRenewsLastYear(resultVo.getRenewsLastYear());

        Object sumUpRenews = JSONPath.eval(jsonObject, "$.aggregations.sign_date.buckets[?(@.key=='签约时间范围')].newoldtype.buckets[?(@.key=='老机会')].newyear_signinfo.value");
        resultVo.setSumUpRenews(Objects.isNull(sumUpRenews) || StringUtil.isEmpty(clear(sumUpRenews + "")) ? ZERO : new BigDecimal(clear(sumUpRenews + "")));

        Object renewMoneyLastYear = JSONPath.eval(object1, "$.aggregations.sign_date.buckets[?(@.key=='签约时间范围-1')].sign_money.value");
        resultVo.setRenewMoneyLastYear(Objects.isNull(renewMoneyLastYear) || StringUtil.isEmpty(clear(renewMoneyLastYear + "")) ? ZERO : new BigDecimal(clear(renewMoneyLastYear + "")));

        Object renewMoney = JSONPath.eval(object1, "$.aggregations.sign_date.buckets[?(@.key=='签约时间范围-1')].nextyear_stage.buckets[?(@.key=='5-已签单' || @.key=='6-已结项')].newyear_signmoney.value");
        String renewMoneyValue = Objects.isNull(renewMoney) ? "0" : ((int) ((JSONArray) JSON.toJSON(renewMoney)).stream().mapToDouble(value -> Double.parseDouble(value + "")).sum()) + "";
        resultVo.setRenewMoney(new BigDecimal(renewMoneyValue));

        resultVo.setSumUpRenewMoneyLastYear(resultVo.getRenewMoneyLastYear());
        Object sumUpRenewMoney = JSONPath.eval(jsonObject, "$.aggregations.sign_date.buckets[?(@.key=='签约时间范围')].newoldtype.buckets[?(@.key=='老机会')].sign_money.value");
        resultVo.setSumUpRenewMoney(Objects.isNull(sumUpRenewMoney) || StringUtil.isEmpty(clear(sumUpRenewMoney + "")) ? ZERO : new BigDecimal(clear(sumUpRenewMoney + "")));
        return resultVo;
    }

    /**
     * 构建查询条件
     *
     */
    private void constructAgg(WorkTableSearchVo searchVO, String yearOnYearTimeStart, String yearOnYearTimeEnd, String endSignDate, SearchSourceBuilder sourceBuilder) {
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

        sourceBuilder.aggregation(rangeAggX);
        rangeAggX.subAggregation(signMoneySum);
        rangeAggX.subAggregation(signProjectIdAgg);
        nextyearStageAgg.subAggregation(signProjectIdAgg);
        nextyearStageAgg.subAggregation(newyearSignmoneySum);
        rangeAggX.subAggregation(nextyearStageAgg);
        newOldTypeAgg.subAggregation(newyearSigninfoCount);
        newOldTypeAgg.subAggregation(signMoneySum);
        rangeAggX.subAggregation(newOldTypeAgg);
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

    /**
     * 签单日报
     *
     * @param searchVO 请求参数
     */
    public SignResultVo findSignKanbanSignDaily(WorkTableSearchVo searchVO) {
        SearchSourceBuilder builder = getSearchSourceBuilder(searchVO, System.out::println);
        //同期范围
        String yearOnYearTimeStart = DateUtil.offset(DateUtil.parse(searchVO.getStartSignDate()), DateField.YEAR, -1).toDateStr();
        String yearOnYearTimeEnd = DateUtil.offsetDay(DateUtil.offset(DateUtil.parse(searchVO.getEndSignDate()), DateField.YEAR, -1), 1).toDateStr();
        String endSignDate = DateUtil.offsetDay(DateUtil.parse(searchVO.getEndSignDate()), 1).toDateStr();

        RangeAggregationBuilder rangeAgg = AggregationBuilders.range(OrderInfoEnum.sign_date.getFields()).field(OrderInfoEnum.sign_date.getFields())
                .addRange(new RangeAggregator.Range("同期", yearOnYearTimeStart, yearOnYearTimeEnd))
                .addRange(new RangeAggregator.Range("当期", searchVO.getStartSignDate(), endSignDate));
        SumAggregationBuilder sumSignMoney = AggregationBuilders.sum(OrderInfoEnum.sign_money.getFields()).field(OrderInfoEnum.sign_money.getFields());
        ValueCountAggregationBuilder signProjectIdCount = AggregationBuilders.count(OrderInfoEnum.sign_project_id.getFields()).field(OrderInfoEnum.sign_project_id.getFields());
        SumAggregationBuilder worknosSum = AggregationBuilders.sum(OrderInfoEnum.sign_nos.getFields()).field(OrderInfoEnum.sign_nos.getFields());
        SumAggregationBuilder avgDiscountSum = AggregationBuilders.sum(OrderInfoEnum.avg_discount.getFields()).field(OrderInfoEnum.avg_discount.getFields());
        //添加条件
        FilterAggregationBuilder filter = AggregationBuilders.filter(OrderInfoEnum.avg_discount.getFields() + "_filter", QueryBuilders.existsQuery(OrderInfoEnum.avg_discount.getFields()));
        filter.subAggregation(avgDiscountSum);
        filter.subAggregation(signProjectIdCount);

        rangeAgg.subAggregation(filter);
        rangeAgg.subAggregation(sumSignMoney);
        rangeAgg.subAggregation(signProjectIdCount);
        rangeAgg.subAggregation(worknosSum);
        builder.aggregation(rangeAgg);

        JSONObject object = HttpClient.esRequest(signinformationUrl, builder.toString());
        JSONObject currentPeriod = ((JSONArray) JSONObject.toJSON(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='当期')]"))).getJSONObject(0);
        JSONObject correspondingTimePeriod = ((JSONArray) JSONObject.toJSON(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='同期')]"))).getJSONObject(0);
        //解析
        SignResultVo resultVo = new SignResultVo();
        resultVo.setSignMoneySum(currentPeriod.getJSONObject(OrderInfoEnum.sign_money.getFields()).getBigDecimal(VALUE));
        resultVo.setSignProjectIdCount(currentPeriod.getJSONObject(OrderInfoEnum.sign_project_id.getFields()).getBigDecimal(VALUE));
        resultVo.setWorknosSum(currentPeriod.getJSONObject(OrderInfoEnum.sign_nos.getFields()).getBigDecimal(VALUE));
        resultVo.setAvgOrderMoney(CalculateUtil.div(resultVo.getSignMoneySum(), resultVo.getSignProjectIdCount()));
        resultVo.setAvgCustomerUnitPrice(CalculateUtil.div(resultVo.getSignMoneySum(), resultVo.getWorknosSum()));
        resultVo.setAvgSignDiscount(CalculateUtil.div(currentPeriod.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.avg_discount.getFields()).get(VALUE) + "",
                currentPeriod.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.sign_project_id.getFields()).get(VALUE) + ""));

        resultVo.setSignMoneySumLastYear(correspondingTimePeriod.getJSONObject(OrderInfoEnum.sign_money.getFields()).getBigDecimal(VALUE));
        resultVo.setSignProjectIdCountLastYear(correspondingTimePeriod.getJSONObject(OrderInfoEnum.sign_project_id.getFields()).getBigDecimal(VALUE));
        resultVo.setWorknosSumLastYear(correspondingTimePeriod.getJSONObject(OrderInfoEnum.sign_nos.getFields()).getBigDecimal(VALUE));
        resultVo.setAvgOrderMoneyLastYear(CalculateUtil.div(resultVo.getSignMoneySumLastYear(), resultVo.getSignProjectIdCountLastYear()));
        resultVo.setAvgCustomerUnitPriceLastYear(CalculateUtil.div(resultVo.getSignMoneySumLastYear(), resultVo.getWorknosSumLastYear()));
        resultVo.setAvgSignDiscountLastYear(CalculateUtil.div(correspondingTimePeriod.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.avg_discount.getFields()).get(VALUE) + "",
                correspondingTimePeriod.getJSONObject(OrderInfoEnum.avg_discount.getFields() + "_filter").getJSONObject(OrderInfoEnum.sign_project_id.getFields()).get(VALUE) + ""));
        return resultVo;
    }

    /**
     * @param searchVO 查询条件
     * @param consumer 处理
     */
    private SearchSourceBuilder getSearchSourceBuilder(WorkTableSearchVo searchVO, Consumer<List<QueryBuilder>> consumer) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(MagicConst.INT_0);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        List<QueryBuilder> must = boolQuery.must();
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


}
