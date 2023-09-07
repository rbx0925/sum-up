package com.ikang.idata.search.search.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.vo.ProjectSigningVO;
import com.ikang.idata.common.enums.ProjectSigningEnum;
import com.ikang.idata.search.search.entity.OpportunityAnalysisEnum;
import com.ikang.idata.search.search.entity.vo.TenderSearchVo;
import com.ikang.idata.search.search.entity.vo.WinBidAnalysisResultVo;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.ikang.idata.common.consts.MagicConst.*;
import static com.ikang.idata.common.consts.MagicConst.VALUE;
import static org.assertj.core.api.Assertions.assertThat;

class ProjectSigningServiceTest {

    private ProjectSigningService projectSigningServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        projectSigningServiceUnderTest = new ProjectSigningService();
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = projectSigningServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final ProjectSigningVO vo = new ProjectSigningVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setDesensitization(false);
        vo.setChiefZone("chiefZone");
        vo.setBrmUserId("brmUserId");
        vo.setProjectZoneType("projectZoneType");
        vo.setBrmZone("brmZone");
        vo.setBrmDepartment("brmDepartment");
        vo.setBrmGroup("brmGroup");

        // Run the test
        final BaseSearch result = projectSigningServiceUnderTest.find(vo);

        // Verify the results
    }

    @Test
    void testFindGroupBy() throws Exception {
        // Setup
        final ProjectSigningVO vo = new ProjectSigningVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        vo.setDesensitization(false);
        vo.setChiefZone("chiefZone");
        vo.setBrmUserId("brmUserId");
        vo.setProjectZoneType("projectZoneType");
        vo.setBrmZone("brmZone");
        vo.setBrmDepartment("brmDepartment");
        vo.setBrmGroup("brmGroup");

        // Run the test
        final BaseSearch result = projectSigningServiceUnderTest.findGroupBy(vo);

        // Verify the results
    }

    @Test
    void testWinBidAnalysis() {
        // Setup
        final TenderSearchVo vo = new TenderSearchVo();
        vo.setChiefZone("chiefZone");
        vo.setProjectZoneType("projectZoneType");
        vo.setBrmZone("brmZone");
        vo.setBrmDepartment("brmDepartment");
        vo.setBrmGroup("brmGroup");
        vo.setEstimatedSigntimeStart("estimatedSigntimeStart");
        vo.setEstimatedSigntimeEnd("estimatedSigntimeEnd");

        final WinBidAnalysisResultVo expectedResult = new WinBidAnalysisResultVo();
        expectedResult.setWinBidNum(new BigDecimal("0.00"));
        expectedResult.setTenderNum(new BigDecimal("0.00"));
        expectedResult.setWinBidMoney(new BigDecimal("0.00"));
        expectedResult.setTenderMoney(new BigDecimal("0.00"));
        expectedResult.setEnterpriseNatureBidNum(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        expectedResult.setEnterpriseNatureBidMoney(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        expectedResult.setScaleBidNum(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        expectedResult.setScaleBidMoney(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        expectedResult.setNatureOfTheBidNum(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        expectedResult.setNatureOfTheBidMoney(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        expectedResult.setCustomerNatureMoney(Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))));
        expectedResult.setTenderSituationMoney(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        expectedResult.setSamePeriod(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));
        expectedResult.setBidMoney(
                Arrays.asList(new Pair<>("key", Arrays.asList(new Pair<>("key", new BigDecimal("0.00"))))));

        // Run the test
        final WinBidAnalysisResultVo result = projectSigningServiceUnderTest.winBidAnalysis(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    /**
     * 解析查询结果
     *
     * @param object 大数据返回结果
     * @return 前端结果
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date 2023/7/4 18:25
     */
    private WinBidAnalysisResultVo resolveByOpportunityAnalysis(JSONObject object) {
        WinBidAnalysisResultVo vo = new WinBidAnalysisResultVo();

        cn.hutool.json.JSONObject tenderData = JSONUtil.parseObj(JSONPath.eval(object, "$.aggregations.estimatedSigningTimeFilter.sfzb.buckets[?(@.key=='是')][0]"));
        //招标个数
        vo.setTenderNum(tenderData.getBigDecimal(DOC_COUNT));
        //招标项目金额
        vo.setTenderMoney(Optional.ofNullable(tenderData.getJSONObject(OpportunityAnalysisEnum.estimated_signmoney.getFields())).map(jsonObject -> jsonObject.getBigDecimal(VALUE)).orElse(BigDecimal.ZERO));

        cn.hutool.json.JSONObject winningData = JSONUtil.parseObj(JSONPath.eval(object, "$.aggregations.estimatedSigningTimeFilter.current_stage.buckets[?(@.key=='5-已签单')]"));
        //中标项目个数
        vo.setWinBidNum(winningData.getBigDecimal(DOC_COUNT));
        //中标项目金额
        vo.setWinBidMoney(Optional.ofNullable(winningData.getJSONObject(OpportunityAnalysisEnum.xmje.getFields())).map(jsonObject -> jsonObject.getBigDecimal(VALUE)).orElse(BigDecimal.ZERO));


        JSONArray winBidCustomerType = JSONUtil.parseArray(JSONPath.eval(object, "$.aggregations.estimatedSigningTimeFilter.sfzb.buckets[?(@.key=='是')].customer_type.buckets[*]"));
        JSONArray unWinBidCustomerType = JSONUtil.parseArray(JSONPath.eval(object, "$.aggregations.estimatedSigningTimeFilter.sfzb.buckets[?(@.key=='否')].customer_type.buckets[*]"));

        //各企业性质单位的招标项目占比（个数）
        Pair<String, List<Pair<String, BigDecimal>>> bidProject = Pair.of("招标项目", winBidCustomerType.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY), jsonObject.getBigDecimal(DOC_COUNT)))
                .collect(Collectors.toList()));
        Pair<String, List<Pair<String, BigDecimal>>> unBidProject = Pair.of("非招标项目", unWinBidCustomerType.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY), jsonObject.getBigDecimal(DOC_COUNT)))
                .collect(Collectors.toList()));
        //补齐
        stuff(bidProject, unBidProject);
        vo.setEnterpriseNatureBidNum(ListUtil.of(bidProject, unBidProject));


        //各企业性质单位的招标项目占比（金额）
        Pair<String, List<Pair<String, BigDecimal>>> bidProjectMoney = Pair.of("招标项目", winBidCustomerType.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY), jsonObject.getJSONObject(OpportunityAnalysisEnum.estimated_signmoney.getFields()).getBigDecimal(VALUE).setScale(2, BigDecimal.ROUND_HALF_UP)))
                .collect(Collectors.toList()));
        Pair<String, List<Pair<String, BigDecimal>>> unBidProjectMoney = Pair.of("非招标项目", unWinBidCustomerType.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY), jsonObject.getJSONObject(OpportunityAnalysisEnum.estimated_signmoney.getFields()).getBigDecimal(VALUE).setScale(2, BigDecimal.ROUND_HALF_UP)))
                .collect(Collectors.toList()));
        stuff(bidProjectMoney, unBidProjectMoney);
        vo.setEnterpriseNatureBidMoney(ListUtil.of(bidProjectMoney, unBidProjectMoney));


        JSONArray winBidScale = JSONUtil.parseArray(JSONPath.eval(object, "$.aggregations.estimatedSigningTimeFilter.sfzb.buckets[?(@.key=='是')].estimated_signscale.buckets[*]"));
        JSONArray unWinBidScale = JSONUtil.parseArray(JSONPath.eval(object, "$.aggregations.estimatedSigningTimeFilter.sfzb.buckets[?(@.key=='否')].estimated_signscale.buckets[*]"));


        //各项目规模的招标项目占比（个数）
        Pair<String, List<Pair<String, BigDecimal>>> bidProjectScaleNum = Pair.of("招标项目", winBidScale.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY), jsonObject.getBigDecimal(DOC_COUNT)))
                .collect(Collectors.toList()));
        Pair<String, List<Pair<String, BigDecimal>>> unBidProjectScaleNum = Pair.of("非招标项目", unWinBidScale.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY), jsonObject.getBigDecimal(DOC_COUNT)))
                .collect(Collectors.toList()));
        stuff(bidProjectScaleNum, unBidProjectScaleNum);
        vo.setScaleBidNum(ListUtil.of(bidProjectScaleNum, unBidProjectScaleNum));

        //各项目规模的招标项目占比（金额）
        Pair<String, List<Pair<String, BigDecimal>>> bidProjectScaleMoney = Pair.of("招标项目", winBidScale.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY), jsonObject.getJSONObject(OpportunityAnalysisEnum.estimated_signmoney.getFields()).getBigDecimal(VALUE).setScale(2, BigDecimal.ROUND_HALF_UP)))
                .collect(Collectors.toList()));
        Pair<String, List<Pair<String, BigDecimal>>> unBidProjectScaleMoney = Pair.of("非招标项目", unWinBidScale.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY), jsonObject.getJSONObject(OpportunityAnalysisEnum.estimated_signmoney.getFields()).getBigDecimal(VALUE).setScale(2, BigDecimal.ROUND_HALF_UP)))
                .collect(Collectors.toList()));
        stuff(bidProjectScaleMoney, unBidProjectScaleMoney);
        vo.setScaleBidMoney(ListUtil.of(bidProjectScaleMoney, unBidProjectScaleMoney));


        //中标项目构成——按项目个数
        JSONArray natureOfTender = JSONUtil.parseArray(JSONPath.eval(object, "$.aggregations.estimatedSigningTimeFilter.current_stage.buckets[?(@.key=='5-已签单')].zblx.buckets[*]"));
        List<Pair<String, BigDecimal>> natureOfTheBid = natureOfTender.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY), jsonObject.getBigDecimal(DOC_COUNT)))
                .collect(Collectors.toList());
        vo.setNatureOfTheBidNum(natureOfTheBid);

        //中标项目构成——按项目金额
        List<Pair<String, BigDecimal>> natureOfTheBidMoney = natureOfTender.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY), jsonObject.getJSONObject(OpportunityAnalysisEnum.xmje.getFields()).getBigDecimal(VALUE).setScale(2, BigDecimal.ROUND_HALF_UP)))
                .collect(Collectors.toList());
        vo.setNatureOfTheBidMoney(natureOfTheBidMoney);

        //中标项目客户性质占比（金额）
        JSONArray natureOfTheWinningProject = JSONUtil.parseArray(JSONPath.eval(object, "$.aggregations.estimatedSigningTimeFilter.current_stage.buckets[?(@.key=='5-已签单')].customer_type.buckets[*]"));
        vo.setCustomerNatureMoney(natureOfTheWinningProject.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY), jsonObject.getJSONObject(OpportunityAnalysisEnum.xmje.getFields()).getBigDecimal(VALUE)))
                .collect(Collectors.toList()));


        //中标项目中公开招标&内部招标项目情况（金额）
        List<Pair<String, List<Pair<String, BigDecimal>>>> bidAmount = natureOfTender.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY),
                        jsonObject.getJSONObject(OpportunityAnalysisEnum.customer_type.getFields()).getJSONArray(BUCKETS).stream().map(JSONUtil::parseObj)
                                .map(jsonObj -> Pair.of(jsonObj.getStr(KEY), jsonObj.getJSONObject(OpportunityAnalysisEnum.xmje.getFields()).getBigDecimal(VALUE).setScale(2, BigDecimal.ROUND_HALF_UP)))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
        vo.setTenderSituationMoney(bidAmount);

        //中标项目按等级分析（金额）
        JSONArray rankData = JSONUtil.parseArray(JSONPath.eval(object, "$.aggregations.current_stage.buckets[?(@.key=='5-已签单')].estimated_signscale.buckets[*]"));
        List<Pair<String, List<Pair<String, BigDecimal>>>> samePeriod = rankData.stream().map(JSONUtil::parseObj)
                .map(jsonObject -> Pair.of(jsonObject.getStr(KEY),
                        jsonObject.getJSONObject("同期当期").getJSONArray(BUCKETS).stream().map(JSONUtil::parseObj)
                                .map(jobj -> Pair.of(jobj.getStr(KEY), jobj.getJSONObject(ProjectSigningEnum.xmje.getFields()).getBigDecimal(VALUE).setScale(2, BigDecimal.ROUND_HALF_UP)))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
        vo.setSamePeriod(samePeriod);

        //招标项目占整体已签单项目比例（金额）
        vo.setBidMoney(vo.getScaleBidMoney());

        return vo;
    }
    /**
     * 补齐
     *
     * @param bidProject
     * @param unBidProject
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date 2022/12/5 11:23
     */
    private void stuff(Pair<String, List<Pair<String, BigDecimal>>> bidProject, Pair<String, List<Pair<String, BigDecimal>>> unBidProject) {
        Set<String> keySetFirst = bidProject.getValue().stream().map(Pair::getKey).collect(Collectors.toSet());
        Set<String> keySetSecond = unBidProject.getValue().stream().map(Pair::getKey).collect(Collectors.toSet());
        Set<String> set = CollUtil.unionDistinct(keySetFirst, keySetSecond);
        bidProject.getValue().addAll(CollUtil.disjunction(set, keySetFirst).stream().map(s -> Pair.of(s, BigDecimal.ZERO)).collect(Collectors.toList()));
        unBidProject.getValue().addAll(CollUtil.disjunction(set, keySetSecond).stream().map(s -> Pair.of(s, BigDecimal.ZERO)).collect(Collectors.toList()));
    }
}
