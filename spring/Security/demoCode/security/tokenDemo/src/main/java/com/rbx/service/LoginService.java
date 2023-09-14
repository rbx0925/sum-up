package com.rbx.service;

import com.rbx.domain.Result;
import com.rbx.entity.User;

/**
 * @author rbx
 * @title
 * @Create 2023-09-08 17:30
 * @Description
 */
public interface LoginService {
    Result login(User user);

    Result logOut();
}
