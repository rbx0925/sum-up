package com.ikang.idata.common.utils.encrypt;



import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * MD5工具类.
 * Created by sofical on 2020/9/27.
 */
@Slf4j
public class EncryptMd5Utils {

    /**
     * MD5加密.
     * @param source 加密的文本.
     * @return 加密结果.
     */
    public static String encode(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }

        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = source.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i=0; i<md5Bytes.length; i++){
                int val = md5Bytes[i] & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        }catch(Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
