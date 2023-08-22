package com.ikang.idata.search.search.javaTest;

import com.alibaba.fastjson.JSONObject;
import com.ikang.idata.common.utils.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:minxin.fan@ikang.com">minxin.fan</a>
 * @description ${description}
 * @date 2022/9/21
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "聚合条件", description = "聚合条件")
public class AggregationCondition implements Serializable {


    private static final long serialVersionUID = -7638220080095291706L;
    @ApiModelProperty("字段code")
    private String code;
    @ApiModelProperty("字段Name")
    private String name;
    @ApiModelProperty("字段类型")
    private String dataType;
    @ApiModelProperty("字段格式化")
    private String format;
    @ApiModelProperty("拓展字段")
    private JSONObject expand;



    public AggregationCondition() {
    }

    public static List<AggregationCondition> parse(String condition) {
        if (StringUtil.isEmpty(condition)) {
            return new ArrayList<>();
        }
        return Stream.of(StringUtils.commaDelimitedListToStringArray(condition)).map(s ->
                s.contains(":") ? new AggregationCondition().setCode(s.split(":")[0]).setFormat(s.split(":")[1]) :
                        new AggregationCondition().setCode(s))
                .collect(Collectors.toList());

    }


}
