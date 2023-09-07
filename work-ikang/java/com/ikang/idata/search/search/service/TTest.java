package com.ikang.idata.search.search.service;

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ikang.idata.common.consts.MagicConst;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description 测试
 * @date 2022/7/25 13:31
 */
public class TTest {
    /**
     * 可行
     * @param listMap table data
     * @param columnNames  header
     * @return
     * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
     * @date: 2022/7/25 13:58
     */
    private static List<List<Object>> dataList(List<Map<String, Object>> listMap, List<Map<String, Object>> columnNames) {
        List<List<Object>> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(listMap)) {
            for (Map<String, Object> map : listMap) {
                List<Object> data = new ArrayList<>(columnNames.size());
                columnNames.forEach(e -> {
                    String col = (String) e.get("returnDataCode");
                    if (null != map.get(col)) {
                        String valueStr = map.get(col).toString();
                        if (valueStr.matches(MagicConst.THOUSANDS_REGEX)) {
                            data.add(valueStr.replace(",", ""));
                        } else {
                            data.add(valueStr);
                        }
                    } else {
                        data.add("");
                    }
                });
                list.add(data);
            }
        }
        return list;
    }

    public static void main(String[] args) {

        List<Map<String, Object>> maps = Lists.newArrayList(
                new HashMap<String, Object>() {
                    private static final long serialVersionUID = -5676344021916829552L;

                    {
                        put("name", "卢斌");
                        put("age", 18);
                    }
                });
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = -5676344021916829552L;

            {
                put("returnDataCode", "name");
            }
        };
        List<Map<String, Object>> columnNames = Lists.newArrayList(map);
        System.out.println(dataList((List<Map<String, Object>>) maps, columnNames));
    }
}
