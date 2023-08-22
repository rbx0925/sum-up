package com.ikang.idata.search.search.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.db.sql.Direction;
import cn.hutool.db.sql.Order;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.primitives.Ints;
import com.ikang.idata.common.entity.AggregationCondition;
import com.ikang.idata.common.entity.ReportsWayAgg;
import com.ikang.idata.common.entity.ReportsWayData;
import com.ikang.idata.common.entity.vo.ReportsWayVO;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import static com.ikang.idata.common.utils.DateUtil.DATEFORMATSECOND;
import static org.assertj.core.api.Assertions.assertThat;

class ReportsWayServiceTest {

    private ReportsWayService reportsWayServiceUnderTest;

    @BeforeEach
    void setUp() throws Exception {
        reportsWayServiceUnderTest = new ReportsWayService();
    }

    @Test
    void testFind() throws Exception {
        // Setup
        final ReportsWayVO reportsWayVO = new ReportsWayVO();
        reportsWayVO.setResourceId(0L);
        reportsWayVO.setQueryMap(new HashMap<>());
        reportsWayVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        reportsWayVO.setGroupBy(Arrays.asList(aggregationCondition));
        reportsWayVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        reportsWayVO.setDesensitization(false);
        reportsWayVO.setInstituteId("instituteId");
        reportsWayVO.setAreaId("areaId");
        reportsWayVO.setRegisterDataStart("registerDataStart");
        reportsWayVO.setRegisterDataEnd("registerDataEnd");
        reportsWayVO.setCardNo("cardNo");
        reportsWayVO.setWorkNo("workNo");
        reportsWayVO.setMobile("mobile");
        reportsWayVO.setProgressStatus("progressStatus");
        reportsWayVO.setMisReportStatus("misReportStatus");
        reportsWayVO.setReportType("reportType");
        reportsWayVO.setReportIsOverdue("reportIsOverdue");
        reportsWayVO.setRemainingDays("remainingDays");

        // Run the test
        final ReportsWayData result = reportsWayServiceUnderTest.find(reportsWayVO);

        // Verify the results
    }

    @Test
    void testAssemblyConditions() throws Exception {
        // Setup
        final JSONObject originalQueryConditions = new JSONObject(0, false);
        final BoolQueryBuilder expectedResult = new BoolQueryBuilder(StreamInput.wrap("content".getBytes()));

        // Run the test
        final BoolQueryBuilder result = reportsWayServiceUnderTest.assemblyConditions(originalQueryConditions);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAgg() {
        // Setup
        final ReportsWayVO reportsWayVO = new ReportsWayVO();
        reportsWayVO.setResourceId(0L);
        reportsWayVO.setQueryMap(new HashMap<>());
        reportsWayVO.setPageSize(0);
        final AggregationCondition aggregationCondition = new AggregationCondition();
        aggregationCondition.setCode("code");
        aggregationCondition.setDataType("dataType");
        aggregationCondition.setFormat("format");
        reportsWayVO.setGroupBy(Arrays.asList(aggregationCondition));
        reportsWayVO.setSortList(Arrays.asList(new Order("field", Direction.ASC)));
        reportsWayVO.setDesensitization(false);
        reportsWayVO.setInstituteId("instituteId");
        reportsWayVO.setAreaId("areaId");
        reportsWayVO.setRegisterDataStart("registerDataStart");
        reportsWayVO.setRegisterDataEnd("registerDataEnd");
        reportsWayVO.setCardNo("cardNo");
        reportsWayVO.setWorkNo("workNo");
        reportsWayVO.setMobile("mobile");
        reportsWayVO.setProgressStatus("progressStatus");
        reportsWayVO.setMisReportStatus("misReportStatus");
        reportsWayVO.setReportType("reportType");
        reportsWayVO.setReportIsOverdue("reportIsOverdue");
        reportsWayVO.setRemainingDays("remainingDays");

        // Run the test
        final ReportsWayAgg result = reportsWayServiceUnderTest.findAgg(reportsWayVO);

        // Verify the results
    }

    @Test
    void testDesensitizationByRealName() {
        assertThat(reportsWayServiceUnderTest.desensitizationByRealName("realName")).isEqualTo("realName");
    }

    /**
     * @description: 当前时间的6天之前的时间值
     *
     * @param dateStr 日期时间
     * @return java.lang.String
     * @auther yanan.mu-ext
     * @date 2022-03-15 下午4:49
     */
    private static String advance(String dateStr){
//        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);

        //结果：2017-02-23 22:33:23
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, -6);

        String format = DateUtil.format(newDate, DATEFORMATSECOND);

        return format;
    }

    public static void main(String[] args) {
        DateTime dateTime = DateUtil.parse("1992-10-17 23:59:59");
        System.out.println(advance("1992-10-17 23:59:59"));
        System.out.println(dateTime);


        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        //生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(simpleUUID);

        //生成类似：5b9e306a4df4f8c54a39fb0c
        String id = ObjectId.next();
        System.out.println(id);
        //方法2：从Hutool-4.1.14开始提供
        String id2 = ObjectId.next();
        System.out.println(id2);

        //参数1为终端ID
        //参数2为数据中心ID
        Snowflake snowflake = new Snowflake(1, 1);
        long snowflakeId = snowflake.nextId();
        System.out.println(snowflakeId);

        System.out.println(Ints.asList(1, 2, 3, 4));
        System.out.println(Ints.compare(1, 2));
        System.out.println(Ints.join(" ", 1, 2, 3, 4));
        System.out.println(Ints.max(1, 3, 5, 4, 6));
        System.out.println(Ints.tryParse("1234"));


        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("a", 1);
        multimap.put("a", 2);
        multimap.put("a", 4);
        multimap.put("b", 3);
        multimap.put("c", 5);

        System.out.println(multimap.keys());//[a x 3, b, c]
        System.out.println(multimap.get("a"));//[1 ,2, 4]
        System.out.println(multimap.get("b"));//[3]
        System.out.println(multimap.get("c"));//[5]
        System.out.println(multimap.get("d"));//[]

        System.out.println(multimap.asMap());//{a=[1, 2, 4], b=[3], c=[5]}
    }

}
