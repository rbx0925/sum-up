package com.ikang.idata.search.search;

import cn.hutool.core.convert.Convert;
import com.alibaba.csp.sentinel.util.function.Supplier;
import com.alibaba.csp.sentinel.util.function.Tuple2;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tuple2Test {

    @Test
    public void testSwap() {
        Tuple2<Integer,Integer> tupleOld = new Tuple2<Integer,Integer>(1, 2);
        Tuple2<Integer,Integer> tupleNew = tupleOld.swap();
        Assert.assertEquals(new Tuple2<Integer,Integer>(2, 1), tupleNew);
    }


    @Test
    public void test11() {
        System.out.println(Convert.numberToWord(10000));
        System.out.println(Convert.numberToSimple(10000));
    }


    @Test
    public void stream(){

        //Java8 Stream 流是不能被复用的，一旦你调用任何终端操作，流就会关闭：
        //对 stream 调用了 anyMatch 终端操作以后，流即关闭了，再调用 noneMatch 就会抛出异常：
        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        stream.anyMatch(s -> true);
        // ok
        stream.noneMatch(s -> true);
        // exception


    }

    @Test
    public void Supplier(){

        //为了克服这个限制，我们必须为我们想要执行的每个终端操作创建一个新的流链，
        //例如，我们可以通过 Supplier 来包装一下流，通过 get() 方法来构建一个新的 Stream 流，如下所示：
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok
    }


    @Test
    public void streamClass(){

        // 构建一个 Student 集合
        List<Student> students =
                Arrays.asList(
                        new Student(1,"Max", "男",18),
                        new Student(2,"Peter", "男",23),
                        new Student(3,"Pamela", "女",23),
                        new Student(4,"David", "女",12));

        List<Student> filtered =
                students
                        .stream()
                        // 构建流
                        .filter(p -> p.name.startsWith("P"))
                        // 过滤出名字以 P 开头的
                        .collect(Collectors.toList());
        // 生成一个新的 List

        System.out.println(filtered);
        // [Peter, Pamela]

        //如果说你需要构造一个 Set 集合，只需要使用Collectors.toSet()就可以了。


        System.out.println("================groupingBy=============");

        Map<Integer, List<Student>> personsByAge = students
                .stream()
                .collect(Collectors.groupingBy(p -> p.no));

        //Map<Integer, List<Student>> personsByAge1 = students
        //        .stream()
        //        .collect(Collectors.groupingBy(p -> (int)p.height)); // 以年龄为 key,进行分组

        personsByAge
                .forEach((no, p) -> System.out.format("no %s: %s\n", no, p));

        /**
         * 1,"Max","男",18
         * 2,"Peter","男",23
         * 3,"Pamela","女",23
         * 4,"David","女",12
         */

        System.out.println("================averHeights=============");

        Double averHeights = students
                .stream()
                .collect(Collectors.averagingInt(p -> (int) p.height));
        // 聚合出平均年龄

        System.out.println(averHeights);
        // 19.0


        System.out.println("================IntSummaryStatistics=============");

        IntSummaryStatistics heightSummary =
                students
                        .stream()
                        .collect(Collectors.summarizingInt(p -> (int)p.height));
        // 生成摘要统计
        System.out.println(heightSummary);

        /**
         * IntSummaryStatistics{count=4, sum=76, min=12, average=19.000000, max=23}
         */

        String phrase = students
                .stream()
                .filter(p -> p.height >= 18) // 过滤出年龄大于等于18的
                .map(p -> p.name) // 提取名字
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal height."));
        // 以 In Germany 开头，and 连接各元素，再以 are of legal height. 结束

        System.out.println(phrase);
        // In Germany Max and Peter and Pamela are of legal height.



        Map<Integer, String> map = students
                .stream()
                .collect(Collectors.toMap(
                        p -> (int)p.height,
                        p -> p.name,
                        (name1, name2) -> name1 + ";" + name2)); // 对于同样 key 的，将值拼接

        System.out.println(map);
      // {18=Max, 23=Peter;Pamela, 12=David}



        Collector<Student, StringJoiner, String> studentsNameCollector =
                Collector.of(
                        () -> new StringJoiner(" | "),
                        // supplier 供应器
                        (j, p) -> j.add(p.name.toUpperCase()),
                        // accumulator 累加器
                        (j1, j2) -> j1.merge(j2),
                        // combiner 组合器
                        StringJoiner::toString);
        // finisher 终止器

        String names = students
                .stream()
                .collect(studentsNameCollector);
        // 传入自定义的收集器

        System.out.println(names);
        // MAX | PETER | PAMELA | DAVID

    }



}
