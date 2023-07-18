package com.atguigu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="order")
public interface OrderInfoFeign {

    /**
     * 增加订单
     * @param username
     * @param id
     * @param count
     */
    @PostMapping(value = "/orderInfo/add")
    String add(@RequestParam(value = "name") String username,
               @RequestParam(value = "id") int id,
               @RequestParam(value = "count") int count);
}