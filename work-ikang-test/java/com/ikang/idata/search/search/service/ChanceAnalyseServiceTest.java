package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.entity.dto.ChanceAnalyseDTO;
import com.ikang.idata.common.enums.BRMResourceEnum;
import com.ikang.idata.common.enums.DeferredEnum;
import com.ikang.idata.common.utils.DateUtil;
import com.ikang.idata.common.utils.DecimalFormatUtil;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.entity.ChanceAnalyseEnum;
import com.ikang.idata.search.search.entity.OrderInfoEnum;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import com.ikang.idata.search.search.util.CheckUtil;
import com.ikang.idata.search.search.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

import static com.ikang.idata.common.consts.MagicConst.INTEGER_0;
import static com.ikang.idata.common.consts.MagicConst.STR_0;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/7/6
 */
@Service
@Slf4j
public class ChanceAnalyseServiceTest {

    /**
     * bi接口-电商看板-实时数据Url
     */
    @Value("${bigdata.url.chanceanalyse.chanceanalyseUrl}")
    private String chanceAnalyseUrl;

    public static String count = "count";

    public static String money = "money";

    public static String allBsuccess = "allBsuccess";

    public static Integer scale = 4;


    public ChanceAnalyseDTO makeDate(WorkTableSearchVo workTableSearchVo) {
        ChanceAnalyseDTO chanceAnalyseDTO = new ChanceAnalyseDTO();
        SearchSourceBuilder searchSourceBuilder = creatSearchSourceBuilderThisDate(workTableSearchVo);
        log.info("机会分析dsl语句：{}", searchSourceBuilder);
        JSONObject thisDateJsonObject = HttpClient.esRequest(chanceAnalyseUrl, searchSourceBuilder.toString());
        Integer allCount = ((LinkedHashMap<String, LinkedHashMap<String, Integer>>) thisDateJsonObject.get(MagicConst.AGGREGATIONS)).get(count).get(MagicConst.VALUE);
        BigDecimal allMoney = BigDecimal.valueOf(Double.parseDouble(((LinkedHashMap<String, LinkedHashMap<String, Object>>) thisDateJsonObject.get(MagicConst.AGGREGATIONS)).get(money).get(MagicConst.VALUE).toString()));
        chanceAnalyseDTO.setChanceNum(Long.valueOf(allCount));
        chanceAnalyseDTO.setChanceMoney(allMoney);
        List<LinkedHashMap<String, LinkedHashMap<String, List<LinkedHashMap>>>> allBsuccess = (((LinkedHashMap<String, LinkedHashMap<String, List<LinkedHashMap<String, LinkedHashMap<String, List<LinkedHashMap>>>>>>)thisDateJsonObject.get(MagicConst.AGGREGATIONS)).get("allBsuccess")).get(MagicConst.BUCKETS);
        List<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap>>> newoldtypeList = (((LinkedHashMap<String, LinkedHashMap<String, List<LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap>>>>>)thisDateJsonObject.get(MagicConst.AGGREGATIONS)).get(ChanceAnalyseEnum.newoldtype.getFields())).get(MagicConst.BUCKETS);
        for (LinkedHashMap<String, LinkedHashMap<String, List<LinkedHashMap>>> allBsuccessDataMap : allBsuccess) {
            int bsuccessType = Integer.parseInt(String.valueOf(allBsuccessDataMap.get(MagicConst.KEY)));
            //  转化数量  与   转化金额   的确定
            if (MagicConst.INTEGER_1.equals(bsuccessType)) {
                //已转化数量
                Long allBsuccessCount =  Long.valueOf(String.valueOf(((LinkedHashMap)allBsuccessDataMap.get(count)).get(MagicConst.VALUE)));
                if (chanceAnalyseDTO.getChanceNum() == null || chanceAnalyseDTO.getChanceNum() == 0) {
                    chanceAnalyseDTO.setChanceNumConversionRate(BigDecimal.ONE);
                } else {
                    chanceAnalyseDTO.setChanceNumConversionRate(BigDecimal.valueOf(allBsuccessCount).divide(BigDecimal.valueOf(chanceAnalyseDTO.getChanceNum()), scale, RoundingMode.HALF_UP));
                }
                //已转化金额
                BigDecimal allBsuccessMoney = DecimalFormatUtil.format2BigDecimal(BigDecimal.valueOf(Double.parseDouble((((LinkedHashMap)allBsuccessDataMap.get(money)).get(MagicConst.VALUE).toString()))));
                if (chanceAnalyseDTO.getChanceMoney() == null || chanceAnalyseDTO.getChanceMoney().compareTo(BigDecimal.ZERO) == 0) {
                    chanceAnalyseDTO.setChanceMoneyConversionRate(BigDecimal.ONE);
                } else {
                    chanceAnalyseDTO.setChanceMoneyConversionRate(allBsuccessMoney.divide(chanceAnalyseDTO.getChanceMoney(), scale, RoundingMode.HALF_UP));
                }
            }
        }
        //新机会金额   新机会个数  得到 (新机会已转化个数  新机会已转化金额)  和  老机会金额   老机会个数  得到  (老机会已转化个数  老机会已转化金额)
        for (LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap>> newoldtypeDataMap : newoldtypeList) {
            if ("201300024773000D7Mod".equals(newoldtypeDataMap.get(MagicConst.KEY))) {
                //新机会金额
                chanceAnalyseDTO.setNewChanceMoney(DecimalFormatUtil.format2BigDecimal(BigDecimal.valueOf(Double.parseDouble(String.valueOf(newoldtypeDataMap.get(money).get(MagicConst.VALUE))))));
                //新机会个数
                chanceAnalyseDTO.setNewChanceNum((Long.valueOf(String.valueOf(newoldtypeDataMap.get(count).get(MagicConst.VALUE)))));
                for (LinkedHashMap bsuccessData : ((List<LinkedHashMap>) newoldtypeDataMap.get(ChanceAnalyseEnum.bsuccess.getFields()).get(MagicConst.BUCKETS))) {
                    Integer bsuccessType = (Integer)bsuccessData.get(MagicConst.KEY);
                    if (MagicConst.INTEGER_1.equals(bsuccessType)) {
                        //新机会已转化个数
                        Long newChanceBsuccessCount = Long.valueOf((Integer)((LinkedHashMap) bsuccessData.get(count)).get(MagicConst.VALUE));
                        if (chanceAnalyseDTO.getNewChanceNum() == null || chanceAnalyseDTO.getNewChanceNum() == 0) {
                            chanceAnalyseDTO.setNewChanceNumConversionRate(BigDecimal.ONE);
                        } else {
                            chanceAnalyseDTO.setNewChanceNumConversionRate(BigDecimal.valueOf(newChanceBsuccessCount).divide(BigDecimal.valueOf(chanceAnalyseDTO.getNewChanceNum()), scale, RoundingMode.HALF_UP));
                        }
                        //新机会已转化金额
                        BigDecimal newChanceBsuccessMoney = BigDecimal.valueOf(Double.parseDouble(((LinkedHashMap) bsuccessData.get(money)).get(MagicConst.VALUE).toString()));
                        if (chanceAnalyseDTO.getNewChanceMoney() == null || chanceAnalyseDTO.getNewChanceMoney().compareTo(BigDecimal.ZERO) == 0) {
                            chanceAnalyseDTO.setNewChanceMoneyConversionRate(BigDecimal.ONE);
                        } else {
                            chanceAnalyseDTO.setNewChanceMoneyConversionRate(newChanceBsuccessMoney.divide(chanceAnalyseDTO.getNewChanceMoney(), scale, RoundingMode.HALF_UP));
                        }
                    }
                }
            } else if ("201300024773024kBJPe".equals(newoldtypeDataMap.get(MagicConst.KEY))) {
                //旧机会金额
                chanceAnalyseDTO.setOldChanceMoney(DecimalFormatUtil.format2BigDecimal(BigDecimal.valueOf(Double.parseDouble(String.valueOf(newoldtypeDataMap.get(money).get(MagicConst.VALUE))))));
                //旧机会个数
                chanceAnalyseDTO.setOldChanceNum(Long.valueOf(String.valueOf(newoldtypeDataMap.get(count).get(MagicConst.VALUE))));
                for (LinkedHashMap bsuccessData : ((List<LinkedHashMap>) newoldtypeDataMap.get(ChanceAnalyseEnum.bsuccess.getFields()).get(MagicConst.BUCKETS))) {
                    Integer bsuccessType = (Integer)bsuccessData.get(MagicConst.KEY);
                    if (MagicConst.INTEGER_1.equals(bsuccessType)) {
                        //老机会已转化金额
                        Long oldChanceBsuccessCount = Long.valueOf((Integer)((LinkedHashMap) bsuccessData.get(count)).get(MagicConst.VALUE));
                        if (chanceAnalyseDTO.getOldChanceNum() == null || chanceAnalyseDTO.getOldChanceNum() == 0) {
                            chanceAnalyseDTO.setOldChanceNumConversionRate(BigDecimal.ONE);
                        } else {
                            chanceAnalyseDTO.setOldChanceNumConversionRate(BigDecimal.valueOf(oldChanceBsuccessCount).divide(BigDecimal.valueOf(chanceAnalyseDTO.getOldChanceNum()), scale, RoundingMode.HALF_UP));
                        }
                        //老机会已转化金额
                        BigDecimal oldChanceBsuccessMoney = BigDecimal.valueOf(Double.parseDouble(((LinkedHashMap) bsuccessData.get(money)).get(MagicConst.VALUE).toString()));
                        if (chanceAnalyseDTO.getOldChanceMoney() == null || chanceAnalyseDTO.getOldChanceMoney().compareTo(BigDecimal.ZERO) == 0) {
                            chanceAnalyseDTO.setOldChanceMoneyConversionRate(BigDecimal.ONE);
                        } else {
                            chanceAnalyseDTO.setOldChanceMoneyConversionRate(oldChanceBsuccessMoney.divide(chanceAnalyseDTO.getOldChanceMoney(), scale, RoundingMode.HALF_UP));
                        }
                    }
                }
            }
        }
        return chanceAnalyseDTO;
    }

