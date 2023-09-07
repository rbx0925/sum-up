package com.ikang.idata.common.utils;

import com.ikang.idata.common.enums.ResultEnum;
import com.ikang.idata.common.exceptions.BusinessException;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @version 1.0
 * @date 2021/11/8 10:17
 */
public class CheckUtil {

    public static void isTrue(Boolean b, Exception e) throws Exception {
        if (b) {
            throw e;
        }
    }

    public static <T> void check(T key, Predicate<T> predicate, Consumer<T> consumer) {
        if (predicate.test(key)) {
            consumer.accept(key);
        }
    }

    public static void isTrue(Boolean b, InternalProcess process) {
        if (b) {
            process.process();
        }
    }

    public static <T> T checkResult(Result<T> result) {
        if (!ResultEnum.SUCCESS.getCode().equals(result.getCode())) {
            throw new BusinessException(result);
        }
        return result.getData();
    }

    @FunctionalInterface
    public interface InternalProcess {
        /**
         * 默认方法
         */
        void process();
    }
}