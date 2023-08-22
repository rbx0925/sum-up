package com.ikang.idata.search.search.util;

import com.ikang.idata.search.search.Person;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:minxin.fan-ext@ikang.com">minxin.fan</a>
 * @version 1.0
 * @description: TODO
 * @date 2023/3/1 15:00
 */
public class ForEachDemo {
    public static void main(String[] args) {
        funciton_2();
        // testHashSet();
    }

    public static void funciton_2() {
        ArrayList<Person> arr = new ArrayList<Person>();
        arr.add(new Person("a", "女"));
        arr.add(new Person("b", "男"));

        for (Person p : arr) {
            System.out.println(p);
        }

    }

    public static void testHashSet() {
        Collection<String> coll = new ArrayList<String>();
        coll.add("abc1");
        coll.add("add2");
        coll.add("add3");
        coll.add("add4");
        coll.add("add5");
        coll.add("add6");
        for (String s : coll) {
            System.out.println(s);
        }
    }

    public static void function_1() {
        String[] str = { "abc", "a2bb", "a2aa" };
        for (String s : str) {
            System.out.println(s.length());
            System.out.println(s);
        }
    }

    public static void function() {
        int[] arr = { 2121, 5454, 545, 4, 54 };
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void list(){
        String[] str = { "abc", "a2bb", "a2aa" };
        String join = StringUtils.join(str, ",");
        System.out.println(join);


        List<String> locIdList = Arrays.asList("One",
                "Two",
                "Three",
                "Four",
                "Five");
        String join1 = StringUtils.join(locIdList, ",");
        System.out.println(join1);




    }

    @Test
    public void listTest1() {

        String[] array = {"张三", "李四", "王五", "撒娇", "九点十几分", "没事", "圣诞节"};
        String str;

        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }

        /**
         *
         * 将集合用字符串，逗号隔开拼接
         * */

        //第一种方法(灵活，可以用逗号隔开，用|线隔开)
        str = StringUtils.join(list, ",");
        System.out.println("第一种方法" + str);

        //第二种方法
        str = listToString1(list);
        System.out.println("第二种方法" + str);

        //第三种方法(灵活，可以用逗号隔开，用|线隔开)
        str = listToString2(list, ',');
        System.out.println("第三种方法" + str);


        //第四种方法
        str = listToString3(list, ",");
        System.out.println("第四种方法" + str);


        Separator separator = new Separator(",");
        //第五种方法
        str = listToString4(list, separator);
        System.out.println("第五种方法" + str);


        // 如何把list集合拼接成以逗号分隔的字符串 a,b,c
        List<String> list1 = Arrays.asList("a", "b", "c");

        // 第一种方法，可以用stream流
        String join = list1.stream().collect(Collectors.joining(","));
        System.out.println(join);
        // 输出 a,b,c

        // 第二种方法，其实String也有join方法可以实现这个功能
        String join1 = String.join(",", list);
        System.out.println(join1);
        // 输出 a,b,c

    }


