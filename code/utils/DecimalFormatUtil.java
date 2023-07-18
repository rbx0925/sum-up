package com.ikang.idata.common.utils;

import com.ikang.idata.common.entity.vo.AreaWorkTableCheckVO;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @version 1.0
 * @date 2021/12/22 19:20
 */
public class DecimalFormatUtil {
    public static final DecimalFormat PERCENT_SIGN_FORMAT = new DecimalFormat("0.00%");
    public static final DecimalFormat POSITIVE_INTEGER_FORMAT = new DecimalFormat(",###,##0");
    public static final DecimalFormat ONE_DECIMAL_PLACE_FORMAT = new DecimalFormat(",###,##0.0");
    public static final DecimalFormat TWO_DECIMAL_PLACE_FORMAT = new DecimalFormat(",###,##0.00");
    public static final DecimalFormat THREE_DECIMAL_PLACE_FORMAT = new DecimalFormat(",###,##0.000");


    /**
     * 方法描述:不要精度
     *
     * @param num
     * @return  java.lang.String
     * @author  wenyue.gao@ikang.com
     * @date    2022/2/10 17:09
     */
    public static String format0(Object num){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(num));
        return POSITIVE_INTEGER_FORMAT.format(bigDecimal);
    }
    /**
     * 方法描述:保留两位
     *
     * @param num
     * @return  java.lang.String
     * @author  wenyue.gao@ikang.com
     * @date    2022/2/10 17:09
     */
    public static String format2(Object num){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(num));
        return TWO_DECIMAL_PLACE_FORMAT.format(bigDecimal);
    }


    /**
     * 方法描述:保留两位
     *
     * @param num
     * @return  java.lang.String
     * @author  wenyue.gao@ikang.com
     * @date    2022/2/10 17:09
     */
    public static BigDecimal format2BigDecimal(Object num){
        if (null == num) {
            return null;
        }
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(num));
        DecimalFormat decimalFormat = new DecimalFormat("00.00");
        String format = decimalFormat.format(bigDecimal);
        return new BigDecimal(format);
    }


    public static void main(String[] args) {
        BigDecimal bigDecimal1 = format2BigDecimal(1.45454545454);
        System.out.println(bigDecimal1);
        AreaWorkTableCheckVO checkVO = new AreaWorkTableCheckVO();
        checkVO.setCheckIncome(new BigDecimal(10000.222));
        System.out.println(checkVO.getCheckIncome());
    }
}
