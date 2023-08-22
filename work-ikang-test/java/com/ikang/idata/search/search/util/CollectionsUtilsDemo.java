package com.ikang.idata.search.search.util;

/**
 * @author <a href="mailto:minxin.fan-ext@ikang.com">minxin.fan</a>
 * @version 1.0
 * @description: TODO
 * @date 2023/2/13 10:01
 */


import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.junit.Test;

import java.util.*;

/**
 * <p>
 * 通过apache-commons包中的org.apache.commons.collections.CollectionUtils集合操作工具类
 * 对集合间进行
 * 合并union、
 * 交叉intersection、
 * 分离disjunction、
 * 减去subtract、
 * 任意包含containsAny、
 * 判断是否为子集isSubCollection、
 * 颠倒序列reverseArray
 * 及判断是否填满isFull等操作。
 * </p>
 **/
public class CollectionsUtilsDemo {

    /** Arrays.asList()返回的是Arrays内部类ArraysList，
     * 不可对其进行add、remove等操作，返回报UnsupportedOperationException */
    /** java.util.ArrayList和Arrays内部类ArraysList都继承AbstractList，
     * remove、add等方法AbstractList中是默认throw UnsupportedOperationException而且不作任何操作。*/
    /** java.util.ArrayList重新了这些方法而Arrays的内部类ArrayList没有重新，所以会抛出异常。*/
    // @Deprecated
    // private static List<String> list1 = Arrays.asList(new String[] {"1", "2", "3", "1"});
    // @Deprecated
    // private static List<String> list2 = Arrays.asList(new String[] {"2", "3", "4"});
    // @Deprecated
    // private static List<String> list3 = Arrays.asList(new String[] {"1", "2"});
    /**
     * 解决
     */
    private static List<String> list1 = new ArrayList<>(Arrays.asList(new String[]{"1", "2", "3", "1", "5"}));
    private static List<String> list2 = new ArrayList<>(Arrays.asList(new String[]{"2", "3", "1"}));
    private static List<String> list3 = new ArrayList<>(Arrays.asList(new String[]{"1", "2"}));

    @Data
    class Employee {
        private String name;
        private String email;
        private int age;
        private double salary;
        /**
         * 是否在职
         */
        private boolean activeEmployee;

        public Employee(String name, String email, int age, double salary, boolean activeEmployee) {
            this.name = name;
            this.email = email;
            this.age = age;
            this.salary = salary;
            this.activeEmployee = activeEmployee;
        }
    }

    /**
     * 判断两个集合是否和相同元素
     */
    public void containsAnyT1() {
        // 判断两个集合是否和相同元素
        boolean b = CollectionUtils.containsAny(list1, list2);
        System.out.println(b);
    }

    /**
     * intersection
     * 得到两个集合中相同的元素
     */
    @Test
    public void intersectionT1() {
        Collection b = CollectionUtils.intersection(list1, list2);
        System.out.println(b);
    }

    /**
     * union
     * 合并两个集合，不去重
     */
    @Test
    public void unionT1() {
        Collection b = CollectionUtils.union(list1, list2);
        System.out.println(b);
    }

    /**
     * disjunction
     * 取两个集合差集，不去重
     * <p>
     * {"1", "2", "3", "1", "5"}
     * {"2", "3", "1"}
     * <p>
     * [1, 5]
     */
    @Test
    public void disjunctionT1() {
        Collection b = CollectionUtils.disjunction(list1, list2);
        System.out.println(b);
    }

    /**
     * list1 - list2 = 剩余元素组成的集合
     */
    @Test
    public void subtractT1() {
        // Collection b = CollectionUtils.subtract(list1, list2);
        Collection b = CollectionUtils.subtract(list2, list1);
        System.out.println(b);
    }

