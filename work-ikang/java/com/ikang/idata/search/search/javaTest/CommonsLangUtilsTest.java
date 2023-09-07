package com.ikang.idata.search.search.javaTest;

import cn.hutool.core.convert.NumberChineseFormatter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ByteUtil;
import cn.hutool.core.util.StrUtil;
import com.ikang.idata.common.entity.User;
import com.ikang.idata.search.search.TestSpring;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/9/7
 */
@Service
@TestSpring
public class CommonsLangUtilsTest {


    /**
     * List集合测试工具
     * <p>
     * List集合转换成以指定字符拼接的字符串
     */
    @org.junit.jupiter.api.Test
    public void ListAppendUtils() {
        // 如何把list集合拼接成以逗号分隔的字符串 a,b,c
        List<String> list = Arrays.asList( "a", "b", "c" );

        // 第一种方法，可以用stream流
        String join = list.stream().collect( Collectors.joining( "," ) );
        System.out.println( join );
        // 输出 a,b,c

        // 第二种方法，其实String也有join方法可以实现这个功能
        String join2 = String.join( ",", list );
        System.out.println( join2 );
        // 输出 a,b,c
    }

    /**
     * 两个List集合取交集
     */
    @org.junit.jupiter.api.Test
    public void ListIntersectionUtils() {
        List<String> list1 = new ArrayList<>();
        list1.add( "a" );
        list1.add( "b" );
        list1.add( "c" );

        List<String> list2 = new ArrayList<>();
        list2.add( "a" );
        list2.add( "b" );
        list2.add( "d" );
        // 取交集
        list1.retainAll( list2 );
        System.out.println( list1 );
        // 输出[a, b]
    }

    /**
     * 两个List集合合并，会重复
     */
    @org.junit.jupiter.api.Test
    public void ListUnionUtils() {
        List<String> list1 = new ArrayList<>();
        list1.add( "a" );
        list1.add( "b" );
        list1.add( "c" );

        List<String> list2 = new ArrayList<>();
        list2.add( "a" );
        list2.add( "b" );
        list2.add( "d" );
        // 取交集
        list1.addAll( list2 );
        System.out.println( list1 );
        // 输出[a, b, c, a, b, d]
    }

    /**
     * 比较两个字符串是否相等，忽略大小写
     */
    @org.junit.jupiter.api.Test
    public void compareIgnoreCaseUtils() {
        String strA = "helloword";
        String strB = "HELLOWORD";
        if (strA.equalsIgnoreCase( strB )) {
            System.out.println( "strA相等strB" );
        }
    }

    /**
     * 比较两个对象是否相等
     */
    @org.junit.jupiter.api.Test
    public void compareObjectUtils() {
//        Object strA = null;
//        Object strB = new JavaUtils();
//        // 如果strA为null就会报空指针，NullPointerException
//        //if (strA.equals( strB )) {
//        //    System.out.println( "strA 等于 strB" );
//        //}
//
//        // 这种方式可以避免空指针问题
//        boolean result = Objects.equals( strA, strB );
//        System.out.println( result );
    }



    /**
     *
     * 传参CharSequence类型是String、StringBuilder、StringBuffer的父类，都可以直接下面方法判空.
     *
     */
    @org.junit.jupiter.api.Test
    public void commonLang3Utils() {
    }

    /**
     *
     * commonStringUtils
     *
     */
    @org.junit.jupiter.api.Test
    public void commonStringUtils() {
        //首字母转成大写
        String str = "yyds";
        String capitalize = StringUtils.capitalize( str );
        System.out.println( capitalize );
        // 输出 Yyds

        //重复拼接字符串
        String str1 = StringUtils.repeat( "ab", 6 );
        System.out.println( str1 );
        // 输出abab
    }

    /**
     *
     * 格式化日期
     *
     * <dependency>
     *     <groupId>org.apache.commons</groupId>
     *     <artifactId>commons-lang3</artifactId>
     *     <version>3.12.0</version>
     * </dependency>
     *
     *
     *
     */
    @org.junit.jupiter.api.Test
    public void DateFormatUtils() throws ParseException {
        // Date类型转String类型
        String date = DateFormatUtils.format( new Date(), "yyyy-MM-dd HH:mm:ss" );
        System.out.println( date );
        // 输出 2021-05-01 01:01:01

        // String类型转Date类型
        Date date1 = DateUtils.parseDate( "2021-05-01 01:01:01", "yyyy-MM-dd HH:mm:ss" );
        System.out.println( date1 );

        // 计算一个小时后的日期
        Date date2 = DateUtils.addHours( new Date(), 1 );
        System.out.println( date2 );
    }

