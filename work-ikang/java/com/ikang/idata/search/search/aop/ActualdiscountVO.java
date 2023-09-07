package com.ikang.idata.search.search.aop;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Objects;
import com.ikang.idata.common.entity.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description 项目实际折扣率
 * @date 2022/9/6 14:24
 */

@EqualsAndHashCode(callSuper = true)
@ApiModel("项目实际折扣率请求vo")
public class ActualdiscountVO extends BaseVO implements Serializable {
    private static final long serialVersionUID = 5439741698238141762L;

    @ApiModelProperty("项目号")
    private String projectid;

    @ApiModelProperty("签约时间开始 样例:2022-03-15")
    private String qysjStart;
    @ApiModelProperty("签约时间结束 样例:2022-03-15")
    private String qysjEnd;

    @ApiModelProperty("到检时间开始 样例:2022-03-15")
    private String regdateStart;
    @ApiModelProperty("到检时间结束 样例:2022-03-15")
    private String regdateEnd;

    @ApiModelProperty("所属大区")
    private String signarea;

    @ApiModelProperty("君安折扣 开始")
    private String jazkStart;
    @ApiModelProperty("君安折扣 结束")
    private String jazkEnd;
    @ApiModelProperty("国宾折扣 开始")
    private String xmpjzkStart;
    @ApiModelProperty("国宾折扣 结束")
    private String xmpjzkEnd;
    @ApiModelProperty("VIP/卓悦普检折扣 开始")
    private String vipzkStart;
    @ApiModelProperty("VIP/卓悦普检折扣 结束")
    private String vipzkEnd;
    @ApiModelProperty("卓悦VIP折扣 开始")
    private String zyzkStart;
    @ApiModelProperty("卓悦VIP折扣 结束")
    private String zyzkEnd;


    @ApiModelProperty("君安实际折扣 开始")
    private String realJazkStart;
    @ApiModelProperty("君安实际折扣 结束")
    private String realJazkEnd;
    @ApiModelProperty("国宾实际折扣 开始")
    private String realXmpjzkStart;
    @ApiModelProperty("国宾实际折扣 结束")
    private String realXmpjzkEnd;
    @ApiModelProperty("VIP/卓悦普检实际折扣 开始")
    private String realVipzkStart;
    @ApiModelProperty("VIP/卓悦普检实际折扣 结束")
    private String realVipzkEnd;
    @ApiModelProperty("卓悦VIP实际折扣 开始")
    private String realZyzkStart;
    @ApiModelProperty("卓悦VIP实际折扣 结束")
    private String realZyzkEnd;


    @ApiModelProperty("最低折扣  开始")
    private String zdzkStart;
    @ApiModelProperty("最低折扣  结束")
    private String zdzkEnd;


    public Boolean judgeRealJazk(String realJazk) {
        if (StrUtil.isAllEmpty(realJazkStart, realJazkEnd)) {
            return true;
        }
        if (StrUtil.isEmpty(realJazk)) {
            return false;
        }
        return (StrUtil.isEmpty(realJazkStart) || NumberUtil.isGreaterOrEqual(new BigDecimal(realJazk), new BigDecimal(realJazkStart)))
                && (StrUtil.isEmpty(realJazkEnd) || NumberUtil.isLessOrEqual(new BigDecimal(realJazk), new BigDecimal(realJazkEnd)));
    }

    public Boolean judgeRealXmpjzk(String realXmpjzk) {
        if (StrUtil.isAllEmpty(realXmpjzkStart, realXmpjzkEnd)) {
            return true;
        }
        if (StrUtil.isEmpty(realXmpjzk)) {
            return false;
        }
        return (StrUtil.isEmpty(realXmpjzkStart) ||  NumberUtil.isGreaterOrEqual(new BigDecimal(realXmpjzk), new BigDecimal(realXmpjzkStart)))
                &&(StrUtil.isEmpty(realXmpjzkEnd) ||  NumberUtil.isLessOrEqual(new BigDecimal(realXmpjzk), new BigDecimal(realXmpjzkEnd)));
    }

    public Boolean judgeRealVipzk(String realVipzk) {
        if (StrUtil.isAllEmpty(realVipzkStart, realVipzkEnd)) {
            return true;
        }
        if (StrUtil.isEmpty(realVipzk)) {
            return false;
        }
        return (StrUtil.isEmpty(realVipzkStart) || NumberUtil.isGreaterOrEqual(new BigDecimal(realVipzk), new BigDecimal(realVipzkStart)))
                &&(StrUtil.isEmpty(realVipzkEnd) || NumberUtil.isLessOrEqual(new BigDecimal(realVipzk), new BigDecimal(realVipzkEnd)));
    }

