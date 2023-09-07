package com.ikang.idata.search.search.javaTest;

import cn.hutool.core.lang.func.VoidFunc1;
import com.alibaba.fastjson.JSON;
import com.ikang.idata.search.search.common.MyFunction;
import com.ikang.idata.search.search.common.MyFunction2;
import com.ikang.idata.search.search.entity.Employee;

import java.io.PrintStream;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestLambda {
    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );
 
    @Test
    public void test1(){
        Collections.sort(emps, (e1, e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });
        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }
    //需求，用于处理字符串
    public String strHandler(String str, MyFunction mf){
        return mf.getValue(str);
    }
 
    @Test
    public void test2(){
        String trimStr=strHandler("   我的名字叫汤姆  ", (MyFunction) (str)->str.trim());
        System.out.println("trimStr = " + trimStr);
 
        String upper=strHandler("abcdefg", (MyFunction) (str)->str.toUpperCase());
        System.out.println("upper = " + upper);
 
        String newStr=strHandler("我的名字", (MyFunction) (str)->str.substring(1,3));
        System.out.println("newStr = " + newStr);
    }
    public void op(Long l1,Long l2, MyFunction2<Long,Long> mf){
        System.out.println(mf.getValue(l1,l2));
    }
    @Test
    public void test3(){
        op(100L,200L,(x,y)->x+y);
        op(200l,300l,(x,y)->x*y);
 
    }

    //原来的匿名内部类
    @Test
    public void test4() {
        Comparator<String> com = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
        TreeSet<String> ts = new TreeSet<>(com);
        TreeSet<String> ts2 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
    }

    //现在的Lambda表达式
    @Test
    public void test5() {
        Comparator<String> com = (x, y) -> Integer.compare(x.length(), y.length());
        TreeSet<String> ts = new TreeSet<>(com);
    }
    List<Employee> emps1 = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    //需求。获取公司中年龄小于35的员工的信息
    public List<Employee> filterEmployeeAge(List<Employee> emps) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (emp.getAge() <= 35) {
                list.add(emp);
            }
        }
        return list;
    }

    @Test
    public void test6() {
        List<Employee> list = filterEmployeeAge(emps);
        for (Employee Employee : list) {
            System.out.println("Employee = " + Employee);
        }
    }

    //需求获取公司中工资大于5000的员工信息
    public List<Employee> filterEmployeeSalary(List<Employee> emps) {
        List<Employee> list = new ArrayList<>();
        for (Employee Employee : emps) {
            if (Employee.getSalary() >= 5000) {
                list.add(Employee);
            }
        }
        return list;
    }

    /**
     * predicate<T> 断言型接口
     */
    @Test
    public void test7() {
        List<String> list = Arrays.asList("Hello", "agg", "Lambda", "www", "ok");
        List<String> strList = filterStr(list, (s) -> s.length() > 3);
        strList.forEach(System.out::println);
    }

    //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = list.stream().filter(str -> pre.test(str)).collect(Collectors.toList());
        return strList;
    }


    /**
     * Function<T,R>函数型接口
     */
    @Test
    public void test8() {
        String newStr = strHandler("       你好，java8! ", (MyFunction) (str) -> str.trim());
        System.out.println("newStr = " + newStr);
        String subStr = strHandler("你好 Java8", (MyFunction) (str) -> str.substring(2, 4));
        System.out.println("subStr = " + subStr);
    }

    //需求：用于处理字符串
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }


    /**
     * supplier<T>供给型接口
     */
    @Test
    public void test9() {

        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));

        numList.forEach(System.out::println);

    }


    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(sup.get());
        }
        return list;
    }

    /**
     * 消费型接口Consumer<T>
     */
    @Test
    public void test10(){
        happy(10000,System.out::println);

    }

    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }

    //************************************************************************************************

    /**
     * 数组引用
     */
    @Test
    public void test11(){
        Function<Integer,String[]>fun= String[]::new;
        String [] strs=fun.apply(10);
        System.out.println(strs.length);
        System.out.println("------------------------");
        Function<Integer, Employee[]> fun2=Employee[]::new;
        Employee[] emps=fun2.apply(20);
        System.out.println(emps.length);
    }


    /**
     * 构造器引用
     */
    @Test
    public void test12(){
        Function<String,Employee> fun=Employee::new;
        Employee apply = fun.apply("100");
        BiFunction<String,Integer,Employee> fun2=Employee::new;
        Employee apply1 = fun2.apply("10", 20);
    }

    @Test
    public void test13(){
        Supplier<Employee> sup=()->new Employee();
        System.out.println(sup.get());
        System.out.println("-----------------");
        Supplier<Employee> sup2=Employee::new;
        System.out.println(sup2.get());
    }


    /**
     * 类名：：实例方法名
     */
    @Test
    public void test14(){
        BiPredicate<String,String> bp=(x, y)->x.equals(y);
        System.out.println(bp.test("abcde","abcde"));
        System.out.println("-----------------------------");
        BiPredicate<String,String> bp2=String::equals;
        System.out.println(bp2.test("abcd","abcd"));
        System.out.println("-----------------------------");
        Function<Employee,String> fun=e -> e.show();
        System.out.println(fun.apply(new Employee()));
        System.out.println("-----------------------------");
        Function<Employee,String> fun2=Employee::show;
        System.out.println(fun2.apply(new Employee()));
    }


    /**
     * 类名：：静态方法
     */
    @Test
    public void test15(){
        Comparator<Integer> com=(x,y)->Integer.compare(x,y);
        System.out.println("----------------------");
        Comparator<Integer> com2=Integer::compare;
    }

    @Test
    public void test16(){
        BiFunction<Double,Double,Double> fun=(x,y)->Math.max(x,y);
        System.out.println(fun.apply(1.5,1.6));
        System.out.println("----------------------");
        BiFunction<Double,Double,Double> fun2=Math::max;
        System.out.println( fun2.apply(1.3,1.5));
    }

    /**
     * 对象的引用：：实例方法名
     */
    @Test
    public void test17(){
        Employee emp=new Employee(101,"张三",18,99999.99);
        Supplier<String> sup=()->emp.getName();
        System.out.println(sup.get());
        System.out.println("------------------");
        Supplier<String> sup2=emp::getName;
        System.out.println(sup2.get());
    }

    @Test
    public void test18(){
        PrintStream ps=System.out;
        Consumer<String> con=str->ps.println(str);
        con.accept("Hello world!");
        System.out.println("-------");
        Consumer<String> con2=ps::println;
        con2.accept("Hello world!");
    }

    //1.创建Stream
    @Test
    public void test19() {
        //1.Collection提供了两个方法 stream()与parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();//获取一个顺序流
        Stream<String> parallelStream = list.parallelStream();//获取一个并行流
        //2.通过Arrays中的stream()创建一个数据流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);
        //3.通过stream类中静态方法 of()
        Stream<Integer> stream2 = Stream.of(1, 2, 3, 4, 5);
        //4.创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream3.forEach(System.out::println);
        //生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);
    }


    //    2.中间操作
    List<Employee> emps2 = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "王五", 44, 9999.99),
            new Employee(102, "赵六", 10, 9999.99),
            new Employee(102, "赵六", 28, 9999.99)
    );


    /*筛选与切片
     * filter ---接收Lambda,从流中排除元素
     * limilt ---截断流，使其元素不超过给定数量
     * skip  ---跳过n个元素，返回一个扔掉，前n个元素，若是流中元素不足n个，则返回一个空流
     * 与limit(n)互补
     *
     * distinct---筛选，通过流所生成的hashCode()和equals()去除重复
     * */
    //内部迭代:迭代操作stream Api
    @Test
    public void test20(){
        //所有中间操作不会做任何的处理
        Stream<Employee> stream=emps.stream()
                .filter((e)->{
                    System.out.println("测试中间操作");
                    return e.getAge()<=35;
                });
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test21(){
        Iterator<Employee> it=emps.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }


    @Test
    public void test22(){
        emps.stream().filter(
                e->{
                    System.out.println("短路！");
                    return e.getSalary()>=5000;
                }
        ).limit(3).forEach(System.out::println);
    }



    @Test
    public void test23(){
        emps.parallelStream().filter(
                        e->e.getSalary()>=5000)
                .skip(2).forEach(System.out::println);
    }
    @Test
    public void test24(){
        emps.stream().distinct().forEach(System.out::println);
    }


    /*
     *  1.给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     *   给定1，2，3，4，5，应该返回1，4，9，16，25
     * */
    @Test
    public void test25(){
        Integer[] nums=new Integer[]{1,2,3,4,5};
        Arrays.stream(nums).map((x)->{ return x*x ;})
                .forEach(System.out::println);

    }

    /*
     * 2.怎样用map和reduce 方法数一数流中有多少个Employee呢？
     *
     * */
    List<Employee>emps3=Arrays.asList(
            new Employee(102,"李四",59,666.66),
            new Employee(101,"张三",18,9999.99),
            new Employee(103,"王五",8,9999.99),
            new Employee(104,"赵六",81,9999.99),
            new Employee(104,"赵六",38,777.77)

    );

    @Test
    public void test26(){

        Optional<Integer> count=emps3.stream().map(e->2).reduce(Integer::sum);

        System.out.println("count.get() = " + count.get());

    }

    List<Employee> emps4 = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    //2.中间操作
    //1.映射  map    --接收Lambda,将元素转换称为其他形式或提取信息。按一个函数作为参数，该函数会被应用到每个元素上，该函数会被应用到每个元素上，并将器映射到一个新的元素上
    //       flatMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后将所有流链接成一个流
    @Test
    public void test27() {
        List<String> collect = emps4.stream().map((e) -> e.getName()).collect(Collectors.toList());
        System.out.println("collect = " + collect);
        System.out.println("--------------------------------");
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "dddd");
        Stream<String> stream = strList.stream().map(String::toUpperCase);
        stream.forEach(System.out::println);

        Stream<Stream<Character>> stream2 = strList.stream()
                .map(TestLambda::filterCharacter);

        stream2.forEach(
                stream21 -> {
                    stream21.forEach(System.out::println);
                }
        );
        System.out.println("----------------------------------");
        Stream<Character> stream3 = strList.stream().flatMap(TestLambda::filterCharacter);
        stream3.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();

    }

    /*sorted自然排序
     * sorted(Comparator com)---定制排序
     * */
    @Test
    public void test28() {
        emps4.stream().map(Employee::getName).sorted().forEach(System.out::println);
        System.out.println("----------------------------------------------------");
        emps4.stream().sorted(
                (x, y) -> {
                    if (x.getAge() == y.getAge()) {
                        return x.getName().compareTo(y.getName());
                    } else {
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }
        ).forEach(System.out::println);

    }

    List<Employee> emps5 = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.9),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    /*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
    @Test
    public void test29(){
        boolean bl=emps5.stream().allMatch((e)->e.getName().equals("赵六"));
        System.out.println(bl);

        boolean bl2=emps5.stream().anyMatch((e)->e.getName().equals("赵六"));
        System.out.println(bl2);

        boolean bl3=emps5.stream().noneMatch((e)->e.getName().equals("赵六"));
        System.out.println(bl3);

    }


    @Test
    public void test30(){
        Optional<Employee> op=emps5.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .findFirst();
        System.out.println("op.get() = " + op.get());
        System.out.println("--------------------------------");
        Optional<Employee> op2=emps5.parallelStream()
                .filter((e)->e.getName().equals("赵六"))
                .findAny();
        System.out.println("op2.get() = " + op2.get());
    }

    @Test
    public void test31() {
        long count = emps5.stream()
                .filter((e) -> e.getName().equals("赵六")).count();
        System.out.println("count = " + count);
        Optional<Double> op = emps5.stream().map(Employee::getSalary).max(Double::compare);
        System.out.println(op.get());

        Optional<Employee> op2 = emps5.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println("op2 = " + op2.get());
    }

    //注意流中：流中进行终止操作后，不能再次使用
    @Test
    public void test32(){
        Stream<Employee> stream=emps5.stream()
                .filter(e->e.getName().equals("赵六"));
        long count=stream.count();
        System.out.println("count = " + count);
    }

    //3.终止操作
    @Test
    public void test33(){
        List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum=list.stream().reduce(0,(x,y)->x+y);
        System.out.println(sum);
        System.out.println("---------------------------------");
        Optional<Double> op=emps5.stream()
                .map(Employee::getSalary).reduce(Double::sum);
        System.out.println("op.get() = " + op.get());

    }


    //需求：搜索名字中 “六” 出现的次数
    @Test
    public void test34(){
        List<Character> collect = emps5.stream().map(Employee::getName).flatMap(TestLambda::filterCharacter).collect(Collectors.toList());
        System.out.println("collect = " + collect);
        Optional<Integer> sum=emps5.stream()
                .map(Employee::getName)
                .flatMap(TestLambda::filterCharacter)
                .map(character -> {
                    if (character.toString().equals("六")){
                        return 1;
                    }else {
                        return 0;
                    }
                }).reduce(Integer::sum);
        System.out.println("sum = " + sum.get());
    }

    //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
    @Test
    public void test35(){
        List<String> list=emps5.stream()
                .map(Employee::getName).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("----------------------------------------");
        Set<String> set=emps5.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("----------------------------------------");
        HashSet<String> hs=emps5.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);
        System.out.println("----------------------------------------");
        Map<String, Integer> list1 = emps5.stream().collect(Collectors.toMap(Employee::getName,Employee::getAge));
        System.out.println("list1 = " + list1);
    }


    @Test
    public void test36() {
        Optional<Double> max = emps5.stream()
                .map(Employee::getSalary).collect(Collectors.maxBy(Double::compare));
        System.out.println(max.get());
        Optional<Employee> op = emps5.stream().collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(op.get());
        Double sum = emps5.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
        Double avg = emps5.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("avg = " + avg);
        Long count = emps5.stream().collect(Collectors.counting());
        System.out.println(count);
        System.out.println("-------------------------------------------------------------");
        DoubleSummaryStatistics dss = emps5.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getMax());
    }

    @Test
    public void test37(){
        Map<String,List<Employee>> map=emps5.stream()
                .collect(Collectors.groupingBy(Employee::getName));
        System.out.println("map = " + map);
    }

    //多级分组
    @Test
    public void test38() {
        Map<String, Map<String, List<Employee>>> map = emps5.stream().collect(Collectors.groupingBy(Employee::getName, Collectors.groupingBy(
                (e) -> {
                    if (e.getAge() >= 60) {
                        return "老年";
                    } else if (e.getAge() >= 35) {
                        return "中年";
                    } else {
                        return "成年";
                    }
                }
        )));
        System.out.println(JSON.toJSONString(map));
    }
    //分区
    @Test
    public void test39(){
        Map<Boolean,List<Employee>> map=emps5.stream().collect(Collectors.partitioningBy((e)->e.getSalary()>=5000));
        System.out.println(map);
        System.out.println("JSON.toJSONString(map) = " + JSON.toJSONString(map));

    }

    @Test
    public void test40() {
        String str = emps5.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "---", "-----"));
        System.out.println(str);
    }

    @Test
    public void test41() {
        Optional<Double> sum = emps5.stream().map(Employee::getSalary).collect(Collectors.reducing(Double::sum));
        System.out.println(sum.get());
    }

    /**
     * LocalDate 日期类(年月日)
     */
    @Test
    public void testLocalDate() {
        //获取当前日期
        LocalDate now = LocalDate.now();
        System.out.println(now);
        //指定日期 LocalDate.of(year,month,day)
        LocalDate date = LocalDate.of(2008, 8, 8);
        System.out.println(date);
        //获取年
        System.out.println("年:" + date.getYear());
        //获取月(英文)
        System.out.println("月(英文):" + date.getMonth());
        //获取月(阿拉伯数字)
        System.out.println("月(数字):" + date.getMonthValue());
        //获取日
        System.out.println("日:" + date.getDayOfMonth());
        //是否是闰年
        System.out.println("是否是闰年:" + date.isLeapYear());
        //检查此日期是否在指定日期之后
        boolean after = date.isAfter(LocalDate.now());
        System.out.println("after = " + after);
        //检查此日期是否在指定日期之前
        boolean before = date.isBefore(LocalDate.now());
        System.out.println("before = " + before);
        //检查此日期是否等于指定的日期
        boolean equal = date.isEqual(LocalDate.now());
        System.out.println("equal = " + equal);
        //使用指定的格式化程序格式化此日期
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String format = date.format(dateTimeFormatter);
        System.out.println("format = " + format);
        //将此日期与时间组合以创建LocalDateTime
        LocalDateTime localDateTime = date.atTime(12, 30);
        System.out.println("localDateTime = " + localDateTime);
    }

    /**
     * LocalDate 时间类(时分秒)
     */
    @Test
    public void testLocalTime() {
        //获取当前时间
        LocalTime now = LocalTime.now();
        System.out.println(now);
        //指定日期 LocalTime.of(hour,minute,second)
        LocalTime date = LocalTime.of(13, 26, 39);
        System.out.println(date);
        //获取时
        System.out.println(date.getHour());
        //获取分
        System.out.println(date.getMinute());
        //获取秒
        System.out.println(date.getSecond());
        //获取纳秒
        System.out.println(now.getNano());
    }

    /**
     * LocalDateTime 日期时间类(年月日 时分秒)
     */
    @Test
    public void testLocalDateTime() {
        //LocalDateTime: LocalDate + LocalTime,有年月日 时分秒
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前日期时间:"+now);
        //指定日期时间 LocalDateTime.of(year,month,day,hour,minute,second)
        LocalDateTime date = LocalDateTime.of(2018, 7, 23, 18, 59, 31);
        System.out.println(date);
        //获取年
        System.out.println(date.getYear());
        //获取月
        System.out.println(date.getMonth());
        //获取日
        System.out.println(date.getDayOfMonth());
        //获取时
        System.out.println(date.getHour());
        //获取分
        System.out.println(date.getMinute());
        //获取秒
        System.out.println(date.getSecond());
    }

    /**
     * 修改时间
     */
    @Test
    public void modifyTime() {
        //以LocalDateTime为例(LocalDate、LocalTime与此类似)
        LocalDateTime now = LocalDateTime.now();
        //修改年[修改时间(不是JDK8之前的setXXX(),而是使用withXXX())]
        System.out.println("修改年后:" + now.withYear(9102));
        //增加年(减使用 minusYear()方法)
        System.out.println("+2年后:" + now.plusYears(2));
        //增加日(减使用 minusDays()方法)
        System.out.println("47天后:" + now.plusDays(47));
    }

    /**
     * 时间比较
     */
    @Test
    public void compareTime() {
        //以LocalDateTime为例(LocalDate、LocalTime与此类似)
        //时间1
        LocalDateTime now = LocalDateTime.now();
        //时间2
        LocalDateTime dateTime = LocalDateTime.of(2018, 7, 12, 13, 28, 51);
        //判断前面日期是否在后面日期后
        System.out.println("A时间是否晚于B时间:" + now.isAfter(dateTime));
        //判断前面日期是否在后面日期前
        System.out.println("A时间是否早于B时间:" + now.isBefore(dateTime));
        //判断两个日期时间是否相等
        System.out.println("两个时间是否相等:" + now.isEqual(dateTime));
    }

    /**
     * JDK8 日期时间格式化与解析
     */
    @Test
    public void dateFormat(){
        LocalDateTime now = LocalDateTime.now();

        //格式化
        //使用JDK自带的时间格式:ISO_DATE_TIME(默认提供了很多格式,自行查看)
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        String format = now.format(dtf);
        System.out.println("format="+format);

        //指定时间格式(ofPattern()方法)
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
        String format1 = now.format(dtf1);
        System.out.println(format1);

        //解析(parse()方法)
        LocalDateTime parse = LocalDateTime.parse(format1, dtf1);
        System.out.println("parse="+parse);

        /**
         * 多线程执行(验证线程安全性)
         * 1.返回结果正确   2.不抛异常
         */
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                LocalDateTime parse1 = LocalDateTime.parse(format1, dtf1);
                System.out.println("parse="+parse1);
            }).start();
        }
    }

    @Test
    public void Instant(){
        //Instant
        // 内部保存了秒和纳秒，一般不是给用户使用的，而是方便程序做一些统计的(比如:统计方法耗时)
        Instant now = Instant.now();
        System.out.println("当前时间戳:"+now);//2020-01-13T06:48:46.267Z
        //Instant类 并没有修改年月日等操作.因为 Instant 本来就不是给用户使用的
        //Instant类:对 秒、纳秒等操作方便
        Instant plus = now.plusSeconds(20);
        System.out.println("+20秒后:"+plus);

        Instant minus = now.minusSeconds(20);
        System.out.println("-20秒后:"+minus);

        //获取秒、毫秒、纳秒
        long second = now.getEpochSecond();
        System.out.println("秒:"+second);
        int nano = now.getNano();
        System.out.println("纳秒:"+nano);
    }

    /**
     * Duration类:计算时间的差值
     */
    @Test
    public void testTimeDiff(){
        //时间1
        LocalTime now = LocalTime.now();
        //时间2
        LocalTime dateTime = LocalTime.of(8, 15, 46);
        //计算两个时间的差值
        //计算规则:让第二个参数 减去 第一个参数(位置错误可能出现负数)
        Duration duration = Duration.between(dateTime,now);
        System.out.println("相差的天数:"+duration.toDays());
        System.out.println("相差的小时数:"+duration.toHours());
        System.out.println("相差的分钟数:"+duration.toMinutes());
        //System.out.println("相差的秒数:"+duration.toSeconds());//JDK 9+ 出现(JDK8会报错误)
        System.out.println("相差的纳秒数:"+duration.toNanos());
    }

    /**
     * Period类:计算日期的差值
     */
    @Test
    public void testDateDiff(){
        //日期1
        LocalDate now = LocalDate.now();
        //日期2
        LocalDate date = LocalDate.of(1999,5,29);
        //计算两个日期的差值
        //计算规则:让第二个参数 减去 第一个参数(位置错误可能出现负数)
        Period period = Period.between(date,now);
        System.out.println("相差的年:"+period.getYears());
        System.out.println("相差的月:"+period.getMonths());
        System.out.println("相差的日:"+period.getDays());
    }

    @Test
    public void TestDemo(){
        String[] names = {"陈昌杰", "王欣", "唐雪冰", "何泽江", "史美江", "杨苏川", "袁江", "邱杰", "张迪"};

        List<String> collect = Stream.of(names).collect(Collectors.toList());

        Map<String, User> userMap = listToMap(collect);
        System.out.println("==============listToMapObject==============");
        System.out.println(userMap);
        System.out.println("\n");

        Map<Integer, String> userM = listToMap2(collect);
        System.out.println("==============listToMap==============");
        System.out.println(userM);
        System.out.println("\n");

        List<User> users = mapToList(userM);
        System.out.println("==============mapToList==============");
        System.out.println(users);
        System.out.println("\n");

        List<User> filter = filter(users, "陈昌杰");
        System.out.println("==============filter==============");
        System.out.println(filter);
        System.out.println("\n");

        List<Group> groups = new ArrayList<>();
        List<GroupByUser> pgs = new ArrayList<>();
        groups.add(new Group(0, "his"));
        groups.add(new Group(1, "wx"));
        groups.add(new Group(2, "jk"));
        Random rd = new Random();
        for (int i = 0; i < 20; i++) {
            GroupByUser pg = new GroupByUser(rd.nextInt(3), rd.nextInt(names.length));
            pgs.add(pg);
        }

        Map<Integer, List<GroupByUser>> collect1 = pgs.stream().collect(Collectors.groupingBy(GroupByUser::getuId));
        System.out.println("==============根据用户id分组==============");
        System.out.println(collect1);
        System.out.println("\n");

        Map<Integer, Long> collect2 = pgs.stream().collect(Collectors.groupingBy(GroupByUser::getuId, Collectors.counting()));
        System.out.println("==============根据用户id分组 统计个数==============");
        System.out.println(collect2);
        System.out.println("\n");

        Map<Integer, Long> collect3 = pgs.stream().collect(Collectors.groupingBy(GroupByUser::getuId, Collectors.summingLong(GroupByUser::getgId)));
        System.out.println("==============根据用户id分组 累加==============");
        System.out.println(collect3);
        System.out.println("\n");

        List<User> users1 = users.stream().map(user -> {
            List<GroupByUser> groupByUsers = collect1.get(user.id);
            for (GroupByUser groupByUser : groupByUsers) {
                for (Group group : groups) {
                    if (groupByUser.getgId() == group.id) {
                        user.groups.add(group);
                    }
                }
            }
            return user;
        }).collect(Collectors.toList());
        System.out.println("==============获取user 的分组1==============");
        System.out.println(users1);
        System.out.println("\n");
    }

    private static Map<String, User> listToMap(List<String> collect) {
        AtomicInteger i = new AtomicInteger();
        //原理演示
        Map<String, User> map = collect.stream().collect(Collectors.toMap(value -> {
            //System.out.println(value); 这里的value其实就是 name
            return value;
        }, value -> {
            //System.out.println(value); 这里的value其实就是 name
            return new User(i.getAndIncrement(), value);
        }));
        return map;
    }

    private static Map<Integer, String> listToMap2(List<String> collect) {
        AtomicInteger i = new AtomicInteger();
        Map<Integer, String> map2 = collect.stream().collect(Collectors.toMap(value -> i.getAndIncrement(), value -> value));
        return map2;
    }


    public static List<User> filter(List<User> users, String key) {
        List<User> collect = users.stream().filter(s -> s.name.equals(key)).collect(Collectors.toList());
        return collect;
    }

    public static List<User> mapToList(Map<Integer, String> map) {
        List<User> collect = map.keySet().stream().map(key -> new User(key, map.get(key))).collect(Collectors.toList());
        return collect;
    }

    private String createStream() {
        List<String> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        String[] arr = {};

        Stream<String> stream = list.stream();
        stream = map.keySet().stream();
        Stream<Object> stream1 = map.values().stream();
        Stream<Map.Entry<String, Object>> stream2 = map.entrySet().stream();
        Stream<String> arr1 = Stream.of(arr);
        Stream<String> arr2 = Stream.of("a", "b", "c", "d");

        return "";
    }


    static class User {
        int id;
        String name;
        List<Group> groups = new ArrayList<>();

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", groups=" + groups +
                    '}';
        }
    }

    static class Group {
        int id;
        String name;

        public Group(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Group{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class GroupByUser {
        int gId;
        int uId;

        public GroupByUser(int gId, int uId) {
            this.gId = gId;
            this.uId = uId;
        }

        @Override
        public String toString() {
            return "GroupByUser{" +
                    "gId=" + gId +
                    ", uId=" + uId +
                    '}';
        }

        public int getgId() {
            return gId;
        }

        public void setgId(int gId) {
            this.gId = gId;
        }

        public int getuId() {
            return uId;
        }

        public void setuId(int uId) {
            this.uId = uId;
        }
    }

    /**
     * TemporalAdjuster类:自定义调整时间
     */
    @Test
    public void timeCorrector(){
        //将日期调整到"下一个月的第一天"操作
        LocalDateTime now = LocalDateTime.now();
        //参数:TemporalAdjuster adjuster。TemporalAdjuster是一个接口,里面只有 Temporal adjustInto(Temporal temporal); 这一个方法,支持接入 lambda 表达式
        //此处 Temporal 就是指时间(包括 LocalDate、LocalTime、LocalDateTime 都是继承自该类。)
        TemporalAdjuster adjuster = ( Temporal temporal)->{
            LocalDateTime dateTime = (LocalDateTime)temporal;
            return dateTime.plusMonths(1).withDayOfMonth(1);//下一个月第一天
        };
        LocalDateTime newDateTime = now.with(adjuster);
        System.out.println("下个月第一天:"+newDateTime);
    }

    /**
     * TemporalAdjusters工具类:使用JDK提供的时间调整器
     */
    @Test
    public void JDKTimeCorrector(){
        //JDK中自带了很多时间调整器,其他调整器请自行查看
        //使用 TemporalAdjusters 工具类
        //TemporalAdjusters.firstDayOfNextYear()--->根据内容可知:下一年第一天
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.firstDayOfNextYear();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newDateTime = now.with(temporalAdjuster);
        System.out.println("下一年第一天:"+newDateTime);
    }

    /**
     * 获取时区ID
     */
    @Test
    public void getZoneIds(){
        //1.获取所有的时区ID
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        zoneIds.forEach(System.out::println);//返回600来个时区
    }

    /**
     * 不带时区 Vs 带时区的日期时间
     */
    @Test
    public void ZonedDemo(){
        //2.操作带时区的类
        //不带时间,获取计算机的当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now:"+now);

        //中国使用的是东八区的时间，比标准时间早8个小时
        //操作带时间的类
        ZonedDateTime zdt = ZonedDateTime.now(Clock.systemUTC());//创建出来的时间是世界标准时间
        System.out.println("世界标准时间:"+zdt);
    }

    /**
     * 本地时间
     */
    @Test
    public void localTime(){
        //now():使用计算机的默认的时区,创建日期时间
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("本地时间:"+now);//本地时间:2020-01-13T15:52:43.633+08:00[Asia/Shanghai]
    }

    /**
     * 使用指定的时区来创建时间
     */
    @Test
    public void ZoneTime(){

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("设置指定时间:"+now);//设置指定时间:2020-01-13T02:56:24.776-05:00[America/New_York]
    }

    /**
     * 修改时区
     */
    @Test
    public void modifyZone(){
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/New_York"));

        //withZoneSameInstant():既更改时区,也更改时间
        ZonedDateTime modifyTime = now.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
        System.out.println("修改时区后的时间:"+modifyTime);

        //withZoneSameLocal():只更改时区,不更改时间
        ZonedDateTime modifyTime2 = now.withZoneSameLocal(ZoneId.of("Asia/Shanghai"));
        System.out.println("修改时区后的时间:"+modifyTime2);
    }
}