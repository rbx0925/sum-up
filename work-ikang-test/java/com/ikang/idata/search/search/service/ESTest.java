package com.ikang.idata.search.search.service;

import org.junit.jupiter.api.Test;

import java.text.Collator;
import java.util.*;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/8/16
 */
public class ESTest {
    //public static final RequestOptions COMMON_OPTIONS;
    //
    //    static {
    //        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
    //        COMMON_OPTIONS = builder.build();
    //    }
    //
    //    @Bean
    //    public RestHighLevelClient esRestClient() {
    //        RestClientBuilder builder = null;
    //        // 可以指定多个es
    //        builder = RestClient.builder(new HttpHost("192.168.112.151", 9200, "http")
    ////                ,new HttpHost("host", 9200, "http")
    //        );
    //        RestHighLevelClient client = new RestHighLevelClient(builder);
    //        return client;
    //    }
    //
    //}
    //
    ////其它代码用到es注入就行了
    //    @Autowired
    //    private RestHighLevelClient esRestClient;


    //
    //
    //
    //
    //@Autowired
    //    private RestHighLevelClient esRestClient;
    //  /**
    //     *  保存数据
    //     * @throws IOException
    //     */
    //    @Test
    //    void indexData() throws IOException{
    //        //设置保存的索引和id
    //        IndexRequest indexRequest = new IndexRequest("users");
    //        indexRequest.id("1");
    //
    //        //保存的数据 这里指定json类型
    //        indexRequest.source("{\"test\":1}", XContentType.JSON);
    //
    //        //执行保存 index同步 indexAsync异步
    //        IndexResponse index = esRestClient.index(indexRequest, GuliESConfig.COMMON_OPTIONS);
    //        System.out.println(index);
    //    }
    //
    //

    //
    //
    //@Test
    //    public void find() throws IOException {
    //        // 1 创建检索请求
    //        SearchRequest searchRequest = new SearchRequest();
    //        searchRequest.indices("bank");
    //        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    //        // 构造检索条件
    ////        sourceBuilder.query();
    ////        sourceBuilder.from();
    ////        sourceBuilder.size();
    ////        sourceBuilder.aggregation();
    //        sourceBuilder.query(QueryBuilders.matchQuery("address","mill"));
    //        System.out.println(sourceBuilder.toString());
    //
    //        searchRequest.source(sourceBuilder);
    //
    //        // 2 执行检索
    //        SearchResponse response = esRestClient.search(searchRequest, GuliESConfig.COMMON_OPTIONS);
    //        // 3 分析响应结果
    //        System.out.println(response.toString());
    //    }
    //// 3.1 获取java bean   Account是bean类
    //        SearchHits hits = response.getHits();
    //        SearchHit[] hits1 = hits.getHits();
    //        for (SearchHit hit : hits1) {
    //            hit.getId();
    //            hit.getIndex();
    //            String sourceAsString = hit.getSourceAsString();
    //            Account account = JSON.parseObject(sourceAsString, Account.class);
    //            System.out.println(account);
    //        }



    //
    //
    //
    //
    // GET bank/_search
    //{
    //  "query": {
    //    "match": {
    //      "address": "Mill"
    //    }
    //  },
    //  "aggs": {
    //    "ageAgg": {
    //      "terms": {
    //        "field": "age",
    //        "size": 10
    //      }
    //    },
    //    "ageAvg": {
    //      "avg": {
    //        "field": "age"
    //      }
    //    },
    //    "balanceAvg": {
    //      "avg": {
    //        "field": "balance"
    //      }
    //    }
    //  }
    //}


    //
    //
    //
    //
    ///**
    //     * 聚合检索
    //     * @throws IOException
    //     */
    //    @Test
    //    public void jhfind() throws IOException {
    //        // 1 创建检索请求
    //        SearchRequest searchRequest = new SearchRequest();
    //        searchRequest.indices("bank");
    //        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    //        // 构造检索条件
    ////        sourceBuilder.query();
    ////        sourceBuilder.from();
    ////        sourceBuilder.size();
    ////        sourceBuilder.aggregation();
    //        sourceBuilder.query(QueryBuilders.matchQuery("address","mill"));
    //        //AggregationBuilders工具类构建AggregationBuilder
    //        // 构建第一个聚合条件:按照年龄的值分布
    //        TermsAggregationBuilder agg1 = AggregationBuilders.terms("agg1").field("age").size(10);// 聚合名称
    //// 参数为AggregationBuilder
    //        sourceBuilder.aggregation(agg1);
    //        // 构建第二个聚合条件:平均薪资
    //        AvgAggregationBuilder agg2 = AggregationBuilders.avg("agg2").field("balance");
    //        sourceBuilder.aggregation(agg2);
    //
    //        System.out.println("检索条件"+sourceBuilder.toString());
    //
    //        searchRequest.source(sourceBuilder);
    //
    //        // 2 执行检索
    //        SearchResponse response = esRestClient.search(searchRequest, GuliESConfig.COMMON_OPTIONS);
    //        // 3 分析响应结果
    //        System.out.println(response.toString());
    //    }

