package com.youxiang.slp.customer.service.impl;

import com.youxiang.slp.common.dict.*;
import com.youxiang.slp.common.utils.DateUtils;
import com.youxiang.slp.customer.dao.mapper.slp.UserLevelReceivePOMapper;
import com.youxiang.slp.customer.dao.mapper.slp.manual.UserLevelReceivePOManualMapper;
import com.youxiang.slp.customer.domain.dto.ActivityLevelGiftDTO;
import com.youxiang.slp.customer.domain.dto.UserFlagDTO;
import com.youxiang.slp.customer.domain.po.CustBenefitModRecordPO;
import com.youxiang.slp.customer.domain.po.UserLevelReceivePO;
import com.youxiang.slp.customer.domain.po.UserLevelReceivePOExample;
import com.youxiang.slp.customer.service.UserLevelReceiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class UserLevelReceiveServiceImpl implements UserLevelReceiveService {

    private static final Logger logger = LoggerFactory.getLogger(UserLevelReceiveServiceImpl.class);

    @Resource
    private UserLevelReceivePOMapper userLevelReceivePOMapper;
    @Resource
    private UserLevelReceivePOManualMapper userLevelReceivePOManualMapper;

    private static Map<String, ActivityLevelGiftDTO> activityMap = new HashMap<>();

    static {
        activityMap.put("silver", new ActivityLevelGiftDTO(2L, "白银等级"));
        activityMap.put("gold", new ActivityLevelGiftDTO(2L, "黄金等级"));
        activityMap.put("platinum", new ActivityLevelGiftDTO(2L, "铂金等级"));
        activityMap.put("king", new ActivityLevelGiftDTO(2L, "尊享等级"));
    }

    @Override
    public List<UserLevelReceivePO> getByCustomerCode(String customerCode, String mobile) {
        UserLevelReceivePOExample example = new UserLevelReceivePOExample();
        UserLevelReceivePOExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerCodeEqualTo(customerCode);
        criteria.andMobileEqualTo(mobile);
        return userLevelReceivePOMapper.selectByExample(example);
    }

    @Override
    public Map<Long, List<CustBenefitModRecordPO>> selectByList(List<UserFlagDTO> dtoList, Map<String, UserFlagDTO> map) {
        List<UserLevelReceivePO> receivePOList = userLevelReceivePOManualMapper.selectByList(dtoList);
        if (!CollectionUtils.isEmpty(receivePOList)) {
            Map<Long, List<CustBenefitModRecordPO>> resultMap = new HashMap<>(8);
            for (UserLevelReceivePO item : receivePOList) {
                UserFlagDTO userFlagDTO = map.get(item.getCustomerCode() + item.getMobile());
                if (userFlagDTO == null) {
                    logger.info("【处理等级领取记录】-未找到对应用户信息,当前记录不处理,用户标识:{},记录ID:{}", item.getCustomerCode(), item.getId());
                    continue;
                }
                ActivityLevelGiftDTO dto = activityMap.get(item.getLevel());
                Date receiveTime = item.getReceiveTime();
                String bussinessId = userFlagDTO.getProjectId() + "-" + userFlagDTO.getCustId() +"-" + dto.getActivityId() + "-" + item.getLevel();
                CustBenefitModRecordPO po = new CustBenefitModRecordPO();
                po.setDataDate(receiveTime);
                po.setCustId(userFlagDTO.getCustId());
                po.setProjectId(userFlagDTO.getProjectId());
                po.setPlanId(0L);
                po.setBenefitId(0L);
                po.setAddSubtractFlag(AddSubtractFlag.ADD.getValue());
                po.setWhitelistBatchId(0L);
                po.setBussinessId(bussinessId);
                po.setModType(PointModType.DIRECTGIVEN_HFVALUE.getValue());
                po.setModValue(BigDecimal.ZERO);// 后台修改增加点数
                po.setModPoint(BigDecimal.ZERO); // 变动后权益点数
                po.setPointUnit(PriceUnit.POINT.getValue());
                po.setBenefitExpireTime(DateUtils.appointYearMonthLast(receiveTime.getYear(), receiveTime.getMonth() + 1));
                po.setUniqueCode("");
                po.setStatus(Status.ENABLED.getValue());
                po.setUseStatus(UseStatus.HAS_RECEIVED.getValue());
                po.setWriteOffStatus(WriteOffStatus.HAS_WRITE_OFF.getValue());
                po.setOrderCode("");
                po.setDescription(dto.getDesc() + "奖励");
                po.setBenefitSource("SL-DATA-MIGRATION");
                po.setAddTime(receiveTime);
                po.setModTime(receiveTime);
                Long index = userFlagDTO.getCustId() % 8;
                List<CustBenefitModRecordPO> mapItem = resultMap.get(index);
                if (mapItem == null) {
                    mapItem = new ArrayList<>(5000);
                    resultMap.put(index, mapItem);
                }
                mapItem.add(po);
            }
            return resultMap;
        }
        return null;
    }
}
