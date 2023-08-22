package com.ikang.idata.search.search.entity;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.ikang.idata.search.search.common.EmployeeTest;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2023/1/11
 */
public class StreamDemo {

    public static void main(String[] args) {
    }
    public static List<EmployeeTest> EmployeeTests(){
        List<EmployeeTest> list = Arrays.asList(
                new EmployeeTest("李星云", 18, 0, "渝州",new BigDecimal(1000)),
                new EmployeeTest("陆林轩", 16, 1, "渝州",new BigDecimal(500)),
                new EmployeeTest("姬如雪", 17, 1, "幻音坊",new BigDecimal(800)),
                new EmployeeTest("袁天罡", 99, 0, "藏兵谷",new BigDecimal(100000)),
                new EmployeeTest("张子凡", 19, 0, "天师府",new BigDecimal(900)),
                new EmployeeTest("陆佑劫", 45, 0, "不良人",new BigDecimal(600)),
                new EmployeeTest("张天师", 48, 0, "天师府",new BigDecimal(1100)),
                new EmployeeTest("蚩梦", 18, 1, "万毒窟",new BigDecimal(800))
        );
        return list;
    }



    /*filter过滤(T-> boolean)*/
    public static void filter(){
        List<EmployeeTest> list = EmployeeTests();
        List<EmployeeTest> newlist = list.stream().filter(EmployeeTest -> EmployeeTest.getAge() > 20)
                .collect(Collectors.toList());
        for (EmployeeTest EmployeeTest : newlist) {
            System.out.println(EmployeeTest.getName()+" --> "+ EmployeeTest.getAge());
        }
    }
//    ---结果---
//    袁天罡 --> 99
//    陆佑劫 --> 45
//    张天师 --> 48

    /*distinct 去重*/
    //数据源中复制new EmployeeTest("李星云", 18, 0, "渝州",new BigDecimal(1000)) 并粘贴两个
    public static void distinct(){
        List<EmployeeTest> list = EmployeeTests();
        List<EmployeeTest> newlist = list.stream().distinct().collect(Collectors.toList());
        for (EmployeeTest EmployeeTest : newlist) {
            System.out.println(EmployeeTest.getName()+" --> "+ EmployeeTest.getAge());
        }
    }
//    ---结果---
//    李星云 --> 18
//    陆林轩 --> 16
//    姬如雪 --> 17
//    袁天罡 --> 99
//    张子凡 --> 19
//    陆佑劫 --> 45
//    张天师 --> 48
//    蚩梦 --> 18

    /*sorted排序*/
    public static void sorted(){
        List<EmployeeTest> list = EmployeeTests();
        List<EmployeeTest> newlist = list.stream()
                .sorted(Comparator.comparingInt(EmployeeTest::getAge))
                .collect(Collectors.toList());
        for (EmployeeTest EmployeeTest : newlist) {
            System.out.println(EmployeeTest.getName()+" --> "+ EmployeeTest.getAge());
        }
    }
//    ---结果---
//    陆林轩 --> 16
//    姬如雪 --> 17
//    李星云 --> 18
//    蚩梦 --> 18
//    张子凡 --> 19
//    陆佑劫 --> 45
//    张天师 --> 48
//    袁天罡 --> 99

    /*limit返回前n个元素*/
    public static void limit(){
        List<EmployeeTest> list = EmployeeTests();
        List<EmployeeTest> newlist = list.stream()
                .sorted(Comparator.comparingInt(EmployeeTest::getAge))
                .limit(2)
                .collect(Collectors.toList());
        for (EmployeeTest EmployeeTest : newlist) {
            System.out.println(EmployeeTest.getName()+" --> "+ EmployeeTest.getAge());
        }
    }
//    ---结果---
//    陆林轩 --> 16
//    姬如雪 --> 17

    /*skip去除前n个元素*/
    public static void skip(){
        List<EmployeeTest> list = EmployeeTests();
        List<EmployeeTest> newlist = list.stream()
                .sorted(Comparator.comparingInt(EmployeeTest::getAge))
                .skip(2)
                .collect(Collectors.toList());
        for (EmployeeTest EmployeeTest : newlist) {
            System.out.println(EmployeeTest.getName()+" --> "+ EmployeeTest.getAge());
        }
    }
//    ---结果---
//    李星云 --> 18
//    蚩梦 --> 18
//    张子凡 --> 19
//    陆佑劫 --> 45
//    张天师 --> 48
//    袁天罡 --> 99

