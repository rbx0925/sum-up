package com.ikang.idata.common.utils;

import cn.hutool.core.util.NumberUtil;
import com.ikang.idata.common.consts.MagicConst;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalculateUtil {

    public static final String ZERO_POINT_ZERO = "0.0";
    public static final String ZERO_POINT_ZERO_ZERO = "0.00";
    public static final String ZERO_POINT_ZERO_ZERO_ZERO_ZERO = "0.0000";

    public static final String ZERO = "0";

    /**
     * @Description: 除法运算
     */
    public static BigDecimal synchronousCalculation(String v1, String v2) {
        v1 = StringUtil.isEmpty(v1) || "null".equals(v1) ? "0" : v1;
        v2 = StringUtil.isEmpty(v2) || "null".equals(v2) ? "0" : v2;
        v1 = StringUtil.isEmpty(v1) ? "0.0" : v1;
        v2 = StringUtil.isEmpty(v2) ? "0.0" : v2;
        v1 = StringUtil.isEmpty(v1) ? "0.00" : v1;
        v2 = StringUtil.isEmpty(v2) ? "0.00" : v2;
        v1 = StringUtil.isEmpty(v1) ? "0.0000" : v1;
        v2 = StringUtil.isEmpty(v2) ? "0.0000" : v2;
        if (v1.equals(v2)) {
            return new BigDecimal("0");
        }
        if (ZERO.equals(v2) && NumberUtil.isNumber(v1)) {
            return new BigDecimal("1");
        }
        if(ZERO_POINT_ZERO.equals(v2) && NumberUtil.isNumber(v1)){
            return new BigDecimal("1");
        }
        if(ZERO_POINT_ZERO_ZERO.equals(v2) && NumberUtil.isNumber(v1)){
            return new BigDecimal("1");
        }
        if(ZERO_POINT_ZERO_ZERO_ZERO_ZERO.equals(v2) && NumberUtil.isNumber(v1)){
            return new BigDecimal("1");
        }
        if (ZERO.equals(v1) && NumberUtil.isNumber(v2)) {
            return new BigDecimal("-1");
        }
        if (ZERO_POINT_ZERO.equals(v1) && NumberUtil.isNumber(v2)) {
            return new BigDecimal("-1");
        }
        if (ZERO_POINT_ZERO_ZERO.equals(v1) && NumberUtil.isNumber(v2)) {
            return new BigDecimal("-1");
        }
        if (ZERO_POINT_ZERO_ZERO_ZERO_ZERO.equals(v1) && NumberUtil.isNumber(v2)) {
            return new BigDecimal("-1");
        }
        return NumberUtil.div(NumberUtil.sub(v1, v2), new BigDecimal(v2)).setScale(4, RoundingMode.HALF_UP);
    }

    /**
     * @description: 添加千分位
     *
     * @param value value值
     * @return java.lang.String
     * @auther yanan.mu-ext
     * @date 2022-02-23 下午2:37
     */
    public static String getComma(String value) {
        String[] split = value.split("\\.");
        StringBuilder sb = new StringBuilder(split[0]);
        for (int i = split[0].length() - MagicConst.INTEGER_3; i > 0; i -= MagicConst.INTEGER_3) {
            sb.insert(i, ",");
        }
        split[0] = sb.toString();
        if (split.length > 1){
            return split[0] + "." + split[1];
        } else {
            return split[0];
        }
    }

    /**
     * 如果是没有进制,保留整数
     * 如果有进制,保留两位小数
     * @param value **
     * @param unit **
     * @return String[]
     */
    public static String[] getNumberDeal(String value ,String unit) {
        if (StringUtils.isBlank(value)) {
            value = "0";
        }
        DecimalFormat formater;
        BigDecimal bigDecimal = new BigDecimal(value);
        String[] result = new String[2];
        // 转换为亿万元（除以100000000）
        BigDecimal decimal = bigDecimal.divide(new BigDecimal("100000000"), 2, RoundingMode.HALF_UP);
        if (decimal.compareTo(BigDecimal.ONE) < 0) {
            // 转换为万元（除以10000）
            decimal = bigDecimal.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);
            if (decimal.compareTo(BigDecimal.ONE) < 0) {
                decimal = bigDecimal;
                result[1] = unit;
                // 保留整数
                formater = new DecimalFormat("#");
            } else {
                result[1] = "万" + unit;
                // 保留两位小数
                formater = new DecimalFormat("0.00");
            }
        } else {
            // 保留两位小数
            formater = new DecimalFormat("0.00");
            result[1] = "亿" + unit;
        }
        // 格式化完成之后得出结果
        String rs = formater.format(decimal);
        result[0] = getComma(rs);
        return result;
    }

    /**
     * 元转万元、亿元且保留两位小数并四舍五入
     * 元-万元-亿元 进位显示
     * eg: 1229.77元        ->    1229.77 元
     * eg: 21229.77元       ->       2.12 万元
     * eg: 33232133.11元    ->    3323.21 万元
     * eg: 133232133.11元   ->       1.33 亿元
     */
    public static String[] getNumberWanTwo(String value ,String unit) {
        if (StringUtils.isBlank(value)) {
            value = "0";
        }
        BigDecimal bigDecimal = new BigDecimal(value);
        String[] result = new String[2];
        // 转换为亿万元（除以100000000）
        BigDecimal decimal = bigDecimal.divide(new BigDecimal("100000000"), 2, RoundingMode.HALF_UP);
        if (decimal.compareTo(BigDecimal.ONE) < 0) {
            // 转换为万元（除以10000）
            decimal = bigDecimal.divide(new BigDecimal("10000"), 2, RoundingMode.HALF_UP);
            if (decimal.compareTo(BigDecimal.ONE) < 0) {
                decimal = bigDecimal;
                result[1] = unit;
            } else {
                result[1] = "万" + unit;
            }
        } else {
            result[1] = "亿" + unit;
        }
        // 保留两位小数
        DecimalFormat formater = new DecimalFormat("0.00");
        // 格式化完成之后得出结果
        String rs = formater.format(decimal);
        result[0] = getComma(rs);
        return result;
    }
}
