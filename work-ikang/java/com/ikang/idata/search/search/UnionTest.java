package com.ikang.idata.search.search;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.*;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;
import com.ikang.idata.common.utils.StringUtil;
import jodd.util.function.Consumers;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.ikang.idata.common.consts.MagicConst.INTEGER_0;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/5/9
 */
@Service
public class UnionTest {
        //交集 = intersection
        //并集 = union
        //补集 = complement
        //析取 = disjunction
        //减去 = subtract

    @Test
    public void TestApache() {





         String[] attr1 = new String[]{"A", "B", "C", "D", "E", "F", null};
         String[] attr2 = new String[]{"1", "2", "3", "D", "E", "F", null};
         List<String> list1 = Arrays.asList(attr1);
         List<String> list2 = Arrays.asList(attr2);

            System.out.println("交集：" + CollectionUtils.intersection(list1, list2)); // 交集
            System.out.println("补集：" + CollectionUtils.disjunction(list1, list2)); // 补集
            System.out.println("并集：" + CollectionUtils.union(list1, list2)); // 并集
            System.out.println("list1的差集：" + CollectionUtils.subtract(list1, list2)); // list1的差集
            System.out.println("list2的差集：" + CollectionUtils.subtract(list2, list1)); // list2的差集

            /**
             * 交集：[null, D, E, F]
             * 补集：[A, 1, B, 2, C, 3]
             * 并集：[null, A, 1, B, 2, C, 3, D, E, F]
             * list1的差集：[A, B, C]
             * list2的差集：[1, 2, 3]
             */

    }

    @Test
    public void TestHutool1(){

        String[] attr1 = new String[]{"A", "B", "C", "D", "E", "F", null};
        String[] attr2 = new String[]{"1", "2", "3", "D", "E", "F", null};

        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList(attr2));

