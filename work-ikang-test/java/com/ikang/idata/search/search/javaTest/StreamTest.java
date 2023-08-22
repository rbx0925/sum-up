package com.ikang.idata.search.search.javaTest;

import com.ikang.idata.search.search.aop.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/9/26
 */

@Slf4j
public class StreamTest {

    //中间操作(往往对数据进行筛选)
    ///filter：过滤流中的某些元素,
    //
    //sorted(): 自然排序，流中元素需实现 Comparable 接口
    //
    //distinct: 去除重复元素
    //
    //limit(n): 获取 n 个元素
    //
    //skip(n): 跳过 n 元素，配合 limit(n)可实现分页
    //
    //map(): 将其映射成一个新的元素

    //终端操作(往往对结果集进行处理)
    //forEach: 遍历流中的元素
    //
    //toArray:将流中的元素倒入一个数组
    //
    //Min:返回流中元素最小值   Max:返回流中元素最大值
    //
    //count:返回流中元素的总个数
    //
    //Reduce:所有元素求和
    //
    //anyMatch:接收一个 Predicate 函数，只要流中有一个元素满足条件则返回 true，否则返回
    //
    //falseallMatch:接收一个 Predicate 函数，当流中每个元素都符合条件时才返回 true，否则返回 false
    //
    //findFirst：返回流中第一个元素
    //
    //collect:将流中的元素倒入一个集合,Collection 或 Map


    @Test
    public void arr() {
        Integer[] arr = new Integer[]{1, 4, 3, 2, 5, 5};
        Arrays.stream(arr)    //拿到流
                .filter((a) -> {
                    return a > 3;
                })  //中间操作,过滤
                .forEach((a) -> {   //终端操作,遍历
                    System.out.println(a);
                });

    }

    @Test
    public void arr1() {

        Integer[] arr = new Integer[]{1, 4, 3, 2, 5, 5};
        Object[] objects = Arrays.stream(arr)
                .sorted().distinct() //排序并去重
                .toArray();  //转数组
        System.out.println(Arrays.toString(objects));


    }

    @Test
    public void arr2() {

        Integer[] arr = new Integer[]{1, 4, 3, 2, 5, 5};
        Integer max = Arrays.stream(arr).distinct()
                .max(((o1, o2) -> {    //去重返回最大值
                    return o1 - o2;
                })).get();  //拿到这个值
        //此处max为终端操作,返回的已经不是流,而是一个OPtion的对象
        //它里面有一个get()方法可以返回这个值
        System.out.println(max);

    }

    @Test
    public void arr3() {

        Integer[] arr = new Integer[]{1, 4, 3, 2, 5, 5};
        long count = Arrays.stream(arr).distinct()
                .count();   //返回总个数
        System.out.println(count);


    }

    @Test
    public void arr4() {


        Integer[] arr = new Integer[]{1, 4, 3, 2, 5, 5};
        Integer i = Arrays.stream(arr).distinct()
                .reduce((o1, o2) -> {  //所有元素求和
                    return o1 + o2;
                })
                .get();
        System.out.println(i);
    }


    @Test
    public void arr5() {

        List<Apple> list = new ArrayList<>();
        list.add(new Apple(100, "苹果1", "红色"));
        list.add(new Apple(105, "苹果5", "红色"));
        list.add(new Apple(104, "苹果4", "红色"));
        list.add(new Apple(103, "苹果3", "红色"));
        list.add(new Apple(102, "苹果2", "红色"));

        List<String> collect = list.stream()
                //将属性的一列通过get方法映射成流
                .map(Apple::getName)
                //转为一个list集合
                .collect(Collectors.toList());
        System.out.println(collect);


        List<Apple> collect1 = list.stream().sorted(((o1, o2) -> {
            //通过num属性自定义排序
            return o1.getNum() - o2.getNum();
        }))
                //转为一个list集合
                .collect(Collectors.toList());
        System.out.println(collect1);


        Map<Integer, String> map = list.stream().sorted(((o1, o2) -> {
            return o1.getNum() - o2.getNum();
        }))
                //转map, 第一个参数为键,第二个参数为值
                .collect(Collectors.toMap(Apple::getNum, Apple::getName));
        System.out.println(map);


    }


