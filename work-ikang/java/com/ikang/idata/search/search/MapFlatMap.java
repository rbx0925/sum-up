package com.ikang.idata.search.search;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="yanan.mu-ext@ikang.com">yanan.mu-ext</a>
 * @date 16/3/2022
 */
@ActiveProfiles(value = {"test"})
@SpringBootTest
public class MapFlatMap {


    public static void main(String[] args) {
        List<String[]> eggs = new ArrayList<>();

        // 第一箱鸡蛋
        eggs.add(new String[]{"鸡蛋_1", "鸡蛋_1", "鸡蛋_1", "鸡蛋_1", "鸡蛋_1"});
        // 第二箱鸡蛋
        eggs.add(new String[]{"鸡蛋_2", "鸡蛋_2", "鸡蛋_2", "鸡蛋_2", "鸡蛋_2"});

        // 自增生成组编号
         AtomicInteger group = new AtomicInteger(1);
        // 自增生成学生编号
         AtomicInteger student = new AtomicInteger(1);

        eggs.stream()
                  .map(x -> Arrays.stream(x)
                        .map(y -> y.replace("鸡", "煎")))
                  .forEach(x -> System.out.println("组" + (group.getAndIncrement()) + ":" + Arrays.toString(x.toArray())));
        /*
         控制台打印：------------
         组1:[煎蛋_1, 煎蛋_1, 煎蛋_1, 煎蛋_1, 煎蛋_1]
         组2:[煎蛋_2, 煎蛋_2, 煎蛋_2, 煎蛋_2, 煎蛋_2]
        */

        eggs.stream()
                    .flatMap(x -> Arrays.stream(x)
                            .map(y -> y.replace("鸡", "煎")))
                     .forEach(x -> System.out.println("学生" + student.getAndIncrement() + ":" + x));
        /*
43         控制台打印：------------
44         学生1:煎蛋_1
45         学生2:煎蛋_1
46         学生3:煎蛋_1
47         学生4:煎蛋_1
48         学生5:煎蛋_1
49         学生6:煎蛋_2
50         学生7:煎蛋_2
51         学生8:煎蛋_2
52         学生9:煎蛋_2
53         学生10:煎蛋_2
54          */
        
        
    }

    @org.junit.jupiter.api.Test
    public void StringTest1(){
        //创建集合
        HashMap<String,String> hashMap = new HashMap<>();
        //添加元素
        hashMap.put("杨绛","钱钟书");
        hashMap.put("林徽因","徐志摩");
        hashMap.put("陈红","陈凯歌");
        hashMap.put("袁咏仪","张智霖");
        hashMap.put("孙俪","邓超");
        hashMap.put("彭麻麻","习大大");
        hashMap.put("加油呀","最棒的");

        //遍历
        Set<String>  keys = hashMap.keySet();
        for (String key : keys) {
            String value = hashMap.get(key);
            System.out.println(key + "=======" + value);
        }

        /**
         * 结果
         *
         * 加油呀=======最棒的
         * 袁咏仪=======张智霖
         * 彭麻麻=======习大大
         * 陈红=======陈凯歌
         * 林徽因=======徐志摩
         * 孙俪=======邓超
         * 杨绛=======钱钟书
         */
    }

    @org.junit.jupiter.api.Test
    public void IntegerTest1(){
        //创建集合
        HashMap<Integer,String> hashMap = new HashMap<Integer,String>();
        //添加元素
        hashMap.put(1,"钱钟书");
        hashMap.put(2,"徐志摩");
        hashMap.put(3,"陈凯歌");
        hashMap.put(4,"张智霖");
        hashMap.put(5,"邓超");
        hashMap.put(0,"习大大");
        hashMap.put(9,"最棒的");
        hashMap.put(9,"加油呀");

        //遍历
        Set<Integer> keys = hashMap.keySet();
        for (Integer key : keys) {
            String value = hashMap.get(key);
            System.out.println(key + "=======" + value);
        }
        /**
         * 结果
         *
         * 0=======习大大
         * 1=======钱钟书
         * 2=======徐志摩
         * 3=======陈凯歌
         * 4=======张智霖
         * 5=======邓超
         * 9=======加油呀
         *
         * hashMap.put(9,"最棒的");
         * hashMap.put(9,"加油呀");
         * 被覆盖  显示最后一个
         *
         */
    }
    /**
     * 这是最常见的方法，并在大多数情况下更可取的。当你在循环中需要使用Map的键和值时，就可以使用这个方法
     * For-Each循环是Java5新引入的，所以只能在Java5以上的版本中使用。如果你遍历的map是null的话，For-Each循环会抛出NullPointerException异常，所以在遍历之前你应该判断是否为空引用。
     */
    //使用For-Each迭代entries
    @org.junit.jupiter.api.Test
    public void ForEachTest(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,100);
        map.put(2,200);
        map.put(3,300);
        map.put(4,400);
        map.put(5,500);
        map.put(6,600);
        map.put(7,700);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + "va  lue = " + entry.getValue() );
        }

        /**
         * 结果
         *
         * key = 1va  lue = 100
         * key = 2va  lue = 200
         * key = 3va  lue = 300
         * key = 4va  lue = 400
         * key = 5va  lue = 500
         * key = 6va  lue = 600
         * key = 7va  lue = 700
         */
    }


    //使用For-Each迭代keys和values
    //这个方法比entrySet迭代具有轻微的性能优势(大约快10%)并且代码更简洁
    @Test
    public void ForEachTest1(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(11,1100);
        map.put(22,2200);
        map.put(33,3300);
        map.put(44,4400);
        map.put(55,5500);
        map.put(66,6600);
        map.put(77,7700);

        //iterating over keys only
        for (Integer key : map.keySet()) {
            System.out.println("key = " + key);
        }

        /**
         * 结果
         *
         * key = 33
         * key = 66
         * key = 22
         * key = 55
         * key = 11
         * key = 44
         * key = 77
         */

        //iterating over value only
        for (Integer value : map.values()) {
            System.out.println("value = " + value);
        }

        /**
         * 结果
         *
         * value = 3300
         * value = 6600
         * value = 2200
         * value = 5500
         * value = 1100
         * value = 4400
         * value = 7700
         */
    }
}