        System.out.println("交集：" + CollectionUtil.intersection(list1, list2)); // 交集
        System.out.println("补集：" + CollectionUtil.disjunction(list1, list2)); // 补集
        System.out.println("并集：" + CollectionUtil.union(list1, list2)); //并集
        System.out.println("list1的差集"+CollectionUtil.subtract(list1,list2));
        System.out.println("list2的差集"+CollectionUtil.subtract(list2,list1));
        System.out.println("list1的差集：" + CollectionUtil.subtractToList(list1, list2));
        System.out.println("list2的差集：" + CollectionUtil.subtractToList(list2, list1));
        /**
         *
         交集：[null, D, E, F]
         补集：[1, A, 2, B, 3, C]
         并集：[null, 1, A, 2, B, 3, C, D, E, F]
         list1的差集[A, B, C]
         list2的差集[1, 2, 3]
         list1的差集：[A, B, C]
         list2的差集：[1, 2, 3]
         */
    }



    @Test
    public void TestStream1(){
        String[] attr1 = new String[]{"A", "B", "C", "D", "E", "F", null};
        String[] attr2 = new String[]{"1", "2", "3", "D", "E", "F", null};
        List<String> list1 = Arrays.asList(attr1);
        List<String> list2 = Arrays.asList(attr2);


        List<Object> intersection = list1.stream().filter(item -> list2.contains(item)).collect(Collectors.toList());
        System.out.println("交集：" + intersection);

        List<String> subtract1 = list1.stream().filter(s -> !list2.contains(s)).collect(Collectors.toList());
        System.out.println("集合list1的差集：" + subtract1);
        List<String> subtract2 = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.toList());
        System.out.println("集合list2的差集：" + subtract2);

        List<String> union1 = list1.parallelStream().collect(Collectors.toList());
        List<String> union2 = list2.parallelStream().collect(Collectors.toList());
        union1.addAll(union2);
        List<String> union3 = union1.stream().distinct().collect(Collectors.toList());
        System.out.println("并集：" + union3);
        /**
         * 交集：[D, E, F, null]
         * 集合list1的差集：[A, B, C]
         * 集合list2的差集：[1, 2, 3]
         */

    }


    @Test
    public void TestCollection1(){
        String[] attr1 = new String[]{"A", "B", "C", "D", "E", "F", null};
        String[] attr2 = new String[]{"1", "2", "3", "D", "E", "F", null};


        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList(attr2));
        list1.retainAll(list2);
        System.out.println("交集：" + list1);

        ArrayList<String> list3 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list4 = new ArrayList<>(Arrays.asList(attr2));
        HashSet<Object> set = new HashSet<>();
        set.addAll(list3);
        set.addAll(list4);
        System.out.println("并集：" + set);

        ArrayList<String> list5 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list6 = new ArrayList<>(Arrays.asList(attr2));
        list5.removeAll(list6);
        System.out.println("集合A的差集：" + list5);
        ArrayList<String> list7 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list8 = new ArrayList<>(Arrays.asList(attr2));
        list8.removeAll(list7);
        System.out.println("集合B的差集：" + list8);

        /**
         * 交集：[D, E, F, null]
         * 并集：[null, A, 1, B, 2, C, 3, D, E, F]
         * 集合A的差集：[A, B, C]
         * 集合B的差集：[1, 2, 3]
         */
    }


    @Test
    public void TestHutool(){

        String[] attr1 = new String[]{"A", "B", "C", "D", "E", "F", null};
        String[] attr2 = new String[]{"1", "2", "3", "D", "E", "F", null};

        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList(attr2));

        System.out.println("交集：" + CollectionUtil.intersection(list1, list2)); // 交集
        System.out.println("补集：" + CollectionUtil.disjunction(list1, list2)); // 补集
        System.out.println("并集：" + CollectionUtil.union(list1, list2)); //并集
        System.out.println("list1的差集"+CollectionUtil.subtract(list1,list2));
        System.out.println("list2的差集"+CollectionUtil.subtract(list2,list1));
        System.out.println("list1的差集：" + CollectionUtil.subtractToList(list1, list2));
        System.out.println("list2的差集：" + CollectionUtil.subtractToList(list2, list1));
        /**
         *
         交集：[null, D, E, F]
         补集：[1, A, 2, B, 3, C]
         并集：[null, 1, A, 2, B, 3, C, D, E, F]
         list1的差集[A, B, C]
         list2的差集[1, 2, 3]
         list1的差集：[A, B, C]
         list2的差集：[1, 2, 3]
         */
    }



    @Test
    public void TestStream(){
        String[] attr1 = new String[]{"A", "B", "C", "D", "E", "F", null};
        String[] attr2 = new String[]{"1", "2", "3", "D", "E", "F", null};
        List<String> list1 = Arrays.asList(attr1);
        List<String> list2 = Arrays.asList(attr2);


        List<Object> intersection = list1.stream().filter(item -> list2.contains(item)).collect(Collectors.toList());
        System.out.println("交集：" + intersection);

        List<String> subtract1 = list1.stream().filter(s -> !list2.contains(s)).collect(Collectors.toList());
        System.out.println("集合list1的差集：" + subtract1);
        List<String> subtract2 = list2.stream().filter(s -> !list1.contains(s)).collect(Collectors.toList());
        System.out.println("集合list2的差集：" + subtract2);

        List<String> union1 = list1.parallelStream().collect(Collectors.toList());
        List<String> union2 = list2.parallelStream().collect(Collectors.toList());
        union1.addAll(union2);
        List<String> union3 = union1.stream().distinct().collect(Collectors.toList());
        System.out.println("并集：" + union3);
        /**
         * 交集：[D, E, F, null]
         * 集合list1的差集：[A, B, C]
         * 集合list2的差集：[1, 2, 3]
         */

    }


    @Test
    public void TestCollection(){
        String[] attr1 = new String[]{"A", "B", "C", "D", "E", "F", null};
        String[] attr2 = new String[]{"1", "2", "3", "D", "E", "F", null};


        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list2 = new ArrayList<>(Arrays.asList(attr2));
        list1.retainAll(list2);
        System.out.println("交集：" + list1);

        ArrayList<String> list3 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list4 = new ArrayList<>(Arrays.asList(attr2));
        HashSet<Object> set = new HashSet<>();
        set.addAll(list3);
        set.addAll(list4);
        System.out.println("并集：" + set);

        ArrayList<String> list5 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list6 = new ArrayList<>(Arrays.asList(attr2));
        list5.removeAll(list6);
        System.out.println("集合A的差集：" + list5);
        ArrayList<String> list7 = new ArrayList<>(Arrays.asList(attr1));
        ArrayList<String> list8 = new ArrayList<>(Arrays.asList(attr2));
        list8.removeAll(list7);
        System.out.println("集合B的差集：" + list8);

        /**
         * 交集：[D, E, F, null]
         * 并集：[null, A, 1, B, 2, C, 3, D, E, F]
         * 集合A的差集：[A, B, C]
         * 集合B的差集：[1, 2, 3]
         */
    }

    @Test
    public void toStrTest(){
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);
        System.out.println("aStr"+aStr);

        long[] b = {1,2,3,4,5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
        System.out.println("bStr"+bStr);
    }

    @Test
    public void toIntArrayTest(){
        String[] b = { "1", "2", "3", "4" };
        //结果为Integer数组
        Integer[] intArray = Convert.toIntArray(b);
        System.out.println("intArray"+intArray);

        long[] c = {1,2,3,4,5};
        //结果为Integer数组
        Integer[] intArray2 = Convert.toIntArray(c);
        System.out.println("intArray2"+intArray2);
    }


    @Test
    public void toDateTest(){
        String a = "2017-05-06";
        Date value = Convert.toDate(a);
        System.out.println("value"+value);
    }

    @Test
    public void toListTest(){
        Object[] a = {"a", "你", "好", "", 1};
        List<?> list = Convert.convert(List.class, a);
        //从4.1.11开始可以这么用
        List<?> list1 = Convert.toList(a);
        System.out.println("list1"+list1);
    }


    //科室看板的实时数据  用的就是这个方法  (convert)
    @Test
    public void toConvertTest(){
        Object[] a = { "a", "你", "好", "", 1 };
        List<String> list = Convert.convert(new TypeReference<List<String>>() {}, a);
        System.out.println(list);
    }


    @Test
    public void toHexTest(){
        //转为16进制（Hex）字符串
        String a = "我是一个小小的可爱的字符串";

//结果："e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2"
        String hex = Convert.toHex(a, CharsetUtil.CHARSET_UTF_8);
        System.out.println("打印结果为"+hex);
    }

    @Test
    public void hexToStrTest(){
        String hex = "e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2";

        //结果为："我是一个小小的可爱的字符串"
        //String raw = Convert.hexStrToStr(hex, CharsetUtil.CHARSET_UTF_8);
        //注意：在4.1.11之后hexStrToStr将改名为hexToStr
        String raw1 = Convert.hexToStr(hex, CharsetUtil.CHARSET_UTF_8);
        System.out.println("打印结果为"+raw1);
    }

    @Test
    public void strToUnicodeTest(){
        String a = "我是一个小小的可爱的字符串";

//结果为："\\u6211\\u662f\\u4e00\\u4e2a\\u5c0f\\u5c0f\\u7684\\u53ef\\u7231\\u7684\\u5b57\\u7b26\\u4e32"
        String unicode = Convert.strToUnicode(a);

//结果为："我是一个小小的可爱的字符串"
        String raw = Convert.unicodeToStr(unicode);
        System.out.println("打印结果为"+raw);
    }

    @Test
    public void convertCharsetTest(){
        String a = "我不是乱码";
//转换后result为乱码
        String result = Convert.convertCharset(a, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        String raw = Convert.convertCharset(result, CharsetUtil.ISO_8859_1, "UTF-8");
        //断言
        Assert.assertEquals(raw, a);
    }

    //时间单位转换
    @Test
    public void convertTimeTest(){
        //Convert.convertTime方法主要用于转换时长单位，比如一个很大的毫秒，我想获得这个毫秒数对应多少分：
        long a = 4535345;
        //结果为：75
        long minutes = Convert.convertTime(a, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
    }

    //金额大小写转换
    @Test
    public void digitToChineseTest(){
        double a = 67556.32;

        //结果为："陆万柒仟伍佰伍拾陆元叁角贰分"
        String digitUppercase = Convert.digitToChinese(a);
        System.out.println("digitUppercase"+digitUppercase);
    }

    //原始类和包装类转换
    @Test
    public void unWrapTest(){
        //去包装
        Class<?> wrapClass = Integer.class;

        //结果为：int.class
        Class<?> unWraped = Convert.unWrap(wrapClass);

        //包装
        Class<?> primitiveClass = long.class;

        //结果为：Long.class
        Class<?> wraped = Convert.wrap(primitiveClass);
    }

    //格式化为字符串
    @Test
    public void dateTimeTest(){
        DateTime dateTime = new DateTime("2017-01-05 12:34:23", DatePattern.NORM_DATETIME_FORMAT);
        //结果：2017-01-05 12:34:23
        String dateStr = dateTime.toString();
        System.out.println("dateStr"+dateStr);
        //结果：2017/01/05
    }


    //LocalDateTime工具-LocalDateTimeUtil   日期转换
    @Test
    public void dateStrTest(){
        String dateStr = "2020-01-23T12:23:56";
        DateTime dt = DateUtil.parse(dateStr);

        // Date对象转换为LocalDateTime
        LocalDateTime of = LocalDateTimeUtil.of(dt);
        System.out.println("of"+of);
        //of  2020-01-23T12:23:56

        // 时间戳转换为LocalDateTime
        of = LocalDateTimeUtil.ofUTC(dt.getTime());
        System.out.println("of"+of);
        //of2020-01-23T12:23:56
    }

    @Test
    public void parseTest(){
        // 解析ISO时间
        LocalDateTime localDateTime = LocalDateTimeUtil.parse("2020-01-23T12:23:56");


        // 解析自定义格式时间
        localDateTime = LocalDateTimeUtil.parse("2020-01-23", DatePattern.NORM_DATE_PATTERN);
    }

    @Test
    public void localDateTest(){
        LocalDate localDate = LocalDateTimeUtil.parseDate("2020-01-23");

// 解析日期时间为LocalDate，时间部分舍弃
        localDate = LocalDateTimeUtil.parseDate("2020-01-23T12:23:56", DateTimeFormatter.ISO_DATE_TIME);
    }

    @Test
    public void localDateTimeTest(){
        LocalDateTime localDateTime = LocalDateTimeUtil.parse("2020-01-23T12:23:56");

        // "2020-01-23 12:23:56"
        String format = LocalDateTimeUtil.format(localDateTime, DatePattern.NORM_DATETIME_PATTERN);
    }

    @Test
    public void localDateTimeTest1(){
        final LocalDateTime localDateTime = LocalDateTimeUtil.parse("2020-01-23T12:23:56");

        // 增加一天
        // "2020-01-24T12:23:56"
        LocalDateTime offset = LocalDateTimeUtil.offset(localDateTime, 1, ChronoUnit.DAYS);

        // "2020-01-22T12:23:56"
        offset = LocalDateTimeUtil.offset(localDateTime, -1, ChronoUnit.DAYS);

        //日期偏移
        LocalDateTime start = LocalDateTimeUtil.parse("2019-02-02T00:00:00");
        LocalDateTime end = LocalDateTimeUtil.parse("2020-02-02T00:00:00");

        Duration between = LocalDateTimeUtil.between(start, end);
        // 365


        //一天的开始和结束
        LocalDateTime localDateTime1 = LocalDateTimeUtil.parse("2020-01-23T12:23:56");

        // "2020-01-23T00:00"
        LocalDateTime beginOfDay = LocalDateTimeUtil.beginOfDay(localDateTime1);

        // "2020-01-23T23:59:59.999999999"
        LocalDateTime endOfDay = LocalDateTimeUtil.endOfDay(localDateTime1);
    }

    //字符串工具-StrUtil
    //1.removePrefix、removeSuffix方法
    @Test
    public void removeSuffixTest(){
        String fileName = StrUtil.removeSuffix("pretty_girl.jpg", ".jpg");  //fileName -> pretty_girl

    }

    @Test
    public void subTest(){
        //例如想截取第4个和第2个字符之间的部分也是可以的哦~） 举个栗子
        String str = "abcdefgh";
        String strSub1 = StrUtil.sub(str, 2, 3);            //strSub1 -> c
        String strSub2 = StrUtil.sub(str, 2, -3);           //strSub2 -> cde
        String strSub3 = StrUtil.sub(str, 3, 2);            //strSub2 -> c
    }

    @Test
    public void formatTest(){
        String template = "{}爱{}，就像老鼠爱大米";
        String str = StrUtil.format(template, "我", "你"); //str -> 我爱你，就像老鼠爱大米
    }


    @Test
    public void digestHexTest(){
        Digester md5 = new Digester(DigestAlgorithm.MD5);

        String testStr = "的法国红酒看来退";
        // 5393554e94bf0eb6436f240a4fd71282
        String digestHex = md5.digestHex(testStr);

        // 5393554e94bf0eb6436f240a4fd71282
        String md5Hex1 = DigestUtil.md5Hex(testStr);
    }

    @Test
    public void ListTest(){
        List<List<String>> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list2.add(""+i);
            list.add(list2);

        }

        System.out.println("list"+list);
        System.out.println("list2"+list2);
    }

    @Test
    public void asList(){
        Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        System.out.println("最后的值为"+collection);
        Integer[] moreInts = {6,7,8,9,10};
        System.out.println("最后的值为"+moreInts);
        ((ArrayList<Integer>) collection).addAll(Arrays.asList(moreInts));
        Collections.addAll(collection,11,12,13,14,15);
        Collections.addAll(collection,moreInts);
        List<Integer> list = Arrays.asList(16,17,18,19,20);
        list.set(0,99);
        //list.add(21);
        System.out.println("最后的值为"+list);
        /**
         * 最后的值为[1, 2, 3, 4, 5]
         * 最后的值为[Ljava.lang.Integer;@3901d134
         * 最后的值为[99, 17, 18, 19, 20]
         */

    }

    public static void main(String[] args) {
        // byte
        System.out.println("基本类型：byte 二进制位数：" + Byte.SIZE);
        System.out.println("包装类：java.lang.Byte");
        System.out.println("最小值：Byte.MIN_VALUE=" + Byte.MIN_VALUE);
        System.out.println("最大值：Byte.MAX_VALUE=" + Byte.MAX_VALUE);
        System.out.println();

        // short
        System.out.println("基本类型：short 二进制位数：" + Short.SIZE);
        System.out.println("包装类：java.lang.Short");
        System.out.println("最小值：Short.MIN_VALUE=" + Short.MIN_VALUE);
        System.out.println("最大值：Short.MAX_VALUE=" + Short.MAX_VALUE);
        System.out.println();

        // int
        System.out.println("基本类型：int 二进制位数：" + Integer.SIZE);
        System.out.println("包装类：java.lang.Integer");
        System.out.println("最小值：Integer.MIN_VALUE=" + Integer.MIN_VALUE);
        System.out.println("最大值：Integer.MAX_VALUE=" + Integer.MAX_VALUE);
        System.out.println();

        // long
        System.out.println("基本类型：long 二进制位数：" + Long.SIZE);
        System.out.println("包装类：java.lang.Long");
        System.out.println("最小值：Long.MIN_VALUE=" + Long.MIN_VALUE);
        System.out.println("最大值：Long.MAX_VALUE=" + Long.MAX_VALUE);
        System.out.println();

        // float
        System.out.println("基本类型：float 二进制位数：" + Float.SIZE);
        System.out.println("包装类：java.lang.Float");
        System.out.println("最小值：Float.MIN_VALUE=" + Float.MIN_VALUE);
        System.out.println("最大值：Float.MAX_VALUE=" + Float.MAX_VALUE);
        System.out.println();

        // double
        System.out.println("基本类型：double 二进制位数：" + Double.SIZE);
        System.out.println("包装类：java.lang.Double");
        System.out.println("最小值：Double.MIN_VALUE=" + Double.MIN_VALUE);
        System.out.println("最大值：Double.MAX_VALUE=" + Double.MAX_VALUE);
        System.out.println();

        // char
        System.out.println("基本类型：char 二进制位数：" + Character.SIZE);
        System.out.println("包装类：java.lang.Character");
        // 以数值形式而不是字符形式将Character.MIN_VALUE输出到控制台
        System.out.println("最小值：Character.MIN_VALUE="
                + (int) Character.MIN_VALUE);
        // 以数值形式而不是字符形式将Character.MAX_VALUE输出到控制台
        System.out.println("最大值：Character.MAX_VALUE="
                + (int) Character.MAX_VALUE);
    }
    /**
     * 基本类型：byte 二进制位数：8
     * 包装类：java.lang.Byte
     * 最小值：Byte.MIN_VALUE=-128
     * 最大值：Byte.MAX_VALUE=127
     *
     * 基本类型：short 二进制位数：16
     * 包装类：java.lang.Short
     * 最小值：Short.MIN_VALUE=-32768
     * 最大值：Short.MAX_VALUE=32767
     *
     * 基本类型：int 二进制位数：32
     * 包装类：java.lang.Integer
     * 最小值：Integer.MIN_VALUE=-2147483648
     * 最大值：Integer.MAX_VALUE=2147483647
     *
     * 基本类型：long 二进制位数：64
     * 包装类：java.lang.Long
     * 最小值：Long.MIN_VALUE=-9223372036854775808
     * 最大值：Long.MAX_VALUE=9223372036854775807
     *
     * 基本类型：float 二进制位数：32
     * 包装类：java.lang.Float
     * 最小值：Float.MIN_VALUE=1.4E-45
     * 最大值：Float.MAX_VALUE=3.4028235E38
     *
     * 基本类型：double 二进制位数：64
     * 包装类：java.lang.Double
     * 最小值：Double.MIN_VALUE=4.9E-324
     * 最大值：Double.MAX_VALUE=1.7976931348623157E308
     *
     * 基本类型：char 二进制位数：16
     * 包装类：java.lang.Character
     * 最小值：Character.MIN_VALUE=0
     * 最大值：Character.MAX_VALUE=65535
     */

   //byte,short,char—> int —> long—> float —> double
    static boolean bool;
    static byte by;
    static char ch;
    static double d;
    static float f;
    static int i;
    static long l;
    static short sh;
    static String str;

    @Test
    public  void PrimitiveTypeTest () {
        System.out.println("Bool :" + bool);
        System.out.println("Byte :" + by);
        System.out.println("Character:" + ch);
        System.out.println("Double :" + d);
        System.out.println("Float :" + f);
        System.out.println("Integer :" + i);
        System.out.println("Long :" + l);
        System.out.println("Short :" + sh);
        System.out.println("String :" + str);

        /**
         * Bool     :false
         * Byte     :0
         * Character:
         * Double   :0.0
         * Float    :0.0
         * Integer  :0
         * Long     :0
         * Short    :0
         * String   :null
         */
    }

    //自动转换
    @Test
    public  void changeTest(){
        char c1='a';//定义一个char类型
        int i1 = c1;//char自动类型转换为int
        System.out.println("char自动类型转换为int后的值等于"+i1);
        char c2 = 'A';//定义一个char类型
        int i2 = c2+1;//char 类型和 int 类型计算
        System.out.println("char类型和int计算后的值等于"+i2);

        /**
         * char自动类型转换为int后的值等于97
         * char类型和int计算后的值等于66
         */
    }

    @Test
    public  void changeTest1(){
        int i1 = 123;
        byte b = (byte)i1;//强制类型转换为byte
        System.out.println("int强制类型转换为byte后的值等于"+b);

        /**
         * int强制类型转换为byte后的值等于123
         */

    }

    @Test
    public  void forTest(){
        int [] numbers = {10, 20, 30, 40, 50};

        for(int x : numbers ){
            System.out.print( x );
            System.out.print(",");
        }
        System.out.print("\n");
        String [] names ={"James", "Larry", "Tom", "Lacy"};
        for( String name : names ) {
            System.out.print( name );
            System.out.print(",");
        }
        /**
         * 10,20,30,40,50,
         * James,Larry,Tom,Lacy,
         */
    }
    @Test
    public  void forTest1(){
       Map map = new HashMap();
       map.put("key1","list1");
       map.put("key2","list2");
       map.put("key3","list3");
       map.put("key4","list4");
       map.put("key5","list5");
       map.put("key6","list6");
       //先获取map集合的所有键的set集合
        Iterator it = map.keySet().iterator();
        //获取迭代器
        while(it.hasNext()){
            Object key = it.next();
            System.out.println(map.get(key));
        }
        Collection<String> cs =map.values();
        Iterator<String> iterator = cs.iterator();
        while(it.hasNext()){
            Object value = it.next();
            System.out.println("value"+value);
        }
        //返回Map.Entry对象的Set集合 Map.Entry包含了key和value对象
        Set<Map.Entry<Integer,String>> es = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator1 = es.iterator();
        while (it.hasNext()){
            //返回的是封装了key和value对象的Map.Entry对象
            Map.Entry<Integer,String>  entry= iterator1.next();

            //获取Map.Entry对象中封装的key和value对象
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key+value);
        }


    }

    @org.junit.jupiter.api.Test
    public void test01(){
        String v1 = "1";
        String v2 = "0";
        v1 = StringUtil.isEmpty(v1) || "null".equals(v1) ? "0" : v1;
        v2 = StringUtil.isEmpty(v2) || "null".equals(v2) ? "0" : v2;
        if (INTEGER_0.equals(v2)) {
            System.out.println(v2);
        }
//            if (INTEGER_0.equals(v2) && NumberUtil.isNumber(v1)) {
//                return new BigDecimal("1");
//            }
//            if (INTEGER_0.equals(v1) && NumberUtil.isNumber(v2)) {
//                return new BigDecimal("-1");
//            }
//            return NumberUtil.div(NumberUtil.sub(v1, v2), new BigDecimal(v2)).setScale(4, BigDecimal.ROUND_HALF_UP);
    }

    @org.junit.jupiter.api.Test
    public void testDateUtil(){
        String dateStart = "2019-10";
        String[] split1 = dateStart.split("-");
        String dateEnd = "2019-12";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("====：" + com.ikang.idata.common.utils.DateUtil.getFirstDay(dateStart));
        System.out.println("======" + com.ikang.idata.common.utils.DateUtil.getLastDay(dateEnd));
        System.out.println("==============================");
        int year2 = 2020;
        int month2 = 2;
//        System.out.println(year2 + "年" + month2 + "月第一天：" + format.format(DateUtil.getFirstDay(year2, month2)));
//        System.out.println(year2 + "年" + month2 + "月最后一天：" + format.format(DateUtil.getLastDay(year2, month2)));

        String lastYearDateStart = cn.hutool.core.date.DateUtil.offset(cn.hutool.core.date.DateUtil.parse("2019-10"), DateField.YEAR, -1).toString();
        System.out.println(lastYearDateStart);
    }

    @org.junit.jupiter.api.Test
    public void test2(){
        String incomeLastYear = null,packageReceivableAmountLastYear = null;
        System.out.println(packageReceivableAmountLastYear);
        System.out.println(incomeLastYear);
    }




    //静态方法 Optional.of()
    //为指定的值创建一个指定非 null 值的 Optional。
    @org.junit.jupiter.api.Test
    public void ofTest(){
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional= Optional.of("加油");
        System.out.println(optional);

//        //传入null值  则会报 NullPointerException
//        Optional optional1= Optional.of(null);
//        System.out.println(optional1);

    }


    //静态方法 Optional.ofNullable()
    //为指定的值创建一个 Optional 对象，如果指定的参数为 null，不抛出异常，直接则返回一个空的 Optional 对象。
    @org.junit.jupiter.api.Test
    public void ofNullAbleTest(){
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional= Optional.ofNullable("嘿呀");
        System.out.println(optional);

        //传入null值   正常返回Optional 对象
        Optional optional1= Optional.ofNullable(null);
        System.out.println(optional1);
    }

    //对象方法 isPresent()
    //如果值存在则方法会返回 true，否则返回 false。
    @org.junit.jupiter.api.Test
    public void isPresentTest(){
        //传入正常值 正常返回一个Optional对象  并使用isPresent方法
        Optional optional= Optional.ofNullable("嘿呦");
        System.out.println("传入正常值" + optional.isPresent());
        //结果  true

        //传入null值   正常返回Optional 对象
        Optional optional1= Optional.ofNullable(null);
        System.out.println("传入正常值" + optional1.isPresent());
        //结果  false
    }


    //对象方法 get()
    //如果 Optional 有值则将其返回，否则抛出 NoSuchElementException 异常。
    @org.junit.jupiter.api.Test
    public void getTest(){
        //传入正常值 正常返回一个Optional对象  并使用get方法
        Optional optional= Optional.ofNullable("嘿呦");
        System.out.println("传入正常值" + optional.get());
        //结果  传入正常值嘿呦

        //传入null值   正常返回Optional 对象
        Optional optional1= Optional.ofNullable(null);
        System.out.println("传入正常值" + optional1.get());
        //结果  java.util.NoSuchElementException: No value present
    }

    //对象方法 ifPresent()
    // 如果值存在则使用该值调用 consumer , 否则不做任何事情。
    //该方法 ifPresent(Consumer<? super T> consumer) 中参数接收的是 Consumer 类，它包含一个接口方法 accept()，该方法能够对传入的值进行处理，但不会返回结果。这里传入参数可以传入 Lamdda 表达式或 Consumer 对象及实现 Consumer 接口的类的对象。
    @org.junit.jupiter.api.Test
    public void ifPresentTest(){
        //创建Optional 对象 然后调用Optional 对象的ifPresent 方法   传入Lambda 表达式
        Optional optional= Optional.ofNullable("向目标前进");
        optional.ifPresent((value) -> System.out.println("optional 的值为" + value));
        //结果  向目标前进

        //创建Optional 对象 调用Optional对象的ifPresent方法 传入实现Consumer匿名
        Optional optional1= Optional.ofNullable("加油呀");
        Consumer<String> consumer = new Consumers(){
            @Override
            public  void accept(Object value){
                System.out.println("Optional 的值为" + value);
            }
        };
        optional1.ifPresent(consumer);
        //结果  加油呀
    }

    //对象方法 orElse()
    //如果该值存在就直接返回， 否则返回指定的其它值。
    // orElse 方法实现很简单，就是使用三目表达式对传入的参数值进行 null 验证，即 value != null ? value : other; 如果为 null 则返回 true，否则返回 false。
    @org.junit.jupiter.api.Test
    public void orElseTest(){
        //传入正常的参数 获取一个Optional 对象  并使用orElse方法设置默认值
        Optional optional = Optional.ofNullable("三生三世");
        Object deFaultValue = optional.orElse("默认值");
        System.out.println("如果值不为空" + deFaultValue);
        //结果   三生三世


        //传入null参数   获取一个Optional对象  并使用orElse方法设置默认值
        Optional optional1 = Optional.ofNullable(null);
        Object deFaultValue1 = optional1.orElse("默认值");
        System.out.println("如果值为空" + deFaultValue1);
        //结果  默认值

    }

