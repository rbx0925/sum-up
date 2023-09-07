package com.ikang.idata.search.search;

import cn.hutool.core.bean.*;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/3/21
 */
public class StudentStreamTest {

    public static void main(String[] args) {
        Student stuA = new Student(1,"那边v从","w",125);
        Student stuB = new Student(2,"规划局","h",185);
        Student stuC = new Student(3,"吧v","h",165);
        Student stuD = new Student(4,"听哟","w",135);
        Student stuE = new Student(5,"提u","h",190);

        List<Student> list = new ArrayList<>();
        list.add(stuA);
        list.add(stuB);
        list.add(stuC);
        list.add(stuD);
        list.add(stuE);

        //假如我们想获取Sex=“G”的Student，并打印出来
        Iterator<Student> iterator = list.iterator();
        System.out.println(iterator);
        while (iterator.hasNext()){
            Student stu = iterator.next();
            if(stu.getSex().equals("h")){
                System.out.println(stu.toString());
            }

        }

        //方式二 假如我们想获取Sex=“G”的Student，并打印出来
        list.stream()
                .filter(student -> student.getSex().equals("h"))
                .forEach(student -> System.out.println(student.toString()));
        //

    }

    /**
     * 创建Stream:通过stream()方法，取得集合对象的数据集。
     * Intermediate:通过一系列中间（Intermediate）方法，对数据集进行过滤、检索等数据集的再次处理。如上例中，使用filter()方法来对数据集进行过滤。
     * Terminal通过最终（terminal）方法完成对数据集中元素的处理。如上例中，使用forEach()完成对过滤后元素的打印。
     * Intermediate：map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 skip、 parallel、 sequential、 unordered
     * Terminal：forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、iterator
     * Short-circuiting： anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
     */


    @Test
    public  void gTest1(){}
    class gfg {

        // Function to skip the elements of stream upto given range, i.e, 3
        public Stream<String> skip_func(Stream<String> ss, int range) {
            return ss.skip(range);
        }

        // Driver code
        public void main(String[] args) {

            // list to save stream of strings
            List<String> arr = new ArrayList<>();

            arr.add("geeksd");
            arr.add("fosr");
            arr.add("geekcs");
            arr.add("compduter");
            arr.add("sciencce");

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
    @Test
    public void test1(){
        List<String> list = new ArrayList<>();
        list.add("武汉加油");
        list.add("中国加油");
        list.add("世界加油");
        list.add("世界加油");

        long count = list.stream().distinct().count();
        System.out.println(count);
    }



    @Test
    public void test2(){
        String[] arr = new String[]{"武汉加油", "中国加油", "世界加油"};
        Stream<String> stream = Arrays.stream(arr);

        stream = Stream.of("武汉加油", "中国加油", "世界加油");

        List<String> list = new ArrayList<>();
        list.add("武汉加油");
        list.add("中国加油");
        list.add("世界加油");
        stream = list.stream();
    }


    @Test
    public void test3(){
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");
        Stream<String> stream = list.stream().filter(element -> element.contains("王"));
        stream.forEach(System.out::println);
        List<String> list1 = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");
        Stream<String> stream1 = list.stream().filter(element -> element.contains("王"));
        stream.forEach(System.out::println);
    }


    @Test
    public void test4(){
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");
        Stream<Integer> stream = list.stream().map(String::length);
        stream.forEach(System.out::println);
    }


    @Test
    public void test5(){
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");

        boolean  anyMatchFlag = list.stream().anyMatch(element -> element.contains("王"));
        boolean  allMatchFlag = list.stream().allMatch(element -> element.length() > 1);
        boolean  noneMatchFlag = list.stream().noneMatch(element -> element.endsWith("沉"));
        System.out.println(anyMatchFlag);
        System.out.println(allMatchFlag);
        System.out.println(noneMatchFlag);
    }

    @Test
    public void test6(){
        Integer[] ints = {0, 1, 2, 3};
        List<Integer> list = Arrays.asList(ints);

        Optional<Integer> optional = list.stream().reduce((a, b) -> a + b);
        Optional<Integer> optional1 = list.stream().reduce(Integer::sum);
        System.out.println(optional.orElse(0));
        System.out.println(optional1.orElse(0));

        int reduce = list.stream().reduce(6, (a, b) -> a + b);
        System.out.println(reduce);
        int reduce1 = list.stream().reduce(6, Integer::sum);
        System.out.println(reduce1);
    }

    @Test
    public void test7(){
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");

        String[] strArray = list.stream().toArray(String[]::new);
        System.out.println(Arrays.toString(strArray));

        List<Integer> list1 = list.stream().map(String::length).collect(Collectors.toList());
        List<String> list2 = list.stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list1);
        System.out.println(list2);

        String str = list.stream().collect(Collectors.joining(", ")).toString();
        System.out.println(str);
    }


