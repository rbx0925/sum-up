package com.ikang.idata.search.search.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.ikang.idata.common.utils.CheckUtil;
import com.ikang.idata.search.search.common.Com;
import com.ikang.idata.search.search.entity.ReturnedNotesEnum;
import com.ikang.idata.search.search.entity.vo.ManagerAdditionDetailsVo;
import com.ikang.idata.search.search.entity.vo.RankingByAddition;
import com.ikang.idata.search.search.entity.vo.RankingByAdditionList;
import com.ikang.idata.search.search.util.HttpClient;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.ikang.idata.common.consts.MagicConst.*;

public class ZoneAddDetailsServiceTest {


    private ZoneAdditionDetailsService zoneAdditionDetailsServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        zoneAdditionDetailsServiceUnderTest = new ZoneAdditionDetailsService();
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final ManagerAdditionDetailsVo managerAdditionDetailsVo = new ManagerAdditionDetailsVo();
        managerAdditionDetailsVo.setBrmUserId("brmUserId");
        managerAdditionDetailsVo.setProjectIdNumber("projectIdNumber");
        managerAdditionDetailsVo.setProjectZoneType("projectZoneType");
        managerAdditionDetailsVo.setBrmZone("brmZone");
        managerAdditionDetailsVo.setBorc("borc");
        managerAdditionDetailsVo.setAreaZone("areaZone");
        managerAdditionDetailsVo.setChiefZone("chiefZone");
        managerAdditionDetailsVo.setStartDate("startDate");
        managerAdditionDetailsVo.setEndDate("endDate");

        // Run the test
        final RankingByAdditionList result = zoneAdditionDetailsServiceUnderTest.find(managerAdditionDetailsVo);

