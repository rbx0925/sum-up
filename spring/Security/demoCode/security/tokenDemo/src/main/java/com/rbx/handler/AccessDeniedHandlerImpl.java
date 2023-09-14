package com.rbx.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.rbx.domain.Result;
import com.rbx.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException){
        Result<String> result = new Result<>();
        result.setCode(HttpStatus.HTTP_FORBIDDEN);
        result.setMsg("权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);

    }
}