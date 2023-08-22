package com.ikang.idata.search.search.service;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.collect.Multimap;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/4/28
 */
public class EncryptTest {
    public static void main(String[] args) {
        String key = "cbde!@#$2018";
        String encode = encrypt("{\"code\":\"-98\",\"message\":\"项目已初始化，请重复初始化\",\"serverTime\":\"2018-11-20T17:42:56.227+0800\"}", key);
        System.out.println(encode);
        System.out.println(decrypt(encode, key));
        //System.out.println(new String(hexStringToBytes("1A200C1325AD890FC3")));
    }

    /**
     * 信息加密.
     * @param sSrc
     * @param sKey
     * @return
     */
    public static String encrypt(String sSrc, String sKey) {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }

        try {
            byte[] raw = hex(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] sSrcByte = sSrc.getBytes("utf-8");
            //自补码
            Integer paddingCount = 8 - (sSrcByte.length % 8);
            byte[] toEncrypt = new byte[sSrcByte.length + paddingCount];
            for (int m=0; m < sSrcByte.length; m++) {
                toEncrypt[m] = sSrcByte[m];
            }
            if (paddingCount > 0) {
                for(int i = 0; i < paddingCount; i++) {
                    toEncrypt[sSrcByte.length + i] = "0".getBytes("utf-8")[0];
                }
            }


            byte[] encrypted = cipher.doFinal(toEncrypt);

            return bytesToHexString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param sSrc
     * @param sKey
     * @return
     */
    public static String encryptPKCS5Padding(String sSrc, String sKey) {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        try {
            byte[] raw = hex(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

            return bytesToHexString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 信息解密。
     * @param sSrc
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String decrypt(String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            byte[] raw = hex(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = hexStringToBytes(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                String pre = originalString.substring(0, originalString.length() - 8);
                String after = originalString.substring(originalString.length() - 8, originalString.length());
                for (int i=7; i>-1; i--) {
                    if (!"0".equals(after.substring(i, i+1))) {
                        break;
                    }
                    after = after.substring(0, i);
                }
                return pre + after;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * 解密.
     * @param sSrc
     * @param sKey
     * @return
     */
    public static String decryptPKCS5Padding (String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            byte[] raw = hex(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = hexStringToBytes(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     *Convert byte[] to hex string.
     *@param src byte[]data
     *@return hex string
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if(src==null||src.length<=0){
            return null;
        }
        for(int i=0;i<src.length;i++){
            int v=src[i]&0xFF;
            String hv=Integer.toHexString(v);
            if(hv.length()<2){
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * Convert hex string to byte[].
     * @param hexString the hexstring
     * @return byte[]
     * */
    public static byte[] hexStringToBytes(String hexString){
        if(hexString == null|| hexString.equals("")){
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length()/2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for(int i=0;i<length;i++){
            int pos=i*2;
            d[i] = (byte)(charToByte(hexChars[pos])<<4|charToByte(hexChars[pos+1]));
        }
        return d;
    }

    /**
     *Convertchartobyte
     *@paramcchar
     *@returnbyte
     */
    public static byte charToByte(char c){
        return (byte)"0123456789ABCDEF".indexOf(c);
    }

    /**
     * string to hex
     * @param key
     * @return
     */
    public static byte[] hex(String key){
        byte[] bkeys = new String(key).getBytes();
        byte[] enk = new byte[24];
        for (int i=0;i<24;i++){
            enk[i] = (i < bkeys.length) ? bkeys[i] : 0x00;
        }
        return enk;
    }


    //算法
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    private static final String AES_KEY = "AnjiPLUSAjReport";
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @Test
    public void math(){
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

//    public static Java8.LazyList<Integer> from(int n) {
//        return new Java8.LazyList<Integer>(n, () -> from(n + 1));
//    }

//    public static Java8.MyList<Integer> primes(Java8.MyList<Integer> numbers) {
//        return new Java8.LazyList<>(numbers.head(), () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
//    }

//    static <T> void printAll(Java8.MyList<T> numbers) {
//        if (numbers.isEmpty()) {
//            return;
//        }
//        System.out.println(numbers.head());
//        printAll(numbers.tail());
//    }

//    private static void simplify() {
//        Java8.TriFunction<String, Java8.Expr, Java8.Expr, Java8.Expr> binopcase =
//                (opname, left, right) -> {
//                    if ("+".equals(opname)) {
//                        if (left instanceof Java8.Number && ((Java8.Number) left).val == 0) {
//                            return right;
//                        }
//                        if (right instanceof Java8.Number && ((Java8.Number) right).val == 0) {
//                            return left;
//                        }
//                    }
//                    if ("*".equals(opname)) {
//                        if (left instanceof Java8.Number && ((Java8.Number) left).val == 1) {
//                            return right;
//                        }
//                        if (right instanceof Java8.Number && ((Java8.Number) right).val == 1) {
//                            return left;
//                        }
//                    }
//                    return new Java8.BinOp(opname, left, right);
//                };
//        Function<Integer, Java8.Expr> numcase = val -> new Java8.Number(val);
//        Supplier<Java8.Expr> defaultcase = () -> new Java8.Number(0);
//
//        Java8.Expr e = new Java8.BinOp("+", new Java8.Number(5), new Java8.Number(0));
//        Java8.Expr match = patternMatchExpr(e, binopcase, numcase, defaultcase);
//        if (match instanceof Java8.Number) {
//            System.out.println("Number: " + match);
//        } else if (match instanceof Java8.BinOp) {
//            System.out.println("BinOp: " + match);
//        }
//    }
//
//    private static Integer evaluate(Java8.Expr e) {
//        Function<Integer, Integer> numcase = val -> val;
//        Supplier<Integer> defaultcase = () -> 0;
//        Java8.TriFunction<String, Java8.Expr, Java8.Expr, Integer> binopcase =
//                (opname, left, right) -> {
//                    if ("+".equals(opname)) {
//                        if (left instanceof Java8.Number && right instanceof Java8.Number) {
//                            return ((Java8.Number) left).val + ((Java8.Number) right).val;
//                        }
//                        if (right instanceof Java8.Number && left instanceof Java8.BinOp) {
//                            return ((Java8.Number) right).val + evaluate((Java8.BinOp) left);
//                        }
//                        if (left instanceof Java8.Number && right instanceof Java8.BinOp) {
//                            return ((Java8.Number) left).val + evaluate((Java8.BinOp) right);
//                        }
//                        if (left instanceof Java8.BinOp && right instanceof Java8.BinOp) {
//                            return evaluate((Java8.BinOp) left) + evaluate((Java8.BinOp) right);
//                        }
//                    }
//                    if ("*".equals(opname)) {
//                        if (left instanceof Java8.Number && right instanceof Java8.Number) {
//                            return ((Java8.Number) left).val * ((Java8.Number) right).val;
//                        }
//                        if (right instanceof Java8.Number && left instanceof Java8.BinOp) {
//                            return ((Java8.Number) right).val * evaluate((Java8.BinOp) left);
//                        }
//                        if (left instanceof Java8.Number && right instanceof Java8.BinOp) {
//                            return ((Java8.Number) left).val * evaluate((Java8.BinOp) right);
//                        }
//                        if (left instanceof Java8.BinOp && right instanceof Java8.BinOp) {
//                            return evaluate((Java8.BinOp) left) * evaluate((Java8.BinOp) right);
//                        }
//                    }
//                    return defaultcase.get();
//                };
//
//        return patternMatchExpr(e, binopcase, numcase, defaultcase);
//    }
//
//    static <T> T MyIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
//        return b ? truecase.get() : falsecase.get();
//    }
//
//    static <T> T patternMatchExpr(Java8.Expr e,
//                                  Java8.TriFunction<String, Java8.Expr, Java8.Expr, T> binopcase,
//                                  Function<Integer, T> numcase, Supplier<T> defaultcase) {
//
//        if (e instanceof Java8.BinOp) {
//            return binopcase.apply(((Java8.BinOp) e).opname, ((Java8.BinOp) e).left, ((Java8.BinOp) e).right);
//        } else if (e instanceof Java8.Number) {
//            return numcase.apply(((Java8.Number) e).val);
//        } else {
//            return defaultcase.get();
//        }
//    }
//
//    public static List<String> getLowCaloricDishesNamesInJava7(List<Java8.Dish> dishes) {
//        List<Java8.Dish> lowCaloricDishes = new ArrayList<>();
//        for (Java8.Dish d : dishes) {
//            if (d.getCalories() < 400) {
//                lowCaloricDishes.add(d);
//            }
//        }
//        List<String> lowCaloricDishesName = new ArrayList<>();
//        lowCaloricDishes.sort(Comparator.comparingInt(Java8.Dish::getCalories));
//        for (Java8.Dish d : lowCaloricDishes) {
//            lowCaloricDishesName.add(d.getName());
//        }
//        return lowCaloricDishesName;
//    }
//
//    public static List<String> getLowCaloricDishesNamesInJava8(List<Java8.Dish> dishes) {
//        return dishes.stream()
//                .filter(d -> d.getCalories() < 400)
//                .sorted(comparing(Java8.Dish::getCalories))
//                .map(Java8.Dish::getName)
//                .collect(toList());
//    }

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

    @org.junit.jupiter.api.Test
    public void testM() {
        int[] nums = {1, 2, 3};
        int sum2 = IntStream.of(nums).parallel().sum();
        System.out.println("结果为：" + sum2);
    }

    @org.junit.jupiter.api.Test
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

//    @org.junit.jupiter.api.Test
//    public void testCe() {
//
//        Java8.MyList<Integer> l = new Java8.MyLinkedList<>(5, new Java8.MyLinkedList<>(10, new Java8.Empty<Integer>()));
//
//        System.out.println(l.head());
//
//        Java8.LazyList<Integer> numbers = from(2);
//        int two = numbers.head();
//        int three = numbers.tail().head();
//        int four = numbers.tail().tail().head();
//        System.out.println(two + " " + three + " " + four);
//
//        numbers = from(2);
//        int prime_two = primes(numbers).head();
//        int prime_three = primes(numbers).tail().head();
//        int prime_five = primes(numbers).tail().tail().head();
//        System.out.println(prime_two + " " + prime_three + " " + prime_five);
//
//        // this will run until a stackoverflow occur because Java does not
//        // support tail call elimination
//        // printAll(primes(from(2)));
//    }

//    @Test
//    public void test() {
//        simplify();
//
//        Java8.Expr e = new Java8.BinOp("+", new Java8.Number(5), new Java8.BinOp("*", new Java8.Number(3), new Java8.Number(4)));
//        Integer result = evaluate(e);
//        System.out.println(e + " = " + result);
//    }

}
