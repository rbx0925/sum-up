package com.ikang.idata.search.search;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/3/18
 */
public class MathTest {
    public static void main(String[] args) {
        final int SIZE = 64;
        final int BYTES = SIZE / Byte.SIZE;
        int i= 10;
        int one = bitCount(i);
        int i1 = numberOfTrailingZeros(i);
        int i2 = numberOfLeadingZeros(i);
        long l = highestOneBit(i);

        System.out.println(one);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(l);

    }

    public static int bitCount(long i) {
        // HD, Figure 5-14
        i = i - ((i >>> 1) & 0x5555555555555555L);
        i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
        i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        i = i + (i >>> 32);
        return (int)i & 0x7f;

    }

    public static int numberOfTrailingZeros(long i) {
        int x, y;
        if (i == 0) return 64;
        int n = 63;
        y = (int) i;
        if (y != 0) {
            n = n - 32;
            x = y;
        } else x = (int) (i >>> 32);
        y = x << 16;
        if (y != 0) {
            n = n - 16;
            x = y;
        }
        y = x << 8;
        if (y != 0) {
            n = n - 8;
            x = y;
        }
        y = x << 4;
        if (y != 0) {
            n = n - 4;
            x = y;
        }
        y = x << 2;
        if (y != 0) {
            n = n - 2;
            x = y;
        }
        return n - ((x << 1) >>> 31);
    }

    public static int numberOfLeadingZeros(long i) {
        // HD, Figure 5-6
        if (i == 0)
            return 64;
        int n = 1;
        int x = (int)(i >>> 32);
        if (x == 0) { n += 32; x = (int)i; }
        if (x >>> 16 == 0) { n += 16; x <<= 16; }
        if (x >>> 24 == 0) { n +=  8; x <<=  8; }
        if (x >>> 28 == 0) { n +=  4; x <<=  4; }
        if (x >>> 30 == 0) { n +=  2; x <<=  2; }
        n -= x >>> 31;
        return n;
    }

    public static long highestOneBit(long i) {
        // HD, Figure 3-1
        i |= (i >>  1);
        i |= (i >>  2);
        i |= (i >>  4);
        i |= (i >>  8);
        i |= (i >> 16);
        i |= (i >> 32);
        return i - (i >>> 1);
    }


    @Test
    public void String() {

        SimpleDateFormat sdf = new SimpleDateFormat("2015-12-01 12:25:45");

        try {


            Date date = sdf.parse("2015-12-01");

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(date);

            System.out.println(calendar.get(Calendar.YEAR));

            System.out.println(calendar.get(Calendar.MONTH) + 1);

            System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        } catch (ParseException e) {

            e.printStackTrace();
        }


    }
    
    
    
    @Test
    public void test1() {

        try {

            String times = "2019-3-22";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date date = sdf.parse(times);

            System.out.println("这个时间是" +date);

           // 获取String字符串中的年

            SimpleDateFormat y = new SimpleDateFormat("yyyy");

            System.out.println(y.format(date));

           // 获取String字符串中的月

            SimpleDateFormat m = new SimpleDateFormat("MM");

            System.out.println(m.format(date));

           // 获取String字符串中的日

            SimpleDateFormat d = new SimpleDateFormat("dd");

            System.out.println(d.format(date));

            Calendar c =Calendar.getInstance();
            System.out.println(sdf.format(c.getTime()));

            System.out.println(y.format(date)+"年"+m.format(date)+"月"+d.format(date) +"日");


        } catch (ParseException e) {

         // TODO Auto-generated catch block

            e.printStackTrace();

        }

        /*

         * Date date = new Date(); SimpleDateFormat dateFormat= new

         * SimpleDateFormat("yyyy-MM-dd"); System.out.println(dateFormat.format(date));

         */

    }


    @Test
    public void test2() {




    }


}
