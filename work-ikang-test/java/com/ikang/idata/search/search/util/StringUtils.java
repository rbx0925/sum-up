package com.ikang.idata.search.search.util;

import com.google.common.collect.*;
import com.ikang.idata.common.entity.User;
import jodd.util.StringPool;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2023/1/29
 */
public class StringUtils {

    /**
     * 空字符
     */
    public static final String EMPTY = StringPool.EMPTY;
    /**
     * 字符串 is
     */
    public static final String IS = "is";
    /**
     * 下划线字符
     */
    public static final char UNDERLINE = '_';
    /**
     * MP 内定义的 SQL 占位符表达式，匹配诸如 {0},{1},{2} ... 的形式
     */
    public final static Pattern MP_SQL_PLACE_HOLDER = Pattern.compile("[{](?<idx>\\d+)}");
    /**
     * 验证字符串是否是数据库字段
     */
    private static final Pattern P_IS_COLUMN = Pattern.compile("^\\w\\S*[\\w\\d]*$");

    /**
     * 是否为大写命名
     */
    private static final Pattern CAPITAL_MODE = Pattern.compile("^[0-9A-Z/_]+$");

    /**
     * 判断字符串中是否全是空白字符
     *
     * @param cs 需要判断的字符串
     * @return 如果字符串序列是 null 或者全是空白，返回 true
     */
    public static boolean isBlank1(CharSequence cs) {
        if (cs != null) {
            int length = cs.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 对象转为字符串去除左右空格
     *
     * @param o 带转换对象
     * @return
     */
    public static String toStringTrim(Object o) {
        return String.valueOf(o).trim();
    }

    /**
     * @see #isBlank(CharSequence)
     */
    public static boolean isNotBlank1(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 判断字符串是不是驼峰命名
     *
     * <li> 包含 '_' 不算 </li>
     * <li> 首字母大写的不算 </li>
     *
     * @param str 字符串
     * @return 结果
     */
    public static boolean isCamel(String str) {
        return Character.isLowerCase(str.charAt(0)) && !str.contains(StringPool.UNDERSCORE);
    }

    /**
     * 判断字符串是否符合数据库字段的命名
     *
     * @param str 字符串
     * @return 判断结果
     */
    public static boolean isNotColumnName(String str) {
        return !P_IS_COLUMN.matcher(str).matches();
    }

    /**
     * 获取真正的字段名
     *
     * @param column 字段名
     * @return 字段名
     */
    public static String getTargetColumn(String column) {
        if (isNotColumnName(column)) {
            return column.substring(1, column.length() - 1);
        }
        return column;
    }

    /**
     * 字符串驼峰转下划线格式
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String camelToUnderline(String param) {
        if (isBlank(param)) {
            return EMPTY;
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append(UNDERLINE);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 字符串下划线转驼峰格式
     *
     * @param param 需要转换的字符串
     * @return 转换好的字符串
     */
    public static String underlineToCamel(String param) {
        if (isBlank(param)) {
            return EMPTY;
        }
        String temp = param.toLowerCase();
        int len = temp.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = temp.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(temp.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isAllEmpty(final CharSequence... css) {
        if (ArrayUtils.isEmpty(css)) {
            return true;
        }
        for (final CharSequence cs : css) {
            if (isNotEmpty(cs)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a CharSequence is empty (""), null or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is null, empty or whitespace only
     * @since 2.0
     * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a CharSequence is not empty (""), not null and not whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param cs  the CharSequence to check, may be null
     * @return {@code true} if the CharSequence is
     *  not empty and not null and not whitespace only
     * @since 2.0
     * @since 3.0 Changed signature from isNotBlank(String) to isNotBlank(CharSequence)
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * <p>Checks if any of the CharSequences are empty ("") or null or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isAnyBlank((String) null)    = true
     * StringUtils.isAnyBlank((String[]) null)  = false
     * StringUtils.isAnyBlank(null, "foo")      = true
     * StringUtils.isAnyBlank(null, null)       = true
     * StringUtils.isAnyBlank("", "bar")        = true
     * StringUtils.isAnyBlank("bob", "")        = true
     * StringUtils.isAnyBlank("  bob  ", null)  = true
     * StringUtils.isAnyBlank(" ", "bar")       = true
     * StringUtils.isAnyBlank(new String[] {})  = false
     * StringUtils.isAnyBlank(new String[]{""}) = true
     * StringUtils.isAnyBlank("foo", "bar")     = false
     * </pre>
     *
     * @param css  the CharSequences to check, may be null or empty
     * @return {@code true} if any of the CharSequences are empty or null or whitespace only
     * @since 3.2
     */
    public static boolean isAnyBlank(final CharSequence... css) {
        if (ArrayUtils.isEmpty(css)) {
            return false;
        }
        for (final CharSequence cs : css) {
            if (isBlank(cs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <p>Checks if none of the CharSequences are empty (""), null or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isNoneBlank((String) null)    = false
     * StringUtils.isNoneBlank((String[]) null)  = true
     * StringUtils.isNoneBlank(null, "foo")      = false
     * StringUtils.isNoneBlank(null, null)       = false
     * StringUtils.isNoneBlank("", "bar")        = false
     * StringUtils.isNoneBlank("bob", "")        = false
     * StringUtils.isNoneBlank("  bob  ", null)  = false
     * StringUtils.isNoneBlank(" ", "bar")       = false
     * StringUtils.isNoneBlank(new String[] {})  = true
     * StringUtils.isNoneBlank(new String[]{""}) = false
     * StringUtils.isNoneBlank("foo", "bar")     = true
     * </pre>
     *
     * @param css  the CharSequences to check, may be null or empty
     * @return {@code true} if none of the CharSequences are empty or null or whitespace only
     * @since 3.2
     */
    public static boolean isNoneBlank(final CharSequence... css) {
        return !isAnyBlank(css);
    }

    /**
     * <p>Checks if all of the CharSequences are empty (""), null or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isAllBlank(null)             = true
     * StringUtils.isAllBlank(null, "foo")      = false
     * StringUtils.isAllBlank(null, null)       = true
     * StringUtils.isAllBlank("", "bar")        = false
     * StringUtils.isAllBlank("bob", "")        = false
     * StringUtils.isAllBlank("  bob  ", null)  = false
     * StringUtils.isAllBlank(" ", "bar")       = false
     * StringUtils.isAllBlank("foo", "bar")     = false
     * StringUtils.isAllBlank(new String[] {})  = true
     * </pre>
     *
     * @param css  the CharSequences to check, may be null or empty
     * @return {@code true} if all of the CharSequences are empty or null or whitespace only
     * @since 3.6
     */
    public static boolean isAllBlank(final CharSequence... css) {
        if (ArrayUtils.isEmpty(css)) {
            return true;
        }
        for (final CharSequence cs : css) {
            if (isNotBlank(cs)) {
                return false;
            }
        }
        return true;
    }

    // Trim
    //-----------------------------------------------------------------------

    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }


    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }



    public static boolean isNumeric(String toString) {

        boolean isNumeric=StringUtils.isNumeric(toString);

        System.out.println(isNumeric);//false

        isNumeric=StringUtils.isNumeric("1234dd");

        System.out.println("1234dd:"+isNumeric);//false

        isNumeric=StringUtils.isNumeric("1234 ");

        System.out.println("1234 :"+isNumeric);//false

        isNumeric=StringUtils.isNumeric("1.234");

        System.out.println("1.234:"+isNumeric);//false

        isNumeric=StringUtils.isNumeric("1234");

        System.out.println("1234:" + isNumeric);//true

        isNumeric=StringUtils.isNumeric("-1234");

        System.out.println("-1234:" + isNumeric);//false

//判断是否是正整数或空格

        return isNumeric;
    }


    /**
     * 集合判空
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
     * 设置对象属性
     */
//    @Test
//    public void beanUtils() throws Exception {
//        User user = new User();
//        BeanUtils.setProperty( user, "id", 1 );
//        BeanUtils.setProperty( user, "name", "jerry" );
//        System.out.println( BeanUtils.getProperty( user, "name" ) ); // 输出 jerry
//        System.out.println( user ); // 输出 {"id":1,"name":"jerry"}
//    }

    /**
     * 对象和map互转
     */
//    @Test
//    public void beanMapUtils() throws Exception {
//        User user = new User();
//        BeanUtils.setProperty( user, "id", 1 );
//        BeanUtils.setProperty( user, "name", "jerry" );
//
//        // 对象转map
//        Map<String, String> map = BeanUtils.describe( user );
//        System.out.println( map ); // 输出 {"id":"1","name":"jerry"}
//
//        // map转对象
//        User newUser = new User();
//        BeanUtils.populate( newUser, map );
//        System.out.println( newUser ); // 输出 {"id":1,"name":"jerry"}
//    }
    /**
     * 创建集合
     */
    @Test
    public void createCollectionsUtils() {
        List<String> emptyList = Lists.newArrayList();

        List<Integer> list = Lists.newArrayList( 1, 2, 3 );
        System.out.println( list );

        // 反转list
        List<Integer> reverse = Lists.reverse( list );
        System.out.println( reverse ); // 输出 [3, 2, 1]

        // list集合元素太多，可以分成若干个集合，每个集合10个元素
        List<List<Integer>> partition = Lists.partition( list, 10 );

        // 创建map
        Map<String, String> map = Maps.newHashMap();
        Set<String> set = Sets.newHashSet();
    }

    /**
     * 黑科技集合
     * Multimap 一个key可以映射多个value的HashMap
     */
    @Test
    public void multiMapUtils() {
        Multimap<String, Integer> map = ArrayListMultimap.create();
        map.put( "key", 1 );
        map.put( "key", 2 );
        System.out.println( map ); // 输出 {"key":[1,2]}

        Collection<Integer> values = map.get( "key" );
        values.stream().forEach( integer -> System.out.println( integer ) );

        // 还能返回你以前使用的臃肿的Map
        Map<String, Collection<Integer>> collectionMap = map.asMap();
        System.out.println( collectionMap.toString() );
    }

    /**
     * 黑科技集合
     *  BiMap 一种连value也不能重复的HashMap
     */
    @Test
    public void BiMapUtils() {
        BiMap<String, String> biMap = HashBiMap.create();
        // 如果value重复，put方法会抛异常，除非用forcePut方法
        biMap.put( "key", "value" );
        System.out.println( biMap ); // 输出 {"key":"value"}
        // 既然value不能重复，何不实现个翻转key/value的方法，已经有了
        BiMap<String, String> inverse = biMap.inverse();
        System.out.println( inverse ); // 输出 {"value":"key"}
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
        System.out.println( table.get( 18, "男" ) ); // 输出 jerry
        // 这其实是一个二维的Map，可以查看行数据
        Map<String, String> row = table.row( 18 );
        System.out.println( row ); // 输出 {"男":"jerry","女":"Lily"}
        // 查看列数据
        Map<Integer, String> column = table.column( "男" );
        System.out.println( column ); // 输出 {18:"jerry"}
    }
}

