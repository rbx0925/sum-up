package com.ikang.idata.common.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * DateListBean
 * 同比环比时间
 * @author  <a href="mailto:wenyue.gao@ikang.com">wenyue.gao</a>
 * @date  2022/3/20
 * @version 1.0.0
 */
@Data
public class DateListBean {
    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    String startDate;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    String endDate;
    /**
     * 环比开始时间
     */
    @ApiModelProperty("环比开始时间")
    String startDateMoM;
    /**
     * 环比结束时间
     */
    @ApiModelProperty("环比结束时间")
    String endDateMoM;
    /**
     * 同比开始时间
     */
    @ApiModelProperty("同比开始时间")
    String startDateYoY;
    /**
     * 同比结束时间
     */
    @ApiModelProperty("同比结束时间")
    String endDateYoY;


}

