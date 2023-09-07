package com.ikang.idata.search.search.javaTest;

import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.map.CaseInsensitiveMap;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/10/17
 */
public class CopyOptionsTest {
    /**
     * 限制的类或接口，必须为目标对象的实现接口或父类，用于限制拷贝的属性，例如一个类我只想复制其父类的一些属性，就可以将editable设置为父类
     */
    protected Class<?> editable;


    /**
     * 是否忽略空值，当源对象的值为null时，true: 忽略而不注入此值，false: 注入null
     */
    protected boolean ignoreNullValue;
    /**
     * 属性过滤器，断言通过的属性才会被复制
     */
    protected BiPredicate<Field, Object> propertiesFilter;
    /**
     * 忽略的目标对象中属性列表，设置一个属性列表，不拷贝这些属性值
     */
    protected String[] ignoreProperties;
    /**
     * 是否忽略字段注入错误
     */
    protected boolean ignoreError;
    /**
     * 是否忽略字段大小写
     */
    protected boolean ignoreCase;
    /**
     * 拷贝属性的字段映射，用于不同的属性之前拷贝做对应表用
     */
    protected Map<String, String> fieldMapping;
    /**
     * 反向映射表，自动生成用于反向查找
     */
    private Map<String, String> reversedFieldMapping;
    /**
     * 字段属性编辑器，用于自定义属性转换规则，例如驼峰转下划线等
     */
    protected Editor<String> fieldNameEditor;
    /**
     * 是否支持transient关键字修饰和@Transient注解，如果支持，被修饰的字段或方法对应的字段将被忽略。
     */
    private boolean transientSupport = true;

    /**
     * 创建拷贝选项
     *
     * @return 拷贝选项
     */
    public static CopyOptions create() {
        return new CopyOptions();
    }

    /**
     * 创建拷贝选项
     *
     * @param editable         限制的类或接口，必须为目标对象的实现接口或父类，用于限制拷贝的属性
     * @param ignoreNullValue  是否忽略空值，当源对象的值为null时，true: 忽略而不注入此值，false: 注入null
     * @param ignoreProperties 忽略的属性列表，设置一个属性列表，不拷贝这些属性值
     * @return 拷贝选项
     */
    public static CopyOptions create(Class<?> editable, boolean ignoreNullValue, String... ignoreProperties) {
        return new CopyOptions(editable, ignoreNullValue, ignoreProperties);
    }

    /**
     * 构造拷贝选项
     */
    public CopyOptionsTest() {
    }

    /**
     * 构造拷贝选项
     *
     * @param editable         限制的类或接口，必须为目标对象的实现接口或父类，用于限制拷贝的属性
     * @param ignoreNullValue  是否忽略空值，当源对象的值为null时，true: 忽略而不注入此值，false: 注入null
     * @param ignoreProperties 忽略的目标对象中属性列表，设置一个属性列表，不拷贝这些属性值
     */
    public CopyOptionsTest(Class<?> editable, boolean ignoreNullValue, String... ignoreProperties) {
        this.propertiesFilter = (f, v) -> true;
        this.editable = editable;
        this.ignoreNullValue = ignoreNullValue;
        this.ignoreProperties = ignoreProperties;
    }

    /**
     * 设置限制的类或接口，必须为目标对象的实现接口或父类，用于限制拷贝的属性
     *
     * @param editable 限制的类或接口
     * @return CopyOptions
     */
    public CopyOptionsTest setEditable(Class<?> editable) {
        this.editable = editable;
        return this;
    }

    /**
     * 设置是否忽略空值，当源对象的值为null时，true: 忽略而不注入此值，false: 注入null
     *
     * @param ignoreNullVall 是否忽略空值，当源对象的值为null时，true: 忽略而不注入此值，false: 注入null
     * @return CopyOptions
     */
    public CopyOptionsTest setIgnoreNullValue(boolean ignoreNullVall) {
        this.ignoreNullValue = ignoreNullVall;
        return this;
    }

    /**
     * 设置忽略空值，当源对象的值为null时，忽略而不注入此值
     *
     * @return CopyOptions
     * @since 4.5.7
     */
    public CopyOptionsTest ignoreNullValue() {
        return setIgnoreNullValue(true);
    }

    /**
     * 属性过滤器，断言通过的属性才会被复制
     *
     * @param propertiesFilter 属性过滤器
     * @return CopyOptions
     */
    public CopyOptionsTest setPropertiesFilter(BiPredicate<Field, Object> propertiesFilter) {
        this.propertiesFilter = propertiesFilter;
        return this;
    }

    /**
     * 设置忽略的目标对象中属性列表，设置一个属性列表，不拷贝这些属性值
     *
     * @param ignoreProperties 忽略的目标对象中属性列表，设置一个属性列表，不拷贝这些属性值
     * @return CopyOptions
     */
    public CopyOptionsTest setIgnoreProperties(String... ignoreProperties) {
        this.ignoreProperties = ignoreProperties;
        return this;
    }

