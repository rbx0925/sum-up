package com.ikang.idata.search.search.javaTest;

import cn.hutool.core.date.DateField;
import com.ikang.idata.common.utils.DateUtil;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.AutomatonQuery;
import org.apache.lucene.search.MultiTermQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.util.automaton.Operations;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.ParseField;
import org.elasticsearch.common.ParsingException;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.lucene.BytesRefs;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.LoggingDeprecationHandler;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.mapper.MappedFieldType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.support.QueryParsers;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.bucketsort.BucketSortPipelineAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.lucene.search.PrefixQuery.toAutomaton;

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


    @Test
    public void createIndex() throws IOException{

        //创建索引
        CreateIndexRequest request = new CreateIndexRequest("HELLO_INDEX");
        //获取请求后的响应
//        CreateIndexResponse search = client.indice().create(request, RequestOptions.DEFAULT);
//        System.out.println(search);
    }

    //#模糊查询fuzzy
    //例如：设置保证前几个是不能出错，设置最大的偏移量(也就是可以错误几个值)
    //GET /book/novel/_search
    //{
    //  "query": {
    //    "fuzzy": {
    //      "name": {
    //        "value": "我的好",
    //        "fuzziness": 2     #偏差的个数
    //      }
    //    }
    //  }
    //}
    //
    //
    //name字段中的数据和关键字**“我的好”，进行匹配；”fuzziness”：2 含义是允许匹配的数字与关键字有最多两个字的偏差**(不同)；

    //#模糊查询fuzzy
    //GET /book/novel/_search
    //{
    //  "query": {
    //    "fuzzy": {
    //      "author": {
    //        "value": "滔滔滔",
    //        "prefix_length": 1  #指定前面几个字符是不允许出现错误的
    //      }
    //    }
    //  }
    //}
    //————————————————
    //author字段中的数据和关键字“滔滔滔”，进行匹配；”prefix_length”: 1 含义是指定前面几个字符是不允许出现错误的；


    //:prefix_length 和fuzziness这两个属性是进一步筛选的，如果有些词语查不出来，原因是就算当没有这两个属性的时候也无法进行匹配出来；所以加上了这两个属性，就更不能匹配出来；这里要注意；如果有问题，就先把这两个属性先去掉试试；
    //java如何进行fuzzy查询？

    @org.junit.jupiter.api.Test
    //fuzzy模糊查询
    public void test18() throws IOException {
        //把字段名，和关键字作为参数传进去；
        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("author", "滔滔滔").prefixLength(3);
        //ClientUtils.queryTemplate(indextName,typeName,queryBuilder);
    }

    @org.junit.jupiter.api.Test
    public void prefixQuery() throws IOException {
        //1。创建request对象，查询用的对象一般都是SearchRequest对象
        SearchRequest mySearchRequest = new SearchRequest("index");

        //2，指定查询条件，依赖查询条件的对象SearchSourceBuilder的对象
        SearchSourceBuilder builder = new SearchSourceBuilder();

        // 查询套路， 只需要修改此处
        builder.from(0).size(10).query(QueryBuilders.prefixQuery("corpName", "上海"));

        mySearchRequest.source(builder);
//        //3. 执行查询
//        SearchResponse search = client.search(mySearchRequest, RequestOptions.DEFAULT);
//
//        //4. 获取到_source中的数据，并展示
//        //注意RESTFUL风格上是两个hits，所以这里要两次getHits()
//        for (SearchHit hit : search.getHits().getHits()) {
//            Map<String, Object> result = hit.getSourceAsMap();
//            System.out.println(result);
//        }

        /**
         * 模糊查询，我们输入字符的大概，ES就可以根据输入的内容去大概匹配一下结果， 同时也支持输入关键字的错别字，所以fuzzy
         * 查询本身相对不太精确和稳定，即错别字太多也可能导致查询无结果，需要则中使用。不同于 MySQL 的 Like 查询，比 Like 查询要强大。
         *
         * fuzzy 查询会用到两个很重要的参数，fuzziness，prefix_length
         *
         * fuzziness：表示输入的关键字通过几次操作可以转变成为ES库里面的对应field的字段
         *
         * 操作是指：新增一个字符，删除一个字符，修改一个字符，每次操作可以记做编辑距离为1，
         * 如中文集团到中威集团编辑距离就是1，只需要修改一个字符；
         * 该参数默认值为0，即不开启模糊查询，一样的，
         * 如果fuzziness值在这里设置成2，会把编辑距离为2的东东集团也抓出来。
         * prefix_length：表示限制输入关键字和ES对应查询field的内容开头的第n个字符必须完全匹配，不允许错别字匹配
         *
         * 如这里等于1，则表示开头的中字必须匹配，不匹配则不返回
         * 默认值也是0
         * 加大prefix_length的值可以提高效率和准确率。
         * 注意：这两个参数不是只适用于fuzzy查询，match查询一样适用。
         */
        /**
         * POST sms-logs-index/_search
         * {
         *   "query": {
         *     "fuzzy": {
         *       "corpName": {
         *         "value": "中文集团"
         *         ,"fuzziness":1
         *         ,"prefix_length": 1
         *
         *       }
         *     }
         *   }
         * }
         *
         * #fuzzy查询2 允许编辑两次距离
         * POST sms-logs-index/_search
         * {
         *   "query": {
         *     "fuzzy": {
         *       "corpName": {
         *         "value": "中文集团"
         *         ,"fuzziness":2
         *
         *       }
         *     }
         *   }
         * }
         *
         */
        // 查询套路， 只需要修改此处
        builder.from(0).size(10).query(QueryBuilders.fuzzyQuery("corpName", "中文集团").fuzziness(Fuzziness.TWO));

        /**
         * fuzzyQuery基于编辑距离(Levenshtein)来进行相似搜索,比如搜索kimzhy,可以搜索出kinzhy(编辑距离为1)
         * 为了进行测试说明,前创建一个索引,插入几条数据ka,kab,kib,ba,我们的搜索源为ki
         * 了解更多关于编辑距离(Levenshtein)的概念,请参考:<a href='http://www.cnblogs.com/biyeymyhjob/archive/2012/09/28/2707343.html'></a>
         * 了解更多编辑距离的算法,请参考:<a href='http://blog.csdn.net/ironrabbit/article/details/18736185'></a>
         *  ki — ka 编辑距离为1
         *  ki — kab 编辑距离为2
         *  ki — kbia 编辑距离为3
         *  ki — kib 编辑距离为1
         *  所以当我们设置编辑距离(ES中使用fuzziness参数来控制)为0的时候,没有结果
         *  所以当我们设置编辑距离(ES中使用fuzziness参数来控制)为1的时候,会出现结果ka,kib
         *  所以当我们设置编辑距离(ES中使用fuzziness参数来控制)为2的时候,会出现结果ka,kib,kab
         *  所以当我们设置编辑距离(ES中使用fuzziness参数来控制)为3的时候,会出现结果ka,kib,kab,kbaa(很遗憾,ES本身最多只支持到2,因此不会出现此结果)
         */
//        QueryBuilder qb = QueryBuilders.fuzzyQuery("username","ki")
////                .fuzziness(Fuzziness.ZERO);  没有结果
////                .fuzziness(Fuzziness.ONE);  会出现结果ka,kib
////                .fuzziness(Fuzziness.TWO);会出现结果ka,kib,kab
//                .fuzziness(Fuzziness.AUTO); //会出现结果ka,kib,kab
//        SearchResponse response = client.prepareSearch()
//                .setIndices("index")
//                .setTypes("type")
//                .setQuery(qb)
//                .execute()
//                .actionGet();


        /**
         * # wildcard 查询 利用*做通配符
         * POST sms-logs-index/_search
         * {
         *   "query": {
         *     "wildcard": {
         *       "corpName": {
         *         "value": "*集团"
         *       }
         *     }
         *   }
         * }
         */

        // 查询套路， 只需要修改此处
        builder.from(0).size(10).query(QueryBuilders.wildcardQuery("corpName", "*集团"));

        //正则表达式查询，通过编写的正则表达式进行内容匹配

        /**
         * # regexp查询
         * POST sms-logs-index/_search
         * {
         *   "query": {
         *     "regexp": {
         *       "moblie": "13[0-9]{9}"
         *     }
         *   }
         * }
         */

        // 查询套路， 只需要修改此处
        builder.from(0).size(10).query(QueryBuilders.regexpQuery("moblie", "13[0-9]{9}"));

        //注意：prefix，fuzzy，wildcard，regexp查询相对都是精确的条件，查询效率也是相抵较低，业务要求实时性高的场景，应该避免使用。


        class PrefixQuery extends AutomatonQuery {

            /**
             * Constructs a query for terms starting with <code>prefix</code>.
             */
            public PrefixQuery(Term prefix) {
                // It's OK to pass unlimited maxDeterminizedStates: the automaton is born small and determinized:
                super(prefix, toAutomaton(prefix.bytes()), Integer.MAX_VALUE, true);
                if (prefix == null) {
                    throw new NullPointerException("prefix must not be null");
                }
            }

            //对于我们这次特定的问题，是因为prefix Query里没有限制用户输入的长度。 看ES的源码，PrefixQuery继承自Lucene的AutomatonQuery，在实例化的时候，maxDeterminizedStates传的是Integer.MAX_VALUE, 并且生成automaton之前，prefix的长度也没有做限制。 个人认为这里可能应该限制一下大小，避免产生过多的状态:

        }
    }

    private static void createIndex1() throws IOException {
        String type = "_doc";
        String index = "test1";
        // setting 的值
        Map<String, Object> setmapping = new HashMap<>();
        // 分区数、副本数、缓存刷新时间
        setmapping.put("number_of_shards", 10);
        setmapping.put("number_of_replicas", 1);
        setmapping.put("refresh_interval", "5s");
        Map<String, Object> keyword = new HashMap<>();
        //设置类型
        keyword.put("type", "keyword");
        Map<String, Object> lon = new HashMap<>();
        //设置类型
        lon.put("type", "long");
        Map<String, Object> date = new HashMap<>();
        //设置类型
        date.put("type", "date");
        date.put("format", "yyyy-MM-dd HH:mm:ss");

        Map<String, Object> jsonMap2 = new HashMap<>();
        Map<String, Object> properties = new HashMap<>();
        //设置字段message信息
        properties.put("uid", lon);
        properties.put("phone", lon);
        properties.put("msgcode", lon);
        properties.put("message", keyword);
        properties.put("sendtime", date);
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        jsonMap2.put(type, mapping);

        GetIndexRequest getRequest = new GetIndexRequest();
        getRequest.indices(index);
        getRequest.local(false);
        getRequest.humanReadable(true);
//        boolean exists2 = client.indices().exists(getRequest, RequestOptions.DEFAULT);
//        //如果存在就不创建了
//        if(exists2) {
//            System.out.println(index+"索引库已经存在!");
//            return;
//        }
        // 开始创建库
        CreateIndexRequest request = new CreateIndexRequest(index);
       // try {
            // 加载数据类型
            request.settings(setmapping);
            //设置mapping参数
            request.mapping(type, jsonMap2);
            //设置别名
            request.alias(new Alias("pancm_alias"));
//            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
//            boolean falg = createIndexResponse.isAcknowledged();
           // if(falg){
                System.out.println("创建索引库:"+index+"成功！" );
          //  }
        //} catch (IOException e) {
           // e.printStackTrace();
       // }

    }

    private static void update() throws IOException {
        String type = "_doc";
        String index = "test1";
        // 唯一编号
        String id = "1";
        UpdateRequest upateRequest = new UpdateRequest();
        upateRequest.id(id);
        upateRequest.index(index);
        upateRequest.type(type);

        // 依旧可以使用Map这种集合作为更新条件
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("uid", 12345);
        jsonMap.put("phone", 123456789019L);
        jsonMap.put("msgcode", 2);
        jsonMap.put("sendtime", "2019-03-14 01:57:04");
        jsonMap.put("message", "xuwujing study Elasticsearch");
        upateRequest.doc(jsonMap);
        // upsert 方法表示如果数据不存在，那么就新增一条
        upateRequest.docAsUpsert(true);
//        client.update(upateRequest, RequestOptions.DEFAULT);
//        System.out.println("更新成功！");

    }

    @Test
    public void qtTest(){
//        // 创建一个查询条件对象
//        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
//        // 拼接查询条件
//        queryBuilder.should(QueryBuilders.termQuery("creator", "1"));
//
//        // 创建聚合查询条件
//        TermsAggregationBuilder sexAgg = AggregationBuilders
//                .terms("sex")
//                .field("sex.keyword");//keyword表示不使用分词进行聚合,全字匹配
//        TermsAggregationBuilder descAgg = AggregationBuilders
//                .terms("desc")
//                .field("desc.keyword");//keyword表示不使用分词进行聚合,全字匹配
//        SumAggregationBuilder ageSumAgg = AggregationBuilders
//                .sum("ageSum")
//                .field("age");
//        //嵌套
//        descAgg.subAggregation(ageSumAgg);
//        sexAgg.subAggregation(descAgg);
//
//        // 创建查询对象
//        SearchQuery build = new NativeSearchQueryBuilder()
//                .withQuery(queryBuilder) //添加查询条件
//                .addAggregation(sexAgg) // 添加聚合条件
//                .withPageable(PageRequest.of(0, 1)) //符合查询条件的文档分页，如果文档比较大，可以把这个分页改小（不是聚合的分页）
//                .build();
//        // 执行查询
//        AggregatedPage<TestEsDto> testEntities = elasticsearchTemplate.queryForPage(build, TestEsDto.class);
//
//        // 取出聚合结果
//        Aggregations entitiesAggregations = testEntities.getAggregations();
//        Terms terms = (Terms) entitiesAggregations.asMap().get("sex");
//
//        // 遍历取出聚合字段列的值，与对应的数量
//        for (Terms.Bucket bucket : terms.getBuckets()) {
//            Terms descTerms = (Terms) bucket.getAggregations().asMap().get("desc");
//            for (Terms.Bucket descTermsBucket : descTerms.getBuckets()) {
//                ParsedSum parsedSum = descTermsBucket.getAggregations().get("ageSum");//注意从bucket而不是searchResponse
//                System.out.println(bucket.getKeyAsString() + "	" +
//                        bucket.getDocCount() + "	" +
//                        descTermsBucket.getKeyAsString() + "	" +
//                        parsedSum.getValueAsString());
//            }
//        }
    }

    @Test
     public void getPrefixTest() throws IOException {
//               //        1. 创建查询对象
//               String index = "sms-logs-index";
//               String type = "sms-logs-type";
//               SearchRequest searchRequest = new SearchRequest(index);//指定索引
//               searchRequest.types(type);//指定类型
//       //    2. 封装查询条件
//               SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//                searchSourceBuilder.query(QueryBuilders.prefixQuery("corpName","途虎"));
//                searchRequest.source(searchSourceBuilder);
//
//                //        3.执行查询
//                // client执行
//                HttpHost httpHost = new HttpHost("192.168.43.30", 9200);
//                RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
//                RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
//                SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//        //        4.获取数据(source中的数据)
//                SearchHit[] hits = search.getHits().getHits();
//                for(SearchHit searchHit : hits){
//                    System.out.println(searchHit);
//                }
            }


    @Test
      public void getFuzzyTest() throws IOException {
//             //        1. 创建查询对象
//             String index = "sms-logs-index";
//             String type = "sms-logs-type";
//             SearchRequest searchRequest = new SearchRequest(index);//指定索引
//             searchRequest.types(type);//指定类型
//     //    2. 封装查询条件
//             SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//              searchSourceBuilder.query(QueryBuilders.fuzzyQuery("corpName","途虎养伡").prefixLength(2));
//              searchRequest.source(searchSourceBuilder);
//
//              //        3.执行查询
//              // client执行
//              HttpHost httpHost = new HttpHost("192.168.43.30", 9200);
//              RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
//              RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
//              SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//      //        4.获取数据(source中的数据)
//              SearchHit[] hits = search.getHits().getHits();
//              for(SearchHit searchHit : hits){
//                   System.out.println(searchHit);
//               }
          }


    @Test
    public void getWildcardTest() throws IOException {
//        //        1. 创建查询对象
//        String index = "sms-logs-index";
//        String type = "sms-logs-type";
//        SearchRequest searchRequest = new SearchRequest(index);//指定索引
//        searchRequest.types(type);//指定类型
////    2. 封装查询条件
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.wildcardQuery("corpName","途虎*"));
//        searchRequest.source(searchSourceBuilder);
//
//        //        3.执行查询
//        // client执行
//        HttpHost httpHost = new HttpHost("192.168.43.30", 9200);
//        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
//        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
//        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
////        4.获取数据(source中的数据)
//        SearchHit[] hits = search.getHits().getHits();
//        for(SearchHit searchHit : hits){
//            System.out.println(searchHit);
//        }
    }




    @Test
    public void regexTest() throws IOException {
//        //        1. 创建查询对象
//        String index = "sms-logs-index";
//        String type = "sms-logs-type";
//        SearchRequest searchRequest = new SearchRequest(index);//指定索引
//        searchRequest.types(type);//指定类型
//        //2. 封装查询条件
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.regexpQuery("mobile", "159[0-9]{7}"));
//        searchRequest.source(searchSourceBuilder);
//
//        //        3.执行查询
//        // client执行
//        HttpHost httpHost = new HttpHost("192.168.43.30", 9200);
//        RestClientBuilder restClientBuilder = RestClient.builder(httpHost);
//        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
//        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//        // 4.获取数据(source中的数据)
//        SearchHit[] hits = search.getHits().getHits();
//        for (SearchHit searchHit : hits) {
//            System.out.println(searchHit);
//        }
    }


    @Test
    public  void allSearch() throws IOException {
//        SearchRequest searchRequestAll = new SearchRequest();
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchRequestAll.source(searchSourceBuilder);
//        // 同步查询
//        SearchResponse searchResponseAll = client.search(searchRequestAll, RequestOptions.DEFAULT);
//        System.out.println("所有查询总数:" + searchResponseAll.getHits().getTotalHits());
    }


    @Test
    public  void genSearch() throws IOException {
//        String type = "_doc";
//    String index = "test1";
//    // 查询指定的索引库
//    SearchRequest searchRequest = new SearchRequest(index);
//    searchRequest.types(type);
//    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//    // 设置查询条件
//    sourceBuilder.query(QueryBuilders.termQuery("uid", "1234"));
//    // 设置起止和结束
//    sourceBuilder.from(0);
//    sourceBuilder.size(5);
//    sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//    // 设置路由
////		searchRequest.routing("routing");
//    // 设置索引库表达式
//    searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
//    // 查询选择本地分片，默认是集群分片
//    searchRequest.preference("_local");
//
//    // 排序
//    // 根据默认值进行降序排序
////	sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
//    // 根据字段进行升序排序
////	sourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));
//
//    // 关闭suorce查询
////	sourceBuilder.fetchSource(false);
//
//    String[] includeFields = new String[]{"title", "user", "innerObject.*"};
//    String[] excludeFields = new String[]{"_type"};
//    // 包含或排除字段
////	sourceBuilder.fetchSource(includeFields, excludeFields);
//
//    searchRequest.source(sourceBuilder);
//	System.out.println("普通查询的DSL语句:"+sourceBuilder.toString());
//    // 同步查询
//    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//    // HTTP状态代码、执行时间或请求是否提前终止或超时
//    RestStatus status = searchResponse.status();
//    TimeValue took = searchResponse.getTook();
//    Boolean terminatedEarly = searchResponse.isTerminatedEarly();
//    boolean timedOut = searchResponse.isTimedOut();
//
//    // 供关于受搜索影响的切分总数的统计信息，以及成功和失败的切分
//    int totalShards = searchResponse.getTotalShards();
//    int successfulShards = searchResponse.getSuccessfulShards();
//    int failedShards = searchResponse.getFailedShards();
//    // 失败的原因
//    for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
//        // failures should be handled here
//    }
//    // 结果
//    searchResponse.getHits().forEach(hit -> {
//        Map<String, Object> map = hit.getSourceAsMap();
//        System.out.println("普通查询的结果:" + map);
//    });
//    System.out.println("\n=================\n");
//}
//
    }

    @Test
    public  void orSearch() throws IOException {
//        SearchRequest searchRequest = new SearchRequest();
//    searchRequest.indices("test1");
//    searchRequest.types("_doc");
//    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//    BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//    BoolQueryBuilder boolQueryBuilder2 = new BoolQueryBuilder();
//
//      /**
//     *  SELECT * FROM test1 where (uid = 1234 or uid =12345)  and phone = 12345678909
//     * */
//    boolQueryBuilder2.should(QueryBuilders.termQuery("uid", 1234));
//    boolQueryBuilder2.should(QueryBuilders.termQuery("uid", 12345));
//    boolQueryBuilder.must(boolQueryBuilder2);
//    boolQueryBuilder.must(QueryBuilders.termQuery("phone", "12345678909"));
//    searchSourceBuilder.query(boolQueryBuilder);
//    System.out.println("或查询语句:" + searchSourceBuilder.toString());
//    searchRequest.source(searchSourceBuilder);
//    // 同步查询
//    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//    searchResponse.getHits().forEach(documentFields -> {
//
//        System.out.println("查询结果:" + documentFields.getSourceAsMap());
//    });
//
    }

    @Test
    public  void likeSearch() throws IOException {
//        String type = "_doc";
//    String index = "test1";
//    SearchRequest searchRequest = new SearchRequest();
//    searchRequest.indices(index);
//    searchRequest.types(type);
//    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//    BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
//
//   /**
//     *  SELECT * FROM p_test where  message like '%xu%';
//     * */
//    boolQueryBuilder.must(QueryBuilders.wildcardQuery("message", "*xu*"));
//    searchSourceBuilder.query(boolQueryBuilder);
//    System.out.println("模糊查询语句:" + searchSourceBuilder.toString());
//    searchRequest.source(searchSourceBuilder);
//    // 同步查询
//    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//    searchResponse.getHits().forEach(documentFields -> {
//        System.out.println("模糊查询结果:" + documentFields.getSourceAsMap());
//    });
//    System.out.println("\n=================\n");
//

    }


    @Test
    public  void inSearch() throws IOException {
//         String type = "_doc";
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
//  		System.out.println("in查询的DSL语句:"+sourceBuilder.toString());
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
//
    }


    @Test
    public  void existSearch() throws IOException {
//        String type = "_doc";
//    String index = "test1";
//    // 查询指定的索引库
//    SearchRequest searchRequest = new SearchRequest(index);
//    searchRequest.types(type);
//    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//    // 设置查询条件
//     sourceBuilder.query(QueryBuilders.existsQuery("msgcode"));
//    searchRequest.source(sourceBuilder);
//    System.out.println("存在查询的DSL语句:"+sourceBuilder.toString());
//    // 同步查询
//    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//    // 结果
//    searchResponse.getHits().forEach(hit -> {
//        Map<String, Object> map = hit.getSourceAsMap();
//        String string = hit.getSourceAsString();
//        System.out.println("存在查询的Map结果:" + map);
//        System.out.println("存在查询的String结果:" + string);
//    });
//    System.out.println("\n=================\n");

//
    }

    @Test
    public  void rangeSearch() throws IOException {
//       String type = "_doc";
//    String index = "test1";
//    SearchRequest searchRequest = new SearchRequest(index);
//    searchRequest.types(type);
//    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//
//    // 设置查询条件
//    sourceBuilder.query(QueryBuilders.rangeQuery("sendtime").gte("2019-01-01 00:00:00").lte("2019-12-31 23:59:59"));
//    searchRequest.source(sourceBuilder);
//     System.out.println("范围查询的DSL语句:"+sourceBuilder.toString());
//    // 同步查询
//    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//    // 结果
//    searchResponse.getHits().forEach(hit -> {
//        String string = hit.getSourceAsString();
//        System.out.println("范围查询的String结果:" + string);
//    });
//    System.out.println("\n=================\n");
//
    }

    @Test
    public  void regexpSearch() throws IOException {
//         String type = "_doc";
//    String index = "test1";
//    // 查询指定的索引库
//    SearchRequest searchRequest = new SearchRequest(index);
//    searchRequest.types(type);
//    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//    // 设置查询条件
//    sourceBuilder.query(QueryBuilders.regexpQuery("message","xu[0-9]"));
//    searchRequest.source(sourceBuilder);
//	 System.out.println("正则查询的DSL语句:"+sourceBuilder.toString());
//    // 同步查询
//    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//    // 结果
//    searchResponse.getHits().forEach(hit -> {
//        Map<String, Object> map = hit.getSourceAsMap();
//        String string = hit.getSourceAsString();
//        System.out.println("正则查询的Map结果:" + map);
//        System.out.println("正则查询的String结果:" + string);
//    });
//
//    System.out.println("\n=================\n");
//
    }

    public static final String NAME = "regexp";

    public static final int DEFAULT_FLAGS_VALUE = RegexpFlag.ALL.value();
    public static final int DEFAULT_MAX_DETERMINIZED_STATES = Operations.DEFAULT_MAX_DETERMINIZED_STATES;

    private static final ParseField FLAGS_VALUE_FIELD = new ParseField("flags_value");
    private static final ParseField MAX_DETERMINIZED_STATES_FIELD = new ParseField("max_determinized_states");
    private static final ParseField FLAGS_FIELD = new ParseField("flags");
    private static final ParseField REWRITE_FIELD = new ParseField("rewrite");
    private static final ParseField VALUE_FIELD = new ParseField("value");

    private final String fieldName;

    private final String value;

    private int flagsValue = DEFAULT_FLAGS_VALUE;

    private int maxDeterminizedStates = DEFAULT_MAX_DETERMINIZED_STATES;

    private String rewrite;


    /**
     * Constructs a new regex query.
     *
     * @param fieldName  The name of the field
     * @param value The regular expression
     */
    public ESTest(String fieldName, String value) {
        if (Strings.isEmpty(fieldName)) {
            throw new IllegalArgumentException("field name is null or empty");
        }
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        this.fieldName = fieldName;
        this.value = value;
    }

    /**
     * Read from a stream.
     */
    public ESTest(StreamInput in) throws IOException {
        //super(in);
        fieldName = in.readString();
        value = in.readString();
        flagsValue = in.readVInt();
        maxDeterminizedStates = in.readVInt();
        rewrite = in.readOptionalString();
    }


    protected void doWriteTo(StreamOutput out) throws IOException {
        out.writeString(fieldName);
        out.writeString(value);
        out.writeVInt(flagsValue);
        out.writeVInt(maxDeterminizedStates);
        out.writeOptionalString(rewrite);
    }

    /** Returns the field name used in this query. */

    public String fieldName() {
        return this.fieldName;
    }

    /**
     *  Returns the value used in this query.
     */
    public String value() {
        return this.value;
    }

    public ESTest flags(RegexpFlag... flags) {
        if (flags == null) {
            this.flagsValue = DEFAULT_FLAGS_VALUE;
           return this;
        }
        int value = 0;
        if (flags.length == 0) {
            //value = RegexpFlag.ALL.value;
        } else {
            for (RegexpFlag flag : flags) {
               // value |= flag.value;
            }
        }
        this.flagsValue = value;
        return this;

    }

    public ESTest flags(int flags) {
        this.flagsValue = flags;
        return this;
    }

    public int flags() {
        return this.flagsValue;
    }

    /**
     * Sets the regexp maxDeterminizedStates.
     */
    public ESTest maxDeterminizedStates(int value) {
        this.maxDeterminizedStates = value;
        return this;
    }

    public int maxDeterminizedStates() {
        return this.maxDeterminizedStates;
    }

    public ESTest rewrite(String rewrite) {
        this.rewrite = rewrite;
        return this;
    }

    public String rewrite() {
        return this.rewrite;
    }


    protected void doXContent(XContentBuilder builder, ToXContent.Params params) throws IOException {
        builder.startObject(NAME);
        builder.startObject(fieldName);
        builder.field(VALUE_FIELD.getPreferredName(), this.value);
        builder.field(FLAGS_VALUE_FIELD.getPreferredName(), flagsValue);
        builder.field(MAX_DETERMINIZED_STATES_FIELD.getPreferredName(), maxDeterminizedStates);
        if (rewrite != null) {
            builder.field(REWRITE_FIELD.getPreferredName(), rewrite);
        }
        //printBoostAndQueryName(builder);
        builder.endObject();
        builder.endObject();
    }

    public static RegexpQueryBuilder fromXContent(XContentParser parser) throws IOException {
        String fieldName = null;
        String rewrite = null;
        String value = null;
        float boost = AbstractQueryBuilder.DEFAULT_BOOST;
        int flagsValue = RegexpQueryBuilder.DEFAULT_FLAGS_VALUE;
        int maxDeterminizedStates = RegexpQueryBuilder.DEFAULT_MAX_DETERMINIZED_STATES;
        String queryName = null;
        String currentFieldName = null;
        XContentParser.Token token;
        while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
            if (token == XContentParser.Token.FIELD_NAME) {
                currentFieldName = parser.currentName();
            } else if (token == XContentParser.Token.START_OBJECT) {
                //throwParsingExceptionOnMultipleFields(NAME, parser.getTokenLocation(), fieldName, currentFieldName);
                fieldName = currentFieldName;
                while ((token = parser.nextToken()) != XContentParser.Token.END_OBJECT) {
                    if (token == XContentParser.Token.FIELD_NAME) {
                        currentFieldName = parser.currentName();
                    } else {
                        if (VALUE_FIELD.match(currentFieldName, parser.getDeprecationHandler())) {
                            value = parser.textOrNull();
                        } else if (AbstractQueryBuilder.BOOST_FIELD.match(currentFieldName, parser.getDeprecationHandler())) {
                            boost = parser.floatValue();
                        } else if (REWRITE_FIELD.match(currentFieldName, parser.getDeprecationHandler())) {
                            rewrite = parser.textOrNull();
                        } else if (FLAGS_FIELD.match(currentFieldName, parser.getDeprecationHandler())) {
                            String flags = parser.textOrNull();
                            flagsValue = RegexpFlag.resolveValue(flags);
                        } else if (MAX_DETERMINIZED_STATES_FIELD.match(currentFieldName, parser.getDeprecationHandler())) {
                            maxDeterminizedStates = parser.intValue();
                        } else if (FLAGS_VALUE_FIELD.match(currentFieldName, parser.getDeprecationHandler())) {
                            flagsValue = parser.intValue();
                        } else if (AbstractQueryBuilder.NAME_FIELD.match(currentFieldName, parser.getDeprecationHandler())) {
                            queryName = parser.text();
                        } else {
                            throw new ParsingException(parser.getTokenLocation(),
                                    "[regexp] query does not support [" + currentFieldName + "]");
                        }
                    }
                }
            } else {
                //throwParsingExceptionOnMultipleFields(NAME, parser.getTokenLocation(), fieldName, parser.currentName());
                fieldName = currentFieldName;
                value = parser.textOrNull();
            }
        }

        return new RegexpQueryBuilder(fieldName, value)
                .flags(flagsValue)
                .maxDeterminizedStates(maxDeterminizedStates)
                .rewrite(rewrite)
                .boost(boost)
                .queryName(queryName);
    }


    public String getWriteableName() {
        return NAME;
    }


    protected Query doToQuery(QueryShardContext context) throws QueryShardException, IOException {
        final int maxAllowedRegexLength = context.getIndexSettings().getMaxRegexLength();
        if (value.length() > maxAllowedRegexLength) {
            throw new IllegalArgumentException(
                    "The length of regex ["  + value.length() +  "] used in the Regexp Query request has exceeded " +
                            "the allowed maximum of [" + maxAllowedRegexLength + "]. " +
                            "This maximum can be set by changing the [" +
                            IndexSettings.MAX_REGEX_LENGTH_SETTING.getKey() + "] index level setting.");
        }
        MultiTermQuery.RewriteMethod method = QueryParsers.parseRewriteMethod(rewrite, null, LoggingDeprecationHandler.INSTANCE);

        Query query = null;
        MappedFieldType fieldType = context.fieldMapper(fieldName);
        if (fieldType != null) {
            query = fieldType.regexpQuery(value, flagsValue, maxDeterminizedStates, method, context);
        }
        if (query == null) {
            RegexpQuery regexpQuery = new RegexpQuery(new Term(fieldName, BytesRefs.toBytesRef(value)), flagsValue, maxDeterminizedStates);
            if (method != null) {
                regexpQuery.setRewriteMethod(method);
            }
            query = regexpQuery;
        }
        return query;
    }


    protected int doHashCode() {
        return Objects.hash(fieldName, value, flagsValue, maxDeterminizedStates, rewrite);
    }


    protected boolean doEquals(RegexpQueryBuilder other) {
//        return Objects.equals(fieldName, other.fieldName) &&
//                Objects.equals(value, other.value) &&
//                Objects.equals(flagsValue, other.flagsValue) &&
//                Objects.equals(maxDeterminizedStates, other.maxDeterminizedStates) &&
//                Objects.equals(rewrite, other.rewrite);
        return true;
    }

    public static void main(String[] args) {
        //rem = value % interval
        //if （rem < 0） （
        //rem += interval}
        //bucket_key = value - rem

        int value = 32;
        int interval = 5;
        int rem  = value % interval;

        System.out.println("rem  "+rem);
        if (rem< 0){
        rem += interval;
            System.out.println("rem  这个值 "+rem);
        }
        int bucket_key = value - rem;
        System.out.println("bucket_key "+ bucket_key);

        // //订阅产品数小于等于1
        //        Script script1 = new Script("doc['now_subscribe_value_added_products'].length<2");
        //        //订阅产品数2-3
        //        Script script2 = new Script("doc['now_subscribe_value_added_products'].length>1&&doc['now_subscribe_value_added_products'].length<4");
        //        //订阅产品数4-6
        //        Script script3 = new Script("doc['now_subscribe_value_added_products'].length>3&&doc['now_subscribe_value_added_products'].length<7");
        //        //订阅产品数7-10
        //        Script script4 = new Script("doc['now_subscribe_value_added_products'].length>6&&doc['now_subscribe_value_added_products'].length<11");
        //        //订阅产品数大于等于11
        //        Script script5 = new Script("doc['now_subscribe_value_added_products'].length>10");
        //
        //        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
        //                .filter(QueryBuilders.termQuery("data_type", 1))
        //                .filter(wrapperQueryBuilder);
        //        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //        searchSourceBuilder.query(queryBuilder)
        //                .aggregation(AggregationBuilders.sum("count1").script(script1)
        //                .aggregation(AggregationBuilders.sum("count2").script(script2)
        //                .aggregation(AggregationBuilders.sum("count3").script(script3)
        //                .aggregation(AggregationBuilders.sum("count4").script(script4)
        //                .aggregation(AggregationBuilders.sum("count5").script(script5);
        //        searchSourceBuilder.size(0)
        //        searchRequest.source(searchSourceBuilder);
        //
        //            SearchResponse result = restHighLevelClient.search(searchRequest);
        //            Aggregations aggregations = result.getAggregations();
        //            String count1 = String.valueOf(((ParsedSum) aggregations.get("count1")).getValue());
        //            String count2 = String.valueOf(((ParsedSum) aggregations.get("count2")).getValue());
        //            String count3 = String.valueOf(((ParsedSum) aggregations.get("count3")).getValue());
        //            String count4 = String.valueOf(((ParsedSum) aggregations.get("count4")).getValue());
        //            String count5 = String.valueOf(((ParsedSum) aggregations.get("count5")).getValue());


        //构造查询器

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

//查询条件查询所有
        QueryBuilder queryBuilders = QueryBuilders.matchAllQuery();

// 需要返回字段的集合
        String[] param = {"pid"};

// 对需要返回的数据包括哪些,不包括哪些,重复的只返回1条
        TopHitsAggregationBuilder top1 = AggregationBuilders.topHits("top").fetchSource(param, Strings.EMPTY_ARRAY).size(1);

// 通过pid聚合并且聚合后返回10条数据,注意这里的size(这里代表聚合查询出多少条数据,注意这里的size要比最下面分页的size要大,因为是对聚合后的数据分页,如果不写的话默认是10)
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("agg").field("pid").subAggregation(top1).size(10);

// 聚合分页
        termsAggregationBuilder.subAggregation(new BucketSortPipelineAggregationBuilder("bucket_field", null).from(0).size(10));

// 这里的.from(0).size(1) 表示最外层hits返回的数据

        searchSourceBuilder.query(queryBuilders).aggregation(termsAggregationBuilder).from(0).size(1);

//解析返回的数据
//        SearchResponse response = getSearchResponse(searchSourceBuilder);
//        Terms agg = response.getAggregations().get("agg");
//        for (Terms.Bucket bucket : agg.getBuckets()) {
//            TopHits top = bucket.getAggregations().get("top");
//            for (SearchHit searchHit : top.getHits()) {
//                System.out.println(searchHit.getSourceAsMap());
//            }
//        }

    }

    // 执行查询并且返回response
    private SearchResponse getSearchResponse(SearchSourceBuilder searchSourceBuilder) {
// 注入自己的es进行查询
        //SearchResponse response = esTemplate.query("distinct_test_es","dgg_doc", searchSourceBuilder);
        //return response;
        return null;
    }

    //Optional 常用示例组合

    @org.junit.jupiter.api.Test
    public void OptionalExample(){
        //创建一个测试的用户集合
        List<BeanTest.User> userList = new ArrayList<>();

        //创建几个测试用户
//        BeanTest.User user1 = new BeanTest.User("abc");
//        BeanTest.User user2 = new BeanTest.User("efg");
        BeanTest.User user3 = null;

        //将用户加入集合
//        userList.add(user1);
//        userList.add(user2);
        userList.add(user3);

        //创建用于存储姓名的集合
        List<String> nameList = new ArrayList<>();

        //循环用户列表获取用户信息,值获取不为空且用户以a开头的姓名
        //如果不符合条件就设置默认值 最后将符合条件的用户姓名加入姓名集合
        for (BeanTest.User user : userList) {
            // nameList.add(Optional.ofNullable(user).map(User::getName).filter(value -> value.startsWith("a")));

        }
        System.out.println("通过Optional过滤的集合输出");
        nameList.stream().forEach(System.out::println);
    }


    @Test
    public void beabTest(){
        String dateStart = "2019-10";
        String[] split1 = dateStart.split("-");
        String dateEnd = "2019-12";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("====：" + DateUtil.getFirstDay(dateStart));
        System.out.println("======" + DateUtil.getLastDay(dateEnd));
        System.out.println("==============================");
        int year2 = 2020;
        int month2 = 2;
//        System.out.println(year2 + "年" + month2 + "月第一天：" + format.format(DateUtil.getFirstDay(year2, month2)));
//        System.out.println(year2 + "年" + month2 + "月最后一天：" + format.format(DateUtil.getLastDay(year2, month2)));

        String lastYearDateStart = cn.hutool.core.date.DateUtil.offset(cn.hutool.core.date.DateUtil.parse("2019-10"), DateField.YEAR, -1).toString();
        System.out.println(lastYearDateStart);
    }
    //静态方法 Optional.ofNullable()
    //为指定的值创建一个 Optional 对象，如果指定的参数为 null，不抛出异常，直接则返回一个空的 Optional 对象。
    @org.junit.jupiter.api.Test
    public void ofNullAbleTest(){
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional= Optional.ofNullable("嘿呀");
        System.out.println(optional);

        //传入null值   正常返回Optional 对象
        Optional optional1= Optional.ofNullable(null);
        System.out.println(optional1);
    }

    //对象方法 isPresent()
    //如果值存在则方法会返回 true，否则返回 false。
    @org.junit.jupiter.api.Test
    public void isPresentTest(){
        //传入正常值 正常返回一个Optional对象  并使用isPresent方法
        Optional optional= Optional.ofNullable("嘿呦");
        System.out.println("传入正常值" + optional.isPresent());
        //结果  true

        //传入null值   正常返回Optional 对象
        Optional optional1= Optional.ofNullable(null);
        System.out.println("传入正常值" + optional1.isPresent());
        //结果  false
    }


    //聚合后   排序
    @Test
    public void AggTest(){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("statistics_assets")
                .field("one_account.one_account_no")
                .size(10);
        //true 为升序，false为降序
        termsAggregationBuilder.order(BucketOrder.aggregation("assets",  true ));
        AggregationBuilder assetsAggregation = AggregationBuilders.sum("assets").field("assets.merge");
        termsAggregationBuilder.subAggregation(assetsAggregation);
        searchSourceBuilder.aggregation(termsAggregationBuilder)
                .aggregation(AggregationBuilders.cardinality("count").field("one_account.one_account_no"));
}

}