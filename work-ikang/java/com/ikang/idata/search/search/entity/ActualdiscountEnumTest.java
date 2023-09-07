package com.ikang.idata.search.search.entity;

import jodd.util.function.Consumers;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

class ActualdiscountEnumTest {

    @Test
    void testGetFields() {
        assertThat(ActualdiscountEnum.id.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.projectid.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.qysj.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.signarea.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.xmpjzk.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.jazk.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.vipzk.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.zyzk.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.zdzk.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.htype.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.regdate.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.cnt.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.packagesaleprice.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.packagemarketprice.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.isdelete.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.regmonth.getFields()).isEqualTo("fields");
        assertThat(ActualdiscountEnum.md5.getFields()).isEqualTo("fields");
    }

    @Test
    void testSetFields() {
        // Run the test
        ActualdiscountEnum.id.setFields("fields");
        ActualdiscountEnum.projectid.setFields("fields");
        ActualdiscountEnum.qysj.setFields("fields");
        ActualdiscountEnum.signarea.setFields("fields");
        ActualdiscountEnum.xmpjzk.setFields("fields");
        ActualdiscountEnum.jazk.setFields("fields");
        ActualdiscountEnum.vipzk.setFields("fields");
        ActualdiscountEnum.zyzk.setFields("fields");
        ActualdiscountEnum.zdzk.setFields("fields");
        ActualdiscountEnum.htype.setFields("fields");
        ActualdiscountEnum.regdate.setFields("fields");
        ActualdiscountEnum.cnt.setFields("fields");
        ActualdiscountEnum.packagesaleprice.setFields("fields");
        ActualdiscountEnum.packagemarketprice.setFields("fields");
        ActualdiscountEnum.isdelete.setFields("fields");
        ActualdiscountEnum.regmonth.setFields("fields");
        ActualdiscountEnum.md5.setFields("fields");

        // Verify the results
    }

    @Test
    void testGetDesc() {
        assertThat(ActualdiscountEnum.id.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.projectid.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.qysj.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.signarea.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.xmpjzk.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.jazk.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.vipzk.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.zyzk.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.zdzk.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.htype.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.regdate.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.cnt.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.packagesaleprice.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.packagemarketprice.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.isdelete.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.regmonth.getDesc()).isEqualTo("desc");
        assertThat(ActualdiscountEnum.md5.getDesc()).isEqualTo("desc");
    }

    @Test
    void testSetDesc() {
        // Run the test
        ActualdiscountEnum.id.setDesc("desc");
        ActualdiscountEnum.projectid.setDesc("desc");
        ActualdiscountEnum.qysj.setDesc("desc");
        ActualdiscountEnum.signarea.setDesc("desc");
        ActualdiscountEnum.xmpjzk.setDesc("desc");
        ActualdiscountEnum.jazk.setDesc("desc");
        ActualdiscountEnum.vipzk.setDesc("desc");
        ActualdiscountEnum.zyzk.setDesc("desc");
        ActualdiscountEnum.zdzk.setDesc("desc");
        ActualdiscountEnum.htype.setDesc("desc");
        ActualdiscountEnum.regdate.setDesc("desc");
        ActualdiscountEnum.cnt.setDesc("desc");
        ActualdiscountEnum.packagesaleprice.setDesc("desc");
        ActualdiscountEnum.packagemarketprice.setDesc("desc");
        ActualdiscountEnum.isdelete.setDesc("desc");
        ActualdiscountEnum.regmonth.setDesc("desc");
        ActualdiscountEnum.md5.setDesc("desc");

        // Verify the results
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
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //使用 Optional 的 map 方法对值处理：
//        @org.junit.jupiter.api.Test
//        public void orElseTest1() {
//            //创建一个对象  设置名字属性而不设置性别  这时候性别为null
//            Test.User user = new Test.User("测试名称");
//            Test.User user1 = new Test.User(null);
//
//            //使用Optional存储User对象
//            Optional<Test.User> optionalUser = Optional.ofNullable(user);
//            Optional<Test.User> optionalUser1 = Optional.ofNullable(user1);
//
//            //获取对象的name属性值
//            String name = optionalUser.map(Test.User::getName).orElse("未填写");
//            String name1 = optionalUser1.map(Test.User::getName).orElse("未填写");
//
//            //输出结果
//            System.out.println("获取的名称 " + name);
//            System.out.println("获取的名称 " + name1);
//
//            /**
//             * 打印结果
//             * name 测试名称
//             * name1 未填写
//             */
//            //通过上面两个示例观察到，通过 Optional 对象的 map 方法能够获取映射对象中的属，创建 Optional 对象，并以此属性充当 Optional 的值，结合 orElse 方法，如果获取的属性的值为空，则设置个默认值。
//        }

        //对象方法 flatMap()
        // flatMap 方法和 map 方法类似，唯一的不同点就是 map 方法会对返回的值进行 Optional 封装，而 flatMap 不会，它需要手动执行 Optional.of 或 Optional.ofNullable 方法对 Optional 值进行封装。
        @org.junit.jupiter.api.Test
        public void flatMapTest() {
            //创建一个map 对象
            Map<String, String> userMap = new HashMap<>();
            userMap.put("name", "jiayou");
            userMap.put("sex", "nan");

            //传入Map对象参数   获取一个Optional对象
            Optional<Map<String, String>> optional = Optional.of(userMap);
            //使用Optionalde flatMap方法  获取Map中的name属性值,然后通过获取的值手动创建一个新的Optional对象
            Optional optional1 = optional.flatMap(value -> Optional.ofNullable(value.get("name")));
            System.out.println("获取的Optional 的值" + optional1.get());

            /**
             * 打印结果
             * jiayou
             */

        }
    }
}
