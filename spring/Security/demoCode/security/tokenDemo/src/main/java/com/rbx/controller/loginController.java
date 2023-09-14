package com.rbx.controller;

import com.rbx.domain.Result;
import com.rbx.domain.ResultUtil;
import com.rbx.entity.User;
import com.rbx.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author rbx
 * @title
 * @Create 2023-09-08 17:25
 * @Description
 */
@RestController
public class loginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        //登陆
        return loginService.login(user);
    }

    @RequestMapping("/user/logOut")
    public Result logOut(){
        //退出登陆
        return loginService.logOut();
    }

}
