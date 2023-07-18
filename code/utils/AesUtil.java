package com.ikang.idata.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 加解密
 *
 * @author <a href="jiangfeng.yan@ikang.com">jiangfeng</a>
 * @version 2021年12月29日 下午 6:05
 */
public class AesUtil {
    /**
     * 加密数据的 iv
     */
    private static final String DATA_IV = "ikangidata$%^&*(";
    /**
     * 加密数据的盐
     */
    public static final String DATA_SALT = "ikangidata%&(+||";

    /**
     * @author
     * @Description AES算法解密密文
     * @param data 密文
     * @return 明文
     */
    public static String decryptAES(String data) {
        try{
            String lowerCase = data.toLowerCase();
            byte[] hexStr2Byte = parseHexStr2Byte(lowerCase);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(DATA_SALT.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(DATA_IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(hexStr2Byte);
            String originalString = new String(original, StandardCharsets.UTF_8);
            return originalString.trim();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
        return null;
    }
    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) {
        String s = AesUtil.decryptAES("45FDB20556A2D8BE318CA086CD3C210A");
        System.out.println("s = " + s);
    }
}
