package com.ikang.idata.search.search;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.ikang.idata.search.search.util.CalculateUtil;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/3/18
 */

@Data
public class BigDecimalTest implements Serializable {

    //BigDecimal  转 String
    @Test
    public  void  BigDecimalTest(){
        String ces  = "123";
        BigDecimal bd = new BigDecimal(ces);
        System.out.println("BigDecimal:"+bd);
    }

    //String  转 BigDecimal
    //Java中String转BigDecimal时注意不要有字母。
    @Test
    public void StringTest(){
        BigDecimal qp = new BigDecimal(456);
        String str = qp.toString();
        System.out.println("String:"+str);
    }

    /**
     * 构造器描述
     * BigDecimal(int)       创建一个具有参数所指定整数值的对象。
     * BigDecimal(double) 创建一个具有参数所指定双精度值的对象。 //不推荐使用
     * BigDecimal(long)    创建一个具有参数所指定长整数值的对象。
     * BigDecimal(String) 创建一个具有参数所指定以字符串表示的数值的对象。//推荐使用
     * 方法描述
     * add(BigDecimal)        BigDecimal对象中的值相加，然后返回这个对象。
     * subtract(BigDecimal) BigDecimal对象中的值相减，然后返回这个对象。
     * multiply(BigDecimal)  BigDecimal对象中的值相乘，然后返回这个对象。
     * divide(BigDecimal)     BigDecimal对象中的值相除，然后返回这个对象。
     * toString()                将BigDecimal对象的数值转换成字符串。
     * doubleValue()          将BigDecimal对象中的值以双精度数返回。
     * floatValue()             将BigDecimal对象中的值以单精度数返回。
     * longValue()             将BigDecimal对象中的值以长整数返回。
     * intValue()               将BigDecimal对象中的值以整数返回。
     */


    @Test
    public void a(){

        //BigDecimal(double) 创建一个具有参数所指定双精度值的对象。 //不推荐使用
        //1、参数类型为double的构造方法的结果有一定的不可预知性。有人可能认为在Java中写入newBigDecimal(0.1)所创建的BigDecimal正好等于 0.1（非标度值 1，其标度为 1），但是它实际上等于0.1000000000000000055511151231257827021181583404541015625。这是因为0.1无法准确地表示为 double（或者说对于该情况，不能表示为任何有限长度的二进制小数）。这样，传入到构造方法的值不会正好等于 0.1（虽然表面上等于该值）。
        BigDecimal inStr = new BigDecimal("99");
        BigDecimal doubleStr = new BigDecimal(1.1111111);
        System.out.println("inStr:"+inStr);
        System.out.println("doubleStr:"+doubleStr );
    }

    @Test
    public void aTest(){

        //BigDecimal(double) 创建一个具有参数所指定双精度值的对象。 //不推荐使用
        //1、参数类型为double的构造方法的结果有一定的不可预知性。有人可能认为在Java中写入newBigDecimal(0.1)所创建的BigDecimal正好等于 0.1（非标度值 1，其标度为 1），但是它实际上等于0.1000000000000000055511151231257827021181583404541015625。这是因为0.1无法准确地表示为 double（或者说对于该情况，不能表示为任何有限长度的二进制小数）。这样，传入到构造方法的值不会正好等于 0.1（虽然表面上等于该值）。
        BigDecimal inStr = new BigDecimal("99");
        //修改方法  请使用Double.toString(double)转成String，然后使用String构造方法，或使用BigDecimal的静态方法valueOf
        BigDecimal doubleStr = new BigDecimal(Double.toString(1.1111111));
        System.out.println("inStr:"+inStr);
        System.out.println("doubleStr:"+doubleStr );
    }

    @Test
    public void b(){

        BigDecimal one = new BigDecimal("99");
        BigDecimal two = new BigDecimal("88");
        System.out.println("a+b= "+ one.add(two));
        System.out.println("a-b= "+ one.subtract(two));
        System.out.println("a*b= "+ one.multiply(two));
        System.out.println("a/b= "+ one.divide(two));

        double d = 4.44444;
        float  f = 888.88F;

        BigDecimal oneD = new BigDecimal(Double.toString(d));
        BigDecimal twoF = new BigDecimal(Float.toHexString(f));

        System.out.println("a*b= "+ oneD.multiply(twoF));
        System.out.println("a/b= "+ oneD.divide(twoF ,2,BigDecimal.ROUND_HALF_UP));//四舍五入  保留两位小数

    }

    //ROUND_CEILING  向正无穷舍入
    //ROUND_DOWN  向零无穷舍入
    //ROUND_FLOOR  向负无穷舍入
    //ROUND_HALF_DOWN  1.55--->1.5
    //ROUND_HALF_UP   1.55--->1.6
    //ROUND_UNNECESSARY  精准计算
    @Test
    public void bTest(){

        BigDecimal inStr = new BigDecimal("99");
        BigDecimal doubleStr = new BigDecimal(Double.toString(9.99999));
        System.out.println("inStr:"+inStr.divide(doubleStr,2,BigDecimal.ROUND_CEILING));
        System.out.println("inStr:"+inStr.divide(doubleStr,2,BigDecimal.ROUND_HALF_UP));
        System.out.println("inStr:"+inStr.divide(doubleStr,2,BigDecimal.ROUND_DOWN));
        System.out.println("inStr:"+inStr.divide(doubleStr,2,BigDecimal.ROUND_HALF_DOWN));
        System.out.println("inStr:"+inStr.divide(doubleStr,2,BigDecimal.ROUND_FLOOR));
        System.out.println("inStr:"+inStr.divide(doubleStr,2,BigDecimal.ROUND_UNNECESSARY));
        System.out.println("doubleStr:"+doubleStr );
    }


