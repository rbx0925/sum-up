package com.atguigu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="user")
public interface UserInfoFeign {

    /***
     * 账户余额递减
     * @param username
     * @param money
     */
    @PostMapping(value = "/userInfo/add")
    String decrMoney(@RequestParam(value = "username") String username,
                     @RequestParam(value = "money") int money);
}