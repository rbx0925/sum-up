package com.ikang.idata.search.search;

import com.alibaba.fastjson.JSON;
import jodd.util.function.Consumers;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/4/2
 */
@Slf4j
public class OptionalTest {
    /**
     * Optional 是一个容器对象，可以存储对象、字符串等值，当然也可以存储 null 值。Optional 提供很多有用的方法，能帮助我们将 Java 中的对象等一些值存入其中，这样我们就不用显式进行空值检测，使我们能够用少量的代码完成复杂的流程。
     *
     * 比如它提供了：
     *
     * of() 方法，可以将值存入 Optional 容器中，如果存入的值是 null 则抛异常。
     *
     * ofNullable() 方法，可以将值存入 Optional 容器中，即使值是 null 也不会抛异常。
     *
     * get() 方法，可以获取容器中的值，如果值为 null 则抛出异常。
     *
     * getElse() 方法，可以获取容器中的值，如果值为 null 则返回设置的默认值。
     *
     * isPresent() 方法，该方法可以判断存入的值是否为空。
     *
     * …等等一些其它常用方法，下面会进行介绍。
     *
     * 使用 Optional 可以帮助我们解决业务中，减少值动不动就抛出空指针异常问题，也减少 null 值的判断，提高代码可读性等，这里我们介绍下，如果使用这个 Optional 类。
     */

    //静态方法 Optional.of()
    //为指定的值创建一个指定非 null 值的 Optional。
    @Test
    public void ofTest(){
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional= Optional.of("加油");
        System.out.println(optional);

        //传入null值  则会报 NullPointerException
        Optional optional1= Optional.of(null);
        System.out.println(optional1);

    }


    //静态方法 Optional.ofNullable()
    //为指定的值创建一个 Optional 对象，如果指定的参数为 null，不抛出异常，直接则返回一个空的 Optional 对象。
    @Test
    public void ofNullAbleTest(){
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional= Optional.ofNullable("嘿呀");
        System.out.println(optional);

        //传入null值   正常返回Optional 对象
        Optional<Object> o = Optional.ofNullable(null);
        System.out.println(o);

    }

    //对象方法 isPresent()
    //如果值存在则方法会返回 true，否则返回 false。
    @Test
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


    //对象方法 get()
    //如果 Optional 有值则将其返回，否则抛出 NoSuchElementException 异常。
    @Test
    public void getTest(){
        //传入正常值 正常返回一个Optional对象  并使用get方法
        Optional optional= Optional.ofNullable("嘿呦");
        System.out.println("传入正常值" + optional.get());
        //结果  传入正常值嘿呦

        //传入null值   正常返回Optional 对象
        Optional optional1= Optional.ofNullable(null);
        System.out.println("传入正常值" + optional1.get());
        //结果  java.util.NoSuchElementException: No value present
    }

    //对象方法 ifPresent()
    // 如果值存在则使用该值调用 consumer , 否则不做任何事情。
    //该方法 ifPresent(Consumer<? super T> consumer) 中参数接收的是 Consumer 类，它包含一个接口方法 accept()，该方法能够对传入的值进行处理，但不会返回结果。这里传入参数可以传入 Lamdda 表达式或 Consumer 对象及实现 Consumer 接口的类的对象。
    @Test
    public void ifPresentTest(){
        //创建Optional 对象 然后调用Optional 对象的ifPresent 方法   传入Lambda 表达式
        Optional<String> optional= Optional.of("向目标前进");
        optional.ifPresent((value) -> System.out.println("optional 的值为" + value));
        //结果  向目标前进

        //创建Optional 对象 调用Optional对象的ifPresent方法 传入实现Consumer匿名
        Optional<String> optional1= Optional.of("加油呀");
        Consumer<String> consumer = new Consumers(){
           @Override
            public  void accept(Object value){
                System.out.println("Optional 的值为" + value);
            }
        };
        optional1.ifPresent(consumer);
        //结果  加油呀
    }

