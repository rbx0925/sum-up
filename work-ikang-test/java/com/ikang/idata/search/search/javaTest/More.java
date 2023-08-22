package com.ikang.idata.search.search.javaTest;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Rot;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import com.ikang.idata.search.search.entity.UserInfo;
import com.ikang.idata.search.search.entity.UserInfoDTO;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.ikang.idata.common.consts.MagicConst.EXPORT_EXCEL;
import static com.ikang.idata.search.search.Java8.IsContinuous;
import static java.util.stream.Collectors.toList;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/12/14
 */
public class More {

    private String name;
    private List<One> Students;//表明有一个想相应的属性，此时并没有为其分配空间,(可以说是只是简单地为器分配了一个地址)

    public More(String name) {
        this.name = name;
        Students = new ArrayList<One>();//配之前的List分配空间（实例化）
    }

    public List<One> AddStu(){
        return Students;//应用这个其下面还有一个类（Java封装的），可以将之前的单独申请的Student加入到List<One>中
    }

    public String toString() {
        return "School :" + this.name;
    }
    //
    ///
    ///
    //
    //


    @org.junit.jupiter.api.Test
    public void test() {
        //季度开始时间
        System.out.println(DateUtil.beginOfQuarter(new Date()));
        System.out.println(DateUtil.beginOfMonth(new Date()));
        System.out.println("---------------------------------");
        System.out.println(DateUtil.endOfMonth(new Date()));
        System.out.println(DateUtil.endOfQuarter(new Date()));
    }

    @org.junit.jupiter.api.Test
    public void testBeanUtil1() {
        UserInfoDTO infoDTO = new UserInfoDTO();
        infoDTO.setAge("10");
        infoDTO.setMailbox("18233197071");
        infoDTO.setName("张三");
        infoDTO.setPassword("----222-2");
        UserInfo userInfo = BeanUtil.toBean(infoDTO, UserInfo.class);
        System.out.println(userInfo);
    }


    @org.junit.jupiter.api.Test
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

    @org.junit.jupiter.api.Test
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


    @org.junit.jupiter.api.Test
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
    @org.junit.jupiter.api.Test
    public void testBean() {
        Stream.of(BeanUtil.getPropertyDescriptors(UserInfo.class)).forEach(System.out::println);
    }

    /**
     * 获取类属性值
     */
    @org.junit.jupiter.api.Test
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


    @org.junit.jupiter.api.Test
    public void testCon() {
        System.out.println("exportExcel/annotation".contains(EXPORT_EXCEL));
        System.out.println("exportExce".contains(EXPORT_EXCEL));
    }


    @org.junit.jupiter.api.Test
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


    @org.junit.jupiter.api.Test
    public void testContinuous() {
        IntStream intStream = IntStream.of(2020, 2018, 2017).sorted();
//        intStream.forEach(System.out::println);
        System.out.println(IsContinuous(intStream.toArray()));
    }

    @org.junit.jupiter.api.Test
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
}
