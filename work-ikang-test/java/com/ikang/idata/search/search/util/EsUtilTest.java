package com.ikang.idata.search.search.util;

import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.BaseVO;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class EsUtilTest {

    @Test
    void testGetPageSearchSourceBuilder() throws Exception {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        final SearchSourceBuilder expectedResult = new SearchSourceBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final SearchSourceBuilder result = EsUtil.getPageSearchSourceBuilder(vo);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetSortBuilder() {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        // Run the test
        final SortBuilder<?>[] result = EsUtil.getSortBuilder(vo);

        // Verify the results
    }

    @Test
    void testGetSortComparator() {
        // Setup
        final BaseVO vo = new BaseVO();
        vo.setResourceId(0L);
        vo.setQueryMap(new HashMap<>());
        vo.setPageNum(0);
        vo.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setName("name");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        aggregationCondition.setExpand(new JSONObject(0, false));
        vo.setGroupBy(Arrays.asList(aggregationCondition));
        vo.setSortList(Arrays.asList(new Order("field", Direction.ASC)));

        // Run the test
        final Comparator<? super Map<String, Object>> result = EsUtil.getSortComparator(vo);

        // Verify the results
    }

    @Test
    void testInterval() {
        // Setup
        final DateHistogramInterval expectedResult = new DateHistogramInterval("expression");

        // Run the test
        final DateHistogramInterval result = EsUtil.interval("format");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testConvertQueryBuilderList() {
        // Setup
        final List<TermsQueryBuilder> expectedResult = Arrays.asList(new TermsQueryBuilder("fieldName", "values"));

        // Run the test
        final List<TermsQueryBuilder> result = EsUtil.convertQueryBuilderList("o");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