    /**
     * 设置是否忽略字段的注入错误
     *
     * @param ignoreError 是否忽略注入错误
     * @return CopyOptions
     */
    public CopyOptionsTest setIgnoreError(boolean ignoreError) {
        this.ignoreError = ignoreError;
        return this;
    }

    /**
     * 设置忽略字段的注入错误
     *
     * @return CopyOptions
     * @since 4.5.7
     */
    public CopyOptionsTest ignoreError() {
        return setIgnoreError(true);
    }

    /**
     * 设置是否忽略字段的大小写
     *
     * @param ignoreCase 是否忽略大小写
     * @return CopyOptions
     */
    public CopyOptionsTest setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    /**
     * 设置忽略字段的大小写
     *
     * @return CopyOptions
     * @since 4.5.7
     */
    public CopyOptionsTest ignoreCase() {
        return setIgnoreCase(true);
    }

    /**
     * 设置拷贝属性的字段映射，用于不同的属性之前拷贝做对应表用
     *
     * @param fieldMapping 拷贝属性的字段映射，用于不同的属性之前拷贝做对应表用
     * @return CopyOptions
     */
    public CopyOptionsTest setFieldMapping(Map<String, String> fieldMapping) {
        this.fieldMapping = fieldMapping;
        return this;
    }

    /**
     * 设置字段属性编辑器，用于自定义属性转换规则，例如驼峰转下划线等<br>
     * 此转换器只针对源端的字段做转换，请确认转换后与目标端字段一致<br>
     * 当转换后的字段名为null时忽略这个字段
     *
     * @param fieldNameEditor 字段属性编辑器，用于自定义属性转换规则，例如驼峰转下划线等
     * @return CopyOptions
     * @since 5.4.2
     */
    public CopyOptionsTest setFieldNameEditor(Editor<String> fieldNameEditor) {
        this.fieldNameEditor = fieldNameEditor;
        return this;
    }

    /**
     * 是否支持transient关键字修饰和@Transient注解，如果支持，被修饰的字段或方法对应的字段将被忽略。
     *
     * @return 是否支持
     * @since 5.4.2
     */
    public boolean isTransientSupport() {
        return this.transientSupport;
    }

    /**
     * 设置是否支持transient关键字修饰和@Transient注解，如果支持，被修饰的字段或方法对应的字段将被忽略。
     *
     * @param transientSupport 是否支持
     * @return this
     * @since 5.4.2
     */
    public CopyOptionsTest setTransientSupport(boolean transientSupport) {
        this.transientSupport = transientSupport;
        return this;
    }

    /**
     * 获得映射后的字段名<br>
     * 当非反向，则根据源字段名获取目标字段名，反之根据目标字段名获取源字段名。
     *
     * @param fieldName 字段名
     * @param reversed 是否反向映射
     * @return 映射后的字段名
     */
    protected String getMappedFieldName(String fieldName, boolean reversed){
        Map<String, String> mapping = reversed ? getReversedMapping() : this.fieldMapping;
        if(MapUtil.isEmpty(mapping)){
            return fieldName;
        }
        return ObjectUtil.defaultIfNull(mapping.get(fieldName), fieldName);
    }

    /**
     * 转换字段名为编辑后的字段名
     * @param fieldName 字段名
     * @return 编辑后的字段名
     * @since 5.4.2
     */
    protected String editFieldName(String fieldName){
        return (null != this.fieldNameEditor) ? this.fieldNameEditor.edit(fieldName) : fieldName;
    }

    /**
     * 获取反转之后的映射
     *
     * @return 反转映射
     * @since 4.1.10
     */
    private Map<String, String> getReversedMapping() {
        if(null == this.fieldMapping){
            return null;
        }
        if(null == this.reversedFieldMapping){
            reversedFieldMapping = MapUtil.reverse(this.fieldMapping);
        }
        return reversedFieldMapping;
    }




    private  Map<?, ?> map;
    private  boolean ignoreError1;

    /**
     * 构造
     *
     * @param map        Map
     * @param ignoreCase 是否忽略key的大小写
     */
    public CopyOptionsTest(Map<?, ?> map, boolean ignoreCase) {
        this(map, ignoreCase, false);
    }

    /**
     * 构造
     *
     * @param map         Map
     * @param ignoreCase  是否忽略key的大小写
     * @param ignoreError 是否忽略错误
     * @since 5.3.2
     */
    public CopyOptionsTest(Map<?, ?> map, boolean ignoreCase, boolean ignoreError) {
        if (false == ignoreCase || map instanceof CaseInsensitiveMap) {
            //不忽略大小写或者提供的Map本身为CaseInsensitiveMap则无需转换
            this.map = map;
        } else {
            //转换为大小写不敏感的Map
            this.map = new CaseInsensitiveMap<>(map);
        }
        this.ignoreError = ignoreError;
    }