    /**
     *
     * 包装临时对象
     *
     * 当一个方法需要返回两个及以上字段时，我们一般会封装成一个临时对象返回，现在有了Pair和Triple就不需要了
     *
     */
    @org.junit.jupiter.api.Test
    public void ObjectUtils() {

        // 返回两个字段
        ImmutablePair<Integer, String> pair = ImmutablePair.of( 1, "jerry" );
        System.out.println( pair.getLeft() + "," + pair.getRight() );
        // 输出 1,jerry
        // 返回三个字段
        ImmutableTriple<Integer, String, Date> triple = ImmutableTriple.of( 1, "jerry", new Date() );
        System.out.println( triple.getLeft() + "," + triple.getMiddle() + "," + triple.getRight() );
    }


    /**
     *
     * 集合判空
     *
     * <dependency>
     *     <groupId>org.apache.commons</groupId>
     *     <artifactId>commons-collections4</artifactId>
     *     <version>4.4</version>
     * </dependency>
     *
     *
     */
    public static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(final Collection<?> coll) {
        return !isEmpty( coll );
    }

    private Collection<String> listA;
    private Collection<String> listB;

    // 两个集合取交集
    Collection<String> collection1 = CollectionUtils.retainAll( listA, listB );
    // 两个集合取并集
    Collection<String> collection2 = CollectionUtils.union( listA, listB );
    // 两个集合取差集
    Collection<String> collection3 = CollectionUtils.subtract( listA, listB );



    /**
     *
     * 设置对象属性
     *
     * <dependency>
     *     <groupId>commons-beanutils</groupId>
     *     <artifactId>commons-beanutils</artifactId>
     *     <version>1.9.4</version>
     * </dependency>
     *
     *
     */
//    @org.junit.jupiter.api.Test
//    public void beanUtils() throws Exception {
//        User user = new User();
//        BeanUtils.setProperty( user, "id", 1 );
//        BeanUtils.setProperty( user, "name", "jerry" );
//        System.out.println( BeanUtils.getProperty( user, "name" ) );
//        // 输出 jerry
//        System.out.println( user );
//        // 输出 {"id":1,"name":"jerry"}
//    }

    /**
     *
     * 对象和map互转
     *
     */
//    @org.junit.jupiter.api.Test
//    public void beanMapUtils() throws Exception {
//        User user = new User();
//        BeanUtils.setProperty( user, "id", 1 );
//        BeanUtils.setProperty( user, "name", "jerry" );
//
//        // 对象转map
//        Map<String, String> map = BeanUtils.describe( user );
//        System.out.println( map );
//        // 输出 {"id":"1","name":"jerry"}
//
//        // map转对象
//        User newUser = new User();
//        BeanUtils.populate( newUser, map );
//        System.out.println( newUser );
//        // 输出 {"id":1,"name":"jerry"}
//    }


    /**
     *
     * 设置对象属性
     *
     * <dependency>
     *     <groupId>commons-io</groupId>
     *     <artifactId>commons-io</artifactId>
     *     <version>2.8.0</version>
     * </dependency>
     *
     */
    @org.junit.jupiter.api.Test
    public void IOUtils() throws Exception {
        //File file = new File( "demo1.txt" );
        //读取文件
                //List<String> lines = FileUtils.readLines( file, Charset.defaultCharset() );
                //写入文件
        //FileUtils.writeLines( new File( "demo2.txt" ), lines );
        //复制文件
        //FileUtils.copyFile( srcFile, destFile );
    }



    /**
     * 传参CharSequence类型是String、StringBuilder、StringBuffer的父类，都可以直接下面方法判空.
     */
    @org.junit.jupiter.api.Test
    public void commonLang3Utils1() {
    }

    /**
     * commonStringUtils
     */
    @org.junit.jupiter.api.Test
    public void commonStringUtils1() {
        //首字母转成大写
        String str = "yyds";
        String capitalize = StringUtils.capitalize( str );
        System.out.println( capitalize ); // 输出 Yyds

        //重复拼接字符串
        String str1 = StringUtils.repeat( "ab", 6 );
        System.out.println( str1 ); // 输出abab
    }

