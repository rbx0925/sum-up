package com.ikang.idata.search.search.entity;

import com.ikang.idata.search.search.JsonPath;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description
 * @date 2022/3/31 14:19
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1055853888431573503L;
    private Long id;
    private String name;
    private String age;
    private String phoneNumber;
    private String mailbox;
    @JsonPath("password.value")
    private String password;

}
