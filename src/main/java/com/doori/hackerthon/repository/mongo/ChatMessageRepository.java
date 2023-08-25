package com.doori.hackerthon.repository.mongo;

import com.doori.hackerthon.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findTop2ByUserId(String userId);
}