    /**
     * 统计集合中各元素出现的次数，并Map<Object, Integer>输出
     */
    @Test
    public void getCardinalityMapT1() {
        Map cardinalityMap = CollectionUtils.getCardinalityMap(list1);
        cardinalityMap.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    /**
     * a是否b集合子集，a集合大小<=b集合大小
     */
    @Test
    public void isSubCollectionT1() {
        // boolean subCollection = CollectionUtils.isSubCollection(list3, list1);
        boolean subCollection = CollectionUtils.isSubCollection(list3, list2);
        System.out.println(subCollection);
    }

    /**
     * a是否b集合子集，a集合大小<b集合大小
     */
    @Test
    public void isProperSubCollectionT1() {
        // boolean subCollection = CollectionUtils.isSubCollection(list3, list1);
        boolean subCollection = CollectionUtils.isProperSubCollection(list3, list2);
        System.out.println(subCollection);
    }

    /**
     * 两个集合是否相同
     */
    @Test
    public void isEqualCollectionT1() {
        boolean subCollection = CollectionUtils.isSubCollection(list1, list1);
        // boolean subCollection = CollectionUtils.isEqualCollection(list3, list2);
        System.out.println(subCollection);
    }

    /**
     * 某元素在集合中出现的次数
     */
    @Test
    public void cardinalityT1() {
        int cardinality = CollectionUtils.cardinality("1", list1);
        System.out.println(cardinality);
    }

    /**
     * 返回集合中满足函数式的唯一元素，只返回最先处理符合条件的唯一元素
     */
    @Test
    public void findT1() {
        Object o = CollectionUtils.find(list1, e -> Integer.parseInt(e.toString()) > 2);
        System.out.println(o.toString());
    }

    /**
     * 对集合中的对象中的某一属性进行批量更新，closure为需要更新的属性对象
     * 如对集合中所有员工的加薪20%
     */
    @Test
    public void forAllDoT1() {
        // // create the closure
        // List<Employee> employees = new ArrayList<>();
        // Employee e1 = new Employee("e1", "e1.com", 21, 10000, true);
        // Employee e2 = new Employee("e2", "e2.com", 22, 14000, false);
        // Employee e3 = new Employee("e3", "e3.com", 23, 12000, true);
        // Employee e4 = new Employee("e4", "e4.com", 21, 12000, true);
        // Closure<E> closure = new Closure() {
        //     @Override
        //     public void execute(Employee e) {
        //         e.setSalary(e.getSalary() * 1.2);
        //     }
        // };

    }

    /**
     * 过滤集合中满足函数式的所有元素
     */
    @Test
    public void filterT1() {
        CollectionUtils.filter(list1, e -> Integer.parseInt(e.toString()) > 1);
        list1.forEach(s -> {
            System.out.println(s);
        });
    }

    /**
     * 转换新的集合，对集合中元素进行操作，如每个元素都累加1
     */
    @Test
    public void transformT1() {

        CollectionUtils.transform(list1, new Transformer() {
            @Override
            public Object transform(Object o) {
                Integer num = Integer.parseInt((String) o);
                return String.valueOf(++num);
            }
        });
        list1.forEach(s -> {
            System.out.println(s);
        });

        System.out.println("============================");

        // JDK8
        List<String> temp = new ArrayList<>();
        list1.stream().forEach(i -> {
            int num = Integer.parseInt(i);
            temp.add(String.valueOf(num));
        });
        temp.forEach(System.out::println);
    }

    /**
     * 返回集合中满足函数式的数量
     */
    @Test
    public void countMatchesT1() {
        int num = CollectionUtils.countMatches(list1, i -> Integer.parseInt((String) i) > 0);
        System.out.println(num);
    }

    /**
     * 将满足表达式的元素存入新集合中并返回新集合元素对象
     */
    @Test
    public void selectT1() {
        Collection select = CollectionUtils.select(list1, i -> Integer.parseInt((String) i) > 2);
        select.forEach(System.out::println);
    }

    /**
     * 将不满足表达式的元素存入新集合中并返回新集合元素对象
     */
    @Test
    public void selectRejectedT1() {
        Collection select = CollectionUtils.selectRejected(list1, i -> Integer.parseInt((String) i) > 2);
        select.forEach(System.out::println);
    }

    /**
     * collect底层调用的transform方法
     * 将所有元素进行处理，并返回新的集合
     */
    @Test
    public void collectT1() {
        Collection collecttion = CollectionUtils.collect(list1, new Transformer() {
            @Override
            public Object transform(Object o) {
                int i = Integer.parseInt((String) o);
                return ++i;
            }
        });
        collecttion.forEach(System.out::println);
    }

    /**
     * 将一个数组或集合中的元素全部添加到另一个集合中
     */
    @Test
    public void adAllT1() {
        CollectionUtils.addAll(list1, new String[]{"5", "6"});
//        CollectionUtils.addAll(list1, list2.toArray());
        list1.forEach(System.out::println);
    }

    /**
     * 返回集合中指定下标元素
     */
    @Test
    public void indexT1() {
//        String index = (String) CollectionUtils.index(list1, 2);
//        System.out.println(index);
    }

    /**
     * 返回集合中指定下标元素
     */
    @Test
    public void getT1() {
        String index = (String) CollectionUtils.get(list1, 2);
        System.out.println(index);
    }

    /**
     * 判断集合是否为空
     */
    @Test
    public void isEmptyT1() {
        int[] arr = new int[5];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        arr[4] = 1;
        boolean empty = CollectionUtils.isFull(new ArrayList(Arrays.asList(arr)));
        System.out.println(empty);
    }

    /**
     * 判断集合是否为空
     */
    @Test
    public void isFullT1() {
        boolean full = CollectionUtils.isFull(list1);
        System.out.println(full);
    }

    /**
     * 返回集合最大空间
     */
    @Test
    public void maxSizeT1() {
        // List<Integer> boundedList = new ArrayList<>(8);
        // int i = CollectionUtils.maxSize(boundedList);
        // System.out.println(i);
    }

    /**
     * 只要集合中元素不满足表达式就抛出异常
     */
    @Test
    public void predicatedCollectionT1() {
        Collection collection = CollectionUtils.predicatedCollection(list1, i -> Integer.parseInt((String) i) > 1);
        collection.forEach(System.out::println);
    }

    /**
     * 只要集合中元素不满足表达式就抛出异常
     */
    @Test
    public void removeAllT1() {
        boolean b = list1.removeAll(list2);
        System.out.println(b);
        list1.forEach(System.out::println);
    }

    /**
     * 只要集合中元素不满足表达式就抛出异常
     */
    @Test
    public void synchronizedCollectionT1() {
        Collection collection = CollectionUtils.synchronizedCollection(list1);
        collection.forEach(System.out::println);
    }

    /**
     * 只要集合中元素不满足表达式就抛出异常
     */
    @Test
    public void unmodifiedCollectionT1() {
        Collection collection = CollectionUtils.unmodifiableCollection(list1);
        collection.forEach(System.out::println);
    }

    /**
     * 只要集合中元素不满足表达式就抛出异常
     */
/*    @Test
    public void predicatedCollectionT2() {
        // Collection collection = CollectionUtils.predicatedCollection(list1, i -> Integer.parseInt((String)i) > 0);
        // Collection collection = CollectionUtils.typedCollection(list1, String.class);
        Collection collection = CollectionUtils.transformedCollection(list1, new Transformer() {
            @Override
            public Object transform(Object o) {
                int n = Integer.parseInt((String) o);
                return n + n;
            }
        });
        collection.forEach(System.out::println);
    }*/


    public static void main(String[] args) {


        List list = new ArrayList();

        list.add("zhangsan");

        list.add("dalian");

        list.add("gateway");

        list.add("gateway");

        list.add("28");

        list.add("35");

//查找第一个符合条件的元素

        Object find = CollectionUtils.find(list, new Predicate() {

            @Override

            public boolean evaluate(Object arg0) {

                if (StringUtils.isNumeric(arg0.toString())) {

                    return false;

                }

                return true;

            }

        });


//排除不符合条件的元素

        Collection select = CollectionUtils.select(list, new Predicate() {

            @Override

            public boolean evaluate(Object arg0) {

                if (StringUtils.isNumeric(arg0.toString())) {

                    return false;

                }

                return true;

            }

        });

        System.out.println(list);//[zhangsan, dalian, 28]

        System.out.println(find);//zhangsan

        System.out.println(select);//[zhangsan, dalian]


        List list2 = new ArrayList();

        list2.add("zhangsan");

        list2.add("dalian2");

        list2.add("282");

//查看两个集合中是否有相同的元素

        boolean containsAny = CollectionUtils.containsAny(list, list2);

        System.out.println(containsAny);//true


//查找集合中某元素的个数

        int countMatches = CollectionUtils.countMatches(list, new Predicate() {

            @Override

            public boolean evaluate(Object arg0) {

                if (StringUtils.isNumeric(arg0.toString())) {

                    return true;

                }

                return false;

            }

        });

        System.out.println(countMatches);


//查找集合中是否存在某元素

        boolean exists = CollectionUtils.exists(list, new Predicate() {

            @Override

            public boolean evaluate(Object arg0) {

                if (StringUtils.isNumeric(arg0.toString())) {

                    return true;

                }

                return false;

            }

        });


       // SRoktugzystem.out.println(exists);


//删除集合中的某些元素

        System.out.println(list);//[zhangsan, dalian, 打法, 28, 35]

        CollectionUtils.filter(list, new Predicate() {

            @Override

            public boolean evaluate(Object arg0) {

                if (StringUtils.isNumeric(arg0.toString())) {

                    return false;

                }

                return true;

            }

        });


        System.out.println(list);//[zhangsan, dalian, 打法]


//查找集合中符合条件的第一个元素

        Object find1 = CollectionUtils.find(list, new Predicate() {

            @Override

            public boolean evaluate(Object arg0) {

                if (StringUtils.isNumeric(arg0.toString())) {

                    return false;

                }

                return true;

            }

        });

        System.out.println(find1);//zhangsan

//
////闭包回调函数
//
//        System.out.println(list);
//
//        CollectionUtils.forAllDo(list, new Closure() {
//
//            @Override
//
//            public void execute(Object arg0) {
//
//                if (StringUtils.isNumeric(arg0.toString())) {
//
//                    work(arg0);
//
//                }
//
//            }
//
//        });
//
//        System.out.println(list);


//查找集合中元素的个数

        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 28, 35]

        Map cardinalityMap = CollectionUtils.getCardinalityMap(list);

        System.out.println(cardinalityMap);//{35=1, 28=1, zhangsan=1, dalian=1, gateway=2}


//查找两个集合中的相同元素

        List list22 = new ArrayList();

        list22.add("lisi");

        list22.add("beijing");

        list22.add("gateway");

        list.add("19");

        Collection intersection = CollectionUtils.intersection(list, list2);

        System.out.println(intersection);


//获取两个List>中相同的部分，可以用于对比两个库中的相同表数据是否相等

        List<Map> list3 = new ArrayList<>();

        List<Map> list4 = new ArrayList<>();

        Map map1 = new HashMap();

        map1.put("name", "zhangsan");

        map1.put("age", 18);

        map1.put("address", "dalian");

        Map map2 = new HashMap();

        map2.put("name", "张三");

        map2.put("age", "十八");

        map2.put("address", "大连");

        list3.add(map1);

        list3.add(map2);


        Map map3 = new HashMap();

        map3.put("name", "李四");

        map3.put("age", "十八");

        map3.put("address", "大连");

        list4.add(map2);

        list4.add(map1);

        list3.add(map3);

        System.out.println(list3);

        System.out.println(list4);

        Collection intersection1 = CollectionUtils.intersection(list3, list4);

        System.out.println(intersection1);//[{address=dalian, name=zhangsan, age=18}]


//判断两个集合是否相同

        boolean equalCollection = CollectionUtils.isEqualCollection(list3, list4);

        System.out.println(equalCollection);


        //判断param2是否包含param1，且不能完全相同

        System.out.println(list3);//[{address=dalian, name=zhangsan, age=18}, {address=大连, name=张三, age=十八}, {address=大连, name=李四, age=十八}]

        System.out.println(list4);//[{address=大连, name=张三, age=十八}, {address=dalian, name=zhangsan, age=18}]

        boolean properSubCollection = CollectionUtils.isProperSubCollection(list4, list3);

        System.out.println(properSubCollection);//true


//断定某集合是否含有某元素，如果包含则抛异常（我觉得应该少用为妙）

        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 28, 35]