        // Verify the results
    }



    /**
     * @description: 到检加项排名
     * select top 20 sum(saleprice) from additiondetails where projectid_number=?
     * and item_type=加项
     * group by item_name order by saleprice desc
     * @param managerAdditionDetailsVo
     * @return com.ikang.idata.common.entity.DailyDataAfterCheck
     * @auther jiangfeng.yan
     * @date 2022-11-09 上午 9:58
     */
    public RankingByAdditionList find(ManagerAdditionDetailsVo managerAdditionDetailsVo) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> must = queryBuilder.must();
        CheckUtil.isTrue(StrUtil.isNotEmpty(managerAdditionDetailsVo.getProjectIdNumber()),
                () -> must.add(QueryBuilders.termQuery("projectid_number", managerAdditionDetailsVo.getProjectIdNumber())));
        CheckUtil.isTrue(StrUtil.isNotEmpty(managerAdditionDetailsVo.getChiefZone()),
                () -> must.add(QueryBuilders.termsQuery(ReturnedNotesEnum.chief_zone.getFields(), managerAdditionDetailsVo.getChiefZone().split(COMMA))));
        CheckUtil.isTrue(StrUtil.isNotEmpty(managerAdditionDetailsVo.getAreaZone()),
                () -> must.add(QueryBuilders.termsQuery("area_zone", managerAdditionDetailsVo.getAreaZone().split(COMMA))));
        CheckUtil.isTrue(StrUtil.isNotEmpty(managerAdditionDetailsVo.getBrmZone()),
                () -> must.add(QueryBuilders.termsQuery(ReturnedNotesEnum.brm_zone.getFields(), managerAdditionDetailsVo.getBrmZone().split(COMMA))));
        CheckUtil.isTrue(StrUtil.isNotEmpty(managerAdditionDetailsVo.getProjectZoneType()),
                () -> must.add(QueryBuilders.termsQuery(ReturnedNotesEnum.project_zone_type.getFields(), managerAdditionDetailsVo.getProjectZoneType().split(COMMA))));
        CheckUtil.isTrue(StrUtil.isNotEmpty(managerAdditionDetailsVo.getBorc()),
                () -> must.add(QueryBuilders.termQuery("borc", managerAdditionDetailsVo.getBorc())));
        //到检日期
        if (StringUtils.isNotBlank(managerAdditionDetailsVo.getStartDate()) && StringUtils.isNotBlank(managerAdditionDetailsVo.getEndDate())) {
            must.add(QueryBuilders.rangeQuery("regdate_date")
                    .gte(managerAdditionDetailsVo.getStartDate())
                    .lte(managerAdditionDetailsVo.getEndDate())
                    .includeLower(true)
                    .includeUpper(true));
        }
        builder.query(queryBuilder);
        builder.size(INT_0);
        // 加项排名——按人数（TOP50）
        TermsAggregationBuilder itemNameAggCount = AggregationBuilders.terms("item_name_groupByCount").field("item_name").size(50);

        // 加项排名-按金额（TOP50）
        TermsAggregationBuilder itemNameAggMoney = AggregationBuilders.terms("item_name_groupByMoney").field("item_name").size(50);
        itemNameAggMoney.order(BucketOrder.aggregation("saleprice_sum",  false));
        SumAggregationBuilder aggregationSum = AggregationBuilders.sum("saleprice_sum").field("saleprice");
        itemNameAggMoney.subAggregation(aggregationSum);
        itemNameAggCount.subAggregation(aggregationSum);
        builder.aggregation(itemNameAggCount);
        builder.aggregation(itemNameAggMoney);

        JSONObject jsonObject = HttpClient.esRequest("", builder.toString());
        JSONArray countBuckets = JSONUtil.parseArray(JSONPath.eval(jsonObject, "$.aggregations.item_name_groupByCount.buckets[*]"));
        List<RankingByAddition> countList = countBuckets.stream()
                .map(JSONUtil::parseObj)
                .map(e -> {
                            // {"doc_count":708,"key":"血脂二项","saleprice_sum":{"value":1195259.6499900818}}
                            cn.hutool.json.JSONObject salePriceSum = (cn.hutool.json.JSONObject) e.get("saleprice_sum");
                            Double value = (Double)salePriceSum.get("value");
                            Long docCount = e.getLong(DOC_COUNT);
                            String docCountFormat = Com.POSITIVE_INTEGER_FORMAT.format(new BigDecimal(docCount));
                            String valueFormat = Com.TWO_DECIMAL_PLACE_FORMAT.format(new BigDecimal(value));
                            RankingByAddition rankingByAddition = new RankingByAddition();
                            rankingByAddition.setItemName(e.getStr(KEY));
                            rankingByAddition.setAdditionCount(docCountFormat);
                            rankingByAddition.setAdditionMoney(valueFormat);
                            return rankingByAddition;
                        }
                ).collect(Collectors.toList());
        JSONArray moneyBuckets = JSONUtil.parseArray(JSONPath.eval(jsonObject, "$.aggregations.item_name_groupByMoney.buckets[*]"));
        List<RankingByAddition> moneyList = moneyBuckets.stream()
                .map(JSONUtil::parseObj)
                .map(e -> {
                            // {"doc_count":708,"key":"血脂二项","saleprice_sum":{"value":1195259.6499900818}}
                            cn.hutool.json.JSONObject salePriceSum = (cn.hutool.json.JSONObject) e.get("saleprice_sum");
                            Double value = (Double)salePriceSum.get("value");
                            Long docCount = e.getLong(DOC_COUNT);
                            String docCountFormat = Com.POSITIVE_INTEGER_FORMAT.format(new BigDecimal(docCount));
                            String valueFormat = Com.TWO_DECIMAL_PLACE_FORMAT.format(new BigDecimal(value));
                            RankingByAddition rankingByAddition = new RankingByAddition();
                            rankingByAddition.setItemName(e.getStr(KEY));
                            rankingByAddition.setAdditionCount(docCountFormat);
                            rankingByAddition.setAdditionMoney(valueFormat);
                            return rankingByAddition;
                        }
                ).collect(Collectors.toList());

        RankingByAdditionList rankingByAdditionList = new RankingByAdditionList();
        rankingByAdditionList.setCountList(countList);
        rankingByAdditionList.setMoneyList(moneyList);
        return rankingByAdditionList;
    }
}