    private SearchSourceBuilder creatSearchSourceBuilderThisDate(WorkTableSearchVo orderInfoSearchVo) {
        String brmZone = orderInfoSearchVo.getBrmZone();
        String brmDepartment = orderInfoSearchVo.getBrmDepartment();
        String brmGroup = orderInfoSearchVo.getBrmGroup();
        String startSignDate = orderInfoSearchVo.getStartSignDate();

        String endSignDate = orderInfoSearchVo.getEndSignDate();

        LocalDate startSignDateTime = DateUtil.dateStringToLocalDate(startSignDate);
        LocalDate endSignDateTime = DateUtil.dateStringToLocalDate(endSignDate);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 权限--
        BoolQueryBuilder queryBuilder = setParam(orderInfoSearchVo);
        builder.size(INTEGER_0);

        List<QueryBuilder> must = queryBuilder.must();
        must.add(QueryBuilders.termsQuery(DeferredEnum.DELETED.getFields(), STR_0));
        must.add(QueryBuilders.rangeQuery(ChanceAnalyseEnum.estimated_signtime.getFields())
                .gte(startSignDateTime.format(DateUtil.DTF_DATE_FORMAT_DATETIME_YEAR_MONTH_DAY))
                .lte(endSignDateTime.format(DateUtil.DTF_DATE_FORMAT_DATETIME_YEAR_MONTH_DAY))
                .includeLower(true)
                .includeUpper(true));

        CheckUtil.isTrue(StringUtil.isNotEmpty(brmZone),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.brm_zone.getFields(), brmZone.split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(brmDepartment),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.brm_department.getFields(), brmDepartment.split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(brmGroup),
                () -> must.add(QueryBuilders.termsQuery(OrderInfoEnum.brm_group.getFields(), brmGroup.split(MagicConst.COMMA_SPLIT))));

        TermsAggregationBuilder allBsuccessAggregationBuilder = AggregationBuilders.terms(allBsuccess).field(ChanceAnalyseEnum.bsuccess.getFields());
        allBsuccessAggregationBuilder.subAggregation(AggregationBuilders.sum(money).field(ChanceAnalyseEnum.estimated_signmoney.getFields()));
        allBsuccessAggregationBuilder.subAggregation(AggregationBuilders.count(count).field(ChanceAnalyseEnum.id.getFields()));

        TermsAggregationBuilder newoldtypeAggregationBuilder = AggregationBuilders.terms(ChanceAnalyseEnum.newoldtype.getFields()).field(ChanceAnalyseEnum.newoldtype.getFields());
        newoldtypeAggregationBuilder.subAggregation(AggregationBuilders.sum(money).field(ChanceAnalyseEnum.estimated_signmoney.getFields()));
        newoldtypeAggregationBuilder.subAggregation(AggregationBuilders.count(count).field(ChanceAnalyseEnum.id.getFields()));

        TermsAggregationBuilder bsuccessAggregationBuilder = AggregationBuilders.terms(ChanceAnalyseEnum.bsuccess.getFields()).field(ChanceAnalyseEnum.bsuccess.getFields());
        bsuccessAggregationBuilder.subAggregation(AggregationBuilders.sum(money).field(ChanceAnalyseEnum.estimated_signmoney.getFields()));
        bsuccessAggregationBuilder.subAggregation(AggregationBuilders.count(count).field(ChanceAnalyseEnum.id.getFields()));

        newoldtypeAggregationBuilder.subAggregation(bsuccessAggregationBuilder);
        builder.aggregation(AggregationBuilders.sum(money).field(ChanceAnalyseEnum.estimated_signmoney.getFields()));
        builder.aggregation(AggregationBuilders.count(count).field(ChanceAnalyseEnum.id.getFields()));
        builder.aggregation(allBsuccessAggregationBuilder);
        builder.aggregation(newoldtypeAggregationBuilder);
        builder.query(queryBuilder);
        return builder;
    }

