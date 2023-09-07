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
class QualityFilmServiceTestNew {

    //减少循环设置param测试
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
