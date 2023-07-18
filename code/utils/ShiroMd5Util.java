package com.ikang.idata.common.utils;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.symmetric.AES;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.nio.charset.StandardCharsets;

/**
 * ShiroMd5Util
 *
 * @author haigang.jia@ikang.com
 * @date 2019-06-14  下午 1:55
 */
public class ShiroMd5Util {


    /**
     * MD5加密
     *
     * @param content 加密内容
     * @param salt    加密盐值
     * @return java.lang.String
     * @author haigang.jia@ikang.com
     * @date 2019-06-14  下午 1:56
     */
    public static String md5(String content, String salt) {
        String algorithmName = "MD5";
        ByteSource saltSource = ByteSource.Util.bytes(salt);
        int hashIterations = 1024;
        SimpleHash hash = new SimpleHash(algorithmName, content, saltSource, hashIterations);
        return hash.toString();
    }
    /**
     * 对应前端的解密
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2021/12/28 16:33
     */
    public static String decrypt(String frontEndPassword) {
        byte[] bytes = HexUtil.decodeHex(frontEndPassword);
        AES aes = new AES("CBC",
                "PKCS5Padding",
                "ikangidata%&(+||".getBytes(StandardCharsets.UTF_8),
                "ikangidata$%^&*(".getBytes(StandardCharsets.UTF_8));
        byte[] decrypt = aes.decrypt(bytes);
        return new String(decrypt);
    }


    public static void main(String[] args) {
        System.out.println(decrypt("45FDB20556A2D8BE318CA086CD3C210A"));
    }
}