//    https://blog.csdn.net/weixin_36380516/article/details/113361959?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164887835416780357237329%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=164887835416780357237329&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-2-113361959.142^v5^pc_search_result_cache,157^v4^control&utm_term=Optional.ofNullable&spm=1018.2226.3001.4187

    //orElseGet()
    //orElseGet 方法和 orElse 方法类似，都是在 Optional 值为空时，返回一个默认操作，只不过 orElse 返回的是默认值，而 orElseGet 是执行 lambda 表达式，然后返回 lambda 表达式执行后的结果。
    @org.junit.jupiter.api.Test
    public  void orElseGetTest(){
        //传入正常参数   获取一个Optional 对象  并使用orElse方法 设置默认值
        Optional optional = Optional.ofNullable("新的一周");
        Object object = optional.orElseGet(() -> {
            String defaultVal = "执行逻辑和生成默认值";
            return  defaultVal;
        });
        System.out.println("输出值为" + object);


        //传入null参数   获取一个Optional对象  并使用orElse方法设置默认值
        Optional optional1 = Optional.ofNullable(null);
        Object object1 = optional1.orElseGet(() -> {
            String defaultVal = "执行逻辑和生成默认值";
            return defaultVal;
        });
        System.out.println("输出值为" + object1);

        /**
         * 打印结果
         * object  新的一周
         * object1 执行逻辑和生成默认值
         */
    }

    //orElseThrow()
    //orElseThrow 方法其实就是判断创建 Optional 时传入的参数是否为 null，如果是非 null 则返回传入的值，否则抛出 异常。
    @org.junit.jupiter.api.Test
    public void orElseThrowTest(){
        //传入正常参数 获取一个Optional对象   并使用orElseThrow方法
        Optional optional = Optional.ofNullable("加油加油");
        try {
            Object elseThrow = optional.orElseThrow(() -> {
                System.out.println("执行逻辑,然后抛出异常");
                return new RuntimeException("抛出异常");
            });
            System.out.println("输出值为 " + elseThrow);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        //传入null参数  获取一个Optional 对象  并使用orElseThrow 方法
        Optional optional1 = Optional.ofNullable(null);
        try {
            Object elseThrow1 = optional1.orElseThrow(() -> {
                System.out.println("执行逻辑,然后抛出异常");
                return new RuntimeException("抛出异常");
            });

            System.out.println("输出值为 " + elseThrow1);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        /**
         * 打印结果
         * elseThrow  加油加油
         * elseThrow1  抛出异常并报错
         */
    }


    // 创建一个用户类，使用 Optional 操作用户对象，获取其 name 参数，结合 Optional 的 map 方法获取值，进行观察：
    class User {
        private String name ;
        public User (String name){
            this.name =name;
        }

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        //使用 Optional 的 map 方法对值处理：
        @org.junit.jupiter.api.Test
        public void orElseTest1(){
            //创建一个对象  设置名字属性而不设置性别  这时候性别为null
            User user = new User("测试名称");
            User user1 = new User(null);

            //使用Optional存储User对象
            Optional<User> optionalUser = Optional.ofNullable(user);
            Optional<User> optionalUser1 = Optional.ofNullable(user1);

            //获取对象的name属性值
            String name = optionalUser.map(User::getName).orElse("未填写");
            String name1 = optionalUser1.map(User::getName).orElse("未填写");

            //输出结果
            System.out.println("获取的名称 " + name);
            System.out.println("获取的名称 " + name1);

            /**
             * 打印结果
             * name 测试名称
             * name1 未填写
             */
            //通过上面两个示例观察到，通过 Optional 对象的 map 方法能够获取映射对象中的属，创建 Optional 对象，并以此属性充当 Optional 的值，结合 orElse 方法，如果获取的属性的值为空，则设置个默认值。
        }

        //对象方法 flatMap()
        // flatMap 方法和 map 方法类似，唯一的不同点就是 map 方法会对返回的值进行 Optional 封装，而 flatMap 不会，它需要手动执行 Optional.of 或 Optional.ofNullable 方法对 Optional 值进行封装。
        @org.junit.jupiter.api.Test
        public  void flatMapTest(){
            //创建一个map 对象
            Map<String,String> userMap = new HashMap<>();
            userMap.put("name","jiayou");
            userMap.put("sex","nan");

            //传入Map对象参数   获取一个Optional对象
            Optional<Map<String,String>> optional = Optional.of(userMap);
            //使用Optionalde flatMap方法  获取Map中的name属性值,然后通过获取的值手动创建一个新的Optional对象
            Optional optional1 = optional.flatMap( value -> Optional.ofNullable(value.get("name")));
            System.out.println("获取的Optional 的值" + optional1.get());

            /**
             * 打印结果
             * jiayou
             */

        }

        //对象方法 filter()
        // filter 方法通过传入的限定条件对 Optional 实例的值进行过滤，如果 Optional 值不为空且满足限定条件就返回包含值的 Optional，否则返回空的 Optional。这里设置的限定条件需要使用实现了 Predicate 接口的 lambda 表达式来进行配置。
        @org.junit.jupiter.api.Test
        public void filterTest(){
            //创建一个测试的Optional对象
            Optional<String> optional = Optional.ofNullable("XIANGQIANCHONGYA");

            //调用Optionalde filter 方法  设置一个满足的条件  然后观察获取Optional对象值 是否为空
            Optional optional1 = optional.filter((value) -> value.length() >2);
            System.out.println("Optional 的值不为空:" +optional1.isPresent());

            //调用Optional的filter方法,设置一个不满足的条件然后观察获取的Optional对象值是否为空
            Optional optional2 = optional.filter((value) -> value.length() < 2);
            System.out.println("Optional的值不为空 " + optional2.isPresent());

            /**
             * 打印结果
             * optional1  true
             * optional2  false
             */
        }

        //Optional 常用示例组合

        @org.junit.jupiter.api.Test
        public void OptionalExample(){
            //创建一个测试的用户集合
            List<User> userList = new ArrayList<>();

            //创建几个测试用户
            User user1 = new User("abc");
            User user2 = new User("efg");
            User user3 = null;

            //将用户加入集合
            userList.add(user1);
            userList.add(user2);
            userList.add(user3);

            //创建用于存储姓名的集合
            List<String> nameList = new ArrayList<>();

            //循环用户列表获取用户信息,值获取不为空且用户以a开头的姓名
            //如果不符合条件就设置默认值 最后将符合条件的用户姓名加入姓名集合
            for (User user : userList) {
                // nameList.add(Optional.ofNullable(user).map(User::getName).filter(value -> value.startsWith("a")));

            }
            System.out.println("通过Optional过滤的集合输出");
            nameList.stream().forEach(System.out::println);
        }

    }


}
