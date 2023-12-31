package com.ikang.idata.common.utils;//package com.ikang.idata.common.utils;
//
//import javax.crypto.*;
//import javax.crypto.spec.DESKeySpec;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.Charset;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
///**
// * 加密解密
// *
// * @author <a href="jiangfeng.yan@ikang.com">jiangfeng</a>
// * @version 2021年12月28日 下午 2:51
// */
//public class AES {
//
//    private static String iv = "ikangidata$%^&*(";//偏移量字符串必须是16位 当模式是CBC的时候必须设置偏移量
//    private static String Algorithm = "AES";
//    private static String AlgorithmProvider = "AES/CBC/PKCS5Padding"; //算法/模式/补码方式
//
//    public static SecretKey generatorKey(String strKey) throws NoSuchAlgorithmException {
////        KeyGenerator keyGenerator = KeyGenerator.getInstance(Algorithm);
////        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
////        secureRandom.setSeed(strKey.getBytes());
////        keyGenerator.init(128, secureRandom);//默认128，获得无政策权限后可为192或256
////        SecretKey secretKey = keyGenerator.generateKey();
////        return secretKey.getEncoded();
//        try {
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Algorithm);
//            DESKeySpec keySpec = new DESKeySpec(strKey.getBytes("utf-8"));
//            return keyFactory.generateSecret(keySpec);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static IvParameterSpec getIv() throws UnsupportedEncodingException {
//        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("utf-8"));
//        System.out.println("偏移量："+byteToHexString(ivParameterSpec.getIV()));
//        return ivParameterSpec;
//    }
//
//    public static byte[] encrypt(String src, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException,
//            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
//        SecretKey secretKey = new SecretKeySpec(key, Algorithm);
//        IvParameterSpec ivParameterSpec = getIv();
//        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
//        byte[] cipherBytes = cipher.doFinal(src.getBytes(Charset.forName("utf-8")));
//        return cipherBytes;
//    }
//
//    public static byte[] decrypt(String src, byte[] key) throws Exception {
////        SecretKey secretKey = new SecretKeySpec(key, Algorithm);
//        SecretKey secretKey = generatorKey("ikangidata%&(+||");
//        IvParameterSpec ivParameterSpec = getIv();
//        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
//        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
//        byte[] hexBytes = hexStringToBytes(src);
//        byte[] plainBytes = cipher.doFinal(hexBytes);
//        return plainBytes;
//    }
//
//    /**
//     * 将byte转换为16进制字符串
//     * @param src
//     * @return
//     */
//    public static String byteToHexString(byte[] src) {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < src.length; i++) {
//            int v = src[i] & 0xff;
//            String hv = Integer.toHexString(v);
//            if (hv.length() < 2) {
//                sb.append("0");
//            }
//            sb.append(hv);
//        }
//        return sb.toString();
//    }
//
//    /**
//     * 将16进制字符串装换为byte数组
//     * @param hexString
//     * @return
//     */
//    public static byte[] hexStringToBytes(String hexString) {
//        hexString = hexString.toUpperCase();
//        int length = hexString.length() / 2;
//        char[] hexChars = hexString.toCharArray();
//        byte[] b = new byte[length];
//        for (int i = 0; i < length; i++) {
//            int pos = i * 2;
//            b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
//        }
//        return b;
//    }
//
//    private static byte charToByte(char c) {
//        return (byte) "0123456789ABCDEF".indexOf(c);
//    }
//
//    public static void main(String[] args) {
//        try {
////             byte key[] = generatorKey("ikangidata%&(+||");
//            // 密钥必须是16的倍数
//            byte key[] = "ikangidata%&(+||".getBytes("utf-8");//hexStringToBytes("0123456789ABCDEF");
////            String src = "werty7890";
////            System.out.println("密钥:"+byteToHexString(key));
////            System.out.println("原字符串:"+src);
////
////            String enc = byteToHexString(encrypt(src, key));
////            System.out.println("加密："+enc);
////            System.out.println("解密："+new String(decrypt(enc, key), "utf-8"));
//
//
//            System.out.println("解密==============："+new String(decrypt("45FDB20556A2D8BE318CA086CD3C210A", key), "utf-8"));
//        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (IllegalBlockSizeException e) {
//            e.printStackTrace();
//        } catch (BadPaddingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}