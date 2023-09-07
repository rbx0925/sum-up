package com.ikang.idata.search.search.javaTest;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2023/1/5
 */
public class PredicateTest {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> collect = list.stream().filter(x -> x > 5).collect(Collectors.toList());

        System.out.println(collect); // [6, 7, 8, 9, 10]

    }

    @Test
    public void test2(){

        Predicate<Integer> noGreaterThan5 = x -> x > 5;

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> collect = list.stream()
                .filter(noGreaterThan5)
                .collect(Collectors.toList());

        System.out.println(collect); // [6, 7, 8, 9, 10]
    }



    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // multiple filters
        List<Integer> collect = list.stream()
                .filter(x -> x > 5 && x < 8).collect(Collectors.toList());

        System.out.println(collect);

    }

    @Test
    public void test4(){

        Predicate<Integer> noGreaterThan5 = x -> x > 5;
        Predicate<Integer> noLessThan8 = x -> x < 8;

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> collect = list.stream()
                .filter(noGreaterThan5.and(noLessThan8))
                .collect(Collectors.toList());

        System.out.println(collect);
    }


    @Test
    public void test5(){

        Predicate<String> lengthIs3 = x -> x.length() == 3;
        Predicate<String> startWithA = x -> x.startsWith("A");

        List<String> list = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");

        List<String> collect = list.stream()
                .filter(lengthIs3.or(startWithA))
                .collect(Collectors.toList());

        System.out.println(collect);
    }



    @Test
    public void test6(){

        Predicate<String> startWithA = x -> x.startsWith("A");

        List<String> list = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");

        List<String> collect = list.stream()
                .filter(startWithA.negate())
                .collect(Collectors.toList());

        System.out.println(collect);
    }

    @Test
    public void test7(){
        List<String> list = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");

        System.out.println(StringProcessor.filter(
                list, x -> x.startsWith("A")));                    // [A, AA, AAA]

        System.out.println(StringProcessor.filter(
                list, x -> x.startsWith("A") && x.length() == 3)); // [AAA]

    }



     static class StringProcessor {
     static List<String> filter(List<String> list, Predicate<String> predicate) {
        return list.stream().filter(predicate::test).collect(Collectors.toList());
    }


    }


    @Test
    public void test8(){
        Predicate<String> startWithA = x -> x.startsWith("a");

        // start with "a" or "m"
        boolean result = startWithA.or(x -> x.startsWith("m")).test("mkyong");
        System.out.println(result);     // true

        // !(start with "a" and length is 3)
        boolean result2 = startWithA.and(x -> x.length() == 3).negate().test("abc");
        System.out.println(result2);    // false

    }


    public class Hosting {

        private int Id;
        private String name;
        private String url;

        public Hosting(int id, String name, String url) {
            Id = id;
            this.name = name;
            this.url = url;
        }

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class HostingRespository {

        public static List<Hosting> filterHosting(List<Hosting> hosting,
                                                  Predicate<Hosting> predicate) {
            return hosting.stream()
                    .filter(predicate)
                    .collect(Collectors.toList());
        }
    }
    @Test
    public void test9(){

        Hosting h1 = new Hosting(1, "amazon", "aws.amazon.com");
        Hosting h2 = new Hosting(2, "linode", "linode.com");
        Hosting h3 = new Hosting(3, "liquidweb", "liquidweb.com");
        Hosting h4 = new Hosting(4, "google", "google.com");

        List<Hosting> list = Arrays.asList(new Hosting[]{h1, h2, h3, h4});

        List<Hosting> result = HostingRespository.filterHosting(list, x -> x.getName().startsWith("g"));
        System.out.println("result : " + result);  // google

        List<Hosting> result2 = HostingRespository.filterHosting(list, isDeveloperFriendly());
        System.out.println("result2 : " + result2); // linode

    }

    public static Predicate<Hosting> isDeveloperFriendly() {
        return n -> n.getName().equals("linode");

    }

    /**
     * result : [Hosting{Id=4, name='google', url='google.com'}]
     * result2 : [Hosting{Id=2, name='linode', url='linode.com'}]
     */

    @Test
    public void test0(){

                //Predicate<T>
        Integer age = 35;
        Predicate<Integer> predicate = (i) -> i >= 35;
        if (predicate.test(age)){
            System.out.println("你该退休了");
        } else {
            System.out.println("我觉得还OK啦");
        }
    }

    @Test
    public void test11(){

        // 1. 通过Collection系列集合的 stream() 方法或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2. 通过 Arrays 类中的静态方法 stream() 获取数组流
        String[] strArr = new String[10];
        Stream<String> stream1 = Arrays.stream(strArr);

        // 3. 通过 Stream 类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        // 4. 创建无限流，要有终止操作才有效果
        // （1）迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.forEach(System.out::println); // 不停打印，停不下来
        stream3.limit(10) // 中间操作
                .forEach(System.out::println); // 终止操作
        // （2）生成
        Stream.generate(() -> new Random().nextInt(32))
                .limit(32)
                .forEach(System.out::println);
    }

}