    //对象方法 orElse()
    //如果该值存在就直接返回， 否则返回指定的其它值。
    // orElse 方法实现很简单，就是使用三目表达式对传入的参数值进行 null 验证，即 value != null ? value : other; 如果为 null 则返回 true，否则返回 false。
    @Test
    public void orElseTest(){
        //传入正常的参数 获取一个Optional 对象  并使用orElse方法设置默认值
        Optional optional = Optional.ofNullable("三生三世");
        Object deFaultValue = optional.orElse("默认值");
        System.out.println("如果值不为空" + deFaultValue);
        //结果   三生三世


        //传入null参数   获取一个Optional对象  并使用orElse方法设置默认值
        Optional optional1 = Optional.ofNullable(null);
        Object deFaultValue1 = optional1.orElse("默认值");
        System.out.println("如果值为空" + deFaultValue1);
        //结果  默认值

    }

//    https://blog.csdn.net/weixin_36380516/article/details/113361959?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164887835416780357237329%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=164887835416780357237329&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-2-113361959.142^v5^pc_search_result_cache,157^v4^control&utm_term=Optional.ofNullable&spm=1018.2226.3001.4187

    //orElseGet()
    //orElseGet 方法和 orElse 方法类似，都是在 Optional 值为空时，返回一个默认操作，只不过 orElse 返回的是默认值，而 orElseGet 是执行 lambda 表达式，然后返回 lambda 表达式执行后的结果。
    @Test
    public  void orElseGetTest(){
        //传入正常参数   获取一个Optional 对象  并使用orElse方法 设置默认值
        Optional optional = Optional.ofNullable("新的一周");
        Object object = optional.orElseGet(() -> {
            String defaultVal = "执行逻辑和生成默认值";
            return  defaultVal;
        });
        System.out.println("输出值为" + object);


        //传入null参数   获取一个Optional对象  并使用orElse方法设置默认值
        Optional optional1 = Optional.ofNullable(null);
        Object object1 = optional1.orElseGet(() -> {
            String defaultVal = "执行逻辑和生成默认值";
            return defaultVal;
        });
        System.out.println("输出值为" + object1);

        /**
         * 打印结果
         * object  新的一周
         * object1 执行逻辑和生成默认值
         */
    }

