package com.youxiang.slp.customer.service.impl;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.youxiang.slp.common.utils.DateUtils;
import com.youxiang.slp.common.utils.StringUtils;
import com.youxiang.slp.common.utils.security.SlpAesUtil;
import com.youxiang.slp.customer.adapter.UserTaskAdapter;
import com.youxiang.slp.customer.biz.MiddleLayerBiz;
import com.youxiang.slp.customer.constants.NlhConstants;
import com.youxiang.slp.customer.dao.mapper.slp.*;
import com.youxiang.slp.customer.dao.mapper.slp.manual.UserTaskPOManualMapper;
import com.youxiang.slp.customer.domain.dto.*;
import com.youxiang.slp.customer.domain.po.*;
import com.youxiang.slp.customer.enums.nlh.NlhTaskEnum;
import com.youxiang.slp.customer.service.UserTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserTaskServiceImpl implements UserTaskService {

    private static final Logger logger = LoggerFactory.getLogger(UserTaskServiceImpl.class);

    @Resource
    private UserTaskAssistancePOMapper assistancePOMapper;
    @Resource
    private UserTaskDailyBrowsingPOMapper dailyBrowsingPOMapper;
    @Resource
    private UserTaskOncePOMapper oncePOMapper;
    @Resource
    private UserTaskBrowsingTextPOMapper browsingTextPOMapper;
    @Resource
    private UserTaskDrawMoneyPOMapper drawMoneyPOMapper;
    @Resource
    private UserTaskPOManualMapper userTaskPOManualMapper;

    @Override
    public void insertBatchAssistance(List<UserTaskAssistanceDTO> userLevelReceiveList) {
        if (userLevelReceiveList.size() > NlhConstants.max_size) {
            List<List<UserTaskAssistanceDTO>> partition = Lists.partition(userLevelReceiveList, NlhConstants.max_size);
            for (List<UserTaskAssistanceDTO> item : partition) {
                List<UserTaskAssistancePO> collect = item.stream().map(UserTaskAdapter::assistanceDTO2PO).collect(Collectors.toList());
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < collect.size(); i++) {
                    UserTaskAssistancePO po = collect.get(i);
                    builder.append(po.toString());
                    if (i < collect.size() - 1) {
                        builder.append(",");
                    }
                }
//                userTaskPOManualMapper.insertBatchAssistance(collect);
                userTaskPOManualMapper.insertBatchAssistance(builder.toString());
            }
        }else {
            List<UserTaskAssistancePO> collect = userLevelReceiveList.stream().map(UserTaskAdapter::assistanceDTO2PO).collect(Collectors.toList());
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < collect.size(); i++) {
                UserTaskAssistancePO po = collect.get(i);
                builder.append(po.toString());
                if (i < collect.size() - 1) {
                    builder.append(",");
                }
            }
//            userTaskPOManualMapper.insertBatchAssistance(collect);
            userTaskPOManualMapper.insertBatchAssistance(builder.toString());
        }
    }

    @Override
    public void insertBatchDailyBrowsing(List<UserTaskDailyBrowsingDTO> userTaskDailyBrowsingList) {
        if (userTaskDailyBrowsingList.size() > NlhConstants.max_size) {
            List<List<UserTaskDailyBrowsingDTO>> partition = Lists.partition(userTaskDailyBrowsingList, NlhConstants.max_size);
            for (List<UserTaskDailyBrowsingDTO> item : partition) {
                List<UserTaskDailyBrowsingPO> collect = item.stream().map(UserTaskAdapter::dailyBrowsingDTO2PO).collect(Collectors.toList());
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < collect.size(); i++) {
                    UserTaskDailyBrowsingPO po = collect.get(i);
                    builder.append(po.toString());
                    if (i < collect.size() - 1) {
                        builder.append(",");
                    }
                }
//                userTaskPOManualMapper.insertBatchDailyBrowsing(collect);
                userTaskPOManualMapper.insertBatchDailyBrowsing(builder.toString());
            }
        }else {
            List<UserTaskDailyBrowsingPO> collect = userTaskDailyBrowsingList.stream().map(UserTaskAdapter::dailyBrowsingDTO2PO).collect(Collectors.toList());
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < collect.size(); i++) {
                UserTaskDailyBrowsingPO po = collect.get(i);
                builder.append(po.toString());
                if (i < collect.size() - 1) {
                    builder.append(",");
                }
            }
