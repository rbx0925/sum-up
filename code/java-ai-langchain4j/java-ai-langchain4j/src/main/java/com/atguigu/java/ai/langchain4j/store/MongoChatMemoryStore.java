package com.atguigu.java.ai.langchain4j.store;

import com.atguigu.java.ai.langchain4j.bean.ChatMessages;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author atguigu-mqx
 * @ClassName MongoChatMemoryStore
 * @description: TODO
 * @date 2025年05月06日
 * @version: 1.0
 */
@Component
public class MongoChatMemoryStore implements ChatMemoryStore {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        //  通过memoryId查询mongodb数据集合;
        Query query = Query.query(Criteria.where("memoryId").is(memoryId));
        ChatMessages chatMessages = mongoTemplate.findOne(query, ChatMessages.class);
        //  判断chatMessages 对象是否为空
        if (null == chatMessages) {
            //  当第一次查询的时候，MongoDB中应该是没有数据的。不能给null, 需要返回一个ArrayList<ChatMessage>
            return new ArrayList<ChatMessage>();
        }
        //  获取数据
        List<ChatMessage> chatMessageList = ChatMessageDeserializer.messagesFromJson(chatMessages.getContent());
        //  返回数据
        return chatMessageList;
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        Criteria criteria = Criteria.where("memoryId").is(memoryId);
        Query query = new Query(criteria);

        Update update = new Update();
        String messagesToJson = ChatMessageSerializer.messagesToJson(messages);
        //  更新content 字段对应的数据；
        update.set("content", messagesToJson);

        //根据query条件能查询出文档，则修改文档；否则新增文档
        mongoTemplate.upsert(query, update, ChatMessages.class);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        //  根据memoryId 删除数据;
        Criteria criteria = Criteria.where("memoryId").is(memoryId);
        Query query = new Query(criteria);
        mongoTemplate.remove(query, ChatMessages.class);
    }
}
