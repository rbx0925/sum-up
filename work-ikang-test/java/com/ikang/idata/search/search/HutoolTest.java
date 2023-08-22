package com.ikang.idata.search.search;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Rot;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.ikang.idata.search.search.entity.UserInfo;
import com.ikang.idata.search.search.entity.UserInfoDTO;
import com.ikang.idata.search.search.entity.vo.RegisterDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.ikang.idata.common.consts.MagicConst.EXPORT_EXCEL;
import static com.ikang.idata.search.search.Java8.IsContinuous;
import static java.util.stream.Collectors.toList;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description Hutool
 * @date 2022/3/8 15:36
 */
public class HutoolTest {


    @Test
    public void test() {
        //季度开始时间
        System.out.println(DateUtil.beginOfQuarter(new Date()));
        System.out.println(DateUtil.beginOfMonth(new Date()));
        System.out.println("---------------------------------");
        System.out.println(DateUtil.endOfMonth(new Date()));
        System.out.println(DateUtil.endOfQuarter(new Date()));
    }

    @Test
    public void testBeanUtil1() {
        UserInfoDTO infoDTO = new UserInfoDTO();
        infoDTO.setAge("10");
        infoDTO.setMailbox("18233197071");
        infoDTO.setName("张三");
        infoDTO.setPassword("----222-2");
        UserInfo userInfo = BeanUtil.toBean(infoDTO, UserInfo.class);
        System.out.println(userInfo);
    }


    @Test
    public void testToBeanList() {
        List<UserInfoDTO> infoDTOS = IntStream.range(0, 1_000).mapToObj(operand -> {
            UserInfoDTO infoDTO = new UserInfoDTO();
            infoDTO.setAge("10");
            infoDTO.setMailbox("18233197071");
            infoDTO.setName("张三");
            infoDTO.setPassword("----222-2");
            return infoDTO;
        }).collect(Collectors.toList());

        Instant start = Instant.now();
        List<UserInfo> userInfos = toBeanList(infoDTOS, UserInfo.class);
        System.out.println(Thread.currentThread().getName());
        System.out.println(Duration.between(start, Instant.now()).toMillis());
    }

    @Test
    public void testcopyToList() {
        List<UserInfoDTO> infoDTOS = IntStream.range(0, 1_000).mapToObj(operand -> {
            UserInfoDTO infoDTO = new UserInfoDTO();
            infoDTO.setAge("10");
            infoDTO.setMailbox("18233197071");
            infoDTO.setName("张三");
            infoDTO.setPassword("----222-2");
            return infoDTO;
        }).collect(Collectors.toList());

        Instant start = Instant.now();
        List<UserInfo> userInfos = BeanUtil.copyToList(infoDTOS, UserInfo.class);
        System.out.println(Thread.currentThread().getName());
        System.out.println(Duration.between(start, Instant.now()).toMillis());
    }


    @Test
    public void test1000() {
        IntStream.range(0, 1_000).forEach(value -> testcopyToList());
        IntStream.range(0, 1_000).forEach(value -> testToBeanList());


        System.out.println("============================================");
    }