//                userTaskPOManualMapper.insertBatchDailyBrowsing(collect);
            userTaskPOManualMapper.insertBatchDailyBrowsing(builder.toString());
        }
    }

    @Override
    public void insertBatchBrowsingText(List<UserTaskBrowsingTextDTO> userTaskBrowsingTextList) {
        if (userTaskBrowsingTextList.size() > NlhConstants.max_size) {
            List<List<UserTaskBrowsingTextDTO>> partition = Lists.partition(userTaskBrowsingTextList, NlhConstants.max_size);
            for (List<UserTaskBrowsingTextDTO> item : partition) {
                List<UserTaskBrowsingTextPO> collect = item.stream().map(UserTaskAdapter::browsingTextDTO2PO).collect(Collectors.toList());
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < collect.size(); i++) {
                    UserTaskBrowsingTextPO po = collect.get(i);
                    builder.append(po.toString());
                    if (i < collect.size() - 1) {
                        builder.append(",");
                    }
                }
//                userTaskPOManualMapper.insertBatchBrowsingText(collect);
                userTaskPOManualMapper.insertBatchBrowsingText(builder.toString());
            }
        }else {
            List<UserTaskBrowsingTextPO> collect = userTaskBrowsingTextList.stream().map(UserTaskAdapter::browsingTextDTO2PO).collect(Collectors.toList());
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < collect.size(); i++) {
                UserTaskBrowsingTextPO po = collect.get(i);
                builder.append(po.toString());
                if (i < collect.size() - 1) {
                    builder.append(",");
                }
            }
//                userTaskPOManualMapper.insertBatchBrowsingText(collect);
            userTaskPOManualMapper.insertBatchBrowsingText(builder.toString());
        }
    }

    @Override
    public void insertBatchOnce(List<UserTaskOnceDTO> userTaskOnceList) {
        if (userTaskOnceList.size() > NlhConstants.max_size) {
            List<List<UserTaskOnceDTO>> partition = Lists.partition(userTaskOnceList, NlhConstants.max_size);
            for (List<UserTaskOnceDTO> item : partition) {
                List<UserTaskOncePO> collect = item.stream().map(UserTaskAdapter::onceDTO2PO).collect(Collectors.toList());
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < collect.size(); i++) {
                    UserTaskOncePO po = collect.get(i);
                    builder.append(po.toString());
                    if (i < collect.size() - 1) {
                        builder.append(",");
                    }
                }
//                userTaskPOManualMapper.insertBatchOnce(collect);
                userTaskPOManualMapper.insertBatchOnce(builder.toString());
            }
        }else {
            List<UserTaskOncePO> collect = userTaskOnceList.stream().map(UserTaskAdapter::onceDTO2PO).collect(Collectors.toList());
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < collect.size(); i++) {
                UserTaskOncePO po = collect.get(i);
                builder.append(po.toString());
                if (i < collect.size() - 1) {
                    builder.append(",");
                }
            }
//                userTaskPOManualMapper.insertBatchOnce(collect);
            userTaskPOManualMapper.insertBatchOnce(builder.toString());
        }
    }

    @Override
    public void insertBatchDrawMoney(List<UserTaskDrawMoneyDTO> userTaskDrawMoneyList) {
        if (userTaskDrawMoneyList.size() > NlhConstants.max_size) {
            List<List<UserTaskDrawMoneyDTO>> partition = Lists.partition(userTaskDrawMoneyList, NlhConstants.max_size);
            for (List<UserTaskDrawMoneyDTO> item : partition) {
                List<UserTaskDrawMoneyPO> collect = item.stream().map(UserTaskAdapter::drawMoneyDTO2PO).collect(Collectors.toList());
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < collect.size(); i++) {
                    UserTaskDrawMoneyPO po = collect.get(i);
                    builder.append(po.toString());
                    if (i < collect.size() - 1) {
                        builder.append(",");
                    }
                }
//                userTaskPOManualMapper.insertBatchDrawMoney(collect);
                userTaskPOManualMapper.insertBatchDrawMoney(builder.toString());

            }
        }else {
            List<UserTaskDrawMoneyPO> collect = userTaskDrawMoneyList.stream().map(UserTaskAdapter::drawMoneyDTO2PO).collect(Collectors.toList());
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < collect.size(); i++) {
                UserTaskDrawMoneyPO po = collect.get(i);
                builder.append(po.toString());
                if (i < collect.size() - 1) {
                    builder.append(",");
                }
            }
