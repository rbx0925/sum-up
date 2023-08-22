package com.ikang.idata.search.search;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

//@Configuration
public class JsonConfig {

    /**
     * @param builder
     * @return
     * @link {https://stackoverflow.com/questions/34965201/customize-jackson-objectmapper-to-read-custom-annotation-and-mask-fields-annotat}
     */
//    @Bean
//    @Primary
//    ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper mapper = builder.createXmlMapper(false).build();
//        AnnotationIntrospector sis = mapper.getSerializationConfig().getAnnotationIntrospector();
//        AnnotationIntrospector is1 = AnnotationIntrospectorPair.pair(sis, new JsonPathInterceptor());
//        mapper.setAnnotationIntrospector(is1);
//        return mapper;
//    }
}