    /**
     * 获取属性描述信息,无法获取注解
     * java.beans.PropertyDescriptor[name=age; propertyType=class java.lang.String; readMethod=public java.lang.String com.ikang.idata.search.search.entity.UserInfo.getAge(); writeMethod=public void com.ikang.idata.search.search.entity.UserInfo.setAge(java.lang.String)]
     * java.beans.PropertyDescriptor[name=id; propertyType=class java.lang.Long; readMethod=public java.lang.Long com.ikang.idata.search.search.entity.UserInfo.getId(); writeMethod=public void com.ikang.idata.search.search.entity.UserInfo.setId(java.lang.Long)]
     * java.beans.PropertyDescriptor[name=mailbox; propertyType=class java.lang.String; readMethod=public java.lang.String com.ikang.idata.search.search.entity.UserInfo.getMailbox(); writeMethod=public void com.ikang.idata.search.search.entity.UserInfo.setMailbox(java.lang.String)]
     * java.beans.PropertyDescriptor[name=name; propertyType=class java.lang.String; readMethod=public java.lang.String com.ikang.idata.search.search.entity.UserInfo.getName(); writeMethod=public void com.ikang.idata.search.search.entity.UserInfo.setName(java.lang.String)]
     * java.beans.PropertyDescriptor[name=password; propertyType=class java.lang.String; readMethod=public java.lang.String com.ikang.idata.search.search.entity.UserInfo.getPassword(); writeMethod=public void com.ikang.idata.search.search.entity.UserInfo.setPassword(java.lang.String)]
     * java.beans.PropertyDescriptor[name=phoneNumber; propertyType=class java.lang.String; readMethod=public java.lang.String com.ikang.idata.search.search.entity.UserInfo.getPhoneNumber(); writeMethod=public void com.ikang.idata.search.search.entity.UserInfo.setPhoneNumber(java.lang.String)]
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/6/28 15:39
     */
    @Test
    public void testBean() {
        Stream.of(BeanUtil.getPropertyDescriptors(UserInfo.class)).forEach(System.out::println);
    }

    /**
     * 获取类属性值
     */
    @Test
    public void testBeanUtil() {
        UserInfo info = new UserInfo();
        info.setAge("10");
        info.setMailbox("18233366654");
        info.setPassword("123123");
        Object age = BeanUtil.getFieldValue(info, "age");
        System.out.println(age);
        BeanUtil.setFieldValue(info,"age","20");
        System.out.println(info);
    }


    public <T, E> List<T> toBeanList(Collection<E> sourceList, Class<T> destinationClass) {
        if (CollUtil.isEmpty(sourceList) || Objects.isNull(destinationClass)) {
            return Collections.emptyList();
        }
        return sourceList.parallelStream()
                .filter(Objects::nonNull)
                .map(e -> BeanUtil.toBean(e, destinationClass))
                .collect(Collectors.toList());
    }


    @Test
    public void testCon() {
        System.out.println("exportExcel/annotation".contains(EXPORT_EXCEL));
        System.out.println("exportExce".contains(EXPORT_EXCEL));
    }


    @Test
    public void testOn() {
        //原始加密
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJsb2dpbklkIjo1NCwicm4iOiJGcHY5T2Rsb1lSOEZOWjU0U1lzYTByYTE2d3Q1MzdtMSIsInVzZXJuYW1lIjoiZmVpLm1hb0Bpa2FuZy5jb20iLCJyZWFsbmFtZSI6Iuavm-mjniIsInVzZXJwaG9uZSI6IjE4MjMzMTk3MDc1In0.nJYk3uUeT0LQokOvDEf5Zt1u82grCZc_nsSG8c0IShc";
        System.out.println(token);
        String enCode = Rot.encode13(token);
        System.out.println(enCode);
        String decode13 = Rot.decode13(enCode);
        System.out.println(decode13);
        System.out.println(decode13.equals(token));

    }


    @Test
    public void testContinuous() {
        IntStream intStream = IntStream.of(2020, 2018, 2017).sorted();
//        intStream.forEach(System.out::println);
        System.out.println(IsContinuous(intStream.toArray()));
    }

    @Test
    public void testSort() {
        ArrayList<String> strings = CollUtil.newArrayList("2017-10", "2020-10", "2018-04");
        System.out.println(strings.stream().sorted().collect(Collectors.joining(",")));
    }

