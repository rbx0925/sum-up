package com.rbx.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.rbx.domain.Result;
import com.rbx.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        Result<String> result = new Result<>();
        result.setCode(HttpStatus.HTTP_UNAUTHORIZED);
        result.setMsg("认证失败请重新登录");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}