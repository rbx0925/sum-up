package com.ikang.idata.search.search.javaTest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>实体类</p>
 * <p>Table: cms_article - </p>
 * @since 2022-04-05 08:03:21
 */
@ApiModel(description="")
public class CmsArticleDTO implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -1L;
	@ApiModelProperty(value="主键", position = 1)
    private Long id;
    @ApiModelProperty(value = "栏目id", position = 10)
    private Long categoryId;
    @ApiModelProperty(value = "标题", position = 15)
    private String title;
    @ApiModelProperty(value = "描述", position = 20)
    private String description;
    @ApiModelProperty(value = "大图", position = 25)
    private String cover;
    @ApiModelProperty(value = "作者", position = 30)
    private String author;
    @ApiModelProperty(value = "文章来源", position = 35)
    private String source;
    @ApiModelProperty(value = "排序", position = 40)
    private Double sort;
    @ApiModelProperty(value = "状态(1->草稿|DRAFT,2->审核中|AUDITING,3->审批通过|PASS,4->审批不通过|NO_PASS)", position = 45)
    private Boolean status;
    @ApiModelProperty(value = "工作流业务编号", position = 50)
    private String businessNo;
    @ApiModelProperty(value = "发布时间", position = 55)
    private Date publishTime;
    @ApiModelProperty(value = "是否发布(1->否|NO,2->是|YES)", position = 60)
    private Boolean isPublish;
    @ApiModelProperty(value = "文本内容", position = 65)
    private String content;
    @ApiModelProperty(value = "扩展信息", position = 70)
    private String extFormValue;
    @ApiModelProperty(value = "所属部门", position = 75)
    private Long deptId;
    @ApiModelProperty(value = "所属用户", position = 80)
    private Long userId;
    @ApiModelProperty(value = "创建时间", position = 85)
    private Date createTime;
    @ApiModelProperty(value = "更新时间", position = 90)
    private Date updateTime;
    @ApiModelProperty(value = "是否删除(1->未删除|NO,2->已删除|YES)", position = 95)
    private Boolean isDeleted;

    /**
     * 获取主键
     *
     */
    public Long getId(){
        return this.id;
    }
	 /**
     * 设置主键
     *
     * @param id
     */
    public void setId(Long id){
        this.id = id;
    }
    /**
     * 获取栏目id
     *
     */
    public Long getCategoryId(){
        return this.categoryId;
    }
	 /**
     * 设置栏目id
     *
     * @param categoryId
     */
    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }
    /**
     * 获取标题
     *
     */
    public String getTitle(){
        return this.title;
    }
	 /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * 获取描述
     *
     */
    public String getDescription(){
        return this.description;
    }
	 /**
     * 设置描述
     *
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }
    /**
     * 获取大图
     *
     */
    public String getCover(){
        return this.cover;
    }
	 /**
     * 设置大图
     *
     * @param cover
     */
    public void setCover(String cover){
        this.cover = cover;
    }
    /**
     * 获取作者
     *
     */
    public String getAuthor(){
        return this.author;
    }
	 /**
     * 设置作者
     *
     * @param author
     */
    public void setAuthor(String author){
        this.author = author;
    }
    /**
     * 获取文章来源
     *
     */
    public String getSource(){
        return this.source;
    }
	 /**
     * 设置文章来源
     *
     * @param source
     */
    public void setSource(String source){
        this.source = source;
    }
    /**
     * 获取排序
     *
     */
    public Double getSort(){
        return this.sort;
    }
	 /**
     * 设置排序
     *
     * @param sort
     */
    public void setSort(Double sort){
        this.sort = sort;
    }
    /**
     * 获取状态(1->草稿|DRAFT,2->审核中|AUDITING,3->审批通过|PASS,4->审批不通过|NO_PASS)
     *
     */
    public Boolean getStatus(){
        return this.status;
    }
	 /**
     * 设置状态(1->草稿|DRAFT,2->审核中|AUDITING,3->审批通过|PASS,4->审批不通过|NO_PASS)
     *
     * @param status
     */
    public void setStatus(Boolean status){
        this.status = status;
    }
    /**
     * 获取工作流业务编号
     *
     */
    public String getBusinessNo(){
        return this.businessNo;
    }
	 /**
     * 设置工作流业务编号
     *
     * @param businessNo
     */
    public void setBusinessNo(String businessNo){
        this.businessNo = businessNo;
    }
    /**
     * 获取发布时间
     *
     */
    public Date getPublishTime(){
        return this.publishTime;
    }
	 /**
     * 设置发布时间
     *
     * @param publishTime
     */
    public void setPublishTime(Date publishTime){
        this.publishTime = publishTime;
    }
    /**
     * 获取是否发布(1->否|NO,2->是|YES)
     *
     */
    public Boolean getIsPublish(){
        return this.isPublish;
    }
	 /**
     * 设置是否发布(1->否|NO,2->是|YES)
     *
     * @param isPublish
     */
    public void setIsPublish(Boolean isPublish){
        this.isPublish = isPublish;
    }
    /**
     * 获取文本内容
     *
     */
    public String getContent(){
        return this.content;
    }
	 /**
     * 设置文本内容
     *
     * @param content
     */
    public void setContent(String content){
        this.content = content;
    }
    /**
     * 获取扩展信息
     *
     */
    public String getExtFormValue(){
        return this.extFormValue;
    }
	 /**
     * 设置扩展信息
     *
     * @param extFormValue
     */
    public void setExtFormValue(String extFormValue){
        this.extFormValue = extFormValue;
    }
    /**
     * 获取所属部门
     *
     */
    public Long getDeptId(){
        return this.deptId;
    }
	 /**
     * 设置所属部门
     *
     * @param deptId
     */
    public void setDeptId(Long deptId){
        this.deptId = deptId;
    }
    /**
     * 获取所属用户
     *
     */
    public Long getUserId(){
        return this.userId;
    }
	 /**
     * 设置所属用户
     *
     * @param userId
     */
    public void setUserId(Long userId){
        this.userId = userId;
    }
    /**
     * 获取创建时间
     *
     */
    public Date getCreateTime(){
        return this.createTime;
    }
	 /**
     * 设置创建时间
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    /**
     * 获取更新时间
     *
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }
	 /**
     * 设置更新时间
     *
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    /**
     * 获取是否删除(1->未删除|NO,2->已删除|YES)
     *
     */
    public Boolean getIsDeleted(){
        return this.isDeleted;
    }
	 /**
     * 设置是否删除(1->未删除|NO,2->已删除|YES)
     *
     * @param isDeleted
     */
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }

}