    @Test
    public void testPractice() {
        //左闭右开
        IntStream tenStream = IntStream.range(0, 10);
        IntStream hundredStream = IntStream.range(10, 100);
        IntStream stream = IntStream.concat(tenStream, hundredStream);
        stream.forEach(System.out::println);

        //peek 主要为调试所用
        List<Integer> result = Stream.of(2, 3, 4, 5)
                .peek(x -> System.out.println("taking from stream: " + x)).map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x)).filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x)).limit(3)
                .peek(x -> System.out.println("after limit: " + x)).collect(toList());

        //
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        numbers.stream()
                .map(x -> x + 17)
                .filter(x -> x % 2 == 0)
                .limit(3)
                .forEach(System.out::println);

        //
        String[] col = new String[]{"a", "b", "c", "d", "e"};
        List<String> colList = CollUtil.newArrayList(col);

        String str = CollUtil.join(colList, "#"); //str -> a#b#c#d#e
        System.out.println(str);

        //Integer比较器
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        //新建三个列表，CollUtil.newArrayList方法表示新建ArrayList并填充元素
        List<Integer> list1 = CollUtil.newArrayList(1, 2, 3);
        List<Integer> list2 = CollUtil.newArrayList(4, 5, 6);
        List<Integer> list3 = CollUtil.newArrayList(7, 8, 9);

        //参数表示把list1,list2,list3合并并按照从小到大排序后，取0~2个（包括第0个，不包括第2个），结果是[1,2]
        @SuppressWarnings("unchecked")
        List<Integer> result1 = CollUtil.sortPageAll(0, 2, comparator, list1, list2, list3);
        System.out.println(result1);     //输出 [1,2]


        Collection<String> keys = CollUtil.newArrayList("a", "b", "c", "d");
        Collection<Integer> values = CollUtil.newArrayList(1, 2, 3, 4);

        // {a=1,b=2,c=3,d=4}
        Map<String, Integer> map = CollUtil.zip(keys, values);
        System.out.println(keys);
        System.out.println(values);
        System.out.println(map);
    }

    @Test
    public void testTree() {


        String text = "Hello World!";

        // ...././.-../.-../---/-...../.--/---/.-./.-../-../-.-.--/
        Dict dict = Dict.create()
                .set("key1", 1)//int
                .set("key2", 1000L)//long
                .set("key3", DateTime.now());//Date
        System.out.println(
                dict
        );
        Long v2 = dict.getLong("key2");//1000
        System.out.println(v2);

        int c = RandomUtil.randomInt(10, 100);
        byte[] c1 = RandomUtil.randomBytes(10);
        System.out.println(Arrays.toString(c1));

        Set<Integer> set = RandomUtil.randomEleSet(CollUtil.newArrayList(1, 2, 3, 4, 5, 6), 2);
        set.forEach(System.out::println);

    }

    @Test
    public void testStringChar() {
        String testStr = "charsdeceshi";
        testStr.chars().forEach(System.out::println);
        System.out.println("**********************************************");
        //look 我们想要把字符串变成单个的时候 我们尽量使用mapToObj 这样比较方便一些
        testStr.chars().mapToObj(value -> (char) value)
                .peek(character -> System.out.println(character.getClass().getSimpleName()))
                .forEach(System.out::println);
        System.out.println("**********************************************");
        //map 是无效的
        testStr.chars().map(operand -> (char) operand)
                .forEach(System.out::println);
    }

    @Test
    public void testSocialCredit() {
        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        //生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(simpleUUID);

        //生成类似：5b9e306a4df4f8c54a39fb0c
        String id = ObjectId.next();
        System.out.println(id);
        //方法2：从Hutool-4.1.14开始提供
        String id2 = ObjectId.next();
        System.out.println(id2);

        //参数1为终端ID
        //参数2为数据中心ID
        Snowflake snowflake = new Snowflake(1, 1);
        long snowflakeId = snowflake.nextId();
        System.out.println(snowflakeId);
    }


//import cn.hutool.core.util.NumberUtil;
//import cn.hutool.core.util.RandomUtil;
//import com.google.common.primitives.Ints;
//import com.ikang.idata.common.utils.AesUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
//import org.junit.Test;
//import org.springframework.util.StringUtils;
//
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.math.BigInteger;
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.nio.charset.StandardCharsets;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.function.BiFunction;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;

    /**
     * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
     * @description ${description}
     * @date 2022/7/13
     */