    public Object value(String key, Type valueType) {
        final String key1 = getKey(key, valueType);
        if (null == key1) {
            return null;
        }

        return Convert.convertWithCheck(valueType, map.get(key1), null, this.ignoreError);
    }


    public boolean containsKey(String key) {
        return null != getKey(key, null);
    }

    /**
     * 获得map中可能包含的key,不包含返回null
     *
     * @param key       map中可能包含的key
     * @param valueType 值类型，用于判断是否为Boolean，可以为null
     * @return map中可能包含的key
     */
    private String getKey(String key, Type valueType) {
        if (map.containsKey(key)) {
            return key;
        }

        //检查下划线模式
        String customKey = StrUtil.toUnderlineCase(key);
        if (map.containsKey(customKey)) {
            return customKey;
        }

        //检查boolean类型
        if (null == valueType || Boolean.class == valueType || boolean.class == valueType) {
            //boolean类型字段字段名支持两种方式
            customKey = StrUtil.upperFirstAndAddPre(key, "is");
            if (map.containsKey(customKey)) {
                return customKey;
            }

            //检查下划线模式
            customKey = StrUtil.toUnderlineCase(customKey);
            if (map.containsKey(customKey)) {
                return customKey;
            }
        }
        return null;
    }


    private static final String[] NUMBER = new String[]{"", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN",
            "EIGHT", "NINE"};
    private static final String[] NUMBER_TEEN = new String[]{"TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN",
            "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"};
    private static final String[] NUMBER_TEN = new String[]{"TEN", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY",
            "SEVENTY", "EIGHTY", "NINETY"};
    private static final String[] NUMBER_MORE = new String[]{"", "THOUSAND", "MILLION", "BILLION"};

    private static final String[] NUMBER_SUFFIX = new String[]{"k", "w", "", "m", "", "", "b", "", "", "t", "", "", "p", "", "", "e"};

    /**
     * 将阿拉伯数字转为英文表达式
     *
     * @param x 阿拉伯数字，可以为{@link Number}对象，也可以是普通对象，最后会使用字符串方式处理
     * @return 英文表达式
     */
    public static String format(Object x) {
        if (x != null) {
            return format(x.toString());
        } else {
            return StrUtil.EMPTY;
        }
    }

    /**
     * 将阿拉伯数字转化为简洁计数单位，例如 2100 =》 2.1k
     * 范围默认只到w
     *
     * @param value 被格式化的数字
     * @return 格式化后的数字
     * @since 5.5.9
     */
    public static String formatSimple(long value) {
        return formatSimple(value, true);
    }

    /**
     * 将阿拉伯数字转化为简介计数单位，例如 2100 =》 2.1k
     *
     * @param value 对应数字的值
     * @param isTwo 控制是否为只为k、w，例如当为{@code false}时返回4.38m，{@code true}返回438.43w
     * @return 格式化后的数字
     * @since 5.5.9
     */
    public static String formatSimple(long value, boolean isTwo) {
        if (value < 1000) {
            return String.valueOf(value);
        }
        int index = -1;
        double res = value;
        while (res > 10 && (false == isTwo || index < 1)) {
            if (res > 1000) {
                res = res / 1000;
                index++;
            }
            if (res > 10) {
                res = res / 10;
                index++;
            }
        }
        return String.format("%s%s", NumberUtil.decimalFormat("#.##", res), NUMBER_SUFFIX[index]);
    }

    /**
     * 将阿拉伯数字转为英文表达式
     *
     * @param x 阿拉伯数字字符串
     * @return 英文表达式
     */
    private static String format(String x) {
        int z = x.indexOf("."); // 取小数点位置
        String lstr, rstr = "";
        if (z > -1) { // 看是否有小数，如果有，则分别取左边和右边
            lstr = x.substring(0, z);
            rstr = x.substring(z + 1);
        } else {
            // 否则就是全部
            lstr = x;
        }

        String lstrrev = StrUtil.reverse(lstr); // 对左边的字串取反
        String[] a = new String[5]; // 定义5个字串变量来存放解析出来的叁位一组的字串

        switch (lstrrev.length() % 3) {
            case 1:
                lstrrev += "00";
                break;
            case 2:
                lstrrev += "0";
                break;
        }
        StringBuilder lm = new StringBuilder(); // 用来存放转换后的整数部分
        for (int i = 0; i < lstrrev.length() / 3; i++) {
            a[i] = StrUtil.reverse(lstrrev.substring(3 * i, 3 * i + 3)); // 截取第一个三位
            if (false == "000".equals(a[i])) { // 用来避免这种情况：1000000 = one million
                // thousand only
                if (i != 0) {
                    lm.insert(0, transThree(a[i]) + " " + parseMore(i) + " "); // 加:
                    // thousand、million、billion
                } else {
                    // 防止i=0时， 在多加两个空格.
                    lm = new StringBuilder(transThree(a[i]));
                }
            } else {
                lm.append(transThree(a[i]));
            }
        }

        String xs = ""; // 用来存放转换后小数部分
        if (z > -1) {
            xs = "AND CENTS " + transTwo(rstr) + " "; // 小数部分存在时转换小数
        }

        return lm.toString().trim() + " " + xs + "ONLY";
    }

