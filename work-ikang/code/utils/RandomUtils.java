package com.ikang.idata.common.utils;

import java.util.Random;

/**
 * RandomUtils
 *
 * @author jiangfeng.yan@ikang.com
 * @date 2019-05-22 下午 1:48
 */
public class RandomUtils {
    //  定义所有的字符组成的串
    public static final String ALLLETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALLNUMBER = "0123456789";

    /**
     * 产生长度为length的随机字符串（包括字母和数字）
     * @param length
     * @return
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            if(i%2 ==0){
                sb.append(ALLNUMBER.charAt(random.nextInt(ALLNUMBER.length())));
            }else{
                sb.append(ALLLETTER.charAt(random.nextInt(ALLLETTER.length())));
            }

        }
        return sb.toString();
    }


    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    /**
     * 生成随机手机号
     *
     * @author haigang.jia@ikang.com
     * @date 2019-07-08 上午 10:38

     * @return java.lang.String
     */
    public static String getTel() {
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;
    }
}
