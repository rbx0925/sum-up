package com.ikang.idata.search.search.javaTest;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.primitives.Ints;
import com.ikang.idata.common.utils.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.junit.Test;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cn.hutool.core.collection.ListUtil.unmodifiable;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/7/13
 */
@Slf4j
public class ArrayTest {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = null;
        System.out.println("结果1"+ CollectionUtils.isEmpty(list1));
        System.out.println("结果2"+ CollectionUtils.isEmpty(list2));
        System.out.println("结果3"+ list1.isEmpty());
        System.out.println("结果4"+ list2.isEmpty());
        /**
         * 结果1true
         * 结果2true
         * Exception in thread "main" java.lang.NullPointerException
         * 结果3true
         * 	at com.ikang.op2c.test.ArrayTest.main(ArrayTest.java:25)
         */

    }

    @org.junit.Test
    public void isEmpty(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ list.isEmpty());
        list = null;
        System.out.println("判断list是否为null:"+ list.isEmpty());
        /**
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
         */
    }

    @org.junit.Test
    public void nullTest(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ (list == null));
        list = null;
        System.out.println("判断list是否为null:"+ (list == null));
        /**
         * 判断list是否为空:false
         * 判断list是否为null:true
         */
    }


    @org.junit.Test
    public void CollectionUtilsTest(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ CollectionUtils.isEmpty(list));
        list = null;
        System.out.println("判断list是否为null:"+ CollectionUtils.isEmpty(list));
        /**
         * 判断list是否为空:true
         * 判断list是否为null:true
         */
    }

    @org.junit.Test
    public void StringUtilsTest(){
        List<String> list = new ArrayList<>();
        System.out.println("判断list是否为空:"+ StringUtils.isEmpty(list));
        list = null;
        System.out.println("判断list是否为null:"+ StringUtils.isEmpty(list));
        /**
         * 判断list是否为空:false
         * 判断list是否为null:true
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
         */
    }

    @org.junit.Test
    public void nullTest1(){
        Map map = new HashMap<String ,String>();
        System.out.println("判断map是否为空:"+ (map == null));
        map = null;
        System.out.println("判断map是否为null:"+ (map == null));
        /**
         * 判断map是否为空:false
         * 判断map是否为null:true
         */
    }

    @org.junit.Test
    public void MapUtilsTest(){
        Map map = new HashMap<String ,String>();
        System.out.println("判断map是否为空:"+ MapUtils.isEmpty(map));
        map = null;
        System.out.println("判断map是否为null:"+ MapUtils.isEmpty(map));
        /**
         * 判断map是否为空:true
         * 判断map是否为null:true
         */
    }

    @org.junit.Test
    public void CollectionUtilsTest2(){
        Map map1 = new HashMap<String ,String>();
        System.out.println("判断map是否为空:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
        map1 = null;
        System.out.println("判断map是否为null:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
        /**
         * 判断map是否为空:true
         * 判断map是否为null:true
         */
    }

    @Test
    public void Test(){
        Map map1 = new HashMap<String ,String>();
        System.out.println("判断map是否为空:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
        map1 = null;
        System.out.println("判断map是否为null:"+ org.springframework.util.CollectionUtils.isEmpty(map1));
        /**
         * 判断map是否为空:true
         * 判断map是否为null:true
         */
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

    @org.junit.jupiter.api.Test
    public void testc() {
        System.out.println(NumberUtil.round(0.2406000000008 * 100, 2).doubleValue());
        System.out.println(NumberUtil.round(0.24060050000008 * 100, 2).toString());
        Double value = Double.valueOf("0.44444");
        System.out.println(value);
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

        private final BiFunction<Double, Double, Boolean> operator;

        private final static Map<String, RelationTest> STRING_RELATION_TEST_MAP =
                Stream.of(values()).collect(Collectors.toMap(Object::toString, Function.identity()));

        RelationTest(BiFunction<Double, Double, Boolean> operator) {
            this.operator = operator;
        }

        public static Optional<RelationTest> fromString(String symbol) {
            return Optional.ofNullable(STRING_RELATION_TEST_MAP.get(symbol));
        }

    }

    @org.junit.jupiter.api.Test
    public void testEnum() {
        RelationTest.fromString("EQ").ifPresent(relationTest -> System.out.println(relationTest.operator.apply(0.01, 0.01)));
    }

    //算法
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    private static final String AES_KEY = "AnjiPLUSAjReport";


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

    @Test
    public void test(){
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

        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
        String [] array = new String[list.size()];
        array = list.toArray(array);
        System.out.println(array);
        /**
         * hello
         * ok
         * 3
         * [Ljava.lang.String;@a4102b8
         */


    }
    @org.junit.jupiter.api.Test
    public void testPrimitives() {
        List<Integer> integers = Ints.asList(1, 2, 3, 4);
        System.out.println(integers);
        /**
         *
         */
    }

    /**
     * 加密数据的 iv
     */
    private static final String DATA_IV = "ikangidata$%^&*(";
    /**
     * 加密数据的盐
     */
    public static final String DATA_SALT = "ikangidata%&(+||";

    /**
     * @author
     * @Description AES算法解密密文
     * @param data 密文
     * @return 明文
     */
    public static String decryptAES(String data) {
        try{
            String lowerCase = data.toLowerCase();
            byte[] hexStr2Byte = parseHexStr2Byte(lowerCase);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(DATA_SALT.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(DATA_IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(hexStr2Byte);
            String originalString = new String(original, StandardCharsets.UTF_8);
            return originalString.trim();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
        return null;
    }
    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    @Test
    public  void AesUtil() {
        String s = AesUtil.decryptAES("45FDB20556A2D8BE318CA086CD3C210A");
        System.out.println("s = " + s);
        /**
         *  s = 667890o
         */
    }

    /**
     * 两个对象值的对比（objA为对比）
     * @param objA
     * @param objB
     * @return
     */
    public static List<String> compareValue(Object objA, Object objB) {
        // 只要比较有一个为空，就返回Null 不做比较
        if(objA==null || objB==null) {
            return null;
        }

        //当前类差异集合
        List<String> differenceAttrs = new ArrayList<String>();
        try {
            Class<?> clazzA = objA.getClass();
            Class<?> clazzB = objB.getClass();
            Method[] methods = clazzA.getDeclaredMethods();
            Object resultA = null;
            Object resultB = null;
            Method methodB = null;
            for(Method method:methods) {
                if(method.getName().startsWith("get")) {
                    methodB = clazzB.getMethod(method.getName(), null);
                    resultB = methodB.invoke(objB, null);
                    resultA = method.invoke(objA, null);
                    if(resultA==null&&resultB==null) {
                        continue;
                    }

                    if(resultA==null&&resultB!=null) {
                        differenceAttrs.add(getSubString(method));
                    }
                    else if(resultA!=null&&resultB==null) {
                        differenceAttrs.add(getSubString(method));
                    }else {
                        if(!(resultA instanceof List)) {
                            if(!resultA.equals(resultB)) {
                                differenceAttrs.add(getSubString(method));
                            }
                        }
                    }

                }
            }
        }catch(Exception e) {
            log.error("",e);
        }
        return differenceAttrs;
    }

    private static String getSubString(Method method) {
        String nameTrim = method.getName().substring(3);
        nameTrim = nameTrim.substring(0,1).toLowerCase()+nameTrim.substring(1);
        return nameTrim;
    }

    /**
     * 根据对象和属性，来得到属性的值
     * @param obj  对象
     * @param fieldName 属性名称
     * @return
     */
    public static String getFieldValue(Object obj,String fieldName){
        if(StringUtils.isEmpty(fieldName)) {
            return "";
        }
        fieldName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
        Class<?> clazzA = obj.getClass();
        String result = null;
        try {
            Method method = clazzA.getMethod(fieldName, null);
            result = (String)method.invoke(obj, null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getMessage());
        }
        if(StringUtils.isEmpty(result)) {
            return "";
        }
        return result;
    }

    public final static String FULL_ROW_BARS_DATETIME_PATTERN = "yyyy/MM/dd HH:mm";


    /**
     * 获取两个日期之间的所有日期
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List<String> getBetweenDates(String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);//定义起始日期
        Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);//定义结束日期

        List<String> result = new ArrayList<String>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
            result.add(sdf.format(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }



    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 将List按照指定长度len拆分为多个子List
     *
     * @param len  分组长度
     * @param list 目标List
     * @return java.util.List<java.util.List < T>>
     * @author haigang.jia@ikang.com
     * @date 2019-06-25 上午 10:00
     */
    public static <T> List<List<T>> groupList(int len, List<T> list) {
        List<List<T>> listGroup = new ArrayList<>();
        int listSize = list.size();
        for (int i = 0; i < list.size(); i += len) {
            if (i + len > listSize) {
                len = listSize - i;
            }
            List<T> newList = list.subList(i, i + len);
            listGroup.add(newList);
        }
        return listGroup;
    }

    /**
     * 从数组中随机获取target个不重复数据
     *
     * @param target 目标数
     * @param array  基数
     * @return java.util.List<T>
     * @author haigang.jia@ikang.com
     * @date 2019-06-28 上午 10:12
     */
    public static String[] randomArray(int target, String[] array) {
        assert null != array;
        if (target <= 0) {
            return new String[0];
        }
        if (target >= array.length) {
            return array;
        }
        int all = array.length;
        String[] targetArray = new String[target];
        for (int i = 0; i < targetArray.length; i++) {
            int index = RandomUtil.randomInt(all - i);
            targetArray[i] = array[index];
            String temp = array[index];
            array[index] = array[all - 1 - i];
            array[all - 1 - i] = temp;
        }
        return targetArray;
    }



    private static final long serialVersionUID = 1L;

    /**
     * 时长毫秒数
     */
    private long betweenMs;
    /**
     * 格式化级别
     */
    private BetweenFormatter.Level level;
    /**
     * 格式化级别的最大个数
     */
    private  int levelMaxCount;

    /**
     * 构造
     *
     * @param betweenMs 日期间隔
     * @param level     级别，按照天、小时、分、秒、毫秒分为5个等级，根据传入等级，格式化到相应级别
     */
    public ArrayTest(long betweenMs, BetweenFormatter.Level level) {
        this(betweenMs, level, 0);
    }

    /**
     * 构造
     *
     * @param betweenMs     日期间隔
     * @param level         级别，按照天、小时、分、秒、毫秒分为5个等级，根据传入等级，格式化到相应级别
     * @param levelMaxCount 格式化级别的最大个数，假如级别个数为1，但是级别到秒，那只显示一个级别
     */
    public ArrayTest(long betweenMs, BetweenFormatter.Level level, int levelMaxCount) {
        this.betweenMs = betweenMs;
        this.level = level;
        this.levelMaxCount = levelMaxCount;
    }

    /**
     * 格式化日期间隔输出<br>
     *
     * @return 格式化后的字符串
     */
    public String format() {
        final StringBuilder sb = new StringBuilder();
//        if (betweenMs > 0) {
//            long day = betweenMs / DateUnit.DAY.getMillis();
//            long hour = betweenMs / DateUnit.HOUR.getMillis() - day * 24;
//            long minute = betweenMs / DateUnit.MINUTE.getMillis() - day * 24 * 60 - hour * 60;
//
//            final long BetweenOfSecond = ((day * 24 + hour) * 60 + minute) * 60;
//            long second = betweenMs / DateUnit.SECOND.getMillis() - BetweenOfSecond;
//            long millisecond = betweenMs - (BetweenOfSecond + second) * 1000;
//
//            final int level = this.level.ordinal();
//            int levelCount = 0;
//
//            if (isLevelCountValid(levelCount) && 0 != day && level >= BetweenFormatter.Level.DAY.ordinal()) {
//                sb.append(day).append(BetweenFormatter.Level.DAY.name);
//                levelCount++;
//            }
//            if (isLevelCountValid(levelCount) && 0 != hour && level >= BetweenFormatter.Level.HOUR.ordinal()) {
//                sb.append(hour).append(BetweenFormatter.Level.HOUR.name);
//                levelCount++;
//            }
//            if (isLevelCountValid(levelCount) && 0 != minute && level >= BetweenFormatter.Level.MINUTE.ordinal()) {
//                sb.append(minute).append(BetweenFormatter.Level.MINUTE.name);
//                levelCount++;
//            }
//            if (isLevelCountValid(levelCount) && 0 != second && level >= BetweenFormatter.Level.SECOND.ordinal()) {
//                sb.append(second).append(BetweenFormatter.Level.SECOND.name);
//                levelCount++;
//            }
//            if (isLevelCountValid(levelCount) && 0 != millisecond && level >= BetweenFormatter.Level.MILLISECOND.ordinal()) {
//                sb.append(millisecond).append(BetweenFormatter.Level.MILLISECOND.name);
//                // levelCount++;
//            }
//        }
//
//        if (StrUtil.isEmpty(sb)) {
//            sb.append(0).append(this.level.name);
//        }

        return sb.toString();
    }

    /**
     * 获得 时长毫秒数
     *
     * @return 时长毫秒数
     */
    public long getBetweenMs() {
        return betweenMs;
    }

    /**
     * 设置 时长毫秒数
     *
     * @param betweenMs 时长毫秒数
     */
    public void setBetweenMs(long betweenMs) {
        this.betweenMs = betweenMs;
    }

    /**
     * 获得 格式化级别
     *
     * @return 格式化级别
     */
    public BetweenFormatter.Level getLevel() {
        return level;
    }

    /**
     * 设置格式化级别
     *
     * @param level 格式化级别
     */
    public void setLevel(BetweenFormatter.Level level) {
        this.level = level;
    }

    /**
     * 格式化等级枚举
     *
     * @author Looly
     */
    public enum Level {

        /**
         * 天
         */
        DAY("天"),
        /**
         * 小时
         */
        HOUR("小时"),
        /**
         * 分钟
         */
        MINUTE("分"),
        /**
         * 秒
         */
        SECOND("秒"),
        /**
         * 毫秒
         */
        MILLISECOND("毫秒");

        /**
         * 级别名称
         */
        private final String name;

        /**
         * 构造
         *
         * @param name 级别名称
         */
        Level(String name) {
            this.name = name;
        }

        /**
         * 获取级别名称
         *
         * @return 级别名称
         */
        public String getName() {
            return this.name;
        }
    }

    @Override
    public String toString() {
        return format();
    }

    /**
     * 等级数量是否有效<br>
     * 有效的定义是：levelMaxCount大于0（被设置），当前等级数量没有超过这个最大值
     *
     * @param levelCount 登记数量
     * @return 是否有效
     */
    private boolean isLevelCountValid(int levelCount) {
        return this.levelMaxCount <= 0 || levelCount < this.levelMaxCount;
    }


    /**
     * 新建一个空List
     *
     * @param <T>      集合元素类型
     * @param isLinked 是否新建LinkedList
     * @return List对象
     * @since 4.1.2
     */
    public static <T> List<T> list(boolean isLinked) {
        return isLinked ? new LinkedList<>() : new ArrayList<>();
    }

    /**
     * 新建一个List
     *
     * @param <T>      集合元素类型
     * @param isLinked 是否新建LinkedList
     * @param values   数组
     * @return List对象
     * @since 4.1.2
     */
    @SafeVarargs
    public static <T> List<T> list(boolean isLinked, T... values) {
        if (ArrayUtil.isEmpty(values)) {
            return list(isLinked);
        }
        final List<T> arrayList = isLinked ? new LinkedList<>() : new ArrayList<>(values.length);
        Collections.addAll(arrayList, values);
        return arrayList;
    }

    /**
     * 新建一个List
     *
     * @param <T>        集合元素类型
     * @param isLinked   是否新建LinkedList
     * @param collection 集合
     * @return List对象
     * @since 4.1.2
     */
    public static <T> List<T> list(boolean isLinked, Collection<T> collection) {
        if (null == collection) {
            return list(isLinked);
        }
        return isLinked ? new LinkedList<>(collection) : new ArrayList<>(collection);
    }

    /**
     * 新建一个List<br>
     * 提供的参数为null时返回空{@link ArrayList}
     *
     * @param <T>      集合元素类型
     * @param isLinked 是否新建LinkedList
     * @param iterable {@link Iterable}
     * @return List对象
     * @since 4.1.2
     */
    public static <T> List<T> list(boolean isLinked, Iterable<T> iterable) {
        if (null == iterable) {
            return list(isLinked);
        }
        return list(isLinked, iterable.iterator());
    }

    /**
     * 新建一个List<br>
     * 提供的参数为null时返回空{@link ArrayList}
     *
     * @param <T>      集合元素类型
     * @param isLinked 是否新建LinkedList
     * @param iter     {@link Iterator}
     * @return ArrayList对象
     * @since 4.1.2
     */
    public static <T> List<T> list(boolean isLinked, Iterator<T> iter) {
        final List<T> list = list(isLinked);
        if (null != iter) {
            while (iter.hasNext()) {
                list.add(iter.next());
            }
        }
        return list;
    }

    /**
     * 新建一个List<br>
     * 提供的参数为null时返回空{@link ArrayList}
     *
     * @param <T>        集合元素类型
     * @param isLinked   是否新建LinkedList
     * @param enumration {@link Enumeration}
     * @return ArrayList对象
     * @since 3.0.8
     */
    public static <T> List<T> list(boolean isLinked, Enumeration<T> enumration) {
        final List<T> list = list(isLinked);
        if (null != enumration) {
            while (enumration.hasMoreElements()) {
                list.add(enumration.nextElement());
            }
        }
        return list;
    }

    /**
     * 新建一个ArrayList
     *
     * @param <T>    集合元素类型
     * @param values 数组
     * @return ArrayList对象
     */
    @SafeVarargs
    public static <T> ArrayList<T> toList(T... values) {
        return (ArrayList<T>) list(false, values);
    }

    /**
     * 新建LinkedList
     *
     * @param values 数组
     * @param <T>    类型
     * @return LinkedList
     * @since 4.1.2
     */
    @SafeVarargs
    public static <T> LinkedList<T> toLinkedList(T... values) {
        return (LinkedList<T>) list(true, values);
    }

    /**
     * 数组转为一个不可变List<br>
     * 类似于Java9中的List.of
     *
     * @param ts  对象
     * @param <T> 对象类型
     * @return 不可修改List
     * @since 5.4.3
     */
    @SafeVarargs
    public static <T> List<T> of(T... ts) {
        if (ArrayUtil.isEmpty(ts)) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(toList(ts));
    }


    /**
     * 新建一个CopyOnWriteArrayList
     *
     * @param <T>        集合元素类型
     * @param collection 集合
     * @return {@link CopyOnWriteArrayList}
     */
    public static <T> CopyOnWriteArrayList<T> toCopyOnWriteArrayList(Collection<T> collection) {
        return (null == collection) ? (new CopyOnWriteArrayList<>()) : (new CopyOnWriteArrayList<>(collection));
    }

    /**
     * 新建一个ArrayList
     *
     * @param <T>        集合元素类型
     * @param collection 集合
     * @return ArrayList对象
     */
    public static <T> ArrayList<T> toList(Collection<T> collection) {
        return (ArrayList<T>) list(false, collection);
    }

    /**
     * 新建一个ArrayList<br>
     * 提供的参数为null时返回空{@link ArrayList}
     *
     * @param <T>      集合元素类型
     * @param iterable {@link Iterable}
     * @return ArrayList对象
     * @since 3.1.0
     */
    public static <T> ArrayList<T> toList(Iterable<T> iterable) {
        return (ArrayList<T>) list(false, iterable);
    }

    /**
     * 新建一个ArrayList<br>
     * 提供的参数为null时返回空{@link ArrayList}
     *
     * @param <T>      集合元素类型
     * @param iterator {@link Iterator}
     * @return ArrayList对象
     * @since 3.0.8
     */
    public static <T> ArrayList<T> toList(Iterator<T> iterator) {
        return (ArrayList<T>) list(false, iterator);
    }



    /**
     * 对指定List分页取值
     *
     * @param <T>      集合元素类型
     * @param pageNo   页码，第一页的页码取决于{@link PageUtil#getFirstPageNo()}，默认0
     * @param pageSize 每页的条目数
     * @param list     列表
     * @return 分页后的段落内容
     * @since 4.1.20
     */
    public static <T> List<T> page(int pageNo, int pageSize, List<T> list) {
        if (CollUtil.isEmpty(list)) {
            return new ArrayList<>(0);
        }

        int resultSize = list.size();
        // 每页条目数大于总数直接返回所有
        if (resultSize <= pageSize) {
            if (pageNo < (PageUtil.getFirstPageNo() + 1)) {
                return unmodifiable(list);
            } else {
                // 越界直接返回空
                return new ArrayList<>(0);
            }
        }
        // 相乘可能会导致越界 临时用long
        if (((long) (pageNo - PageUtil.getFirstPageNo()) * pageSize) > resultSize) {
            // 越界直接返回空
            return new ArrayList<>(0);
        }

        final int[] startEnd = PageUtil.transToStartEnd(pageNo, pageSize);
        if (startEnd[1] > resultSize) {
            startEnd[1] = resultSize;
            if (startEnd[0] > startEnd[1]) {
                return new ArrayList<>(0);
            }
        }

        return sub(list, startEnd[0], startEnd[1]);
    }

    /**
     * 对指定List进行分页，逐页返回数据
     *
     * @param <T>              集合元素类型
     * @param list             源数据列表
     * @param pageSize         每页的条目数
     * @param pageListConsumer 单页数据函数式返回
     * @since 5.7.10
     */
    public static <T> void page(List<T> list, int pageSize, Consumer<List<T>> pageListConsumer) {
        if (CollUtil.isEmpty(list) || pageSize <= 0) {
            return;
        }

        final int total = list.size();
        final int totalPage = PageUtil.totalPage(total, pageSize);
        for (int pageNo = PageUtil.getFirstPageNo(); pageNo < totalPage + PageUtil.getFirstPageNo(); pageNo++) {
            // 获取当前页在列表中对应的起止序号
            final int[] startEnd = PageUtil.transToStartEnd(pageNo, pageSize);
            if (startEnd[1] > total) {
                startEnd[1] = total;
            }

            // 返回数据
            pageListConsumer.accept(sub(list, startEnd[0], startEnd[1]));
        }
    }



    /**
     * 截取集合的部分
     *
     * @param <T>   集合元素类型
     * @param list  被截取的数组
     * @param start 开始位置（包含）
     * @param end   结束位置（不包含）
     * @return 截取后的数组，当开始位置超过最大时，返回空的List
     */
    public static <T> List<T> sub(List<T> list, int start, int end) {
        return sub(list, start, end, 1);
    }

    /**
     * 截取集合的部分<br>
     * 此方法与{@link List#subList(int, int)} 不同在于子列表是新的副本，操作子列表不会影响原列表。
     *
     * @param <T>   集合元素类型
     * @param list  被截取的数组
     * @param start 开始位置（包含）
     * @param end   结束位置（不包含）
     * @param step  步进
     * @return 截取后的数组，当开始位置超过最大时，返回空的List
     * @since 4.0.6
     */
    public static <T> List<T> sub(List<T> list, int start, int end, int step) {
        if (list == null) {
            return null;
        }

        if (list.isEmpty()) {
            return new ArrayList<>(0);
        }

        final int size = list.size();
        if (start < 0) {
            start += size;
        }
        if (end < 0) {
            end += size;
        }
        if (start == size) {
            return new ArrayList<>(0);
        }
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        if (end > size) {
            if (start >= size) {
                return new ArrayList<>(0);
            }
            end = size;
        }

        if (step < 1) {
            step = 1;
        }

        final List<T> result = new ArrayList<>();
        for (int i = start; i < end; i += step) {
            result.add(list.get(i));
        }
        return result;
    }

}

