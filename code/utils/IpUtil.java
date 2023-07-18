package com.ikang.idata.common.utils;

import cn.hutool.core.util.StrUtil;
import com.ikang.idata.common.consts.MagicConst;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

/**
 * IpUtil
 *
 * @author haigang.jia@ikang.com
 * @date 2019-06-14  下午 1:59
 */
public class IpUtil {
    private IpUtil() {
    }

    public static String longToIpV4(long longIp) {
        int octet3 = (int) ((longIp >> 24) % 256);
        int octet2 = (int) ((longIp >> 16) % 256);
        int octet1 = (int) ((longIp >> 8) % 256);
        int octet0 = (int) ((longIp) % 256);
        return octet3 + "." + octet2 + "." + octet1 + "." + octet0;
    }

    private static long ipV4ToLong(String ip) {
        String[] octets = ip.split("\\.");
        return (Long.parseLong(octets[0]) << 24) + (Integer.parseInt(octets[1]) << 16
        )
                + (Integer.parseInt(octets[2]) << 8) + Integer.parseInt(octets[3]);
    }

    private static boolean isIPv4Private(String ip) {
        long longIp = ipV4ToLong(ip);
        return (longIp >= ipV4ToLong(MagicConst.IP) && longIp <= ipV4ToLong(MagicConst.IP1))
                || (longIp >= ipV4ToLong(MagicConst.IP2) && longIp <= ipV4ToLong(MagicConst.IP3))
                || longIp >= ipV4ToLong(MagicConst.IP4) && longIp <= ipV4ToLong(MagicConst.IP5);
    }

    private static boolean isIPv4Valid(String ip) {
        return MagicConst.PATTERN.matcher(ip).matches();
    }

    public static String getIpFromRequest(HttpServletRequest request) {
        String ip;
        boolean found = false;
        if ((ip = request.getHeader(MagicConst.X_FORWARDED_FOR)) != null) {
            StringTokenizer tokenizer = new StringTokenizer(ip, ",");
            while (tokenizer.hasMoreTokens()) {
                ip = tokenizer.nextToken().trim();
                if (isIPv4Valid(ip) && !isIPv4Private(ip)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                if (StrUtil.isBlank(ip) || MagicConst.UNKNOWN.equalsIgnoreCase(ip)) {
                    ip = request.getHeader(MagicConst.WL_PROXY_CLIENT_IP);
                }
                if (StrUtil.isBlank(ip) || MagicConst.UNKNOWN.equalsIgnoreCase(ip)) {
                    ip = request.getHeader(MagicConst.HTTP_CLIENT_IP);
                }
                if (StrUtil.isBlank(ip) || MagicConst.UNKNOWN.equalsIgnoreCase(ip)) {
                    ip = request.getHeader(MagicConst.HTTP_X_FORWARDED_FOR);
                }
            }
        }
        if (StrUtil.isBlank(ip) || MagicConst.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? MagicConst.LOCAL_IP : ip;
    }


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request HttpServletRequest
     * @return ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader(MagicConst.X_FORWARDED_FOR);
        if (ip == null || ip.length() == 0 || MagicConst.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(MagicConst.PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || MagicConst.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(MagicConst.WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || MagicConst.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(MagicConst.HTTP_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || MagicConst.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(MagicConst.HTTP_X_FORWARDED_FOR);
        }
        if (ip == null || ip.length() == 0 || MagicConst.UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
