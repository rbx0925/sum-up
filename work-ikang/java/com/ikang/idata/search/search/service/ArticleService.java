package com.ikang.idata.search.search.service;


import org.springframework.stereotype.Service;
/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/4/28
 */
@Service
public class ArticleService {
    private static final String ARTICLE_INDEX = "article";

//    @Resource
//    private RestHighLevelClient client;
//    @Resource
//    private ArticleRepository articleRepository;
//
//    public boolean createIndexOfArticle(){
//        Settings settings = Settings.builder()
//                .put("index.number_of_shards", 1)
//                .put("index.number_of_replicas", 1)
//                .build();
//// {"properties":{"author":{"type":"text"},
//// "content":{"type":"text","analyzer":"ik_max_word","search_analyzer":"ik_smart"}
//// ,"title":{"type":"text","analyzer":"ik_max_word","search_analyzer":"ik_smart"},
//// ,"createDate":{"type":"date","format":"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd"}
//// }
//        String mapping = "{\"properties\":{\"author\":{\"type\":\"text\"},\n" +
//                "\"content\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\",\"search_analyzer\":\"ik_smart\"}\n" +
//                ",\"title\":{\"type\":\"text\",\"analyzer\":\"ik_max_word\",\"search_analyzer\":\"ik_smart\"}\n" +
//                ",\"createDate\":{\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd\"}\n" +
//                "},\"url\":{\"type\":\"text\"}\n" +
//                "}";
//        CreateIndexRequest indexRequest = new CreateIndexRequest(ARTICLE_INDEX)
//                .settings(settings).mapping(mapping,XContentType.JSON);
//        CreateIndexResponse response = null;
//        try {
//            response = client.indices().create(indexRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (response!=null) {
//            System.err.println(response.isAcknowledged() ? "success" : "default");
//            return response.isAcknowledged();
//        } else {
//            return false;
//        }
//    }
//
//    public boolean deleteArticle(){
//        DeleteIndexRequest request = new DeleteIndexRequest(ARTICLE_INDEX);
//        try {
//            AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
//            return response.isAcknowledged();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    public IndexResponse addArticle(ArticleEntity article){
//        Gson gson = new Gson();
//        String s = gson.toJson(article);
//        //创建索引创建对象
//        IndexRequest indexRequest = new IndexRequest(ARTICLE_INDEX);
//        //文档内容
//        indexRequest.source(s,XContentType.JSON);
//        //通过client进行http的请求
//        IndexResponse re = null;
//        try {
//            re = client.index(indexRequest, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return re;
//    }
//
//    public void transferFromMysql(){
//        articleRepository.findAll().forEach(this::addArticle);
//    }
//
//    public List<ArticleEntity> queryByKey(String keyword){
//        SearchRequest request = new SearchRequest();
//        /*
//         * 创建  搜索内容参数设置对象:SearchSourceBuilder
//         * 相对于matchQuery，multiMatchQuery针对的是多个fi eld，也就是说，当multiMatchQuery中，fieldNames参数只有一个时，其作用与matchQuery相当；
//         * 而当fieldNames有多个参数时，如field1和field2，那查询的结果中，要么field1中包含text，要么field2中包含text。
//         */
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        searchSourceBuilder.query(QueryBuilders
//                .multiMatchQuery(keyword, "author","content","title"));
//        request.source(searchSourceBuilder);
//        List<ArticleEntity> result = new ArrayList<>();
//        try {
//            SearchResponse search = client.search(request, RequestOptions.DEFAULT);
//            for (SearchHit hit:search.getHits()){
//                Map<String, Object> map = hit.getSourceAsMap();
//                ArticleEntity item = new ArticleEntity();
//                item.setAuthor((String) map.get("author"));
//                item.setContent((String) map.get("content"));
//                item.setTitle((String) map.get("title"));
//                item.setUrl((String) map.get("url"));
//                result.add(item);
//            }
//            return result;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public ArticleEntity queryById(String indexId){
//        GetRequest request = new GetRequest(ARTICLE_INDEX, indexId);
//        GetResponse response = null;
//        try {
//            response = client.get(request, RequestOptions.DEFAULT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (response!=null&&response.isExists()){
//            Gson gson = new Gson();
//            return gson.fromJson(response.getSourceAsString(),ArticleEntity.class);
//        }
//        return null;
//    }
}
