package com.ikang.idata.search.search.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.entity.*;
import com.ikang.idata.common.entity.es.CheckItemEntryData;
import com.ikang.idata.common.entity.es.CheckItemEntryHits;
import com.ikang.idata.common.entity.es.ESBaseData;
import com.ikang.idata.common.entity.vo.*;
import com.ikang.idata.common.enums.ESIndexEnum;
import com.ikang.idata.common.enums.UserDataPermissionEnum;
import com.ikang.idata.common.enums.UserPermissionEnum;
import com.ikang.idata.common.enums.UserSearchTwoEnum;
import com.ikang.idata.common.exceptions.BusinessException;
import com.ikang.idata.common.utils.DecimalFormatUtil;
import com.ikang.idata.common.utils.IdataSecurityUtil;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.common.Com;
import com.ikang.idata.search.search.entity.vo.Statistics;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.DictFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.ParamDataFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.ReturnDataFeignServiceImpl;
import com.ikang.idata.search.search.util.CheckUtil;
import com.ikang.idata.search.search.util.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

import static com.ikang.idata.common.consts.MagicConst.*;
import static com.ikang.idata.common.consts.MagicConst.DATA;
import static com.ikang.idata.common.enums.UserSearchFieldsEnum.*;

/**
 * @author rbx
 * @title
 * @Create 2023-07-26 15:32
 * @Description
 */
@Slf4j
@Service
public class userSearchForImpala {


    @Value("${spring.mail.username}")
    String sender;
    /**
     * bi接口-查询2c用户登录信息url
     */
    @Value("${bigdata.url.userSearchService.searchUrl}")
    private String searchUrl;

    @Value("${bigdata.url.userSearchService.searchGroupUrl}")
    private String searchGroupUrl;


    @Value("${op2c.sms.price:0.04}")
    Double smsPrice;

    /**
     * 2c明细体检的科室诊断关系表
     */
    @Value("${bigdata.url.satisfiedHospitalService.checkitemUrl}")
    private String checkitemUrl;

    /**
     * bi接口-2c用户登录信息url
     */
    @Value("${bigdata.url.userSearchTwo.searchUrlTwo}")
    private String searchUrlTwo;

    @Autowired
    private ESHttpClientService esHttpClientService;

    @Autowired
    private ReturnDataFeignServiceImpl returnDataFeignService;

    @Autowired
    private DictFeignServiceImpl dictFeignService;

    @Autowired
    private AuthorityFeignServiceImpl authorityFeignService;

    @Autowired
    protected ParamDataFeignServiceImpl paramDataFeignService;


