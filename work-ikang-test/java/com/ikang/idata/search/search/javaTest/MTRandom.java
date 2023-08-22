package com.ikang.idata.search.search.javaTest;

import cn.hutool.core.date.DateField;
import com.ikang.idata.common.utils.DateUtil;
import com.ikang.idata.common.utils.StringUtil;
import jodd.util.function.Consumers;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

import static com.ikang.idata.common.consts.MagicConst.INTEGER_0;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/7/15
 */
public class MTRandom extends Random {
    // Constants used in the original C implementation
    private final static int UPPER_MASK = 0x80000000;
    private final static int LOWER_MASK = 0x7fffffff;

    private final static int N = 624;
    private final static int M = 397;
    private final static int MAGIC[] = {0x0, 0x9908b0df};
    private final static int MAGIC_FACTOR1 = 1812433253;
    private final static int MAGIC_FACTOR2 = 1664525;
    private final static int MAGIC_FACTOR3 = 1566083941;
    private final static int MAGIC_MASK1 = 0x9d2c5680;
    private final static int MAGIC_MASK2 = 0xefc60000;
    private final static int MAGIC_SEED = 19650218;
    private final static long DEFAULT_SEED = 5489L;

    // Internal state
    private transient int[] mt;
    private transient int mti;
    private transient boolean compat = false;

    // Temporary buffer used during setSeed(long)
    private transient int[] ibuf;

    /**
     * The default constructor for an instance of MTRandom. This invokes
     * the no-argument constructor for java.util.Random which will result
     * in the class being initialised with a seed value obtained by calling
     * System.currentTimeMillis().
     */
    public MTRandom() {
    }

    /**
     * This version of the constructor can be used to implement identical
     * behaviour to the original C code version of this algorithm including
     * exactly replicating the case where the seed value had not been set
     * prior to calling genrand_int32.
     * <p>
     * If the compatibility flag is set to true, then the algorithm will be
     * seeded with the same default value as was used in the original C
     * code. Furthermore the setSeed() method, which must take a 64 bit
     * long value, will be limited to using only the lower 32 bits of the
     * seed to facilitate seamless migration of existing C code into Java
     * where identical behaviour is required.
     * <p>
     * Whilst useful for ensuring backwards compatibility, it is advised
     * that this feature not be used unless specifically required, due to
     * the reduction in strength of the seed value.
     *
     * @param compatible Compatibility flag for replicating original
     *                   behaviour.
     */
    public MTRandom(boolean compatible) {
        super(0L);
        compat = compatible;
        setSeed(compat ? DEFAULT_SEED : System.currentTimeMillis());
    }

    /**
     * This version of the constructor simply initialises the class with
     * the given 64 bit seed value. For a better random number sequence
     * this seed value should contain as much entropy as possible.
     *
     * @param seed The seed value with which to initialise this class.
     */
    public MTRandom(long seed) {
        super(seed);
    }

    /**
     * This version of the constructor initialises the class with the
     * given byte array. All the data will be used to initialise this
     * instance.
     *
     * @param buf The non-empty byte array of seed information.
     * @throws NullPointerException     if the buffer is null.
     * @throws IllegalArgumentException if the buffer has zero length.
     */
    public MTRandom(byte[] buf) {
        super(0L);
        setSeed(buf);
    }

    /**
     * This version of the constructor initialises the class with the
     * given integer array. All the data will be used to initialise
     * this instance.
     *
     * @param buf The non-empty integer array of seed information.
     * @throws NullPointerException     if the buffer is null.
     * @throws IllegalArgumentException if the buffer has zero length.
     */
    public MTRandom(int[] buf) {
        super(0L);
        setSeed(buf);
    }

    // Initializes mt[N] with a simple integer seed. This method is
    // required as part of the Mersenne Twister algorithm but need
    // not be made public.
    private final void setSeed(int seed) {

        // Annoying runtime check for initialisation of internal data
        // caused by java.util.Random invoking setSeed() during init.
        // This is unavoidable because no fields in our instance will
        // have been initialised at this point, not even if the code
        // were placed at the declaration of the member variable.
        if (mt == null) {
            mt = new int[N];
        }

        // ---- Begin Mersenne Twister Algorithm ----
        mt[0] = seed;
        for (mti = 1; mti < N; mti++) {
            mt[mti] = (MAGIC_FACTOR1 * (mt[mti - 1] ^ (mt[mti - 1] >>> 30)) + mti);
        }
        // ---- End Mersenne Twister Algorithm ----
    }

