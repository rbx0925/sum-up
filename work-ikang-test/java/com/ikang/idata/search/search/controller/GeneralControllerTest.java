package com.ikang.idata.search.search.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.entity.BaseSearch;
import com.ikang.idata.common.entity.dto.DetailDto;
import com.ikang.idata.common.entity.es.DetailDtoHits;
import com.ikang.idata.common.entity.es.SearchScrollData;
import com.ikang.idata.common.entity.vo.GeneralSearchVO;
import com.ikang.idata.common.enums.GeneralEnum;
import com.ikang.idata.common.exceptions.BusinessException;
import com.ikang.idata.search.search.service.ESHttpClientService;
import com.ikang.idata.search.search.service.GeneralService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GeneralController.class)
@Slf4j
class GeneralControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeneralService mockGeneralService;

    @Autowired
    private ESHttpClientService esHttpClientService;
    /**
     * bi接口-报告时效性下载明细游标 滚屏查询
     */
    @Value("${bigdata.url.kanbanReportService.searchScrollUrl}")
    private String searchScrollUrl;
    /**
     * bi接口-滚屏查询
     */
    @Value("${bigdata.url.kanbanReportService.scrollUrl}")
    private String getSearchScroll;
    @Value("${bigdata.url.kanbanReportService.scrollIdKeepTime}")
    private String SCROLLID_KEEPTIME;

    @Test
    void testFindSMS() throws Exception {
        // Setup
        // Configure GeneralService.find(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        baseSearch.setColumnNames(Arrays.asList(new HashMap<>()));
        baseSearch.setSumOrAvgMap(new HashMap<>());
        baseSearch.setListMap(Arrays.asList(new HashMap<>()));
        baseSearch.setResourceId(0L);
        baseSearch.setDesensitization(false);
        baseSearch.setSort("sort");
        when(mockGeneralService.find(new GeneralSearchVO(), "satisfiedSMSUrl")).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/general/findSMS")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportSMSExcel() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/general/exportSMSExcel")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockGeneralService).exportExcel(eq(new GeneralSearchVO()), any(HttpServletResponse.class),
                eq("satisfiedSMSUrl"), eq("短信满意度汇总"));
    }

    @Test
    void testExportSMSExcel_GeneralServiceThrowsException() throws Exception {
        // Setup
        doThrow(Exception.class).when(mockGeneralService).exportExcel(eq(new GeneralSearchVO()),
                any(HttpServletResponse.class), eq("satisfiedSMSUrl"), eq("短信满意度汇总"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/general/exportSMSExcel")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testFindReport() throws Exception {
        // Setup
        // Configure GeneralService.find(...).
        final BaseSearch baseSearch = new BaseSearch();
        baseSearch.setPageSize(0);
        baseSearch.setPageNum(0);
        baseSearch.setReturnData(Arrays.asList(new HashMap<>()));
        baseSearch.setTotal(0);
        baseSearch.setTotalStr("totalStr");
        baseSearch.setColumnNames(Arrays.asList(new HashMap<>()));
        baseSearch.setSumOrAvgMap(new HashMap<>());
        baseSearch.setListMap(Arrays.asList(new HashMap<>()));
        baseSearch.setResourceId(0L);
        baseSearch.setDesensitization(false);
        baseSearch.setSort("sort");
        when(mockGeneralService.find(new GeneralSearchVO(), "satisfiedReportUrl")).thenReturn(baseSearch);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/general/findReport")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportReportExcel() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/general/exportReportExcel")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockGeneralService).exportExcel(eq(new GeneralSearchVO()), any(HttpServletResponse.class),
                eq("satisfiedReportUrl"), eq("报告数据时效汇总"));
    }

    @Test
    void testExportReportExcel_GeneralServiceThrowsException() throws Exception {
        // Setup
        doThrow(Exception.class).when(mockGeneralService).exportExcel(eq(new GeneralSearchVO()),
                any(HttpServletResponse.class), eq("satisfiedReportUrl"), eq("报告数据时效汇总"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/general/exportReportExcel")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testExportReportExcelDetail() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/general/exportReportExcelDetail")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockGeneralService).exportReportExcelDetail(eq(new GeneralSearchVO()), any(HttpServletResponse.class),
                eq("报告数据时效汇总明细"));
    }

    @Test
    void testExportReportExcelDetail_GeneralServiceThrowsException() throws Exception {
        // Setup
        doThrow(Exception.class).when(mockGeneralService).exportReportExcelDetail(eq(new GeneralSearchVO()),
                any(HttpServletResponse.class), eq("报告数据时效汇总明细"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/general/exportReportExcelDetail")
                .content("content").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    /**
     * 构建Detail
     * @param generalSearchVO
     * @return
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/7/6 9:33
     */
    public List<DetailDto> getDetail(GeneralSearchVO generalSearchVO) {
        long start = System.currentTimeMillis();
        // 到检月份
        String regMonth = generalSearchVO.getRegMonth();
        if (null == regMonth) {
            throw new BusinessException("到检月份不能为空");
        }
        // 检线
        String hospId = generalSearchVO.getHospId();
        if (null == hospId) {
            throw new BusinessException("检线不能为空");
        }

        Date date = DateUtil.parse(regMonth + "-01");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> must = queryBuilder.must();
        must.add(QueryBuilders.termQuery(GeneralEnum.HOSPID.getFields(), hospId));

        must.add(QueryBuilders.rangeQuery(GeneralEnum.REGDATE.getFields())
                .gte(DateUtil.formatDate(DateUtil.beginOfMonth(date)))
                .lte(DateUtil.formatDate(DateUtil.endOfMonth(date)))
                .includeLower(true)
                .includeUpper(true));

        // 先默认所有的都要添加 锁定数据
        int islock = 1;
        // 到检月份
        //选年加月的时候
        if (regMonth.length() > MagicConst.INT_5) {
            //当前时间
            DateTime nowTime = DateTime.now();
            //上个月的时间
            DateTime dateTime1 = DateUtil.offsetMonth(nowTime, -1);
            //上个月第一天
            DateTime dateTime2 = DateUtil.beginOfMonth(dateTime1);
            //当月第一天
            DateTime dateTime3 = DateUtil.beginOfMonth(nowTime);
            //选择查询的时间
            String dateStr = regMonth + "-01";
            Date date1 = DateUtil.parse(dateStr);
            int i = date1.compareTo(dateTime2);
            int i1 = date1.compareTo(dateTime3);
            if (0 == i) {
                DateTime dateTimeHour = DateUtil.offsetHour(dateTime3, 250);
                boolean in = DateUtil.isIn(nowTime, dateTime3, dateTimeHour);
                if (in) {
                    // 选择当月时间   不锁定数据   因为本月过完
                    islock = 0;
                }
            }
            if (0 == i1) {
                //选择时间与当前时间对比  是不是在当前月份的1-11 是  不加
                islock = 0;
            }
            must.add(QueryBuilders.termQuery(GeneralEnum.ISLOCK.getFields(), islock));
        }
        must.add(QueryBuilders.termQuery(GeneralEnum.ISLOCK.getFields(), islock));
        builder.query(queryBuilder);
        log.info("报告时效性汇总-下载明细-拼接dsl  times:{}", System.currentTimeMillis() - start);
        // 滚屏获取数据
        long scrollStart = System.currentTimeMillis();
        List<DetailDto> detailDtos = invokeAndScrollGetResult(builder.toString());
        log.info("报告时效性汇总-下载明细-滚屏获取数据 times:{}", System.currentTimeMillis() - scrollStart);
        return detailDtos;
    }

    public List<DetailDto> invokeAndScrollGetResult(String queryDsl) {
        List<DetailDto> result = new ArrayList<>();
        log.info("=====================================构建滚屏请求===========================================================");
        // bi接口-滚屏查询
        SearchScrollData esData = esHttpClientService.exchange(searchScrollUrl, HttpMethod.POST, SearchScrollData.class, queryDsl);
        try {
            List<DetailDtoHits> hits = esData.getCoverHits();
            // scroll_id
            String scrollId = esData.getScroll();
            while (hits != null && hits.size() > 0) {
                result.addAll(hits.stream().map(DetailDtoHits::getSource).collect(Collectors.toList()));
                String scrollBody = "{\"scroll\" : \"" + SCROLLID_KEEPTIME + "\",\"scroll_id\" : \"" + scrollId + "\"}";
                log.info("=====================================构建滚屏请求===========================================================");
                esData = esHttpClientService.exchange(getSearchScroll, HttpMethod.POST, SearchScrollData.class, scrollBody);
                scrollId = esData.getScroll();
                hits = esData.getCoverHits();
            }
        } catch (Exception e) {
            log.error("general error:{}", e);
            throw new BusinessException("报告数据时效汇总-下载明细接口调用异常");
        }
        return result;
    }
}
