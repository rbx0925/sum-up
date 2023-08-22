package com.ikang.idata.search.search;

import com.ikang.idata.search.search.entity.*;
import com.ikang.idata.search.search.entity.Student;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.jupiter.api.Test;
import org.springframework.transaction.TransactionStatus;

public class StreamDemo {

    public static void main(String[] args) {

        List<Author> authors = getAuthors();
//        authors.stream()
//                .map(Author::getName)
//                .map(StringBuilder::new)
//                .map(sb->sb.append("-rbx").toString())
//                .forEach(System.out::println);

//        test30();
//        test29_2();
//        test29();
//        test28();
//        test27();
//        testNegate();
//        testOr();
//        testAnd();
//        test26();
//        test25();
//        test24();
//        test23();
//        test22();
//        test21();
//        test20();
//        test19();
//        test18();
//        test17();
//        test16();
//        test15();
//        test14();
//        test13();
//        test12();
//        test11();
//        test10();
//        test09();
//        test08();
//        test07();
//        test06();
//        test05();


//        test04();

//        test03();
//        test02();

//        test01(authors);
    }

    private static void test30() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> collect = stream.filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer.compareTo(Integer.valueOf(5)) > 0;
            }
        }).collect(Collectors.toList());
        System.out.println("collect = " + collect);


        /**
         * Consumer总结：
         *
         * Consumer接口是一个消费型的接口，只要实现它的accept方法，就能作为消费者来输出信息。
         * lambda、方法引用都可以是一个Consumer类型，因此他们可以作为forEach的参数，用来协助Stream输出信息。
         * Consumer还有很多变种，例如IntConsumer、DoubleConsumer与LongConsumer等，归根结底，这些变种其实只是指定了Consumer中的泛型而已，方法上并无变化。
         */
        Consumer<Integer> consumer=new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };

        /**
         * Supplier总结：
         *
         * Supplier是一个供给型的接口，其中的get方法用于返回一个值。
         * Supplier也有很多的变种，例如IntSupplier、LongSupplier与BooleanSupplier等
         */
        Optional<Double> optional=Optional.empty();
        Supplier<Double> supplier=()->new Random().nextDouble();
        optional.orElseGet(supplier);

        /**
         * Predicate总结：
         *
         * Predicate是一个判断型的接口，用一个test方法去测试传入的参数。
         * 当然，Predicate也有对应的变种。
         */
        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8);
        list.stream().filter(i->i>5).forEach(System.out::print);

        /**
         * Function总结：
         *
         * Function是一个转换型的接口，其中的apply可以将一种类型的数据转化成另外一种类型的数据。
         * Function的变种就更多了。
         */
        stream.map(Object::toString);

    }

    private static void test29() {
        /**
         * 一 . 收集 Stream 流中的数据到集合中
         * //1.收集数据到list集合中
         * stream.collect(Collectors.toList())
         * //2.收集数据到set集合中
         * stream.collect(Collectors.toSet())
         * //3.收集数据到指定的集合中
         * Collectors.toCollection(Supplier<C> collectionFactory)
         * stream.collect(Collectors.joining())
         */
        //Stream 流
        Stream<String> stream = Stream.of("aaa", "bbb", "ccc", "bbb");
        //收集流中的数据到集合中
        //1.收集流中的数据到 list
        List<String> list = stream.collect(Collectors.toList());
        System.out.println(list);

        //2.收集流中的数据到 set
        Set<String> collect = stream.collect(Collectors.toSet());
        System.out.println(collect);

        //3.收集流中的数据(ArrayList)(不收集到list,set等集合中,而是)收集到指定的集合中
        ArrayList<String> arrayList = stream.collect(Collectors.toCollection(ArrayList::new));
        System.out.println(arrayList);

        //4.收集流中的数据到 HashSet
        HashSet<String> hashSet = stream.collect(Collectors.toCollection(HashSet::new));
        System.out.println(hashSet);

        /**
         *  2.收集 Stream 流中的数据到数组中
         *
         *  //2.1 使用无参,收集到数组,返回值为 Object[](Object类型将不好操作)
         * Object[] toArray();
         * //2.2 使用有参,可以指定将数据收集到指定类型数组,方便后续对数组的操作
         * <A> A[] toArray(IntFunction<A[]> generator);
         */
        //Stream 流
        Stream<String> stream2 = Stream.of("aaa", "bbb", "ccc", "bbb");

        //2.1 使用 toArray()无参
        Object[] objects = stream2.toArray();
        for (Object o: objects) {//此处无法使用.length() 等方法
            System.out.println("data:"+o);
        }

        //2.2 使用有参返回指定类型数组
        //无参不好的一点就是返回的是 Object[] 类型,操作比较麻烦.想要拿到长度，Object是拿不到长度的
        String[] strings = stream2.toArray(String[]::new);
        for(String str : strings){
            System.out.println("data:"+str + ",length:"+str.length());
        }

    }

    /**
     * Stream流中数据聚合/分组/分区/拼接操作
     * 除了 collect() 方法将数据收集到集合/数组中。对 Stream流 的收集还有其他的方法。比如说：聚合计算，分组，多级分组，分区，拼接等。
     */
    private static void test29_2() {
        /**
         * 1.聚合操作
         * //最大值
         * Collectors.maxBy();
         * //最小值
         * Collectors.minBy();
         * //总和
         * Collectors.summingInt();/Collectors.summingDouble();/Collectors.summingLong();
         * //平均值
         * Collectors.averagingInt();/Collectors.averagingDouble();/Collectors.averagingLong();
         * //总个数
         * Collectors.counting();
         */
        Stream<Student> studentStream = Stream.of(
                new Student("赵丽颖", 58, 95),
                new Student("杨颖", 56, 88),
                new Student("迪丽热巴", 56, 99),
                new Student("柳岩", 52, 77)
        );

        //聚合操作
        //获取最大值(Stream流 max()方法亦可)
        //max()方法实现
        //(聚合)实现
        Optional<Student> max = studentStream.collect(Collectors.maxBy((s1, s2) -> s1.getScore() - s2.getScore()));
        System.out.println("最大值:"+max.get());

        //获取最小值(Stream流 min()方法亦可)
        //min()方法实现
        //(聚合)实现
        Optional<Student> min = studentStream.collect(Collectors.minBy((s1, s2) -> s1.getScore() - s2.getScore()));
        System.out.println("最小值:"+min.get());

        //求总和(使用Stream流的map()和reduce()方法亦可求和)
        //map()和reduce()方法实现
        //Integer reduce = studentStream.map(s -> s.getAge()).reduce(0, Integer::sum);
        //(聚合)简化前
        //Integer ageSum = studentStream.collect(Collectors.summingInt(s->s.getAge()));
        //(聚合)使用方法引用简化
        Integer ageSum = studentStream.collect(Collectors.summingInt(Student::getAge));
        System.out.println("年龄总和:"+ageSum);

        //求平均值
        //(聚合)简化前
        //Double avgScore = studentStream.collect(Collectors.averagingInt(s->s.getScore()));
        //(聚合)使用方法引用简化
        Double avgScore = studentStream.collect(Collectors.averagingInt(Student::getScore));
        System.out.println("分数平均值:"+avgScore);

        //统计数量(Stream流 count()方法亦可)
        //count()方法实现
        //long count = studentStream.count();
        //(聚合)统计数量
        Long count = studentStream.collect(Collectors.counting());
        System.out.println("数量为:"+count);
    }

    private static void test29_3(){
        /**
         * 分组操作
         *        当我们使用 Stream 流处理数据后，可以根据某个属性来将数据进行分组。
         * 接收一个 Function 参数
         * groupingBy(Function<? super T, ? extends K> classifier)
         */
        Stream<Student> studentStream = Stream.of(
                new Student("赵丽颖", 52, 56),
                new Student("杨颖", 56, 88),
                new Student("迪丽热巴", 56, 99),
                new Student("柳岩", 52, 53)
        );

        //1.按照具体年龄分组
        Map<Integer, List<Student>> map = studentStream.collect(Collectors.groupingBy(Student::getAge));
        map.forEach((key,value)->{
            System.out.println(key + "---->"+value);
        });
        //        52---->[Student{name='赵丽颖', age=52, score=56}, Student{name='柳岩', age=52, score=53}]
        //        56---->[Student{name='杨颖', age=56, score=88}, Student{name='迪丽热巴', age=56, score=99}]

        //2.按照分数>=60 分为"及格"一组  <60 分为"不及格"一组
        Map<String, List<Student>> map2 = studentStream.collect(Collectors.groupingBy(s -> {
            if (s.getScore() >= 60) {
                return "及格";
            } else {
                return "不及格";
            }
        }));
        map2.forEach((key,value)->{
            System.out.println(key + "---->"+value);
        });
        //        不及格---->[Student{name='赵丽颖', age=52, score=56}, Student{name='柳岩', age=52, score=53}]
        //        及格---->[Student{name='杨颖', age=56, score=88}, Student{name='迪丽热巴', age=56, score=99}]

        //3.按照年龄分组,规约求每组的最大值最小值(规约：reducing)
        Map<Integer, Optional<Student>> reducingMap = studentStream.collect(
                Collectors.groupingBy(Student::getAge,
                        Collectors.reducing(
                                BinaryOperator.maxBy(Comparator.comparing(Student::getScore))
                        )
                )
        );
        reducingMap .forEach((key,value)->{
            System.out.println(key + "---->"+value);
        });
        //        52---->Student{name='赵丽颖', age=52, score=95}
        //        56---->Student{name='杨颖', age=56, score=88}

        //4.多级分组操作
        //1.先根据年龄分组,然后再根据成绩分组
        //分析:第一个Collectors.groupingBy() 使用的是(年龄+成绩)两个维度分组,所以使用两个参数 groupingBy()方法
        //    第二个Collectors.groupingBy() 就是用成绩分组,使用一个参数 groupingBy() 方法
        Map<Integer, Map<Integer, Map<String, List<Student>>>> map4 = studentStream.collect(Collectors.groupingBy(str -> str.getAge(), Collectors.groupingBy(str -> str.getScore(), Collectors.groupingBy((student) -> {
            if (student.getScore() >= 60) {
                return "及格";
            } else {
                return "不及格";
            }
        }))));

        map4.forEach((key,value)->{
            System.out.println("年龄:" + key);
            value.forEach((k2,v2)->{
                System.out.println("\t" + v2);
            });
        });
        //        年龄:52
        //            {不及格=[Student{name='柳岩', age=52, score=33}]}
        //            {及格=[Student{name='赵丽颖', age=52, score=95}]}
        //        年龄:56
        //            {不及格=[Student{name='迪丽热巴', age=56, score=55}]}
        //            {及格=[Student{name='杨颖', age=56, score=88}]}

        /**
         * 分区操作
         *        我们在前面学习了 Stream流中数据的分组操作，我们可以根据属性完成对数据的分组。接下来我们介绍分区操作，我们通过使用 Collectors.partitioningBy() ，根据返回值是否为 true，把集合分为两个列表，一个 true 列表，一个 false 列表。
         *        分组和分区的区别就在：分组可以有多个组。分区只会有两个区( true 和 false)
         */
        //分区操作
        Map<Boolean, List<Student>> partitionMap = studentStream.collect(Collectors.partitioningBy(s -> s.getScore() > 60));
        partitionMap.forEach((key,value)-> {
            System.out.println(key + "---->" + value);
        });
        //false---->[Student{name='迪丽热巴', age=56, score=55}, Student{name='柳岩', age=52, score=33}]
        //true---->[Student{name='赵丽颖', age=52, score=95}, Student{name='杨颖', age=56, score=88}]

        /**
         * 拼接操作
         *        Collectors.joining() 会根据指定的连接符，将所有元素连接成一个字符串。
         * //无参数--等价于 joining("");
         * joining()
         * //一个参数
         * joining(CharSequence delimiter)
         * //三个参数(前缀+后缀)
         * joining(CharSequence delimiter, CharSequence prefix,CharSequence suffix)
         */
        //拼接操作
        //无参:join()
        String joinStr1 = studentStream.map(s -> s.getName()).collect(Collectors.joining());
        System.out.println(joinStr1);
        //一个参数:joining(CharSequence delimiter)
        String joinStr2 = studentStream.map(s -> s.getName()).collect(Collectors.joining(","));
        System.out.println(joinStr2);
        //三个参数:joining(CharSequence delimiter, CharSequence prefix,CharSequence suffix)
        String joinStr3 = studentStream.map(s -> s.getName()).collect(Collectors.joining("—","^_^",">_<"));
        System.out.println(joinStr3);

        //赵丽颖杨颖迪丽热巴柳岩
        //赵丽颖,杨颖,迪丽热巴,柳岩
        //^_^赵丽颖—杨颖—迪丽热巴—柳岩>_<
    }

    private static void test28() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = stream.parallel()
                .peek(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer num) {
                        System.out.println(num+Thread.currentThread().getName());
                    }
                })
                .filter(num -> num > 5)
                .reduce((result, ele) -> result + ele)
                .get();
        System.out.println(sum);
    }

    private static void test27() {

        List<Author> authors = getAuthors();
        authors.parallelStream()
                .map(author -> author.getAge())
                .map(age -> age + 10)
                .filter(age->age>18)
                .map(age->age+2)
                .forEach(System.out::println);

        authors.stream()
                .mapToInt(author -> author.getAge())
                .map(age -> age + 10)
                .filter(age->age>18)
                .map(age->age+2)
                .forEach(System.out::println);
    }

    private static void testNegate() {
//        打印作家中年龄不大于17的作家。
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge()>17;
                    }
                }.negate()).forEach(author -> System.out.println(author.getAge()));

    }

    private static void testOr() {
//        打印作家中年龄大于17或者姓名的长度小于2的作家。
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge()>17;
                    }
                }.or(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getName().length()<2;
                    }
                })).forEach(author -> System.out.println(author.getName()));

    }

    private static void testAnd() {
//        打印作家中年龄大于17并且姓名的长度大于1的作家。
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(author -> author.getAge()>17&&author.getName().length()>1).
                forEach(author -> System.out.println(author.getAge()+":::"+author.getName()));
//        authors.stream()
//                .filter(new Predicate<Author>() {
//                    @Override
//                    public boolean test(Author author) {
//                        return author.getAge()>17;
//                    }
//                }.and(new Predicate<Author>() {
//                    @Override
//                    public boolean test(Author author) {
//                        return author.getName().length()>1;
//                    }
//                })).forEach(author -> System.out.println(author.getAge()+":::"+author.getName()));

    }

    private static void test26() {

        //        使用reduce求所有作者中年龄的最小值
        List<Author> authors = getAuthors();
        Optional<Integer> minOptional = authors.stream()
                .map(author -> author.getAge())
                .reduce((result, element) -> result > element ? element : result);
        minOptional.ifPresent(System.out::println);
    }

    private static void test25() {
//        使用reduce求所有作者中年龄的最小值
        List<Author> authors = getAuthors();
        Integer min = authors.stream()
                .map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, (result, element) -> result > element ? element : result);
        System.out.println(min);
    }


    private static void test24() {
//        使用reduce求所有作者中年龄的最大值
        List<Author> authors = getAuthors();
        Integer max = authors.stream()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, (result, element) -> result < element ? element : result);

        System.out.println(max);
    }

    private static void test23() {
//        使用reduce求所有作者年龄的和
        List<Author> authors = getAuthors();
        Integer sum = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, (result, element) -> result + element);
        System.out.println(sum);
    }


    private static void test22() {
//        获取一个年龄最小的作家，并输出他的姓名。
        List<Author> authors = getAuthors();
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();

        first.ifPresent(author -> System.out.println(author.getName()));

    }

    private static void test21() {
//        获取任意一个年龄大于18的作家，如果存在就输出他的名字
        List<Author> authors = getAuthors();
        Optional<Author> optionalAuthor = authors.stream()
                .filter(author -> author.getAge()>18)
                .findAny();

        optionalAuthor.ifPresent(author -> System.out.println(author.getName()));
    }

    private static void test20() {
//        判断作家是否都没有超过100岁的。
        List<Author> authors = getAuthors();

        boolean b = authors.stream()
                .noneMatch(author -> author.getAge() > 100);

        System.out.println(b);

    }

    private static void test19() {
//        判断是否所有的作家都是成年人
        List<Author> authors = getAuthors();
        boolean flag = authors.stream()
                .allMatch(author -> author.getAge() >= 18);
        System.out.println(flag);
    }

    private static void test18() {
//        判断是否有年龄在29以上的作家
        List<Author> authors = getAuthors();
        boolean flag = authors.stream()
                .anyMatch(author -> author.getAge() > 29);
        System.out.println(flag);

    }


    private static void test17() {
//        获取一个Map集合，map的key为作者名，value为List<Book>
        List<Author> authors = getAuthors();

        Map<String, List<Book>> map = authors.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));

        System.out.println(map);
    }

    private static void test16() {
//        获取一个所有书名的Set集合。
        List<Author> authors = getAuthors();
        Set<Book> books = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .collect(Collectors.toSet());

        System.out.println(books);
    }

    private static void test15() {
//        获取一个存放所有作者名字的List集合。
        List<Author> authors = getAuthors();
        List<String> nameList = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(nameList);
    }


    private static void test14() {
//        分别获取这些作家的所出书籍的最高分和最低分并打印。
        //Stream<Author>  -> Stream<Book> ->Stream<Integer>  ->求值

        List<Author> authors = getAuthors();
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .max((score1, score2) -> score1 - score2);

        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(book -> book.getScore())
                .min((score1, score2) -> score1 - score2);
        System.out.println(max.get());
        System.out.println(min.get());
    }

    private static void test13() {
//        打印这些作家的所出书籍的数目，注意删除重复元素。
        List<Author> authors = getAuthors();

        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);

    }

    private static void test12() {
//        输出所有作家的名字
        List<Author> authors = getAuthors();

        authors.stream()
                .map(author -> author.getName())
                .distinct()
                .forEach(name-> System.out.println(name));

    }

    private static void test11() {

//        打印现有数据的所有分类。要求对分类进行去重。不能出现这种格式：哲学,爱情     爱情
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(category-> System.out.println(category));

    }

    private static void test10() {
//        打印所有书籍的名字。要求对重复的元素进行去重。
        List<Author> authors = getAuthors();

        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));

    }


    private static void test09() {
//        打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序。
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted()
                .skip(1)
                .forEach(author -> System.out.println(author.getName()));


    }

    private static void test08() {
//        对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素,然后打印其中年龄最大的两个作家的姓名。
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted()
                .limit(2)
                .forEach(author -> System.out.println(author.getName()));
    }


    private static void test07() {
        List<Author> authors = getAuthors();
//        对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素。
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o2.getAge()-o1.getAge())
                .forEach(author -> System.out.println(author.getAge()));
    }

    private static void test06() {
//        打印所有作家的姓名，并且要求其中不能有重复元素。
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .forEach(author -> System.out.println(author.getName()));
    }

    private static void test05() {
//        打印所有作家的姓名
        List<Author> authors = getAuthors();

//        authors.stream()
//                .map(author -> author.getName())
//                .forEach(s -> System.out.println(s));

        authors.stream()
                .map(author -> author.getAge())
                .map(age->age+10)
                .forEach(age-> System.out.println(age));
    }

    private static void test04() {
        List<Author> authors = getAuthors();
//        打印所有姓名长度大于1的作家的姓名
        authors.stream()
                .filter(author -> author.getName().length()>1)
                .forEach(author -> System.out.println(author.getName()));

    }

    private static void test03() {
        Map<String,Integer> map = new HashMap<>();
        map.put("蜡笔小新",19);
        map.put("黑子",17);
        map.put("日向翔阳",16);

        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Stream<Map.Entry<String, Integer>> stream = entrySet.stream();

        stream.filter(new Predicate<Map.Entry<String, Integer>>() {
            @Override
            public boolean test(Map.Entry<String, Integer> entry) {
                return entry.getValue()>16;
            }
        }).forEach(new Consumer<Map.Entry<String, Integer>>() {
            @Override
            public void accept(Map.Entry<String, Integer> entry) {
                System.out.println(entry.getKey()+"==="+entry.getValue());
            }
        });
    }

    private static void test02() {
        Integer[] arr = {1,2,3,4,5};
//        Stream<Integer> stream = Arrays.stream(arr);
        Stream<Integer> stream = Stream.of(arr);
        stream.distinct()
                .filter(integer -> integer>2)
                .forEach(integer -> System.out.println(integer));
    }


    private static void test01(List<Author> authors) {
        authors.stream()//把集合转换成流
                .distinct()
                .filter(author -> {
                    System.out.println("test");
                    return author.getAge() < 18;
                })
                .forEach(author -> System.out.println(author.getName()));
    }

    @Test
    public void Test1(){
        //2011年发生的所有交易，并按交易额排序（从低到高）
        List<Transaction> ts = getTransactions();
        List<Transaction> collect = ts.stream()
                .filter(transaction -> transaction.getYearOfTransaction() == 2011)
                .sorted(Comparator.comparing(Transaction::getTransactionAmount))
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);

        //交易员都在哪些不同的城市工作过？
        List<Transaction> ts2 = getTransactions();
        List<String> collect1 = ts2.stream().map(tss -> tss.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("collect1 = " + collect1);

        //返回所有交易员的姓名字符串，按字母顺序排序。
        List<Transaction> ts3 = getTransactions();
        ts3.stream().map(e -> e.getTrader().getName())
                .sorted(String::compareTo)
                .forEach(System.out::println);

        // 有没有交易员是在米兰工作的？
        System.out.println("**********************************************");
        List<Transaction> ts4 = getTransactions();
        List<Transaction> collect2 = ts4.stream().filter(e -> e.getTrader().getCity().equals("米兰"))
                .collect(Collectors.toList());
        if (collect2.isEmpty()){
            System.out.println("没有在米兰工作的");
        }

        //所有交易中，最高的交易额是多少？
        List<Transaction> ts5 = getTransactions();
        Integer integer = ts5.stream().map(Transaction::getTransactionAmount)
                .reduce(Integer::max).get();
        System.out.println("integer = " + integer);

        Integer integer1 = ts5.stream().map(Transaction::getTransactionAmount)
                .max(Integer::compareTo).get();
        System.out.println("integer1 = " + integer1);

        //找到交易额最小的交易
        List<Transaction> ts6 = getTransactions();
        Integer integer2 = ts6.stream().map(Transaction::getTransactionAmount)
                .reduce(Integer::max).get();
        System.out.println("integer = " + integer);

        Integer integer3 = ts6.stream().map(Transaction::getTransactionAmount)
                .max(Integer::compareTo).get();
        System.out.println("integer1 = " + integer1);
    }


    private static List<Transaction> getTransactions() {
        List<Transaction> tslist = new ArrayList();
        tslist.add(new Transaction(new Trader("张大大", "上海"), 2006, 478));
        tslist.add(new Transaction(new Trader("李四", "广东"), 2011, 9874));
        tslist.add(new Transaction(new Trader("小王", "广东"), 2019, 2131));
        tslist.add(new Transaction(new Trader("晓白", "深圳"), 2011, 3412));
        tslist.add(new Transaction(new Trader("晓白", "深圳"), 2017, 2394));
        tslist.add(new Transaction(new Trader("达尔温", "波西"), 2009, 3121));
        return tslist;
    }

    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L,"蒙多",33,"一个从菜刀中明悟哲理的祖安人",null);
        Author author2 = new Author(2L,"亚拉索",15,"狂风也追逐不上他的思考速度",null);
        Author author3 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);
        Author author4 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L,"刀的两侧是光明与黑暗","哲学,爱情",88,"用一把刀划分了爱恨"));
        books1.add(new Book(2L,"一个人不能死在同一把刀下","个人成长,爱情",99,"讲述如何从失败中明悟真理"));

        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(4L,"吹或不吹","爱情,个人传记",56,"一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author,author2,author3,author4));
        return authorList;
    }
}