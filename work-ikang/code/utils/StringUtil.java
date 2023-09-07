package com.ikang.idata.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * StringUtil
 *
 * @author jiangfeng.yan@ikang.com
 * @date 2019-07-01 下午 3:25
 */
@Slf4j
public class StringUtil {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "只";
        System.out.println(getWordCount(str));
        System.out.println(getWordCountRegex(str));
        System.out.println(getWordCountCode(str, "GBK"));
        System.out.println(getWordCountCode(str, "UTF-8"));

        String str2 = "我是谁的呀我是谁的呀我是谁的呀我是谁的呀我是谁的呀我是谁的呀我是谁的呀我是谁的呀我是谁的呀我是谁的呀.";
        log.info("长度{}", getWordCount(str2));
    }

    /**
     * 方法描述:手机号脱敏
     *
     * @param mobile 手机号
     * @return  java.lang.String
     * @author  wenyue.gao@ikang.com
     * @date    2022/1/28 11:31
     */
    public static String mobileEncrypt(String mobile){
        if (StringUtil.isEmpty(mobile)) {
            return null;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 方法描述:手机号脱敏
     *
     * @param cardNum 卡号
     * @return  java.lang.String
     * @author  wenyue.gao@ikang.com
     * @date    2022/1/28 11:31
     */
    public static String cardNumEncrypt(String cardNum){
        if (StringUtil.isEmpty(cardNum)) {
            return null;
        }
        return cardNum.replaceAll("(?<=\\d{4})\\d+(?=\\d{4})", "********");
    }


    /**
     * 方法描述:手机号脱敏
     *
     * @param userName 姓名
     * @return  java.lang.String
     * @author  wenyue.gao@ikang.com
     * @date    2022/1/28 11:31
     */
    public static String userNameEncrypt(String userName){
        if (StringUtil.isEmpty(userName)) {
            return null;
        }
        return userName.replaceAll("((?<=.).)", "*");
    }

    /**
     * 功能描述 由于Java是基于Unicode编码的，因此，一个汉字的长度为1，而不是2。
     * 但有时需要以字节单位获得字符串的长度。例如，“123abc长城”按字节长度计算是10，而按Unicode计算长度是8。
     * 为了获得10，需要从头扫描根据字符的Ascii来获得具体的长度。如果是标准的字符，Ascii的范围是0至255，如果是汉字或其他全角字符，Ascii会大于255。
     * 因此，可以编写如下的方法来获得以字节为单位的字符串长度。
     * @param s
     * @return int
     * @author jiangfeng.yan@ikang.com
     * @date 2019-07-01 下午 3:36
     */
    public static int getWordCount(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255)
            {
                length++;
            }
            else
            {
                length += 2;
            }
        }
        return length;
    }

    /**
     * 功能描述 基本原理是将字符串中所有的非标准字符（双字节字符）替换成两个标准字符（**，或其他的也可以）。这样就可以直接例用length方法获得字符串的字节长度了
     * @param s
     * @return int
     * @author jiangfeng.yan@ikang.com
     * @date 2019-07-01 下午 3:36
     */
    public static int getWordCountRegex(String s) {

        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }

    /**
     * 功能描述 按特定的编码格式获取长度
     * @param str
     * @param code
     * @return int
     * @author jiangfeng.yan@ikang.com
     * @date 2019-07-01 下午 3:37
     */
    public static int getWordCountCode(String str, String code) throws UnsupportedEncodingException {
        return str.getBytes(code).length;
    }

    /**
     * 判空
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2021/12/2 10:04
     */
    public static Boolean isEmpty(String str) {
        return Objects.isNull(str) || "".equals(str.trim());
    }

    public static Boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    /**
     * 校验是否为数字
     *
     * @author jiangfeng.yan
     * @date 2021-06-30 下午 5:01
     */
    public static Boolean isNumber(String s){
        Pattern pattern = compile("[0-9]*");
        Matcher isNum = pattern.matcher(s);
        return isNum.matches();
    }
}