    //orElseThrow()
    //orElseThrow 方法其实就是判断创建 Optional 时传入的参数是否为 null，如果是非 null 则返回传入的值，否则抛出 异常。
    @Test
    public void orElseThrowTest(){
        //传入正常参数 获取一个Optional对象   并使用orElseThrow方法
        Optional optional = Optional.ofNullable("加油加油");
        try {
            Object elseThrow = optional.orElseThrow(() -> {
                System.out.println("执行逻辑,然后抛出异常");
                return new RuntimeException("抛出异常");
            });
            System.out.println("输出值为 " + elseThrow);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        //传入null参数  获取一个Optional 对象  并使用orElseThrow 方法
        Optional optional1 = Optional.ofNullable(null);
        try {
            Object elseThrow1 = optional1.orElseThrow(() -> {
                System.out.println("执行逻辑,然后抛出异常");
                return new RuntimeException("抛出异常");
            });

            System.out.println("输出值为 " + elseThrow1);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        /**
         * 打印结果
         * elseThrow  加油加油
         * elseThrow1  抛出异常并报错
         */
    }

    //对象方法 map()
    // map 方法主要用于获取某个对象中的某个属性值的 Optional 对象时使用。map 方法调用时，首先验证传入的映射函数是否为空，如果为空则抛出异常。然后，再检测 Optional 的 value 是否为空，如果是，则返回一个空 value 的 Optional 对象。如果传入的映射函数和 Optinal 的 value 都不为空，则返回一个带 value 对象属性的 Optional 对象。
    @Test
    public void mapTest(){
        //创建一个map对象
        Map<String,String> userMap = new HashMap<>();
        userMap.put("name1","jiayou");
        userMap.put("name2","jiajiayou");

        //传入map对象  获取一个Optional对象 获取name1属性
        Optional<String>  optional = Optional.ofNullable(userMap).map(value -> value.get("name1"));

        //传入map 对象参数 获取一个Optional对象  获取name2属性
        Optional<String> optional1 = Optional.ofNullable(userMap).map(value -> value.get("name2"));

        System.out.println("获取的name1 的值" + optional.orElse("默认值"));
        System.out.println("获取的name2 的值" + optional1.orElse("默认值"));

        /**
         * 打印结果
         *
         * name1  jiayou
         * name2  默认值
         */
    }



    // 创建一个用户类，使用 Optional 操作用户对象，获取其 name 参数，结合 Optional 的 map 方法获取值，进行观察：
    public static class User {
        private String name ;
        public User (String name){
            this.name =name;
        }

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        //使用 Optional 的 map 方法对值处理：
        @Test
        public void orElseTest1(){
            //创建一个对象  设置名字属性而不设置性别  这时候性别为null
            User user = new User("测试名称");
            User user1 = new User(null);

            //使用Optional存储User对象
            Optional<User> optionalUser = Optional.ofNullable(user);
            Optional<User> optionalUser1 = Optional.ofNullable(user1);

            //获取对象的name属性值
            String name = optionalUser.map(User::getName).orElse("未填写");
            String name1 = optionalUser1.map(User::getName).orElse("未填写");

            //输出结果
            System.out.println("获取的名称 " + name);
            System.out.println("获取的名称 " + name1);

            /**
             * 打印结果
             * name 测试名称
             * name1 未填写
             */
            //通过上面两个示例观察到，通过 Optional 对象的 map 方法能够获取映射对象中的属，创建 Optional 对象，并以此属性充当 Optional 的值，结合 orElse 方法，如果获取的属性的值为空，则设置个默认值。
        }

        //对象方法 flatMap()
        // flatMap 方法和 map 方法类似，唯一的不同点就是 map 方法会对返回的值进行 Optional 封装，而 flatMap 不会，它需要手动执行 Optional.of 或 Optional.ofNullable 方法对 Optional 值进行封装。
        @Test
        public  void flatMapTest(){
            //创建一个map 对象
            Map<String,String> userMap = new HashMap<>();
            userMap.put("name","jiayou");
            userMap.put("sex","nan");

            //传入Map对象参数   获取一个Optional对象
            Optional<Map<String,String>> optional = Optional.of(userMap);
            //使用Optionalde flatMap方法  获取Map中的name属性值,然后通过获取的值手动创建一个新的Optional对象
            Optional optional1 = optional.flatMap( value -> Optional.ofNullable(value.get("name")));
            System.out.println("获取的Optional 的值" + optional1.get());

            /**
             * 打印结果
             * jiayou
             */

        }

        //对象方法 filter()
        // filter 方法通过传入的限定条件对 Optional 实例的值进行过滤，如果 Optional 值不为空且满足限定条件就返回包含值的 Optional，否则返回空的 Optional。这里设置的限定条件需要使用实现了 Predicate 接口的 lambda 表达式来进行配置。
        @Test
        public void filterTest(){
            //创建一个测试的Optional对象
             Optional<String> optional = Optional.ofNullable("XIANGQIANCHONGYA");

             //调用Optionalde filter 方法  设置一个满足的条件  然后观察获取Optional对象值 是否为空
            Optional optional1 = optional.filter((value) -> value.length() >2);
            System.out.println("Optional 的值不为空:" +optional1.isPresent());

            //调用Optional的filter方法,设置一个不满足的条件然后观察获取的Optional对象值是否为空
            Optional optional2 = optional.filter((value) -> value.length() < 2);
            System.out.println("Optional的值不为空 " + optional2.isPresent());

            /**
             * 打印结果
             * optional1  true
             * optional2  false
             */
        }

        //Optional 常用示例组合

        @Test
        public void OptionalExample(){
            //创建一个测试的用户集合
            List<User> userList = new ArrayList<>();

            //创建几个测试用户
            User user1 = new User("abc");
            User user2 = new User("efg");
            User user3 = null;

            //将用户加入集合
            userList.add(user1);
            userList.add(user2);
            userList.add(user3);

            //创建用于存储姓名的集合
            List<String> nameList = new ArrayList<>();

            //循环用户列表获取用户信息,值获取不为空且用户以a开头的姓名
            //如果不符合条件就设置默认值 最后将符合条件的用户姓名加入姓名集合
            for (User user : userList) {
               // nameList.add(Optional.ofNullable(user).map(User::getName).filter(value -> value.startsWith("a")));

            }
            System.out.println("通过Optional过滤的集合输出");
            nameList.stream().forEach(System.out::println);
        }

    }


    @Value("${bigdata.url.doctor.doctorReadUrl}")
    private String doctorReadUrl;
    /**
     *
     * 指定索引文档数据中按某个字段分组后对应的文档总数
     *
     */
    @Test
    public void testCountGroupBy() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("indexName").types("indexType");
        TermsAggregationBuilder aggregation = AggregationBuilders
                //别名
                .terms("uid")
                //聚合字段名
                .field("uid.keyword")
                //降序
                .order(BucketOrder.count(false))
                //聚合结果数据量，默认只返回前十条
                .size(100);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(aggregation);
        //执行查询
        searchRequest.source(searchSourceBuilder);
        List<Person> result = new ArrayList<>();
        SearchResponse response;
//        try {

//            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

//            log.info("response is {}", response);

//            Terms byAgeAggregation = response.getAggregations().get("uid");

//            for (Terms.Bucket buck : byAgeAggregation.getBuckets()) {

//                Person person = new Person();

//                //  int 类型的时候

//                //person.setCount((int) buck.getDocCount());

//                person.setName(buck.getKeyAsString());

//                result.add(person);

//            }

//        } catch (IOException e) {

//            log.error("[EsClientConfig.groupByField][error][fail to query]", e);

//        }
        log.info("result is {}", JSON.toJSONString(result));
    }


    @Test
    public void testCountDistinctGroupBy() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("indexName").types("indexType");
        //指定去重字段,cardinality指定别名,field指定字段名
        CardinalityAggregationBuilder aggregationBuilder =
                AggregationBuilders.cardinality("alias").field("field_distinct");
        //指定分组字段,terms指定别名,field指定字段名
        TermsAggregationBuilder aggregation = AggregationBuilders.terms("alias")
                //聚合字段名
                .field("field_group")
                .subAggregation(aggregationBuilder)
                .size(100)
                //按去重字段数量降序
                .order(BucketOrder.aggregation("field_distinct", false));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.aggregation(aggregation);
        //执行查询
        searchRequest.source(searchSourceBuilder);
        List<Person> result = new ArrayList<>();
        SearchResponse response;

//        try {

//            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

//            Terms byAgeAggregation = response.getAggregations().get("field_group");

//            for (Terms.Bucket buck : byAgeAggregation.getBuckets()) {

//                Aggregations aggregations1 = buck.getAggregations();

//                Aggregation subjectCount = aggregations1.get("field_distinct");

//                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(subjectCount));

//                String cardinalityValue = jsonObject.getString("value");

//                Person person = new Person();

//                person.setCount(Integer.parseInt(cardinalityValue));

//                person.setKey(buck.getKeyAsString());

//                result.add(person);

//            }
//        } catch (IOException e) {

//            log.error("[EsClientConfig.groupByField][error][fail to query]", e);

//        }

        log.info("result is {}", JSON.toJSONString(result));
    }