    /**
     * 方法描述: 用户查询-时间和来源查询
     *
     * @param queryDsl dsl
     * @return  com.ikang.idata.common.entity.UserSearchTwo
     * @author  wenyue.gao@ikang.com
     * @date    2022/2/25 13:50
     */
    public UserSearchTwo invokeAndGetResultTwo(String queryDsl) {
        UserSearchTwo userSearchTwo = new UserSearchTwo();
        log.info("============================================大数据C端用户主题-时间和来源查询 开始====================================================");
        // 调bi接口，获取2c用户登录信息
        ESBaseData exchange = esHttpClientService.exchange(searchUrlTwo, HttpMethod.POST, ESBaseData.class, queryDsl);
        try {
            // 结果解析
            List<LinkedHashMap<Object, Object>> hits = exchange.getOriginalHits();
            Integer total = exchange.getTotal();

            List<Map> mapList = new ArrayList<>();

            // 查询返回数据表
            List<ReturnData> returnData = returnDataFeignService.listByReturnDataIndex(ESIndexEnum.TWOCFINDCHECKLOGINUSER.getName());
            LinkedList<String> dictTypeList = new LinkedList<>();
            if (returnData != null) {
                for (ReturnData returnDatum : returnData) {
                    dictTypeList.add(returnDatum.getReturnDataValue());
                }
            }
            // system code字典置换
            List<DictInfo> byType = dictFeignService.findByTypeList(dictTypeList);
            if (null != hits && hits.size() > 0) {
                for (int i = 0; i < hits.size(); i++) {
                    Map<String, Map<String, Object>> map = (Map) hits.get(i);
                    Map<String, Object> source = map.get(MagicConst.SOURCE);

                    if (CollectionUtils.isNotEmpty(returnData)) {
                        Map<String, Object> ma = new HashMap<>();
                        returnData.stream().forEach(el -> {
                            // 返回数据对应ES编码
                            String returnDataCode = el.getReturnDataCode();
                            if (null != source.get(returnDataCode)) {
                                String valueStr = source.get(returnDataCode).toString();
                                // 返回数据处理(1手机号脱敏 2字典置换 3卡号脱敏 4姓名脱敏)
                                Integer returnDataDeals = el.getReturnDataDeals();
                                if (INTEGER_1.equals(returnDataDeals)) {
                                    //处理手机号脱敏
                                    ma.put(el.getReturnDataCode(), StringUtil.mobileEncrypt(valueStr));
                                } else if (INTEGER_2.equals(returnDataDeals)) {
                                    if (CollectionUtils.isNotEmpty(byType)) {
                                        byType.stream().forEach(val -> {
                                            if (val.getCode().equals(valueStr)) {
                                                ma.put(returnDataCode, val.getValue());
                                            }
                                        });
                                    }
                                } else {
                                    if (el.getDataType().equals(1) && valueStr.matches(MagicConst.NUMBER_REGEX)) {
                                        BigDecimal a = new BigDecimal(valueStr);
                                        ma.put(returnDataCode, DecimalFormatUtil.TWO_DECIMAL_PLACE_FORMAT.format(a));
                                    } else {
                                        ma.put(returnDataCode, valueStr);
                                    }
                                }
                            }
                        });
                        mapList.add(ma);
                    }
                }
            }
            userSearchTwo.setTotal(total);
            BigDecimal a = new BigDecimal(String.valueOf(total));
            userSearchTwo.setTotalStr(DecimalFormatUtil.POSITIVE_INTEGER_FORMAT.format(a));
            userSearchTwo.setMapList(mapList);
            userSearchTwo.setColumnNames(returnData.stream()
                    .filter(e -> e.getShowFlag().equals(0))
                    .map(el -> {
                        Map<String,Object> map = new HashMap<>();
                        map.put(RETURN_DATA_CODE, el.getReturnDataCode());
                        map.put(RETURN_DATA_NAME, el.getReturnDataName());
                        map.put(GROUP_FLAG, el.getGroupFlag());
                        return map;
                    }).collect(Collectors.toList()));
        } catch (Exception e) {
            log.error("C端用户主题-时间和来源查询 invokeAndGetResultTwo error:{}", e);
            throw new BusinessException("注册时间和用户来源查询接口异常");
        }
        return userSearchTwo;
    }

    /**
     * 方法描述:分组统计分析
     *
     * @param userSearchVO 用户自助查询条件
     * @return  com.ikang.idata.common.entity.BaseSearch
     * @author  wenyue.gao@ikang.com
     * @date    2022/1/28 10:45
     */
    public BaseSearch findGroupBy(UserSearchVO userSearchVO) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> must = queryBuilder.must();
//        handlingAuthority(queryBuilder.filter());
        handlingCondition(userSearchVO, must);
        builder.size(INTEGER_0);
        builder.query(queryBuilder);

