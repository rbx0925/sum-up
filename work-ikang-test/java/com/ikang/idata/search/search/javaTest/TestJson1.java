package com.ikang.idata.search.search.javaTest;

import cn.hutool.core.io.unit.DataSize;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikang.idata.search.search.TestSpring;
import lombok.ToString;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/9/23
 */
public class TestJson1  {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为对象
     *
     * @return
     * @paramjsonDatajson数据
     * @paramclazz对象中的object类型
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @return
     * @paramjsonData
     * @parambeanType
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            List<T> list = MAPPER.readValue(jsonData, javaType);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @org.junit.Test
    public void test() throws JsonProcessingException {
        //jackson
        HashMap<String, String> map = MapUtil.of("xiaobin", "大佬");
        System.out.println(objectToJson(map));
        HashMap map1 = jsonToPojo(objectToJson(map), map.getClass());
        System.out.println(map1);
        Map<String, Object> map2 = MAPPER.readValue(objectToJson(map), new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map2);

        ClassPathScanningCandidateComponentProvider componentProvider = new ClassPathScanningCandidateComponentProvider(false);
        componentProvider.addIncludeFilter(new AnnotationTypeFilter(TestSpring.class));
        Set<BeanDefinition> components = componentProvider.findCandidateComponents("*");
        components.forEach(beanDefinition -> System.out.println(beanDefinition.getBeanClassName()));
    }


    @org.junit.Test
    public void test1() {
        String str = "[\n" +
                "{\n" +
                "\"locid_id\":853,\n" +
                "\"locationname_name\":\"爱康卓悦合肥高新拓基中心分院\",\n" +
                "\"institute_id\":\"071\",\n" +
                "\"hospname_name\":\"爱康卓悦合肥高新拓基中心分院\",\n" +
                "\"work_no\":\"2832203170001\",\n" +
                "\"card_id\":null,\n" +
                "\"customer_name\":null,\n" +
                "\"mobile\":null,\n" +
                "\"company_name\":null,\n" +
                "\"sex\":\"其他\",\n" +
                "\"age\":null,\n" +
                "\"department_name\":\"放射科\",\n" +
                "\"department_code\":\"3.1\",\n" +
                "\"item_name\":\"胸部正位\",\n" +
                "\"item_code\":\"3.1.1.41\",\n" +
                "\"item_status\":\"4\"\n" +
                "},\n" +
                "{\n" +
                "\"locid_id\":853,\n" +
                "\"locationname_name\":\"爱康卓悦合肥高新拓基中心分院\",\n" +
                "\"institute_id\":\"071\",\n" +
                "\"hospname_name\":\"爱康卓悦合肥高新拓基中心分院\",\n" +
                "\"work_no\":\"2832203170001\",\n" +
                "\"card_id\":null,\n" +
                "\"customer_name\":null,\n" +
                "\"mobile\":null,\n" +
                "\"company_name\":null,\n" +
                "\"sex\":\"其他\",\n" +
                "\"age\":null,\n" +
                "\"department_name\":\"放射科\",\n" +
                "\"department_code\":\"3.1\",\n" +
                "\"item_name\":\"胸部正位\",\n" +
                "\"item_code\":\"3.1.1.41\",\n" +
                "\"item_status\":\"4\"\n" +
                "}\n" +
                "\n" +
                "]";

        JSONArray objects = JSONArray.parseArray(str);
        System.out.println(objects.size());
    }

    @ToString
    public static class App implements Serializable {
        private static final long serialVersionUID = -6526705747295296106L;
        private String name;
        @TestSpring
        private String type;

        public App() {
        }
    }


    @org.junit.Test
    public void testzone() {
        TestJson1.App app = new TestJson1.App();
        DirectFieldAccessor accessor = new DirectFieldAccessor(app);
        Stream.of(accessor.getWrappedClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(TestSpring.class))
                .forEach(field -> accessor.setPropertyValue(field.getName(), TestSpring.class.getSimpleName()));
        System.out.println(app);

    }


    /**
     * The pattern for parsing.
     */
    private static final Pattern PATTERN = Pattern.compile("^([+-]?\\d+(\\.\\d+)?)([a-zA-Z]{0,2})$");

    /**
     * Bytes per Kilobyte(KB).
     */
    private static final long BYTES_PER_KB = 1024;

    /**
     * Bytes per Megabyte(MB).
     */
    private static final long BYTES_PER_MB = BYTES_PER_KB * 1024;

    /**
     * Bytes per Gigabyte(GB).
     */
    private static final long BYTES_PER_GB = BYTES_PER_MB * 1024;

    /**
     * Bytes per Terabyte(TB).
     */
    private static final long BYTES_PER_TB = BYTES_PER_GB * 1024;


    /**
     * bytes长度
     */
    private  long bytes;


    /**
     * 构造
     *
     * @param bytes 长度
     */
    private TestJson1(long bytes) {
        this.bytes = bytes;
    }


    /**
     * 获得对应bytes的DataSize
     *
     * @param bytes bytes大小，可正可负
     * @return this
     */
    public static TestJson1 ofBytes(long bytes) {
        return new TestJson1(bytes);
    }

    /**
     * 获得对应kilobytes的DataSize
     *
     * @param kilobytes kilobytes大小，可正可负
     * @return a DataSize
     */
    public static TestJson1 ofKilobytes(long kilobytes) {
        return new TestJson1(Math.multiplyExact(kilobytes, BYTES_PER_KB));
    }

