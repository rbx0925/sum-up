package com.ikang.idata.common.utils;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @ Author     ：zhiyuan.xu@ikang.com
 * @ Date       ：Created in 20:00 2019/9/20
 * @ Description： 对称加密工具
 * @ Modified By：
 * @Version:
 */
public class EncryptionUtils {

    private static EncryptionUtils instance = null;
    private EncryptionUtils() {

    }

    public static EncryptionUtils getInstance() {
        if (instance == null) {
            instance = new EncryptionUtils();
        }
        return instance;
    }

    private static KeyGenerator kgen;
    private static SecureRandom random;
    private static Cipher cipher;
    private static SecretKey secretKey;
    private static  SecretKeySpec secretKeySpec;
    private static String key="key";

    static {
        try {
            kgen = KeyGenerator.getInstance("AES");
            random=SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
            kgen.init(128, random);
            secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // 加密
    public String encrypt(String content){
        if (content == null || content.length() < 1) {
            return null;
        }
        try {
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] byteRresult = cipher.doFinal(byteContent);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteRresult.length; i++) {
                String hex = Integer.toHexString(byteRresult[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }
            return sb.toString();
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
    }


    // 解密
    public String decrypt(String content){
        if (content == null || content.length() < 1) {
            throw new RuntimeException("解密字符串不能为空");
        }
        if (content.trim().length() < 19) {
            throw new RuntimeException("解密字符串非法");
        }
        byte[] byteRresult = new byte[content.length() / 2];
        for (int i = 0; i < content.length() / 2; i++) {
            int high = Integer.parseInt(content.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(content.substring(i * 2 + 1, i * 2 + 2), 16);
            byteRresult[i] = (byte) (high * 16 + low);
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(byteRresult);
            return new String(result);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
