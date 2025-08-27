package com.atguigu.java.ai.langchain4j.assistant;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * @author atguigu-mqx
 * @ClassName MemoryChatAssistant
 * @description: TODO
 * @date 2025年05月06日
 * @version: 1.0
 */
@AiService(wiringMode = EXPLICIT
        , chatModel = "qwenChatModel"
        , chatMemory = "chatMemory" // 使用自定义的MemoryChatMemory
)
public interface MemoryChatAssistant {
    /**
     * 聊天方法
     * @param userMessage
     * @return
     */
    @UserMessage("你是我的好朋友，请用上海话回答问题，并且添加一些表情符号。 {{it}}")
    String chat(String userMessage);
}
