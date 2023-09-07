package com.ikang.idata.search.search;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ikang.idata.search.search.aop.Employee;
import com.ikang.idata.search.search.common.EmployeeTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * @author rbx
 * @title
 * @Create 2023-05-16 13:35
 * @Description
 */
@Slf4j
public class JsonPathTest {

    final JSONObject jObject = JSON.parseObject("{\n" +
            "  \"store\": {\n" +
            "    \"book\":[\n" +
            "      { \"category\": \"reference\",\n" +
            "        \"author\": \"Nigel Rees\",\n" +
            "        \"title\": \"Sayings of the Century\",\n" +
            "        \"price\": 8.95\n" +
            "      },\n" +
            "      { \"category\": \"fiction\",\n" +
            "        \"author\": \"J. R. R. Tolkien\",\n" +
            "        \"title\": \"The Lord of the Rings\",\n" +
            "        \"isbn\": \"0-395-19395-8\",\n" +
            "        \"price\": 22.99\n" +
            "      }\n" +
            "    ],\n" +
            "    \"bicycle\": {\n" +
            "      \"color\": \"red\",\n" +
            "      \"price\": 19.95\n" +
            "    }\n" +
            "  }\n" +
            "}\n");

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

    String json = "{\"0\":[{\"name\":\"李星云\",\"age\":18,\"sex\":0,\"address\":\"渝州\",\"money\":1000},\n" +
            " {\"name\":\"袁天罡\",\"age\":99,\"sex\":0,\"address\":\"藏兵谷\",\"money\":100000},\n" +
            " {\"name\":\"张子凡\",\"age\":19,\"sex\":0,\"address\":\"天师府\",\"money\":900},\n" +
            " {\"name\":\"陆佑劫\",\"age\":45,\"sex\":0,\"address\":\"不良人\",\"money\":600},\n" +
            " {\"name\":\"张天师\",\"age\":48,\"sex\":0,\"address\":\"天师府\",\"money\":1100}],\n" +
            " \"1\":[{\"name\":\"陆林轩\",\"age\":16,\"sex\":1,\"address\":\"渝州\",\"money\":500},\n" +
            " {\"name\":\"姬如雪\",\"age\":17,\"sex\":1,\"address\":\"幻音坊\",\"money\":800},\n" +
            " {\"name\":\"蚩梦\",\"age\":18,\"sex\":1,\"address\":\"万毒窟\",\"money\":800}]}";

    @Test
    public void Test() throws JsonProcessingException {
        Map<Integer, List<EmployeeTest>> map = EmployeeTests().stream()
                .collect(Collectors.groupingBy(EmployeeTest::getSex));
        cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(map);
        System.out.println("jsonObject = " + jsonObject);
    }

    @Test
    public void Test1(){
        log.info("jPath:{}", jObject);
        //查看store下的bicycle的color属性：
        Object eval = JSONPath.eval(jObject, "$.store.bicycle.color");
        log.info("eval:{} " , eval);

        //输出book节点中包含的所有对象：
        Object eval1 = JSONPath.eval(jObject, "$.store.book[*]");
        log.info("eval:{} " , eval1);

        //输出book节点的第一个对象：
        Object eval2 = JSONPath.eval(jObject, "$.store.book[0]");
        log.info("eval:{}",eval2);

        //输出book节点中所有对象对应的属性title值：
        Object eval3 = JSONPath.eval(jObject, "$.store.book[*].title");
        log.info("eval:{}",eval3);

        //输出book节点中category为fiction的所有对象：
        Object eval4 = JSONPath.eval(jObject, "$.store.book[?(@.category=='fiction')]");
        log.info("eval:{}",eval4);

        //输出book节点中所有价格小于10的对象：
        Object eval5 = JSONPath.eval(jObject, "$.store.book[?(@.price<10)]");
        log.info("eval:{}",eval5);

        //输出book节点中所有含有isbn的对象：
        Object eval6 = JSONPath.eval(jObject, "$.store.book[?(@.isbn)]");
        log.info("eval:{}",eval6);
    }

