package com.ikang.idata.search.search.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/6/30
 */
public class ThreadDemo extends Thread {


        private Thread t;
        private String threadName;

        ThreadDemo( String name) {
            threadName = name;
            System.out.println("Creating " +  threadName );
        }

        public void run() {
            System.out.println("Running " +  threadName );
            try {
                for(int i = 4; i > 0; i--) {
                    System.out.println("Thread: " + threadName + ", " + i);
                    // 让线程睡眠一会
                    Thread.sleep(50);
                }
            }catch (InterruptedException e) {
                System.out.println("Thread " +  threadName + " interrupted.");
            }
            System.out.println("Thread " +  threadName + " exiting.");
        }

        public void start () {
            System.out.println("Starting " +  threadName );
            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }



        @Test
    public void ThreadDemo() {


            ThreadDemo T1 = new ThreadDemo( "Thread-1");
            T1.start();

            ThreadDemo T2 = new ThreadDemo( "Thread-2");
            T2.start();
        }




        @Test
    public void threadTest(){

            List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
            // 获取对应的平方数
//        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
            List<Integer> squaresList = numbers.stream()
                    .map(i -> i * i)
                    .sorted((x, y) -> y - x)
                    .collect(Collectors.toList());
//        squaresList.forEach(System.out::println);
            squaresList.forEach(num -> {
                num++;
                System.out.println(num);
            });

            List<String> strList = Arrays.asList("a", "ba", "bb", "abc", "cbb", "bba", "cab");
            Map<Integer, String> strMap = new HashMap<Integer, String>();

            strMap = strList.stream()
                    .collect( Collectors.toMap( str -> strList.indexOf(str), str -> str ) );

            strMap.forEach((key, value) -> {
                System.out.println(key+"::"+value);
            });
        }
    /**
     * 50
     * 26
     * 10
     * 10
     * 10
     * 5
     * 5
     * 0::a
     * 1::ba
     * 2::bb
     * 3::abc
     * 4::cbb
     * 5::bba
     * 6::cab
     */

}

