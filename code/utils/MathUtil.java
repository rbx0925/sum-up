package com.ikang.idata.common.utils;

import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import static com.ikang.idata.common.consts.MagicConst.STR_0;

/**
 * @author <a href="yanan.mu-ext@ikang.com">yanan.mu-ext</a>
 * @date 22/3/2022
 */
@Slf4j
public class MathUtil {
    /**
     * @description: 除法计算 增长率=（当期数据-同期数据）/同期数据
     *
     * @param v1 v1
     * @param v2 v2
     * @return java.math.BigDecimal
     * @auther yanan.mu-ext
     * @date 2022-03-10 下午3:28
     */
    public static BigDecimal calculation(String v1, String v2,  Integer scale){
        v1 = StringUtil.isEmpty(v1) || "null".equals(v1) || "0.0".equals(v1)  ? "0" : v1;
        v2 = StringUtil.isEmpty(v2) || "null".equals(v2) || "0.0".equals(v2)  ? "0" : v2;
        if (STR_0.equals(v2) && STR_0.equals(v1)) {
            return new BigDecimal("0");
        }
        if (STR_0.equals(v2) && NumberUtil.isNumber(v1)) {
            return new BigDecimal("1");
        }
        String sub = DoubleUtils.sub(v1, v2, scale);

        return DoubleUtils.divide(sub,v2,scale);
    }
    
    /**
     * @description: 除法运算
     * 
     * @param v1 v1
     * @param v2 v2
     * @return java.math.BigDecimal
     * @auther yanan.mu-ext
     * @date 2022-03-22 下午7:49
     */
    public static BigDecimal div(String v1, String v2){
        v1 = StringUtil.isEmpty(v1) || "null".equals(v1) || "0.0".equals(v1)  ? "0" : v1;
        v2 = StringUtil.isEmpty(v2) || "null".equals(v2) || "0.0".equals(v2)  ? "0" : v2;
        if (STR_0.equals(v2) && NumberUtil.isNumber(v1)) {
            return new BigDecimal(" ");
        }
        return NumberUtil.div(v1,v2);
    }
    
    
    public static void main(String[] args) {
        //增长率=（当期数据-同期数据）/ 同期数据
//        System.out.println(calculation("1", "0"));
//        System.out.println(calculation("2", "0"));
//        System.out.println(calculation("10", "15"));
//        System.out.println(calculation("15", "10"));
//        System.out.println(calculation("0", "1"));
//
//        System.out.println("=================");
//        System.out.println(NumberUtil.add("1", "2"));

//        System.out.println("======================");
//        System.out.println(div("2865.0999755859375", "0.0"));
//        System.out.println(div("0", "2865.0999755859375"));

//        System.out.println(DoubleUtils.divide("2865.0999755859375", "0.0", 4));
//        System.out.println(DoubleUtils.divide("0.0","2865.0999755859375", 4));
//        System.out.println(calculation("2865.0999755859375", "0.0", 4));
//        System.out.println(calculation("0.0","2865.0999755859375",  4));
//        System.out.println(calculation("10","0.0",  4));
//        System.out.println(calculation("0.0","10",  4));
//        System.out.println(calculation("0.0","0.0",  4));
//
        System.out.println(calculation("2865.0999755859375","0.0",  4));
//        System.out.println(calculation("0.0","2865.0999755859375",  4));
//        System.out.println(calculation("2865.0999755859375","2865.0999755859375",  4));

//        System.out.println(DoubleUtils.divide(-2865.0999755859375, 2865.0999755859375));
//        
        System.out.println(DoubleUtils.divide("0.0", "0.0",4));
//        System.out.println((String) null);
    
//        System.out.println(DoubleUtils.divide("2865.0999755859375", "10.0",4));
    }
    
}
