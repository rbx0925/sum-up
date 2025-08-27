package com.atguigu.java.ai.langchain4j;

import com.atguigu.java.ai.langchain4j.assistant.Assistant;
import com.atguigu.java.ai.langchain4j.assistant.MemoryChatAssistant;
import com.atguigu.java.ai.langchain4j.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ChatMemoryTest {

    @Autowired
    private Assistant assistant;

    @Autowired
    private QwenChatModel qwenChatModel;

    @Autowired
    private MemoryChatAssistant memoryChatAssistant;

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    /**
     * 多参数设置
     */
    @Test
    public void testUserInfo() {
        String answer = separateChatAssistant.chat3(2, "我是谁，我多大了", "翠花", 18);
        System.out.println(answer);
    }


    /**
     * 测试多个参数；使用@V注解与@MemoryId注解进行参数传递；
     */
    @Test
    public void testV() {
        String answer1 = separateChatAssistant.chat2(1, "我是华仔");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat2(1, "我是谁");
        System.out.println(answer2);
    }

    @Test
    public void testUserMessage() {
        String answer = memoryChatAssistant.chat("我是华仔");
        System.out.println(answer);
    }

    /**
     * 会话隔离
     */
    @Test
    public void testChatMemory5() {
        String answer1 = separateChatAssistant.chat(1,"我是华仔");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat(1,"我是谁");
        System.out.println(answer2);
        String answer3 = separateChatAssistant.chat(2,"我是谁");
        System.out.println(answer3);
    }

    //  只用AIService 注解实现的！
    @Test
    public void testChatMemory4() {
        String answer1 = memoryChatAssistant.chat("我是华仔");
        System.out.println(answer1);
        String answer2 = memoryChatAssistant.chat("我是谁");
        System.out.println(answer2);
    }

    @Test
    public void testChatMemory3() {
        //  创建记忆对象
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        //  需要串讲对象
        Assistant assistant = AiServices.builder(Assistant.class)
                .chatMemory(chatMemory)
                .chatLanguageModel(qwenChatModel)
                .build();
        //  对话聊天;
        String answer1 = assistant.chat("我是华仔");
        System.out.println(answer1);

        String answer2 = assistant.chat("我是谁");
        System.out.println(answer2);

    }
    @Test
    public void testChatMemory2() {
        //  体现了AiService 的原理!
        UserMessage userMessage1 = UserMessage.userMessage("我是华仔");
        ChatResponse chatResponse1 = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage1 = chatResponse1.aiMessage();
        System.out.println(aiMessage1.text());

        UserMessage userMessage2 = UserMessage.userMessage("我是谁");
        ChatResponse chatResponse2= qwenChatModel.chat(Arrays.asList(userMessage1,aiMessage1,userMessage2));
        AiMessage aiMessage2 = chatResponse2.aiMessage();
        System.out.println(aiMessage2.text());
    }

    @Test
    public void testChatMemory() {

        String answer1 = assistant.chat("我是华仔");
        System.out.println(answer1);

        String answer2 = assistant.chat("我是谁");
        System.out.println(answer2);
    }
}