    @Test
    public void testAgg(){

//        //1. firstAggregations聚合
//        TermsAggregationBuilder firstAggregations= AggregationBuilders.terms("firstAggregations").field("user_id").order(BucketOrder.count(false)).size(100);
////2. secondAggregations排序
//        firstAggregations.subAggregation(AggregationBuilders.topHits("secondAggregations").size(1).sort("publish_time", SortOrder.DESC));
//
////3. 结果部分代码
//        Aggregations aggregations = searchResponse.getAggregations();
////4. firstAggregations 用terms聚合，结果使用Terms类接收
//        Terms firstAggregations = aggregations.get("firstAggregations");
//        List<? extends Terms.Bucket> bucketList = firstAggregations.getBuckets();
//
//        List<Map<String,Object>> voList = bucketList.stream()
//                .map(bucket -> {
//                    Aggregations bucketAggregations = bucket.getAggregations();
//                    //5. secondAggregations使用top_hits聚合，结果使用TopHits类接收
//                    TopHits secondAggregations = bucketAggregations.get("secondAggregations");
//                    SearchHits searchHits = secondAggregations.getHits();
//                    SearchHit[] hits = searchHits.getHits();
//                    SearchHit hit = hits[0];
//                    return hit.getSourceAsMap();
//                }).collect(Collectors.toList());
   }

    /**
     * {
     * 	"size": 0,
     * 	"aggregations": {
     * 		"firstAggregations": {
     * 			"terms": {
     * 				"field": "user_id",
     * 				"size": 50,
     * 				"min_doc_count": 1,
     * 				"shard_min_doc_count": 0,
     * 				"show_term_doc_count_error": false,
     * 				"order": [{
     * 					"_count": "desc"
     *                                }, {
     * 					"_key": "asc"
     *                }]* 			},
     * 			"aggregations": {
     * 				"secondAggregations": {
     * 					"top_hits": {
     * 						"from": 0,
     * 						"size": 1,
     * 						"version": false,
     * 						"seq_no_primary_term": false,
     * 						"explain": false,
     * 						"sort": [{
     * 							"publish_time": {
     * 								"order": "desc"
     *                            }
     *                        }]
     *                    }
     *                }
     *            }
     *        }* 	}
     * }
     * ————————————————
     * 版权声明：本文为CSDN博主「时间静适」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/weixin_43884057/article/details/117114699
     */


