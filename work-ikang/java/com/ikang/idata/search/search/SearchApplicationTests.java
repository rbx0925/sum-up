package com.ikang.idata.search.search;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.ikang.idata.search.search.entity.UserInfo;
import com.ikang.idata.search.search.support.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ActiveProfiles;

import static com.ikang.idata.search.search.TestJson.jsonToPojo;

@ActiveProfiles(value = {"test"})
@SpringBootTest
public class SearchApplicationTests {

    @Autowired
    private RedisCache redisCache;

    @Test
    void contextLoads() {
    }

    @Test
    public void testRedis() {
        redisCache.set("xxxxxtest", 1);

        System.out.println(redisCache.get("xxxxxtest") + "");
    }

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Autowired
    private Jackson2ObjectMapperBuilder builder;

    @Test
    public void testsss() throws JsonProcessingException {
        UserInfo info = new UserInfo();
        info.setAge("10");
        info.setName("kaishi");
        info.setPassword("2222");
        System.out.println("JSON.toJSONString(info) = " + JSON.toJSONString(info));


        String json = "{\n" +
                "    \"age\": \"10\",\n" +
                "    \"name\": \"kaishi\",\n" +
                "    \"password\": {\n" +
                "        \"value\": \"2222\"\n" +
                "    }\n" +
                "}";

        ObjectMapper mapper = builder.createXmlMapper(false).build();
        AnnotationIntrospector sis = mapper.getSerializationConfig().getAnnotationIntrospector();
        AnnotationIntrospector is1 = AnnotationIntrospectorPair.pair(sis, new JsonPathInterceptor());
        mapper.setAnnotationIntrospector(is1);

        System.out.println("jsonToPojo(json,UserInfo.class) = " + mapper.readValue(json, UserInfo.class));
    }

}
