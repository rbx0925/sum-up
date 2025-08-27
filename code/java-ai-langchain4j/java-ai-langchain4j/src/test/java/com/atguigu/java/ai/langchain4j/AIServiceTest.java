package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author atguigu-mqx
 * @ClassName AIServiceTest
 * @description: TODO
 * @date 2025年05月06日
 * @version: 1.0
 */
@SpringBootTest
public class AIServiceTest {

    @Autowired
    private QwenChatModel qwenChatModel;

    @Autowired
    private Assistant assistant;

    @Test
    public void testQwen() {
        //  创建接口对象
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        String answer = assistant.chat("你是谁呀?");
        System.out.println(answer); // Hello, how can I help you?
    }
    @Test
    public void tesAssistant() {
        //  从spring 容器中获取对象
        String answer = assistant.chat("你是谁呀?");
        System.out.println(answer); // Hello, how can I help you?
    }
}
