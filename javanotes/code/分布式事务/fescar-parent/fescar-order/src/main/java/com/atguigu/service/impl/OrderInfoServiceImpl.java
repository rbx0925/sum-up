package com.atguigu.service.impl;

import com.atguigu.dao.OrderInfoMapper;
import com.atguigu.feign.ItemInfoFeign;
import com.atguigu.pojo.OrderInfo;
import com.atguigu.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private ItemInfoFeign itemInfoFeign;

    /***
     * 添加订单
     * @param username
     * @param id
     * @param count
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(String username, int id, int count) {
        //添加订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setMessage("生成订单");
        orderInfo.setMoney(10);
        int icount = orderInfoMapper.insertSelective(orderInfo);
        System.out.println("添加订单受影响函数："+icount);

        //递减库存
        itemInfoFeign.decrCount(id,count);
    }
}
