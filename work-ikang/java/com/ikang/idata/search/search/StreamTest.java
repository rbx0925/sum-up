package com.ikang.idata.search.search;

import com.alibaba.csp.sentinel.config.SentinelConfig;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.utils.CheckUtil;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.entity.OrderInfoEnum;
import com.ikang.idata.search.search.entity.vo.WorkTableSearchVo;
import org.apache.commons.lang3.ArrayUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.alibaba.cloud.sentinel.SentinelConstants.CHARSET;
import static com.alibaba.csp.sentinel.config.SentinelConfig.*;
import static com.alibaba.csp.sentinel.util.ConfigUtil.addSeparator;
import static com.ikang.idata.common.consts.MagicConst.COMMA_SPLIT;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/3/18
 */
public class StreamTest {

    //mapToInt()具有将流元素除以3的函数
    @Test
    public void aTest() {
        //过滤可以除以三的
        // Creating a list of Strings
        List<String> list = Arrays.asList("3", "6", "8", "14", "15");
        // Using Stream mapToInt(ToIntFunction mapper)
        // and displaying the corresponding IntStream
        list.stream().mapToInt(num -> Integer.parseInt(num))
                //过滤可以除以三的 余数为0的  num % 3 == 0
                .filter(num -> num % 3 == 0)
                .forEach(System.out::println);
    }

    //mapToInt()在执行具有其长度的映射字符串的操作后返回IntStream。
    @Test
    public void bTest(){
        // Creating a list of Strings
        List<String> list = Arrays.asList("Geeks", "for", "gfg", "GeeksforGeeks", "GeeksQuiz");
        // Using Stream mapToInt(ToIntFunction mapper)
        // and displaying the corresponding IntStream
        // which contains length of each element in
        // given Stream
        //字符串的长度  length
        list.stream().mapToInt(str -> str.length()).forEach(System.out::println);
    }

    //mapToLong
    @Test
    public void cTest(){
        System.out.println("The stream after applying " + "the function is:");

        // Creating a list of Strings
        List<String> list = Arrays.asList("25", "225", "1000", "20", "15");
        // Using Stream mapToLong(ToLongFunction mapper)
        // and displaying the corresponding LongStream
        list.stream().mapToLong(num -> Long.parseLong(num))
                .filter(num -> Math.sqrt(num) / 5 == 3 )  //  sqrt 求平方根函数; 根号; 函数; 开平方函数;
                .forEach(System.out::println);
    }

    @Test
    public void dTest(){
        // Creating a list of Strings
        List<String> list = Arrays.asList("Data Structures", "JAVA", "OOPS",
                "GeeksforGeeks", "Algorithms");

        // Using Stream mapToLong(ToLongFunction mapper)
        // and displaying the corresponding LongStream
        // which contains the number of one-bits in
        // binary representation of String length
            list.stream().mapToLong(str -> Long.bitCount(str.length()))
                .forEach(System.out::println);
    }

    @Test