        Collection predicatedCollection = CollectionUtils.predicatedCollection(list, new Predicate() {

            @Override

            public boolean evaluate(Object object) {

                if (StringUtils.isNumeric(object.toString())) {

                    return false;

                }

                return true;

            }

        });

        System.out.println(predicatedCollection);//Exception in thread "main" java.lang.IllegalArgumentException: Cannot add Object '28' - Predicate rejected it

        List list23 = new ArrayList();

        list23.add("gateway");

        System.out.println(list);

        System.out.println(list23);

//删除list1中的list2

        Collection removeAll = CollectionUtils.removeAll(list, list23);

        System.out.println(removeAll);


//反转数组

        //String[] array = new String[];
        //
        //System.out.println(StringUtils.join(array, ","));//zs,ls,ww
        //
        //CollectionUtils.reverseArray(array);
        //
        //System.out.println(StringUtils.join(array, ","));//ww,ls,zs


        //删除list1中的list24

        List list24 = new ArrayList();

        list24.add("zhangsan");

        list24.add("dalian");

        Collection subtract = CollectionUtils.subtract(list, list24);

        System.out.println(subtract);


//更改集合中的特定值

        Collection collect = CollectionUtils.collect(list, new Transformer() {

            @Override

            public Object transform(Object arg0) {

                if (StringUtils.isNumeric(arg0.toString())) {

                    return 15;

                }

                return arg0;

            }

        });

        System.out.println(list);//[zhangsan, dalian, 28, 35]

        System.out.println(collect);//[zhangsan, dalian, 15, 15]


//更改集合中的特定值，改变集合本身

        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 28, 35]

        CollectionUtils.transform(list, new Transformer() {


            @Override

            public Object transform(Object input) {

                if (StringUtils.isNumeric(input.toString())) {

                    return 15;

                }

                return input;

            }

        });

        System.out.println(list);//[zhangsan, dalian, gateway, gateway, 15, 15]

        List list25 = new ArrayList();

        list25.add("docker");

        list25.add("zhangsan");

        System.out.println(list);

        System.out.println(list25);

//将list和list2中的元素合并，但去除list2中的重复元素

        Collection union = CollectionUtils.union(list, list25);

        System.out.println(union);
    }
}