    private static String parseFirst(String s) {
        return NUMBER[Integer.parseInt(s.substring(s.length() - 1))];
    }

    private static String parseTeen(String s) {
        return NUMBER_TEEN[Integer.parseInt(s) - 10];
    }

    private static String parseTen(String s) {
        return NUMBER_TEN[Integer.parseInt(s.substring(0, 1)) - 1];
    }

    private static String parseMore(int i) {
        return NUMBER_MORE[i];
    }

    // 两位
    private static String transTwo(String s) {
        String value;
        // 判断位数
        if (s.length() > 2) {
            s = s.substring(0, 2);
        } else if (s.length() < 2) {
            s = "0" + s;
        }

        if (s.startsWith("0")) {// 07 - seven 是否小於10
            value = parseFirst(s);
        } else if (s.startsWith("1")) {// 17 seventeen 是否在10和20之间
            value = parseTeen(s);
        } else if (s.endsWith("0")) {// 是否在10与100之间的能被10整除的数
            value = parseTen(s);
        } else {
            value = parseTen(s) + " " + parseFirst(s);
        }
        return value;
    }

    // 制作叁位的数
    // s.length = 3
    private static String transThree(String s) {
        String value;
        if (s.startsWith("0")) {// 是否小於100
            value = transTwo(s.substring(1));
        } else if ("00".equals(s.substring(1))) {// 是否被100整除
            value = parseFirst(s.substring(0, 1)) + " HUNDRED";
        } else {
            value = parseFirst(s.substring(0, 1)) + " HUNDRED AND " + transTwo(s.substring(1));
        }
        return value;
    }

    /**
     * 中文形式，奇数位置是简体，偶数位置是记账繁体，0共用<br>
     * 使用混合数组提高效率和数组复用
     **/
    private static final char[] DIGITS = {'零', '一', '壹', '二', '贰', '三', '叁', '四', '肆', '五', '伍',
            '六', '陆', '七', '柒', '八', '捌', '九', '玖'};

    /**
     * 汉字转阿拉伯数字的
     */
    private static final CopyOptionsTest.ChineseUnit[] CHINESE_NAME_VALUE = {
            new CopyOptionsTest.ChineseUnit(' ', 1, false),
            new CopyOptionsTest.ChineseUnit('十', 10, false),
            new CopyOptionsTest.ChineseUnit('拾', 10, false),
            new CopyOptionsTest.ChineseUnit('百', 100, false),
            new CopyOptionsTest.ChineseUnit('佰', 100, false),
            new CopyOptionsTest.ChineseUnit('千', 1000, false),
            new CopyOptionsTest.ChineseUnit('仟', 1000, false),
            new CopyOptionsTest.ChineseUnit('万', 1_0000, true),
            new CopyOptionsTest.ChineseUnit('亿', 1_0000_0000, true),
    };


    /**
     * 阿拉伯数字转换成中文,小数点后四舍五入保留两位. 使用于整数、小数的转换.
     *
     * @param amount           数字
     * @param isUseTraditional 是否使用繁体
     * @return 中文
     */
    public static String format(double amount, boolean isUseTraditional) {
        return format(amount, isUseTraditional, false);
    }