    @Test
    public void Test2(){
        int a = 10;
        int b = 20;
        /*
            语法格式一:
            assert [boolean 表达式]
               - 如果[boolean表达式]为true，则程序继续执行。
               - 如果为false，则程序抛出AssertionError，并终止执行。
         */
        //assert a>b;

        /*
            语法格式二:
            assert [boolean 表达式 : 错误表达式 （日志）]
                - 如果[boolean表达式]为true，则程序继续执行。
                - 如果为false，则程序抛出java.lang.AssertionError，输出[错误信息]。
         */
        //assert a>b:"错误a不大于b";

        Map root = Collections.singletonMap("company",
                Collections.singletonMap("departs",
                        Arrays.asList(Collections.singletonMap("id", 1001),
                                Collections.singletonMap("id", 1002),
                                Collections.singletonMap("id", 1003))));
        log.info("root:{}",root);
        /*
         * 1.assertEquals(expected,actual)  和 assertNotEquals(expected,actual);
         * 比较实际值与预期值是否一致。如果一致，程序继续运行，否则抛出异常，会打印报错信息。常用断言方法，便于调试。
         */
        Assert.assertEquals(1,root.size());
        log.info("rootSize:{}",root.size());
        Assert.assertNotEquals(3,root.size());
        log.info("rootSize:{}",root.size());


        /*
         * 2.assertTrue(message,condition) 和 assertFalse(message,condition)
         * 如果条件的真假与预期相同，程序继续运行，否则抛出异常，不会打印报错信息。
         */
        Assert.assertTrue("结果为false打印报错信息", root.size()==1);
        Assert.assertFalse("结果为true打印报错信息",root.size() == 0);

        /*
         * 3.assertNull(message,object) 和 assertNotNull(message,object)
         * 判断一个对象是否为空，如果结果与预期相同，程序继续运行，否则抛出异常。
         */
        //Assert.assertNull(root.size());
        Assert.assertNotNull("不为null会报错",root.size());

        /*
         * 4.assertSame(expected,actual) 和 assertNotSame(expected,actual)
         * 判断预期的值和实际的值是否为同一个参数(即判断是否为相同的引用)，如果结果与预期相同，程序继续运行，否则抛出异常。
         * assertSame(expected,actual) 和 assertEquals(expected,actual)的区别；
         * assertSame(A,B)  ————————————> A==B
         * assertEquals(A,B)————————————>A.equals(B)
         */
        //Assert.assertSame(new Object(),new Object());
        Assert.assertNotSame(new Object(),new Object());

        /*
         * 5.fail(message)
         * “fail”断言能使测试立即失败，这种断言通常用于标记某个不应该被到达的分支。例如测试中某个代码块要try  catch，则在catch代码中加入fail(message)方法，否则代码直接进入catch块，无法判断测试结果。
         */
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
            Assert.fail(e.getMessage());
        }
    }


    @Test
    public void TestOptional(){
        log.info("-----------------------------创建Optional类-----------------------------------");
        /*
        创建Optional类对象的方法：
            Optional.of(T t) : 创建一个 Optional 实例，t必须非空；
            Optional.empty() : 创建一个空的 Optional 实例
            Optional.ofNullable(T t)：t可以为null
         */
        Optional<Object> optional = Optional.empty();
        log.info("Optional:{}", optional);

        // 依据一个非空值创建Optional
        Student student = new Student();
        Optional<Student> student1 = Optional.of(student);
        log.info("Student:{}", student1);

        // 可接受null的Optional
        student = null;
        Optional<Student> student2 = Optional.ofNullable(student);
        log.info("student2:{}",student2);


        /*
        判断Optional容器中是否包含对象：
            boolean isPresent() : 判断是否包含对象
            void ifPresent(Consumer<? super T> consumer) ：如果有值，就执行Consumer接口的实现代码，并且该值会作为参数传给它。
         */
        log.info("---------------------------判断Optional容器中是否包含对象-------------------------------------");
        Student student3 = new Student();
        Optional<Student> optional1 = Optional.ofNullable(student3);
        boolean present = optional1.isPresent();
        log.info("student3是否存在:{}",present);
        optional1.ifPresent(stu -> stu.setName("张三"));
        log.info("optional1:{}",optional1);

        /*
        获取Optional容器的对象：
            T get(): 如果调用对象包含值，返回该值，否则抛异常
            T orElse(T other) ：如果有值则将其返回，否则返回指定的other对象。
            T orElseGet(Supplier<? extends T> other) ：如果有值则将其返回，否则返回由Supplier接口实现提供的对象。
            T orElseThrow(Supplier<? extends X> exceptionSupplier) ：如果有值则将其返回，否则抛出由Supplier接口实现提供的异常。
         */
        log.info("--------------------------------获取Optional容器的对象--------------------------------");
        Student student4 = null;
        Optional<Student> opt2 = Optional.ofNullable(student4);
        // 使用get一定要注意，假如student对象为空，get是会报错的
        /*Student result = opt2.get();
        log.info("result:{}",result);*/

        // orElse 当student为空的时候,返回我们新建的这个对象,有点像三目运算的感觉
        Student result2 = opt2.orElse(new Student(1, "张三", "man", 180.88f));
        log.info("result2:{}",result2);

        // orElseGet就是当student为空的时候，返回通过Supplier供应商函数创建的对象
        Student result3 = opt2.orElseGet(() -> new Student(2, "李四", "women", 166.88f));
        log.info("result3:{}",result3);

        // orElseThrow就是当student为空的时候，可以抛出我们指定的异常
        /*Student result4 = opt2.orElseThrow(() -> new RuntimeException("自定义异常:{对象为空}"));
        log.info("result4:{}",result4);*/


        /*
        过滤：
        Optional<T> filter(Predicate<? super <T> predicate)：如果值存在，并且这个值匹配给定的 predicate，返回一个Optional用以描述这个值，否则返回一个空的Optional。
         */
        log.info("--------------------------------过滤--------------------------------");
        Student student5 = new Student(3, "王五", "man", 177.88f);
        Optional<Student> opt3 = Optional.ofNullable(student5);
        Optional<Student> result = opt3.filter(p -> "王五".equals(p.getName()));
        result.ifPresent(x-> System.out.println("存在"));
        log.info("result5:{}",result);

        /*
        映射:
        <U>Optional<U> map(Function<? super T,? extends U> mapper)：如果有值，则对其执行调用映射函数得到返回值。如果返回值不为 null，则创建包含映射返回值的Optional作为map方法返回值，否则返回空Optional。
        <U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)：如果值存在，就对该值执行提供的mapping函数调用，返回一个Optional类型的值，否则就返回一个空的Optional对象
         */
        log.info("--------------------------------映射--------------------------------");
        Student student6 = new Student(4, "赵六", "women", 188.88f);
        Optional<Student> opt4 = Optional.ofNullable(student6);
        opt4.map(p -> {
            p.setName("new-"+p.getName());
            return p;
        });
        log.info("opt4:{}",opt4);

        String result4 = opt4.flatMap(p -> Optional.of(p.getName())).get();
        System.out.println(result4);
    }


}
