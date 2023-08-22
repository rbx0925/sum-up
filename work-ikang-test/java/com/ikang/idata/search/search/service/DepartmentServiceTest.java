package com.ikang.idata.search.search.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.entity.DepartmentFilm;
import com.ikang.idata.common.entity.ReturnData;
import com.ikang.idata.common.entity.UserChannel;
import com.ikang.idata.common.entity.vo.*;
import com.ikang.idata.common.enums.DepartmentFilmEnum;
import com.ikang.idata.common.exceptions.BusinessException;
import com.ikang.idata.common.utils.IdataSecurityUtil;
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
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
@ActiveProfiles(value = {"uat"})
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private ReturnDataFeignServiceImpl mockReturnDataFeignService;
    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;

    @InjectMocks
    private DepartmentFilmService departmentFilmServiceUnderTest;


    @Test
    void testFind() throws Exception {
        // Setup
        final DepartmentFilmVo departmentFilmVo = new DepartmentFilmVo();
        departmentFilmVo.setExamDateStart("examDateStart");
        departmentFilmVo.setExamDateEnd("examDateEnd");
        departmentFilmVo.setExamineDateStart("examineDateStart");
        departmentFilmVo.setExamineDateEnd("examineDateEnd");
        departmentFilmVo.setDoctorNcc("doctorNcc");
        departmentFilmVo.setInstituteId("instituteId");
        departmentFilmVo.setLocId("locId");
        departmentFilmVo.setDepartmentName("departmentName");
        departmentFilmVo.setDepartmentId("departmentId");
        departmentFilmVo.setDoctorName("doctorName");
        departmentFilmVo.setItemName("itemName");
        departmentFilmVo.setType(0);
        departmentFilmVo.setPageNum(0);
        departmentFilmVo.setPageSize(0);
        departmentFilmVo.setGroupBy("groupBy");

        final DepartmentFilm expectedResult = new DepartmentFilm();
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
        when(mockReturnDataFeignService.listByReturnDataIndex("reader_department")).thenReturn(returnData);

        // Run the test
        final DepartmentFilm result = departmentFilmServiceUnderTest.find(departmentFilmVo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    /**
     * 测试dsl
     */
    @Test
    public void testDSL() {
        DepartmentFilmVo departmentFilmVo = JSONObject.parseObject("{\"instituteId\":\"605,Q79,Q32,N77,L77,202,V29\",\"pageNum\":1,\"pageSize\":0}", DepartmentFilmVo.class);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> must = queryBuilder.must();
        builder.size(MagicConst.INT_0);
        //权限
        UserChannelAndHospital channelOrHospitals = authorityFeignService.getChannelOrHospitals(146L);
        setParams(departmentFilmVo, must, channelOrHospitals);
        builder.query(queryBuilder);
        //检线名称聚合
        TermsAggregationBuilder hospNameField = AggregationBuilders.terms(ReaderEnum.hospname_name.getFields()).field(ReaderEnum.hospname_name.getFields()).size(10000).executionHint("map");
        //医生id 聚合
        TermsAggregationBuilder doctorLoginIdField = AggregationBuilders.terms(ReaderEnum.doctor_loginid.getFields()).field(ReaderEnum.doctor_loginid.getFields()).size(10000).executionHint("map");
        //项目名称
        TermsAggregationBuilder itemNameField = AggregationBuilders.terms(ReaderEnum.item_name.getFields()).field(ReaderEnum.item_name.getFields()).size(10000).executionHint("map");
        SumAggregationBuilder aggregationSum = AggregationBuilders.sum(ReaderEnum.ct.getFields()).field(ReaderEnum.ct.getFields());
        TopHitsAggregationBuilder resultAgg = AggregationBuilders.topHits("result").size(1);
        hospNameField.subAggregation(doctorLoginIdField);
        doctorLoginIdField.subAggregation(itemNameField);
        itemNameField.subAggregation(aggregationSum);
        itemNameField.subAggregation(resultAgg);
        builder.aggregation(hospNameField);

        System.out.println(builder.toString());

    }
    @Autowired
    private AuthorityFeignServiceImpl authorityFeignService;

    private void setParams(DepartmentFilmVo departmentFilmVo, List<QueryBuilder> must, UserChannelAndHospital channelOrHospitals) {
        if (channelOrHospitals.getChannelAndHospitalType().equals(0) || channelOrHospitals.getChannelAndHospitalType().equals(-1)) {
            throw new BusinessException("无权查询数据！");
        }
        //类型
        //0-阅片中心已阅,1-影像管理阅片已阅,2-阅片中心未阅,3-影像管理未阅,4-所有检查项目,5-影像管理审核,6-拍片,7-心电采集,8-心电审核
        must.add(QueryBuilders.termsQuery(ReaderEnum.type.getFields(), MagicConst.STR_4));
        //项目状态   猜测4,5 应该是已完结一类的
        must.add(QueryBuilders.termsQuery(ReaderEnum.item_status.getFields(), MagicConst.STR_4, MagicConst.STR_5));
        //登记时间 检查时间   二者择其一
        if (StringUtils.isNotBlank(departmentFilmVo.getExamDateStart()) && StringUtils.isNotBlank(departmentFilmVo.getExamDateEnd()) && StringUtils.isNotBlank(departmentFilmVo.getExamineDateStart()) && StringUtils.isNotBlank(departmentFilmVo.getExamineDateEnd())) {
            throw new BusinessException("登记时间和检查时间不能同时选择");
        }

        //登记时间
        if (StringUtils.isNotBlank(departmentFilmVo.getExamDateStart()) && StringUtils.isNotBlank(departmentFilmVo.getExamDateEnd())) {
            must.add(QueryBuilders.rangeQuery(DepartmentFilmEnum.EXAMDATE.getFields())
                    .gte(departmentFilmVo.getExamDateStart())
                    .lte(departmentFilmVo.getExamDateEnd())
                    .includeLower(true)
                    .includeUpper(true));
        }

        if (StringUtils.isNotBlank(departmentFilmVo.getExamineDateStart()) && StringUtils.isNotBlank(departmentFilmVo.getExamineDateEnd())) {
            must.add(QueryBuilders.rangeQuery(DepartmentFilmEnum.EXAMINEDATE.getFields())
                    .gte(departmentFilmVo.getExamineDateStart())
                    .lte(departmentFilmVo.getExamineDateEnd())
                    .includeLower(true)
                    .includeUpper(true));
        }
        if (StringUtils.isNotBlank(departmentFilmVo.getInstituteId())) {
            String instituteId = departmentFilmVo.getInstituteId();
            String[] instituteIds = instituteId.split(MagicConst.COMMA_SPLIT);
            List<String> list = Arrays.asList(instituteIds);
            List<String> collectCheckLineCode = channelOrHospitals.getHospitals().values().stream().flatMap(List::stream).map(UserHospitalAndName::getCheckLines)
                    .flatMap(List::stream).map(UserCheckLineAndName::getCheckLineCode).collect(Collectors.toList());
            Optional<String> any = list.stream().filter(s -> !collectCheckLineCode.contains(s)).findAny();
            if (any.isPresent()) {
                throw new BusinessException("科室信息范围异常！");
            }
            must.add(QueryBuilders.termsQuery(DepartmentFilmEnum.INSTITUTEID.getFields(), instituteIds));
        } else {
            if (channelOrHospitals.getChannelAndHospitalType().equals(1)) {
                List<String> collectCheckLineCode = channelOrHospitals.getHospitals().values().stream().flatMap(List::stream).map(UserHospitalAndName::getCheckLines)
                        .flatMap(List::stream).map(UserCheckLineAndName::getCheckLineCode).collect(Collectors.toList());
                must.add(QueryBuilders.termsQuery(DepartmentFilmEnum.INSTITUTEID.getFields(), collectCheckLineCode));
            }
        }

        //科室名称
        if (StringUtils.isNotBlank(departmentFilmVo.getDepartmentName())) {
            String departmentId = departmentFilmVo.getDepartmentName();
            String[] departmentIds = departmentId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(DepartmentFilmEnum.DEPARTMENTNAME.getFields(), departmentIds));
        }
        //科室id
        if (StringUtils.isNotBlank(departmentFilmVo.getDepartmentId())) {
            String departmentId = departmentFilmVo.getDepartmentId();
            String[] departmentIds = departmentId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(DepartmentFilmEnum.DEPARTMENTID.getFields(), departmentIds));
        }
        //医生姓名
        if (StringUtils.isNotBlank(departmentFilmVo.getDoctorName())) {
            String doctorId = departmentFilmVo.getDoctorName();
            String[] doctorIds = doctorId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(DepartmentFilmEnum.DOCTORNAME.getFields(), doctorIds));
        }
        //检项名称
        if (StringUtils.isNotBlank(departmentFilmVo.getItemName())) {
            String itemId = departmentFilmVo.getItemName();
            String[] itemIds = itemId.split(MagicConst.COMMA_SPLIT);
            must.add(QueryBuilders.termsQuery(DepartmentFilmEnum.ITEMNAME.getFields(), itemIds));
        }
        //医生员工号
        if (StringUtils.isNotBlank(departmentFilmVo.getDoctorNcc())) {
            must.add(QueryBuilders.termsQuery(ReaderEnum.doctor_ncc.getFields(), departmentFilmVo.getDoctorNcc()));
        }

    }


    @Test
    public void testDSLT() {

        DepartmentFilmVo departmentFilmVo =
                JSONObject.
                parseObject("{\"instituteId\":\"605,Q79,Q32,N77,L77,202,V29\",\"pageNum\":1,\"pageSize\":0}",
                        DepartmentFilmVo.class);

        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> must = queryBuilder.must();
        builder.size(MagicConst.INT_0);
        //权限
        UserChannelAndHospital channelOrHospitals = authorityFeignService.
                getChannelOrHospitals(146L);
        setParams(departmentFilmVo, must, channelOrHospitals);
        builder.query(queryBuilder);
        TermsAggregationBuilder aggDoctorField = AggregationBuilders.terms(ReaderEnum.agg_doctorSWorkload.getFields()).field(ReaderEnum.agg_doctorSWorkload.getFields()).size(10000).executionHint("map");
        SumAggregationBuilder aggregationSum = AggregationBuilders.sum(ReaderEnum.ct.getFields()).field(ReaderEnum.ct.getFields());
        TopHitsAggregationBuilder resultAgg = AggregationBuilders.topHits("result").size(1);
        aggDoctorField.subAggregation(aggregationSum);
        aggDoctorField.subAggregation(resultAgg);
        builder.aggregation(aggDoctorField);

        System.out.println(builder);
    }

}
