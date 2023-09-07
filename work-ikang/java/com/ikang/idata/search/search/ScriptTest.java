package com.ikang.idata.search.search;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.utils.CheckUtil;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.entity.OrderInfoEnum;
import com.ikang.idata.search.search.entity.vo.SignResultTableVo;
import com.ikang.idata.search.search.entity.vo.WorkSearchVo;
import com.ikang.idata.search.search.util.HttpClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregator;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import static com.ikang.idata.common.consts.MagicConst.*;
import static java.math.BigDecimal.ZERO;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/5/9
 */
@Service
public class ScriptTest {

    //("分组条件：brm_department、brm_group、ownerid")
    private String groupBy;
    //ES Script语句  签单分析案例分析


        public Script getScript(String groupBy) {
            StringBuilder sb = new StringBuilder();
            //锁定按区域 该区域的部门里面的小组的销售人员
            sb.append("doc['brm_zone'].value +','+ ");
            if ("brm_department".equals(groupBy)) {
                //第一种情况  按部门 则要加上部门这个条件
                //"source": "doc['brm_zone'].value +','+ doc['brm_department'].value",
                sb.append("doc['brm_department'].value");
            }else if ("brm_group".equals(groupBy)) {
                //第二种情况  按部门 则要加上部门/小组两个条件
                sb.append("doc['brm_department'].value +','+ doc['brm_group'].value");
            }else {
                //第三种情况  按销售姓名 则要加上部门+小组+销售姓名两个条件
                sb.append("doc['brm_department'].value +','+ doc['brm_group'].value +','+ doc['name'].value");
            }
            System.out.println("sb = " + sb);
            return new Script(sb.toString());
        }
    /**
     * DSL语句
     * GET /signinformation/_search
     * {
     *   "size": 0,
     *   "query": {
     *     "bool": {
     *       "must": [
     *         {
     *           "terms": {
     *             "brm_zone": [
     *               "北京区"
     *             ],
     *             "boost": 1
     *           }
     *         }
     *       ],
     *       "must_not": [
     *         {
     *           "exists": {
     *             "field": "brm_group",
     *             "boost": 1
     *           }
     *         }
     *       ],
     *       "adjust_pure_negative": true,
     *       "boost": 1
     *     }
     *   },
     *   "aggregations": {
     *     "sign_date": {
     *       "range": {
     *         "field": "sign_date",
     *         "ranges": [
     *           {
     *             "key": "同期",
     *             "from": "2021-03-21",
     *             "to": "2021-04-22"
     *           },
     *           {
     *             "key": "当期",
     *             "from": "2022-03-21",
     *             "to": "2022-04-22"
     *           }
     *         ],
     *         "keyed": false
     *       },
     *       "aggregations": {
     *         "brm_department": {
     *           "terms": {
     *             "script": {
     *               "source": "doc['brm_zone'].value +','+ doc['brm_department'].value",
     *               "lang": "painless"
     *             },
     *             "size": 10000,
     *             "min_doc_count": 1,
     *             "shard_min_doc_count": 0,
     *             "show_term_doc_count_error": false,
     *             "order": [
     *               {
     *                 "_count": "desc"
     *               },
     *               {
     *                 "_key": "asc"
     *               }
     *             ]
     *           },
     *           "aggregations": {
     *             "sign_money": {
     *               "sum": {
     *                 "field": "sign_money"
     *               }
     *             },
     *             "sign_project_id": {
     *               "value_count": {
     *                 "field": "sign_project_id"
     *               }
     *             },
     *             "newoldtype": {
     *               "terms": {
     *                 "field": "newoldtype",
     *                 "size": 10000,
     *                 "min_doc_count": 1,
     *                 "shard_min_doc_count": 0,
     *                 "show_term_doc_count_error": false,
     *                 "order": [
     *                   {
     *                     "_count": "desc"
     *                   },
     *                   {
     *                     "_key": "asc"
     *                   }
     *                 ]
     *               },
     *               "aggregations": {
     *                 "sign_money": {
     *                   "sum": {
     *                     "field": "sign_money"
     *                   }
     *                 },
     *                 "sign_project_id": {
     *                   "value_count": {
     *                     "field": "sign_project_id"
     *                   }
     *                 }
     *               }
     *             }
     *           }
     *         }
     *       }
     *     }
     *   }
     * }
     */


        @Value("${kanban.signinformation.searchUrl}")
         private String signinformationUrl;