        // system 根据登录人id等信息 获取返回数据表
        Long userId = IdataSecurityUtil.getUserId();
        List<ReturnData> returnDataList = returnDataFeignService.selectAllByUserIDAndIndexAndTableAndResourceId(userId, MagicConst.LIST_TABLE, userSearchVO.getResourceId());
        //可统计或可分组
        List<ReturnData> statsOrGroupList = returnDataList.stream().filter(e -> !INTEGER_0.equals(e.getGroupFlag())).collect(Collectors.toList());
        //应该是可统计字段
        List<String> statsFieldNames = returnDataList.stream().filter(e -> INTEGER_2.equals(e.getGroupFlag())).map(ReturnData::getReturnDataCode).collect(Collectors.toList());
        //getBuilderByGroup(userSearchVO,builder,statsFieldNames);
        List<String> list = CollUtil.isNotEmpty(userSearchVO.getGroupBy())?userSearchVO.getGroupBy().stream()
                .map(AggregationCondition::getCode).collect(Collectors.toList()): CollUtil.newArrayList();
        List<String> collect2 = statsOrGroupList.stream().filter(e -> list.contains(e.getReturnDataCode()))
                .map(ReturnData::getReturnDataValue).collect(Collectors.toList());
        List<DictInfo> byTypes = dictFeignService.findByTypeList(collect2);
        BaseSearch baseSearch = this.invokeAndGetResultGroup1(builder.toString(), statsFieldNames, userSearchVO, statsOrGroupList, byTypes);
        return baseSearch;
    }



    private BaseSearch invokeAndGetResultGroup1(String toString,
                                                List<String> returnDataCodeList,
                                                UserSearchVO userSearchVO,
                                                List<ReturnData> collect,
                                                List<DictInfo> byTypes) {
        BaseSearch baseSearch = new UserSearch();
        log.info("=====================大数据体检主题查询调用大数据接口开始===========================================================================");
        // 调bi接口，2c用户查询
        JSONObject jsonObject = HttpClient.esRequest(searchGroupUrl, toString);
        try {
            JSONArray aggregation = jsonObject.getJSONArray("data");
            if (null != aggregation) {
                //只被求和平均
                if (CollUtil.isEmpty(userSearchVO.getGroupBy())) {
                    getSumAvg1(returnDataCodeList, baseSearch, aggregation);
                } else {
                    getGroupOneAndTwo1(returnDataCodeList, collect, byTypes, baseSearch, userSearchVO, aggregation);
                }
            }
        } catch (Exception e) {
            log.error("用户查询-分组统计查询 invokeAndGetResultGroup error:{}", e);
            throw new BusinessException("体检主题分组统计查询接口异常");
        }
        return baseSearch;
    }

    public void getSumAvg1(List<String> returnDataCodeList, BaseSearch baseSearch, JSONArray aggregation) {
        JSONObject data = aggregation.getJSONObject(INT_0);
        Map<String, Map<String, Object>> resultMap = new HashMap<>();
        for (String returnDataCode : returnDataCodeList) {
            BigDecimal sum = Optional.ofNullable(data.getBigDecimal(returnDataCode + AND + SUM)).orElse(BigDecimal.ZERO);
            BigDecimal avg = Optional.ofNullable(data.getBigDecimal(returnDataCode + AND + AVG)).orElse(BigDecimal.ZERO);
            Map<String, Object> ll = new HashMap<>();
            ll.put(MagicConst.AVG, Com.TWO_DECIMAL_PLACE_FORMAT.format(sum));
            ll.put(MagicConst.SUM, Com.TWO_DECIMAL_PLACE_FORMAT.format(avg));
            resultMap.put(returnDataCode, ll);
        }
        baseSearch.setSumOrAvgMap(resultMap);
    }

    public void getGroupOneAndTwo1(List<String> returnDataCodeList,
                                   List<ReturnData> collect,
                                   List<DictInfo> byTypes,
                                   BaseSearch baseSearch,
                                   BaseVO baseVO,
                                   JSONArray aggregation) {
        List<String> groupBys = baseVO.getGroupBy().stream().map(AggregationCondition::getCode).collect(Collectors.toList());
        List<String> feilds = new ArrayList<String>();
        switch (groupBys.size()) {
            case INT_1:
            case INT_2:
                getGroupOne1(returnDataCodeList, collect, byTypes, baseSearch, aggregation, groupBys, feilds);
                break;
            default:
                break;

        }
        baseSearch.setColumnNames(feilds.stream().map(k -> {
                    Optional<ReturnData> first = collect.stream().filter(l -> k.equals(l.getReturnDataCode())).findFirst();
                    Map<String, Object> mapFeilds = new HashMap<>();
                    if (first.isPresent()) {
                        mapFeilds.put(RETURN_DATA_CODE, first.get().getReturnDataCode());
                        mapFeilds.put(RETURN_DATA_NAME, first.get().getReturnDataName());
                        mapFeilds.put(GROUP_FLAG, first.get().getGroupFlag());
                        mapFeilds.put(SUM_OR_AVG_FLAG, first.get().getSumOrAvgFlag());
                        mapFeilds.put(FIELD_DESC, first.get().getFieldDesc());
                        return mapFeilds;
                    } else {
                        mapFeilds.put(RETURN_DATA_CODE, k);
                        mapFeilds.put(RETURN_DATA_NAME, "结果数量");
                        mapFeilds.put(GROUP_FLAG, INTEGER_0);
                        mapFeilds.put(SUM_OR_AVG_FLAG, INTEGER_0);
                        return mapFeilds;
                    }
                }
        ).collect(Collectors.toList()));
    }

    public void getGroupOne1(List<String> returnDataCodeList,
                             List<ReturnData> collect,
                             List<DictInfo> byTypes,
                             BaseSearch baseSearch,
                             JSONArray aggregation,
                             List<String> groupBys,
                             List<String> feilds) {
        boolean firstDeals = false;
        feilds.add(groupBys.get(INTEGER_0));
        //第一个分组条件 对应的处理方式
        Optional<ReturnData> first = collect.stream().filter(l -> groupBys.get(INTEGER_0).equals(l.getReturnDataCode())).findFirst();
        if (first.isPresent()) {
            ReturnData returnData = first.get();
            if (returnData.getReturnDataDeals().equals(INTEGER_3)) {
                firstDeals = true;
            }
        }
        feilds.add(COUNT);
        feilds.addAll(returnDataCodeList);
        //分组列表求和、平均数据
        List<Map<String, Object>> listMap = new ArrayList<>();
        //JSONObject groupBy = aggregation.getJSONObject(groupBys.get(INTEGER_0) + AND + GROUP_BY);
        //JSONArray buckets = groupBy.getJSONArray(BUCKETS);
        //列求和 列平均
        Map<String, Map<String, Object>> lm = new HashMap<>();
        for (int i = 0; i < aggregation.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> mapJson = (Map<String, Object>) aggregation.get(i);
            if (CollectionUtils.isNotEmpty(byTypes)) {
                byTypes.stream().forEach(val -> {
                    if (val.getCode().equals(mapJson.get(groupBys.get(INTEGER_0)).toString())) {
                        mapJson.put(groupBys.get(INTEGER_0), val.getValue());
                    }
                });
            }

            for (ReturnData returnD : collect) {
                String returnDataCode = returnD.getReturnDataCode();

                String key = Optional.ofNullable(mapJson.get(groupBys.get(INTEGER_0))).map(Object::toString).orElse("");
                if (firstDeals) {
                    map.put(groupBys.get(INTEGER_0), StringUtil.cardNumEncrypt(key));
                } else {
                    map.put(groupBys.get(INTEGER_0), key);
                }

                if (groupBys.size()>1){
                    String key1 = Optional.ofNullable(mapJson.get(groupBys.get(INTEGER_1))).map(Object::toString).orElse("");
                    if (firstDeals) {
                        map.put(groupBys.get(INTEGER_1), StringUtil.cardNumEncrypt(key1));
                    } else {
                        map.put(groupBys.get(INTEGER_1), key);
                    }
                }

                map.put(COUNT, Com.POSITIVE_INTEGER_FORMAT.format(new BigDecimal(mapJson.get(COUNT).toString())));
                String keyAvg = returnDataCode + AND + AVG;
                String keySum = returnDataCode + AND + SUM;
                if (null != mapJson.get(keyAvg)) {
                    String avgSon = Optional.ofNullable(mapJson.get(keyAvg)).map(Object::toString).orElse("0.0");
                    String sumSon = Optional.ofNullable(mapJson.get(keySum)).map(Object::toString).orElse("0.0");

                    Map ll = new HashMap();
                    ll.put(AVG, avgSon);
                    ll.put(SUM, sumSon);
                    map.put(returnDataCode, ll);

                    Double avgValue = Double.valueOf(avgSon);
                    Double sumValue = Double.valueOf(sumSon);
                    if (null == lm.get(returnDataCode)) {
                        lm.put(returnDataCode, ll);
                    } else {
                        Map<String, Object> aDouble = lm.get(returnDataCode);
                        Double avg = Double.valueOf(String.valueOf(aDouble.get(AVG)));
                        avg += avgValue;
                        aDouble.put(MagicConst.AVG, avg);
                        Double sum = Double.valueOf(String.valueOf(aDouble.get(SUM)));
                        sum += sumValue;
                        aDouble.put(MagicConst.SUM, sum);
                    }
                }
            }


            map.keySet().stream().forEach(key -> {
                if (returnDataCodeList.contains(key)) {
                    Integer returnDataDeals = INTEGER_0;
                    Optional<ReturnData> firstKey = collect.stream().filter(k -> k.getReturnDataCode().equals(key)).findFirst();
                    if (firstKey.isPresent()) {
                        ReturnData returnDataKey = firstKey.get();
                        returnDataDeals = returnDataKey.getReturnDataDeals();
                    }
                    Map o = (Map) map.get(key);
                    Double avg = Double.valueOf(String.valueOf(o.get(AVG)));
                    Double sum = Double.valueOf(String.valueOf(o.get(SUM)));
                    Map ll = new HashMap();
                    ll.put(AVG, INTEGER_6.equals(returnDataDeals) ? Com.POSITIVE_INTEGER_FORMAT.format(new BigDecimal(avg)) : Com.TWO_DECIMAL_PLACE_FORMAT.format(new BigDecimal(avg)));
                    ll.put(SUM, INTEGER_6.equals(returnDataDeals) ? Com.POSITIVE_INTEGER_FORMAT.format(new BigDecimal(sum)) : Com.TWO_DECIMAL_PLACE_FORMAT.format(new BigDecimal(sum)));
                    map.put(key, ll);
                }
            });
            listMap.add(map);
        }

        baseSearch.setListMap(listMap);
        lm.keySet().stream().forEach(key -> {
            Map o = (Map) lm.get(key);
            Double avg = Double.valueOf(String.valueOf(o.get(AVG)));
            Double sum = Double.valueOf(String.valueOf(o.get(SUM)));
            Map ll = new HashMap();
            ll.put(AVG, Com.TWO_DECIMAL_PLACE_FORMAT.format(new BigDecimal(avg)));
            ll.put(SUM, Com.TWO_DECIMAL_PLACE_FORMAT.format(new BigDecimal(sum)));
            lm.put(key, ll);
        });
        baseSearch.setSumOrAvgMap(lm);
        baseSearch.setTotal(listMap.size());
        BigDecimal a = new BigDecimal(String.valueOf(listMap.size()));
        baseSearch.setTotalStr(Com.POSITIVE_INTEGER_FORMAT.format(a));
    }

    public void setMusts(CommonSearchVO searchVO,
                         List<QueryBuilder> must) {
        // 体检卡使用状态：0：全部（默认）；1：未预约；2：已预约未到检；3：已到检
        Integer cardUseStatus = searchVO.getCardUseStatus();
        if (null != cardUseStatus && !INTEGER_0.equals(cardUseStatus)) {
            if (INTEGER_1.equals(cardUseStatus)) {
//                reg_status INT COMMENT '是否预约（是1，否2）',  1 预约  2未预约  3取消
                must.add(QueryBuilders.termsQuery(REGSTATUS.getFields(), Arrays.asList(STR_2, STR_3)));
            } else if (INTEGER_2.equals(cardUseStatus)) {
                must.add(QueryBuilders.termQuery(REGSTATUS.getFields(), STR_1));
                //是否到检（是1，否2）
                must.add(QueryBuilders.termQuery(CHECKSTATUS.getFields(), STR_2));
            } else {
                must.add(QueryBuilders.termQuery(CHECKSTATUS.getFields(), STR_1));
            }
        }

        // 预约渠道
        String regChannel = searchVO.getRegChannel();
        if (StringUtils.isNotBlank(regChannel)) {
            List<String> regChannels = Arrays.asList(regChannel.split(COMMA_SPLIT));
            must.add(QueryBuilders.termsQuery(REGCHANNEL.getFields(), regChannels));
        }
        // 报告时间和到检时间差
        String reportTimeDiff = searchVO.getReportTimeDiff();
        if (StringUtils.isNotBlank(reportTimeDiff)) {
            if ("当天".equals(reportTimeDiff)) {
                must.add(QueryBuilders.rangeQuery(REPORTTIMEDIFF.getFields())
                        .from(INT_0).to(INT_1)
                        .includeLower(true)
                        .includeUpper(false));
            }
            if ("大于等于1天小于等于3天".equals(reportTimeDiff)) {
                must.add(QueryBuilders.rangeQuery(REPORTTIMEDIFF.getFields())
                        .gte(INT_1)
                        .lte(INT_3));
            }
            if ("大于3天小于等于5天".equals(reportTimeDiff)) {
                must.add(QueryBuilders.rangeQuery(REPORTTIMEDIFF.getFields())
                        .from(INT_3).to(INT_5)
                        .includeLower(false)
                        .includeUpper(true));
            }
            if ("大于5天".equals(reportTimeDiff)) {
                must.add(QueryBuilders.rangeQuery(REPORTTIMEDIFF.getFields())
                        .gt(INT_5));
            }
        }
    }

    public void setParamMap(List<QueryBuilder> must, Map paramMap, Long resourceId) {
        List<ParamData> paramDataList = paramDataFeignService.selectParamDataByUserIdAndResourceId(IdataSecurityUtil.getUserId(), resourceId);
        if (null != paramMap) {
            for (ParamData paramData : paramDataList) {
//            "组件类型 0默认文本精准 1:日期选择器 2:多选框 3:单选框 4:数字区间选择器 5:文本搜索-单值模糊 6:文本搜索-多值精准" 7:月份选择器
                String code = paramData.getParamDataCode();
                Object obj = paramMap.get(code);
                if (null != obj) {
                    Integer dataType = paramData.getDataType();
                    if (dataType.equals(INTEGER_0) || dataType.equals(INTEGER_3) || dataType.equals(INTEGER_7)) {
                        //0默认文本精准 || 3:单选框 || 7:月份选择器
                        if (StringUtils.isNotBlank(obj.toString())) {
                            must.add(QueryBuilders.termQuery(code, obj.toString()));
                        }
                    } else if (dataType.equals(INTEGER_1)) {
                        //1:日期选择器{"start": "2021-01-01 00:00:00","end": "2021-01-01 00:00:00"}
                        Map<String, String> map = (Map<String, String>) obj;
                        if (0 != map.size()) {
                            if (StringUtils.isNotBlank(map.get(START)) && StringUtils.isNotBlank(map.get(END))) {
                                must.add(QueryBuilders.rangeQuery(code)
                                        .gte(map.get(START))
                                        .lte(map.get(END))
                                        .includeLower(true)
                                        .includeUpper(true));
                            }
                        }
                    } else if (dataType.equals(INTEGER_2) || dataType.equals(INTEGER_6)) {
                        //2:多选框 || 6:文本搜索-多值精准
                        if (StringUtils.isNotBlank(obj.toString())) {
                            must.add(QueryBuilders.termsQuery(code, Arrays.asList(obj.toString().split(",|，"))));
                        }
                    } else if (dataType.equals(INTEGER_4)) {
                        //4:数字区间选择器{"low": "2000","high": "5000"}
                        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(code);
                        Map<String, String> map = (Map<String, String>) obj;
                        if (0 != map.size()) {
                            if (StringUtils.isNotBlank(map.get(LOW))) {
                                rangeQueryBuilder.gte(map.get(LOW));
                            }
                            if (StringUtils.isNotBlank(map.get(HIGH))) {
                                rangeQueryBuilder.lte(map.get(HIGH));
                            }
                            rangeQueryBuilder.includeLower(true).includeUpper(true);
                            must.add(rangeQueryBuilder);
                        }
                    } else if (dataType.equals(INTEGER_5)) {
                        //5:文本搜索-单值模糊
                        if (StringUtils.isNotBlank(obj.toString())) {
                            must.add(QueryBuilders.wildcardQuery(code, ("*" + obj.toString() + "*")));
                        }
                    }
                }
            }

        }
    }

    /**
     * 处理特殊条件
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2021/12/28 10:09
     */
    private void handlingCondition(UserSearchVO userSearchVO,
                                   List<QueryBuilder> must) {

        CommonSearchVO searchVO = new CommonSearchVO();
        BeanUtils.copyProperties(userSearchVO,searchVO);
        // 拼接条件
        setMusts(searchVO, must);

        // 电商类型权限
        String channels = searchVO.getChannels();
        List<String> channelList = StrUtil.isNotBlank(channels) ? CollUtil.newArrayList(channels.split(COMMA_SPLIT)) : null;
        if (CollectionUtils.isNotEmpty(channelList)) {
            must.add(QueryBuilders.termsQuery(CHANNEL.getFields(), channelList));
        }
        // 分院类型权限
        // 非全部数据权限
        if (!StringUtils.isBlank(userSearchVO.getRegHospCodesList())
                && !StringUtils.isBlank(userSearchVO.getCheckHospCodesList())) {
            // 区域权限
            if (StringUtils.isBlank(userSearchVO.getRegHospCodesListCopy())
                    && StringUtils.isBlank(userSearchVO.getCheckHospCodesListCopy())) {
                List<String> regHospCodesList = Arrays.asList(userSearchVO.getRegHospCodesList().split(COMMA_SPLIT));
                List<String> checkHospCodesList = Arrays.asList(userSearchVO.getCheckHospCodesList().split(COMMA_SPLIT));
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                List<QueryBuilder> should = boolQueryBuilder.should();
                should.add(QueryBuilders.termsQuery(REGHOSPID.getFields(), regHospCodesList));
                should.add(QueryBuilders.termsQuery(CHECKHOSPID.getFields(), checkHospCodesList));
                must.add(boolQueryBuilder);
            } else {
                if (StringUtils.isNotBlank(userSearchVO.getRegHospCodesListCopy())) {
                    List<String> regHospCodesList = Arrays.asList(userSearchVO.getRegHospCodesList().split(COMMA_SPLIT));
                    must.add(QueryBuilders.termsQuery(REGHOSPID.getFields(), regHospCodesList));
                }
                if (StringUtils.isNotBlank(userSearchVO.getCheckHospCodesListCopy())) {
                    List<String> checkHospCodesList = Arrays.asList(userSearchVO.getCheckHospCodesList().split(COMMA_SPLIT));
                    must.add(QueryBuilders.termsQuery(CHECKHOSPID.getFields(), checkHospCodesList));
                }
            }
            // 全部数据权限
        } else {
            if (StringUtils.isNotBlank(userSearchVO.getRegHospCodesList())) {
                List<String> regHospCodesList = Arrays.asList(userSearchVO.getRegHospCodesList().split(COMMA_SPLIT));
                must.add(QueryBuilders.termsQuery(REGHOSPID.getFields(), regHospCodesList));
            }
            if (StringUtils.isNotBlank(userSearchVO.getCheckHospCodesList())) {
                List<String> checkHospCodesList = Arrays.asList(userSearchVO.getCheckHospCodesList().split(COMMA_SPLIT));
                must.add(QueryBuilders.termsQuery(CHECKHOSPID.getFields(), checkHospCodesList));
            }
        }



        // 销售类型权限  三个字段为权限专用 禁止前端使用
        // 职位
        String postCodes = userSearchVO.getPostCodesList();
        List<String> postCodesList = StrUtil.isNotBlank(postCodes) ? CollUtil.newArrayList(postCodes.split(COMMA_SPLIT)) : null;
        //销管职位
        if (CollectionUtils.isNotEmpty(postCodesList) && postCodesList.size() == 1 && PROJECT_TYPE.getFields().equals(postCodesList.get(0))) {
            //行业
            String industryCodes = userSearchVO.getIndustryCodesList();
            List<String> industryCodesList = StrUtil.isNotBlank(industryCodes) ? CollUtil.newArrayList(industryCodes.split(COMMA_SPLIT)) : null;
            if (CollectionUtils.isNotEmpty(industryCodesList)) {
                must.add(QueryBuilders.termsQuery(PROJECT_TYPE.getFields(), industryCodesList));
            }
        } else if (CollectionUtils.isNotEmpty(postCodesList)){
            //除了销管 都是按着名称来查询数据
            String realName = IdataSecurityUtil.getUser().getRealname();
            List<TermsQueryBuilder> builders = postCodesList.stream().map(userPost -> QueryBuilders.termsQuery(userPost, realName)).collect(Collectors.toList());
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.should().addAll(builders);
            must.add(boolQueryBuilder);
        }

        String areaCodes = userSearchVO.getAreaCodesList();
        List<String> areaCodesList = StrUtil.isNotBlank(areaCodes) ? CollUtil.newArrayList(areaCodes.split(COMMA_SPLIT)) : null;
        if (CollectionUtils.isNotEmpty(areaCodesList)) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            boolQueryBuilder.must()
                    .add(QueryBuilders.termsQuery(PURCHASE_BELONG_REGION.getFields(), areaCodesList.get(0)));
            must.add(boolQueryBuilder);
        }



        Map<String, Object> paramMap = userSearchVO.getQueryMap();
        Long resourceId = userSearchVO.getResourceId();
        setParamMap(must, paramMap, resourceId);

    }

