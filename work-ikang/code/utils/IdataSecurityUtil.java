package com.ikang.idata.common.utils;

import cn.hutool.jwt.JWT;
import com.ikang.idata.common.consts.MagicConst;
import com.ikang.idata.common.entity.LoginInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class IdataSecurityUtil {
    /**
     * 获取用户信息, 主要有id,姓名,邮箱
     *
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/7 10:39
     */
    public static LoginInfo getUser() {
        return JWT.of(getToken()).getPayloads().toBean(LoginInfo.class);
    }

    /**
     * 获取token
     *
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/8 14:58
     */

    public static String getToken() {
        String token = getRequest().getHeader(MagicConst.TOKEN_HEADER);
        return token;
    }
    /**
     * 获取 请求
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/2/15 13:46
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }


    /***
     *  获取登录用户的邮箱地址
     * @return
     */
    public static String getUserMail() {
        return getUser().getUsername();
    }


    public static Long getUserId() {
        return getUser().getLoginId();
    }
}