    @Test
    public void test01() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };
        r.run();
    }


    @Test
    public void test02() {
        Runnable r1 = () -> {
            System.out.println("Hello Lambda");
        };
        r1.run();
    }

    private Integer id;
    private String name;
    private Integer age;
    private Double salary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public static Logger getLog() {
        return log;
    }

    public StreamTest() {
    }

    public StreamTest(Integer id, String name, Integer age, Double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Test
    public void arr6() {
        List<Employee> emps = Arrays.asList(
                new Employee(101, "Z3", 19, 9999.99),
                new Employee(102, "L4", 20, 7777.77),
                new Employee(103, "W5", 35, 6666.66),
                new Employee(104, "Tom", 44, 1111.11),
                new Employee(105, "Jerry", 60, 4444.44)
        );


        Collections.sort(emps, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    //消费型接口
    @Test
    public void arr7() {
        //Consumer
        Consumer<Integer> consumer = (x) -> System.out.println("消费型接口" + x);
        //test
        consumer.accept(100);

    }

    //提供型接口
    @Test
    public void arr8() {
//        List<Integer> list = new ArrayList<>();
//        List<Integer> integers = Arrays.asList(1,2,3);
//        list.addAll(integers);
//        //Supplier<T>
//        Supplier<Integer> supplier = () -> (int)(Math.random() * 10);
//        list.add(supplier.get());
//        System.out.println(supplier);
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }

    }


    //函数型接口
    @Test
    public void test03() {
        //Function<T, R>
        String oldStr = "abc123456xyz";
        Function<String, String> function = (s) -> s.substring(1, s.length() - 1);
        //test
        System.out.println(function.apply(oldStr));
    }

    // 断言型接口

    @Test
    public void test04() {
//        //Predicate<T>
//        Integer age = 35;
//        Predicate<Integer> predicate = (i) -> i >= 35;
//        if (predicate.test(age)){
//            System.out.println("你该退休了");
//        } else {
//            System.out.println("我觉得还OK啦");
//        }
    }

    @Test
    public void test05() {

        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(list);

        // Lambda表达式

        list.sort((o1, o2) -> {
            return o2.compareTo(o1);
        });
    }

    @Test
    public void test() {
        // 1. 通过Collection系列集合的 stream() 方法或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2. 通过 Arrays 类中的静态方法 stream() 获取数组流
        String[] strArr = new String[10];
        Stream<String> stream1 = Arrays.stream(strArr);

        // 3. 通过 Stream 类中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        // 4. 创建无限流，要有终止操作才有效果
        // （1）迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.forEach(System.out::println); // 不停打印，停不下来
        stream3.limit(10) // 中间操作
                .forEach(System.out::println); // 终止操作
        // （2）生成
        Stream.generate(() -> new Random().nextInt(32))
                .limit(32)
                .forEach(System.out::println);

    }


    @Test
    public void test06() {

        List<Employee> emps = Arrays.asList(
                new Employee(101, "Z3", 19, 9999.99),
                new Employee(102, "L4", 20, 7777.77),
                new Employee(103, "W5", 35, 6666.66),
                new Employee(104, "Tom", 44, 1111.11),
                new Employee(105, "Jerry", 60, 4444.44)
        );

        emps.stream()
                .filter((x) -> x.getAge() > 35)
                .limit(3) //短路？达到满足不再内部迭代
                .distinct()
                .skip(1)
                .forEach(System.out::println);
    }


    @Test
    public void test07() {
        List<String> list = Arrays.asList("a", "b", "c");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

    }

//    public Stream<Character> filterCharacter(String str){
//        List<Character> list = new ArrayList<>();
//        for (char c : str.toCharArray()) {
//            list.add(c);
//        }
//
//        return list.stream();
//    }
//
//    @Test
//    public void test03(){
//        List<String> list = Arrays.asList("a", "b", "c");
//        Test02 test02 = new Test02();
//        list.stream()
//                .flatMap(test02::filterCharacter)
//                .forEach(System.out::println);
//    }


    public class test {
        @Test
        public void test() {
            //自然排序
            List<String> list = Arrays.asList("aaa", "eee", "ddd", "bbb");
            list.stream().sorted().forEach(System.out::println);

            //定制排序
            List<Employee> emps = Arrays.asList(
                    new Employee(101, "Z3", 19, 9999.99),
                    new Employee(102, "L4", 20, 7777.77),
                    new Employee(103, "W5", 35, 6666.66),
                    new Employee(104, "Tom", 44, 1111.11),
                    new Employee(105, "Jerry", 60, 4444.44)
            );
//            emps.stream().sorted((p1,p2) -> {
//                if (p1.getAge().equals(p2.getAge())){
//                    return p1.getSalary().compareTo(p2.getSalary());
//                }else {
//                    return p1.getAge().compareTo(p2.getAge());
//                }
//            }).forEach(System.out::println);
        }


        @Test
        public void test08() {
//            List<Person> list = Arrays.asList(
//                    new Person("张三", 18, 2000.0, Person.Status.BUSY),
//                    new Person("李四", 18, 5000.0,Person.Status.FREE),
//                    new Person("王五", 45, 8700.0,Person.Status.VOCATION),
//                    new Person("赵六", 42, 4200.0,Person.Status.BUSY),
//                    new Person("陈七", 56, 13100.0,Person.Status.BUSY)
//            );
//            //allMatch 检查是否匹配所有元素，返回值为Boolean类型
//            boolean b = list.stream().allMatch(e -> e.getStatus().equals(Person.Status.BUSY));
//            System.out.println(b);  // false
//
//            //anyMatch 检查是否匹配至少一个元素，返回值为Boolean类型
//            boolean b1 = list.stream().anyMatch(e -> e.getStatus().equals(Person.Status.BUSY));
//            System.out.println(b1); // true
//
//            //noneMatch 检查是否没有匹配所有元素，返回值为Boolean类型
//            boolean b2 = list.stream().noneMatch(e -> e.getStatus().equals(Person.Status.BUSY));
//            System.out.println(b2); // false
//
//            //findFirst 返回第一个元素
//            //Optional 防止空指针异常的类型，如果first为null，可以使用.orelse()方法指定一个不为空的对象
//            Optional<Person> op1 = list.stream()
//                    .sorted((e1, e2) -> Double.compare(e1.getSale(), e2.getSale())).findFirst();
//            System.out.println(op1.get());	// Person(name=张三, age=18, sale=2000.0, status=BUSY)
//
//            //findAny 返回当前流中的任意元素
//            //parallelStream 并行流，多个进程同时去进行filter、findAny，谁先找到算谁的
//            Optional<Person> op2 = list.parallelStream().filter(e -> e.getStatus().equals(Person.Status.FREE)).findAny();
//            System.out.println(op2.get());	// Person(name=李四, age=18, sale=5000.0, status=FREE)
//
//            //count 返回流中元素的总个数
//            long count = list.stream().count();
//            System.out.println(count);	// 5
//
//            //max 返回流中的最大值
//            Optional<Person> max = list.stream().max((e1, e2) -> Double.compare(e1.getSale(), e2.getSale()));
//            System.out.println(max.get());	// Person(name=陈七, age=56, sale=13100.0, status=BUSY)
//
//
//            //min 返回流中的最小值
//            //返回list中的最小工资数
//            System.out.println(list.stream().map(Person::getSale).min(Double::compare).get());	// 2000.0
            // 求和
            List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

            // Integer::sum  ----> (x, y) -> x+y
            // 一开始把 0 当作 x，然后从集合中取出一个元素当作 y，求和为 1
            // 然后把 1 再当作 x，再从集合中取出一个元素当作 y，求和为 3
            // ...
            Integer sum = list.stream()
                    // 有0作为初始值，不可能为空
                    .reduce(0, Integer::sum);
            System.out.println(sum);


            Optional<Integer> sum1 = list.stream()
                    // 没有初始值，可能为空，所以返回 Optional 对象
                    .reduce(Integer::sum);
            System.out.println(sum1.get());
        }
    }


    @Test
    public void test09() {
        List<Employee> emps = Arrays.asList(
                new Employee(1, "张三", 10, 9999.99),
                new Employee(2, "李四", 40, 7777.77),
                new Employee(3, "王五", 30, 6666.66),
                new Employee(4, "赵六", 28, 1111.11),
                new Employee(5, "老王", 37, 4444.44)
        );

        //放入List
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);

        //放入Set
        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        //放入LinkedHashSet
        LinkedHashSet<String> linkedHashSet = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        linkedHashSet.forEach(System.out::println);
    }


    @Test
    public void test10() {

        List<Employee> emps = Arrays.asList(
                new Employee(1, "张三", 10, 9999.99),
                new Employee(2, "李四", 40, 7777.77),
                new Employee(3, "王五", 30, 6666.66),
                new Employee(4, "赵六", 28, 1111.11),
                new Employee(5, "老王", 37, 4444.44)
        );
        //总数
        Long count = emps.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        //平均值
        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);

        //总和
        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        //最大值
        Optional<Employee> max = emps.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());

        //最小值
        Optional<Double> min = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
    }

    @Test
    public void test11() {
        List<Employee> emps = Arrays.asList(
                new Employee(1, "张三", 10, 9999.99),
                new Employee(2, "李四", 40, 7777.77),
                new Employee(3, "王五", 30, 6666.66),
                new Employee(4, "赵六", 28, 1111.11),
                new Employee(5, "老王", 37, 4444.44)
        );
        //分组
        Map<Integer, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getId));
        System.out.println(map);

        //多级分组
        Map<Integer, Map<String, List<Employee>>> mapMap = emps.stream()
                .collect(Collectors.groupingBy(Employee::getId, Collectors.groupingBy((e) -> {
                    if (e.getAge() > 35) {
                        return "开除";
                    } else {
                        return "继续加班";
                    }
                })));
        System.out.println(mapMap);

        //分区
        Map<Boolean, List<Employee>> listMap = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 4321));
        System.out.println(listMap);
    }

    @Test
    public void test12() {
        List<Employee> emps = Arrays.asList(
                new Employee(1, "张三", 10, 9999.99),
                new Employee(2, "李四", 40, 7777.77),
                new Employee(3, "王五", 30, 6666.66),
                new Employee(4, "赵六", 28, 1111.11),
                new Employee(5, "老王", 37, 4444.44)
        );
        //总结
        DoubleSummaryStatistics dss = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getSum());
        System.out.println(dss.getCount());
        System.out.println(dss.getAverage());

        //连接
        String str = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining("-")); //可传入分隔符
        System.out.println(str);
    }
}