    /**
     * 阿拉伯数字转换成中文,小数点后四舍五入保留两位. 使用于整数、小数的转换.
     *
     * @param amount           数字
     * @param isUseTraditional 是否使用繁体
     * @param isMoneyMode      是否为金额模式
     * @return 中文
     */
    public static String format(double amount, boolean isUseTraditional, boolean isMoneyMode) {
        if (amount > 99_9999_9999_9999.99 || amount < -99999999999999.99) {
            throw new IllegalArgumentException("Number support only: (-99999999999999.99 ～ 99999999999999.99)！");
        }

        // 负数
        boolean negative = false;
        if (amount < 0) {
            negative = true;
            amount = -amount;
        }

        // 分和角
        long temp = Math.round(amount * 100);

        final int numFen = (int) (temp % 10);
        temp = temp / 10;
        final int numJiao = (int) (temp % 10);
        temp = temp / 10;

        final StringBuilder chineseStr = new StringBuilder(longToChinese(temp, isUseTraditional));
        //负数
        if (negative) { // 整数部分不为 0
            chineseStr.insert(0, "负");
        }

        // 小数部分
        if (numFen != 0 || numJiao != 0) {
            if (numFen == 0) {
                chineseStr.append(isMoneyMode ? "元" : "点").append(numberToChinese(numJiao, isUseTraditional)).append(isMoneyMode ? "角" : "");
            } else { // “分”数不为 0
                if (numJiao == 0) {
                    chineseStr.append(isMoneyMode ? "元零" : "点零").append(numberToChinese(numFen, isUseTraditional)).append(isMoneyMode ? "分" : "");
                } else {
                    chineseStr.append(isMoneyMode ? "元" : "点").append(numberToChinese(numJiao, isUseTraditional)).append(isMoneyMode ? "角" : "").append(numberToChinese(numFen, isUseTraditional)).append(isMoneyMode ? "分" : "");
                }
            }
        } else if (isMoneyMode) {
            //无小数部分的金额结尾
            chineseStr.append("元整");
        }

        return chineseStr.toString();
    }

    /**
     * 数字字符转中文，非数字字符原样返回
     *
     * @param c                数字字符
     * @param isUseTraditional 是否繁体
     * @return 中文字符
     * @since 5.3.9
     */
    public static String numberCharToChinese(char c, boolean isUseTraditional) {
        if (c < '0' || c > '9') {
            return String.valueOf(c);
        }
        return String.valueOf(numberToChinese(c - '0', isUseTraditional));
    }

    /**
     * 阿拉伯数字整数部分转换成中文，只支持正数
     *
     * @param amount           数字
     * @param isUseTraditional 是否使用繁体
     * @return 中文
     */
    private static String longToChinese(long amount, boolean isUseTraditional) {
        if(0 == amount){
            return "零";
        }

        //将数字以万为单位分为多份
        int[] parts = new int[4];
        for (int i = 0; amount != 0; i++) {
            parts[i] = (int) (amount % 10000);
            amount = amount / 10000;
        }

        final StringBuilder chineseStr = new StringBuilder();
        int partValue;
        String partChinese;

        // 千
        partValue = parts[0];
        if(partValue > 0){
            partChinese = thousandToChinese(partValue, isUseTraditional);
            chineseStr.insert(0, partChinese);

            if(partValue < 1000){
                // 和万位之间空0，则补零，如一万零三百
                addPreZero(chineseStr);
            }
        }

        // 万
        partValue = parts[1];
        if(partValue > 0){
            if((partValue % 10 == 0 && parts[0] > 0)){
                // 如果"万"的个位是0，则补零，如十万零八千
                addPreZero(chineseStr);
            }
            partChinese = thousandToChinese(partValue, isUseTraditional);
            chineseStr.insert(0, partChinese + "万");

            if(partValue < 1000){
                // 和亿位之间空0，则补零，如一亿零三百万
                addPreZero(chineseStr);
            }
        } else{
            addPreZero(chineseStr);
        }

        // 亿
        partValue = parts[2];
        if(partValue > 0){
            if((partValue % 10 == 0 && parts[1] > 0)){
                // 如果"万"的个位是0，则补零，如十万零八千
                addPreZero(chineseStr);
            }

            partChinese = thousandToChinese(partValue, isUseTraditional);
            chineseStr.insert(0, partChinese + "亿");

            if(partValue < 1000){
                // 和万亿位之间空0，则补零，如一万亿零三百亿
                addPreZero(chineseStr);
            }
        } else{
            addPreZero(chineseStr);
        }

        // 万亿
        partValue = parts[3];
        if(partValue > 0){
            if(parts[2] == 0){
                chineseStr.insert(0, "亿");
            }
            partChinese = thousandToChinese(partValue, isUseTraditional);
            chineseStr.insert(0, partChinese + "万");
        }

        if(StrUtil.isNotEmpty(chineseStr) && '零' == chineseStr.charAt(0)){
            return chineseStr.substring(1);
        }

        return chineseStr.toString();
    }

    /**
     * 把一个 0~9999 之间的整数转换为汉字的字符串，如果是 0 则返回 ""
     *
     * @param amountPart       数字部分
     * @param isUseTraditional 是否使用繁体单位
     * @return 转换后的汉字
     */
    private static String thousandToChinese(int amountPart, boolean isUseTraditional) {
        int temp = amountPart;

        StringBuilder chineseStr = new StringBuilder();
        boolean lastIsZero = true; // 在从低位往高位循环时，记录上一位数字是不是 0
        for (int i = 0; temp > 0; i++) {
            int digit = temp % 10;
            if (digit == 0) { // 取到的数字为 0
                if (false == lastIsZero) {
                    // 前一个数字不是 0，则在当前汉字串前加“零”字;
                    chineseStr.insert(0, "零");
                }
                lastIsZero = true;
            } else { // 取到的数字不是 0
                chineseStr.insert(0, numberToChinese(digit, isUseTraditional) + getUnitName(i, isUseTraditional));
                lastIsZero = false;
            }
            temp = temp / 10;
        }
        return chineseStr.toString();
    }