    public void eTest() {
        List<String> collect = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void fTest(){
        // Creating a list of integers
        List<Integer> list = new ArrayList<Integer>();
        // adding elements in the list
        list.add(-2);
        list.add(0);
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(8);
        list.add(10);
        list.add(12);
        list.add(14);
        list.add(16);

        // setting the value of N as 4
        int limit = 4;
        int count = 0;
        Iterator<Integer> it = list.iterator();

        // Iterating through the list of integers
        while (it.hasNext()) {
            it.next();
            count++;

            // Check if first four i.e, (equal to N)
            // integers are iterated.
            if (count <= limit) {

                // If yes then remove first N elements.
                it.remove();
            }
        }

        System.out.print("New stream is : ");

        // Displaying new stream
        for (Integer number : list) {
            System.out.print(number + " ");
        }


    }

    /**
     * 之间的区别limit()和skip()：
     *
     * limit()方法返回减少的前N个元素流，但skip()方法在跳过前N个元素后返回其余元素流。
     * limit()是一种短路的有状态中间操作，即，当使用无限输入进行处理时，它可能会生成有限的流，而无需处理整个输入，但是skip()是有状态的中间操作，即，可能需要先处理整个输入产生结果。
     */
    @Test
    public  void gTest(){}
    class gfg {

        // Function to skip the elements of stream upto given range, i.e, 3
        public Stream<String> skip_func(Stream<String> ss, int range) {
            return ss.skip(range);
        }

        // Driver code
        public void main(String[] args) {

            // list to save stream of strings
            List<String> arr = new ArrayList<>();

            arr.add("geeks");
            arr.add("for");
            arr.add("geeks");
            arr.add("computer");
            arr.add("science");

            Stream<String> str = arr.stream();

            // calling function to skip the elements to range 3
            Stream<String> sk = skip_func(str, 3);
            sk.forEach(System.out::println);

            /**
             * computer
             * science
             */
        }

    }

//    @Test
//    public void hTest(){
//      List<Integer> list=  List.of(1,2,3,4,5,6);
//    }

    private static final int STRING_BUILDER_SIZE = 256;
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String LF = "\n";
    public static final String CR = "\r";
    public static final int INDEX_NOT_FOUND = -1;
    private static final int PAD_LIMIT = 8192;



    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isAnyEmpty(CharSequence... css) {
        if (ArrayUtils.isEmpty(css)) {
            return false;
        } else {
            CharSequence[] var1 = css;
            int var2 = css.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence cs = var1[var3];
                if (isEmpty(cs)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isNoneEmpty(CharSequence... css) {
        return !isAnyEmpty(css);
    }

    public static boolean isAllEmpty(CharSequence... css) {
        if (ArrayUtils.isEmpty(css)) {
            return true;
        } else {
            CharSequence[] var1 = css;
            int var2 = css.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence cs = var1[var3];
                if (isNotEmpty(cs)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static boolean isAnyBlank(CharSequence... css) {
        if (ArrayUtils.isEmpty(css)) {
            return false;
        } else {
            CharSequence[] var1 = css;
            int var2 = css.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence cs = var1[var3];
                if (isBlank(cs)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isNoneBlank(CharSequence... css) {
        return !isAnyBlank(css);
    }

    public static boolean isAllBlank(CharSequence... css) {
        if (ArrayUtils.isEmpty(css)) {
            return true;
        } else {
            CharSequence[] var1 = css;
            int var2 = css.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                CharSequence cs = var1[var3];
                if (isNotBlank(cs)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static String trimToEmpty(String str) {
        return str == null ? "" : str.trim();
    }

    public static String truncate(String str, int maxWidth) {
        return truncate(str, 0, maxWidth);
    }

    public static String truncate(String str, int offset, int maxWidth) {
        if (offset < 0) {
            throw new IllegalArgumentException("offset cannot be negative");
        } else if (maxWidth < 0) {
            throw new IllegalArgumentException("maxWith cannot be negative");
        } else if (str == null) {
            return null;
        } else if (offset > str.length()) {
            return "";
        } else if (str.length() > maxWidth) {
            int ix = offset + maxWidth > str.length() ? str.length() : offset + maxWidth;
            return str.substring(offset, ix);
        } else {
            return str.substring(offset);
        }
    }

    public static String strip(String str) {
        return strip(str, (String)null);
    }

    public static String stripToNull(String str) {
        if (str == null) {
            return null;
        } else {
            str = strip(str, (String)null);
            return str.isEmpty() ? null : str;
        }
    }

    public static String stripToEmpty(String str) {
        return str == null ? "" : strip(str, (String)null);
    }

    public static String strip(String str, String stripChars) {
        if (isEmpty(str)) {
            return str;
        } else {
            str = stripStart(str, stripChars);
            return stripEnd(str, stripChars);
        }
    }

    public static String stripStart(String str, String stripChars) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            int start = 0;
            if (stripChars == null) {
                while(start != strLen && Character.isWhitespace(str.charAt(start))) {
                    ++start;
                }
            } else {
                if (stripChars.isEmpty()) {
                    return str;
                }

                while(start != strLen && stripChars.indexOf(str.charAt(start)) != -1) {
                    ++start;
                }
            }

            return str.substring(start);
        } else {
            return str;
        }
    }

    public static String stripEnd(String str, String stripChars) {
        int end;
        if (str != null && (end = str.length()) != 0) {
            if (stripChars == null) {
                while(end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
                    --end;
                }
            } else {
                if (stripChars.isEmpty()) {
                    return str;
                }

                while(end != 0 && stripChars.indexOf(str.charAt(end - 1)) != -1) {
                    --end;
                }
            }

            return str.substring(0, end);
        } else {
            return str;
        }
    }

    public static String[] stripAll(String... strs) {
        return stripAll(strs, (String)null);
    }

    public static String[] stripAll(String[] strs, String stripChars) {
        int strsLen;
        if (strs != null && (strsLen = strs.length) != 0) {
            String[] newArr = new String[strsLen];

            for(int i = 0; i < strsLen; ++i) {
                newArr[i] = strip(strs[i], stripChars);
            }

            return newArr;
        } else {
            return strs;
        }
    }

    public static String stripAccents(String input) {
        if (input == null) {
            return null;
        } else {
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            StringBuilder decomposed = new StringBuilder();
            convertRemainingAccentCharacters(decomposed);
            return pattern.matcher(decomposed).replaceAll("");
        }
    }

    private static void convertRemainingAccentCharacters(StringBuilder decomposed) {
        for(int i = 0; i < decomposed.length(); ++i) {
            if (decomposed.charAt(i) == 321) {
                decomposed.deleteCharAt(i);
                decomposed.insert(i, 'L');
            } else if (decomposed.charAt(i) == 322) {
                decomposed.deleteCharAt(i);
                decomposed.insert(i, 'l');
            }
        }

    }

    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        } else if (cs1 != null && cs2 != null) {
            if (cs1.length() != cs2.length()) {
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        if (str1 != null && str2 != null) {
            if (str1 == str2) {
                return true;
            } else {
                return false;
            }
        } else {
            return str1 == str2;
        }
    }

    public static int compare(String str1, String str2) {
        return compare(str1, str2, true);
    }

    public static int compare(String str1, String str2, boolean nullIsLess) {
        if (str1 == str2) {
            return 0;
        } else if (str1 == null) {
            return nullIsLess ? -1 : 1;
        } else if (str2 == null) {
            return nullIsLess ? 1 : -1;
        } else {
            return str1.compareTo(str2);
        }
    }

    public static int compareIgnoreCase(String str1, String str2) {
        return compareIgnoreCase(str1, str2, true);
    }

    public static int compareIgnoreCase(String str1, String str2, boolean nullIsLess) {
        if (str1 == str2) {
            return 0;
        } else if (str1 == null) {
            return nullIsLess ? -1 : 1;
        } else if (str2 == null) {
            return nullIsLess ? 1 : -1;
        } else {
            return str1.compareToIgnoreCase(str2);
        }
    }

    public static boolean equalsAny(CharSequence string, CharSequence... searchStrings) {
        if (ArrayUtils.isNotEmpty(searchStrings)) {
            CharSequence[] var2 = searchStrings;
            int var3 = searchStrings.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                CharSequence next = var2[var4];
                if (equals(string, next)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean equalsAnyIgnoreCase(CharSequence string, CharSequence... searchStrings) {
        if (ArrayUtils.isNotEmpty(searchStrings)) {
            CharSequence[] var2 = searchStrings;
            int var3 = searchStrings.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                CharSequence next = var2[var4];
                if (equalsIgnoreCase(string, next)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int indexOf(CharSequence seq, int searchChar) {
        return -1;
    }

    public static int indexOf(CharSequence seq, int searchChar, int startPos) {
        return -1;
    }

    public static int indexOf(CharSequence seq, CharSequence searchSeq) {
        return -1;
    }

    public static int indexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
        return -1;
    }

    public static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
        return ordinalIndexOf(str, searchStr, ordinal, false);
    }

    private static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal, boolean lastIndex) {
        if (str != null && searchStr != null && ordinal > 0) {
            if (searchStr.length() == 0) {
                return lastIndex ? str.length() : 0;
            } else {
                int found = 0;
                int index = lastIndex ? str.length() : -1;

                do {
                    if (lastIndex) {
                        index =-1;
                    } else {
                        index = -1;
                    }

                    if (index < 0) {
                        return index;
                    }

                    ++found;
                } while(found < ordinal);

                return index;
            }
        } else {
            return -1;
        }
    }

    public static int indexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
        return indexOfIgnoreCase(str, searchStr, 0);
    }

    public static int indexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
        if (str != null && searchStr != null) {
            if (startPos < 0) {
                startPos = 0;
            }

            int endLimit = str.length() - searchStr.length() + 1;
            if (startPos > endLimit) {
                return -1;
            } else if (searchStr.length() == 0) {
                return startPos;
            } else {
                for(int i = startPos; i < endLimit; ++i) {
//                    if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
                    return i;
//                    }
                }

                return -1;
            }
        } else {
            return -1;
        }
    }


    private static int getCountEmptyStringUsingJava7(List<String> strings) {
        int count = 0;

        for (String string : strings) {

            if (string.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static int getCountLength3UsingJava7(List<String> strings) {
        int count = 0;

        for (String string : strings) {

            if (string.length() == 3) {
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
        List<String> filteredList = new ArrayList<String>();

        for (String string : strings) {

            if (!string.isEmpty()) {
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : strings) {

            if (!string.isEmpty()) {
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length() - 2);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        List<Integer> squaresList = new ArrayList<Integer>();

        for (Integer number : numbers) {
            Integer square = new Integer(number.intValue() * number.intValue());

            if (!squaresList.contains(square)) {
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers) {
        int max = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {

            Integer number = numbers.get(i);

            if (number.intValue() > max) {
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers) {
        int min = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);

            if (number.intValue() < min) {
                min = number.intValue();
            }
        }
        return min;
    }

    private static int getSum(List numbers) {
        int sum = (int) (numbers.get(0));

        for (int i = 1; i < numbers.size(); i++) {
            sum += (int) numbers.get(i);
        }
        return sum;
    }

    private static int getAverage(List<Integer> numbers) {
        return getSum(numbers) / numbers.size();
    }


    @org.junit.Test
    public void testM() {
        int[] nums = {1, 2, 3};
        int sum2 = IntStream.of(nums).parallel().sum();
        System.out.println("结果为：" + sum2);
    }


    @org.junit.Test
    public void testJava8() {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
        DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

        System.out.println(convertCtoF.applyAsDouble(24));
        System.out.println(convertUSDtoGBP.applyAsDouble(100));
        System.out.println(convertKmtoMi.applyAsDouble(20));

        DoubleUnaryOperator convertFtoC = expandedCurriedConverter(-32, 5.0 / 9, 0);
        System.out.println(convertFtoC.applyAsDouble(98.6));
    }

    static double converter(double x, double y, double z) {
        return x * y + z;
    }

    static DoubleUnaryOperator curriedConverter(double y, double z) {
        return (double x) -> x * y + z;
    }

    static DoubleUnaryOperator expandedCurriedConverter(double w, double y, double z) {
        return (double x) -> (x + w) * y + z;
    }

    @org.junit.Test
    public void testCe() {

        MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<Integer>()));

        System.out.println(l.head());

        LazyList<Integer> numbers = from(2);
        int two = numbers.head();
        int three = numbers.tail().head();
        int four = numbers.tail().tail().head();
        System.out.println(two + " " + three + " " + four);

        numbers = from(2);
        int prime_two = primes(numbers).head();
        int prime_three = primes(numbers).tail().head();
        int prime_five = primes(numbers).tail().tail().head();
        System.out.println(prime_two + " " + prime_three + " " + prime_five);

        // this will run until a stackoverflow occur because Java does not
        // support tail call elimination
        // printAll(primes(from(2)));
    }


    interface MyList<T> {
        T head();

        MyList<T> tail();

        default boolean isEmpty() {
            return true;
        }

        MyList<T> filter(Predicate<T> p);
    }

    static class MyLinkedList<T> implements MyList<T> {
        final T head;
        final MyList<T> tail;

        public MyLinkedList(T head, MyList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public MyList<T> tail() {
            return tail;
        }

        public boolean isEmpty() {
            return false;
        }

        public MyList<T> filter(Predicate<T> p) {
            return isEmpty() ? this : p.test(head()) ? new MyLinkedList<>(
                    head(), tail().filter(p)) : tail().filter(p);
        }
    }

    static class Empty<T> implements MyList<T> {
        public T head() {
            throw new UnsupportedOperationException();
        }

        public MyList<T> tail() {
            throw new UnsupportedOperationException();
        }

        public MyList<T> filter(Predicate<T> p) {
            return this;
        }
    }

    static class LazyList<T> implements MyList<T> {
        final T head;
        final Supplier<MyList<T>> tail;

        public LazyList(T head, Supplier<MyList<T>> tail) {
            this.head = head;
            this.tail = tail;
        }

        public T head() {
            return head;
        }

        public MyList<T> tail() {
            return tail.get();
        }

        public boolean isEmpty() {
            return false;
        }

        public MyList<T> filter(Predicate<T> p) {
            return isEmpty() ? this : p.test(head()) ? new LazyList<>(head(),
                    () -> tail().filter(p)) : tail().filter(p);
        }

    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<Integer>(n, () -> from(n + 1));
    }

    public static MyList<Integer> primes(MyList<Integer> numbers) {
        return new LazyList<>(numbers.head(), () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
    }

    static <T> void printAll(MyList<T> numbers) {
        if (numbers.isEmpty()) {
            return;
        }
        System.out.println(numbers.head());
        printAll(numbers.tail());
    }


    @org.junit.Test
    public void test() {
        simplify();

        Expr e = new BinOp("+", new Number(5), new BinOp("*", new Number(3), new Number(4)));
        Integer result = evaluate(e);
        System.out.println(e + " = " + result);
    }

    private static void simplify() {
        TriFunction<String, Expr, Expr, Expr> binopcase =
                (opname, left, right) -> {
                    if ("+".equals(opname)) {
                        if (left instanceof Number && ((Number) left).val == 0) {
                            return right;
                        }
                        if (right instanceof Number && ((Number) right).val == 0) {
                            return left;
                        }
                    }
                    if ("*".equals(opname)) {
                        if (left instanceof Number && ((Number) left).val == 1) {
                            return right;
                        }
                        if (right instanceof Number && ((Number) right).val == 1) {
                            return left;
                        }
                    }
                    return new BinOp(opname, left, right);
                };
        Function<Integer, Expr> numcase = val -> new Number(val);
        Supplier<Expr> defaultcase = () -> new Number(0);

        Expr e = new BinOp("+", new Number(5), new Number(0));
        Expr match = patternMatchExpr(e, binopcase, numcase, defaultcase);
        if (match instanceof Number) {
            System.out.println("Number: " + match);
        } else if (match instanceof BinOp) {
            System.out.println("BinOp: " + match);
        }
    }

    private static Integer evaluate(Expr e) {
        Function<Integer, Integer> numcase = val -> val;
        Supplier<Integer> defaultcase = () -> 0;
        TriFunction<String, Expr, Expr, Integer> binopcase =
                (opname, left, right) -> {
                    if ("+".equals(opname)) {
                        if (left instanceof Number && right instanceof Number) {
                            return ((Number) left).val + ((Number) right).val;
                        }
                        if (right instanceof Number && left instanceof BinOp) {
                            return ((Number) right).val + evaluate((BinOp) left);
                        }
                        if (left instanceof Number && right instanceof BinOp) {
                            return ((Number) left).val + evaluate((BinOp) right);
                        }
                        if (left instanceof BinOp && right instanceof BinOp) {
                            return evaluate((BinOp) left) + evaluate((BinOp) right);
                        }
                    }
                    if ("*".equals(opname)) {
                        if (left instanceof Number && right instanceof Number) {
                            return ((Number) left).val * ((Number) right).val;
                        }
                        if (right instanceof Number && left instanceof BinOp) {
                            return ((Number) right).val * evaluate((BinOp) left);
                        }
                        if (left instanceof Number && right instanceof BinOp) {
                            return ((Number) left).val * evaluate((BinOp) right);
                        }
                        if (left instanceof BinOp && right instanceof BinOp) {
                            return evaluate((BinOp) left) * evaluate((BinOp) right);
                        }
                    }
                    return defaultcase.get();
                };

        return patternMatchExpr(e, binopcase, numcase, defaultcase);
    }

    static class Expr {
    }

    static class Number extends Expr {
        int val;

        public Number(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "" + val;
        }
    }

    static class BinOp extends Expr {
        String opname;
        Expr left, right;

        public BinOp(String opname, Expr left, Expr right) {
            this.opname = opname;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "(" + left + " " + opname + " " + right + ")";
        }
    }

    static <T> T MyIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
        return b ? truecase.get() : falsecase.get();
    }

    static interface TriFunction<S, T, U, R> {
        R apply(S s, T t, U u);
    }

    static <T> T patternMatchExpr(Expr e,
                                  TriFunction<String, Expr, Expr, T> binopcase,
                                  Function<Integer, T> numcase, Supplier<T> defaultcase) {

        if (e instanceof BinOp) {
            return binopcase.apply(((BinOp) e).opname, ((BinOp) e).left, ((BinOp) e).right);
        } else if (e instanceof Number) {
            return numcase.apply(((Number) e).val);
        } else {
            return defaultcase.get();
        }
    }


    static class Dish {

        private final String name;
        private final boolean vegetarian;
        private final int calories;
        private final Type type;

        public Dish(String name, boolean vegetarian, int calories, Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public int getCalories() {
            return calories;
        }

        public Type getType() {
            return type;
        }

        enum Type {MEAT, FISH, OTHER}

        @Override
        public String toString() {
            return name;
        }

        public static final List<Dish> menu =
                Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
                        new Dish("beef", false, 700, Dish.Type.MEAT),
                        new Dish("chicken", false, 400, Dish.Type.MEAT),
                        new Dish("french fries", true, 530, Dish.Type.OTHER),
                        new Dish("rice", true, 350, Dish.Type.OTHER),
                        new Dish("season fruit", true, 120, Dish.Type.OTHER),
                        new Dish("pizza", true, 550, Dish.Type.OTHER),
                        new Dish("prawns", false, 400, Dish.Type.FISH),
                        new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    @org.junit.Test
    public void test7Vs8() {

        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : dishes) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        lowCaloricDishes.sort(Comparator.comparingInt(Dish::getCalories));
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }

    @org.junit.Test
    /**
     * 聚合
     */
    public void testReduce() {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int calories = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);
    }

    @org.junit.Test
    public void testDouble() {
        DoubleStream doubleStream = DoubleStream.of(0.8D, 0.44545D, 0.446464D);
        OptionalDouble average = doubleStream.average();
//        double sum = doubleStream.sum();/
//        System.out.println(sum);
        average.ifPresent(System.out::println);
    }


    @org.junit.Test
    public void testInt() {
        Stream<Integer> stream = IntStream.range(0, 100).boxed();
        System.out.println(stream);
    }

    @org.junit.Test
    /**
     * 开始
     */
    public void testParallel() {
        List<DoubleSummaryStatistics> statistic;
    }

    /**
     * 测试
     * @param searchVO
     * @param consumer
     * @return
     */
    private SearchSourceBuilder getSearchSourceBuilder(WorkTableSearchVo searchVO, Consumer<List<QueryBuilder>> consumer) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(MagicConst.INT_0);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        List<QueryBuilder> must = boolQuery.must();
        //拼接条件
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmZone()),
                () -> must.add(
                        QueryBuilders.termsQuery(OrderInfoEnum.brm_zone.getFields(), searchVO.getBrmZone().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmDepartment()),
                () -> must.add(
                        QueryBuilders.termsQuery(OrderInfoEnum.brm_department.getFields(), searchVO.getBrmDepartment().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmGroup()),
                () -> must.add(
                        QueryBuilders.termsQuery(OrderInfoEnum.brm_group.getFields(), searchVO.getBrmGroup().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmUserId()),
                () -> must.add(
                        QueryBuilders.wildcardQuery(OrderInfoEnum.leaderid.getFields(), ("*<" + searchVO.getBrmUserId() + ">*"))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getChiefZone()),
                () -> must.add(
                        QueryBuilders.termsQuery(OrderInfoEnum.chief_zone.getFields(), searchVO.getChiefZone().split(COMMA_SPLIT))));
        consumer.accept(must);
        builder.query(boolQuery);
        return builder;
    }


    @org.junit.Test
    public void testDefaultConfig() {
        assertEquals(SentinelConfig.DEFAULT_CHARSET, SentinelConfig.charset());
        assertEquals(SentinelConfig.DEFAULT_SINGLE_METRIC_FILE_SIZE, SentinelConfig.singleMetricFileSize());
        assertEquals(SentinelConfig.DEFAULT_TOTAL_METRIC_FILE_COUNT, SentinelConfig.totalMetricFileCount());
        assertEquals(SentinelConfig.DEFAULT_COLD_FACTOR, SentinelConfig.coldFactor());
        assertEquals(SentinelConfig.DEFAULT_STATISTIC_MAX_RT, SentinelConfig.statisticMaxRt());
    }

    //    add JVM parameter
//    -Dcsp.sentinel.charset=gbk
//    -Dcsp.sentinel.metric.file.single.size=104857600
//    -Dcsp.sentinel.metric.file.total.count=10
//    -Dcsp.sentinel.flow.cold.factor=5
//    -Dcsp.sentinel.statistic.max.rt=10000
//    @Test
    public void testCustomConfig() {
        assertEquals("gbk", SentinelConfig.charset());
        assertEquals(104857600L, SentinelConfig.singleMetricFileSize());
        assertEquals(10, SentinelConfig.totalMetricFileCount());
        assertEquals(5, SentinelConfig.coldFactor());
        assertEquals(10000, SentinelConfig.statisticMaxRt());
    }


    /**
     * when set code factor alue equal or smaller than 1, get value
     * in SentinelConfig.coldFactor() will return DEFAULT_COLD_FACTOR
     * see {@link SentinelConfig#coldFactor()}
     */
    @org.junit.Test
    public void testColdFactorEqualOrSmallerThanOne() {
        SentinelConfig.setConfig(SentinelConfig.COLD_FACTOR, "0.5");
        assertEquals(SentinelConfig.DEFAULT_COLD_FACTOR, SentinelConfig.coldFactor());

        SentinelConfig.setConfig(SentinelConfig.COLD_FACTOR, "1");
        assertEquals(SentinelConfig.DEFAULT_COLD_FACTOR, SentinelConfig.coldFactor());
    }

    @org.junit.Test
    public void testColdFactoryLargerThanOne() {
        SentinelConfig.setConfig(SentinelConfig.COLD_FACTOR, "2");
        assertEquals(2, SentinelConfig.coldFactor());

        SentinelConfig.setConfig(SentinelConfig.COLD_FACTOR, "4");
        assertEquals(4, SentinelConfig.coldFactor());
    }


    //add Jvm parameter
    //-Dcsp.sentinel.config.file=classpath:sentinel-propertiesTest.properties
    //-Dcsp.sentinel.flow.cold.factor=5
    //-Dcsp.sentinel.statistic.max.rt=1000
    //@Test
    public void testLoadProperties() throws IOException {

        File file = null;
        String fileName = "sentinel-propertiesTest.properties";
        try {
            file = new File(addSeparator(System.getProperty("user.dir")) + "target/classes/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write(buildPropertyStr(CHARSET, "utf-8"));
            out.write("\n");
            out.write(buildPropertyStr(SINGLE_METRIC_FILE_SIZE, "1000"));
            out.write("\n");
            out.write(buildPropertyStr(TOTAL_METRIC_FILE_COUNT, "20"));
            out.write("\n");
            out.write(buildPropertyStr(COLD_FACTOR, "123"));
            out.write("\n");
            out.write(buildPropertyStr(STATISTIC_MAX_RT, "6000"));
            out.write("\n");
            out.write(buildPropertyStr(PROJECT_NAME_PROP_KEY, "sentinel_test"));
            out.flush();
            out.close();

            Assert.assertTrue(SentinelConfig.getConfig(CHARSET).equals("utf-8"));
            Assert.assertTrue(SentinelConfig.getConfig(SINGLE_METRIC_FILE_SIZE).equals("1000"));
            Assert.assertTrue(SentinelConfig.getConfig(TOTAL_METRIC_FILE_COUNT).equals("20"));
            Assert.assertTrue(SentinelConfig.getConfig(COLD_FACTOR).equals("5"));
            Assert.assertTrue(SentinelConfig.getConfig(STATISTIC_MAX_RT).equals("1000"));
            Assert.assertTrue(SentinelConfig.getAppName().equals("sentinel_test"));

        } finally {
            if (file != null) {
                file.delete();
            }
        }


    }

    private String buildPropertyStr(String key, String value) {
        return key + "=" + value;
    }


    /**
     * 测试
     * @param
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/6/27 17:06
     */
    private SearchSourceBuilder getSearchSourceBuilderOne(WorkTableSearchVo searchVO, Consumer<List<QueryBuilder>> consumer) {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.size(MagicConst.INT_0);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        List<QueryBuilder> must = boolQuery.must();
        //拼接条件
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmZone()),
                () -> must.add(
                        QueryBuilders.termsQuery(
                                OrderInfoEnum.brm_zone.getFields(), searchVO.getBrmZone().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmDepartment()),
                () -> must.add(
                        QueryBuilders.termsQuery(OrderInfoEnum.brm_department.getFields(), searchVO.getBrmDepartment().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmGroup()),
                () -> must.add(
                        QueryBuilders.termsQuery(OrderInfoEnum.brm_group.getFields(), searchVO.getBrmGroup().split(MagicConst.COMMA_SPLIT))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getBrmUserId()),
                () -> must.add(
                        QueryBuilders.wildcardQuery(OrderInfoEnum.leaderid.getFields(), ("*<" + searchVO.getBrmUserId() + ">*"))));
        CheckUtil.isTrue(StringUtil.isNotEmpty(searchVO.getChiefZone()),
                () -> must.add(
                        QueryBuilders.termsQuery(OrderInfoEnum.chief_zone.getFields(), searchVO.getChiefZone().split(COMMA_SPLIT))));
        consumer.accept(must);
        builder.query(boolQuery);
        return builder;
    }


    /**
     * next() 与 nextLine() 区别
     * next():
     *
     * 1、一定要读取到有效字符后才可以结束输入。
     * 2、对输入有效字符之前遇到的空白，next() 方法会自动将其去掉。
     * 3、只有输入有效字符后才将其后面输入的空白作为分隔符或者结束符。
     * next() 不能得到带有空格的字符串。
     * nextLine()：
     *
     * 1、以Enter为结束符,也就是说 nextLine()方法返回的是输入回车之前的所有字符。
     * 2、可以获得空白
     */
    //使用 next 方法
    @Test
    public void nextTest() {
        Scanner scan = new Scanner(System.in);
        // 从键盘接收数据

        // next方式接收字符串
        System.out.println("next方式接收：");
        // 判断是否还有输入
        if (scan.hasNext()) {
            String str1 = scan.next();
            System.out.println("输入的数据为：" + str1);
        }
        scan.close();
    }

    /**
     * $ javac ScannerDemo.java
     * $ java ScannerDemo
     * next方式接收：
     * runoob com
     * 输入的数据为：runoob
     */

    //使用 nextLine 方法：
    @Test
    public void nextLineTest() {
        Scanner scan = new Scanner(System.in);
        // 从键盘接收数据

        // next方式接收字符串
        System.out.println("next方式接收：");
        // 判断是否还有输入
        if (scan.hasNext()) {
            String str1 = scan.next();
            System.out.println("输入的数据为：" + str1);
        }
        scan.close();
    }
    /**
     * $ javac ScannerDemo.java
     * $ java ScannerDemo
     * nextLine方式接收：
     * runoob com
     * 输入的数据为：runoob com
     */

    @Test
    public  void nextFloatTest() {
        Scanner scan = new Scanner(System.in);
        // 从键盘接收数据
        int i = 0;
        float f = 0.0f;
        System.out.print("输入整数：");
        if (scan.hasNextInt()) {
            // 判断输入的是否是整数
            i = scan.nextInt();
            // 接收整数
            System.out.println("整数数据：" + i);
        } else {
            // 输入错误的信息
            System.out.println("输入的不是整数！");
        }
        System.out.print("输入小数：");
        if (scan.hasNextFloat()) {
            // 判断输入的是否是小数
            f = scan.nextFloat();
            // 接收小数
            System.out.println("小数数据：" + f);
        } else {
            // 输入错误的信息
            System.out.println("输入的不是小数！");
        }
        scan.close();
    }
    /**
     * $ javac ScannerDemo.java
     * $ java ScannerDemo
     * 输入整数：12
     * 整数数据：12
     * 输入小数：1.2
     * 小数数据：1.2
     */

    @Test
    public void StreamTest(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Predicate<Integer> predicate = n -> true
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // n 如果存在则 test 方法返回 true

        System.out.println("输出所有数据:");

        // 传递参数 n
        eval(list, n->true);

        // Predicate<Integer> predicate1 = n -> n%2 == 0
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n%2 为 0 test 方法返回 true

        System.out.println("输出所有偶数:");
        eval(list, n-> n%2 == 0 );

        // Predicate<Integer> predicate2 = n -> n > 3
        // n 是一个参数传递到 Predicate 接口的 test 方法
        // 如果 n 大于 3 test 方法返回 true

        System.out.println("输出大于 3 的所有数字:");
        eval(list, n-> n > 3 );
    }

    public static void eval(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list) {

            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }

        /**
         * $ javac Java8Tester.java
         * $ java Java8Tester
         * 输出所有数据:
         * 1
         * 2
         * 3
         * 4
         * 5
         * 6
         * 7
         * 8
         * 9
         * 输出所有偶数:
         * 2
         * 4
         * 6
         * 8
         * 输出大于 3 的所有数字:
         * 4
         * 5
         * 6
         * 7
         * 8
         * 9
         */
    }

}
