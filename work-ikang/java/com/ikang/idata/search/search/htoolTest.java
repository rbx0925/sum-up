
package com.ikang.idata.search.search;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.*;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.ikang.idata.common.entity.ReportsWayAgg;
import lombok.Data;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.ikang.idata.common.consts.MagicConst.*;
import static com.ikang.idata.common.consts.MagicConst.INT_4;
import static com.ikang.idata.common.entity.ReportsWay.KEY;
import static com.ikang.idata.common.entity.ReportsWay.REPORT_REQ_RNTIME_NAME;
import static java.util.Arrays.*;

/**
 * @author rbx
 * @title
 * @Create 2023-05-22 13:36
 * @Description
 */
public class htoolTest {

    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person();
        person.setName("John");
        person.setSex("male");
        Class<? extends Person> aClass = person.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object o = field.get(person);
            System.out.println("o = " + o.toString());
            String copy = field.getName() + "Copy";
            Field field1 = ReflectionUtils.findField(Person.class, copy);
            System.out.println("field1 = " + field1);
        }
    }

    /**
     * 信息脱敏工具-DesensitizedUtil
     */
    @Test
    public void Test() {
        // 以身份证号为例 保留前一位 后两位
        String result = DesensitizedUtil.idCardNum("51343620000320711X", 1, 2);
        System.out.println("result = " + result);

        // 对于约定俗成的脱敏，可以不用指定隐藏位数，比如手机号：
        String s2 = DesensitizedUtil.mobilePhone("13512345678");
        System.out.println("s2 = " + s2);

        // 还有一些简单粗暴的脱敏，比如密码，只保留了位数信息：
        String s3 = DesensitizedUtil.password("1234567890");
        System.out.println("s3 = " + s3);
    }

    /**
     * 字符串工具-StrUtil
     */
    @Test
    public void Test2() {
        /*
         * 1. hasBlank、hasEmpty方法
         * 就是给定一些字符串，如果一旦有空的就返回true，常用于判断好多字段是否有空的（例如web表单数据）。
         * 这两个方法的区别是hasEmpty只判断是否为null或者空字符串（""），hasBlank则会把不可见字符也算做空，isEmpty和isBlank同理。
         */
        String temp = null;
        String temp1 = "";
        String temp2 = "heihei";
        System.out.println(StrUtil.hasBlank(temp)); // true
        System.out.println(StrUtil.hasEmpty(temp1)); // true
        System.out.println(StrUtil.hasEmpty(temp2)); // false

        // removePrefix、removeSuffix方法 去掉字符串的前缀后缀
        String fileName = StrUtil.removeSuffix("pretty_girl.jpg", ".jpg"); // fileName -> pretty_girl
        System.out.println("fileName = " + fileName);

        // sub方法 可以理解为String中subString方法的增强
        String str = "abcdefgh";
        String strSub1 = StrUtil.sub(str, 2, 3); // strSub1 -> c
        String strSub2 = StrUtil.sub(str, 2, -3); // strSub2 -> cde
        String strSub3 = StrUtil.sub(str, 3, 2); // strSub2 -> c
        System.out.println(strSub1 + "//" + strSub2 + "//" + strSub3);

        // format方法
        String template = "{}爱{}，就像老鼠爱大米";
        String str1 = StrUtil.format(template, "我", "你"); // str -> 我爱你，就像老鼠爱大米
        System.out.println("str1 = " + str1);
    }

    /**
     * 反射工具-ReflectUtil
     */
    @Test
    public void Test3() {
        // 获取某个类的所有方法
        Method[] methods = ReflectUtil.getMethods(Person.class);

        // 获取某个类的指定方法
        Method method = ReflectUtil.getMethod(Person.class, "getId");

        // 构造对象
        Person person = ReflectUtil.newInstance(Person.class);

        // 执行方法
        Person testClass = new Person();
        ReflectUtil.invoke(testClass, "setName", "小明");
    }

    /**
     * 分页工具-PageUtil
     */
    @Test
    public void Test4() {
        // transToStartEnd 将页数和每页条目数转换为开始位置和结束位置。 此方法用于不包括结束位置的分页方法。
        /*
         * 页码：0，每页10 -> [0, 10]
         * 页码：1，每页10 -> [10, 20]
         */
        int[] startEnd1 = PageUtil.transToStartEnd(0, 10);// [0, 10]
        Arrays.stream(startEnd1).forEach(System.out::println);
        int[] startEnd2 = PageUtil.transToStartEnd(1, 10);// [10, 20]
        Arrays.stream(startEnd2).forEach(System.out::println);

        // totalPage 根据总数计算总页数
        int totalPage = PageUtil.totalPage(20, 3);// 7
        System.out.println("totalPage = " + totalPage);

        // PageUtil.rainbow 分页彩虹算法
        /**
         * 例如我们当前页为第5页，共有20页，只显示6个页码，显示的分页列表应为：
         * 上一页 3 4 [5] 6 7 8 下一页
         */
        // 参数意义分别为：当前页、总页数、每屏展示的页数
        int[] rainbow = PageUtil.rainbow(5, 20, 6); // [3, 4, 5, 6, 7, 8]
        System.out.println("rainbow = " + rainbow);
    }

    /**
     * 枚举工具-EnumUtil
     */
    @Test
    public void Test5() {
        // getNames 获取枚举类中所有枚举对象的name列表
        List<String> names = EnumUtil.getNames(TestEnum.class);
        System.out.println("names = " + names);

        // getFieldValues 获得枚举类中各枚举对象下指定字段的值
        List<Object> type = EnumUtil.getFieldValues(TestEnum.class, "type");
        System.out.println("type = " + type);

        // getEnumMap 获取枚举字符串值和枚举对象的Map对应，使用LinkedHashMap保证有序，结果中键为枚举名，值为枚举对象。
        LinkedHashMap<String, TestEnum> enumMap = EnumUtil.getEnumMap(TestEnum.class);
        TestEnum test1 = enumMap.get("TEST1");
        System.out.println("test1 = " + test1);

        // getNameFieldMap 获得枚举名对应指定字段值的Map，键为枚举名，值为字段值
        Map<String, Object> enumMap1 = EnumUtil.getNameFieldMap(TestEnum.class, "type");
        Object test11 = enumMap1.get("TEST1");// 结果为：type1
        System.out.println("enumMap1 = " + test11);
    }

    /**
     * 数字工具-NumberUtil
     */
    @Test
    public void Test6() {
        /**
         * NumberUtil.add 针对数字类型做加法
         * NumberUtil.sub 针对数字类型做减法
         * NumberUtil.mul 针对数字类型做乘法
         * NumberUtil.div 针对数字类型做除法，并提供重载方法用于规定除不尽的情况下保留小数位数和舍弃方式。
         * 以上四种运算都会将double转为BigDecimal后计算，解决float和double类型无法进行精确计算的问题
         */
        BigDecimal add = NumberUtil.add(new BigDecimal("1.00000000"), 2.02f);
        System.out.println("add = " + add);
        BigDecimal sub = NumberUtil.sub(new BigDecimal("5.5"), 6.6d);
        System.out.println("sub = " + sub);
        BigDecimal mul = NumberUtil.mul("1.5", "1.5");
        System.out.println("mul = " + mul);
        BigDecimal div = NumberUtil.div(new BigDecimal("10"), new BigDecimal("3"), 3);
        System.out.println("div = " + div);

        /**
         * 0 -> 取一位整数
         * 0.00 -> 取一位整数和两位小数
         * 00.000 -> 取两位整数和三位小数
         * # -> 取所有整数部分
         * #.##% -> 以百分比方式计数，并取两位小数
         * #.#####E0 -> 显示为科学计数法，并取五位小数
         * ,### -> 每三位以逗号进行分隔，例如：299,792,458
         * 光速大小为每秒,###米 -> 将格式嵌入文本
         */
        // DecimalFormat.format进行简单封装。按照固定格式对double或long类型的数字做格式化操作。
        long c = 299792458;// 光速
        String format = NumberUtil.decimalFormat(",###", c);// 299,792,458
        System.out.println("format = " + format);

        // 随机数
        // NumberUtil.generateRandomNumber 生成不重复随机数 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组。
        // NumberUtil.generateBySet 生成不重复随机数 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组。
        int[] ints = NumberUtil.generateRandomNumber(0, 10, 10);
        Arrays.stream(ints).forEach(System.out::println);
        System.out.println("--------------------------------");
        Integer[] integers = NumberUtil.generateBySet(0, 10, 10);
        Arrays.stream(integers).forEach(System.out::println);
        // NumberUtil.appendRange 将给定范围内的整数添加到已有集合中
        Collection<Integer> appended = NumberUtil.appendRange(10, 50, new ArrayList<>());
        appended.stream().forEach(System.out::println);

        /**
         * NumberUtil.factorial 阶乘
         * NumberUtil.sqrt 平方根
         * NumberUtil.divisor 最大公约数
         * NumberUtil.multiple 最小公倍数
         * NumberUtil.getBinaryStr 获得数字对应的二进制字符串
         * NumberUtil.binaryToInt 二进制转int
         * NumberUtil.binaryToLong 二进制转long
         * NumberUtil.compare 比较两个值的大小
         * NumberUtil.toStr 数字转字符串，自动并去除尾小数点儿后多余的0
         */
    }

    /**
     * 数组工具-ArrayUtil
     */
    @Test
    public void Test7() {
        // 数组的判空类似于字符串的判空，标准是null或者数组长度为0，ArrayUtil中封装了针对原始类型和泛型数组的判空和判非空：
        // 判断空 和 非空
        int[] a = {};
        int[] b = null;
        System.out.println(ArrayUtil.isEmpty(a));
        System.out.println(ArrayUtil.isEmpty(b));
        int[] a1 = {1, 2};
        System.out.println(ArrayUtil.isNotEmpty(a1));

        // 新建泛型数组 Array.newInstance并不支持泛型返回值，在此封装此方法使之支持泛型返回值。
        String[] newArray = ArrayUtil.newArray(String.class, 3);

        // 克隆
        // 数组本身支持clone方法，因此确定为某种类型数组时调用ArrayUtil.clone(T[]),不确定类型的使用ArrayUtil.clone(T)，两种重载方法在实现上有所不同，但是在使用中并不能感知出差别。
        // 1.泛型数组调用原生克隆
        Integer[] b1 = {1, 2, 3};
        Integer[] cloneB = ArrayUtil.clone(b1);
        Assert.assertArrayEquals(b1, cloneB);
        // 2.非泛型数组（原始类型数组）调用第二种重载方法
        int[] a2 = {1, 2, 3};
        int[] clone = ArrayUtil.clone(a2);
        Assert.assertArrayEquals(a2, clone);

        // 过滤
        // ArrayUtil.filter方法用于过滤已有数组元素，只针对泛型数组操作，原始类型数组并未提供。
        // 方法中Filter接口用于返回boolean值决定是否保留。
        // 过滤数组，只保留偶数
        Integer[] a3 = {1, 2, 3, 4, 5, 6};
        Integer[] filter = ArrayUtil.filter(a3, f -> f % 2 == 0); // [2,4,6]
        System.out.println(ArrayUtil.toString(filter));

        // zip
        // ArrayUtil.zip方法传入两个数组，第一个数组为key，第二个数组对应位置为value，此方法在Python中为zip()函数。
        String[] keys = {"a", "b", "c"};
        Integer[] values = {1, 2, 3};
        Map<String, Integer> map = ArrayUtil.zip(keys, values, true);
        System.out.println("map = " + map);
    }

    /**
     * 唯一ID工具-IdUtil
     */
    @Test
    public void Test8() {
        /**
         * 说明
         * Hutool重写java.util.UUID的逻辑，对应类为cn.hutool.core.lang.UUID，使生成不带-的UUID字符串不再需要做字符替换，性能提升一倍左右。
         */
        // UUID
        // UUID全称通用唯一识别码（universally unique identifier），JDK通过java.util.UUID提供了
        // Leach-Salz 变体的封装。
        // 生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = IdUtil.randomUUID();
        System.out.println("uuid = " + uuid);
        // 生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = IdUtil.simpleUUID();
        System.out.println("simpleUUID = " + simpleUUID);

        // Snowflake
        // 分布式系统中，有一些需要使用全局唯一ID的场景，有些时候我们希望能使用一种简单一些的ID，并且希望ID能够按照时间有序生成。Twitter的Snowflake
        // 算法就是这种生成器。
        // 参数1为终端ID
        // 参数2为数据中心ID
        /**
         * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 -
         * 000000000000
         * 第一位为0不使用 - 时间戳41位 - 工作机器ID10位(机房ID5位+机器ID5位) - 序列号12位
         */
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id = snowflake.nextId();
        System.out.println("id = " + id);

        // 简单使用
        Snowflake snowflake1 = IdUtil.getSnowflake();
        String strId = snowflake1.nextIdStr();
        System.out.println("strId = " + strId);
        /**
         * IdUtil.createSnowflake每次调用会创建一个新的Snowflake对象，不同的Snowflake对象创建的ID可能会有重复，因此需自行维护此对象为单例，或者使用IdUtil.getSnowflake使用全局单例对象。
         */
    }

    @Test
    public void TestBeanUtil() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("alias_name", "Joe");
        map.put("sex", "0");
        Person person = BeanUtil.mapToBean(map, Person.class, true);
        System.out.println("person = " + person);

        Person person1 = new Person("rbx", "1");
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(person1);
        System.out.println("stringObjectMap = " + stringObjectMap);
    }

    /**
     * Bean工具-BeanUtil
     */
    @Test
    public void Test9() {
        // BeanUtil.fillBeanWithMap 使用Map填充bean
        // BeanUtil.fillBeanWithMapIgnoreCase 使用Map填充bean，忽略大小写
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Joe");
        map.put("sex", "0");
        Person person = BeanUtil.fillBeanWithMap(map, new Person(), false);
        // BeanUtil.fillBeanWithMapIgnoreCase(map,new Person(),false);
        System.out.println("person = " + person);

        // 同时提供了map转bean的方法，与fillBean不同的是，此处并不是传Bean对象，而是Bean类，Hutool会自动调用默认构造方法创建对象。当然，前提是Bean类有默认构造方法（空构造）
        // BeanUtil.toBean
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("name", "Joe");
        map1.put("sex", "1");
        // 设置别名，用于对应bean的字段名
        HashMap<String, String> mapping = new HashMap<>();
        mapping.put("name", "sao");
        mapping.put("sex", "0");
        Person person1 = BeanUtil.toBean(map, Person.class, CopyOptions.create().setFieldMapping(mapping));
        // Person person1 = BeanUtil.toBean(map, Person.class, new
        // CopyOptions(Person.class,false,null));
        System.out.println("person1 = " + person1);

        // BeanUtil.beanToMap方法则是将一个Bean对象转为Map对象。
        Person person2 = new Person();
        person2.setName("sab");
        person2.setSex("0");
        Map<String, Object> map2 = BeanUtil.beanToMap(person);
        System.out.println("map2 = " + map2);

        // Bean转Bean
        // Bean之间的转换主要是相同属性的复制，因此方法名为copyProperties，此方法支持Bean和Map之间的字段复制。
        // BeanUtil.copyProperties方法同样提供一个CopyOptions参数用于自定义属性复制。
        Person p1 = new Person();
        p1.setSex("0");
        p1.setName("testName");
        Map<String, Object> map3 = MapUtil.newHashMap();
        Person p2 = new Person();
        BeanUtil.copyProperties(p1, p2);
        System.out.println("p2 = " + p2);

        // Alias注解 通过此注解可以给Bean的字段设置别名。
        Person p3 = new Person();
        p3.setName("名字");
        p3.setSex("1");
        Map<String, Object> map4 = BeanUtil.beanToMap(p3);// Bean转换为Map时，自动将subName修改为aliasSubName
        System.out.println("map4 = " + map4);
        System.out.println(map4.get("aliasName"));// 返回"sub名字"
        // 同样Alias注解支持注入Bean时的别名：
        Map<String, Object> map5 = new HashMap<>();
        map5.put("aliasName", "sub名字");
        map5.put("sex", "0");
        Person p4 = BeanUtil.mapToBean(map5, Person.class, false);
        System.out.println(p4.getName());// 返回"sub名字"
    }

    private Person TestMapToBean(Person person, String param) {
        Map<String, Object> toMap = BeanUtil.beanToMap(person);
        System.out.println("person = " + person);
        toMap.put("sex", param);
        person = BeanUtil.mapToBean(toMap, Person.class, false);
        System.out.println("person = " + person);
        return person;
    }

    @Test
    public void Testtest() {
        Person person = new Person();
        person.setName("testName");
        person = TestMapToBean(person, "1");
        System.out.println("person = " + person);
        person = TestMapToBean(person, "2");
        System.out.println("person = " + person);
    }

    /**
     * 注解工具-AnnotationUtil
     */
    @Test
    public void Test10() {
        /**
         * getAnnotations 获取指定类、方法、字段、构造等上的注解列表
         * getAnnotation 获取指定类型注解
         * getAnnotationValue 获取指定注解属性的值
         */
        Annotation[] annotations = AnnotationUtil.getAnnotations(ClassWithAnnotation.class, false);
        AnnotationForTest annotation = AnnotationUtil.getAnnotation(ClassWithAnnotation.class, AnnotationForTest.class);
        String value1 = annotation.value();
        System.out.println("value1 = " + value1);// value为"测试"
        Object value = AnnotationUtil.getAnnotationValue(ClassWithAnnotation.class, AnnotationForTest.class);
        System.out.println("value = " + value);
    }

    /**
     * JSON工具-JSONUtil
     */
    @Test
    public void Test11() {
        // JSONUtil.toJsonStr可以将任意对象（Bean、Map、集合等）直接转换为JSON字符串。
        // 如果对象是有序的Map等对象，则转换后的JSON字符串也是有序的。 -- JSON字符串创建
        SortedMap<Object, Object> sortedMap = new TreeMap<Object, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("attributes", "a");
                put("b", "b");
                put("c", "c");
            }
        };
        String jsonStr = JSONUtil.toJsonStr(sortedMap);
        System.out.println("jsonStr = " + jsonStr); // {"attributes":"a","b":"b","c":"c"}
        // 如果我们想获得格式化后的JSON
        /**
         * {
         * "attributes": "a",
         * "b": "b",
         * "c": "c"
         * }
         */
        JSONUtil.toJsonPrettyStr(sortedMap);

        // JSON字符串解析
        String html = "{\"name\":\"Something must have been changed since you leave\"}";
        JSONObject jsonObject = JSONUtil.parseObj(html);
        String name = jsonObject.getStr("name");
        System.out.println("name = " + name); // Something must have been changed since you leave

        // JSON转Bean
        String json = "{\"ADT\":[[{\"BookingCode\":[\"N\",\"N\"]}]]}";
        Price price = JSONUtil.toBean(json, Price.class);
        String result = price.getADT().get(0).get(0).getBookingCode().get(0);
        System.out.println("result = " + result);
    }

    /**
     * JSON对象-JSONObject
     */
    @Test
    public void Test12() {
        // 创建 JSONUtil.createObj()是快捷新建JSONObject的工具方法，同样我们可以直接new：
        JSONObject json1 = JSONUtil.createObj().put("a", "value1").put("b", "value2").put("c", "value3");
        System.out.println("json1 = " + json1);

        // JSON字符串解析
        String jsonStr = "{\"b\":\"value2\",\"c\":\"value3\",\"a\":\"value1\"}";
        // 方法一：使用工具类转换
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        // 方法二：new的方式转换
        JSONObject jsonObject2 = new JSONObject(jsonStr);
        // JSON对象转字符串（一行） jsonObject.toString();

        // JavaBean解析
        JSONObject json = JSONUtil.parseObj(new Person("xiaoli", "0"), false, true);

        // 默认的，Hutool将日期输出为时间戳，如果需要自定义日期格式，可以调用
        json.setDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * JSON数组-JSONArray
     */
    @Test
    public void Test13() {
        // 使用 创建
        // 方法1
        JSONArray array = JSONUtil.createArray();
        // 方法2
        JSONArray array2 = new JSONArray();
        array.add("value1");
        array.add("value2");
        array.add("value3");
        // 转为JSONArray字符串
        array.toString();

        // 从Bean列表解析
        KeyBean b1 = new KeyBean();
        b1.setAkey("aValue1");
        b1.setBkey("bValue1");
        KeyBean b2 = new KeyBean();
        b2.setAkey("aValue2");
        b2.setBkey("bValue2");
        ArrayList<KeyBean> list = CollUtil.newArrayList(b1, b2);
        // [{"akey":"aValue1","bkey":"bValue1"},{"akey":"aValue2","bkey":"bValue2"}]
        JSONArray jsonArray = JSONUtil.parseArray(list);
        jsonArray.getJSONObject(0).getStr("akey");// aValue1

        // 从JSON字符串解析
        String jsonStr = "[\"value1\", \"value2\", \"value3\"]";
        JSONArray array3 = JSONUtil.parseArray(jsonStr);

        // 转换为bean的List
        String jsonArr = "[{\"id\":111,\"name\":\"test1\"},{\"id\":112,\"name\":\"test2\"}]";
        JSONArray array4 = JSONUtil.parseArray(jsonArr);
        List<User> userList = JSONUtil.toList(array4, User.class);
        userList.get(0).getId();// 111

        // JSON路径
        // 如果JSON的层级特别深，那么获取某个值就变得非常麻烦，代码也很臃肿，Hutool提供了getByPath方法可以通过表达式获取JSON中的值。
        String jsonStr2 = "[{\"id\": \"1\",\"name\": \"a\"},{\"id\": \"2\",\"name\": \"b\"}]";
        final JSONArray jsonArray2 = JSONUtil.parseArray(jsonStr2);
        Object byPath = jsonArray2.getByPath("[1].name");// b
        System.out.println("byPath = " + byPath);
    }

    // 定义枚举
    public enum TestEnum {
        TEST1("type1"),
        TEST2("type2"),
        TEST3("type3");

        private TestEnum(String type) {
            this.type = type;
        }

        private String type;

        public String getType() {
            return this.type;
        }
    }

    @Data
    public class ADT {
        private List<String> BookingCode;
    }

    @Data
    public class Price {
        private List<List<ADT>> ADT;
    }

    @Data
    public class KeyBean {
        private String akey;
        private String bkey;
    }

    @Data
    static class User {
        private Integer id;
        private String name;
    }

    @AnnotationForTest("测试")
    public class ClassWithAnnotation {
    }

    // Retention注解决定MyAnnotation注解的生命周期
    @Retention(RetentionPolicy.RUNTIME)
    // Target注解决定MyAnnotation注解可以加在哪些成分上，如加在类身上，或者属性身上，或者方法身上等成分
    @Target({ElementType.METHOD, ElementType.TYPE})
    public @interface AnnotationForTest {

        /**
         * 注解的默认属性值
         *
         * @return 属性值
         */
        String value();
    }
}