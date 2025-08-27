package com.atguigu.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * @author atguigu-mqx
 * @ClassName SeparateChatAssistant
 * @description: TODO
 * @date 2025年05月06日
 * @version: 1.0
 */
@AiService(wiringMode = EXPLICIT
        , chatModel = "qwenChatModel"
        , chatMemoryProvider = "chatMemoryProvider" // 实现会话隔离
        , tools = "calculatorTools"
)
public interface SeparateChatAssistant {

    /**
     * 聊天方法
     * @param memoryId      区分会话
     * @param userMessage   用户发送的消息
     * @return
     */
    //  @SystemMessage("你是我的好朋友，请用东北话回答问题。今天是{{current_date}}")//系统消息提示词
    @SystemMessage(fromResource = "my-prompt-template.txt")
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    /**
     * 聊天方法
     * @param memoryId
     * @param userMessage
     * @return
     */
    @UserMessage("你是我的好朋友，请用粤语回答问题。{{message}}")
    String chat2(@MemoryId int memoryId, @V("message") String userMessage);

    /**
     * 系统提示词与参数提示词
     * @param memoryId
     * @param userMessage
     * @param username
     * @param age
     * @return
     */
    @SystemMessage(fromResource = "my-prompt-template3.txt")
    String chat3(
            @MemoryId int memoryId,
            @UserMessage String userMessage,
            @V("username") String username,
            @V("age") int age
    );
}

