package com.ikang.idata.common.utils;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * URLUtil
 *
 * @author haigang.jia@ikang.com
 * @date 2019年09月23日
 */
public class URLUtil {


    @Getter
    public static class UrlEntity {

        /**
         * 基础url
         */
        private String baseUrl;
        /**
         * url参数
         */
        private Map<String, String> params;
    }

    public static UrlEntity parse(String url) {

        UrlEntity entity = new UrlEntity();
        if (StrUtil.isBlank(url)) {
            return entity;
        }
        String[] queryStr = url.split("\\?");
        entity.baseUrl = queryStr[0];
        if (queryStr.length == 1) {
            return entity;
        }
        String[] params = queryStr[1].split("&");
        entity.params = new HashMap<>(5);
        for (String param : params) {
            String[] keyValue = param.split("=");
            entity.params.put(keyValue[0], keyValue[1]);
        }
        return entity;
    }
}
