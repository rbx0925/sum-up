package com.youxiang.slp.customer.biz;

import com.youxiang.slp.common.dict.*;
import com.youxiang.slp.common.utils.DateUtils;
import com.youxiang.slp.common.utils.security.SlpAesUtil;
import com.youxiang.slp.customer.domain.dto.ActivityLevelGiftDTO;
import com.youxiang.slp.customer.domain.po.*;
import com.youxiang.slp.customer.enums.nlh.NlhTaskEnum;
import com.youxiang.slp.customer.service.*;
import com.youxiang.slp.customer.utils.JDBCUtils;
import com.youxiang.slp.customer.utils.PoolUtil;
import com.youxiang.slp.customer.utils.StreamUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @projectName: tmp-hxsc-migration
 * @author: ZB
 * @date: 2024/3/26 18:11
 */
@Service
public class MiddleLayerBiz {

    private static final Logger logger = LoggerFactory.getLogger(MiddleLayerBiz.class);

    @Value("${nlh.projectId}")
    private Integer projectId;

    @Resource
    private TmpCustService tmpCustService;
    @Resource
    private CustLevelRecordService custLevelRecordService;
    @Resource
    private UserPointService userPointService;
    @Resource
    private UserPointRecordService userPointRecordService;
    @Resource
    private UserPointDetailService userPointDetailService;
    @Resource
    private UserLevelReceiveService userLevelReceiveService;
    @Resource
    private UserTaskService userTaskService;
    @Resource
    private CustTaskService custTaskService;
    @Resource
    private CustService custService;

    private Integer LIMIT = 3000;
    private Integer size = 3000;
    private static Map<String, ActivityLevelGiftDTO> map = new HashMap<>();

    static {
        map.put("silver", new ActivityLevelGiftDTO(123L, "白银等级"));
        map.put("gold", new ActivityLevelGiftDTO(456L, "黄金等级"));
        map.put("platinum", new ActivityLevelGiftDTO(789L, "铂金等级"));
        map.put("king", new ActivityLevelGiftDTO(999L, "尊享等级"));
    }