    //
    //
    //
    //
    //// 3.2 获取检索到的分析信息
    ////实际测试检索结果aggregations下名称不是"agg2"而是"avg#agg2"  不是"agg1"而是"lterms#agg1" 暂不知道是什么原因 先记录着
    //Aggregations aggregations = response.getAggregations();
    //Terms agg21 = aggregations.get("agg2"); //"avg#agg2"
    //for (Terms.Bucket bucket : agg21.getBuckets()) {
    //    String keyAsString = bucket.getKeyAsString();
    //    System.out.println(keyAsString);
    //}


    //
    //
    //
    //
    //新增数据代码示例一，通过jsonString进行创建：
    //    String index = "test1";
    //    String type = "_doc";
    //    // 唯一编号
    //    String id = "1";
    //    IndexRequest request = new IndexRequest(index, type, id);
    //
    //    String jsonString = "{" + "\"uid\":\"1234\","+ "\"phone\":\"12345678909\","+ "\"msgcode\":\"1\"," + "\"sendtime\":\"2019-03-14 01:57:04\","
    //            + "\"message\":\"xuwujing study Elasticsearch\"" + "}";
    //	request.source(jsonString, XContentType.JSON);
    //    IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);


    //新增数据代码示例二，通过map创建,会自动转换成json的数据:
    // String index = "test1";
    //	String type = "_doc";
    //	// 唯一编号
    //	String id = "1";
    //	IndexRequest request = new IndexRequest(index, type, id);
    //	Map<String, Object> jsonMap = new HashMap<>();
    //	jsonMap.put("uid", 1234);
    //	jsonMap.put("phone", 12345678909L);
    //	jsonMap.put("msgcode", 1);
    //	jsonMap.put("sendtime", "2019-03-14 01:57:04");
    //	jsonMap.put("message", "xuwujing study Elasticsearch");
    //	request.source(jsonMap);
    //	IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
    //
    //新增数据代码示例三，通过XContentBuilder对象进行创建:
    //String index = "test1";
    //	String type = "_doc";
    //	// 唯一编号
    //	String id = "1";
    //	IndexRequest request = new IndexRequest(index, type, id);
    //	XContentBuilder builder = XContentFactory.jsonBuilder();
    //	builder.startObject();
    //	{
    //		builder.field("uid", 1234);
    //		builder.field("phone", 12345678909L);
    //		builder.field("msgcode", 1);
    //		builder.timeField("sendtime", "2019-03-14 01:57:04");
    //		builder.field("message", "xuwujing study Elasticsearch");
    //	}
    //	builder.endObject();
    //	request.source(builder);
    //	IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
    //
    //private static void createIndex() throws IOException {
    //	String type = "_doc";
    //	String index = "test1";
    //	// setting 的值
    //	Map<String, Object> setmapping = new HashMap<>();
    //	// 分区数、副本数、缓存刷新时间
    //	setmapping.put("number_of_shards", 10);
    //	setmapping.put("number_of_replicas", 1);
    //	setmapping.put("refresh_interval", "5s");
    //	Map<String, Object> keyword = new HashMap<>();
    //	//设置类型
    //	keyword.put("type", "keyword");
    //	Map<String, Object> lon = new HashMap<>();
    //	//设置类型
    //	lon.put("type", "long");
    //	Map<String, Object> date = new HashMap<>();
    //	//设置类型
    //	date.put("type", "date");
    //	date.put("format", "yyyy-MM-dd HH:mm:ss");
    //
    //	Map<String, Object> jsonMap2 = new HashMap<>();
    //	Map<String, Object> properties = new HashMap<>();
    //	//设置字段message信息
    //	properties.put("uid", lon);
    //	properties.put("phone", lon);
    //	properties.put("msgcode", lon);
    //	properties.put("message", keyword);
    //	properties.put("sendtime", date);
    //	Map<String, Object> mapping = new HashMap<>();
    //	mapping.put("properties", properties);
    //	jsonMap2.put(type, mapping);
    //
    //	GetIndexRequest getRequest = new GetIndexRequest();
    //	getRequest.indices(index);
    //	getRequest.local(false);
    //	getRequest.humanReadable(true);
    //	boolean exists2 = client.indices().exists(getRequest, RequestOptions.DEFAULT);
    //	//如果存在就不创建了
    //	if(exists2) {
    //		System.out.println(index+"索引库已经存在!");
    //		return;
    //	}
    //	// 开始创建库
    //	CreateIndexRequest request = new CreateIndexRequest(index);
    //	try {
    //		// 加载数据类型
    //		request.settings(setmapping);
    //		//设置mapping参数
    //		request.mapping(type, jsonMap2);
    //		//设置别名
    //		request.alias(new Alias("pancm_alias"));
    //		CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
    //		boolean falg = createIndexResponse.isAcknowledged();
    //		if(falg){
    //			System.out.println("创建索引库:"+index+"成功！" );
    //		}
    //	} catch (IOException e) {
    //		e.printStackTrace();
    //	}
    //
    //}
    //