    private BoolQueryBuilder setParam(WorkTableSearchVo searchVo) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> must = queryBuilder.must();
        // 区总区域
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVo.getChiefZone()),
                () -> must.add(QueryBuilders.termsQuery(BRMResourceEnum.CHIEFZONE.getFields(), searchVo.getChiefZone().split(MagicConst.COMMA))));
        //brmUserId
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVo.getBrmUserId()),
                () -> must.add(QueryBuilders.wildcardQuery(BRMResourceEnum.LEADERID.getFields(),"*<"+searchVo.getBrmUserId()+">*")));
        return queryBuilder;
    }

    /**
     * GET /chanceanalyse/_search
     * {
     *   "size": 0,
     *   "query": {
     *     "bool": {
     *       "must": [
     *         {
     *           "wildcard": {
     *             "leaderid": {
     *               "wildcard": "*<005201431264519QCxhj>*",
     *               "boost": 1
     *             }
     *           }
     *         },
     *         {
     *           "range": {
     *             "create_time": {
     *               "from": "2021-03-15",
     *               "to": "2022-03-15",
     *               "include_lower": true,
     *               "include_upper": true,
     *               "boost": 1
     *             }
     *           }
     *         },
     *         {
     *           "terms": {
     *             "brm_zone": [
     *               "沈阳区"
     *             ],
     *             "boost": 1
     *           }
     *         },
     *         {
     *           "terms": {
     *             "brm_department": [
     *               "沈阳区销售二部"
     *             ],
     *             "boost": 1
     *           }
     *         },
     *         {
     *           "terms": {
     *             "brm_group": [
     *               "销售二部（行业）"
     *             ],
     *             "boost": 1
     *           }
     *         }
     *       ],
     *       "adjust_pure_negative": true,
     *       "boost": 1
     *     }
     *   },
     *   "aggregations": {
     *     "money": {
     *       "sum": {
     *         "field": "estimated_signmoney"
     *       }
     *     },
     *     "count": {
     *       "value_count": {
     *         "field": "id"
     *       }
     *     },
     *     "allBsuccess": {
     *       "terms": {
     *         "field": "bsuccess",
     *         "size": 10,
     *         "min_doc_count": 1,
     *         "shard_min_doc_count": 0,
     *         "show_term_doc_count_error": false,
     *         "order": [
     *           {
     *             "_count": "desc"
     *           },
     *           {
     *             "_key": "asc"
     *           }
     *         ]
     *       },
     *       "aggregations": {
     *         "money": {
     *           "sum": {
     *             "field": "estimated_signmoney"
     *           }
     *         },
     *         "count": {
     *           "value_count": {
     *             "field": "id"
     *           }
     *         }
     *       }
     *     },
     *     "newoldtype": {
     *       "terms": {
     *         "field": "newoldtype",
     *         "size": 10,
     *         "min_doc_count": 1,
     *         "shard_min_doc_count": 0,
     *         "show_term_doc_count_error": false,
     *         "order": [
     *           {
     *             "_count": "desc"
     *           },
     *           {
     *             "_key": "asc"
     *           }
     *         ]
     *       },
     *       "aggregations": {
     *         "money": {
     *           "sum": {
     *             "field": "estimated_signmoney"
     *           }
     *         },
     *         "count": {
     *           "value_count": {
     *             "field": "id"
     *           }
     *         },
     *         "bsuccess": {
     *           "terms": {
     *             "field": "bsuccess",
     *             "size": 10,
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
     *             "money": {
     *               "sum": {
     *                 "field": "estimated_signmoney"
     *               }
     *             },
     *             "count": {
     *               "value_count": {
     *                 "field": "id"
     *               }
     *             }
     *           }
     *         }
     *       }
     *     }
     *   }
     * }
     */

}