        //关于签单分析的理解
        public List<SignResultTableVo> find(WorkSearchVo searchVO) {
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
        Script script = getScript(searchVO.getGroupBy());
        TermsAggregationBuilder groupByField = AggregationBuilders.terms(searchVO.getGroupBy())
                .script(script).size(PAGE_SIZE_10000);
        groupByField.subAggregation(sumSignMoney);
        groupByField.subAggregation(signProjectIdCount);
        rangeAgg.subAggregation(groupByField);

        builder.aggregation(rangeAgg);

        // 新/老
        TermsAggregationBuilder newOldAgg = AggregationBuilders.terms(OrderInfoEnum.newoldtype.getFields()).field(OrderInfoEnum.newoldtype.getFields()).size(PAGE_SIZE_10000);
        newOldAgg.subAggregation(sumSignMoney);
        newOldAgg.subAggregation(signProjectIdCount);
        groupByField.subAggregation(newOldAgg);
        //请求es
        JSONObject object = HttpClient.esRequest(signinformationUrl, builder.toString());
        //解析

        JSONArray currentList = (JSONArray) JSONObject.toJSON(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='当期')]." + searchVO.getGroupBy() + ".buckets"));
        JSONArray correspondingList = (JSONArray) JSONObject.toJSON(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='同期')]." + searchVO.getGroupBy() + ".buckets"));

        List<String> currentKeys = JSONUtil.parseArray(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='当期')]." + searchVO.getGroupBy() + ".buckets[*].key")).toList(String.class);

        List<String> correspondingKeys = JSONUtil.parseArray(JSONPath.eval(object, "$.aggregations.sign_date.buckets[?(@.key=='同期')]." + searchVO.getGroupBy() + ".buckets[*].key")).toList(String.class);

        List<String> union = (List) CollectionUtil.union(currentKeys, correspondingKeys);

        List<SignResultTableVo> list = new ArrayList<>();
        for (int i = 0; i < union.size(); i++) {
            String str = union.get(i);
            SignResultTableVo result = new SignResultTableVo();
            setBrmInfo(searchVO.getGroupBy(), result, str);
            JSONObject currentJson = new JSONObject();
            JSONObject correspondingJson = new JSONObject();
            Optional<Object> key = currentList.stream().filter(Objects::nonNull)
                    .map(JSON::toJSON)
                    .filter(e -> str.equals( ((JSONObject) e).get("key").toString())).findFirst();
            if (key.isPresent()) {
                currentJson = (JSONObject)JSON.toJSON(key.get());
            }
            Optional<Object> key2 = correspondingList.stream()
                    .filter(Objects::nonNull)
                    .map(JSON::toJSON)
                    .filter(e -> str.equals( ((JSONObject) e).get("key").toString())).findFirst();
            if (key2.isPresent()) {
                correspondingJson = (JSONObject)JSON.toJSON(key2.get());
            }
            // 签单个数
            BigDecimal project = ZERO;
            JSONObject currentSignProject = currentJson.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            if (null != currentSignProject) {
                project = currentSignProject.getBigDecimal(VALUE);
            }
            result.setSignProjectIdCount(project);

            // 签单金额
            BigDecimal money = ZERO;
            JSONObject currentSignMoney = currentJson.getJSONObject(OrderInfoEnum.sign_money.getFields());
            if (null != currentSignMoney) {
                money = currentSignMoney.getBigDecimal(VALUE);
            }
            result.setSignMoneySum(money);


            JSONObject jsonObject = currentJson.getJSONObject(OrderInfoEnum.newoldtype.getFields());
            JSONObject newType = new JSONObject();
            JSONObject oldType = new JSONObject();

            JSONArray newObjects = (JSONArray) JSONObject.toJSON(JSONPath.eval(jsonObject, "$.buckets[*][?(@.key=='新项目')]"));
            JSONArray oldObjects = (JSONArray) JSONObject.toJSON(JSONPath.eval(jsonObject, "$.buckets[*][?(@.key=='老项目')]"));
            if (null != newObjects && newObjects.size() > 0) {
                newType = newObjects.getJSONObject(0);
            }
            if (null != oldObjects && oldObjects.size() > 0) {
                oldType = oldObjects.getJSONObject(0);
            }

            JSONObject jsonNewProject = newType.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            JSONObject jsonNewMoney = newType.getJSONObject(OrderInfoEnum.sign_money.getFields());
            BigDecimal newProject = ZERO;
            BigDecimal newMoney = ZERO;
            if (null != jsonNewProject) {
                newProject = jsonNewProject.getBigDecimal(VALUE);
            }
            result.setNewSignProjectIdCount(newProject);
            if (null != jsonNewMoney) {
                newMoney = jsonNewMoney.getBigDecimal(VALUE);
            }
            result.setNewSignMoneySum(newMoney);
            JSONObject jsonOldProject = oldType.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            JSONObject jsonOldMoney = oldType.getJSONObject(OrderInfoEnum.sign_money.getFields());
            BigDecimal oldProject = ZERO;
            BigDecimal oldMoney = ZERO;
            if (null != jsonOldProject) {
                oldProject = jsonOldProject.getBigDecimal(VALUE);
            }
            result.setOldSignProjectIdCount(oldProject);
            if (null != jsonOldMoney) {
                oldMoney = jsonOldMoney.getBigDecimal(VALUE);
            }
            result.setOldSignMoneySum(oldMoney);
            // 同期签单个数
            BigDecimal projectBefore = ZERO;
            JSONObject correspondingSignProject = correspondingJson.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            if (null != correspondingSignProject) {
                projectBefore = correspondingSignProject.getBigDecimal(VALUE);
            }
            result.setSignProjectIdCountLastYear(projectBefore);

            // 同期签单金额
            BigDecimal moneyBefore = ZERO;
            JSONObject correspondingSignMoney = correspondingJson.getJSONObject(OrderInfoEnum.sign_money.getFields());
            if (null != correspondingSignMoney) {
                moneyBefore = correspondingSignMoney.getBigDecimal(VALUE);
            }
            result.setSignMoneySumLastYear(moneyBefore);

            JSONObject jsonObjectCorr = correspondingJson.getJSONObject(OrderInfoEnum.newoldtype.getFields());
            JSONObject newTypeCorr = new JSONObject();
            JSONObject oldTypeCorr = new JSONObject();

            JSONArray newObjectsCorr = (JSONArray) JSONObject.toJSON(JSONPath.eval(jsonObjectCorr, "$.buckets[*][?(@.key=='新项目')]"));
            JSONArray oldObjectsCorr = (JSONArray) JSONObject.toJSON(JSONPath.eval(jsonObjectCorr, "$.buckets[*][?(@.key=='老项目')]"));
            if (null != newObjectsCorr && newObjectsCorr.size() > 0) {
                newTypeCorr = newObjectsCorr.getJSONObject(0);
            }
            if (null != oldObjectsCorr && oldObjectsCorr.size() > 0) {
                oldTypeCorr = oldObjectsCorr.getJSONObject(0);
            }

            JSONObject jsonNewProjectCorr = newTypeCorr.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            JSONObject jsonNewMoneyCorr = newTypeCorr.getJSONObject(OrderInfoEnum.sign_money.getFields());
            BigDecimal newProjectCorr = ZERO;
            BigDecimal newMoneyCorr = ZERO;
            if (null != jsonNewProjectCorr) {
                newProjectCorr = jsonNewProjectCorr.getBigDecimal(VALUE);
            }
            result.setNewSignProjectIdCountLastYear(newProjectCorr);
            if (null != jsonNewMoneyCorr) {
                newMoneyCorr = jsonNewMoneyCorr.getBigDecimal(VALUE);
            }
            result.setNewSignMoneySumLastYear(newMoneyCorr);
            JSONObject jsonOldProjectCorr = oldTypeCorr.getJSONObject(OrderInfoEnum.sign_project_id.getFields());
            JSONObject jsonOldMoneyCorr = oldTypeCorr.getJSONObject(OrderInfoEnum.sign_money.getFields());
            BigDecimal oldProjectCorr = ZERO;
            BigDecimal oldMoneyCorr = ZERO;
            if (null != jsonOldProjectCorr) {
                oldProjectCorr = jsonOldProjectCorr.getBigDecimal(VALUE);
            }
            result.setOldSignProjectIdCountLastYear(oldProjectCorr);
            if (null != jsonOldMoneyCorr) {
                oldMoneyCorr = jsonOldMoneyCorr.getBigDecimal(VALUE);
            }
            result.setOldSignMoneySumLastYear(oldMoneyCorr);

            list.add(result);
        }
        return list;
    }

    private SearchSourceBuilder getSearchSourceBuilder(WorkSearchVo searchVO, Consumer<List<QueryBuilder>> consumer) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(MagicConst.INT_0);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        List<QueryBuilder> must = boolQuery.must();
        if ("brm_group".equals(searchVO.getGroupBy())) {
            must.add(QueryBuilders.existsQuery("brm_group"));
        }
        //拼接条件
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmZone()),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.brm_zone.getFields(), searchVO.getBrmZone().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmDepartment()),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.brm_department.getFields(), searchVO.getBrmDepartment().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmGroup()),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.brm_group.getFields(), searchVO.getBrmGroup().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getOwnerNames()),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.ownerid.getFields(), searchVO.getOwnerNames().split(COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmUserId()),
                () -> must.add(QueryBuilders.wildcardQuery(OrderInfoEnum.leaderid.getFields(), ("*<" + searchVO.getBrmUserId() + ">*"))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getChiefZone()),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.chief_zone.getFields(), searchVO.getChiefZone().split(COMMA_SPLIT))));
        consumer.accept(must);
        builder.query(boolQuery);
        return builder;
    }

    private void setBrmInfo(String groupBy, SignResultTableVo result, String info) {
        result.setBrmZone(info.split(MagicConst.COMMA_SPLIT)[0]);
        if ("brm_department".equals(groupBy)) {
            result.setBrmDepartment(info.split(MagicConst.COMMA_SPLIT)[1]);
        }else if ("brm_group".equals(groupBy)) {
            result.setBrmDepartment(info.split(MagicConst.COMMA_SPLIT)[1]);
            result.setBrmGroup(info.split(MagicConst.COMMA_SPLIT)[2]);
        }else {
            result.setBrmDepartment(info.split(MagicConst.COMMA_SPLIT)[1]);
            result.setBrmGroup(info.split(MagicConst.COMMA_SPLIT)[2]);
            result.setOwnerName(info.split(MagicConst.COMMA_SPLIT)[3]);
        }
    }


/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description 脚本测试
 * @date 2022/5/7 14:35
 */
    public static void main(String[] args) {
        Script script = new Script("xxxx");
        System.out.println(script);
    }
}