    //ES修改的代码示例:
    //private static void update() throws IOException {
    //	String type = "_doc";
    //	String index = "test1";
    //	// 唯一编号
    //	String id = "1";
    //	UpdateRequest upateRequest = new UpdateRequest();
    //	upateRequest.id(id);
    //	upateRequest.index(index);
    //	upateRequest.type(type);
    //
    //	// 依旧可以使用Map这种集合作为更新条件
    //	Map<String, Object> jsonMap = new HashMap<>();
    //	jsonMap.put("uid", 12345);
    //	jsonMap.put("phone", 123456789019L);
    //	jsonMap.put("msgcode", 2);
    //	jsonMap.put("sendtime", "2019-03-14 01:57:04");
    //	jsonMap.put("message", "xuwujing study Elasticsearch");
    //	upateRequest.doc(jsonMap);
    //	// upsert 方法表示如果数据不存在，那么就新增一条
    //	upateRequest.docAsUpsert(true);
    //	client.update(upateRequest, RequestOptions.DEFAULT);
    //	System.out.println("更新成功！");
    //
    //}
    //



    //ES根据ID删除代码示例:
    //private static void delete() throws IOException {
    //
    //	String type = "_doc";
    //	String index = "test1";
    //	// 唯一编号
    //	String id = "1";
    //	DeleteRequest deleteRequest = new DeleteRequest();
    //	deleteRequest.id(id);
    //	deleteRequest.index(index);
    //	deleteRequest.type(type);
    //	// 设置超时时间
    //	deleteRequest.timeout(TimeValue.timeValueMinutes(2));
    //	// 设置刷新策略"wait_for"
    //	// 保持此请求打开，直到刷新使此请求的内容可以搜索为止。此刷新策略与高索引和搜索吞吐量兼容，但它会导致请求等待响应，直到发生刷新
    //	deleteRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
    //	// 同步删除
    //	DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
    //}
    //



    //
    //
    //
    //
    //ES根据条件进行删除：
    // private static void deleteByQuery() throws IOException {
    //	String type = "_doc";
    //	String index = "test1";
    //	DeleteByQueryRequest request = new DeleteByQueryRequest(index,type);
    //	// 设置查询条件
    //	request.setQuery(QueryBuilders.termsQuery("uid",1234));
    //	// 同步执行
    //	BulkByScrollResponse bulkResponse = client.deleteByQuery(request, RequestOptions.DEFAULT);
    //}
    //
    //
    //
    //


//等值（term查询：QueryBuilders.termQuery(name,value);
//多值(terms)查询:QueryBuilders.termsQuery(name,value,value2,value3...);
//范围（range)查询：QueryBuilders.rangeQuery(name).gte(value).lte(value);
//存在(exists)查询:QueryBuilders.existsQuery(name);
//模糊(wildcard)查询:QueryBuilders.wildcardQuery(name,+value+);
//组合（bool）查询: BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//
//
//
//
//

//查询所有代码示例
//
//    private static void allSearch() throws IOException {
//
//        SearchRequest searchRequestAll = new SearchRequest();
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchRequestAll.source(searchSourceBuilder);
//        // 同步查询
//        SearchResponse searchResponseAll = client.search(searchRequestAll, RequestOptions.DEFAULT);
//        System.out.println("所有查询总数:" + searchResponseAll.getHits().getTotalHits());
//    }
//
//
//普通查询的DSL语句:{"from":0,"size":5,"timeout":"60s","query":{"term":{"uid":{"value":"1234","boost":1.0}}}}


//    //一般查询代码示例
//    private static void genSearch() throws IOException {
//        String type = "_doc";
//        String index = "test1";
//        // 查询指定的索引库
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        // 设置查询条件
//        sourceBuilder.query(QueryBuilders.termQuery("uid", "1234"));
//        // 设置起止和结束
//        sourceBuilder.from(0);
//        sourceBuilder.size(5);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        // 设置路由
////		searchRequest.routing("routing");
//        // 设置索引库表达式
//        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
//        // 查询选择本地分片，默认是集群分片
//        searchRequest.preference("_local");
//
//        // 排序
//        // 根据默认值进行降序排序
////	sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
//        // 根据字段进行升序排序
////	sourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));
//
//        // 关闭suorce查询
////	sourceBuilder.fetchSource(false);
//
//        String[] includeFields = new String[]{"title", "user", "innerObject.*"};
//        String[] excludeFields = new String[]{"_type"};
//        // 包含或排除字段
////	sourceBuilder.fetchSource(includeFields, excludeFields);
//
//        searchRequest.source(sourceBuilder);
//        System.out.println("普通查询的DSL语句:"+sourceBuilder.toString());
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//        // HTTP状态代码、执行时间或请求是否提前终止或超时
//        RestStatus status = searchResponse.status();
//        TimeValue took = searchResponse.getTook();
//        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
//        boolean timedOut = searchResponse.isTimedOut();
//
//        // 供关于受搜索影响的切分总数的统计信息，以及成功和失败的切分
//        int totalShards = searchResponse.getTotalShards();
//        int successfulShards = searchResponse.getSuccessfulShards();
//        int failedShards = searchResponse.getFailedShards();
//        // 失败的原因
//        for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
//            // failures should be handled here
//        }
//        // 结果
//        searchResponse.getHits().forEach(hit -> {
//            Map<String, Object> map = hit.getSourceAsMap();
//            System.out.println("普通查询的结果:" + map);
//        });
//        System.out.println("\n=================\n");
//    }



//    //或查询
//    private static void orSearch() throws IOException {
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("test1");
//        searchRequest.types("_doc");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//        BoolQueryBuilder boolQueryBuilder2 = new BoolQueryBuilder();
//
//        /**
//         *  SELECT * FROM test1 where (uid = 1234 or uid =12345)  and phone = 12345678909
//         * */
//        boolQueryBuilder2.should(QueryBuilders.termQuery("uid", 1234));
//        boolQueryBuilder2.should(QueryBuilders.termQuery("uid", 12345));
//        boolQueryBuilder.must(boolQueryBuilder2);
//        boolQueryBuilder.must(QueryBuilders.termQuery("phone", "12345678909"));
//        searchSourceBuilder.query(boolQueryBuilder);
//        System.out.println("或查询语句:" + searchSourceBuilder.toString());
//        searchRequest.source(searchSourceBuilder);
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//        searchResponse.getHits().forEach(documentFields -> {
//
//            System.out.println("查询结果:" + documentFields.getSourceAsMap());
//        });
//
//    }

