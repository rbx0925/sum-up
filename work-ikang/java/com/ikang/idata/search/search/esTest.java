//package com.ikang.idata.search.search;
//
//import cn.hutool.json.JSONArray;
//import cn.hutool.json.JSONUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.JSONPath;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpHost;
//import org.apache.poi.ss.formula.functions.Count;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
//import org.elasticsearch.action.bulk.BulkItemResponse;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.common.unit.Fuzziness;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.elasticsearch.index.query.*;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.aggregations.*;
//import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
//import org.elasticsearch.search.aggregations.bucket.histogram.HistogramAggregationBuilder;
//import org.elasticsearch.search.aggregations.bucket.terms.Terms;
//import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.avg.Avg;
//import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
//import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.max.Max;
//import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.min.Min;
//import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanks;
//import org.elasticsearch.search.aggregations.metrics.percentiles.PercentileRanksAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.stats.Stats;
//import org.elasticsearch.search.aggregations.metrics.stats.StatsAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.sum.Sum;
//import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCount;
//import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
//import org.elasticsearch.search.aggregations.pipeline.PipelineAggregatorBuilders;
//import org.elasticsearch.search.aggregations.pipeline.bucketsort.BucketSortPipelineAggregationBuilder;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.collapse.CollapseBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.sort.FieldSortBuilder;
//import org.elasticsearch.search.sort.SortBuilder;
//import org.elasticsearch.search.sort.SortBuilders;
//import org.elasticsearch.search.sort.SortOrder;
//import org.junit.Test;
//import org.mapstruct.Qualifier;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.domain.Sort;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.*;
//import java.util.function.BiConsumer;
//import java.util.function.Consumer;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//
///**
// * @author rbx
// * @title
// * @Create 2023-05-08 9:58
// * @Description
// */
//@Slf4j
//public class esTest {
//    public static void main(String[] args) {
//
//        System.out.println(new BigDecimal("100.789456123").subtract(new BigDecimal("80.78")).setScale(3, RoundingMode.HALF_UP));
//    }
//
//    private static final String MY_INDEX = "my_index";
//
//    @Bean
//    public RestHighLevelClient esRestClient() {
//        RestClientBuilder builder = null;
//        // 可以指定多个es
//        builder = RestClient.builder(new HttpHost("localhost", 9200, "http")
////                ,new HttpHost("host", 9200, "http")
//        );
//        RestHighLevelClient client = new RestHighLevelClient(builder);
//        return client;
//    }
//
////    RestHighLevelClient client = new RestHighLevelClient(
////            RestClient.builder(
////                    new HttpHost("localhost", 9200, "http"),
////                    new HttpHost("localhost", 9201, "http")));
//
//    @Resource
//    private RestHighLevelClient esClint;
//
//    //创建索引
//    @Test
//    public void createIndex(){
//        log.info("--------------------------------索引操作--------------------------------");
//        IndexRequest indexRequest = new IndexRequest("test", "_doc", "1");
//        try {
//            String jsonString = "{\n" +
//                    "    \"properties\": {\n" +
//                    "      \"name\": {\n" +
//                    "        \"type\": \"keyword\",\n" +
//                    "        \"index\": true,\n" +
//                    "        \"store\": true\n" +
//                    "      },\n" +
//                    "      \"age\": {\n" +
//                    "        \"type\": \"integer\",\n" +
//                    "        \"index\": true,\n" +
//                    "        \"store\": true\n" +
//                    "      },\n" +
//                    "      \"remark\": {\n" +
//                    "        \"type\": \"text\",\n" +
//                    "        \"index\": true,\n" +
//                    "        \"store\": true,\n" +
//                    "        \"analyzer\": \"ik_max_word\",\n" +
//                    "        \"search_analyzer\": \"ik_max_word\"\n" +
//                    "      }\n" +
//                    "    }\n" +
//                    "  }";
//            indexRequest.source(jsonString,XContentType.JSON);
//            esClint.index(indexRequest, RequestOptions.DEFAULT);
//            //IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //查看索引
//    @Test
//    public void getIndex(){
//        GetRequest request = new GetRequest("test","_doc","1");
//        try {
//
//            esClint.get(request,RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //删除索引
//    @Test
//    public void deleteIndex(){
//        DeleteRequest request = new DeleteRequest(
//                "test",
//                "_doc",
//                "1");
//        try {
//            DeleteResponse delete = esClint.delete(request, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    //创建文档
//    @Test
//    public void createDocument(){
//        log.info("--------------------------------文档操作--------------------------------");
//        IndexRequest request = new IndexRequest();
//        request.index("test");
//        request.id("1");
//        Student student = new Student(10,"tset","nan",180.88f);
//        request.source(JSONObject.toJSONString(student), XContentType.JSON);
//        try {
//            IndexResponse index = esClint.index(request, RequestOptions.DEFAULT);
//            System.out.println(index.getResult());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //修改文档
//    @Test
//    public void updateDocuemnt(){
//        UpdateRequest request = new UpdateRequest("test","_doc","1");
//        try {
//            Student student = new Student(10,"tset2","nan",180.88f);
//            request.doc(JSONObject.toJSONString(student), XContentType.JSON);
//            UpdateResponse response = esClint.update(request, RequestOptions.DEFAULT);
//            System.out.println(response.getResult());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //根据ID查询
//    @Test
//    public void getDocument(){
//        GetRequest request = new GetRequest("test","_doc","1");
//        try {
//            GetResponse response = esClint.get(request, RequestOptions.DEFAULT);
//            System.out.println(response.getSourceAsString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //批量操作
//    @Test
//    public void bulkDocument(){
//        BulkRequest request = new BulkRequest();
//        Student student = new Student();
//        for(int i=0;i<10;i++){
//            student.setNo(18 + i);
//            student.setName("robin" + i);
//            student.setSex("good man " + i);
//            request.add(new IndexRequest("test").id(String.valueOf(10 + i)).source(JSONObject.toJSONString(student), XContentType.JSON));
//        }
//        try {
//            BulkResponse response = esClint.bulk(request, RequestOptions.DEFAULT);
//            for(BulkItemResponse itemResponse : response.getItems()){
//                System.out.println(itemResponse.isFailed());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //删除文档
//    @Test
//    public void deleteDocument(){
//        DeleteRequest request = new DeleteRequest("test","_doc","1");
//        try {
//            DeleteResponse response = esClint.delete(request, RequestOptions.DEFAULT);
//            System.out.println(response.getResult());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * dsl查询文档:
//     * {
//     *   "query": {
//     *     "match": {
//     *       "title": "华为智能手机"
//     *     }
//     *   }
//     * }
//     * */
//    @Test
//    public void search(){
//        SearchRequest request = new SearchRequest(MY_INDEX);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        //设置查询条件
//        builder.query(QueryBuilders.matchQuery("title","华为智能手机"));
//        request.source(builder);
//        try {
//            SearchResponse response = esClint.search(request, RequestOptions.DEFAULT);
//            //循环打印查询结果
//            for(SearchHit hit : response.getHits().getHits()){
//                System.out.println(hit.getSourceAsString());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * #term精确匹配
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "term": {
//     *       "name.keyword": {
//     *         "value": "小王子"
//     *       }
//     *     }
//     *   }
//     * }
//     */
//    @Test
//    public void termTest(){
//        SearchRequest request = new SearchRequest(MY_INDEX);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        //TermQueryBuilder termedQuery = QueryBuilders.termQuery("name.keyword", "小王子");
//        String[] strings = new String[]{"小王子", "小黑子"};
//        TermsQueryBuilder termedQuery = QueryBuilders.termsQuery("name.keyword", strings);
//        builder.query(termedQuery);
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            HashMap<String, String> map = new HashMap<>();
//            search.getHits().forEach(documentFields -> {
//                String name = documentFields.getSourceAsMap().get("name").toString();
//                String price = documentFields.getSourceAsMap().get("price").toString();
//                map.put(name, price);
//            });
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * #前缀匹配
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "prefix": {
//     *       "name": {
//     *         "value": "小"
//     *       }
//     *     }
//     *   }
//     * }
//     */
//    @Test
//    public void prefixTest(){
//        SearchRequest request = new SearchRequest();
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        PrefixQueryBuilder prefixQueryBuilder = QueryBuilders.prefixQuery("name", "小");
//        builder.query(prefixQueryBuilder);
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            Arrays.stream(search.getHits().getHits()).map(doc ->{
//                log.info("name:{}",doc.getSourceAsMap().get("name"));
//                log.info("price:{}",doc.getSourceAsMap().get("price"));
//                return null;
//            });
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    /**
//     * #近似查询 fuzzy
//     * #查询不到结果可使用fuzziness提升匹配距离，但更加浪费效率
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "fuzzy": {
//     *       "name.keyword": {
//     *         "value": "小土子",
//     *         "fuzziness": 2
//     *       }
//     *     }
//     *   }
//     * }
//     */
//    @Test
//    public void fuzzyTest(){
//        SearchRequest request = new SearchRequest(MY_INDEX);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name.keyword", "小土子").fuzziness(Fuzziness.TWO);
//        builder.query(fuzzyQueryBuilder);
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            Arrays.stream(search.getHits().getHits()).map(doc -> {
//                log.info("name:{}",doc.getSourceAsMap().get("name"));
//                log.info("price:{}",doc.getSourceAsMap().get("price"));
//                return null;
//            });
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * # WildCardQuery 通配符查询
//     * #通配符 ?，它与任何单个字符匹配，通配符 *，可以匹配零个或多个字符，包括一个空字符
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "wildcard": {
//     *       "name.keyword": {
//     *         "value": "小*"
//     *       }
//     *     }
//     *   }
//     * }
//     */
//    @Test
//    public void WildCardQueryTest(){
//        SearchRequest request = new SearchRequest(MY_INDEX);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        WildcardQueryBuilder wildcardQueryBuilder = QueryBuilders.wildcardQuery("name.keyword", "小*");
//        builder.query(wildcardQueryBuilder);
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            Arrays.stream(search.getHits().getHits()).map(doc ->{
//                log.info("name:{}",doc.getSourceAsMap().get("name"));
//                log.info("price:{}",doc.getSourceAsMap().get("price"));
//                return null;
//            });
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * #range范围查询
//     * # gt->大于 gte->大于等于 lt->小于 lte->小于等于
//     *
//     * collapse 对查询结果 根据指定字段进行去重    下面的terms + top_hits也是在做去重 可以理解为去重的另一种实现
//     *
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "range": {
//     *       "price": {
//     *         "gt": 98,
//     *         "lte": 100
//     *       }
//     *     }
//     *   },
//     *   "collapse": {
//     *     "field": "name.keyword"
//     *   },
//     *   "aggs": {
//     *     "NAME": {
//     *       "terms": {
//     *         "field": "name.keyword"
//     *       },
//     *       "aggs": {
//     *         "NAME": {
//     *           "top_hits": {
//     *             "size": 1
//     *           }
//     *         }
//     *       }
//     *     }
//     *   }
//     * }
//     */
//    @Test
//    public void rangeTest(){
//        SearchRequest request = new SearchRequest(MY_INDEX);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        RangeQueryBuilder price = QueryBuilders.rangeQuery("price").gt(98).lte(100);
//        builder.query(price);
//        builder.collapse(new CollapseBuilder("name.keyword"));
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            Map<String, String> resultMap = Arrays.stream(search.getHits().getHits()).map(doc -> {
//                        HashMap<String, String> map = new HashMap<>();
//                        map.put(doc.getSourceAsMap().get("name").toString(), doc.getSourceAsMap().get("price").toString());
//                        return map;
//                    })
//                    .flatMap(map -> map.entrySet().stream())
//                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//            System.out.println("resultMap = " + resultMap);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * #sort排序
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "match_all": {}
//     *   },
//     *   "sort": [
//     *     {
//     *       "price": {
//     *         "order": "desc"
//     *       }
//     *     }
//     *   ]
//     * }
//     */
//    @Test
//    public void sortTest(){
//        SearchRequest request = new SearchRequest();
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.matchAllQuery());
//        builder.sort("price", SortOrder.DESC);
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            Map<String, String> resultMap = Arrays.stream(search.getHits().getHits()).map(doc -> {
//                        HashMap<String, String> map = new HashMap<>();
//                        map.put(doc.getSourceAsMap().get("name").toString(), doc.getSourceAsMap().get("price").toString());
//                        return map;
//                    })
//                    .flatMap(map -> map.entrySet().stream())
//                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//            System.out.println("resultMap = " + resultMap);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * #分页的两个关键属性:from、size。
//     * #- from: 当前页的起始索引，默认从0开始。 from = (pageNum - 1) * size。如每页显示5条，第6页为=(6-1)*5=25条，则显示25到5
//     * #- size: 每页显示多少条
//     * _source指定返回字段
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "match_all": {}
//     *   },
//     *   "from": 0,
//     *   "size": 20,
//     *   "_source": ["name","price"]
//     * }
//     */
//    @Test
//    public void fromOrSizeTest(){
//        final int pageNum = 10;
//        final int size = 20;
//        SearchRequest request = new SearchRequest();
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.matchAllQuery());
//        builder.from((pageNum-1)*size);
//        builder.size(size);
//        String[] strings = {"name", "price"};
//        builder.fetchSource(strings,null);
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            Map<String, String> resultMap = Arrays.stream(search.getHits().getHits()).map(documentFields -> {
//                        HashMap<String, String> map = new HashMap<>();
//                        map.put(documentFields.getSourceAsMap().get("name").toString(), documentFields.getSourceAsMap().get("price").toString());
//                        return map;
//                    })
//                    .flatMap(map -> map.entrySet().stream())
//                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//            System.out.println("resultMap = " + resultMap);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * #组合查询 bool
//     * #must 各个条件都必须满足,所有条件是and的关系
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "bool": {
//     *       "must": [
//     *         {
//     *           "term": {
//     *             "name.keyword": {
//     *               "value": "小白子"
//     *             }
//     *           }
//     *         },
//     *         {
//     *           "term": {
//     *             "price": {
//     *               "value": 98
//     *             }
//     *           }
//     *         }
//     *       ]
//     *     }
//     *   }
//     * }
//     */
//    @Test
//    public void boolTest(){
//        SearchRequest request = new SearchRequest();
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        BoolQueryBuilder booledQuery = QueryBuilders.boolQuery();
//        /*#should 各个条件满足一个即可 各条件是or的关系
//        List<QueryBuilder> should = booledQuery.should();*/
//
//        /*# must_not 不满足所有条件,即各条件是not的关系
//        List<QueryBuilder> mustNot = booledQuery.mustNot();*/
//
//        /*# filter 与must效果相同但是不计算得分,效率更高
//        List<QueryBuilder> filter = booledQuery.filter();*/
//
//        //组合条件查询 #must 各个条件都必须满足,所有条件是and的关系
//        List<QueryBuilder> must = booledQuery.must();
//        must.add(QueryBuilders.termQuery("name.keyword","小白子"));
//        must.add(QueryBuilders.termQuery("price","98"));
//        builder.query(booledQuery);
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            Map<String, String> resultMap = Arrays.stream(search.getHits().getHits()).map(documentFields -> {
//                        HashMap<String, String> map = new HashMap<>();
//                        map.put(documentFields.getSourceAsMap().get("name").toString(), documentFields.getSourceAsMap().get("price").toString());
//                        return map;
//                    })
//                    .flatMap(map -> map.entrySet().stream())
//                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//            System.out.println("resultMap = " + resultMap);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 高亮查询
//     * */
//    @Test
//    public void highlightSearch(){
//        SearchRequest request = new SearchRequest(MY_INDEX);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.matchQuery("title","华为智能手机"));
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        highlightBuilder.field("title");
//        //设置高亮查询的css样式
//        highlightBuilder.preTags("<b style='color:red'>");
//        highlightBuilder.postTags("</b>");
//        builder.highlighter(highlightBuilder);
//        request.source(builder);
//        try {
//            SearchResponse response = esClint.search(request, RequestOptions.DEFAULT);
//            for(SearchHit hit : response.getHits().getHits()){
//                System.out.println(hit.getSourceAsMap().get("title") + ":" +hit.getHighlightFields().get("title").fragments()[0].string());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * # 聚合查询max查询最大值 ,min最小值,avg平均值,sum求和 value_count计数size设置为0不显示查询数据只显示最大值
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "match_all": {}
//     *   },
//     *   "size": 0,
//     *   "aggs": {
//     *     "max_value": {
//     *       "max": {
//     *         "field": "price"
//     *       }
//     *     }
//     *   }
//     * }
//     */
//    @Test
//    public void aggTest(){
//        SearchRequest request = new SearchRequest();
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
//        //最大值
//        MaxAggregationBuilder max = AggregationBuilders.max("max").field("price");
//        //最小值
//        MinAggregationBuilder min = AggregationBuilders.min("min").field("price");
//        //平均值
//        AvgAggregationBuilder avg = AggregationBuilders.avg("avg").field("price");
//        //总和
//        SumAggregationBuilder sum = AggregationBuilders.sum("sum").field("price");
//        //计数
//        ValueCountAggregationBuilder count = AggregationBuilders.count("count").field("price");
//        //stats信息汇总 包括上面的五个
//        StatsAggregationBuilder stats = AggregationBuilders.stats("stats").field("price");
//        builder.query(matchAllQuery);
//        builder.size(10);
//        builder.aggregation(max);
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            Aggregations aggregations = search.getAggregations();
//            /**
//             * org.elasticsearch.search.aggregations.metrics.max;
//             * 取值的时候 es包下个的 max min avg sum valueCount来接收数据
//             */
//            Max maxAgg = aggregations.get("max");
//            Min minAgg = aggregations.get("min");
//            Avg avgAgg = aggregations.get("avg");
//            Sum sumAgg = aggregations.get("sum");
//            ValueCount valueCountcount = aggregations.get("count");
//            double value = maxAgg.getValue();
//            log.info("value = {}" , value);
//            /**
//             * org.elasticsearch.search.aggregations.metrics.stats;
//             * 用stats来接收汇总数据 , 然后再从stats中分别去获取数据
//             */
//            Stats statsAgg = aggregations.get("stats");
//            double avg1 = statsAgg.getAvg();
//            double avg2 = statsAgg.getMin();
//            double avg3 = statsAgg.getMax();
//            double avg4 = statsAgg.getCount();
//            double avg5 = statsAgg.getSum();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * #terms 桶聚和相当于sql中的group by语句，分组查询
//     * # bucket_sort 桶内排序
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "match_all": {}
//     *   },
//     *   "size": 0,
//     *   "aggs": {
//     *     "terms_name": {
//     *       "terms": {
//     *         "field": "name.keyword",
//     *         "size": 10
//     *       },
//     *       "aggs": {
//     *         "terms_price": {
//     *           "terms": {
//     *             "field": "price",
//     *             "size": 10
//     *           },
//     *           "aggs": {
//     *             "NAME_sort": {
//     *               "bucket_sort": {
//     *                 "sort": [{
//     *                   "_key":{
//     *                     "order":"desc"
//     *                   }
//     *                 }]
//     *               }
//     *             }
//     *           }
//     *         }
//     *       }
//     *     }
//     *   }
//     * }
//     */
//    @Test
//    public void termsAggTest(){
//        SearchRequest request = new SearchRequest();
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
//        builder.size(10);
//        TermsAggregationBuilder termsName = AggregationBuilders.terms("terms_name").field("name.keyword").size(10);
//        TermsAggregationBuilder termsPrice = AggregationBuilders.terms("terms_price").field("price").size(10).order(BucketOrder.aggregation("_key",false));
//        termsName.subAggregation(termsPrice);
//        builder.query(matchAllQuery);
//        builder.aggregation(termsName);
//        try {
//            HashMap<String, HashMap<String,Object>> map = new HashMap<>();
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            Terms nameAgg = search.getAggregations().get("terms_name");
//            for (Terms.Bucket bucket : nameAgg.getBuckets()) {
//                HashMap<String, Object> map1 = new HashMap<>();
//                long docCount = bucket.getDocCount();
//                String key = bucket.getKey().toString();
//                map1.put("docCount", String.valueOf(docCount));
//                map1.put("key", key);
//                Terms priceAgg = bucket.getAggregations().get("terms_price");
//                for (Terms.Bucket priceAggBucket : priceAgg.getBuckets()) {
//                    HashMap<String, String> map2 = new HashMap<>();
//                    long docCount2 = priceAggBucket.getDocCount();
//                    String key2 = priceAggBucket.getKey().toString();
//                    map2.put("docCount2", String.valueOf(docCount2));
//                    map2.put("key2",key2);
//                    map1.putAll(map2);
//                    map.put(key,map1);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * #对统计的结果进行去重 Cardinality
//     * #比如价格是 10 20 20 30 那去重统计的结果即为3
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "match_all": {}
//     *   },
//     *   "size": 0,
//     *   "aggs": {
//     *     "Cardinality": {
//     *       "cardinality": {
//     *         "field": "price"
//     *       }
//     *     }
//     *   }
//     * }
//     */
//    @Test
//    public void CardinalityAggTest(){
//        SearchRequest request = new SearchRequest(MY_INDEX);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
//        builder.size(0);
//        CardinalityAggregationBuilder cardAgg = AggregationBuilders.cardinality("cardinality").field("price");
//        builder.query(matchAllQuery);
//        builder.aggregation(cardAgg);
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            Cardinality cardinality = search.getAggregations().get("cardinality");
//            String valueAsString = cardinality.getValueAsString();
//            log.info("valueAsString = {}" , valueAsString);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * # 直方图聚合 histogram
//     * 统计每间隔10有多少数据 , 至少有一条数据才会显示
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "match_all": {}
//     *   },
//     *   "aggs": {
//     *     "prices": {
//     *       "histogram": {
//     *         "field": "price",
//     *         "interval": 10,
//     *         "min_doc_count": 1
//     *       }
//     *     }
//     *   }
//     * }
//     */
//    @Test
//    public void histogramTest(){
//        SearchRequest request = new SearchRequest(MY_INDEX);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
//        HistogramAggregationBuilder histogramAggregationBuilder = AggregationBuilders.histogram("histogram").field("price").interval(10).minDocCount(1);
//        builder.query(matchAllQuery);
//        builder.aggregation(histogramAggregationBuilder);
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            Histogram histogram = search.getAggregations().get("histogram");
//            histogram.getBuckets().forEach(aggregations -> {
//                log.info("aggregations.getDocCount() = {}",aggregations.getDocCount());
//                log.info("aggregations.getDocCount() = {}" , aggregations.getDocCount());
//                log.info("aggregations.getKeyAsString() = {}" , aggregations.getKeyAsString());
//            });
//            // JSONPath.eval方式取值
//            JSONObject jsonObject = JSONObject.parseObject(search.toString());
//            JSONArray gramArray = JSONUtil.parseArray(JSONPath.eval(jsonObject, "$.aggregations.prices.buckets[?(@.length !=0)]"));
//            gramArray.forEach(o -> log.info(o.toString()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * # percentile_ranks 百分比分级聚合
//     * POST /bookstore/_search
//     * {
//     *   "query": {
//     *     "match_all": {}
//     *   },
//     *   "aggs": {
//     *     "NAME": {
//     *       "percentile_ranks": {
//     *         "field": "price",
//     *         "values": [
//     *           100,
//     *           200
//     *         ]
//     *       }
//     *     }
//     *   }
//     * }
//     */
//    @Test
//    public void ranksTest(){
//        SearchRequest request = new SearchRequest(MY_INDEX);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
//        PercentileRanksAggregationBuilder price = AggregationBuilders.percentileRanks("prices", new double[]{100, 200}).field("price");
//        builder.query(matchAllQuery);
//        builder.aggregation(price);
//        request.source(builder);
//        try {
//            SearchResponse search = esClint.search(request, RequestOptions.DEFAULT);
//            PercentileRanks rankAgg = search.getAggregations().get("prices");
//            rankAgg.getMetaData().get("prices");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * 聚合查询
//     * */
//    @Test
//    public void aggsSearch(){
//        SearchRequest request = new SearchRequest(MY_INDEX);
//        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.matchAllQuery());
//        //设置查询条件和返回字段
//        //使用聚合函数terms桶聚（分组查询，根据category分组）
//        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("groupby_category").field("category");
//        //使用聚合函数，查询分组后的结果平均价格
//        aggregationBuilder.subAggregation(AggregationBuilders.avg("avg_price").field("price"));
//        builder.aggregation(aggregationBuilder);
//        request.source(builder);
//        try {
//            SearchResponse response = esClint.search(request, RequestOptions.DEFAULT);
//            Aggregations aggregations = response.getAggregations();
//            Terms terms = aggregations.get("groupby_category");
//            terms.getBuckets().forEach(bucket -> {
//                Avg avg = bucket.getAggregations().get("avg_price");
//                System.out.println(bucket.getKeyAsString() + ":" + bucket.getDocCount() + "," + avg.getValue());
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}