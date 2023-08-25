package com.doori.hackerthon.repository.mongo;

import com.doori.hackerthon.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}