    /**
     * 获得对应megabytes的DataSize
     *
     * @param megabytes megabytes大小，可正可负
     * @return a DataSize
     */
    public static TestJson1 ofMegabytes(long megabytes) {
        return new TestJson1(Math.multiplyExact(megabytes, BYTES_PER_MB));
    }

    /**
     * 获得对应gigabytes的DataSize
     *
     * @param gigabytes gigabytes大小，可正可负
     * @return a DataSize
     */
    public static TestJson1 ofGigabytes(long gigabytes) {
        return new TestJson1(Math.multiplyExact(gigabytes, BYTES_PER_GB));
    }

    /**
     * 获得对应terabytes的DataSize
     *
     * @param terabytes terabytes大小，可正可负
     * @return a DataSize
     */
    public static TestJson1 ofTerabytes(long terabytes) {
        return new TestJson1(Math.multiplyExact(terabytes, BYTES_PER_TB));
    }

    /**
     * 获得指定{@link DataUnit}对应的DataSize
     *
     * @param amount 大小
     * @param unit 数据大小单位，null表示默认的BYTES
     * @return DataSize
     */
//    public static TestJson1 of(long amount, DataUnit unit) {
//        if(null == unit){
//            unit = DataUnit.BYTES;
//        }
//        return new TestJson1(Math.multiplyExact(amount, unit.size().toBytes()));
//    }

    /**
     * 获得指定{@link DataUnit}对应的DataSize
     *
     * @param amount 大小
     * @param unit 数据大小单位，null表示默认的BYTES
     * @return DataSize
     * @since 5.4.5
     */
//    public static DataSize of(BigDecimal amount, DataUnit unit) {
//        if(null == unit){
//            unit = DataUnit.BYTES;
//        }
//        return new DataSize(amount.multiply(new BigDecimal(unit.size().toBytes())).longValue());
//    }

    /**
     * 获取指定数据大小文本对应的DataSize对象，如果无单位指定，默认获取{@link DataUnit#BYTES}
     * <p>
     * 例如：
     * <pre>
     * "12KB" -- parses as "12 kilobytes"
     * "5MB"  -- parses as "5 megabytes"
     * "20"   -- parses as "20 bytes"
     * </pre>
     *
     * @param text the text to parse
     * @return the parsed DataSize
     * @see #parse(CharSequence, DataUnit)
     */
    public static DataSize parse(CharSequence text) {
        return parse(text, null);
    }

    /**
     * Obtain a DataSize from a text string such as {@code 12MB} using
     * the specified default {@link DataUnit} if no unit is specified.
     * <p>
     * The string starts with a number followed optionally by a unit matching one of the
     * supported {@linkplain DataUnit suffixes}.
     * <p>
     * Examples:
     * <pre>
     * "12KB" -- parses as "12 kilobytes"
     * "5MB"  -- parses as "5 megabytes"
     * "20"   -- parses as "20 kilobytes" (where the {@code defaultUnit} is {@link DataUnit#KILOBYTES})
     * </pre>
     *
     * @param text the text to parse
     * @param defaultUnit 默认的数据单位
     * @return the parsed DataSize
     */
    public static DataSize parse(CharSequence text, DataUnit defaultUnit) {
        Assert.notNull(text, "Text must not be null");
        try {
            final Matcher matcher = PATTERN.matcher(text);
            Assert.state(matcher.matches(), "Does not match data size pattern");

            final DataUnit unit = determineDataUnit(matcher.group(3), defaultUnit);
            return DataSize.of(new BigDecimal(matcher.group(1)), unit);
        } catch (Exception ex) {
            throw new IllegalArgumentException("'" + text + "' is not a valid data size", ex);
        }
    }

    /**
     * 决定数据单位，后缀不识别时使用默认单位
     * @param suffix 后缀
     * @param defaultUnit 默认单位
     * @return {@link DataUnit}
     */
    private static DataUnit determineDataUnit(String suffix, DataUnit defaultUnit) {
        DataUnit defaultUnitToUse = (defaultUnit != null ? defaultUnit : DataUnit.BYTES);
        return (StrUtil.isNotEmpty(suffix) ? DataUnit.fromSuffix(suffix) : defaultUnitToUse);
    }

    /**
     * 是否为负数，不包括0
     *
     * @return 负数返回true，否则false
     */
    public boolean isNegative() {
        return this.bytes < 0;
    }

    /**
     * 返回bytes大小
     *
     * @return bytes大小
     */
    public long toBytes() {
        return this.bytes;
    }

    /**
     * 返回KB大小
     *
     * @return KB大小
     */
    public long toKilobytes() {
        return this.bytes / BYTES_PER_KB;
    }

    /**
     * 返回MB大小
     *
     * @return MB大小
     */
    public long toMegabytes() {
        return this.bytes / BYTES_PER_MB;
    }

    /**
     * 返回GB大小
     *
     * @return GB大小
     *
     */
    public long toGigabytes() {
        return this.bytes / BYTES_PER_GB;
    }

    /**
     * 返回TB大小
     *
     * @return TB大小
     */
    public long toTerabytes() {
        return this.bytes / BYTES_PER_TB;
    }

    //@Override
//    public int compareTo(DataSize other) {
//        return Long.compare(this.bytes, other.bytes);
//    }

    @Override
    public String toString() {
        return String.format("%dB", this.bytes);
    }


//    @Override
//    public boolean equals(Object other) {
//        if (this == other) {
//            return true;
//        }
//        if (other == null || getClass() != other.getClass()) {
//            return false;
//        }
//        DataSize otherSize = (DataSize) other;
//        return (this.bytes == otherSize.bytes);
//    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.bytes);
    }


}

