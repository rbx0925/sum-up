package com.rbx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rbx.entity.User;
import com.rbx.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author rbx
 * @title
 * @Create 2023-09-13 15:56
 * @Description
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
}