    /**
     * 格式化日期
     */
    @org.junit.jupiter.api.Test
    public void DateFormatUtils1() throws ParseException {
        // Date类型转String类型
        String date = DateFormatUtils.format( new Date(), "yyyy-MM-dd HH:mm:ss" );
        System.out.println( date ); // 输出 2021-05-01 01:01:01

        // String类型转Date类型
        Date date1 = DateUtils.parseDate( "2021-05-01 01:01:01", "yyyy-MM-dd HH:mm:ss" );
        System.out.println( date1 );

        // 计算一个小时后的日期
        Date date2 = DateUtils.addHours( new Date(), 1 );
        System.out.println( date2 );
    }

    /**
     * 包装临时对象
     * 当一个方法需要返回两个及以上字段时，我们一般会封装成一个临时对象返回，现在有了Pair和Triple就不需要了
     */
    @org.junit.jupiter.api.Test
    public void ObjectUtils1() {

        // 返回两个字段
        ImmutablePair<Integer, String> pair = ImmutablePair.of( 1, "jerry" );
        System.out.println( pair.getLeft() + "," + pair.getRight() ); // 输出 1,jerry
        // 返回三个字段
        ImmutableTriple<Integer, String, Date> triple = ImmutableTriple.of( 1, "jerry", new Date() );
        System.out.println( triple.getLeft() + "," + triple.getMiddle() + "," + triple.getRight() );
    }




