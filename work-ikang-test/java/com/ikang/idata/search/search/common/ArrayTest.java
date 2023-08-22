package com.ikang.idata.search.search.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/7/13
 */
@Slf4j
public class ArrayTest {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = null;
        System.out.println("结果1"+ CollectionUtils.isEmpty(list1));
        System.out.println("结果2"+ CollectionUtils.isEmpty(list2));
        System.out.println("结果3"+ list1.isEmpty());
        System.out.println("结果4"+ list2.isEmpty());
        /**
         * 结果1true
         * 结果2true
         * Exception in thread "main" java.lang.NullPointerException
         * 结果3true
         * 	at com.ikang.op2c.test.ArrayTest.main(ArrayTest.java:25)
         */

    }

    @Test
    public void isEmpty(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ list.isEmpty());
        list = null;
        System.out.println("判断list是否为null:"+ list.isEmpty());
        /**
         * 判断list是否为空:true
         *
         * java.lang.NullPointerException
         * 	at com.ikang.op2c.test.ArrayTest.isEmpty(ArrayTest.java:41)
         * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
         * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
         * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
         * 	at java.lang.reflect.Method.invoke(Method.java:498)
         * 	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
         * 	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
         * 	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
         * 	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
         * 	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
         * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
         * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
         * 	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
         * 	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
         * 	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
         * 	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
         * 	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
         * 	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
         * 	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
         * 	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
         * 	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
         * 	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
         * 	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
         */
    }

    @Test
    public void nullTest(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ (list == null));
        list = null;
        System.out.println("判断list是否为null:"+ (list == null));
        /**
         * 判断list是否为空:false
         * 判断list是否为null:true
         */
    }

    @Test
    public void CollectionUtilsTest(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ CollectionUtils.isEmpty(list));
        list = null;
        System.out.println("判断list是否为null:"+ CollectionUtils.isEmpty(list));
        /**
         * 判断list是否为空:true
         * 判断list是否为null:true
         */
    }

    @Test
    public void StringUtilsTest(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ StringUtils.isEmpty(list));
        list = null;
        System.out.println("判断list是否为null:"+ StringUtils.isEmpty(list));
        /**
         * 判断list是否为空:false
         * 判断list是否为null:true
         */
    }

    @Test
    public void ArrayListTest(){
        List<String> list = new ArrayList<>();
        if(list != null && !list.isEmpty()){
            System.out.println("操作list数据");

        }
    }

    @Test
    public void CollectionUtilsTest1(){
        List<String> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            System.out.println("操作list数据");
        }
    }

    @Test
    public void MapTest1(){
        Map map = new HashMap<String ,String>();
        System.out.println("判断map是否为空:"+ map.isEmpty());
        map = null;
        System.out.println("判断map是否为null:"+ map.isEmpty());
        /**
         * 判断map是否为空:true
         *
         * java.lang.NullPointerException
         * 	at com.ikang.op2c.test.ArrayTest.MapTest1(ArrayTest.java:130)
         * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
         * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
         * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
         * 	at java.lang.reflect.Method.invoke(Method.java:498)
         * 	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
         * 	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
         * 	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
         * 	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
         * 	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
         * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
         * 	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
         * 	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
         * 	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
         * 	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
         * 	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
         * 	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
         * 	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
         * 	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
         * 	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
         * 	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
         * 	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
         * 	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
         */
    }

    @Test
    public void nullTest1(){
        Map map = new HashMap<String ,String>();
        System.out.println("判断map是否为空:"+ (map == null));
        map = null;
        System.out.println("判断map是否为null:"+ (map == null));
        /**
         * 判断map是否为空:false
         * 判断map是否为null:true
         */
    }

    @Test
    public void MapUtilsTest(){
        Map map = new HashMap<String ,String>();
        System.out.println("判断map是否为空:"+ MapUtils.isEmpty(map));
        map = null;
        System.out.println("判断map是否为null:"+ MapUtils.isEmpty(map));
        /**
         * 判断map是否为空:true
         * 判断map是否为null:true
         */
    }
}

