package com.atguigu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="item")
public interface ItemInfoFeign {

    /**
     * 库存递减
     * @param id
     * @param count
     * @return
     */
    @PostMapping(value = "/itemInfo/decrCount")
    String decrCount(@RequestParam(value = "id") int id, @RequestParam(value = "count") int count);
}