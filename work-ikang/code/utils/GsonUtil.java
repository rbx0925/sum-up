package com.ikang.idata.common.utils;

import com.alibaba.fastjson.JSON;
import com.github.wnameless.json.flattener.JsonFlattener;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ikang.idata.common.entity.CheckTotalDataAnalysis;
import com.ikang.idata.common.entity.vo.CheckTotalDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:wenyue.gao@ikang.com">wenyue.gao</a>
 * @description
 * @date 2022/4/1
 */
public class GsonUtil {

    /**
     * 方法描述: {"age1":{"value":1}} 转换成{"age1":1}
     *{"age1":{"value":1}} 转换成 {"age1_value":1}
     * {"age1_value":1} 转换成 {"age1":1}
     * @param source
     * @param targetClass
     * @return  T
     * @author  wenyue.gao@ikang.com
     * @date    2022/4/1 15:49
     */
    public static <T> T beanConvert (Object source, Class<T> targetClass) {
        Gson gson = new Gson();
        Map<String, Object> flatMap1 = new JsonFlattener(gson.toJson(source)).withSeparator('_').flattenAsMap();
        Map<String, Object> tempMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : flatMap1.entrySet()) {
            tempMap.put(entry.getKey().replace("_value", "").replace("[\"","").replace("\"]",""), entry.getValue());
        }
        return  gson.fromJson(gson.toJson(tempMap), targetClass);
    }

    public static void main(String[] args) {
        CheckTotalDTO dto = new CheckTotalDTO();
        CheckTotalDTO.Age1 age1 = new CheckTotalDTO.Age1();
        age1.setValue(new BigDecimal(1));
        dto.setAge1(age1);
        /*  Gson gson = new Gson();
      String flatten = JsonFlattener.flatten(gson.toJson(dto));
        System.out.println(flatten);
        CheckTotalDataAnalysis result =  gson.fromJson(flatten.replaceAll(".value", ""), CheckTotalDataAnalysis.class);
        System.out.println(JSON.toJSONString(result));*/
        CheckTotalDataAnalysis analysis = beanConvert(dto, CheckTotalDataAnalysis.class);
        System.out.println(JSON.toJSONString(analysis));
    }
}
