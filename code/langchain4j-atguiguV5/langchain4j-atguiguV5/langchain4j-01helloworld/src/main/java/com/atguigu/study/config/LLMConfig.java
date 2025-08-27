package com.atguigu.study.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther zzyybs@126.com
 * @Date 2025-05-27 22:04
 * @Description: 知识出处 https://docs.langchain4j.dev/get-started
 */
@Configuration
public class LLMConfig
{
    @Bean
    public ChatModel chatModelQwen()
    {
        return OpenAiChatModel.builder()
                        .apiKey("sk-31a35329f3344f66af5b8d0876b0a318")
                        .modelName("qwen-plus")
                        .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

}
