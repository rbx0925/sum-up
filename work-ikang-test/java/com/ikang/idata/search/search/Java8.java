package com.ikang.idata.search.search;


import cn.hutool.Hutool;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.*;
import cn.hutool.cron.timingwheel.SystemTimer;
import cn.hutool.cron.timingwheel.TimerTask;
import cn.hutool.cron.timingwheel.TimingWheel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Java8Tester
 *
 * @author <a href="mailto:yanan.mu-ext@ikang.com">yanan.mu-ext</a>
 * @date 2021年08月26日
 */
public class Java8 {


    //算法
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    private static final String AES_KEY = "AnjiPLUSAjReport";
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static <T> List<T> getPageLimit(List<T> dataList, long pageNum, long pageSize) {
        return dataList.stream().skip(pageNum * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    public static void main(String args[]) {
        System.out.println("使用 Java 7: ");

        // 计算空字符串
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        System.out.println("列表: " + strings);
        long count = getCountEmptyStringUsingJava7(strings);

        System.out.println("空字符数量为: " + count);
        count = getCountLength3UsingJava7(strings);

        System.out.println("字符串长度为 3 的数量为: " + count);

        // 删除空字符串
        List<String> filtered = deleteEmptyStringsUsingJava7(strings);
        System.out.println("筛选后的列表: " + filtered);

        // 删除空字符串，并使用逗号把它们合并起来
        String mergedString = getMergedStringUsingJava7(strings, ", ");
        System.out.println("合并字符串: " + mergedString);
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        // 获取列表元素平方数
        List<Integer> squaresList = getSquares(numbers);
        System.out.println("平方数列表: " + squaresList);
        List<Integer> integers = Arrays.asList(1, 2, 13, 4, 15, 6, 17, 8, 19);

        System.out.println("列表: " + integers);
        System.out.println("列表中最大的数 : " + getMax(integers));
        System.out.println("列表中最小的数 : " + getMin(integers));
        System.out.println("所有数之和 : " + getSum(integers));
        System.out.println("平均数 : " + getAverage(integers));
        System.out.println("随机数: ");

        // 输出10个随机数
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt());
        }

        System.out.println("--------------------------------------------------");

        System.out.println("使用 Java 8: ");
        System.out.println("列表: " + strings);

        count = strings.stream().filter(String::isEmpty).count();
        System.out.println("空字符串数量为: " + count);

        count = strings.stream().filter(string -> string.length() == 3).count();
        System.out.println("字符串长度为 3 的数量为: " + count);

        filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选后的列表: " + filtered);

        mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println("Squares List: " + squaresList);
        System.out.println("列表: " + integers);

        IntSummaryStatistics stats = integers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
        System.out.println("随机数: ");

        random.ints().limit(10).sorted().forEach(System.out::println);

        // 并行处理
        count = strings.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println("空字符串的数量为: " + count);
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

    static double converter(double x, double y, double z) {
        return x * y + z;
    }

    static DoubleUnaryOperator curriedConverter(double y, double z) {
        return (double x) -> x * y + z;
    }

    static DoubleUnaryOperator expandedCurriedConverter(double w, double y, double z) {
        return (double x) -> (x + w) * y + z;
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

    static <T> T MyIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
        return b ? truecase.get() : falsecase.get();
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

    /**
     * 初始化 objectMapper 属性
     * <p>
     * 通过这样的方式，使用 Spring 创建的 ObjectMapper Bean
     *
     * @param objectMapper ObjectMapper 对象
     */
    public static void init(ObjectMapper objectMapper) {
        objectMapper = objectMapper;
    }

    public static String toJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return null;
        }
        try {
            return objectMapper.readValue(text, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        if (ArrayUtil.isEmpty(bytes)) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(text, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(text, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得服务器的IP地址
     */
    public static String getLocalIP() {
        String ipStr = "";
        InetAddress inetAddress = null;
        try {
            boolean isFindIp = false;
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                if (isFindIp) {
                    break;
                }
                NetworkInterface ni = netInterfaces
                        .nextElement();
                Enumeration<InetAddress> inetAddressList = ni.getInetAddresses();
                while (inetAddressList.hasMoreElements()) {
                    inetAddress = inetAddressList.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress.getHostAddress().matches(
                            "(\\d{1,3}\\.){3}\\d{1,3}")) {
                        isFindIp = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
        if (Objects.nonNull(inetAddress)) {
            ipStr = inetAddress.getHostAddress();
        }
        return ipStr;
    }

    /**
     * 获得服务器的IP地址(多网卡)
     */
    public static List<String> getLocalIPS() {
        InetAddress ip = null;
        List<String> ipList = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces
                        .nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = ips.nextElement();
                    if (!ip.isLoopbackAddress()
                            && ip.getHostAddress().matches(
                            "(\\d{1,3}\\.){3}\\d{1,3}")) {
                        ipList.add(ip.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
        }
        return ipList;
    }

    /**
     * 获取随机key
     *
     * @return
     */
    public static String getKey() {
        return AES_KEY;
    }

    /**
     * 将byte[]转为各种进制的字符串
     *
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes) {
        //return Base64.encodeBase64String(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        return StringUtils.isEmpty(base64Code) ? null : decoder.decode(base64Code);
    }

    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        if (StringUtils.isBlank(encryptKey)) {
            return content;
        }
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        if (StringUtils.isBlank(decryptKey)) {
            return encryptStr;
        }
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    @Test
    public void testPage() {
        ArrayList<String> list = new ArrayList<>();
        list.add("one");
        List<String> limit = getPageLimit(list, 1, 50);
        System.out.println(limit);
    }

    @Test
    public void testJava() throws InterruptedException {
        System.out.println("找找感觉");
        IntStream.range(0, 10).boxed().filter(integer -> integer > 8).forEach(System.out::println);
        //java8 运行时间
        Instant now = Instant.now();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Duration.between(now, Instant.now()).toMillis());

    }

    @Test
    public void teststart() throws InterruptedException {
        Instant strat = Instant.now();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(Duration.between(strat, Instant.now()).toMillis());

        //

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

    @Test
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

    @Test
    public void test() {
        simplify();

        Expr e = new BinOp("+", new Number(5), new BinOp("*", new Number(3), new Number(4)));
        Integer result = evaluate(e);
        System.out.println(e + " = " + result);
    }

    @Test
    public void test7Vs8() {

        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

    }

    @Test
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

    @Test
    public void testDouble() {
        DoubleStream doubleStream = DoubleStream.of(0.8D, 0.44545D, 0.446464D);
        OptionalDouble average = doubleStream.average();
//        double sum = doubleStream.sum();/
//        System.out.println(sum);
        average.ifPresent(System.out::println);
    }

    @Test
    public void testInt() {
        Stream<Integer> stream = IntStream.range(0, 100).boxed();
        System.out.println(stream);
    }

    @Test
    public void testParallel() {
        List<DoubleSummaryStatistics> statistics = IntStream.range(0, 10000).boxed().parallel()
                .map(String::valueOf).map(s -> DoubleStream.of(0.8D, 0.44545D, 0.446464D).parallel()
                        .summaryStatistics()).collect(toList());
        System.out.println(statistics.size());
        statistics.forEach(System.out::println);

//            List<ReportResultDTO> collect = subs.stream().parallel().map(workNos -> {
//            Weekend<PisCheckItemIndexResult> weekend = Weekend.of(PisCheckItemIndexResult.class);
//            WeekendCriteria<PisCheckItemIndexResult, Object> weekendCriteria = weekend.weekendCriteria();
//            weekendCriteria.andIn(PisCheckItemIndexResult::getWorkNo, workNos);
//            weekendCriteria.andIsNotNull(PisCheckItemIndexResult::getValue);
//            return pisCheckItemIndexResultMapper.selectByExample(weekend).parallelStream().map(item -> {
//                ReportResultDTO dto = new ReportResultDTO();
//                if (null == item.getValue()) {
////                            log.debug("体检指标结果为空, 该条数据忽略 workNo={} data={}", item.getWorkNo(), JSONUtil.toJsonStr(item));
//                    return null;
//                }
//                dto.setResultValue(item.getValue());
//                dto.setWorkNo(item.getWorkNo());
//                dto.setMisCode(item.getCode());
//                dto.setLowValue(item.getLowValue() != null && NumberUtils.isNumber(item.getLowValue()) ? Float.parseFloat(item.getLowValue()) : 0);
//                dto.setHighValue(item.getHighValue() != null && NumberUtils.isNumber(item.getHighValue()) ? Float.parseFloat(item.getHighValue()) : Integer.MAX_VALUE);
//                return dto;
//            }).filter(Objects::nonNull).collect(Collectors.toList());
//        }).flatMap(Collection::stream).collect(Collectors.toList());

    }

    //    @Test
    public void testParallelStream() {
        ArrayList<String> list = CollUtil.newArrayList("10", "11", "12");
        List<IntStream> intStreams = list.parallelStream().map(String::toCharArray)
                .map(chars -> String.valueOf(chars).chars()).map(IntStream::sorted).collect(toList());
        System.out.println(intStreams.size());
        intStreams.forEach(System.out::println);
    }

    @Test
    public void process() {
        //去重
        ArrayList<String> list = CollUtil.newArrayList("10", "151", "12", "151", "152");
        ArrayList<String> list1 = list.parallelStream()
                .collect(collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(String::compareTo)), ArrayList::new));
        list1.forEach(System.out::println);
        System.out.println("---------------------------------");
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("one", 10));
        personList.add(new Person("two", 20));
        personList.add(new Person("three", 30));
        //这两的name值重复
        personList.add(new Person("four", 40));
        personList.add(new Person("four", 45));

        List<Object> objects = personList.stream().parallel()
                .collect(Collectors.toMap(Person::getName, Function.identity(), (oldValue, newValue) -> oldValue))
                .values()
                .stream()
                .collect(toList());
        objects.forEach(System.out::println);
        System.out.println("----------------------------------------");
        List<Person> people = personList.stream().parallel()
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparing(Person::getName))))
                .stream()
                .collect(toList());
        people.forEach(System.out::println);

    }

    @Test
    public void merge() {
        List<Integer> listA = Arrays.asList(new Integer[]{1, 2});
        List<Integer> listB = Arrays.asList(new Integer[]{3, 4});
        List<Integer> res = Stream.of(listA, listB).flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(res.size());
        res.forEach(System.out::println);
    }

    @Test
    public void testMock() {
        Person person = mock(Person.class);
        System.out.println(person);
        //对于 特别难生成的对象 可以通过 mock 进行生成
        // 也可以设置测试桩
        Person mock = mock(Person.class, RETURNS_DEFAULTS);
        System.out.println(mock.getName());

        Person foo = mock(Person.class, RETURNS_DEEP_STUBS);

        //测试桩
        //当调用的  对应的
        when(foo.getName()).thenReturn("万恶的张三");
        System.out.println(foo.getName());

        // 你可以mock具体的类型,不仅只是接口  这个很好用
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0)).thenReturn("first");

        when(mockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0));

        System.out.println(mockedList.get(1));

        System.out.println(mockedList.get(999));

    }

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
    public void testGuavaCollect() {
        List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");
        System.out.println(theseElements);
    }

    @Test
    public void testGuavaSets() {
        //union(Set, Set) 并集
        //intersection(Set, Set) 交集
        //difference(Set, Set)  差集
        //symmetricDifference(Set, Set) 外集


        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

        Sets.SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength); // contains "two", "three", "seven"
// I can use intersection as a Set directly, but copying it can be more efficient if I use it a lot.
        ImmutableSet<String> set = intersection.immutableCopy();
        System.out.println(set);

        Sets.SetView<String> union = Sets.union(wordsWithPrimeLength, primes);
        System.out.println(union.immutableCopy());
        //左侧数据
        System.out.println(Sets.difference(wordsWithPrimeLength, primes).immutableCopy());
        //除公共以外的数据
        System.out.println(Sets.symmetricDifference(wordsWithPrimeLength, primes).immutableCopy());

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
    public void testX() {
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
    }

    @Test
    public void testIP() {
        System.out.println(getLocalIP());
        System.out.println(NumberUtil.isNumber("1"));
    }


    @Test
    public void testc() {
        System.out.println(NumberUtil.round(0.2406000000008 * 100, 2).doubleValue());
        System.out.println(NumberUtil.round(0.24060050000008 * 100, 2).toString());
        Double value = Double.valueOf("0.44444");
        System.out.println(value);
    }

    @Test
    public void testEnum() {
        RelationTest.fromString("EQ").ifPresent(relationTest -> System.out.println(relationTest.operator.apply(0.01, 0.01)));
    }

    @Test
    public void testSecretCode() throws Exception {
        String randomString = getKey();
        String content = "report";
        System.out.println("加密前：" + content);
        System.out.println("加密密钥和解密密钥：" + randomString);
        String encrypt = aesEncrypt(content, randomString);
        System.out.println("加密后：" + encrypt);
        String decrypt = aesDecrypt(encrypt, randomString);
        System.out.println("解密后：" + decrypt);
    }

    @Test
    public void testencrypt() {
        Hutool.printAllUtils();
    }

    @Test
    public void testTimingWheel() throws InterruptedException {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//        TimingWheel timingWheel = new TimingWheel(1, 8, );
        SystemTimer timer = new SystemTimer();
        TimerTask timerTask = new TimerTask(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("hello world " + LocalDateTime.now().format(timeFormatter));

                    }
                }, 5);

        timer.addTask(timerTask);
        timer.start();

        Thread.currentThread().join();

    }


    public enum RelationTest {

/*        GT {
            @Override
            public boolean apply(double value1, double value2) {
                return Double.compare(value1, value2) == 1;

            }
        }, GE {
            @Override
            public boolean apply(double value1, double value2) {
                return Double.compare(value1, value2) >= 0;
            }
        }, LT {
            @Override
            public boolean apply(double value1, double value2) {
                return Double.compare(value1, value2) == -1;
            }
        }, LE {
            @Override
            public boolean apply(double value1, double value2) {
                return Double.compare(value1, value2) <= 0;
            }
        }, EQ {
            @Override
            public boolean apply(double value1, double value2) {
                return Double.compare(value1, value2) == 0;
            }
        };*/

        GT((value1, value2) -> Double.compare(value1, value2) == 1),
        GE((value1, value2) -> Double.compare(value1, value2) >= 0),
        LT((value1, value2) -> Double.compare(value1, value2) == -1),
        LE((value1, value2) -> Double.compare(value1, value2) <= 0),
        EQ((value1, value2) -> Double.compare(value1, value2) == 0);

//        public abstract boolean apply(double value1, double value2);

        private final static Map<String, RelationTest> STRING_RELATION_TEST_MAP =
                Stream.of(values()).collect(Collectors.toMap(Object::toString, Function.identity()));
        private final BiFunction<Double, Double, Boolean> operator;

        RelationTest(BiFunction<Double, Double, Boolean> operator) {
            this.operator = operator;
        }

        public static Optional<RelationTest> fromString(String symbol) {
            return Optional.ofNullable(STRING_RELATION_TEST_MAP.get(symbol));
        }

    }

    interface MyList<T> {
        T head();

        MyList<T> tail();

        default boolean isEmpty() {
            return true;
        }

        MyList<T> filter(Predicate<T> p);
    }

    static interface TriFunction<S, T, U, R> {
        R apply(S s, T t, U u);
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

    static class Dish {

        public static final List<Dish> menu =
                Arrays.asList(new Dish("pork", false, 800, Type.MEAT),
                        new Dish("beef", false, 700, Type.MEAT),
                        new Dish("chicken", false, 400, Type.MEAT),
                        new Dish("french fries", true, 530, Type.OTHER),
                        new Dish("rice", true, 350, Type.OTHER),
                        new Dish("season fruit", true, 120, Type.OTHER),
                        new Dish("pizza", true, 550, Type.OTHER),
                        new Dish("prawns", false, 400, Type.FISH),
                        new Dish("salmon", false, 450, Type.FISH));
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

        @Override
        public String toString() {
            return name;
        }

        enum Type {MEAT, FISH, OTHER}
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


}