//                userTaskPOManualMapper.insertBatchDrawMoney(collect);
            userTaskPOManualMapper.insertBatchDrawMoney(builder.toString());
        }
    }

    @Override
    public void updateCustIdToAssistance(Long custId, String customerCode, String mobile) {
        UserTaskAssistancePO po = new UserTaskAssistancePO();
//        po.setCustId(custId);
        UserTaskAssistancePOExample example = new UserTaskAssistancePOExample();
        UserTaskAssistancePOExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerCodeEqualTo(customerCode);
        criteria.andMobileEqualTo(mobile);
        assistancePOMapper.updateByExampleSelective(po, example);
    }

    @Override
    public void updateCustIdToBrowsingText(Long custId, String customerCode, String mobile) {
        UserTaskBrowsingTextPO po = new UserTaskBrowsingTextPO();
//        po.setCustId(custId);
        UserTaskBrowsingTextPOExample example = new UserTaskBrowsingTextPOExample();
        UserTaskBrowsingTextPOExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerCodeEqualTo(customerCode);
        criteria.andMobileEqualTo(mobile);
        browsingTextPOMapper.updateByExampleSelective(po, example);
    }

    @Override
    public void updateCustIdToDailyBrowsing(Long custId, String customerCode, String mobile) {
        UserTaskDailyBrowsingPO po = new UserTaskDailyBrowsingPO();
//        po.setCustId(custId);
        UserTaskDailyBrowsingPOExample example = new UserTaskDailyBrowsingPOExample();
        UserTaskDailyBrowsingPOExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerCodeEqualTo(customerCode);
        criteria.andMobileEqualTo(mobile);
        dailyBrowsingPOMapper.updateByExampleSelective(po, example);
    }

    @Override
    public void updateCustIdToDrawMoney(Long custId, String customerCode, String mobile) {
        UserTaskDrawMoneyPO po = new UserTaskDrawMoneyPO();
//        po.setCustId(custId);
        UserTaskDrawMoneyPOExample example = new UserTaskDrawMoneyPOExample();
        UserTaskDrawMoneyPOExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerCodeEqualTo(customerCode);
        criteria.andMobileEqualTo(mobile);
        drawMoneyPOMapper.updateByExampleSelective(po, example);
    }

    @Override
    public void updateCustIdToOnce(Long custId, String customerCode, String mobile) {
        UserTaskOncePO po = new UserTaskOncePO();
//        po.setCustId(custId);
        UserTaskOncePOExample example = new UserTaskOncePOExample();
        UserTaskOncePOExample.Criteria criteria = example.createCriteria();
        criteria.andCustomerCodeEqualTo(customerCode);
        criteria.andMobileEqualTo(mobile);
        oncePOMapper.updateByExampleSelective(po, example);
    }

    @Override
    public List<UserTaskDailyBrowsingPO> selectTaskRecordBy01(Integer beginId, Integer limit) {
        UserTaskDailyBrowsingPOExample example = new UserTaskDailyBrowsingPOExample();
        UserTaskDailyBrowsingPOExample.Criteria criteria = example.createCriteria();
        criteria.andIdGreaterThan(beginId);
        example.setOrderByClause(" id ASC LIMIT 0," + limit);
        return dailyBrowsingPOMapper.selectByExample(example);
    }

    @Override
    public List<UserTaskBrowsingTextPO> selectTaskRecordBy02(Integer beginId, Integer limit) {
        UserTaskBrowsingTextPOExample example = new UserTaskBrowsingTextPOExample();
        UserTaskBrowsingTextPOExample.Criteria criteria = example.createCriteria();
        criteria.andIdGreaterThan(beginId);
        example.setOrderByClause(" id ASC LIMIT 0," + limit);
        return browsingTextPOMapper.selectByExample(example);
    }

    @Override
    public List<UserTaskDrawMoneyPO> selectTaskRecordBy03(Integer beginId, Integer limit) {
        UserTaskDrawMoneyPOExample example = new UserTaskDrawMoneyPOExample();
        UserTaskDrawMoneyPOExample.Criteria criteria = example.createCriteria();
        criteria.andIdGreaterThan(beginId);
        example.setOrderByClause(" id ASC LIMIT 0," + limit);
        return drawMoneyPOMapper.selectByExample(example);
    }

    @Override
    public List<UserTaskOncePO> selectTaskRecordBy04(Integer beginId, Integer limit) {
        UserTaskOncePOExample example = new UserTaskOncePOExample();
        UserTaskOncePOExample.Criteria criteria = example.createCriteria();
        criteria.andIdGreaterThan(beginId);
        example.setOrderByClause(" id ASC LIMIT 0," + limit);
        return oncePOMapper.selectByExample(example);
    }

    @Override
    public List<UserTaskAssistancePO> selectTaskRecordBy05(Integer beginId, Integer limit) {
        UserTaskAssistancePOExample example = new UserTaskAssistancePOExample();
        UserTaskAssistancePOExample.Criteria criteria = example.createCriteria();
        criteria.andIdGreaterThan(beginId);
        example.setOrderByClause(" id ASC LIMIT 0," + limit);
        return assistancePOMapper.selectByExample(example);
    }

    @Override
    public List<CustTaskPO> selectTaskRecordByList(List<UserFlagDTO> dtoList, Map<String, UserFlagDTO> map) {
        List<UserTaskAssistancePO> assistancePOList = userTaskPOManualMapper.selectTaskAssistanceByList(dtoList);
        List<UserTaskOncePO> oncePOList = userTaskPOManualMapper.selectTaskOnceByList(dtoList);
        List<UserTaskDrawMoneyPO> drawMoneyPOList = userTaskPOManualMapper.selectTaskDrawMoneyByList(dtoList);
        List<UserTaskBrowsingTextPO> browsingTextPOList = userTaskPOManualMapper.selectTaskBrowsingTextByList(dtoList);
        List<UserTaskDailyBrowsingPO> dailyBrowsingPOList = userTaskPOManualMapper.selectTaskDailyBrowsingByList(dtoList);
        List<CustTaskPO> taskPOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(assistancePOList)) {
            List<CustTaskPO> taskList = new ArrayList<>(assistancePOList.size());
            for (UserTaskAssistancePO item : assistancePOList) {
                UserFlagDTO userFlagDTO = map.get(item.getOriCustomerCode() + item.getOriMobile());
                if (userFlagDTO == null) {
                    logger.info("【助力任务】-未找到对应用户信息,当前记录不处理,用户标识:{},记录ID:{}", item.getOriCustomerCode(), item.getId());
                    continue;
                }
                String uniqueId = StringUtils.isNotBlank(userFlagDTO.getCustomerCode()) ? userFlagDTO.getCustomerCode() : StringUtils.isNotBlank(userFlagDTO.getMobile()) ? userFlagDTO.getMobile() : "";
                String taskFlag = "00010" + "-" + userFlagDTO.getCustId() + "-" + uniqueId;
                CustTaskPO po = new CustTaskPO();
                po.setCustId(userFlagDTO.getCustId());
                po.setUniqueId(StringUtils.isNotBlank(uniqueId) ? SlpAesUtil.encode(uniqueId) : "");
                po.setTaskFlag(taskFlag);
                po.setTaskCode("00010");
                po.setTaskName("帮助好友助力互赢花粉值");
                po.setTaskType("0");
                po.setPointValue(188);
                po.setProductCode("");
                po.setGetTimeBegin(item.getTime());
                po.setGetTimeEnd(DateUtils.dateTime("yyyy-MM-dd HH:mm:ss", "2099-12-31 23:59:59"));
                po.setIsGet("1");
                po.setOrderCode("");
                po.setExtInfo("SL-DATA-MIGRATION");
                po.setRemark(item.getOriCustomerCode() + "-邀请人标识");
                po.setAddTime(item.getTime());
                po.setUpdateTime(item.getTime());
                taskList.add(po);
            }
            assistancePOList.clear();
            taskPOList.addAll(taskList);
        }
        if (!CollectionUtils.isEmpty(oncePOList)) {
            List<CustTaskPO> taskList = new ArrayList<>(oncePOList.size());
            for (UserTaskOncePO item : oncePOList) {
                UserFlagDTO userFlagDTO = map.get(item.getCustomerCode() + item.getMobile());
                if (userFlagDTO == null) {
                    logger.info("【单次完成任务】-未找到对应用户信息,当前记录不处理,用户标识:{},记录ID:{}", item.getCustomerCode(), item.getId());
                    continue;
                }
                String taskCode = "00011";
                String taskName = "额度激活";
                Integer pointValue = 5000;
                String uniqueId = StringUtils.isNotBlank(userFlagDTO.getCustomerCode()) ? userFlagDTO.getCustomerCode() : StringUtils.isNotBlank(userFlagDTO.getMobile()) ? userFlagDTO.getMobile() : "";
                String taskFlag = taskCode + "-" + userFlagDTO.getCustId() + "-" + uniqueId;
                if (Objects.equals(item.getTaskFlag(), "apply")) {
                    taskCode = "00001";
                    taskName = "成功提交宁来花申请";
                    pointValue = 2000;
                    taskFlag = taskCode + "-" + userFlagDTO.getCustId() + "-" + uniqueId;
                }else if (Objects.equals(item.getTaskFlag(), "withdrawal")) {
                    taskCode = "00002";
                    taskName = "完成宁来花新客首提(额度出账30天内提款)";
                    pointValue = 10000;
                    taskFlag = taskCode + "-" + userFlagDTO.getCustId() + "-" + uniqueId;
                }
                CustTaskPO po = new CustTaskPO();
                po.setCustId(userFlagDTO.getCustId());
                po.setUniqueId(StringUtils.isNotBlank(uniqueId) ? SlpAesUtil.encode(uniqueId) : "");
                po.setTaskFlag(taskFlag);
                po.setTaskCode(taskCode);
                po.setTaskName(taskName);
                po.setTaskType("0");
                po.setPointValue(pointValue);
                po.setProductCode("");
                po.setGetTimeBegin(item.getReceiveTime());
                po.setGetTimeEnd(DateUtils.dateTime("yyyy-MM-dd HH:mm:ss", "2099-12-31 23:59:59"));
                po.setIsGet("1");
                po.setOrderCode("");
                po.setExtInfo("SL-DATA-MIGRATION");
                po.setAddTime(item.getReceiveTime());
                po.setUpdateTime(item.getReceiveTime());
                taskList.add(po);
            }
            oncePOList.clear();
            taskPOList.addAll(taskList);
        }
        if (!CollectionUtils.isEmpty(drawMoneyPOList)) {
            List<CustTaskPO> taskList = new ArrayList<>(drawMoneyPOList.size());
            for (UserTaskDrawMoneyPO item : drawMoneyPOList) {
                UserFlagDTO userFlagDTO = map.get(item.getCustomerCode() + item.getMobile());
                if (userFlagDTO == null) {
                    logger.info("【提款任务】-未找到对应用户信息,当前记录不处理,用户标识:{},记录ID:{}", item.getCustomerCode(), item.getId());
                    continue;
                }
                String level = "_4";
                if (Objects.equals(item.getDrawMoneyLevel(), "1")) {
                    level = "_1";
                }else if (Objects.equals(item.getDrawMoneyLevel(), "2")) {
                    level = "_2";
                }else if (Objects.equals(item.getDrawMoneyLevel(), "3")) {
                    level = "_3";
                }
                NlhTaskEnum taskEnum = NlhTaskEnum.getByOriFlag("isWithdrawal" + level);
                if (taskEnum == null) {
                    logger.info("【处理任务记录】-提款笔笔送，未找到对应任务项,当前记录不处理,用户ID:{},记录ID:{}", userFlagDTO.getCustId(), item.getId());
                    continue;
                }
                String status = "1";
                if (Objects.equals(item.getStatus(), "待领取")) {
                    status = "0";
                }else if (Objects.equals(item.getStatus(), "冻结中")) {
                    status = "2";
                }else if (Objects.equals(item.getStatus(), "已过期")) {
                    status = "3";
                }
                String uniqueId = StringUtils.isNotBlank(userFlagDTO.getCustomerCode()) ? userFlagDTO.getCustomerCode() : StringUtils.isNotBlank(userFlagDTO.getMobile()) ? userFlagDTO.getMobile() : "";
                String taskFlag = taskEnum.getTaskCode() + "-" + DateUtils.parseDateToStr(DateUtils.YYYYMM, item.getAddTime()) + "-" + uniqueId;
                CustTaskPO po = new CustTaskPO();
                po.setCustId(userFlagDTO.getCustId());
                po.setUniqueId(StringUtils.isNotBlank(uniqueId) ? SlpAesUtil.encode(uniqueId) : "");
                po.setTaskFlag(taskFlag);
                po.setTaskCode(taskEnum.getTaskCode());
                po.setTaskName(taskEnum.getPageName());
                po.setTaskType(taskEnum.getType());
                po.setPointValue(taskEnum.getPointValue());
                po.setProductCode("");
                po.setGetTimeBegin(item.getEffectTime());
                po.setGetTimeEnd(DateUtils.addDays(item.getEffectTime(), 15));
                po.setIsGet(status);
                po.setOrderCode("");
                po.setRemark(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, item.getDrawMoneyTime()) + "-" + item.getDrawMoneySeqNo());
                po.setExtInfo("SL-DATA-MIGRATION");
                po.setAddTime(item.getAddTime());
                po.setUpdateTime(item.getAddTime());
                taskList.add(po);
            }
            drawMoneyPOList.clear();
            taskPOList.addAll(taskList);
        }
        if (!CollectionUtils.isEmpty(browsingTextPOList)) {
            List<CustTaskPO> taskList = new ArrayList<>(browsingTextPOList.size());
            for (UserTaskBrowsingTextPO item : browsingTextPOList) {
                UserFlagDTO userFlagDTO = map.get(item.getCustomerCode() + item.getMobile());
                if (userFlagDTO == null) {
                    logger.info("【浏览文章任务】-未找到对应用户信息,当前记录不处理,用户标识:{},记录ID:{}", item.getCustomerCode(), item.getId());
                    continue;
                }
                String uniqueId = StringUtils.isNotBlank(userFlagDTO.getCustomerCode()) ? userFlagDTO.getCustomerCode() : StringUtils.isNotBlank(userFlagDTO.getMobile()) ? userFlagDTO.getMobile() : "";
                String taskFlag = "00009" + "-" + userFlagDTO.getCustId() + "-" + uniqueId + "-" + item.getTextUrl();
                CustTaskPO po = new CustTaskPO();
                po.setCustId(userFlagDTO.getCustId());
                po.setUniqueId(StringUtils.isNotBlank(uniqueId) ? SlpAesUtil.encode(uniqueId) : "");
                po.setTaskFlag(taskFlag);
                po.setTaskCode("00009");
                po.setTaskName("首次游览推文");
                po.setTaskType("0");
                po.setPointValue(5);
                po.setProductCode("");
                po.setGetTimeBegin(item.getBrowsingTime());
                po.setGetTimeEnd(DateUtils.dateTime("yyyy-MM-dd HH:mm:ss", "2099-12-31 23:59:59"));
                po.setIsGet("1");
                po.setOrderCode("");
                po.setRemark(item.getTextUrl());
                po.setExtInfo("SL-DATA-MIGRATION");
                po.setAddTime(item.getBrowsingTime());
                po.setUpdateTime(item.getBrowsingTime());
                taskList.add(po);
            }
            browsingTextPOList.clear();
            taskPOList.addAll(taskList);
        }
        if (!CollectionUtils.isEmpty(dailyBrowsingPOList)) {
            List<CustTaskPO> taskList = new ArrayList<>(dailyBrowsingPOList.size());
            for (UserTaskDailyBrowsingPO item : dailyBrowsingPOList) {
                UserFlagDTO userFlagDTO = map.get(item.getCustomerCode() + item.getMobile());
                if (userFlagDTO == null) {
                    logger.info("【浏览产品任务】-未找到对应用户信息,当前记录不处理,用户标识:{},记录ID:{}", item.getCustomerCode(), item.getId());
                    continue;
                }
                String uniqueId = StringUtils.isNotBlank(userFlagDTO.getCustomerCode()) ? userFlagDTO.getCustomerCode() : StringUtils.isNotBlank(userFlagDTO.getMobile()) ? userFlagDTO.getMobile() : "";
                String taskFlag = "00008" + "-" + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, item.getTime()) + "-" + uniqueId;
                CustTaskPO po = new CustTaskPO();
                po.setCustId(userFlagDTO.getCustId());
                po.setUniqueId(StringUtils.isNotBlank(uniqueId) ? SlpAesUtil.encode(uniqueId) : "");
                po.setTaskFlag(taskFlag);
                po.setTaskCode("00008");
                po.setTaskName("每日游览宁来花直接贷产品");
                po.setTaskType("0");
                po.setPointValue(5);
                po.setProductCode("");
                po.setGetTimeBegin(item.getTime());
                po.setGetTimeEnd(DateUtils.dateTime("yyyy-MM-dd HH:mm:ss", "2099-12-31 23:59:59"));
                po.setIsGet("1");
                po.setOrderCode("");
                po.setExtInfo("SL-DATA-MIGRATION");
                po.setAddTime(item.getTime());
                po.setUpdateTime(item.getTime());
                taskList.add(po);
            }
            dailyBrowsingPOList.clear();
            taskPOList.addAll(taskList);
        }
        return taskPOList;
    }
}
