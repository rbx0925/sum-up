package com.ikang.idata.search.search.common;

import cn.hutool.core.date.DateField;
import com.ikang.idata.common.utils.DateUtil;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.Person;
import jodd.util.function.Consumers;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static com.ikang.idata.common.consts.MagicConst.INTEGER_0;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description Map和Bean的相互转换
 * @date 2022/4/2
 */
public class BeanTest {

//    public static void main(String[] args) {
//
//        System.out.println("好累!!!");
//        IntStream.range(0, 100).boxed().forEachOrdered(System.out::println);
//        Person person1 = new Person();
//        person1.setName("name1");
//        person1.setSex("sex1");
//        Map<String, String> map = null;
//        try {
//            map = BeanUtils.describe(person1);
//
//            //=============Bean转换成Map=======================//
//            System.out.println(map.size() + ">>>>>>>>>>>");
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//
//        Person person = BeanTest.map2Bean(map, Person.class);
//        System.out.println(person.getName());
//
//        System.out.println("使用 Java 7: ");
//
//        // 计算空字符串
//        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
//        System.out.println("列表: " + strings);
//        long count = 0;
//
//        System.out.println("空字符数量为: " + count);
//        count = 0;
//
//        System.out.println("字符串长度为 3 的数量为: " + count);
//
//        // 删除空字符串
//        List<String> filtered = null;
//        System.out.println("筛选后的列表: " + filtered);
//
//        // 删除空字符串，并使用逗号把它们合并起来
//        String mergedString = "getMergedStringUsingJava7(strings";
//        System.out.println("合并字符串: " + mergedString);
//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//    }

    /**
     * Map转换层Bean，使用泛型免去了类型转换的麻烦。
     *
     * @param <T>
     * @param map
     * @param class1
     * @return
     *
     */
//    public static <T> T map2Bean(Map<String, String> map, Class<T> class1) {
//        T bean = null;
//        try {
//            bean = class1.newInstance();
//            BeanUtils.populate(bean, map);
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        return bean;
//    }

    @Test
    public void test() {

    }


    @Test
    public void test01(){
        String v1 = "1";
        String v2 = "0";
        v1 = StringUtil.isEmpty(v1) || "null".equals(v1) ? "0" : v1;
        v2 = StringUtil.isEmpty(v2) || "null".equals(v2) ? "0" : v2;
        if (INTEGER_0.equals(v2)) {
            System.out.println(v2);
        }
//
//         if (INTEGER_0.equals(v2) && NumberUtil.isNumber(v1)) {
//                return new BigDecimal("1");
//            }
//            if (INTEGER_0.equals(v1) && NumberUtil.isNumber(v2)) {
//                return new BigDecimal("-1");
//            }
//            return NumberUtil.div(NumberUtil.sub(v1, v2), new BigDecimal(v2)).setScale(4, BigDecimal.ROUND_HALF_UP);

    }

    @Test
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

    @Test
    public void test2(){
        String incomeLastYear = null,packageReceivableAmountLastYear = null;
        System.out.println(packageReceivableAmountLastYear);
        System.out.println(incomeLastYear);
    }




    //静态方法 Optional.of()
    //为指定的值创建一个指定非 null 值的 Optional。
    @Test
    public void ofTest(){
        //传入正常值 正常返回一个Optional对象
        Optional<String> optional= Optional.of("加油");
        System.out.println(optional);

//
//      传入null值  则会报 NullPointerException
//        Optional optional1= Optional.of(null);
//        System.out.println(optional1);


    }


    //
    // 静态方法 Optional.ofNullable()
    //为指定的值创建一个 Optional 对象，如果指定的参数为 null，不抛出异常，直接则返回一个空的 Optional 对象。

    @Test
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
    @Test
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
    @Test
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
    @Test
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
    @Test
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
    @Test
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



    @Test
    public void beabTest(){
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

}