    /**
     * 把中文转换为数字 如 二百二十 220<br>
     * 见：https://www.d5.nz/read/sfdlq/text-part0000_split_030.html
     * <ul>
     *     <li>一百一十二 -》 112</li>
     *     <li>一千零一十二 -》 1012</li>
     * </ul>
     *
     * @param chinese 中文字符
     * @return 数字
     * @since 5.6.0
     */
    public static int chineseToNumber(String chinese) {
        final int length = chinese.length();
        int result = 0;

        // 节总和
        int section = 0;
        int number = 0;
        CopyOptionsTest.ChineseUnit unit = null;
        char c;
        for (int i = 0; i < length; i++) {
            c = chinese.charAt(i);
            final int num = chineseToNumber(c);
            if (num >= 0) {
                if (num == 0) {
                    // 遇到零时节结束，权位失效，比如两万二零一十
                    if (number > 0 && null != unit) {
                        section += number * (unit.value / 10);
                    }
                    unit = null;
                } else if (number > 0) {
                    // 多个数字同时出现，报错
                    throw new IllegalArgumentException(StrUtil.format("Bad number '{}{}' at: {}", chinese.charAt(i - 1), c, i));
                }
                // 普通数字
                number = num;
            } else {
                unit = chineseToUnit(c);
                if (null == unit) {
                    // 出现非法字符
                    throw new IllegalArgumentException(StrUtil.format("Unknown unit '{}' at: {}", c, i));
                }

                //单位
                if (unit.secUnit) {
                    // 节单位，按照节求和
                    section = (section + number) * unit.value;
                    result += section;
                    section = 0;
                } else {
                    // 非节单位，和单位前的单数字组合为值
                    int unitNumber = number;
                    if(0 == number && 0 == i){
                        // issue#1726，对于单位开头的数组，默认赋予1
                        // 十二 -> 一十二
                        // 百二 -> 一百二
                        unitNumber = 1;
                    }
                    section += (unitNumber * unit.value);
                }
                number = 0;
            }
        }

        if (number > 0 && null != unit) {
            number = number * (unit.value / 10);
        }

        return result + section + number;
    }

    /**
     * 查找对应的权对象
     *
     * @param chinese 中文权位名
     * @return 权对象
     */
    private static CopyOptionsTest.ChineseUnit chineseToUnit(char chinese) {
        for (CopyOptionsTest.ChineseUnit chineseNameValue : CHINESE_NAME_VALUE) {
            if (chineseNameValue.name == chinese) {
                return chineseNameValue;
            }
        }
        return null;
    }

    /**
     * 将汉字单个数字转换为int类型数字
     *
     * @param chinese 汉字数字，支持简体和繁体
     * @return 数字，-1表示未找到
     * @since 5.6.4
     */
    private static int chineseToNumber(char chinese) {
        if ('两' == chinese) {
            // 口语纠正
            chinese = '二';
        }
        final int i = ArrayUtil.indexOf(DIGITS, chinese);
        if (i > 0) {
            return (i + 1) / 2;
        }
        return i;
    }

    /**
     * 单个数字转汉字
     *
     * @param number           数字
     * @param isUseTraditional 是否使用繁体
     * @return 汉字
     */
    private static char numberToChinese(int number, boolean isUseTraditional) {
        if (0 == number) {
            return DIGITS[0];
        }
        return DIGITS[number * 2 - (isUseTraditional ? 0 : 1)];
    }

    /**
     * 获取对应级别的单位
     *
     * @param index            级别，0表示各位，1表示十位，2表示百位，以此类推
     * @param isUseTraditional 是否使用繁体
     * @return 单位
     */
    private static String getUnitName(int index, boolean isUseTraditional) {
        if (0 == index) {
            return StrUtil.EMPTY;
        }
        return String.valueOf(CHINESE_NAME_VALUE[index * 2 - (isUseTraditional ? 0 : 1)].name);
    }

    /**
     * 权位
     *
     * @author totalo
     * @since 5.6.0
     */
    private static class ChineseUnit {
        /**
         * 中文权名称
         */
        private final char name;
        /**
         * 10的倍数值
         */
        private final int value;
        /**
         * 是否为节权位，它不是与之相邻的数字的倍数，而是整个小节的倍数。<br>
         * 例如二十三万，万是节权位，与三无关，而和二十三关联
         */
        private final boolean secUnit;

        /**
         * 构造
         *
         * @param name    名称
         * @param value   值，即10的倍数
         * @param secUnit 是否为节权位
         */
        public ChineseUnit(char name, int value, boolean secUnit) {
            this.name = name;
            this.value = value;
            this.secUnit = secUnit;
        }
    }

