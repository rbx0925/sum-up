package com.atguigu.java.ai.langchain4j.assistant;

import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * @author atguigu-mqx
 * @ClassName Assistant
 * @description: TODO
 * @date 2025年05月06日
 * @version: 1.0
 */
@AiService(wiringMode = EXPLICIT, chatModel = "qwenChatModel")
public interface Assistant {
    /**
     * 聊天方法
     * @param userMessage
     * @return
     */
    String chat(String userMessage);
}