    /**
     * This method resets the state of this instance using the 64
     * bits of seed data provided. Note that if the same seed data
     * is passed to two different instances of MTRandom (both of
     * which share the same compatibility state) then the sequence
     * of numbers generated by both instances will be identical.
     * <p>
     * If this instance was initialised in 'compatibility' mode then
     * this method will only use the lower 32 bits of any seed value
     * passed in and will match the behaviour of the original C code
     * exactly with respect to state initialisation.
     *
     * @param seed The 64 bit value used to initialise the random
     *             number generator state.
     */
    @Override
    public final synchronized void setSeed(long seed) {
        if (compat) {
            setSeed((int) seed);
        } else {

            // Annoying runtime check for initialisation of internal data
            // caused by java.util.Random invoking setSeed() during init.
            // This is unavoidable because no fields in our instance will
            // have been initialised at this point, not even if the code
            // were placed at the declaration of the member variable.
            if (ibuf == null) {
                ibuf = new int[2];
            }

            ibuf[0] = (int) seed;
            ibuf[1] = (int) (seed >>> 32);
            setSeed(ibuf);
        }
    }

    /**
     * This method resets the state of this instance using the byte
     * array of seed data provided. Note that calling this method
     * is equivalent to calling "setSeed(pack(buf))" and in particular
     * will result in a new integer array being generated during the
     * call. If you wish to retain this seed data to allow the pseudo
     * random sequence to be restarted then it would be more efficient
     * to use the "pack()" method to convert it into an integer array
     * first and then use that to re-seed the instance. The behaviour
     * of the class will be the same in both cases but it will be more
     * efficient.
     *
     * @param buf The non-empty byte array of seed information.
     * @throws NullPointerException     if the buffer is null.
     * @throws IllegalArgumentException if the buffer has zero length.
     */
    public final void setSeed(byte[] buf) {
        setSeed(pack(buf));
    }

