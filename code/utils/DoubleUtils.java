package com.ikang.idata.common.utils;

import cn.hutool.core.util.NumberUtil;
import com.ikang.idata.common.entity.vo.AreaWorkTableCheckVO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static com.ikang.idata.common.consts.MagicConst.*;

/**
 * DoubleUtil.
 *
 * @author zj.
 *         Created on 2018/9/19 0019.
 */
public class DoubleUtils {
    // 默认除法运算精度
    private static final Integer DEF_DIV_SCALE = 6;



    /**
     * 提供精确的加法运算（保留2位小数）。
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和.
     */
    public static Double add(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        BigDecimal sum = b1.add(b2);
        return sum.setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
    * @return 两个参数的差
     */
    public static double sub(Double value1, Double value2) {
        return sub(value1, value2, DEF_DIV_SCALE);
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @param scale 保留位数
     * @return 两个参数的差
     */
    public static double sub(Double value1, Double value2, Integer scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }



    public static BigDecimal subtract4(String value1, String value2) {
        value1 = StringUtil.isEmpty(value1) || "null".equals(value1) || "0.0".equals(value1) ||  "0.00".equals(value1) ? "0" : value1;
        value2 = StringUtil.isEmpty(value2) || "null".equals(value2) || "0.0".equals(value2)  || "0.00".equals(value2) ? "0" : value2;
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.subtract(b2).setScale(4, RoundingMode.HALF_UP);
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @param scale 保留位数
     * @return 两个参数的差
     */

    public static BigDecimal subtract(String value1, String value2, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        value1 = StringUtil.isEmpty(value1) || "null".equals(value1) || "0.0".equals(value1) ||  "0.00".equals(value1) ? "0" : value1;
        value2 = StringUtil.isEmpty(value2) || "null".equals(value2) || "0.0".equals(value2)  || "0.00".equals(value2) ? "0" : value2;
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.subtract(b2).setScale(scale, RoundingMode.HALF_UP);
    }


    public static BigDecimal add(String value1, String value2) {
        value1 = StringUtil.isEmpty(value1) || "null".equals(value1) || "0.0".equals(value1) ||  "0.00".equals(value1) ? "0" : value1;
        value2 = StringUtil.isEmpty(value2) || "null".equals(value2) || "0.0".equals(value2)  || "0.00".equals(value2) ? "0" : value2;
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        BigDecimal sum = b1.add(b2);
        return sum.setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal subtract( BigDecimal b1, BigDecimal b2, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        if (null == b1) {
            b1 = new BigDecimal(0);
        }
        if (null == b2) {
            b2 = new BigDecimal(0);
        }
        return b1.subtract(b2).setScale(scale, RoundingMode.HALF_UP);
    }


    public static String sub(String value1, String value2, Integer scale) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.subtract(b2).setScale(scale, RoundingMode.HALF_UP).toString();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static Double mul(Double value1, Double value2) {
        return mul(value1, value2, DEF_DIV_SCALE);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @param scale 保留位数
     * @return 两个参数的积
     */
    public static Double mul(Double value1, Double value2, Integer scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.multiply(b2).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后10位，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 两个参数的商
     */
    public static Double divide(Double dividend, Double divisor) {
        return divide(dividend, divisor, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时， 精确到小数点以后10位，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 两个参数的商
     */
    public static Double divide(Integer dividend, Integer divisor) {
        return divide(dividend, divisor, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static Double divide(Integer dividend, Integer divisor, Integer scale) {
        Integer temp = 0;
        if (null == dividend || null == divisor || temp.equals(divisor)) {
            return 0d;
        }
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }
    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return 两个参数的商
     */
    public static BigDecimal divide4(String dividend, String divisor) {
        dividend = StringUtil.isEmpty(dividend) || "null".equals(dividend) || "0.0".equals(dividend) ||  "0.00".equals(dividend) ? "0" : dividend;
        divisor = StringUtil.isEmpty(divisor) || "null".equals(divisor) || "0.0".equals(divisor)  || "0.00".equals(divisor) ? "0" : divisor;

        if (STR_0.equals(divisor) && !STR_0.equals(dividend)) {
            return new BigDecimal(1);
        }
        if (STR_0.equals(divisor) && NumberUtil.isNumber(dividend)) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(dividend);
        BigDecimal b2 = new BigDecimal(divisor);
        return b1.divide(b2, 4, RoundingMode.HALF_UP);
    }


    /**
     * 方法描述:
     * 当期：不为0，同期为0，+100%
     * 当期：0，同期不为0，-100%
     * 当期：0，同期0，持平增长0%
     * @param dividend
     * @param divisor 
     * @return  java.math.BigDecimal
     * @author  wenyue.gao@ikang.com
     * @date    2022/3/25 11:14
     */
    public static BigDecimal divide4Rate(BigDecimal dividend, BigDecimal divisor) {
       //  当期：不为0，同期为0，+100%
        if ((null != dividend || INT_0 != dividend.intValue()) && (null == divisor || INT_0 == divisor.intValue())){
            return new BigDecimal(1);
        }
        // 当期：0，同期不为0，-100%
        if ((null == dividend || INT_0 == dividend.intValue()) && (null != divisor || INT_0 != divisor.intValue())){
            return new BigDecimal(-1);
        }

        // 0，同期0，持平增长0%
        if ((null == dividend || INT_0 == dividend.intValue()) && (null == divisor || INT_0 == divisor.intValue())){
            return new BigDecimal(0);
        }
        return dividend.divide(divisor, 4, RoundingMode.HALF_UP);
    }



    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static Double divide(Double dividend, Double divisor, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static BigDecimal divide(String dividend, String divisor, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        dividend = StringUtil.isEmpty(dividend) || "null".equals(dividend) || "0.0".equals(dividend)  ||  "0.00".equals(dividend) ? "0" : dividend;
        divisor = StringUtil.isEmpty(divisor) || "null".equals(divisor) || "0.0".equals(divisor)  ||  "0.00".equals(divisor) ? "0" : divisor;
        if (STR_0.equals(divisor) && NumberUtil.isNumber(dividend)) {
            return null;
        }
        BigDecimal b1 = new BigDecimal(dividend);
        BigDecimal b2 = new BigDecimal(divisor);
        return b1.divide(b2, scale, RoundingMode.HALF_UP);
    }


    /**
     * 提供指定数值的（精确）小数位四舍五入处理。
     *
     * @param value 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double value,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(value));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one,scale, RoundingMode.HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        AreaWorkTableCheckVO checkVO = new AreaWorkTableCheckVO();
        checkVO.setCheckIncome(new BigDecimal(1.222));
        System.out.println(checkVO.getCheckIncome());

        BigDecimal bigDecimal = divide4("111", "0");
        System.out.println(bigDecimal);
    }
}