    /*map(T->R)*/
    public static void map(){
        List<EmployeeTest> list = EmployeeTests();
        List<String> newlist = list.stream()
                .map(EmployeeTest::getName).distinct().collect(Collectors.toList());
        for (String add : newlist) {
            System.out.println(add);
        }
    }
//    ---结果---
//    李星云
//            陆林轩
//    姬如雪
//            袁天罡
//    张子凡
//            陆佑劫
//    张天师
//            蚩梦

    /*flatMap(T -> Stream<R>)*/
    public static void flatmap(){
        List<String> flatmap = new ArrayList<>();
        flatmap.add("常宣灵,常昊灵");
        flatmap.add("孟婆,判官红,判官蓝");
        /*
            这里原集合中的数据由逗号分割，使用split进行拆分后，得到的是Stream<String[]>，
            字符串数组组成的流，要使用flatMap的Arrays::stream
            将Stream<String[]>转为Stream<String>,然后把流相连接
        */
        flatmap = flatmap.stream()
                .map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        for (String name : flatmap) {
            System.out.println(name);
        }
    }
//    ---结果---
//    常宣灵
//            常昊灵
//    孟婆
//            判官红
//    判官蓝

    /*allMatch（T->boolean）检测是否全部满足参数行为*/
    public static void allMatch(){
        List<EmployeeTest> list = EmployeeTests();
        boolean flag = list.stream()
                .allMatch(EmployeeTest -> EmployeeTest.getAge() >= 17);
        System.out.println(flag);
    }
//	---结果---
//            false

    /*anyMatch（T->boolean）检测是否有任意元素满足给定的条件*/
    public static void anyMatch(){
        List<EmployeeTest> list = EmployeeTests();
        boolean flag = list.stream()
                .anyMatch(EmployeeTest -> EmployeeTest.getSex() == 1);
        System.out.println(flag);
    }
//	---结果---
//            true

    /*noneMatchT->boolean）流中是否有元素匹配给定的 T -> boolean条件*/
    public static void noneMatch(){
        List<EmployeeTest> list = EmployeeTests();
        boolean flag = list.stream()
                .noneMatch(EmployeeTest -> EmployeeTest.getAddress().contains("郑州"));
        System.out.println(flag);
    }
//	---结果---
//            true

    /*findFirst( ):找到第一个元素*/
    public static void findfirst(){
        List<EmployeeTest> list = EmployeeTests();
        Optional<EmployeeTest> optionalEmployeeTest = list.stream()
                .sorted(Comparator.comparingInt(EmployeeTest::getAge))
                .findFirst();
        System.out.println(optionalEmployeeTest.toString());
    }
    //	---结果---
//    Optional[EmployeeTest{name='陆林轩', age=16, sex=1, money=500, address='渝州'}]
    /*findAny( ):找到任意一个元素*/
    public static void findAny(){
        List<EmployeeTest> list = EmployeeTests();
//        Optional<EmployeeTest> optionalEmployeeTest = list.stream()
        // .findAny();
        Optional<EmployeeTest> optionalEmployeeTest = list.stream()
                .findAny();
        System.out.println(optionalEmployeeTest.toString());
    }
//   ---结果---
//    Optional[EmployeeTest{name='李星云', age=18, sex=0, money=1000, address='渝州'}]


    /*计算总数*/
    public static void count(){
        List<EmployeeTest> list = EmployeeTests();
        long count = list.stream().count();
        System.out.println(count);
    }
//    ---结果---
//            8

    /*最大值最小值*/
    public static void max_min(){
        List<EmployeeTest> list = EmployeeTests();
        Optional<EmployeeTest> max = list.stream()
                .collect(
                        Collectors.maxBy(
                                Comparator.comparing(EmployeeTest::getAge)
                        )
                );
        Optional<EmployeeTest> min = list.stream()
                .collect(
                        Collectors.minBy(
                                Comparator.comparing(EmployeeTest::getAge)
                        )
                );
        System.out.println("max--> " + max+"  min--> "+ min);
    }
//   ---结果---
//    max--> Optional[EmployeeTest{name='袁天罡', age=99, sex=0, money=100000, address='藏兵谷'}]  min--> Optional[EmployeeTest{name='陆林轩', age=16, sex=1, money=500, address='渝州'}]

