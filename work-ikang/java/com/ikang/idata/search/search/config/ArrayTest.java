package com.ikang.idata.search.search.config;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.primitives.Ints;
import com.ikang.idata.common.utils.AesUtil;
import jodd.util.function.Consumers;
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
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void ArrayListTest(){
        List<String> list = new ArrayList<>();
        if(list != null && !list.isEmpty()){
            System.out.println("操作list数据");

        }
    }

    @Test
    public void CollectionUtilsTest1(){
        List<String> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            System.out.println("操作list数据");
        }
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
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


//    https://blog.csdn.net/weixin_36380516/article/details/113361959?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164887835416780357237329%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=164887835416780357237329&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-2-113361959.142^v5^pc_search_result_cache,157^v4^control&utm_term=Optional.ofNullable&spm=1018.2226.3001.4187

    //orElseGet()
    //orElseGet 方法和 orElse 方法类似，都是在 Optional 值为空时，返回一个默认操作，只不过 orElse 返回的是默认值，而 orElseGet 是执行 lambda 表达式，然后返回 lambda 表达式执行后的结果。
    @org.junit.jupiter.api.Test
    public  void orElseGetTest(){
        //传入正常参数   获取一个Optional 对象  并使用orElse方法 设置默认值
        Optional<String> optional = Optional.of("新的一周");
        Object object = optional.orElseGet(() -> {
            return "执行逻辑和生成默认值";
        });
        System.out.println("输出值为" + object);


        //传入null参数   获取一个Optional对象  并使用orElse方法设置默认值
        Optional<String> optional1 = Optional.ofNullable(null);
        Object object1 = optional1.orElse("执行逻辑和生成默认值");
        System.out.println("输出值为" + object1);

        /**
         * 打印结果
         * object  新的一周
         * object1 执行逻辑和生成默认值
         */
    }

}

