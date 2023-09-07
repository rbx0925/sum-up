package com.ikang.idata.search.search.javaTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/12/2
 */
public class EncryptTest {

    public static void main(String[] args) {
        String key = "cbde!@#$2018";
        String encode = encrypt("{\"code\":\"-98\",\"message\":\"项目已初始化，请重复初始化\",\"serverTime\":\"2018-11-20T17:42:56.227+0800\"}", key);
        System.out.println(encode);
        System.out.println(decrypt(encode, key));
        //System.out.println(new String(hexStringToBytes("1A200C1325AD890FC3")));
    }

    /**
     * 信息加密.
     * @param sSrc
     * @param sKey
     * @return
     */
    public static String encrypt(String sSrc, String sKey) {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }

        try {
            byte[] raw = hex(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] sSrcByte = sSrc.getBytes("utf-8");
            //自补码
            Integer paddingCount = 8 - (sSrcByte.length % 8);
            byte[] toEncrypt = new byte[sSrcByte.length + paddingCount];
            for (int m=0; m < sSrcByte.length; m++) {
                toEncrypt[m] = sSrcByte[m];
            }
            if (paddingCount > 0) {
                for(int i = 0; i < paddingCount; i++) {
                    toEncrypt[sSrcByte.length + i] = "0".getBytes("utf-8")[0];
                }
            }


            byte[] encrypted = cipher.doFinal(toEncrypt);

            return bytesToHexString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param sSrc
     * @param sKey
     * @return
     */
    public static String encryptPKCS5Padding(String sSrc, String sKey) {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        try {
            byte[] raw = hex(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

            return bytesToHexString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 信息解密。
     * @param sSrc
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String decrypt(String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            byte[] raw = hex(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = hexStringToBytes(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                String pre = originalString.substring(0, originalString.length() - 8);
                String after = originalString.substring(originalString.length() - 8, originalString.length());
                for (int i=7; i>-1; i--) {
                    if (!"0".equals(after.substring(i, i+1))) {
                        break;
                    }
                    after = after.substring(0, i);
                }
                return pre + after;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     * 解密.
     * @param sSrc
     * @param sKey
     * @return
     */
    public static String decryptPKCS5Padding (String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            byte[] raw = hex(sKey);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = hexStringToBytes(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    /**
     *Convert byte[] to hex string.
     *@param src byte[]data
     *@return hex string
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if(src==null||src.length<=0){
            return null;
        }
        for(int i=0;i<src.length;i++){
            int v=src[i]&0xFF;
            String hv=Integer.toHexString(v);
            if(hv.length()<2){
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * Convert hex string to byte[].
     * @param hexString the hexstring
     * @return byte[]
     * */
    public static byte[] hexStringToBytes(String hexString){
        if(hexString == null|| hexString.equals("")){
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length()/2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for(int i=0;i<length;i++){
            int pos=i*2;
            d[i] = (byte)(charToByte(hexChars[pos])<<4|charToByte(hexChars[pos+1]));
        }
        return d;
    }

    /**
     *Convertchartobyte
     *@paramcchar
     *@returnbyte
     */
    public static byte charToByte(char c){
        return (byte)"0123456789ABCDEF".indexOf(c);
    }

    /**
     * string to hex
     * @param key
     * @return
     */
    public static byte[] hex(String key){
        byte[] bkeys = new String(key).getBytes();
        byte[] enk = new byte[24];
        for (int i=0;i<24;i++){
            enk[i] = (i < bkeys.length) ? bkeys[i] : 0x00;
        }
        return enk;
    }


    //算法
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    private static final String AES_KEY = "AnjiPLUSAjReport";
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    private static int getCountEmptyStringUsingJava7(List<String> strings) {
        int count = 0;

        for (String string : strings) {

            if (string.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    private static List<String> deleteEmptyStringsUsingJava7(List<String> strings) {
        List<String> filteredList = new ArrayList<String>();

        for (String string : strings) {

            if (!string.isEmpty()) {
                filteredList.add(string);
            }
        }
        return filteredList;
    }

    private static String getMergedStringUsingJava7(List<String> strings, String separator) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : strings) {

            if (!string.isEmpty()) {
                stringBuilder.append(string);
                stringBuilder.append(separator);
            }
        }
        String mergedString = stringBuilder.toString();
        return mergedString.substring(0, mergedString.length() - 2);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        List<Integer> squaresList = new ArrayList<Integer>();

        for (Integer number : numbers) {
            Integer square = new Integer(number.intValue() * number.intValue());

            if (!squaresList.contains(square)) {
                squaresList.add(square);
            }
        }
        return squaresList;
    }

    private static int getMax(List<Integer> numbers) {
        int max = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {

            Integer number = numbers.get(i);

            if (number.intValue() > max) {
                max = number.intValue();
            }
        }
        return max;
    }

    private static int getMin(List<Integer> numbers) {
        int min = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            Integer number = numbers.get(i);

            if (number.intValue() < min) {
                min = number.intValue();
            }
        }
        return min;
    }
}
