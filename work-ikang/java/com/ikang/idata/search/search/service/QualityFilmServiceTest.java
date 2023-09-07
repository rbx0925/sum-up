package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.entity.DepartmentFilm;
import com.ikang.idata.common.entity.QualityFilm;
import com.ikang.idata.common.entity.ReturnData;
import com.ikang.idata.common.entity.UserChannel;
import com.ikang.idata.common.entity.vo.*;
import com.ikang.idata.common.enums.QualityFilmEnum;
import com.ikang.idata.common.exceptions.BusinessException;
import com.ikang.idata.common.utils.DecimalFormatUtil;
import com.ikang.idata.search.search.entity.ReaderEnum;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.ReturnDataFeignServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QualityFilmServiceTest {

    @Mock
    private ReturnDataFeignServiceImpl mockReturnDataFeignService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;

    @InjectMocks
    private QualityFilmService qualityFilmServiceUnderTest;

    @Test
    void testFindDoctorGroupBy() {
        // Setup
        final LocAndInstituteVO locAndInstituteVO = new LocAndInstituteVO();
        locAndInstituteVO.setType(0);
        locAndInstituteVO.setInstituteId("instituteId");
        locAndInstituteVO.setLocId("locId");

        // Run the test
        final List<String> result = qualityFilmServiceUnderTest.findDoctorGroupBy(locAndInstituteVO);

        // Verify the results
        assertThat(result).isEqualTo(Arrays.asList("value"));
    }

    @Test
    void testInvokeAndGetResult() {
        assertThat(qualityFilmServiceUnderTest.invokeAndGetResult("queryDsl")).isEqualTo(Arrays.asList("value"));
        assertThat(qualityFilmServiceUnderTest.invokeAndGetResult("queryDsl")).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final QualityFilmVo qualityFilmVo = new QualityFilmVo();
        qualityFilmVo.setUpLoadDateStart("upLoadDateStart");
        qualityFilmVo.setUpLoadDateEnd("upLoadDateEnd");
        qualityFilmVo.setExamineDateStart("examineDateStart");
        qualityFilmVo.setExamineDateEnd("examineDateEnd");
        qualityFilmVo.setInstituteId("instituteId");
        qualityFilmVo.setLocId("locId");
        qualityFilmVo.setDoctorName("doctorName");
        qualityFilmVo.setPageNum(0);
        qualityFilmVo.setPageSize(0);
        qualityFilmVo.setGroupBy("groupBy");

        final QualityFilm expectedResult = new QualityFilm();
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setTotalStr("totalStr");
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());
        expectedResult.setReturnData(Arrays.asList(new HashMap<>()));
        expectedResult.setSumOrAvgMap(new HashMap<>());
        expectedResult.setListMap(Arrays.asList(new HashMap<>()));

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        userChannel.setChannelCode("channelCode");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("returnDataCode");
        returnData1.setReturnDataName("returnDataName");
        returnData1.setDataType(0);
        returnData1.setDataValue("dataValue");
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setReturnDataMemo("returnDataMemo");
        returnData1.setSort(0);
        returnData1.setDeleteStatus(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("quality")).thenReturn(returnData);

        // Run the test
        final QualityFilm result = qualityFilmServiceUnderTest.find(qualityFilmVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFind_ReturnDataFeignServiceImplReturnsNoItems() {
        // Setup
        final QualityFilmVo qualityFilmVo = new QualityFilmVo();
        qualityFilmVo.setUpLoadDateStart("upLoadDateStart");
        qualityFilmVo.setUpLoadDateEnd("upLoadDateEnd");
        qualityFilmVo.setExamineDateStart("examineDateStart");
        qualityFilmVo.setExamineDateEnd("examineDateEnd");
        qualityFilmVo.setInstituteId("instituteId");
        qualityFilmVo.setLocId("locId");
        qualityFilmVo.setDoctorName("doctorName");
        qualityFilmVo.setPageNum(0);
        qualityFilmVo.setPageSize(0);
        qualityFilmVo.setGroupBy("groupBy");

        final QualityFilm expectedResult = new QualityFilm();
        expectedResult.setPageSize(0);
        expectedResult.setPageNum(0);
        expectedResult.setTotalStr("totalStr");
        expectedResult.setTotal(0);
        expectedResult.setColumnNames(Arrays.asList());
        expectedResult.setReturnData(Arrays.asList(new HashMap<>()));
        expectedResult.setSumOrAvgMap(new HashMap<>());
        expectedResult.setListMap(Arrays.asList(new HashMap<>()));

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        userChannel.setChannelCode("channelCode");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        when(mockReturnDataFeignService.listByReturnDataIndex("quality")).thenReturn(Collections.emptyList());

        // Run the test
        final QualityFilm result = qualityFilmServiceUnderTest.find(qualityFilmVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testExportExcel() throws Exception {
        // Setup
        final QualityFilmVo qualityFilmVo = new QualityFilmVo();
        qualityFilmVo.setUpLoadDateStart("upLoadDateStart");
        qualityFilmVo.setUpLoadDateEnd("upLoadDateEnd");
        qualityFilmVo.setExamineDateStart("examineDateStart");
        qualityFilmVo.setExamineDateEnd("examineDateEnd");
        qualityFilmVo.setInstituteId("instituteId");
        qualityFilmVo.setLocId("locId");
        qualityFilmVo.setDoctorName("doctorName");
        qualityFilmVo.setPageNum(0);
        qualityFilmVo.setPageSize(0);
        qualityFilmVo.setGroupBy("groupBy");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        userChannel.setChannelCode("channelCode");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("returnDataCode");
        returnData1.setReturnDataName("returnDataName");
        returnData1.setDataType(0);
        returnData1.setDataValue("dataValue");
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setReturnDataMemo("returnDataMemo");
        returnData1.setSort(0);
        returnData1.setDeleteStatus(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("quality")).thenReturn(returnData);

        // Run the test
        qualityFilmServiceUnderTest.exportExcel(qualityFilmVo, response);

        // Verify the results
    }

    @Test
    void testExportExcel_ReturnDataFeignServiceImplReturnsNoItems() throws Exception {
        // Setup
        final QualityFilmVo qualityFilmVo = new QualityFilmVo();
        qualityFilmVo.setUpLoadDateStart("upLoadDateStart");
        qualityFilmVo.setUpLoadDateEnd("upLoadDateEnd");
        qualityFilmVo.setExamineDateStart("examineDateStart");
        qualityFilmVo.setExamineDateEnd("examineDateEnd");
        qualityFilmVo.setInstituteId("instituteId");
        qualityFilmVo.setLocId("locId");
        qualityFilmVo.setDoctorName("doctorName");
        qualityFilmVo.setPageNum(0);
        qualityFilmVo.setPageSize(0);
        qualityFilmVo.setGroupBy("groupBy");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        userChannel.setChannelCode("channelCode");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        when(mockReturnDataFeignService.listByReturnDataIndex("quality")).thenReturn(Collections.emptyList());

        // Run the test
        qualityFilmServiceUnderTest.exportExcel(qualityFilmVo, response);

        // Verify the results
    }

    @Test
    void testExportExcel_ThrowsException() {
        // Setup
        final QualityFilmVo qualityFilmVo = new QualityFilmVo();
        qualityFilmVo.setUpLoadDateStart("upLoadDateStart");
        qualityFilmVo.setUpLoadDateEnd("upLoadDateEnd");
        qualityFilmVo.setExamineDateStart("examineDateStart");
        qualityFilmVo.setExamineDateEnd("examineDateEnd");
        qualityFilmVo.setInstituteId("instituteId");
        qualityFilmVo.setLocId("locId");
        qualityFilmVo.setDoctorName("doctorName");
        qualityFilmVo.setPageNum(0);
        qualityFilmVo.setPageSize(0);
        qualityFilmVo.setGroupBy("groupBy");

        final HttpServletResponse response = new MockHttpServletResponse();

        // Configure AuthorityFeignServiceImpl.getChannelOrHospitals(...).
        final UserChannelAndHospital userChannelAndHospital = new UserChannelAndHospital();
        final JobDistrictHeadArea jobDistrictHeadArea = new JobDistrictHeadArea();
        jobDistrictHeadArea.setPostCode("postCode");
        jobDistrictHeadArea.setPostName("postName");
        jobDistrictHeadArea.setChiefZone(Arrays.asList("value"));
        userChannelAndHospital.setJobDistrictHeadArea(jobDistrictHeadArea);
        userChannelAndHospital.setChannelAndHospitalType(0);
        final UserChannel userChannel = new UserChannel();
        userChannel.setId(0L);
        userChannel.setUserId(0L);
        userChannel.setCreateBy(0L);
        userChannel.setCreateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setUpdateBy(0L);
        userChannel.setUpdateTime(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userChannel.setDelFlag(0);
        userChannel.setChannelCode("channelCode");
        userChannelAndHospital.setChannels(Arrays.asList(userChannel));
        userChannelAndHospital.setHospitals(new HashMap<>());
        when(mockAuthorityFeignService.getChannelOrHospitals(0L)).thenReturn(userChannelAndHospital);

        // Configure ReturnDataFeignServiceImpl.listByReturnDataIndex(...).
        final ReturnData returnData1 = new ReturnData();
        returnData1.setFieldDesc("fieldDesc");
        returnData1.setSumOrAvgFlag(0);
        returnData1.setId(0L);
        returnData1.setReturnDataIndex("returnDataIndex");
        returnData1.setReturnDataCode("returnDataCode");
        returnData1.setReturnDataName("returnDataName");
        returnData1.setDataType(0);
        returnData1.setDataValue("dataValue");
        returnData1.setReturnDataDeals(0);
        returnData1.setReturnDataValue("returnDataValue");
        returnData1.setShowFlag(0);
        returnData1.setReturnDataMemo("returnDataMemo");
        returnData1.setSort(0);
        returnData1.setDeleteStatus(0);
        returnData1.setGroupFlag(0);
        final List<ReturnData> returnData = Arrays.asList(returnData1);
        when(mockReturnDataFeignService.listByReturnDataIndex("quality")).thenReturn(returnData);

        // Run the test
        assertThatThrownBy(() -> qualityFilmServiceUnderTest.exportExcel(qualityFilmVo, response))
                .isInstanceOf(Exception.class);
    }


    @Test
    public void setParamAndStartTest() {
        QualityFilmVo qualityFilmVo = new QualityFilmVo();
        QualityFilm qualityFilm = findTest(qualityFilmVo);
        //方便打断点，随便写一行
        System.out.println(qualityFilm);
    }

    public QualityFilm findTest(QualityFilmVo qualityFilmVo) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> must = queryBuilder.must();
        Integer pageNum = qualityFilmVo.getPageNum();
        if (pageNum < 1) {
            pageNum = 1;
        }
        Integer pageSize = qualityFilmVo.getPageSize();
        if ((pageNum - 1) * pageSize + pageSize >= MagicConst.PAGE_SIZE_10000) {
            throw new BusinessException("没法显示更多了，查询结果数量大于10000条数据");
        }
        // 分页参数
        builder.from((pageNum - 1) * pageSize);
        builder.size(pageSize);

        builder.query(queryBuilder);

        TermsAggregationBuilder hospNameField = AggregationBuilders.terms("hospname_name_group").field("hospname_name").size(MagicConst.PAGE_SIZE_10000);
        TermsAggregationBuilder doctorLoginIdField = AggregationBuilders.terms("doctor_loginid_group").field("doctor_loginid").size(MagicConst.PAGE_SIZE_10000);
        TermsAggregationBuilder doctorNameField = AggregationBuilders.terms("doctor_name_group").field("doctor_name").size(MagicConst.PAGE_SIZE_10000);
        TermsAggregationBuilder typeField = AggregationBuilders.terms("type_group").field("type").size(MagicConst.PAGE_SIZE_10000);
        SumAggregationBuilder aggregationSum = AggregationBuilders.sum("ct_sum").field("ct");
        hospNameField.subAggregation(doctorLoginIdField);
        doctorLoginIdField.subAggregation(doctorNameField);
        doctorNameField.subAggregation(typeField);
        typeField.subAggregation(aggregationSum);
        builder.aggregation(hospNameField);

        //todo 排序              默认排序方式：按照检线名称排序 正序
        builder.sort(QualityFilmEnum.HOSPNAMENAME.getFields(), SortOrder.ASC);
        QualityFilm qualityFilm = invokeAndGetResultSatisfiedTest(builder.toString());
        qualityFilm.setPageNum(qualityFilmVo.getPageNum());
        qualityFilm.setPageSize(qualityFilmVo.getPageSize());
        return qualityFilm;
    }

    private QualityFilm invokeAndGetResultSatisfiedTest(String queryDsl) {
        QualityFilm qualityFilm=new QualityFilm();
        try {
            JSONObject jsonObject = new JSONObject();
            // 结果解析
            Integer total;

            List<Map<String, Object>> mapList = new ArrayList<>();


            JSONObject aggregations = jsonObject.getJSONObject("aggregations");
            if (null != aggregations) {
                JSONObject hospNameGroup = aggregations.getJSONObject("hospname_name_group");
                JSONArray hospNameBuckets = hospNameGroup.getJSONArray("buckets");
//                List<DepartmentFilmDTO> listMap=new ArrayList<>();
                for (Object hospNameBucket : hospNameBuckets) {
                    Map<String, Object> hospNameMap = (Map<String, Object>) hospNameBucket;
                    String hospName = "";
                    if (null != hospNameMap.get("key")) {
                        hospName = hospNameMap.get("key").toString();
                    }
                    Map<String, Object> doctorLoginIdGroup = (Map<String, Object>) hospNameMap.get("doctor_loginid_group");
                    List doctorLoginIdList = (List) doctorLoginIdGroup.get("buckets");
                    for (Object item : doctorLoginIdList) {
                        Map<String, Object> doctorLoginIdMap = (Map<String, Object>) item;
                        String doctorLoginId = "";
                        if (null != doctorLoginIdMap.get("key")) {
                            doctorLoginId = doctorLoginIdMap.get("key").toString();
                        }
                        Map<String, Object> doctorNameGroup = (Map<String, Object>) doctorLoginIdMap.get("doctor_name_group");
                        List doctorNameList = (List) doctorNameGroup.get("buckets");
                        for (Object o : doctorNameList) {
                            Map<String, Object> doctorNameMap = (Map<String, Object>) o;
                            String doctorName = "";
                            if (null != doctorNameMap.get("key")) {
                                doctorName = doctorNameMap.get("key").toString();
                            }
                            Map<String, Object> typeGroup = (Map<String, Object>) doctorNameMap.get("type_group");
                            List typeList = (List) typeGroup.get("buckets");
                            Map<String, Object> ma = new HashMap<>(8);
                            ma.put("hospname_name", hospName);
                            ma.put("doctor_loginid", doctorLoginId);
                            ma.put("doctor_name", doctorName);
                            for (Object value : typeList) {
                                Map<String, Object> typeMap = (Map<String, Object>) value;
                                Map<String, Object> ctSum = (Map<String, Object>) typeMap.get("ct_sum");
                                ctSum.putIfAbsent("value", 0.0);
                                if ("0".equals(typeMap.get("key").toString())) {
                                    ma.put("check_sum", DecimalFormatUtil.POSITIVE_INTEGER_FORMAT.format(new BigDecimal(ctSum.get("value").toString())));
                                }
                                if ("1".equals(typeMap.get("key").toString())) {
                                    ma.put("quality_sum", DecimalFormatUtil.POSITIVE_INTEGER_FORMAT.format(new BigDecimal(ctSum.get("value").toString())));
                                }
                            }
                            mapList.add(ma);
                        }
                    }
                }
            }

            total = mapList.size();

            BigDecimal a = new BigDecimal(String.valueOf(total));

            qualityFilm.setTotalStr(DecimalFormatUtil.POSITIVE_INTEGER_FORMAT.format(a));

            //total   总条目数
            qualityFilm.setTotal(total);

//            qualityFilm.setReturnData(mapList);
//            qualityFilm.setColumnNames(returnData.stream()
//                    .filter(e -> e.getShowFlag().equals(0))
//                    .map(el -> {
//                                el.getReturnDataCode() + "&" + el.getReturnDataName()
//                        Map<String, Object> map = new HashMap<>(16);
//                        map.put("returnDataCode", el.getReturnDataCode());
//                        map.put("returnDataName", el.getReturnDataName());
//                        map.put("groupFlag", el.getGroupFlag());
//                        map.put("sumOrAvgFlag", el.getSumOrAvgFlag());
//                        map.put("fieldDesc", el.getFieldDesc());
//                        return map;
//                    })
//                    .collect(Collectors.toList()));
        }catch (Exception e) {
            throw new BusinessException("总检/汇总工作量主题查询接口异常");
        }
        return qualityFilm;
    }

    //减少两层循环专用测试方法
    private QualityFilm invokeAndGetResultSatisfiedTest2(String queryDsl) {
        QualityFilm qualityFilm=new QualityFilm();
        try {
            JSONObject jsonObject = new JSONObject();
            // 结果解析
            Integer total;

            List<Map<String, Object>> mapList = new ArrayList<>();


            JSONObject aggregations = jsonObject.getJSONObject("aggregations");
        if (null != aggregations) {
            JSONObject hospNameGroup = aggregations.getJSONObject("hospname_name_group");
            JSONArray hospNameBuckets = hospNameGroup.getJSONArray("buckets");
//                List<DepartmentFilmDTO> listMap=new ArrayList<>();
                List doctorLoginIdList = new ArrayList<>();
                for (Object item : doctorLoginIdList) {
                    Map<String, Object> doctorLoginIdMap = (Map<String, Object>) item;
                    String doctorLoginId = "";
                    if (null != doctorLoginIdMap.get("key")) {
                        doctorLoginId = doctorLoginIdMap.get("key").toString();
                    }
                    Map<String, Object> doctorNameGroup = (Map<String, Object>) doctorLoginIdMap.get("doctor_name_group");
                    List doctorNameList = (List) doctorNameGroup.get("buckets");
                    for (Object o : doctorNameList) {
                        Map<String, Object> doctorNameMap = (Map<String, Object>) o;
                        String doctorName = "";
                        if (null != doctorNameMap.get("key")) {
                            doctorName = doctorNameMap.get("key").toString();
                        }
                        Map<String, Object> typeGroup = (Map<String, Object>) doctorNameMap.get("type_group");
                        List typeList = (List) typeGroup.get("buckets");
                        Map<String, Object> ma = new HashMap<>(8);
                        ma.put("doctor_loginid", doctorLoginId);
                        ma.put("doctor_name", doctorName);
                        for (Object value : typeList) {
                            Map<String, Object> typeMap = (Map<String, Object>) value;
                            Map<String, Object> ctSum = (Map<String, Object>) typeMap.get("ct_sum");
                            ctSum.putIfAbsent("value", 0.0);
                            if ("0".equals(typeMap.get("key").toString())) {
                                ma.put("check_sum", DecimalFormatUtil.POSITIVE_INTEGER_FORMAT.format(new BigDecimal(ctSum.get("value").toString())));
                            }
                            if ("1".equals(typeMap.get("key").toString())) {
                                ma.put("quality_sum", DecimalFormatUtil.POSITIVE_INTEGER_FORMAT.format(new BigDecimal(ctSum.get("value").toString())));
                            }
                        }
                        mapList.add(ma);
                    }
                }
            }
        }catch (Exception e) {
            throw new BusinessException("总检/汇总工作量主题查询接口异常");
        }
        return qualityFilm;
    }


    //正常设置param
    private void setParamsTest(QualityFilmVo qualityFilmVo, List<QueryBuilder> must, UserChannelAndHospital channelOrHospitals) {
        if (channelOrHospitals.getChannelAndHospitalType().equals(0) || channelOrHospitals.getChannelAndHospitalType().equals(-1)) {
            throw new BusinessException("无权查询数据！");
        }

        //登记时间 检查时间   二者择其一
        if (StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateEnd()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateEnd())) {
            throw new BusinessException("登记时间和检查时间不能同时选择");
        }

        //工作量查询时间
        if (StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateEnd())) {
            must.add(QueryBuilders.rangeQuery(QualityFilmEnum.UPLOADDATE.getFields())
                    .gte(qualityFilmVo.getUpLoadDateStart())
                    .lte(qualityFilmVo.getUpLoadDateEnd())
                    .includeLower(true)
                    .includeUpper(true));
        }
        if (StringUtils.isNotBlank(qualityFilmVo.getExamineDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateEnd())) {
            must.add(QueryBuilders.rangeQuery(QualityFilmEnum.EXAMINEDATE.getFields())
                    .gte(qualityFilmVo.getExamineDateStart())
                    .lte(qualityFilmVo.getExamineDateEnd())
                    .includeLower(true)
                    .includeUpper(true));
        }
        if (StringUtils.isNotBlank(qualityFilmVo.getInstituteId())) {
            String instituteId = qualityFilmVo.getInstituteId();
            String[] instituteIds = instituteId.split(MagicConst.COMMA_SPLIT);
            List<String> list = Arrays.asList(instituteIds);
            List<String> collectCheckLineCode = channelOrHospitals.getHospitals().values().stream().flatMap(List::stream).map(UserHospitalAndName::getCheckLines)
                    .flatMap(List::stream).map(UserCheckLineAndName::getCheckLineCode).collect(Collectors.toList());
            Optional<String> any = list.stream().filter(s -> !collectCheckLineCode.contains(s)).findAny();
            if (any.isPresent()) {
                throw new BusinessException("检线信息范围异常！");
            }
            must.add(QueryBuilders.termsQuery(QualityFilmEnum.INSTITUTEID.getFields(), instituteIds));
        } else {
            if (channelOrHospitals.getChannelAndHospitalType().equals(1)) {
                List<String> collectCheckLineCode = channelOrHospitals.getHospitals().values().stream().flatMap(List::stream).map(UserHospitalAndName::getCheckLines)
                        .flatMap(List::stream).map(UserCheckLineAndName::getCheckLineCode).collect(Collectors.toList());
                must.add(QueryBuilders.termsQuery(QualityFilmEnum.INSTITUTEID.getFields(), collectCheckLineCode));
            }
        }


        //医生姓名(分院与医生 一对多的关系)
        if (StringUtils.isNotBlank(qualityFilmVo.getDoctorName())) {
            String departmentId = qualityFilmVo.getDoctorName();
            String[] departmentIds = departmentId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(QualityFilmEnum.DOCTORNAME.getFields(), departmentIds));
        }

        //分院id(分院与医生 一对多的关系)
        if (StringUtils.isNotBlank(qualityFilmVo.getLocId())) {
            String itemId = qualityFilmVo.getLocId();
            String[] itemIds = itemId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(QualityFilmEnum.LOCIDID.getFields(), itemIds));
        }


    }

    //减少循环设置param
    private void setParamsTest2(QualityFilmVo qualityFilmVo, List<QueryBuilder> must, UserChannelAndHospital channelOrHospitals) {
        if (channelOrHospitals.getChannelAndHospitalType().equals(0) || channelOrHospitals.getChannelAndHospitalType().equals(-1)) {
            throw new BusinessException("无权查询数据！");
        }

        //登记时间 检查时间   二者择其一
        if (StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateEnd()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateEnd())) {
            throw new BusinessException("登记时间和检查时间不能同时选择");
        }

        //工作量查询时间
        if (StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateEnd())) {
            must.add(QueryBuilders.rangeQuery(QualityFilmEnum.UPLOADDATE.getFields())
                    .gte(qualityFilmVo.getUpLoadDateStart())
                    .lte(qualityFilmVo.getUpLoadDateEnd())
                    .includeLower(true)
                    .includeUpper(true));
        }
        if (StringUtils.isNotBlank(qualityFilmVo.getExamineDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateEnd())) {
            must.add(QueryBuilders.rangeQuery(QualityFilmEnum.EXAMINEDATE.getFields())
                    .gte(qualityFilmVo.getExamineDateStart())
                    .lte(qualityFilmVo.getExamineDateEnd())
                    .includeLower(true)
                    .includeUpper(true));
        }

        //医生姓名(分院与医生 一对多的关系)
        if (StringUtils.isNotBlank(qualityFilmVo.getDoctorName())) {
            String departmentId = qualityFilmVo.getDoctorName();
            String[] departmentIds = departmentId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(QualityFilmEnum.DOCTORNAME.getFields(), departmentIds));
        }

        //分院id(分院与医生 一对多的关系)
        if (StringUtils.isNotBlank(qualityFilmVo.getLocId())) {
            String itemId = qualityFilmVo.getLocId();
            String[] itemIds = itemId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(QualityFilmEnum.LOCIDID.getFields(), itemIds));
        }


    }

    private DepartmentFilm invokeAndGetResultSatisfiedTestHaveParam(String queryDsl) {
        DepartmentFilm departmentFilm = new DepartmentFilm();
        try {
            JSONObject jsonObject = new JSONObject();
            // 结果解析
            Integer total;
            JSONArray results = JSONArray.parseArray(JSON.toJSONString(JSONPath.eval(jsonObject, "$.aggregations.hospname_name.buckets[?(@.length !=0)].doctor_loginid.buckets[?(@.length !=0)].item_name.buckets[?(@.length !=0)].result.hits.hits[?(@.length !=0)]._source")));
            JSONArray cts = JSONArray.parseArray(JSON.toJSONString(JSONPath.eval(jsonObject, "$.aggregations.hospname_name.buckets[?(@.length !=0)].doctor_loginid.buckets[?(@.length !=0)].item_name.buckets[?(@.length !=0)].ct.value")));
            List<Map> returnDataList = IntStream.range(0, results.size()).mapToObj(index ->
            {
                JSONObject object = results.getJSONObject(index);
                object.put(ReaderEnum.ct.getFields(), DecimalFormatUtil.POSITIVE_INTEGER_FORMAT.format(cts.getBigDecimal(index)));
                return (Map) object;
            }).collect(Collectors.toList());
//            JSONObject aggregations = jsonObject.getJSONObject("aggregations");
//            if (null != aggregations) {
//                JSONObject hospNameGroup = aggregations.getJSONObject("hospname_name_group");
//                JSONArray hospNameBuckets = hospNameGroup.getJSONArray("buckets");
//                for (Object hospNameBucket : hospNameBuckets) {
//                    Map<String, Object> hospNameMap = (Map<String, Object>) hospNameBucket;
//                    String hospId = "";
//                    if (null != hospNameMap.get("key")) {
//                        hospId = hospNameMap.get("key").toString();
//                    }
//                    Map<String, Object> doctorNameGroup = (Map<String, Object>) hospNameMap.get("doctor_name_group");
//                    List doctorNameList = (List) doctorNameGroup.get("buckets");
//                    for (Object o : doctorNameList) {
//                        Map<String, Object> doctorNameMap = (Map<String, Object>) o;
//                        String doctorName = "";
//                        if (null != doctorNameMap.get("key")) {
//                            doctorName = doctorNameMap.get("key").toString();
//                        }
//                        Map<String, Object> doctorLoginIdGroup = (Map<String, Object>) doctorNameMap.get("doctor_loginid_group");
//                        List doctorLoginIdList = (List) doctorLoginIdGroup.get("buckets");
//                        for (Object value : doctorLoginIdList) {
//                            Map<String, Object> doctorLoginIdMap = (Map<String, Object>) value;
//                            String doctorLoginId = "";
//                            if (null != doctorLoginIdMap.get("key")) {
//                                doctorLoginId = doctorLoginIdMap.get("key").toString();
//                            }
//                            Map<String, Object> departmentGroup = (Map<String, Object>) doctorLoginIdMap.get("department_name_group");
//                            List departmentList = (List) departmentGroup.get("buckets");
//                            for (Object item : departmentList) {
//                                Map<String, Object> departmentMap = (Map<String, Object>) item;
//                                String departmentName = "";
//                                if (null != departmentMap.get("key")) {
//                                    departmentName = departmentMap.get("key").toString();
//                                }
//                                Map<String, Object> itemNameGroup = (Map<String, Object>) departmentMap.get("item_name_group");
//                                List itemNameList = (List) itemNameGroup.get("buckets");
//                                for (Object element : itemNameList) {
//                                    Map<String, Object> itemNameMap = (Map<String, Object>) element;
//                                    String itemName = "";
//                                    if (null != itemNameMap.get("key")) {
//                                        itemName = itemNameMap.get("key").toString();
//                                    }
//                                    Map<String, Object> ctSum = (Map<String, Object>) itemNameMap.get("ct_sum");
//                                    ctSum.putIfAbsent("value", 0.0);
//                                    Map<String, Object> ma = new HashMap<>(16);
//                                    ma.put("hospname_name", hospId);
//                                    ma.put("doctor_name", doctorName);
//                                    ma.put("doctor_loginid", doctorLoginId);
//                                    ma.put("department_name", departmentName);
//                                    ma.put("item_name", itemName);
//                                    ma.put("ct", DecimalFormatUtil.POSITIVE_INTEGER_FORMAT.format(new BigDecimal(ctSum.get("value").toString())));
//                                    mapList.add(ma);
//                                }
//                            }
//                        }
//                    }
//                }
//            }

        } catch (Exception e) {
            throw new BusinessException("阅片医生工作量主题查询接口异常");
        }
        return departmentFilm;
    }

    private DepartmentFilm invokeAndGetResultSatisfiedTestNoParam(String queryDsl) {
        DepartmentFilm departmentFilm = new DepartmentFilm();
        try {
            JSONObject jsonObject = new JSONObject();
            // 结果解析
            Integer total;
            List<ReturnData> returnData = new ArrayList<>();
            JSONArray results = JSONArray.parseArray(JSON.toJSONString(JSONPath.eval(jsonObject, "$.aggregations.hospname_name.buckets[?(@.length !=0)].doctor_loginid.buckets[?(@.length !=0)].item_name.buckets[?(@.length !=0)].result.hits.hits[?(@.length !=0)]._source")));
            JSONArray cts = JSONArray.parseArray(JSON.toJSONString(JSONPath.eval(jsonObject, "$.aggregations.hospname_name.buckets[?(@.length !=0)].doctor_loginid.buckets[?(@.length !=0)].item_name.buckets[?(@.length !=0)].ct.value")));
            List<Map<String,Object>> returnDataList = IntStream.range(0, results.size()).mapToObj(index ->
            {
                JSONObject object = results.getJSONObject(index);
                object.put(ReaderEnum.ct.getFields(), DecimalFormatUtil.POSITIVE_INTEGER_FORMAT.format(cts.getBigDecimal(index)));
                return (Map<String,Object>) object;
            }).collect(Collectors.toList());
            JSONObject aggregations = jsonObject.getJSONObject("aggregations");
            if (null != aggregations) {
                JSONObject hospNameGroup = aggregations.getJSONObject("hospname_name_group");
                JSONArray hospNameBuckets = hospNameGroup.getJSONArray("buckets");
                for (Object hospNameBucket : hospNameBuckets) {
                    Map<String, Object> hospNameMap = (Map<String, Object>) hospNameBucket;
                    String hospName = "";
                    if (null != hospNameMap.get("key")) {
                        hospName = hospNameMap.get("key").toString();
                    }
                    Map<String, Object> doctorNameGroup = (Map<String, Object>) hospNameMap.get("doctor_name_group");
                    List doctorNameList = (List) doctorNameGroup.get("buckets");
                    for (Object o : doctorNameList) {
                        Map<String, Object> doctorNameMap = (Map<String, Object>) o;
                        String doctorName = "";
                        if (null != doctorNameMap.get("key")) {
                            doctorName = doctorNameMap.get("key").toString();
                        }
                        Map<String, Object> doctorLoginIdGroup = (Map<String, Object>) doctorNameMap.get("doctor_loginid_group");
                        List doctorLoginIdList = (List) doctorLoginIdGroup.get("buckets");
                        for (Object value : doctorLoginIdList) {
                            Map<String, Object> doctorLoginIdMap = (Map<String, Object>) value;
                            String doctorLoginId = "";
                            if (null != doctorLoginIdMap.get("key")) {
                                doctorLoginId = doctorLoginIdMap.get("key").toString();
                            }
                            Map<String, Object> departmentGroup = (Map<String, Object>) doctorLoginIdMap.get("department_name_group");
                            List departmentList = (List) departmentGroup.get("buckets");
                            for (Object item : departmentList) {
                                Map<String, Object> departmentMap = (Map<String, Object>) item;
                                String departmentName = "";
                                if (null != departmentMap.get("key")) {
                                    departmentName = departmentMap.get("key").toString();
                                }
                                Map<String, Object> itemNameGroup = (Map<String, Object>) departmentMap.get("item_name_group");
                                List itemNameList = (List) itemNameGroup.get("buckets");
                                for (Object element : itemNameList) {
                                    Map<String, Object> itemNameMap = (Map<String, Object>) element;
                                    String itemName = "";
                                    if (null != itemNameMap.get("key")) {
                                        itemName = itemNameMap.get("key").toString();
                                    }
                                    Map<String, Object> ctSum = (Map<String, Object>) itemNameMap.get("ct_sum");
                                    ctSum.putIfAbsent("value", 0.0);
                                    Map<String, Object> ma = new HashMap<>(16);
                                    ma.put("hospname_name", hospName);
                                    ma.put("doctor_name", doctorName);
                                    ma.put("doctor_loginid", doctorLoginId);
                                    ma.put("department_name", departmentName);
                                    ma.put("item_name", itemName);
                                    ma.put("ct", DecimalFormatUtil.POSITIVE_INTEGER_FORMAT.format(new BigDecimal(ctSum.get("value").toString())));
                                }
                            }
                        }
                    }
                }
            }

            total = results.size();
            BigDecimal a = new BigDecimal(String.valueOf(total));
            departmentFilm.setTotalStr(DecimalFormatUtil.POSITIVE_INTEGER_FORMAT.format(a));
            departmentFilm.setTotal(total);

            departmentFilm.setReturnData(returnDataList);

            departmentFilm.setColumnNames(returnData.stream()
                    .filter(e -> e.getShowFlag().equals(0))
                    .map(el -> {
                        Map<String, Object> map = new HashMap<>(16);
                        map.put("returnDataCode", el.getReturnDataCode());
                        map.put("returnDataName", el.getReturnDataName());
                        map.put("groupFlag", el.getGroupFlag());
                        map.put("sumOrAvgFlag", el.getSumOrAvgFlag());
                        map.put("fieldDesc", el.getFieldDesc());
                        return map;
                    })
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            throw new BusinessException("阅片医生工作量主题查询接口异常");
        }
        return departmentFilm;
    }

    //正常设置param
    private void setParamsTestNoParam(QualityFilmVo qualityFilmVo, List<QueryBuilder> must, UserChannelAndHospital channelOrHospitals) {
        if (channelOrHospitals.getChannelAndHospitalType().equals(0) || channelOrHospitals.getChannelAndHospitalType().equals(-1)) {
            throw new BusinessException("无权查询数据！");
        }

        //登记时间 检查时间   二者择其一
        if (StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateEnd()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateEnd())) {
            throw new BusinessException("登记时间和检查时间不能同时选择");
        }

        //工作量查询时间
        if (StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateEnd())) {
            must.add(QueryBuilders.rangeQuery(QualityFilmEnum.UPLOADDATE.getFields())
                    .gte(qualityFilmVo.getUpLoadDateStart())
                    .lte(qualityFilmVo.getUpLoadDateEnd())
                    .includeLower(true)
                    .includeUpper(true));
        }
        if (StringUtils.isNotBlank(qualityFilmVo.getExamineDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateEnd())) {
            must.add(QueryBuilders.rangeQuery(QualityFilmEnum.EXAMINEDATE.getFields())
                    .gte(qualityFilmVo.getExamineDateStart())
                    .lte(qualityFilmVo.getExamineDateEnd())
                    .includeLower(true)
                    .includeUpper(true));
        }
        if (StringUtils.isNotBlank(qualityFilmVo.getInstituteId())) {
            String instituteId = qualityFilmVo.getInstituteId();
            String[] instituteIds = instituteId.split(MagicConst.COMMA_SPLIT);
            List<String> list = Arrays.asList(instituteIds);
            List<String> collectCheckLineCode = channelOrHospitals.getHospitals().values().stream().flatMap(List::stream).map(UserHospitalAndName::getCheckLines)
                    .flatMap(List::stream).map(UserCheckLineAndName::getCheckLineCode).collect(Collectors.toList());
            Optional<String> any = list.stream().filter(s -> !collectCheckLineCode.contains(s)).findAny();
            if (any.isPresent()) {
                throw new BusinessException("检线信息范围异常！");
            }
            must.add(QueryBuilders.termsQuery(QualityFilmEnum.INSTITUTEID.getFields(), instituteIds));
        } else {
            if (channelOrHospitals.getChannelAndHospitalType().equals(1)) {
                List<String> collectCheckLineCode = channelOrHospitals.getHospitals().values().stream().flatMap(List::stream).map(UserHospitalAndName::getCheckLines)
                        .flatMap(List::stream).map(UserCheckLineAndName::getCheckLineCode).collect(Collectors.toList());
                must.add(QueryBuilders.termsQuery(QualityFilmEnum.INSTITUTEID.getFields(), collectCheckLineCode));
            }
        }


        //医生姓名(分院与医生 一对多的关系)
        if (StringUtils.isNotBlank(qualityFilmVo.getDoctorName())) {
            String departmentId = qualityFilmVo.getDoctorName();
            String[] departmentIds = departmentId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(QualityFilmEnum.DOCTORNAME.getFields(), departmentIds));
        }

        //分院id(分院与医生 一对多的关系)
        if (StringUtils.isNotBlank(qualityFilmVo.getLocId())) {
            String itemId = qualityFilmVo.getLocId();
            String[] itemIds = itemId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(QualityFilmEnum.LOCIDID.getFields(), itemIds));
        }


    }

    //减少循环设置param
    private void setParams(QualityFilmVo qualityFilmVo, List<QueryBuilder> must, UserChannelAndHospital channelOrHospitals) {
        if (channelOrHospitals.getChannelAndHospitalType().equals(0) || channelOrHospitals.getChannelAndHospitalType().equals(-1)) {
            throw new BusinessException("无权查询数据！");
        }

        //登记时间 检查时间   二者择其一
        if (StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateEnd()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateEnd())) {
            throw new BusinessException("登记时间和检查时间不能同时选择");
        }

        //工作量查询时间
        if (StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getUpLoadDateEnd())) {
            must.add(QueryBuilders.rangeQuery(QualityFilmEnum.UPLOADDATE.getFields())
                    .gte(qualityFilmVo.getUpLoadDateStart())
                    .lte(qualityFilmVo.getUpLoadDateEnd())
                    .includeLower(true)
                    .includeUpper(true));
        }
        if (StringUtils.isNotBlank(qualityFilmVo.getExamineDateStart()) && StringUtils.isNotBlank(qualityFilmVo.getExamineDateEnd())) {
            must.add(QueryBuilders.rangeQuery(QualityFilmEnum.EXAMINEDATE.getFields())
                    .gte(qualityFilmVo.getExamineDateStart())
                    .lte(qualityFilmVo.getExamineDateEnd())
                    .includeLower(true)
                    .includeUpper(true));
        }

        //医生姓名(分院与医生 一对多的关系)
        if (StringUtils.isNotBlank(qualityFilmVo.getDoctorName())) {
            String departmentId = qualityFilmVo.getDoctorName();
            String[] departmentIds = departmentId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(QualityFilmEnum.DOCTORNAME.getFields(), departmentIds));
        }

        //分院id(分院与医生 一对多的关系)
        if (StringUtils.isNotBlank(qualityFilmVo.getLocId())) {
            String itemId = qualityFilmVo.getLocId();
            String[] itemIds = itemId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(QualityFilmEnum.LOCIDID.getFields(), itemIds));
        }


    }
}
