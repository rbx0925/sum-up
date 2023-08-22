package com.ikang.idata.search.search;

import cn.hutool.core.bean.*;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ModifierUtil;
import cn.hutool.core.util.NumberUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.ikang.idata.search.search.entity.RenewalOrderInfoEnum;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import jodd.util.function.Consumers;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregator;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.mockito.InOrder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.beans.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Slf4j
public class DemoTest {
    public static void parallelStreamTest() {
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<20;i++) {
            list.add(i);
        }
        System.out.println(list.parallelStream().count());
        List<Integer> htmlChapterVOS = new ArrayList<>();
        list.parallelStream().forEach(
                i-> {
                    htmlChapterVOS.add(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        log.info("----list.size():{}, htmlChapterVOS.size:{}",list.parallelStream().count(), htmlChapterVOS.parallelStream().count());

    }



    public static void main(String[] args) {

        parallelStreamTest();

       /* for (int i=0; i<100;i++) {
            parallelStreamTest2();
        }*/

      /*  String content = "牙齿缺失:26，1，3，3，26,12345,5；其他：右上4牙体缺损；";
        Set<String> rightUplist = new HashSet<>();
        Set<String> leftUplist = new HashSet<>();
        Set<String> rightDownlist = new HashSet<>();
        Set<String> leftDownlist = new HashSet<>();
        Pattern pattern = Pattern.compile("[0-9]{2,}");
        Matcher matcher = pattern.matcher(content);


        while (matcher.find()){
            System.out.println(matcher.group());
        }*/

//        while (matcher.find()){
////            System.out.println(matcher.group());
//            String group = matcher.group();
//            if(null !=group && group.length() == 2){
//                char temp = group.charAt(1);
//                if(group.startsWith("1")){
//                    rightUplist.add(temp+"");
////                    content.replace(group,"右上"+temp);
//                }else if(group.startsWith("2")){
//                    leftUplist.add(temp+"");
////                    content.replace(group,"左上"+temp);
//                }else if(group.startsWith("3")){
//                    leftDownlist.add(temp+"");
////                    content.replace(group,"左下"+temp);
//                }else if(group.startsWith("4")){
//                    rightDownlist.add(temp+"");
////                    content.replace(group,"右下"+temp);
//                }
//                for(int i = 0; i<content.length() ; i++){
//
//                }
//            }
//        }
//        System.out.println(content);
        System.out.println("使用 Java 7: ");

        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        System.out.println("列表: " + strings);
        long count = getCountEmptyStringUsingJava7(strings);

        System.out.println("空字符数量为: " + count);
        count = getCountLength3UsingJava7(strings);

        System.out.println("字符串长度为 3 的数量为: " + count);

        // 删除空字符串
        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        System.out.println("筛选后的列表: " + filtered);

        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = getMergedStringUsingJava7(strings, ", ");
        System.out.println("合并字符串: " + mergedString);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        // 获取列表元素平方数
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("平方数列表: " + squaresList);
        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);

        System.out.println("列表: " + integers);
        System.out.println("列表中最大的数 : " + getMax(integers));
        System.out.println("列表中最小的数 : " + getMin(integers));
        System.out.println("所有数之和 : " + getSum(integers));
        System.out.println("平均数 : " + getAverage(integers));
        System.out.println("随机数: ");

        // 输出10个随机数
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
        }

        System.out.println("--------------------------------------------------");

        System.out.println("使用 Java 8: ");
        System.out.println("列表: " + strings);

        count = strings.stream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串数量为: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);
        System.out.println("列表: " + integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);
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
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
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
        List<DoubleSummaryStatistics> statistics = IntStream.range(0, 10000).boxed().parallel()
                .map(String::valueOf).map(s -> DoubleStream.of(0.8D, 0.44545D, 0.446464D).parallel()
                        .summaryStatistics()).collect(toList());
        System.out.println(statistics.size());
        statistics.forEach(System.out::println);

//            List<ReportResultDTO> collect = subs.stream().parallel().map(workNos -> {
//            Weekend<PisCheckItemIndexResult> weekend = Weekend.of(PisCheckItemIndexResult.class);
//            WeekendCriteria<PisCheckItemIndexResult, Object> weekendCriteria = weekend.weekendCriteria();
//            weekendCriteria.andIn(PisCheckItemIndexResult::getWorkNo, workNos);
//            weekendCriteria.andIsNotNull(PisCheckItemIndexResult::getValue);
//            return pisCheckItemIndexResultMapper.selectByExample(weekend).parallelStream().map(item -> {
//                ReportResultDTO dto = new ReportResultDTO();
//                if (null == item.getValue()) {
////                            log.debug("体检指标结果为空, 该条数据忽略 workNo={} data={}", item.getWorkNo(), JSONUtil.toJsonStr(item));
//                    return null;
//                }
//                dto.setResultValue(item.getValue());
//                dto.setWorkNo(item.getWorkNo());
//                dto.setMisCode(item.getCode());
//                dto.setLowValue(item.getLowValue() != null && NumberUtils.isNumber(item.getLowValue()) ? Float.parseFloat(item.getLowValue()) : 0);
//                dto.setHighValue(item.getHighValue() != null && NumberUtils.isNumber(item.getHighValue()) ? Float.parseFloat(item.getHighValue()) : Integer.MAX_VALUE);
//                return dto;
//            }).filter(Objects::nonNull).collect(Collectors.toList());
//        }).flatMap(Collection::stream).collect(Collectors.toList());

    }




    @Data
    private class Person {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getAddress(String anyString) {
            return "万能的张三";
        }
    }

    @org.junit.Test
    public void merge() {
        List<Integer> listA = Arrays.asList(new Integer[]{1, 2});
        List<Integer> listB = Arrays.asList(new Integer[]{3, 4});
        List<Integer> res = Stream.of(listA, listB).flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(res.size());
        res.forEach(System.out::println);
    }

    @org.junit.Test
    public void testMock() {
        Person person = mock(Person.class);
        System.out.println(person);
        //对于 特别难生成的对象 可以通过 mock 进行生成
        // 也可以设置测试桩
        Person mock = mock(Person.class, RETURNS_DEFAULTS);
        System.out.println(mock.getName());

        Person foo = mock(Person.class, RETURNS_DEEP_STUBS);

        //测试桩
        //当调用的  对应的
        when(foo.getName()).thenReturn("万恶的张三");
        System.out.println(foo.getName());

        // 你可以mock具体的类型,不仅只是接口  这个很好用
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0)).thenReturn("first");

        when(mockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0));

        System.out.println(mockedList.get(1));

        System.out.println(mockedList.get(999));

    }

    @org.junit.Test
    public void testMockTwo() {
        LinkedList list = mock(LinkedList.class);
        //多个调用返回的情况
        when(list.size()).thenReturn(1, 2, 3);
        //1
        System.out.println(list.size());
        //2
        System.out.println(list.size());
        //3
        System.out.println(list.size());
        // 超过次数后 使用的 最后的  注意
        System.out.println(list.size());
    }

    @org.junit.Test
    public void testInOrder() {
        //执行顺序  对于调试
        List mock = mock(List.class);
        mock.add("fast execution");
        mock.add("second execution");

        InOrder order = inOrder(mock);
        //当运行顺序不一样的时候 会出现 Argument(s) are different! Wanted:
        order.verify(mock).add(" execution");
        order.verify(mock).add("second execution");
    }

    @org.junit.Test
    public void testNumberOfCalls() {
        List list = mock(List.class);
        list.add("罗老师");
        list.add("张三");
        list.add("张三的一生");
        list.add("脱口秀");
        list.add("交个朋友");
        list.add("交个朋友");

        verify(list).add("罗老师");
        //执行次数
        verify(list, times(2)).add("交个朋友");
    }

    @org.junit.Test
    public void testAssertThat() {
        List<Integer> list = mock(List.class);

        // 断言list.get(0)值等于1
        assertThat(list.get(0)).isEqualTo(1);

        // 断言大于50
        assertThat(list.get(0)).isGreaterThan(20);
        // 断言小于等于50
        assertThat(list.get(0)).isLessThanOrEqualTo(50);

        // 断言 必须大于20 并且 小于等于50（所有条件成立）
//        assertThat(list.get(0), allOf(greaterThan(20), lessThanOrEqualTo(50)));
        // 断言 必须大于20 或 小于等于50（其中至少一个条件成立）
//        assertThat(list.get(0), oneOf(greaterThan(20), lessThanOrEqualTo(50)));

        // 断言任何条件都成立
//        assertThat(list.get(0), anything());
        // 断言等于1
//        assertThat(list.get(0), is(1));
        // 断言不等于-1
//        assertThat(list.get(0), not(-1));
        // 断言返回的字符串包含1
//        assertThat(list.get(0), containsString("1"));
        // 断言返回的字符串以1开头
//        assertThat(list.get(0), startsWith("1"));
//         断言该异常属于RuntimeException
//        assertThat(e, instanceOf(RuntimeException.class));
    }


    @org.junit.Test
    public void testContinuous() {
        IntStream intStream = IntStream.of(2020, 2018, 2017).sorted();
//        intStream.forEach(System.out::println);
        System.out.println(IsContinuous(intStream.toArray()));
    }

    public static Boolean IsContinuous(int[] arr) {
        if (arr.length < 3) {
            return false;
        }

        boolean b = false;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] * 2 != arr[i - 1] + arr[i + 1]) {
                b = false;
                break;
            }
            if (Math.abs(arr[i + 1] - arr[i]) != 1) {
                b = false;
                break;
            }
            if ((arr[i + 1] - arr[i]) != (arr[i] - arr[i - 1])) {
                b = false;
                break;
            }
            b = true;
        }
        return b;
    }



    @org.junit.Test
    public void testPrimitive() {
        System.out.println(Ints.asList(1, 2, 3, 4));
        System.out.println(Ints.compare(1, 2));
        System.out.println(Ints.join(" ", 1, 2, 3, 4));
        System.out.println(Ints.max(1, 3, 5, 4, 6));
        System.out.println(Ints.tryParse("1234"));
    }

    @org.junit.Test
    public void testString() {
        Joiner joiner = Joiner.on("; ").skipNulls();
        String s = joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(s);

        String s1 = Joiner.on(",").join(Arrays.asList(1, 5, 7));
        System.out.println(s1);
    }

    @org.junit.Test
    public void testStr() {

        String s = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");
        System.out.println(s);
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom("移除control字符"); //移除control字符
        System.out.println(noControl);
        String theDigits = CharMatcher.DIGIT.retainFrom("control123123"); //只保留数字字符
        System.out.println(theDigits);
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom("    这里是   空格     ", ' ');//去除两端的空格，并把中间的连续空格替换成单个空格
        System.out.println(spaced);
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom("中间是10000数字", "*"); //用*号替换所有数字
        System.out.println(noDigits);
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom("张三的一生1213");// 只保留数字和小写字母
        System.out.println(lowerAndDigit);
    }


    @org.junit.Test
    public void testX() {
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    /**
     * 初始化 objectMapper 属性
     * <p>
     * 通过这样的方式，使用 Spring 创建的 ObjectMapper Bean
     *
     * @param objectMapper ObjectMapper 对象
     */
    public static void init(ObjectMapper objectMapper) {
        objectMapper = objectMapper;
    }

    public static String toJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(text, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得服务器的IP地址
     */
    public static String getLocalIP() {
        String ipStr = "";
        InetAddress inetAddress = null;
        try {
            boolean isFindIp = false;
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                if (isFindIp) {
                    break;
                }
                NetworkInterface ni = netInterfaces
                        .nextElement();
                Enumeration<InetAddress> inetAddressList = ni.getInetAddresses();
                while (inetAddressList.hasMoreElements()) {
                    inetAddress = inetAddressList.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress.getHostAddress().matches(
                            "(\\d{1,3}\\.){3}\\d{1,3}")) {
                        isFindIp = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
        if (Objects.nonNull(inetAddress)) {
            ipStr = inetAddress.getHostAddress();
        }
        return ipStr;
    }

    /**
     * 获得服务器的IP地址(多网卡)
     */
    public static List<String> getLocalIPS() {
        InetAddress ip = null;
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces
                        .nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = ips.nextElement();
                    if (!ip.isLoopbackAddress()
                            && ip.getHostAddress().matches(
                            "(\\d{1,3}\\.){3}\\d{1,3}")) {
                        ipList.add(ip.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
        }
        return ipList;
    }
    public enum RelationTest {

        GT((value1, value2) -> Double.compare(value1, value2) == 1),
        GE((value1, value2) -> Double.compare(value1, value2) >= 0),
        LT((value1, value2) -> Double.compare(value1, value2) == -1),
        LE((value1, value2) -> Double.compare(value1, value2) <= 0),
        EQ((value1, value2) -> Double.compare(value1, value2) == 0);

//        public abstract boolean apply(double value1, double value2);

        private final BiFunction<Double, Double, Boolean> operator;

        private final static Map<String, RelationTest> STRING_RELATION_TEST_MAP =
                Stream.of(values()).collect(Collectors.toMap(Object::toString, Function.identity()));

        RelationTest(BiFunction<Double, Double, Boolean> operator) {
            this.operator = operator;
        }

        public static Optional<RelationTest> fromString(String symbol) {
            return Optional.ofNullable(STRING_RELATION_TEST_MAP.get(symbol));
        }

    }

    @org.junit.Test
    public void testEnum() {
        RelationTest.fromString("EQ").ifPresent(relationTest -> System.out.println(relationTest.operator.apply(0.01, 0.01)));
    }

    //算法
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    private static final String AES_KEY = "AnjiPLUSAjReport";


    /**
     * 获取随机key
     *
     * @return
     */
    public static String getKey() {
        return AES_KEY;
    }


    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        //return Base64.encodeBase64String(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        return StringUtils.isEmpty(base64Code) ? null : decoder.decode(base64Code);
    }


    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        if (StringUtils.isBlank(encryptKey)) {
            return content;
        }
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }


    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        if (StringUtils.isBlank(decryptKey)) {
            return encryptStr;
        }
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    @Test
    public void testSecretCode() throws Exception {
        String randomString = getKey();
        String content = "report";
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + randomString);
        String encrypt = aesEncrypt(content, randomString);
        System.out.println("加密后：" + encrypt);
        String decrypt = aesDecrypt(encrypt, randomString);
        System.out.println("解密后：" + decrypt);
    }


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
    @org.junit.jupiter.api.Test
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


    //对象方法 get()
    //如果 Optional 有值则将其返回，否则抛出 NoSuchElementException 异常。
    @org.junit.jupiter.api.Test
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
    @org.junit.jupiter.api.Test
    public void ifPresentTest(){
        //创建Optional 对象 然后调用Optional 对象的ifPresent 方法   传入Lambda 表达式
        Optional optional= Optional.ofNullable("向目标前进");
        optional.ifPresent((value) -> System.out.println("optional 的值为" + value));
        //结果  向目标前进

        //创建Optional 对象 调用Optional对象的ifPresent方法 传入实现Consumer匿名
        Optional optional1= Optional.ofNullable("加油呀");
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
    @org.junit.jupiter.api.Test
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
    @org.junit.jupiter.api.Test
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
    @org.junit.jupiter.api.Test
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
    @org.junit.jupiter.api.Test
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

    @Test
    public void testClient() throws IOException {

        // 1、设置连接管理器
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory>create().register("http", plainsf).register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(1000);
        cm.setDefaultMaxPerRoute(500);

        // 2、创建请求配置核心设置超时
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000)
                .setConnectTimeout(1000).setSocketTimeout(2000).build();

        // 3、创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        // 4、发送请求并解析返回值
        HttpGet httpGet = new HttpGet("http://hc.apache.org/");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            // 解析返回值
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, Charset.forName("utf8"));
            EntityUtils.consume(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭并释放连接，可能多余
            response.close();
        }
    }

    @Test
    public void testOne() {
        HttpGet get = new HttpGet("https://httpbin.org/get");
        try ( CloseableHttpClient client = HttpClients.createDefault();
              CloseableHttpResponse execute = client.execute(get)) {
            HttpEntity entity = execute.getEntity();
            System.out.println(execute.getStatusLine());
            System.out.println("***********************************************");
            System.out.println(EntityUtils.toString(entity));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void DateMethod() {
        Date today=new Date();
        //today中的日期被设成创建时刻的日期和时间，假设创建时刻为1997年3月23日17时51分54秒。
        System.out.println("Today's date is "+today);
        //返回一般的时间表示法，本例中结果为Today's date is Fri May 23 17:51:54 1997
        System.out.println("Today's date(Internet GMT)is:" +today.toGMTString());
        //返回结果为GMT时间表示法，本例中结果为Today's date(Internet GMT)is: 23 May 1997 09:51:54:GMT
        System.out.println("Today's date(Locale) is:" +today.toLocaleString());
        //返回结果为本地习惯的时间表示法，结果为Today's date(Locale)is:05/23/97 17:51:54
        System.out.println("Today's year is: "+today.getYear());
        System.out.println("Today's month is: "+(today.getMonth()+1));
        System.out.println("Today's date is: "+today.getDate());
        //调用Date类中方法，获取年月日的值。下面调用了不同的构造方法来创建Date类的对象。
        Date day1=new Date(100,1,23,10,12,34);
        System.out.println("Day1's date is: "+day1);
        Date day2=new Date("Sat 12 Aug 1996 13:3:00");
        System.out.println("Day2's date is: "+day2);
        long l= Date.parse("Sat 5 Aug 1996 13:3:00 GMT+0800");
        Date day3= new Date(l);
        System.out.println("Day3's date(GMT)is: "+day3.toGMTString());
        System.out.println("Day3's date(Locale)is: "+day3.toLocaleString());
        System.out.println("Day3's time zone offset is:"+day3.getTimezoneOffset());
        System.out.println("90 度的正弦值：" + Math.sin(Math.PI/2));
        System.out.println("0度的余弦值：" + Math.cos(0));
        System.out.println("60度的正切值：" + Math.tan(Math.PI/3));
        System.out.println("1的反正切值： " + Math.atan(1));
        System.out.println("π/2的角度值：" + Math.toDegrees(Math.PI/2));
        System.out.println(Math.PI);
        //------------------------------------分割线--------------------------------------------------------
        // 初始化 Date 对象
        Date dateLast = new Date();

        //c的使用
        System.out.printf("全部日期和时间信息：%tc%n",dateLast);
        //f的使用
        System.out.printf("年-月-日格式：%tF%n",dateLast);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n",dateLast);
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",dateLast);
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",dateLast);
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR",dateLast);
//------------------------------------分割线--------------------------------------------------------
        Date dateOne=new Date();
        //b的使用，月份简称
        String str=String.format(Locale.US,"英文月份简称：%tb",dateOne);
        System.out.println(str);
        System.out.printf("本地月份简称：%tb%n",dateOne);
        //B的使用，月份全称
        str=String.format(Locale.US,"英文月份全称：%tB",dateOne);
        System.out.println(str);
        System.out.printf("本地月份全称：%tB%n",dateOne);
        //a的使用，星期简称
        str=String.format(Locale.US,"英文星期的简称：%ta",dateOne);
        System.out.println(str);
        //A的使用，星期全称
        System.out.printf("本地星期的简称：%tA%n",dateOne);
        //C的使用，年前两位
        System.out.printf("年的前两位数字（不足两位前面补0）：%tC%n",dateOne);
        //y的使用，年后两位
        System.out.printf("年的后两位数字（不足两位前面补0）：%ty%n",dateOne);
        //j的使用，一年的天数
        System.out.printf("一年中的天数（即年的第几天）：%tj%n",dateOne);
        //m的使用，月份
        System.out.printf("两位数字的月份（不足两位前面补0）：%tm%n",dateOne);
        //d的使用，日（二位，不够补零）
        System.out.printf("两位数字的日（不足两位前面补0）：%td%n",dateOne);
        //e的使用，日（一位不补零）
        System.out.printf("月份的日（前面不补0）：%te",dateOne);
        /**
         * 英文月份简称：May
         * 本地月份简称：五月
         * 英文月份全称：May
         * 本地月份全称：五月
         * 英文星期的简称：Thu
         * 本地星期的简称：星期四
         * 年的前两位数字（不足两位前面补0）：20
         * 年的后两位数字（不足两位前面补0）：17
         * 一年中的天数（即年的第几天）：124
         * 两位数字的月份（不足两位前面补0）：05
         * 两位数字的日（不足两位前面补0）：04
         * 月份的日（前面不补0）：4
         */
//------------------------------------分割线--------------------------------------------------------
        Calendar c1 = Calendar.getInstance();
// 获得年份
        int year = c1.get(Calendar.YEAR);
// 获得月份
        int month = c1.get(Calendar.MONTH) + 1;
// 获得日期
        int dateTwo = c1.get(Calendar.DATE);
// 获得小时
        int hour = c1.get(Calendar.HOUR_OF_DAY);
// 获得分钟
        int minute = c1.get(Calendar.MINUTE);
// 获得秒
        int second = c1.get(Calendar.SECOND);
// 获得星期几（注意（这个与Date类是不同的）：1代表星期日、2代表星期1、3代表星期二，以此类推）
        int day = c1.get(Calendar.DAY_OF_WEEK);
//------------------------------------分割线--------------------------------------------------------
        String months[] = {
                "Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec"};

        int yearTwo;
        // 初始化 Gregorian 日历
        // 使用当前时间和日期
        // 默认为本地时间和时区
        GregorianCalendar gcalendar = new GregorianCalendar();
        // 显示当前时间和日期的信息
        System.out.print("Date: ");
        System.out.print(months[gcalendar.get(Calendar.MONTH)]);
        System.out.print(" " + gcalendar.get(Calendar.DATE) + " ");
        System.out.println(yearTwo = gcalendar.get(Calendar.YEAR));
        System.out.print("Time: ");
        System.out.print(gcalendar.get(Calendar.HOUR) + ":");
        System.out.print(gcalendar.get(Calendar.MINUTE) + ":");
        System.out.println(gcalendar.get(Calendar.SECOND));
//------------------------------------分割线--------------------------------------------------------
        // 测试当前年份是否为闰年
        if(gcalendar.isLeapYear(yearTwo)) {
            System.out.println("当前年份是闰年");
        }
        else {
            System.out.println("当前年份不是闰年");
        }

        //2017-1-1
        //2017-0-31
        Calendar cOne = Calendar.getInstance();
        cOne.set(2017, 1, 1);
        System.out.println(cOne.get(Calendar.YEAR)
                +"-"+cOne.get(Calendar.MONTH)
                +"-"+cOne.get(Calendar.DATE));
        cOne.set(2017, 1, 0);
        System.out.println(cOne.get(Calendar.YEAR)
                +"-"+cOne.get(Calendar.MONTH)
                +"-"+cOne.get(Calendar.DATE));
        //------------------------------------分割线--------------------------------------------------------
        //2017-2-1
        //2017-1-28
        Calendar cTwo = Calendar.getInstance();
        cTwo.set(2017, 2, 1);
        System.out.println(cTwo.get(Calendar.YEAR)
                +"-"+cTwo.get(Calendar.MONTH)
                +"-"+cTwo.get(Calendar.DATE));
        cTwo.set(2017, 2, -10);
        System.out.println(cTwo.get(Calendar.YEAR)
                +"-"+cTwo.get(Calendar.MONTH)
                +"-"+cTwo.get(Calendar.DATE));
//------------------------------------分割线--------------------------------------------------------
        // 初始化 Date 对象
        Date dateThree = new Date();

        //c的使用
        System.out.printf("全部日期和时间信息：%tc%n",dateThree);
        //f的使用
        System.out.printf("年-月-日格式：%tF%n",dateThree);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n",dateThree);
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",dateThree);
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n",dateThree);
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR",dateThree);
        /**
         * 全部日期和时间信息：周六 8月 22 11:53:36 CST 2020
         * 年-月-日格式：2020-08-22
         * 月/日/年格式：08/22/20
         * HH:MM:SS PM格式（12时制）：11:53:36 上午
         * HH:MM:SS格式（24时制）：11:53:36
         * HH:MM格式（24时制）：11:53
         */
    }


    // 创建一个用户类，使用 Optional 操作用户对象，获取其 name 参数，结合 Optional 的 map 方法获取值，进行观察：
    public  class User {
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
        @org.junit.jupiter.api.Test
        public void orElseTest1(){
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
        @org.junit.jupiter.api.Test
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
        @org.junit.jupiter.api.Test
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

        @org.junit.jupiter.api.Test
        public void OptionalExample(){
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
    private void constructAgg(WorkTableSearchVo searchVO, String yearOnYearTimeStart, String yearOnYearTimeEnd, String endSignDate, SearchSourceBuilder sourceBuilder) {
        //聚合组装
        RangeAggregationBuilder rangeAggX = AggregationBuilders.range(RenewalOrderInfoEnum.sign_date.getFields()).field(RenewalOrderInfoEnum.sign_date.getFields())
                .addRange(new RangeAggregator.Range("签约时间范围-1", yearOnYearTimeStart, yearOnYearTimeEnd))
                .addRange(new RangeAggregator.Range("签约时间范围", searchVO.getStartSignDate(), endSignDate));
        CardinalityAggregationBuilder signProjectIdAgg = AggregationBuilders.cardinality(RenewalOrderInfoEnum.sign_project_id.getFields()).field(RenewalOrderInfoEnum.sign_project_id.getFields());
        TermsAggregationBuilder nextyearStageAgg = AggregationBuilders.terms(RenewalOrderInfoEnum.nextyear_stage.getFields()).field(RenewalOrderInfoEnum.nextyear_stage.getFields());

        SumAggregationBuilder signMoneySum = AggregationBuilders.sum(RenewalOrderInfoEnum.sign_money.getFields()).field(RenewalOrderInfoEnum.sign_money.getFields());
        TermsAggregationBuilder newOldTypeAgg = AggregationBuilders.terms(RenewalOrderInfoEnum.newoldtype.getFields()).field(RenewalOrderInfoEnum.newoldtype.getFields());
        ValueCountAggregationBuilder newyearSigninfoCount = AggregationBuilders.count(RenewalOrderInfoEnum.newyear_signinfo.getFields()).field(RenewalOrderInfoEnum.newyear_signinfo.getFields());
        SumAggregationBuilder newyearSignmoneySum = AggregationBuilders.sum(RenewalOrderInfoEnum.newyear_signmoney.getFields()).field(RenewalOrderInfoEnum.newyear_signmoney.getFields());

        sourceBuilder.aggregation(rangeAggX);
        rangeAggX.subAggregation(signMoneySum);
        rangeAggX.subAggregation(signProjectIdAgg);
        nextyearStageAgg.subAggregation(signProjectIdAgg);
        nextyearStageAgg.subAggregation(newyearSignmoneySum);
        rangeAggX.subAggregation(nextyearStageAgg);
        newOldTypeAgg.subAggregation(newyearSigninfoCount);
        newOldTypeAgg.subAggregation(signMoneySum);
        rangeAggX.subAggregation(newOldTypeAgg);
    }

      private static String REGEX = "dog";
        private static String INPUT = "The dog says meow. " +
                "All dogs say meow.";
        private static String REPLACE = "cat";

        @Test
        public  void regexTest() {
            Pattern p = Pattern.compile(REGEX);
            // get a matcher object
            Matcher m = p.matcher(INPUT);
            INPUT = m.replaceAll(REPLACE);
            System.out.println(INPUT);
        }
    /**
     * The cat says meow.
     * All cats say meow.
     */


    @Test
    public void Regex2(){
        String REGEX = "a*b";
        String INPUT = "aabfooaabfooabfoobkkk";
        String REPLACE = "-";

            Pattern p = Pattern.compile(REGEX);
            // 获取 matcher 对象
            Matcher m = p.matcher(INPUT);
            StringBuffer sb = new StringBuffer();
            while(m.find()){
                m.appendReplacement(sb,REPLACE);
            }
            m.appendTail(sb);
            System.out.println(sb.toString());
        }
    /**
     * -foo-foo-foo-kkk
     */


    /**
     *
     * BeanUtil
     *
     *
     * 判断是否为可读的Bean对象，判定方法是：
     *
     * <pre>
     *     1、是否存在只有无参数的getXXX方法或者isXXX方法
     *     2、是否存在public类型的字段
     * </pre>
     *
     * @param clazz 待测试类
     * @return 是否为可读的Bean对象
     * @see #hasGetter(Class)
     * @see #hasPublicField(Class)
     */
    public static boolean isReadableBean(Class<?> clazz) {
        return hasGetter(clazz) || hasPublicField(clazz);
    }

    /**
     * 判断是否为Bean对象，判定方法是：
     *
     * <pre>
     *     1、是否存在只有一个参数的setXXX方法
     *     2、是否存在public类型的字段
     * </pre>
     *
     * @param clazz 待测试类
     * @return 是否为Bean对象
     * @see #hasSetter(Class)
     * @see #hasPublicField(Class)
     */
    public static boolean isBean(Class<?> clazz) {
        return hasSetter(clazz) || hasPublicField(clazz);
    }

    /**
     * 判断是否有Setter方法<br>
     * 判定方法是否存在只有一个参数的setXXX方法
     *
     * @param clazz 待测试类
     * @return 是否为Bean对象
     * @since 4.2.2
     */
    public static boolean hasSetter(Class<?> clazz) {
        if (ClassUtil.isNormalClass(clazz)) {
            final Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getParameterTypes().length == 1 && method.getName().startsWith("set")) {
                    // 检测包含标准的setXXX方法即视为标准的JavaBean
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否为Bean对象<br>
     * 判定方法是否存在只有无参数的getXXX方法或者isXXX方法
     *
     * @param clazz 待测试类
     * @return 是否为Bean对象
     * @since 4.2.2
     */
    public static boolean hasGetter(Class<?> clazz) {
        if (ClassUtil.isNormalClass(clazz)) {
            for (Method method : clazz.getMethods()) {
                if (method.getParameterTypes().length == 0) {
                    if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 指定类中是否有public类型字段(static字段除外)
     *
     * @param clazz 待测试类
     * @return 是否有public类型字段
     * @since 5.1.0
     */
    public static boolean hasPublicField(Class<?> clazz) {
        if (ClassUtil.isNormalClass(clazz)) {
            for (Field field : clazz.getFields()) {
                if (ModifierUtil.isPublic(field) && false == ModifierUtil.isStatic(field)) {
                    //非static的public字段
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 创建动态Bean
     *
     * @param bean 普通Bean或Map
     * @return {@link DynaBean}
     * @since 3.0.7
     */
    public static DynaBean createDynaBean(Object bean) {
        return new DynaBean(bean);
    }

    /**
     * 查找类型转换器 {@link PropertyEditor}
     *
     * @param type 需要转换的目标类型
     * @return {@link PropertyEditor}
     */
    public static PropertyEditor findEditor(Class<?> type) {
        return PropertyEditorManager.findEditor(type);
    }

    /**
     * 获取{@link BeanDesc} Bean描述信息
     *
     * @param clazz Bean类
     * @return {@link BeanDesc}
     * @since 3.1.2
     */
    public static BeanDesc getBeanDesc(Class<?> clazz) {
        return BeanDescCache.INSTANCE.getBeanDesc(clazz, () -> new BeanDesc(clazz));
    }

    /**
     * 遍历Bean的属性
     *
     * @param clazz  Bean类
     * @param action 每个元素的处理类
     * @since 5.4.2
     */
    public static void descForEach(Class<?> clazz, Consumer<? super PropDesc> action) {
        getBeanDesc(clazz).getProps().forEach(action);
    }

    // --------------------------------------------------------------------------------------------------------- PropertyDescriptor

    /**
     * 获得Bean字段描述数组
     *
     * @param clazz Bean类
     * @return 字段描述数组
     * @throws BeanException 获取属性异常
     */
    public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) throws BeanException {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            throw new BeanException(e);
        }
        return ArrayUtil.filter(beanInfo.getPropertyDescriptors(), t -> {
            // 过滤掉getClass方法
            return false == "class".equals(t.getName());
        });
    }




}

