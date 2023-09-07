package com.ikang.idata.search.search.util;

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
import com.ikang.idata.search.search.MapTest;
import com.ikang.idata.search.search.feign.impl.AuthorityFeignServiceImpl;
import com.ikang.idata.search.search.feign.impl.SystemUserConfigFeignServiceImpl;
import com.ikang.idata.search.search.service.ESHttpClientService;
import com.ikang.idata.search.search.service.ImpalaCheckService;
import com.ikang.idata.search.search.service.SatisfiedHospitalService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@ExtendWith(MockitoExtension.class)
class MapTestTest {

    @Mock
    private AuthorityFeignServiceImpl mockAuthorityFeignService;
    @Mock
    private ESHttpClientService mockEsHttpClientService;
    @Mock
    private SystemUserConfigFeignServiceImpl mockSystemUserConfigFeignService;
    @Mock
    private ImpalaCheckService mockImpalaCheckService;
    @Mock
    private SatisfiedHospitalService mockSatisfiedHospitalService;

    private MapTest mapTestUnderTest;

    @BeforeEach
    void setUp() {
        mapTestUnderTest = new MapTest(mockAuthorityFeignService, mockEsHttpClientService,
                mockSystemUserConfigFeignService, mockImpalaCheckService, mockSatisfiedHospitalService);
    }

    @Test
    void testStringTest1() {
        // Setup
        // Run the test
        mapTestUnderTest.StringTest1();

        // Verify the results
    }

    @Test
    public void Test1(){
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");

        List<String> zhangList = new ArrayList<>();
        for(String name : list){
            if(name.startsWith("张")){
                zhangList.add(name);
            }
        }

        List<String> shortList = new ArrayList<>();
        for(String name : zhangList){
            if(name.length() == 3){
                shortList.add(name);
            }
        }

        for (String name : shortList){
            System.out.println(name);
        }
    }

    @Test
    public void test2(){

// 1. 通过Collection系列集合的 stream() 方法或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2. 通过 Arrays 类中的静态方法 stream() 获取数组流
        String[] strArr = new String[10];
        Stream<String> stream1 = Arrays.stream(strArr);

        // 3. 通过 Stream 类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        // 4. 创建无限流，要有终止操作才有效果
        // （1）迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.forEach(System.out::println); // 不停打印，停不下来
        stream3.limit(10) // 中间操作
                .forEach(System.out::println); // 终止操作
        // （2）生成
        Stream.generate(() -> new Random().nextInt(32))
                .limit(32)
                .forEach(System.out::println);
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
    @Test
    public void String() {

        SimpleDateFormat sdf = new SimpleDateFormat("2015-12-01 12:25:45");

        try {


            Date date = sdf.parse("2015-12-01");

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(date);

            System.out.println(calendar.get(Calendar.YEAR));

            System.out.println(calendar.get(Calendar.MONTH) + 1);

            System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        } catch (ParseException e) {

            e.printStackTrace();
        }


    }



    @Test
    public void test1() {

        try {

            String times = "2019-3-22";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date date = sdf.parse(times);

            System.out.println("这个时间是" +date);

            // 获取String字符串中的年

            SimpleDateFormat y = new SimpleDateFormat("yyyy");

            System.out.println(y.format(date));

            // 获取String字符串中的月

            SimpleDateFormat m = new SimpleDateFormat("MM");

            System.out.println(m.format(date));

            // 获取String字符串中的日

            SimpleDateFormat d = new SimpleDateFormat("dd");

            System.out.println(d.format(date));

            Calendar c =Calendar.getInstance();
            System.out.println(sdf.format(c.getTime()));

            System.out.println(y.format(date)+"年"+m.format(date)+"月"+d.format(date) +"日");


        } catch (ParseException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        /*

         * Date date = new Date(); SimpleDateFormat dateFormat= new

         * SimpleDateFormat("yyyy-MM-dd"); System.out.println(dateFormat.format(date));

         */

    }

}