    AggregationBuilder timeAgg = AggregationBuilders
            .dateHistogram("timeAgg")
            .field("publish_time")
            .interval(86400000L) // 间隔时间
            //.timeZone(ZoneId.of("Asia/Shanghai"))
            .format("yyyy-MM-dd");

    /**
     * {
     *   "size": 0,
     *   "aggs": {
     *     "timeAgg": {
     *       "date_histogram": {
     *         "field": "publish_time",
     *         "interval": "day",  // 时间间隔
     *         "format": "yyyy-MM-dd", // 返回日期格式
     *         "time_zone": "Asia/Shanghai" //使用时区
     *       }
     *     }
     *   }
     * }
     * ————————————————
     * 版权声明：本文为CSDN博主「时间静适」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/weixin_43884057/article/details/117114699
     */

//    boolBuilder.must(
//            QueryBuilders.nestedQuery("user",
//            QueryBuilders.termQuery("user.name", "test"),
//    ScoreMode.None);

    /**
     * {
     * 	"query": {
     * 		"nested": {
     * 			"path": "user",
     * 			"query": {
     * 				"term": {
     * 					"user.name": "test"
     *                                }* 			}
     *        }* 	}
     * }
     */

    //boolBuilder.must(
    //        QueryBuilders.nestedQuery(
    //                "user.address",
    //                QueryBuilders.termQuery("user.address.country","China"),
    //                ScoreMode.None)

    /**
     * {
     * 	"query": {
     * 		"nested": {
     * 			//多层嵌套关键在于path
     * 			"path": "user.address",
     * 			"query": {
     * 				"term": {
     * 					"user.address.country": "China"
     *                                }* 			}
     *        }* 	}
     * }
     *
     */

    @Test
    public void test() {
        Person dtoClass = new Person("耶耶", null);
        Optional.ofNullable(dtoClass).ifPresent(dto -> {
            System.out.println("不为空才执行：" + dto.getName());
        });
    }

    @Test
    public void myList() {

        List<String> myList =
                Arrays.asList("a1", "a2", "b1", "c2", "c1");

        myList.stream()
                // 创建流
                .filter(s -> s.startsWith("c"))
                // 执行过滤，过滤出以 c 为前缀的字符串
                .map(String::toUpperCase)
                // 转换成大写
                .sorted()
                // 排序
                .forEach(System.out::println);
        // for 循环打印

        /**
         * C1
         * C2
         */

    }

    @Test
    public void ArraysStream() {

        Arrays.asList("a1", "a2", "a3")
                .stream()
                // 创建流
                .findFirst()
                // 找到第一个元素
                .ifPresent(System.out::println);
        // 如果存在，即输出


        /**
         * a1
         */
    }


    @Test
    public void Stream() {
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);

