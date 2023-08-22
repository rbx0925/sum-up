package com.ikang.idata.search.search;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author <a href="mailto:hao.liu-ext@ikang.com">hao.liu</a>
 * @description 拦截器
 * @date 2022/4/1 17:48
 */
@Component
@Slf4j
public class JsonPathInterceptor extends NopAnnotationIntrospector {
    private static final long serialVersionUID = 1817367048714479688L;


    public Object findDeserializer(Annotated am) {
        JsonPath path = am.getAnnotation(JsonPath.class);
        if (Objects.nonNull(path)) {
            return JsonPathDeserializer.class;
        }
        return super.findDeserializer(am);
    }

}
