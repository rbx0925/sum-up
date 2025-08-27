package com.youxiang.slp.customer.service.impl;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.youxiang.slp.customer.adapter.UserPointAdapter;
import com.youxiang.slp.customer.constants.NlhConstants;
import com.youxiang.slp.customer.dao.mapper.slp.CustTaskPOMapper;
import com.youxiang.slp.customer.dao.mapper.slp.manual.CustTaskPOManualMapper;
import com.youxiang.slp.customer.domain.dto.UserMobilePointDTO;
import com.youxiang.slp.customer.domain.po.CustTaskPO;
import com.youxiang.slp.customer.domain.po.UserMobilePointPO;
import com.youxiang.slp.customer.service.CustTaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustTaskServiceImpl implements CustTaskService {

    @Resource
    private CustTaskPOMapper custTaskPOMapper;
    @Resource
    private CustTaskPOManualMapper custTaskPOManualMapper;

    @Override
    public void insertBatch(List<CustTaskPO> taskList) {
        if (taskList.size() > NlhConstants.max_size) { // NlhConstants.max_size是5000
            List<List<CustTaskPO>> partition = Lists.partition(taskList, NlhConstants.max_size);
            for (List<CustTaskPO> item : partition) {
                custTaskPOManualMapper.insertBatch(item);
            }
        }else {
            custTaskPOManualMapper.insertBatch(taskList);
        }
    }
}
