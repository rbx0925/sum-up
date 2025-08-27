package com.youxiang.slp.customer.biz;

import com.alibaba.fastjson.JSON;
import com.youxiang.slp.common.utils.DateUtils;
import com.youxiang.slp.customer.adapter.CustInfoAdapter;
import com.youxiang.slp.customer.adapter.CustModRecordAdapter;
import com.youxiang.slp.customer.domain.dto.UserFlagDTO;
import com.youxiang.slp.customer.domain.po.*;
import com.youxiang.slp.customer.service.*;
import com.youxiang.slp.customer.utils.StreamUtil;
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
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @projectName: tmp-hxsc-migration
 * @author: ZB
 * @date: 2024/4/13 17:08
 */
@Service
public class SecondLevelBiz {

    private static final Logger logger = LoggerFactory.getLogger(SecondLevelBiz.class);

    private Integer max = 300;
    private Integer LIMIT = 3000;

    @Value("${nlh.projectId}")
    private Integer projectId;

    @Resource
    private MiddleLayerBiz middleLayerBiz;
    @Resource
    private UserPointDetailService userPointDetailService;
    @Resource
    private UserPointService userPointService;
    @Resource
    private UserPointRecordService userPointRecordService;
    @Resource
    private UserLevelService userLevelService;
    @Resource
    private UserTaskService userTaskService;
    @Resource
    private CustService custService;
    @Resource
    private CustTaskService custTaskService;
    @Resource
    private UserLevelReceiveService userLevelReceiveService;
    @Resource
    private CustLevelRecordService custLevelRecordService;

    public void user(List<UserLevelPO> levelList) {
        List<CustInfoPO> custInfoPOList = new ArrayList<>(levelList.size());
        levelList.forEach(item -> {
            CustInfoPO custInfoPO = CustInfoAdapter.level2PO(item, projectId);
            custInfoPOList.add(custInfoPO);
        });
        custService.insertBatch(custInfoPOList);
    }