    /**
     * This method resets the state of this instance using the integer
     * array of seed data provided. This is the canonical way of
     * resetting the pseudo random number sequence.
     *
     * @param buf The non-empty integer array of seed information.
     * @throws NullPointerException     if the buffer is null.
     * @throws IllegalArgumentException if the buffer has zero length.
     */
    public final synchronized void setSeed(int[] buf) {
        int length = buf.length;
        if (length == 0) {
            throw new IllegalArgumentException("Seed buffer may not be empty");
        }
        // ---- Begin Mersenne Twister Algorithm ----
        int i = 1, j = 0, k = (N > length ? N : length);
        setSeed(MAGIC_SEED);
        for (; k > 0; k--) {
            mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * MAGIC_FACTOR2)) + buf[j] + j;
            i++;
            j++;
            if (i >= N) {
                mt[0] = mt[N - 1];
                i = 1;
            }
            if (j >= length) {
                j = 0;
            }
        }
        for (k = N - 1; k > 0; k--) {
            mt[i] = (mt[i] ^ ((mt[i - 1] ^ (mt[i - 1] >>> 30)) * MAGIC_FACTOR3)) - i;
            i++;
            if (i >= N) {
                mt[0] = mt[N - 1];
                i = 1;
            }
        }
        mt[0] = UPPER_MASK;
        // MSB is 1; assuring non-zero initial array
        // ---- End Mersenne Twister Algorithm ----
    }

    /**
     * This method forms the basis for generating a pseudo random number
     * sequence from this class. If given a value of 32, this method
     * behaves identically to the genrand_int32 function in the original
     * C code and ensures that using the standard nextInt() function
     * (inherited from Random) we are able to replicate behaviour exactly.
     * <p>
     * Note that where the number of bits requested is not equal to 32
     * then bits will simply be masked out from the top of the returned
     * integer value. That is to say that:
     * <pre>
     * mt.setSeed(12345);
     * int foo = mt.nextInt(16) + (mt.nextInt(16) << 16);</pre>
     * will not give the same result as
     * <pre>
     * mt.setSeed(12345);
     * int foo = mt.nextInt(32);</pre>
     *
     * @param bits The number of significant bits desired in the output.
     * @return The next value in the pseudo random sequence with the
     * specified number of bits in the lower part of the integer.
     */
    @Override
    public final synchronized int next(int bits) {
        // ---- Begin Mersenne Twister Algorithm ----
        int y, kk;
        if (mti >= N) {
            // generate N words at one time
            // In the original C implementation, mti is checked here
            // to determine if initialisation has occurred; if not
            // it initialises this instance with DEFAULT_SEED (5489).
            // This is no longer necessary as initialisation of the
            // Java instance must result in initialisation occurring
            // Use the constructor MTRandom(true) to enable backwards
            // compatible behaviour.

            for (kk = 0; kk < N - M; kk++) {
                y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
                mt[kk] = mt[kk + M] ^ (y >>> 1) ^ MAGIC[y & 0x1];
            }
            for (; kk < N - 1; kk++) {
                y = (mt[kk] & UPPER_MASK) | (mt[kk + 1] & LOWER_MASK);
                mt[kk] = mt[kk + (M - N)] ^ (y >>> 1) ^ MAGIC[y & 0x1];
            }
            y = (mt[N - 1] & UPPER_MASK) | (mt[0] & LOWER_MASK);
            mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ MAGIC[y & 0x1];

            mti = 0;
        }
        y = mt[mti++];
        // Tempering
        y ^= (y >>> 11);
        y ^= (y << 7) & MAGIC_MASK1;
        y ^= (y << 15) & MAGIC_MASK2;
        y ^= (y >>> 18);
        // ---- End Mersenne Twister Algorithm ----
        return (y >>> (32 - bits));
    }

    // This is a fairly obscure little code section to pack a
    // byte[] into an int[] in little endian ordering.

    /**
     * This simply utility method can be used in cases where a byte
     * array of seed data is to be used to repeatedly re-seed the
     * random number sequence. By packing the byte array into an
     * integer array first, using this method, and then invoking
     * setSeed() with that; it removes the need to re-pack the byte
     * array each time setSeed() is called.
     * <p>
     * If the length of the byte array is not a multiple of 4 then
     * it is implicitly padded with zeros as necessary. For example:
     * <pre>  byte[] { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06 }</pre>
     * becomes
     * <pre>  int[] { 0x04030201, 0x00000605 }</pre>
     * <p>
     * Note that this method will not complain if the given byte array
     * is empty and will produce an empty integer array, but the
     * setSeed() method will throw an exception if the empty integer
     * array is passed to it.
     *
     * @param buf The non-null byte array to be packed.
     * @return A non-null integer array of the packed bytes.
     * @throws NullPointerException if the given byte array is null.
     */
    public static int[] pack(byte[] buf) {
        int k, blen = buf.length, ilen = ((buf.length + 3) >>> 2);
        int[] ibuf = new int[ilen];
        for (int n = 0; n < ilen; n++) {
            int m = (n + 1) << 2;
            if (m > blen) {
                m = blen;
            }
            for (k = buf[--m] & 0xff; (m & 0x3) != 0; k = (k << 8) | buf[--m] & 0xff) {
                ;
            }
            ibuf[n] = k;
        }
        return ibuf;
    }

