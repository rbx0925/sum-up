package com.atguigu.service.impl;

import com.atguigu.dao.LogInfoMapper;
import com.atguigu.feign.OrderInfoFeign;
import com.atguigu.feign.UserInfoFeign;
import com.atguigu.pojo.LogInfo;
import com.atguigu.service.BusinessService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private OrderInfoFeign orderInfoFeign;

    @Autowired
    private UserInfoFeign userInfoFeign;

    @Resource
    private LogInfoMapper logInfoMapper;

    /***
     * ①
     * 下单
     * @GlobalTransactional:全局事务入口
     * @param username
     * @param id
     * @param count
     */
    @Override
    @GlobalTransactional
    public void add(String username, int id, int count) {
        //添加订单日志
        LogInfo logInfo = new LogInfo();
        logInfo.setContent("添加订单数据---"+new Date());
        logInfo.setCreatetime(new Date());
        //向日志插入数据
        int logcount = logInfoMapper.insertSelective(logInfo);
        System.out.println("添加日志受影响行数："+logcount);

        //添加订单，调用订单微服务
        orderInfoFeign.add(username,id,count);

        int price = 5;

        //用户账户余额递减
        userInfoFeign.decrMoney(username,10*price);
    }
}
