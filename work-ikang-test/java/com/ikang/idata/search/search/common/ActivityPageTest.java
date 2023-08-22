package com.ikang.idata.search.search.common;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * ActivityPage
 *
 * @author haigang.jia@ikang.com
 * @date 2019-08-19  下午 2:15
 */
@Data
@ApiModel("活动页面PO")
public class ActivityPageTest implements java.io.Serializable {
    private static final long serialVersionUID = -2381168246154433093L;

    @ApiModelProperty(value = "页面ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "APP端展示Title", example = "活动促销")
    private String appTitle;

    @ApiModelProperty(value = "页面标题", example = "活动促销")
    private String pageTitle;

    @ApiModelProperty(value = "活动页面内容及用途介绍", example = "活动促销")
    private String pageContent;

    @ApiModelProperty(value = "app推广的类型", example = "IKANG_PUSH")
    private String appType;

    @ApiModelProperty(value = "是否开启推广0：关闭；1：打开", example = "0")
    private Integer appConfOpen;

    @ApiModelProperty(value = "推广主标题", example = "爱康推广")
    private String appPopTitle;

    @ApiModelProperty(value = "推广副标题", example = "爱康推广")
    private String appPopSubtitle;

    @ApiModelProperty(value = "业务线ID", example = "1", hidden = true)
    private Long businessLineId;

    @ApiModelProperty(value = "页面链接", example = "http://uat-aqs.m.ikang.com/2c?logicId=55540701b93004d898a81ae6ad0215c3")
    private String pageUrl;

    @ApiModelProperty(value = "页面状态，0：草稿，1下架，2上架", example = "0")
    private Integer pageStatus;

    @ApiModelProperty(value = "创建人ID", example = "1", hidden = true)
    private Long createBy;

    @ApiModelProperty(value = "创建时间", example = "2019-07-02 15:29:58", hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "创建人ID", example = "1", hidden = true)
    private Long updateBy;

    @ApiModelProperty(value = "创建时间", example = "2019-07-02 15:29:58", hidden = true)
    private Date updateTime;

    @ApiModelProperty(value = "分享标题", example = "微信分享标题")
    private String shareTitle;

    @ApiModelProperty(value = "分享描述", example = "微信分享描述")
    private String shareContent;

    @ApiModelProperty(value = "分享封面", example = "微信分享封面")
    private String shareCover;

    @ApiModelProperty(value = "上架时间", example = "2019-07-02 15:29:58")
    private Date publishTime;

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

    @ApiModelProperty(value = "发送方式 SMS;短信方式 APP_PUSH:爱康APP TJB_PUSH:体检宝APP")
    private String sendType;

    @ApiModelProperty(value = "发送时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "活动短信内容")
    private String activityMessageSms;

//    ALTER TABLE `ikang_op2c`.`page`
//    ADD COLUMN `share_title` varchar(255) NULL COMMENT '分享标题' AFTER `update_time`,
//    ADD COLUMN `share_content` varchar(255) NULL COMMENT '分享描述' AFTER `share_title`,
//    ADD COLUMN `share_cover` varchar(255) NULL COMMENT '分享封面' AFTER `share_content`;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageContent() {
        return pageContent;
    }

    public void setPageContent(String pageContent) {
        this.pageContent = pageContent;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Integer getAppConfOpen() {
        return appConfOpen;
    }

    public void setAppConfOpen(Integer appConfOpen) {
        this.appConfOpen = appConfOpen;
    }

    public String getAppPopTitle() {
        return appPopTitle;
    }

    public void setAppPopTitle(String appPopTitle) {
        this.appPopTitle = appPopTitle;
    }

    public String getAppPopSubtitle() {
        return appPopSubtitle;
    }

    public void setAppPopSubtitle(String appPopSubtitle) {
        this.appPopSubtitle = appPopSubtitle;
    }

    public Long getBusinessLineId() {
        return businessLineId;
    }

    public void setBusinessLineId(Long businessLineId) {
        this.businessLineId = businessLineId;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Integer getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(Integer pageStatus) {
        this.pageStatus = pageStatus;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getShareCover() {
        return shareCover;
    }

    public void setShareCover(String shareCover) {
        this.shareCover = shareCover;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