    @Test
    public void testNegativeNumber() {
        BigDecimal decimal = new BigDecimal("-1");
        System.out.println(decimal);
    }


    @Test
    public void testToString() {
        BigDecimal bigDecimal = new BigDecimal("-1");
        System.out.println(bigDecimal.toString());
    }

    @Test
    public void testHashBasedTable() {
        HashBasedTable<String, String, String> table = HashBasedTable.create();
        table.put("行1", "列1", "第一排第一列");
        table.put("行1", "列2", "第一排第二列");
        table.put("行1", "列3", "第一排第三列");
        table.put("行1", "列4", "第一排第四列");
        table.put("行1", "列5", "第一排第五列");
        table.put("行2", "列1", "第二排第一列");
        table.put("行2", "列2", "第二排第二列");
        table.put("行2", "列3", "第二排第三列");
        table.put("行2", "列4", "第二排第四列");
        table.put("行2", "列5", "第二排第五列");

        Map<String, String> column = table.column("列1");
        System.out.println(column);

        Map<String, String> row = table.row("行1");
        System.out.println(row);
        System.out.println(table);

        Table<String, String, String> transpose = Tables.transpose(table);
        System.out.println(transpose);
    }


    @Test
    public void testString() {
        System.out.println("123456789.".length());
        System.out.println("张三".length());
        System.out.println("zhangsan".length());
        System.out.println("\"zhangsan\".length() = " + "zhangsan".length());
    }


    @Test
    public void testCompareTo() {
        BigDecimal bigDecimal = new BigDecimal("1.0000");
        BigDecimal decimal = new BigDecimal("1");
        System.out.println(bigDecimal.compareTo(decimal));
        System.out.println(decimal);
        System.out.println(bigDecimal);

    }


    @Test
    public void testParseNumber() {
        System.out.println(NumberUtil.parseNumber("1979988100000.5555"));
        IntStream.range(0,10).parallel().forEach(value -> {
            BigDecimal bigDecimal = CalculateUtil.calculateGrowthRate4(new BigDecimal("1979988100000.54"), new BigDecimal("14999500000000.5454545"));
            System.out.println(JSONObject.toJSONString(bigDecimal));
            System.out.println("========================");
        });
        IntStream.range(0,10).parallel().forEach(value -> System.out.println(CalculateUtil.calculateGrowthRate4(new BigDecimal("19799881"),new BigDecimal("14999500"))));
    }


    @Test
    public void testNegativeNumber1() {
        BigDecimal decimal = new BigDecimal("19799881");
        BigDecimal bigDecimal = new BigDecimal("14999500");
        BigDecimal divide = decimal.subtract(bigDecimal).divide(bigDecimal, 4, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);
    }


    @Test
    public void testOn() {
        BigDecimal decimal = new BigDecimal("1.49995E7");
        System.out.println(decimal.stripTrailingZeros());
        System.out.println(decimal.stripTrailingZeros().toPlainString());
        System.out.println(new BigDecimal(decimal.stripTrailingZeros().toPlainString()));
        System.out.println("======================================");
        BigDecimal decimal1 = new BigDecimal("19799881");
        System.out.println(decimal1.stripTrailingZeros());
        System.out.println(decimal1.stripTrailingZeros().toPlainString());

        System.out.println( "==============================================================");
        System.out.println(CalculateUtil.div("1.49995E7", "2"));

        System.out.println( decimal.setScale(2,BigDecimal.ROUND_HALF_UP));


        System.out.println("================================================");


        System.out.println(new BigDecimal("199999.22000").stripTrailingZeros());
        System.out.println(new BigDecimal("199900").stripTrailingZeros());
        System.out.println(new BigDecimal("199900").stripTrailingZeros().toPlainString());
        System.out.println(new BigDecimal("1.999E+5").toPlainString());


        System.out.println(new BigDecimal("-1.999E+5").toPlainString());

    }


    public static int cut(int []p,int n) {
        if (n == 0)
            return 0;
        int q = Integer.MIN_VALUE;
        //尝试切割的次数 不能超过 总长度n
        for (int i = 1; i <= n; i++) {
            //n-i 为剩余长度
            q = Math.max(q, p[i - 1] + cut(p, n - i));//q的结果每次递归都是上一个的最大值,也就是最优的价格
        }
        return q;
    }
    public static void main(String[] args) {
        int[] ints = new int[]{1,5,8,9,10,17,17,20,24,30};
        System.out.println(cut(ints, 4));
    }


    @Test
    void testDiv1() {
        assertThat(CalculateUtil.div("1.999E+5", "1.999")).isEqualTo(new BigDecimal("100000.0000000000"));
    }

    @Test
    void testDiv2() {
        assertThat(CalculateUtil.div(new BigDecimal("0.00"), new BigDecimal("0.00"))).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testDiv3() {
        assertThat(CalculateUtil.div(new BigDecimal("0.00"), new BigDecimal("0.00"), 0))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testDiv4() {
        assertThat(CalculateUtil.div("v1", "v1", 0)).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testCalculateGrowthRate4() {
        assertThat(CalculateUtil.calculateGrowthRate4(new BigDecimal("0.00"), new BigDecimal("0.00")))
                .isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testRound1() {
        assertThat(CalculateUtil.round("numStr", 0)).isEqualTo("result");
    }

    @Test
    void testRound2() {
        assertThat(CalculateUtil.round(new BigDecimal("0.00"), 0)).isEqualTo(new BigDecimal("0.00"));
    }




}
