package com.ikang.idata.search.search.util;


import com.ikang.idata.search.search.Student;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MapCollectTest {
    static int hashMapW = 0;
    static int hashMapR = 0;
    static int linkMapW = 0;
    static int linkMapR = 0;
    static int treeMapW = 0;
    static int treeMapR = 0;
    static int hashTableW = 0;
    static int hashTableR = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            MapCollectTest test = new MapCollectTest();
            test.test(100 * 10000);
            System.out.println();
        }

        System.out.println("hashMapW = " + hashMapW / 10);
        System.out.println("hashMapR = " + hashMapR / 10);
        System.out.println("linkMapW = " + linkMapW / 10);
        System.out.println("linkMapR = " + linkMapR / 10);
        System.out.println("treeMapW = " + treeMapW / 10);
        System.out.println("treeMapR = " + treeMapR / 10);
        System.out.println("hashTableW = " + hashTableW / 10);
        System.out.println("hashTableR = " + hashTableR / 10);
    }

    public void test(int size) {
        int index;
        Random random = new Random();
        String[] key = new String[size];

        // HashMap 插入
        Map<String, String> map = new HashMap<String, String>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        long end = System.currentTimeMillis();
        hashMapW += (end - start);
        System.out.println("HashMap插入耗时 = " + (end - start) + " ms");

        // HashMap 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        hashMapR += (end - start);
        System.out.println("HashMap读取耗时 = " + (end - start) + " ms");

        // LinkedHashMap 插入
        map = new LinkedHashMap<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        linkMapW += (end - start);
        System.out.println("LinkedHashMap插入耗时 = " + (end - start) + " ms");

        // LinkedHashMap 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        linkMapR += (end - start);
        System.out.println("LinkedHashMap读取耗时 = " + (end - start) + " ms");

        // TreeMap 插入
        key = new String[size];
        map = new TreeMap<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        treeMapW += (end - start);
        System.out.println("TreeMap插入耗时 = " + (end - start) + " ms");

        // TreeMap 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        treeMapR += (end - start);
        System.out.println("TreeMap读取耗时 = " + (end - start) + " ms");

        // Hashtable 插入
        key = new String[size];
        map = new Hashtable<String, String>();
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            key[i] = UUID.randomUUID().toString();
            map.put(key[i], UUID.randomUUID().toString());
        }
        end = System.currentTimeMillis();
        hashTableW += (end - start);
        System.out.println("Hashtable插入耗时 = " + (end - start) + " ms");

        // Hashtable 读取
        start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            index = random.nextInt(size);
            map.get(key[index]);
        }
        end = System.currentTimeMillis();
        hashTableR += (end - start);
        System.out.println("Hashtable读取耗时 = " + (end - start) + " ms");


        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        for (String key1 : map1.keySet()) {
            System.out.println(key1 + " ：" + map1.get(key1));
        }

        for (Map.Entry<String, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + " ：" + entry.getValue());
        }

        Iterator<String> iterator = map1.keySet().iterator();
        while (iterator.hasNext()) {
            String key2 = iterator.next();
            System.out.println(key2 + "　：" + map1.get(key2));
        }

        Iterator<Map.Entry<String, String>> iterator1 = map1.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<String, String> entry = iterator1.next();
            System.out.println(entry.getKey() + "　：" + entry.getValue());
        }


        // 初始化，10W次赋值
        Map<Integer, Integer> mapp = new HashMap<Integer, Integer>();
        for (int i = 0; i < 100000; i++)
            mapp.put(i, i);

        /** 增强for循环，keySet迭代 */
        long startp = System.currentTimeMillis();
        for (Integer keyp : mapp.keySet()) {
            mapp.get(keyp);
        }
        long endp = System.currentTimeMillis();
        System.out.println("增强for循环，keySet迭代 -> " + (endp - startp) + " ms");

        /** 增强for循环，entrySet迭代 */
        start = System.currentTimeMillis();
        for (Map.Entry<Integer, Integer> entry : mapp.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();
        System.out.println("增强for循环，entrySet迭代 -> " + (end - start) + " ms");

        /** 迭代器，keySet迭代 */
        start = System.currentTimeMillis();
        Iterator<Integer> iteratorp = mapp.keySet().iterator();
        Integer keyp;
        while (iteratorp.hasNext()) {
            keyp = iteratorp.next();
            map.get(keyp);
        }
        end = System.currentTimeMillis();
        System.out.println("迭代器，keySet迭代 -> " + (endp - startp) + " ms");

        /** 迭代器，entrySet迭代 */
        start = System.currentTimeMillis();
        Iterator<Map.Entry<Integer, Integer>> iterator1p = mapp.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        while (iterator1p.hasNext()) {
            entry = iterator1p.next();
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();

        System.out.println("迭代器，entrySet迭代 -> " + (end - start) + " ms");

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("a", "c");
        map2.put("b", "b");
        map2.put("c", "a");

        // 通过ArrayList构造函数把map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 通过比较器实现比较排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
                return mapping1.getKey().compareTo(mapping2.getKey());
            }
        });

        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + " ：" + mapping.getValue());
        }

    }

    @Test
    public void mapTest() {

        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");

        for (String key1 : map1.keySet()) {
            System.out.println(key1 + " ：" + map1.get(key1));
        }

        System.out.println("****************************");
        for (Map.Entry<String, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + " ：" + entry.getValue());
        }
        System.out.println("****************************");

        Iterator<String> iterator = map1.keySet().iterator();
        while (iterator.hasNext()) {
            String key2 = iterator.next();
            System.out.println(key2 + "　：" + map1.get(key2));
        }
        System.out.println("****************************");

        Iterator<Map.Entry<String, String>> iterator1 = map1.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<String, String> entry = iterator1.next();
            System.out.println(entry.getKey() + "　：" + entry.getValue());
        }
        System.out.println("****************************");

    }

    @Test
    public void test2() {
        // 初始化，10W次赋值
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 100000; i++)
            map.put(i, i);

        /** 增强for循环，keySet迭代 */
        long start = System.currentTimeMillis();
        for (Integer key : map.keySet()) {
            map.get(key);
        }
        long end = System.currentTimeMillis();
        System.out.println("增强for循环，keySet迭代 -> " + (end - start) + " ms");

        /** 增强for循环，entrySet迭代 */
        start = System.currentTimeMillis();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();
        System.out.println("增强for循环，entrySet迭代 -> " + (end - start) + " ms");

        /** 迭代器，keySet迭代 */
        start = System.currentTimeMillis();
        Iterator<Integer> iterator = map.keySet().iterator();
        Integer key;
        while (iterator.hasNext()) {
            key = iterator.next();
            map.get(key);
        }
        end = System.currentTimeMillis();
        System.out.println("迭代器，keySet迭代 -> " + (end - start) + " ms");

        /** 迭代器，entrySet迭代 */
        start = System.currentTimeMillis();
        Iterator<Map.Entry<Integer, Integer>> iterator1 = map.entrySet().iterator();
        Map.Entry<Integer, Integer> entry;
        while (iterator1.hasNext()) {
            entry = iterator1.next();
            entry.getKey();
            entry.getValue();
        }
        end = System.currentTimeMillis();

        System.out.println("迭代器，entrySet迭代 -> " + (end - start) + " ms");
        /**
         * 第一次
         * 增强for循环，keySet迭代 -> 37 ms
         * 增强for循环，entrySet迭代 -> 19 ms
         * 迭代器，keySet迭代 -> 14 ms
         * 迭代器，entrySet迭代 -> 9 ms
         *
         * 第二次
         * 增强for循环，keySet迭代 -> 29 ms
         * 增强for循环，entrySet迭代 -> 22 ms
         * 迭代器，keySet迭代 -> 19 ms
         * 迭代器，entrySet迭代 -> 12 ms
         *
         * 第三次
         * 增强for循环，keySet迭代 -> 27 ms
         * 增强for循环，entrySet迭代 -> 19 ms
         * 迭代器，keySet迭代 -> 18 ms
         * 迭代器，entrySet迭代 -> 10 ms
         *
         * 第四次
         * 增强for循环，keySet迭代 -> 31 ms
         * 增强for循环，entrySet迭代 -> 20 ms
         * 迭代器，keySet迭代 -> 17 ms
         * 迭代器，entrySet迭代 -> 10.33 ms
         */
    }

    @Test
    public void test3() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "c");
        map.put("b", "b");
        map.put("c", "a");

        // 通过ArrayList构造函数把map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 通过比较器实现比较排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
                return mapping1.getKey().compareTo(mapping2.getKey());
            }
        });

        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + " ：" + mapping.getValue());
        }
    }

    @Test
    public void test4() {

        Map<String, String> map = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                return obj2.compareTo(obj1);// 降序排序
            }
        });
        map.put("a", "c");
        map.put("b", "b");
        map.put("c", "a");

        for (String key : map.keySet()) {
            System.out.println(key + " ：" + map.get(key));
        }
    }

    @Test
    public void test5() {
        Map<String, String> map = new TreeMap<String, String>();
        map.put("a", "c");
        map.put("b", "b");
        map.put("c", "a");

        // 通过ArrayList构造函数把map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 通过比较器实现比较排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }
        });

        for (String key : map.keySet()) {
            System.out.println(key + " ：" + map.get(key));
        }
    }

    @Test
    public void test6() {
        //HashSet : 无序、不重复、无索引。
        // 无序, 不重复, 无索引
        Set<String> sets = new HashSet<>();
        sets.add("MySQL");
        sets.add("MySQL");
        sets.add("JAVA");
        sets.add("JAVA");
        sets.add("HTML");
        sets.add("HTML");
        sets.add("Vue");
        sets.add("Vue");
        System.out.println(sets);
        // [JAVA, MySQL, Vue, HTML]
    }

    @Test
    public void test7() {
        //LinkedHashSet：有序、不重复、无索引。
        // 有序、不重复、无索引
        Set<String> sets = new LinkedHashSet<>();
        sets.add("MySQL");
        sets.add("MySQL");
        sets.add("JAVA");
        sets.add("JAVA");
        sets.add("HTML");
        sets.add("HTML");
        sets.add("Vue");
        sets.add("Vue");
        System.out.println(sets);
        // [MySQL, JAVA, HTML, Vue]

    }

    @Test
    public void test8() {

        //TreeSet：排序: 默认升序、不重复、无索引。
        // 排序、不重复、无索引
        Set<Integer> sets = new TreeSet<>();
        sets.add(10);
        sets.add(10);
        sets.add(20);
        sets.add(20);
        sets.add(30);
        sets.add(30);
        sets.add(40);
        sets.add(40);
        sets.add(50);
        sets.add(50);
        System.out.println(sets);
        // [10, 20, 30, 40, 50]
    }

    @Test
    public void test9() {

        // 创建集合存储学生对象
        Set<Student> students = new HashSet<>();
        // 创建学生对象
        Student stu1 = new Student(1, "小明", "男", 101);
        Student stu2 = new Student(2, "小明", "男", 101);
        Student stu3 = new Student(3, "小王", "女", 102);
        // 将学生对象添加到集合中
        students.add(stu1);
        students.add(stu2);
        students.add(stu3);
        System.out.println(students);

    }

    @Test
    public void test10() {

        Set<Integer> sets1 = new TreeSet<>();
        sets1.add(50);
        sets1.add(10);
        sets1.add(30);
        sets1.add(20);
        System.out.println(sets1);
        // [10, 20, 30, 50]

        Set<Double> sets2 = new TreeSet<>();
        sets2.add(10.11);
        sets2.add(20.22);
        sets2.add(43.22);
        sets2.add(8.22);
        System.out.println(sets2);
        // [8.22, 10.11, 20.22, 43.22]


        Set<String> sets = new TreeSet<>();
        sets.add("bbb");
        sets.add("eee");
        sets.add("aaa");
        sets.add("ccc");
        System.out.println(sets);
        // [aaa, bbb, ccc, eee]


    }

    @Test
    public void Demo() {

        //初始化,10w次赋值
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < 100000; i++) {
            map.put(i, i);
            //增强for循环 keySet迭代
            for (Integer key : map.keySet()) {
                map.get(key);
            }

            //增强for entrySet迭代
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                entry.getKey();
                entry.getValue();

            }

            //迭代器 keySet迭代
            Iterator<Integer> iterator = map.keySet().iterator();
            Integer key;
            while (iterator.hasNext()) {
                key = iterator.next();
                map.get(key);

            }

            //迭代器 entrySet迭代
            Iterator<Map.Entry<Integer, Integer>> iterator1 = map.entrySet().iterator();
            Map.Entry<Integer, Integer> entry;

            while (iterator1.hasNext()) {
                entry = iterator1.next();
                entry.getValue();
                entry.getKey();

            }


        }
    }

    @Test
    public void treeSet() {
        // 创建学生对象
        Student stu1 = new Student(1, "小明", "男", 101);
        Student stu2 = new Student(2, "小赵", "男", 101);
        Student stu3 = new Student(3, "小王", "女", 102);
        // 创建集合
        Set<Student> students = new TreeSet<>();
        students.add(stu1);
        students.add(stu2);
        students.add(stu3);
        System.out.println(students);
        // 打印结果: 按照no升序
        // [Student{no=1,name='小王', sex='男', height=103},
        // Student{no=2,name='小赵', sex='男', height=102},
        // Student{no=3,name='小明', sex='女', height=101}]
    }

    @Test
    public void treeSet1() {

        // 创建学生对象
        Student stu1 = new Student(1, "小明", "男", 101);
        Student stu2 = new Student(2, "小赵", "男", 101);
        Student stu3 = new Student(3, "小王", "女", 102);
        // 创建集合
        // 方式二: 使用构造器自带的比较器对象
        Set<Student> students = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o2.getNo() - o1.getNo();
            }
        });
        students.add(stu1);
        students.add(stu2);
        students.add(stu3);
        System.out.println(students);
        // 打印结果: 按照no降序
        // [Student{no=1,name='小王', sex='男', height=103},
        // Student{no=2,name='小赵', sex='男', height=102},
        // Student{no=3,name='小明', sex='女', height=101}]

    }

    @Test
    public void treeSet2() {

        // 创建学生对象
        Student stu1 = new Student(1, "小明", "男", 101);
        Student stu2 = new Student(2, "小赵", "男", 101);
        Student stu3 = new Student(3, "小王", "女", 102);
        // 创建集合
        // 方式二: 使用构造器自带的比较器对象
        Set<Student> students = new TreeSet<>((Student o1, Student o2) -> o2.getNo() - o1.getNo());

        students.add(stu1);
        students.add(stu2);
        students.add(stu3);
        System.out.println(students);
        // 打印结果: 按照id降序
        // [Student{no=1,name='小王', sex='男', height=103},
        // Student{no=2,name='小赵', sex='男', height=102},
        // Student{no=3,name='小明', sex='女', height=101}]

    }


    @Test
    public void set1() {
        Set mys = new HashSet<>();

        // 增加值
        mys.add(true);
        mys.add("哈哈");
        mys.add('a');
        mys.add(1);

        // 循环取值  foreach
        for (Object object : mys) {
            System.out.println(object);

        }
        //Iterator 迭代器
        Iterator it = mys.iterator();
        while(it.hasNext()) {
            Object next = it.next();
            System.out.println(next);
        }

        /**
         * 结果
         * a
         * 1
         * 哈哈
         * true
         */
    }

    @Test
    public void set2() {
        Set mys = new HashSet<>();

        // 增加值
        mys.add(true);
        mys.add("哈哈");
        mys.add('a');
        mys.add(1);
        mys.add("哈哈");

        // 循环取值
        for (Object object : mys) {
            System.out.println(object);

        }
        /**
         * 结果
         * a
         * 1
         * 哈哈
         * true
         */
        /**
         * 总结：
         *
         * 1.无序；
         *
         * 2.（对象）不能重复(eqauls)。
         *       eqauls 从 Object 继承，默认比较的地址
         *
         * 补充：和List集合的特点相反。
         *
         * 注意：不能使用for循环，Set不能单独取值，没有下标。
         */
    }

    @Test
    public void set3(){
        // 1.两个对象
        Student u1 = new Student(1, "小明", "男", 101);
        Student u2 = new Student(2, "小赵", "男", 101);

        // 找出学号比较大的学生
        if(u1.compareTo1(u2) > 0) {
            System.out.println(u1.getNo() + "\t" + u1.getName());
        }else {
            System.out.println(u2.getNo() + "\t" + u2.getName());
        }

        // 2.数组
        Student[] user = new Student[] {
            new Student(1, "小明", "男", 101),
            new Student(3, "小王", "女", 102),
            new Student(2, "小赵", "男", 101)
        };

        Arrays.sort(user);
        for (Student u : user) {
            System.out.println(u);
        }


        // 3.集合
        List<Student> listUser = new ArrayList<Student>();

        listUser.add(new Student(1,"王晴","男",178));
        listUser.add(new Student(3,"金眉","男",150));
        listUser.add(new Student(4,"周婷","男",164));
        listUser.add(new Student(5,"乐媛","女",160));
        listUser.add(new Student(2,"王欣雨","男",165));

        Collections.sort(listUser);
        for (Student student : listUser) {
            System.out.println("学号：" + student.getNo() + "\t" + "姓名：" + student.getName());
        }
        System.out.println(listUser);



    }

    //public String exportPdf(@RequestBody GwclwxsqBean gwclwxsqBean , HttpServletResponse response) throws UnsupportedEncodingException {
    //    // 1.指定解析器
    //    System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
    //            "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
    //    String filename="车辆维修审批单.pdf";
    //    String path="e:/";
    //    response.setContentType("application/pdf");
    //    response.setHeader("Content-Disposition", "attachment;fileName="
    //            + URLEncoder.encode(filename, "UTF-8"));
    //    OutputStream os = null;
    //    PdfStamper ps = null;
    //    PdfReader reader = null;
    //    try {
    //        os = response.getOutputStream();
    //        // 2 读入pdf表单
    //        reader = new PdfReader(path+ "/"+filename);
    //        // 3 根据表单生成一个新的pdf
    //        ps = new PdfStamper(reader, os);
    //        // 4 获取pdf表单
    //        AcroFields form = ps.getAcroFields();
    //        // 5给表单添加中文字体 这里采用系统字体。不设置的话，中文可能无法显示
    //        BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1",
    //                BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    //        form.addSubstitutionFont(bf);
    //        // 6查询数据================================================
    //        Map data = new HashMap();
    //        data.put("commitTime", gwclwxsqBean.getCommitTime());
    //        data.put("driver", gwclwxsqBean.getDriver());
    //        data.put("carId", gwclwxsqBean.getCarId());
    //        data.put("carType", gwclwxsqBean.getCarType());
    //        data.put("repairAddress", gwclwxsqBean.getRepairAddress());
    //        data.put("repairCost",gwclwxsqBean.getRepairCost());
    //        data.put("project", gwclwxsqBean.getProject());
    //        data.put("fwbzzxfzrYj", gwclwxsqBean.getFwbzzxfzrYj());
    //        data.put("fgldspYj", gwclwxsqBean.getFgldspYj());
    //        data.put("remarks", gwclwxsqBean.getRemarks());
    //        // 7遍历data 给pdf表单表格赋值
    //        for (String key : data.keySet()) {
    //            form.setField(key,data.get(key).toString());
    //        }
    //        ps.setFormFlattening(true);
    //        log.info("*******************PDF导出成功***********************");
    //    } catch (Exception e) {          log.error("*******************PDF导出失败***********************");
    //        e.printStackTrace();
    //    } finally {
    //        try {
    //            ps.close();
    //            reader.close();
    //            os.close();
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }
    //    return null;
    //}


    //public void sign(String src, String target, SignatureInfo signatureInfo) {
    //    InputStream inputStream = null;
    //    FileOutputStream outputStream = null;
    //    ByteArrayOutputStream result = new ByteArrayOutputStream();
    //    try {
    //        inputStream = new FileInputStream(src);
    //        ByteArrayOutputStream tempArrayOutputStream = new ByteArrayOutputStream();
    //        PdfReader reader = new PdfReader(inputStream);
    //        // 创建签章工具PdfStamper ，最后一个boolean参数是否允许被追加签名
    //        // false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
    //        // true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
    //        PdfStamper stamper = PdfStamper.createSignature(reader,
    //                tempArrayOutputStream, '\0', null, true);
    //        // 获取数字签章属性对象
    //        PdfSignatureAppearance appearance = stamper
    //                .getSignatureAppearance();
    //        appearance.setReason(signatureInfo.getReason());
    //        appearance.setLocation(signatureInfo.getLocation());
    //        // 设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样 图片大小受表单域大小影响（过小导致压缩）
    //        // 签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
    //        // 四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
    //        appearance.setVisibleSignature(new Rectangle(280, 220, 140, 600), 1, "sig1");
    //        // 读取图章图片
    //        Image image = Image.getInstance(signatureInfo.getImagePath());
    //        appearance.setSignatureGraphic(image);
    //        appearance.setCertificationLevel(signatureInfo
    //                .getCertificationLevel());
    //        // 设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
    //        appearance.setRenderingMode(signatureInfo.getRenderingMode());
    //        // 这里的itext提供了2个用于签名的接口，可以自己实现，后边着重说这个实现
    //        // 摘要算法
    //        ExternalDigest digest = new BouncyCastleDigest();
    //        // 签名算法
    //        ExternalSignature signature = new PrivateKeySignature(
    //                signatureInfo.getPk(), signatureInfo.getDigestAlgorithm(),
    //                null);
    //        // 调用itext签名方法完成pdf签章 //数字签名格式，CMS,CADE
    //        MakeSignature.signDetached(appearance, digest, signature,
    //                signatureInfo.getChain(), null, null, null, 0,
    //                MakeSignature.CryptoStandard.CADES);
    //
    //        inputStream = new ByteArrayInputStream(
    //                tempArrayOutputStream.toByteArray());
    //        // 定义输入流为生成的输出流内容，以完过成多次签章的程
    //        result = tempArrayOutputStream;
    //
    //        outputStream = new FileOutputStream(new File(target));
    //        outputStream.write(result.toByteArray());
    //        outputStream.flush();
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    } finally {
    //        try {
    //            if (null != outputStream) {
    //                outputStream.close();
    //            }
    //            if (null != inputStream) {
    //                inputStream.close();
    //            }
    //            if (null != result) {
    //                result.close();
    //            }
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //    }
    //}


}