//
//
//
//
//
//
/**
//     * 将byte转换为16进制字符串
//     * @param src
//     * @return
//     */
//
//    public static String byteToHexString(byte[] src) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < src.length; i++) {
//            int v = src[i] & 0xff;
//            String hv = Integer.toHexString(v);
//            if (hv.length() < 2) {
//                sb.append("0");
//            }
//            sb.append(hv);
//        }
//        return sb.toString();
//    }
//
//
//
//
//
//
//
//    /**
//     * 将16进制字符串装换为byte数组
//     * @param hexString
//     * @return
//     */
//    public static byte[] hexStringToBytes(String hexString) {
//        hexString = hexString.toUpperCase();
//        int length = hexString.length() / 2;
//        char[] hexChars = hexString.toCharArray();
//        byte[] b = new byte[length];
//        for (int i = 0; i < length; i++) {
//            int pos = i * 2;
//            b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
//        }
//        return b;
//    }
//
//    private static byte charToByte(char c) {
//        return (byte) "0123456789ABCDEF".indexOf(c);
//    }
//
//
//
//////
//
//
//
//
//    public static void main(String[] args) {
//        try {
////             byte key[] = generatorKey("ikangidata%&(+||");
//            // 密钥必须是16的倍数
//            byte key[] = "ikangidata%&(+||".getBytes("utf-8");//hexStringToBytes("0123456789ABCDEF");
////            String src = "werty7890";
////            System.out.println("密钥:"+byteToHexString(key));
////            System.out.println("原字符串:"+src);
////
////            String enc = byteToHexString(encrypt(src, key));
////            System.out.println("加密："+enc);
////            System.out.println("解密："+new String(decrypt(enc, key), "utf-8"));
//
//
//
//
//
//
//
//
//
//
//            System.out.println("解密==============："+new String(decrypt("45FDB20556A2D8BE318CA086CD3C210A", key), "utf-8"));
//        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//


    @org.junit.Test
    public void testClient() throws IOException {

        // 1、设置连接管理器
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory>create().register("http", plainsf).register("https", sslsf).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(1000);
        cm.setDefaultMaxPerRoute(500);

        // 2、创建请求配置核心设置超时
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000)
                .setConnectTimeout(1000).setSocketTimeout(2000).build();

        // 3、创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        // 4、发送请求并解析返回值
        HttpGet httpGet = new HttpGet("http://hc.apache.org/");
        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            // 解析返回值
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, Charset.forName("utf8"));
            EntityUtils.consume(entity);
        } catch (Exception e) {

        } finally {
            // 关闭并释放连接，可能多余
            response.close();
        }
    }


    @org.junit.Test
    public void testOne() {

        HttpGet get = new HttpGet("https://httpbin.org/get");
        try ( CloseableHttpClient client = HttpClients.createDefault();
              CloseableHttpResponse execute = client.execute(get)) {
            HttpEntity entity = execute.getEntity();
            System.out.println(execute.getStatusLine());
            System.out.println("***********************************************");
            System.out.println(EntityUtils.toString(entity));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGET() {

    }

    @org.junit.jupiter.api.Test
    public void test01(){
        String v1 = "1";
        String v2 = "0";
        v1 = StringUtil.isEmpty(v1) || "null".equals(v1) ? "0" : v1;
        v2 = StringUtil.isEmpty(v2) || "null".equals(v2) ? "0" : v2;
        if (INTEGER_0.equals(v2)) {
            System.out.println(v2);
        }
//            if (INTEGER_0.equals(v2) && NumberUtil.isNumber(v1)) {
//                return new BigDecimal("1");
//            }
//            if (INTEGER_0.equals(v1) && NumberUtil.isNumber(v2)) {
//                return new BigDecimal("-1");
//            }
//            return NumberUtil.div(NumberUtil.sub(v1, v2), new BigDecimal(v2)).setScale(4, BigDecimal.ROUND_HALF_UP);
    }

    @org.junit.jupiter.api.Test
    public void testDateUtil(){
        String dateStart = "2019-10";
        String[] split1 = dateStart.split("-");
        String dateEnd = "2019-12";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("====：" + DateUtil.getFirstDay(dateStart));
        System.out.println("======" + DateUtil.getLastDay(dateEnd));
        System.out.println("==============================");
        int year2 = 2020;
        int month2 = 2;
//        System.out.println(year2 + "年" + month2 + "月第一天：" + format.format(DateUtil.getFirstDay(year2, month2)));
//        System.out.println(year2 + "年" + month2 + "月最后一天：" + format.format(DateUtil.getLastDay(year2, month2)));

        String lastYearDateStart = cn.hutool.core.date.DateUtil.offset(cn.hutool.core.date.DateUtil.parse("2019-10"), DateField.YEAR, -1).toString();
        System.out.println(lastYearDateStart);
    }

    @org.junit.jupiter.api.Test
    public void test2(){
        String incomeLastYear = null,packageReceivableAmountLastYear = null;
        System.out.println(packageReceivableAmountLastYear);
        System.out.println(incomeLastYear);
    }




    //静态方法 Optional.of()
    //为指定的值创建一个指定非 null 值的 Optional。
    @org.junit.jupiter.api.Test
    public void ofTest(){
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional= Optional.of("加油");
        System.out.println(optional);

//        //传入null值  则会报 NullPointerException
//        Optional optional1= Optional.of(null);
//        System.out.println(optional1);

    }


    //静态方法 Optional.ofNullable()
    //为指定的值创建一个 Optional 对象，如果指定的参数为 null，不抛出异常，直接则返回一个空的 Optional 对象。
    @org.junit.jupiter.api.Test
    public void ofNullAbleTest(){
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional= Optional.ofNullable("嘿呀");
        System.out.println(optional);

        //传入null值   正常返回Optional 对象
        Optional optional1= Optional.ofNullable(null);
        System.out.println(optional1);
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

//    https://blog.csdn.net/weixin_36380516/article/details/113361959?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522164887835416780357237329%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=164887835416780357237329&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-2-113361959.142^v5^pc_search_result_cache,157^v4^control&utm_term=Optional.ofNullable&spm=1018.2226.3001.4187

    //orElseGet()
    //orElseGet 方法和 orElse 方法类似，都是在 Optional 值为空时，返回一个默认操作，只不过 orElse 返回的是默认值，而 orElseGet 是执行 lambda 表达式，然后返回 lambda 表达式执行后的结果。
    @org.junit.jupiter.api.Test
    public  void orElseGetTest(){
        //传入正常参数   获取一个Optional 对象  并使用orElse方法 设置默认值
        Optional optional = Optional.ofNullable("新的一周");
        Object object = optional.orElseGet(() -> {
            String defaultVal = "执行逻辑和生成默认值";
            return  defaultVal;
        });
        System.out.println("输出值为" + object);


        //传入null参数   获取一个Optional对象  并使用orElse方法设置默认值
        Optional optional1 = Optional.ofNullable(null);
        Object object1 = optional1.orElseGet(() -> {
            String defaultVal = "执行逻辑和生成默认值";
            return defaultVal;
        });
        System.out.println("输出值为" + object1);

        /**
         * 打印结果
         * object  新的一周
         * object1 执行逻辑和生成默认值
         */
    }

    //orElseThrow()
    //orElseThrow 方法其实就是判断创建 Optional 时传入的参数是否为 null，如果是非 null 则返回传入的值，否则抛出 异常。
    @org.junit.jupiter.api.Test
    public void orElseThrowTest(){
        //传入正常参数 获取一个Optional对象   并使用orElseThrow方法
        Optional optional = Optional.ofNullable("加油加油");
        try {
            Object elseThrow = optional.orElseThrow(() -> {
                System.out.println("执行逻辑,然后抛出异常");
                return new RuntimeException("抛出异常");
            });
            System.out.println("输出值为 " + elseThrow);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        //传入null参数  获取一个Optional 对象  并使用orElseThrow 方法
        Optional optional1 = Optional.ofNullable(null);
        try {
            Object elseThrow1 = optional1.orElseThrow(() -> {
                System.out.println("执行逻辑,然后抛出异常");
                return new RuntimeException("抛出异常");
            });

            System.out.println("输出值为 " + elseThrow1);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        /**
         * 打印结果
         * elseThrow  加油加油
         * elseThrow1  抛出异常并报错
         */
    }


    // 创建一个用户类，使用 Optional 操作用户对象，获取其 name 参数，结合 Optional 的 map 方法获取值，进行观察：
    class User {
        private String name ;
        public User (String name){
            this.name =name;
        }

        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        //使用 Optional 的 map 方法对值处理：
        @org.junit.jupiter.api.Test
        public void orElseTest1(){
            //创建一个对象  设置名字属性而不设置性别  这时候性别为null
//            com.ikang.idata.search.search.javaTest.Test.User user = new com.ikang.idata.search.search.javaTest.Test.User("测试名称");
//            com.ikang.idata.search.search.javaTest.Test.User user1 = new com.ikang.idata.search.search.javaTest.Test.User(null);

//            //使用Optional存储User对象
//            Optional<com.ikang.idata.search.search.javaTest.Test.User> optionalUser = Optional.ofNullable(user);
//            Optional<com.ikang.idata.search.search.javaTest.Test.User> optionalUser1 = Optional.ofNullable(user1);
//
//            //获取对象的name属性值
//            String name = optionalUser.map(com.ikang.idata.search.search.javaTest.Test.User::getName).orElse("未填写");
//            String name1 = optionalUser1.map(com.ikang.idata.search.search.javaTest.Test.User::getName).orElse("未填写");
//
//            //输出结果
//            System.out.println("获取的名称 " + name);
//            System.out.println("获取的名称 " + name1);
//
//
//
//
//
            /**
             * 打印结果
             * name 测试名称
             * name1 未填写
             */
            //通过上面两个示例观察到，通过 Optional 对象的 map 方法能够获取映射对象中的属，创建 Optional 对象，并以此属性充当 Optional 的值，结合 orElse 方法，如果获取的属性的值为空，则设置个默认值。
        }

        //对象方法 flatMap()
        // flatMap 方法和 map 方法类似，唯一的不同点就是 map 方法会对返回的值进行 Optional 封装，而 flatMap 不会，它需要手动执行 Optional.of 或 Optional.ofNullable 方法对 Optional 值进行封装。
        @org.junit.jupiter.api.Test
        public  void flatMapTest(){
            //创建一个map 对象
            Map<String,String> userMap = new HashMap<>();
            userMap.put("name","jiayou");
            userMap.put("sex","nan");

            //传入Map对象参数   获取一个Optional对象
            Optional<Map<String,String>> optional = Optional.of(userMap);
            //使用Optionalde flatMap方法  获取Map中的name属性值,然后通过获取的值手动创建一个新的Optional对象
            Optional optional1 = optional.flatMap( value -> Optional.ofNullable(value.get("name")));
            System.out.println("获取的Optional 的值" + optional1.get());

            /**
             * 打印结果
             * jiayou
             */

        }

        //对象方法 filter()
        // filter 方法通过传入的限定条件对 Optional 实例的值进行过滤，如果 Optional 值不为空且满足限定条件就返回包含值的 Optional，否则返回空的 Optional。这里设置的限定条件需要使用实现了 Predicate 接口的 lambda 表达式来进行配置。
        @org.junit.jupiter.api.Test
        public void filterTest(){
            //创建一个测试的Optional对象
            Optional<String> optional = Optional.ofNullable("XIANGQIANCHONGYA");

            //调用Optionalde filter 方法  设置一个满足的条件  然后观察获取Optional对象值 是否为空
            Optional optional1 = optional.filter((value) -> value.length() >2);
            System.out.println("Optional 的值不为空:" +optional1.isPresent());

            //调用Optional的filter方法,设置一个不满足的条件然后观察获取的Optional对象值是否为空
            Optional optional2 = optional.filter((value) -> value.length() < 2);
            System.out.println("Optional的值不为空 " + optional2.isPresent());

            /**
             * 打印结果
             * optional1  true
             * optional2  false
             */
        }

        //Optional 常用示例组合

        @org.junit.jupiter.api.Test
        public void OptionalExample(){
            //创建一个测试的用户集合
            List<com.ikang.idata.search.search.javaTest.Test.User> userList = new ArrayList<>();

//            //创建几个测试用户
//            com.ikang.idata.search.search.javaTest.Test.User user1 = new com.ikang.idata.search.search.javaTest.Test.User("abc");
//            com.ikang.idata.search.search.javaTest.Test.User user2 = new com.ikang.idata.search.search.javaTest.Test.User("efg");
//            com.ikang.idata.search.search.javaTest.Test.User user3 = null;
//
//            //将用户加入集合
//            userList.add(user1);
//            userList.add(user2);
//            userList.add(user3);
//
//            //创建用于存储姓名的集合
//            List<String> nameList = new ArrayList<>();
//
//            //循环用户列表获取用户信息,值获取不为空且用户以a开头的姓名
//            //如果不符合条件就设置默认值 最后将符合条件的用户姓名加入姓名集合
//            for (com.ikang.idata.search.search.javaTest.Test.User user : userList) {
//                // nameList.add(Optional.ofNullable(user).map(User::getName).filter(value -> value.startsWith("a")));
//
//            }
//            System.out.println("通过Optional过滤的集合输出");
//            nameList.stream().forEach(System.out::println);
//
//
//
//
//
//


        }

    }
}



