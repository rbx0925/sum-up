package com.ikang.idata.search.search;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/4/8
 */
public class AssertTrueTest {

    /**
     * assertEquals和assertTrue区别
     * 1、相同点：都能判断两个值是否相等
     * assertTrue如果为 true ，则运行success，反之Failure
     * <p>
     * assertEquals如果预期值与真实值相等，则运行success，反之Failure
     * <p>
     * 2、不同点：
     * assertEquals运行Failure会有错误提示，提示预期值是xxx，而实际值是xxx。容易调试
     * <p>
     * assertTrue没有错误提示
     */

    @Test
    public void test1() {
        int i = 5;
        assert i == 6;
        System.out.println("如果断言正常,我就打印 ");
    }

    @Test
    public void test2() {
        int i = 5;

        switch (i) {
            case 1:
                System.out.println("正常");
                break;

            case 2:
                System.out.println("正常");
                break;
            default:
                assert false : "i的值无效";
        }
        System.out.println("如果断言正常,我就打印");
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


    @Test
    public void testM() {
        int[] nums = {1, 2, 3};
        int sum2 = IntStream.of(nums).parallel().sum();
        System.out.println("结果为：" + sum2);
    }


    @Test
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


    @Data
    private class Person {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getAddress(String anyString) {
            return "万能的张三";
        }
    }

    @Test
    public void merge() {
        List<Integer> listA = Arrays.asList(new Integer[]{1, 2});
        List<Integer> listB = Arrays.asList(new Integer[]{3, 4});
        List<Integer> res = Stream.of(listA, listB).flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(res.size());
        res.forEach(System.out::println);
    }

//    @Test
//    public void testMock() {
//        Java8Tester.Person person = mock(Java8Tester.Person.class);
//        System.out.println(person);
//        //对于 特别难生成的对象 可以通过 mock 进行生成
//        // 也可以设置测试桩
//        Java8Tester.Person mock = mock(Java8Tester.Person.class, RETURNS_DEFAULTS);
//        System.out.println(mock.getName());
//
//        Java8Tester.Person foo = mock(Java8Tester.Person.class, RETURNS_DEEP_STUBS);
//
//        //测试桩
//        //当调用的  对应的
//        when(foo.getName()).thenReturn("万恶的张三");
//        System.out.println(foo.getName());
//
//        // 你可以mock具体的类型,不仅只是接口  这个很好用
//        LinkedList mockedList = mock(LinkedList.class);
//
//        when(mockedList.get(0)).thenReturn("first");
//
//        when(mockedList.get(1)).thenThrow(new RuntimeException());
//
//        System.out.println(mockedList.get(0));
//
//        System.out.println(mockedList.get(1));
//
//        System.out.println(mockedList.get(999));
//
//    }

    @Test
    public void testMockTwo() {
        LinkedList list = mock(LinkedList.class);
        //多个调用返回的情况
        when(list.size()).thenReturn(1, 2, 3);
        //1
        System.out.println(list.size());
        //2
        System.out.println(list.size());
        //3
        System.out.println(list.size());
        // 超过次数后 使用的 最后的  注意
        System.out.println(list.size());
    }

    @Test
    public void testInOrder() {
        //执行顺序  对于调试
        List mock = mock(List.class);
        mock.add("fast execution");
        mock.add("second execution");

        InOrder order = inOrder(mock);
        //当运行顺序不一样的时候 会出现 Argument(s) are different! Wanted:
        order.verify(mock).add(" execution");
        order.verify(mock).add("second execution");
    }

    @Test
    public void testNumberOfCalls() {
        List list = mock(List.class);
        list.add("罗老师");
        list.add("张三");
        list.add("张三的一生");
        list.add("脱口秀");
        list.add("交个朋友");
        list.add("交个朋友");

        verify(list).add("罗老师");
        //执行次数
        verify(list, times(2)).add("交个朋友");
    }

    @Test
    public void testAssertThat() {
        List<Integer> list = mock(List.class);

        // 断言list.get(0)值等于1
        assertThat(list.get(0)).isEqualTo(1);

        // 断言大于50
        assertThat(list.get(0)).isGreaterThan(20);
        // 断言小于等于50
        assertThat(list.get(0)).isLessThanOrEqualTo(50);

        // 断言 必须大于20 并且 小于等于50（所有条件成立）
//        assertThat(list.get(0), allOf(greaterThan(20), lessThanOrEqualTo(50)));
        // 断言 必须大于20 或 小于等于50（其中至少一个条件成立）
//        assertThat(list.get(0), oneOf(greaterThan(20), lessThanOrEqualTo(50)));

        // 断言任何条件都成立
//        assertThat(list.get(0), anything());
        // 断言等于1
//        assertThat(list.get(0), is(1));
        // 断言不等于-1
//        assertThat(list.get(0), not(-1));
        // 断言返回的字符串包含1
//        assertThat(list.get(0), containsString("1"));
        // 断言返回的字符串以1开头
//        assertThat(list.get(0), startsWith("1"));
//         断言该异常属于RuntimeException
//        assertThat(e, instanceOf(RuntimeException.class));
    }


    @Test
    public void testContinuous() {
        IntStream intStream = IntStream.of(2020, 2018, 2017).sorted();
//        intStream.forEach(System.out::println);
        System.out.println(IsContinuous(intStream.toArray()));
    }

    public static Boolean IsContinuous(int[] arr) {
        if (arr.length < 3) {
            return false;
        }

        boolean b = false;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] * 2 != arr[i - 1] + arr[i + 1]) {
                b = false;
                break;
            }
            if (Math.abs(arr[i + 1] - arr[i]) != 1) {
                b = false;
                break;
            }
            if ((arr[i + 1] - arr[i]) != (arr[i] - arr[i - 1])) {
                b = false;
                break;
            }
            b = true;
        }
        return b;
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
        //通常使用
        String result1 = StrFormatter.format("this is {} for {}", "a", "b");
        Assert.assertEquals("this is a for b", result1);

        //转义{}
        String result2 = StrFormatter.format("this is \\{} for {}", "a", "b");
        Assert.assertEquals("this is {} for a", result2);

        //转义\
        String result3 = StrFormatter.format("this is \\\\{} for {}", "a", "b");
        Assert.assertEquals("this is \\a for b", result3);


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

    //解析结果
    @Test
    public void testJSongString() {
//        String jsonString = getString();
//        Object parse = JSONObject.parse(jsonString);
//        JSONArray jsonArray = ((JSONArray) JSON.toJSON(JSONPath.eval(parse,"$.aggregations.newoldthype.buckets")));
//        for (Object o : jsonArray) {
//
//            System.out.println(Double.parseDouble(JSONPath.eval(o,"$money.value").toString()));
//        }

    }


    /**
     * 使用 StringBuffer 类时，每次都会对 StringBuffer 对象本身进行操作，而不是生成新的对象，所以如果需要对字符串进行修改推荐使用 StringBuffer。
     * <p>
     * StringBuilder 类在 Java 5 中被提出，它和 StringBuffer 之间的最大不同在于 StringBuilder 的方法不是线程安全的（不能同步访问）。
     * <p>
     * 由于 StringBuilder 相较于 StringBuffer 有速度优势，所以多数情况下建议使用 StringBuilder 类。
     */
    @Test
    public void RunoobTest() {
        StringBuilder sb = new StringBuilder(10);
        sb.append("Runoob..");
        System.out.println(sb);
        sb.append("!");
        System.out.println(sb);
        sb.insert(8, "Java");
        System.out.println(sb);
        sb.delete(5, 8);
        System.out.println(sb);

        /**
         * Runoob..
         * Runoob..!
         * Runoob..Java!
         * RunooJava!
         */


    }


    @Test
    public void StringBufferTest() {
        StringBuilder StringBuilder = new StringBuilder("sdfghjkl876543gfdsahgfds");
        System.out.println("打印的值为" + StringBuilder);
        //1	public StringBuffer append(String s)   将指定的字符串追加到此字符序列。
        StringBuilder.append("加油加油");
        System.out.println(StringBuilder);
        //2	public StringBuffer reverse()   将此字符序列用其反转形式取代。
        java.lang.StringBuilder reverse = StringBuilder.reverse();
        System.out.println("打印的值为" + reverse);
        //3	public delete(int start, int end)移除此序列的子字符串中的字符。
        java.lang.StringBuilder deleteStr = StringBuilder.delete(1, 2);
        System.out.println("打印的值为" + deleteStr);
        //public insert(int offset, int i)将 int 参数的字符串表示形式插入此序列中。
        java.lang.StringBuilder insertStr = StringBuilder.insert(8, 000000);
        System.out.println("打印的值为" + insertStr);
        //insert(int offset, String str)将 str 参数的字符串插入此序列中
        java.lang.StringBuilder insert = StringBuilder.insert(9, "学习");
        System.out.println("打印的值为" + insert);
        //replace(int start, int end, String str) 使用给定 String 中的字符替换此序列的子字符串中的字符。
        java.lang.StringBuilder hahahaha = StringBuilder.replace(11, 13, "hahahaha");
        System.out.println("打印的值为" + hahahaha);
        //int capacity() 返回当前容量。
        int capacity = StringBuilder.capacity();
        System.out.println("打印的值为" + capacity);
        //char charAt(int index)返回此序列中指定索引处的 char 值。
        char c = StringBuilder.charAt(16);
        System.out.println("打印的值为" + c);
        //void ensureCapacity(int minimumCapacity)确保容量至少等于指定的最小值。
        StringBuilder.ensureCapacity(1);
        //void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)将字符从此序列复制到目标字符数组 dst。
        //int indexOf(String str) 返回第一次出现的指定子字符串在该字符串中的索引。
        int a = StringBuilder.indexOf("a");
        System.out.println("打印的值为" + a);
        //int indexOf(String str, int fromIndex)从指定的索引处开始，返回第一次出现的指定子字符串在该字符串中的索引。
        int a1 = StringBuilder.indexOf("a", 4);
        System.out.println("打印的值为" + a1);
        //int lastIndexOf(String str) 返回最右边出现的指定子字符串在此字符串中的索引
        int i = StringBuilder.lastIndexOf("8");
        System.out.println("打印的值为" + i);
        //int lastIndexOf(String str, int fromIndex)返回 String 对象中子字符串最后出现的位置。
        int a2 = StringBuilder.lastIndexOf("a", 4);
        System.out.println("打印的值为" + a2);
        //int length()返回长度（字符数）。
        int length = StringBuilder.length();
        System.out.println("打印的值为" + length);
        //void setCharAt(int index, char ch)将给定索引处的字符设置为 ch。
        //void setLength(int newLength)设置字符序列的长度。
        StringBuilder.setLength(9);
        //CharSequence subSequence(int start, int end)返回一个新的字符序列，该字符序列是此序列的子序列。
        CharSequence subSequence = StringBuilder.subSequence(0, 8);
        System.out.println("打印的值为" + subSequence);
        //String substring(int start) 返回一个新的 String，它包含此字符序列当前所包含的字符子序列。
        String substring = StringBuilder.substring(4);
        System.out.println("打印的值为" + substring);
        //String substring(int start, int end)返回一个新的 String，它包含此序列当前所包含的字符子序列。
        String substring1 = StringBuilder.substring(2, 4);
        System.out.println("打印的值为" + substring1);
        //String toString()返回此序列中数据的字符串表示形式
        String s = StringBuilder.toString();
        System.out.println("打印的值为" + s);

        /**
         * 打印的值为sdfghjkl876543gfdsahgfds
         * sdfghjkl876543gfdsahgfds加油加油
         * 打印的值为油加油加sdfghasdfg345678lkjhgfds
         * 打印的值为油油加sdfghasdfg345678lkjhgfds
         * 打印的值为油油加sdfgh0asdfg345678lkjhgfds
         * 打印的值为油油加sdfgh0学习asdfg345678lkjhgfds
         * 打印的值为油油加sdfgh0学习hahahahadfg345678lkjhgfds
         * 打印的值为40
         * 打印的值为a
         * 打印的值为12
         * 打印的值为12
         * 打印的值为27
         * 打印的值为-1
         * 打印的值为36
         * 打印的值为油油加sdfgh
         * 打印的值为dfgh0
         * 打印的值为加s
         * 打印的值为油油加sdfgh0
         */

    }

    //正则表达
    @Test
    public void RegexExample1() {

        String content = "I am noob " +
                "from runoob.com.";
        String pattern = ".*runoob.*";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);

        /**
         * 字符串中是否包含了 'runoob' 子字符串? true
         */
    }


    @Test
    public void RegexMatches(){
            // 按指定模式在字符串查找
            String line = "This order was placed for QT3000! OK?";
            String pattern = "(\\D*)(\\d+)(.*)";
            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);
            // 现在创建 matcher 对象
            Matcher m = r.matcher(line);
            if (m.find( )) {
                System.out.println("Found value: " + m.group(0) );
                System.out.println("Found value: " + m.group(1) );
                System.out.println("Found value: " + m.group(2) );
                System.out.println("Found value: " + m.group(3) );
            } else {
                System.out.println("NO MATCH");
            }
        }

    /**
     * Found value: This order was placed for QT3000! OK?
     * Found value: This order was placed for QT
     * Found value: 3000
     * Found value: ! OK?
     */



    @Test
    public void Matches() {
         final String REGEX = "\\bcat\\b";
         final String INPUT = "cat cat cat cattie cat";
            Pattern p = Pattern.compile(REGEX);
            Matcher m = p.matcher(INPUT); // 获取 matcher 对象
            int count = 0;
            while(m.find()) {
                count++;
                System.out.println("Match number "+count);
                System.out.println("start(): "+m.start());
                System.out.println("end(): "+m.end());
            }
        }

    /**
     * Match number 1
     * start(): 0
     * end(): 3
     * Match number 2
     * start(): 4
     * end(): 7
     * Match number 3
     * start(): 8
     * end(): 11
     * Match number 4
     * start(): 19
     * end(): 22
     */


    @org.junit.Test
    public void isEmpty(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ list.isEmpty());
        list = null;
        System.out.println("判断list是否为null:"+ list.isEmpty());
        /**
         *
         *
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
         *
         *
         */
    }

    @org.junit.Test
    public void nullTest(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ (list == null));
        list = null;
        System.out.println("判断list是否为null:"+ (list == null));
        /**
         *
         * 判断list是否为空:false
         *
         * 判断list是否为null:true
         *
         */
    }

    @org.junit.Test
    public void CollectionUtilsTest(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ CollectionUtils.isEmpty(list));
        list = null;
        System.out.println("判断list是否为null:"+ CollectionUtils.isEmpty(list));
        /**
         *
         * 判断list是否为空:true
         *
         * 判断list是否为null:true
         *
         */
    }

    @org.junit.Test
    public void StringUtilsTest(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ StringUtils.isEmpty(list));
        list = null;
        System.out.println("判断list是否为null:"+ StringUtils.isEmpty(list));
        /**
         *
         * 判断list是否为空:false
         *
         * 判断list是否为null:true
         *
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
         *
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
         *
         *
         */
    }

    @org.junit.Test
    public void MapUtilsTest(){
        Map map = new HashMap<String ,String>();
        System.out.println("判断map是否为空:"+ MapUtils.isEmpty(map));
        map = null;
        System.out.println("判断map是否为null:"+ MapUtils.isEmpty(map));
        /**
         *
         * 判断map是否为空:true
         *
         * 判断map是否为null:true
         *
         */
    }

    @org.junit.Test
    public void CollectionUtilsTest2(){
        Map map1 = new HashMap<String ,String>();
        System.out.println("判断map是否为空:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
        map1 = null;
        System.out.println("判断map是否为null:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
        /**
         *
         * 判断map是否为空:true
         *
         * 判断map是否为null:true
         *
         */
    }

    @org.junit.Test
    public void Test(){
        Map map1 = new HashMap<String ,String>();
        System.out.println("判断map是否为空:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
        map1 = null;
        System.out.println("判断map是否为null:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
        /**
         *
         * 判断map是否为空:true
         *
         * 判断map是否为null:true
         *
         */
    }

    @org.junit.Test
    public void test333(){
        String say = "hello";

        int flag  = 0;

        if(flag == 0){
            System.out.println(say);
        }

        if(flag == 1){
            System.out.println("world");
        }else {
            System.out.println("ok");
        }

        StringBuffer sb = new StringBuffer();
        sb.append("zi")
                .append("xin")
                .append("huang")
                .append("zhong")
                .append("ren");

        String str = "a,b,c,,";
        String[] ary = str.split(",");
        System.out.println(ary.length);
        // 3


        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
        String [] array = new String[list.size()];
        array = list.toArray(array);
        System.out.println(array);

    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2023, 2, 1);
        System.out.println(date.lengthOfMonth());
        LocalDate loopEnd = date.withDayOfMonth(date.lengthOfMonth());
        System.out.println("月的最后一天"+loopEnd);


        LocalDate year = date.withDayOfYear(date.lengthOfYear());
        System.out.println("年的最后一天"+year);
    }


}
