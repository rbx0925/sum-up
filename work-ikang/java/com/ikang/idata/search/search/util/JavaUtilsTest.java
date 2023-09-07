package com.ikang.idata.search.search.util;

import com.google.common.collect.*;
import com.ikang.idata.common.entity.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.junit.Test;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:minxin.fan-ext@ikang.com">minxin.fan</a>
 * @version 1.0
 * @description: TODO
 * @date 2023/2/24 9:35
 */
public class JavaUtilsTest {
    /**
     *
     * List集合测试工具
     * <p>
     * List集合转换成以指定字符拼接的字符串
     *
     */
    @Test
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
     *
     * 两个List集合取交集
     *
     */
    @Test
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
     *
     * 两个List集合合并，会重复
     *
     */
    @Test
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
     *
     * 比较两个字符串是否相等，忽略大小写
     *
     */
    @Test
    public void compareIgnoreCaseUtils() {
        String strA = "helloword";
        String strB = "HELLOWORD";
        if (strA.equalsIgnoreCase( strB )) {
            System.out.println( "strA相等strB" );
        }
    }

    /**
     *
     * 比较两个对象是否相等
     *
     */
    @Test
    public void compareObjectUtils() {
        Object strA = null;
        Object strB = new JavaUtilsTest();
        // 如果strA为null就会报空指针，NullPointerException
        //if (strA.equals( strB )) {
        //    System.out.println( "strA 等于 strB" );
        //}

        // 这种方式可以避免空指针问题
        boolean result = Objects.equals( strA, strB );
        System.out.println( result );
    }


    /**
     *
     * 传参CharSequence类型是String、StringBuilder、StringBuffer的父类，都可以直接下面方法判空.
     *
     */
    @Test
    public void commonLang3Utils() {
    }

    /**
     * <dependency>
     *     <groupId>org.apache.commons</groupId>
     *     <artifactId>commons-lang3</artifactId>
     *     <version>3.12.0</version>
     * </dependency>
     */

    /**
     *
     * commonStringUtils
     *
     */
    @Test
    public void commonStringUtils() {
        //首字母转成大写
        String str = "yyds";
        //String capitalize = StringUtils.capitalize( str );
        //System.out.println( capitalize ); // 输出 Yyds
        //
        ////重复拼接字符串
        //String str1 = StringUtils.repeat( "ab", 6 );
        System.out.println( str ); // 输出abab
    }