    @SuppressWarnings("unused")
    private static int chineseNumber2Int(String chineseNumber){
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
        char[] chArr = new char[]{'十','百','千','万','亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if(b){//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }

    /**
     * 把一个 0~9999 之间的整数转换为汉字的字符串，如果是 0 则返回 ""
     *
     * @param amountPart       数字部分
     * @param isUseTraditional 是否使用繁体单位
     * @return 转换后的汉字
     */
    private static String thousandToChinese(int amountPart, boolean isUseTraditional) {
        int temp = amountPart;

        StringBuilder chineseStr = new StringBuilder();
        boolean lastIsZero = true; // 在从低位往高位循环时，记录上一位数字是不是 0
        for (int i = 0; temp > 0; i++) {
            int digit = temp % 10;
            if (digit == 0) { // 取到的数字为 0
                if (false == lastIsZero) {
                    // 前一个数字不是 0，则在当前汉字串前加“零”字;
                    chineseStr.insert(0, "零");
                }
                lastIsZero = true;
            } else { // 取到的数字不是 0
                chineseStr.insert(0, numberToChinese(digit, isUseTraditional) + getUnitName(i, isUseTraditional));
                lastIsZero = false;
            }
            temp = temp / 10;
        }
        return chineseStr.toString();
    }

    private static final char[] DIGITS = {'零', '一', '壹', '二', '贰', '三', '叁', '四', '肆', '五', '伍',
            '六', '陆', '七', '柒', '八', '捌', '九', '玖'};


    /**
     * 汉字转阿拉伯数字的
     */
    private static final ChineseUnit[] CHINESE_NAME_VALUE = {
            new ChineseUnit(' ', 1, false),
            new ChineseUnit('十', 10, false),
            new ChineseUnit('拾', 10, false),
            new ChineseUnit('百', 100, false),
            new ChineseUnit('佰', 100, false),
            new ChineseUnit('千', 1000, false),
            new ChineseUnit('仟', 1000, false),
            new ChineseUnit('万', 1_0000, true),
            new ChineseUnit('亿', 1_0000_0000, true),
    };
    /**
     * 把中文转换为数字 如 二百二十 220<br>
     * 见：https://www.d5.nz/read/sfdlq/text-part0000_split_030.html
     * <ul>
     *     <li>一百一十二 -》 112</li>
     *     <li>一千零一十二 -》 1012</li>
     * </ul>
     *
     * @param chinese 中文字符
     * @return 数字
     * @since 5.6.0
     */
    public static int chineseToNumber(String chinese) {
        final int length = chinese.length();
        int result = 0;

        // 节总和
        int section = 0;
        int number = 0;
        CommonsLangUtilsTest.ChineseUnit unit = null;
        char c;
        for (int i = 0; i < length; i++) {
            c = chinese.charAt(i);
            final int num = chineseToNumber(c);
            if (num >= 0) {
                if (num == 0) {
                    // 遇到零时节结束，权位失效，比如两万二零一十
                    if (number > 0 && null != unit) {
                        section += number * (unit.value / 10);
                    }
                    unit = null;
                } else if (number > 0) {
                    // 多个数字同时出现，报错
                    throw new IllegalArgumentException(StrUtil.format("Bad number '{}{}' at: {}", chinese.charAt(i - 1), c, i));
                }
                // 普通数字
                number = num;
            } else {
                unit = chineseToUnit(c);
                if (null == unit) {
                    // 出现非法字符
                    throw new IllegalArgumentException(StrUtil.format("Unknown unit '{}' at: {}", c, i));
                }

                //单位
                if (unit.secUnit) {
                    // 节单位，按照节求和
                    section = (section + number) * unit.value;
                    result += section;
                    section = 0;
                } else {
                    // 非节单位，和单位前的单数字组合为值
                    int unitNumber = number;
                    if(0 == number && 0 == i){
                        // issue#1726，对于单位开头的数组，默认赋予1
                        // 十二 -> 一十二
                        // 百二 -> 一百二
                        unitNumber = 1;
                    }
                    section += (unitNumber * unit.value);
                }
                number = 0;
            }
        }

        if (number > 0 && null != unit) {
            number = number * (unit.value / 10);
        }

        return result + section + number;
    }

    /**
     * 查找对应的权对象
     *
     * @param chinese 中文权位名
     * @return 权对象
     */
    private static CommonsLangUtilsTest.ChineseUnit chineseToUnit(char chinese) {
        for (CommonsLangUtilsTest.ChineseUnit chineseNameValue : CHINESE_NAME_VALUE) {
            if (chineseNameValue.name == chinese) {
                return chineseNameValue;
            }
        }
        return null;
    }

    /**
     * 将汉字单个数字转换为int类型数字
     *
     * @param chinese 汉字数字，支持简体和繁体
     * @return 数字，-1表示未找到
     * @since 5.6.4
     */
    private static int chineseToNumber(char chinese) {
        if ('两' == chinese) {
            // 口语纠正
            chinese = '二';
        }
        final int i = ArrayUtil.indexOf(DIGITS, chinese);
        if (i > 0) {
            return (i + 1) / 2;
        }
        return i;
    }

    /**
     * 单个数字转汉字
     *
     * @param number           数字
     * @param isUseTraditional 是否使用繁体
     * @return 汉字
     */
    private static char numberToChinese(int number, boolean isUseTraditional) {
        if (0 == number) {
            return DIGITS[0];
        }
        return DIGITS[number * 2 - (isUseTraditional ? 0 : 1)];
    }

    /**
     * 获取对应级别的单位
     *
     * @param index            级别，0表示各位，1表示十位，2表示百位，以此类推
     * @param isUseTraditional 是否使用繁体
     * @return 单位
     */
    private static String getUnitName(int index, boolean isUseTraditional) {
        if (0 == index) {
            return StrUtil.EMPTY;
        }
        return String.valueOf(CHINESE_NAME_VALUE[index * 2 - (isUseTraditional ? 0 : 1)].name);
    }

    /**
     * 权位
     *
     * @author totalo
     * @since 5.6.0
     */
    private static class ChineseUnit {
        /**
         * 中文权名称
         */
        private final char name;
        /**
         * 10的倍数值
         */
        private final int value;
        /**
         * 是否为节权位，它不是与之相邻的数字的倍数，而是整个小节的倍数。<br>
         * 例如二十三万，万是节权位，与三无关，而和二十三关联
         */
        private final boolean secUnit;

        /**
         * 构造
         *
         * @param name    名称
         * @param value   值，即10的倍数
         * @param secUnit 是否为节权位
         */
        public ChineseUnit(char name, int value, boolean secUnit) {
            this.name = name;
            this.value = value;
            this.secUnit = secUnit;
        }
    }

    private static void addPreZero(StringBuilder chineseStr){
        if(StrUtil.isEmpty(chineseStr)){
            return;
        }
        final char c = chineseStr.charAt(0);
        if('零' != c){
            chineseStr.insert(0, '零');
        }
    }


    /**
     * 金额转为中文形式
     *
     * @param n 数字
     * @return 中文大写数字
     * @since 3.2.3
     */
    public static String digitToChinese(Number n) {
        if(null == n) {
            return "零";
        }
        return NumberChineseFormatter.format(n.doubleValue(), true, true);
    }

    // -------------------------------------------------------------------------- 数字转换
    /**
     * int转byte
     *
     * @param intValue int值
     * @return byte值
     * @since 3.2.0
     */
    public static byte intToByte(int intValue) {
        return (byte) intValue;
    }

    /**
     * byte转无符号int
     *
     * @param byteValue byte值
     * @return 无符号int值
     * @since 3.2.0
     */
    public static int byteToUnsignedInt(byte byteValue) {
        // Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return byteValue & 0xFF;
    }

    /**
     * byte数组转short<br>
     * 默认以小端序转换
     *
     * @param bytes byte数组
     * @return short值
     * @since 5.6.3
     */
    public static short bytesToShort(byte[] bytes) {
        return ByteUtil.bytesToShort(bytes);
    }

    /**
     * short转byte数组<br>
     * 默认以小端序转换
     *
     * @param shortValue short值
     * @return byte数组
     * @since 5.6.3
     */
    public static byte[] shortToBytes(short shortValue) {
        return ByteUtil.shortToBytes(shortValue);
    }

    /**
     * byte[]转int值<br>
     * 默认以小端序转换
     *
     * @param bytes byte数组
     * @return int值
     * @since 5.6.3
     */
    public static int bytesToInt(byte[] bytes) {
        return ByteUtil.bytesToInt(bytes);
    }

    /**
     * int转byte数组<br>
     * 默认以小端序转换
     *
     * @param intValue int值
     * @return byte数组
     * @since 5.6.3
     */
    public static byte[] intToBytes(int intValue) {
        return ByteUtil.intToBytes(intValue);
    }

    /**
     * long转byte数组<br>
     * 默认以小端序转换<br>
     * from: https://stackoverflow.com/questions/4485128/how-do-i-convert-long-to-byte-and-back-in-java
     *
     * @param longValue long值
     * @return byte数组
     * @since 5.6.3
     */
    public static byte[] longToBytes(long longValue) {
        return ByteUtil.longToBytes(longValue);
    }

    /**
     * byte数组转long<br>
     * 默认以小端序转换<br>
     * from: https://stackoverflow.com/questions/4485128/how-do-i-convert-long-to-byte-and-back-in-java
     *
     * @param bytes byte数组
     * @return long值
     * @since 5.6.3
     */
    public static long bytesToLong(byte[] bytes) {
        return ByteUtil.bytesToLong(bytes);
    }


//    private final char value[];
//    public int compareTo(String anotherString) {
//        int len1 = value.length;
//        int len2 = anotherString.value.length;
//        int lim = Math.min(len1, len2);
//        char v1[] = value;
//        char v2[] = anotherString.value;
//
//        int k = 0;
//        while (k < lim) {
//            char c1 = v1[k];
//            char c2 = v2[k];
//            if (c1 != c2) {
//                return c1 - c2;
//            }
//            k++;
//        }
//        return len1 - len2;
//    }

    /**
     * A Comparator that orders {@code String} objects as by
     * {@code compareToIgnoreCase}. This comparator is serializable.
     * <p>
     * Note that this Comparator does <em>not</em> take locale into account,
     * and will result in an unsatisfactory ordering for certain locales.
     * The java.text package provides <em>Collators</em> to allow
     * locale-sensitive ordering.
     *
     * @see     java.text.Collator#compare(String, String)
     * @since   1.2
     */

//    public static final Comparator<String> CASE_INSENSITIVE_ORDER
//            = new String.CaseInsensitiveComparator();
    private static class CaseInsensitiveComparator
            implements Comparator<String>, java.io.Serializable {
        // use serialVersionUID from JDK 1.2.2 for interoperability
        private static final long serialVersionUID = 8575799808933029326L;

        public int compare(String s1, String s2) {
            int n1 = s1.length();
            int n2 = s2.length();
            int min = Math.min(n1, n2);
            for (int i = 0; i < min; i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                if (c1 != c2) {
                    c1 = Character.toUpperCase(c1);
                    c2 = Character.toUpperCase(c2);
                    if (c1 != c2) {
                        c1 = Character.toLowerCase(c1);
                        c2 = Character.toLowerCase(c2);
                        if (c1 != c2) {
                            // No overflow because of numeric promotion
                            return c1 - c2;
                        }
                    }
                }
            }
            return n1 - n2;
        }

        /** Replaces the de-serialized object. */
//        private Object readResolve() { return CASE_INSENSITIVE_ORDER; }
    }

}

