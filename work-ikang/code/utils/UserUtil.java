package com.ikang.idata.common.utils;

import cn.hutool.core.util.StrUtil;
import com.ikang.idata.common.consts.MagicConst;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * UserUtil
 *
 * @author haigang.jia@ikang.com
 * @date 2019-06-14  下午 2:01
 */
public class UserUtil {


    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录用户ID
     */
    public static Long getLoginId() {
        Object obj = getSubject().getPrincipal();
        return null == obj ? null : (Long) obj;
    }

    /**
     * 从当前请求中获取请求的用户名称
     */
    public static String getUserName() {
        String token = getToken();
        if (StrUtil.isBlank(token)) {
            return null;
        }
        return JwtUtil.getUsername(token);
    }

    /**
     * 从当前请求中获取token
     * 从ThreadLocal中获取，因此使用时需要保证在HttpServletRequest上下文中
     */
    public static String getToken() {
        Object obj = getSubject().getPrincipal();
        if (null == obj) {
            return null;
        }
        Object req = RequestContextHolder.getRequestAttributes();
        if (null == req) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) req).getRequest();
        return request.getHeader(MagicConst.TOKEN_HEADER);
    }

    /**
     * 当前subject是否具有指定角色编码
     *
     * @param roleCode 角色编码
     */
    public static boolean hasRole(String roleCode) {
        return getSubject().hasRole(roleCode);
    }

    /**
     * 当前subject是否具有指定角色编码列表
     *
     * @param roleCodeList 角色编码列表
     */
    public static boolean[] hasRoles(List<String> roleCodeList) {
        return getSubject().hasRoles(roleCodeList);
    }

    /**
     * 当前subject是否具有指定资源权限
     *
     * @param resourceCode 资源编码
     */
    public static boolean isPermitted(String resourceCode) {
        return getSubject().isPermitted(resourceCode);
    }
}