    //
    //
    //
    //或查询语句:{"query":{"bool":{"must":[{"bool":{"should":[{"term":{"uid":{"value":1234,"boost":1.0}}},{"term":{"uid":{"value":12345,"boost":1.0}}}],"adjust_pure_negative":true,"boost":1.0}},{"term":{"phone":{"value":"12345678909","boost":1.0}}}],"adjust_pure_negative":true,"boost":1.0}}}
    //或查询结果:{msgcode=1, uid=12345, phone=12345678909, message=qq, sendtime=2019-03-14 01:57:04}





//    //模糊查询
//    private static void likeSearch() throws IOException {
//        String type = "_doc";
//        String index = "test1";
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices(index);
//        searchRequest.types(type);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//
//        /**
//         *  SELECT * FROM p_test where  message like '%xu%';
//         * */
//        boolQueryBuilder.must(QueryBuilders.wildcardQuery("message", "*xu*"));
//        searchSourceBuilder.query(boolQueryBuilder);
//        System.out.println("模糊查询语句:" + searchSourceBuilder.toString());
//        searchRequest.source(searchSourceBuilder);
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        searchResponse.getHits().forEach(documentFields -> {
//            System.out.println("模糊查询结果:" + documentFields.getSourceAsMap());
//        });
//        System.out.println("\n=================\n");
//    }
//
//

    //
    //
    //模糊查询语句:{"query":{"bool":{"must":[{"wildcard":{"message":{"wildcard":"xu","boost":1.0}}}],"adjust_pure_negative":true,"boost":1.0}}}
    //模糊查询结果:{msgcode=2, uid=12345, phone=123456789019, sendtime=2019-03-14 01:57:04, message=xuwujing study Elasticsearch}
    //模糊查询结果:{uid=123456, phone=12345678909, message=xu1, sendtime=2019-03-14 01:57:04}
    //
    //




//
//    //多值查询
//    private static void inSearch() throws IOException {
//        String type = "_doc";
//        String index = "test1";
//        // 查询指定的索引库
//        SearchRequest searchRequest = new SearchRequest(index,type);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        /**
//         *  SELECT * FROM p_test where uid in (1,2)
//         * */
//        // 设置查询条件
//        sourceBuilder.query(QueryBuilders.termsQuery("uid", 1, 2));
//        searchRequest.source(sourceBuilder);
//        System.out.println("in查询的DSL语句:"+sourceBuilder.toString());
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        // 结果
//        searchResponse.getHits().forEach(hit -> {
//            Map<String, Object> map = hit.getSourceAsMap();
//            String string = hit.getSourceAsString();
//            System.out.println("in查询的Map结果:" + map);
//            System.out.println("in查询的String结果:" + string);
//        });
//
//        System.out.println("\n=================\n");
//    }
//
//

//    //存在查询
//    private static void existSearch() throws IOException {
//        String type = "_doc";
//        String index = "test1";
//        // 查询指定的索引库
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        // 设置查询条件
//        sourceBuilder.query(QueryBuilders.existsQuery("msgcode"));
//        searchRequest.source(sourceBuilder);
//        System.out.println("存在查询的DSL语句:"+sourceBuilder.toString());
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        // 结果
//        searchResponse.getHits().forEach(hit -> {
//            Map<String, Object> map = hit.getSourceAsMap();
//            String string = hit.getSourceAsString();
//            System.out.println("存在查询的Map结果:" + map);
//            System.out.println("存在查询的String结果:" + string);
//        });
//        System.out.println("\n=================\n");
//    }


    /**
     * 存在查询的DSL语句:{"query":{"exists":{"field":"msgcode","boost":1.0}}}
     * 存在查询的Map结果:{msgcode=2, uid=12345, phone=123456789019, sendtime=2019-03-14 01:57:04, message=xuwujing study Elasticsearch}
     * 存在查询的String结果:{"uid":12345,"phone":123456789019,"msgcode":2,"sendtime":"2019-03-14 01:57:04","message":"xuwujing study Elasticsearch"}
     * 存在查询的Map结果:{msgcode=1, uid=12345, phone=12345678909, message=qq, sendtime=2019-03-14 01:57:04}
     * 存在查询的String结果:{"uid":"12345","phone":"12345678909","message":"qq","msgcode":"1","sendtime":"2019-03-14 01:57:04"}
     *
     *
     *
     */




//    //范围查询
//    private static void rangeSearch() throws IOException{
//        String type = "_doc";
//        String index = "test1";
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        // 设置查询条件
//        sourceBuilder.query(QueryBuilders.rangeQuery("sendtime").gte("2019-01-01 00:00:00").lte("2019-12-31 23:59:59"));
//        searchRequest.source(sourceBuilder);
//        System.out.println("范围查询的DSL语句:"+sourceBuilder.toString());
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        // 结果
//        searchResponse.getHits().forEach(hit -> {
//            String string = hit.getSourceAsString();
//            System.out.println("范围查询的String结果:" + string);
//        });
//        System.out.println("\n=================\n");
//    }

    /**
     * 范围查询的DSL语句:{"query":{"range":{"sendtime":{"from":"2019-01-01 00:00:00","to":"2019-12-31 23:59:59","include_lower":true,"include_upper":true,"boost":1.0}}}}
     * 范围查询的String结果:{"uid":12345,"phone":123456789019,"msgcode":2,"sendtime":"2019-03-14 01:57:04","message":"xuwujing study Elasticsearch"}
     * 范围查询的String结果:{"uid":"123456","phone":"12345678909","message":"xu1","sendtime":"2019-03-14 01:57:04"}
     * 范围查询的String结果:{"uid":"12345","phone":"12345678909","message":"qq","msgcode":"1","sendtime":"2019-03-14 01:57:04"}
     *
     *
     *
     *
     *
     *
     */




//    //正则查询
//    private static void regexpSearch() throws IOException{
//        String type = "_doc";
//        String index = "test1";
//        // 查询指定的索引库
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        // 设置查询条件
//        sourceBuilder.query(QueryBuilders.regexpQuery("message","xu[0-9]"));
//        searchRequest.source(sourceBuilder);
//        System.out.println("正则查询的DSL语句:"+sourceBuilder.toString());
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        // 结果
//        searchResponse.getHits().forEach(hit -> {
//            Map<String, Object> map = hit.getSourceAsMap();
//            String string = hit.getSourceAsString();
//            System.out.println("正则查询的Map结果:" + map);
//            System.out.println("正则查询的String结果:" + string);
//        });
//
//        System.out.println("\n=================\n");
//    }

    /**
     * 正则查询的DSL语句:{"query":{"regexp":{"message":{"value":"xu[0-9]","flags_value":65535,"max_determinized_states":10000,"boost":1.0}}}}
     * 正则查询的Map结果:{uid=123456, phone=12345678909, message=xu1, sendtime=2019-03-14 01:57:04}
     * 正则查询的String结果:{"uid":"123456","phone":"12345678909","message":"xu1","sendtime":"2019-03-14 01:57:04"}
     *
     *
     */



//    @Test
//    public void testAdd() throws IOException {
//        /**
//         * 向ES中的索引christy下的type类型中添加一天文档
//         */
//        IndexRequest indexRequest = new IndexRequest("christy","user","11");
//        indexRequest.source("{\"name\":\"齐天大圣孙悟空\",\"age\":685,\"bir\":\"1685-01-01\",\"introduce\":\"花果山水帘洞美猴王齐天大圣孙悟空是也！\"," +
//                "\"address\":\"花果山\"}", XContentType.JSON);
//        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//        System.out.println(indexResponse.status());
//    }
    //等值（term查询：QueryBuilders.termQuery(name,value);
//多值(terms)查询:QueryBuilders.termsQuery(name,value,value2,value3...);
//范围（range)查询：QueryBuilders.rangeQuery(name).gte(value).lte(value);
//存在(exists)查询:QueryBuilders.existsQuery(name);
//模糊(wildcard)查询:QueryBuilders.wildcardQuery(name,+value+);
//组合（bool）查询: BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//
//
//
//
//

//查询所有代码示例
//
//    private static void allSearch() throws IOException {
//
//        SearchRequest searchRequestAll = new SearchRequest();
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchRequestAll.source(searchSourceBuilder);
//        // 同步查询
//        SearchResponse searchResponseAll = client.search(searchRequestAll, RequestOptions.DEFAULT);
//        System.out.println("所有查询总数:" + searchResponseAll.getHits().getTotalHits());
//    }
//
//
//普通查询的DSL语句:{"from":0,"size":5,"timeout":"60s","query":{"term":{"uid":{"value":"1234","boost":1.0}}}}


//    //一般查询代码示例
//    private static void genSearch() throws IOException {
//        String type = "_doc";
//        String index = "test1";
//        // 查询指定的索引库
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        // 设置查询条件
//        sourceBuilder.query(QueryBuilders.termQuery("uid", "1234"));
//        // 设置起止和结束
//        sourceBuilder.from(0);
//        sourceBuilder.size(5);
//        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        // 设置路由
////		searchRequest.routing("routing");
//        // 设置索引库表达式
//        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
//        // 查询选择本地分片，默认是集群分片
//        searchRequest.preference("_local");
//
//        // 排序
//        // 根据默认值进行降序排序
////	sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
//        // 根据字段进行升序排序
////	sourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));
//
//        // 关闭suorce查询
////	sourceBuilder.fetchSource(false);
//
//        String[] includeFields = new String[]{"title", "user", "innerObject.*"};
//        String[] excludeFields = new String[]{"_type"};
//        // 包含或排除字段
////	sourceBuilder.fetchSource(includeFields, excludeFields);
//
//        searchRequest.source(sourceBuilder);
//        System.out.println("普通查询的DSL语句:"+sourceBuilder.toString());
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//        // HTTP状态代码、执行时间或请求是否提前终止或超时
//        RestStatus status = searchResponse.status();
//        TimeValue took = searchResponse.getTook();
//        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
//        boolean timedOut = searchResponse.isTimedOut();
//
//        // 供关于受搜索影响的切分总数的统计信息，以及成功和失败的切分
//        int totalShards = searchResponse.getTotalShards();
//        int successfulShards = searchResponse.getSuccessfulShards();
//        int failedShards = searchResponse.getFailedShards();
//        // 失败的原因
//        for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
//            // failures should be handled here
//        }
//        // 结果
//        searchResponse.getHits().forEach(hit -> {
//            Map<String, Object> map = hit.getSourceAsMap();
//            System.out.println("普通查询的结果:" + map);
//        });
//        System.out.println("\n=================\n");
//    }



//    //或查询
//    private static void orSearch() throws IOException {
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices("test1");
//        searchRequest.types("_doc");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//        BoolQueryBuilder boolQueryBuilder2 = new BoolQueryBuilder();
//
//        /**
//         *  SELECT * FROM test1 where (uid = 1234 or uid =12345)  and phone = 12345678909
//         * */
//        boolQueryBuilder2.should(QueryBuilders.termQuery("uid", 1234));
//        boolQueryBuilder2.should(QueryBuilders.termQuery("uid", 12345));
//        boolQueryBuilder.must(boolQueryBuilder2);
//        boolQueryBuilder.must(QueryBuilders.termQuery("phone", "12345678909"));
//        searchSourceBuilder.query(boolQueryBuilder);
//        System.out.println("或查询语句:" + searchSourceBuilder.toString());
//        searchRequest.source(searchSourceBuilder);
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//        searchResponse.getHits().forEach(documentFields -> {
//
//            System.out.println("查询结果:" + documentFields.getSourceAsMap());
//        });
//
//    }

    //
    //
    //
    //或查询语句:{"query":{"bool":{"must":[{"bool":{"should":[{"term":{"uid":{"value":1234,"boost":1.0}}},{"term":{"uid":{"value":12345,"boost":1.0}}}],"adjust_pure_negative":true,"boost":1.0}},{"term":{"phone":{"value":"12345678909","boost":1.0}}}],"adjust_pure_negative":true,"boost":1.0}}}
    //或查询结果:{msgcode=1, uid=12345, phone=12345678909, message=qq, sendtime=2019-03-14 01:57:04}





//    //模糊查询
//    private static void likeSearch() throws IOException {
//        String type = "_doc";
//        String index = "test1";
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices(index);
//        searchRequest.types(type);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//
//        /**
//         *  SELECT * FROM p_test where  message like '%xu%';
//         * */
//        boolQueryBuilder.must(QueryBuilders.wildcardQuery("message", "*xu*"));
//        searchSourceBuilder.query(boolQueryBuilder);
//        System.out.println("模糊查询语句:" + searchSourceBuilder.toString());
//        searchRequest.source(searchSourceBuilder);
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        searchResponse.getHits().forEach(documentFields -> {
//            System.out.println("模糊查询结果:" + documentFields.getSourceAsMap());
//        });
//        System.out.println("\n=================\n");
//    }
//
//

    //
    //
    //模糊查询语句:{"query":{"bool":{"must":[{"wildcard":{"message":{"wildcard":"xu","boost":1.0}}}],"adjust_pure_negative":true,"boost":1.0}}}
    //模糊查询结果:{msgcode=2, uid=12345, phone=123456789019, sendtime=2019-03-14 01:57:04, message=xuwujing study Elasticsearch}
    //模糊查询结果:{uid=123456, phone=12345678909, message=xu1, sendtime=2019-03-14 01:57:04}
    //
    //




//
//    //多值查询
//    private static void inSearch() throws IOException {
//        String type = "_doc";
//        String index = "test1";
//        // 查询指定的索引库
//        SearchRequest searchRequest = new SearchRequest(index,type);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        /**
//         *  SELECT * FROM p_test where uid in (1,2)
//         * */
//        // 设置查询条件
//        sourceBuilder.query(QueryBuilders.termsQuery("uid", 1, 2));
//        searchRequest.source(sourceBuilder);
//        System.out.println("in查询的DSL语句:"+sourceBuilder.toString());
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        // 结果
//        searchResponse.getHits().forEach(hit -> {
//            Map<String, Object> map = hit.getSourceAsMap();
//            String string = hit.getSourceAsString();
//            System.out.println("in查询的Map结果:" + map);
//            System.out.println("in查询的String结果:" + string);
//        });
//
//        System.out.println("\n=================\n");
//    }
//
//

//    //存在查询
//    private static void existSearch() throws IOException {
//        String type = "_doc";
//        String index = "test1";
//        // 查询指定的索引库
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        // 设置查询条件
//        sourceBuilder.query(QueryBuilders.existsQuery("msgcode"));
//        searchRequest.source(sourceBuilder);
//        System.out.println("存在查询的DSL语句:"+sourceBuilder.toString());
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        // 结果
//        searchResponse.getHits().forEach(hit -> {
//            Map<String, Object> map = hit.getSourceAsMap();
//            String string = hit.getSourceAsString();
//            System.out.println("存在查询的Map结果:" + map);
//            System.out.println("存在查询的String结果:" + string);
//        });
//        System.out.println("\n=================\n");
//    }


    /**
     * 存在查询的DSL语句:{"query":{"exists":{"field":"msgcode","boost":1.0}}}
     * 存在查询的Map结果:{msgcode=2, uid=12345, phone=123456789019, sendtime=2019-03-14 01:57:04, message=xuwujing study Elasticsearch}
     * 存在查询的String结果:{"uid":12345,"phone":123456789019,"msgcode":2,"sendtime":"2019-03-14 01:57:04","message":"xuwujing study Elasticsearch"}
     * 存在查询的Map结果:{msgcode=1, uid=12345, phone=12345678909, message=qq, sendtime=2019-03-14 01:57:04}
     * 存在查询的String结果:{"uid":"12345","phone":"12345678909","message":"qq","msgcode":"1","sendtime":"2019-03-14 01:57:04"}
     *
     *
     *
     */




//    //范围查询
//    private static void rangeSearch() throws IOException{
//        String type = "_doc";
//        String index = "test1";
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//        // 设置查询条件
//        sourceBuilder.query(QueryBuilders.rangeQuery("sendtime").gte("2019-01-01 00:00:00").lte("2019-12-31 23:59:59"));
//        searchRequest.source(sourceBuilder);
//        System.out.println("范围查询的DSL语句:"+sourceBuilder.toString());
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        // 结果
//        searchResponse.getHits().forEach(hit -> {
//            String string = hit.getSourceAsString();
//            System.out.println("范围查询的String结果:" + string);
//        });
//        System.out.println("\n=================\n");
//    }

    /**
     * 范围查询的DSL语句:{"query":{"range":{"sendtime":{"from":"2019-01-01 00:00:00","to":"2019-12-31 23:59:59","include_lower":true,"include_upper":true,"boost":1.0}}}}
     * 范围查询的String结果:{"uid":12345,"phone":123456789019,"msgcode":2,"sendtime":"2019-03-14 01:57:04","message":"xuwujing study Elasticsearch"}
     * 范围查询的String结果:{"uid":"123456","phone":"12345678909","message":"xu1","sendtime":"2019-03-14 01:57:04"}
     * 范围查询的String结果:{"uid":"12345","phone":"12345678909","message":"qq","msgcode":"1","sendtime":"2019-03-14 01:57:04"}
     *
     *
     *
     *
     *
     *
     */




//    //正则查询
//    private static void regexpSearch() throws IOException{
//        String type = "_doc";
//        String index = "test1";
//        // 查询指定的索引库
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        // 设置查询条件
//        sourceBuilder.query(QueryBuilders.regexpQuery("message","xu[0-9]"));
//        searchRequest.source(sourceBuilder);
//        System.out.println("正则查询的DSL语句:"+sourceBuilder.toString());
//        // 同步查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        // 结果
//        searchResponse.getHits().forEach(hit -> {
//            Map<String, Object> map = hit.getSourceAsMap();
//            String string = hit.getSourceAsString();
//            System.out.println("正则查询的Map结果:" + map);
//            System.out.println("正则查询的String结果:" + string);
//        });
//
//        System.out.println("\n=================\n");
//    }

    /**
     * 正则查询的DSL语句:{"query":{"regexp":{"message":{"value":"xu[0-9]","flags_value":65535,"max_determinized_states":10000,"boost":1.0}}}}
     * 正则查询的Map结果:{uid=123456, phone=12345678909, message=xu1, sendtime=2019-03-14 01:57:04}
     * 正则查询的String结果:{"uid":"123456","phone":"12345678909","message":"xu1","sendtime":"2019-03-14 01:57:04"}
     *
     *
     */



//    @Test
//    public void testAdd() throws IOException {
//        /**
//         * 向ES中的索引christy下的type类型中添加一天文档
//         */
//        IndexRequest indexRequest = new IndexRequest("christy","user","11");
//        indexRequest.source("{\"name\":\"齐天大圣孙悟空\",\"age\":685,\"bir\":\"1685-01-01\",\"introduce\":\"花果山水帘洞美猴王齐天大圣孙悟空是也！\"," +
//                "\"address\":\"花果山\"}", XContentType.JSON);
//        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
//        System.out.println(indexResponse.status());
//    }
    public static void main(String[] args) {
        List sourceList = new ArrayList();

        sourceList.add("中");

        sourceList.add("华");

        sourceList.add("人");

        sourceList.add("民");

        sourceList.add("共");

        sourceList.add("和");

        sourceList.add("国");

        sourceList.add("愚");

// 1.打印数组

        System.out.println("第一次打印-------------------");

        for (int i = 0; i < sourceList.size(); i++) {

            System.out.println(sourceList.get(i));

        }
        // 2.Arrays排序,打印数组

        System.out.println("第二次打印-------------------");

        Object[] sStrings = sourceList.toArray();

        Arrays.sort(sStrings);

        for (int i = 0; i < sStrings.length; i++) {

            System.out.println(sStrings[i]);

        }

        // 3.collections排序

        sourceList.clear();

        sourceList.add("一线区域");

        sourceList.add("华");

        sourceList.add("三线区域");

        sourceList.add("民");

        sourceList.add("共");

        sourceList.add("和");

        sourceList.add("国");

        sourceList.add("二线区域");

        Collections.sort(sourceList, new Comparator() {

            public int compare(Object _o1, Object _o2) {

                return chineseCompare(_o1, _o2);

            }

        });

        System.out.println("第三次打印-------------------");

        for (int i = 0; i < sourceList.size(); i++) {

            System.out.println(sourceList.get(i));

        }

    }


    private static int chineseCompare(Object _oChinese1, Object _oChinese2) {

        return

                Collator.getInstance(Locale.SIMPLIFIED_CHINESE)
                        .compare(_oChinese1, _oChinese2);

    }

    @Test
    public void teat() {
        ArrayList<String> list = new ArrayList<>();
        list.add(("一线区域"));
        list.add(("三线区域"));
        list.add(("二线区域"));
        System.out.println(list);
        HashMap<String, Integer> Sites = new HashMap<String, Integer>();
        // 添加键值对
        Sites.put("一", 1);
        Sites.put("二", 2);
        Sites.put("三", 3);
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            System.out.println();
            arr[Sites.get(list.get(i).substring(0, 1)) - 1] = list.get(i);
        }
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
