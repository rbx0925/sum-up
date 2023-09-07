package com.ikang.idata.search.search;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author kdyzm
 * @date 2021/11/18
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class JsonPathDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {

    private JavaType type;

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Object value = p.getCurrentValue();
        log.info("CurrentValue:{}", value);
        Object o = JSONUtil.getByPath(JSONUtil.parseObj(value), type.getRawClass().getDeclaredAnnotation(JsonPath.class).value());
        return Convert.convert(type,o);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        //beanProperty is null when the type to deserialize is the top-level type or a generic type, not a type of a bean property



        JavaType type = deserializationContext.getContextualType() != null
                ? deserializationContext.getContextualType()
                : beanProperty.getMember().getType();
        return new JsonPathDeserializer(type);
    }
}
