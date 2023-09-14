//package com.rbx.utils;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.ToString;
//import org.springframework.lang.Nullable;
//
//import java.time.Duration;
//
//@Getter
//@ToString
//@RequiredArgsConstructor
//public class CacheKey  {
//
//    /**
//     * redis key
//     */
//    private final String key;
//
//    /**
//     * 超时时间 秒
//     */
//    @Nullable
//    private final Duration expire;
//
//    public CacheKey(String key) {
//        this(key, null);
//    }
//
//}