        /**
         * a1
         */

    }

    /**
     * IntStreams.range()方法还可以被用来取代常规的 for 循环
     * 原始类型流使用其独有的函数式接口，例如IntFunction代替Function，IntPredicate代替Predicate。
     */
    @Test
    public void IntStream() {
        IntStream.range(1, 4)
                .forEach(System.out::println);
        // 相当于 for (int i = 1; i < 4; i++) {}

        /**
         * 1
         * 2
         * 3
         */
    }

    /**
     * 原始类型流支持额外的终端聚合操作，sum()以及average()，如下所示：
     */
    @Test
    public void average() {
        Arrays.stream(new int[]{1, 2, 3})
                .map(n -> 2 * n + 1)
                // 对数值中的每个对象执行 2*n + 1 操作
                .average()
                // 求平均值
                .ifPresent(System.out::println);
        // 如果值不为空，则输出
        /**
         *  5.0
         */
    }

    /**
     * 需要将常规对象流转换为原始类型流，
     * 这个时候，中间操作 mapToInt()，mapToLong() 以及mapToDouble就派上用场了
     */
    @Test
    public void mapToInt() {

        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                // 对每个字符串元素从下标1位置开始截取
                .mapToInt(Integer::parseInt)
                // 转成 int 基础类型类型流
                .max()
                // 取最大值
                .ifPresent(System.out::println);
        // 不为空则输出

        /**
         * 3
         */
    }


    /**
     * 需要将原始类型流装换成对象流，您可以使用 mapToObj()来达到目的：
     */
    @Test
    public void maoToObj() {
        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                // for 循环 1->4, 拼接前缀 a
                .forEach(System.out::println);
        // for 循环打印

        /**
         * a1
         * a2
         * a3
         */

        System.out.println("-----------------------------------");

        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                // double 类型转 int
                .mapToObj(i -> "a" + i)
                // 对值拼接前缀 a
                .forEach(System.out::println);
        // for 循环打印

        /**
         * a1
         * a2
         * a3
         */
    }
    @Test
    public void stream1() {

        //观察下面这个没有终端操作的示例代码
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });
        /**
         * 实际去执行的时候，它不会打印任何内容
         *
         * 出现这样的原因是：当且仅当存在终端操作时，中间操作操作才会被执行。
         */

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        /**
         * filter:  d2
         * forEach: d2
         * filter:  a2
         * forEach: a2
         * filter:  b1
         * forEach: b1
         * filter:  b3
         * forEach: b3
         * filter:  c
         * forEach: c
         */
    }

    @Test
    public void anyMatch() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                    // 转大写
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                    // 过滤出以 A 为前缀的元素
                });

        /**
         * 终端操作 anyMatch()表示任何一个元素以 A 为前缀，返回为 true，就停止循环。
         * 所以它会从 d2 开始匹配，接着循环到 a2 的时候，返回为 true ，于是停止循环。
         *  map:      d2
         *  anyMatch: D2
         *  map:      a2
         *  anyMatch: A2
         */

    }

    @Test
    public void stream2() {

        //map和filter会对集合中的每个字符串调用五次，
        // 而forEach却只会调用一次，因为只有 “a2” 满足过滤条件，满足条件才会放行

        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                    // 转大写
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                    // 过滤出以 A 为前缀的元素
                })
                .forEach(s -> System.out.println("forEach: " + s));
        // for 循环输出

        /**
         * map:     d2
         * filter:  D2
         * map:     a2
         * filter:  A2
         * forEach: A2
         * map:     b1
         * filter:  B1
         * map:     b3
         * filter:  B3
         * map:     c
         * filter:  C
         */
    }


    @Test
    public void stream3(){

        //改变中间操作的顺序，将filter移动到链头的最开始，就可以大大减少实际的执行次数：
        //map仅仅只需调用一次，性能得到了提升，这种小技巧对于流中存在大量元素来说，是非常很有用的。
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                    // 过滤出以 a 为前缀的元素
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                    // 转大写
                })
                .forEach(s -> System.out.println("forEach: " + s));
        // for 循环输出

        /**
         *  filter:  d2
         *  filter:  a2
         *  map:     a2
         *  forEach: A2
         *  filter:  b1
         *  filter:  b3
         *  filter:  c
         */
    }

    @Test
    public void sorted(){

        //sorted 是一个有状态的操作，因为它需要在处理的过程中，保存状态以对集合中的元素进行排序。
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                    // 排序
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                    // 过滤出以 a 为前缀的元素
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                    // 转大写
                })
                .forEach(s -> System.out.println("forEach: " + s));
        // for 循环输出

        //sorted是水平执行的。因此，在这种情况下，sorted会对集合中的元素组合调用八次。
        /**
         * sort:    a2; d2
         * sort:    b1; a2
         * sort:    b1; d2
         * sort:    b1; a2
         * sort:    b3; b1
         * sort:    b3; d2
         * sort:    c; b3
         * sort:    c; d2
         * filter:  a2
         * map:     a2
         * forEach: A2
         * filter:  b1
         * filter:  b3
         * filter:  c
         * filter:  d2
         */
    }


    @Test
    public void streamSorted(){

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));


        //看到了 sorted从未被调用过，因为经过filter过后的元素已经减少到只有一个，
        // 这种情况下，是不用执行排序操作的。因此性能被大大提高了。
        /**
         *  filter:  d2
         *  filter:  a2
         *  filter:  b1
         *  filter:  b3
         *  filter:  c
         *  map:     a2
         *  forEach: A2
         */
    }

}
