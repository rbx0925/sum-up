package com.ikang.idata.common.utils.encrypt;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * EncryptAESUtils.
 *
 * @author zj.
 *         Created on 2018/11/29 0029.
 */
public class EncryptAESUtils {
    final public static String DEFAULT_CHARSET = "utf-8";
    final public static String MODE_ECB_ZERO_PADDING = "AES/ECB/ZeroPadding";
    final public static String MODE_ECB_NO_PADDING = "AES/ECB/NoPadding";
    final public static String MODE_ECB_PKCS5_PADDING = "AES/ECB/PKCS5Padding";
    final public static String MODE_ECB_PKCS7_PADDING = "AES/ECB/PKCS7Padding";
    /**
     * AES 加密.
     * @param source 明文.
     * @param sKey 密钥.
     * @param mode 模式.
     * @param charset 编码.
     * @return 密文.
     */
    public static String encrypt(String source, String sKey, String mode, String charset) {
        try {
            byte[] raw = sKey.getBytes(charset);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            if (MODE_ECB_PKCS7_PADDING.equals(mode)) {
                // todo
                // Security.addProvider(new BouncyCastleProvider());
            }
            //自补码
            if (MODE_ECB_NO_PADDING.equals(mode)) {
                Integer paddingCount = 16 - (source.length() % 16);
                for(int i = 0; i < paddingCount; i++) {
                    source += "\0";
                }
            }
            Cipher cipher = Cipher.getInstance(mode);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(source.getBytes(charset));

            return new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * AES 加密（模式可选，默认UTF-8编码）.
     * @param source 明文.
     * @param sKey 密钥.
     * @param mode 模式.
     * @return
     */
    public static String encrypt(String source, String sKey, String mode) {
        return encrypt(source, sKey, mode, DEFAULT_CHARSET);
    }

    /**
     * AES ECB 解密.
     * @param encode 密文.
     * @param sKey 密钥.
     * @param mode 模式.
     * @param charset 编码.
     * @return 解密明文.
     */
    public static String decrypt(String encode, String sKey, String mode, String charset) {
        try {
            byte[] raw = sKey.getBytes(charset);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            if (MODE_ECB_PKCS7_PADDING.equals(mode)) {
                // todo
                // Security.addProvider(new BouncyCastleProvider());
            }
            Cipher cipher = Cipher.getInstance(mode);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            //先用base64解密
            encode = new String(encode.getBytes(charset), charset);
            byte[] encrypted = new Base64().decode(encode);

            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original,charset);
            //去自补码
            if (MODE_ECB_NO_PADDING.equals(mode)) {
                originalString = originalString.replaceAll("\0", "").replaceAll("\n", "");
            }
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * AES ECB 解密.
     * @param encode 密文.
     * @param sKey 密钥.
     * @param mode 模式.
     * @return 解密明文.
     */
    public static String decrypt(String encode, String sKey, String mode) {
        return decrypt(encode, sKey, mode, DEFAULT_CHARSET);
    }
}
