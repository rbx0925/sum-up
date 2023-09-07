/*
 * Copyright (c) 2016 iKang Guobin Healthcare Group. All rights reserved.
 */
package com.ikang.idata.common.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 封装各种格式的编码解码工具类.
 * 1.SHA-1加密.
 * 2.MD5加密.
 * 3.Commons-Codec的 hex/base64 编码
 * 4.JDK提供的URLEncoder
 * 5.自制的base62 编码
 * 6.Commons-Lang的xml/html escape
 *
 * @author <a href="mailto:jason.wu@ikang.com">Wu Jun</a>
 * @version 1.0.0, 10/17/2014
 * @since 2.0.0
 */
public abstract class EncodeUtils {

    private static final Logger logger = LoggerFactory.getLogger(EncodeUtils.class);
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
            .toCharArray();

    // 算法DESede
    private static final String ALGORITHM = "DESede";

    // 工作模式CBC(ECB)，填充模式PKCS5Padding(NoPadding)
    // eg: DESede/CBC/PKCS5Padding, DESede/ECB/PKCS5Padding
    private static final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";

    // 向量iv,ECB不需要向量iv，CBC需要向量iv
    // CBC工作模式下，同样的密钥，同样的明文，使用不同的向量iv加密 会生成不同的密文
    private static final String IV = "\0\0\0\0\0\0\0\0";


    /**
     * 编码加密
     *
     * @param input
     * @param algorithm MD2 MD5 SHA-1 SHA-256 SHA-384 SHA-512
     * @return
     */
    public static String encode(String input, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(input.getBytes());
            return bytesToHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }
        return input;
    }

    /**
     * SHA1加密.
     *
     * @param input
     * @return
     */
    public static String sha1(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(input.getBytes());
            return bytesToHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            // e.printStackTrace();
        }
        return input;
    }

    /**
     * MD5加密.
     *
     * @param input
     * @return
     */
    public static String md5(String input) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes());
            // 获得密文并进行十六进制转码
            return bytesToHex(mdInst.digest());
        } catch (NoSuchAlgorithmException e) {
            // e.printStackTrace();
        }
        return input;
    }

    /**
     * 把字节数组转换成十六进制字符串.
     *
     * @param input
     * @return
     */
    public static String bytesToHex(byte[] input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            String hex = Integer.toHexString(input[i] & 0xFF);
            if (hex.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }

    /**
     * 把十六字节数组转换成字符数组
     *
     * @param b
     * @return
     */
    public static byte[] hexToByte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    /**
     * Hex编码.
     *
     * @param input
     * @return
     */
    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }

    /**
     * Hex解码.
     *
     * @param input
     * @return
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            //throw ExceptionUtils.unchecked(e);
        }
        return new byte[0];
    }

    /**
     * Base64编码.
     *
     * @param input
     * @return
     */
    public static String encodeBase64(byte[] input) {
        return Base64.encodeBase64String(input);
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
     *
     * @param input
     * @return
     */
    public static String encodeUrlSafeBase64(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * Base64解码.
     *
     * @param input
     * @return
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input);
    }

    /**
     * Base62编码.
     *
     * @param input
     * @return
     */
    public static String encodeBase62(byte[] input) {
        char[] chars = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
        }
        return new String(chars);
    }

    /**
     * 按UTF-8字符进行URL编码.
     *
     * @param s
     * @return
     */
    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            return s;
            // throw ExceptionUtils.unchecked(e);
        }
    }

    /**
     * 按UTF-8字符进行URL解码.
     *
     * @param s
     * @return
     */
    public static String urlDecode(String s) {
        try {
            return URLDecoder.decode(s, DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            return s;
            // throw ExceptionUtils.unchecked(e);
        }
    }

    /**
     * Html 转码.
     *
     * @param html
     * @return
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * Html 解码.
     *
     * @param htmlEscaped
     * @return
     */
    public static String unescapeHtml(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    /**
     * Xml 转码.
     *
     * @param xml
     * @return
     */
    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml11(xml);
    }

    /**
     * Xml 解码.
     *
     * @param xmlEscaped
     * @return
     */
    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    // starts DES3
    // -----------------------------------------------------------------------

    /**
     * DES3加密.
     *
     * @param plainText 明文
     * @param key       密钥
     * @return
     */
    public static String encryptDes3(String plainText, String key) {
        try {
            byte[] cipherText = encryptDes3(plainText.getBytes(DEFAULT_CHARSET), getDes3Key(key));
            return bytesToHex(cipherText);
        } catch (UnsupportedEncodingException e) {
            //ignore
        }
        return plainText;
    }

    /**
     * DES3解密.
     *
     * @param cipherText 密文
     * @param key        密钥
     * @return
     */
    public static String decryptDes3(String cipherText, String key) {
        try {
            byte[] plainText = decryptDes3(hexToByte(cipherText.getBytes()), getDes3Key(key));
            return new String(plainText);
        } catch (Exception e) {
            //ignore
        }
        return cipherText;
    }

    /**
     * DES3加密.
     *
     * @param plainText
     * @param key
     * @return
     */
    private static byte[] encryptDes3(byte[] plainText, byte[] key) {
        try {
            // 根据给定的字节数组和算法构造一个密钥
            SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
            // 加密
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            Cipher c1 = Cipher.getInstance(TRANSFORMATION);
            c1.init(Cipher.ENCRYPT_MODE, deskey, iv);
            return c1.doFinal(plainText);
            //return bytesToHex(c1.doFinal(plainText));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }

    /**
     * DES3解密.
     *
     * @param cipherText
     * @param key
     * @return
     */
    private static byte[] decryptDes3(byte[] cipherText, byte[] key) throws Exception {
        // 生成密钥
        SecretKey deskey = new SecretKeySpec(key, ALGORITHM);
        // 解密
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
        Cipher c1 = Cipher.getInstance(TRANSFORMATION);
        c1.init(Cipher.DECRYPT_MODE, deskey, iv);
        return c1.doFinal(cipherText);
        //byte[] data = c1.doFinal(cipherText);
        //return new String(data);

    }

    /**
     * 获取Key.
     *
     * @param key
     * @return
     * @throws UnsupportedEncodingException
     */
    private static byte[] getDes3Key(String key) throws UnsupportedEncodingException {
        // 加密数据必须是24位，不足补0；超出24位则只取前面的24数据
        byte[] data = key.getBytes(DEFAULT_CHARSET);
        int len = data.length;
        byte[] keys = new byte[24];
        System.arraycopy(data, 0, keys, 0, len > 24 ? 24 : len);
        return keys;
    }

    // ends Des3
    // -----------------------------------------------------------------------

    /**
     * @param args
     */
    public static void main(String[] args) {
        //String key = "QAZWSXEDCTGFREDW@#$%";
        String key = "3fc41dad0cbb4a9a90c44616c0b82f17" + "&" + new Date().getTime();

        System.out.println("加密密钥：" + key);
        //key = EncodeUtils.sha1(key);
        //System.out.println("加密密钥SHA1加密后：" + key);

        key = EncodeUtils.md5("5");
        System.out.println("加密密钥MD5加密后：" + key);

        String plainText = "01234567890123456789";
        System.out.println("加密密钥：" + key);
        System.out.println("原始数据：" + plainText);
        String cipherText = encryptDes3(plainText, key);
        System.out.println("加密数据：" + cipherText + ", 加密后的长度:" + cipherText.length());
        String decryptData = decryptDes3(cipherText, key);
        System.out.println("解密数据：" + decryptData);
    }

}