    private static void addPreZero(StringBuilder chineseStr){
        if(StrUtil.isEmpty(chineseStr)){
            return;
        }
        final char c = chineseStr.charAt(0);
        if('零' != c){
            chineseStr.insert(0, '零');
        }
    }


    private static final long serialVersionUID = 1L;

    /**
     * Bean类
     */
    private  Class<?> beanClass;
    /**
     * 属性Map
     */
    private  Map<String, PropDesc> propMap = new LinkedHashMap<>();

    /**
     * 构造
     *
     * @param beanClass Bean类
     */
    public CopyOptionsTest(Class<?> beanClass) {
        Assert.notNull(beanClass);
        this.beanClass = beanClass;
        init();
    }

    /**
     * 获取Bean的全类名
     *
     * @return Bean的类名
     */
    public String getName() {
        return this.beanClass.getName();
    }

    /**
     * 获取Bean的简单类名
     *
     * @return Bean的类名
     */
    public String getSimpleName() {
        return this.beanClass.getSimpleName();
    }

    /**
     * 获取字段名-字段属性Map
     *
     * @param ignoreCase 是否忽略大小写，true为忽略，false不忽略
     * @return 字段名-字段属性Map
     */
    public Map<String, PropDesc> getPropMap(boolean ignoreCase) {
        return ignoreCase ? new CaseInsensitiveMap<>(1, this.propMap) : this.propMap;
    }

    /**
     * 获取字段属性列表
     *
     * @return {@link PropDesc} 列表
     */
    public Collection<PropDesc> getProps() {
        return this.propMap.values();
    }

    /**
     * 获取属性，如果不存在返回null
     *
     * @param fieldName 字段名
     * @return {@link PropDesc}
     */
    public PropDesc getProp(String fieldName) {
        return this.propMap.get(fieldName);
    }

    /**
     * 获得字段名对应的字段对象，如果不存在返回null
     *
     * @param fieldName 字段名
     * @return 字段值
     */
    public Field getField(String fieldName) {
        final PropDesc desc = this.propMap.get(fieldName);
        return null == desc ? null : desc.getField();
    }

    /**
     * 获取Getter方法，如果不存在返回null
     *
     * @param fieldName 字段名
     * @return Getter方法
     */
    public Method getGetter(String fieldName) {
        final PropDesc desc = this.propMap.get(fieldName);
        return null == desc ? null : desc.getGetter();
    }

    /**
     * 获取Setter方法，如果不存在返回null
     *
     * @param fieldName 字段名
     * @return Setter方法
     */
    public Method getSetter(String fieldName) {
        final PropDesc desc = this.propMap.get(fieldName);
        return null == desc ? null : desc.getSetter();
    }

    // ------------------------------------------------------------------------------------------------------ Private method start

    /**
     * 初始化<br>
     * 只有与属性关联的相关Getter和Setter方法才会被读取，无关的getXXX和setXXX都被忽略
     *
     * @return this
     */
    private CopyOptionsTest init() {
        final Method[] methods = ReflectUtil.getMethods(this.beanClass);
        PropDesc prop;
        for (Field field : ReflectUtil.getFields(this.beanClass)) {
            if (false == ModifierUtil.isStatic(field)) {
                //只针对非static属性
                prop = createProp(field, methods);
                // 只有不存在时才放入，防止父类属性覆盖子类属性
                this.propMap.putIfAbsent(prop.getFieldName(), prop);
            }
        }
        return this;
    }

    /**
     * 根据字段创建属性描述<br>
     * 查找Getter和Setter方法时会：
     *
     * <pre>
     * 1. 忽略字段和方法名的大小写
     * 2. Getter查找getXXX、isXXX、getIsXXX
     * 3. Setter查找setXXX、setIsXXX
     * 4. Setter忽略参数值与字段值不匹配的情况，因此有多个参数类型的重载时，会调用首次匹配的
     * </pre>
     *
     * @param field   字段
     * @param methods 类中所有的方法
     * @return {@link PropDesc}
     * @since 4.0.2
     */
    private PropDesc createProp(Field field, Method[] methods) {
        final PropDesc prop = findProp(field, methods, false);
//        // 忽略大小写重新匹配一次
//        if (null == prop.getter || null == prop.setter) {
//            final PropDesc propIgnoreCase = findProp(field, methods, true);
//            if (null == prop.getter) {
//                prop.getter = propIgnoreCase.getter;
//            }
//            if (null == prop.setter) {
//                prop.setter = propIgnoreCase.setter;
//            }
//        }

        return prop;
    }

