package com.ikang.idata.search.search.service;

import jodd.util.function.Consumers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/7/5
 */
public class CodeTest {
    public static void main(String[] args) throws IOException {

        int n = 10;
        System.out.println("第"+n+"个月兔子总数为"+fun(n));

        int m = 1;
        int n1 = 1000;
        int count = 0;
        //统计素数个数
        for(int i=m;i<n1;i++){
            if(isPrime(i)){
                count++;
                System.out.print(i+" ");
                if(count%10==0){
                    System.out.println();
                }
            }
        }
        System.out.println();
        System.out.println("在"+m+"和"+n+"之间共有"+count+"个素数");

        int m2,n2;
        try{
            m2 = Integer.parseInt(args[0]);
            n2 = Integer.parseInt(args[1]);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("输入有误");
            return;
        }
        System.out.print("请输入一串字符：");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();//将一行字符转化为字符串
        scan.close();
        count(str);

        System.out.println("请输入一行字符：");
        Scanner scan1 = new Scanner(System.in);
        String str1 = scan1.nextLine();
        scan.close();

        System.out.print("求s=a+aa+aaa+aaaa+...的值，请输入a的值：");
        Scanner scan3 = new Scanner(System.in).useDelimiter("\\s*");//以空格作为分隔符
        int a3 = scan3.nextInt();
        int n3 = scan3.nextInt();
        scan3.close();//关闭扫描器

        System.out.println(expressed(2,5)+add(2,5));
        System.out.print("请输入当前利润：");
        long profit = Long.parseLong(key_Input());
        System.out.println("应发奖金："+bonus(profit));

        int n4=0;
        for(int i=0;i<100001;i++){
            if(isCompSqrt(i+100) && isCompSqrt(i+268)){
                n4 = i;
                break;

            }
        }
        Scanner scan2 = new Scanner(System.in).useDelimiter("\\D");//匹配非数字
        System.out.print("请输入当前日期（年-月-日）:");
        int year = scan2.nextInt();
        int month = scan2.nextInt();
        int date = scan2.nextInt();
        scan.close();
        System.out.println("今天是"+year+"年的第"+analysis(year,month,date)+"天");
        double n5 = 1;
        double n6 = 1;
        double fraction = n5/n6;
        double Sn = 0;
        for(int i=0;i<20;i++){
            double t1 = n5;
            double t2 = n6;
            n5 = t1+t2;
            n6 = t1;
            fraction = n5/n6;
            Sn += fraction;
        }
        System.out.print(Sn);
        long sum = 0;
        for(int i=0;i<20;i++)
            sum += factorial(i+1);
        System.out.println(sum);
        System.out.println(fact(10));

        int n7 = 0;
        System.out.print("请输入一个5位数：");
        BufferedReader bufin = new BufferedReader(new InputStreamReader(System.in));
        try{
            n7 = Integer.parseInt(bufin.readLine());
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                bufin.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private static boolean isPrime(int n1){
        boolean flag = true;
        if(n1==1)
            flag = false;
        else{
            for(int i=2;i<=Math.sqrt(n1);i++){
                if((n1%i)==0 || n1==1){
                    flag = false;
                    break;
                }
                else
                    flag = true;
            }
        }
        return flag;
    }

    private static int fun(int n){
        if(n==1 || n==2)
            return 1;
        else
            return fun(n-1)+fun(n-2);
    }

    private static void palin(int n7){
        int m = n7;
        int[] a = new int[5];
        if(n7<10000 || n7>99999){
            System.out.println("输入的不是5位数！");
            return;
        }else{
            for(int i=0;i<5;i++){
                a[i] = n7%10;
                n7 /= 10;
            }
            if(a[0]==a[4] && a[1]==a[3])
                System.out.println(m+"是一个回文数");
            else
                System.out.println(m+"不是回文数");
        }
    }

    //递归求阶乘
    private static long fact(int n){
        if(n==1)
            return 1;
        else
            return fact(n-1)*n;
    }

    //阶乘
    private static long factorial(int n){
        int mult = 1;
        for(int i=1;i<n+1;i++)
            mult *= i;
        return mult;
    }

    //判断天数
    private static int analysis(int year, int month, int date){
        int n = 0;
        int[] month_date = new int[] {0,31,28,31,30,31,30,31,31,30,31,30};
        if((year%400)==0 || ((year%4)==0)&&((year%100)!=0))
            month_date[2] = 29;
        for(int i=0;i<month;i++)
            n += month_date[i];
        return n+date;
    }

    //判断完全平方数
    private static boolean isCompSqrt(int n4){
        boolean isComp = false;
        for(int i=1;i<Math.sqrt(n4)+1;i++){
            if(n4==Math.pow(i,2)){
                isComp = true;
                break;
            }
        }
        return isComp;
    }

    //接受从键盘输入的内容
    private static String key_Input(){
        String str = null;
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(System.in));
        try{
            str = bufIn.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                bufIn.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return str;
    }

    //计算奖金
    private static long bonus(long profit){
        long prize = 0;
        long profit_sub = profit;
        if(profit>1000000){
            profit = profit_sub-1000000;
            profit_sub = 1000000;
            prize += profit*0.01;
        }
        if(profit>600000){
            profit = profit_sub-600000;
            profit_sub = 600000;
            prize += profit*0.015;
        }
        if(profit>400000){
            profit = profit_sub-400000;
            profit_sub = 400000;
            prize += profit*0.03;
        }

        if(profit>200000){
            profit = profit_sub-200000;
            profit_sub = 200000;
            prize += prize*0.05;

        }

        if(profit>100000){
            profit = profit_sub-100000;
            profit_sub = 100000;
            prize += profit*0.075;
        }
        prize += profit_sub*0.1;
        return prize;

    }

    //求和表达式
    private static String expressed(int a3,int n3){
        StringBuffer sb = new StringBuffer();
        StringBuffer subSB = new StringBuffer();
        for(int i=1;i<n3+1;i++){
            subSB = subSB.append(a3);
            sb = sb.append(subSB);
            if(i<n3)
                sb = sb.append("+");
        }
        sb.append("=");
        return sb.toString();
    }
    //求和
    private static long add(int a3,int n3){
        long sum = 0;

        long subSUM = 0;

        for(int i=1;i<n3+1;i++){
            subSUM = subSUM*10+a3;
            sum = sum+subSUM;
        }
        return sum;
    }

    //统计输入的字符
    private static void count1(String str1){
        List<String> list = new ArrayList<String>();
        char[] array_Char = str1.toCharArray();
        for(char c:array_Char)
            list.add(String.valueOf(c));//将字符作为字符串添加到list表中
        Collections.sort(list);//排序
        for(String s:list){
            int begin = list.indexOf(s);
            int end = list.lastIndexOf(s);
            //索引结束统计字符数
            if(list.get(end)==s)
                System.out.println("字符‘"+s+"’有"+(end-begin+1)+"个");
        }
    }

    private static void count(String str){
        String E1 = "[\u4e00-\u9fa5]";//汉字
        String E2 = "[a-zA-Z]";
        String E3 = "[0-9]";
        String E4 = "\\s";//空格
        int countChinese = 0;
        int countLetter = 0;
        int countNumber = 0;
        int countSpace = 0;
        int countOther = 0;
        char[] array_Char = str.toCharArray();//将字符串转化为字符数组
        String[] array_String = new String[array_Char.length];//汉字只能作为字符串处理
        for(int i=0;i<array_Char.length;i++)
            array_String[i] = String.valueOf(array_Char[i]);
        //遍历字符串数组中的元素
        for(String s:array_String){
            if(s.matches(E1))
                countChinese++;
            else if(s.matches(E2))
                countLetter++;
            else if(s.matches(E3))
                countNumber++;
            else if(s.matches(E4))
                countSpace++;
            else
                countOther++;
        }

        System.out.println("输入的汉字个数："+countChinese);
        System.out.println("输入的字母个数："+countLetter);
        System.out.println("输入的数字个数："+countNumber);
        System.out.println("输入的空格个数："+countSpace);
        System.out.println("输入的其它字符个数："+countSpace);
    }

    //求最大公约数和最小公倍数
    private static void max_min(int m2, int n2){
        int temp2 = 1;
        int yshu = 1;
        int bshu = m2*n2;
        if(n2<m2){
            temp2 = n2;
            n2 = m2;
            m2 = temp2;
        }
        while(2!=0){
            temp2 = n2%m2;
            n2 = m2;
            m2 = temp2;
        }
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
