package com.ikang.idata.search.search.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description 测试类
 * @date 2022/3/31 14:17
 */
@Data
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = 2336827791282197064L;

    private Long id;
    private String name;
    private String age;
    private String phoneNumber;
    private String mailbox;
    private String password;
}