    @Test
    public void test8(){
        List<String> list = new ArrayList<>();
        list.add("周杰伦");
        list.add("王力宏");
        list.add("陶喆");
        list.add("林俊杰");

        String[] strArray = list.stream().toArray(String[]::new);
        System.out.println(Arrays.toString(strArray));

        List<Integer> list1 = list.stream().map(String::length).collect(Collectors.toList());
        List<String> list2 = list.stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list1);
        System.out.println(list2);

        String str = list.stream().collect(Collectors.joining(", ")).toString();
        System.out.println(str);
    }


    @Test
    public void NormalFilter() {

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
    public void StreamFilter () {

        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");

        list.stream()
                .filter(s -> s.startsWith("张"))
                .filter(s -> s.length() == 3)
                .forEach(System.out::println);
    }


    @Test
    public  void main() {
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();
        Set<String> set = new HashSet<>();
        Stream<String> stream2 = set.stream();
        Vector<String> vector = new Vector<>();
    Map<String, String> map = new HashMap<>();
    Stream<String> keyStream = map.keySet().stream();
    Stream<String> valueStream = map.values().stream(); 	Stream<Map.Entry<String, String>> entryStream = 		map.entrySet().stream();

        String[] array = { "张无忌", "张翠山", "张三丰", "张一元" };
        Stream<String> stream = Stream.of(array);

    }


    /** 表达式边界符号数组 */
    private static final char[] EXP_CHARS = { CharUtil.DOT, CharUtil.BRACKET_START, CharUtil.BRACKET_END };

    private boolean isStartWith = false;
    protected List<String> patternParts;

    /**
     * 解析Bean路径表达式为Bean模式<br>
     * Bean表达式，用于获取多层嵌套Bean中的字段值或Bean对象<br>
     * 根据给定的表达式，查找Bean中对应的属性值对象。 表达式分为两种：
     * <ol>
     * <li>.表达式，可以获取Bean对象中的属性（字段）值或者Map中key对应的值</li>
     * <li>[]表达式，可以获取集合等对象中对应index的值</li>
     * </ol>
     *
     * 表达式栗子：
     *
     * <pre>
     * persion
     * persion.name
     * persons[3]
     * person.friends[5].name
     * ['person']['friends'][5]['name']
     * </pre>
     *
     * @param expression 表达式
     * @return BeanPath
     */
    public static BeanPath create(String expression) {
        return new BeanPath(expression);
    }

    /**
     * 构造
     *
     * @param expression 表达式
     */
    public StudentStreamTest(String expression) {
        init(expression);
    }

    /**
     * 获取Bean中对应表达式的值
     *
     * @param bean Bean对象或Map或List等
     * @return 值，如果对应值不存在，则返回null
     */
    public Object get(Object bean) {
        return get(this.patternParts, bean, false);
    }

    /**
     * 设置表达式指定位置（或filed对应）的值<br>
     * 若表达式指向一个List则设置其坐标对应位置的值，若指向Map则put对应key的值，Bean则设置字段的值<br>
     * 注意：
     *
     * <pre>
     * 1. 如果为List，如果下标不大于List长度，则替换原有值，否则追加值
     * 2. 如果为数组，如果下标不大于数组长度，则替换原有值，否则追加值
     * </pre>
     *
     * @param bean Bean、Map或List
     * @param value 值
     */
    public void set(Object bean, Object value) {
        set(bean, this.patternParts, value);
    }

    /**
     * 设置表达式指定位置（或filed对应）的值<br>
     * 若表达式指向一个List则设置其坐标对应位置的值，若指向Map则put对应key的值，Bean则设置字段的值<br>
     * 注意：
     *
     * <pre>
     * 1. 如果为List，如果下标不大于List长度，则替换原有值，否则追加值
     * 2. 如果为数组，如果下标不大于数组长度，则替换原有值，否则追加值
     * </pre>
     *
     * @param bean Bean、Map或List
     * @param patternParts 表达式块列表
     * @param value 值
     */
    private void set(Object bean, List<String> patternParts, Object value) {
        Object subBean = get(patternParts, bean, true);
        if(null == subBean) {
            set(bean, patternParts.subList(0, patternParts.size() - 1), new HashMap<>());
            //set中有可能做过转换，因此此处重新获取bean
            subBean = get(patternParts, bean, true);
        }
        BeanUtil.setFieldValue(subBean, patternParts.get(patternParts.size() - 1), value);
    }

    // ------------------------------------------------------------------------------------------------------------------------------------- Private method start
    /**
     * 获取Bean中对应表达式的值
     *
     * @param patternParts 表达式分段列表
     * @param bean Bean对象或Map或List等
     * @param ignoreLast 是否忽略最后一个值，忽略最后一个值则用于set，否则用于read
     * @return 值，如果对应值不存在，则返回null
     */
    private Object get(List<String> patternParts, Object bean, boolean ignoreLast) {
        int length = patternParts.size();
        if (ignoreLast) {
            length--;
        }
        Object subBean = bean;
        boolean isFirst = true;
        String patternPart;
        for (int i = 0; i < length; i++) {
            patternPart = patternParts.get(i);
            subBean = getFieldValue(subBean, patternPart);
            if (null == subBean) {
                // 支持表达式的第一个对象为Bean本身（若用户定义表达式$开头，则不做此操作）
                if (isFirst && false == this.isStartWith && BeanUtil.isMatchName(bean, patternPart, true)) {
                    subBean = bean;
                    isFirst = false;
                } else {
                    return null;
                }
            }
        }
        return subBean;
    }

    @SuppressWarnings("unchecked")
    private static Object getFieldValue(Object bean, String expression) {
        if (StrUtil.isBlank(expression)) {
            return null;
        }

        if (StrUtil.contains(expression, ':')) {
            // [start:end:step] 模式
            final List<String> parts = StrUtil.splitTrim(expression, ':');
            int start = Integer.parseInt(parts.get(0));
            int end = Integer.parseInt(parts.get(1));
            int step = 1;
            if (3 == parts.size()) {
                step = Integer.parseInt(parts.get(2));
            }
            if (bean instanceof Collection) {
                return CollUtil.sub((Collection<?>) bean, start, end, step);
            } else if (ArrayUtil.isArray(bean)) {
                return ArrayUtil.sub(bean, start, end, step);
            }
        } else if (StrUtil.contains(expression, ',')) {
            // [num0,num1,num2...]模式或者['key0','key1']模式
            final List<String> keys = StrUtil.splitTrim(expression, ',');
            if (bean instanceof Collection) {
                return CollUtil.getAny((Collection<?>) bean, Convert.convert(int[].class, keys));
            } else if (ArrayUtil.isArray(bean)) {
                return ArrayUtil.getAny(bean, Convert.convert(int[].class, keys));
            } else {
                final String[] unWrappedKeys = new String[keys.size()];
                for (int i = 0; i < unWrappedKeys.length; i++) {
                    unWrappedKeys[i] = StrUtil.unWrap(keys.get(i), '\'');
                }
                if (bean instanceof Map) {
                    // 只支持String为key的Map
                    return MapUtil.getAny((Map<String, ?>) bean, unWrappedKeys);
                } else {
                    final Map<String, Object> map = BeanUtil.beanToMap(bean);
                    return MapUtil.getAny(map, unWrappedKeys);
                }
            }
        } else {
            // 数字或普通字符串
            return BeanUtil.getFieldValue(bean, expression);
        }

        return null;
    }

    /**
     * 初始化
     *
     * @param expression 表达式
     */
    private void init(String expression) {
        List<String> localPatternParts = new ArrayList<>();
        int length = expression.length();

        final StrBuilder builder = StrUtil.strBuilder();
        char c;
        boolean isNumStart = false;// 下标标识符开始
        for (int i = 0; i < length; i++) {
            c = expression.charAt(i);
            if (0 == i && '$' == c) {
                // 忽略开头的$符，表示当前对象
                isStartWith = true;
                continue;
            }

            if (ArrayUtil.contains(EXP_CHARS, c)) {
                // 处理边界符号
                if (CharUtil.BRACKET_END == c) {
                    // 中括号（数字下标）结束
                    if (false == isNumStart) {
                        throw new IllegalArgumentException(StrUtil.format("Bad expression '{}':{}, we find ']' but no '[' !", expression, i));
                    }
                    isNumStart = false;
                    // 中括号结束加入下标
                } else {
                    if (isNumStart) {
                        // 非结束中括号情况下发现起始中括号报错（中括号未关闭）
                        throw new IllegalArgumentException(StrUtil.format("Bad expression '{}':{}, we find '[' but no ']' !", expression, i));
                    } else if (CharUtil.BRACKET_START == c) {
                        // 数字下标开始
                        isNumStart = true;
                    }
                    // 每一个边界符之前的表达式是一个完整的KEY，开始处理KEY
                }
                if (builder.length() > 0) {
                    localPatternParts.add(unWrapIfPossible(builder));
                }
                builder.reset();
            } else {
                // 非边界符号，追加字符
                builder.append(c);
            }
        }

        // 末尾边界符检查
        if (isNumStart) {
            throw new IllegalArgumentException(StrUtil.format("Bad expression '{}':{}, we find '[' but no ']' !", expression, length - 1));
        } else {
            if (builder.length() > 0) {
                localPatternParts.add(unWrapIfPossible(builder));
            }
        }

        // 不可变List
        this.patternParts = Collections.unmodifiableList(localPatternParts);
    }

    /**
     * 对于非表达式去除单引号
     *
     * @param expression 表达式
     * @return 表达式
     */
    private static String unWrapIfPossible(CharSequence expression) {
        if (StrUtil.containsAny(expression, " = ", " > ", " < ", " like ", ",")) {
            return expression.toString();
        }
        return StrUtil.unWrap(expression, '\'');
    }
    // ------------------------------------------------------------------------------------------------------------------------------------- Private method end



    private static final long serialVersionUID = 1L;

    private  Class<?> beanClass;
    private  Object bean;

    /**
     * 创建一个DynaBean
     *
     * @param bean 普通Bean
     * @return DynaBean
     */
    public static DynaBean create(Object bean) {
        return new DynaBean(bean);
    }

    /**
     * 创建一个DynaBean
     *
     * @param beanClass Bean类
     * @return DynaBean
     */
    public static DynaBean create(Class<?> beanClass) {
        return new DynaBean(beanClass);
    }


    /**
     * 创建一个DynaBean
     *
     * @param beanClass Bean类
     * @param params    构造Bean所需要的参数
     * @return DynaBean
     */
    public static DynaBean create(Class<?> beanClass, Object... params) {
        return new DynaBean(beanClass, params);
    }

    //------------------------------------------------------------------------ Constructor start

    /**
     * 构造
     *
     * @param beanClass Bean类
     * @param params    构造Bean所需要的参数
     */
    public StudentStreamTest(Class<?> beanClass, Object... params) {
        this(ReflectUtil.newInstance(beanClass, params));
    }

    /**
     * 构造
     *
     * @param beanClass Bean类
     */
    public StudentStreamTest(Class<?> beanClass) {
        this(ReflectUtil.newInstance(beanClass));
    }

    /**
     * 构造
     *
     * @param bean 原始Bean
     */
    public StudentStreamTest(Object bean) {
        Assert.notNull(bean);
        if (bean instanceof DynaBean) {
            bean = ((DynaBean) bean).getBean();
        }
        this.bean = bean;
        this.beanClass = ClassUtil.getClass(bean);
    }
    //------------------------------------------------------------------------ Constructor end

    /**
     * 获得字段对应值
     *
     * @param <T>       属性值类型
     * @param fieldName 字段名
     * @return 字段值
     * @throws BeanException 反射获取属性值或字段值导致的异常
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String fieldName) throws BeanException {
        if (Map.class.isAssignableFrom(beanClass)) {
            return (T) ((Map<?, ?>) bean).get(fieldName);
        } else {
            final PropDesc prop = BeanUtil.getBeanDesc(beanClass).getProp(fieldName);
            if (null == prop) {
                throw new BeanException("No public field or get method for {}", fieldName);
            }
            return (T) prop.getValue(bean);
        }
    }

    /**
     * 检查是否有指定名称的bean属性
     *
     * @param fieldName 字段名
     * @return 是否有bean属性
     * @since 5.4.2
     */
    public boolean containsProp(String fieldName) {
        return null != BeanUtil.getBeanDesc(beanClass).getProp(fieldName);
    }

    /**
     * 获得字段对应值，获取异常返回{@code null}
     *
     * @param <T>       属性值类型
     * @param fieldName 字段名
     * @return 字段值
     * @since 3.1.1
     */
    public <T> T safeGet(String fieldName) {
        try {
            return get(fieldName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 设置字段值
     *
     * @param fieldName 字段名
     * @param value     字段值
     * @throws BeanException 反射获取属性值或字段值导致的异常
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void set(String fieldName, Object value) throws BeanException {
        if (Map.class.isAssignableFrom(beanClass)) {
            ((Map) bean).put(fieldName, value);
        } else {
            final PropDesc prop = BeanUtil.getBeanDesc(beanClass).getProp(fieldName);
            if (null == prop) {
                throw new BeanException("No public field or set method for {}", fieldName);
            }
            prop.setValue(bean, value);
        }
    }

    /**
     * 执行原始Bean中的方法
     *
     * @param methodName 方法名
     * @param params     参数
     * @return 执行结果，可能为null
     */
    public Object invoke(String methodName, Object... params) {
        return ReflectUtil.invoke(this.bean, methodName, params);
    }

    /**
     * 获得原始Bean
     *
     * @param <T> Bean类型
     * @return bean
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean() {
        return (T) this.bean;
    }

    /**
     * 获得Bean的类型
     *
     * @param <T> Bean类型
     * @return Bean类型
     */
    @SuppressWarnings("unchecked")
    public <T> Class<T> getBeanClass() {
        return (Class<T>) this.beanClass;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bean == null) ? 0 : bean.hashCode());
        return result;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final DynaBean other = (DynaBean) obj;
//        if (bean == null) {
//            return other.bean == null;
//        } else return bean.equals(other.bean);
//    }

    @Override
    public String toString() {
        return this.bean.toString();
    }
}