    public static String listToString1(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    public static String listToString2(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return list.isEmpty() ? "" : sb.toString().substring(0, sb.toString().length() - 1);
    }


    public static String listToString3(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }


    public static class Separator {
        private String next = "";
        private String separator;

        public Separator(String separator) {
            this.separator = separator;
        }

        public String get() {
            String result = next;
            next = separator;
            return result;
        }
    }

    public static String listToString4(List<String> list, Separator separator) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            if (s != null && !"".equals(s)) {
                sb.append(separator.get()).append(s);
            }
        }
        return sb.toString();
    }


    @Test
    public void ListString() {


        //<string集合、integer集合均可随意转换成string>
        ArrayList<String> arrayList = new ArrayList<String>() {{
            add("1");
            add("2");
            add("2");
            add("4");
        }};
        System.out.println(arrayList.stream().collect(Collectors.toList()));
        String str1 = arrayList.stream().map(integer -> integer.toString()).collect(Collectors.joining(","));
        System.out.println(str1);


        //integer类型的集合转换成string
        ArrayList<Integer> list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        System.out.println(list1);
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (Integer integer : list1) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(integer);
        }
        System.out.println(result);


        //String类型的集合转换成string
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        System.out.println(stringList);
        String string = StringUtils.join(stringList, ",");
        System.out.println(string);

    }

    @Test
    public void saveToString() {

        //创建集合对象
        List s = new ArrayList();

        //创建字符串添加字符串
        s.add("hellow");
        s.add("word");
        s.add("java");

        //遍历字符串
        Iterator it = s.iterator();

        while (it.hasNext()) {
            String a = (String) it.next();
            System.out.println(a);
        }


        //实现list清除重复的字符串
        List list = new ArrayList();

        list.add("abc");

        list.add("efg");

        list.add("ghi");

        list.add("ghi");

        list.add("ghi");

        list.add("efg");

        for (int i = 0; i < list.size(); i++) {

            int j = list.lastIndexOf(list.get(i));

            if (i != j) {

                list.remove(j);

                i--;
                //每次有重复都让i回到初始位置，i==j时才可以向下循环

            }

        }

        System.out.println(list);

    }

    private static final int max = 100;


    @Test
    public void testPlus(){

        System.out.println(">>> testPlus() <<<");


        String str = "";


        long start = System.currentTimeMillis();


        for (int i = 0; i < max; i++) {
            str = str + "a";
        }


        long end = System.currentTimeMillis();


        long cost = end - start;


        System.out.println("   {str + \"a\"} cost=" + cost + " ms");

    }

    @Test
    public void testConcat() {
        System.out.println(">>> testConcat() <<<");


        String str = "";


        long start = System.currentTimeMillis();


        for (int i = 0; i < max; i++) {
            str = str.concat("a");
        }


        long end = System.currentTimeMillis();


        long cost = end - start;


        System.out.println("   {str.concat(\"a\")} cost=" + cost + " ms");
    }

    @Test
    public void testJoin() {
        System.out.println(">>> testJoin() <<<");


        long start = System.currentTimeMillis();


        List<String> list = new ArrayList<String>();


        for (int i = 0; i < max; i++) {
            list.add("a");
        }


        long end1 = System.currentTimeMillis();
        long cost1 = end1 - start;


        StringUtils.join(list, "");


        long end = System.currentTimeMillis();
        long cost = end - end1;


        System.out.println("   {list.add(\"a\")} cost1=" + cost1 + " ms");
        System.out.println("   {StringUtils.join(list, \"\")} cost=" + cost
                + " ms");
    }

    @Test
    public void testStringBuffer() {
        System.out.println(">>> testStringBuffer() <<<");


        long start = System.currentTimeMillis();


        StringBuffer strBuffer = new StringBuffer();


        for (int i = 0; i < max; i++) {
            strBuffer.append("a");
        }
        strBuffer.toString();


        long end = System.currentTimeMillis();


        long cost = end - start;


        System.out.println("   {strBuffer.append(\"a\")} cost=" + cost + " ms");
    }


    @Test
    public void testStringBuilder() {
        System.out.println(">>> testStringBuilder() <<<");

        long start = System.currentTimeMillis();

        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < max; i++) {
            strBuilder.append("a");
        }
        strBuilder.toString();

        long end = System.currentTimeMillis();

        long cost = end - start;

        System.out.println("   {strBuilder.append(\"a\")} cost=" + cost + " ms");
    }

    /**
     * 结果
     >>> testPlus() <<<
     {str + "a"} cost=0 ms
     >>> testConcat() <<<
     {str.concat("a")} cost=0 ms
     >>> testJoin() <<<
     {list.add("a")} cost1=0 ms
     {StringUtils.join(list, "")} cost=20 ms
     >>> testStringBuffer() <<<
     {strBuffer.append("a")} cost=0 ms
     >>> testStringBuilder() <<<
     {strBuilder.append("a")} cost=0 ms
     */

    @Test
    public void StreamJoin(){

        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("d");
        stringList.add("k");
        System.out.println(String.join(",", stringList));

        List<Object> objectList = new ArrayList<>();
        objectList.add(1L);
        objectList.add(10);
        objectList.add("很方便");
        System.out.println(objectList.stream().map(Objects::toString).collect(Collectors.joining()));
        System.out.println(objectList.stream().map(Objects::toString).collect(Collectors.joining(",")));
        System.out.println(objectList.stream().map(Objects::toString).collect(Collectors.joining("-")));
        System.out.println(stringList.stream().map(Objects::toString).collect(Collectors.joining("-")));

        /**
         * a,d,k
         * 110很方便
         * 1,10,很方便
         * 1-10-很方便
         * a-d-k
         */

    }


}
