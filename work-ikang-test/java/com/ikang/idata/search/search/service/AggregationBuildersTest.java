package com.ikang.idata.search.search.service;

import com.alibaba.csp.sentinel.Constants;
import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.context.NullContext;
import com.ikang.idata.search.search.ContextTestUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ReverseNestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.IncludeExclude;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/4/7
 */
@Slf4j
public class AggregationBuildersTest {

    //常见的聚合查询
    @Test
    public void  AggregationBuildersTest1(){
        //统计某个字段的数量
        ValueCountAggregationBuilder field = AggregationBuilders.count("count_uid").field("uid");

        //去重统计某个字段的数量（有少量误差）
        CardinalityAggregationBuilder field1 = AggregationBuilders.cardinality("distinct_count_uid").field("uid");

        //聚合过滤
        FilterAggregationBuilder fab= AggregationBuilders.filter("uid_filter",QueryBuilders.queryStringQuery("uid:001"));
        //FilterAggregationBuilder fab= AggregationBuilders.filter("uid_filter").filter(QueryBuilders.queryStringQuery("uid:001"));

        //按某个字段分组
        TermsAggregationBuilder termsField = AggregationBuilders.terms("group_name").field("name");

        //求和
        SumAggregationBuilder field2 = AggregationBuilders.sum("sum_price").field("price");

        //求平均
        AvgAggregationBuilder field3 = AggregationBuilders.avg("avg_price").field("price");

        //求最大值
        MaxAggregationBuilder field4 = AggregationBuilders.max("max_price").field("price");

        //求最小值
        MinAggregationBuilder field5 = AggregationBuilders.min("min_price").field("price");

        //按日期间隔分组
        DateHistogramAggregationBuilder field6 = AggregationBuilders.dateHistogram("dh").field("date");

        //获取聚合里面的结果
        TopHitsAggregationBuilder top_result = AggregationBuilders.topHits("top_result");

        //嵌套的聚合
        String path = AggregationBuilders.nested("negsted_path","quests").path();
        //NestedBuilder nb= AggregationBuilders.nested("negsted_path").path("quests");

        //反转嵌套
        ReverseNestedAggregationBuilder path1 = AggregationBuilders.reverseNested("res_negsted").path("kps ");
    }


    //简单的词频统计
    @Test
    public void tes1(){
        //构建数据筛选条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.rangeQuery("publish_time")
        .gte("startTime")
        .lte("endTime"));
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(0)
                .size(0)
                .timeout(new TimeValue(60, TimeUnit.SECONDS))
                .query(boolQueryBuilder);


        //构建聚合桶
        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("terms_aggs")
                .field("word_cloud")
                .includeExclude(new IncludeExclude(".{2,}",null))
                .size(50);
        builder.aggregation(termsAggregationBuilder);

        //构建查询对象
        SearchRequest searchRequest = new SearchRequest("ES_INDEX_NAME");
        searchRequest.source(builder);

        // 查询 获取查询结果
        SearchResponse response = null;

//        try {
//            response = ES.getHighLevelClient().search(searchRequest);
//
//        }catch (IOException e){
//            log.warn("search ES Error" , e);
//            return;
//        }

