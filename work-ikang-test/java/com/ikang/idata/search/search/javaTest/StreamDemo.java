package com.ikang.idata.search.search.javaTest;

import com.ikang.idata.search.search.OptionalTest;
import com.ikang.idata.search.search.Person;
import jodd.util.function.Consumers;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/11/16
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();
        Set<String> set = new HashSet<>();
        Stream<String> stream2 = set.stream();
        Vector<String> vector = new Vector<>();
    }


    @Test
    public void test1() {
        Map<String, String> map = new HashMap<>();
        Stream<String> keyStream = map.keySet().stream();
        System.out.println(keyStream);
        Stream<String> valueStream = map.values().stream();
        Stream<Map.Entry<String, String>> entryStream = map.entrySet().stream();

    }

    /**
     * Optional 是一个容器对象，可以存储对象、字符串等值，当然也可以存储 null 值。Optional 提供很多有用的方法，能帮助我们将 Java 中的对象等一些值存入其中，这样我们就不用显式进行空值检测，使我们能够用少量的代码完成复杂的流程。
     * <p>
     * 比如它提供了：
     * <p>
     * of() 方法，可以将值存入 Optional 容器中，如果存入的值是 null 则抛异常。
     * <p>
     * ofNullable() 方法，可以将值存入 Optional 容器中，即使值是 null 也不会抛异常。
     * <p>
     * get() 方法，可以获取容器中的值，如果值为 null 则抛出异常。
     * <p>
     * getElse() 方法，可以获取容器中的值，如果值为 null 则返回设置的默认值。
     * <p>
     * isPresent() 方法，该方法可以判断存入的值是否为空。
     * <p>
     * …等等一些其它常用方法，下面会进行介绍。
     * <p>
     * 使用 Optional 可以帮助我们解决业务中，减少值动不动就抛出空指针异常问题，也减少 null 值的判断，提高代码可读性等，这里我们介绍下，如果使用这个 Optional 类。
     */

    //静态方法 Optional.of()
    //为指定的值创建一个指定非 null 值的 Optional。
    @Test
    public void ofTest() {
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional = Optional.of("加油");
        System.out.println(optional);

        //传入null值  则会报 NullPointerException
        Optional optional1 = Optional.of(null);
        System.out.println(optional1);

    }


    //静态方法 Optional.ofNullable()
    //为指定的值创建一个 Optional 对象，如果指定的参数为 null，不抛出异常，直接则返回一个空的 Optional 对象。
    @Test
    public void ofNullAbleTest() {
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional = Optional.ofNullable("嘿呀");
        System.out.println(optional);

        //传入null值   正常返回Optional 对象
        Optional optional1 = Optional.ofNullable(null);
        System.out.println(optional1);
    }

    //对象方法 isPresent()
    //如果值存在则方法会返回 true，否则返回 false。
    @Test
    public void isPresentTest() {
        //传入正常值 正常返回一个Optional对象  并使用isPresent方法
        Optional optional = Optional.ofNullable("嘿呦");
        System.out.println("传入正常值" + optional.isPresent());
        //结果  true

        //传入null值   正常返回Optional 对象
        Optional optional1 = Optional.ofNullable(null);
        System.out.println("传入正常值" + optional1.isPresent());
        //结果  false
    }


    //对象方法 get()
    //如果 Optional 有值则将其返回，否则抛出 NoSuchElementException 异常。
    @Test
    public void getTest() {
        //传入正常值 正常返回一个Optional对象  并使用get方法
        Optional optional = Optional.ofNullable("嘿呦");
        System.out.println("传入正常值" + optional.get());
        //结果  传入正常值嘿呦

        //传入null值   正常返回Optional 对象
        Optional optional1 = Optional.ofNullable(null);
        System.out.println("传入正常值" + optional1.get());
        //结果  java.util.NoSuchElementException: No value present
    }

    //对象方法 ifPresent()
    // 如果值存在则使用该值调用 consumer , 否则不做任何事情。
    //该方法 ifPresent(Consumer<? super T> consumer) 中参数接收的是 Consumer 类，它包含一个接口方法 accept()，该方法能够对传入的值进行处理，但不会返回结果。这里传入参数可以传入 Lamdda 表达式或 Consumer 对象及实现 Consumer 接口的类的对象。
    @Test
    public void ifPresentTest() {
        //创建Optional 对象 然后调用Optional 对象的ifPresent 方法   传入Lambda 表达式
        Optional<String> optional = Optional.of("向目标前进");
        optional.ifPresent((value) -> System.out.println("optional 的值为" + value));
        //结果  向目标前进

        //创建Optional 对象 调用Optional对象的ifPresent方法 传入实现Consumer匿名
        Optional<String> optional1 = Optional.of("加油呀");
        Consumer<String> consumer = new Consumers() {
            @Override
            public void accept(Object value) {
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
    public void orElseTest() {
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
    public void orElseGetTest() {
        //传入正常参数   获取一个Optional 对象  并使用orElse方法 设置默认值
        Optional optional = Optional.ofNullable("新的一周");
        Object object = optional.orElseGet(() -> {
            String defaultVal = "执行逻辑和生成默认值";
            return defaultVal;
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
    public void orElseThrowTest() {
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
    public void mapTest() {
        //创建一个map对象
        Map<String, String> userMap = new HashMap<>();
        userMap.put("name1", "jiayou");
        userMap.put("name2", "jiajiayou");

        //传入map对象  获取一个Optional对象 获取name1属性
        Optional<String> optional = Optional.ofNullable(userMap).map(value -> value.get("name1"));

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
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //使用 Optional 的 map 方法对值处理：
        @Test
        public void orElseTest1() {
            //创建一个对象  设置名字属性而不设置性别  这时候性别为null
            OptionalTest.User user = new OptionalTest.User("测试名称");
            OptionalTest.User user1 = new OptionalTest.User(null);

            //使用Optional存储User对象
            Optional<OptionalTest.User> optionalUser = Optional.ofNullable(user);
            Optional<OptionalTest.User> optionalUser1 = Optional.ofNullable(user1);

            //获取对象的name属性值
            String name = optionalUser.map(OptionalTest.User::getName).orElse("未填写");
            String name1 = optionalUser1.map(OptionalTest.User::getName).orElse("未填写");

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
        public void flatMapTest() {
            //创建一个map 对象
            Map<String, String> userMap = new HashMap<>();
            userMap.put("name", "jiayou");
            userMap.put("sex", "nan");

            //传入Map对象参数   获取一个Optional对象
            Optional<Map<String, String>> optional = Optional.of(userMap);
            //使用Optionalde flatMap方法  获取Map中的name属性值,然后通过获取的值手动创建一个新的Optional对象
            Optional optional1 = optional.flatMap(value -> Optional.ofNullable(value.get("name")));
            System.out.println("获取的Optional 的值" + optional1.get());

            /**
             * 打印结果
             * jiayou
             */

        }

        //对象方法 filter()
        // filter 方法通过传入的限定条件对 Optional 实例的值进行过滤，如果 Optional 值不为空且满足限定条件就返回包含值的 Optional，否则返回空的 Optional。这里设置的限定条件需要使用实现了 Predicate 接口的 lambda 表达式来进行配置。
        @Test
        public void filterTest() {
            //创建一个测试的Optional对象
            Optional<String> optional = Optional.ofNullable("XIANGQIANCHONGYA");

            //调用Optionalde filter 方法  设置一个满足的条件  然后观察获取Optional对象值 是否为空
            Optional optional1 = optional.filter((value) -> value.length() > 2);
            System.out.println("Optional 的值不为空:" + optional1.isPresent());

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
        public void OptionalExample() {
            //创建一个测试的用户集合
            List<OptionalTest.User> userList = new ArrayList<>();

            //创建几个测试用户
            OptionalTest.User user1 = new OptionalTest.User("abc");
            OptionalTest.User user2 = new OptionalTest.User("efg");
            OptionalTest.User user3 = null;

            //将用户加入集合
            userList.add(user1);
            userList.add(user2);
            userList.add(user3);

            //创建用于存储姓名的集合
            List<String> nameList = new ArrayList<>();

            //循环用户列表获取用户信息,值获取不为空且用户以a开头的姓名
            //如果不符合条件就设置默认值 最后将符合条件的用户姓名加入姓名集合
            for (OptionalTest.User user : userList) {
                // nameList.add(Optional.ofNullable(user).map(User::getName).filter(value -> value.startsWith("a")));

            }
            System.out.println("通过Optional过滤的集合输出");
            nameList.stream().forEach(System.out::println);
        }

    }


    @Value("${bigdata.url.doctor.doctorReadUrl}")
    private String doctorReadUrl;

    /**
     * 指定索引文档数据中按某个字段分组后对应的文档总数
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


    }

}