    public Boolean judgeRealZyzk(String realZyzk) {
        if (StrUtil.isAllEmpty(realZyzkStart, realZyzkEnd)) {
            return true;
        }
        if (StrUtil.isEmpty(realZyzk)) {
            return false;
        }
        return (StrUtil.isEmpty(realZyzkStart) || NumberUtil.isGreaterOrEqual(new BigDecimal(realZyzk), new BigDecimal(realZyzkStart)))
                && (StrUtil.isEmpty(realZyzkEnd) || NumberUtil.isLessOrEqual(new BigDecimal(realZyzk), new BigDecimal(realZyzkEnd)));
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getQysjStart() {
        return qysjStart;
    }

    public void setQysjStart(String qysjStart) {
        this.qysjStart = qysjStart;
    }

    public String getQysjEnd() {
        return qysjEnd;
    }

    public void setQysjEnd(String qysjEnd) {
        this.qysjEnd = qysjEnd;
    }

    public String getRegdateStart() {
        return regdateStart;
    }

    public void setRegdateStart(String regdateStart) {
        this.regdateStart = regdateStart;
    }

    public String getRegdateEnd() {
        return regdateEnd;
    }

    public void setRegdateEnd(String regdateEnd) {
        this.regdateEnd = regdateEnd;
    }

    public String getSignarea() {
        return signarea;
    }

    public void setSignarea(String signarea) {
        this.signarea = signarea;
    }

    public String getJazkStart() {
        return jazkStart;
    }

    public void setJazkStart(String jazkStart) {
        this.jazkStart = jazkStart;
    }

    public String getJazkEnd() {
        return jazkEnd;
    }

    public void setJazkEnd(String jazkEnd) {
        this.jazkEnd = jazkEnd;
    }

    public String getXmpjzkStart() {
        return xmpjzkStart;
    }

    public void setXmpjzkStart(String xmpjzkStart) {
        this.xmpjzkStart = xmpjzkStart;
    }

    public String getXmpjzkEnd() {
        return xmpjzkEnd;
    }

    public void setXmpjzkEnd(String xmpjzkEnd) {
        this.xmpjzkEnd = xmpjzkEnd;
    }

    public String getVipzkStart() {
        return vipzkStart;
    }

    public void setVipzkStart(String vipzkStart) {
        this.vipzkStart = vipzkStart;
    }

    public String getVipzkEnd() {
        return vipzkEnd;
    }

    public void setVipzkEnd(String vipzkEnd) {
        this.vipzkEnd = vipzkEnd;
    }

    public String getZyzkStart() {
        return zyzkStart;
    }

    public void setZyzkStart(String zyzkStart) {
        this.zyzkStart = zyzkStart;
    }

    public String getZyzkEnd() {
        return zyzkEnd;
    }

    public void setZyzkEnd(String zyzkEnd) {
        this.zyzkEnd = zyzkEnd;
    }

    public String getRealJazkStart() {
        return realJazkStart;
    }

    public void setRealJazkStart(String realJazkStart) {
        this.realJazkStart = realJazkStart;
    }

    public String getRealJazkEnd() {
        return realJazkEnd;
    }

    public void setRealJazkEnd(String realJazkEnd) {
        this.realJazkEnd = realJazkEnd;
    }

    public String getRealXmpjzkStart() {
        return realXmpjzkStart;
    }

    public void setRealXmpjzkStart(String realXmpjzkStart) {
        this.realXmpjzkStart = realXmpjzkStart;
    }

    public String getRealXmpjzkEnd() {
        return realXmpjzkEnd;
    }

    public void setRealXmpjzkEnd(String realXmpjzkEnd) {
        this.realXmpjzkEnd = realXmpjzkEnd;
    }

    public String getRealVipzkStart() {
        return realVipzkStart;
    }

    public void setRealVipzkStart(String realVipzkStart) {
        this.realVipzkStart = realVipzkStart;
    }

    public String getRealVipzkEnd() {
        return realVipzkEnd;
    }

    public void setRealVipzkEnd(String realVipzkEnd) {
        this.realVipzkEnd = realVipzkEnd;
    }

    public String getRealZyzkStart() {
        return realZyzkStart;
    }

    public void setRealZyzkStart(String realZyzkStart) {
        this.realZyzkStart = realZyzkStart;
    }

    public String getRealZyzkEnd() {
        return realZyzkEnd;
    }

    public void setRealZyzkEnd(String realZyzkEnd) {
        this.realZyzkEnd = realZyzkEnd;
    }

    public String getZdzkStart() {
        return zdzkStart;
    }

    public void setZdzkStart(String zdzkStart) {
        this.zdzkStart = zdzkStart;
    }

    public String getZdzkEnd() {
        return zdzkEnd;
    }

    public void setZdzkEnd(String zdzkEnd) {
        this.zdzkEnd = zdzkEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActualdiscountVO)) return false;
        ActualdiscountVO that = (ActualdiscountVO) o;
        return Objects.equal(getProjectid(),
                that.getProjectid()) &&
                Objects.equal(getQysjStart(),
                        that.getQysjStart()) &&
                Objects.equal(getQysjEnd(),
                        that.getQysjEnd()) &&
                Objects.equal(getRegdateStart(),
                        that.getRegdateStart()) &&
                Objects.equal(getRegdateEnd(),
                        that.getRegdateEnd()) &&
                Objects.equal(getSignarea(),
                        that.getSignarea()) &&
                Objects.equal(getJazkStart(),
                        that.getJazkStart()) &&
                Objects.equal(getJazkEnd(),
                        that.getJazkEnd()) &&
                Objects.equal(getXmpjzkStart(),
                        that.getXmpjzkStart()) &&
                Objects.equal(getXmpjzkEnd(),
                        that.getXmpjzkEnd()) &&
                Objects.equal(getVipzkStart(),
                        that.getVipzkStart()) &&
                Objects.equal(getVipzkEnd(),
                        that.getVipzkEnd()) &&
                Objects.equal(getZyzkStart(),
                        that.getZyzkStart()) &&
                Objects.equal(getZyzkEnd(),
                        that.getZyzkEnd()) &&
                Objects.equal(getRealJazkStart(),
                        that.getRealJazkStart()) &&
                Objects.equal(getRealJazkEnd(),
                        that.getRealJazkEnd()) &&
                Objects.equal(getRealXmpjzkStart(),
                        that.getRealXmpjzkStart()) &&
                Objects.equal(getRealXmpjzkEnd(),
                        that.getRealXmpjzkEnd()) &&
                Objects.equal(getRealVipzkStart(),
                        that.getRealVipzkStart()) &&
                Objects.equal(getRealVipzkEnd(),
                        that.getRealVipzkEnd()) &&
                Objects.equal(getRealZyzkStart(),
                        that.getRealZyzkStart()) &&
                Objects.equal(getRealZyzkEnd(),
                        that.getRealZyzkEnd()) &&
                Objects.equal(getZdzkStart(),
                        that.getZdzkStart()) &&
                Objects.equal(getZdzkEnd(),
                        that.getZdzkEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getProjectid(),
                getQysjStart(),
                getQysjEnd(),
                getRegdateStart(),
                getRegdateEnd(),
                getSignarea(),
                getJazkStart(),
                getJazkEnd(),
                getXmpjzkStart(),
                getXmpjzkEnd(),
                getVipzkStart(),
                getVipzkEnd(),
                getZyzkStart(),
                getZyzkEnd(),
                getRealJazkStart(),
                getRealJazkEnd(),
                getRealXmpjzkStart(),
                getRealXmpjzkEnd(),
                getRealVipzkStart(),
                getRealVipzkEnd(),
                getRealZyzkStart(),
                getRealZyzkEnd(),
                getZdzkStart(),
                getZdzkEnd());
    }

    public ActualdiscountVO(String projectid,
                            String qysjStart,
                            String qysjEnd,
                            String regdateStart,
                            String regdateEnd,
                            String signarea,
                            String jazkStart,
                            String jazkEnd,
                            String xmpjzkStart,
                            String xmpjzkEnd,
                            String vipzkStart,
                            String vipzkEnd,
                            String zyzkStart,
                            String zyzkEnd,
                            String realJazkStart,
                            String realJazkEnd,
                            String realXmpjzkStart,
                            String realXmpjzkEnd,
                            String realVipzkStart,
                            String realVipzkEnd,
                            String realZyzkStart,
                            String realZyzkEnd,
                            String zdzkStart,
                            String zdzkEnd) {
        this.projectid = projectid;
        this.qysjStart = qysjStart;
        this.qysjEnd = qysjEnd;
        this.regdateStart = regdateStart;
        this.regdateEnd = regdateEnd;
        this.signarea = signarea;
        this.jazkStart = jazkStart;
        this.jazkEnd = jazkEnd;
        this.xmpjzkStart = xmpjzkStart;
        this.xmpjzkEnd = xmpjzkEnd;
        this.vipzkStart = vipzkStart;
        this.vipzkEnd = vipzkEnd;
        this.zyzkStart = zyzkStart;
        this.zyzkEnd = zyzkEnd;
        this.realJazkStart = realJazkStart;
        this.realJazkEnd = realJazkEnd;
        this.realXmpjzkStart = realXmpjzkStart;
        this.realXmpjzkEnd = realXmpjzkEnd;
        this.realVipzkStart = realVipzkStart;
        this.realVipzkEnd = realVipzkEnd;
        this.realZyzkStart = realZyzkStart;
        this.realZyzkEnd = realZyzkEnd;
        this.zdzkStart = zdzkStart;
        this.zdzkEnd = zdzkEnd;
    }

    public ActualdiscountVO(String qysjStart,
                            String qysjEnd,
                            String regdateStart,
                            String regdateEnd,
                            String signarea,
                            String jazkStart,
                            String jazkEnd,
                            String xmpjzkStart,
                            String xmpjzkEnd,
                            String vipzkStart,
                            String vipzkEnd,
                            String zyzkStart,
                            String zyzkEnd,
                            String realJazkStart,
                            String realJazkEnd,
                            String realXmpjzkStart,
                            String realXmpjzkEnd,
                            String realVipzkStart,
                            String realVipzkEnd,
                            String realZyzkStart,
                            String realZyzkEnd,
                            String zdzkStart,
                            String zdzkEnd) {
        this.qysjStart = qysjStart;
        this.qysjEnd = qysjEnd;
        this.regdateStart = regdateStart;
        this.regdateEnd = regdateEnd;
        this.signarea = signarea;
        this.jazkStart = jazkStart;
        this.jazkEnd = jazkEnd;
        this.xmpjzkStart = xmpjzkStart;
        this.xmpjzkEnd = xmpjzkEnd;
        this.vipzkStart = vipzkStart;
        this.vipzkEnd = vipzkEnd;
        this.zyzkStart = zyzkStart;
        this.zyzkEnd = zyzkEnd;
        this.realJazkStart = realJazkStart;
        this.realJazkEnd = realJazkEnd;
        this.realXmpjzkStart = realXmpjzkStart;
        this.realXmpjzkEnd = realXmpjzkEnd;
        this.realVipzkStart = realVipzkStart;
        this.realVipzkEnd = realVipzkEnd;
        this.realZyzkStart = realZyzkStart;
        this.realZyzkEnd = realZyzkEnd;
        this.zdzkStart = zdzkStart;
        this.zdzkEnd = zdzkEnd;
    }

    public ActualdiscountVO(String qysjEnd,
                            String regdateStart,
                            String regdateEnd,
                            String signarea,
                            String jazkStart,
                            String jazkEnd,
                            String xmpjzkStart,
                            String xmpjzkEnd,
                            String vipzkStart,
                            String vipzkEnd,
                            String zyzkStart,
                            String zyzkEnd,
                            String realJazkStart,
                            String realJazkEnd,
                            String realXmpjzkStart,
                            String realXmpjzkEnd,
                            String realVipzkStart,
                            String realVipzkEnd,
                            String realZyzkStart,
                            String realZyzkEnd,
                            String zdzkStart,
                            String zdzkEnd) {
        this.qysjEnd = qysjEnd;
        this.regdateStart = regdateStart;
        this.regdateEnd = regdateEnd;
        this.signarea = signarea;
        this.jazkStart = jazkStart;
        this.jazkEnd = jazkEnd;
        this.xmpjzkStart = xmpjzkStart;
        this.xmpjzkEnd = xmpjzkEnd;
        this.vipzkStart = vipzkStart;
        this.vipzkEnd = vipzkEnd;
        this.zyzkStart = zyzkStart;
        this.zyzkEnd = zyzkEnd;
        this.realJazkStart = realJazkStart;
        this.realJazkEnd = realJazkEnd;
        this.realXmpjzkStart = realXmpjzkStart;
        this.realXmpjzkEnd = realXmpjzkEnd;
        this.realVipzkStart = realVipzkStart;
        this.realVipzkEnd = realVipzkEnd;
        this.realZyzkStart = realZyzkStart;
        this.realZyzkEnd = realZyzkEnd;
        this.zdzkStart = zdzkStart;
        this.zdzkEnd = zdzkEnd;
    }

    public ActualdiscountVO(String regdateStart,
                            String regdateEnd,
                            String signarea,
                            String jazkStart,
                            String jazkEnd,
                            String xmpjzkStart,
                            String xmpjzkEnd,
                            String vipzkStart,
                            String vipzkEnd,
                            String zyzkStart,
                            String zyzkEnd,
                            String realJazkStart,
                            String realJazkEnd,
                            String realXmpjzkStart,
                            String realXmpjzkEnd,
                            String realVipzkStart,
                            String realVipzkEnd,
                            String realZyzkStart,
                            String realZyzkEnd,
                            String zdzkStart,
                            String zdzkEnd) {
        this.regdateStart = regdateStart;
        this.regdateEnd = regdateEnd;
        this.signarea = signarea;
        this.jazkStart = jazkStart;
        this.jazkEnd = jazkEnd;
        this.xmpjzkStart = xmpjzkStart;
        this.xmpjzkEnd = xmpjzkEnd;
        this.vipzkStart = vipzkStart;
        this.vipzkEnd = vipzkEnd;
        this.zyzkStart = zyzkStart;
        this.zyzkEnd = zyzkEnd;
        this.realJazkStart = realJazkStart;
        this.realJazkEnd = realJazkEnd;
        this.realXmpjzkStart = realXmpjzkStart;
        this.realXmpjzkEnd = realXmpjzkEnd;
        this.realVipzkStart = realVipzkStart;
        this.realVipzkEnd = realVipzkEnd;
        this.realZyzkStart = realZyzkStart;
        this.realZyzkEnd = realZyzkEnd;
        this.zdzkStart = zdzkStart;
        this.zdzkEnd = zdzkEnd;
    }

    public ActualdiscountVO(String signarea,
                            String jazkStart,
                            String jazkEnd,
                            String xmpjzkStart,
                            String xmpjzkEnd,
                            String vipzkStart,
                            String vipzkEnd,
                            String zyzkStart,
                            String zyzkEnd,
                            String realJazkStart,
                            String realJazkEnd,
                            String realXmpjzkStart,
                            String realXmpjzkEnd,
                            String realVipzkStart,
                            String realVipzkEnd,
                            String realZyzkStart,
                            String realZyzkEnd,
                            String zdzkStart,
                            String zdzkEnd) {
        this.signarea = signarea;
        this.jazkStart = jazkStart;
        this.jazkEnd = jazkEnd;
        this.xmpjzkStart = xmpjzkStart;
        this.xmpjzkEnd = xmpjzkEnd;
        this.vipzkStart = vipzkStart;
        this.vipzkEnd = vipzkEnd;
        this.zyzkStart = zyzkStart;
        this.zyzkEnd = zyzkEnd;
        this.realJazkStart = realJazkStart;
        this.realJazkEnd = realJazkEnd;
        this.realXmpjzkStart = realXmpjzkStart;
        this.realXmpjzkEnd = realXmpjzkEnd;
        this.realVipzkStart = realVipzkStart;
        this.realVipzkEnd = realVipzkEnd;
        this.realZyzkStart = realZyzkStart;
        this.realZyzkEnd = realZyzkEnd;
        this.zdzkStart = zdzkStart;
        this.zdzkEnd = zdzkEnd;
    }

    public ActualdiscountVO(String xmpjzkStart,
                            String xmpjzkEnd,
                            String vipzkStart,
                            String vipzkEnd,
                            String zyzkStart,
                            String zyzkEnd,
                            String realJazkStart,
                            String realJazkEnd,
                            String realXmpjzkStart,
                            String realXmpjzkEnd,
                            String realVipzkStart,
                            String realVipzkEnd,
                            String realZyzkStart,
                            String realZyzkEnd,
                            String zdzkStart,
                            String zdzkEnd) {
        this.xmpjzkStart = xmpjzkStart;
        this.xmpjzkEnd = xmpjzkEnd;
        this.vipzkStart = vipzkStart;
        this.vipzkEnd = vipzkEnd;
        this.zyzkStart = zyzkStart;
        this.zyzkEnd = zyzkEnd;
        this.realJazkStart = realJazkStart;
        this.realJazkEnd = realJazkEnd;
        this.realXmpjzkStart = realXmpjzkStart;
        this.realXmpjzkEnd = realXmpjzkEnd;
        this.realVipzkStart = realVipzkStart;
        this.realVipzkEnd = realVipzkEnd;
        this.realZyzkStart = realZyzkStart;
        this.realZyzkEnd = realZyzkEnd;
        this.zdzkStart = zdzkStart;
        this.zdzkEnd = zdzkEnd;
    }


}
