package com.atguigu.java.ai.langchain4j.config;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author atguigu-mqx
 * @ClassName MemoryChatAssistantConfig
 * @description: TODO
 * @date 2025年05月06日
 * @version: 1.0
 */
@Configuration
public class MemoryChatAssistantConfig {

    //  创建一个对象注入到spring 容器中;
    @Bean
    public MessageWindowChatMemory chatMemory(){
        return MessageWindowChatMemory.withMaxMessages(10);
    }
}