    /*求和_平均值*/
    public static void sum_avg(){
        List<EmployeeTest>list = EmployeeTests();
        int totalAge = list.stream()
                .collect(Collectors.summingInt(EmployeeTest::getAge));
        System.out.println("totalAge--> "+ totalAge);

        /*获得列表对象金额， 使用reduce聚合函数,实现累加器*/
        BigDecimal totalMpney = list.stream()
                .map(EmployeeTest::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("totalMpney--> " + totalMpney);

        double avgAge = list.stream()
                .collect(Collectors.averagingInt(EmployeeTest::getAge));
        System.out.println("avgAge--> " + avgAge);
    }
//   ---结果---
//    totalAge--> 280
//    totalMpney--> 105700
//    avgAge--> 35.0

    /*一次性得到元素的个数、总和、最大值、最小值*/
    public static void allVlaue(){
        List<EmployeeTest> list = EmployeeTests();
        IntSummaryStatistics statistics = list.stream()
                .collect(Collectors.summarizingInt(EmployeeTest::getAge));
        System.out.println(statistics);
    }
//   ---结果---
//    IntSummaryStatistics{count=8, sum=280, min=16, average=35.000000, max=99}

    /*拼接*/
    public static void join(){
        List<EmployeeTest> list = EmployeeTests();
        String names = list.stream()
                .map(EmployeeTest::getName)
                .collect(Collectors.joining(", "));
        System.out.println(names);
    }
//   ---结果---
//    李星云, 陆林轩, 姬如雪, 袁天罡, 张子凡, 陆佑劫, 张天师, 蚩梦

    /*分组*/
    public static void group(){
        Map<Integer, List<EmployeeTest>> map = EmployeeTests().stream()
                .collect(Collectors.groupingBy(EmployeeTest::getSex));
        System.out.println(new Gson().toJson(map));
        System.out.println();
        Map<Integer, Map<Integer,List<EmployeeTest>>> map2 = EmployeeTests().stream()
                .collect(Collectors.groupingBy(EmployeeTest::getSex,
                        Collectors.groupingBy(EmployeeTest::getAge)));
        System.out.println(new Gson().toJson(map2));
    }
//   ---结果---
//    {"0":[{"name":"李星云","age":18,"sex":0,"address":"渝州","money":1000},
// {"name":"袁天罡","age":99,"sex":0,"address":"藏兵谷","money":100000},
// {"name":"张子凡","age":19,"sex":0,"address":"天师府","money":900},
// {"name":"陆佑劫","age":45,"sex":0,"address":"不良人","money":600},
// {"name":"张天师","age":48,"sex":0,"address":"天师府","money":1100}],
// "1":[{"name":"陆林轩","age":16,"sex":1,"address":"渝州","money":500},
// {"name":"姬如雪","age":17,"sex":1,"address":"幻音坊","money":800},
// {"name":"蚩梦","age":18,"sex":1,"address":"万毒窟","money":800}]}
//
//    {"0":{"48":[{"name":"张天师","age":48,"sex":0,"address":"天师府","money":1100}],
// "18":[{"name":"李星云","age":18,"sex":0,"address":"渝州","money":1000}],
// "19":[{"name":"张子凡","age":19,"sex":0,"address":"天师府","money":900}],
// "99":[{"name":"袁天罡","age":99,"sex":0,"address":"藏兵谷","money":100000}],
// "45":[{"name":"陆佑劫","age":45,"sex":0,"address":"不良人","money":600}]},
// "1":{"16":[{"name":"陆林轩","age":16,"sex":1,"address":"渝州","money":500}],
// "17":[{"name":"姬如雪","age":17,"sex":1,"address":"幻音坊","money":800}],
// "18":[{"name":"蚩梦","age":18,"sex":1,"address":"万毒窟","money":800}]}}

    /*分组合计*/
    public static void groupCount(){
        Map<Integer, Long> num = EmployeeTests().stream()
                .collect(Collectors.groupingBy(EmployeeTest::getSex, Collectors.counting()));
        System.out.println(num);


        Map<Integer, Long> num2 = EmployeeTests().stream()
                .filter(EmployeeTest -> EmployeeTest.getAge()>=18)
                .collect(Collectors.groupingBy(EmployeeTest::getSex, Collectors.counting()));
        System.out.println(num2);
    }
}