    @Transactional
    public TmpCustInfoPO handleCustInfoAndLevel(Integer projectId, UserLevelPO item) {
        TmpCustInfoPO tmpCustInfo = new TmpCustInfoPO();
        if (StringUtils.isNotBlank(item.getCustomerCode())) {
            tmpCustInfo.setIdentifyNo(SlpAesUtil.encode(item.getCustomerCode()));
            tmpCustInfo.setIdentifyType(IdentifyType.BANK_CIF.getValue());
        } else {
            tmpCustInfo.setIdentifyNo(SlpAesUtil.encode(item.getMobile()));
            tmpCustInfo.setIdentifyType(IdentifyType.MOBILE_NO.getValue());
        }
        tmpCustInfo.setAccountNo(StringUtils.isNotBlank(item.getCustomerCode()) ? SlpAesUtil.encode(item.getCustomerCode()) : "");
        tmpCustInfo.setMobile(StringUtils.isNotBlank(item.getMobile()) ? SlpAesUtil.encode(item.getMobile()) : "");
        tmpCustInfo.setCustName("白名单导入");
        tmpCustInfo.setProjectId(projectId);
        tmpCustInfo.setCustLevel(item.getLevel());
        tmpCustInfo.setCustStatus(Status.ENABLED.getValue());
        tmpCustInfo.setCustSource("深蓝-数据迁移");
        Long custId = tmpCustService.custInfoInsert(tmpCustInfo);
        CustLevelRecordPO levelRecordPO = new CustLevelRecordPO();
        levelRecordPO.setProjectId(projectId);
        levelRecordPO.setCustId(custId);
        levelRecordPO.setCustLevel(item.getLevel());
        levelRecordPO.setLevelEffectiveTime(item.getJoinTime());
        Date expire = item.getExpireTime();
        if (Objects.equals(item.getLevel(), "silver")) {
            if (item.getExpireTime() == null) {
                expire = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, "2099-12-31 00:00:00");
            }
        } else if (Objects.equals(item.getLevel(), "gold")) {
            if (item.getExpireTime() == null) {
                expire = DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, "2099-12-31 00:00:00");
            }
        } else if (Objects.equals(item.getLevel(), "platinum")) {
            if (item.getExpireTime() == null) {
                expire = DateUtils.addYears(item.getJoinTime(), 1);
            }
        } else if (Objects.equals(item.getLevel(), "king")) {
            if (item.getExpireTime() == null) {
                expire = DateUtils.addYears(item.getJoinTime(), 1);
            }
        }
        levelRecordPO.setLevelExpireTime(expire);
        levelRecordPO.setStatus(Status.ENABLED.getValue());
        custLevelRecordService.insertLevelRecord(levelRecordPO);
        return tmpCustInfo;
    }

    @Transactional
    public void syncCustIdToPointInfo(Long custId, String customerCode, String mobile) {
        userPointService.updateCustIdByCustomerCode(custId, customerCode, mobile);
        userPointRecordService.updateCustIdByCustomerCode(custId, customerCode, mobile);
        userPointDetailService.updateCustIdByCustomerCode(custId, customerCode, mobile);
    }

    public void handleCustLevelReceive(Integer projectId, Long custId, String customerCode, String mobile) {
        List<UserLevelReceivePO> list = userLevelReceiveService.getByCustomerCode(customerCode, mobile);
        if (CollectionUtils.isEmpty(list)) {
            logger.info("【同步用户等级数据】-当前用户未查询到等级奖励领取信息,不作处理,客户标识:{},用户ID:{}", customerCode, custId);
            return;
        }
        List<TmpCustBenefitModRecordPO> tmpModRecordList = new ArrayList<>();
        for (UserLevelReceivePO item : list) {
            ActivityLevelGiftDTO dto = map.get(item.getLevel());
            Date receiveTime = item.getReceiveTime();
            String bussinessId = projectId + "-" + custId + "-" + dto.getActivityId() + "-" + item.getLevel();
            TmpCustBenefitModRecordPO po = new TmpCustBenefitModRecordPO();
            po.setDataDate(receiveTime);
            po.setCustId(custId);
            po.setProjectId(projectId);
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
            tmpModRecordList.add(po);
        }
        tmpCustService.insertBatchTmpModRecord(tmpModRecordList);
    }

    public void syncCustIdToTask(Long custId, String customerCode, String mobile) {
        userTaskService.updateCustIdToAssistance(custId, customerCode, mobile);
        userTaskService.updateCustIdToBrowsingText(custId, customerCode, mobile);
        userTaskService.updateCustIdToDailyBrowsing(custId, customerCode, mobile);
        userTaskService.updateCustIdToDrawMoney(custId, customerCode, mobile);
        userTaskService.updateCustIdToOnce(custId, customerCode, mobile);
    }

    public void processingTaskData_01() {
        Integer beginId = 0;
        while (true) {
            List<UserTaskDailyBrowsingPO> list = userTaskService.selectTaskRecordBy01(beginId, LIMIT);
            if (CollectionUtils.isEmpty(list)) {
                break;
            }
            List<CustTaskPO> taskList = new ArrayList<>(list.size());
            for (UserTaskDailyBrowsingPO item : list) {
                String taskFlag = "00008" + "-" + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, item.getTime()) + "-" + item.getCustomerCode();
                CustTaskPO po = new CustTaskPO();
//                po.setCustId(item.getCustId());
                po.setUniqueId(item.getCustomerCode());
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
            custTaskService.insertBatch(taskList);
            if (list.size() < LIMIT) {
                break;
            }
            beginId = list.get(list.size() - 1).getId();
        }
    }

    public void processingTaskData_02() {
        Integer beginId = 0;
        while (true) {
            List<UserTaskBrowsingTextPO> list = userTaskService.selectTaskRecordBy02(beginId, LIMIT);
            if (CollectionUtils.isEmpty(list)) {
                break;
            }
            List<CustTaskPO> taskList = new ArrayList<>(list.size());
            for (UserTaskBrowsingTextPO item : list) {
//                String taskFlag = "00009" + "-" + item.getCustId() + "-" + item.getCustomerCode();
                CustTaskPO po = new CustTaskPO();
//                po.setCustId(item.getCustId());
                po.setUniqueId(item.getCustomerCode());
//                po.setTaskFlag(taskFlag);
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
            custTaskService.insertBatch(taskList);
            if (list.size() < LIMIT) {
                break;
            }
            beginId = list.get(list.size() - 1).getId();
        }
    }

    //TODO 询问提款笔笔送任务是否为不同等级任务项-且有额度也算一个单向任务
    public void processingTaskData_03() {
        Integer beginId = 0;
        while (true) {
            List<UserTaskDrawMoneyPO> list = userTaskService.selectTaskRecordBy03(beginId, LIMIT);
            if (CollectionUtils.isEmpty(list)) {
                break;
            }
            List<CustTaskPO> taskList = new ArrayList<>(list.size());
            for (UserTaskDrawMoneyPO item : list) {
                String level = "_4";
                if (Objects.equals(item.getDrawMoneyLevel(), "1")) {
                    level = "_1";
                } else if (Objects.equals(item.getDrawMoneyLevel(), "2")) {
                    level = "_2";
                } else if (Objects.equals(item.getDrawMoneyLevel(), "3")) {
                    level = "_3";
                }
                NlhTaskEnum taskEnum = NlhTaskEnum.getByOriFlag("isWithdrawal" + level);
                if (taskEnum == null) {
//                    logger.info("【处理任务记录】-提款笔笔送，未找到对应任务项,当前记录不处理,用户ID:{},记录ID:{}", item.getCustId(), item.getId());
                    return;
                }
                String status = "1";
                if (Objects.equals(item.getStatus(), "待领取")) {
                    status = "0";
                } else if (Objects.equals(item.getStatus(), "冻结中")) {
                    status = "2";
                } else if (Objects.equals(item.getStatus(), "已过期")) {
                    status = "3";
                }
                String taskFlag = taskEnum.getTaskCode() + "-" + DateUtils.parseDateToStr(DateUtils.YYYYMM, item.getAddTime()) + "-" + item.getCustomerCode();
                CustTaskPO po = new CustTaskPO();
//                po.setCustId(item.getCustId());
                po.setUniqueId(item.getCustomerCode());
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
            custTaskService.insertBatch(taskList);
            if (list.size() < LIMIT) {
                break;
            }
            beginId = list.get(list.size() - 1).getId();
        }
    }

    private CustTaskPO once2PO(UserTaskOncePO item) {
        String taskCode = "00011";
        String taskName = "额度激活";
        Integer pointValue = 5000;
//        String taskFlag = taskCode + "-" + item.getCustId() + "-" + item.getCustomerCode();
        if (Objects.equals(item.getTaskFlag(), "apply")) {
            taskCode = "00001";
            taskName = "成功提交宁来花申请";
            pointValue = 2000;
//            taskFlag = taskCode + "-" + item.getCustId() + "-" + item.getCustomerCode();
        } else if (Objects.equals(item.getTaskFlag(), "withdrawal")) {
            taskCode = "00002";
            taskName = "完成宁来花新客首提(额度出账30天内提款)";
            pointValue = 10000;
//            taskFlag = taskCode + "-" + item.getCustId() + "-" + item.getCustomerCode();
        }
        CustTaskPO po = new CustTaskPO();
//        po.setCustId(item.getCustId());
        po.setUniqueId(item.getCustomerCode());
//        po.setTaskFlag(taskFlag);
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
        return po;
    }

    public void processingTaskData_04() {
        Integer beginId = 0;
        while (true) {
            List<UserTaskOncePO> list = userTaskService.selectTaskRecordBy04(beginId, LIMIT);
            if (CollectionUtils.isEmpty(list)) {
                break;
            }
            List<CustTaskPO> taskList = new ArrayList<>(list.size());
            for (UserTaskOncePO item : list) {
                CustTaskPO po = once2PO(item);
                taskList.add(po);
            }
            custTaskService.insertBatch(taskList);
            if (list.size() < LIMIT) {
                break;
            }
            beginId = list.get(list.size() - 1).getId();
        }
    }

    public void processingTaskData_05() {
        Integer beginId = 0;
        while (true) {
            List<UserTaskAssistancePO> list = userTaskService.selectTaskRecordBy05(beginId, LIMIT);
            if (CollectionUtils.isEmpty(list)) {
                break;
            }
            List<CustTaskPO> taskList = new ArrayList<>(list.size());
            for (UserTaskAssistancePO item : list) {
                String taskFlag = "00010" + "-" + DateUtils.parseDateToStr(DateUtils.YYYYMMDD, item.getTime()) + "-" + item.getCustomerCode();
                CustTaskPO po = new CustTaskPO();
//                po.setCustId(item.getCustId());
                po.setUniqueId(item.getCustomerCode());
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
                po.setRemark(item.getOriCustomerCode() + "-助力人标识");
                po.setAddTime(item.getTime());
                po.setUpdateTime(item.getTime());
                taskList.add(po);
            }
            custTaskService.insertBatch(taskList);
            if (list.size() < LIMIT) {
                break;
            }
            beginId = list.get(list.size() - 1).getId();
        }
    }

    @Transactional
    public void handleLegacyUser(UserMobilePointPO item) {
        TmpCustInfoPO custPO = new TmpCustInfoPO();
        String mobile = SlpAesUtil.encode(item.getMobile());
        custPO.setAccountNo("");
        custPO.setMobile(mobile);
        custPO.setIdentifyNo(mobile);
        custPO.setIdentifyType(IdentifyType.MOBILE_NO.getValue());
        custPO.setCustName("白名单导入");
        custPO.setProjectId(projectId);
        custPO.setCustLevel("silver");
        custPO.setCustStatus(Status.ENABLED.getValue());
        custPO.setCustSource("深蓝-数据迁移-一期遗留");
        Long custId = tmpCustService.custInfoInsert(custPO);
        if (custId == null || custId == 0L) {
            logger.error("【遗留用户积分信息】-插入用户信息异常,未正常返回custId,手机号:{}", item.getMobile());
            return;
        }
        Date date = new Date();
        TmpCustBenefitPO custBenefitPO = new TmpCustBenefitPO();
        custBenefitPO.setProjectId(projectId);
        custBenefitPO.setCustId(custId);
        custBenefitPO.setPlanId(0L);
        custBenefitPO.setBenefitName(BenefitType.POINT.getLable());
        custBenefitPO.setBenefitType(BenefitType.POINT.getValue());
        custBenefitPO.setTotalPoint(item.getPointValue());
        custBenefitPO.setPointValue(item.getPointValue());
        custBenefitPO.setPointUnit(PriceUnit.POINT.getValue());
        custBenefitPO.setBenefitEffectiveTime(date);
        custBenefitPO.setBenefitExpireTime(DateUtils.parseDate("2025-04-30 23:59:59", DateUtils.YYYY_MM_DD_HH_MM_SS));
        custBenefitPO.setStatus(Status.ENABLED.getValue());
        custBenefitPO.setBenefitSource("SL-DATA-MIGRATION");
        custBenefitPO.setAddDate(new Date());
        custBenefitPO.setAddTime(date);
        custBenefitPO.setModTime(date);
        tmpCustService.insertBatchTmpBenefit(Arrays.asList(custBenefitPO));
        String bussinessId = projectId + "-" + custId + "-" + DateUtils.parseDateToStr(DateUtils.YYYYMMDDHHMMSS, date);
        TmpCustBenefitModRecordPO modRecordPO = new TmpCustBenefitModRecordPO();
        modRecordPO.setDataDate(date);
        modRecordPO.setCustId(custId);
        modRecordPO.setProjectId(projectId);
        modRecordPO.setPlanId(0L);
        modRecordPO.setBenefitId(0L);
        modRecordPO.setAddSubtractFlag(AddSubtractFlag.ADD.getValue());
        modRecordPO.setWhitelistBatchId(0L);
        modRecordPO.setBussinessId(bussinessId);
        modRecordPO.setModType(PointModType.nlhPointAdd.getValue());
        modRecordPO.setModValue(item.getPointValue());// 后台修改增加点数
        modRecordPO.setModPoint(item.getPointValue()); // 变动后权益点数
        modRecordPO.setPointUnit(PriceUnit.POINT.getValue());
        modRecordPO.setBenefitExpireTime(DateUtils.parseDate("2025-04-30 23:59:59", DateUtils.YYYY_MM_DD_HH_MM_SS));
        modRecordPO.setUniqueCode("");
        modRecordPO.setStatus(Status.ENABLED.getValue());
        modRecordPO.setUseStatus(UseStatus.HAS_RECEIVED.getValue());
        modRecordPO.setWriteOffStatus(WriteOffStatus.HAS_WRITE_OFF.getValue());
        modRecordPO.setOrderCode("");
        modRecordPO.setDescription("迁移积分核算");
        modRecordPO.setBenefitSource("SL-DATA-MIGRATION");
        modRecordPO.setAddTime(date);
        modRecordPO.setModTime(date);
        tmpCustService.insertBatchTmpModRecord(Arrays.asList(modRecordPO));
        CustLevelRecordPO custLevelRecordPO = new CustLevelRecordPO();
        CustLevelRecordPO levelRecordPO = new CustLevelRecordPO();
        levelRecordPO.setProjectId(projectId);
        levelRecordPO.setCustId(custId);
        levelRecordPO.setCustLevel("silver");
        levelRecordPO.setLevelEffectiveTime(date);
        levelRecordPO.setLevelExpireTime(DateUtils.dateTime(DateUtils.YYYY_MM_DD_HH_MM_SS, "2099-12-31 23:59:59"));
        levelRecordPO.setStatus(Status.ENABLED.getValue());
        custLevelRecordService.insertLevelRecord(custLevelRecordPO);
        logger.error("【遗留用户积分信息】-插入用户信息完成,custId:{},手机号:{}", custId, item.getMobile());
    }

    public void insertByKey0(List<Future<String>> featureList, List<CustBenefitPO> item) {
        List<List<CustBenefitPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_0` (`project_id`, `cust_id`, `plan_id`, `benefit_name`,\n" +
//                    "        `benefit_type`, `total_point`, `point_value`, `point_unit`, `benefit_effective_time`,\n" +
//                    "        `benefit_expire_time`, `status`, `benefit_source`, `add_date`, `add_time`, `mod_time`)\n" +
//                    "        VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitPO benefitPO = itemList.get(i);
                builder.append(benefitPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchBenefit--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchBenefit0(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchBenefit:" + tid + ":插入成功";
                } catch (Exception e) {
                    logger.error("insertBatchBenefit--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchBenefit:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertByKey1(List<Future<String>> featureList, List<CustBenefitPO> item) {
        List<List<CustBenefitPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_1` (`project_id`, `cust_id`, `plan_id`, `benefit_name`,\n" +
//                    "        `benefit_type`, `total_point`, `point_value`, `point_unit`, `benefit_effective_time`,\n" +
//                    "        `benefit_expire_time`, `status`, `benefit_source`, `add_date`, `add_time`, `mod_time`)\n" +
//                    "        VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitPO benefitPO = itemList.get(i);
                builder.append(benefitPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchBenefit--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchBenefit1(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchBenefit:" + tid + ":插入成功";
                } catch (Exception e) {
                    logger.error("insertBatchBenefit--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchBenefit:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertByKey2(List<Future<String>> featureList, List<CustBenefitPO> item) {
        List<List<CustBenefitPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_2` (`project_id`, `cust_id`, `plan_id`, `benefit_name`,\n" +
//                    "        `benefit_type`, `total_point`, `point_value`, `point_unit`, `benefit_effective_time`,\n" +
//                    "        `benefit_expire_time`, `status`, `benefit_source`, `add_date`, `add_time`, `mod_time`)\n" +
//                    "        VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitPO benefitPO = itemList.get(i);
                builder.append(benefitPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchBenefit--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchBenefit2(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchBenefit:" + tid + ":插入成功";
                } catch (Exception e) {
                    logger.error("insertBatchBenefit--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchBenefit:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertByKey3(List<Future<String>> featureList, List<CustBenefitPO> item) {
        List<List<CustBenefitPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_3` (`project_id`, `cust_id`, `plan_id`, `benefit_name`,\n" +
//                    "        `benefit_type`, `total_point`, `point_value`, `point_unit`, `benefit_effective_time`,\n" +
//                    "        `benefit_expire_time`, `status`, `benefit_source`, `add_date`, `add_time`, `mod_time`)\n" +
//                    "        VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitPO benefitPO = itemList.get(i);
                builder.append(benefitPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchBenefit--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchBenefit3(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchBenefit:" + tid + ":插入成功";
                } catch (Exception e) {
                    logger.error("insertBatchBenefit--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchBenefit:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertByKey4(List<Future<String>> featureList, List<CustBenefitPO> item) {
        List<List<CustBenefitPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_4` (`project_id`, `cust_id`, `plan_id`, `benefit_name`,\n" +
//                    "        `benefit_type`, `total_point`, `point_value`, `point_unit`, `benefit_effective_time`,\n" +
//                    "        `benefit_expire_time`, `status`, `benefit_source`, `add_date`, `add_time`, `mod_time`)\n" +
//                    "        VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitPO benefitPO = itemList.get(i);
                builder.append(benefitPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchBenefit--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchBenefit4(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchBenefit:" + tid + ":插入成功";
                } catch (Exception e) {
                    logger.error("insertBatchBenefit--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchBenefit:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertByKey5(List<Future<String>> featureList, List<CustBenefitPO> item) {
        List<List<CustBenefitPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_5` (`project_id`, `cust_id`, `plan_id`, `benefit_name`,\n" +
//                    "        `benefit_type`, `total_point`, `point_value`, `point_unit`, `benefit_effective_time`,\n" +
//                    "        `benefit_expire_time`, `status`, `benefit_source`, `add_date`, `add_time`, `mod_time`)\n" +
//                    "        VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitPO benefitPO = itemList.get(i);
                builder.append(benefitPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchBenefit--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchBenefit5(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchBenefit:" + tid + ":插入成功";
                } catch (Exception e) {
                    logger.error("insertBatchBenefit--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchBenefit:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertByKey6(List<Future<String>> featureList, List<CustBenefitPO> item) {
        List<List<CustBenefitPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_6` (`project_id`, `cust_id`, `plan_id`, `benefit_name`,\n" +
//                    "        `benefit_type`, `total_point`, `point_value`, `point_unit`, `benefit_effective_time`,\n" +
//                    "        `benefit_expire_time`, `status`, `benefit_source`, `add_date`, `add_time`, `mod_time`)\n" +
//                    "        VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitPO benefitPO = itemList.get(i);
                builder.append(benefitPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchBenefit--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchBenefit6(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchBenefit:" + tid + ":插入成功";
                } catch (Exception e) {
                    logger.error("insertBatchBenefit--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchBenefit:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertByKey7(List<Future<String>> featureList, List<CustBenefitPO> item) {
        List<List<CustBenefitPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_7` (`project_id`, `cust_id`, `plan_id`, `benefit_name`,\n" +
//                    "        `benefit_type`, `total_point`, `point_value`, `point_unit`, `benefit_effective_time`,\n" +
//                    "        `benefit_expire_time`, `status`, `benefit_source`, `add_date`, `add_time`, `mod_time`)\n" +
//                    "        VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitPO benefitPO = itemList.get(i);
                builder.append(benefitPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchBenefit--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchBenefit7(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchBenefit:" + tid + ":插入成功";
                } catch (Exception e) {
                    logger.error("insertBatchBenefit--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchBenefit:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertModRecordByKey0(List<Future<String>> featureList, List<CustBenefitModRecordPO> item) {
        List<List<CustBenefitModRecordPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitModRecordPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_mod_record_0` (`data_date`, `cust_id`, `project_id`, `plan_id`, `whitelist_batch_id`,\n" +
//                    " `benefit_id`, `bussiness_id`, `add_subtract_flag`, `mod_type`, `mod_value`, `mod_point`,\n" +
//                    " `point_unit`, `status`, `use_status`, `unique_code`, `write_off_status`, `benefit_source`,\n" +
//                    " `benefit_expire_time`, `description`, `order_code`, `add_time`, `mod_time`)\n" +
//                    " VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitModRecordPO recordPO = itemList.get(i);
                builder.append(recordPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchModRecord--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchModRecord0(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchModRecord:" + tid + ":插入成功!";
                } catch (Exception e) {
                    logger.error("insertBatchModRecord--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchModRecord:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertModRecordByKey1(List<Future<String>> featureList, List<CustBenefitModRecordPO> item) {
        List<List<CustBenefitModRecordPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitModRecordPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_mod_record_1` (`data_date`, `cust_id`, `project_id`, `plan_id`, `whitelist_batch_id`,\n" +
//                    " `benefit_id`, `bussiness_id`, `add_subtract_flag`, `mod_type`, `mod_value`, `mod_point`,\n" +
//                    " `point_unit`, `status`, `use_status`, `unique_code`, `write_off_status`, `benefit_source`,\n" +
//                    " `benefit_expire_time`, `description`, `order_code`, `add_time`, `mod_time`)\n" +
//                    " VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitModRecordPO recordPO = itemList.get(i);
                builder.append(recordPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchModRecord--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchModRecord1(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchModRecord:" + tid + ":插入成功!";
                } catch (Exception e) {
                    logger.error("insertBatchModRecord--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchModRecord:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertModRecordByKey2(List<Future<String>> featureList, List<CustBenefitModRecordPO> item) {
        List<List<CustBenefitModRecordPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitModRecordPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_mod_record_2` (`data_date`, `cust_id`, `project_id`, `plan_id`, `whitelist_batch_id`,\n" +
//                    " `benefit_id`, `bussiness_id`, `add_subtract_flag`, `mod_type`, `mod_value`, `mod_point`,\n" +
//                    " `point_unit`, `status`, `use_status`, `unique_code`, `write_off_status`, `benefit_source`,\n" +
//                    " `benefit_expire_time`, `description`, `order_code`, `add_time`, `mod_time`)\n" +
//                    " VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitModRecordPO recordPO = itemList.get(i);
                builder.append(recordPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchModRecord--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchModRecord2(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchModRecord:" + tid + ":插入成功!";
                } catch (Exception e) {
                    logger.error("insertBatchModRecord--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchModRecord:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertModRecordByKey3(List<Future<String>> featureList, List<CustBenefitModRecordPO> item) {
        List<List<CustBenefitModRecordPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitModRecordPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_mod_record_3` (`data_date`, `cust_id`, `project_id`, `plan_id`, `whitelist_batch_id`,\n" +
//                    " `benefit_id`, `bussiness_id`, `add_subtract_flag`, `mod_type`, `mod_value`, `mod_point`,\n" +
//                    " `point_unit`, `status`, `use_status`, `unique_code`, `write_off_status`, `benefit_source`,\n" +
//                    " `benefit_expire_time`, `description`, `order_code`, `add_time`, `mod_time`)\n" +
//                    " VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitModRecordPO recordPO = itemList.get(i);
                builder.append(recordPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchModRecord--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchModRecord3(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchModRecord:" + tid + ":插入成功!";
                } catch (Exception e) {
                    logger.error("insertBatchModRecord--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchModRecord:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertModRecordByKey4(List<Future<String>> featureList, List<CustBenefitModRecordPO> item) {
        List<List<CustBenefitModRecordPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitModRecordPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_mod_record_4` (`data_date`, `cust_id`, `project_id`, `plan_id`, `whitelist_batch_id`,\n" +
//                    " `benefit_id`, `bussiness_id`, `add_subtract_flag`, `mod_type`, `mod_value`, `mod_point`,\n" +
//                    " `point_unit`, `status`, `use_status`, `unique_code`, `write_off_status`, `benefit_source`,\n" +
//                    " `benefit_expire_time`, `description`, `order_code`, `add_time`, `mod_time`)\n" +
//                    " VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitModRecordPO recordPO = itemList.get(i);
                builder.append(recordPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchModRecord--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchModRecord4(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchModRecord:" + tid + ":插入成功!";
                } catch (Exception e) {
                    logger.error("insertBatchModRecord--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchModRecord:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertModRecordByKey5(List<Future<String>> featureList, List<CustBenefitModRecordPO> item) {
        List<List<CustBenefitModRecordPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitModRecordPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_mod_record_5` (`data_date`, `cust_id`, `project_id`, `plan_id`, `whitelist_batch_id`,\n" +
//                    " `benefit_id`, `bussiness_id`, `add_subtract_flag`, `mod_type`, `mod_value`, `mod_point`,\n" +
//                    " `point_unit`, `status`, `use_status`, `unique_code`, `write_off_status`, `benefit_source`,\n" +
//                    " `benefit_expire_time`, `description`, `order_code`, `add_time`, `mod_time`)\n" +
//                    " VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitModRecordPO recordPO = itemList.get(i);
                builder.append(recordPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchModRecord--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchModRecord5(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchModRecord:" + tid + ":插入成功!";
                } catch (Exception e) {
                    logger.error("insertBatchModRecord--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchModRecord:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertModRecordByKey6(List<Future<String>> featureList, List<CustBenefitModRecordPO> item) {
        List<List<CustBenefitModRecordPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitModRecordPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_mod_record_6` (`data_date`, `cust_id`, `project_id`, `plan_id`, `whitelist_batch_id`,\n" +
//                    " `benefit_id`, `bussiness_id`, `add_subtract_flag`, `mod_type`, `mod_value`, `mod_point`,\n" +
//                    " `point_unit`, `status`, `use_status`, `unique_code`, `write_off_status`, `benefit_source`,\n" +
//                    " `benefit_expire_time`, `description`, `order_code`, `add_time`, `mod_time`)\n" +
//                    " VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitModRecordPO recordPO = itemList.get(i);
                builder.append(recordPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchModRecord--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchModRecord6(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchModRecord:" + tid + ":插入成功!";
                } catch (Exception e) {
                    logger.error("insertBatchModRecord--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchModRecord:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    public void insertModRecordByKey7(List<Future<String>> featureList, List<CustBenefitModRecordPO> item) {
        List<List<CustBenefitModRecordPO>> pageData = StreamUtil.pageSplit(item, size);
        for (List<CustBenefitModRecordPO> itemList : pageData) {
            StringBuilder builder = new StringBuilder();
//            builder.append("INSERT INTO `c_cust_benefit_mod_record_7` (`data_date`, `cust_id`, `project_id`, `plan_id`, `whitelist_batch_id`,\n" +
//                    " `benefit_id`, `bussiness_id`, `add_subtract_flag`, `mod_type`, `mod_value`, `mod_point`,\n" +
//                    " `point_unit`, `status`, `use_status`, `unique_code`, `write_off_status`, `benefit_source`,\n" +
//                    " `benefit_expire_time`, `description`, `order_code`, `add_time`, `mod_time`)\n" +
//                    " VALUES");
            for (int i = 0; i < itemList.size(); i++) {
                CustBenefitModRecordPO recordPO = itemList.get(i);
                builder.append(recordPO.toString());
                if (i < itemList.size() - 1) {
                    builder.append(",");
                }
            }
//            Integer integer = JDBCUtils.insertSql(builder.toString());
            Future<String> future = PoolUtil.getService2().submit(() -> {
                String tid = RandomStringUtils.randomNumeric(12);
                try {
                    logger.info("insertBatchModRecord--->>>tid:{},size:{},开始插入!", tid, itemList.size());
                    long t1 = System.currentTimeMillis();
                    custService.insertBatchModRecord7(builder.toString());
                    long t2 = System.currentTimeMillis();
                    logger.info("insertBatchModRecord--->>>tid:{},size:{}!耗时:{}ms", tid, itemList.size(), t2 - t1);
                    return "insertBatchModRecord:" + tid + ":插入成功!";
                } catch (Exception e) {
                    logger.error("insertBatchModRecord--->>>tid:{},插入异常:{}", tid, e.getMessage());
                    return "insertBatchModRecord:" + tid + ":插入失败:" + e.getMessage();
                }
            });
            featureList.add(future);
        }
    }

    @Transactional
    public void handleCustPointAndRecord(List<CustBenefitModRecordPO> addModRecordList, List<CustBenefitModRecordPO> subAndFreezeList, List<CustBenefitPO> benefitPOList) {
        List<Future<String>> featureList = new ArrayList<>(80);
        if (!CollectionUtils.isEmpty(benefitPOList)) {
            Map<Long, List<CustBenefitPO>> benefitPOListMap = new HashMap<>(8);
            for (CustBenefitPO item : benefitPOList) {
                Long index = item.getCustId() % 8;
                List<CustBenefitPO> list = benefitPOListMap.get(index);
                if (list == null) {
                    list = new ArrayList<>(5000);
                    benefitPOListMap.put(index, list);
                }
                list.add(item);
            }
            for (Long key : benefitPOListMap.keySet()) {
                List<CustBenefitPO> item = benefitPOListMap.get(key);
                if (CollectionUtils.isEmpty(item)) continue;
                if (key % 8 == 0) {
                    insertByKey0(featureList, item);
                } else if (key % 8 == 1) {
                    insertByKey1(featureList, item);
                } else if (key % 8 == 2) {
                    insertByKey2(featureList, item);
                } else if (key % 8 == 3) {
                    insertByKey3(featureList, item);
                } else if (key % 8 == 4) {
                    insertByKey4(featureList, item);
                } else if (key % 8 == 5) {
                    insertByKey5(featureList, item);
                } else if (key % 8 == 6) {
                    insertByKey6(featureList, item);
                } else if (key % 8 == 7) {
                    insertByKey7(featureList, item);
                }
            }
        }

        if (!CollectionUtils.isEmpty(addModRecordList)) {
            Map<Long, List<CustBenefitModRecordPO>> addModRecordListMap = new HashMap<>(8);
            for (CustBenefitModRecordPO item : addModRecordList) {
                Long index = item.getCustId() % 8;
                List<CustBenefitModRecordPO> list = addModRecordListMap.get(index);
                if (list == null) {
                    list = new ArrayList<>(5000);
                    addModRecordListMap.put(index, list);
                }
                list.add(item);
            }
            for (Long key : addModRecordListMap.keySet()) {
                List<CustBenefitModRecordPO> item = addModRecordListMap.get(key);
                if (CollectionUtils.isEmpty(item)) continue;
                if (key % 8 == 0) {
                    insertModRecordByKey0(featureList, item);
                } else if (key % 8 == 1) {
                    insertModRecordByKey1(featureList, item);
                } else if (key % 8 == 2) {
                    insertModRecordByKey2(featureList, item);
                } else if (key % 8 == 3) {
                    insertModRecordByKey3(featureList, item);
                } else if (key % 8 == 4) {
                    insertModRecordByKey4(featureList, item);
                } else if (key % 8 == 5) {
                    insertModRecordByKey5(featureList, item);
                } else if (key % 8 == 6) {
                    insertModRecordByKey6(featureList, item);
                } else if (key % 8 == 7) {
                    insertModRecordByKey7(featureList, item);
                }
            }
        }

        if (!CollectionUtils.isEmpty(subAndFreezeList)) {
            Map<Long, List<CustBenefitModRecordPO>> subAndFreezeListMap = new HashMap<>(8);
            for (CustBenefitModRecordPO item : subAndFreezeList) {
                Long index = item.getCustId() % 8;
                List<CustBenefitModRecordPO> list = subAndFreezeListMap.get(index);
                if (list == null) {
                    list = new ArrayList<>(5000);
                    subAndFreezeListMap.put(index, list);
                }
                list.add(item);
            }
            for (Long key : subAndFreezeListMap.keySet()) {
                List<CustBenefitModRecordPO> item = subAndFreezeListMap.get(key);
                if (CollectionUtils.isEmpty(item)) continue;
                if (key % 8 == 0) {
                    insertModRecordByKey0(featureList, item);
                } else if (key % 8 == 1) {
                    insertModRecordByKey1(featureList, item);
                } else if (key % 8 == 2) {
                    insertModRecordByKey2(featureList, item);
                } else if (key % 8 == 3) {
                    insertModRecordByKey3(featureList, item);
                } else if (key % 8 == 4) {
                    insertModRecordByKey4(featureList, item);
                } else if (key % 8 == 5) {
                    insertModRecordByKey5(featureList, item);
                } else if (key % 8 == 6) {
                    insertModRecordByKey6(featureList, item);
                } else if (key % 8 == 7) {
                    insertModRecordByKey7(featureList, item);
                }
            }
        }
        long t1 = System.currentTimeMillis();
        for (Future<String> item : featureList) {
            try {
                String result = item.get();
                logger.info("线程任务结果:{}", result);
            } catch (InterruptedException e) {
                logger.error("线程任务异常1:{}", e.getMessage());
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                logger.error("线程任务异常2:{}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
        long t2 = System.currentTimeMillis();
        logger.error("checkFuture--->>>耗时:{}ms", t2 - t1);
    }
}