    /**
     * 查找字段对应的Getter和Setter方法
     *
     * @param field      字段
     * @param methods    类中所有的方法
     * @param ignoreCase 是否忽略大小写匹配
     * @return PropDesc
     */
    private PropDesc findProp(Field field, Method[] methods, boolean ignoreCase) {
        final String fieldName = field.getName();
        final Class<?> fieldType = field.getType();
        final boolean isBooleanField = BooleanUtil.isBoolean(fieldType);

        Method getter = null;
        Method setter = null;
        String methodName;
        Class<?>[] parameterTypes;
        for (Method method : methods) {
            parameterTypes = method.getParameterTypes();
            if (parameterTypes.length > 1) {
                // 多于1个参数说明非Getter或Setter
                continue;
            }

            methodName = method.getName();
            if (parameterTypes.length == 0) {
                // 无参数，可能为Getter方法
                if (isMatchGetter(methodName, fieldName, isBooleanField, ignoreCase)) {
                    // 方法名与字段名匹配，则为Getter方法
                    getter = method;
                }
            } else if (isMatchSetter(methodName, fieldName, isBooleanField, ignoreCase)) {
                // 只有一个参数的情况下方法名与字段名对应匹配，则为Setter方法
                setter = method;
            }
            if (null != getter && null != setter) {
                // 如果Getter和Setter方法都找到了，不再继续寻找
                break;
            }
        }

        return new PropDesc(field, getter, setter);
    }

    /**
     * 方法是否为Getter方法<br>
     * 匹配规则如下（忽略大小写）：
     *
     * <pre>
     * 字段名    -》 方法名
     * isName  -》 isName
     * isName  -》 isIsName
     * isName  -》 getIsName
     * name     -》 isName
     * name     -》 getName
     * </pre>
     *
     * @param methodName     方法名
     * @param fieldName      字段名
     * @param isBooleanField 是否为Boolean类型字段
     * @param ignoreCase     匹配是否忽略大小写
     * @return 是否匹配
     */
    private boolean isMatchGetter(String methodName, String fieldName, boolean isBooleanField, boolean ignoreCase) {
        final String handledFieldName;
        if (ignoreCase) {
            // 全部转为小写，忽略大小写比较
            methodName = methodName.toLowerCase();
            handledFieldName = fieldName.toLowerCase();
            fieldName = handledFieldName;
        } else {
            handledFieldName = StrUtil.upperFirst(fieldName);
        }

        if (false == methodName.startsWith("get") && false == methodName.startsWith("is")) {
            // 非标准Getter方法
            return false;
        }
        if ("getclass".equals(methodName)) {
            //跳过getClass方法
            return false;
        }

        // 针对Boolean类型特殊检查
        if (isBooleanField) {
            if (fieldName.startsWith("is")) {
                // 字段已经是is开头
                if (methodName.equals(fieldName) // isName -》 isName
                        || ("get" + handledFieldName).equals(methodName)// isName -》 getIsName
                        || ("is" + handledFieldName).equals(methodName)// isName -》 isIsName
                ) {
                    return true;
                }
            } else if (("is" + handledFieldName).equals(methodName)) {
                // 字段非is开头， name -》 isName
                return true;
            }
        }

        // 包括boolean的任何类型只有一种匹配情况：name -》 getName
        return ("get" + handledFieldName).equals(methodName);
    }

    /**
     * 方法是否为Setter方法<br>
     * 匹配规则如下（忽略大小写）：
     *
     * <pre>
     * 字段名    -》 方法名
     * isName  -》 setName
     * isName  -》 setIsName
     * name     -》 setName
     * </pre>
     *
     * @param methodName     方法名
     * @param fieldName      字段名
     * @param isBooleanField 是否为Boolean类型字段
     * @param ignoreCase     匹配是否忽略大小写
     * @return 是否匹配
     */
    private boolean isMatchSetter(String methodName, String fieldName, boolean isBooleanField, boolean ignoreCase) {
        final String handledFieldName;
        if (ignoreCase) {
            // 全部转为小写，忽略大小写比较
            methodName = methodName.toLowerCase();
            handledFieldName = fieldName.toLowerCase();
            fieldName = handledFieldName;
        } else {
            handledFieldName = StrUtil.upperFirst(fieldName);
        }

        // 非标准Setter方法跳过
        if (false == methodName.startsWith("set")) {
            return false;
        }

        // 针对Boolean类型特殊检查
        if (isBooleanField && fieldName.startsWith("is")) {
            // 字段是is开头
            if (("set" + StrUtil.removePrefix(fieldName, "is")).equals(methodName)// isName -》 setName
                    || ("set" + handledFieldName).equals(methodName)// isName -》 setIsName
            ) {
                return true;
            }
        }

        // 包括boolean的任何类型只有一种匹配情况：name -》 setName
        return ("set" + fieldName).equals(methodName);
    }
    // ------------------------------------------------------------------------------------------------------ Private method end
}
