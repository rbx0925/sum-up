package com.rbx.service.impl;

import com.rbx.config.RedisCache;
import com.rbx.domain.Result;
import com.rbx.domain.ResultUtil;
import com.rbx.entity.LoginUser;
import com.rbx.entity.User;
import com.rbx.service.LoginService;
import com.rbx.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author rbx
 * @title
 * @Create 2023-09-08 17:30
 * @Description
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Result login(User user) {
        //authenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        //如果认证没通过给出提示
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登陆失败");
        }
        //如果认证通过了生成jwt
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入redis
        //redisCache.set("login:"+userid,loginUser);
        redisCache.setCacheObject("login:"+userid,loginUser);
        return ResultUtil.success(map);
    }

    /**
     * 退出登陆
     * @param
     * @return
     */
    @Override
    public Result logOut() {
        //获取SecurityContextHolder中的用户id
        Authentication authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login:"+userId);
        return ResultUtil.success();
    }
}