    /**
     *
     * 格式化日期
     *
     */
    @Test
    public void DateFormatUtils() throws ParseException, ParseException {
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
     * 当一个方法需要返回两个及以上字段时，我们一般会封装成一个临时对象返回，现在有了Pair和Triple就不需要了
     *
     */
    @Test
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
     * <dependency>
     *     <groupId>org.apache.commons</groupId>
     *     <artifactId>commons-collections4</artifactId>
     *     <version>4.4</version>
     * </dependency>
     */


    /**
     *
     * 集合判空
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
     * <dependency>
     *     <groupId>commons-beanutils</groupId>
     *     <artifactId>commons-beanutils</artifactId>
     *     <version>1.9.4</version>
     * </dependency>
     */


    /**
     *
     * 设置对象属性
     *
     */
//    @Test
//    public void beanUtils() throws Exception {
//        User user = new User();
//        BeanUtils.setProperty( user, "id", 1 );
//        BeanUtils.setProperty( user, "name", "jerry" );
//        System.out.println( BeanUtils.getProperty( user, "name" ) ); // 输出 jerry
//        System.out.println( user );
//        // 输出 {"id":1,"name":"jerry"}
//    }

    /**
     *
     * 对象和map互转
     *
     */
//    @Test
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
     * <dependency>
     *     <groupId>commons-io</groupId>
     *     <artifactId>commons-io</artifactId>
     *     <version>2.8.0</version>
     * </dependency>
     */

    /**
     *
     * 设置对象属性
     *
     */
    @Test
    public void IOUtils() throws Exception {
        //File file = new File( "demo1.txt" );
        //// 读取文件
        //List<String> lines = FileUtils.readLines( file, Charset.defaultCharset() );
        ////   写入文件
        //FileUtils.writeLines( new File( "demo2.txt" ), lines );
        ////复制文件
        //FileUtils.copyFile( srcFile, destFile );
    }

    /**
     * <dependency>
     *     <groupId>com.google.guava</groupId>
     *     <artifactId>guava</artifactId>
     *     <version>30.1.1-jre</version>
     * </dependency>
     */

    /**
     *
     * 创建集合
     *
     */
    @Test
    public void createCollectionsUtils() {
        List<String> emptyList = Lists.newArrayList();

        List<Integer> list = Lists.newArrayList( 1, 2, 3 );
        System.out.println( list );

        // 反转list
        List<Integer> reverse = Lists.reverse( list );
        System.out.println( reverse );
        // 输出 [3, 2, 1]

        // list集合元素太多，可以分成若干个集合，每个集合10个元素
        List<List<Integer>> partition = Lists.partition( list, 10 );

        // 创建map
        Map<String, String> map = Maps.newHashMap();
        Set<String> set = Sets.newHashSet();
    }

    /**
     *
     * 黑科技集合
     * Multimap 一个key可以映射多个value的HashMap
     *
     */
    @Test
    public void multiMapUtils() {
        Multimap<String, Integer> map = ArrayListMultimap.create();
        map.put( "key", 1 );
        map.put( "key", 2 );
        System.out.println( map );
        // 输出 {"key":[1,2]}

        Collection<Integer> values = map.get( "key" );
        values.stream().forEach( integer -> System.out.println( integer ) );

        // 还能返回你以前使用的臃肿的Map
        Map<String, Collection<Integer>> collectionMap = map.asMap();
        System.out.println( collectionMap.toString() );
    }

    /**
     *
     * 黑科技集合
     *  BiMap 一种连value也不能重复的HashMap
     *
     */
    @Test
    public void BiMapUtils() {
        BiMap<String, String> biMap = HashBiMap.create();
        // 如果value重复，put方法会抛异常，除非用forcePut方法
        biMap.put( "key", "value" );
        System.out.println( biMap );
        // 输出 {"key":"value"}
        // 既然value不能重复，何不实现个翻转key/value的方法，已经有了
        BiMap<String, String> inverse = biMap.inverse();
        System.out.println( inverse );
        // 输出 {"value":"key"}
    }

    /**
     * 黑科技集合
     *   Table 一种有两个key的HashMap
     */
    @Test
    public void tableMapUtils() {
        // 一批用户，同时按年龄和性别分组
        Table<Integer, String, String> table = HashBasedTable.create();
        table.put( 18, "男", "jerry" );
        table.put( 18, "女", "Lily" );
        System.out.println( table.get( 18, "男" ) );
        // 输出 jerry
        // 这其实是一个二维的Map，可以查看行数据
        Map<String, String> row = table.row( 18 );
        System.out.println( row );
        // 输出 {"男":"jerry","女":"Lily"}
        // 查看列数据
        Map<Integer, String> column = table.column( "男" );
        System.out.println( column );
        // 输出 {18:"jerry"}
    }

    /**
     * 黑科技集合
     *   Multiset 一种用来计数的Set
     */
    @Test
    public void setCountUtils() {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add( "apple" );
        multiset.add( "apple" );
        multiset.add( "orange" );
        System.out.println( multiset.count( "apple" ) );
        // 输出 2
        // 查看去重的元素
        Set<String> set = multiset.elementSet();
        System.out.println( set );
        // 输出 ["orange","apple"]
        // 还能查看没有去重的元素
        Iterator<String> iterator = multiset.iterator();
        while (iterator.hasNext()) {
            System.out.println( iterator.next() );
        }
        // 还能手动设置某个元素出现的次数
        multiset.setCount( "apple", 5 );
    }

    public static String firstUpper(String fieldName) {
        if(fieldName == null || fieldName.length() < 1) {
            return fieldName;
        }
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    public static void main(String[] args) {
        System.out.println(firstUpper(null));
        System.out.println(firstUpper(""));
        System.out.println(firstUpper("a"));
        System.out.println(firstUpper("aa"));
        System.out.println(firstUpper("Aa"));
        System.out.println(firstUpper("AAaaa"));
        System.out.println(firstUpper("-aaaaa"));


        //StringUtils.capitalize(null)  = null;
        //StringUtils.capitalize("")    = "";
        //StringUtils.capitalize("cat") = "Cat";
        //StringUtils.capitalize("cAt") = "CAt";
        //StringUtils.capitalize("'cat'") = "'cat'";

        //String str = "hello";
        //System.out.println(StringUtils.capitalize(str));
        //String str1 = "World";
        //System.out.println(StringUtils.uncapitalize(str1));
    }

    /**
     *
     null

     A
     Aa
     Aa
     AAaaa
     -aaaaa
     Hello
     world
     */


    public static String getSetterName(String fieldName) {
        return "set" + firstUpper(fieldName);
    }

    public static String getGetterName(String fieldName) {
        return "get" + firstUpper(fieldName);
    }
}

