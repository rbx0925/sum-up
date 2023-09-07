package com.ikang.idata.common.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ikang.idata.common.consts.MagicConst;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * JwtUtil
 *
 * @author haigang.jia@ikang.com
 * @date 2019-06-14  下午 1:58
 */
@Slf4j
public class JwtUtil {


    /**
     * 获取token失效时间
     *
     * @param token token
     * @return java.util.Date
     * @author haigang.jia@ikang.com
     * @date 2019-06-14  下午 1:58
     */
    public static Date getExpiresAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("token 解析失败");
        }
    }


    /**
     * 获取token的声明中的用户名称
     *
     * @param token token
     * @return java.lang.String
     * @author haigang.jia@ikang.com
     * @date 2019-06-14  下午 1:58
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(MagicConst.PARAM_USER_NAME).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取token的声明中的用户ID
     *
     * @param token token
     * @return java.lang.Long
     * @author haigang.jia@ikang.com
     * @date 2019-07-12 下午 5:43
     */
    public static Long getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(MagicConst.PARAM_USER_ID).asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * token是否过期
     * true 为过期
     *
     * @param token token
     * @return boolean
     * @author haigang.jia@ikang.com
     * @date 2019-06-14  下午 1:58
     */
    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }

    /**
     * Token是否应该刷新
     *
     * @param token token
     * @return boolean
     * @author haigang.jia@ikang.com
     * @date 2019-06-14  下午 1:58
     */
    public static boolean shouldRefresh(String token) {
        LocalDateTime expireTime = LocalDateTime.ofInstant(getExpiresAt(token).toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().plusSeconds(MagicConst.TOKEN_REFRESH_SECOND).isAfter(expireTime);
    }


    /**
     * 公共的有效载体内容
     *
     * @return com.auth0.jwt.JWTCreator.Builder
     * @author haigang.jia@ikang.com
     * @date 2019-06-14  下午 1:58
     */
    private static JWTCreator.Builder commonPayLoad(String userId) {
        return JWT.create()
                .withIssuer(MagicConst.ISSUER)
                .withAudience(userId)
                .withExpiresAt(DateUtil.offsetSecond(new Date(), MagicConst.TOKEN_EXPIRE_SECOND))
                .withIssuedAt(new Date());
    }

    /**
     * 创建Token
     * 用在登录接口使用，其他接口只能调用刷新方法
     *
     * @param userId   用户ID
     * @param userName 用户名称
     * @return java.lang.String
     * @author haigang.jia@ikang.com
     * @date 2019-06-14  下午 1:58
     */
    public static String createToken(String userName, Long userId) {
        Algorithm algorithm = Algorithm.HMAC256(MagicConst.SECRET);
        return commonPayLoad(String.valueOf(userId))
                .withClaim("timestamp", System.currentTimeMillis())
                .withClaim(MagicConst.PARAM_USER_NAME, userName)
                .withClaim(MagicConst.PARAM_USER_ID, userId)
                .sign(algorithm);
    }


    /**
     * 校验token
     * 校验令牌本身的有效性
     * 校验Token颁发对象是否一直
     *
     * @param token  token
     * @param userId 用户ID
     * @return boolean
     * @author haigang.jia@ikang.com
     * @date 2019-06-14  下午 1:58
     */
    public static boolean verifyToken(String token, Long userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(MagicConst.SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withAudience(String.valueOf(userId))
                    .withIssuer(MagicConst.ISSUER).build();
            verifier.verify(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * 获取Token的颁发时间
     *
     * @param token token
     * @return java.util.Date
     * @author haigang.jia@ikang.com
     * @date 2019-07-04 下午 7:51
     */
    public static Date getIssuedAt(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getIssuedAt();
    }
}
