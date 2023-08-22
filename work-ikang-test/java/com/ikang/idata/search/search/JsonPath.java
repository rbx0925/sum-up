package com.ikang.idata.search.search;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description jsonpa
 * @date 2022/4/1 17:52
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
@Documented
public @interface JsonPath {
    String value() default "";
}