        //取出查询结果
        List<Map<String,Object>> results = new ArrayList<>();
        Terms aggs = response.getAggregations().get("terms_aggr");
        for (Terms.Bucket bucket: aggs.getBuckets()) {
            String key = bucket.getKeyAsString().toLowerCase().replaceFirst("'s$", "");
            Map<String,Object> map = new HashMap<>(2);
            map.put("name",key);
            map.put("value",bucket.getDocCount());
            results.add(map);
            System.out.println("results" + results);

        }
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings) {
        int count = 0;

        for (String string : strings) {

            if (string.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings) {
        int count = 0;

        for (String string : strings) {

            if (string.length() == 3) {
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
        List<String> filteredList = new ArrayList<String>();

        for (String string : strings) {

            if (!string.isEmpty()) {
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : strings) {

            if (!string.isEmpty()) {
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length() - 2);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        List<Integer> squaresList = new ArrayList<Integer>();

        for (Integer number : numbers) {
            Integer square = new Integer(number.intValue() * number.intValue());

            if (!squaresList.contains(square)) {
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers) {
        int max = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {

            Integer number = numbers.get(i);

            if (number.intValue() > max) {
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers) {
        int min = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);

            if (number.intValue() < min) {
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List numbers) {
        int sum = (int) (numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            sum += (int) numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers) {
        return getSum(numbers) / numbers.size();
    }


    @org.junit.Test
    public void testM() {
        int[] nums = {1, 2, 3};
        int sum2 = IntStream.of(nums).parallel().sum();
        System.out.println("结果为：" + sum2);
    }


    @org.junit.Test
    public void testJava8() {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        System.out.println(convertCtoF.applyAsDouble(24));
        System.out.println(convertUSDtoGBP.applyAsDouble(100));
        System.out.println(convertKmtoMi.applyAsDouble(20));

        DoubleUnaryOperator convertFtoC = expandedCurriedConverter(-32, 5.0 / 9, 0);
        System.out.println(convertFtoC.applyAsDouble(98.6));
    }

    static double converter(double x, double y, double z) {
        return x * y + z;
    }

    static DoubleUnaryOperator curriedConverter(double y, double z) {
        return (double x) -> x * y + z;
    }

    static DoubleUnaryOperator expandedCurriedConverter(double w, double y, double z) {
        return (double x) -> (x + w) * y + z;
    }

    @org.junit.Test
    public void testCe() {

        MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<Integer>()));

        System.out.println(l.head());

        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();
        System.out.println(two + " " + three + " " + four);

        numbers = from(2);
        int prime_two = primes(numbers).head();
        int prime_three = primes(numbers).tail().head();
        int prime_five = primes(numbers).tail().tail().head();
        System.out.println(prime_two + " " + prime_three + " " + prime_five);

        // this will run until a stackoverflow occur because Java does not
        // support tail call elimination
        // printAll(primes(from(2)));
    }


    interface MyList<T> {
        T head();

        MyList<T> tail();

        default boolean isEmpty() {
            return true;
        }

        MyList<T> filter(Predicate<T> p);
    }

    static class MyLinkedList<T> implements MyList<T> {
        final T head;
        final MyList<T> tail;

        public MyLinkedList(T head, MyList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public MyList<T> tail() {
            return tail;
        }

        public boolean isEmpty() {
            return false;
        }

        public MyList<T> filter(Predicate<T> p) {
            return isEmpty() ? this : p.test(head()) ? new MyLinkedList<>(
                    head(), tail().filter(p)) : tail().filter(p);
        }
    }

    static class Empty<T> implements MyList<T> {
        public T head() {
            throw new UnsupportedOperationException();
        }

        public MyList<T> tail() {
            throw new UnsupportedOperationException();
        }

        public MyList<T> filter(Predicate<T> p) {
            return this;
        }
    }

    static class LazyList<T> implements MyList<T> {
        final T head;
        final Supplier<MyList<T>> tail;

        public LazyList(T head, Supplier<MyList<T>> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public MyList<T> tail() {
            return tail.get();
        }

        public boolean isEmpty() {
            return false;
        }

        public MyList<T> filter(Predicate<T> p) {
            return isEmpty() ? this : p.test(head()) ? new LazyList<>(head(),
                    () -> tail().filter(p)) : tail().filter(p);
        }

    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<Integer>(n, () -> from(n + 1));
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(), () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
    }

    static <T> void printAll(MyList<T> numbers) {
        if (numbers.isEmpty()) {
            return;
        }
        System.out.println(numbers.head());
        printAll(numbers.tail());
    }


    @org.junit.Test
    public void test() {
        simplify();

        Expr e = new BinOp("+", new Number(5), new BinOp("*", new Number(3), new Number(4)));
        Integer result = evaluate(e);
        System.out.println(e + " = " + result);
    }

    private static void simplify() {
        TriFunction<String, Expr, Expr, Expr> binopcase =
                (opname, left, right) -> {
                    if ("+".equals(opname)) {
                        if (left instanceof Number && ((Number) left).val == 0) {
                            return right;
                        }
                        if (right instanceof Number && ((Number) right).val == 0) {
                            return left;
                        }
                    }
                    if ("*".equals(opname)) {
                        if (left instanceof Number && ((Number) left).val == 1) {
                            return right;
                        }
                        if (right instanceof Number && ((Number) right).val == 1) {
                            return left;
                        }
                    }
                    return new BinOp(opname, left, right);
                };
        Function<Integer, Expr> numcase = val -> new Number(val);
        Supplier<Expr> defaultcase = () -> new Number(0);

        Expr e = new BinOp("+", new Number(5), new Number(0));
        Expr match = patternMatchExpr(e, binopcase, numcase, defaultcase);
        if (match instanceof Number) {
            System.out.println("Number: " + match);
        } else if (match instanceof BinOp) {
            System.out.println("BinOp: " + match);
        }
    }

    private static Integer evaluate(Expr e) {
        Function<Integer, Integer> numcase = val -> val;
        Supplier<Integer> defaultcase = () -> 0;
        TriFunction<String, Expr, Expr, Integer> binopcase =
                (opname, left, right) -> {
                    if ("+".equals(opname)) {
                        if (left instanceof Number && right instanceof Number) {
                            return ((Number) left).val + ((Number) right).val;
                        }
                        if (right instanceof Number && left instanceof BinOp) {
                            return ((Number) right).val + evaluate((BinOp) left);
                        }
                        if (left instanceof Number && right instanceof BinOp) {
                            return ((Number) left).val + evaluate((BinOp) right);
                        }
                        if (left instanceof BinOp && right instanceof BinOp) {
                            return evaluate((BinOp) left) + evaluate((BinOp) right);
                        }
                    }
                    if ("*".equals(opname)) {
                        if (left instanceof Number && right instanceof Number) {
                            return ((Number) left).val * ((Number) right).val;
                        }
                        if (right instanceof Number && left instanceof BinOp) {
                            return ((Number) right).val * evaluate((BinOp) left);
                        }
                        if (left instanceof Number && right instanceof BinOp) {
                            return ((Number) left).val * evaluate((BinOp) right);
                        }
                        if (left instanceof BinOp && right instanceof BinOp) {
                            return evaluate((BinOp) left) * evaluate((BinOp) right);
                        }
                    }
                    return defaultcase.get();
                };

        return patternMatchExpr(e, binopcase, numcase, defaultcase);
    }

    static class Expr {
    }

    static class Number extends Expr {
        int val;

        public Number(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "" + val;
        }
    }

    static class BinOp extends Expr {
        String opname;
        Expr left, right;

        public BinOp(String opname, Expr left, Expr right) {
            this.opname = opname;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "(" + left + " " + opname + " " + right + ")";
        }
    }

    static <T> T MyIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
        return b ? truecase.get() : falsecase.get();
    }

    static interface TriFunction<S, T, U, R> {
        R apply(S s, T t, U u);
    }

    static <T> T patternMatchExpr(Expr e,
                                  TriFunction<String, Expr, Expr, T> binopcase,
                                  Function<Integer, T> numcase, Supplier<T> defaultcase) {

        if (e instanceof BinOp) {
            return binopcase.apply(((BinOp) e).opname, ((BinOp) e).left, ((BinOp) e).right);
        } else if (e instanceof Number) {
            return numcase.apply(((Number) e).val);
        } else {
            return defaultcase.get();
        }
    }


    static class Dish {

        private final String name;
        private final boolean vegetarian;
        private final int calories;
        private final Type type;

        public Dish(String name, boolean vegetarian, int calories, Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public int getCalories() {
            return calories;
        }

        public Type getType() {
            return type;
        }

        enum Type {MEAT, FISH, OTHER}

        @Override
        public String toString() {
            return name;
        }

        public static final List<Dish> menu =
                Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                        new Dish("beef", false, 700, Dish.Type.MEAT),
                        new Dish("chicken", false, 400, Dish.Type.MEAT),
                        new Dish("french fries", true, 530, Dish.Type.OTHER),
                        new Dish("rice", true, 350, Dish.Type.OTHER),
                        new Dish("season fruit", true, 120, Dish.Type.OTHER),
                        new Dish("pizza", true, 550, Dish.Type.OTHER),
                        new Dish("prawns", false, 400, Dish.Type.FISH),
                        new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    @org.junit.Test
    public void test7Vs8() {

        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : dishes) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        lowCaloricDishes.sort(Comparator.comparingInt(Dish::getCalories));
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }

    @org.junit.Test
    public void testReduce() {
        List<Integer> numbersOne = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbersOne.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = numbersOne.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int max = numbersOne.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> min = numbersOne.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int calories = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);
    }

    @org.junit.Test
    public void testDouble() {
        DoubleStream doubleStream = DoubleStream.of(0.8D, 0.44545D, 0.446464D);
        OptionalDouble average = doubleStream.average();
//        double sum = doubleStream.sum();/
//        System.out.println(sum);
        average.ifPresent(System.out::println);
    }


    @org.junit.Test
    public void testInt() {
        Stream<Integer> stream = IntStream.range(0, 100).boxed();
        System.out.println(stream);
    }

    @org.junit.Test
    public void testParallel() {
        List<DoubleSummaryStatistics> statistic;
    }


    //----------------------------------------------------------------------------------------

    private long character;
    /** 是否结尾 End of stream */
    private boolean eof;
    /** 在Reader的位置（解析到第几个字符） */
    private long index;
    /** 当前所在行 */
    private long line;
    /** 前一个字符 */
    private char previous;
    /** 是否使用前一个字符 */
    private boolean usePrevious;
    /** 源 */
    private Reader reader;

    // ------------------------------------------------------------------------------------ Constructor start
    /**
     * 从Reader中构建
     *
     * @param reader Reader
     */
    public AggregationBuildersTest(Reader reader) {
        this.reader = reader.markSupported() ? reader : new BufferedReader(reader);
        this.eof = false;
        this.usePrevious = false;
        this.previous = 0;
        this.index = 0;
        this.character = 1;
        this.line = 1;
    }


    //--------------------------------------------------------------------------------------------
    @Before
    public void setUp() {
        resetContextMap();
    }

    @After
    public void cleanUp() {
        ContextTestUtil.cleanUpContext();
    }

    @org.junit.Test
    public void testEnterCustomContextWhenExceedsThreshold() {
        fillContext();
        try {
            String contextName = "abc";
            ContextUtil.enter(contextName, "bcd");
            Context curContext = ContextUtil.getContext();
            assertNotEquals(contextName, curContext.getName());
            assertTrue(curContext instanceof NullContext);
            assertEquals("", curContext.getOrigin());
        } finally {
            ContextUtil.exit();
            resetContextMap();
        }
    }

    @org.junit.Test
    public void testDefaultContextWhenExceedsThreshold() {
        fillContext();
        try {
            ContextUtil.enter(Constants.CONTEXT_DEFAULT_NAME, "");
            Context curContext = ContextUtil.getContext();
            assertEquals(Constants.CONTEXT_DEFAULT_NAME, curContext.getName());
            assertNotNull(curContext.getEntranceNode());
        } finally {
            ContextUtil.exit();
            resetContextMap();
        }
    }

    private void fillContext() {
        for (int i = 0; i < Constants.MAX_CONTEXT_NAME_SIZE; i++) {
            ContextUtil.enter("test-context-" + i);
            ContextUtil.exit();
        }
    }

    private void resetContextMap() {
        ContextTestUtil.resetContextMap();
    }

}