//    @Slf4j
//    public class ArrayTest {

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

        @org.junit.Test
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

        @org.junit.Test
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

        @org.junit.Test
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

        @org.junit.Test
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

        @org.junit.Test
        public void ArrayListTest(){
            List<String> list = new ArrayList<>();
            if(list != null && !list.isEmpty()){
                System.out.println("操作list数据");

            }
        }

        @org.junit.Test
        public void CollectionUtilsTest1(){
            List<String> list = new ArrayList<>();
            if(!CollectionUtils.isEmpty(list)){
                System.out.println("操作list数据");
            }
        }

        @org.junit.Test
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

        @org.junit.Test
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

        @org.junit.Test
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

        @org.junit.Test
        public void CollectionUtilsTest2(){
            Map map1 = new HashMap<String ,String>();
            System.out.println("判断map是否为空:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
            map1 = null;
            System.out.println("判断map是否为null:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
            /**
             * 判断map是否为空:true
             * 判断map是否为null:true
             */
        }

        @org.junit.Test
        public void Test(){
            Map map1 = new HashMap<String ,String>();
            System.out.println("判断map是否为空:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
            map1 = null;
            System.out.println("判断map是否为null:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
            /**
             * 判断map是否为空:true
             * 判断map是否为null:true
             */
        }

    @Test
    public void testGuava() {
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("a", 1);
        multimap.put("a", 2);
        multimap.put("a", 4);
        multimap.put("b", 3);
        multimap.put("c", 5);

        System.out.println(multimap.keys());//[a x 3, b, c]
        System.out.println(multimap.get("a"));//[1 ,2, 4]
        System.out.println(multimap.get("b"));//[3]
        System.out.println(multimap.get("c"));//[5]
        System.out.println(multimap.get("d"));//[]

        System.out.println(multimap.asMap());//{a=[1, 2, 4], b=[3], c=[5]}
    }

    @Test
    public void testStr() {

        String s = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");
        System.out.println(s);
        String noControl = CharMatcher.javaIsoControl().removeFrom("移除control字符"); //移除control字符
        System.out.println(noControl);
        String theDigits = CharMatcher.digit().retainFrom("control123123"); //只保留数字字符
        System.out.println(theDigits);
        String spaced = CharMatcher.whitespace().trimAndCollapseFrom("    这里是   空格     ", ' ');//去除两端的空格，并把中间的连续空格替换成单个空格
        System.out.println(spaced);
        String noDigits = CharMatcher.digit().replaceFrom("中间是10000数字", "*"); //用*号替换所有数字
        System.out.println(noDigits);
        String lowerAndDigit = CharMatcher.digit().or(CharMatcher.javaLowerCase()).retainFrom("张三的一生1213");// 只保留数字和小写字母
        System.out.println(lowerAndDigit);
    }

    @Test
    public void testbigMap() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("key1", "value1");
        biMap.put("key2", "value2");
        biMap.put("key3", "value3");
        biMap.put("key4", "value4");
        biMap.put("key5", "value5");
        biMap.put("key6", "value6");
        biMap.put("key7", "value7");
        biMap.put("key8", "value8");

        System.out.println(biMap.get("key1"));
//        调转
        BiMap<String, String> inverse = biMap.inverse();
        System.out.println(inverse.get("value1"));
        inverse.put("value8", "key9");
        System.out.println(inverse);
        System.out.println(biMap);
        // 强制put  会把原先的value1和key1删除掉
        biMap.forcePut("key9", "value1");
        System.out.println(biMap);
    }


    @Test
    public void testMultiMap() {
        ArrayListMultimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("key1", "value");
        multimap.put("key1", "value");
        multimap.put("key1", "value");
        multimap.put("key1", "value");
        multimap.put("key2", "value1");
        System.out.println(multimap);

        List<String> strings = multimap.get("key1");
        System.out.println(strings);


        HashMultimap<String, String> hashMultimap = HashMultimap.create();
        hashMultimap.put("key1", "value");
        hashMultimap.put("key1", "value");
        hashMultimap.put("key1", "value");
        hashMultimap.put("key1", "value1");
        hashMultimap.put("key2", "value2");
        System.out.println(hashMultimap);
        Set<String> key1 = hashMultimap.get("key1");
        System.out.println(key1);


        TreeMultimap<String, String> treeMultimap = TreeMultimap.create();
        treeMultimap.put("key1", "value4");
        treeMultimap.put("key1", "value");
        treeMultimap.put("key1", "value2");
        treeMultimap.put("key1", "value");
        System.out.println(treeMultimap);
        NavigableSet<String> x = treeMultimap.get("key1");
        System.out.println(x);

        NavigableMap<String, Collection<String>> x1 = treeMultimap.asMap();
        System.out.println(x1);

        System.out.println("value的size" + treeMultimap.size());
    }


    @Test
    public void testRangMap() {
        TreeRangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.open(0, 10), "第一档");
        rangeMap.put(Range.closedOpen(10, 20), "第二档");
        rangeMap.put(Range.closedOpen(20, 50), "第三档");
        rangeMap.put(Range.atLeast(50), "第四档");
        System.out.println(rangeMap.get(50));
    }


    @Test
    public void testClassToinstanceMap() {

        MutableClassToInstanceMap<Object> instanceMap = MutableClassToInstanceMap.create();
        RegisterDetail detail = new RegisterDetail();
        instanceMap.putInstance(RegisterDetail.class, detail);

        System.out.println(instanceMap.getInstance(RegisterDetail.class) == detail);


        //超类也是可以使用的
        ClassToInstanceMap<Map> map = MutableClassToInstanceMap.create();
        HashMap<String, Object> hashMap = new HashMap<>();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        ArrayList<Object> list = new ArrayList<>();

        map.putInstance(HashMap.class,hashMap);
        map.putInstance(TreeMap.class,treeMap);
    }

    @Test
    public void testSocialCredi2t() {
        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        //生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(simpleUUID);

        //生成类似：5b9e306a4df4f8c54a39fb0c
        String id = ObjectId.next();
        System.out.println(id);
        //方法2：从Hutool-4.1.14开始提供
        String id2 =ObjectId.next();
        System.out.println(id2);

        //参数1为终端ID
        //参数2为数据中心ID
        Snowflake snowflake = new Snowflake(1, 1);
        long snowflakeId = snowflake.nextId();
        System.out.println(snowflakeId);
    }

    public static <T> T clone(T object, Consumer<T> consumer) {
        T result = ObjectUtil.clone(object);
        if (result != null) {
            consumer.accept(result);
        }
        return result;
    }

    public static <T extends Comparable<T>> T max(T obj1, T obj2) {
        if (obj1 == null) {
            return obj2;
        }
        if (obj2 == null) {
            return obj1;
        }
        return obj1.compareTo(obj2) > 0 ? obj1 : obj2;
    }

    public static <K, V> List<V> getList(Multimap<K, V> multimap, Collection<K> keys) {
        List<V> result = new ArrayList<>();
        keys.forEach(k -> {
            Collection<V> values = multimap.get(k);
            if (CollectionUtil.isEmpty(values)) {
                return;
            }
            result.addAll(values);
        });
        return result;
    }

    @Test
    public void testtest() {
        System.out.println(NumberUtil.isGreater(new BigDecimal("10"), new BigDecimal("150")));
        System.out.println(NumberUtil.isGreater(new BigDecimal("151"), new BigDecimal("150")));
    }

    @Test
    public void testPrimitive() {
        System.out.println(Ints.asList(1, 2, 3, 4));
        System.out.println(Ints.compare(1, 2));
        System.out.println(Ints.join(" ", 1, 2, 3, 4));
        System.out.println(Ints.max(1, 3, 5, 4, 6));
        System.out.println(Ints.tryParse("1234"));
    }

    @Test
    public void testString() {
        Joiner joiner = Joiner.on("; ").skipNulls();
        String s = joiner.join("Harry", null, "Ron", "Hermione");
        System.out.println(s);

        String s1 = Joiner.on(",").join(Arrays.asList(1, 5, 7));
        System.out.println(s1);
    }

    public static <K, V> void findAndThen(Map<K, V> map, K key, Consumer<V> consumer) {
        if (CollUtil.isEmpty(map)) {
            return;
        }
        V value = map.get(key);
        if (value == null) {
            return;
        }
        consumer.accept(value);
    }




}
