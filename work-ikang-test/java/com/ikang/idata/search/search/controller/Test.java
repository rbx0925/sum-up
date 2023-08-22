package com.ikang.idata.search.search.controller;

import cn.hutool.core.date.DateField;
import com.ikang.idata.common.utils.DateUtil;
import com.ikang.idata.common.utils.StringUtil;
import com.ikang.idata.search.search.TestSpring;
import jodd.util.function.Consumers;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

import static com.ikang.idata.common.consts.MagicConst.INTEGER_0;

/**
 * @author <a href="yanan.mu-ext@ikang.com">jiangfeng</a>
 * @date 8/3/2022
 */
@TestSpring
public class Test {

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
            User user = new User("测试名称");
            User user1 = new User(null);

            //使用Optional存储User对象
            Optional<User> optionalUser = Optional.ofNullable(user);
            Optional<User> optionalUser1 = Optional.ofNullable(user1);

            //获取对象的name属性值
            String name = optionalUser.map(User::getName).orElse("未填写");
            String name1 = optionalUser1.map(User::getName).orElse("未填写");

            //输出结果
            System.out.println("获取的名称 " + name);
            System.out.println("获取的名称 " + name1);

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
            List<User> userList = new ArrayList<>();

            //创建几个测试用户
            User user1 = new User("abc");
            User user2 = new User("efg");
            User user3 = null;

            //将用户加入集合
            userList.add(user1);
            userList.add(user2);
            userList.add(user3);

            //创建用于存储姓名的集合
            List<String> nameList = new ArrayList<>();

            //循环用户列表获取用户信息,值获取不为空且用户以a开头的姓名
            //如果不符合条件就设置默认值 最后将符合条件的用户姓名加入姓名集合
            for (User user : userList) {
                // nameList.add(Optional.ofNullable(user).map(User::getName).filter(value -> value.startsWith("a")));

            }
            System.out.println("通过Optional过滤的集合输出");
            nameList.stream().forEach(System.out::println);
        }

    }


}