//    public BaseSearch getBaseSearch(BaseVO vo, SearchSourceBuilder builder, String url) {
//        //设置size为0
//        builder.size(MagicConst.INTEGER_0);
//        //查询返回字段信息
//        List<ReturnData> returnDataList = partInfos(vo.getResourceId());
//        //groupFlag 是否可以被分组或统计 默认0:都不可以 1:可分组 2:可统计
//        // 过滤可被分组/可统计的list
//        Map<Integer, List<ReturnData>> groupFieldMap = returnDataList.stream().collect(Collectors.groupingBy(ReturnData::getGroupFlag));
//        // 可统计的返回数据对应ES编码List
//        List<String> statisticFieldNames = groupFieldMap.getOrDefault(2, ListUtil.empty()).stream().map(ReturnData::getReturnDataCode).collect(Collectors.toList());
//        // 分组统计
//        constructAggStatement(vo, builder, statisticFieldNames);
//        //查询
//        JSONObject jsonObject = HttpClient.esRequestAgg(url, builder.toString());
//        JSONArray aggResult = jsonObject.getJSONArray("data");
//        BaseSearch baseSearch = new BaseSearch();
//        if (Objects.nonNull(aggResult) && CollUtil.isEmpty(vo.getGroupBy())) {
//            getSumAvg1(statisticFieldNames, baseSearch, aggResult);
//        } else if (Objects.nonNull(aggResult)) {
//            List<Map<String, Object>> result = parseAggResult(vo, statisticFieldNames, aggResult);
//            List<String> groupFieldNames = vo.getGroupBy().stream().map(AggregationCondition::getCode).collect(Collectors.toList());
//            processGroupTableList(groupFieldMap.get(1).stream().filter(returnData -> groupFieldNames.contains(returnData.getReturnDataCode())).collect(Collectors.toList()), result);
//            Map<String, Map<String, Object>> sumOrAvgMap = processStatisticsAndConstructSumOrAvgMap(groupFieldMap.get(2), result);
//            baseSearch.setSumOrAvgMap(sumOrAvgMap);
//            baseSearch.setListMap(Objects.isNull(EsUtil.getSortComparator(vo)) ? result : ListUtil.sort(result, EsUtil.getSortComparator(vo)));
//            baseSearch.setTotal(result.size());
//            baseSearch.setColumnNames(buildColumnName(vo.getGroupBy(), groupFieldMap));
//        }
//        return baseSearch;
//    }
//
//    private List<Map<String, Object>> parseAggResult(BaseVO vo, List<String> statisticFieldNames, JSONArray buckets) {
//        return buckets.stream()
//                .map(JSONUtil::parseObj)
//                .map(json -> {
//                    Outcome outcome = new Outcome();
//                    outcome.setCount(json.getStr(COUNT));
//                    IntStream.range(0, vo.getGroupBy().size())
//                            .forEach(i -> outcome.put(vo.getGroupBy().get(i).getCode(), Optional.ofNullable(json.get(vo.getGroupBy().get(i).getCode())).orElse("")));
//                    statisticFieldNames.forEach(s -> outcome.put(s, new Statistics(s, json)));
//                    return outcome;
//                }).collect(Collectors.toList());
//    }
//
//    private void processGroupTableList(List<ReturnData> groupFieldInfos, List<Map<String, Object>> sourceList) {
//        long start = System.currentTimeMillis();
//        List<DictInfo> dictInfos = dictFeignService.findByTypeList(groupFieldInfos.stream().map(ReturnData::getReturnDataValue).collect(Collectors.toList()));
//        log.info("dictFeignService.findByTypeList response times:{}", System.currentTimeMillis() - start);
//        //获取全部的字典 为了后面的替换
//        //如果有好的方案 请联系我  <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
//        com.dictListThreadLocal.set(dictInfos);
//        sourceList.forEach(source -> {
//            groupFieldInfos.forEach(info -> {
//                        // 组件管理-列表出参管理-出参方式处理，手机号脱敏等
//                        source.put(info.getReturnDataCode(), com.valuePostProcessing(source, info));
//                        //没有获取到对应的编码特殊处理， returnDataCode：返回数据对应ES编码 ，returnDataDeals返回数据类型处理对应值(字典type等)
//                        CheckUtil.isTrue(Objects.isNull(source.get(info.getReturnDataCode())) && info.getReturnDataDeals().equals(INTEGER_5),
//                                () -> source.put(info.getReturnDataCode(), "不可用/未激活"));
//                    }
//            );
//        });
//        com.dictListThreadLocal.remove();
//    }
//
//    private Map<String, Map<String, Object>> processStatisticsAndConstructSumOrAvgMap(List<ReturnData> returnData, List<Map<String, Object>> result) {
//        Map<String, Map<String, Object>> sumOrAvgMap = new HashMap<>();
//        if (CollUtil.isEmpty(returnData)) {
//            return sumOrAvgMap;
//        }
//        returnData.forEach(statisticsFieldInfo -> {
//            Statistics sumOrAvg = new Statistics();
//            Function<String, String> function = Com.PROCESSING_METHOD.getOrDefault(statisticsFieldInfo.getReturnDataDeals(), Function.identity());
//            result.stream()
//                    .filter(obj -> Objects.nonNull(obj.get(statisticsFieldInfo.getReturnDataCode())))
//                    .forEach(
//                            stringObjectMap -> {
//                                Statistics bean = BeanUtil.toBean(stringObjectMap.get(statisticsFieldInfo.getReturnDataCode()), Statistics.class);
//                                calculateTableHeader(statisticsFieldInfo, sumOrAvg, bean);
//                                bean.setAvg(function.apply(bean.getAvg()));
//                                bean.setSum(function.apply(bean.getSum()));
//                                stringObjectMap.remove(statisticsFieldInfo.getReturnDataCode());
//                                stringObjectMap.put(statisticsFieldInfo.getReturnDataCode(), bean);
//                            });
//            sumOrAvg.setAvg(Com.TWO_DECIMAL_PLACE_FORMAT.format(CalculateUtil.div(sumOrAvg.getSum(), result.size() + "")));
//            sumOrAvg.setSum(Com.TWO_DECIMAL_PLACE_FORMAT.format(new BigDecimal(sumOrAvg.getSum())));
//            sumOrAvgMap.put(statisticsFieldInfo.getReturnDataCode(), BeanUtil.beanToMap(sumOrAvg));
//        });
//        return sumOrAvgMap;
//    }

}
