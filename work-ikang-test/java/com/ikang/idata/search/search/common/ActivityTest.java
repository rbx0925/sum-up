package com.ikang.idata.search.search.common;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 活动主表
 * </p>
 *
 * @author jiangfeng
 * @since 2019-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ActivityTest implements Serializable {

    private static final long serialVersionUID = -3459298323366247323L;

    @ApiModelProperty(value = "活动id")
    private Long id;

    @ApiModelProperty(value = "所属业务线id")
    private Long businessLineId;

    @ApiModelProperty(value = "活动状态0：草稿；1：待审核；2：审核通过待发送；3：发送中；4已发送")
    private Integer activityStatus;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "活动备注")
    private String activityRemark;

    @ApiModelProperty(value = "活动区域")
    private String activityArea;

    @ApiModelProperty(value = "活动品牌")
    private String activityBrand;

    @ApiModelProperty(value = "活动范围")
    private String activityScope;

    @ApiModelProperty(value = "目标用户群json数据")
    private String activityUseTags;

    @ApiModelProperty(value = "用户画像返回的汇总结果")
    private String activityUseInfo;

    @ApiModelProperty(value = "活动短连接")
    private String activityShortUrl;

    @ApiModelProperty(value = "发送方式 SMS;短信方式 APP_PUSH:爱康APP TJB_PUSH:体检宝APP")
    private String sendType;

    @ApiModelProperty(value = "发送时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "活动短信内容")
    private String activityMessageSms;

    @ApiModelProperty(value = "活动APP PUSH内容")
    private String activityMessagePush;

    @ApiModelProperty(value = "活动push打开方式：1：H5 ；2：原生")
    private String pushOpenType;

    @ApiModelProperty(value = "打开方式为1是为url；打开方式为2是为原生跳转码；如商品code")
    private String pushOpenUrl;

    @ApiModelProperty(value = "目标用户总数")
    private Long userTotalNum;

    @ApiModelProperty(value = "黑名单用户数")
    private Long  blackNum;

    @ApiModelProperty(value = "可接受短信用户总数")
    private Long canSmsNum;

    @ApiModelProperty(value = "可PUSH用户总数")
    private Long canPushNum;

    @ApiModelProperty(value = "可APP推送用户总数")
    private Long canPushAppNum;

    @ApiModelProperty(value = "可体检宝推送用数")
    private Long canPushTjbNum;

    @ApiModelProperty(value = "活动选取的目标用户总数")
    private Long actUserNum;

    @ApiModelProperty(value = "活动实际发送短信人数")
    private Long actSmsNum;

    @ApiModelProperty(value = "活动实际PUSH人数")
    private Long actPushNum;

    @ApiModelProperty(value = "触达的短信人数")
    private Long touchSmsNum;

    @ApiModelProperty(value = "触达的PUSH人数")
    private Long touchPushNum;

    @ApiModelProperty(value = "PUSH打开人数")
    private Long openPushNum;

    @ApiModelProperty(value = "活动push通知标题")
    private String pushTitle;

    @ApiModelProperty(value = "审核人")
    private Long auditor;

    @ApiModelProperty(value = "审核时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;

    @ApiModelProperty(value = "活动审核邮箱")
    private String actAuditEmail;

    @ApiModelProperty(value = "活动步数")
    private Integer actStep;

    @ApiModelProperty(value = "查询大数据用的json格式的QueryDSL")
    private String queryDsl;

    @ApiModelProperty(value = "kafka数据批次号actId+_+待审核提交次数")
    private String batchId;

    @ApiModelProperty(value = "kafka回调数据操作结果 1:成功；0：失败")
    private String kafkaPutStatus;

    @ApiModelProperty(value = "kafka回调数据操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime kafkaPutTime;

    private Long createBy;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long updateBy;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "活动任务类型：FIXED_TIME：固定时间(默认)，FIXED_RATE_STANDARD：固定周期")
    private String jobType;

    @ApiModelProperty(value = "活动类型：0：活动（默认），1：模板")
    private Integer activityType;

    @ApiModelProperty(value = "模板名称：当活动为模板时的名称")
    private String templateName;

    @ApiModelProperty(value = "发送时间类型：BY_DAY：每日，BY_WEEK：每周，BY_MONTH：每月")
    private String sendTimeType;

    @ApiModelProperty(value = "发送时间区间：每日为：空，每周为：1-7，每月为：1-31，逗号分隔")
    private String sendTimeInterval;

    @ApiModelProperty(value = "发送时间：开始年月,格式 YYYY-MM")
    private String sendTimeBegin;

    @ApiModelProperty(value = "发送时间：结束年月,格式 YYYY-MM")
    private String sendTimeEnd;

    @ApiModelProperty(value = "用户人群ID")
    private Long userGroupId;

    @ApiModelProperty(value = "发送时间：具体时间点：格式 HH:mm")
    private String sendTimePoint;

    @ApiModelProperty(value = "拉取活动用户时间，根据活动规则拉取用户")
    private Date pullUserTime;

    @ApiModelProperty(value = "发送状态：0：用户拉取完毕（默认），1：待拉取用户，2：周期发送中，3：周期活动发送完成 ，4： 用户数据清理完毕，5：该批次待审核 区别活动状态")
    private Integer sendStatus;

    @ApiModelProperty(value = "删除状态：0：正常（默认），1：已删除，")
    private Integer delFlag;

    @ApiModelProperty(value = "活动选取的目标用户百分比，默认100")
    private Integer actUserNumRate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setBusinessLineId(Long businessLineId) {
        this.businessLineId = businessLineId;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setActivityRemark(String activityRemark) {
        this.activityRemark = activityRemark;
    }

    public void setActivityArea(String activityArea) {
        this.activityArea = activityArea;
    }

    public void setActivityBrand(String activityBrand) {
        this.activityBrand = activityBrand;
    }

    public void setActivityScope(String activityScope) {
        this.activityScope = activityScope;
    }

    public void setActivityUseTags(String activityUseTags) {
        this.activityUseTags = activityUseTags;
    }

    public void setActivityUseInfo(String activityUseInfo) {
        this.activityUseInfo = activityUseInfo;
    }

    public void setActivityShortUrl(String activityShortUrl) {
        this.activityShortUrl = activityShortUrl;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public void setActivityMessageSms(String activityMessageSms) {
        this.activityMessageSms = activityMessageSms;
    }

    public void setActivityMessagePush(String activityMessagePush) {
        this.activityMessagePush = activityMessagePush;
    }

    public void setPushOpenType(String pushOpenType) {
        this.pushOpenType = pushOpenType;
    }

    public void setPushOpenUrl(String pushOpenUrl) {
        this.pushOpenUrl = pushOpenUrl;
    }

    public void setUserTotalNum(Long userTotalNum) {
        this.userTotalNum = userTotalNum;
    }

    public void setBlackNum(Long blackNum) {
        this.blackNum = blackNum;
    }

    public void setCanSmsNum(Long canSmsNum) {
        this.canSmsNum = canSmsNum;
    }

    public void setCanPushNum(Long canPushNum) {
        this.canPushNum = canPushNum;
    }

    public void setCanPushAppNum(Long canPushAppNum) {
        this.canPushAppNum = canPushAppNum;
    }

    public void setCanPushTjbNum(Long canPushTjbNum) {
        this.canPushTjbNum = canPushTjbNum;
    }

    public void setActUserNum(Long actUserNum) {
        this.actUserNum = actUserNum;
    }

    public void setActSmsNum(Long actSmsNum) {
        this.actSmsNum = actSmsNum;
    }

    public void setActPushNum(Long actPushNum) {
        this.actPushNum = actPushNum;
    }

    public void setTouchSmsNum(Long touchSmsNum) {
        this.touchSmsNum = touchSmsNum;
    }

    public void setTouchPushNum(Long touchPushNum) {
        this.touchPushNum = touchPushNum;
    }

    public void setOpenPushNum(Long openPushNum) {
        this.openPushNum = openPushNum;
    }

    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
    }

    public void setAuditor(Long auditor) {
        this.auditor = auditor;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public void setActAuditEmail(String actAuditEmail) {
        this.actAuditEmail = actAuditEmail;
    }

    public void setActStep(Integer actStep) {
        this.actStep = actStep;
    }

    public void setQueryDsl(String queryDsl) {
        this.queryDsl = queryDsl;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public void setKafkaPutStatus(String kafkaPutStatus) {
        this.kafkaPutStatus = kafkaPutStatus;
    }

    public void setKafkaPutTime(LocalDateTime kafkaPutTime) {
        this.kafkaPutTime = kafkaPutTime;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }
}
