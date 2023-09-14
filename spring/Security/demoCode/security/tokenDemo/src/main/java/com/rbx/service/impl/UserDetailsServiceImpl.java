package com.rbx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rbx.entity.LoginUser;
import com.rbx.entity.User;
import com.rbx.mapper.MenuMapper;
import com.rbx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author rbx
 * @title
 * @Create 2023-09-08 13:36
 * @Description
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 查询用户信息
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, userName));
        if (Objects.isNull(user)){
            throw new RuntimeException("User Or PassWord Error");
        }
        // 查询对应的权限信息
        List<String> list = menuMapper.selectPermsByUserId(user.getId());
        // 数据封装成UserDetails返回
        return new LoginUser(user,list);
    }
}