    @Transactional
    public Map<String, Object> handleUserData(List<UserLevelPO> levelList) {
        List<CustInfoPO> custInfoPOList = new ArrayList<>(levelList.size());
        Map<String, CustLevelRecordPO> levelRecordPOMap = new HashMap<>(levelList.size());
        levelList.forEach(item -> {
            CustInfoPO custInfoPO = CustInfoAdapter.level2PO(item, projectId);
            custInfoPOList.add(custInfoPO);
            CustLevelRecordPO custLevelRecordPO = CustInfoAdapter.levelRecord2PO(item, projectId, null);
            levelRecordPOMap.put(item.getCustomerCode() + item.getMobile(), custLevelRecordPO);
        });
        custService.insertBatch(custInfoPOList);

        //用户基本信息映射及查询条件
        List<CustInfoPO> poList = custService.selectByCustomerCodeAndMobile(custInfoPOList);
        List<UserFlagDTO> dtoList = poList.stream().map(CustInfoAdapter::po2DTO).collect(Collectors.toList());
        //初始化用户信息MAP,处理用户等级记录
        Map<String, UserFlagDTO> map = new HashMap<>(dtoList.size());
        List<CustLevelRecordPO> custLevelRecordPOList = new ArrayList<>(dtoList.size());
        for (UserFlagDTO item : dtoList) {
            String key = item.getCustomerCode() + item.getMobile();
            map.put(key, item);
            CustLevelRecordPO custLevelRecordPO = levelRecordPOMap.get(key);
            if (custLevelRecordPO != null) {
                custLevelRecordPO.setCustId(item.getCustId());
                custLevelRecordPOList.add(custLevelRecordPO);
            }
        }
        if (!CollectionUtils.isEmpty(custLevelRecordPOList)) {
            custLevelRecordService.insertBatachLevelRecord(custLevelRecordPOList);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("dtoList", dtoList);
        resultMap.put("map", map);
        return resultMap;
    }

    @Transactional
    public void handleOtherData(List<UserFlagDTO> dtoList, Map<String, UserFlagDTO> map) {
        long beginTime_task = System.currentTimeMillis();
        logger.info("【处理任务信息】--->开始处理!");
        List<CustTaskPO> taskPOList = userTaskService.selectTaskRecordByList(dtoList, map);
        if (!CollectionUtils.isEmpty(taskPOList)) {
            custTaskService.insertBatch(taskPOList);
            logger.info("【处理任务信息】--->处理完成,耗时:{}秒!", (System.currentTimeMillis() - beginTime_task) / 1000);
        }
        long beginTime_received = System.currentTimeMillis();
        logger.info("【处理等级领取信息】--->开始处理!");
        Map<Long, List<CustBenefitModRecordPO>> resultMap = userLevelReceiveService.selectByList(dtoList, map);
        if (!CollectionUtils.isEmpty(resultMap)) {
            List<Future<String>> featureList = new ArrayList<>(80);
            for (Long key : resultMap.keySet()) {
                List<CustBenefitModRecordPO> item = resultMap.get(key);
                if (CollectionUtils.isEmpty(item)) {
                    continue;
                }
                if (key % 8 == 0) {
                    middleLayerBiz.insertModRecordByKey0(featureList, item);
                } else if (key % 8 == 1) {
                    middleLayerBiz.insertModRecordByKey1(featureList, item);
                } else if (key % 8 == 2) {
                    middleLayerBiz.insertModRecordByKey2(featureList, item);
                } else if (key % 8 == 3) {
                    middleLayerBiz.insertModRecordByKey3(featureList, item);
                } else if (key % 8 == 4) {
                    middleLayerBiz.insertModRecordByKey4(featureList, item);
                } else if (key % 8 == 5) {
                    middleLayerBiz.insertModRecordByKey5(featureList, item);
                } else if (key % 8 == 6) {
                    middleLayerBiz.insertModRecordByKey6(featureList, item);
                } else if (key % 8 == 7) {
                    middleLayerBiz.insertModRecordByKey7(featureList, item);
                }
            }
            logger.info("【处理等级领取信息】--->处理完成,耗时:{}秒!", (System.currentTimeMillis() - beginTime_received) / 1000);
        }
    }

//    public void handlePointData(List<UserFlagDTO> dtoList1, Map<String, UserFlagDTO> map) {
//        Map<String, CustBenefitPO> benefitPOMap = userPointService.getByMap(dtoList1, map);
//        StreamUtil.forEach(dtoList1, 1, 100, (dataList1) -> {
//            long beginTime_point = System.currentTimeMillis();
//            logger.info("【分页处理积分信息】--->开始处理,起始用户ID:{}!", dataList1.get(0).getCustId());
//            //核算积分-拆分内容:增加记录,扣减和冻结记录,总增分,总扣分,总冻结分
//            Map<String, Object> userPointMap1 = userPointRecordService.getByMap(dataList1, map);
//            Map<String, List<UserPointRecordPO>> addListMap1 = (Map<String, List<UserPointRecordPO>>) userPointMap1.get("addList");
//            Map<String, List<Integer>> detailIdMap1 = (Map<String, List<Integer>>) userPointMap1.get("detailIds");
//            Map<String, List<CustBenefitModRecordPO>> subAndFreezeListMap = (Map<String, List<CustBenefitModRecordPO>>) userPointMap1.get("subAndFreezeList");
//            //记录用户增分明细
//            List<Integer> detailIdsExt1 = new ArrayList<>(120000);
//            List<UserPointRecordPO> recordPOListExt1 = new ArrayList<>(120000);
//            int count = 0;
//            for (String key1 : addListMap1.keySet()) {
//                count++;
//                List<UserPointRecordPO> recordPOList1 = addListMap1.get(key1);
//                UserFlagDTO userFlagDTO1 = map.get(key1);
//                if (userFlagDTO1 == null) {
//                    logger.info("【核算积分】-未找到对应用户信息,当前记录不处理,用户标识:{}", key1);
//                    continue;
//                }
//                List<Integer> detailIds1 = detailIdMap1.get(key1);
//                detailIdsExt1.addAll(detailIds1);
//                recordPOListExt1.addAll(recordPOList1);
//                if (detailIdsExt1.size() < 100000) {
//                    if (count < addListMap1.keySet().size()) continue;
//                }
//                long l = System.currentTimeMillis();
//                Map<Integer, UserPointDetailPO> detailMap2 = userPointDetailService.mapByDetailIds(detailIds1);
//                logger.info("【核算积分详情】getByMap--->>>detailMap2.size-:{}查询耗时:{}秒", detailMap2.size(), (System.currentTimeMillis() - l) / 1000);
//                //记录用户余额明细
//                Map<String, Map<Date, BigDecimal>> totalPointMap2 = new HashMap<>(100);
//                Map<String, Map<Date, BigDecimal>> enablePointMap2 = new HashMap<>(100);
//                List<CustBenefitModRecordPO> addModRecordList2 = new ArrayList<>();
//                Set<String> currentBatchUser = new HashSet<>();
//                //核算单个用户进项积分
//                for (UserPointRecordPO item : recordPOList1) {
//                    String key2 = item.getCustomerCode() + item.getMobile();
//                    UserPointDetailPO detailPO = detailMap2.get(item.getDetailId());
//                    if (detailPO == null) {
//                        logger.info("【核算积分】-增项记录转换,未查询到详情明细,跳过本条记录,用户标识:{}", item.getCustomerCode() + "-" + item.getMobile());
//                        continue;
//                    }
//                    currentBatchUser.add(key2);
//                    //计算当月累计积分
//                    Map<Date, BigDecimal> dateBigDecimalMap = totalPointMap2.get(key2);
//                    if (CollectionUtils.isEmpty(dateBigDecimalMap)) {
//                        dateBigDecimalMap = new HashMap<>();
//                        totalPointMap2.put(key2, dateBigDecimalMap);
//                    }
//                    BigDecimal totalPoint = dateBigDecimalMap.get(detailPO.getExpireTime());
//                    if (totalPoint == null) {
//                        dateBigDecimalMap.put(detailPO.getExpireTime(), item.getPointValue());
//                    } else {
//                        dateBigDecimalMap.put(detailPO.getExpireTime(), totalPoint.add(item.getPointValue()));
//                    }
//
//                    Date now = new Date();
//                    if (detailPO.getExpireTime().before(now)) {
//                        //计算已过期积分
//                    } else {
//                        //计算当月未过期可用积分
//                        Map<Date, BigDecimal> dateEnableMap = enablePointMap2.get(key2);
//                        if (CollectionUtils.isEmpty(dateEnableMap)) {
//                            dateEnableMap = new HashMap<>();
//                            enablePointMap2.put(key2, dateEnableMap);
//                        }
//                        BigDecimal enablePoint = dateEnableMap.get(detailPO.getExpireTime());
//                        if (enablePoint == null) {
//                            dateEnableMap.put(detailPO.getExpireTime(), detailPO.getEnablePoint());
//                        } else {
//                            dateEnableMap.put(detailPO.getExpireTime(), enablePoint.add(detailPO.getEnablePoint()));
//                        }
//                    }
//                    UserFlagDTO userFlagDTO2 = map.get(key2);
//                    CustBenefitModRecordPO addModRecordPO = CustModRecordAdapter.add2ModRecordPO(item, detailPO, userFlagDTO2.getProjectId(), userFlagDTO2.getCustId());
//                    addModRecordList2.add(addModRecordPO);
//                }
//                detailIdsExt1 = new ArrayList<>(120000);
//                recordPOListExt1 = new ArrayList<>(120000);
//
//                Map<String, BigDecimal> sumAddMap = (Map<String, BigDecimal>) userPointMap1.get("sumAdd");
//                Map<String, BigDecimal> sumSubMap = (Map<String, BigDecimal>) userPointMap1.get("sumSub");
//                Map<String, BigDecimal> sumFreezeMap = (Map<String, BigDecimal>) userPointMap1.get("sumFreeze");
//                List<String> keyList = new ArrayList<>();
//                List<CustBenefitModRecordPO> subAndFreezeList = new ArrayList<>(10000);
//                for (String key : currentBatchUser) {
//                    CustBenefitPO custBenefitPO = benefitPOMap.get(key);
//                    if (custBenefitPO == null) {
//                        logger.info("【核算积分】-未找到对应用户余额信息,当前记录不处理,用户标识:{}", key);
//                        continue;
//                    }
//                    UserFlagDTO userFlagDTO = map.get(key);
//                    if (userFlagDTO == null) {
//                        logger.info("【核算积分】-未找到对应用户信息,当前记录不处理,用户标识:{}", custBenefitPO.getCustId());
//                        continue;
//                    }
//                    BigDecimal sumAdd = sumAddMap.get(key);
//                    if (sumAdd == null) {
//                        sumAdd = BigDecimal.ZERO;
//                    }
//                    BigDecimal sumSub = sumSubMap.get(key);
//                    if (sumSub == null) {
//                        sumSub = BigDecimal.ZERO;
//                    }
//                    BigDecimal sumFreeze = sumFreezeMap.get(key);
//                    if (sumFreeze == null) {
//                        sumFreeze = BigDecimal.ZERO;
//                    }
//                    BigDecimal expirePoint3 = new BigDecimal(custBenefitPO.getRemark());
//                    //计算规则-(总进项-总出项-总冻结比较余额信息的可用积分)
////                    if (sumAdd.subtract(sumSub).subtract(expirePoint3).subtract(sumFreeze).compareTo(custBenefitPO.getPointValue()) != 0) {
////                        logger.info("【核算积分】-当前用户核算积分,用户ID{},用户标识:{},可用积分:{},增项累计积分:{},减项累计积分:{},冻结累计积分:{},过期累计积分:{}"
////                                , userFlagDTO.getCustId(), userFlagDTO.getCustomerCode() + "-" + userFlagDTO.getMobile(), custBenefitPO.getPointValue(), sumAdd, sumSub, sumFreeze, expirePoint3);
////                        continue;
////                    }
//                    logger.info("【核算积分】-当前用户核算积分校验通过,用户ID:{},用户标识:{}", userFlagDTO.getCustId(), key);
//                    keyList.add(key);
//                    List<CustBenefitModRecordPO> modRecordPOList = subAndFreezeListMap.get(key);
//                    if (!CollectionUtils.isEmpty(modRecordPOList)) {
//                        subAndFreezeList.addAll(modRecordPOList);
//                    }
//                }
//                logger.info("【分页处理积分信息-单批处理】-用户余额核算,本次查询计算完成,耗时:{}秒", (System.currentTimeMillis() - l) / 1000);
//                long l1 = System.currentTimeMillis();
//                if (!CollectionUtils.isEmpty(keyList)) {
//                    List<CustBenefitPO> benefitPOList = CustModRecordAdapter.custPoint2Benefit(keyList, totalPointMap2, enablePointMap2, benefitPOMap);
//                    middleLayerBiz.handleCustPointAndRecord(addModRecordList2, subAndFreezeList, benefitPOList);
//                    logger.info("【分页处理积分信息-单批处理】-用户插入记录,本次插入记录完成,耗时:{}秒", (System.currentTimeMillis() - l1) / 1000);
//                } else {
//                    logger.info("【核算积分】-当前批次用户全部核算失败,进行下批核算!!!!!起始用户积分余额信息:{}", JSON.toJSONString(dataList1.get(0)));
//                    logger.info("【核算积分】-当前批次用户全部核算失败,进行下批核算!!!!!结束用户积分余额信息:{}", JSON.toJSONString(dataList1.get(dataList1.size() - 1)));
//                }
//            }
//            logger.info("【分页处理积分信息】--->处理完成,结束用户ID:{},批次处理耗时:{}秒!", dataList1.get(dataList1.size() - 1).getCustId(), (System.currentTimeMillis() - beginTime_point) / 1000);
//        });
//    }

    public void handlePointData(List<UserFlagDTO> dtoList1, Map<String, UserFlagDTO> map) {
        Map<String, CustBenefitPO> benefitPOMap = userPointService.getByMap(dtoList1, map);
        StreamUtil.forEach(dtoList1, 1, 100, (dataList1) -> {
            long beginTime_point = System.currentTimeMillis();
            logger.info("【分页处理积分信息】--->开始处理,起始用户ID:{}!", dataList1.get(0).getCustId());
            //核算积分-拆分内容:增加记录,扣减和冻结记录,总增分,总扣分,总冻结分
            Map<String, Object> userPointMap1 = userPointRecordService.getByMap(dataList1, map);
            Map<String, Object> userMap = (Map<String, Object>) userPointMap1.get("user");
            Map<String, List<UserPointRecordPO>> addListMap1 = (Map<String, List<UserPointRecordPO>>) userPointMap1.get("addList");
            Map<String, List<Integer>> detailIdMap1 = (Map<String, List<Integer>>) userPointMap1.get("detailIds");
            Map<String, List<CustBenefitModRecordPO>> subAndFreezeListMap = (Map<String, List<CustBenefitModRecordPO>>) userPointMap1.get("subAndFreezeList");
            //记录用户增分明细
            for (String key1 : userMap.keySet()) {
                UserFlagDTO userFlagDTO1 = map.get(key1);
                if (userFlagDTO1 == null) {
                    logger.info("【核算积分】-未找到对应用户信息,当前记录不处理,用户标识:{}", key1);
                    continue;
                }
                List<Integer> detailIds1 = detailIdMap1.get(key1);
                long l = System.currentTimeMillis();
                Map<Integer, UserPointDetailPO> detailMap2 = userPointDetailService.mapByDetailIds(detailIds1);
                logger.info("【核算积分详情】getByMap--->>>detailMap2.size-:{}查询耗时:{}秒", detailMap2.size(), (System.currentTimeMillis() - l) / 1000);
                //记录用户余额明细
                Map<String, Map<Date, BigDecimal>> totalPointMap2 = new HashMap<>(100);
                Map<String, Map<Date, BigDecimal>> enablePointMap2 = new HashMap<>(100);
                List<CustBenefitModRecordPO> addModRecordList2 = new ArrayList<>();
                //核算单个用户进项积分
                List<UserPointRecordPO> recordPOList1 = addListMap1.get(key1);
                if (!CollectionUtils.isEmpty(recordPOList1)) {
                    for (UserPointRecordPO item : recordPOList1) {
                        String key2 = item.getCustomerCode() + item.getMobile();
                        UserPointDetailPO detailPO = detailMap2.get(item.getDetailId());
                        if (detailPO == null) {
                            logger.info("【核算积分】-增项记录转换,未查询到详情明细,跳过本条记录,用户标识:{}", item.getCustomerCode() + "-" + item.getMobile());
                            continue;
                        }
                        //计算当月累计积分
                        Map<Date, BigDecimal> dateBigDecimalMap = totalPointMap2.get(key2);
                        if (CollectionUtils.isEmpty(dateBigDecimalMap)) {
                            dateBigDecimalMap = new HashMap<>();
                            totalPointMap2.put(key2, dateBigDecimalMap);
                        }
                        BigDecimal totalPoint = dateBigDecimalMap.get(detailPO.getExpireTime());
                        if (totalPoint == null) {
                            dateBigDecimalMap.put(detailPO.getExpireTime(), item.getPointValue());
                        } else {
                            dateBigDecimalMap.put(detailPO.getExpireTime(), totalPoint.add(item.getPointValue()));
                        }

                        Date now = DateUtils.parseDate("2024-05-07 00:00:00", DateUtils.YYYY_MM_DD_HH_MM_SS);
                        if (detailPO.getExpireTime().before(now)) {
                            //计算已过期积分
                        } else {
                            //计算当月未过期可用积分
                            Map<Date, BigDecimal> dateEnableMap = enablePointMap2.get(key2);
                            if (CollectionUtils.isEmpty(dateEnableMap)) {
                                dateEnableMap = new HashMap<>();
                                enablePointMap2.put(key2, dateEnableMap);
                            }
                            BigDecimal enablePoint = dateEnableMap.get(detailPO.getExpireTime());
                            if (enablePoint == null) {
                                dateEnableMap.put(detailPO.getExpireTime(), detailPO.getEnablePoint());
                            } else {
                                dateEnableMap.put(detailPO.getExpireTime(), enablePoint.add(detailPO.getEnablePoint()));
                            }
                        }
                        UserFlagDTO userFlagDTO2 = map.get(key2);
                        CustBenefitModRecordPO addModRecordPO = CustModRecordAdapter.add2ModRecordPO(item, detailPO, userFlagDTO2.getProjectId(), userFlagDTO2.getCustId());
                        addModRecordList2.add(addModRecordPO);
                    }
                }

                Map<String, BigDecimal> sumAddMap = (Map<String, BigDecimal>) userPointMap1.get("sumAdd");
                Map<String, BigDecimal> sumSubMap = (Map<String, BigDecimal>) userPointMap1.get("sumSub");
                Map<String, BigDecimal> sumFreezeMap = (Map<String, BigDecimal>) userPointMap1.get("sumFreeze");
                CustBenefitPO custBenefitPO = benefitPOMap.get(key1);
                if (custBenefitPO == null) {
                    logger.info("【核算积分】-未找到对应用户余额信息,当前记录不处理,用户标识:{}", key1);
                    continue;
                }
                UserFlagDTO userFlagDTO = map.get(key1);
                if (userFlagDTO == null) {
                    logger.info("【核算积分】-未找到对应用户信息,当前记录不处理,用户标识:{}", custBenefitPO.getCustId());
                    continue;
                }
                BigDecimal sumAdd = sumAddMap.get(key1);
                if (sumAdd == null) {
                    sumAdd = BigDecimal.ZERO;
                }
                BigDecimal sumSub = sumSubMap.get(key1);
                if (sumSub == null) {
                    sumSub = BigDecimal.ZERO;
                }
                BigDecimal sumFreeze = sumFreezeMap.get(key1);
                if (sumFreeze == null) {
                    sumFreeze = BigDecimal.ZERO;
                }
                BigDecimal expirePoint3 = new BigDecimal(custBenefitPO.getRemark());
                //计算规则-(总进项-总出项-总冻结比较余额信息的可用积分)
//                if (sumAdd.subtract(sumSub).subtract(expirePoint3).subtract(sumFreeze).compareTo(custBenefitPO.getPointValue()) != 0) {
//                    logger.info("【核算积分】-当前用户核算积分,用户ID{},用户标识:{},可用积分:{},增项累计积分:{},减项累计积分:{},冻结累计积分:{},过期累计积分:{}"
//                            , userFlagDTO.getCustId(), userFlagDTO.getCustomerCode() + "-" + userFlagDTO.getMobile(), custBenefitPO.getPointValue(), sumAdd, sumSub, sumFreeze, expirePoint3);
//                    continue;
//                }
                logger.info("【核算积分】-当前用户核算积分校验通过,用户ID:{},用户标识:{}", userFlagDTO.getCustId(), key1);
                List<CustBenefitModRecordPO> modRecordPOList = subAndFreezeListMap.get(key1);
                logger.info("【分页处理积分信息-单批处理】-用户余额核算,本次查询计算完成,耗时:{}秒", (System.currentTimeMillis() - l) / 1000);
                long l1 = System.currentTimeMillis();
                List<CustBenefitPO> benefitPOList = CustModRecordAdapter.custPoint2Benefit(key1, totalPointMap2, enablePointMap2, benefitPOMap);
                middleLayerBiz.handleCustPointAndRecord(addModRecordList2, modRecordPOList, benefitPOList);
                logger.info("【分页处理积分信息-单批处理】-用户插入记录,本次插入记录完成,耗时:{}秒", (System.currentTimeMillis() - l1) / 1000);
            }
            logger.info("【分页处理积分信息】--->处理完成,结束用户ID:{},批次处理耗时:{}秒!", dataList1.get(dataList1.size() - 1).getCustId(), (System.currentTimeMillis() - beginTime_point) / 1000);
        });
    }

//    @Transactional
    public void handleLegacyUser(List<UserMobilePointPO> userMobilePointList) {
        List<CustInfoPO> poList = new ArrayList<>(userMobilePointList.size());
        userMobilePointList.forEach(item -> {
            CustInfoPO custInfoPO = CustInfoAdapter.mobilePo2DTO(item, projectId);
            poList.add(custInfoPO);
        });
        custService.insertBatch(poList);

        List<CustInfoPO> custInfoPOList = custService.selectByCustomerCodeAndMobile(poList);
        List<UserFlagDTO> dtoList = custInfoPOList.stream().map(CustInfoAdapter::po2DTO).collect(Collectors.toList());
        List<CustLevelRecordPO> levelRecordPOList = dtoList.stream().map(CustInfoAdapter::mobilePO2RecordPO).collect(Collectors.toList());
        custLevelRecordService.insertBatachLevelRecord(levelRecordPOList);

        Map<String, UserFlagDTO> dtoMap = dtoList.stream().collect(Collectors.toMap(item -> item.getMobile(), Function.identity()));
        Map<Long, List<CustBenefitPO>> benefitPOListMap = new HashMap<>(8);
        Map<Long, List<CustBenefitModRecordPO>> addModRecordListMap = new HashMap<>(8);
        userMobilePointList.forEach(item -> {
            UserFlagDTO userFlagDTO = dtoMap.get(item.getMobile());
            CustBenefitPO custBenefitPO = CustInfoAdapter.mobilePO2BenefitPO(userFlagDTO, item);
            Long index = custBenefitPO.getCustId() % 8;
            List<CustBenefitPO> list = benefitPOListMap.get(index);
            if (list == null) {
                list = new ArrayList<>(5000);
                benefitPOListMap.put(index, list);
            }
            list.add(custBenefitPO);
            benefitPOListMap.put(index, list);
            CustBenefitModRecordPO recordPO = CustInfoAdapter.mobilePO2ModRecordPO(userFlagDTO, item);
            Long index2 = recordPO.getCustId() % 8;
            List<CustBenefitModRecordPO> list2 = addModRecordListMap.get(index2);
            if (list2 == null) {
                list2 = new ArrayList<>(5000);
                addModRecordListMap.put(index, list2);
            }
            list2.add(recordPO);
            addModRecordListMap.put(index2, list2);
        });
        List<Future<String>> featureList = new ArrayList<>(80);
        for (Long key : benefitPOListMap.keySet()) {
            List<CustBenefitPO> item = benefitPOListMap.get(key);
            if (CollectionUtils.isEmpty(item)) continue;
            if (key % 8 == 0) {
                middleLayerBiz.insertByKey0(featureList, item);
            } else if (key % 8 == 1) {
                middleLayerBiz.insertByKey1(featureList, item);
            } else if (key % 8 == 2) {
                middleLayerBiz.insertByKey2(featureList, item);
            } else if (key % 8 == 3) {
                middleLayerBiz.insertByKey3(featureList, item);
            } else if (key % 8 == 4) {
                middleLayerBiz.insertByKey4(featureList, item);
            } else if (key % 8 == 5) {
                middleLayerBiz.insertByKey5(featureList, item);
            } else if (key % 8 == 6) {
                middleLayerBiz.insertByKey6(featureList, item);
            } else if (key % 8 == 7) {
                middleLayerBiz.insertByKey7(featureList, item);
            }
        }
        for (Long key : addModRecordListMap.keySet()) {
            List<CustBenefitModRecordPO> item = addModRecordListMap.get(key);
            if (CollectionUtils.isEmpty(item)) continue;
            if (key % 8 == 0) {
                middleLayerBiz.insertModRecordByKey0(featureList, item);
            } else if (key % 8 == 1) {
                middleLayerBiz.insertModRecordByKey1(featureList, item);
            } else if (key % 8 == 2) {
                middleLayerBiz.insertModRecordByKey2(featureList, item);
            } else if (key % 8 == 3) {
                middleLayerBiz.insertModRecordByKey3(featureList, item);
            } else if (key % 8 == 4) {
                middleLayerBiz.insertModRecordByKey4(featureList, item);
            } else if (key % 8 == 5) {
                middleLayerBiz.insertModRecordByKey5(featureList, item);
            } else if (key % 8 == 6) {
                middleLayerBiz.insertModRecordByKey6(featureList, item);
            } else if (key % 8 == 7) {
                middleLayerBiz.insertModRecordByKey7(featureList, item);
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
