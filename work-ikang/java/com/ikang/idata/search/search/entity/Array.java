package com.ikang.idata.search.search.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * 测试
 *
 * @author <a href="jiangfeng.yan@ikang.com">jiangfeng</a>
 * @version 2022年12月07日 下午 2:53
 */
public class Array {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessLineId() {
        return businessLineId;
    }

    public void setBusinessLineId(Long businessLineId) {
        this.businessLineId = businessLineId;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityRemark() {
        return activityRemark;
    }

    public void setActivityRemark(String activityRemark) {
        this.activityRemark = activityRemark;
    }

    public String getActivityArea() {
        return activityArea;
    }

    public void setActivityArea(String activityArea) {
        this.activityArea = activityArea;
    }

    public String getActivityBrand() {
        return activityBrand;
    }

    public void setActivityBrand(String activityBrand) {
        this.activityBrand = activityBrand;
    }

    public String getActivityScope() {
        return activityScope;
    }

    public void setActivityScope(String activityScope) {
        this.activityScope = activityScope;
    }

    public String getActivityUseTags() {
        return activityUseTags;
    }

    public void setActivityUseTags(String activityUseTags) {
        this.activityUseTags = activityUseTags;
    }

    public String getActivityUseInfo() {
        return activityUseInfo;
    }

    public void setActivityUseInfo(String activityUseInfo) {
        this.activityUseInfo = activityUseInfo;
    }

    public String getActivityShortUrl() {
        return activityShortUrl;
    }

    public void setActivityShortUrl(String activityShortUrl) {
        this.activityShortUrl = activityShortUrl;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public String getActivityMessageSms() {
        return activityMessageSms;
    }

    public void setActivityMessageSms(String activityMessageSms) {
        this.activityMessageSms = activityMessageSms;
    }

    public String getActivityMessagePush() {
        return activityMessagePush;
    }

    public void setActivityMessagePush(String activityMessagePush) {
        this.activityMessagePush = activityMessagePush;
    }

    public String getPushOpenType() {
        return pushOpenType;
    }

    public void setPushOpenType(String pushOpenType) {
        this.pushOpenType = pushOpenType;
    }

    public String getPushOpenUrl() {
        return pushOpenUrl;
    }

    public void setPushOpenUrl(String pushOpenUrl) {
        this.pushOpenUrl = pushOpenUrl;
    }

    public Long getUserTotalNum() {
        return userTotalNum;
    }

    public void setUserTotalNum(Long userTotalNum) {
        this.userTotalNum = userTotalNum;
    }

    public Long getBlackNum() {
        return blackNum;
    }

    public void setBlackNum(Long blackNum) {
        this.blackNum = blackNum;
    }

    public Long getCanSmsNum() {
        return canSmsNum;
    }

    public void setCanSmsNum(Long canSmsNum) {
        this.canSmsNum = canSmsNum;
    }

    public Long getCanPushNum() {
        return canPushNum;
    }

    public void setCanPushNum(Long canPushNum) {
        this.canPushNum = canPushNum;
    }

    public Long getCanPushAppNum() {
        return canPushAppNum;
    }

    public void setCanPushAppNum(Long canPushAppNum) {
        this.canPushAppNum = canPushAppNum;
    }

    public Long getCanPushTjbNum() {
        return canPushTjbNum;
    }

    public void setCanPushTjbNum(Long canPushTjbNum) {
        this.canPushTjbNum = canPushTjbNum;
    }

    public Long getActUserNum() {
        return actUserNum;
    }

    public void setActUserNum(Long actUserNum) {
        this.actUserNum = actUserNum;
    }

    public Long getActSmsNum() {
        return actSmsNum;
    }

    public void setActSmsNum(Long actSmsNum) {
        this.actSmsNum = actSmsNum;
    }

    public Long getActPushNum() {
        return actPushNum;
    }

    public void setActPushNum(Long actPushNum) {
        this.actPushNum = actPushNum;
    }

    public Long getTouchSmsNum() {
        return touchSmsNum;
    }

    public void setTouchSmsNum(Long touchSmsNum) {
        this.touchSmsNum = touchSmsNum;
    }

    public Long getTouchPushNum() {
        return touchPushNum;
    }

    public void setTouchPushNum(Long touchPushNum) {
        this.touchPushNum = touchPushNum;
    }

    public Long getOpenPushNum() {
        return openPushNum;
    }

    public void setOpenPushNum(Long openPushNum) {
        this.openPushNum = openPushNum;
    }
}
